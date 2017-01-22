package com.google.android.gms.tagmanager;

import android.content.Context;
import com.google.android.gms.internal.c.j;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

class cp
  implements o.e
{
  private final String aoc;
  private String aoy;
  private bg<c.j> aqt;
  private r aqu;
  private final ScheduledExecutorService aqw;
  private final a aqx;
  private ScheduledFuture<?> aqy;
  private boolean mClosed;
  private final Context mContext;
  
  public cp(Context paramContext, String paramString, r paramr)
  {
    this(paramContext, paramString, paramr, null, null);
  }
  
  cp(Context paramContext, String paramString, r paramr, b paramb, a parama)
  {
    this.aqu = paramr;
    this.mContext = paramContext;
    this.aoc = paramString;
    paramContext = paramb;
    if (paramb == null) {
      paramContext = new b()
      {
        public ScheduledExecutorService oQ()
        {
          return Executors.newSingleThreadScheduledExecutor();
        }
      };
    }
    this.aqw = paramContext.oQ();
    if (parama == null) {}
    for (this.aqx = new a()
        {
          public co a(r paramAnonymousr)
          {
            return new co(cp.a(cp.this), cp.b(cp.this), paramAnonymousr);
          }
        };; this.aqx = parama) {
      return;
    }
  }
  
  private co cK(String paramString)
  {
    co localco = this.aqx.a(this.aqu);
    localco.a(this.aqt);
    localco.cu(this.aoy);
    localco.cJ(paramString);
    return localco;
  }
  
  private void oP()
  {
    try
    {
      if (this.mClosed)
      {
        IllegalStateException localIllegalStateException = new java/lang/IllegalStateException;
        localIllegalStateException.<init>("called method after closed");
        throw localIllegalStateException;
      }
    }
    finally {}
  }
  
  public void a(bg<c.j> parambg)
  {
    try
    {
      oP();
      this.aqt = parambg;
      return;
    }
    finally
    {
      parambg = finally;
      throw parambg;
    }
  }
  
  public void cu(String paramString)
  {
    try
    {
      oP();
      this.aoy = paramString;
      return;
    }
    finally
    {
      paramString = finally;
      throw paramString;
    }
  }
  
  public void e(long paramLong, String paramString)
  {
    try
    {
      StringBuilder localStringBuilder = new java/lang/StringBuilder;
      localStringBuilder.<init>();
      bh.V("loadAfterDelay: containerId=" + this.aoc + " delay=" + paramLong);
      oP();
      if (this.aqt == null)
      {
        paramString = new java/lang/IllegalStateException;
        paramString.<init>("callback must be set before loadAfterDelay() is called.");
        throw paramString;
      }
    }
    finally {}
    if (this.aqy != null) {
      this.aqy.cancel(false);
    }
    this.aqy = this.aqw.schedule(cK(paramString), paramLong, TimeUnit.MILLISECONDS);
  }
  
  public void release()
  {
    try
    {
      oP();
      if (this.aqy != null) {
        this.aqy.cancel(false);
      }
      this.aqw.shutdown();
      this.mClosed = true;
      return;
    }
    finally {}
  }
  
  static abstract interface a
  {
    public abstract co a(r paramr);
  }
  
  static abstract interface b
  {
    public abstract ScheduledExecutorService oQ();
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\tagmanager\cp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */