package com.google.android.gms.internal;

import android.content.Context;
import android.os.Handler;
import android.os.RemoteException;
import java.util.Iterator;
import java.util.List;

@ez
public final class ck
{
  private final ct lq;
  private final Context mContext;
  private final Object mw = new Object();
  private final fi pQ;
  private final cm pR;
  private boolean pS = false;
  private cp pT;
  
  public ck(Context paramContext, fi paramfi, ct paramct, cm paramcm)
  {
    this.mContext = paramContext;
    this.pQ = paramfi;
    this.lq = paramct;
    this.pR = paramcm;
  }
  
  public cq a(long paramLong1, long paramLong2)
  {
    gs.S("Starting mediation.");
    Iterator localIterator2 = this.pR.qd.iterator();
    cl localcl;
    label79:
    String str;
    if (localIterator2.hasNext())
    {
      localcl = (cl)localIterator2.next();
      gs.U("Trying mediation network: " + localcl.pX);
      Iterator localIterator1 = localcl.pY.iterator();
      if (localIterator1.hasNext()) {
        str = (String)localIterator1.next();
      }
    }
    for (;;)
    {
      synchronized (this.mw)
      {
        if (this.pS)
        {
          localObject1 = new com/google/android/gms/internal/cq;
          ((cq)localObject1).<init>(-1);
          return (cq)localObject1;
        }
        Object localObject1 = new com/google/android/gms/internal/cp;
        ((cp)localObject1).<init>(this.mContext, str, this.lq, this.pR, localcl, this.pQ.tx, this.pQ.lH, this.pQ.lD);
        this.pT = ((cp)localObject1);
        localObject1 = this.pT.b(paramLong1, paramLong2);
        if (((cq)localObject1).qx == 0) {
          gs.S("Adapter succeeded.");
        }
      }
      if (localcq1.qz == null) {
        break label79;
      }
      gr.wC.post(new Runnable()
      {
        public void run()
        {
          try
          {
            localcq1.qz.destroy();
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
      });
      break label79;
      break;
      cq localcq2 = new cq(1);
    }
  }
  
  public void cancel()
  {
    synchronized (this.mw)
    {
      this.pS = true;
      if (this.pT != null) {
        this.pT.cancel();
      }
      return;
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\internal\ck.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */