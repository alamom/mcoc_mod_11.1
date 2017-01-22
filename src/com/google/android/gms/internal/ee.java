package com.google.android.gms.internal;

import android.content.Intent;

@ez
public class ee
{
  private final String oA;
  
  public ee(String paramString)
  {
    this.oA = paramString;
  }
  
  public boolean a(String paramString, int paramInt, Intent paramIntent)
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    if (paramString != null)
    {
      if (paramIntent != null) {
        break label22;
      }
      bool1 = bool2;
    }
    for (;;)
    {
      return bool1;
      label22:
      String str = ed.e(paramIntent);
      paramIntent = ed.f(paramIntent);
      bool1 = bool2;
      if (str != null)
      {
        bool1 = bool2;
        if (paramIntent != null) {
          if (!paramString.equals(ed.D(str)))
          {
            gs.W("Developer payload not match.");
            bool1 = bool2;
          }
          else if ((this.oA != null) && (!ef.b(this.oA, str, paramIntent)))
          {
            gs.W("Fail to verify signature.");
            bool1 = bool2;
          }
          else
          {
            bool1 = true;
          }
        }
      }
    }
  }
  
  public String ct()
  {
    return gj.jdMethod_do();
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\internal\ee.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */