package com.google.android.gms.internal;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

@ez
public class al
{
  private final Object mw = new Object();
  private int np;
  private List<ak> nq = new LinkedList();
  
  public boolean a(ak paramak)
  {
    synchronized (this.mw)
    {
      if (this.nq.contains(paramak))
      {
        bool = true;
        return bool;
      }
      boolean bool = false;
    }
  }
  
  public ak aU()
  {
    Object localObject1 = null;
    ak localak2 = null;
    label154:
    for (;;)
    {
      synchronized (this.mw)
      {
        if (this.nq.size() == 0)
        {
          gs.S("Queue empty");
          localObject1 = localak2;
          return (ak)localObject1;
        }
        if (this.nq.size() >= 2)
        {
          int i = Integer.MIN_VALUE;
          Iterator localIterator = this.nq.iterator();
          if (localIterator.hasNext())
          {
            localak2 = (ak)localIterator.next();
            int j = localak2.getScore();
            if (j <= i) {
              break label154;
            }
            localObject1 = localak2;
            i = j;
            continue;
          }
          this.nq.remove(localObject1);
        }
      }
      ak localak1 = (ak)this.nq.get(0);
      localak1.aP();
    }
  }
  
  public boolean b(ak paramak)
  {
    synchronized (this.mw)
    {
      Iterator localIterator = this.nq.iterator();
      while (localIterator.hasNext())
      {
        ak localak = (ak)localIterator.next();
        if ((paramak != localak) && (localak.aO().equals(paramak.aO())))
        {
          this.nq.remove(paramak);
          bool = true;
          return bool;
        }
      }
      boolean bool = false;
    }
  }
  
  public void c(ak paramak)
  {
    synchronized (this.mw)
    {
      if (this.nq.size() >= 10)
      {
        StringBuilder localStringBuilder = new java/lang/StringBuilder;
        localStringBuilder.<init>();
        gs.S("Queue is full, current size = " + this.nq.size());
        this.nq.remove(0);
      }
      int i = this.np;
      this.np = (i + 1);
      paramak.c(i);
      this.nq.add(paramak);
      return;
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\internal\al.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */