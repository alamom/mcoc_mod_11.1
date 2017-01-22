package com.adjust.sdk;

import android.content.Context;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpParams;

public class AdjustFactory
{
  private static IActivityHandler activityHandler;
  private static IAttributionHandler attributionHandler;
  private static HttpClient httpClient;
  private static ILogger logger;
  private static IPackageHandler packageHandler = null;
  private static IRequestHandler requestHandler = null;
  private static long sessionInterval = -1L;
  private static long subsessionInterval = -1L;
  private static long timerInterval;
  private static long timerStart;
  
  static
  {
    attributionHandler = null;
    activityHandler = null;
    logger = null;
    httpClient = null;
    timerInterval = -1L;
    timerStart = -1L;
  }
  
  public static IActivityHandler getActivityHandler(AdjustConfig paramAdjustConfig)
  {
    if (activityHandler == null) {}
    for (paramAdjustConfig = ActivityHandler.getInstance(paramAdjustConfig);; paramAdjustConfig = activityHandler)
    {
      return paramAdjustConfig;
      activityHandler.init(paramAdjustConfig);
    }
  }
  
  public static IAttributionHandler getAttributionHandler(IActivityHandler paramIActivityHandler, ActivityPackage paramActivityPackage, boolean paramBoolean1, boolean paramBoolean2)
  {
    if (attributionHandler == null) {}
    for (paramIActivityHandler = new AttributionHandler(paramIActivityHandler, paramActivityPackage, paramBoolean1, paramBoolean2);; paramIActivityHandler = attributionHandler)
    {
      return paramIActivityHandler;
      attributionHandler.init(paramIActivityHandler, paramActivityPackage, paramBoolean1, paramBoolean2);
    }
  }
  
  public static HttpClient getHttpClient(HttpParams paramHttpParams)
  {
    if (httpClient == null) {}
    for (paramHttpParams = new DefaultHttpClient(paramHttpParams);; paramHttpParams = httpClient) {
      return paramHttpParams;
    }
  }
  
  public static ILogger getLogger()
  {
    if (logger == null) {
      logger = new Logger();
    }
    return logger;
  }
  
  public static IPackageHandler getPackageHandler(ActivityHandler paramActivityHandler, Context paramContext, boolean paramBoolean)
  {
    if (packageHandler == null) {}
    for (paramActivityHandler = new PackageHandler(paramActivityHandler, paramContext, paramBoolean);; paramActivityHandler = packageHandler)
    {
      return paramActivityHandler;
      packageHandler.init(paramActivityHandler, paramContext, paramBoolean);
    }
  }
  
  public static IRequestHandler getRequestHandler(IPackageHandler paramIPackageHandler)
  {
    if (requestHandler == null) {}
    for (paramIPackageHandler = new RequestHandler(paramIPackageHandler);; paramIPackageHandler = requestHandler)
    {
      return paramIPackageHandler;
      requestHandler.init(paramIPackageHandler);
    }
  }
  
  public static long getSessionInterval()
  {
    if (sessionInterval == -1L) {}
    for (long l = 1800000L;; l = sessionInterval) {
      return l;
    }
  }
  
  public static long getSubsessionInterval()
  {
    if (subsessionInterval == -1L) {}
    for (long l = 1000L;; l = subsessionInterval) {
      return l;
    }
  }
  
  public static long getTimerInterval()
  {
    if (timerInterval == -1L) {}
    for (long l = 60000L;; l = timerInterval) {
      return l;
    }
  }
  
  public static long getTimerStart()
  {
    if (timerStart == -1L) {}
    for (long l = 0L;; l = timerStart) {
      return l;
    }
  }
  
  public static void setActivityHandler(IActivityHandler paramIActivityHandler)
  {
    activityHandler = paramIActivityHandler;
  }
  
  public static void setAttributionHandler(IAttributionHandler paramIAttributionHandler)
  {
    attributionHandler = paramIAttributionHandler;
  }
  
  public static void setHttpClient(HttpClient paramHttpClient)
  {
    httpClient = paramHttpClient;
  }
  
  public static void setLogger(ILogger paramILogger)
  {
    logger = paramILogger;
  }
  
  public static void setPackageHandler(IPackageHandler paramIPackageHandler)
  {
    packageHandler = paramIPackageHandler;
  }
  
  public static void setRequestHandler(IRequestHandler paramIRequestHandler)
  {
    requestHandler = paramIRequestHandler;
  }
  
  public static void setSessionInterval(long paramLong)
  {
    sessionInterval = paramLong;
  }
  
  public static void setSubsessionInterval(long paramLong)
  {
    subsessionInterval = paramLong;
  }
  
  public static void setTimerInterval(long paramLong)
  {
    timerInterval = paramLong;
  }
  
  public static void setTimerStart(long paramLong)
  {
    timerStart = paramLong;
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\adjust\sdk\AdjustFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */