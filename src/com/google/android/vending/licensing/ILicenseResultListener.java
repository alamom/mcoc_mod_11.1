package com.google.android.vending.licensing;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract interface ILicenseResultListener
  extends IInterface
{
  public abstract void verifyLicense(int paramInt, String paramString1, String paramString2)
    throws RemoteException;
  
  public static abstract class Stub
    extends Binder
    implements ILicenseResultListener
  {
    private static final String DESCRIPTOR = "com.android.vending.licensing.ILicenseResultListener";
    static final int TRANSACTION_verifyLicense = 1;
    
    public Stub()
    {
      attachInterface(this, "com.android.vending.licensing.ILicenseResultListener");
    }
    
    public static ILicenseResultListener asInterface(IBinder paramIBinder)
    {
      if (paramIBinder == null) {
        paramIBinder = null;
      }
      for (;;)
      {
        return paramIBinder;
        IInterface localIInterface = paramIBinder.queryLocalInterface("com.android.vending.licensing.ILicenseResultListener");
        if ((localIInterface != null) && ((localIInterface instanceof ILicenseResultListener))) {
          paramIBinder = (ILicenseResultListener)localIInterface;
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
        paramParcel2.writeString("com.android.vending.licensing.ILicenseResultListener");
        continue;
        paramParcel1.enforceInterface("com.android.vending.licensing.ILicenseResultListener");
        verifyLicense(paramParcel1.readInt(), paramParcel1.readString(), paramParcel1.readString());
      }
    }
    
    private static class Proxy
      implements ILicenseResultListener
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
      
      public String getInterfaceDescriptor()
      {
        return "com.android.vending.licensing.ILicenseResultListener";
      }
      
      public void verifyLicense(int paramInt, String paramString1, String paramString2)
        throws RemoteException
      {
        Parcel localParcel = Parcel.obtain();
        try
        {
          localParcel.writeInterfaceToken("com.android.vending.licensing.ILicenseResultListener");
          localParcel.writeInt(paramInt);
          localParcel.writeString(paramString1);
          localParcel.writeString(paramString2);
          this.mRemote.transact(1, localParcel, null, 1);
          return;
        }
        finally
        {
          localParcel.recycle();
        }
      }
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\vending\licensing\ILicenseResultListener.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */