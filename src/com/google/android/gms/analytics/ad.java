package com.google.android.gms.analytics;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;

class ad
  implements l
{
  private static ad Bi;
  private static Object xz = new Object();
  private final Context mContext;
  
  protected ad(Context paramContext)
  {
    this.mContext = paramContext;
  }
  
  public static ad eQ()
  {
    synchronized (xz)
    {
      ad localad = Bi;
      return localad;
    }
  }
  
  public static void y(Context paramContext)
  {
    synchronized (xz)
    {
      if (Bi == null)
      {
        ad localad = new com/google/android/gms/analytics/ad;
        localad.<init>(paramContext);
        Bi = localad;
      }
      return;
    }
  }
  
  public boolean ac(String paramString)
  {
    return "&sr".equals(paramString);
  }
  
  protected String eR()
  {
    DisplayMetrics localDisplayMetrics = this.mContext.getResources().getDisplayMetrics();
    return localDisplayMetrics.widthPixels + "x" + localDisplayMetrics.heightPixels;
  }
  
  public String getValue(String paramString)
  {
    String str = null;
    if (paramString == null) {}
    for (;;)
    {
      return str;
      if (paramString.equals("&sr")) {
        str = eR();
      }
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\analytics\ad.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */