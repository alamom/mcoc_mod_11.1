package com.google.android.gms.internal;

import java.util.Map;

@ez
public final class bv
  implements by
{
  private final bw pz;
  
  public bv(bw parambw)
  {
    this.pz = parambw;
  }
  
  public void a(gv paramgv, Map<String, String> paramMap)
  {
    paramgv = (String)paramMap.get("name");
    if (paramgv == null) {
      gs.W("App event with no name parameter.");
    }
    for (;;)
    {
      return;
      this.pz.onAppEvent(paramgv, (String)paramMap.get("info"));
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\internal\bv.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */