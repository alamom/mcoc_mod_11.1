package com.google.android.gms.internal;

import android.content.Context;
import android.content.pm.PackageManager;
import java.util.regex.Pattern;

public final class jt
{
  private static Pattern MR = null;
  
  public static boolean K(Context paramContext)
  {
    return paramContext.getPackageManager().hasSystemFeature("android.hardware.type.watch");
  }
  
  public static int aN(int paramInt)
  {
    return paramInt / 1000;
  }
  
  public static int aO(int paramInt)
  {
    return paramInt % 1000 / 100;
  }
  
  public static boolean aP(int paramInt)
  {
    if (aO(paramInt) == 3) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\internal\jt.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */