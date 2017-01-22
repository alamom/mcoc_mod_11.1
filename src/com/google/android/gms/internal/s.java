package com.google.android.gms.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract interface s
  extends IInterface
{
  public abstract boolean a(boolean paramBoolean)
    throws RemoteException;
  
  public abstract void b(String paramString, boolean paramBoolean)
    throws RemoteException;
  
  public abstract String c(String paramString)
    throws RemoteException;
  
  public abstract String getId()
    throws RemoteException;
  
  public static abstract class a
    extends Binder
    implements s
  {
    public static s b(IBinder paramIBinder)
    {
      if (paramIBinder == null) {
        paramIBinder = null;
      }
      for (;;)
      {
        return paramIBinder;
        IInterface localIInterface = paramIBinder.queryLocalInterface("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
        if ((localIInterface != null) && ((localIInterface instanceof s))) {
          paramIBinder = (s)localIInterface;
        } else {
          paramIBinder = new a(paramIBinder);
        }
      }
    }
    
    public boolean onTransact(int paramInt1, Parcel paramParcel1, Parcel paramParcel2, int paramInt2)
      throws RemoteException
    {
      boolean bool1 = false;
      int i = 0;
      boolean bool2 = true;
      switch (paramInt1)
      {
      default: 
        bool1 = super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
      }
      for (;;)
      {
        return bool1;
        paramParcel2.writeString("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
        bool1 = bool2;
        continue;
        paramParcel1.enforceInterface("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
        paramParcel1 = getId();
        paramParcel2.writeNoException();
        paramParcel2.writeString(paramParcel1);
        bool1 = bool2;
        continue;
        paramParcel1.enforceInterface("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
        if (paramParcel1.readInt() != 0) {}
        for (bool1 = true;; bool1 = false)
        {
          bool1 = a(bool1);
          paramParcel2.writeNoException();
          paramInt1 = i;
          if (bool1) {
            paramInt1 = 1;
          }
          paramParcel2.writeInt(paramInt1);
          bool1 = bool2;
          break;
        }
        paramParcel1.enforceInterface("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
        paramParcel1 = c(paramParcel1.readString());
        paramParcel2.writeNoException();
        paramParcel2.writeString(paramParcel1);
        bool1 = bool2;
        continue;
        paramParcel1.enforceInterface("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
        String str = paramParcel1.readString();
        if (paramParcel1.readInt() != 0) {
          bool1 = true;
        }
        b(str, bool1);
        paramParcel2.writeNoException();
        bool1 = bool2;
      }
    }
    
    private static class a
      implements s
    {
      private IBinder lb;
      
      a(IBinder paramIBinder)
      {
        this.lb = paramIBinder;
      }
      
      /* Error */
      public boolean a(boolean paramBoolean)
        throws RemoteException
      {
        // Byte code:
        //   0: iconst_1
        //   1: istore_3
        //   2: invokestatic 28	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   5: astore 5
        //   7: invokestatic 28	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   10: astore 6
        //   12: aload 5
        //   14: ldc 30
        //   16: invokevirtual 34	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   19: iload_1
        //   20: ifeq +56 -> 76
        //   23: iconst_1
        //   24: istore_2
        //   25: aload 5
        //   27: iload_2
        //   28: invokevirtual 38	android/os/Parcel:writeInt	(I)V
        //   31: aload_0
        //   32: getfield 18	com/google/android/gms/internal/s$a$a:lb	Landroid/os/IBinder;
        //   35: iconst_2
        //   36: aload 5
        //   38: aload 6
        //   40: iconst_0
        //   41: invokeinterface 44 5 0
        //   46: pop
        //   47: aload 6
        //   49: invokevirtual 47	android/os/Parcel:readException	()V
        //   52: aload 6
        //   54: invokevirtual 51	android/os/Parcel:readInt	()I
        //   57: istore_2
        //   58: iload_2
        //   59: ifeq +22 -> 81
        //   62: iload_3
        //   63: istore_1
        //   64: aload 6
        //   66: invokevirtual 54	android/os/Parcel:recycle	()V
        //   69: aload 5
        //   71: invokevirtual 54	android/os/Parcel:recycle	()V
        //   74: iload_1
        //   75: ireturn
        //   76: iconst_0
        //   77: istore_2
        //   78: goto -53 -> 25
        //   81: iconst_0
        //   82: istore_1
        //   83: goto -19 -> 64
        //   86: astore 4
        //   88: aload 6
        //   90: invokevirtual 54	android/os/Parcel:recycle	()V
        //   93: aload 5
        //   95: invokevirtual 54	android/os/Parcel:recycle	()V
        //   98: aload 4
        //   100: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	101	0	this	a
        //   0	101	1	paramBoolean	boolean
        //   24	54	2	i	int
        //   1	62	3	bool	boolean
        //   86	13	4	localObject	Object
        //   5	89	5	localParcel1	Parcel
        //   10	79	6	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   12	19	86	finally
        //   25	58	86	finally
      }
      
      public IBinder asBinder()
      {
        return this.lb;
      }
      
      public void b(String paramString, boolean paramBoolean)
        throws RemoteException
      {
        int i = 0;
        Parcel localParcel2 = Parcel.obtain();
        Parcel localParcel1 = Parcel.obtain();
        try
        {
          localParcel2.writeInterfaceToken("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
          localParcel2.writeString(paramString);
          if (paramBoolean) {
            i = 1;
          }
          localParcel2.writeInt(i);
          this.lb.transact(4, localParcel2, localParcel1, 0);
          localParcel1.readException();
          return;
        }
        finally
        {
          localParcel1.recycle();
          localParcel2.recycle();
        }
      }
      
      public String c(String paramString)
        throws RemoteException
      {
        Parcel localParcel2 = Parcel.obtain();
        Parcel localParcel1 = Parcel.obtain();
        try
        {
          localParcel2.writeInterfaceToken("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
          localParcel2.writeString(paramString);
          this.lb.transact(3, localParcel2, localParcel1, 0);
          localParcel1.readException();
          paramString = localParcel1.readString();
          return paramString;
        }
        finally
        {
          localParcel1.recycle();
          localParcel2.recycle();
        }
      }
      
      public String getId()
        throws RemoteException
      {
        Parcel localParcel2 = Parcel.obtain();
        Parcel localParcel1 = Parcel.obtain();
        try
        {
          localParcel2.writeInterfaceToken("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
          this.lb.transact(1, localParcel2, localParcel1, 0);
          localParcel1.readException();
          String str = localParcel1.readString();
          return str;
        }
        finally
        {
          localParcel1.recycle();
          localParcel2.recycle();
        }
      }
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\internal\s.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */