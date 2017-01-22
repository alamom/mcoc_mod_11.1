package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.a;
import com.google.android.gms.internal.b;
import com.google.android.gms.internal.d.a;
import java.util.Map;

class cf
  extends aj
{
  private static final String ID = a.Q.toString();
  private static final String aql = b.dz.toString();
  private static final String aqm = b.dy.toString();
  
  public cf()
  {
    super(ID, new String[0]);
  }
  
  public d.a C(Map<String, d.a> paramMap)
  {
    Object localObject = (d.a)paramMap.get(aql);
    d.a locala = (d.a)paramMap.get(aqm);
    double d2;
    double d1;
    if ((localObject != null) && (localObject != di.pK()) && (locala != null) && (locala != di.pK()))
    {
      paramMap = di.k((d.a)localObject);
      localObject = di.k(locala);
      if ((paramMap != di.pI()) && (localObject != di.pI()))
      {
        d2 = paramMap.doubleValue();
        d1 = ((dh)localObject).doubleValue();
        if (d2 > d1) {}
      }
    }
    for (;;)
    {
      return di.u(Long.valueOf(Math.round((d1 - d2) * Math.random() + d2)));
      d1 = 2.147483647E9D;
      d2 = 0.0D;
    }
  }
  
  public boolean nN()
  {
    return false;
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\tagmanager\cf.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */