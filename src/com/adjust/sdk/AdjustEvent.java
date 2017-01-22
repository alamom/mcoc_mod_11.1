package com.adjust.sdk;

import java.util.LinkedHashMap;
import java.util.Map;

public class AdjustEvent
{
  private static ILogger logger = ;
  Map<String, String> callbackParameters;
  String currency;
  String eventToken;
  Map<String, String> partnerParameters;
  Double revenue;
  
  public AdjustEvent(String paramString)
  {
    if (!checkEventToken(paramString, logger)) {}
    for (;;)
    {
      return;
      this.eventToken = paramString;
    }
  }
  
  private static boolean checkEventToken(String paramString, ILogger paramILogger)
  {
    boolean bool = false;
    if (paramString == null) {
      paramILogger.error("Missing Event Token", new Object[0]);
    }
    for (;;)
    {
      return bool;
      if (paramString.length() != 6) {
        paramILogger.error("Malformed Event Token '%s'", new Object[] { paramString });
      } else {
        bool = true;
      }
    }
  }
  
  private boolean checkRevenue(Double paramDouble, String paramString)
  {
    boolean bool = false;
    if (paramDouble != null) {
      if (paramDouble.doubleValue() < 0.0D) {
        logger.error("Invalid amount %.4f", new Object[] { paramDouble });
      }
    }
    for (;;)
    {
      return bool;
      if (paramString == null)
      {
        logger.error("Currency must be set with revenue", new Object[0]);
      }
      else
      {
        if (paramString.equals(""))
        {
          logger.error("Currency is empty", new Object[0]);
          continue;
          if (paramString != null)
          {
            logger.error("Revenue must be set with currency", new Object[0]);
            continue;
          }
        }
        bool = true;
      }
    }
  }
  
  private boolean isValidParameter(String paramString1, String paramString2, String paramString3)
  {
    boolean bool = false;
    if (paramString1 == null) {
      logger.error("%s parameter %s is missing", new Object[] { paramString3, paramString2 });
    }
    for (;;)
    {
      return bool;
      if (paramString1.equals("")) {
        logger.error("%s parameter %s is empty", new Object[] { paramString3, paramString2 });
      } else {
        bool = true;
      }
    }
  }
  
  public void addCallbackParameter(String paramString1, String paramString2)
  {
    if (!isValidParameter(paramString1, "key", "Callback")) {}
    for (;;)
    {
      return;
      if (isValidParameter(paramString2, "value", "Callback"))
      {
        if (this.callbackParameters == null) {
          this.callbackParameters = new LinkedHashMap();
        }
        if ((String)this.callbackParameters.put(paramString1, paramString2) != null) {
          logger.warn("key %s was overwritten", new Object[] { paramString1 });
        }
      }
    }
  }
  
  public void addPartnerParameter(String paramString1, String paramString2)
  {
    if (!isValidParameter(paramString1, "key", "Partner")) {}
    for (;;)
    {
      return;
      if (isValidParameter(paramString2, "value", "Partner"))
      {
        if (this.partnerParameters == null) {
          this.partnerParameters = new LinkedHashMap();
        }
        if ((String)this.partnerParameters.put(paramString1, paramString2) != null) {
          logger.warn("key %s was overwritten", new Object[] { paramString1 });
        }
      }
    }
  }
  
  public boolean isValid()
  {
    if (this.eventToken != null) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public void setRevenue(double paramDouble, String paramString)
  {
    if (!checkRevenue(Double.valueOf(paramDouble), paramString)) {}
    for (;;)
    {
      return;
      this.revenue = Double.valueOf(paramDouble);
      this.currency = paramString;
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\adjust\sdk\AdjustEvent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */