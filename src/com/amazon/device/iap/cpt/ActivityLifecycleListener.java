package com.amazon.device.iap.cpt;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

public class ActivityLifecycleListener
{
  private Activity activity;
  
  public static void registerActivityLifecycleCallbacks(Application paramApplication, ActivityLifecycleListener paramActivityLifecycleListener) {}
  
  public Activity getCurrentAndroidActivity()
  {
    return this.activity;
  }
  
  public void onActivityCreated(Activity paramActivity, Bundle paramBundle)
  {
    this.activity = paramActivity;
  }
  
  public void onActivityDestroyed(Activity paramActivity) {}
  
  public void onActivityPaused(Activity paramActivity) {}
  
  public void onActivityResumed(Activity paramActivity) {}
  
  public void onActivitySaveInstanceState(Activity paramActivity, Bundle paramBundle) {}
  
  public void onActivityStarted(Activity paramActivity)
  {
    this.activity = paramActivity;
  }
  
  public void onActivityStopped(Activity paramActivity) {}
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\amazon\device\iap\cpt\ActivityLifecycleListener.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */