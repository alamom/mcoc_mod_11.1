package com.facebook;

import android.content.ContentResolver;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import com.facebook.internal.AttributionIdentifiers;
import com.facebook.internal.Utility;
import com.facebook.internal.Utility.FetchedAppSettings;
import com.facebook.internal.Validate;
import com.facebook.model.GraphObject;
import com.facebook.model.GraphObject.Factory;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import org.json.JSONException;
import org.json.JSONObject;

public final class Settings
{
  private static final String ANALYTICS_EVENT = "event";
  public static final String APPLICATION_ID_PROPERTY = "com.facebook.sdk.ApplicationId";
  private static final String APP_EVENT_PREFERENCES = "com.facebook.sdk.appEventPreferences";
  private static final String ATTRIBUTION_ID_COLUMN_NAME = "aid";
  private static final Uri ATTRIBUTION_ID_CONTENT_URI;
  private static final String ATTRIBUTION_PREFERENCES = "com.facebook.sdk.attributionTracking";
  private static final String AUTO_PUBLISH = "auto_publish";
  public static final String CLIENT_TOKEN_PROPERTY = "com.facebook.sdk.ClientToken";
  private static final int DEFAULT_CORE_POOL_SIZE = 5;
  private static final int DEFAULT_KEEP_ALIVE = 1;
  private static final int DEFAULT_MAXIMUM_POOL_SIZE = 128;
  private static final ThreadFactory DEFAULT_THREAD_FACTORY = new ThreadFactory()
  {
    private final AtomicInteger counter = new AtomicInteger(0);
    
    public Thread newThread(Runnable paramAnonymousRunnable)
    {
      return new Thread(paramAnonymousRunnable, "FacebookSdk #" + this.counter.incrementAndGet());
    }
  };
  private static final BlockingQueue<Runnable> DEFAULT_WORK_QUEUE;
  private static final String FACEBOOK_COM = "facebook.com";
  private static final Object LOCK;
  private static final String MOBILE_INSTALL_EVENT = "MOBILE_APP_INSTALL";
  private static final String PUBLISH_ACTIVITY_PATH = "%s/activities";
  private static final String TAG = Settings.class.getCanonicalName();
  private static volatile String appClientToken;
  private static volatile String appVersion;
  private static volatile String applicationId;
  private static volatile boolean defaultsLoaded;
  private static volatile Executor executor;
  private static volatile String facebookDomain;
  private static volatile boolean isDebugEnabled;
  private static final HashSet<LoggingBehavior> loggingBehaviors = new HashSet(Arrays.asList(new LoggingBehavior[] { LoggingBehavior.DEVELOPER_ERRORS }));
  private static AtomicLong onProgressThreshold;
  private static volatile boolean platformCompatibilityEnabled;
  private static Boolean sdkInitialized = Boolean.valueOf(false);
  private static volatile boolean shouldAutoPublishInstall;
  
  static
  {
    defaultsLoaded = false;
    facebookDomain = "facebook.com";
    onProgressThreshold = new AtomicLong(65536L);
    isDebugEnabled = true;
    LOCK = new Object();
    ATTRIBUTION_ID_CONTENT_URI = Uri.parse("content://com.facebook.katana.provider.AttributionIdProvider");
    DEFAULT_WORK_QUEUE = new LinkedBlockingQueue(10);
  }
  
  public static final void addLoggingBehavior(LoggingBehavior paramLoggingBehavior)
  {
    synchronized (loggingBehaviors)
    {
      loggingBehaviors.add(paramLoggingBehavior);
      return;
    }
  }
  
  public static final void clearLoggingBehaviors()
  {
    synchronized (loggingBehaviors)
    {
      loggingBehaviors.clear();
      return;
    }
  }
  
  public static String getAppVersion()
  {
    return appVersion;
  }
  
  public static String getApplicationId()
  {
    return applicationId;
  }
  
  /* Error */
  public static String getApplicationSignature(Context paramContext)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_2
    //   2: aload_0
    //   3: ifnonnull +7 -> 10
    //   6: aload_2
    //   7: astore_1
    //   8: aload_1
    //   9: areturn
    //   10: aload_0
    //   11: invokevirtual 182	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   14: astore_3
    //   15: aload_2
    //   16: astore_1
    //   17: aload_3
    //   18: ifnull -10 -> 8
    //   21: aload_0
    //   22: invokevirtual 185	android/content/Context:getPackageName	()Ljava/lang/String;
    //   25: astore_0
    //   26: aload_3
    //   27: aload_0
    //   28: bipush 64
    //   30: invokevirtual 191	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   33: astore_0
    //   34: aload_0
    //   35: getfield 197	android/content/pm/PackageInfo:signatures	[Landroid/content/pm/Signature;
    //   38: astore_3
    //   39: aload_2
    //   40: astore_1
    //   41: aload_3
    //   42: ifnull -34 -> 8
    //   45: aload_2
    //   46: astore_1
    //   47: aload_3
    //   48: arraylength
    //   49: ifeq -41 -> 8
    //   52: ldc -57
    //   54: invokestatic 205	java/security/MessageDigest:getInstance	(Ljava/lang/String;)Ljava/security/MessageDigest;
    //   57: astore_1
    //   58: aload_1
    //   59: aload_0
    //   60: getfield 197	android/content/pm/PackageInfo:signatures	[Landroid/content/pm/Signature;
    //   63: iconst_0
    //   64: aaload
    //   65: invokevirtual 211	android/content/pm/Signature:toByteArray	()[B
    //   68: invokevirtual 215	java/security/MessageDigest:update	([B)V
    //   71: aload_1
    //   72: invokevirtual 218	java/security/MessageDigest:digest	()[B
    //   75: bipush 9
    //   77: invokestatic 224	android/util/Base64:encodeToString	([BI)Ljava/lang/String;
    //   80: astore_1
    //   81: goto -73 -> 8
    //   84: astore_0
    //   85: aload_2
    //   86: astore_1
    //   87: goto -79 -> 8
    //   90: astore_0
    //   91: aload_2
    //   92: astore_1
    //   93: goto -85 -> 8
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	96	0	paramContext	Context
    //   7	86	1	localObject1	Object
    //   1	91	2	localObject2	Object
    //   14	34	3	localObject3	Object
    // Exception table:
    //   from	to	target	type
    //   26	34	84	android/content/pm/PackageManager$NameNotFoundException
    //   52	58	90	java/security/NoSuchAlgorithmException
  }
  
  private static Executor getAsyncTaskExecutor()
  {
    try
    {
      localObject1 = AsyncTask.class.getField("THREAD_POOL_EXECUTOR");
    }
    catch (NoSuchFieldException localNoSuchFieldException)
    {
      for (;;)
      {
        Object localObject1;
        label20:
        Object localObject2 = null;
      }
    }
    try
    {
      localObject1 = ((Field)localObject1).get(null);
      if (localObject1 != null) {
        break label34;
      }
      localObject1 = null;
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      Executor localExecutor = null;
      break label20;
      if ((localExecutor instanceof Executor)) {
        break label46;
      }
      localExecutor = null;
      break label20;
      localExecutor = (Executor)localExecutor;
      break label20;
    }
    return (Executor)localObject1;
  }
  
  public static String getAttributionId(ContentResolver paramContentResolver)
  {
    for (;;)
    {
      try
      {
        localCursor = paramContentResolver.query(ATTRIBUTION_ID_CONTENT_URI, new String[] { "aid" }, null, null, null);
        if ((localCursor != null) && (localCursor.moveToFirst())) {
          continue;
        }
        paramContentResolver = null;
      }
      catch (Exception paramContentResolver)
      {
        Cursor localCursor;
        Log.d(TAG, "Caught unexpected exception in getAttributionId(): " + paramContentResolver.toString());
        paramContentResolver = null;
        continue;
      }
      return paramContentResolver;
      paramContentResolver = localCursor.getString(localCursor.getColumnIndex("aid"));
      localCursor.close();
    }
  }
  
  public static String getClientToken()
  {
    return appClientToken;
  }
  
  public static Executor getExecutor()
  {
    synchronized (LOCK)
    {
      if (executor == null)
      {
        Executor localExecutor = getAsyncTaskExecutor();
        Object localObject1 = localExecutor;
        if (localExecutor == null)
        {
          localObject1 = new java/util/concurrent/ThreadPoolExecutor;
          ((ThreadPoolExecutor)localObject1).<init>(5, 128, 1L, TimeUnit.SECONDS, DEFAULT_WORK_QUEUE, DEFAULT_THREAD_FACTORY);
        }
        executor = (Executor)localObject1;
      }
      return executor;
    }
  }
  
  public static String getFacebookDomain()
  {
    return facebookDomain;
  }
  
  public static boolean getLimitEventAndDataUsage(Context paramContext)
  {
    return paramContext.getSharedPreferences("com.facebook.sdk.appEventPreferences", 0).getBoolean("limitEventUsage", false);
  }
  
  public static final Set<LoggingBehavior> getLoggingBehaviors()
  {
    synchronized (loggingBehaviors)
    {
      Object localObject1 = new java/util/HashSet;
      ((HashSet)localObject1).<init>(loggingBehaviors);
      localObject1 = Collections.unmodifiableSet((Set)localObject1);
      return (Set<LoggingBehavior>)localObject1;
    }
  }
  
  public static long getOnProgressThreshold()
  {
    return onProgressThreshold.get();
  }
  
  public static boolean getPlatformCompatibilityEnabled()
  {
    return platformCompatibilityEnabled;
  }
  
  public static String getSdkVersion()
  {
    return "3.20.0/Unity.6.2.2";
  }
  
  @Deprecated
  public static boolean getShouldAutoPublishInstall()
  {
    return shouldAutoPublishInstall;
  }
  
  public static final boolean isDebugEnabled()
  {
    return isDebugEnabled;
  }
  
  public static final boolean isLoggingBehaviorEnabled(LoggingBehavior paramLoggingBehavior)
  {
    synchronized (loggingBehaviors)
    {
      if ((isDebugEnabled()) && (loggingBehaviors.contains(paramLoggingBehavior)))
      {
        bool = true;
        return bool;
      }
      boolean bool = false;
    }
  }
  
  @Deprecated
  public static final boolean isLoggingEnabled()
  {
    return isDebugEnabled();
  }
  
  public static void loadDefaultsFromMetadata(Context paramContext)
  {
    defaultsLoaded = true;
    if (paramContext == null) {}
    for (;;)
    {
      return;
      try
      {
        paramContext = paramContext.getPackageManager().getApplicationInfo(paramContext.getPackageName(), 128);
        if ((paramContext != null) && (paramContext.metaData != null))
        {
          if (applicationId == null) {
            applicationId = paramContext.metaData.getString("com.facebook.sdk.ApplicationId");
          }
          if (appClientToken == null) {
            appClientToken = paramContext.metaData.getString("com.facebook.sdk.ClientToken");
          }
        }
      }
      catch (PackageManager.NameNotFoundException paramContext) {}
    }
  }
  
  static void loadDefaultsFromMetadataIfNeeded(Context paramContext)
  {
    if (!defaultsLoaded) {
      loadDefaultsFromMetadata(paramContext);
    }
  }
  
  static Response publishInstallAndWaitForResponse(Context paramContext, String paramString, boolean paramBoolean)
  {
    if ((paramContext == null) || (paramString == null)) {
      try
      {
        paramContext = new java/lang/IllegalArgumentException;
        paramContext.<init>("Both context and applicationId must be non-null");
        throw paramContext;
      }
      catch (Exception paramContext)
      {
        Utility.logd("Facebook-publish", paramContext);
        paramContext = new Response(null, null, new FacebookRequestError(null, paramContext));
      }
    }
    for (;;)
    {
      return paramContext;
      AttributionIdentifiers localAttributionIdentifiers = AttributionIdentifiers.getAttributionIdentifiers(paramContext);
      SharedPreferences localSharedPreferences = paramContext.getSharedPreferences("com.facebook.sdk.attributionTracking", 0);
      Object localObject = new java/lang/StringBuilder;
      ((StringBuilder)localObject).<init>();
      String str1 = paramString + "ping";
      localObject = new java/lang/StringBuilder;
      ((StringBuilder)localObject).<init>();
      String str3 = paramString + "json";
      long l = localSharedPreferences.getLong(str1, 0L);
      String str2 = localSharedPreferences.getString(str3, null);
      if (!paramBoolean) {
        setShouldAutoPublishInstall(false);
      }
      localObject = GraphObject.Factory.create();
      ((GraphObject)localObject).setProperty("event", "MOBILE_APP_INSTALL");
      Utility.setAppEventAttributionParameters((GraphObject)localObject, localAttributionIdentifiers, Utility.getHashedDeviceAndAppID(paramContext, paramString), getLimitEventAndDataUsage(paramContext));
      ((GraphObject)localObject).setProperty("auto_publish", Boolean.valueOf(paramBoolean));
      ((GraphObject)localObject).setProperty("application_package_name", paramContext.getPackageName());
      localObject = Request.newPostRequest(null, String.format("%s/activities", new Object[] { paramString }), (GraphObject)localObject, null);
      if (l != 0L)
      {
        paramString = null;
        paramContext = paramString;
        if (str2 == null) {}
      }
      try
      {
        paramContext = new org/json/JSONObject;
        paramContext.<init>(str2);
        paramContext = GraphObject.Factory.create(paramContext);
        if (paramContext == null)
        {
          paramContext = new com/facebook/RequestBatch;
          paramContext.<init>(new Request[] { localObject });
          paramContext = (Response)Response.createResponsesFromString("true", null, paramContext, true).get(0);
          continue;
        }
        paramContext = new Response(null, null, null, paramContext, true);
        continue;
        if ((localAttributionIdentifiers == null) || ((localAttributionIdentifiers.getAndroidAdvertiserId() == null) && (localAttributionIdentifiers.getAttributionId() == null)))
        {
          paramContext = new com/facebook/FacebookException;
          paramContext.<init>("No attribution id available to send to server.");
          throw paramContext;
        }
        if (!Utility.queryAppSettings(paramString, false).supportsAttribution())
        {
          paramContext = new com/facebook/FacebookException;
          paramContext.<init>("Install attribution has been disabled on the server.");
          throw paramContext;
        }
        paramContext = ((Request)localObject).executeAndWait();
        paramString = localSharedPreferences.edit();
        paramString.putLong(str1, System.currentTimeMillis());
        if ((paramContext.getGraphObject() != null) && (paramContext.getGraphObject().getInnerJSONObject() != null)) {
          paramString.putString(str3, paramContext.getGraphObject().getInnerJSONObject().toString());
        }
        paramString.apply();
      }
      catch (JSONException paramContext)
      {
        for (;;)
        {
          paramContext = paramString;
        }
      }
    }
  }
  
  static void publishInstallAsync(Context paramContext, final String paramString, final Request.Callback paramCallback)
  {
    paramContext = paramContext.getApplicationContext();
    getExecutor().execute(new Runnable()
    {
      public void run()
      {
        final Response localResponse = Settings.publishInstallAndWaitForResponse(this.val$applicationContext, paramString, false);
        if (paramCallback != null) {
          new Handler(Looper.getMainLooper()).post(new Runnable()
          {
            public void run()
            {
              Settings.2.this.val$callback.onCompleted(localResponse);
            }
          });
        }
      }
    });
  }
  
  public static final void removeLoggingBehavior(LoggingBehavior paramLoggingBehavior)
  {
    synchronized (loggingBehaviors)
    {
      loggingBehaviors.remove(paramLoggingBehavior);
      return;
    }
  }
  
  /* Error */
  public static void sdkInitialize(Context paramContext)
  {
    // Byte code:
    //   0: ldc 2
    //   2: monitorenter
    //   3: getstatic 153	com/facebook/Settings:sdkInitialized	Ljava/lang/Boolean;
    //   6: invokevirtual 567	java/lang/Boolean:booleanValue	()Z
    //   9: istore_1
    //   10: iload_1
    //   11: iconst_1
    //   12: if_icmpne +7 -> 19
    //   15: ldc 2
    //   17: monitorexit
    //   18: return
    //   19: aload_0
    //   20: invokestatic 569	com/facebook/Settings:loadDefaultsFromMetadataIfNeeded	(Landroid/content/Context;)V
    //   23: aload_0
    //   24: invokestatic 571	com/facebook/Settings:getApplicationId	()Ljava/lang/String;
    //   27: invokestatic 575	com/facebook/internal/Utility:loadAppSettingsAsync	(Landroid/content/Context;Ljava/lang/String;)V
    //   30: aload_0
    //   31: invokevirtual 551	android/content/Context:getApplicationContext	()Landroid/content/Context;
    //   34: invokestatic 580	com/facebook/BoltsMeasurementEventListener:getInstance	(Landroid/content/Context;)Lcom/facebook/BoltsMeasurementEventListener;
    //   37: pop
    //   38: iconst_1
    //   39: invokestatic 151	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   42: putstatic 153	com/facebook/Settings:sdkInitialized	Ljava/lang/Boolean;
    //   45: goto -30 -> 15
    //   48: astore_0
    //   49: ldc 2
    //   51: monitorexit
    //   52: aload_0
    //   53: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	54	0	paramContext	Context
    //   9	4	1	bool	boolean
    // Exception table:
    //   from	to	target	type
    //   3	10	48	finally
    //   19	45	48	finally
  }
  
  public static void setAppVersion(String paramString)
  {
    appVersion = paramString;
  }
  
  public static void setApplicationId(String paramString)
  {
    applicationId = paramString;
  }
  
  public static void setClientToken(String paramString)
  {
    appClientToken = paramString;
  }
  
  public static void setExecutor(Executor paramExecutor)
  {
    Validate.notNull(paramExecutor, "executor");
    synchronized (LOCK)
    {
      executor = paramExecutor;
      return;
    }
  }
  
  public static void setFacebookDomain(String paramString)
  {
    facebookDomain = paramString;
  }
  
  public static final void setIsDebugEnabled(boolean paramBoolean)
  {
    isDebugEnabled = paramBoolean;
  }
  
  @Deprecated
  public static final void setIsLoggingEnabled(boolean paramBoolean)
  {
    setIsDebugEnabled(paramBoolean);
  }
  
  public static void setLimitEventAndDataUsage(Context paramContext, boolean paramBoolean)
  {
    paramContext.getSharedPreferences("com.facebook.sdk.appEventPreferences", 0).edit().putBoolean("limitEventUsage", paramBoolean).apply();
  }
  
  public static void setOnProgressThreshold(long paramLong)
  {
    onProgressThreshold.set(paramLong);
  }
  
  public static void setPlatformCompatibilityEnabled(boolean paramBoolean)
  {
    platformCompatibilityEnabled = paramBoolean;
  }
  
  @Deprecated
  public static void setShouldAutoPublishInstall(boolean paramBoolean)
  {
    shouldAutoPublishInstall = paramBoolean;
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\facebook\Settings.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */