package com.google.android.gms.drive.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract interface ae
  extends IInterface
{
  public abstract void L(boolean paramBoolean)
    throws RemoteException;
  
  public static abstract class a
    extends Binder
    implements ae
  {
    public static ae X(IBinder paramIBinder)
    {
      if (paramIBinder == null) {
        paramIBinder = null;
      }
      for (;;)
      {
        return paramIBinder;
        IInterface localIInterface = paramIBinder.queryLocalInterface("com.google.android.gms.drive.internal.IEventReleaseCallback");
        if ((localIInterface != null) && ((localIInterface instanceof ae))) {
          paramIBinder = (ae)localIInterface;
        } else {
          paramIBinder = new a(paramIBinder);
        }
      }
    }
    
    public boolean onTransact(int paramInt1, Parcel paramParcel1, Parcel paramParcel2, int paramInt2)
      throws RemoteException
    {
      boolean bool2 = true;
      switch (paramInt1)
      {
      default: 
      case 1598968902: 
        for (bool1 = super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);; bool1 = bool2)
        {
          return bool1;
          paramParcel2.writeString("com.google.android.gms.drive.internal.IEventReleaseCallback");
        }
      }
      paramParcel1.enforceInterface("com.google.android.gms.drive.internal.IEventReleaseCallback");
      if (paramParcel1.readInt() != 0) {}
      for (boolean bool1 = true;; bool1 = false)
      {
        L(bool1);
        bool1 = bool2;
        break;
      }
    }
    
    private static class a
      implements ae
    {
      private IBinder lb;
      
      a(IBinder paramIBinder)
      {
        this.lb = paramIBinder;
      }
      
      /* Error */
      public void L(boolean paramBoolean)
        throws RemoteException
      {
        // Byte code:
        //   0: iconst_1
        //   1: istore_2
        //   2: invokestatic 29	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   5: astore_3
        //   6: aload_3
        //   7: ldc 31
        //   9: invokevirtual 35	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   12: iload_1
        //   13: ifeq +27 -> 40
        //   16: aload_3
        //   17: iload_2
        //   18: invokevirtual 39	android/os/Parcel:writeInt	(I)V
        //   21: aload_0
        //   22: getfield 18	com/google/android/gms/drive/internal/ae$a$a:lb	Landroid/os/IBinder;
        //   25: iconst_1
        //   26: aload_3
        //   27: aconst_null
        //   28: iconst_1
        //   29: invokeinterface 45 5 0
        //   34: pop
        //   35: aload_3
        //   36: invokevirtual 48	android/os/Parcel:recycle	()V
        //   39: return
        //   40: iconst_0
        //   41: istore_2
        //   42: goto -26 -> 16
        //   45: astore 4
        //   47: aload_3
        //   48: invokevirtual 48	android/os/Parcel:recycle	()V
        //   51: aload 4
        //   53: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	54	0	this	a
        //   0	54	1	paramBoolean	boolean
        //   1	41	2	i	int
        //   5	43	3	localParcel	Parcel
        //   45	7	4	localObject	Object
        // Exception table:
        //   from	to	target	type
        //   6	12	45	finally
        //   16	35	45	finally
      }
      
      public IBinder asBinder()
      {
        return this.lb;
      }
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\drive\internal\ae.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */