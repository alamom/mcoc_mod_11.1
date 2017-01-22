package com.google.android.gms.plus.internal;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable.Creator;
import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.StatusCreator;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.f;
import com.google.android.gms.internal.jp;
import com.google.android.gms.internal.jq;
import com.google.android.gms.internal.nz;
import com.google.android.gms.internal.oa;

public abstract interface b
  extends IInterface
{
  public abstract void a(int paramInt, Bundle paramBundle1, Bundle paramBundle2)
    throws RemoteException;
  
  public abstract void a(int paramInt, Bundle paramBundle, ParcelFileDescriptor paramParcelFileDescriptor)
    throws RemoteException;
  
  public abstract void a(int paramInt, Bundle paramBundle, jp paramjp)
    throws RemoteException;
  
  public abstract void a(int paramInt, nz paramnz)
    throws RemoteException;
  
  public abstract void a(DataHolder paramDataHolder, String paramString)
    throws RemoteException;
  
  public abstract void a(DataHolder paramDataHolder, String paramString1, String paramString2)
    throws RemoteException;
  
  public abstract void aB(Status paramStatus)
    throws RemoteException;
  
  public abstract void ce(String paramString)
    throws RemoteException;
  
  public abstract void cf(String paramString)
    throws RemoteException;
  
  public abstract void h(int paramInt, Bundle paramBundle)
    throws RemoteException;
  
  public static abstract class a
    extends Binder
    implements b
  {
    public a()
    {
      attachInterface(this, "com.google.android.gms.plus.internal.IPlusCallbacks");
    }
    
    public static b bE(IBinder paramIBinder)
    {
      if (paramIBinder == null) {
        paramIBinder = null;
      }
      for (;;)
      {
        return paramIBinder;
        IInterface localIInterface = paramIBinder.queryLocalInterface("com.google.android.gms.plus.internal.IPlusCallbacks");
        if ((localIInterface != null) && ((localIInterface instanceof b))) {
          paramIBinder = (b)localIInterface;
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
      jp localjp = null;
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
        paramParcel2.writeString("com.google.android.gms.plus.internal.IPlusCallbacks");
        bool = true;
        continue;
        paramParcel1.enforceInterface("com.google.android.gms.plus.internal.IPlusCallbacks");
        paramInt1 = paramParcel1.readInt();
        if (paramParcel1.readInt() != 0)
        {
          localObject1 = (Bundle)Bundle.CREATOR.createFromParcel(paramParcel1);
          label174:
          if (paramParcel1.readInt() == 0) {
            break label218;
          }
        }
        label218:
        for (paramParcel1 = (Bundle)Bundle.CREATOR.createFromParcel(paramParcel1);; paramParcel1 = null)
        {
          a(paramInt1, (Bundle)localObject1, paramParcel1);
          paramParcel2.writeNoException();
          bool = true;
          break;
          localObject1 = null;
          break label174;
        }
        paramParcel1.enforceInterface("com.google.android.gms.plus.internal.IPlusCallbacks");
        paramInt1 = paramParcel1.readInt();
        if (paramParcel1.readInt() != 0)
        {
          localObject1 = (Bundle)Bundle.CREATOR.createFromParcel(paramParcel1);
          label255:
          if (paramParcel1.readInt() == 0) {
            break label299;
          }
        }
        label299:
        for (paramParcel1 = (ParcelFileDescriptor)ParcelFileDescriptor.CREATOR.createFromParcel(paramParcel1);; paramParcel1 = null)
        {
          a(paramInt1, (Bundle)localObject1, paramParcel1);
          paramParcel2.writeNoException();
          bool = true;
          break;
          localObject1 = null;
          break label255;
        }
        paramParcel1.enforceInterface("com.google.android.gms.plus.internal.IPlusCallbacks");
        ce(paramParcel1.readString());
        paramParcel2.writeNoException();
        bool = true;
        continue;
        paramParcel1.enforceInterface("com.google.android.gms.plus.internal.IPlusCallbacks");
        if (paramParcel1.readInt() != 0) {
          localObject1 = DataHolder.CREATOR.z(paramParcel1);
        }
        a((DataHolder)localObject1, paramParcel1.readString());
        paramParcel2.writeNoException();
        bool = true;
        continue;
        paramParcel1.enforceInterface("com.google.android.gms.plus.internal.IPlusCallbacks");
        paramInt1 = paramParcel1.readInt();
        if (paramParcel1.readInt() != 0) {}
        for (localObject1 = (Bundle)Bundle.CREATOR.createFromParcel(paramParcel1);; localObject1 = null)
        {
          if (paramParcel1.readInt() != 0) {
            localjp = jp.CREATOR.M(paramParcel1);
          }
          a(paramInt1, (Bundle)localObject1, localjp);
          paramParcel2.writeNoException();
          bool = true;
          break;
        }
        paramParcel1.enforceInterface("com.google.android.gms.plus.internal.IPlusCallbacks");
        localObject1 = localObject2;
        if (paramParcel1.readInt() != 0) {
          localObject1 = DataHolder.CREATOR.z(paramParcel1);
        }
        a((DataHolder)localObject1, paramParcel1.readString(), paramParcel1.readString());
        paramParcel2.writeNoException();
        bool = true;
        continue;
        paramParcel1.enforceInterface("com.google.android.gms.plus.internal.IPlusCallbacks");
        paramInt1 = paramParcel1.readInt();
        if (paramParcel1.readInt() != 0) {}
        for (paramParcel1 = (Bundle)Bundle.CREATOR.createFromParcel(paramParcel1);; paramParcel1 = null)
        {
          h(paramInt1, paramParcel1);
          paramParcel2.writeNoException();
          bool = true;
          break;
        }
        paramParcel1.enforceInterface("com.google.android.gms.plus.internal.IPlusCallbacks");
        cf(paramParcel1.readString());
        paramParcel2.writeNoException();
        bool = true;
        continue;
        paramParcel1.enforceInterface("com.google.android.gms.plus.internal.IPlusCallbacks");
        paramInt1 = paramParcel1.readInt();
        localObject1 = localObject3;
        if (paramParcel1.readInt() != 0) {
          localObject1 = nz.CREATOR.dd(paramParcel1);
        }
        a(paramInt1, (nz)localObject1);
        paramParcel2.writeNoException();
        bool = true;
        continue;
        paramParcel1.enforceInterface("com.google.android.gms.plus.internal.IPlusCallbacks");
        localObject1 = localObject4;
        if (paramParcel1.readInt() != 0) {
          localObject1 = Status.CREATOR.createFromParcel(paramParcel1);
        }
        aB((Status)localObject1);
        paramParcel2.writeNoException();
        bool = true;
      }
    }
    
    private static class a
      implements b
    {
      private IBinder lb;
      
      a(IBinder paramIBinder)
      {
        this.lb = paramIBinder;
      }
      
      public void a(int paramInt, Bundle paramBundle1, Bundle paramBundle2)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.plus.internal.IPlusCallbacks");
            localParcel1.writeInt(paramInt);
            if (paramBundle1 != null)
            {
              localParcel1.writeInt(1);
              paramBundle1.writeToParcel(localParcel1, 0);
              if (paramBundle2 != null)
              {
                localParcel1.writeInt(1);
                paramBundle2.writeToParcel(localParcel1, 0);
                this.lb.transact(1, localParcel1, localParcel2, 0);
                localParcel2.readException();
              }
            }
            else
            {
              localParcel1.writeInt(0);
              continue;
            }
            localParcel1.writeInt(0);
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
        }
      }
      
      public void a(int paramInt, Bundle paramBundle, ParcelFileDescriptor paramParcelFileDescriptor)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.plus.internal.IPlusCallbacks");
            localParcel1.writeInt(paramInt);
            if (paramBundle != null)
            {
              localParcel1.writeInt(1);
              paramBundle.writeToParcel(localParcel1, 0);
              if (paramParcelFileDescriptor != null)
              {
                localParcel1.writeInt(1);
                paramParcelFileDescriptor.writeToParcel(localParcel1, 0);
                this.lb.transact(2, localParcel1, localParcel2, 0);
                localParcel2.readException();
              }
            }
            else
            {
              localParcel1.writeInt(0);
              continue;
            }
            localParcel1.writeInt(0);
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
        }
      }
      
      public void a(int paramInt, Bundle paramBundle, jp paramjp)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.plus.internal.IPlusCallbacks");
            localParcel1.writeInt(paramInt);
            if (paramBundle != null)
            {
              localParcel1.writeInt(1);
              paramBundle.writeToParcel(localParcel1, 0);
              if (paramjp != null)
              {
                localParcel1.writeInt(1);
                paramjp.writeToParcel(localParcel1, 0);
                this.lb.transact(5, localParcel1, localParcel2, 0);
                localParcel2.readException();
              }
            }
            else
            {
              localParcel1.writeInt(0);
              continue;
            }
            localParcel1.writeInt(0);
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
        }
      }
      
      /* Error */
      public void a(int paramInt, nz paramnz)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 28	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore 4
        //   5: invokestatic 28	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   8: astore_3
        //   9: aload 4
        //   11: ldc 30
        //   13: invokevirtual 34	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   16: aload 4
        //   18: iload_1
        //   19: invokevirtual 38	android/os/Parcel:writeInt	(I)V
        //   22: aload_2
        //   23: ifnull +46 -> 69
        //   26: aload 4
        //   28: iconst_1
        //   29: invokevirtual 38	android/os/Parcel:writeInt	(I)V
        //   32: aload_2
        //   33: aload 4
        //   35: iconst_0
        //   36: invokevirtual 69	com/google/android/gms/internal/nz:writeToParcel	(Landroid/os/Parcel;I)V
        //   39: aload_0
        //   40: getfield 18	com/google/android/gms/plus/internal/b$a$a:lb	Landroid/os/IBinder;
        //   43: bipush 9
        //   45: aload 4
        //   47: aload_3
        //   48: iconst_0
        //   49: invokeinterface 50 5 0
        //   54: pop
        //   55: aload_3
        //   56: invokevirtual 53	android/os/Parcel:readException	()V
        //   59: aload_3
        //   60: invokevirtual 56	android/os/Parcel:recycle	()V
        //   63: aload 4
        //   65: invokevirtual 56	android/os/Parcel:recycle	()V
        //   68: return
        //   69: aload 4
        //   71: iconst_0
        //   72: invokevirtual 38	android/os/Parcel:writeInt	(I)V
        //   75: goto -36 -> 39
        //   78: astore_2
        //   79: aload_3
        //   80: invokevirtual 56	android/os/Parcel:recycle	()V
        //   83: aload 4
        //   85: invokevirtual 56	android/os/Parcel:recycle	()V
        //   88: aload_2
        //   89: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	90	0	this	a
        //   0	90	1	paramInt	int
        //   0	90	2	paramnz	nz
        //   8	72	3	localParcel1	Parcel
        //   3	81	4	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   9	22	78	finally
        //   26	39	78	finally
        //   39	59	78	finally
        //   69	75	78	finally
      }
      
      /* Error */
      public void a(DataHolder paramDataHolder, String paramString)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 28	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore_3
        //   4: invokestatic 28	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   7: astore 4
        //   9: aload_3
        //   10: ldc 30
        //   12: invokevirtual 34	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   15: aload_1
        //   16: ifnull +49 -> 65
        //   19: aload_3
        //   20: iconst_1
        //   21: invokevirtual 38	android/os/Parcel:writeInt	(I)V
        //   24: aload_1
        //   25: aload_3
        //   26: iconst_0
        //   27: invokevirtual 73	com/google/android/gms/common/data/DataHolder:writeToParcel	(Landroid/os/Parcel;I)V
        //   30: aload_3
        //   31: aload_2
        //   32: invokevirtual 76	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   35: aload_0
        //   36: getfield 18	com/google/android/gms/plus/internal/b$a$a:lb	Landroid/os/IBinder;
        //   39: iconst_4
        //   40: aload_3
        //   41: aload 4
        //   43: iconst_0
        //   44: invokeinterface 50 5 0
        //   49: pop
        //   50: aload 4
        //   52: invokevirtual 53	android/os/Parcel:readException	()V
        //   55: aload 4
        //   57: invokevirtual 56	android/os/Parcel:recycle	()V
        //   60: aload_3
        //   61: invokevirtual 56	android/os/Parcel:recycle	()V
        //   64: return
        //   65: aload_3
        //   66: iconst_0
        //   67: invokevirtual 38	android/os/Parcel:writeInt	(I)V
        //   70: goto -40 -> 30
        //   73: astore_1
        //   74: aload 4
        //   76: invokevirtual 56	android/os/Parcel:recycle	()V
        //   79: aload_3
        //   80: invokevirtual 56	android/os/Parcel:recycle	()V
        //   83: aload_1
        //   84: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	85	0	this	a
        //   0	85	1	paramDataHolder	DataHolder
        //   0	85	2	paramString	String
        //   3	77	3	localParcel1	Parcel
        //   7	68	4	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   9	15	73	finally
        //   19	30	73	finally
        //   30	55	73	finally
        //   65	70	73	finally
      }
      
      /* Error */
      public void a(DataHolder paramDataHolder, String paramString1, String paramString2)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 28	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore 4
        //   5: invokestatic 28	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   8: astore 5
        //   10: aload 4
        //   12: ldc 30
        //   14: invokevirtual 34	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   17: aload_1
        //   18: ifnull +61 -> 79
        //   21: aload 4
        //   23: iconst_1
        //   24: invokevirtual 38	android/os/Parcel:writeInt	(I)V
        //   27: aload_1
        //   28: aload 4
        //   30: iconst_0
        //   31: invokevirtual 73	com/google/android/gms/common/data/DataHolder:writeToParcel	(Landroid/os/Parcel;I)V
        //   34: aload 4
        //   36: aload_2
        //   37: invokevirtual 76	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   40: aload 4
        //   42: aload_3
        //   43: invokevirtual 76	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   46: aload_0
        //   47: getfield 18	com/google/android/gms/plus/internal/b$a$a:lb	Landroid/os/IBinder;
        //   50: bipush 6
        //   52: aload 4
        //   54: aload 5
        //   56: iconst_0
        //   57: invokeinterface 50 5 0
        //   62: pop
        //   63: aload 5
        //   65: invokevirtual 53	android/os/Parcel:readException	()V
        //   68: aload 5
        //   70: invokevirtual 56	android/os/Parcel:recycle	()V
        //   73: aload 4
        //   75: invokevirtual 56	android/os/Parcel:recycle	()V
        //   78: return
        //   79: aload 4
        //   81: iconst_0
        //   82: invokevirtual 38	android/os/Parcel:writeInt	(I)V
        //   85: goto -51 -> 34
        //   88: astore_1
        //   89: aload 5
        //   91: invokevirtual 56	android/os/Parcel:recycle	()V
        //   94: aload 4
        //   96: invokevirtual 56	android/os/Parcel:recycle	()V
        //   99: aload_1
        //   100: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	101	0	this	a
        //   0	101	1	paramDataHolder	DataHolder
        //   0	101	2	paramString1	String
        //   0	101	3	paramString2	String
        //   3	92	4	localParcel1	Parcel
        //   8	82	5	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   10	17	88	finally
        //   21	34	88	finally
        //   34	68	88	finally
        //   79	85	88	finally
      }
      
      /* Error */
      public void aB(Status paramStatus)
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
        //   15: ifnull +42 -> 57
        //   18: aload_3
        //   19: iconst_1
        //   20: invokevirtual 38	android/os/Parcel:writeInt	(I)V
        //   23: aload_1
        //   24: aload_3
        //   25: iconst_0
        //   26: invokevirtual 82	com/google/android/gms/common/api/Status:writeToParcel	(Landroid/os/Parcel;I)V
        //   29: aload_0
        //   30: getfield 18	com/google/android/gms/plus/internal/b$a$a:lb	Landroid/os/IBinder;
        //   33: bipush 10
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
        //   57: aload_3
        //   58: iconst_0
        //   59: invokevirtual 38	android/os/Parcel:writeInt	(I)V
        //   62: goto -33 -> 29
        //   65: astore_1
        //   66: aload_2
        //   67: invokevirtual 56	android/os/Parcel:recycle	()V
        //   70: aload_3
        //   71: invokevirtual 56	android/os/Parcel:recycle	()V
        //   74: aload_1
        //   75: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	76	0	this	a
        //   0	76	1	paramStatus	Status
        //   7	60	2	localParcel1	Parcel
        //   3	68	3	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   8	14	65	finally
        //   18	29	65	finally
        //   29	48	65	finally
        //   57	62	65	finally
      }
      
      public IBinder asBinder()
      {
        return this.lb;
      }
      
      public void ce(String paramString)
        throws RemoteException
      {
        Parcel localParcel2 = Parcel.obtain();
        Parcel localParcel1 = Parcel.obtain();
        try
        {
          localParcel2.writeInterfaceToken("com.google.android.gms.plus.internal.IPlusCallbacks");
          localParcel2.writeString(paramString);
          this.lb.transact(3, localParcel2, localParcel1, 0);
          localParcel1.readException();
          return;
        }
        finally
        {
          localParcel1.recycle();
          localParcel2.recycle();
        }
      }
      
      public void cf(String paramString)
        throws RemoteException
      {
        Parcel localParcel2 = Parcel.obtain();
        Parcel localParcel1 = Parcel.obtain();
        try
        {
          localParcel2.writeInterfaceToken("com.google.android.gms.plus.internal.IPlusCallbacks");
          localParcel2.writeString(paramString);
          this.lb.transact(8, localParcel2, localParcel1, 0);
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
      public void h(int paramInt, Bundle paramBundle)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 28	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore 4
        //   5: invokestatic 28	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   8: astore_3
        //   9: aload 4
        //   11: ldc 30
        //   13: invokevirtual 34	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   16: aload 4
        //   18: iload_1
        //   19: invokevirtual 38	android/os/Parcel:writeInt	(I)V
        //   22: aload_2
        //   23: ifnull +46 -> 69
        //   26: aload 4
        //   28: iconst_1
        //   29: invokevirtual 38	android/os/Parcel:writeInt	(I)V
        //   32: aload_2
        //   33: aload 4
        //   35: iconst_0
        //   36: invokevirtual 44	android/os/Bundle:writeToParcel	(Landroid/os/Parcel;I)V
        //   39: aload_0
        //   40: getfield 18	com/google/android/gms/plus/internal/b$a$a:lb	Landroid/os/IBinder;
        //   43: bipush 7
        //   45: aload 4
        //   47: aload_3
        //   48: iconst_0
        //   49: invokeinterface 50 5 0
        //   54: pop
        //   55: aload_3
        //   56: invokevirtual 53	android/os/Parcel:readException	()V
        //   59: aload_3
        //   60: invokevirtual 56	android/os/Parcel:recycle	()V
        //   63: aload 4
        //   65: invokevirtual 56	android/os/Parcel:recycle	()V
        //   68: return
        //   69: aload 4
        //   71: iconst_0
        //   72: invokevirtual 38	android/os/Parcel:writeInt	(I)V
        //   75: goto -36 -> 39
        //   78: astore_2
        //   79: aload_3
        //   80: invokevirtual 56	android/os/Parcel:recycle	()V
        //   83: aload 4
        //   85: invokevirtual 56	android/os/Parcel:recycle	()V
        //   88: aload_2
        //   89: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	90	0	this	a
        //   0	90	1	paramInt	int
        //   0	90	2	paramBundle	Bundle
        //   8	72	3	localParcel1	Parcel
        //   3	81	4	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   9	22	78	finally
        //   26	39	78	finally
        //   39	59	78	finally
        //   69	75	78	finally
      }
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\plus\internal\b.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */