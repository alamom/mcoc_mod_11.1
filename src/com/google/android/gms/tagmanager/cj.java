package com.google.android.gms.tagmanager;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import com.google.android.gms.internal.a;
import com.google.android.gms.internal.d.a;
import java.util.Map;

class cj
  extends aj
{
  private static final String ID = a.S.toString();
  private final Context mContext;
  
  public cj(Context paramContext)
  {
    super(ID, new String[0]);
    this.mContext = paramContext;
  }
  
  public d.a C(Map<String, d.a> paramMap)
  {
    paramMap = new DisplayMetrics();
    ((WindowManager)this.mContext.getSystemService("window")).getDefaultDisplay().getMetrics(paramMap);
    int j = paramMap.widthPixels;
    int i = paramMap.heightPixels;
    return di.u(j + "x" + i);
  }
  
  public boolean nN()
  {
    return true;
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\tagmanager\cj.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */