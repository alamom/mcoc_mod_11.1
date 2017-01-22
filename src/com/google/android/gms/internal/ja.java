package com.google.android.gms.internal;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.Set;

public class ja<K, V>
{
  private final LinkedHashMap<K, V> Mj;
  private int Mk;
  private int Ml;
  private int Mm;
  private int Mn;
  private int Mo;
  private int Mp;
  private int size;
  
  public ja(int paramInt)
  {
    if (paramInt <= 0) {
      throw new IllegalArgumentException("maxSize <= 0");
    }
    this.Mk = paramInt;
    this.Mj = new LinkedHashMap(0, 0.75F, true);
  }
  
  private int c(K paramK, V paramV)
  {
    int i = sizeOf(paramK, paramV);
    if (i < 0) {
      throw new IllegalStateException("Negative size: " + paramK + "=" + paramV);
    }
    return i;
  }
  
  protected V create(K paramK)
  {
    return null;
  }
  
  protected void entryRemoved(boolean paramBoolean, K paramK, V paramV1, V paramV2) {}
  
  public final void evictAll()
  {
    trimToSize(-1);
  }
  
  public final V get(K paramK)
  {
    if (paramK == null) {
      throw new NullPointerException("key == null");
    }
    for (;;)
    {
      Object localObject1;
      Object localObject2;
      try
      {
        localObject1 = this.Mj.get(paramK);
        if (localObject1 != null)
        {
          this.Mo += 1;
          paramK = (K)localObject1;
          return paramK;
        }
        this.Mp += 1;
        localObject2 = create(paramK);
        if (localObject2 == null)
        {
          paramK = null;
          continue;
        }
      }
      finally {}
      try
      {
        this.Mm += 1;
        localObject1 = this.Mj.put(paramK, localObject2);
        if (localObject1 != null) {
          this.Mj.put(paramK, localObject1);
        }
        for (;;)
        {
          if (localObject1 == null) {
            break label155;
          }
          entryRemoved(false, paramK, localObject2, localObject1);
          paramK = (K)localObject1;
          break;
          this.size += c(paramK, localObject2);
        }
        trimToSize(this.Mk);
      }
      finally {}
      label155:
      paramK = (K)localObject2;
    }
  }
  
  public final V put(K paramK, V paramV)
  {
    if ((paramK == null) || (paramV == null)) {
      throw new NullPointerException("key == null || value == null");
    }
    try
    {
      this.Ml += 1;
      this.size += c(paramK, paramV);
      Object localObject = this.Mj.put(paramK, paramV);
      if (localObject != null) {
        this.size -= c(paramK, localObject);
      }
      if (localObject != null) {
        entryRemoved(false, paramK, localObject, paramV);
      }
      trimToSize(this.Mk);
      return (V)localObject;
    }
    finally {}
  }
  
  public final int size()
  {
    try
    {
      int i = this.size;
      return i;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  protected int sizeOf(K paramK, V paramV)
  {
    return 1;
  }
  
  public final String toString()
  {
    int i = 0;
    try
    {
      int j = this.Mo + this.Mp;
      if (j != 0) {
        i = this.Mo * 100 / j;
      }
      String str = String.format("LruCache[maxSize=%d,hits=%d,misses=%d,hitRate=%d%%]", new Object[] { Integer.valueOf(this.Mk), Integer.valueOf(this.Mo), Integer.valueOf(this.Mp), Integer.valueOf(i) });
      return str;
    }
    finally {}
  }
  
  public void trimToSize(int paramInt)
  {
    for (;;)
    {
      try
      {
        if ((this.size < 0) || ((this.Mj.isEmpty()) && (this.size != 0)))
        {
          IllegalStateException localIllegalStateException = new java/lang/IllegalStateException;
          localObject3 = new java/lang/StringBuilder;
          ((StringBuilder)localObject3).<init>();
          localIllegalStateException.<init>(getClass().getName() + ".sizeOf() is reporting inconsistent results!");
          throw localIllegalStateException;
        }
      }
      finally {}
      if ((this.size <= paramInt) || (this.Mj.isEmpty())) {
        return;
      }
      Object localObject3 = (Map.Entry)this.Mj.entrySet().iterator().next();
      Object localObject2 = ((Map.Entry)localObject3).getKey();
      localObject3 = ((Map.Entry)localObject3).getValue();
      this.Mj.remove(localObject2);
      this.size -= c(localObject2, localObject3);
      this.Mn += 1;
      entryRemoved(true, localObject2, localObject3, null);
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\internal\ja.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */