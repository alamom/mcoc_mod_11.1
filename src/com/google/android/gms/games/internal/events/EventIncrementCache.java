package com.google.android.gms.games.internal.events;

import android.os.Handler;
import android.os.Looper;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class EventIncrementCache
{
  final Object aaq = new Object();
  private Handler aar;
  private boolean aas;
  private HashMap<String, AtomicInteger> aat;
  private int aau;
  
  public EventIncrementCache(Looper paramLooper, int paramInt)
  {
    this.aar = new Handler(paramLooper);
    this.aat = new HashMap();
    this.aau = paramInt;
  }
  
  private void kQ()
  {
    synchronized (this.aaq)
    {
      this.aas = false;
      flush();
      return;
    }
  }
  
  public void flush()
  {
    synchronized (this.aaq)
    {
      Iterator localIterator = this.aat.entrySet().iterator();
      if (localIterator.hasNext())
      {
        Map.Entry localEntry = (Map.Entry)localIterator.next();
        q((String)localEntry.getKey(), ((AtomicInteger)localEntry.getValue()).get());
      }
    }
    this.aat.clear();
  }
  
  protected abstract void q(String paramString, int paramInt);
  
  public void w(String paramString, int paramInt)
  {
    synchronized (this.aaq)
    {
      if (!this.aas)
      {
        this.aas = true;
        localObject1 = this.aar;
        localObject2 = new com/google/android/gms/games/internal/events/EventIncrementCache$1;
        ((1)localObject2).<init>(this);
        ((Handler)localObject1).postDelayed((Runnable)localObject2, this.aau);
      }
      Object localObject2 = (AtomicInteger)this.aat.get(paramString);
      Object localObject1 = localObject2;
      if (localObject2 == null)
      {
        localObject1 = new java/util/concurrent/atomic/AtomicInteger;
        ((AtomicInteger)localObject1).<init>();
        this.aat.put(paramString, localObject1);
      }
      ((AtomicInteger)localObject1).addAndGet(paramInt);
      return;
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\games\internal\events\EventIncrementCache.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */