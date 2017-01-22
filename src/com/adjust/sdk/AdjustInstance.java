package com.adjust.sdk;

import android.net.Uri;

public class AdjustInstance
{
  private ActivityHandler activityHandler;
  private String referrer;
  private long referrerClickTime;
  
  private boolean checkActivityHandler()
  {
    boolean bool = false;
    if (this.activityHandler == null) {
      getLogger().error("Adjust not initialized correctly", new Object[0]);
    }
    for (;;)
    {
      return bool;
      bool = true;
    }
  }
  
  private static ILogger getLogger()
  {
    return AdjustFactory.getLogger();
  }
  
  public void appWillOpenUrl(Uri paramUri)
  {
    if (!checkActivityHandler()) {}
    for (;;)
    {
      return;
      long l = System.currentTimeMillis();
      this.activityHandler.readOpenUrl(paramUri, l);
    }
  }
  
  public boolean isEnabled()
  {
    if (!checkActivityHandler()) {}
    for (boolean bool = false;; bool = this.activityHandler.isEnabled()) {
      return bool;
    }
  }
  
  public void onCreate(AdjustConfig paramAdjustConfig)
  {
    if (this.activityHandler != null) {
      getLogger().error("Adjust already initialized", new Object[0]);
    }
    for (;;)
    {
      return;
      paramAdjustConfig.referrer = this.referrer;
      paramAdjustConfig.referrerClickTime = this.referrerClickTime;
      this.activityHandler = ActivityHandler.getInstance(paramAdjustConfig);
    }
  }
  
  public void onPause()
  {
    if (!checkActivityHandler()) {}
    for (;;)
    {
      return;
      this.activityHandler.trackSubsessionEnd();
    }
  }
  
  public void onResume()
  {
    if (!checkActivityHandler()) {}
    for (;;)
    {
      return;
      this.activityHandler.trackSubsessionStart();
    }
  }
  
  public void sendReferrer(String paramString)
  {
    long l = System.currentTimeMillis();
    if (this.activityHandler == null)
    {
      this.referrer = paramString;
      this.referrerClickTime = l;
    }
    for (;;)
    {
      return;
      this.activityHandler.sendReferrer(paramString, l);
    }
  }
  
  public void setEnabled(boolean paramBoolean)
  {
    if (!checkActivityHandler()) {}
    for (;;)
    {
      return;
      this.activityHandler.setEnabled(paramBoolean);
    }
  }
  
  public void setOfflineMode(boolean paramBoolean)
  {
    if (!checkActivityHandler()) {}
    for (;;)
    {
      return;
      this.activityHandler.setOfflineMode(paramBoolean);
    }
  }
  
  public void trackEvent(AdjustEvent paramAdjustEvent)
  {
    if (!checkActivityHandler()) {}
    for (;;)
    {
      return;
      this.activityHandler.trackEvent(paramAdjustEvent);
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\adjust\sdk\AdjustInstance.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */