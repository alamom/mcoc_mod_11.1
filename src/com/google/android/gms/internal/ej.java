package com.google.android.gms.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.d;
import com.google.android.gms.dynamic.d.a;

public abstract interface ej
  extends IInterface
{
  public abstract IBinder b(d paramd)
    throws RemoteException;
  
  public static abstract class a
    extends Binder
    implements ej
  {
    public static ej v(IBinder paramIBinder)
    {
      if (paramIBinder == null) {
        paramIBinder = null;
      }
      for (;;)
      {
        return paramIBinder;
        IInterface localIInterface = paramIBinder.queryLocalInterface("com.google.android.gms.ads.internal.purchase.client.IInAppPurchaseManagerCreator");
        if ((localIInterface != null) && ((localIInterface instanceof ej))) {
          paramIBinder = (ej)localIInterface;
        } else {
          paramIBinder = new a(paramIBinder);
        }
      }
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
        paramParcel2.writeString("com.google.android.gms.ads.internal.purchase.client.IInAppPurchaseManagerCreator");
        continue;
        paramParcel1.enforceInterface("com.google.android.gms.ads.internal.purchase.client.IInAppPurchaseManagerCreator");
        paramParcel1 = b(d.a.am(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        paramParcel2.writeStrongBinder(paramParcel1);
      }
    }
    
    private static class a
      implements ej
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
      public IBinder b(d paramd)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 31	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore_3
        //   4: invokestatic 31	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   7: astore_2
        //   8: aload_3
        //   9: ldc 33
        //   11: invokevirtual 37	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   14: aload_1
        //   15: ifnull +48 -> 63
        //   18: aload_1
        //   19: invokeinterface 41 1 0
        //   24: astore_1
        //   25: aload_3
        //   26: aload_1
        //   27: invokevirtual 44	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   30: aload_0
        //   31: getfield 18	com/google/android/gms/internal/ej$a$a:lb	Landroid/os/IBinder;
        //   34: iconst_1
        //   35: aload_3
        //   36: aload_2
        //   37: iconst_0
        //   38: invokeinterface 50 5 0
        //   43: pop
        //   44: aload_2
        //   45: invokevirtual 53	android/os/Parcel:readException	()V
        //   48: aload_2
        //   49: invokevirtual 56	android/os/Parcel:readStrongBinder	()Landroid/os/IBinder;
        //   52: astore_1
        //   53: aload_2
        //   54: invokevirtual 59	android/os/Parcel:recycle	()V
        //   57: aload_3
        //   58: invokevirtual 59	android/os/Parcel:recycle	()V
        //   61: aload_1
        //   62: areturn
        //   63: aconst_null
        //   64: astore_1
        //   65: goto -40 -> 25
        //   68: astore_1
        //   69: aload_2
        //   70: invokevirtual 59	android/os/Parcel:recycle	()V
        //   73: aload_3
        //   74: invokevirtual 59	android/os/Parcel:recycle	()V
        //   77: aload_1
        //   78: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	79	0	this	a
        //   0	79	1	paramd	d
        //   7	63	2	localParcel1	Parcel
        //   3	71	3	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   8	14	68	finally
        //   18	25	68	finally
        //   25	53	68	finally
      }
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\internal\ej.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */