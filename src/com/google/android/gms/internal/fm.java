package com.google.android.gms.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract interface fm
  extends IInterface
{
  public abstract fk b(fi paramfi)
    throws RemoteException;
  
  public static abstract class a
    extends Binder
    implements fm
  {
    public a()
    {
      attachInterface(this, "com.google.android.gms.ads.internal.request.IAdRequestService");
    }
    
    public static fm D(IBinder paramIBinder)
    {
      if (paramIBinder == null) {
        paramIBinder = null;
      }
      for (;;)
      {
        return paramIBinder;
        IInterface localIInterface = paramIBinder.queryLocalInterface("com.google.android.gms.ads.internal.request.IAdRequestService");
        if ((localIInterface != null) && ((localIInterface instanceof fm))) {
          paramIBinder = (fm)localIInterface;
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
          paramParcel2.writeString("com.google.android.gms.ads.internal.request.IAdRequestService");
        }
      }
      paramParcel1.enforceInterface("com.google.android.gms.ads.internal.request.IAdRequestService");
      if (paramParcel1.readInt() != 0)
      {
        paramParcel1 = fi.CREATOR.h(paramParcel1);
        label75:
        paramParcel1 = b(paramParcel1);
        paramParcel2.writeNoException();
        if (paramParcel1 == null) {
          break label111;
        }
        paramParcel2.writeInt(1);
        paramParcel1.writeToParcel(paramParcel2, 1);
      }
      for (;;)
      {
        bool = true;
        break;
        paramParcel1 = null;
        break label75;
        label111:
        paramParcel2.writeInt(0);
      }
    }
    
    private static class a
      implements fm
    {
      private IBinder lb;
      
      a(IBinder paramIBinder)
      {
        this.lb = paramIBinder;
      }
      
      public IBinder asBinder()
      {
        return this.lb;
      }
      
      public fk b(fi paramfi)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.ads.internal.request.IAdRequestService");
            if (paramfi != null)
            {
              localParcel1.writeInt(1);
              paramfi.writeToParcel(localParcel1, 0);
              this.lb.transact(1, localParcel1, localParcel2, 0);
              localParcel2.readException();
              if (localParcel2.readInt() != 0)
              {
                paramfi = fk.CREATOR.i(localParcel2);
                return paramfi;
              }
            }
            else
            {
              localParcel1.writeInt(0);
              continue;
            }
            paramfi = null;
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
        }
      }
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\internal\fm.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */