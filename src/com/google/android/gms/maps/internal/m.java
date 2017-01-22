package com.google.android.gms.maps.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.maps.model.internal.f;
import com.google.android.gms.maps.model.internal.f.a;

public abstract interface m
  extends IInterface
{
  public abstract void b(f paramf)
    throws RemoteException;
  
  public abstract void c(f paramf)
    throws RemoteException;
  
  public abstract void d(f paramf)
    throws RemoteException;
  
  public static abstract class a
    extends Binder
    implements m
  {
    public a()
    {
      attachInterface(this, "com.google.android.gms.maps.internal.IOnMarkerDragListener");
    }
    
    public static m bd(IBinder paramIBinder)
    {
      if (paramIBinder == null) {
        paramIBinder = null;
      }
      for (;;)
      {
        return paramIBinder;
        IInterface localIInterface = paramIBinder.queryLocalInterface("com.google.android.gms.maps.internal.IOnMarkerDragListener");
        if ((localIInterface != null) && ((localIInterface instanceof m))) {
          paramIBinder = (m)localIInterface;
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
      boolean bool = true;
      switch (paramInt1)
      {
      default: 
        bool = super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
      }
      for (;;)
      {
        return bool;
        paramParcel2.writeString("com.google.android.gms.maps.internal.IOnMarkerDragListener");
        continue;
        paramParcel1.enforceInterface("com.google.android.gms.maps.internal.IOnMarkerDragListener");
        b(f.a.bu(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        continue;
        paramParcel1.enforceInterface("com.google.android.gms.maps.internal.IOnMarkerDragListener");
        d(f.a.bu(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        continue;
        paramParcel1.enforceInterface("com.google.android.gms.maps.internal.IOnMarkerDragListener");
        c(f.a.bu(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
      }
    }
    
    private static class a
      implements m
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
      public void b(f paramf)
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
        //   15: ifnull +42 -> 57
        //   18: aload_1
        //   19: invokeinterface 41 1 0
        //   24: astore_1
        //   25: aload_3
        //   26: aload_1
        //   27: invokevirtual 44	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   30: aload_0
        //   31: getfield 18	com/google/android/gms/maps/internal/m$a$a:lb	Landroid/os/IBinder;
        //   34: iconst_1
        //   35: aload_3
        //   36: aload_2
        //   37: iconst_0
        //   38: invokeinterface 50 5 0
        //   43: pop
        //   44: aload_2
        //   45: invokevirtual 53	android/os/Parcel:readException	()V
        //   48: aload_2
        //   49: invokevirtual 56	android/os/Parcel:recycle	()V
        //   52: aload_3
        //   53: invokevirtual 56	android/os/Parcel:recycle	()V
        //   56: return
        //   57: aconst_null
        //   58: astore_1
        //   59: goto -34 -> 25
        //   62: astore_1
        //   63: aload_2
        //   64: invokevirtual 56	android/os/Parcel:recycle	()V
        //   67: aload_3
        //   68: invokevirtual 56	android/os/Parcel:recycle	()V
        //   71: aload_1
        //   72: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	73	0	this	a
        //   0	73	1	paramf	f
        //   7	57	2	localParcel1	Parcel
        //   3	65	3	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   8	14	62	finally
        //   18	25	62	finally
        //   25	48	62	finally
      }
      
      /* Error */
      public void c(f paramf)
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
        //   15: ifnull +42 -> 57
        //   18: aload_1
        //   19: invokeinterface 41 1 0
        //   24: astore_1
        //   25: aload_3
        //   26: aload_1
        //   27: invokevirtual 44	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   30: aload_0
        //   31: getfield 18	com/google/android/gms/maps/internal/m$a$a:lb	Landroid/os/IBinder;
        //   34: iconst_3
        //   35: aload_3
        //   36: aload_2
        //   37: iconst_0
        //   38: invokeinterface 50 5 0
        //   43: pop
        //   44: aload_2
        //   45: invokevirtual 53	android/os/Parcel:readException	()V
        //   48: aload_2
        //   49: invokevirtual 56	android/os/Parcel:recycle	()V
        //   52: aload_3
        //   53: invokevirtual 56	android/os/Parcel:recycle	()V
        //   56: return
        //   57: aconst_null
        //   58: astore_1
        //   59: goto -34 -> 25
        //   62: astore_1
        //   63: aload_2
        //   64: invokevirtual 56	android/os/Parcel:recycle	()V
        //   67: aload_3
        //   68: invokevirtual 56	android/os/Parcel:recycle	()V
        //   71: aload_1
        //   72: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	73	0	this	a
        //   0	73	1	paramf	f
        //   7	57	2	localParcel1	Parcel
        //   3	65	3	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   8	14	62	finally
        //   18	25	62	finally
        //   25	48	62	finally
      }
      
      /* Error */
      public void d(f paramf)
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
        //   15: ifnull +42 -> 57
        //   18: aload_1
        //   19: invokeinterface 41 1 0
        //   24: astore_1
        //   25: aload_3
        //   26: aload_1
        //   27: invokevirtual 44	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   30: aload_0
        //   31: getfield 18	com/google/android/gms/maps/internal/m$a$a:lb	Landroid/os/IBinder;
        //   34: iconst_2
        //   35: aload_3
        //   36: aload_2
        //   37: iconst_0
        //   38: invokeinterface 50 5 0
        //   43: pop
        //   44: aload_2
        //   45: invokevirtual 53	android/os/Parcel:readException	()V
        //   48: aload_2
        //   49: invokevirtual 56	android/os/Parcel:recycle	()V
        //   52: aload_3
        //   53: invokevirtual 56	android/os/Parcel:recycle	()V
        //   56: return
        //   57: aconst_null
        //   58: astore_1
        //   59: goto -34 -> 25
        //   62: astore_1
        //   63: aload_2
        //   64: invokevirtual 56	android/os/Parcel:recycle	()V
        //   67: aload_3
        //   68: invokevirtual 56	android/os/Parcel:recycle	()V
        //   71: aload_1
        //   72: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	73	0	this	a
        //   0	73	1	paramf	f
        //   7	57	2	localParcel1	Parcel
        //   3	65	3	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   8	14	62	finally
        //   18	25	62	finally
        //   25	48	62	finally
      }
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\maps\internal\m.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */