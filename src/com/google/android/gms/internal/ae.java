package com.google.android.gms.internal;

import android.content.Context;
import android.view.View;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.WeakHashMap;

@ez
public final class ae
  implements ag
{
  private final Object mw = new Object();
  private final WeakHashMap<fz, af> mx = new WeakHashMap();
  private final ArrayList<af> my = new ArrayList();
  
  public af a(Context paramContext, ay paramay, fz paramfz, View paramView, gt paramgt)
  {
    synchronized (this.mw)
    {
      if (c(paramfz))
      {
        paramContext = (af)this.mx.get(paramfz);
        return paramContext;
      }
      af localaf = new com/google/android/gms/internal/af;
      localaf.<init>(paramContext, paramay, paramfz, paramView, paramgt);
      localaf.a(this);
      this.mx.put(paramfz, localaf);
      this.my.add(localaf);
      paramContext = localaf;
    }
  }
  
  public af a(ay paramay, fz paramfz)
  {
    return a(paramfz.rN.getContext(), paramay, paramfz, paramfz.rN, paramfz.rN.dx());
  }
  
  public void a(af paramaf)
  {
    synchronized (this.mw)
    {
      if (!paramaf.aH()) {
        this.my.remove(paramaf);
      }
      return;
    }
  }
  
  public boolean c(fz paramfz)
  {
    synchronized (this.mw)
    {
      paramfz = (af)this.mx.get(paramfz);
      if ((paramfz != null) && (paramfz.aH()))
      {
        bool = true;
        return bool;
      }
      boolean bool = false;
    }
  }
  
  public void d(fz paramfz)
  {
    synchronized (this.mw)
    {
      paramfz = (af)this.mx.get(paramfz);
      if (paramfz != null) {
        paramfz.aF();
      }
      return;
    }
  }
  
  public void pause()
  {
    synchronized (this.mw)
    {
      Iterator localIterator = this.my.iterator();
      if (localIterator.hasNext()) {
        ((af)localIterator.next()).pause();
      }
    }
  }
  
  public void resume()
  {
    synchronized (this.mw)
    {
      Iterator localIterator = this.my.iterator();
      if (localIterator.hasNext()) {
        ((af)localIterator.next()).resume();
      }
    }
  }
  
  public void stop()
  {
    synchronized (this.mw)
    {
      Iterator localIterator = this.my.iterator();
      if (localIterator.hasNext()) {
        ((af)localIterator.next()).stop();
      }
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\internal\ae.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */