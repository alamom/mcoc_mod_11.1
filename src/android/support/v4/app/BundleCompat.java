package android.support.v4.app;

import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.IBinder;

public class BundleCompat
{
  public static IBinder getBinder(Bundle paramBundle, String paramString)
  {
    if (Build.VERSION.SDK_INT >= 18) {}
    for (paramBundle = BundleCompatJellybeanMR2.getBinder(paramBundle, paramString);; paramBundle = BundleCompatDonut.getBinder(paramBundle, paramString)) {
      return paramBundle;
    }
  }
  
  public static void putBinder(Bundle paramBundle, String paramString, IBinder paramIBinder)
  {
    if (Build.VERSION.SDK_INT >= 18) {
      BundleCompatJellybeanMR2.putBinder(paramBundle, paramString, paramIBinder);
    }
    for (;;)
    {
      return;
      BundleCompatDonut.putBinder(paramBundle, paramString, paramIBinder);
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\android\support\v4\app\BundleCompat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */