package com.google.android.gms.maps.model.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract interface e
  extends IInterface
{
  public abstract boolean a(e parame)
    throws RemoteException;
  
  public abstract void activate()
    throws RemoteException;
  
  public abstract String getName()
    throws RemoteException;
  
  public abstract String getShortName()
    throws RemoteException;
  
  public abstract int hashCodeRemote()
    throws RemoteException;
  
  public static abstract class a
    extends Binder
    implements e
  {
    public static e bt(IBinder paramIBinder)
    {
      if (paramIBinder == null) {
        paramIBinder = null;
      }
      for (;;)
      {
        return paramIBinder;
        IInterface localIInterface = paramIBinder.queryLocalInterface("com.google.android.gms.maps.model.internal.IIndoorLevelDelegate");
        if ((localIInterface != null) && ((localIInterface instanceof e))) {
          paramIBinder = (e)localIInterface;
        } else {
          paramIBinder = new a(paramIBinder);
        }
      }
    }
    
    public boolean onTransact(int paramInt1, Parcel paramParcel1, Parcel paramParcel2, int paramInt2)
      throws RemoteException
    {
      boolean bool1 = true;
      switch (paramInt1)
      {
      default: 
        bool1 = super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
      }
      for (;;)
      {
        return bool1;
        paramParcel2.writeString("com.google.android.gms.maps.model.internal.IIndoorLevelDelegate");
        continue;
        paramParcel1.enforceInterface("com.google.android.gms.maps.model.internal.IIndoorLevelDelegate");
        paramParcel1 = getName();
        paramParcel2.writeNoException();
        paramParcel2.writeString(paramParcel1);
        continue;
        paramParcel1.enforceInterface("com.google.android.gms.maps.model.internal.IIndoorLevelDelegate");
        paramParcel1 = getShortName();
        paramParcel2.writeNoException();
        paramParcel2.writeString(paramParcel1);
        continue;
        paramParcel1.enforceInterface("com.google.android.gms.maps.model.internal.IIndoorLevelDelegate");
        activate();
        paramParcel2.writeNoException();
        continue;
        paramParcel1.enforceInterface("com.google.android.gms.maps.model.internal.IIndoorLevelDelegate");
        boolean bool2 = a(bt(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        if (bool2) {}
        for (paramInt1 = 1;; paramInt1 = 0)
        {
          paramParcel2.writeInt(paramInt1);
          break;
        }
        paramParcel1.enforceInterface("com.google.android.gms.maps.model.internal.IIndoorLevelDelegate");
        paramInt1 = hashCodeRemote();
        paramParcel2.writeNoException();
        paramParcel2.writeInt(paramInt1);
      }
    }
    
    private static class a
      implements e
    {
      private IBinder lb;
      
      a(IBinder paramIBinder)
      {
        this.lb = paramIBinder;
      }
      
      /* Error */
      public boolean a(e parame)
        throws RemoteException
      {
        // Byte code:
        //   0: iconst_0
        //   1: istore_3
        //   2: invokestatic 28	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   5: astore 5
        //   7: invokestatic 28	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   10: astore 4
        //   12: aload 5
        //   14: ldc 30
        //   16: invokevirtual 34	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   19: aload_1
        //   20: ifnull +61 -> 81
        //   23: aload_1
        //   24: invokeinterface 38 1 0
        //   29: astore_1
        //   30: aload 5
        //   32: aload_1
        //   33: invokevirtual 41	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   36: aload_0
        //   37: getfield 18	com/google/android/gms/maps/model/internal/e$a$a:lb	Landroid/os/IBinder;
        //   40: iconst_4
        //   41: aload 5
        //   43: aload 4
        //   45: iconst_0
        //   46: invokeinterface 47 5 0
        //   51: pop
        //   52: aload 4
        //   54: invokevirtual 50	android/os/Parcel:readException	()V
        //   57: aload 4
        //   59: invokevirtual 54	android/os/Parcel:readInt	()I
        //   62: istore_2
        //   63: iload_2
        //   64: ifeq +5 -> 69
        //   67: iconst_1
        //   68: istore_3
        //   69: aload 4
        //   71: invokevirtual 57	android/os/Parcel:recycle	()V
        //   74: aload 5
        //   76: invokevirtual 57	android/os/Parcel:recycle	()V
        //   79: iload_3
        //   80: ireturn
        //   81: aconst_null
        //   82: astore_1
        //   83: goto -53 -> 30
        //   86: astore_1
        //   87: aload 4
        //   89: invokevirtual 57	android/os/Parcel:recycle	()V
        //   92: aload 5
        //   94: invokevirtual 57	android/os/Parcel:recycle	()V
        //   97: aload_1
        //   98: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	99	0	this	a
        //   0	99	1	parame	e
        //   62	2	2	i	int
        //   1	79	3	bool	boolean
        //   10	78	4	localParcel1	Parcel
        //   5	88	5	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   12	19	86	finally
        //   23	30	86	finally
        //   30	63	86	finally
      }
      
      public void activate()
        throws RemoteException
      {
        Parcel localParcel2 = Parcel.obtain();
        Parcel localParcel1 = Parcel.obtain();
        try
        {
          localParcel2.writeInterfaceToken("com.google.android.gms.maps.model.internal.IIndoorLevelDelegate");
          this.lb.transact(3, localParcel2, localParcel1, 0);
          localParcel1.readException();
          return;
        }
        finally
        {
          localParcel1.recycle();
          localParcel2.recycle();
        }
      }
      
      public IBinder asBinder()
      {
        return this.lb;
      }
      
      public String getName()
        throws RemoteException
      {
        Parcel localParcel2 = Parcel.obtain();
        Parcel localParcel1 = Parcel.obtain();
        try
        {
          localParcel2.writeInterfaceToken("com.google.android.gms.maps.model.internal.IIndoorLevelDelegate");
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
      
      public String getShortName()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.maps.model.internal.IIndoorLevelDelegate");
          this.lb.transact(2, localParcel1, localParcel2, 0);
          localParcel2.readException();
          String str = localParcel2.readString();
          return str;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
      
      public int hashCodeRemote()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.maps.model.internal.IIndoorLevelDelegate");
          this.lb.transact(5, localParcel1, localParcel2, 0);
          localParcel2.readException();
          int i = localParcel2.readInt();
          return i;
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


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\maps\model\internal\e.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */