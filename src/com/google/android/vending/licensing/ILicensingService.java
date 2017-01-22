package com.google.android.vending.licensing;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract interface ILicensingService
  extends IInterface
{
  public abstract void checkLicense(long paramLong, String paramString, ILicenseResultListener paramILicenseResultListener)
    throws RemoteException;
  
  public static abstract class Stub
    extends Binder
    implements ILicensingService
  {
    private static final String DESCRIPTOR = "com.android.vending.licensing.ILicensingService";
    static final int TRANSACTION_checkLicense = 1;
    
    public Stub()
    {
      attachInterface(this, "com.android.vending.licensing.ILicensingService");
    }
    
    public static ILicensingService asInterface(IBinder paramIBinder)
    {
      if (paramIBinder == null) {
        paramIBinder = null;
      }
      for (;;)
      {
        return paramIBinder;
        IInterface localIInterface = paramIBinder.queryLocalInterface("com.android.vending.licensing.ILicensingService");
        if ((localIInterface != null) && ((localIInterface instanceof ILicensingService))) {
          paramIBinder = (ILicensingService)localIInterface;
        } else {
          paramIBinder = new Proxy(paramIBinder);
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
      boolean bool = true;
      switch (paramInt1)
      {
      default: 
        bool = super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
      }
      for (;;)
      {
        return bool;
        paramParcel2.writeString("com.android.vending.licensing.ILicensingService");
        continue;
        paramParcel1.enforceInterface("com.android.vending.licensing.ILicensingService");
        checkLicense(paramParcel1.readLong(), paramParcel1.readString(), ILicenseResultListener.Stub.asInterface(paramParcel1.readStrongBinder()));
      }
    }
    
    private static class Proxy
      implements ILicensingService
    {
      private IBinder mRemote;
      
      Proxy(IBinder paramIBinder)
      {
        this.mRemote = paramIBinder;
      }
      
      public IBinder asBinder()
      {
        return this.mRemote;
      }
      
      public void checkLicense(long paramLong, String paramString, ILicenseResultListener paramILicenseResultListener)
        throws RemoteException
      {
        Object localObject = null;
        Parcel localParcel = Parcel.obtain();
        try
        {
          localParcel.writeInterfaceToken("com.android.vending.licensing.ILicensingService");
          localParcel.writeLong(paramLong);
          localParcel.writeString(paramString);
          paramString = (String)localObject;
          if (paramILicenseResultListener != null) {
            paramString = paramILicenseResultListener.asBinder();
          }
          localParcel.writeStrongBinder(paramString);
          this.mRemote.transact(1, localParcel, null, 1);
          return;
        }
        finally
        {
          localParcel.recycle();
        }
      }
      
      public String getInterfaceDescriptor()
      {
        return "com.android.vending.licensing.ILicensingService";
      }
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\vending\licensing\ILicensingService.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */