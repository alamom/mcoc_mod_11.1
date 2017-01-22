package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.a;
import com.google.android.gms.internal.d.a;
import java.util.Locale;
import java.util.Map;

class bc
  extends aj
{
  private static final String ID = a.N.toString();
  
  public bc()
  {
    super(ID, new String[0]);
  }
  
  public d.a C(Map<String, d.a> paramMap)
  {
    paramMap = Locale.getDefault();
    if (paramMap == null) {
      paramMap = di.pK();
    }
    for (;;)
    {
      return paramMap;
      paramMap = paramMap.getLanguage();
      if (paramMap == null) {
        paramMap = di.pK();
      } else {
        paramMap = di.u(paramMap.toLowerCase());
      }
    }
  }
  
  public boolean nN()
  {
    return false;
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\tagmanager\bc.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */