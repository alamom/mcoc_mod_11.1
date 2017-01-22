package com.adjust.sdk;

import android.content.Context;

public class AdjustConfig
{
  public static final String ENVIRONMENT_PRODUCTION = "production";
  public static final String ENVIRONMENT_SANDBOX = "sandbox";
  String appToken;
  Context context;
  String defaultTracker;
  String environment;
  Boolean eventBufferingEnabled;
  Boolean knownDevice;
  LogLevel logLevel;
  OnAttributionChangedListener onAttributionChangedListener;
  String processName;
  String referrer;
  long referrerClickTime;
  String sdkPrefix;
  
  public AdjustConfig(Context paramContext, String paramString1, String paramString2)
  {
    if (!isValid(paramContext, paramString1, paramString2)) {}
    for (;;)
    {
      return;
      this.context = paramContext.getApplicationContext();
      this.appToken = paramString1;
      this.environment = paramString2;
      this.logLevel = LogLevel.INFO;
      this.eventBufferingEnabled = Boolean.valueOf(false);
    }
  }
  
  private static boolean checkAppToken(String paramString)
  {
    boolean bool = false;
    ILogger localILogger = AdjustFactory.getLogger();
    if (paramString == null) {
      localILogger.error("Missing App Token.", new Object[0]);
    }
    for (;;)
    {
      return bool;
      if (paramString.length() != 12) {
        localILogger.error("Malformed App Token '%s'", new Object[] { paramString });
      } else {
        bool = true;
      }
    }
  }
  
  private static boolean checkContext(Context paramContext)
  {
    boolean bool = false;
    ILogger localILogger = AdjustFactory.getLogger();
    if (paramContext == null) {
      localILogger.error("Missing context", new Object[0]);
    }
    for (;;)
    {
      return bool;
      if (!Util.checkPermission(paramContext, "android.permission.INTERNET")) {
        localILogger.error("Missing permission: INTERNET", new Object[0]);
      } else {
        bool = true;
      }
    }
  }
  
  private static boolean checkEnvironment(String paramString)
  {
    boolean bool = false;
    ILogger localILogger = AdjustFactory.getLogger();
    if (paramString == null) {
      localILogger.error("Missing environment", new Object[0]);
    }
    for (;;)
    {
      return bool;
      if (paramString.equals("sandbox"))
      {
        localILogger.Assert("SANDBOX: Adjust is running in Sandbox mode. Use this setting for testing. Don't forget to set the environment to `production` before publishing!", new Object[0]);
        bool = true;
      }
      else if (paramString.equals("production"))
      {
        localILogger.Assert("PRODUCTION: Adjust is running in Production mode. Use this setting only for the build that you want to publish. Set the environment to `sandbox` if you want to test your app!", new Object[0]);
        bool = true;
      }
      else
      {
        localILogger.error("Unknown environment '%s'", new Object[] { paramString });
      }
    }
  }
  
  private boolean isValid(Context paramContext, String paramString1, String paramString2)
  {
    boolean bool2 = false;
    boolean bool1;
    if (!checkAppToken(paramString1)) {
      bool1 = bool2;
    }
    for (;;)
    {
      return bool1;
      bool1 = bool2;
      if (checkEnvironment(paramString2))
      {
        bool1 = bool2;
        if (checkContext(paramContext)) {
          bool1 = true;
        }
      }
    }
  }
  
  public boolean hasListener()
  {
    if (this.onAttributionChangedListener != null) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public boolean isValid()
  {
    if (this.appToken != null) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public void setDefaultTracker(String paramString)
  {
    this.defaultTracker = paramString;
  }
  
  public void setEventBufferingEnabled(Boolean paramBoolean)
  {
    this.eventBufferingEnabled = paramBoolean;
  }
  
  public void setLogLevel(LogLevel paramLogLevel)
  {
    this.logLevel = paramLogLevel;
  }
  
  public void setOnAttributionChangedListener(OnAttributionChangedListener paramOnAttributionChangedListener)
  {
    this.onAttributionChangedListener = paramOnAttributionChangedListener;
  }
  
  public void setProcessName(String paramString)
  {
    this.processName = paramString;
  }
  
  public void setSdkPrefix(String paramString)
  {
    this.sdkPrefix = paramString;
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\adjust\sdk\AdjustConfig.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */