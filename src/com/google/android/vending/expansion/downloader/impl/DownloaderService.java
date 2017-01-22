package com.google.android.vending.expansion.downloader.impl;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.os.IBinder;
import android.os.Messenger;
import android.os.SystemClock;
import android.provider.Settings.Secure;
import android.util.Log;
import com.google.android.vending.expansion.downloader.DownloadProgressInfo;
import com.google.android.vending.expansion.downloader.DownloaderServiceMarshaller;
import com.google.android.vending.expansion.downloader.Helpers;
import com.google.android.vending.expansion.downloader.IDownloaderService;
import com.google.android.vending.expansion.downloader.IStub;
import com.google.android.vending.licensing.AESObfuscator;
import com.google.android.vending.licensing.APKExpansionPolicy;
import com.google.android.vending.licensing.LicenseChecker;
import com.google.android.vending.licensing.LicenseCheckerCallback;
import com.google.android.vending.licensing.Policy;
import java.io.File;

public abstract class DownloaderService
  extends CustomIntentService
  implements IDownloaderService
{
  public static final String ACTION_DOWNLOADS_CHANGED = "downloadsChanged";
  public static final String ACTION_DOWNLOAD_COMPLETE = "lvldownloader.intent.action.DOWNLOAD_COMPLETE";
  public static final String ACTION_DOWNLOAD_STATUS = "lvldownloader.intent.action.DOWNLOAD_STATUS";
  public static final int CONTROL_PAUSED = 1;
  public static final int CONTROL_RUN = 0;
  public static final int DOWNLOAD_REQUIRED = 2;
  public static final String EXTRA_FILE_NAME = "downloadId";
  public static final String EXTRA_IS_WIFI_REQUIRED = "isWifiRequired";
  public static final String EXTRA_MESSAGE_HANDLER = "EMH";
  public static final String EXTRA_PACKAGE_NAME = "EPN";
  public static final String EXTRA_PENDING_INTENT = "EPI";
  public static final String EXTRA_STATUS_CURRENT_FILE_SIZE = "CFS";
  public static final String EXTRA_STATUS_CURRENT_PROGRESS = "CFP";
  public static final String EXTRA_STATUS_STATE = "ESS";
  public static final String EXTRA_STATUS_TOTAL_PROGRESS = "TFP";
  public static final String EXTRA_STATUS_TOTAL_SIZE = "ETS";
  private static final String LOG_TAG = "LVLDL";
  public static final int LVL_CHECK_REQUIRED = 1;
  public static final int NETWORK_CANNOT_USE_ROAMING = 5;
  public static final int NETWORK_MOBILE = 1;
  public static final int NETWORK_NO_CONNECTION = 2;
  public static final int NETWORK_OK = 1;
  public static final int NETWORK_RECOMMENDED_UNUSABLE_DUE_TO_SIZE = 4;
  public static final int NETWORK_TYPE_DISALLOWED_BY_REQUESTOR = 6;
  public static final int NETWORK_UNUSABLE_DUE_TO_SIZE = 3;
  public static final int NETWORK_WIFI = 2;
  public static final int NO_DOWNLOAD_REQUIRED = 0;
  private static final float SMOOTHING_FACTOR = 0.005F;
  public static final int STATUS_CANCELED = 490;
  public static final int STATUS_CANNOT_RESUME = 489;
  public static final int STATUS_DEVICE_NOT_FOUND_ERROR = 499;
  public static final int STATUS_FILE_ALREADY_EXISTS_ERROR = 488;
  public static final int STATUS_FILE_DELIVERED_INCORRECTLY = 487;
  public static final int STATUS_FILE_ERROR = 492;
  public static final int STATUS_FORBIDDEN = 403;
  public static final int STATUS_HTTP_DATA_ERROR = 495;
  public static final int STATUS_HTTP_EXCEPTION = 496;
  public static final int STATUS_INSUFFICIENT_SPACE_ERROR = 498;
  public static final int STATUS_PAUSED_BY_APP = 193;
  public static final int STATUS_PENDING = 190;
  public static final int STATUS_QUEUED_FOR_WIFI = 197;
  public static final int STATUS_QUEUED_FOR_WIFI_OR_CELLULAR_PERMISSION = 196;
  public static final int STATUS_RUNNING = 192;
  public static final int STATUS_SUCCESS = 200;
  public static final int STATUS_TOO_MANY_REDIRECTS = 497;
  public static final int STATUS_UNHANDLED_HTTP_CODE = 494;
  public static final int STATUS_UNHANDLED_REDIRECT = 493;
  public static final int STATUS_UNKNOWN_ERROR = 491;
  public static final int STATUS_WAITING_FOR_NETWORK = 195;
  public static final int STATUS_WAITING_TO_RETRY = 194;
  private static final String TEMP_EXT = ".tmp";
  public static final int VISIBILITY_HIDDEN = 2;
  public static final int VISIBILITY_VISIBLE = 0;
  public static final int VISIBILITY_VISIBLE_NOTIFY_COMPLETED = 1;
  private static boolean sIsRunning;
  private PendingIntent mAlarmIntent;
  float mAverageDownloadSpeed;
  long mBytesAtSample;
  long mBytesSoFar;
  private Messenger mClientMessenger;
  private BroadcastReceiver mConnReceiver;
  private ConnectivityManager mConnectivityManager;
  private int mControl;
  int mFileCount;
  private boolean mIsAtLeast3G;
  private boolean mIsAtLeast4G;
  private boolean mIsCellularConnection;
  private boolean mIsConnected;
  private boolean mIsFailover;
  private boolean mIsRoaming;
  long mMillisecondsAtSample;
  private DownloadNotification mNotification;
  private PackageInfo mPackageInfo;
  private PendingIntent mPendingIntent;
  private final Messenger mServiceMessenger = this.mServiceStub.getMessenger();
  private final IStub mServiceStub = DownloaderServiceMarshaller.CreateStub(this);
  private boolean mStateChanged;
  private int mStatus;
  long mTotalLength;
  private WifiManager mWifiManager;
  
  public DownloaderService()
  {
    super("LVLDownloadService");
  }
  
  private void cancelAlarms()
  {
    AlarmManager localAlarmManager;
    if (this.mAlarmIntent != null)
    {
      localAlarmManager = (AlarmManager)getSystemService("alarm");
      if (localAlarmManager != null) {
        break label30;
      }
      Log.e("LVLDL", "couldn't get alarm manager");
    }
    for (;;)
    {
      return;
      label30:
      localAlarmManager.cancel(this.mAlarmIntent);
      this.mAlarmIntent = null;
    }
  }
  
  private static boolean isLVLCheckRequired(DownloadsDB paramDownloadsDB, PackageInfo paramPackageInfo)
  {
    if (paramDownloadsDB.mVersionCode != paramPackageInfo.versionCode) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  private static boolean isServiceRunning()
  {
    try
    {
      boolean bool = sIsRunning;
      return bool;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public static boolean isStatusClientError(int paramInt)
  {
    if ((paramInt >= 400) && (paramInt < 500)) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public static boolean isStatusCompleted(int paramInt)
  {
    if (((paramInt >= 200) && (paramInt < 300)) || ((paramInt >= 400) && (paramInt < 600))) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public static boolean isStatusError(int paramInt)
  {
    if ((paramInt >= 400) && (paramInt < 600)) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public static boolean isStatusInformational(int paramInt)
  {
    if ((paramInt >= 100) && (paramInt < 200)) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public static boolean isStatusServerError(int paramInt)
  {
    if ((paramInt >= 500) && (paramInt < 600)) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public static boolean isStatusSuccess(int paramInt)
  {
    if ((paramInt >= 200) && (paramInt < 300)) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  private void scheduleAlarm(long paramLong)
  {
    AlarmManager localAlarmManager = (AlarmManager)getSystemService("alarm");
    if (localAlarmManager == null) {
      Log.e("LVLDL", "couldn't get alarm manager");
    }
    for (;;)
    {
      return;
      String str = getAlarmReceiverClassName();
      Intent localIntent = new Intent("android.intent.action.DOWNLOAD_WAKEUP");
      localIntent.putExtra("EPI", this.mPendingIntent);
      localIntent.setClassName(getPackageName(), str);
      this.mAlarmIntent = PendingIntent.getBroadcast(this, 0, localIntent, 1073741824);
      localAlarmManager.set(0, System.currentTimeMillis() + paramLong, this.mAlarmIntent);
    }
  }
  
  private static void setServiceRunning(boolean paramBoolean)
  {
    try
    {
      sIsRunning = paramBoolean;
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public static int startDownloadServiceIfRequired(Context paramContext, PendingIntent paramPendingIntent, Class<?> paramClass)
    throws PackageManager.NameNotFoundException
  {
    return startDownloadServiceIfRequired(paramContext, paramPendingIntent, paramContext.getPackageName(), paramClass.getName());
  }
  
  public static int startDownloadServiceIfRequired(Context paramContext, PendingIntent paramPendingIntent, String paramString1, String paramString2)
    throws PackageManager.NameNotFoundException
  {
    int k = 0;
    PackageInfo localPackageInfo = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0);
    int i = 0;
    Object localObject = DownloadsDB.getDB(paramContext);
    if (isLVLCheckRequired((DownloadsDB)localObject, localPackageInfo)) {
      i = 1;
    }
    int j;
    if (((DownloadsDB)localObject).mStatus == 0)
    {
      DownloadInfo[] arrayOfDownloadInfo = ((DownloadsDB)localObject).getDownloads();
      j = i;
      if (arrayOfDownloadInfo != null)
      {
        int m = arrayOfDownloadInfo.length;
        j = i;
        if (k < m)
        {
          localPackageInfo = arrayOfDownloadInfo[k];
          if (Helpers.doesFileExist(paramContext, localPackageInfo.mFileName, localPackageInfo.mTotalBytes, true)) {
            break label143;
          }
          j = 2;
          ((DownloadsDB)localObject).updateStatus(-1);
        }
      }
      label114:
      switch (j)
      {
      }
    }
    for (;;)
    {
      return j;
      label143:
      k++;
      break;
      j = 2;
      break label114;
      localObject = new Intent();
      ((Intent)localObject).setClassName(paramString1, paramString2);
      ((Intent)localObject).putExtra("EPI", paramPendingIntent);
      paramContext.startService((Intent)localObject);
    }
  }
  
  public static int startDownloadServiceIfRequired(Context paramContext, Intent paramIntent, Class<?> paramClass)
    throws PackageManager.NameNotFoundException
  {
    return startDownloadServiceIfRequired(paramContext, (PendingIntent)paramIntent.getParcelableExtra("EPI"), paramClass);
  }
  
  private void updateNetworkState(NetworkInfo paramNetworkInfo)
  {
    boolean bool1 = false;
    boolean bool4 = this.mIsConnected;
    boolean bool5 = this.mIsFailover;
    boolean bool3 = this.mIsCellularConnection;
    boolean bool6 = this.mIsRoaming;
    boolean bool2 = this.mIsAtLeast3G;
    if (paramNetworkInfo != null)
    {
      this.mIsRoaming = paramNetworkInfo.isRoaming();
      this.mIsFailover = paramNetworkInfo.isFailover();
      this.mIsConnected = paramNetworkInfo.isConnected();
      updateNetworkType(paramNetworkInfo.getType(), paramNetworkInfo.getSubtype());
    }
    for (;;)
    {
      if ((this.mStateChanged) || (bool4 != this.mIsConnected) || (bool5 != this.mIsFailover) || (bool3 != this.mIsCellularConnection) || (bool6 != this.mIsRoaming) || (bool2 != this.mIsAtLeast3G)) {
        bool1 = true;
      }
      this.mStateChanged = bool1;
      return;
      this.mIsRoaming = false;
      this.mIsFailover = false;
      this.mIsConnected = false;
      updateNetworkType(-1, -1);
    }
  }
  
  private void updateNetworkType(int paramInt1, int paramInt2)
  {
    switch (paramInt1)
    {
    }
    for (;;)
    {
      return;
      this.mIsCellularConnection = false;
      this.mIsAtLeast3G = false;
      this.mIsAtLeast4G = false;
      continue;
      this.mIsCellularConnection = true;
      this.mIsAtLeast3G = true;
      this.mIsAtLeast4G = true;
      continue;
      this.mIsCellularConnection = true;
      switch (paramInt2)
      {
      case 12: 
      default: 
        this.mIsCellularConnection = false;
        this.mIsAtLeast3G = false;
        this.mIsAtLeast4G = false;
        break;
      case 1: 
      case 2: 
      case 4: 
      case 7: 
      case 11: 
        this.mIsAtLeast3G = false;
        this.mIsAtLeast4G = false;
        break;
      case 3: 
      case 5: 
      case 6: 
      case 8: 
      case 9: 
      case 10: 
        this.mIsAtLeast3G = true;
        this.mIsAtLeast4G = false;
        break;
      case 13: 
      case 14: 
      case 15: 
        this.mIsAtLeast3G = true;
        this.mIsAtLeast4G = true;
      }
    }
  }
  
  public String generateSaveFile(String paramString, long paramLong)
    throws DownloaderService.GenerateSaveFileError
  {
    paramString = generateTempSaveFileName(paramString);
    File localFile = new File(paramString);
    if (!Helpers.isExternalMediaMounted())
    {
      Log.d("LVLDL", "External media not mounted: " + paramString);
      throw new GenerateSaveFileError(499, "external media is not yet mounted");
    }
    if (localFile.exists())
    {
      Log.d("LVLDL", "File already exists: " + paramString);
      throw new GenerateSaveFileError(488, "requested destination file already exists");
    }
    if (Helpers.getAvailableBytes(Helpers.getFilesystemRoot(paramString)) < paramLong) {
      throw new GenerateSaveFileError(498, "insufficient space on external storage");
    }
    return paramString;
  }
  
  public String generateTempSaveFileName(String paramString)
  {
    return Helpers.getSaveFilePath(this) + File.separator + paramString + ".tmp";
  }
  
  public abstract String getAlarmReceiverClassName();
  
  public int getControl()
  {
    return this.mControl;
  }
  
  public String getLogMessageForNetworkError(int paramInt)
  {
    String str;
    switch (paramInt)
    {
    default: 
      str = "unknown error with network connectivity";
    }
    for (;;)
    {
      return str;
      str = "download size exceeds recommended limit for mobile network";
      continue;
      str = "download size exceeds limit for mobile network";
      continue;
      str = "no network connection available";
      continue;
      str = "download cannot use the current network connection because it is roaming";
      continue;
      str = "download was requested to not use the current network type";
    }
  }
  
  public int getNetworkAvailabilityState(DownloadsDB paramDownloadsDB)
  {
    int i = 1;
    if (this.mIsConnected) {
      if (this.mIsCellularConnection) {}
    }
    for (;;)
    {
      return i;
      int j = paramDownloadsDB.mFlags;
      if (this.mIsRoaming)
      {
        i = 5;
      }
      else if ((j & 0x1) == 0)
      {
        i = 6;
        continue;
        i = 2;
      }
    }
  }
  
  public abstract String getPublicKey();
  
  public abstract byte[] getSALT();
  
  public int getStatus()
  {
    return this.mStatus;
  }
  
  public boolean handleFileUpdated(DownloadsDB paramDownloadsDB, int paramInt, String paramString, long paramLong)
  {
    boolean bool1 = true;
    boolean bool2 = false;
    paramDownloadsDB = paramDownloadsDB.getDownloadInfoByFileName(paramString);
    if (paramDownloadsDB != null)
    {
      paramDownloadsDB = paramDownloadsDB.mFileName;
      if (paramDownloadsDB != null) {
        if (paramString.equals(paramDownloadsDB)) {
          bool1 = bool2;
        }
      }
    }
    while (!Helpers.doesFileExist(this, paramString, paramLong, true))
    {
      return bool1;
      paramDownloadsDB = new File(Helpers.generateSaveFileName(this, paramDownloadsDB));
      if (paramDownloadsDB.exists()) {
        paramDownloadsDB.delete();
      }
    }
    for (;;)
    {
      bool1 = false;
    }
  }
  
  public boolean isWiFi()
  {
    if ((this.mIsConnected) && (!this.mIsCellularConnection)) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public void notifyUpdateBytes(long paramLong)
  {
    long l2 = SystemClock.uptimeMillis();
    float f;
    if (0L != this.mMillisecondsAtSample)
    {
      l1 = this.mMillisecondsAtSample;
      f = (float)(paramLong - this.mBytesAtSample) / (float)(l2 - l1);
      if (0.0F != this.mAverageDownloadSpeed) {
        this.mAverageDownloadSpeed = (0.005F * f + 0.995F * this.mAverageDownloadSpeed);
      }
    }
    for (long l1 = ((float)(this.mTotalLength - paramLong) / this.mAverageDownloadSpeed);; l1 = -1L)
    {
      this.mMillisecondsAtSample = l2;
      this.mBytesAtSample = paramLong;
      this.mNotification.onDownloadProgress(new DownloadProgressInfo(this.mTotalLength, paramLong, l1, this.mAverageDownloadSpeed));
      return;
      this.mAverageDownloadSpeed = f;
      break;
    }
  }
  
  public IBinder onBind(Intent paramIntent)
  {
    Log.d("LVLDL", "Service Bound");
    return this.mServiceMessenger.getBinder();
  }
  
  public void onClientUpdated(Messenger paramMessenger)
  {
    this.mClientMessenger = paramMessenger;
    this.mNotification.setMessenger(this.mClientMessenger);
  }
  
  public void onCreate()
  {
    super.onCreate();
    try
    {
      this.mPackageInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
      Object localObject = getApplicationInfo();
      localObject = getPackageManager().getApplicationLabel((ApplicationInfo)localObject);
      DownloadNotification localDownloadNotification = new com/google/android/vending/expansion/downloader/impl/DownloadNotification;
      localDownloadNotification.<init>(this, (CharSequence)localObject);
      this.mNotification = localDownloadNotification;
      return;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      for (;;)
      {
        localNameNotFoundException.printStackTrace();
      }
    }
  }
  
  public void onDestroy()
  {
    if (this.mConnReceiver != null)
    {
      unregisterReceiver(this.mConnReceiver);
      this.mConnReceiver = null;
    }
    this.mServiceStub.disconnect(this);
    super.onDestroy();
  }
  
  protected void onHandleIntent(Intent paramIntent)
  {
    setServiceRunning(true);
    for (;;)
    {
      DownloadsDB localDownloadsDB;
      try
      {
        localDownloadsDB = DownloadsDB.getDB(this);
        paramIntent = (PendingIntent)paramIntent.getParcelableExtra("EPI");
        if (paramIntent != null)
        {
          this.mNotification.setClientIntent(paramIntent);
          this.mPendingIntent = paramIntent;
          if (!isLVLCheckRequired(localDownloadsDB, this.mPackageInfo)) {
            break label103;
          }
          updateLVL(this);
          return;
        }
        if (this.mPendingIntent != null)
        {
          this.mNotification.setClientIntent(this.mPendingIntent);
          continue;
        }
        Log.e("LVLDL", "Downloader started in bad state without notification intent.");
      }
      finally
      {
        setServiceRunning(false);
      }
      setServiceRunning(false);
      continue;
      label103:
      paramIntent = localDownloadsDB.getDownloads();
      this.mBytesSoFar = 0L;
      this.mTotalLength = 0L;
      this.mFileCount = paramIntent.length;
      int j = paramIntent.length;
      Object localObject;
      for (int i = 0; i < j; i++)
      {
        localObject = paramIntent[i];
        if ((((DownloadInfo)localObject).mStatus == 200) && (!Helpers.doesFileExist(this, ((DownloadInfo)localObject).mFileName, ((DownloadInfo)localObject).mTotalBytes, true)))
        {
          ((DownloadInfo)localObject).mStatus = 0;
          ((DownloadInfo)localObject).mCurrentBytes = 0L;
        }
        this.mTotalLength += ((DownloadInfo)localObject).mTotalBytes;
        this.mBytesSoFar += ((DownloadInfo)localObject).mCurrentBytes;
      }
      pollNetworkState();
      if (this.mConnReceiver == null)
      {
        localObject = new com/google/android/vending/expansion/downloader/impl/DownloaderService$InnerBroadcastReceiver;
        ((InnerBroadcastReceiver)localObject).<init>(this, this);
        this.mConnReceiver = ((BroadcastReceiver)localObject);
        localObject = new android/content/IntentFilter;
        ((IntentFilter)localObject).<init>("android.net.conn.CONNECTIVITY_CHANGE");
        ((IntentFilter)localObject).addAction("android.net.wifi.WIFI_STATE_CHANGED");
        registerReceiver(this.mConnReceiver, (IntentFilter)localObject);
      }
      int k = paramIntent.length;
      i = 0;
      label282:
      if (i < k)
      {
        DownloadInfo localDownloadInfo = paramIntent[i];
        long l = localDownloadInfo.mCurrentBytes;
        if (localDownloadInfo.mStatus != 200)
        {
          localObject = new com/google/android/vending/expansion/downloader/impl/DownloadThread;
          ((DownloadThread)localObject).<init>(localDownloadInfo, this, this.mNotification);
          cancelAlarms();
          scheduleAlarm(5000L);
          ((DownloadThread)localObject).run();
          cancelAlarms();
        }
        localDownloadsDB.updateFromDb(localDownloadInfo);
        j = 0;
        switch (localDownloadInfo.mStatus)
        {
        default: 
          i = 19;
          label463:
          if (j != 0) {
            scheduleAlarm(60000L);
          }
          break;
        }
        for (;;)
        {
          this.mNotification.onDownloadStateChanged(i);
          setServiceRunning(false);
          break;
          updateLVL(this);
          setServiceRunning(false);
          break;
          this.mBytesSoFar += localDownloadInfo.mCurrentBytes - l;
          localDownloadsDB.updateMetadata(this.mPackageInfo.versionCode, 0);
          i++;
          break label282;
          i = 13;
          localDownloadInfo.mCurrentBytes = 0L;
          localDownloadsDB.updateDownload(localDownloadInfo);
          j = 1;
          break label463;
          i = 7;
          break label463;
          i = 6;
          j = 1;
          break label463;
          if ((this.mWifiManager != null) && (!this.mWifiManager.isWifiEnabled()))
          {
            i = 8;
            j = 1;
            break label463;
          }
          i = 9;
          j = 1;
          break label463;
          i = 18;
          j = 1;
          break label463;
          i = 17;
          j = 1;
          break label463;
          i = 14;
          j = 1;
          break label463;
          cancelAlarms();
        }
      }
      this.mNotification.onDownloadStateChanged(5);
      setServiceRunning(false);
    }
  }
  
  void pollNetworkState()
  {
    if (this.mConnectivityManager == null) {
      this.mConnectivityManager = ((ConnectivityManager)getSystemService("connectivity"));
    }
    if (this.mWifiManager == null) {
      this.mWifiManager = ((WifiManager)getSystemService("wifi"));
    }
    if (this.mConnectivityManager == null) {
      Log.w("LVLDL", "couldn't get connectivity manager to poll network state");
    }
    for (;;)
    {
      return;
      updateNetworkState(this.mConnectivityManager.getActiveNetworkInfo());
    }
  }
  
  public void requestAbortDownload()
  {
    this.mControl = 1;
    this.mStatus = 490;
  }
  
  public void requestContinueDownload()
  {
    if (this.mControl == 1) {
      this.mControl = 0;
    }
    Intent localIntent = new Intent(this, getClass());
    localIntent.putExtra("EPI", this.mPendingIntent);
    startService(localIntent);
  }
  
  public void requestDownloadStatus()
  {
    this.mNotification.resendState();
  }
  
  public void requestPauseDownload()
  {
    this.mControl = 1;
    this.mStatus = 193;
  }
  
  public void setDownloadFlags(int paramInt)
  {
    DownloadsDB.getDB(this).updateFlags(paramInt);
  }
  
  protected boolean shouldStop()
  {
    if (DownloadsDB.getDB(this).mStatus == 0) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public void updateLVL(Context paramContext)
  {
    paramContext = paramContext.getApplicationContext();
    new Handler(paramContext.getMainLooper()).post(new LVLRunnable(paramContext, this.mPendingIntent));
  }
  
  public static class GenerateSaveFileError
    extends Exception
  {
    private static final long serialVersionUID = 3465966015408936540L;
    String mMessage;
    int mStatus;
    
    public GenerateSaveFileError(int paramInt, String paramString)
    {
      this.mStatus = paramInt;
      this.mMessage = paramString;
    }
  }
  
  private class InnerBroadcastReceiver
    extends BroadcastReceiver
  {
    final Service mService;
    
    InnerBroadcastReceiver(Service paramService)
    {
      this.mService = paramService;
    }
    
    public void onReceive(Context paramContext, Intent paramIntent)
    {
      DownloaderService.this.pollNetworkState();
      if ((DownloaderService.this.mStateChanged) && (!DownloaderService.access$400()))
      {
        Log.d("LVLDL", "InnerBroadcastReceiver Called");
        paramIntent = new Intent(paramContext, this.mService.getClass());
        paramIntent.putExtra("EPI", DownloaderService.this.mPendingIntent);
        paramContext.startService(paramIntent);
      }
    }
  }
  
  private class LVLRunnable
    implements Runnable
  {
    final Context mContext;
    
    LVLRunnable(Context paramContext, PendingIntent paramPendingIntent)
    {
      this.mContext = paramContext;
      DownloaderService.access$002(DownloaderService.this, paramPendingIntent);
    }
    
    public void run()
    {
      DownloaderService.setServiceRunning(true);
      DownloaderService.this.mNotification.onDownloadStateChanged(2);
      final Object localObject = Settings.Secure.getString(this.mContext.getContentResolver(), "android_id");
      localObject = new APKExpansionPolicy(this.mContext, new AESObfuscator(DownloaderService.this.getSALT(), this.mContext.getPackageName(), (String)localObject));
      ((APKExpansionPolicy)localObject).resetPolicy();
      new LicenseChecker(this.mContext, (Policy)localObject, DownloaderService.this.getPublicKey()).checkAccess(new LicenseCheckerCallback()
      {
        public void allow(int paramAnonymousInt)
        {
          int i;
          for (;;)
          {
            int j;
            Object localObject2;
            try
            {
              int k = localObject.getExpansionURLCount();
              DownloadsDB localDownloadsDB = DownloadsDB.getDB(DownloaderService.LVLRunnable.this.mContext);
              i = 0;
              paramAnonymousInt = 0;
              if (k == 0) {
                break;
              }
              j = 0;
              i = paramAnonymousInt;
              if (j >= k) {
                break;
              }
              localObject2 = localObject.getExpansionFileName(j);
              i = paramAnonymousInt;
              DownloadInfo localDownloadInfo;
              long l;
              if (localObject2 != null)
              {
                localDownloadInfo = new com/google/android/vending/expansion/downloader/impl/DownloadInfo;
                localDownloadInfo.<init>(j, (String)localObject2, DownloaderService.LVLRunnable.this.mContext.getPackageName());
                l = localObject.getExpansionFileSize(j);
                if (DownloaderService.this.handleFileUpdated(localDownloadsDB, j, (String)localObject2, l))
                {
                  i = paramAnonymousInt | 0xFFFFFFFF;
                  localDownloadInfo.resetDownload();
                  localDownloadInfo.mUri = localObject.getExpansionURL(j);
                  localDownloadInfo.mTotalBytes = l;
                  localDownloadInfo.mStatus = i;
                  localDownloadsDB.updateDownload(localDownloadInfo);
                }
              }
              else
              {
                j++;
                paramAnonymousInt = i;
                continue;
              }
              localObject2 = localDownloadsDB.getDownloadInfoByFileName(localDownloadInfo.mFileName);
              if (localObject2 == null)
              {
                localObject2 = new java/lang/StringBuilder;
                ((StringBuilder)localObject2).<init>();
                Log.d("LVLDL", "file " + localDownloadInfo.mFileName + " found. Not downloading.");
                localDownloadInfo.mStatus = 200;
                localDownloadInfo.mTotalBytes = l;
                localDownloadInfo.mCurrentBytes = l;
                localDownloadInfo.mUri = localObject.getExpansionURL(j);
                localDownloadsDB.updateDownload(localDownloadInfo);
                i = paramAnonymousInt;
                continue;
              }
              i = paramAnonymousInt;
            }
            finally
            {
              DownloaderService.setServiceRunning(false);
            }
            if (((DownloadInfo)localObject2).mStatus != 200)
            {
              ((DownloadInfo)localObject2).mUri = localObject.getExpansionURL(j);
              ((DownloadsDB)localObject1).updateDownload((DownloadInfo)localObject2);
              i = paramAnonymousInt | 0xFFFFFFFF;
            }
          }
          for (;;)
          {
            try
            {
              ((DownloadsDB)localObject1).updateMetadata(DownloaderService.LVLRunnable.this.mContext.getPackageManager().getPackageInfo(DownloaderService.LVLRunnable.this.mContext.getPackageName(), 0).versionCode, i);
              Class localClass = DownloaderService.this.getClass();
              paramAnonymousInt = DownloaderService.startDownloadServiceIfRequired(DownloaderService.LVLRunnable.this.mContext, DownloaderService.this.mPendingIntent, localClass);
              switch (paramAnonymousInt)
              {
              default: 
                DownloaderService.setServiceRunning(false);
                return;
              }
            }
            catch (PackageManager.NameNotFoundException localNameNotFoundException)
            {
              localNameNotFoundException.printStackTrace();
              localRuntimeException = new java/lang/RuntimeException;
              localRuntimeException.<init>("Error with getting information from package name");
              throw localRuntimeException;
            }
            DownloaderService.this.mNotification.onDownloadStateChanged(5);
          }
          Log.e("LVLDL", "In LVL checking loop!");
          DownloaderService.this.mNotification.onDownloadStateChanged(15);
          RuntimeException localRuntimeException = new java/lang/RuntimeException;
          localRuntimeException.<init>("Error with LVL checking and database integrity");
          throw localRuntimeException;
        }
        
        public void applicationError(int paramAnonymousInt)
        {
          try
          {
            DownloaderService.this.mNotification.onDownloadStateChanged(16);
            return;
          }
          finally
          {
            DownloaderService.setServiceRunning(false);
          }
        }
        
        public void dontAllow(int paramAnonymousInt)
        {
          switch (paramAnonymousInt)
          {
          }
          for (;;)
          {
            DownloaderService.setServiceRunning(false);
            return;
            try
            {
              DownloaderService.this.mNotification.onDownloadStateChanged(15);
              continue;
            }
            finally
            {
              DownloaderService.setServiceRunning(false);
            }
            DownloaderService.this.mNotification.onDownloadStateChanged(16);
          }
        }
      });
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\vending\expansion\downloader\impl\DownloaderService.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */