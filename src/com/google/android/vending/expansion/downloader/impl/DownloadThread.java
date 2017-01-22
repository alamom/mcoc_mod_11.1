package com.google.android.vending.expansion.downloader.impl;

import android.content.Context;
import android.net.Proxy;
import android.os.Build;
import android.os.Build.VERSION;
import android.util.Log;
import com.google.android.vending.expansion.downloader.Helpers;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Locale;
import java.util.Random;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.HttpGet;

public class DownloadThread
{
  private Context mContext;
  private final DownloadsDB mDB;
  private DownloadInfo mInfo;
  private final DownloadNotification mNotification;
  private DownloaderService mService;
  private String mUserAgent;
  
  public DownloadThread(DownloadInfo paramDownloadInfo, DownloaderService paramDownloaderService, DownloadNotification paramDownloadNotification)
  {
    this.mContext = paramDownloaderService;
    this.mInfo = paramDownloadInfo;
    this.mService = paramDownloaderService;
    this.mNotification = paramDownloadNotification;
    this.mDB = DownloadsDB.getDB(paramDownloaderService);
    this.mUserAgent = ("APKXDL (Linux; U; Android " + Build.VERSION.RELEASE + ";" + Locale.getDefault().toString() + "; " + Build.DEVICE + "/" + Build.ID + ")" + paramDownloaderService.getPackageName());
  }
  
  private void addRequestHeaders(InnerState paramInnerState, HttpGet paramHttpGet)
  {
    if (paramInnerState.mContinuingDownload)
    {
      if (paramInnerState.mHeaderETag != null) {
        paramHttpGet.addHeader("If-Match", paramInnerState.mHeaderETag);
      }
      paramHttpGet.addHeader("Range", "bytes=" + paramInnerState.mBytesSoFar + "-");
    }
  }
  
  private boolean cannotResume(InnerState paramInnerState)
  {
    if ((paramInnerState.mBytesSoFar > 0) && (paramInnerState.mHeaderETag == null)) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  private void checkConnectivity(State paramState)
    throws DownloadThread.StopRequest
  {
    switch (this.mService.getNetworkAvailabilityState(this.mDB))
    {
    case 1: 
    case 4: 
    default: 
      return;
    case 2: 
      throw new StopRequest(195, "waiting for network to return");
    case 6: 
      throw new StopRequest(196, "waiting for wifi or for download over cellular to be authorized");
    case 5: 
      throw new StopRequest(195, "roaming is not allowed");
    }
    throw new StopRequest(197, "waiting for wifi");
  }
  
  private void checkPausedOrCanceled(State paramState)
    throws DownloadThread.StopRequest
  {
    if (this.mService.getControl() == 1) {}
    switch (this.mService.getStatus())
    {
    default: 
      return;
    }
    throw new StopRequest(this.mService.getStatus(), "download paused");
  }
  
  private void cleanupDestination(State paramState, int paramInt)
  {
    closeDestination(paramState);
    if ((paramState.mFilename != null) && (DownloaderService.isStatusError(paramInt)))
    {
      new File(paramState.mFilename).delete();
      paramState.mFilename = null;
    }
  }
  
  private void closeDestination(State paramState)
  {
    try
    {
      if (paramState.mStream != null)
      {
        paramState.mStream.close();
        paramState.mStream = null;
      }
      return;
    }
    catch (IOException paramState)
    {
      for (;;) {}
    }
  }
  
  private void executeDownload(State paramState, AndroidHttpClient paramAndroidHttpClient, HttpGet paramHttpGet)
    throws DownloadThread.StopRequest, DownloadThread.RetryDownload
  {
    InnerState localInnerState = new InnerState(null);
    byte[] arrayOfByte = new byte['က'];
    checkPausedOrCanceled(paramState);
    setupDestinationFile(paramState, localInnerState);
    addRequestHeaders(localInnerState, paramHttpGet);
    checkConnectivity(paramState);
    this.mNotification.onDownloadStateChanged(3);
    paramAndroidHttpClient = sendRequest(paramState, paramAndroidHttpClient, paramHttpGet);
    handleExceptionalStatus(paramState, localInnerState, paramAndroidHttpClient);
    processResponseHeaders(paramState, localInnerState, paramAndroidHttpClient);
    paramAndroidHttpClient = openResponseEntity(paramState, paramAndroidHttpClient);
    this.mNotification.onDownloadStateChanged(4);
    transferData(paramState, localInnerState, arrayOfByte, paramAndroidHttpClient);
  }
  
  private void finalizeDestinationFile(State paramState)
    throws DownloadThread.StopRequest
  {
    syncDestination(paramState);
    String str = paramState.mFilename;
    Object localObject = Helpers.generateSaveFileName(this.mService, this.mInfo.mFileName);
    if (!paramState.mFilename.equals(localObject))
    {
      paramState = new File(str);
      localObject = new File((String)localObject);
      if ((this.mInfo.mTotalBytes != -1L) && (this.mInfo.mCurrentBytes == this.mInfo.mTotalBytes))
      {
        if (!paramState.renameTo((File)localObject)) {
          throw new StopRequest(492, "unable to finalize destination file");
        }
      }
      else {
        throw new StopRequest(487, "file delivered with incorrect size. probably due to network not browser configured");
      }
    }
  }
  
  private int getFinalStatusForHttpError(State paramState)
  {
    int i;
    if (this.mService.getNetworkAvailabilityState(this.mDB) != 1) {
      i = 195;
    }
    for (;;)
    {
      return i;
      if (this.mInfo.mNumFailed < 5)
      {
        paramState.mCountRetry = true;
        i = 194;
      }
      else
      {
        Log.w("LVLDL", "reached max retries for " + this.mInfo.mNumFailed);
        i = 495;
      }
    }
  }
  
  private void handleEndOfStream(State paramState, InnerState paramInnerState)
    throws DownloadThread.StopRequest
  {
    this.mInfo.mCurrentBytes = paramInnerState.mBytesSoFar;
    this.mDB.updateDownload(this.mInfo);
    int i;
    if ((paramInnerState.mHeaderContentLength != null) && (paramInnerState.mBytesSoFar != Integer.parseInt(paramInnerState.mHeaderContentLength))) {
      i = 1;
    }
    while (i != 0) {
      if (cannotResume(paramInnerState))
      {
        throw new StopRequest(489, "mismatched content length");
        i = 0;
      }
      else
      {
        throw new StopRequest(getFinalStatusForHttpError(paramState), "closed socket before end of file");
      }
    }
  }
  
  private void handleExceptionalStatus(State paramState, InnerState paramInnerState, HttpResponse paramHttpResponse)
    throws DownloadThread.StopRequest, DownloadThread.RetryDownload
  {
    int j = paramHttpResponse.getStatusLine().getStatusCode();
    if ((j == 503) && (this.mInfo.mNumFailed < 5)) {
      handleServiceUnavailable(paramState, paramHttpResponse);
    }
    if ((j == 301) || (j == 302) || (j == 303) || (j == 307)) {
      handleRedirect(paramState, paramHttpResponse, j);
    }
    int i;
    if (paramInnerState.mContinuingDownload)
    {
      i = 206;
      if (j == i) {
        break label114;
      }
      handleOtherStatus(paramState, paramInnerState, j);
    }
    for (;;)
    {
      return;
      i = 200;
      break;
      label114:
      paramState.mRedirectCount = 0;
    }
  }
  
  private void handleOtherStatus(State paramState, InnerState paramInnerState, int paramInt)
    throws DownloadThread.StopRequest
  {
    int i;
    if (DownloaderService.isStatusError(paramInt)) {
      i = paramInt;
    }
    for (;;)
    {
      throw new StopRequest(i, "http error " + paramInt);
      if ((paramInt >= 300) && (paramInt < 400)) {
        i = 493;
      } else if ((paramInnerState.mContinuingDownload) && (paramInt == 200)) {
        i = 489;
      } else {
        i = 494;
      }
    }
  }
  
  private void handleRedirect(State paramState, HttpResponse paramHttpResponse, int paramInt)
    throws DownloadThread.StopRequest, DownloadThread.RetryDownload
  {
    if (paramState.mRedirectCount >= 5) {
      throw new StopRequest(497, "too many redirects");
    }
    Header localHeader = paramHttpResponse.getFirstHeader("Location");
    if (localHeader == null) {
      return;
    }
    try
    {
      paramHttpResponse = new java/net/URI;
      paramHttpResponse.<init>(this.mInfo.mUri);
      URI localURI = new java/net/URI;
      localURI.<init>(localHeader.getValue());
      paramHttpResponse = paramHttpResponse.resolve(localURI).toString();
      paramState.mRedirectCount += 1;
      paramState.mRequestUri = paramHttpResponse;
      if ((paramInt == 301) || (paramInt == 303)) {
        paramState.mNewUri = paramHttpResponse;
      }
      throw new RetryDownload(null);
    }
    catch (URISyntaxException paramState)
    {
      throw new StopRequest(495, "Couldn't resolve redirect URI");
    }
  }
  
  private void handleServiceUnavailable(State paramState, HttpResponse paramHttpResponse)
    throws DownloadThread.StopRequest
  {
    paramState.mCountRetry = true;
    paramHttpResponse = paramHttpResponse.getFirstHeader("Retry-After");
    if (paramHttpResponse != null) {}
    try
    {
      paramState.mRetryAfter = Integer.parseInt(paramHttpResponse.getValue());
      if (paramState.mRetryAfter < 0) {}
      for (paramState.mRetryAfter = 0;; paramState.mRetryAfter *= 1000)
      {
        throw new StopRequest(194, "got 503 Service Unavailable, will retry later");
        if (paramState.mRetryAfter >= 30) {
          break;
        }
        paramState.mRetryAfter = 30;
        paramState.mRetryAfter += Helpers.sRandom.nextInt(31);
      }
    }
    catch (NumberFormatException paramState)
    {
      for (;;)
      {
        continue;
        if (paramState.mRetryAfter > 86400) {
          paramState.mRetryAfter = 86400;
        }
      }
    }
  }
  
  private static final boolean isLocalHost(String paramString)
  {
    boolean bool2 = false;
    boolean bool1;
    if (paramString == null) {
      bool1 = bool2;
    }
    for (;;)
    {
      return bool1;
      try
      {
        paramString = URI.create(paramString).getHost();
        bool1 = bool2;
        if (paramString != null) {
          if ((!paramString.equalsIgnoreCase("localhost")) && (!paramString.equals("127.0.0.1")))
          {
            boolean bool3 = paramString.equals("[::1]");
            bool1 = bool2;
            if (!bool3) {}
          }
          else
          {
            bool1 = true;
          }
        }
      }
      catch (IllegalArgumentException paramString)
      {
        bool1 = bool2;
      }
    }
  }
  
  private void logNetworkState()
  {
    StringBuilder localStringBuilder = new StringBuilder().append("Net ");
    if (this.mService.getNetworkAvailabilityState(this.mDB) == 1) {}
    for (String str = "Up";; str = "Down")
    {
      Log.i("LVLDL", str);
      return;
    }
  }
  
  private void notifyDownloadCompleted(int paramInt1, boolean paramBoolean1, int paramInt2, int paramInt3, boolean paramBoolean2, String paramString)
  {
    updateDownloadDatabase(paramInt1, paramBoolean1, paramInt2, paramInt3, paramBoolean2, paramString);
    if (DownloaderService.isStatusCompleted(paramInt1)) {}
  }
  
  private InputStream openResponseEntity(State paramState, HttpResponse paramHttpResponse)
    throws DownloadThread.StopRequest
  {
    try
    {
      paramHttpResponse = paramHttpResponse.getEntity().getContent();
      return paramHttpResponse;
    }
    catch (IOException paramHttpResponse)
    {
      logNetworkState();
      throw new StopRequest(getFinalStatusForHttpError(paramState), "while getting entity: " + paramHttpResponse.toString(), paramHttpResponse);
    }
  }
  
  /* Error */
  private void processResponseHeaders(State paramState, InnerState paramInnerState, HttpResponse paramHttpResponse)
    throws DownloadThread.StopRequest
  {
    // Byte code:
    //   0: aload_2
    //   1: getfield 106	com/google/android/vending/expansion/downloader/impl/DownloadThread$InnerState:mContinuingDownload	Z
    //   4: ifeq +4 -> 8
    //   7: return
    //   8: aload_0
    //   9: aload_1
    //   10: aload_2
    //   11: aload_3
    //   12: invokespecial 460	com/google/android/vending/expansion/downloader/impl/DownloadThread:readResponseHeaders	(Lcom/google/android/vending/expansion/downloader/impl/DownloadThread$State;Lcom/google/android/vending/expansion/downloader/impl/DownloadThread$InnerState;Lorg/apache/http/HttpResponse;)V
    //   15: aload_1
    //   16: aload_0
    //   17: getfield 41	com/google/android/vending/expansion/downloader/impl/DownloadThread:mService	Lcom/google/android/vending/expansion/downloader/impl/DownloaderService;
    //   20: aload_0
    //   21: getfield 39	com/google/android/vending/expansion/downloader/impl/DownloadThread:mInfo	Lcom/google/android/vending/expansion/downloader/impl/DownloadInfo;
    //   24: getfield 241	com/google/android/vending/expansion/downloader/impl/DownloadInfo:mFileName	Ljava/lang/String;
    //   27: aload_0
    //   28: getfield 39	com/google/android/vending/expansion/downloader/impl/DownloadThread:mInfo	Lcom/google/android/vending/expansion/downloader/impl/DownloadInfo;
    //   31: getfield 257	com/google/android/vending/expansion/downloader/impl/DownloadInfo:mTotalBytes	J
    //   34: invokevirtual 464	com/google/android/vending/expansion/downloader/impl/DownloaderService:generateSaveFile	(Ljava/lang/String;J)Ljava/lang/String;
    //   37: putfield 168	com/google/android/vending/expansion/downloader/impl/DownloadThread$State:mFilename	Ljava/lang/String;
    //   40: new 189	java/io/FileOutputStream
    //   43: astore_3
    //   44: aload_3
    //   45: aload_1
    //   46: getfield 168	com/google/android/vending/expansion/downloader/impl/DownloadThread$State:mFilename	Ljava/lang/String;
    //   49: invokespecial 465	java/io/FileOutputStream:<init>	(Ljava/lang/String;)V
    //   52: aload_1
    //   53: aload_3
    //   54: putfield 187	com/google/android/vending/expansion/downloader/impl/DownloadThread$State:mStream	Ljava/io/FileOutputStream;
    //   57: aload_0
    //   58: aload_1
    //   59: aload_2
    //   60: invokespecial 468	com/google/android/vending/expansion/downloader/impl/DownloadThread:updateDatabaseFromHeaders	(Lcom/google/android/vending/expansion/downloader/impl/DownloadThread$State;Lcom/google/android/vending/expansion/downloader/impl/DownloadThread$InnerState;)V
    //   63: aload_0
    //   64: aload_1
    //   65: invokespecial 207	com/google/android/vending/expansion/downloader/impl/DownloadThread:checkConnectivity	(Lcom/google/android/vending/expansion/downloader/impl/DownloadThread$State;)V
    //   68: goto -61 -> 7
    //   71: astore_1
    //   72: new 17	com/google/android/vending/expansion/downloader/impl/DownloadThread$StopRequest
    //   75: dup
    //   76: aload_0
    //   77: aload_1
    //   78: getfield 471	com/google/android/vending/expansion/downloader/impl/DownloaderService$GenerateSaveFileError:mStatus	I
    //   81: aload_1
    //   82: getfield 474	com/google/android/vending/expansion/downloader/impl/DownloaderService$GenerateSaveFileError:mMessage	Ljava/lang/String;
    //   85: invokespecial 143	com/google/android/vending/expansion/downloader/impl/DownloadThread$StopRequest:<init>	(Lcom/google/android/vending/expansion/downloader/impl/DownloadThread;ILjava/lang/String;)V
    //   88: athrow
    //   89: astore_3
    //   90: new 174	java/io/File
    //   93: dup
    //   94: aload_0
    //   95: getfield 41	com/google/android/vending/expansion/downloader/impl/DownloadThread:mService	Lcom/google/android/vending/expansion/downloader/impl/DownloaderService;
    //   98: invokestatic 478	com/google/android/vending/expansion/downloader/Helpers:getSaveFilePath	(Landroid/content/Context;)Ljava/lang/String;
    //   101: invokespecial 177	java/io/File:<init>	(Ljava/lang/String;)V
    //   104: astore 4
    //   106: aload 4
    //   108: invokevirtual 481	java/io/File:mkdirs	()Z
    //   111: ifeq -54 -> 57
    //   114: new 189	java/io/FileOutputStream
    //   117: astore 4
    //   119: aload 4
    //   121: aload_1
    //   122: getfield 168	com/google/android/vending/expansion/downloader/impl/DownloadThread$State:mFilename	Ljava/lang/String;
    //   125: invokespecial 465	java/io/FileOutputStream:<init>	(Ljava/lang/String;)V
    //   128: aload_1
    //   129: aload 4
    //   131: putfield 187	com/google/android/vending/expansion/downloader/impl/DownloadThread$State:mStream	Ljava/io/FileOutputStream;
    //   134: goto -77 -> 57
    //   137: astore_1
    //   138: new 17	com/google/android/vending/expansion/downloader/impl/DownloadThread$StopRequest
    //   141: dup
    //   142: aload_0
    //   143: sipush 492
    //   146: new 53	java/lang/StringBuilder
    //   149: dup
    //   150: invokespecial 54	java/lang/StringBuilder:<init>	()V
    //   153: ldc_w 483
    //   156: invokevirtual 60	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   159: aload_3
    //   160: invokevirtual 484	java/io/FileNotFoundException:toString	()Ljava/lang/String;
    //   163: invokevirtual 60	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   166: invokevirtual 97	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   169: aload_3
    //   170: invokespecial 451	com/google/android/vending/expansion/downloader/impl/DownloadThread$StopRequest:<init>	(Lcom/google/android/vending/expansion/downloader/impl/DownloadThread;ILjava/lang/String;Ljava/lang/Throwable;)V
    //   173: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	174	0	this	DownloadThread
    //   0	174	1	paramState	State
    //   0	174	2	paramInnerState	InnerState
    //   0	174	3	paramHttpResponse	HttpResponse
    //   104	26	4	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   15	40	71	com/google/android/vending/expansion/downloader/impl/DownloaderService$GenerateSaveFileError
    //   40	57	89	java/io/FileNotFoundException
    //   106	134	137	java/lang/Exception
  }
  
  private int readFromResponse(State paramState, InnerState paramInnerState, byte[] paramArrayOfByte, InputStream paramInputStream)
    throws DownloadThread.StopRequest
  {
    try
    {
      int i = paramInputStream.read(paramArrayOfByte);
      return i;
    }
    catch (IOException paramArrayOfByte)
    {
      logNetworkState();
      this.mInfo.mCurrentBytes = paramInnerState.mBytesSoFar;
      this.mDB.updateDownload(this.mInfo);
      if (cannotResume(paramInnerState)) {
        throw new StopRequest(489, "while reading response: " + paramArrayOfByte.toString() + ", can't resume interrupted download with no ETag", paramArrayOfByte);
      }
      throw new StopRequest(getFinalStatusForHttpError(paramState), "while reading response: " + paramArrayOfByte.toString(), paramArrayOfByte);
    }
  }
  
  private void readResponseHeaders(State paramState, InnerState paramInnerState, HttpResponse paramHttpResponse)
    throws DownloadThread.StopRequest
  {
    paramState = paramHttpResponse.getFirstHeader("Content-Disposition");
    if (paramState != null) {
      paramInnerState.mHeaderContentDisposition = paramState.getValue();
    }
    paramState = paramHttpResponse.getFirstHeader("Content-Location");
    if (paramState != null) {
      paramInnerState.mHeaderContentLocation = paramState.getValue();
    }
    paramState = paramHttpResponse.getFirstHeader("ETag");
    if (paramState != null) {
      paramInnerState.mHeaderETag = paramState.getValue();
    }
    paramState = null;
    Header localHeader = paramHttpResponse.getFirstHeader("Transfer-Encoding");
    if (localHeader != null) {
      paramState = localHeader.getValue();
    }
    localHeader = paramHttpResponse.getFirstHeader("Content-Type");
    if ((localHeader != null) && (!localHeader.getValue().equals("application/vnd.android.obb"))) {
      throw new StopRequest(487, "file delivered with incorrect Mime type");
    }
    if (paramState == null)
    {
      paramHttpResponse = paramHttpResponse.getFirstHeader("Content-Length");
      if (paramHttpResponse != null)
      {
        paramInnerState.mHeaderContentLength = paramHttpResponse.getValue();
        long l = Long.parseLong(paramInnerState.mHeaderContentLength);
        if ((l != -1L) && (l != this.mInfo.mTotalBytes)) {
          Log.e("LVLDL", "Incorrect file size delivered.");
        }
      }
    }
    if ((paramInnerState.mHeaderContentLength == null) && ((paramState == null) || (!paramState.equalsIgnoreCase("chunked")))) {}
    for (int i = 1; i != 0; i = 0) {
      throw new StopRequest(495, "can't know size of download, giving up");
    }
  }
  
  private void reportProgress(State paramState, InnerState paramInnerState)
  {
    long l1 = System.currentTimeMillis();
    if ((paramInnerState.mBytesSoFar - paramInnerState.mBytesNotified > 4096) && (l1 - paramInnerState.mTimeLastNotification > 1000L))
    {
      this.mInfo.mCurrentBytes = paramInnerState.mBytesSoFar;
      this.mDB.updateDownloadCurrentBytes(this.mInfo);
      paramInnerState.mBytesNotified = paramInnerState.mBytesSoFar;
      paramInnerState.mTimeLastNotification = l1;
      l1 = paramInnerState.mBytesThisSession;
      long l2 = this.mService.mBytesSoFar;
      this.mService.notifyUpdateBytes(l1 + l2);
    }
  }
  
  private HttpResponse sendRequest(State paramState, AndroidHttpClient paramAndroidHttpClient, HttpGet paramHttpGet)
    throws DownloadThread.StopRequest
  {
    try
    {
      paramAndroidHttpClient = paramAndroidHttpClient.execute(paramHttpGet);
      return paramAndroidHttpClient;
    }
    catch (IllegalArgumentException paramState)
    {
      throw new StopRequest(495, "while trying to execute request: " + paramState.toString(), paramState);
    }
    catch (IOException paramAndroidHttpClient)
    {
      logNetworkState();
      throw new StopRequest(getFinalStatusForHttpError(paramState), "while trying to execute request: " + paramAndroidHttpClient.toString(), paramAndroidHttpClient);
    }
  }
  
  private void setupDestinationFile(State paramState, InnerState paramInnerState)
    throws DownloadThread.StopRequest
  {
    Object localObject;
    long l;
    if (paramState.mFilename != null)
    {
      if (!Helpers.isFilenameValid(paramState.mFilename)) {
        throw new StopRequest(492, "found invalid internal destination filename");
      }
      localObject = new File(paramState.mFilename);
      if (((File)localObject).exists())
      {
        l = ((File)localObject).length();
        if (l != 0L) {
          break label89;
        }
        ((File)localObject).delete();
        paramState.mFilename = null;
      }
    }
    for (;;)
    {
      if (paramState.mStream != null) {
        closeDestination(paramState);
      }
      return;
      label89:
      if (this.mInfo.mETag == null)
      {
        ((File)localObject).delete();
        throw new StopRequest(489, "Trying to resume a download that can't be resumed");
      }
      try
      {
        localObject = new java/io/FileOutputStream;
        ((FileOutputStream)localObject).<init>(paramState.mFilename, true);
        paramState.mStream = ((FileOutputStream)localObject);
        paramInnerState.mBytesSoFar = ((int)l);
        if (this.mInfo.mTotalBytes != -1L) {
          paramInnerState.mHeaderContentLength = Long.toString(this.mInfo.mTotalBytes);
        }
        paramInnerState.mHeaderETag = this.mInfo.mETag;
        paramInnerState.mContinuingDownload = true;
      }
      catch (FileNotFoundException paramState)
      {
        throw new StopRequest(492, "while opening destination for resuming: " + paramState.toString(), paramState);
      }
    }
  }
  
  /* Error */
  private void syncDestination(State paramState)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 6
    //   3: aconst_null
    //   4: astore 7
    //   6: aconst_null
    //   7: astore 4
    //   9: aconst_null
    //   10: astore 8
    //   12: aconst_null
    //   13: astore 5
    //   15: aload 8
    //   17: astore_2
    //   18: new 189	java/io/FileOutputStream
    //   21: astore_3
    //   22: aload 8
    //   24: astore_2
    //   25: aload_3
    //   26: aload_1
    //   27: getfield 168	com/google/android/vending/expansion/downloader/impl/DownloadThread$State:mFilename	Ljava/lang/String;
    //   30: iconst_1
    //   31: invokespecial 589	java/io/FileOutputStream:<init>	(Ljava/lang/String;Z)V
    //   34: aload_3
    //   35: invokevirtual 602	java/io/FileOutputStream:getFD	()Ljava/io/FileDescriptor;
    //   38: invokevirtual 607	java/io/FileDescriptor:sync	()V
    //   41: aload_3
    //   42: ifnull +460 -> 502
    //   45: aload_3
    //   46: invokevirtual 192	java/io/FileOutputStream:close	()V
    //   49: return
    //   50: astore_1
    //   51: ldc_w 280
    //   54: ldc_w 609
    //   57: aload_1
    //   58: invokestatic 612	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   61: pop
    //   62: goto -13 -> 49
    //   65: astore_1
    //   66: ldc_w 280
    //   69: ldc_w 614
    //   72: aload_1
    //   73: invokestatic 612	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   76: pop
    //   77: goto -28 -> 49
    //   80: astore 4
    //   82: aload 5
    //   84: astore_3
    //   85: aload_3
    //   86: astore_2
    //   87: new 53	java/lang/StringBuilder
    //   90: astore 5
    //   92: aload_3
    //   93: astore_2
    //   94: aload 5
    //   96: invokespecial 54	java/lang/StringBuilder:<init>	()V
    //   99: aload_3
    //   100: astore_2
    //   101: ldc_w 280
    //   104: aload 5
    //   106: ldc_w 616
    //   109: invokevirtual 60	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   112: aload_1
    //   113: getfield 168	com/google/android/vending/expansion/downloader/impl/DownloadThread$State:mFilename	Ljava/lang/String;
    //   116: invokevirtual 60	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   119: ldc_w 618
    //   122: invokevirtual 60	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   125: aload 4
    //   127: invokevirtual 621	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   130: invokevirtual 97	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   133: invokestatic 288	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;)I
    //   136: pop
    //   137: aload_3
    //   138: ifnull -89 -> 49
    //   141: aload_3
    //   142: invokevirtual 192	java/io/FileOutputStream:close	()V
    //   145: goto -96 -> 49
    //   148: astore_1
    //   149: ldc_w 280
    //   152: ldc_w 609
    //   155: aload_1
    //   156: invokestatic 612	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   159: pop
    //   160: goto -111 -> 49
    //   163: astore_1
    //   164: ldc_w 280
    //   167: ldc_w 614
    //   170: aload_1
    //   171: invokestatic 612	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   174: pop
    //   175: goto -126 -> 49
    //   178: astore 4
    //   180: aload 6
    //   182: astore_3
    //   183: aload_3
    //   184: astore_2
    //   185: new 53	java/lang/StringBuilder
    //   188: astore 5
    //   190: aload_3
    //   191: astore_2
    //   192: aload 5
    //   194: invokespecial 54	java/lang/StringBuilder:<init>	()V
    //   197: aload_3
    //   198: astore_2
    //   199: ldc_w 280
    //   202: aload 5
    //   204: ldc_w 616
    //   207: invokevirtual 60	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   210: aload_1
    //   211: getfield 168	com/google/android/vending/expansion/downloader/impl/DownloadThread$State:mFilename	Ljava/lang/String;
    //   214: invokevirtual 60	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   217: ldc_w 623
    //   220: invokevirtual 60	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   223: aload 4
    //   225: invokevirtual 621	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   228: invokevirtual 97	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   231: invokestatic 288	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;)I
    //   234: pop
    //   235: aload_3
    //   236: ifnull -187 -> 49
    //   239: aload_3
    //   240: invokevirtual 192	java/io/FileOutputStream:close	()V
    //   243: goto -194 -> 49
    //   246: astore_1
    //   247: ldc_w 280
    //   250: ldc_w 609
    //   253: aload_1
    //   254: invokestatic 612	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   257: pop
    //   258: goto -209 -> 49
    //   261: astore_1
    //   262: ldc_w 280
    //   265: ldc_w 614
    //   268: aload_1
    //   269: invokestatic 612	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   272: pop
    //   273: goto -224 -> 49
    //   276: astore 4
    //   278: aload 7
    //   280: astore_3
    //   281: aload_3
    //   282: astore_2
    //   283: new 53	java/lang/StringBuilder
    //   286: astore 5
    //   288: aload_3
    //   289: astore_2
    //   290: aload 5
    //   292: invokespecial 54	java/lang/StringBuilder:<init>	()V
    //   295: aload_3
    //   296: astore_2
    //   297: ldc_w 280
    //   300: aload 5
    //   302: ldc_w 625
    //   305: invokevirtual 60	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   308: aload_1
    //   309: getfield 168	com/google/android/vending/expansion/downloader/impl/DownloadThread$State:mFilename	Ljava/lang/String;
    //   312: invokevirtual 60	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   315: ldc_w 627
    //   318: invokevirtual 60	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   321: aload 4
    //   323: invokevirtual 621	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   326: invokevirtual 97	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   329: invokestatic 288	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;)I
    //   332: pop
    //   333: aload_3
    //   334: ifnull -285 -> 49
    //   337: aload_3
    //   338: invokevirtual 192	java/io/FileOutputStream:close	()V
    //   341: goto -292 -> 49
    //   344: astore_1
    //   345: ldc_w 280
    //   348: ldc_w 609
    //   351: aload_1
    //   352: invokestatic 612	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   355: pop
    //   356: goto -307 -> 49
    //   359: astore_1
    //   360: ldc_w 280
    //   363: ldc_w 614
    //   366: aload_1
    //   367: invokestatic 612	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   370: pop
    //   371: goto -322 -> 49
    //   374: astore_3
    //   375: aload 4
    //   377: astore_1
    //   378: aload_1
    //   379: astore_2
    //   380: ldc_w 280
    //   383: ldc_w 629
    //   386: aload_3
    //   387: invokestatic 612	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   390: pop
    //   391: aload_1
    //   392: ifnull -343 -> 49
    //   395: aload_1
    //   396: invokevirtual 192	java/io/FileOutputStream:close	()V
    //   399: goto -350 -> 49
    //   402: astore_1
    //   403: ldc_w 280
    //   406: ldc_w 609
    //   409: aload_1
    //   410: invokestatic 612	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   413: pop
    //   414: goto -365 -> 49
    //   417: astore_1
    //   418: ldc_w 280
    //   421: ldc_w 614
    //   424: aload_1
    //   425: invokestatic 612	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   428: pop
    //   429: goto -380 -> 49
    //   432: astore_1
    //   433: aload_2
    //   434: ifnull +7 -> 441
    //   437: aload_2
    //   438: invokevirtual 192	java/io/FileOutputStream:close	()V
    //   441: aload_1
    //   442: athrow
    //   443: astore_2
    //   444: ldc_w 280
    //   447: ldc_w 609
    //   450: aload_2
    //   451: invokestatic 612	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   454: pop
    //   455: goto -14 -> 441
    //   458: astore_2
    //   459: ldc_w 280
    //   462: ldc_w 614
    //   465: aload_2
    //   466: invokestatic 612	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   469: pop
    //   470: goto -29 -> 441
    //   473: astore_1
    //   474: aload_3
    //   475: astore_2
    //   476: goto -43 -> 433
    //   479: astore_2
    //   480: aload_3
    //   481: astore_1
    //   482: aload_2
    //   483: astore_3
    //   484: goto -106 -> 378
    //   487: astore 4
    //   489: goto -208 -> 281
    //   492: astore 4
    //   494: goto -311 -> 183
    //   497: astore 4
    //   499: goto -414 -> 85
    //   502: goto -453 -> 49
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	505	0	this	DownloadThread
    //   0	505	1	paramState	State
    //   17	421	2	localObject1	Object
    //   443	8	2	localIOException1	IOException
    //   458	8	2	localRuntimeException1	RuntimeException
    //   475	1	2	localObject2	Object
    //   479	4	2	localRuntimeException2	RuntimeException
    //   21	317	3	localObject3	Object
    //   374	107	3	localRuntimeException3	RuntimeException
    //   483	1	3	localRuntimeException4	RuntimeException
    //   7	1	4	localObject4	Object
    //   80	46	4	localFileNotFoundException1	FileNotFoundException
    //   178	46	4	localSyncFailedException1	java.io.SyncFailedException
    //   276	100	4	localIOException2	IOException
    //   487	1	4	localIOException3	IOException
    //   492	1	4	localSyncFailedException2	java.io.SyncFailedException
    //   497	1	4	localFileNotFoundException2	FileNotFoundException
    //   13	288	5	localStringBuilder	StringBuilder
    //   1	180	6	localObject5	Object
    //   4	275	7	localObject6	Object
    //   10	13	8	localObject7	Object
    // Exception table:
    //   from	to	target	type
    //   45	49	50	java/io/IOException
    //   45	49	65	java/lang/RuntimeException
    //   18	22	80	java/io/FileNotFoundException
    //   25	34	80	java/io/FileNotFoundException
    //   141	145	148	java/io/IOException
    //   141	145	163	java/lang/RuntimeException
    //   18	22	178	java/io/SyncFailedException
    //   25	34	178	java/io/SyncFailedException
    //   239	243	246	java/io/IOException
    //   239	243	261	java/lang/RuntimeException
    //   18	22	276	java/io/IOException
    //   25	34	276	java/io/IOException
    //   337	341	344	java/io/IOException
    //   337	341	359	java/lang/RuntimeException
    //   18	22	374	java/lang/RuntimeException
    //   25	34	374	java/lang/RuntimeException
    //   395	399	402	java/io/IOException
    //   395	399	417	java/lang/RuntimeException
    //   18	22	432	finally
    //   25	34	432	finally
    //   87	92	432	finally
    //   94	99	432	finally
    //   101	137	432	finally
    //   185	190	432	finally
    //   192	197	432	finally
    //   199	235	432	finally
    //   283	288	432	finally
    //   290	295	432	finally
    //   297	333	432	finally
    //   380	391	432	finally
    //   437	441	443	java/io/IOException
    //   437	441	458	java/lang/RuntimeException
    //   34	41	473	finally
    //   34	41	479	java/lang/RuntimeException
    //   34	41	487	java/io/IOException
    //   34	41	492	java/io/SyncFailedException
    //   34	41	497	java/io/FileNotFoundException
  }
  
  private void transferData(State paramState, InnerState paramInnerState, byte[] paramArrayOfByte, InputStream paramInputStream)
    throws DownloadThread.StopRequest
  {
    for (;;)
    {
      int i = readFromResponse(paramState, paramInnerState, paramArrayOfByte, paramInputStream);
      if (i == -1)
      {
        handleEndOfStream(paramState, paramInnerState);
        return;
      }
      paramState.mGotData = true;
      writeDataToDestination(paramState, paramArrayOfByte, i);
      paramInnerState.mBytesSoFar += i;
      paramInnerState.mBytesThisSession += i;
      reportProgress(paramState, paramInnerState);
      checkPausedOrCanceled(paramState);
    }
  }
  
  private void updateDatabaseFromHeaders(State paramState, InnerState paramInnerState)
  {
    this.mInfo.mETag = paramInnerState.mHeaderETag;
    this.mDB.updateDownload(this.mInfo);
  }
  
  private void updateDownloadDatabase(int paramInt1, boolean paramBoolean1, int paramInt2, int paramInt3, boolean paramBoolean2, String paramString)
  {
    this.mInfo.mStatus = paramInt1;
    this.mInfo.mRetryAfter = paramInt2;
    this.mInfo.mRedirectCount = paramInt3;
    this.mInfo.mLastMod = System.currentTimeMillis();
    if (!paramBoolean1) {
      this.mInfo.mNumFailed = 0;
    }
    for (;;)
    {
      this.mDB.updateDownload(this.mInfo);
      return;
      if (paramBoolean2)
      {
        this.mInfo.mNumFailed = 1;
      }
      else
      {
        paramString = this.mInfo;
        paramString.mNumFailed += 1;
      }
    }
  }
  
  private String userAgent()
  {
    return this.mUserAgent;
  }
  
  private void writeDataToDestination(State paramState, byte[] paramArrayOfByte, int paramInt)
    throws DownloadThread.StopRequest
  {
    try
    {
      if (paramState.mStream == null)
      {
        FileOutputStream localFileOutputStream = new java/io/FileOutputStream;
        localFileOutputStream.<init>(paramState.mFilename, true);
        paramState.mStream = localFileOutputStream;
      }
      paramState.mStream.write(paramArrayOfByte, 0, paramInt);
      closeDestination(paramState);
      return;
    }
    catch (IOException paramArrayOfByte)
    {
      if (!Helpers.isExternalMediaMounted()) {
        throw new StopRequest(499, "external media not mounted while writing destination file");
      }
      if (Helpers.getAvailableBytes(Helpers.getFilesystemRoot(paramState.mFilename)) < paramInt) {
        throw new StopRequest(498, "insufficient space while writing destination file", paramArrayOfByte);
      }
      throw new StopRequest(492, "while writing destination file: " + paramArrayOfByte.toString(), paramArrayOfByte);
    }
  }
  
  public HttpHost getPreferredHttpHost(Context paramContext, String paramString)
  {
    if ((!isLocalHost(paramString)) && (!this.mService.isWiFi()))
    {
      paramString = Proxy.getHost(paramContext);
      if (paramString == null) {}
    }
    for (paramContext = new HttpHost(paramString, Proxy.getPort(paramContext), "http");; paramContext = null) {
      return paramContext;
    }
  }
  
  /* Error */
  public void run()
  {
    // Byte code:
    //   0: bipush 10
    //   2: invokestatic 700	android/os/Process:setThreadPriority	(I)V
    //   5: new 14	com/google/android/vending/expansion/downloader/impl/DownloadThread$State
    //   8: dup
    //   9: aload_0
    //   10: getfield 39	com/google/android/vending/expansion/downloader/impl/DownloadThread:mInfo	Lcom/google/android/vending/expansion/downloader/impl/DownloadInfo;
    //   13: aload_0
    //   14: getfield 41	com/google/android/vending/expansion/downloader/impl/DownloadThread:mService	Lcom/google/android/vending/expansion/downloader/impl/DownloaderService;
    //   17: invokespecial 703	com/google/android/vending/expansion/downloader/impl/DownloadThread$State:<init>	(Lcom/google/android/vending/expansion/downloader/impl/DownloadInfo;Lcom/google/android/vending/expansion/downloader/impl/DownloaderService;)V
    //   20: astore 12
    //   22: aconst_null
    //   23: astore 10
    //   25: aconst_null
    //   26: astore 11
    //   28: aconst_null
    //   29: astore 9
    //   31: aconst_null
    //   32: astore 7
    //   34: aconst_null
    //   35: astore_3
    //   36: aconst_null
    //   37: astore 6
    //   39: aload 9
    //   41: astore 4
    //   43: aload 10
    //   45: astore 5
    //   47: aload 11
    //   49: astore_2
    //   50: aload_0
    //   51: getfield 37	com/google/android/vending/expansion/downloader/impl/DownloadThread:mContext	Landroid/content/Context;
    //   54: ldc_w 705
    //   57: invokevirtual 711	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   60: checkcast 713	android/os/PowerManager
    //   63: iconst_1
    //   64: ldc_w 280
    //   67: invokevirtual 717	android/os/PowerManager:newWakeLock	(ILjava/lang/String;)Landroid/os/PowerManager$WakeLock;
    //   70: astore 8
    //   72: aload 9
    //   74: astore 4
    //   76: aload 8
    //   78: astore 6
    //   80: aload 10
    //   82: astore 5
    //   84: aload 8
    //   86: astore 7
    //   88: aload 11
    //   90: astore_2
    //   91: aload 8
    //   93: astore_3
    //   94: aload 8
    //   96: invokevirtual 722	android/os/PowerManager$WakeLock:acquire	()V
    //   99: aload 9
    //   101: astore 4
    //   103: aload 8
    //   105: astore 6
    //   107: aload 10
    //   109: astore 5
    //   111: aload 8
    //   113: astore 7
    //   115: aload 11
    //   117: astore_2
    //   118: aload 8
    //   120: astore_3
    //   121: aload_0
    //   122: invokespecial 724	com/google/android/vending/expansion/downloader/impl/DownloadThread:userAgent	()Ljava/lang/String;
    //   125: aload_0
    //   126: getfield 37	com/google/android/vending/expansion/downloader/impl/DownloadThread:mContext	Landroid/content/Context;
    //   129: invokestatic 728	com/google/android/vending/expansion/downloader/impl/AndroidHttpClient:newInstance	(Ljava/lang/String;Landroid/content/Context;)Lcom/google/android/vending/expansion/downloader/impl/AndroidHttpClient;
    //   132: astore 9
    //   134: iconst_0
    //   135: istore_1
    //   136: iload_1
    //   137: ifne +385 -> 522
    //   140: aload 9
    //   142: astore 4
    //   144: aload 8
    //   146: astore 6
    //   148: aload 9
    //   150: astore 5
    //   152: aload 8
    //   154: astore 7
    //   156: aload 9
    //   158: astore_2
    //   159: aload 8
    //   161: astore_3
    //   162: aload 9
    //   164: invokevirtual 732	com/google/android/vending/expansion/downloader/impl/AndroidHttpClient:getParams	()Lorg/apache/http/params/HttpParams;
    //   167: aload_0
    //   168: aload_0
    //   169: getfield 37	com/google/android/vending/expansion/downloader/impl/DownloadThread:mContext	Landroid/content/Context;
    //   172: aload 12
    //   174: getfield 367	com/google/android/vending/expansion/downloader/impl/DownloadThread$State:mRequestUri	Ljava/lang/String;
    //   177: invokevirtual 734	com/google/android/vending/expansion/downloader/impl/DownloadThread:getPreferredHttpHost	(Landroid/content/Context;Ljava/lang/String;)Lorg/apache/http/HttpHost;
    //   180: invokestatic 740	org/apache/http/conn/params/ConnRouteParams:setDefaultProxy	(Lorg/apache/http/params/HttpParams;Lorg/apache/http/HttpHost;)V
    //   183: aload 9
    //   185: astore 4
    //   187: aload 8
    //   189: astore 6
    //   191: aload 9
    //   193: astore 5
    //   195: aload 8
    //   197: astore 7
    //   199: aload 9
    //   201: astore_2
    //   202: aload 8
    //   204: astore_3
    //   205: new 113	org/apache/http/client/methods/HttpGet
    //   208: astore 10
    //   210: aload 9
    //   212: astore 4
    //   214: aload 8
    //   216: astore 6
    //   218: aload 9
    //   220: astore 5
    //   222: aload 8
    //   224: astore 7
    //   226: aload 9
    //   228: astore_2
    //   229: aload 8
    //   231: astore_3
    //   232: aload 10
    //   234: aload 12
    //   236: getfield 367	com/google/android/vending/expansion/downloader/impl/DownloadThread$State:mRequestUri	Ljava/lang/String;
    //   239: invokespecial 741	org/apache/http/client/methods/HttpGet:<init>	(Ljava/lang/String;)V
    //   242: aload_0
    //   243: aload 12
    //   245: aload 9
    //   247: aload 10
    //   249: invokespecial 743	com/google/android/vending/expansion/downloader/impl/DownloadThread:executeDownload	(Lcom/google/android/vending/expansion/downloader/impl/DownloadThread$State;Lcom/google/android/vending/expansion/downloader/impl/AndroidHttpClient;Lorg/apache/http/client/methods/HttpGet;)V
    //   252: iconst_1
    //   253: istore_1
    //   254: aload 9
    //   256: astore 4
    //   258: aload 8
    //   260: astore 6
    //   262: aload 9
    //   264: astore 5
    //   266: aload 8
    //   268: astore 7
    //   270: aload 9
    //   272: astore_2
    //   273: aload 8
    //   275: astore_3
    //   276: aload 10
    //   278: invokevirtual 746	org/apache/http/client/methods/HttpGet:abort	()V
    //   281: goto -145 -> 136
    //   284: astore_2
    //   285: aload 9
    //   287: astore 4
    //   289: aload 8
    //   291: astore 6
    //   293: aload 9
    //   295: astore 5
    //   297: aload 8
    //   299: astore 7
    //   301: aload 9
    //   303: astore_2
    //   304: aload 8
    //   306: astore_3
    //   307: aload 10
    //   309: invokevirtual 746	org/apache/http/client/methods/HttpGet:abort	()V
    //   312: goto -176 -> 136
    //   315: astore 11
    //   317: aload 9
    //   319: astore 4
    //   321: aload 8
    //   323: astore 6
    //   325: aload 9
    //   327: astore 5
    //   329: aload 8
    //   331: astore 7
    //   333: aload 9
    //   335: astore_2
    //   336: aload 8
    //   338: astore_3
    //   339: aload 10
    //   341: invokevirtual 746	org/apache/http/client/methods/HttpGet:abort	()V
    //   344: aload 9
    //   346: astore 4
    //   348: aload 8
    //   350: astore 6
    //   352: aload 9
    //   354: astore 5
    //   356: aload 8
    //   358: astore 7
    //   360: aload 9
    //   362: astore_2
    //   363: aload 8
    //   365: astore_3
    //   366: aload 11
    //   368: athrow
    //   369: astore 7
    //   371: aload 4
    //   373: astore_2
    //   374: aload 6
    //   376: astore_3
    //   377: new 53	java/lang/StringBuilder
    //   380: astore 5
    //   382: aload 4
    //   384: astore_2
    //   385: aload 6
    //   387: astore_3
    //   388: aload 5
    //   390: invokespecial 54	java/lang/StringBuilder:<init>	()V
    //   393: aload 4
    //   395: astore_2
    //   396: aload 6
    //   398: astore_3
    //   399: ldc_w 280
    //   402: aload 5
    //   404: ldc_w 748
    //   407: invokevirtual 60	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   410: aload_0
    //   411: getfield 39	com/google/android/vending/expansion/downloader/impl/DownloadThread:mInfo	Lcom/google/android/vending/expansion/downloader/impl/DownloadInfo;
    //   414: getfield 241	com/google/android/vending/expansion/downloader/impl/DownloadInfo:mFileName	Ljava/lang/String;
    //   417: invokevirtual 60	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   420: ldc_w 627
    //   423: invokevirtual 60	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   426: aload 7
    //   428: invokevirtual 751	com/google/android/vending/expansion/downloader/impl/DownloadThread$StopRequest:getMessage	()Ljava/lang/String;
    //   431: invokevirtual 60	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   434: invokevirtual 97	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   437: invokestatic 288	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;)I
    //   440: pop
    //   441: aload 4
    //   443: astore_2
    //   444: aload 6
    //   446: astore_3
    //   447: aload 7
    //   449: invokevirtual 754	com/google/android/vending/expansion/downloader/impl/DownloadThread$StopRequest:printStackTrace	()V
    //   452: aload 4
    //   454: astore_2
    //   455: aload 6
    //   457: astore_3
    //   458: aload 7
    //   460: getfield 757	com/google/android/vending/expansion/downloader/impl/DownloadThread$StopRequest:mFinalStatus	I
    //   463: istore_1
    //   464: aload 6
    //   466: ifnull +8 -> 474
    //   469: aload 6
    //   471: invokevirtual 760	android/os/PowerManager$WakeLock:release	()V
    //   474: aload 4
    //   476: ifnull +8 -> 484
    //   479: aload 4
    //   481: invokevirtual 761	com/google/android/vending/expansion/downloader/impl/AndroidHttpClient:close	()V
    //   484: aload_0
    //   485: aload 12
    //   487: iload_1
    //   488: invokespecial 763	com/google/android/vending/expansion/downloader/impl/DownloadThread:cleanupDestination	(Lcom/google/android/vending/expansion/downloader/impl/DownloadThread$State;I)V
    //   491: aload_0
    //   492: iload_1
    //   493: aload 12
    //   495: getfield 278	com/google/android/vending/expansion/downloader/impl/DownloadThread$State:mCountRetry	Z
    //   498: aload 12
    //   500: getfield 382	com/google/android/vending/expansion/downloader/impl/DownloadThread$State:mRetryAfter	I
    //   503: aload 12
    //   505: getfield 336	com/google/android/vending/expansion/downloader/impl/DownloadThread$State:mRedirectCount	I
    //   508: aload 12
    //   510: getfield 636	com/google/android/vending/expansion/downloader/impl/DownloadThread$State:mGotData	Z
    //   513: aload 12
    //   515: getfield 168	com/google/android/vending/expansion/downloader/impl/DownloadThread$State:mFilename	Ljava/lang/String;
    //   518: invokespecial 765	com/google/android/vending/expansion/downloader/impl/DownloadThread:notifyDownloadCompleted	(IZIIZLjava/lang/String;)V
    //   521: return
    //   522: aload 9
    //   524: astore 4
    //   526: aload 8
    //   528: astore 6
    //   530: aload 9
    //   532: astore 5
    //   534: aload 8
    //   536: astore 7
    //   538: aload 9
    //   540: astore_2
    //   541: aload 8
    //   543: astore_3
    //   544: aload_0
    //   545: aload 12
    //   547: invokespecial 767	com/google/android/vending/expansion/downloader/impl/DownloadThread:finalizeDestinationFile	(Lcom/google/android/vending/expansion/downloader/impl/DownloadThread$State;)V
    //   550: aload 8
    //   552: ifnull +8 -> 560
    //   555: aload 8
    //   557: invokevirtual 760	android/os/PowerManager$WakeLock:release	()V
    //   560: aload 9
    //   562: ifnull +8 -> 570
    //   565: aload 9
    //   567: invokevirtual 761	com/google/android/vending/expansion/downloader/impl/AndroidHttpClient:close	()V
    //   570: aload_0
    //   571: aload 12
    //   573: sipush 200
    //   576: invokespecial 763	com/google/android/vending/expansion/downloader/impl/DownloadThread:cleanupDestination	(Lcom/google/android/vending/expansion/downloader/impl/DownloadThread$State;I)V
    //   579: aload_0
    //   580: sipush 200
    //   583: aload 12
    //   585: getfield 278	com/google/android/vending/expansion/downloader/impl/DownloadThread$State:mCountRetry	Z
    //   588: aload 12
    //   590: getfield 382	com/google/android/vending/expansion/downloader/impl/DownloadThread$State:mRetryAfter	I
    //   593: aload 12
    //   595: getfield 336	com/google/android/vending/expansion/downloader/impl/DownloadThread$State:mRedirectCount	I
    //   598: aload 12
    //   600: getfield 636	com/google/android/vending/expansion/downloader/impl/DownloadThread$State:mGotData	Z
    //   603: aload 12
    //   605: getfield 168	com/google/android/vending/expansion/downloader/impl/DownloadThread$State:mFilename	Ljava/lang/String;
    //   608: invokespecial 765	com/google/android/vending/expansion/downloader/impl/DownloadThread:notifyDownloadCompleted	(IZIIZLjava/lang/String;)V
    //   611: goto -90 -> 521
    //   614: astore 4
    //   616: aload 5
    //   618: astore_2
    //   619: aload 7
    //   621: astore_3
    //   622: new 53	java/lang/StringBuilder
    //   625: astore 6
    //   627: aload 5
    //   629: astore_2
    //   630: aload 7
    //   632: astore_3
    //   633: aload 6
    //   635: invokespecial 54	java/lang/StringBuilder:<init>	()V
    //   638: aload 5
    //   640: astore_2
    //   641: aload 7
    //   643: astore_3
    //   644: ldc_w 280
    //   647: aload 6
    //   649: ldc_w 769
    //   652: invokevirtual 60	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   655: aload_0
    //   656: getfield 39	com/google/android/vending/expansion/downloader/impl/DownloadThread:mInfo	Lcom/google/android/vending/expansion/downloader/impl/DownloadInfo;
    //   659: getfield 241	com/google/android/vending/expansion/downloader/impl/DownloadInfo:mFileName	Ljava/lang/String;
    //   662: invokevirtual 60	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   665: ldc_w 627
    //   668: invokevirtual 60	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   671: aload 4
    //   673: invokevirtual 621	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   676: invokevirtual 97	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   679: invokestatic 288	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;)I
    //   682: pop
    //   683: aload 7
    //   685: ifnull +8 -> 693
    //   688: aload 7
    //   690: invokevirtual 760	android/os/PowerManager$WakeLock:release	()V
    //   693: aload 5
    //   695: ifnull +8 -> 703
    //   698: aload 5
    //   700: invokevirtual 761	com/google/android/vending/expansion/downloader/impl/AndroidHttpClient:close	()V
    //   703: aload_0
    //   704: aload 12
    //   706: sipush 491
    //   709: invokespecial 763	com/google/android/vending/expansion/downloader/impl/DownloadThread:cleanupDestination	(Lcom/google/android/vending/expansion/downloader/impl/DownloadThread$State;I)V
    //   712: aload_0
    //   713: sipush 491
    //   716: aload 12
    //   718: getfield 278	com/google/android/vending/expansion/downloader/impl/DownloadThread$State:mCountRetry	Z
    //   721: aload 12
    //   723: getfield 382	com/google/android/vending/expansion/downloader/impl/DownloadThread$State:mRetryAfter	I
    //   726: aload 12
    //   728: getfield 336	com/google/android/vending/expansion/downloader/impl/DownloadThread$State:mRedirectCount	I
    //   731: aload 12
    //   733: getfield 636	com/google/android/vending/expansion/downloader/impl/DownloadThread$State:mGotData	Z
    //   736: aload 12
    //   738: getfield 168	com/google/android/vending/expansion/downloader/impl/DownloadThread$State:mFilename	Ljava/lang/String;
    //   741: invokespecial 765	com/google/android/vending/expansion/downloader/impl/DownloadThread:notifyDownloadCompleted	(IZIIZLjava/lang/String;)V
    //   744: goto -223 -> 521
    //   747: astore 4
    //   749: aload_3
    //   750: ifnull +7 -> 757
    //   753: aload_3
    //   754: invokevirtual 760	android/os/PowerManager$WakeLock:release	()V
    //   757: aload_2
    //   758: ifnull +7 -> 765
    //   761: aload_2
    //   762: invokevirtual 761	com/google/android/vending/expansion/downloader/impl/AndroidHttpClient:close	()V
    //   765: aload_0
    //   766: aload 12
    //   768: sipush 491
    //   771: invokespecial 763	com/google/android/vending/expansion/downloader/impl/DownloadThread:cleanupDestination	(Lcom/google/android/vending/expansion/downloader/impl/DownloadThread$State;I)V
    //   774: aload_0
    //   775: sipush 491
    //   778: aload 12
    //   780: getfield 278	com/google/android/vending/expansion/downloader/impl/DownloadThread$State:mCountRetry	Z
    //   783: aload 12
    //   785: getfield 382	com/google/android/vending/expansion/downloader/impl/DownloadThread$State:mRetryAfter	I
    //   788: aload 12
    //   790: getfield 336	com/google/android/vending/expansion/downloader/impl/DownloadThread$State:mRedirectCount	I
    //   793: aload 12
    //   795: getfield 636	com/google/android/vending/expansion/downloader/impl/DownloadThread$State:mGotData	Z
    //   798: aload 12
    //   800: getfield 168	com/google/android/vending/expansion/downloader/impl/DownloadThread$State:mFilename	Ljava/lang/String;
    //   803: invokespecial 765	com/google/android/vending/expansion/downloader/impl/DownloadThread:notifyDownloadCompleted	(IZIIZLjava/lang/String;)V
    //   806: aload 4
    //   808: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	809	0	this	DownloadThread
    //   135	358	1	i	int
    //   49	224	2	localObject1	Object
    //   284	1	2	localRetryDownload	RetryDownload
    //   303	459	2	localObject2	Object
    //   35	719	3	localObject3	Object
    //   41	484	4	localAndroidHttpClient1	AndroidHttpClient
    //   614	58	4	localThrowable	Throwable
    //   747	60	4	localObject4	Object
    //   45	654	5	localObject5	Object
    //   37	611	6	localObject6	Object
    //   32	327	7	localObject7	Object
    //   369	90	7	localStopRequest	StopRequest
    //   536	153	7	localObject8	Object
    //   70	486	8	localWakeLock	android.os.PowerManager.WakeLock
    //   29	537	9	localAndroidHttpClient2	AndroidHttpClient
    //   23	317	10	localHttpGet	HttpGet
    //   26	90	11	localObject9	Object
    //   315	52	11	localObject10	Object
    //   20	779	12	localState	State
    // Exception table:
    //   from	to	target	type
    //   242	252	284	com/google/android/vending/expansion/downloader/impl/DownloadThread$RetryDownload
    //   242	252	315	finally
    //   50	72	369	com/google/android/vending/expansion/downloader/impl/DownloadThread$StopRequest
    //   94	99	369	com/google/android/vending/expansion/downloader/impl/DownloadThread$StopRequest
    //   121	134	369	com/google/android/vending/expansion/downloader/impl/DownloadThread$StopRequest
    //   162	183	369	com/google/android/vending/expansion/downloader/impl/DownloadThread$StopRequest
    //   205	210	369	com/google/android/vending/expansion/downloader/impl/DownloadThread$StopRequest
    //   232	242	369	com/google/android/vending/expansion/downloader/impl/DownloadThread$StopRequest
    //   276	281	369	com/google/android/vending/expansion/downloader/impl/DownloadThread$StopRequest
    //   307	312	369	com/google/android/vending/expansion/downloader/impl/DownloadThread$StopRequest
    //   339	344	369	com/google/android/vending/expansion/downloader/impl/DownloadThread$StopRequest
    //   366	369	369	com/google/android/vending/expansion/downloader/impl/DownloadThread$StopRequest
    //   544	550	369	com/google/android/vending/expansion/downloader/impl/DownloadThread$StopRequest
    //   50	72	614	java/lang/Throwable
    //   94	99	614	java/lang/Throwable
    //   121	134	614	java/lang/Throwable
    //   162	183	614	java/lang/Throwable
    //   205	210	614	java/lang/Throwable
    //   232	242	614	java/lang/Throwable
    //   276	281	614	java/lang/Throwable
    //   307	312	614	java/lang/Throwable
    //   339	344	614	java/lang/Throwable
    //   366	369	614	java/lang/Throwable
    //   544	550	614	java/lang/Throwable
    //   50	72	747	finally
    //   94	99	747	finally
    //   121	134	747	finally
    //   162	183	747	finally
    //   205	210	747	finally
    //   232	242	747	finally
    //   276	281	747	finally
    //   307	312	747	finally
    //   339	344	747	finally
    //   366	369	747	finally
    //   377	382	747	finally
    //   388	393	747	finally
    //   399	441	747	finally
    //   447	452	747	finally
    //   458	464	747	finally
    //   544	550	747	finally
    //   622	627	747	finally
    //   633	638	747	finally
    //   644	683	747	finally
  }
  
  private static class InnerState
  {
    public int mBytesNotified = 0;
    public int mBytesSoFar = 0;
    public int mBytesThisSession = 0;
    public boolean mContinuingDownload = false;
    public String mHeaderContentDisposition;
    public String mHeaderContentLength;
    public String mHeaderContentLocation;
    public String mHeaderETag;
    public long mTimeLastNotification = 0L;
  }
  
  private class RetryDownload
    extends Throwable
  {
    private static final long serialVersionUID = 6196036036517540229L;
    
    private RetryDownload() {}
  }
  
  private static class State
  {
    public boolean mCountRetry = false;
    public String mFilename;
    public boolean mGotData = false;
    public String mNewUri;
    public int mRedirectCount = 0;
    public String mRequestUri;
    public int mRetryAfter = 0;
    public FileOutputStream mStream;
    
    public State(DownloadInfo paramDownloadInfo, DownloaderService paramDownloaderService)
    {
      this.mRedirectCount = paramDownloadInfo.mRedirectCount;
      this.mRequestUri = paramDownloadInfo.mUri;
      this.mFilename = paramDownloaderService.generateTempSaveFileName(paramDownloadInfo.mFileName);
    }
  }
  
  private class StopRequest
    extends Throwable
  {
    private static final long serialVersionUID = 6338592678988347973L;
    public int mFinalStatus;
    
    public StopRequest(int paramInt, String paramString)
    {
      super();
      this.mFinalStatus = paramInt;
    }
    
    public StopRequest(int paramInt, String paramString, Throwable paramThrowable)
    {
      super(paramThrowable);
      this.mFinalStatus = paramInt;
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\vending\expansion\downloader\impl\DownloadThread.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */