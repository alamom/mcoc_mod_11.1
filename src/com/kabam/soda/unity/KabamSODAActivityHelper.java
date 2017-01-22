package com.kabam.soda.unity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import com.kabam.soda.KabamSession;

public class KabamSODAActivityHelper
{
  public static final String IN_APP_PURCHASE_DATA_KEY = "INAPP_PURCHASE_DATA";
  
  public static void onActivityResult(Activity paramActivity, int paramInt1, int paramInt2, Intent paramIntent) {}
  
  public static void onCreate(Activity paramActivity, Bundle paramBundle) {}
  
  public static void onPause(Activity paramActivity) {}
  
  public static void onResume(Activity paramActivity)
  {
    KabamSession.resume(paramActivity);
  }
  
  public static void onStop(Activity paramActivity) {}
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\kabam\soda\unity\KabamSODAActivityHelper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */