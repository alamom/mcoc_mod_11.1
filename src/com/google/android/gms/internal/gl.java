package com.google.android.gms.internal;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

@ez
public class gl<T>
  implements Future<T>
{
  private final T wq;
  
  public gl(T paramT)
  {
    this.wq = paramT;
  }
  
  public boolean cancel(boolean paramBoolean)
  {
    return false;
  }
  
  public T get()
  {
    return (T)this.wq;
  }
  
  public T get(long paramLong, TimeUnit paramTimeUnit)
  {
    return (T)this.wq;
  }
  
  public boolean isCancelled()
  {
    return false;
  }
  
  public boolean isDone()
  {
    return true;
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\internal\gl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */