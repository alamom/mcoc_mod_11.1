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
public final class en
  extends g<ej>
{
  private static final en sK = new en();
  
  private en()
  {
    super("com.google.android.gms.ads.InAppPurchaseManagerCreatorImpl");
  }
  
  private static boolean c(Activity paramActivity)
    throws en.a
  {
    paramActivity = paramActivity.getIntent();
    if (!paramActivity.hasExtra("com.google.android.gms.ads.internal.purchase.useClientJar")) {
      throw new a("InAppPurchaseManager requires the useClientJar flag in intent extras.");
    }
    return paramActivity.getBooleanExtra("com.google.android.gms.ads.internal.purchase.useClientJar", false);
  }
  
  public static ei e(Activity paramActivity)
  {
    for (;;)
    {
      try
      {
        if (!c(paramActivity)) {
          continue;
        }
        gs.S("Using AdOverlay from the client jar.");
        dz localdz = new com/google/android/gms/internal/dz;
        localdz.<init>(paramActivity);
        paramActivity = localdz;
      }
      catch (a paramActivity)
      {
        gs.W(paramActivity.getMessage());
        paramActivity = null;
        continue;
      }
      return paramActivity;
      paramActivity = sK.f(paramActivity);
    }
  }
  
  private ei f(Activity paramActivity)
  {
    try
    {
      d locald = e.k(paramActivity);
      paramActivity = ei.a.u(((ej)L(paramActivity)).b(locald));
      return paramActivity;
    }
    catch (RemoteException paramActivity)
    {
      for (;;)
      {
        gs.d("Could not create remote InAppPurchaseManager.", paramActivity);
        paramActivity = null;
      }
    }
    catch (g.a paramActivity)
    {
      for (;;)
      {
        gs.d("Could not create remote InAppPurchaseManager.", paramActivity);
        paramActivity = null;
      }
    }
  }
  
  protected ej y(IBinder paramIBinder)
  {
    return ej.a.v(paramIBinder);
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


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\internal\en.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */