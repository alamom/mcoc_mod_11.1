package com.google.android.gms.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.os.RemoteException;
import com.google.android.gms.cast.ApplicationMetadata;

public abstract interface io
  extends IInterface
{
  public abstract void a(ApplicationMetadata paramApplicationMetadata, String paramString1, String paramString2, boolean paramBoolean)
    throws RemoteException;
  
  public abstract void a(String paramString, double paramDouble, boolean paramBoolean)
    throws RemoteException;
  
  public abstract void a(String paramString, long paramLong)
    throws RemoteException;
  
  public abstract void a(String paramString, long paramLong, int paramInt)
    throws RemoteException;
  
  public abstract void ac(int paramInt)
    throws RemoteException;
  
  public abstract void ad(int paramInt)
    throws RemoteException;
  
  public abstract void ae(int paramInt)
    throws RemoteException;
  
  public abstract void af(int paramInt)
    throws RemoteException;
  
  public abstract void b(ig paramig)
    throws RemoteException;
  
  public abstract void b(il paramil)
    throws RemoteException;
  
  public abstract void b(String paramString, byte[] paramArrayOfByte)
    throws RemoteException;
  
  public abstract void k(String paramString1, String paramString2)
    throws RemoteException;
  
  public abstract void onApplicationDisconnected(int paramInt)
    throws RemoteException;
  
  public static abstract class a
    extends Binder
    implements io
  {
    public a()
    {
      attachInterface(this, "com.google.android.gms.cast.internal.ICastDeviceControllerListener");
    }
    
    public IBinder asBinder()
    {
      return this;
    }
    
    public boolean onTransact(int paramInt1, Parcel paramParcel1, Parcel paramParcel2, int paramInt2)
      throws RemoteException
    {
      boolean bool3 = false;
      boolean bool1 = false;
      String str2 = null;
      Object localObject = null;
      String str1 = null;
      boolean bool2 = true;
      switch (paramInt1)
      {
      default: 
        bool1 = super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
      }
      for (;;)
      {
        return bool1;
        paramParcel2.writeString("com.google.android.gms.cast.internal.ICastDeviceControllerListener");
        bool1 = bool2;
        continue;
        paramParcel1.enforceInterface("com.google.android.gms.cast.internal.ICastDeviceControllerListener");
        ac(paramParcel1.readInt());
        bool1 = bool2;
        continue;
        paramParcel1.enforceInterface("com.google.android.gms.cast.internal.ICastDeviceControllerListener");
        paramParcel2 = str1;
        if (paramParcel1.readInt() != 0) {
          paramParcel2 = (ApplicationMetadata)ApplicationMetadata.CREATOR.createFromParcel(paramParcel1);
        }
        str1 = paramParcel1.readString();
        str2 = paramParcel1.readString();
        if (paramParcel1.readInt() != 0) {
          bool1 = true;
        }
        a(paramParcel2, str1, str2, bool1);
        bool1 = bool2;
        continue;
        paramParcel1.enforceInterface("com.google.android.gms.cast.internal.ICastDeviceControllerListener");
        ad(paramParcel1.readInt());
        bool1 = bool2;
        continue;
        paramParcel1.enforceInterface("com.google.android.gms.cast.internal.ICastDeviceControllerListener");
        paramParcel2 = paramParcel1.readString();
        double d = paramParcel1.readDouble();
        bool1 = bool3;
        if (paramParcel1.readInt() != 0) {
          bool1 = true;
        }
        a(paramParcel2, d, bool1);
        bool1 = bool2;
        continue;
        paramParcel1.enforceInterface("com.google.android.gms.cast.internal.ICastDeviceControllerListener");
        k(paramParcel1.readString(), paramParcel1.readString());
        bool1 = bool2;
        continue;
        paramParcel1.enforceInterface("com.google.android.gms.cast.internal.ICastDeviceControllerListener");
        b(paramParcel1.readString(), paramParcel1.createByteArray());
        bool1 = bool2;
        continue;
        paramParcel1.enforceInterface("com.google.android.gms.cast.internal.ICastDeviceControllerListener");
        af(paramParcel1.readInt());
        bool1 = bool2;
        continue;
        paramParcel1.enforceInterface("com.google.android.gms.cast.internal.ICastDeviceControllerListener");
        ae(paramParcel1.readInt());
        bool1 = bool2;
        continue;
        paramParcel1.enforceInterface("com.google.android.gms.cast.internal.ICastDeviceControllerListener");
        onApplicationDisconnected(paramParcel1.readInt());
        bool1 = bool2;
        continue;
        paramParcel1.enforceInterface("com.google.android.gms.cast.internal.ICastDeviceControllerListener");
        a(paramParcel1.readString(), paramParcel1.readLong(), paramParcel1.readInt());
        bool1 = bool2;
        continue;
        paramParcel1.enforceInterface("com.google.android.gms.cast.internal.ICastDeviceControllerListener");
        a(paramParcel1.readString(), paramParcel1.readLong());
        bool1 = bool2;
        continue;
        paramParcel1.enforceInterface("com.google.android.gms.cast.internal.ICastDeviceControllerListener");
        paramParcel2 = str2;
        if (paramParcel1.readInt() != 0) {
          paramParcel2 = (ig)ig.CREATOR.createFromParcel(paramParcel1);
        }
        b(paramParcel2);
        bool1 = bool2;
        continue;
        paramParcel1.enforceInterface("com.google.android.gms.cast.internal.ICastDeviceControllerListener");
        paramParcel2 = (Parcel)localObject;
        if (paramParcel1.readInt() != 0) {
          paramParcel2 = (il)il.CREATOR.createFromParcel(paramParcel1);
        }
        b(paramParcel2);
        bool1 = bool2;
      }
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\internal\io.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */