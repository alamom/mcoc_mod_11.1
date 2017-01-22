package com.facebook.unity;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;

public class FBUnityDeepLinkingActivity
  extends Activity
{
  private Class<?> getMainActivityClass()
  {
    Object localObject1 = getPackageName();
    localObject1 = getPackageManager().getLaunchIntentForPackage((String)localObject1);
    try
    {
      localObject1 = Class.forName(((Intent)localObject1).getComponent().getClassName());
      return (Class<?>)localObject1;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        Log.e("FBUnitySDK", "Unable to find Main Activity Class");
        Object localObject2 = null;
      }
    }
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    requestWindowFeature(1);
    Log.v("FBUnitySDK", "Saving deep link from deep linking activity");
    FB.SetIntent(getIntent());
    Log.v("FBUnitySDK", "Returning to main activity");
    startActivity(new Intent(this, getMainActivityClass()));
    finish();
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\facebook\unity\FBUnityDeepLinkingActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */