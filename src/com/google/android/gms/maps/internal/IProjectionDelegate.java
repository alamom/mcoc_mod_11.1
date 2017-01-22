package com.google.android.gms.maps.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.d;
import com.google.android.gms.dynamic.d.a;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.VisibleRegion;
import com.google.android.gms.maps.model.i;

public abstract interface IProjectionDelegate
  extends IInterface
{
  public abstract LatLng fromScreenLocation(d paramd)
    throws RemoteException;
  
  public abstract VisibleRegion getVisibleRegion()
    throws RemoteException;
  
  public abstract d toScreenLocation(LatLng paramLatLng)
    throws RemoteException;
  
  public static abstract class a
    extends Binder
    implements IProjectionDelegate
  {
    public static IProjectionDelegate bj(IBinder paramIBinder)
    {
      if (paramIBinder == null) {
        paramIBinder = null;
      }
      for (;;)
      {
        return paramIBinder;
        IInterface localIInterface = paramIBinder.queryLocalInterface("com.google.android.gms.maps.internal.IProjectionDelegate");
        if ((localIInterface != null) && ((localIInterface instanceof IProjectionDelegate))) {
          paramIBinder = (IProjectionDelegate)localIInterface;
        } else {
          paramIBinder = new a(paramIBinder);
        }
      }
    }
    
    public boolean onTransact(int paramInt1, Parcel paramParcel1, Parcel paramParcel2, int paramInt2)
      throws RemoteException
    {
      Object localObject = null;
      boolean bool;
      switch (paramInt1)
      {
      default: 
      case 1598968902: 
        for (bool = super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);; bool = true)
        {
          return bool;
          paramParcel2.writeString("com.google.android.gms.maps.internal.IProjectionDelegate");
        }
      case 1: 
        paramParcel1.enforceInterface("com.google.android.gms.maps.internal.IProjectionDelegate");
        paramParcel1 = fromScreenLocation(d.a.am(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        if (paramParcel1 != null)
        {
          paramParcel2.writeInt(1);
          paramParcel1.writeToParcel(paramParcel2, 1);
        }
        for (;;)
        {
          bool = true;
          break;
          paramParcel2.writeInt(0);
        }
      case 2: 
        paramParcel1.enforceInterface("com.google.android.gms.maps.internal.IProjectionDelegate");
        if (paramParcel1.readInt() != 0) {}
        for (paramParcel1 = LatLng.CREATOR.cM(paramParcel1);; paramParcel1 = null)
        {
          d locald = toScreenLocation(paramParcel1);
          paramParcel2.writeNoException();
          paramParcel1 = (Parcel)localObject;
          if (locald != null) {
            paramParcel1 = locald.asBinder();
          }
          paramParcel2.writeStrongBinder(paramParcel1);
          bool = true;
          break;
        }
      }
      paramParcel1.enforceInterface("com.google.android.gms.maps.internal.IProjectionDelegate");
      paramParcel1 = getVisibleRegion();
      paramParcel2.writeNoException();
      if (paramParcel1 != null)
      {
        paramParcel2.writeInt(1);
        paramParcel1.writeToParcel(paramParcel2, 1);
      }
      for (;;)
      {
        bool = true;
        break;
        paramParcel2.writeInt(0);
      }
    }
    
    private static class a
      implements IProjectionDelegate
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
      public LatLng fromScreenLocation(d paramd)
        throws RemoteException
      {
        // Byte code:
        //   0: aconst_null
        //   1: astore_2
        //   2: invokestatic 31	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   5: astore 4
        //   7: invokestatic 31	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   10: astore_3
        //   11: aload 4
        //   13: ldc 33
        //   15: invokevirtual 37	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   18: aload_1
        //   19: ifnull +63 -> 82
        //   22: aload_1
        //   23: invokeinterface 41 1 0
        //   28: astore_1
        //   29: aload 4
        //   31: aload_1
        //   32: invokevirtual 44	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   35: aload_0
        //   36: getfield 18	com/google/android/gms/maps/internal/IProjectionDelegate$a$a:lb	Landroid/os/IBinder;
        //   39: iconst_1
        //   40: aload 4
        //   42: aload_3
        //   43: iconst_0
        //   44: invokeinterface 50 5 0
        //   49: pop
        //   50: aload_3
        //   51: invokevirtual 53	android/os/Parcel:readException	()V
        //   54: aload_2
        //   55: astore_1
        //   56: aload_3
        //   57: invokevirtual 57	android/os/Parcel:readInt	()I
        //   60: ifeq +11 -> 71
        //   63: getstatic 63	com/google/android/gms/maps/model/LatLng:CREATOR	Lcom/google/android/gms/maps/model/i;
        //   66: aload_3
        //   67: invokevirtual 69	com/google/android/gms/maps/model/i:cM	(Landroid/os/Parcel;)Lcom/google/android/gms/maps/model/LatLng;
        //   70: astore_1
        //   71: aload_3
        //   72: invokevirtual 72	android/os/Parcel:recycle	()V
        //   75: aload 4
        //   77: invokevirtual 72	android/os/Parcel:recycle	()V
        //   80: aload_1
        //   81: areturn
        //   82: aconst_null
        //   83: astore_1
        //   84: goto -55 -> 29
        //   87: astore_1
        //   88: aload_3
        //   89: invokevirtual 72	android/os/Parcel:recycle	()V
        //   92: aload 4
        //   94: invokevirtual 72	android/os/Parcel:recycle	()V
        //   97: aload_1
        //   98: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	99	0	this	a
        //   0	99	1	paramd	d
        //   1	54	2	localObject	Object
        //   10	79	3	localParcel1	Parcel
        //   5	88	4	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   11	18	87	finally
        //   22	29	87	finally
        //   29	54	87	finally
        //   56	71	87	finally
      }
      
      /* Error */
      public VisibleRegion getVisibleRegion()
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 31	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore_2
        //   4: invokestatic 31	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   7: astore_3
        //   8: aload_2
        //   9: ldc 33
        //   11: invokevirtual 37	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   14: aload_0
        //   15: getfield 18	com/google/android/gms/maps/internal/IProjectionDelegate$a$a:lb	Landroid/os/IBinder;
        //   18: iconst_3
        //   19: aload_2
        //   20: aload_3
        //   21: iconst_0
        //   22: invokeinterface 50 5 0
        //   27: pop
        //   28: aload_3
        //   29: invokevirtual 53	android/os/Parcel:readException	()V
        //   32: aload_3
        //   33: invokevirtual 57	android/os/Parcel:readInt	()I
        //   36: ifeq +21 -> 57
        //   39: getstatic 80	com/google/android/gms/maps/model/VisibleRegion:CREATOR	Lcom/google/android/gms/maps/model/y;
        //   42: aload_3
        //   43: invokevirtual 86	com/google/android/gms/maps/model/y:cW	(Landroid/os/Parcel;)Lcom/google/android/gms/maps/model/VisibleRegion;
        //   46: astore_1
        //   47: aload_3
        //   48: invokevirtual 72	android/os/Parcel:recycle	()V
        //   51: aload_2
        //   52: invokevirtual 72	android/os/Parcel:recycle	()V
        //   55: aload_1
        //   56: areturn
        //   57: aconst_null
        //   58: astore_1
        //   59: goto -12 -> 47
        //   62: astore_1
        //   63: aload_3
        //   64: invokevirtual 72	android/os/Parcel:recycle	()V
        //   67: aload_2
        //   68: invokevirtual 72	android/os/Parcel:recycle	()V
        //   71: aload_1
        //   72: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	73	0	this	a
        //   46	13	1	localVisibleRegion	VisibleRegion
        //   62	10	1	localObject	Object
        //   3	65	2	localParcel1	Parcel
        //   7	57	3	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   8	47	62	finally
      }
      
      /* Error */
      public d toScreenLocation(LatLng paramLatLng)
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
        //   15: ifnull +50 -> 65
        //   18: aload_3
        //   19: iconst_1
        //   20: invokevirtual 92	android/os/Parcel:writeInt	(I)V
        //   23: aload_1
        //   24: aload_3
        //   25: iconst_0
        //   26: invokevirtual 96	com/google/android/gms/maps/model/LatLng:writeToParcel	(Landroid/os/Parcel;I)V
        //   29: aload_0
        //   30: getfield 18	com/google/android/gms/maps/internal/IProjectionDelegate$a$a:lb	Landroid/os/IBinder;
        //   33: iconst_2
        //   34: aload_3
        //   35: aload_2
        //   36: iconst_0
        //   37: invokeinterface 50 5 0
        //   42: pop
        //   43: aload_2
        //   44: invokevirtual 53	android/os/Parcel:readException	()V
        //   47: aload_2
        //   48: invokevirtual 99	android/os/Parcel:readStrongBinder	()Landroid/os/IBinder;
        //   51: invokestatic 105	com/google/android/gms/dynamic/d$a:am	(Landroid/os/IBinder;)Lcom/google/android/gms/dynamic/d;
        //   54: astore_1
        //   55: aload_2
        //   56: invokevirtual 72	android/os/Parcel:recycle	()V
        //   59: aload_3
        //   60: invokevirtual 72	android/os/Parcel:recycle	()V
        //   63: aload_1
        //   64: areturn
        //   65: aload_3
        //   66: iconst_0
        //   67: invokevirtual 92	android/os/Parcel:writeInt	(I)V
        //   70: goto -41 -> 29
        //   73: astore_1
        //   74: aload_2
        //   75: invokevirtual 72	android/os/Parcel:recycle	()V
        //   78: aload_3
        //   79: invokevirtual 72	android/os/Parcel:recycle	()V
        //   82: aload_1
        //   83: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	84	0	this	a
        //   0	84	1	paramLatLng	LatLng
        //   7	68	2	localParcel1	Parcel
        //   3	76	3	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   8	14	73	finally
        //   18	29	73	finally
        //   29	55	73	finally
        //   65	70	73	finally
      }
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\maps\internal\IProjectionDelegate.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */