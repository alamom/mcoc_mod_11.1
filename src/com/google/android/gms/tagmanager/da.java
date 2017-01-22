package com.google.android.gms.tagmanager;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

class da<K, V>
  implements k<K, V>
{
  private final Map<K, V> arD = new HashMap();
  private final int arE;
  private final l.a<K, V> arF;
  private int arG;
  
  da(int paramInt, l.a<K, V> parama)
  {
    this.arE = paramInt;
    this.arF = parama;
  }
  
  public void e(K paramK, V paramV)
  {
    if ((paramK == null) || (paramV == null)) {
      try
      {
        paramK = new java/lang/NullPointerException;
        paramK.<init>("key == null || value == null");
        throw paramK;
      }
      finally {}
    }
    this.arG += this.arF.sizeOf(paramK, paramV);
    if (this.arG > this.arE)
    {
      Iterator localIterator = this.arD.entrySet().iterator();
      do
      {
        if (!localIterator.hasNext()) {
          break;
        }
        Map.Entry localEntry = (Map.Entry)localIterator.next();
        this.arG -= this.arF.sizeOf(localEntry.getKey(), localEntry.getValue());
        localIterator.remove();
      } while (this.arG > this.arE);
    }
    this.arD.put(paramK, paramV);
  }
  
  public V get(K paramK)
  {
    try
    {
      paramK = this.arD.get(paramK);
      return paramK;
    }
    finally
    {
      paramK = finally;
      throw paramK;
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\tagmanager\da.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */