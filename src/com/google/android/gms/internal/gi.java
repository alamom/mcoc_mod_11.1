package com.google.android.gms.internal;

import android.os.Process;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

@ez
public final class gi
{
  private static final ThreadFactory wh = new ThreadFactory()
  {
    private final AtomicInteger wl = new AtomicInteger(1);
    
    public Thread newThread(Runnable paramAnonymousRunnable)
    {
      return new Thread(paramAnonymousRunnable, "AdWorker #" + this.wl.getAndIncrement());
    }
  };
  private static final ExecutorService wi = Executors.newFixedThreadPool(10, wh);
  
  public static Future<Void> a(Runnable paramRunnable)
  {
    submit(new Callable()
    {
      public Void dj()
      {
        this.wj.run();
        return null;
      }
    });
  }
  
  public static <T> Future<T> submit(Callable<T> paramCallable)
  {
    try
    {
      ExecutorService localExecutorService = wi;
      Callable local2 = new com/google/android/gms/internal/gi$2;
      local2.<init>(paramCallable);
      paramCallable = localExecutorService.submit(local2);
      return paramCallable;
    }
    catch (RejectedExecutionException paramCallable)
    {
      for (;;)
      {
        gs.d("Thread execution is rejected.", paramCallable);
        paramCallable = new gl(null);
      }
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\internal\gi.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */