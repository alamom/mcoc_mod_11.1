package com.google.android.gms.tagmanager;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import com.google.android.gms.internal.a;
import com.google.android.gms.internal.d.a;
import java.util.Map;

class g
  extends aj
{
  private static final String ID = a.x.toString();
  private final Context mContext;
  
  public g(Context paramContext)
  {
    super(ID, new String[0]);
    this.mContext = paramContext;
  }
  
  public d.a C(Map<String, d.a> paramMap)
  {
    try
    {
      paramMap = this.mContext.getPackageManager();
      paramMap = di.u(paramMap.getApplicationLabel(paramMap.getApplicationInfo(this.mContext.getPackageName(), 0)).toString());
      return paramMap;
    }
    catch (PackageManager.NameNotFoundException paramMap)
    {
      for (;;)
      {
        bh.b("App name is not found.", paramMap);
        paramMap = di.pK();
      }
    }
  }
  
  public boolean nN()
  {
    return true;
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\tagmanager\g.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */