package com.google.android.gms.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.internal.a;
import com.google.android.gms.common.internal.e;
import com.google.android.gms.common.internal.e.e;
import com.google.android.gms.common.internal.l;
import com.google.android.gms.common.internal.o;
import java.util.ArrayList;
import java.util.Iterator;

public class no
  extends e<nh>
{
  private final String BZ;
  private final nl akW;
  private final nj akX;
  private boolean akY;
  private final Object mw;
  
  public no(Context paramContext, nl paramnl)
  {
    super(paramContext, paramnl, paramnl, new String[0]);
    this.BZ = paramContext.getPackageName();
    this.akW = ((nl)o.i(paramnl));
    this.akW.a(this);
    this.akX = new nj();
    this.mw = new Object();
    this.akY = true;
  }
  
  private void c(nm paramnm, ni paramni)
  {
    this.akX.a(paramnm, paramni);
  }
  
  private void d(nm paramnm, ni paramni)
  {
    try
    {
      mY();
      ((nh)gS()).a(this.BZ, paramnm, paramni);
      return;
    }
    catch (RemoteException localRemoteException)
    {
      for (;;)
      {
        Log.e("PlayLoggerImpl", "Couldn't send log event.  Will try caching.");
        c(paramnm, paramni);
      }
    }
    catch (IllegalStateException localIllegalStateException)
    {
      for (;;)
      {
        Log.e("PlayLoggerImpl", "Service was disconnected.  Will try caching.");
        c(paramnm, paramni);
      }
    }
  }
  
  private void mY()
  {
    boolean bool;
    ArrayList localArrayList;
    nj.a locala;
    if (!this.akY)
    {
      bool = true;
      a.I(bool);
      if (!this.akX.isEmpty())
      {
        Object localObject = null;
        try
        {
          localArrayList = new java/util/ArrayList;
          localArrayList.<init>();
          Iterator localIterator = this.akX.mW().iterator();
          for (;;)
          {
            if (localIterator.hasNext())
            {
              locala = (nj.a)localIterator.next();
              if (locala.akO != null)
              {
                ((nh)gS()).a(this.BZ, locala.akM, pn.f(locala.akO));
                continue;
              }
            }
          }
        }
        catch (RemoteException localRemoteException)
        {
          Log.e("PlayLoggerImpl", "Couldn't send cached log events to AndroidLog service.  Retaining in memory cache.");
        }
      }
    }
    for (;;)
    {
      bool = false;
      break;
      if (locala.akM.equals(localRemoteException)) {
        localArrayList.add(locala.akN);
      }
      nm localnm;
      for (;;)
      {
        break;
        if (!localArrayList.isEmpty())
        {
          ((nh)gS()).a(this.BZ, localRemoteException, localArrayList);
          localArrayList.clear();
        }
        localnm = locala.akM;
        localArrayList.add(locala.akN);
      }
      if (!localArrayList.isEmpty()) {
        ((nh)gS()).a(this.BZ, localnm, localArrayList);
      }
      this.akX.clear();
    }
  }
  
  void S(boolean paramBoolean)
  {
    synchronized (this.mw)
    {
      boolean bool = this.akY;
      this.akY = paramBoolean;
      if ((bool) && (!this.akY)) {
        mY();
      }
      return;
    }
  }
  
  protected void a(l paraml, e.e parame)
    throws RemoteException
  {
    Bundle localBundle = new Bundle();
    paraml.f(parame, 6171000, getContext().getPackageName(), localBundle);
  }
  
  public void b(nm paramnm, ni paramni)
  {
    synchronized (this.mw)
    {
      if (this.akY)
      {
        c(paramnm, paramni);
        return;
      }
      d(paramnm, paramni);
    }
  }
  
  protected nh bD(IBinder paramIBinder)
  {
    return nh.a.bC(paramIBinder);
  }
  
  protected String getServiceDescriptor()
  {
    return "com.google.android.gms.playlog.internal.IPlayLogService";
  }
  
  protected String getStartServiceAction()
  {
    return "com.google.android.gms.playlog.service.START";
  }
  
  public void start()
  {
    synchronized (this.mw)
    {
      if ((isConnecting()) || (isConnected())) {
        return;
      }
      this.akW.R(true);
      connect();
    }
  }
  
  public void stop()
  {
    synchronized (this.mw)
    {
      this.akW.R(false);
      disconnect();
      return;
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\internal\no.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */