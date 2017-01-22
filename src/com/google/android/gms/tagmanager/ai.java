package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.c.c;
import com.google.android.gms.internal.c.d;
import com.google.android.gms.internal.c.i;
import com.google.android.gms.internal.d.a;
import java.util.Map;

class ai
{
  private static void a(DataLayer paramDataLayer, c.d paramd)
  {
    paramd = paramd.fB;
    int j = paramd.length;
    for (int i = 0; i < j; i++) {
      paramDataLayer.cv(di.j(paramd[i]));
    }
  }
  
  public static void a(DataLayer paramDataLayer, c.i parami)
  {
    if (parami.gq == null) {
      bh.W("supplemental missing experimentSupplemental");
    }
    for (;;)
    {
      return;
      a(paramDataLayer, parami.gq);
      b(paramDataLayer, parami.gq);
      c(paramDataLayer, parami.gq);
    }
  }
  
  private static void b(DataLayer paramDataLayer, c.d paramd)
  {
    paramd = paramd.fA;
    int j = paramd.length;
    for (int i = 0; i < j; i++)
    {
      Map localMap = c(paramd[i]);
      if (localMap != null) {
        paramDataLayer.push(localMap);
      }
    }
  }
  
  private static Map<String, Object> c(d.a parama)
  {
    parama = di.o(parama);
    if (!(parama instanceof Map)) {
      bh.W("value: " + parama + " is not a map value, ignored.");
    }
    for (parama = null;; parama = (Map)parama) {
      return parama;
    }
  }
  
  private static void c(DataLayer paramDataLayer, c.d paramd)
  {
    c.c[] arrayOfc = paramd.fC;
    int j = arrayOfc.length;
    int i = 0;
    while (i < j)
    {
      c.c localc = arrayOfc[i];
      if (localc.fv == null)
      {
        bh.W("GaExperimentRandom: No key");
        i++;
      }
      else
      {
        Object localObject = paramDataLayer.get(localc.fv);
        if (!(localObject instanceof Number))
        {
          paramd = null;
          label63:
          long l1 = localc.fw;
          long l2 = localc.fx;
          if ((!localc.fy) || (paramd == null) || (paramd.longValue() < l1) || (paramd.longValue() > l2))
          {
            if (l1 > l2) {
              break label236;
            }
            localObject = Long.valueOf(Math.round(Math.random() * (l2 - l1) + l1));
          }
          paramDataLayer.cv(localc.fv);
          paramd = paramDataLayer.c(localc.fv, localObject);
          if (localc.fz > 0L)
          {
            if (paramd.containsKey("gtm")) {
              break label244;
            }
            paramd.put("gtm", DataLayer.mapOf(new Object[] { "lifetime", Long.valueOf(localc.fz) }));
          }
        }
        for (;;)
        {
          paramDataLayer.push(paramd);
          break;
          paramd = Long.valueOf(((Number)localObject).longValue());
          break label63;
          label236:
          bh.W("GaExperimentRandom: random range invalid");
          break;
          label244:
          localObject = paramd.get("gtm");
          if ((localObject instanceof Map)) {
            ((Map)localObject).put("lifetime", Long.valueOf(localc.fz));
          } else {
            bh.W("GaExperimentRandom: gtm not a map");
          }
        }
      }
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\tagmanager\ai.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */