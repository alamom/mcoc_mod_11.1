package com.google.android.gms.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.d;

public abstract interface br
  extends IInterface
{
  public abstract void as()
    throws RemoteException;
  
  public abstract String bt()
    throws RemoteException;
  
  public abstract d bu()
    throws RemoteException;
  
  public abstract d bv()
    throws RemoteException;
  
  public abstract String bw()
    throws RemoteException;
  
  public abstract double bx()
    throws RemoteException;
  
  public abstract String by()
    throws RemoteException;
  
  public abstract String bz()
    throws RemoteException;
  
  public abstract String getBody()
    throws RemoteException;
  
  public abstract void i(int paramInt)
    throws RemoteException;
  
  public static abstract class a
    extends Binder
    implements br
  {
    public a()
    {
      attachInterface(this, "com.google.android.gms.ads.internal.formats.client.INativeAppInstallAd");
    }
    
    public IBinder asBinder()
    {
      return this;
    }
    
    public boolean onTransact(int paramInt1, Parcel paramParcel1, Parcel paramParcel2, int paramInt2)
      throws RemoteException
    {
      d locald2 = null;
      d locald1 = null;
      boolean bool;
      switch (paramInt1)
      {
      default: 
        bool = super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
      }
      for (;;)
      {
        return bool;
        paramParcel2.writeString("com.google.android.gms.ads.internal.formats.client.INativeAppInstallAd");
        bool = true;
        continue;
        paramParcel1.enforceInterface("com.google.android.gms.ads.internal.formats.client.INativeAppInstallAd");
        i(paramParcel1.readInt());
        paramParcel2.writeNoException();
        bool = true;
        continue;
        paramParcel1.enforceInterface("com.google.android.gms.ads.internal.formats.client.INativeAppInstallAd");
        as();
        paramParcel2.writeNoException();
        bool = true;
        continue;
        paramParcel1.enforceInterface("com.google.android.gms.ads.internal.formats.client.INativeAppInstallAd");
        paramParcel1 = bt();
        paramParcel2.writeNoException();
        paramParcel2.writeString(paramParcel1);
        bool = true;
        continue;
        paramParcel1.enforceInterface("com.google.android.gms.ads.internal.formats.client.INativeAppInstallAd");
        locald2 = bu();
        paramParcel2.writeNoException();
        paramParcel1 = locald1;
        if (locald2 != null) {
          paramParcel1 = locald2.asBinder();
        }
        paramParcel2.writeStrongBinder(paramParcel1);
        bool = true;
        continue;
        paramParcel1.enforceInterface("com.google.android.gms.ads.internal.formats.client.INativeAppInstallAd");
        paramParcel1 = getBody();
        paramParcel2.writeNoException();
        paramParcel2.writeString(paramParcel1);
        bool = true;
        continue;
        paramParcel1.enforceInterface("com.google.android.gms.ads.internal.formats.client.INativeAppInstallAd");
        locald1 = bv();
        paramParcel2.writeNoException();
        paramParcel1 = locald2;
        if (locald1 != null) {
          paramParcel1 = locald1.asBinder();
        }
        paramParcel2.writeStrongBinder(paramParcel1);
        bool = true;
        continue;
        paramParcel1.enforceInterface("com.google.android.gms.ads.internal.formats.client.INativeAppInstallAd");
        paramParcel1 = bw();
        paramParcel2.writeNoException();
        paramParcel2.writeString(paramParcel1);
        bool = true;
        continue;
        paramParcel1.enforceInterface("com.google.android.gms.ads.internal.formats.client.INativeAppInstallAd");
        double d = bx();
        paramParcel2.writeNoException();
        paramParcel2.writeDouble(d);
        bool = true;
        continue;
        paramParcel1.enforceInterface("com.google.android.gms.ads.internal.formats.client.INativeAppInstallAd");
        paramParcel1 = by();
        paramParcel2.writeNoException();
        paramParcel2.writeString(paramParcel1);
        bool = true;
        continue;
        paramParcel1.enforceInterface("com.google.android.gms.ads.internal.formats.client.INativeAppInstallAd");
        paramParcel1 = bz();
        paramParcel2.writeNoException();
        paramParcel2.writeString(paramParcel1);
        bool = true;
      }
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\internal\br.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */