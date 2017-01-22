package com.mobileapptracker;

import android.content.Context;
import android.provider.Settings.Secure;
import android.util.Log;
import java.lang.ref.WeakReference;
import java.lang.reflect.Method;

final class d
  implements Runnable
{
  private final WeakReference<Context> b;
  
  public d(MATParameters paramMATParameters, Context paramContext)
  {
    this.b = new WeakReference(paramContext);
  }
  
  public final void run()
  {
    for (;;)
    {
      try
      {
        Object localObject = Class.forName("com.google.android.gms.ads.identifier.AdvertisingIdClient").getDeclaredMethod("getAdvertisingIdInfo", new Class[] { Context.class }).invoke(null, new Object[] { this.b.get() });
        String str = (String)Class.forName("com.google.android.gms.ads.identifier.AdvertisingIdClient$Info").getDeclaredMethod("getId", new Class[0]).invoke(localObject, new Object[0]);
        boolean bool = ((Boolean)Class.forName("com.google.android.gms.ads.identifier.AdvertisingIdClient$Info").getDeclaredMethod("isLimitAdTrackingEnabled", new Class[0]).invoke(localObject, new Object[0])).booleanValue();
        if (MATParameters.a(this.a).params == null)
        {
          this.a.setGoogleAdvertisingId(str);
          if (bool)
          {
            i = 1;
            this.a.setGoogleAdTrackingLimited(Integer.toString(i));
          }
        }
        else
        {
          MATParameters.a(this.a).setGoogleAdvertisingId(str, bool);
          return;
        }
      }
      catch (Exception localException)
      {
        int i;
        localException.printStackTrace();
        Log.d("MobileAppTracker", "MAT SDK failed to get Google Advertising Id, collecting ANDROID_ID instead");
        if (MATParameters.a(this.a).params != null) {
          continue;
        }
        this.a.setAndroidId(Settings.Secure.getString(((Context)this.b.get()).getContentResolver(), "android_id"));
        MATParameters.a(this.a).setAndroidId(Settings.Secure.getString(((Context)this.b.get()).getContentResolver(), "android_id"));
        continue;
      }
      i = 0;
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\mobileapptracker\d.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */