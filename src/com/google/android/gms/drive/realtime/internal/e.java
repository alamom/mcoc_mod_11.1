package com.google.android.gms.drive.realtime.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.StatusCreator;

public abstract interface e
  extends IInterface
{
  public abstract void a(ParcelableCollaborator[] paramArrayOfParcelableCollaborator)
    throws RemoteException;
  
  public abstract void o(Status paramStatus)
    throws RemoteException;
  
  public static abstract class a
    extends Binder
    implements e
  {
    public static e aa(IBinder paramIBinder)
    {
      if (paramIBinder == null) {
        paramIBinder = null;
      }
      for (;;)
      {
        return paramIBinder;
        IInterface localIInterface = paramIBinder.queryLocalInterface("com.google.android.gms.drive.realtime.internal.ICollaboratorsCallback");
        if ((localIInterface != null) && ((localIInterface instanceof e))) {
          paramIBinder = (e)localIInterface;
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
      boolean bool;
      switch (paramInt1)
      {
      default: 
        bool = super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
      case 1598968902: 
      case 1: 
        for (;;)
        {
          return bool;
          paramParcel2.writeString("com.google.android.gms.drive.realtime.internal.ICollaboratorsCallback");
          bool = true;
          continue;
          paramParcel1.enforceInterface("com.google.android.gms.drive.realtime.internal.ICollaboratorsCallback");
          a((ParcelableCollaborator[])paramParcel1.createTypedArray(ParcelableCollaborator.CREATOR));
          paramParcel2.writeNoException();
          bool = true;
        }
      }
      paramParcel1.enforceInterface("com.google.android.gms.drive.realtime.internal.ICollaboratorsCallback");
      if (paramParcel1.readInt() != 0) {}
      for (paramParcel1 = Status.CREATOR.createFromParcel(paramParcel1);; paramParcel1 = null)
      {
        o(paramParcel1);
        paramParcel2.writeNoException();
        bool = true;
        break;
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
      
      public void a(ParcelableCollaborator[] paramArrayOfParcelableCollaborator)
        throws RemoteException
      {
        Parcel localParcel2 = Parcel.obtain();
        Parcel localParcel1 = Parcel.obtain();
        try
        {
          localParcel2.writeInterfaceToken("com.google.android.gms.drive.realtime.internal.ICollaboratorsCallback");
          localParcel2.writeTypedArray(paramArrayOfParcelableCollaborator, 0);
          this.lb.transact(1, localParcel2, localParcel1, 0);
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
      
      /* Error */
      public void o(Status paramStatus)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 28	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore_2
        //   4: invokestatic 28	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   7: astore_3
        //   8: aload_2
        //   9: ldc 30
        //   11: invokevirtual 34	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   14: aload_1
        //   15: ifnull +41 -> 56
        //   18: aload_2
        //   19: iconst_1
        //   20: invokevirtual 59	android/os/Parcel:writeInt	(I)V
        //   23: aload_1
        //   24: aload_2
        //   25: iconst_0
        //   26: invokevirtual 65	com/google/android/gms/common/api/Status:writeToParcel	(Landroid/os/Parcel;I)V
        //   29: aload_0
        //   30: getfield 18	com/google/android/gms/drive/realtime/internal/e$a$a:lb	Landroid/os/IBinder;
        //   33: iconst_2
        //   34: aload_2
        //   35: aload_3
        //   36: iconst_0
        //   37: invokeinterface 44 5 0
        //   42: pop
        //   43: aload_3
        //   44: invokevirtual 47	android/os/Parcel:readException	()V
        //   47: aload_3
        //   48: invokevirtual 50	android/os/Parcel:recycle	()V
        //   51: aload_2
        //   52: invokevirtual 50	android/os/Parcel:recycle	()V
        //   55: return
        //   56: aload_2
        //   57: iconst_0
        //   58: invokevirtual 59	android/os/Parcel:writeInt	(I)V
        //   61: goto -32 -> 29
        //   64: astore_1
        //   65: aload_3
        //   66: invokevirtual 50	android/os/Parcel:recycle	()V
        //   69: aload_2
        //   70: invokevirtual 50	android/os/Parcel:recycle	()V
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


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\drive\realtime\internal\e.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */