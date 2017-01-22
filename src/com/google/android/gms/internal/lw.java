package com.google.android.gms.internal;

import android.app.PendingIntent;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.os.RemoteException;

public abstract interface lw
  extends IInterface
{
  public abstract void onAddGeofencesResult(int paramInt, String[] paramArrayOfString)
    throws RemoteException;
  
  public abstract void onRemoveGeofencesByPendingIntentResult(int paramInt, PendingIntent paramPendingIntent)
    throws RemoteException;
  
  public abstract void onRemoveGeofencesByRequestIdsResult(int paramInt, String[] paramArrayOfString)
    throws RemoteException;
  
  public static abstract class a
    extends Binder
    implements lw
  {
    public a()
    {
      attachInterface(this, "com.google.android.gms.location.internal.IGeofencerCallbacks");
    }
    
    public static lw aJ(IBinder paramIBinder)
    {
      if (paramIBinder == null) {
        paramIBinder = null;
      }
      for (;;)
      {
        return paramIBinder;
        IInterface localIInterface = paramIBinder.queryLocalInterface("com.google.android.gms.location.internal.IGeofencerCallbacks");
        if ((localIInterface != null) && ((localIInterface instanceof lw))) {
          paramIBinder = (lw)localIInterface;
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
      case 2: 
        for (;;)
        {
          return bool;
          paramParcel2.writeString("com.google.android.gms.location.internal.IGeofencerCallbacks");
          bool = true;
          continue;
          paramParcel1.enforceInterface("com.google.android.gms.location.internal.IGeofencerCallbacks");
          onAddGeofencesResult(paramParcel1.readInt(), paramParcel1.createStringArray());
          paramParcel2.writeNoException();
          bool = true;
          continue;
          paramParcel1.enforceInterface("com.google.android.gms.location.internal.IGeofencerCallbacks");
          onRemoveGeofencesByRequestIdsResult(paramParcel1.readInt(), paramParcel1.createStringArray());
          paramParcel2.writeNoException();
          bool = true;
        }
      }
      paramParcel1.enforceInterface("com.google.android.gms.location.internal.IGeofencerCallbacks");
      paramInt1 = paramParcel1.readInt();
      if (paramParcel1.readInt() != 0) {}
      for (paramParcel1 = (PendingIntent)PendingIntent.CREATOR.createFromParcel(paramParcel1);; paramParcel1 = null)
      {
        onRemoveGeofencesByPendingIntentResult(paramInt1, paramParcel1);
        paramParcel2.writeNoException();
        bool = true;
        break;
      }
    }
    
    private static class a
      implements lw
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
      
      public void onAddGeofencesResult(int paramInt, String[] paramArrayOfString)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.location.internal.IGeofencerCallbacks");
          localParcel1.writeInt(paramInt);
          localParcel1.writeStringArray(paramArrayOfString);
          this.lb.transact(1, localParcel1, localParcel2, 0);
          localParcel2.readException();
          return;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
      
      /* Error */
      public void onRemoveGeofencesByPendingIntentResult(int paramInt, PendingIntent paramPendingIntent)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 31	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore 4
        //   5: invokestatic 31	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   8: astore_3
        //   9: aload 4
        //   11: ldc 33
        //   13: invokevirtual 37	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   16: aload 4
        //   18: iload_1
        //   19: invokevirtual 41	android/os/Parcel:writeInt	(I)V
        //   22: aload_2
        //   23: ifnull +45 -> 68
        //   26: aload 4
        //   28: iconst_1
        //   29: invokevirtual 41	android/os/Parcel:writeInt	(I)V
        //   32: aload_2
        //   33: aload 4
        //   35: iconst_0
        //   36: invokevirtual 66	android/app/PendingIntent:writeToParcel	(Landroid/os/Parcel;I)V
        //   39: aload_0
        //   40: getfield 18	com/google/android/gms/internal/lw$a$a:lb	Landroid/os/IBinder;
        //   43: iconst_3
        //   44: aload 4
        //   46: aload_3
        //   47: iconst_0
        //   48: invokeinterface 51 5 0
        //   53: pop
        //   54: aload_3
        //   55: invokevirtual 54	android/os/Parcel:readException	()V
        //   58: aload_3
        //   59: invokevirtual 57	android/os/Parcel:recycle	()V
        //   62: aload 4
        //   64: invokevirtual 57	android/os/Parcel:recycle	()V
        //   67: return
        //   68: aload 4
        //   70: iconst_0
        //   71: invokevirtual 41	android/os/Parcel:writeInt	(I)V
        //   74: goto -35 -> 39
        //   77: astore_2
        //   78: aload_3
        //   79: invokevirtual 57	android/os/Parcel:recycle	()V
        //   82: aload 4
        //   84: invokevirtual 57	android/os/Parcel:recycle	()V
        //   87: aload_2
        //   88: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	89	0	this	a
        //   0	89	1	paramInt	int
        //   0	89	2	paramPendingIntent	PendingIntent
        //   8	71	3	localParcel1	Parcel
        //   3	80	4	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   9	22	77	finally
        //   26	39	77	finally
        //   39	58	77	finally
        //   68	74	77	finally
      }
      
      public void onRemoveGeofencesByRequestIdsResult(int paramInt, String[] paramArrayOfString)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.location.internal.IGeofencerCallbacks");
          localParcel1.writeInt(paramInt);
          localParcel1.writeStringArray(paramArrayOfString);
          this.lb.transact(2, localParcel1, localParcel2, 0);
          localParcel2.readException();
          return;
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


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\internal\lw.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */