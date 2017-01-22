package com.google.android.gms.drive.realtime.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.StatusCreator;

public abstract interface c
  extends IInterface
{
  public abstract void M(boolean paramBoolean)
    throws RemoteException;
  
  public abstract void o(Status paramStatus)
    throws RemoteException;
  
  public static abstract class a
    extends Binder
    implements c
  {
    public static c Y(IBinder paramIBinder)
    {
      if (paramIBinder == null) {
        paramIBinder = null;
      }
      for (;;)
      {
        return paramIBinder;
        IInterface localIInterface = paramIBinder.queryLocalInterface("com.google.android.gms.drive.realtime.internal.IBooleanCallback");
        if ((localIInterface != null) && ((localIInterface instanceof c))) {
          paramIBinder = (c)localIInterface;
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
      boolean bool2 = true;
      boolean bool1;
      switch (paramInt1)
      {
      default: 
      case 1598968902: 
        for (bool1 = super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);; bool1 = bool2)
        {
          return bool1;
          paramParcel2.writeString("com.google.android.gms.drive.realtime.internal.IBooleanCallback");
        }
      case 1: 
        paramParcel1.enforceInterface("com.google.android.gms.drive.realtime.internal.IBooleanCallback");
        if (paramParcel1.readInt() != 0) {}
        for (bool1 = true;; bool1 = false)
        {
          M(bool1);
          paramParcel2.writeNoException();
          bool1 = bool2;
          break;
        }
      }
      paramParcel1.enforceInterface("com.google.android.gms.drive.realtime.internal.IBooleanCallback");
      if (paramParcel1.readInt() != 0) {}
      for (paramParcel1 = Status.CREATOR.createFromParcel(paramParcel1);; paramParcel1 = null)
      {
        o(paramParcel1);
        paramParcel2.writeNoException();
        bool1 = bool2;
        break;
      }
    }
    
    private static class a
      implements c
    {
      private IBinder lb;
      
      a(IBinder paramIBinder)
      {
        this.lb = paramIBinder;
      }
      
      /* Error */
      public void M(boolean paramBoolean)
        throws RemoteException
      {
        // Byte code:
        //   0: iconst_1
        //   1: istore_2
        //   2: invokestatic 29	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   5: astore 5
        //   7: invokestatic 29	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   10: astore 4
        //   12: aload 5
        //   14: ldc 31
        //   16: invokevirtual 35	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   19: iload_1
        //   20: ifeq +41 -> 61
        //   23: aload 5
        //   25: iload_2
        //   26: invokevirtual 39	android/os/Parcel:writeInt	(I)V
        //   29: aload_0
        //   30: getfield 18	com/google/android/gms/drive/realtime/internal/c$a$a:lb	Landroid/os/IBinder;
        //   33: iconst_1
        //   34: aload 5
        //   36: aload 4
        //   38: iconst_0
        //   39: invokeinterface 45 5 0
        //   44: pop
        //   45: aload 4
        //   47: invokevirtual 48	android/os/Parcel:readException	()V
        //   50: aload 4
        //   52: invokevirtual 51	android/os/Parcel:recycle	()V
        //   55: aload 5
        //   57: invokevirtual 51	android/os/Parcel:recycle	()V
        //   60: return
        //   61: iconst_0
        //   62: istore_2
        //   63: goto -40 -> 23
        //   66: astore_3
        //   67: aload 4
        //   69: invokevirtual 51	android/os/Parcel:recycle	()V
        //   72: aload 5
        //   74: invokevirtual 51	android/os/Parcel:recycle	()V
        //   77: aload_3
        //   78: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	79	0	this	a
        //   0	79	1	paramBoolean	boolean
        //   1	62	2	i	int
        //   66	12	3	localObject	Object
        //   10	58	4	localParcel1	Parcel
        //   5	68	5	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   12	19	66	finally
        //   23	50	66	finally
      }
      
      public IBinder asBinder()
      {
        return this.lb;
      }
      
      /* Error */
      public void o(Status paramStatus)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 29	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore_2
        //   4: invokestatic 29	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   7: astore_3
        //   8: aload_2
        //   9: ldc 31
        //   11: invokevirtual 35	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   14: aload_1
        //   15: ifnull +41 -> 56
        //   18: aload_2
        //   19: iconst_1
        //   20: invokevirtual 39	android/os/Parcel:writeInt	(I)V
        //   23: aload_1
        //   24: aload_2
        //   25: iconst_0
        //   26: invokevirtual 62	com/google/android/gms/common/api/Status:writeToParcel	(Landroid/os/Parcel;I)V
        //   29: aload_0
        //   30: getfield 18	com/google/android/gms/drive/realtime/internal/c$a$a:lb	Landroid/os/IBinder;
        //   33: iconst_2
        //   34: aload_2
        //   35: aload_3
        //   36: iconst_0
        //   37: invokeinterface 45 5 0
        //   42: pop
        //   43: aload_3
        //   44: invokevirtual 48	android/os/Parcel:readException	()V
        //   47: aload_3
        //   48: invokevirtual 51	android/os/Parcel:recycle	()V
        //   51: aload_2
        //   52: invokevirtual 51	android/os/Parcel:recycle	()V
        //   55: return
        //   56: aload_2
        //   57: iconst_0
        //   58: invokevirtual 39	android/os/Parcel:writeInt	(I)V
        //   61: goto -32 -> 29
        //   64: astore_1
        //   65: aload_3
        //   66: invokevirtual 51	android/os/Parcel:recycle	()V
        //   69: aload_2
        //   70: invokevirtual 51	android/os/Parcel:recycle	()V
        //   73: aload_1
        //   74: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	75	0	this	a
        //   0	75	1	paramStatus	Status
        //   3	67	2	localParcel1	Parcel
        //   7	59	3	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   8	14	64	finally
        //   18	29	64	finally
        //   29	47	64	finally
        //   56	61	64	finally
      }
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\drive\realtime\internal\c.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */