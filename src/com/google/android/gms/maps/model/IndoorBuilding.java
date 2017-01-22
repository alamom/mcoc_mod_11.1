package com.google.android.gms.maps.model;

import android.os.IBinder;
import android.os.RemoteException;
import com.google.android.gms.common.internal.o;
import com.google.android.gms.maps.model.internal.d;
import com.google.android.gms.maps.model.internal.e.a;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class IndoorBuilding
{
  private final d ajW;
  
  public IndoorBuilding(d paramd)
  {
    this.ajW = ((d)o.i(paramd));
  }
  
  public boolean equals(Object paramObject)
  {
    boolean bool;
    if (!(paramObject instanceof IndoorBuilding)) {
      bool = false;
    }
    for (;;)
    {
      return bool;
      try
      {
        bool = this.ajW.b(((IndoorBuilding)paramObject).ajW);
      }
      catch (RemoteException paramObject)
      {
        throw new RuntimeRemoteException((RemoteException)paramObject);
      }
    }
  }
  
  public int getActiveLevelIndex()
  {
    try
    {
      int i = this.ajW.getActiveLevelIndex();
      return i;
    }
    catch (RemoteException localRemoteException)
    {
      throw new RuntimeRemoteException(localRemoteException);
    }
  }
  
  public int getDefaultLevelIndex()
  {
    try
    {
      int i = this.ajW.getActiveLevelIndex();
      return i;
    }
    catch (RemoteException localRemoteException)
    {
      throw new RuntimeRemoteException(localRemoteException);
    }
  }
  
  public List<IndoorLevel> getLevels()
  {
    try
    {
      Object localObject = this.ajW.getLevels();
      ArrayList localArrayList = new java/util/ArrayList;
      localArrayList.<init>(((List)localObject).size());
      Iterator localIterator = ((List)localObject).iterator();
      while (localIterator.hasNext())
      {
        localObject = (IBinder)localIterator.next();
        IndoorLevel localIndoorLevel = new com/google/android/gms/maps/model/IndoorLevel;
        localIndoorLevel.<init>(e.a.bt((IBinder)localObject));
        localArrayList.add(localIndoorLevel);
      }
      return localRemoteException;
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
      int i = this.ajW.hashCodeRemote();
      return i;
    }
    catch (RemoteException localRemoteException)
    {
      throw new RuntimeRemoteException(localRemoteException);
    }
  }
  
  public boolean isUnderground()
  {
    try
    {
      boolean bool = this.ajW.isUnderground();
      return bool;
    }
    catch (RemoteException localRemoteException)
    {
      throw new RuntimeRemoteException(localRemoteException);
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\maps\model\IndoorBuilding.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */