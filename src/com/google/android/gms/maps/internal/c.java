package com.google.android.gms.maps.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.d;
import com.google.android.gms.dynamic.d.a;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.StreetViewPanoramaOptions;
import com.google.android.gms.maps.model.internal.a.a;

public abstract interface c
  extends IInterface
{
  public abstract IMapViewDelegate a(d paramd, GoogleMapOptions paramGoogleMapOptions)
    throws RemoteException;
  
  public abstract IStreetViewPanoramaViewDelegate a(d paramd, StreetViewPanoramaOptions paramStreetViewPanoramaOptions)
    throws RemoteException;
  
  public abstract void a(d paramd, int paramInt)
    throws RemoteException;
  
  public abstract void i(d paramd)
    throws RemoteException;
  
  public abstract IMapFragmentDelegate j(d paramd)
    throws RemoteException;
  
  public abstract IStreetViewPanoramaFragmentDelegate k(d paramd)
    throws RemoteException;
  
  public abstract ICameraUpdateFactoryDelegate mI()
    throws RemoteException;
  
  public abstract com.google.android.gms.maps.model.internal.a mJ()
    throws RemoteException;
  
  public static abstract class a
    extends Binder
    implements c
  {
    public static c aP(IBinder paramIBinder)
    {
      if (paramIBinder == null) {
        paramIBinder = null;
      }
      for (;;)
      {
        return paramIBinder;
        IInterface localIInterface = paramIBinder.queryLocalInterface("com.google.android.gms.maps.internal.ICreator");
        if ((localIInterface != null) && ((localIInterface instanceof c))) {
          paramIBinder = (c)localIInterface;
        } else {
          paramIBinder = new a(paramIBinder);
        }
      }
    }
    
    public boolean onTransact(int paramInt1, Parcel paramParcel1, Parcel paramParcel2, int paramInt2)
      throws RemoteException
    {
      Object localObject1 = null;
      Object localObject4 = null;
      Object localObject5 = null;
      Object localObject2 = null;
      Object localObject6 = null;
      Object localObject3 = null;
      boolean bool;
      switch (paramInt1)
      {
      default: 
        bool = super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
      }
      for (;;)
      {
        return bool;
        paramParcel2.writeString("com.google.android.gms.maps.internal.ICreator");
        bool = true;
        continue;
        paramParcel1.enforceInterface("com.google.android.gms.maps.internal.ICreator");
        i(d.a.am(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        bool = true;
        continue;
        paramParcel1.enforceInterface("com.google.android.gms.maps.internal.ICreator");
        localObject1 = j(d.a.am(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        paramParcel1 = (Parcel)localObject3;
        if (localObject1 != null) {
          paramParcel1 = ((IMapFragmentDelegate)localObject1).asBinder();
        }
        paramParcel2.writeStrongBinder(paramParcel1);
        bool = true;
        continue;
        paramParcel1.enforceInterface("com.google.android.gms.maps.internal.ICreator");
        localObject2 = d.a.am(paramParcel1.readStrongBinder());
        if (paramParcel1.readInt() != 0) {}
        for (paramParcel1 = GoogleMapOptions.CREATOR.cG(paramParcel1);; paramParcel1 = null)
        {
          localObject2 = a((d)localObject2, paramParcel1);
          paramParcel2.writeNoException();
          paramParcel1 = (Parcel)localObject1;
          if (localObject2 != null) {
            paramParcel1 = ((IMapViewDelegate)localObject2).asBinder();
          }
          paramParcel2.writeStrongBinder(paramParcel1);
          bool = true;
          break;
        }
        paramParcel1.enforceInterface("com.google.android.gms.maps.internal.ICreator");
        localObject1 = mI();
        paramParcel2.writeNoException();
        paramParcel1 = (Parcel)localObject4;
        if (localObject1 != null) {
          paramParcel1 = ((ICameraUpdateFactoryDelegate)localObject1).asBinder();
        }
        paramParcel2.writeStrongBinder(paramParcel1);
        bool = true;
        continue;
        paramParcel1.enforceInterface("com.google.android.gms.maps.internal.ICreator");
        localObject1 = mJ();
        paramParcel2.writeNoException();
        paramParcel1 = (Parcel)localObject5;
        if (localObject1 != null) {
          paramParcel1 = ((com.google.android.gms.maps.model.internal.a)localObject1).asBinder();
        }
        paramParcel2.writeStrongBinder(paramParcel1);
        bool = true;
        continue;
        paramParcel1.enforceInterface("com.google.android.gms.maps.internal.ICreator");
        a(d.a.am(paramParcel1.readStrongBinder()), paramParcel1.readInt());
        paramParcel2.writeNoException();
        bool = true;
        continue;
        paramParcel1.enforceInterface("com.google.android.gms.maps.internal.ICreator");
        localObject1 = d.a.am(paramParcel1.readStrongBinder());
        if (paramParcel1.readInt() != 0) {}
        for (paramParcel1 = StreetViewPanoramaOptions.CREATOR.cH(paramParcel1);; paramParcel1 = null)
        {
          localObject1 = a((d)localObject1, paramParcel1);
          paramParcel2.writeNoException();
          paramParcel1 = (Parcel)localObject2;
          if (localObject1 != null) {
            paramParcel1 = ((IStreetViewPanoramaViewDelegate)localObject1).asBinder();
          }
          paramParcel2.writeStrongBinder(paramParcel1);
          bool = true;
          break;
        }
        paramParcel1.enforceInterface("com.google.android.gms.maps.internal.ICreator");
        localObject1 = k(d.a.am(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        paramParcel1 = (Parcel)localObject6;
        if (localObject1 != null) {
          paramParcel1 = ((IStreetViewPanoramaFragmentDelegate)localObject1).asBinder();
        }
        paramParcel2.writeStrongBinder(paramParcel1);
        bool = true;
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
      public IMapViewDelegate a(d paramd, GoogleMapOptions paramGoogleMapOptions)
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
        //   17: ifnull +71 -> 88
        //   20: aload_1
        //   21: invokeinterface 40 1 0
        //   26: astore_1
        //   27: aload 4
        //   29: aload_1
        //   30: invokevirtual 43	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   33: aload_2
        //   34: ifnull +59 -> 93
        //   37: aload 4
        //   39: iconst_1
        //   40: invokevirtual 47	android/os/Parcel:writeInt	(I)V
        //   43: aload_2
        //   44: aload 4
        //   46: iconst_0
        //   47: invokevirtual 53	com/google/android/gms/maps/GoogleMapOptions:writeToParcel	(Landroid/os/Parcel;I)V
        //   50: aload_0
        //   51: getfield 18	com/google/android/gms/maps/internal/c$a$a:lb	Landroid/os/IBinder;
        //   54: iconst_3
        //   55: aload 4
        //   57: aload_3
        //   58: iconst_0
        //   59: invokeinterface 59 5 0
        //   64: pop
        //   65: aload_3
        //   66: invokevirtual 62	android/os/Parcel:readException	()V
        //   69: aload_3
        //   70: invokevirtual 65	android/os/Parcel:readStrongBinder	()Landroid/os/IBinder;
        //   73: invokestatic 71	com/google/android/gms/maps/internal/IMapViewDelegate$a:aU	(Landroid/os/IBinder;)Lcom/google/android/gms/maps/internal/IMapViewDelegate;
        //   76: astore_1
        //   77: aload_3
        //   78: invokevirtual 74	android/os/Parcel:recycle	()V
        //   81: aload 4
        //   83: invokevirtual 74	android/os/Parcel:recycle	()V
        //   86: aload_1
        //   87: areturn
        //   88: aconst_null
        //   89: astore_1
        //   90: goto -63 -> 27
        //   93: aload 4
        //   95: iconst_0
        //   96: invokevirtual 47	android/os/Parcel:writeInt	(I)V
        //   99: goto -49 -> 50
        //   102: astore_1
        //   103: aload_3
        //   104: invokevirtual 74	android/os/Parcel:recycle	()V
        //   107: aload 4
        //   109: invokevirtual 74	android/os/Parcel:recycle	()V
        //   112: aload_1
        //   113: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	114	0	this	a
        //   0	114	1	paramd	d
        //   0	114	2	paramGoogleMapOptions	GoogleMapOptions
        //   8	96	3	localParcel1	Parcel
        //   3	105	4	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   9	16	102	finally
        //   20	27	102	finally
        //   27	33	102	finally
        //   37	50	102	finally
        //   50	77	102	finally
        //   93	99	102	finally
      }
      
      /* Error */
      public IStreetViewPanoramaViewDelegate a(d paramd, StreetViewPanoramaOptions paramStreetViewPanoramaOptions)
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
        //   16: ifnull +71 -> 87
        //   19: aload_1
        //   20: invokeinterface 40 1 0
        //   25: astore_1
        //   26: aload_3
        //   27: aload_1
        //   28: invokevirtual 43	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   31: aload_2
        //   32: ifnull +60 -> 92
        //   35: aload_3
        //   36: iconst_1
        //   37: invokevirtual 47	android/os/Parcel:writeInt	(I)V
        //   40: aload_2
        //   41: aload_3
        //   42: iconst_0
        //   43: invokevirtual 79	com/google/android/gms/maps/StreetViewPanoramaOptions:writeToParcel	(Landroid/os/Parcel;I)V
        //   46: aload_0
        //   47: getfield 18	com/google/android/gms/maps/internal/c$a$a:lb	Landroid/os/IBinder;
        //   50: bipush 7
        //   52: aload_3
        //   53: aload 4
        //   55: iconst_0
        //   56: invokeinterface 59 5 0
        //   61: pop
        //   62: aload 4
        //   64: invokevirtual 62	android/os/Parcel:readException	()V
        //   67: aload 4
        //   69: invokevirtual 65	android/os/Parcel:readStrongBinder	()Landroid/os/IBinder;
        //   72: invokestatic 85	com/google/android/gms/maps/internal/IStreetViewPanoramaViewDelegate$a:bn	(Landroid/os/IBinder;)Lcom/google/android/gms/maps/internal/IStreetViewPanoramaViewDelegate;
        //   75: astore_1
        //   76: aload 4
        //   78: invokevirtual 74	android/os/Parcel:recycle	()V
        //   81: aload_3
        //   82: invokevirtual 74	android/os/Parcel:recycle	()V
        //   85: aload_1
        //   86: areturn
        //   87: aconst_null
        //   88: astore_1
        //   89: goto -63 -> 26
        //   92: aload_3
        //   93: iconst_0
        //   94: invokevirtual 47	android/os/Parcel:writeInt	(I)V
        //   97: goto -51 -> 46
        //   100: astore_1
        //   101: aload 4
        //   103: invokevirtual 74	android/os/Parcel:recycle	()V
        //   106: aload_3
        //   107: invokevirtual 74	android/os/Parcel:recycle	()V
        //   110: aload_1
        //   111: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	112	0	this	a
        //   0	112	1	paramd	d
        //   0	112	2	paramStreetViewPanoramaOptions	StreetViewPanoramaOptions
        //   3	104	3	localParcel1	Parcel
        //   7	95	4	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   9	15	100	finally
        //   19	26	100	finally
        //   26	31	100	finally
        //   35	46	100	finally
        //   46	76	100	finally
        //   92	97	100	finally
      }
      
      /* Error */
      public void a(d paramd, int paramInt)
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
        //   35: iload_2
        //   36: invokevirtual 47	android/os/Parcel:writeInt	(I)V
        //   39: aload_0
        //   40: getfield 18	com/google/android/gms/maps/internal/c$a$a:lb	Landroid/os/IBinder;
        //   43: bipush 6
        //   45: aload 4
        //   47: aload_3
        //   48: iconst_0
        //   49: invokeinterface 59 5 0
        //   54: pop
        //   55: aload_3
        //   56: invokevirtual 62	android/os/Parcel:readException	()V
        //   59: aload_3
        //   60: invokevirtual 74	android/os/Parcel:recycle	()V
        //   63: aload 4
        //   65: invokevirtual 74	android/os/Parcel:recycle	()V
        //   68: return
        //   69: aconst_null
        //   70: astore_1
        //   71: goto -44 -> 27
        //   74: astore_1
        //   75: aload_3
        //   76: invokevirtual 74	android/os/Parcel:recycle	()V
        //   79: aload 4
        //   81: invokevirtual 74	android/os/Parcel:recycle	()V
        //   84: aload_1
        //   85: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	86	0	this	a
        //   0	86	1	paramd	d
        //   0	86	2	paramInt	int
        //   8	68	3	localParcel1	Parcel
        //   3	77	4	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   9	16	74	finally
        //   20	27	74	finally
        //   27	59	74	finally
      }
      
      public IBinder asBinder()
      {
        return this.lb;
      }
      
      /* Error */
      public void i(d paramd)
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
        //   31: getfield 18	com/google/android/gms/maps/internal/c$a$a:lb	Landroid/os/IBinder;
        //   34: iconst_1
        //   35: aload_3
        //   36: aload_2
        //   37: iconst_0
        //   38: invokeinterface 59 5 0
        //   43: pop
        //   44: aload_2
        //   45: invokevirtual 62	android/os/Parcel:readException	()V
        //   48: aload_2
        //   49: invokevirtual 74	android/os/Parcel:recycle	()V
        //   52: aload_3
        //   53: invokevirtual 74	android/os/Parcel:recycle	()V
        //   56: return
        //   57: aconst_null
        //   58: astore_1
        //   59: goto -34 -> 25
        //   62: astore_1
        //   63: aload_2
        //   64: invokevirtual 74	android/os/Parcel:recycle	()V
        //   67: aload_3
        //   68: invokevirtual 74	android/os/Parcel:recycle	()V
        //   71: aload_1
        //   72: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	73	0	this	a
        //   0	73	1	paramd	d
        //   7	57	2	localParcel1	Parcel
        //   3	65	3	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   8	14	62	finally
        //   18	25	62	finally
        //   25	48	62	finally
      }
      
      /* Error */
      public IMapFragmentDelegate j(d paramd)
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
        //   15: ifnull +51 -> 66
        //   18: aload_1
        //   19: invokeinterface 40 1 0
        //   24: astore_1
        //   25: aload_3
        //   26: aload_1
        //   27: invokevirtual 43	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   30: aload_0
        //   31: getfield 18	com/google/android/gms/maps/internal/c$a$a:lb	Landroid/os/IBinder;
        //   34: iconst_2
        //   35: aload_3
        //   36: aload_2
        //   37: iconst_0
        //   38: invokeinterface 59 5 0
        //   43: pop
        //   44: aload_2
        //   45: invokevirtual 62	android/os/Parcel:readException	()V
        //   48: aload_2
        //   49: invokevirtual 65	android/os/Parcel:readStrongBinder	()Landroid/os/IBinder;
        //   52: invokestatic 96	com/google/android/gms/maps/internal/IMapFragmentDelegate$a:aT	(Landroid/os/IBinder;)Lcom/google/android/gms/maps/internal/IMapFragmentDelegate;
        //   55: astore_1
        //   56: aload_2
        //   57: invokevirtual 74	android/os/Parcel:recycle	()V
        //   60: aload_3
        //   61: invokevirtual 74	android/os/Parcel:recycle	()V
        //   64: aload_1
        //   65: areturn
        //   66: aconst_null
        //   67: astore_1
        //   68: goto -43 -> 25
        //   71: astore_1
        //   72: aload_2
        //   73: invokevirtual 74	android/os/Parcel:recycle	()V
        //   76: aload_3
        //   77: invokevirtual 74	android/os/Parcel:recycle	()V
        //   80: aload_1
        //   81: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	82	0	this	a
        //   0	82	1	paramd	d
        //   7	66	2	localParcel1	Parcel
        //   3	74	3	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   8	14	71	finally
        //   18	25	71	finally
        //   25	56	71	finally
      }
      
      /* Error */
      public IStreetViewPanoramaFragmentDelegate k(d paramd)
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
        //   15: ifnull +52 -> 67
        //   18: aload_1
        //   19: invokeinterface 40 1 0
        //   24: astore_1
        //   25: aload_2
        //   26: aload_1
        //   27: invokevirtual 43	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   30: aload_0
        //   31: getfield 18	com/google/android/gms/maps/internal/c$a$a:lb	Landroid/os/IBinder;
        //   34: bipush 8
        //   36: aload_2
        //   37: aload_3
        //   38: iconst_0
        //   39: invokeinterface 59 5 0
        //   44: pop
        //   45: aload_3
        //   46: invokevirtual 62	android/os/Parcel:readException	()V
        //   49: aload_3
        //   50: invokevirtual 65	android/os/Parcel:readStrongBinder	()Landroid/os/IBinder;
        //   53: invokestatic 104	com/google/android/gms/maps/internal/IStreetViewPanoramaFragmentDelegate$a:bm	(Landroid/os/IBinder;)Lcom/google/android/gms/maps/internal/IStreetViewPanoramaFragmentDelegate;
        //   56: astore_1
        //   57: aload_3
        //   58: invokevirtual 74	android/os/Parcel:recycle	()V
        //   61: aload_2
        //   62: invokevirtual 74	android/os/Parcel:recycle	()V
        //   65: aload_1
        //   66: areturn
        //   67: aconst_null
        //   68: astore_1
        //   69: goto -44 -> 25
        //   72: astore_1
        //   73: aload_3
        //   74: invokevirtual 74	android/os/Parcel:recycle	()V
        //   77: aload_2
        //   78: invokevirtual 74	android/os/Parcel:recycle	()V
        //   81: aload_1
        //   82: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	83	0	this	a
        //   0	83	1	paramd	d
        //   3	75	2	localParcel1	Parcel
        //   7	67	3	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   8	14	72	finally
        //   18	25	72	finally
        //   25	57	72	finally
      }
      
      public ICameraUpdateFactoryDelegate mI()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.maps.internal.ICreator");
          this.lb.transact(4, localParcel1, localParcel2, 0);
          localParcel2.readException();
          ICameraUpdateFactoryDelegate localICameraUpdateFactoryDelegate = ICameraUpdateFactoryDelegate.a.aN(localParcel2.readStrongBinder());
          return localICameraUpdateFactoryDelegate;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
      
      public com.google.android.gms.maps.model.internal.a mJ()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.maps.internal.ICreator");
          this.lb.transact(5, localParcel1, localParcel2, 0);
          localParcel2.readException();
          com.google.android.gms.maps.model.internal.a locala = a.a.bp(localParcel2.readStrongBinder());
          return locala;
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


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\maps\internal\c.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */