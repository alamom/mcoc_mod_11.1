package com.google.android.gms.games.internal;

import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.os.RemoteException;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.f;

public abstract interface IGamesSignInCallbacks
  extends IInterface
{
  public abstract void T(DataHolder paramDataHolder)
    throws RemoteException;
  
  public abstract void U(DataHolder paramDataHolder)
    throws RemoteException;
  
  public abstract void b(int paramInt, Intent paramIntent)
    throws RemoteException;
  
  public abstract void dD(int paramInt)
    throws RemoteException;
  
  public abstract void dE(int paramInt)
    throws RemoteException;
  
  public abstract void g(DataHolder paramDataHolder)
    throws RemoteException;
  
  public static abstract class Stub
    extends Binder
    implements IGamesSignInCallbacks
  {
    public Stub()
    {
      attachInterface(this, "com.google.android.gms.games.internal.IGamesSignInCallbacks");
    }
    
    public static IGamesSignInCallbacks aC(IBinder paramIBinder)
    {
      if (paramIBinder == null) {
        paramIBinder = null;
      }
      for (;;)
      {
        return paramIBinder;
        IInterface localIInterface = paramIBinder.queryLocalInterface("com.google.android.gms.games.internal.IGamesSignInCallbacks");
        if ((localIInterface != null) && ((localIInterface instanceof IGamesSignInCallbacks))) {
          paramIBinder = (IGamesSignInCallbacks)localIInterface;
        } else {
          paramIBinder = new Proxy(paramIBinder);
        }
      }
    }
    
    public boolean onTransact(int paramInt1, Parcel paramParcel1, Parcel paramParcel2, int paramInt2)
      throws RemoteException
    {
      Object localObject2 = null;
      Object localObject3 = null;
      Object localObject4 = null;
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
        paramParcel2.writeString("com.google.android.gms.games.internal.IGamesSignInCallbacks");
        bool = true;
        continue;
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesSignInCallbacks");
        paramInt1 = paramParcel1.readInt();
        if (paramParcel1.readInt() != 0) {
          localObject1 = (Intent)Intent.CREATOR.createFromParcel(paramParcel1);
        }
        b(paramInt1, (Intent)localObject1);
        paramParcel2.writeNoException();
        bool = true;
        continue;
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesSignInCallbacks");
        localObject1 = localObject2;
        if (paramParcel1.readInt() != 0) {
          localObject1 = DataHolder.CREATOR.z(paramParcel1);
        }
        T((DataHolder)localObject1);
        paramParcel2.writeNoException();
        bool = true;
        continue;
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesSignInCallbacks");
        localObject1 = localObject3;
        if (paramParcel1.readInt() != 0) {
          localObject1 = DataHolder.CREATOR.z(paramParcel1);
        }
        U((DataHolder)localObject1);
        paramParcel2.writeNoException();
        bool = true;
        continue;
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesSignInCallbacks");
        dD(paramParcel1.readInt());
        paramParcel2.writeNoException();
        bool = true;
        continue;
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesSignInCallbacks");
        localObject1 = localObject4;
        if (paramParcel1.readInt() != 0) {
          localObject1 = DataHolder.CREATOR.z(paramParcel1);
        }
        g((DataHolder)localObject1);
        paramParcel2.writeNoException();
        bool = true;
        continue;
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesSignInCallbacks");
        dE(paramParcel1.readInt());
        paramParcel2.writeNoException();
        bool = true;
      }
    }
    
    private static class Proxy
      implements IGamesSignInCallbacks
    {
      private IBinder lb;
      
      Proxy(IBinder paramIBinder)
      {
        this.lb = paramIBinder;
      }
      
      /* Error */
      public void T(DataHolder paramDataHolder)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore_2
        //   4: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   7: astore_3
        //   8: aload_2
        //   9: ldc 32
        //   11: invokevirtual 36	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   14: aload_1
        //   15: ifnull +43 -> 58
        //   18: aload_2
        //   19: iconst_1
        //   20: invokevirtual 40	android/os/Parcel:writeInt	(I)V
        //   23: aload_1
        //   24: aload_2
        //   25: iconst_0
        //   26: invokevirtual 46	com/google/android/gms/common/data/DataHolder:writeToParcel	(Landroid/os/Parcel;I)V
        //   29: aload_0
        //   30: getfield 19	com/google/android/gms/games/internal/IGamesSignInCallbacks$Stub$Proxy:lb	Landroid/os/IBinder;
        //   33: sipush 5002
        //   36: aload_2
        //   37: aload_3
        //   38: iconst_0
        //   39: invokeinterface 52 5 0
        //   44: pop
        //   45: aload_3
        //   46: invokevirtual 55	android/os/Parcel:readException	()V
        //   49: aload_3
        //   50: invokevirtual 58	android/os/Parcel:recycle	()V
        //   53: aload_2
        //   54: invokevirtual 58	android/os/Parcel:recycle	()V
        //   57: return
        //   58: aload_2
        //   59: iconst_0
        //   60: invokevirtual 40	android/os/Parcel:writeInt	(I)V
        //   63: goto -34 -> 29
        //   66: astore_1
        //   67: aload_3
        //   68: invokevirtual 58	android/os/Parcel:recycle	()V
        //   71: aload_2
        //   72: invokevirtual 58	android/os/Parcel:recycle	()V
        //   75: aload_1
        //   76: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	77	0	this	Proxy
        //   0	77	1	paramDataHolder	DataHolder
        //   3	69	2	localParcel1	Parcel
        //   7	61	3	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   8	14	66	finally
        //   18	29	66	finally
        //   29	49	66	finally
        //   58	63	66	finally
      }
      
      /* Error */
      public void U(DataHolder paramDataHolder)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore_3
        //   4: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   7: astore_2
        //   8: aload_3
        //   9: ldc 32
        //   11: invokevirtual 36	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   14: aload_1
        //   15: ifnull +43 -> 58
        //   18: aload_3
        //   19: iconst_1
        //   20: invokevirtual 40	android/os/Parcel:writeInt	(I)V
        //   23: aload_1
        //   24: aload_3
        //   25: iconst_0
        //   26: invokevirtual 46	com/google/android/gms/common/data/DataHolder:writeToParcel	(Landroid/os/Parcel;I)V
        //   29: aload_0
        //   30: getfield 19	com/google/android/gms/games/internal/IGamesSignInCallbacks$Stub$Proxy:lb	Landroid/os/IBinder;
        //   33: sipush 5003
        //   36: aload_3
        //   37: aload_2
        //   38: iconst_0
        //   39: invokeinterface 52 5 0
        //   44: pop
        //   45: aload_2
        //   46: invokevirtual 55	android/os/Parcel:readException	()V
        //   49: aload_2
        //   50: invokevirtual 58	android/os/Parcel:recycle	()V
        //   53: aload_3
        //   54: invokevirtual 58	android/os/Parcel:recycle	()V
        //   57: return
        //   58: aload_3
        //   59: iconst_0
        //   60: invokevirtual 40	android/os/Parcel:writeInt	(I)V
        //   63: goto -34 -> 29
        //   66: astore_1
        //   67: aload_2
        //   68: invokevirtual 58	android/os/Parcel:recycle	()V
        //   71: aload_3
        //   72: invokevirtual 58	android/os/Parcel:recycle	()V
        //   75: aload_1
        //   76: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	77	0	this	Proxy
        //   0	77	1	paramDataHolder	DataHolder
        //   7	61	2	localParcel1	Parcel
        //   3	69	3	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   8	14	66	finally
        //   18	29	66	finally
        //   29	49	66	finally
        //   58	63	66	finally
      }
      
      public IBinder asBinder()
      {
        return this.lb;
      }
      
      /* Error */
      public void b(int paramInt, Intent paramIntent)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore 4
        //   5: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   8: astore_3
        //   9: aload 4
        //   11: ldc 32
        //   13: invokevirtual 36	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   16: aload 4
        //   18: iload_1
        //   19: invokevirtual 40	android/os/Parcel:writeInt	(I)V
        //   22: aload_2
        //   23: ifnull +47 -> 70
        //   26: aload 4
        //   28: iconst_1
        //   29: invokevirtual 40	android/os/Parcel:writeInt	(I)V
        //   32: aload_2
        //   33: aload 4
        //   35: iconst_0
        //   36: invokevirtual 67	android/content/Intent:writeToParcel	(Landroid/os/Parcel;I)V
        //   39: aload_0
        //   40: getfield 19	com/google/android/gms/games/internal/IGamesSignInCallbacks$Stub$Proxy:lb	Landroid/os/IBinder;
        //   43: sipush 5001
        //   46: aload 4
        //   48: aload_3
        //   49: iconst_0
        //   50: invokeinterface 52 5 0
        //   55: pop
        //   56: aload_3
        //   57: invokevirtual 55	android/os/Parcel:readException	()V
        //   60: aload_3
        //   61: invokevirtual 58	android/os/Parcel:recycle	()V
        //   64: aload 4
        //   66: invokevirtual 58	android/os/Parcel:recycle	()V
        //   69: return
        //   70: aload 4
        //   72: iconst_0
        //   73: invokevirtual 40	android/os/Parcel:writeInt	(I)V
        //   76: goto -37 -> 39
        //   79: astore_2
        //   80: aload_3
        //   81: invokevirtual 58	android/os/Parcel:recycle	()V
        //   84: aload 4
        //   86: invokevirtual 58	android/os/Parcel:recycle	()V
        //   89: aload_2
        //   90: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	91	0	this	Proxy
        //   0	91	1	paramInt	int
        //   0	91	2	paramIntent	Intent
        //   8	73	3	localParcel1	Parcel
        //   3	82	4	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   9	22	79	finally
        //   26	39	79	finally
        //   39	60	79	finally
        //   70	76	79	finally
      }
      
      public void dD(int paramInt)
        throws RemoteException
      {
        Parcel localParcel2 = Parcel.obtain();
        Parcel localParcel1 = Parcel.obtain();
        try
        {
          localParcel2.writeInterfaceToken("com.google.android.gms.games.internal.IGamesSignInCallbacks");
          localParcel2.writeInt(paramInt);
          this.lb.transact(5004, localParcel2, localParcel1, 0);
          localParcel1.readException();
          return;
        }
        finally
        {
          localParcel1.recycle();
          localParcel2.recycle();
        }
      }
      
      public void dE(int paramInt)
        throws RemoteException
      {
        Parcel localParcel2 = Parcel.obtain();
        Parcel localParcel1 = Parcel.obtain();
        try
        {
          localParcel2.writeInterfaceToken("com.google.android.gms.games.internal.IGamesSignInCallbacks");
          localParcel2.writeInt(paramInt);
          this.lb.transact(5006, localParcel2, localParcel1, 0);
          localParcel1.readException();
          return;
        }
        finally
        {
          localParcel1.recycle();
          localParcel2.recycle();
        }
      }
      
      /* Error */
      public void g(DataHolder paramDataHolder)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore_2
        //   4: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   7: astore_3
        //   8: aload_2
        //   9: ldc 32
        //   11: invokevirtual 36	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   14: aload_1
        //   15: ifnull +43 -> 58
        //   18: aload_2
        //   19: iconst_1
        //   20: invokevirtual 40	android/os/Parcel:writeInt	(I)V
        //   23: aload_1
        //   24: aload_2
        //   25: iconst_0
        //   26: invokevirtual 46	com/google/android/gms/common/data/DataHolder:writeToParcel	(Landroid/os/Parcel;I)V
        //   29: aload_0
        //   30: getfield 19	com/google/android/gms/games/internal/IGamesSignInCallbacks$Stub$Proxy:lb	Landroid/os/IBinder;
        //   33: sipush 5005
        //   36: aload_2
        //   37: aload_3
        //   38: iconst_0
        //   39: invokeinterface 52 5 0
        //   44: pop
        //   45: aload_3
        //   46: invokevirtual 55	android/os/Parcel:readException	()V
        //   49: aload_3
        //   50: invokevirtual 58	android/os/Parcel:recycle	()V
        //   53: aload_2
        //   54: invokevirtual 58	android/os/Parcel:recycle	()V
        //   57: return
        //   58: aload_2
        //   59: iconst_0
        //   60: invokevirtual 40	android/os/Parcel:writeInt	(I)V
        //   63: goto -34 -> 29
        //   66: astore_1
        //   67: aload_3
        //   68: invokevirtual 58	android/os/Parcel:recycle	()V
        //   71: aload_2
        //   72: invokevirtual 58	android/os/Parcel:recycle	()V
        //   75: aload_1
        //   76: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	77	0	this	Proxy
        //   0	77	1	paramDataHolder	DataHolder
        //   3	69	2	localParcel1	Parcel
        //   7	61	3	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   8	14	66	finally
        //   18	29	66	finally
        //   29	49	66	finally
        //   58	63	66	finally
      }
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\games\internal\IGamesSignInCallbacks.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */