package com.google.android.gms.internal;

import android.content.Intent;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.os.RemoteException;

public abstract interface na
  extends IInterface
{
  public abstract void a(int paramInt1, Bundle paramBundle, int paramInt2, Intent paramIntent)
    throws RemoteException;
  
  public static abstract class a
    extends Binder
    implements na
  {
    public a()
    {
      attachInterface(this, "com.google.android.gms.panorama.internal.IPanoramaCallbacks");
    }
    
    public static na bz(IBinder paramIBinder)
    {
      if (paramIBinder == null) {
        paramIBinder = null;
      }
      for (;;)
      {
        return paramIBinder;
        IInterface localIInterface = paramIBinder.queryLocalInterface("com.google.android.gms.panorama.internal.IPanoramaCallbacks");
        if ((localIInterface != null) && ((localIInterface instanceof na))) {
          paramIBinder = (na)localIInterface;
        } else {
          paramIBinder = new a(paramIBinder);
        }
      }
    }
    
    public IBinder asBinder()
    {
      return this;
    }
    
    public boolean onTransact(int paramInt1, Parcel paramParcel1, Parcel paramParcel2, int paramInt2)
      throws RemoteException
    {
      boolean bool;
      switch (paramInt1)
      {
      default: 
      case 1598968902: 
        for (bool = super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);; bool = true)
        {
          return bool;
          paramParcel2.writeString("com.google.android.gms.panorama.internal.IPanoramaCallbacks");
        }
      }
      paramParcel1.enforceInterface("com.google.android.gms.panorama.internal.IPanoramaCallbacks");
      paramInt1 = paramParcel1.readInt();
      Bundle localBundle;
      if (paramParcel1.readInt() != 0)
      {
        localBundle = (Bundle)Bundle.CREATOR.createFromParcel(paramParcel1);
        label86:
        paramInt2 = paramParcel1.readInt();
        if (paramParcel1.readInt() == 0) {
          break label138;
        }
      }
      label138:
      for (paramParcel1 = (Intent)Intent.CREATOR.createFromParcel(paramParcel1);; paramParcel1 = null)
      {
        a(paramInt1, localBundle, paramInt2, paramParcel1);
        paramParcel2.writeNoException();
        bool = true;
        break;
        localBundle = null;
        break label86;
      }
    }
    
    private static class a
      implements na
    {
      private IBinder lb;
      
      a(IBinder paramIBinder)
      {
        this.lb = paramIBinder;
      }
      
      public void a(int paramInt1, Bundle paramBundle, int paramInt2, Intent paramIntent)
        throws RemoteException
      {
        Parcel localParcel2 = Parcel.obtain();
        Parcel localParcel1 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel2.writeInterfaceToken("com.google.android.gms.panorama.internal.IPanoramaCallbacks");
            localParcel2.writeInt(paramInt1);
            if (paramBundle != null)
            {
              localParcel2.writeInt(1);
              paramBundle.writeToParcel(localParcel2, 0);
              localParcel2.writeInt(paramInt2);
              if (paramIntent != null)
              {
                localParcel2.writeInt(1);
                paramIntent.writeToParcel(localParcel2, 0);
                this.lb.transact(1, localParcel2, localParcel1, 0);
                localParcel1.readException();
              }
            }
            else
            {
              localParcel2.writeInt(0);
              continue;
            }
            localParcel2.writeInt(0);
          }
          finally
          {
            localParcel1.recycle();
            localParcel2.recycle();
          }
        }
      }
      
      public IBinder asBinder()
      {
        return this.lb;
      }
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\internal\na.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */