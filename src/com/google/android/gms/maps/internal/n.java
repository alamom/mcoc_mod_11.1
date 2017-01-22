package com.google.android.gms.maps.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract interface n
  extends IInterface
{
  public abstract boolean onMyLocationButtonClick()
    throws RemoteException;
  
  public static abstract class a
    extends Binder
    implements n
  {
    public a()
    {
      attachInterface(this, "com.google.android.gms.maps.internal.IOnMyLocationButtonClickListener");
    }
    
    public static n be(IBinder paramIBinder)
    {
      if (paramIBinder == null) {
        paramIBinder = null;
      }
      for (;;)
      {
        return paramIBinder;
        IInterface localIInterface = paramIBinder.queryLocalInterface("com.google.android.gms.maps.internal.IOnMyLocationButtonClickListener");
        if ((localIInterface != null) && ((localIInterface instanceof n))) {
          paramIBinder = (n)localIInterface;
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
      boolean bool1 = true;
      switch (paramInt1)
      {
      default: 
        bool1 = super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
      case 1598968902: 
        for (;;)
        {
          return bool1;
          paramParcel2.writeString("com.google.android.gms.maps.internal.IOnMyLocationButtonClickListener");
        }
      }
      paramParcel1.enforceInterface("com.google.android.gms.maps.internal.IOnMyLocationButtonClickListener");
      boolean bool2 = onMyLocationButtonClick();
      paramParcel2.writeNoException();
      if (bool2) {}
      for (paramInt1 = 1;; paramInt1 = 0)
      {
        paramParcel2.writeInt(paramInt1);
        break;
      }
    }
    
    private static class a
      implements n
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
      
      /* Error */
      public boolean onMyLocationButtonClick()
        throws RemoteException
      {
        // Byte code:
        //   0: iconst_1
        //   1: istore_2
        //   2: invokestatic 31	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   5: astore 5
        //   7: invokestatic 31	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   10: astore 4
        //   12: aload 5
        //   14: ldc 33
        //   16: invokevirtual 37	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   19: aload_0
        //   20: getfield 18	com/google/android/gms/maps/internal/n$a$a:lb	Landroid/os/IBinder;
        //   23: iconst_1
        //   24: aload 5
        //   26: aload 4
        //   28: iconst_0
        //   29: invokeinterface 43 5 0
        //   34: pop
        //   35: aload 4
        //   37: invokevirtual 46	android/os/Parcel:readException	()V
        //   40: aload 4
        //   42: invokevirtual 50	android/os/Parcel:readInt	()I
        //   45: istore_1
        //   46: iload_1
        //   47: ifeq +15 -> 62
        //   50: aload 4
        //   52: invokevirtual 53	android/os/Parcel:recycle	()V
        //   55: aload 5
        //   57: invokevirtual 53	android/os/Parcel:recycle	()V
        //   60: iload_2
        //   61: ireturn
        //   62: iconst_0
        //   63: istore_2
        //   64: goto -14 -> 50
        //   67: astore_3
        //   68: aload 4
        //   70: invokevirtual 53	android/os/Parcel:recycle	()V
        //   73: aload 5
        //   75: invokevirtual 53	android/os/Parcel:recycle	()V
        //   78: aload_3
        //   79: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	80	0	this	a
        //   45	2	1	i	int
        //   1	63	2	bool	boolean
        //   67	12	3	localObject	Object
        //   10	59	4	localParcel1	Parcel
        //   5	69	5	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   12	46	67	finally
      }
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\maps\internal\n.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */