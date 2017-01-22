package com.google.android.gms.internal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

@ez
public final class cd
  implements by
{
  private final bz pL;
  private final v pM;
  
  public cd(bz parambz, v paramv)
  {
    this.pL = parambz;
    this.pM = paramv;
  }
  
  private static boolean b(Map<String, String> paramMap)
  {
    return "1".equals(paramMap.get("custom_close"));
  }
  
  private static int c(Map<String, String> paramMap)
  {
    paramMap = (String)paramMap.get("o");
    int i;
    if (paramMap != null) {
      if ("p".equalsIgnoreCase(paramMap)) {
        i = gj.dm();
      }
    }
    for (;;)
    {
      return i;
      if ("l".equalsIgnoreCase(paramMap)) {
        i = gj.dl();
      } else {
        i = -1;
      }
    }
  }
  
  public void a(gv paramgv, Map<String, String> paramMap)
  {
    String str = (String)paramMap.get("a");
    if (str == null) {
      gs.W("Action missing from an open GMSG.");
    }
    for (;;)
    {
      return;
      if ((this.pM != null) && (!this.pM.av()))
      {
        this.pM.d((String)paramMap.get("u"));
      }
      else
      {
        gw localgw = paramgv.du();
        if ("expand".equalsIgnoreCase(str))
        {
          if (paramgv.dy()) {
            gs.W("Cannot expand WebView that is already expanded.");
          } else {
            localgw.a(b(paramMap), c(paramMap));
          }
        }
        else if ("webapp".equalsIgnoreCase(str))
        {
          paramgv = (String)paramMap.get("u");
          if (paramgv != null) {
            localgw.a(b(paramMap), c(paramMap), paramgv);
          } else {
            localgw.a(b(paramMap), c(paramMap), (String)paramMap.get("html"), (String)paramMap.get("baseurl"));
          }
        }
        else if ("in_app_purchase".equalsIgnoreCase(str))
        {
          paramgv = (String)paramMap.get("product_id");
          paramMap = (String)paramMap.get("report_urls");
          if (this.pL != null) {
            if ((paramMap != null) && (!paramMap.isEmpty()))
            {
              paramMap = paramMap.split(" ");
              this.pL.a(paramgv, new ArrayList(Arrays.asList(paramMap)));
            }
            else
            {
              this.pL.a(paramgv, new ArrayList());
            }
          }
        }
        else
        {
          localgw.a(new dj((String)paramMap.get("i"), (String)paramMap.get("u"), (String)paramMap.get("m"), (String)paramMap.get("p"), (String)paramMap.get("c"), (String)paramMap.get("f"), (String)paramMap.get("e")));
        }
      }
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\internal\cd.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */