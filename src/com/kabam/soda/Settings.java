package com.kabam.soda;

import android.content.Context;
import java.util.Locale;

public class Settings
{
  private static final String BASE_URL_BETA = "https://api-beta.wske.kabam.com";
  private static final String BASE_URL_DEV = "http://10.0.2.2:9000";
  private static final String BASE_URL_PROD = "https://api.wske.kabam.com";
  private static final String BASE_URL_SANDBOX = "https://api-sandbox.wske.kabam.com";
  public static final String ENV_BETA = "beta";
  private static final String ENV_DEV = "dev";
  public static final String ENV_PROD = "production";
  public static final String ENV_SANDBOX = "sandbox";
  private String clientId;
  private String env;
  private final String matAdvertiserId = "885";
  private final String matAppKey = "429151a2d9a0e1601b518a5cda19c750";
  private String matSiteId;
  private String mobileKey;
  private String userAgentExtras = "";
  private final String version = "v3.0.0";
  private String wskeUrl;
  
  private Settings() {}
  
  public Settings(Context paramContext)
  {
    this();
    withEnv("production");
  }
  
  public Settings(String paramString)
  {
    this();
    withEnv(paramString);
  }
  
  public Settings(String paramString1, String paramString2)
  {
    this();
    this.clientId = paramString1;
    this.mobileKey = paramString2;
  }
  
  private String getBaseUrl(String paramString)
  {
    if (paramString != null) {
      if (paramString.toLowerCase(Locale.US).equals("sandbox")) {
        paramString = "https://api-sandbox.wske.kabam.com";
      }
    }
    for (;;)
    {
      return paramString;
      if (paramString.toLowerCase(Locale.US).equals("beta")) {
        paramString = "https://api-beta.wske.kabam.com";
      } else if (paramString.toLowerCase(Locale.US).equals("dev")) {
        paramString = "http://10.0.2.2:9000";
      } else {
        paramString = "https://api.wske.kabam.com";
      }
    }
  }
  
  public String getBaseUrl()
  {
    return this.wskeUrl;
  }
  
  public String getClientId()
  {
    return this.clientId;
  }
  
  public String getEnv()
  {
    String str;
    if ((this.env != null) && (!this.env.isEmpty())) {
      str = this.env;
    }
    for (;;)
    {
      return str;
      if ((this.wskeUrl != null) && (!this.wskeUrl.isEmpty()))
      {
        if (this.wskeUrl.equals("https://api-sandbox.wske.kabam.com"))
        {
          str = "sandbox";
          continue;
        }
        if (this.wskeUrl.equals("https://api-beta.wske.kabam.com"))
        {
          str = "beta";
          continue;
        }
        if (this.wskeUrl.equals("http://10.0.2.2:9000"))
        {
          str = "dev";
          continue;
        }
      }
      str = "production";
    }
  }
  
  public String getMatAdvertiserId()
  {
    return "885";
  }
  
  public String getMatAppKey()
  {
    return "429151a2d9a0e1601b518a5cda19c750";
  }
  
  public String getMatSiteId()
  {
    return this.matSiteId;
  }
  
  public String getMobileKey()
  {
    return this.mobileKey;
  }
  
  public String getUserAgentExtras()
  {
    return this.userAgentExtras;
  }
  
  public String getVersion()
  {
    return this.version;
  }
  
  public void setBaseUrl(String paramString)
  {
    this.wskeUrl = paramString;
  }
  
  public void setClientId(String paramString)
  {
    this.clientId = paramString;
  }
  
  public void setMatSiteId(String paramString)
  {
    this.matSiteId = paramString;
  }
  
  public void setMobileKey(String paramString)
  {
    this.mobileKey = paramString;
  }
  
  public Settings withEnv(String paramString)
  {
    this.env = paramString;
    this.wskeUrl = getBaseUrl(paramString);
    return this;
  }
  
  public Settings withUserAgentExtras(String paramString)
  {
    this.userAgentExtras = paramString;
    return this;
  }
  
  public Settings withWskeUrl(String paramString)
  {
    this.wskeUrl = paramString;
    return this;
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\kabam\soda\Settings.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */