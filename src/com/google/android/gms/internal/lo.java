package com.google.android.gms.internal;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.os.RemoteException;
import com.google.android.gms.identity.intents.UserAddressRequest;

public abstract interface lo
  extends IInterface
{
  public abstract void a(ln paramln, UserAddressRequest paramUserAddressRequest, Bundle paramBundle)
    throws RemoteException;
  
  public static abstract class a
    extends Binder
    implements lo
  {
    public static lo aH(IBinder paramIBinder)
    {
      if (paramIBinder == null) {
        paramIBinder = null;
      }
      for (;;)
      {
        return paramIBinder;
        IInterface localIInterface = paramIBinder.queryLocalInterface("com.google.android.gms.identity.intents.internal.IAddressService");
        if ((localIInterface != null) && ((localIInterface instanceof lo))) {
          paramIBinder = (lo)localIInterface;
        } else {
          paramIBinder = new a(paramIBinder);
        }
      }
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
          paramParcel2.writeString("com.google.android.gms.identity.intents.internal.IAddressService");
        }
      }
      paramParcel1.enforceInterface("com.google.android.gms.identity.intents.internal.IAddressService");
      ln localln = ln.a.aG(paramParcel1.readStrongBinder());
      UserAddressRequest localUserAddressRequest;
      if (paramParcel1.readInt() != 0)
      {
        localUserAddressRequest = (UserAddressRequest)UserAddressRequest.CREATOR.createFromParcel(paramParcel1);
        label90:
        if (paramParcel1.readInt() == 0) {
          break label135;
        }
      }
      label135:
      for (paramParcel1 = (Bundle)Bundle.CREATOR.createFromParcel(paramParcel1);; paramParcel1 = null)
      {
        a(localln, localUserAddressRequest, paramParcel1);
        paramParcel2.writeNoException();
        bool = true;
        break;
        localUserAddressRequest = null;
        break label90;
      }
    }
    
    private static class a
      implements lo
    {
      private IBinder lb;
      
      a(IBinder paramIBinder)
      {
        this.lb = paramIBinder;
      }
      
      public void a(ln paramln, UserAddressRequest paramUserAddressRequest, Bundle paramBundle)
        throws RemoteException
      {
        Parcel localParcel2 = Parcel.obtain();
        Parcel localParcel1 = Parcel.obtain();
        label127:
        for (;;)
        {
          try
          {
            localParcel2.writeInterfaceToken("com.google.android.gms.identity.intents.internal.IAddressService");
            if (paramln != null)
            {
              paramln = paramln.asBinder();
              localParcel2.writeStrongBinder(paramln);
              if (paramUserAddressRequest != null)
              {
                localParcel2.writeInt(1);
                paramUserAddressRequest.writeToParcel(localParcel2, 0);
                if (paramBundle == null) {
                  break label127;
                }
                localParcel2.writeInt(1);
                paramBundle.writeToParcel(localParcel2, 0);
                this.lb.transact(2, localParcel2, localParcel1, 0);
                localParcel1.readException();
              }
            }
            else
            {
              paramln = null;
              continue;
            }
            localParcel2.writeInt(0);
            continue;
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


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\internal\lo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */