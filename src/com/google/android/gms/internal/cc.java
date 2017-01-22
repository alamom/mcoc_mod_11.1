package com.google.android.gms.internal;

import java.util.HashMap;
import java.util.Map;

@ez
public class cc
  implements by
{
  static final Map<String, Integer> pK = new HashMap();
  
  static
  {
    pK.put("resize", Integer.valueOf(1));
    pK.put("playVideo", Integer.valueOf(2));
    pK.put("storePicture", Integer.valueOf(3));
    pK.put("createCalendarEvent", Integer.valueOf(4));
  }
  
  public void a(gv paramgv, Map<String, String> paramMap)
  {
    String str = (String)paramMap.get("a");
    switch (((Integer)pK.get(str)).intValue())
    {
    case 2: 
    default: 
      gs.U("Unknown MRAID command called.");
    }
    for (;;)
    {
      return;
      new dd(paramgv, paramMap).execute();
      continue;
      new dc(paramgv, paramMap).execute();
      continue;
      new de(paramgv, paramMap).execute();
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\internal\cc.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */