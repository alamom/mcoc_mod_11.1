package com.google.android.gms.tagmanager;

import android.os.Build.VERSION;
import com.google.android.gms.internal.a;
import com.google.android.gms.internal.d.a;
import java.util.Map;

class cv
  extends aj
{
  private static final String ID = a.U.toString();
  
  public cv()
  {
    super(ID, new String[0]);
  }
  
  public d.a C(Map<String, d.a> paramMap)
  {
    return di.u(Integer.valueOf(Build.VERSION.SDK_INT));
  }
  
  public boolean nN()
  {
    return true;
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\tagmanager\cv.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */