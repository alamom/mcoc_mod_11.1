package com.google.android.gms.tagmanager;

import android.content.Context;
import com.google.android.gms.internal.a;
import com.google.android.gms.internal.b;
import com.google.android.gms.internal.d.a;
import java.util.Map;

class ax
  extends aj
{
  private static final String ID = a.ad.toString();
  private static final String anT = b.bW.toString();
  private final Context lB;
  
  public ax(Context paramContext)
  {
    super(ID, new String[0]);
    this.lB = paramContext;
  }
  
  public d.a C(Map<String, d.a> paramMap)
  {
    if ((d.a)paramMap.get(anT) != null)
    {
      paramMap = di.j((d.a)paramMap.get(anT));
      paramMap = ay.e(this.lB, paramMap);
      if (paramMap == null) {
        break label56;
      }
    }
    label56:
    for (paramMap = di.u(paramMap);; paramMap = di.pK())
    {
      return paramMap;
      paramMap = null;
      break;
    }
  }
  
  public boolean nN()
  {
    return true;
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\tagmanager\ax.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */