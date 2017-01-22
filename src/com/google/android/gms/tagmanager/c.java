package com.google.android.gms.tagmanager;

import android.content.Context;
import com.google.android.gms.internal.d.a;
import java.util.Map;

class c
  extends aj
{
  private static final String ID = com.google.android.gms.internal.a.v.toString();
  private final a anS;
  
  public c(Context paramContext)
  {
    this(a.W(paramContext));
  }
  
  c(a parama)
  {
    super(ID, new String[0]);
    this.anS = parama;
  }
  
  public d.a C(Map<String, d.a> paramMap)
  {
    if (!this.anS.isLimitAdTrackingEnabled()) {}
    for (boolean bool = true;; bool = false) {
      return di.u(Boolean.valueOf(bool));
    }
  }
  
  public boolean nN()
  {
    return false;
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\tagmanager\c.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */