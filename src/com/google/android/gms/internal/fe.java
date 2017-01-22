package com.google.android.gms.internal;

import android.content.Context;
import android.content.res.Resources;
import android.os.Handler;
import android.os.SystemClock;
import android.util.DisplayMetrics;
import com.google.ads.mediation.admob.AdMobAdapter;
import java.util.List;

@ez
public class fe
  extends gg
  implements gw.a
{
  private final ct lq;
  private final Context mContext;
  private final gv md;
  private final Object mw = new Object();
  private cm pR;
  private final Object sV = new Object();
  private fk sZ;
  private final fd.a tm;
  private final fz.a tn;
  private boolean to = false;
  private ck tp;
  private cq tq;
  
  public fe(Context paramContext, fz.a parama, gv paramgv, ct paramct, fd.a parama1)
  {
    this.mContext = paramContext;
    this.tn = parama;
    this.sZ = parama.vw;
    this.md = paramgv;
    this.lq = paramct;
    this.tm = parama1;
    this.pR = parama.vq;
  }
  
  private void a(fi paramfi, long paramLong)
    throws fe.a
  {
    synchronized (this.sV)
    {
      ck localck = new com/google/android/gms/internal/ck;
      localck.<init>(this.mContext, paramfi, this.lq, this.pR);
      this.tp = localck;
      this.tq = this.tp.a(paramLong, 60000L);
      switch (this.tq.qx)
      {
      default: 
        throw new a("Unexpected mediation result: " + this.tq.qx, 0);
      }
    }
    throw new a("No fill from any mediation ad networks.", 3);
  }
  
  private boolean c(long paramLong)
    throws fe.a
  {
    paramLong = 60000L - (SystemClock.elapsedRealtime() - paramLong);
    boolean bool;
    if (paramLong <= 0L) {
      bool = false;
    }
    for (;;)
    {
      return bool;
      try
      {
        this.mw.wait(paramLong);
        bool = true;
      }
      catch (InterruptedException localInterruptedException)
      {
        throw new a("Ad request cancelled.", -1);
      }
    }
  }
  
  private void f(long paramLong)
    throws fe.a
  {
    gr.wC.post(new Runnable()
    {
      public void run()
      {
        for (;;)
        {
          synchronized (fe.a(fe.this))
          {
            if (fe.c(fe.this).errorCode != -2) {
              return;
            }
            fe.d(fe.this).du().a(fe.this);
            if (fe.c(fe.this).errorCode == -3)
            {
              StringBuilder localStringBuilder = new java/lang/StringBuilder;
              localStringBuilder.<init>();
              gs.V("Loading URL in WebView: " + fe.c(fe.this).rP);
              fe.d(fe.this).loadUrl(fe.c(fe.this).rP);
            }
          }
          gs.V("Loading HTML in WebView.");
          fe.d(fe.this).loadDataWithBaseURL(gj.L(fe.c(fe.this).rP), fe.c(fe.this).tG, "text/html", "UTF-8", null);
        }
      }
    });
    h(paramLong);
  }
  
  private void h(long paramLong)
    throws fe.a
  {
    do
    {
      if (!c(paramLong)) {
        throw new a("Timed out waiting for WebView to finish loading.", 2);
      }
    } while (!this.to);
  }
  
  public void a(gv paramgv)
  {
    synchronized (this.mw)
    {
      gs.S("WebView finished loading.");
      this.to = true;
      this.mw.notify();
      return;
    }
  }
  
  public void co()
  {
    for (;;)
    {
      int i;
      long l;
      synchronized (this.mw)
      {
        gs.S("AdRendererBackgroundTask started.");
        Object localObject1 = this.tn.vv;
        i = this.tn.errorCode;
        try
        {
          l = SystemClock.elapsedRealtime();
          if (this.sZ.tI)
          {
            a((fi)localObject1, l);
            fz localfz = new com/google/android/gms/internal/fz;
            av localav = ((fi)localObject1).tx;
            gv localgv = this.md;
            List localList2 = this.sZ.qf;
            List localList1 = this.sZ.qg;
            List localList3 = this.sZ.tK;
            int j = this.sZ.orientation;
            l = this.sZ.qj;
            String str = ((fi)localObject1).tA;
            boolean bool = this.sZ.tI;
            if (this.tq == null) {
              break label460;
            }
            localObject1 = this.tq.qy;
            if (this.tq == null) {
              break label466;
            }
            Object localObject4 = this.tq.qz;
            if (this.tq == null) {
              break label472;
            }
            localObject6 = this.tq.qA;
            cm localcm = this.pR;
            if (this.tq == null) {
              break label483;
            }
            localco = this.tq.qB;
            localfz.<init>(localav, localgv, localList2, i, localList1, localList3, j, l, str, bool, (cl)localObject1, (cu)localObject4, (String)localObject6, localcm, localco, this.sZ.tJ, this.tn.lH, this.sZ.tH, this.tn.vs, this.sZ.tM, this.sZ.tN, this.tn.vp, null);
            localObject4 = gr.wC;
            localObject1 = new com/google/android/gms/internal/fe$2;
            ((2)localObject1).<init>(this, localfz);
            ((Handler)localObject4).post((Runnable)localObject1);
            return;
          }
          if (!this.sZ.tO) {
            break label414;
          }
          g(l);
          continue;
          if (i != -1) {
            break label422;
          }
        }
        catch (a locala)
        {
          i = locala.getErrorCode();
          if (i == 3) {}
        }
        gs.U(locala.getMessage());
        if (this.sZ != null) {
          break label433;
        }
        localObject5 = new com/google/android/gms/internal/fk;
        ((fk)localObject5).<init>(i);
        this.sZ = ((fk)localObject5);
        localObject5 = gr.wC;
        localObject6 = new com/google/android/gms/internal/fe$1;
        ((1)localObject6).<init>(this);
        ((Handler)localObject5).post((Runnable)localObject6);
      }
      label414:
      f(l);
      continue;
      label422:
      gs.W(((a)localObject5).getMessage());
      continue;
      label433:
      Object localObject5 = new com/google/android/gms/internal/fk;
      ((fk)localObject5).<init>(i, this.sZ.qj);
      this.sZ = ((fk)localObject5);
      continue;
      label460:
      Object localObject3 = null;
      continue;
      label466:
      localObject5 = null;
      continue;
      label472:
      Object localObject6 = AdMobAdapter.class.getName();
      continue;
      label483:
      co localco = null;
    }
  }
  
  protected void g(long paramLong)
    throws fe.a
  {
    final Object localObject = this.md.Y();
    int j;
    if (((ay)localObject).og) {
      j = this.mContext.getResources().getDisplayMetrics().widthPixels;
    }
    for (int i = this.mContext.getResources().getDisplayMetrics().heightPixels;; i = ((ay)localObject).heightPixels)
    {
      localObject = new fc(this, this.md, j, i);
      gr.wC.post(new Runnable()
      {
        public void run()
        {
          synchronized (fe.a(fe.this))
          {
            if (fe.c(fe.this).errorCode != -2) {
              return;
            }
            fe.d(fe.this).du().a(fe.this);
            localObject.b(fe.c(fe.this));
          }
        }
      });
      h(paramLong);
      if (!((fc)localObject).cA()) {
        break;
      }
      gs.S("Ad-Network indicated no fill with passback URL.");
      throw new a("AdNetwork sent passback url", 3);
      j = ((ay)localObject).widthPixels;
    }
    if (!((fc)localObject).cB()) {
      throw new a("AdNetwork timed out", 2);
    }
  }
  
  public void onStop()
  {
    synchronized (this.sV)
    {
      this.md.stopLoading();
      gj.a(this.md);
      if (this.tp != null) {
        this.tp.cancel();
      }
      return;
    }
  }
  
  private static final class a
    extends Exception
  {
    private final int tc;
    
    public a(String paramString, int paramInt)
    {
      super();
      this.tc = paramInt;
    }
    
    public int getErrorCode()
    {
      return this.tc;
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\internal\fe.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */