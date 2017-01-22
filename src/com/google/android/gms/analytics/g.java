package com.google.android.gms.analytics;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;

class g
  implements l
{
  private static g xP;
  private static Object xz = new Object();
  protected String xL;
  protected String xM;
  protected String xN;
  protected String xO;
  
  protected g() {}
  
  private g(Context paramContext)
  {
    PackageManager localPackageManager = paramContext.getPackageManager();
    this.xN = paramContext.getPackageName();
    this.xO = localPackageManager.getInstallerPackageName(this.xN);
    String str = this.xN;
    localObject2 = null;
    localObject1 = str;
    try
    {
      PackageInfo localPackageInfo = localPackageManager.getPackageInfo(paramContext.getPackageName(), 0);
      paramContext = (Context)localObject2;
      localObject1 = str;
      if (localPackageInfo != null)
      {
        localObject1 = str;
        paramContext = localPackageManager.getApplicationLabel(localPackageInfo.applicationInfo).toString();
        localObject1 = paramContext;
        str = localPackageInfo.versionName;
        localObject1 = paramContext;
        paramContext = str;
      }
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      for (;;)
      {
        z.T("Error retrieving package info: appName set to " + (String)localObject1);
        paramContext = (Context)localObject2;
      }
    }
    this.xL = ((String)localObject1);
    this.xM = paramContext;
  }
  
  public static g dP()
  {
    return xP;
  }
  
  public static void y(Context paramContext)
  {
    synchronized (xz)
    {
      if (xP == null)
      {
        g localg = new com/google/android/gms/analytics/g;
        localg.<init>(paramContext);
        xP = localg;
      }
      return;
    }
  }
  
  public boolean ac(String paramString)
  {
    if (("&an".equals(paramString)) || ("&av".equals(paramString)) || ("&aid".equals(paramString)) || ("&aiid".equals(paramString))) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public String getValue(String paramString)
  {
    String str = null;
    if (paramString == null) {}
    for (;;)
    {
      return str;
      if (paramString.equals("&an")) {
        str = this.xL;
      } else if (paramString.equals("&av")) {
        str = this.xM;
      } else if (paramString.equals("&aid")) {
        str = this.xN;
      } else if (paramString.equals("&aiid")) {
        str = this.xO;
      }
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\analytics\g.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */