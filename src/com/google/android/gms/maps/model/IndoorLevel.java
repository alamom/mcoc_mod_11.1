package com.google.android.gms.maps.model;

import android.os.RemoteException;
import com.google.android.gms.common.internal.o;
import com.google.android.gms.maps.model.internal.e;

public final class IndoorLevel
{
  private final e ajX;
  
  public IndoorLevel(e parame)
  {
    this.ajX = ((e)o.i(parame));
  }
  
  public void activate()
  {
    try
    {
      this.ajX.activate();
      return;
    }
    catch (RemoteException localRemoteException)
    {
      throw new RuntimeRemoteException(localRemoteException);
    }
  }
  
  public boolean equals(Object paramObject)
  {
    boolean bool;
    if (!(paramObject instanceof IndoorLevel)) {
      bool = false;
    }
    for (;;)
    {
      return bool;
      try
      {
        bool = this.ajX.a(((IndoorLevel)paramObject).ajX);
      }
      catch (RemoteException paramObject)
      {
        throw new RuntimeRemoteException((RemoteException)paramObject);
      }
    }
  }
  
  public String getName()
  {
    try
    {
      String str = this.ajX.getName();
      return str;
    }
    catch (RemoteException localRemoteException)
    {
      throw new RuntimeRemoteException(localRemoteException);
    }
  }
  
  public String getShortName()
  {
    try
    {
      String str = this.ajX.getShortName();
      return str;
    }
    catch (RemoteException localRemoteException)
    {
      throw new RuntimeRemoteException(localRemoteException);
    }
  }
  
  public int hashCode()
  {
    try
    {
      int i = this.ajX.hashCodeRemote();
      return i;
    }
    catch (RemoteException localRemoteException)
    {
      throw new RuntimeRemoteException(localRemoteException);
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\maps\model\IndoorLevel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */