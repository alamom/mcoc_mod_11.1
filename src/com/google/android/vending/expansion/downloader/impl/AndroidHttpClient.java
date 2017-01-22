package com.google.android.vending.expansion.downloader.impl;

import android.content.ContentResolver;
import android.os.Looper;
import android.util.Log;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpException;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.entity.AbstractHttpEntity;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.RequestWrapper;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.BasicHttpProcessor;
import org.apache.http.protocol.HttpContext;

public final class AndroidHttpClient
  implements HttpClient
{
  public static long DEFAULT_SYNC_MIN_GZIP_BYTES = 0L;
  private static final int SOCKET_OPERATION_TIMEOUT = 60000;
  private static final String TAG = "AndroidHttpClient";
  static Class<?> sSslSessionCacheClass;
  private static final HttpRequestInterceptor sThreadCheckInterceptor;
  private volatile LoggingConfiguration curlConfiguration;
  private final HttpClient delegate;
  private RuntimeException mLeakedException = new IllegalStateException("AndroidHttpClient created and never closed");
  
  static
  {
    try
    {
      sSslSessionCacheClass = Class.forName("android.net.SSLSessionCache");
      DEFAULT_SYNC_MIN_GZIP_BYTES = 256L;
      sThreadCheckInterceptor = new HttpRequestInterceptor()
      {
        public void process(HttpRequest paramAnonymousHttpRequest, HttpContext paramAnonymousHttpContext)
        {
          if ((Looper.myLooper() != null) && (Looper.myLooper() == Looper.getMainLooper())) {
            throw new RuntimeException("This thread forbids HTTP requests");
          }
        }
      };
      return;
    }
    catch (Exception localException)
    {
      for (;;) {}
    }
  }
  
  private AndroidHttpClient(ClientConnectionManager paramClientConnectionManager, HttpParams paramHttpParams)
  {
    this.delegate = new DefaultHttpClient(paramClientConnectionManager, paramHttpParams)
    {
      protected HttpContext createHttpContext()
      {
        BasicHttpContext localBasicHttpContext = new BasicHttpContext();
        localBasicHttpContext.setAttribute("http.authscheme-registry", getAuthSchemes());
        localBasicHttpContext.setAttribute("http.cookiespec-registry", getCookieSpecs());
        localBasicHttpContext.setAttribute("http.auth.credentials-provider", getCredentialsProvider());
        return localBasicHttpContext;
      }
      
      protected BasicHttpProcessor createHttpProcessor()
      {
        BasicHttpProcessor localBasicHttpProcessor = super.createHttpProcessor();
        localBasicHttpProcessor.addRequestInterceptor(AndroidHttpClient.sThreadCheckInterceptor);
        localBasicHttpProcessor.addRequestInterceptor(new AndroidHttpClient.CurlLogger(AndroidHttpClient.this, null));
        return localBasicHttpProcessor;
      }
    };
  }
  
  public static AbstractHttpEntity getCompressedEntity(byte[] paramArrayOfByte, ContentResolver paramContentResolver)
    throws IOException
  {
    if (paramArrayOfByte.length < getMinGzipSize(paramContentResolver)) {
      paramArrayOfByte = new ByteArrayEntity(paramArrayOfByte);
    }
    for (;;)
    {
      return paramArrayOfByte;
      paramContentResolver = new ByteArrayOutputStream();
      GZIPOutputStream localGZIPOutputStream = new GZIPOutputStream(paramContentResolver);
      localGZIPOutputStream.write(paramArrayOfByte);
      localGZIPOutputStream.close();
      paramArrayOfByte = new ByteArrayEntity(paramContentResolver.toByteArray());
      paramArrayOfByte.setContentEncoding("gzip");
    }
  }
  
  public static long getMinGzipSize(ContentResolver paramContentResolver)
  {
    return DEFAULT_SYNC_MIN_GZIP_BYTES;
  }
  
  public static InputStream getUngzippedContent(HttpEntity paramHttpEntity)
    throws IOException
  {
    InputStream localInputStream = paramHttpEntity.getContent();
    if (localInputStream == null) {
      paramHttpEntity = localInputStream;
    }
    for (;;)
    {
      return paramHttpEntity;
      paramHttpEntity = paramHttpEntity.getContentEncoding();
      if (paramHttpEntity == null)
      {
        paramHttpEntity = localInputStream;
      }
      else
      {
        String str = paramHttpEntity.getValue();
        if (str == null)
        {
          paramHttpEntity = localInputStream;
        }
        else
        {
          paramHttpEntity = localInputStream;
          if (str.contains("gzip")) {
            paramHttpEntity = new GZIPInputStream(localInputStream);
          }
        }
      }
    }
  }
  
  public static void modifyRequestToAcceptGzipResponse(HttpRequest paramHttpRequest)
  {
    paramHttpRequest.addHeader("Accept-Encoding", "gzip");
  }
  
  public static AndroidHttpClient newInstance(String paramString)
  {
    return newInstance(paramString, null);
  }
  
  /* Error */
  public static AndroidHttpClient newInstance(String paramString, android.content.Context paramContext)
  {
    // Byte code:
    //   0: new 188	org/apache/http/params/BasicHttpParams
    //   3: dup
    //   4: invokespecial 189	org/apache/http/params/BasicHttpParams:<init>	()V
    //   7: astore 4
    //   9: aload 4
    //   11: iconst_0
    //   12: invokestatic 195	org/apache/http/params/HttpConnectionParams:setStaleCheckingEnabled	(Lorg/apache/http/params/HttpParams;Z)V
    //   15: aload 4
    //   17: ldc 23
    //   19: invokestatic 199	org/apache/http/params/HttpConnectionParams:setConnectionTimeout	(Lorg/apache/http/params/HttpParams;I)V
    //   22: aload 4
    //   24: ldc 23
    //   26: invokestatic 202	org/apache/http/params/HttpConnectionParams:setSoTimeout	(Lorg/apache/http/params/HttpParams;I)V
    //   29: aload 4
    //   31: sipush 8192
    //   34: invokestatic 205	org/apache/http/params/HttpConnectionParams:setSocketBufferSize	(Lorg/apache/http/params/HttpParams;I)V
    //   37: aload 4
    //   39: iconst_0
    //   40: invokestatic 210	org/apache/http/client/params/HttpClientParams:setRedirecting	(Lorg/apache/http/params/HttpParams;Z)V
    //   43: aconst_null
    //   44: astore_3
    //   45: aload_3
    //   46: astore_2
    //   47: aload_1
    //   48: ifnull +38 -> 86
    //   51: aload_3
    //   52: astore_2
    //   53: getstatic 52	com/google/android/vending/expansion/downloader/impl/AndroidHttpClient:sSslSessionCacheClass	Ljava/lang/Class;
    //   56: ifnull +30 -> 86
    //   59: getstatic 52	com/google/android/vending/expansion/downloader/impl/AndroidHttpClient:sSslSessionCacheClass	Ljava/lang/Class;
    //   62: iconst_1
    //   63: anewarray 46	java/lang/Class
    //   66: dup
    //   67: iconst_0
    //   68: ldc -44
    //   70: aastore
    //   71: invokevirtual 216	java/lang/Class:getConstructor	([Ljava/lang/Class;)Ljava/lang/reflect/Constructor;
    //   74: iconst_1
    //   75: anewarray 4	java/lang/Object
    //   78: dup
    //   79: iconst_0
    //   80: aload_1
    //   81: aastore
    //   82: invokevirtual 221	java/lang/reflect/Constructor:newInstance	([Ljava/lang/Object;)Ljava/lang/Object;
    //   85: astore_2
    //   86: aload 4
    //   88: aload_0
    //   89: invokestatic 227	org/apache/http/params/HttpProtocolParams:setUserAgent	(Lorg/apache/http/params/HttpParams;Ljava/lang/String;)V
    //   92: new 229	org/apache/http/conn/scheme/SchemeRegistry
    //   95: dup
    //   96: invokespecial 230	org/apache/http/conn/scheme/SchemeRegistry:<init>	()V
    //   99: astore_3
    //   100: aload_3
    //   101: new 232	org/apache/http/conn/scheme/Scheme
    //   104: dup
    //   105: ldc -22
    //   107: invokestatic 240	org/apache/http/conn/scheme/PlainSocketFactory:getSocketFactory	()Lorg/apache/http/conn/scheme/PlainSocketFactory;
    //   110: bipush 80
    //   112: invokespecial 243	org/apache/http/conn/scheme/Scheme:<init>	(Ljava/lang/String;Lorg/apache/http/conn/scheme/SocketFactory;I)V
    //   115: invokevirtual 247	org/apache/http/conn/scheme/SchemeRegistry:register	(Lorg/apache/http/conn/scheme/Scheme;)Lorg/apache/http/conn/scheme/Scheme;
    //   118: pop
    //   119: aconst_null
    //   120: astore_1
    //   121: aload_1
    //   122: astore_0
    //   123: aload_2
    //   124: ifnull +50 -> 174
    //   127: ldc -7
    //   129: ldc -5
    //   131: iconst_2
    //   132: anewarray 46	java/lang/Class
    //   135: dup
    //   136: iconst_0
    //   137: getstatic 256	java/lang/Integer:TYPE	Ljava/lang/Class;
    //   140: aastore
    //   141: dup
    //   142: iconst_1
    //   143: getstatic 52	com/google/android/vending/expansion/downloader/impl/AndroidHttpClient:sSslSessionCacheClass	Ljava/lang/Class;
    //   146: aastore
    //   147: invokevirtual 260	java/lang/Class:getDeclaredMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   150: aconst_null
    //   151: iconst_2
    //   152: anewarray 4	java/lang/Object
    //   155: dup
    //   156: iconst_0
    //   157: ldc 23
    //   159: invokestatic 264	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   162: aastore
    //   163: dup
    //   164: iconst_1
    //   165: aload_2
    //   166: aastore
    //   167: invokevirtual 270	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   170: checkcast 272	org/apache/http/conn/scheme/SocketFactory
    //   173: astore_0
    //   174: aload_0
    //   175: astore_1
    //   176: aload_0
    //   177: ifnonnull +7 -> 184
    //   180: invokestatic 277	org/apache/http/conn/ssl/SSLSocketFactory:getSocketFactory	()Lorg/apache/http/conn/ssl/SSLSocketFactory;
    //   183: astore_1
    //   184: aload_3
    //   185: new 232	org/apache/http/conn/scheme/Scheme
    //   188: dup
    //   189: ldc_w 279
    //   192: aload_1
    //   193: sipush 443
    //   196: invokespecial 243	org/apache/http/conn/scheme/Scheme:<init>	(Ljava/lang/String;Lorg/apache/http/conn/scheme/SocketFactory;I)V
    //   199: invokevirtual 247	org/apache/http/conn/scheme/SchemeRegistry:register	(Lorg/apache/http/conn/scheme/Scheme;)Lorg/apache/http/conn/scheme/Scheme;
    //   202: pop
    //   203: new 2	com/google/android/vending/expansion/downloader/impl/AndroidHttpClient
    //   206: dup
    //   207: new 281	org/apache/http/impl/conn/tsccm/ThreadSafeClientConnManager
    //   210: dup
    //   211: aload 4
    //   213: aload_3
    //   214: invokespecial 284	org/apache/http/impl/conn/tsccm/ThreadSafeClientConnManager:<init>	(Lorg/apache/http/params/HttpParams;Lorg/apache/http/conn/scheme/SchemeRegistry;)V
    //   217: aload 4
    //   219: invokespecial 286	com/google/android/vending/expansion/downloader/impl/AndroidHttpClient:<init>	(Lorg/apache/http/conn/ClientConnectionManager;Lorg/apache/http/params/HttpParams;)V
    //   222: areturn
    //   223: astore_1
    //   224: aload_1
    //   225: invokevirtual 289	java/lang/SecurityException:printStackTrace	()V
    //   228: aload_3
    //   229: astore_2
    //   230: goto -144 -> 86
    //   233: astore_1
    //   234: aload_1
    //   235: invokevirtual 290	java/lang/NoSuchMethodException:printStackTrace	()V
    //   238: aload_3
    //   239: astore_2
    //   240: goto -154 -> 86
    //   243: astore_1
    //   244: aload_1
    //   245: invokevirtual 291	java/lang/IllegalArgumentException:printStackTrace	()V
    //   248: aload_3
    //   249: astore_2
    //   250: goto -164 -> 86
    //   253: astore_1
    //   254: aload_1
    //   255: invokevirtual 292	java/lang/InstantiationException:printStackTrace	()V
    //   258: aload_3
    //   259: astore_2
    //   260: goto -174 -> 86
    //   263: astore_1
    //   264: aload_1
    //   265: invokevirtual 293	java/lang/IllegalAccessException:printStackTrace	()V
    //   268: aload_3
    //   269: astore_2
    //   270: goto -184 -> 86
    //   273: astore_1
    //   274: aload_1
    //   275: invokevirtual 294	java/lang/reflect/InvocationTargetException:printStackTrace	()V
    //   278: aload_3
    //   279: astore_2
    //   280: goto -194 -> 86
    //   283: astore_0
    //   284: aload_0
    //   285: invokevirtual 289	java/lang/SecurityException:printStackTrace	()V
    //   288: aload_1
    //   289: astore_0
    //   290: goto -116 -> 174
    //   293: astore_0
    //   294: aload_0
    //   295: invokevirtual 290	java/lang/NoSuchMethodException:printStackTrace	()V
    //   298: aload_1
    //   299: astore_0
    //   300: goto -126 -> 174
    //   303: astore_0
    //   304: aload_0
    //   305: invokevirtual 291	java/lang/IllegalArgumentException:printStackTrace	()V
    //   308: aload_1
    //   309: astore_0
    //   310: goto -136 -> 174
    //   313: astore_0
    //   314: aload_0
    //   315: invokevirtual 293	java/lang/IllegalAccessException:printStackTrace	()V
    //   318: aload_1
    //   319: astore_0
    //   320: goto -146 -> 174
    //   323: astore_0
    //   324: aload_0
    //   325: invokevirtual 294	java/lang/reflect/InvocationTargetException:printStackTrace	()V
    //   328: aload_1
    //   329: astore_0
    //   330: goto -156 -> 174
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	333	0	paramString	String
    //   0	333	1	paramContext	android.content.Context
    //   46	234	2	localObject	Object
    //   44	235	3	localSchemeRegistry	org.apache.http.conn.scheme.SchemeRegistry
    //   7	211	4	localBasicHttpParams	org.apache.http.params.BasicHttpParams
    // Exception table:
    //   from	to	target	type
    //   59	86	223	java/lang/SecurityException
    //   59	86	233	java/lang/NoSuchMethodException
    //   59	86	243	java/lang/IllegalArgumentException
    //   59	86	253	java/lang/InstantiationException
    //   59	86	263	java/lang/IllegalAccessException
    //   59	86	273	java/lang/reflect/InvocationTargetException
    //   127	174	283	java/lang/SecurityException
    //   127	174	293	java/lang/NoSuchMethodException
    //   127	174	303	java/lang/IllegalArgumentException
    //   127	174	313	java/lang/IllegalAccessException
    //   127	174	323	java/lang/reflect/InvocationTargetException
  }
  
  public static long parseDate(String paramString)
  {
    return HttpDateTime.parse(paramString);
  }
  
  private static String toCurl(HttpUriRequest paramHttpUriRequest, boolean paramBoolean)
    throws IOException
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("curl ");
    Object localObject2 = paramHttpUriRequest.getAllHeaders();
    int j = localObject2.length;
    int i = 0;
    if (i < j)
    {
      localObject1 = localObject2[i];
      if ((!paramBoolean) && ((((Header)localObject1).getName().equals("Authorization")) || (((Header)localObject1).getName().equals("Cookie")))) {}
      for (;;)
      {
        i++;
        break;
        localStringBuilder.append("--header \"");
        localStringBuilder.append(localObject1.toString().trim());
        localStringBuilder.append("\" ");
      }
    }
    localObject2 = paramHttpUriRequest.getURI();
    Object localObject1 = localObject2;
    if ((paramHttpUriRequest instanceof RequestWrapper))
    {
      HttpRequest localHttpRequest = ((RequestWrapper)paramHttpUriRequest).getOriginal();
      localObject1 = localObject2;
      if ((localHttpRequest instanceof HttpUriRequest)) {
        localObject1 = ((HttpUriRequest)localHttpRequest).getURI();
      }
    }
    localStringBuilder.append("\"");
    localStringBuilder.append(localObject1);
    localStringBuilder.append("\"");
    if ((paramHttpUriRequest instanceof HttpEntityEnclosingRequest))
    {
      paramHttpUriRequest = ((HttpEntityEnclosingRequest)paramHttpUriRequest).getEntity();
      if ((paramHttpUriRequest != null) && (paramHttpUriRequest.isRepeatable()))
      {
        if (paramHttpUriRequest.getContentLength() >= 1024L) {
          break label289;
        }
        localObject1 = new ByteArrayOutputStream();
        paramHttpUriRequest.writeTo((OutputStream)localObject1);
        paramHttpUriRequest = ((ByteArrayOutputStream)localObject1).toString();
        localStringBuilder.append(" --data-ascii \"").append(paramHttpUriRequest).append("\"");
      }
    }
    for (;;)
    {
      return localStringBuilder.toString();
      label289:
      localStringBuilder.append(" [TOO MUCH DATA TO INCLUDE]");
    }
  }
  
  public void close()
  {
    if (this.mLeakedException != null)
    {
      getConnectionManager().shutdown();
      this.mLeakedException = null;
    }
  }
  
  public void disableCurlLogging()
  {
    this.curlConfiguration = null;
  }
  
  public void enableCurlLogging(String paramString, int paramInt)
  {
    if (paramString == null) {
      throw new NullPointerException("name");
    }
    if ((paramInt < 2) || (paramInt > 7)) {
      throw new IllegalArgumentException("Level is out of range [2..7]");
    }
    this.curlConfiguration = new LoggingConfiguration(paramString, paramInt, null);
  }
  
  public <T> T execute(HttpHost paramHttpHost, HttpRequest paramHttpRequest, ResponseHandler<? extends T> paramResponseHandler)
    throws IOException, ClientProtocolException
  {
    return (T)this.delegate.execute(paramHttpHost, paramHttpRequest, paramResponseHandler);
  }
  
  public <T> T execute(HttpHost paramHttpHost, HttpRequest paramHttpRequest, ResponseHandler<? extends T> paramResponseHandler, HttpContext paramHttpContext)
    throws IOException, ClientProtocolException
  {
    return (T)this.delegate.execute(paramHttpHost, paramHttpRequest, paramResponseHandler, paramHttpContext);
  }
  
  public <T> T execute(HttpUriRequest paramHttpUriRequest, ResponseHandler<? extends T> paramResponseHandler)
    throws IOException, ClientProtocolException
  {
    return (T)this.delegate.execute(paramHttpUriRequest, paramResponseHandler);
  }
  
  public <T> T execute(HttpUriRequest paramHttpUriRequest, ResponseHandler<? extends T> paramResponseHandler, HttpContext paramHttpContext)
    throws IOException, ClientProtocolException
  {
    return (T)this.delegate.execute(paramHttpUriRequest, paramResponseHandler, paramHttpContext);
  }
  
  public HttpResponse execute(HttpHost paramHttpHost, HttpRequest paramHttpRequest)
    throws IOException
  {
    return this.delegate.execute(paramHttpHost, paramHttpRequest);
  }
  
  public HttpResponse execute(HttpHost paramHttpHost, HttpRequest paramHttpRequest, HttpContext paramHttpContext)
    throws IOException
  {
    return this.delegate.execute(paramHttpHost, paramHttpRequest, paramHttpContext);
  }
  
  public HttpResponse execute(HttpUriRequest paramHttpUriRequest)
    throws IOException
  {
    return this.delegate.execute(paramHttpUriRequest);
  }
  
  public HttpResponse execute(HttpUriRequest paramHttpUriRequest, HttpContext paramHttpContext)
    throws IOException
  {
    return this.delegate.execute(paramHttpUriRequest, paramHttpContext);
  }
  
  protected void finalize()
    throws Throwable
  {
    super.finalize();
    if (this.mLeakedException != null)
    {
      Log.e("AndroidHttpClient", "Leak found", this.mLeakedException);
      this.mLeakedException = null;
    }
  }
  
  public ClientConnectionManager getConnectionManager()
  {
    return this.delegate.getConnectionManager();
  }
  
  public HttpParams getParams()
  {
    return this.delegate.getParams();
  }
  
  private class CurlLogger
    implements HttpRequestInterceptor
  {
    private CurlLogger() {}
    
    public void process(HttpRequest paramHttpRequest, HttpContext paramHttpContext)
      throws HttpException, IOException
    {
      paramHttpContext = AndroidHttpClient.this.curlConfiguration;
      if ((paramHttpContext != null) && (AndroidHttpClient.LoggingConfiguration.access$400(paramHttpContext)) && ((paramHttpRequest instanceof HttpUriRequest))) {
        AndroidHttpClient.LoggingConfiguration.access$600(paramHttpContext, AndroidHttpClient.toCurl((HttpUriRequest)paramHttpRequest, false));
      }
    }
  }
  
  private static class LoggingConfiguration
  {
    private final int level;
    private final String tag;
    
    private LoggingConfiguration(String paramString, int paramInt)
    {
      this.tag = paramString;
      this.level = paramInt;
    }
    
    private boolean isLoggable()
    {
      return Log.isLoggable(this.tag, this.level);
    }
    
    private void println(String paramString)
    {
      Log.println(this.level, this.tag, paramString);
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\vending\expansion\downloader\impl\AndroidHttpClient.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */