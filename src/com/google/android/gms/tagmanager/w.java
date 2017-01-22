package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.a;
import com.google.android.gms.internal.b;
import com.google.android.gms.internal.d.a;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

class w
  extends dg
{
  private static final String ID = a.ah.toString();
  private static final String VALUE = b.ff.toString();
  private static final String apa = b.bS.toString();
  private final DataLayer aod;
  
  public w(DataLayer paramDataLayer)
  {
    super(ID, new String[] { VALUE });
    this.aod = paramDataLayer;
  }
  
  private void a(d.a parama)
  {
    if ((parama == null) || (parama == di.pE())) {}
    for (;;)
    {
      return;
      parama = di.j(parama);
      if (parama != di.pJ()) {
        this.aod.cv(parama);
      }
    }
  }
  
  private void b(d.a parama)
  {
    if ((parama == null) || (parama == di.pE())) {}
    for (;;)
    {
      return;
      parama = di.o(parama);
      if ((parama instanceof List))
      {
        parama = ((List)parama).iterator();
        while (parama.hasNext())
        {
          Object localObject = parama.next();
          if ((localObject instanceof Map))
          {
            localObject = (Map)localObject;
            this.aod.push((Map)localObject);
          }
        }
      }
    }
  }
  
  public void E(Map<String, d.a> paramMap)
  {
    b((d.a)paramMap.get(VALUE));
    a((d.a)paramMap.get(apa));
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\tagmanager\w.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */