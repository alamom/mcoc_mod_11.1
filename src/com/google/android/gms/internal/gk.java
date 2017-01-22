package com.google.android.gms.internal;

import java.util.concurrent.CancellationException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@ez
public class gk<T>
  implements Future<T>
{
  private final Object mw = new Object();
  private boolean pS = false;
  private T wq = null;
  private boolean wr = false;
  
  public void a(T paramT)
  {
    synchronized (this.mw)
    {
      if (this.wr)
      {
        paramT = new java/lang/IllegalStateException;
        paramT.<init>("Provided CallbackFuture with multiple values.");
        throw paramT;
      }
    }
    this.wr = true;
    this.wq = paramT;
    this.mw.notifyAll();
  }
  
  public boolean cancel(boolean paramBoolean)
  {
    boolean bool = false;
    if (!paramBoolean) {}
    for (paramBoolean = bool;; paramBoolean = true)
    {
      for (;;)
      {
        return paramBoolean;
        synchronized (this.mw)
        {
          if (this.wr) {
            paramBoolean = bool;
          }
        }
      }
      this.pS = true;
      this.wr = true;
      this.mw.notifyAll();
    }
  }
  
  public T get()
  {
    synchronized (this.mw)
    {
      boolean bool = this.wr;
      if (bool) {}
    }
    try
    {
      this.mw.wait();
      if (this.pS)
      {
        CancellationException localCancellationException = new java/util/concurrent/CancellationException;
        localCancellationException.<init>("CallbackFuture was cancelled.");
        throw localCancellationException;
        localObject2 = finally;
        throw ((Throwable)localObject2);
      }
      Object localObject3 = this.wq;
      return (T)localObject3;
    }
    catch (InterruptedException localInterruptedException)
    {
      for (;;) {}
    }
  }
  
  public T get(long paramLong, TimeUnit paramTimeUnit)
    throws TimeoutException
  {
    synchronized (this.mw)
    {
      boolean bool = this.wr;
      if (!bool) {}
      try
      {
        paramLong = paramTimeUnit.toMillis(paramLong);
        if (paramLong != 0L) {
          this.mw.wait(paramLong);
        }
      }
      catch (InterruptedException paramTimeUnit)
      {
        for (;;) {}
      }
      if (!this.wr)
      {
        paramTimeUnit = new java/util/concurrent/TimeoutException;
        paramTimeUnit.<init>("CallbackFuture timed out.");
        throw paramTimeUnit;
      }
    }
    if (this.pS)
    {
      paramTimeUnit = new java/util/concurrent/CancellationException;
      paramTimeUnit.<init>("CallbackFuture was cancelled.");
      throw paramTimeUnit;
    }
    paramTimeUnit = this.wq;
    return paramTimeUnit;
  }
  
  public boolean isCancelled()
  {
    synchronized (this.mw)
    {
      boolean bool = this.pS;
      return bool;
    }
  }
  
  public boolean isDone()
  {
    synchronized (this.mw)
    {
      boolean bool = this.wr;
      return bool;
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\internal\gk.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */