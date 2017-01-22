package com.google.android.gms.common.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import java.util.ArrayList;
import java.util.Iterator;

public final class f
{
  private final b LM;
  private final ArrayList<GoogleApiClient.ConnectionCallbacks> LN = new ArrayList();
  final ArrayList<GoogleApiClient.ConnectionCallbacks> LO = new ArrayList();
  private boolean LP = false;
  private final ArrayList<GooglePlayServicesClient.OnConnectionFailedListener> LQ = new ArrayList();
  private final Handler mHandler;
  
  public f(Context paramContext, Looper paramLooper, b paramb)
  {
    this.LM = paramb;
    this.mHandler = new a(paramLooper);
  }
  
  public void aB(int paramInt)
  {
    this.mHandler.removeMessages(1);
    synchronized (this.LN)
    {
      this.LP = true;
      Object localObject1 = new java/util/ArrayList;
      ((ArrayList)localObject1).<init>(this.LN);
      Iterator localIterator = ((ArrayList)localObject1).iterator();
      do
      {
        if (localIterator.hasNext())
        {
          localObject1 = (GoogleApiClient.ConnectionCallbacks)localIterator.next();
          if (this.LM.gq()) {}
        }
        else
        {
          this.LP = false;
          return;
        }
      } while (!this.LN.contains(localObject1));
      ((GoogleApiClient.ConnectionCallbacks)localObject1).onConnectionSuspended(paramInt);
    }
  }
  
  public void b(ConnectionResult paramConnectionResult)
  {
    this.mHandler.removeMessages(1);
    for (;;)
    {
      synchronized (this.LQ)
      {
        Object localObject = new java/util/ArrayList;
        ((ArrayList)localObject).<init>(this.LQ);
        Iterator localIterator = ((ArrayList)localObject).iterator();
        if (localIterator.hasNext())
        {
          localObject = (GooglePlayServicesClient.OnConnectionFailedListener)localIterator.next();
          if (!this.LM.gq()) {
            return;
          }
          if (!this.LQ.contains(localObject)) {
            continue;
          }
          ((GooglePlayServicesClient.OnConnectionFailedListener)localObject).onConnectionFailed(paramConnectionResult);
        }
      }
    }
  }
  
  public void d(Bundle paramBundle)
  {
    boolean bool2 = true;
    synchronized (this.LN)
    {
      boolean bool1;
      label49:
      Object localObject;
      Iterator localIterator;
      if (!this.LP)
      {
        bool1 = true;
        o.I(bool1);
        this.mHandler.removeMessages(1);
        this.LP = true;
        if (this.LO.size() != 0) {
          break label141;
        }
        bool1 = bool2;
        o.I(bool1);
        localObject = new java/util/ArrayList;
        ((ArrayList)localObject).<init>(this.LN);
        localIterator = ((ArrayList)localObject).iterator();
      }
      label141:
      do
      {
        if (localIterator.hasNext())
        {
          localObject = (GoogleApiClient.ConnectionCallbacks)localIterator.next();
          if ((this.LM.gq()) && (this.LM.isConnected())) {}
        }
        else
        {
          this.LO.clear();
          this.LP = false;
          return;
          bool1 = false;
          break;
          bool1 = false;
          break label49;
        }
      } while (this.LO.contains(localObject));
      ((GoogleApiClient.ConnectionCallbacks)localObject).onConnected(paramBundle);
    }
  }
  
  protected void dL()
  {
    synchronized (this.LN)
    {
      d(this.LM.fC());
      return;
    }
  }
  
  public boolean isConnectionCallbacksRegistered(GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks)
  {
    o.i(paramConnectionCallbacks);
    synchronized (this.LN)
    {
      boolean bool = this.LN.contains(paramConnectionCallbacks);
      return bool;
    }
  }
  
  public boolean isConnectionFailedListenerRegistered(GooglePlayServicesClient.OnConnectionFailedListener paramOnConnectionFailedListener)
  {
    o.i(paramOnConnectionFailedListener);
    synchronized (this.LQ)
    {
      boolean bool = this.LQ.contains(paramOnConnectionFailedListener);
      return bool;
    }
  }
  
  public void registerConnectionCallbacks(GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks)
  {
    o.i(paramConnectionCallbacks);
    synchronized (this.LN)
    {
      if (this.LN.contains(paramConnectionCallbacks))
      {
        StringBuilder localStringBuilder = new java/lang/StringBuilder;
        localStringBuilder.<init>();
        Log.w("GmsClientEvents", "registerConnectionCallbacks(): listener " + paramConnectionCallbacks + " is already registered");
        if (this.LM.isConnected()) {
          this.mHandler.sendMessage(this.mHandler.obtainMessage(1, paramConnectionCallbacks));
        }
        return;
      }
      this.LN.add(paramConnectionCallbacks);
    }
  }
  
  public void registerConnectionFailedListener(GooglePlayServicesClient.OnConnectionFailedListener paramOnConnectionFailedListener)
  {
    o.i(paramOnConnectionFailedListener);
    synchronized (this.LQ)
    {
      if (this.LQ.contains(paramOnConnectionFailedListener))
      {
        StringBuilder localStringBuilder = new java/lang/StringBuilder;
        localStringBuilder.<init>();
        Log.w("GmsClientEvents", "registerConnectionFailedListener(): listener " + paramOnConnectionFailedListener + " is already registered");
        return;
      }
      this.LQ.add(paramOnConnectionFailedListener);
    }
  }
  
  public void unregisterConnectionCallbacks(GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks)
  {
    o.i(paramConnectionCallbacks);
    synchronized (this.LN)
    {
      if (this.LN != null)
      {
        if (this.LN.remove(paramConnectionCallbacks)) {
          break label65;
        }
        localStringBuilder = new java/lang/StringBuilder;
        localStringBuilder.<init>();
        Log.w("GmsClientEvents", "unregisterConnectionCallbacks(): listener " + paramConnectionCallbacks + " not found");
      }
      label65:
      while (!this.LP)
      {
        StringBuilder localStringBuilder;
        return;
      }
      this.LO.add(paramConnectionCallbacks);
    }
  }
  
  public void unregisterConnectionFailedListener(GooglePlayServicesClient.OnConnectionFailedListener paramOnConnectionFailedListener)
  {
    o.i(paramOnConnectionFailedListener);
    synchronized (this.LQ)
    {
      if ((this.LQ != null) && (!this.LQ.remove(paramOnConnectionFailedListener)))
      {
        StringBuilder localStringBuilder = new java/lang/StringBuilder;
        localStringBuilder.<init>();
        Log.w("GmsClientEvents", "unregisterConnectionFailedListener(): listener " + paramOnConnectionFailedListener + " not found");
      }
      return;
    }
  }
  
  final class a
    extends Handler
  {
    public a(Looper paramLooper)
    {
      super();
    }
    
    public void handleMessage(Message paramMessage)
    {
      if (paramMessage.what == 1) {}
      for (;;)
      {
        synchronized (f.a(f.this))
        {
          if ((f.b(f.this).gq()) && (f.b(f.this).isConnected()) && (f.a(f.this).contains(paramMessage.obj)))
          {
            Bundle localBundle = f.b(f.this).fC();
            ((GoogleApiClient.ConnectionCallbacks)paramMessage.obj).onConnected(localBundle);
          }
          return;
        }
        Log.wtf("GmsClientEvents", "Don't know how to handle this message.");
      }
    }
  }
  
  public static abstract interface b
  {
    public abstract Bundle fC();
    
    public abstract boolean gq();
    
    public abstract boolean isConnected();
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\common\internal\f.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */