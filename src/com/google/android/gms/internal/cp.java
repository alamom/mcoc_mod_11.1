package com.google.android.gms.internal;

import android.content.Context;
import android.os.Handler;
import android.os.RemoteException;
import android.os.SystemClock;
import android.text.TextUtils;
import com.google.android.gms.dynamic.e;

@ez
public final class cp
  implements cq.a
{
  private final ct lq;
  private final Context mContext;
  private final av ml;
  private final Object mw = new Object();
  private final String qo;
  private final long qp;
  private final cl qq;
  private final ay qr;
  private final gt qs;
  private cu qt;
  private int qu = -2;
  
  public cp(Context paramContext, String paramString, ct paramct, cm paramcm, cl paramcl, av paramav, ay paramay, gt paramgt)
  {
    this.mContext = paramContext;
    this.lq = paramct;
    this.qq = paramcl;
    if ("com.google.ads.mediation.customevent.CustomEventAdapter".equals(paramString))
    {
      this.qo = bE();
      if (paramcm.qe == -1L) {
        break label106;
      }
    }
    label106:
    for (long l = paramcm.qe;; l = 10000L)
    {
      this.qp = l;
      this.ml = paramav;
      this.qr = paramay;
      this.qs = paramgt;
      return;
      this.qo = paramString;
      break;
    }
  }
  
  private void a(long paramLong1, long paramLong2, long paramLong3, long paramLong4)
  {
    for (;;)
    {
      if (this.qu != -2) {
        return;
      }
      b(paramLong1, paramLong2, paramLong3, paramLong4);
    }
  }
  
  private void a(co paramco)
  {
    try
    {
      if (this.qs.wF < 4100000)
      {
        if (this.qr.og) {
          this.qt.a(e.k(this.mContext), this.ml, this.qq.qc, paramco);
        }
        for (;;)
        {
          return;
          this.qt.a(e.k(this.mContext), this.qr, this.ml, this.qq.qc, paramco);
        }
      }
    }
    catch (RemoteException paramco)
    {
      for (;;)
      {
        gs.d("Could not request ad from mediation adapter.", paramco);
        j(5);
        continue;
        if (this.qr.og) {
          this.qt.a(e.k(this.mContext), this.ml, this.qq.qc, this.qq.pW, paramco);
        } else {
          this.qt.a(e.k(this.mContext), this.qr, this.ml, this.qq.qc, this.qq.pW, paramco);
        }
      }
    }
  }
  
  private void b(long paramLong1, long paramLong2, long paramLong3, long paramLong4)
  {
    long l = SystemClock.elapsedRealtime();
    paramLong1 = paramLong2 - (l - paramLong1);
    paramLong2 = paramLong4 - (l - paramLong3);
    if ((paramLong1 <= 0L) || (paramLong2 <= 0L))
    {
      gs.U("Timed out waiting for adapter.");
      this.qu = 3;
    }
    for (;;)
    {
      return;
      try
      {
        this.mw.wait(Math.min(paramLong1, paramLong2));
      }
      catch (InterruptedException localInterruptedException)
      {
        this.qu = -1;
      }
    }
  }
  
  private String bE()
  {
    for (;;)
    {
      try
      {
        if (TextUtils.isEmpty(this.qq.qa)) {
          continue;
        }
        if (!this.lq.y(this.qq.qa)) {
          continue;
        }
        str1 = "com.google.android.gms.ads.mediation.customevent.CustomEventAdapter";
      }
      catch (RemoteException localRemoteException)
      {
        String str1;
        gs.W("Fail to determine the custom event's version, assuming the old one.");
        String str2 = "com.google.ads.mediation.customevent.CustomEventAdapter";
        continue;
      }
      return str1;
      str1 = "com.google.ads.mediation.customevent.CustomEventAdapter";
    }
  }
  
  private cu bF()
  {
    gs.U("Instantiating mediation adapter: " + this.qo);
    try
    {
      cu localcu = this.lq.x(this.qo);
      return localcu;
    }
    catch (RemoteException localRemoteException)
    {
      for (;;)
      {
        gs.a("Could not instantiate mediation adapter: " + this.qo, localRemoteException);
        Object localObject = null;
      }
    }
  }
  
  public cq b(long paramLong1, long paramLong2)
  {
    synchronized (this.mw)
    {
      long l = SystemClock.elapsedRealtime();
      co localco = new com/google/android/gms/internal/co;
      localco.<init>();
      Handler localHandler = gr.wC;
      Object localObject3 = new com/google/android/gms/internal/cp$1;
      ((1)localObject3).<init>(this, localco);
      localHandler.post((Runnable)localObject3);
      a(l, this.qp, paramLong1, paramLong2);
      localObject3 = new com/google/android/gms/internal/cq;
      ((cq)localObject3).<init>(this.qq, this.qt, this.qo, localco, this.qu);
      return (cq)localObject3;
    }
  }
  
  public void cancel()
  {
    synchronized (this.mw)
    {
      try
      {
        if (this.qt != null) {
          this.qt.destroy();
        }
        this.qu = -1;
        this.mw.notify();
        return;
      }
      catch (RemoteException localRemoteException)
      {
        for (;;)
        {
          gs.d("Could not destroy mediation adapter.", localRemoteException);
        }
      }
    }
  }
  
  public void j(int paramInt)
  {
    synchronized (this.mw)
    {
      this.qu = paramInt;
      this.mw.notify();
      return;
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\internal\cp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */