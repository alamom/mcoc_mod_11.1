package com.google.android.gms.drive.realtime.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.os.RemoteException;

public abstract interface d
  extends IInterface
{
  public abstract void a(ParcelableCollaborator paramParcelableCollaborator)
    throws RemoteException;
  
  public abstract void b(ParcelableCollaborator paramParcelableCollaborator)
    throws RemoteException;
  
  public static abstract class a
    extends Binder
    implements d
  {
    public static d Z(IBinder paramIBinder)
    {
      if (paramIBinder == null) {
        paramIBinder = null;
      }
      for (;;)
      {
        return paramIBinder;
        IInterface localIInterface = paramIBinder.queryLocalInterface("com.google.android.gms.drive.realtime.internal.ICollaboratorEventCallback");
        if ((localIInterface != null) && ((localIInterface instanceof d))) {
          paramIBinder = (d)localIInterface;
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
      Object localObject2 = null;
      Object localObject1 = null;
      boolean bool;
      switch (paramInt1)
      {
      default: 
        bool = super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
      }
      for (;;)
      {
        return bool;
        paramParcel2.writeString("com.google.android.gms.drive.realtime.internal.ICollaboratorEventCallback");
        bool = true;
        continue;
        paramParcel1.enforceInterface("com.google.android.gms.drive.realtime.internal.ICollaboratorEventCallback");
        if (paramParcel1.readInt() != 0) {
          localObject1 = (ParcelableCollaborator)ParcelableCollaborator.CREATOR.createFromParcel(paramParcel1);
        }
        a((ParcelableCollaborator)localObject1);
        paramParcel2.writeNoException();
        bool = true;
        continue;
        paramParcel1.enforceInterface("com.google.android.gms.drive.realtime.internal.ICollaboratorEventCallback");
        localObject1 = localObject2;
        if (paramParcel1.readInt() != 0) {
          localObject1 = (ParcelableCollaborator)ParcelableCollaborator.CREATOR.createFromParcel(paramParcel1);
        }
        b((ParcelableCollaborator)localObject1);
        paramParcel2.writeNoException();
        bool = true;
      }
    }
    
    private static class a
      implements d
    {
      private IBinder lb;
      
      a(IBinder paramIBinder)
      {
        this.lb = paramIBinder;
      }
      
      /* Error */
      public void a(ParcelableCollaborator paramParcelableCollaborator)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 28	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore_3
        //   4: invokestatic 28	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   7: astore_2
        //   8: aload_3
        //   9: ldc 30
        //   11: invokevirtual 34	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   14: aload_1
        //   15: ifnull +41 -> 56
        //   18: aload_3
        //   19: iconst_1
        //   20: invokevirtual 38	android/os/Parcel:writeInt	(I)V
        //   23: aload_1
        //   24: aload_3
        //   25: iconst_0
        //   26: invokevirtual 44	com/google/android/gms/drive/realtime/internal/ParcelableCollaborator:writeToParcel	(Landroid/os/Parcel;I)V
        //   29: aload_0
        //   30: getfield 18	com/google/android/gms/drive/realtime/internal/d$a$a:lb	Landroid/os/IBinder;
        //   33: iconst_1
        //   34: aload_3
        //   35: aload_2
        //   36: iconst_0
        //   37: invokeinterface 50 5 0
        //   42: pop
        //   43: aload_2
        //   44: invokevirtual 53	android/os/Parcel:readException	()V
        //   47: aload_2
        //   48: invokevirtual 56	android/os/Parcel:recycle	()V
        //   51: aload_3
        //   52: invokevirtual 56	android/os/Parcel:recycle	()V
        //   55: return
        //   56: aload_3
        //   57: iconst_0
        //   58: invokevirtual 38	android/os/Parcel:writeInt	(I)V
        //   61: goto -32 -> 29
        //   64: astore_1
        //   65: aload_2
        //   66: invokevirtual 56	android/os/Parcel:recycle	()V
        //   69: aload_3
        //   70: invokevirtual 56	android/os/Parcel:recycle	()V
        //   73: aload_1
        //   74: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	75	0	this	a
        //   0	75	1	paramParcelableCollaborator	ParcelableCollaborator
        //   7	59	2	localParcel1	Parcel
        //   3	67	3	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   8	14	64	finally
        //   18	29	64	finally
        //   29	47	64	finally
        //   56	61	64	finally
      }
      
      public IBinder asBinder()
      {
        return this.lb;
      }
      
      /* Error */
      public void b(ParcelableCollaborator paramParcelableCollaborator)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 28	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore_3
        //   4: invokestatic 28	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   7: astore_2
        //   8: aload_3
        //   9: ldc 30
        //   11: invokevirtual 34	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   14: aload_1
        //   15: ifnull +41 -> 56
        //   18: aload_3
        //   19: iconst_1
        //   20: invokevirtual 38	android/os/Parcel:writeInt	(I)V
        //   23: aload_1
        //   24: aload_3
        //   25: iconst_0
        //   26: invokevirtual 44	com/google/android/gms/drive/realtime/internal/ParcelableCollaborator:writeToParcel	(Landroid/os/Parcel;I)V
        //   29: aload_0
        //   30: getfield 18	com/google/android/gms/drive/realtime/internal/d$a$a:lb	Landroid/os/IBinder;
        //   33: iconst_2
        //   34: aload_3
        //   35: aload_2
        //   36: iconst_0
        //   37: invokeinterface 50 5 0
        //   42: pop
        //   43: aload_2
        //   44: invokevirtual 53	android/os/Parcel:readException	()V
        //   47: aload_2
        //   48: invokevirtual 56	android/os/Parcel:recycle	()V
        //   51: aload_3
        //   52: invokevirtual 56	android/os/Parcel:recycle	()V
        //   55: return
        //   56: aload_3
        //   57: iconst_0
        //   58: invokevirtual 38	android/os/Parcel:writeInt	(I)V
        //   61: goto -32 -> 29
        //   64: astore_1
        //   65: aload_2
        //   66: invokevirtual 56	android/os/Parcel:recycle	()V
        //   69: aload_3
        //   70: invokevirtual 56	android/os/Parcel:recycle	()V
        //   73: aload_1
        //   74: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	75	0	this	a
        //   0	75	1	paramParcelableCollaborator	ParcelableCollaborator
        //   7	59	2	localParcel1	Parcel
        //   3	67	3	localParcel2	Parcel
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


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\drive\realtime\internal\d.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */