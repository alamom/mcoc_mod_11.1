package com.google.android.gms.internal;

import android.app.Activity;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import com.google.android.gms.dynamic.d;
import com.google.android.gms.dynamic.e;
import com.google.android.gms.dynamic.g;
import com.google.android.gms.dynamic.g.a;

@ez
public final class dr
  extends g<dt>
{
  private static final dr sh = new dr();
  
  private dr()
  {
    super("com.google.android.gms.ads.AdOverlayCreatorImpl");
  }
  
  public static ds b(Activity paramActivity)
  {
    for (;;)
    {
      try
      {
        if (!c(paramActivity)) {
          continue;
        }
        gs.S("Using AdOverlay from the client jar.");
        dk localdk = new com/google/android/gms/internal/dk;
        localdk.<init>(paramActivity);
        paramActivity = localdk;
      }
      catch (a paramActivity)
      {
        gs.W(paramActivity.getMessage());
        paramActivity = null;
        continue;
      }
      return paramActivity;
      paramActivity = sh.d(paramActivity);
    }
  }
  
  private static boolean c(Activity paramActivity)
    throws dr.a
  {
    paramActivity = paramActivity.getIntent();
    if (!paramActivity.hasExtra("com.google.android.gms.ads.internal.overlay.useClientJar")) {
      throw new a("Ad overlay requires the useClientJar flag in intent extras.");
    }
    return paramActivity.getBooleanExtra("com.google.android.gms.ads.internal.overlay.useClientJar", false);
  }
  
  private ds d(Activity paramActivity)
  {
    try
    {
      d locald = e.k(paramActivity);
      paramActivity = ds.a.p(((dt)L(paramActivity)).a(locald));
      return paramActivity;
    }
    catch (RemoteException paramActivity)
    {
      for (;;)
      {
        gs.d("Could not create remote AdOverlay.", paramActivity);
        paramActivity = null;
      }
    }
    catch (g.a paramActivity)
    {
      for (;;)
      {
        gs.d("Could not create remote AdOverlay.", paramActivity);
        paramActivity = null;
      }
    }
  }
  
  protected dt o(IBinder paramIBinder)
  {
    return dt.a.q(paramIBinder);
  }
  
  private static final class a
    extends Exception
  {
    public a(String paramString)
    {
      super();
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\internal\dr.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */