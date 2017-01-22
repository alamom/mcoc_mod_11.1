package com.google.android.gms.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.SystemClock;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@ez
public class dx
  extends gg
  implements ServiceConnection
{
  private Context mContext;
  private final Object mw = new Object();
  private boolean sl = false;
  private el sm;
  private dw sn;
  private ec so;
  private List<ea> sp = null;
  private ee sq;
  
  public dx(Context paramContext, el paramel, ee paramee)
  {
    this.mContext = paramContext;
    this.sm = paramel;
    this.sq = paramee;
    this.sn = new dw(paramContext);
    this.so = ec.j(this.mContext);
    this.sp = this.so.d(10L);
  }
  
  private void a(final ea paramea, String paramString1, String paramString2)
  {
    final Intent localIntent = new Intent();
    localIntent.putExtra("RESPONSE_CODE", 0);
    localIntent.putExtra("INAPP_PURCHASE_DATA", paramString1);
    localIntent.putExtra("INAPP_DATA_SIGNATURE", paramString2);
    gr.wC.post(new Runnable()
    {
      public void run()
      {
        try
        {
          Object localObject1;
          Object localObject2;
          if (dx.a(dx.this).a(paramea.sB, -1, localIntent))
          {
            localObject1 = dx.c(dx.this);
            localObject2 = new com/google/android/gms/internal/eb;
            ((eb)localObject2).<init>(dx.b(dx.this), paramea.sC, true, -1, localIntent, paramea);
            ((el)localObject1).a((ek)localObject2);
          }
          for (;;)
          {
            return;
            localObject2 = dx.c(dx.this);
            localObject1 = new com/google/android/gms/internal/eb;
            ((eb)localObject1).<init>(dx.b(dx.this), paramea.sC, false, -1, localIntent, paramea);
            ((el)localObject2).a((ek)localObject1);
          }
        }
        catch (RemoteException localRemoteException)
        {
          for (;;)
          {
            gs.W("Fail to verify and dispatch pending transaction");
          }
        }
      }
    });
  }
  
  private void b(long paramLong)
  {
    do
    {
      if (!c(paramLong)) {
        gs.W("Timeout waiting for pending transaction to be processed.");
      }
    } while (!this.sl);
  }
  
  private boolean c(long paramLong)
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
        for (;;)
        {
          gs.W("waitWithTimeout_lock interrupted");
        }
      }
    }
  }
  
  private void cp()
  {
    if (this.sp.isEmpty()) {
      return;
    }
    HashMap localHashMap = new HashMap();
    Object localObject1 = this.sp.iterator();
    Object localObject2;
    while (((Iterator)localObject1).hasNext())
    {
      localObject2 = (ea)((Iterator)localObject1).next();
      localHashMap.put(((ea)localObject2).sC, localObject2);
    }
    localObject1 = null;
    for (;;)
    {
      localObject1 = this.sn.d(this.mContext.getPackageName(), (String)localObject1);
      if (localObject1 == null) {}
      do
      {
        do
        {
          localObject1 = localHashMap.keySet().iterator();
          while (((Iterator)localObject1).hasNext())
          {
            localObject2 = (String)((Iterator)localObject1).next();
            this.so.a((ea)localHashMap.get(localObject2));
          }
          break;
        } while (ed.b((Bundle)localObject1) != 0);
        localObject2 = ((Bundle)localObject1).getStringArrayList("INAPP_PURCHASE_ITEM_LIST");
        ArrayList localArrayList2 = ((Bundle)localObject1).getStringArrayList("INAPP_PURCHASE_DATA_LIST");
        ArrayList localArrayList1 = ((Bundle)localObject1).getStringArrayList("INAPP_DATA_SIGNATURE_LIST");
        localObject1 = ((Bundle)localObject1).getString("INAPP_CONTINUATION_TOKEN");
        for (int i = 0; i < ((ArrayList)localObject2).size(); i++) {
          if (localHashMap.containsKey(((ArrayList)localObject2).get(i)))
          {
            String str1 = (String)((ArrayList)localObject2).get(i);
            String str2 = (String)localArrayList2.get(i);
            String str4 = (String)localArrayList1.get(i);
            ea localea = (ea)localHashMap.get(str1);
            String str3 = ed.D(str2);
            if (localea.sB.equals(str3))
            {
              a(localea, str2, str4);
              localHashMap.remove(str1);
            }
          }
        }
      } while ((localObject1 == null) || (localHashMap.isEmpty()));
    }
  }
  
  public void co()
  {
    synchronized (this.mw)
    {
      Context localContext1 = this.mContext;
      Intent localIntent = new android/content/Intent;
      localIntent.<init>("com.android.vending.billing.InAppBillingService.BIND");
      Context localContext2 = this.mContext;
      localContext1.bindService(localIntent, this, 1);
      b(SystemClock.elapsedRealtime());
      this.mContext.unbindService(this);
      this.sn.destroy();
      return;
    }
  }
  
  public void onServiceConnected(ComponentName arg1, IBinder paramIBinder)
  {
    synchronized (this.mw)
    {
      this.sn.r(paramIBinder);
      cp();
      this.sl = true;
      this.mw.notify();
      return;
    }
  }
  
  public void onServiceDisconnected(ComponentName paramComponentName)
  {
    gs.U("In-app billing service disconnected.");
    this.sn.destroy();
  }
  
  public void onStop()
  {
    synchronized (this.mw)
    {
      this.mContext.unbindService(this);
      this.sn.destroy();
      return;
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\internal\dx.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */