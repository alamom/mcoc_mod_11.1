package com.google.android.gms.wearable.internal;

import android.net.Uri;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.os.RemoteException;
import com.google.android.gms.wearable.Asset;
import com.google.android.gms.wearable.PutDataRequest;
import com.google.android.gms.wearable.c;

public abstract interface af
  extends IInterface
{
  public abstract void a(ad paramad)
    throws RemoteException;
  
  public abstract void a(ad paramad, Uri paramUri)
    throws RemoteException;
  
  public abstract void a(ad paramad, Asset paramAsset)
    throws RemoteException;
  
  public abstract void a(ad paramad, PutDataRequest paramPutDataRequest)
    throws RemoteException;
  
  public abstract void a(ad paramad, c paramc)
    throws RemoteException;
  
  public abstract void a(ad paramad, aq paramaq)
    throws RemoteException;
  
  public abstract void a(ad paramad, b paramb)
    throws RemoteException;
  
  public abstract void a(ad paramad, String paramString)
    throws RemoteException;
  
  public abstract void a(ad paramad, String paramString1, String paramString2, byte[] paramArrayOfByte)
    throws RemoteException;
  
  public abstract void b(ad paramad)
    throws RemoteException;
  
  public abstract void b(ad paramad, Uri paramUri)
    throws RemoteException;
  
  public abstract void b(ad paramad, c paramc)
    throws RemoteException;
  
  public abstract void b(ad paramad, String paramString)
    throws RemoteException;
  
  public abstract void c(ad paramad)
    throws RemoteException;
  
  public abstract void c(ad paramad, Uri paramUri)
    throws RemoteException;
  
  public abstract void c(ad paramad, String paramString)
    throws RemoteException;
  
  public abstract void d(ad paramad)
    throws RemoteException;
  
  public abstract void e(ad paramad)
    throws RemoteException;
  
  public abstract void f(ad paramad)
    throws RemoteException;
  
  public abstract void g(ad paramad)
    throws RemoteException;
  
  public abstract void h(ad paramad)
    throws RemoteException;
  
  public abstract void i(ad paramad)
    throws RemoteException;
  
  public static abstract class a
    extends Binder
    implements af
  {
    public static af bT(IBinder paramIBinder)
    {
      if (paramIBinder == null) {
        paramIBinder = null;
      }
      for (;;)
      {
        return paramIBinder;
        IInterface localIInterface = paramIBinder.queryLocalInterface("com.google.android.gms.wearable.internal.IWearableService");
        if ((localIInterface != null) && ((localIInterface instanceof af))) {
          paramIBinder = (af)localIInterface;
        } else {
          paramIBinder = new a(paramIBinder);
        }
      }
    }
    
    public boolean onTransact(int paramInt1, Parcel paramParcel1, Parcel paramParcel2, int paramInt2)
      throws RemoteException
    {
      ad localad1 = null;
      ad localad2 = null;
      Object localObject2 = null;
      Object localObject3 = null;
      Object localObject4 = null;
      Object localObject5 = null;
      Object localObject6 = null;
      Object localObject7 = null;
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
        paramParcel2.writeString("com.google.android.gms.wearable.internal.IWearableService");
        bool = true;
        continue;
        paramParcel1.enforceInterface("com.google.android.gms.wearable.internal.IWearableService");
        localad1 = ad.a.bR(paramParcel1.readStrongBinder());
        if (paramParcel1.readInt() != 0) {
          localObject1 = (c)c.CREATOR.createFromParcel(paramParcel1);
        }
        a(localad1, (c)localObject1);
        paramParcel2.writeNoException();
        bool = true;
        continue;
        paramParcel1.enforceInterface("com.google.android.gms.wearable.internal.IWearableService");
        a(ad.a.bR(paramParcel1.readStrongBinder()), paramParcel1.readString());
        paramParcel2.writeNoException();
        bool = true;
        continue;
        paramParcel1.enforceInterface("com.google.android.gms.wearable.internal.IWearableService");
        a(ad.a.bR(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        bool = true;
        continue;
        paramParcel1.enforceInterface("com.google.android.gms.wearable.internal.IWearableService");
        b(ad.a.bR(paramParcel1.readStrongBinder()), paramParcel1.readString());
        paramParcel2.writeNoException();
        bool = true;
        continue;
        paramParcel1.enforceInterface("com.google.android.gms.wearable.internal.IWearableService");
        c(ad.a.bR(paramParcel1.readStrongBinder()), paramParcel1.readString());
        paramParcel2.writeNoException();
        bool = true;
        continue;
        paramParcel1.enforceInterface("com.google.android.gms.wearable.internal.IWearableService");
        localad2 = ad.a.bR(paramParcel1.readStrongBinder());
        localObject1 = localad1;
        if (paramParcel1.readInt() != 0) {
          localObject1 = (PutDataRequest)PutDataRequest.CREATOR.createFromParcel(paramParcel1);
        }
        a(localad2, (PutDataRequest)localObject1);
        paramParcel2.writeNoException();
        bool = true;
        continue;
        paramParcel1.enforceInterface("com.google.android.gms.wearable.internal.IWearableService");
        localad1 = ad.a.bR(paramParcel1.readStrongBinder());
        localObject1 = localad2;
        if (paramParcel1.readInt() != 0) {
          localObject1 = (Uri)Uri.CREATOR.createFromParcel(paramParcel1);
        }
        a(localad1, (Uri)localObject1);
        paramParcel2.writeNoException();
        bool = true;
        continue;
        paramParcel1.enforceInterface("com.google.android.gms.wearable.internal.IWearableService");
        b(ad.a.bR(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        bool = true;
        continue;
        paramParcel1.enforceInterface("com.google.android.gms.wearable.internal.IWearableService");
        localad1 = ad.a.bR(paramParcel1.readStrongBinder());
        localObject1 = localObject2;
        if (paramParcel1.readInt() != 0) {
          localObject1 = (Uri)Uri.CREATOR.createFromParcel(paramParcel1);
        }
        b(localad1, (Uri)localObject1);
        paramParcel2.writeNoException();
        bool = true;
        continue;
        paramParcel1.enforceInterface("com.google.android.gms.wearable.internal.IWearableService");
        localad1 = ad.a.bR(paramParcel1.readStrongBinder());
        localObject1 = localObject3;
        if (paramParcel1.readInt() != 0) {
          localObject1 = (Uri)Uri.CREATOR.createFromParcel(paramParcel1);
        }
        c(localad1, (Uri)localObject1);
        paramParcel2.writeNoException();
        bool = true;
        continue;
        paramParcel1.enforceInterface("com.google.android.gms.wearable.internal.IWearableService");
        a(ad.a.bR(paramParcel1.readStrongBinder()), paramParcel1.readString(), paramParcel1.readString(), paramParcel1.createByteArray());
        paramParcel2.writeNoException();
        bool = true;
        continue;
        paramParcel1.enforceInterface("com.google.android.gms.wearable.internal.IWearableService");
        localad1 = ad.a.bR(paramParcel1.readStrongBinder());
        localObject1 = localObject4;
        if (paramParcel1.readInt() != 0) {
          localObject1 = (Asset)Asset.CREATOR.createFromParcel(paramParcel1);
        }
        a(localad1, (Asset)localObject1);
        paramParcel2.writeNoException();
        bool = true;
        continue;
        paramParcel1.enforceInterface("com.google.android.gms.wearable.internal.IWearableService");
        c(ad.a.bR(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        bool = true;
        continue;
        paramParcel1.enforceInterface("com.google.android.gms.wearable.internal.IWearableService");
        d(ad.a.bR(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        bool = true;
        continue;
        paramParcel1.enforceInterface("com.google.android.gms.wearable.internal.IWearableService");
        localad1 = ad.a.bR(paramParcel1.readStrongBinder());
        localObject1 = localObject5;
        if (paramParcel1.readInt() != 0) {
          localObject1 = (b)b.CREATOR.createFromParcel(paramParcel1);
        }
        a(localad1, (b)localObject1);
        paramParcel2.writeNoException();
        bool = true;
        continue;
        paramParcel1.enforceInterface("com.google.android.gms.wearable.internal.IWearableService");
        localad1 = ad.a.bR(paramParcel1.readStrongBinder());
        localObject1 = localObject6;
        if (paramParcel1.readInt() != 0) {
          localObject1 = (aq)aq.CREATOR.createFromParcel(paramParcel1);
        }
        a(localad1, (aq)localObject1);
        paramParcel2.writeNoException();
        bool = true;
        continue;
        paramParcel1.enforceInterface("com.google.android.gms.wearable.internal.IWearableService");
        e(ad.a.bR(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        bool = true;
        continue;
        paramParcel1.enforceInterface("com.google.android.gms.wearable.internal.IWearableService");
        f(ad.a.bR(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        bool = true;
        continue;
        paramParcel1.enforceInterface("com.google.android.gms.wearable.internal.IWearableService");
        localad1 = ad.a.bR(paramParcel1.readStrongBinder());
        localObject1 = localObject7;
        if (paramParcel1.readInt() != 0) {
          localObject1 = (c)c.CREATOR.createFromParcel(paramParcel1);
        }
        b(localad1, (c)localObject1);
        paramParcel2.writeNoException();
        bool = true;
        continue;
        paramParcel1.enforceInterface("com.google.android.gms.wearable.internal.IWearableService");
        g(ad.a.bR(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        bool = true;
        continue;
        paramParcel1.enforceInterface("com.google.android.gms.wearable.internal.IWearableService");
        h(ad.a.bR(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        bool = true;
        continue;
        paramParcel1.enforceInterface("com.google.android.gms.wearable.internal.IWearableService");
        i(ad.a.bR(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        bool = true;
      }
    }
    
    private static class a
      implements af
    {
      private IBinder lb;
      
      a(IBinder paramIBinder)
      {
        this.lb = paramIBinder;
      }
      
      /* Error */
      public void a(ad paramad)
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
        //   15: ifnull +43 -> 58
        //   18: aload_1
        //   19: invokeinterface 40 1 0
        //   24: astore_1
        //   25: aload_3
        //   26: aload_1
        //   27: invokevirtual 43	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   30: aload_0
        //   31: getfield 18	com/google/android/gms/wearable/internal/af$a$a:lb	Landroid/os/IBinder;
        //   34: bipush 22
        //   36: aload_3
        //   37: aload_2
        //   38: iconst_0
        //   39: invokeinterface 49 5 0
        //   44: pop
        //   45: aload_2
        //   46: invokevirtual 52	android/os/Parcel:readException	()V
        //   49: aload_2
        //   50: invokevirtual 55	android/os/Parcel:recycle	()V
        //   53: aload_3
        //   54: invokevirtual 55	android/os/Parcel:recycle	()V
        //   57: return
        //   58: aconst_null
        //   59: astore_1
        //   60: goto -35 -> 25
        //   63: astore_1
        //   64: aload_2
        //   65: invokevirtual 55	android/os/Parcel:recycle	()V
        //   68: aload_3
        //   69: invokevirtual 55	android/os/Parcel:recycle	()V
        //   72: aload_1
        //   73: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	74	0	this	a
        //   0	74	1	paramad	ad
        //   7	58	2	localParcel1	Parcel
        //   3	66	3	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   8	14	63	finally
        //   18	25	63	finally
        //   25	49	63	finally
      }
      
      /* Error */
      public void a(ad paramad, Uri paramUri)
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
        //   16: aload_1
        //   17: ifnull +63 -> 80
        //   20: aload_1
        //   21: invokeinterface 40 1 0
        //   26: astore_1
        //   27: aload 4
        //   29: aload_1
        //   30: invokevirtual 43	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   33: aload_2
        //   34: ifnull +51 -> 85
        //   37: aload 4
        //   39: iconst_1
        //   40: invokevirtual 61	android/os/Parcel:writeInt	(I)V
        //   43: aload_2
        //   44: aload 4
        //   46: iconst_0
        //   47: invokevirtual 67	android/net/Uri:writeToParcel	(Landroid/os/Parcel;I)V
        //   50: aload_0
        //   51: getfield 18	com/google/android/gms/wearable/internal/af$a$a:lb	Landroid/os/IBinder;
        //   54: bipush 7
        //   56: aload 4
        //   58: aload_3
        //   59: iconst_0
        //   60: invokeinterface 49 5 0
        //   65: pop
        //   66: aload_3
        //   67: invokevirtual 52	android/os/Parcel:readException	()V
        //   70: aload_3
        //   71: invokevirtual 55	android/os/Parcel:recycle	()V
        //   74: aload 4
        //   76: invokevirtual 55	android/os/Parcel:recycle	()V
        //   79: return
        //   80: aconst_null
        //   81: astore_1
        //   82: goto -55 -> 27
        //   85: aload 4
        //   87: iconst_0
        //   88: invokevirtual 61	android/os/Parcel:writeInt	(I)V
        //   91: goto -41 -> 50
        //   94: astore_1
        //   95: aload_3
        //   96: invokevirtual 55	android/os/Parcel:recycle	()V
        //   99: aload 4
        //   101: invokevirtual 55	android/os/Parcel:recycle	()V
        //   104: aload_1
        //   105: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	106	0	this	a
        //   0	106	1	paramad	ad
        //   0	106	2	paramUri	Uri
        //   8	88	3	localParcel1	Parcel
        //   3	97	4	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   9	16	94	finally
        //   20	27	94	finally
        //   27	33	94	finally
        //   37	50	94	finally
        //   50	70	94	finally
        //   85	91	94	finally
      }
      
      /* Error */
      public void a(ad paramad, Asset paramAsset)
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
        //   16: aload_1
        //   17: ifnull +63 -> 80
        //   20: aload_1
        //   21: invokeinterface 40 1 0
        //   26: astore_1
        //   27: aload 4
        //   29: aload_1
        //   30: invokevirtual 43	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   33: aload_2
        //   34: ifnull +51 -> 85
        //   37: aload 4
        //   39: iconst_1
        //   40: invokevirtual 61	android/os/Parcel:writeInt	(I)V
        //   43: aload_2
        //   44: aload 4
        //   46: iconst_0
        //   47: invokevirtual 71	com/google/android/gms/wearable/Asset:writeToParcel	(Landroid/os/Parcel;I)V
        //   50: aload_0
        //   51: getfield 18	com/google/android/gms/wearable/internal/af$a$a:lb	Landroid/os/IBinder;
        //   54: bipush 13
        //   56: aload 4
        //   58: aload_3
        //   59: iconst_0
        //   60: invokeinterface 49 5 0
        //   65: pop
        //   66: aload_3
        //   67: invokevirtual 52	android/os/Parcel:readException	()V
        //   70: aload_3
        //   71: invokevirtual 55	android/os/Parcel:recycle	()V
        //   74: aload 4
        //   76: invokevirtual 55	android/os/Parcel:recycle	()V
        //   79: return
        //   80: aconst_null
        //   81: astore_1
        //   82: goto -55 -> 27
        //   85: aload 4
        //   87: iconst_0
        //   88: invokevirtual 61	android/os/Parcel:writeInt	(I)V
        //   91: goto -41 -> 50
        //   94: astore_1
        //   95: aload_3
        //   96: invokevirtual 55	android/os/Parcel:recycle	()V
        //   99: aload 4
        //   101: invokevirtual 55	android/os/Parcel:recycle	()V
        //   104: aload_1
        //   105: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	106	0	this	a
        //   0	106	1	paramad	ad
        //   0	106	2	paramAsset	Asset
        //   8	88	3	localParcel1	Parcel
        //   3	97	4	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   9	16	94	finally
        //   20	27	94	finally
        //   27	33	94	finally
        //   37	50	94	finally
        //   50	70	94	finally
        //   85	91	94	finally
      }
      
      /* Error */
      public void a(ad paramad, PutDataRequest paramPutDataRequest)
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
        //   16: aload_1
        //   17: ifnull +63 -> 80
        //   20: aload_1
        //   21: invokeinterface 40 1 0
        //   26: astore_1
        //   27: aload 4
        //   29: aload_1
        //   30: invokevirtual 43	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   33: aload_2
        //   34: ifnull +51 -> 85
        //   37: aload 4
        //   39: iconst_1
        //   40: invokevirtual 61	android/os/Parcel:writeInt	(I)V
        //   43: aload_2
        //   44: aload 4
        //   46: iconst_0
        //   47: invokevirtual 75	com/google/android/gms/wearable/PutDataRequest:writeToParcel	(Landroid/os/Parcel;I)V
        //   50: aload_0
        //   51: getfield 18	com/google/android/gms/wearable/internal/af$a$a:lb	Landroid/os/IBinder;
        //   54: bipush 6
        //   56: aload 4
        //   58: aload_3
        //   59: iconst_0
        //   60: invokeinterface 49 5 0
        //   65: pop
        //   66: aload_3
        //   67: invokevirtual 52	android/os/Parcel:readException	()V
        //   70: aload_3
        //   71: invokevirtual 55	android/os/Parcel:recycle	()V
        //   74: aload 4
        //   76: invokevirtual 55	android/os/Parcel:recycle	()V
        //   79: return
        //   80: aconst_null
        //   81: astore_1
        //   82: goto -55 -> 27
        //   85: aload 4
        //   87: iconst_0
        //   88: invokevirtual 61	android/os/Parcel:writeInt	(I)V
        //   91: goto -41 -> 50
        //   94: astore_1
        //   95: aload_3
        //   96: invokevirtual 55	android/os/Parcel:recycle	()V
        //   99: aload 4
        //   101: invokevirtual 55	android/os/Parcel:recycle	()V
        //   104: aload_1
        //   105: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	106	0	this	a
        //   0	106	1	paramad	ad
        //   0	106	2	paramPutDataRequest	PutDataRequest
        //   8	88	3	localParcel1	Parcel
        //   3	97	4	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   9	16	94	finally
        //   20	27	94	finally
        //   27	33	94	finally
        //   37	50	94	finally
        //   50	70	94	finally
        //   85	91	94	finally
      }
      
      /* Error */
      public void a(ad paramad, c paramc)
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
        //   16: ifnull +61 -> 77
        //   19: aload_1
        //   20: invokeinterface 40 1 0
        //   25: astore_1
        //   26: aload_3
        //   27: aload_1
        //   28: invokevirtual 43	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   31: aload_2
        //   32: ifnull +50 -> 82
        //   35: aload_3
        //   36: iconst_1
        //   37: invokevirtual 61	android/os/Parcel:writeInt	(I)V
        //   40: aload_2
        //   41: aload_3
        //   42: iconst_0
        //   43: invokevirtual 79	com/google/android/gms/wearable/c:writeToParcel	(Landroid/os/Parcel;I)V
        //   46: aload_0
        //   47: getfield 18	com/google/android/gms/wearable/internal/af$a$a:lb	Landroid/os/IBinder;
        //   50: bipush 20
        //   52: aload_3
        //   53: aload 4
        //   55: iconst_0
        //   56: invokeinterface 49 5 0
        //   61: pop
        //   62: aload 4
        //   64: invokevirtual 52	android/os/Parcel:readException	()V
        //   67: aload 4
        //   69: invokevirtual 55	android/os/Parcel:recycle	()V
        //   72: aload_3
        //   73: invokevirtual 55	android/os/Parcel:recycle	()V
        //   76: return
        //   77: aconst_null
        //   78: astore_1
        //   79: goto -53 -> 26
        //   82: aload_3
        //   83: iconst_0
        //   84: invokevirtual 61	android/os/Parcel:writeInt	(I)V
        //   87: goto -41 -> 46
        //   90: astore_1
        //   91: aload 4
        //   93: invokevirtual 55	android/os/Parcel:recycle	()V
        //   96: aload_3
        //   97: invokevirtual 55	android/os/Parcel:recycle	()V
        //   100: aload_1
        //   101: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	102	0	this	a
        //   0	102	1	paramad	ad
        //   0	102	2	paramc	c
        //   3	94	3	localParcel1	Parcel
        //   7	85	4	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   9	15	90	finally
        //   19	26	90	finally
        //   26	31	90	finally
        //   35	46	90	finally
        //   46	67	90	finally
        //   82	87	90	finally
      }
      
      /* Error */
      public void a(ad paramad, aq paramaq)
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
        //   16: aload_1
        //   17: ifnull +63 -> 80
        //   20: aload_1
        //   21: invokeinterface 40 1 0
        //   26: astore_1
        //   27: aload 4
        //   29: aload_1
        //   30: invokevirtual 43	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   33: aload_2
        //   34: ifnull +51 -> 85
        //   37: aload 4
        //   39: iconst_1
        //   40: invokevirtual 61	android/os/Parcel:writeInt	(I)V
        //   43: aload_2
        //   44: aload 4
        //   46: iconst_0
        //   47: invokevirtual 83	com/google/android/gms/wearable/internal/aq:writeToParcel	(Landroid/os/Parcel;I)V
        //   50: aload_0
        //   51: getfield 18	com/google/android/gms/wearable/internal/af$a$a:lb	Landroid/os/IBinder;
        //   54: bipush 17
        //   56: aload 4
        //   58: aload_3
        //   59: iconst_0
        //   60: invokeinterface 49 5 0
        //   65: pop
        //   66: aload_3
        //   67: invokevirtual 52	android/os/Parcel:readException	()V
        //   70: aload_3
        //   71: invokevirtual 55	android/os/Parcel:recycle	()V
        //   74: aload 4
        //   76: invokevirtual 55	android/os/Parcel:recycle	()V
        //   79: return
        //   80: aconst_null
        //   81: astore_1
        //   82: goto -55 -> 27
        //   85: aload 4
        //   87: iconst_0
        //   88: invokevirtual 61	android/os/Parcel:writeInt	(I)V
        //   91: goto -41 -> 50
        //   94: astore_1
        //   95: aload_3
        //   96: invokevirtual 55	android/os/Parcel:recycle	()V
        //   99: aload 4
        //   101: invokevirtual 55	android/os/Parcel:recycle	()V
        //   104: aload_1
        //   105: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	106	0	this	a
        //   0	106	1	paramad	ad
        //   0	106	2	paramaq	aq
        //   8	88	3	localParcel1	Parcel
        //   3	97	4	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   9	16	94	finally
        //   20	27	94	finally
        //   27	33	94	finally
        //   37	50	94	finally
        //   50	70	94	finally
        //   85	91	94	finally
      }
      
      /* Error */
      public void a(ad paramad, b paramb)
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
        //   16: ifnull +61 -> 77
        //   19: aload_1
        //   20: invokeinterface 40 1 0
        //   25: astore_1
        //   26: aload_3
        //   27: aload_1
        //   28: invokevirtual 43	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   31: aload_2
        //   32: ifnull +50 -> 82
        //   35: aload_3
        //   36: iconst_1
        //   37: invokevirtual 61	android/os/Parcel:writeInt	(I)V
        //   40: aload_2
        //   41: aload_3
        //   42: iconst_0
        //   43: invokevirtual 87	com/google/android/gms/wearable/internal/b:writeToParcel	(Landroid/os/Parcel;I)V
        //   46: aload_0
        //   47: getfield 18	com/google/android/gms/wearable/internal/af$a$a:lb	Landroid/os/IBinder;
        //   50: bipush 16
        //   52: aload_3
        //   53: aload 4
        //   55: iconst_0
        //   56: invokeinterface 49 5 0
        //   61: pop
        //   62: aload 4
        //   64: invokevirtual 52	android/os/Parcel:readException	()V
        //   67: aload 4
        //   69: invokevirtual 55	android/os/Parcel:recycle	()V
        //   72: aload_3
        //   73: invokevirtual 55	android/os/Parcel:recycle	()V
        //   76: return
        //   77: aconst_null
        //   78: astore_1
        //   79: goto -53 -> 26
        //   82: aload_3
        //   83: iconst_0
        //   84: invokevirtual 61	android/os/Parcel:writeInt	(I)V
        //   87: goto -41 -> 46
        //   90: astore_1
        //   91: aload 4
        //   93: invokevirtual 55	android/os/Parcel:recycle	()V
        //   96: aload_3
        //   97: invokevirtual 55	android/os/Parcel:recycle	()V
        //   100: aload_1
        //   101: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	102	0	this	a
        //   0	102	1	paramad	ad
        //   0	102	2	paramb	b
        //   3	94	3	localParcel1	Parcel
        //   7	85	4	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   9	15	90	finally
        //   19	26	90	finally
        //   26	31	90	finally
        //   35	46	90	finally
        //   46	67	90	finally
        //   82	87	90	finally
      }
      
      /* Error */
      public void a(ad paramad, String paramString)
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
        //   16: aload_1
        //   17: ifnull +52 -> 69
        //   20: aload_1
        //   21: invokeinterface 40 1 0
        //   26: astore_1
        //   27: aload 4
        //   29: aload_1
        //   30: invokevirtual 43	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   33: aload 4
        //   35: aload_2
        //   36: invokevirtual 91	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   39: aload_0
        //   40: getfield 18	com/google/android/gms/wearable/internal/af$a$a:lb	Landroid/os/IBinder;
        //   43: bipush 21
        //   45: aload 4
        //   47: aload_3
        //   48: iconst_0
        //   49: invokeinterface 49 5 0
        //   54: pop
        //   55: aload_3
        //   56: invokevirtual 52	android/os/Parcel:readException	()V
        //   59: aload_3
        //   60: invokevirtual 55	android/os/Parcel:recycle	()V
        //   63: aload 4
        //   65: invokevirtual 55	android/os/Parcel:recycle	()V
        //   68: return
        //   69: aconst_null
        //   70: astore_1
        //   71: goto -44 -> 27
        //   74: astore_1
        //   75: aload_3
        //   76: invokevirtual 55	android/os/Parcel:recycle	()V
        //   79: aload 4
        //   81: invokevirtual 55	android/os/Parcel:recycle	()V
        //   84: aload_1
        //   85: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	86	0	this	a
        //   0	86	1	paramad	ad
        //   0	86	2	paramString	String
        //   8	68	3	localParcel1	Parcel
        //   3	77	4	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   9	16	74	finally
        //   20	27	74	finally
        //   27	59	74	finally
      }
      
      /* Error */
      public void a(ad paramad, String paramString1, String paramString2, byte[] paramArrayOfByte)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 28	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore 5
        //   5: invokestatic 28	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   8: astore 6
        //   10: aload 5
        //   12: ldc 30
        //   14: invokevirtual 34	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   17: aload_1
        //   18: ifnull +68 -> 86
        //   21: aload_1
        //   22: invokeinterface 40 1 0
        //   27: astore_1
        //   28: aload 5
        //   30: aload_1
        //   31: invokevirtual 43	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   34: aload 5
        //   36: aload_2
        //   37: invokevirtual 91	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   40: aload 5
        //   42: aload_3
        //   43: invokevirtual 91	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   46: aload 5
        //   48: aload 4
        //   50: invokevirtual 96	android/os/Parcel:writeByteArray	([B)V
        //   53: aload_0
        //   54: getfield 18	com/google/android/gms/wearable/internal/af$a$a:lb	Landroid/os/IBinder;
        //   57: bipush 12
        //   59: aload 5
        //   61: aload 6
        //   63: iconst_0
        //   64: invokeinterface 49 5 0
        //   69: pop
        //   70: aload 6
        //   72: invokevirtual 52	android/os/Parcel:readException	()V
        //   75: aload 6
        //   77: invokevirtual 55	android/os/Parcel:recycle	()V
        //   80: aload 5
        //   82: invokevirtual 55	android/os/Parcel:recycle	()V
        //   85: return
        //   86: aconst_null
        //   87: astore_1
        //   88: goto -60 -> 28
        //   91: astore_1
        //   92: aload 6
        //   94: invokevirtual 55	android/os/Parcel:recycle	()V
        //   97: aload 5
        //   99: invokevirtual 55	android/os/Parcel:recycle	()V
        //   102: aload_1
        //   103: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	104	0	this	a
        //   0	104	1	paramad	ad
        //   0	104	2	paramString1	String
        //   0	104	3	paramString2	String
        //   0	104	4	paramArrayOfByte	byte[]
        //   3	95	5	localParcel1	Parcel
        //   8	85	6	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   10	17	91	finally
        //   21	28	91	finally
        //   28	75	91	finally
      }
      
      public IBinder asBinder()
      {
        return this.lb;
      }
      
      /* Error */
      public void b(ad paramad)
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
        //   15: ifnull +43 -> 58
        //   18: aload_1
        //   19: invokeinterface 40 1 0
        //   24: astore_1
        //   25: aload_3
        //   26: aload_1
        //   27: invokevirtual 43	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   30: aload_0
        //   31: getfield 18	com/google/android/gms/wearable/internal/af$a$a:lb	Landroid/os/IBinder;
        //   34: bipush 8
        //   36: aload_3
        //   37: aload_2
        //   38: iconst_0
        //   39: invokeinterface 49 5 0
        //   44: pop
        //   45: aload_2
        //   46: invokevirtual 52	android/os/Parcel:readException	()V
        //   49: aload_2
        //   50: invokevirtual 55	android/os/Parcel:recycle	()V
        //   53: aload_3
        //   54: invokevirtual 55	android/os/Parcel:recycle	()V
        //   57: return
        //   58: aconst_null
        //   59: astore_1
        //   60: goto -35 -> 25
        //   63: astore_1
        //   64: aload_2
        //   65: invokevirtual 55	android/os/Parcel:recycle	()V
        //   68: aload_3
        //   69: invokevirtual 55	android/os/Parcel:recycle	()V
        //   72: aload_1
        //   73: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	74	0	this	a
        //   0	74	1	paramad	ad
        //   7	58	2	localParcel1	Parcel
        //   3	66	3	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   8	14	63	finally
        //   18	25	63	finally
        //   25	49	63	finally
      }
      
      /* Error */
      public void b(ad paramad, Uri paramUri)
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
        //   16: ifnull +61 -> 77
        //   19: aload_1
        //   20: invokeinterface 40 1 0
        //   25: astore_1
        //   26: aload_3
        //   27: aload_1
        //   28: invokevirtual 43	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   31: aload_2
        //   32: ifnull +50 -> 82
        //   35: aload_3
        //   36: iconst_1
        //   37: invokevirtual 61	android/os/Parcel:writeInt	(I)V
        //   40: aload_2
        //   41: aload_3
        //   42: iconst_0
        //   43: invokevirtual 67	android/net/Uri:writeToParcel	(Landroid/os/Parcel;I)V
        //   46: aload_0
        //   47: getfield 18	com/google/android/gms/wearable/internal/af$a$a:lb	Landroid/os/IBinder;
        //   50: bipush 9
        //   52: aload_3
        //   53: aload 4
        //   55: iconst_0
        //   56: invokeinterface 49 5 0
        //   61: pop
        //   62: aload 4
        //   64: invokevirtual 52	android/os/Parcel:readException	()V
        //   67: aload 4
        //   69: invokevirtual 55	android/os/Parcel:recycle	()V
        //   72: aload_3
        //   73: invokevirtual 55	android/os/Parcel:recycle	()V
        //   76: return
        //   77: aconst_null
        //   78: astore_1
        //   79: goto -53 -> 26
        //   82: aload_3
        //   83: iconst_0
        //   84: invokevirtual 61	android/os/Parcel:writeInt	(I)V
        //   87: goto -41 -> 46
        //   90: astore_1
        //   91: aload 4
        //   93: invokevirtual 55	android/os/Parcel:recycle	()V
        //   96: aload_3
        //   97: invokevirtual 55	android/os/Parcel:recycle	()V
        //   100: aload_1
        //   101: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	102	0	this	a
        //   0	102	1	paramad	ad
        //   0	102	2	paramUri	Uri
        //   3	94	3	localParcel1	Parcel
        //   7	85	4	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   9	15	90	finally
        //   19	26	90	finally
        //   26	31	90	finally
        //   35	46	90	finally
        //   46	67	90	finally
        //   82	87	90	finally
      }
      
      /* Error */
      public void b(ad paramad, c paramc)
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
        //   16: aload_1
        //   17: ifnull +62 -> 79
        //   20: aload_1
        //   21: invokeinterface 40 1 0
        //   26: astore_1
        //   27: aload 4
        //   29: aload_1
        //   30: invokevirtual 43	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   33: aload_2
        //   34: ifnull +50 -> 84
        //   37: aload 4
        //   39: iconst_1
        //   40: invokevirtual 61	android/os/Parcel:writeInt	(I)V
        //   43: aload_2
        //   44: aload 4
        //   46: iconst_0
        //   47: invokevirtual 79	com/google/android/gms/wearable/c:writeToParcel	(Landroid/os/Parcel;I)V
        //   50: aload_0
        //   51: getfield 18	com/google/android/gms/wearable/internal/af$a$a:lb	Landroid/os/IBinder;
        //   54: iconst_2
        //   55: aload 4
        //   57: aload_3
        //   58: iconst_0
        //   59: invokeinterface 49 5 0
        //   64: pop
        //   65: aload_3
        //   66: invokevirtual 52	android/os/Parcel:readException	()V
        //   69: aload_3
        //   70: invokevirtual 55	android/os/Parcel:recycle	()V
        //   73: aload 4
        //   75: invokevirtual 55	android/os/Parcel:recycle	()V
        //   78: return
        //   79: aconst_null
        //   80: astore_1
        //   81: goto -54 -> 27
        //   84: aload 4
        //   86: iconst_0
        //   87: invokevirtual 61	android/os/Parcel:writeInt	(I)V
        //   90: goto -40 -> 50
        //   93: astore_1
        //   94: aload_3
        //   95: invokevirtual 55	android/os/Parcel:recycle	()V
        //   98: aload 4
        //   100: invokevirtual 55	android/os/Parcel:recycle	()V
        //   103: aload_1
        //   104: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	105	0	this	a
        //   0	105	1	paramad	ad
        //   0	105	2	paramc	c
        //   8	87	3	localParcel1	Parcel
        //   3	96	4	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   9	16	93	finally
        //   20	27	93	finally
        //   27	33	93	finally
        //   37	50	93	finally
        //   50	69	93	finally
        //   84	90	93	finally
      }
      
      /* Error */
      public void b(ad paramad, String paramString)
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
        //   16: ifnull +51 -> 67
        //   19: aload_1
        //   20: invokeinterface 40 1 0
        //   25: astore_1
        //   26: aload_3
        //   27: aload_1
        //   28: invokevirtual 43	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   31: aload_3
        //   32: aload_2
        //   33: invokevirtual 91	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   36: aload_0
        //   37: getfield 18	com/google/android/gms/wearable/internal/af$a$a:lb	Landroid/os/IBinder;
        //   40: bipush 23
        //   42: aload_3
        //   43: aload 4
        //   45: iconst_0
        //   46: invokeinterface 49 5 0
        //   51: pop
        //   52: aload 4
        //   54: invokevirtual 52	android/os/Parcel:readException	()V
        //   57: aload 4
        //   59: invokevirtual 55	android/os/Parcel:recycle	()V
        //   62: aload_3
        //   63: invokevirtual 55	android/os/Parcel:recycle	()V
        //   66: return
        //   67: aconst_null
        //   68: astore_1
        //   69: goto -43 -> 26
        //   72: astore_1
        //   73: aload 4
        //   75: invokevirtual 55	android/os/Parcel:recycle	()V
        //   78: aload_3
        //   79: invokevirtual 55	android/os/Parcel:recycle	()V
        //   82: aload_1
        //   83: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	84	0	this	a
        //   0	84	1	paramad	ad
        //   0	84	2	paramString	String
        //   3	76	3	localParcel1	Parcel
        //   7	67	4	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   9	15	72	finally
        //   19	26	72	finally
        //   26	57	72	finally
      }
      
      /* Error */
      public void c(ad paramad)
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
        //   15: ifnull +43 -> 58
        //   18: aload_1
        //   19: invokeinterface 40 1 0
        //   24: astore_1
        //   25: aload_3
        //   26: aload_1
        //   27: invokevirtual 43	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   30: aload_0
        //   31: getfield 18	com/google/android/gms/wearable/internal/af$a$a:lb	Landroid/os/IBinder;
        //   34: bipush 14
        //   36: aload_3
        //   37: aload_2
        //   38: iconst_0
        //   39: invokeinterface 49 5 0
        //   44: pop
        //   45: aload_2
        //   46: invokevirtual 52	android/os/Parcel:readException	()V
        //   49: aload_2
        //   50: invokevirtual 55	android/os/Parcel:recycle	()V
        //   53: aload_3
        //   54: invokevirtual 55	android/os/Parcel:recycle	()V
        //   57: return
        //   58: aconst_null
        //   59: astore_1
        //   60: goto -35 -> 25
        //   63: astore_1
        //   64: aload_2
        //   65: invokevirtual 55	android/os/Parcel:recycle	()V
        //   68: aload_3
        //   69: invokevirtual 55	android/os/Parcel:recycle	()V
        //   72: aload_1
        //   73: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	74	0	this	a
        //   0	74	1	paramad	ad
        //   7	58	2	localParcel1	Parcel
        //   3	66	3	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   8	14	63	finally
        //   18	25	63	finally
        //   25	49	63	finally
      }
      
      /* Error */
      public void c(ad paramad, Uri paramUri)
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
        //   16: ifnull +61 -> 77
        //   19: aload_1
        //   20: invokeinterface 40 1 0
        //   25: astore_1
        //   26: aload_3
        //   27: aload_1
        //   28: invokevirtual 43	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   31: aload_2
        //   32: ifnull +50 -> 82
        //   35: aload_3
        //   36: iconst_1
        //   37: invokevirtual 61	android/os/Parcel:writeInt	(I)V
        //   40: aload_2
        //   41: aload_3
        //   42: iconst_0
        //   43: invokevirtual 67	android/net/Uri:writeToParcel	(Landroid/os/Parcel;I)V
        //   46: aload_0
        //   47: getfield 18	com/google/android/gms/wearable/internal/af$a$a:lb	Landroid/os/IBinder;
        //   50: bipush 11
        //   52: aload_3
        //   53: aload 4
        //   55: iconst_0
        //   56: invokeinterface 49 5 0
        //   61: pop
        //   62: aload 4
        //   64: invokevirtual 52	android/os/Parcel:readException	()V
        //   67: aload 4
        //   69: invokevirtual 55	android/os/Parcel:recycle	()V
        //   72: aload_3
        //   73: invokevirtual 55	android/os/Parcel:recycle	()V
        //   76: return
        //   77: aconst_null
        //   78: astore_1
        //   79: goto -53 -> 26
        //   82: aload_3
        //   83: iconst_0
        //   84: invokevirtual 61	android/os/Parcel:writeInt	(I)V
        //   87: goto -41 -> 46
        //   90: astore_1
        //   91: aload 4
        //   93: invokevirtual 55	android/os/Parcel:recycle	()V
        //   96: aload_3
        //   97: invokevirtual 55	android/os/Parcel:recycle	()V
        //   100: aload_1
        //   101: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	102	0	this	a
        //   0	102	1	paramad	ad
        //   0	102	2	paramUri	Uri
        //   3	94	3	localParcel1	Parcel
        //   7	85	4	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   9	15	90	finally
        //   19	26	90	finally
        //   26	31	90	finally
        //   35	46	90	finally
        //   46	67	90	finally
        //   82	87	90	finally
      }
      
      /* Error */
      public void c(ad paramad, String paramString)
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
        //   16: aload_1
        //   17: ifnull +52 -> 69
        //   20: aload_1
        //   21: invokeinterface 40 1 0
        //   26: astore_1
        //   27: aload 4
        //   29: aload_1
        //   30: invokevirtual 43	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   33: aload 4
        //   35: aload_2
        //   36: invokevirtual 91	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   39: aload_0
        //   40: getfield 18	com/google/android/gms/wearable/internal/af$a$a:lb	Landroid/os/IBinder;
        //   43: bipush 24
        //   45: aload 4
        //   47: aload_3
        //   48: iconst_0
        //   49: invokeinterface 49 5 0
        //   54: pop
        //   55: aload_3
        //   56: invokevirtual 52	android/os/Parcel:readException	()V
        //   59: aload_3
        //   60: invokevirtual 55	android/os/Parcel:recycle	()V
        //   63: aload 4
        //   65: invokevirtual 55	android/os/Parcel:recycle	()V
        //   68: return
        //   69: aconst_null
        //   70: astore_1
        //   71: goto -44 -> 27
        //   74: astore_1
        //   75: aload_3
        //   76: invokevirtual 55	android/os/Parcel:recycle	()V
        //   79: aload 4
        //   81: invokevirtual 55	android/os/Parcel:recycle	()V
        //   84: aload_1
        //   85: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	86	0	this	a
        //   0	86	1	paramad	ad
        //   0	86	2	paramString	String
        //   8	68	3	localParcel1	Parcel
        //   3	77	4	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   9	16	74	finally
        //   20	27	74	finally
        //   27	59	74	finally
      }
      
      /* Error */
      public void d(ad paramad)
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
        //   15: ifnull +43 -> 58
        //   18: aload_1
        //   19: invokeinterface 40 1 0
        //   24: astore_1
        //   25: aload_2
        //   26: aload_1
        //   27: invokevirtual 43	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   30: aload_0
        //   31: getfield 18	com/google/android/gms/wearable/internal/af$a$a:lb	Landroid/os/IBinder;
        //   34: bipush 15
        //   36: aload_2
        //   37: aload_3
        //   38: iconst_0
        //   39: invokeinterface 49 5 0
        //   44: pop
        //   45: aload_3
        //   46: invokevirtual 52	android/os/Parcel:readException	()V
        //   49: aload_3
        //   50: invokevirtual 55	android/os/Parcel:recycle	()V
        //   53: aload_2
        //   54: invokevirtual 55	android/os/Parcel:recycle	()V
        //   57: return
        //   58: aconst_null
        //   59: astore_1
        //   60: goto -35 -> 25
        //   63: astore_1
        //   64: aload_3
        //   65: invokevirtual 55	android/os/Parcel:recycle	()V
        //   68: aload_2
        //   69: invokevirtual 55	android/os/Parcel:recycle	()V
        //   72: aload_1
        //   73: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	74	0	this	a
        //   0	74	1	paramad	ad
        //   3	66	2	localParcel1	Parcel
        //   7	58	3	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   8	14	63	finally
        //   18	25	63	finally
        //   25	49	63	finally
      }
      
      /* Error */
      public void e(ad paramad)
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
        //   15: ifnull +43 -> 58
        //   18: aload_1
        //   19: invokeinterface 40 1 0
        //   24: astore_1
        //   25: aload_2
        //   26: aload_1
        //   27: invokevirtual 43	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   30: aload_0
        //   31: getfield 18	com/google/android/gms/wearable/internal/af$a$a:lb	Landroid/os/IBinder;
        //   34: bipush 18
        //   36: aload_2
        //   37: aload_3
        //   38: iconst_0
        //   39: invokeinterface 49 5 0
        //   44: pop
        //   45: aload_3
        //   46: invokevirtual 52	android/os/Parcel:readException	()V
        //   49: aload_3
        //   50: invokevirtual 55	android/os/Parcel:recycle	()V
        //   53: aload_2
        //   54: invokevirtual 55	android/os/Parcel:recycle	()V
        //   57: return
        //   58: aconst_null
        //   59: astore_1
        //   60: goto -35 -> 25
        //   63: astore_1
        //   64: aload_3
        //   65: invokevirtual 55	android/os/Parcel:recycle	()V
        //   68: aload_2
        //   69: invokevirtual 55	android/os/Parcel:recycle	()V
        //   72: aload_1
        //   73: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	74	0	this	a
        //   0	74	1	paramad	ad
        //   3	66	2	localParcel1	Parcel
        //   7	58	3	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   8	14	63	finally
        //   18	25	63	finally
        //   25	49	63	finally
      }
      
      /* Error */
      public void f(ad paramad)
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
        //   15: ifnull +43 -> 58
        //   18: aload_1
        //   19: invokeinterface 40 1 0
        //   24: astore_1
        //   25: aload_3
        //   26: aload_1
        //   27: invokevirtual 43	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   30: aload_0
        //   31: getfield 18	com/google/android/gms/wearable/internal/af$a$a:lb	Landroid/os/IBinder;
        //   34: bipush 19
        //   36: aload_3
        //   37: aload_2
        //   38: iconst_0
        //   39: invokeinterface 49 5 0
        //   44: pop
        //   45: aload_2
        //   46: invokevirtual 52	android/os/Parcel:readException	()V
        //   49: aload_2
        //   50: invokevirtual 55	android/os/Parcel:recycle	()V
        //   53: aload_3
        //   54: invokevirtual 55	android/os/Parcel:recycle	()V
        //   57: return
        //   58: aconst_null
        //   59: astore_1
        //   60: goto -35 -> 25
        //   63: astore_1
        //   64: aload_2
        //   65: invokevirtual 55	android/os/Parcel:recycle	()V
        //   68: aload_3
        //   69: invokevirtual 55	android/os/Parcel:recycle	()V
        //   72: aload_1
        //   73: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	74	0	this	a
        //   0	74	1	paramad	ad
        //   7	58	2	localParcel1	Parcel
        //   3	66	3	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   8	14	63	finally
        //   18	25	63	finally
        //   25	49	63	finally
      }
      
      /* Error */
      public void g(ad paramad)
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
        //   15: ifnull +42 -> 57
        //   18: aload_1
        //   19: invokeinterface 40 1 0
        //   24: astore_1
        //   25: aload_2
        //   26: aload_1
        //   27: invokevirtual 43	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   30: aload_0
        //   31: getfield 18	com/google/android/gms/wearable/internal/af$a$a:lb	Landroid/os/IBinder;
        //   34: iconst_3
        //   35: aload_2
        //   36: aload_3
        //   37: iconst_0
        //   38: invokeinterface 49 5 0
        //   43: pop
        //   44: aload_3
        //   45: invokevirtual 52	android/os/Parcel:readException	()V
        //   48: aload_3
        //   49: invokevirtual 55	android/os/Parcel:recycle	()V
        //   52: aload_2
        //   53: invokevirtual 55	android/os/Parcel:recycle	()V
        //   56: return
        //   57: aconst_null
        //   58: astore_1
        //   59: goto -34 -> 25
        //   62: astore_1
        //   63: aload_3
        //   64: invokevirtual 55	android/os/Parcel:recycle	()V
        //   67: aload_2
        //   68: invokevirtual 55	android/os/Parcel:recycle	()V
        //   71: aload_1
        //   72: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	73	0	this	a
        //   0	73	1	paramad	ad
        //   3	65	2	localParcel1	Parcel
        //   7	57	3	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   8	14	62	finally
        //   18	25	62	finally
        //   25	48	62	finally
      }
      
      /* Error */
      public void h(ad paramad)
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
        //   18: aload_1
        //   19: invokeinterface 40 1 0
        //   24: astore_1
        //   25: aload_3
        //   26: aload_1
        //   27: invokevirtual 43	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   30: aload_0
        //   31: getfield 18	com/google/android/gms/wearable/internal/af$a$a:lb	Landroid/os/IBinder;
        //   34: iconst_4
        //   35: aload_3
        //   36: aload_2
        //   37: iconst_0
        //   38: invokeinterface 49 5 0
        //   43: pop
        //   44: aload_2
        //   45: invokevirtual 52	android/os/Parcel:readException	()V
        //   48: aload_2
        //   49: invokevirtual 55	android/os/Parcel:recycle	()V
        //   52: aload_3
        //   53: invokevirtual 55	android/os/Parcel:recycle	()V
        //   56: return
        //   57: aconst_null
        //   58: astore_1
        //   59: goto -34 -> 25
        //   62: astore_1
        //   63: aload_2
        //   64: invokevirtual 55	android/os/Parcel:recycle	()V
        //   67: aload_3
        //   68: invokevirtual 55	android/os/Parcel:recycle	()V
        //   71: aload_1
        //   72: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	73	0	this	a
        //   0	73	1	paramad	ad
        //   7	57	2	localParcel1	Parcel
        //   3	65	3	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   8	14	62	finally
        //   18	25	62	finally
        //   25	48	62	finally
      }
      
      /* Error */
      public void i(ad paramad)
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
        //   15: ifnull +42 -> 57
        //   18: aload_1
        //   19: invokeinterface 40 1 0
        //   24: astore_1
        //   25: aload_2
        //   26: aload_1
        //   27: invokevirtual 43	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   30: aload_0
        //   31: getfield 18	com/google/android/gms/wearable/internal/af$a$a:lb	Landroid/os/IBinder;
        //   34: iconst_5
        //   35: aload_2
        //   36: aload_3
        //   37: iconst_0
        //   38: invokeinterface 49 5 0
        //   43: pop
        //   44: aload_3
        //   45: invokevirtual 52	android/os/Parcel:readException	()V
        //   48: aload_3
        //   49: invokevirtual 55	android/os/Parcel:recycle	()V
        //   52: aload_2
        //   53: invokevirtual 55	android/os/Parcel:recycle	()V
        //   56: return
        //   57: aconst_null
        //   58: astore_1
        //   59: goto -34 -> 25
        //   62: astore_1
        //   63: aload_3
        //   64: invokevirtual 55	android/os/Parcel:recycle	()V
        //   67: aload_2
        //   68: invokevirtual 55	android/os/Parcel:recycle	()V
        //   71: aload_1
        //   72: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	73	0	this	a
        //   0	73	1	paramad	ad
        //   3	65	2	localParcel1	Parcel
        //   7	57	3	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   8	14	62	finally
        //   18	25	62	finally
        //   25	48	62	finally
      }
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\wearable\internal\af.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */