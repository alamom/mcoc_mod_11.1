package com.google.android.gms.maps.model.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.d;
import com.google.android.gms.dynamic.d.a;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.g;
import com.google.android.gms.maps.model.i;

public abstract interface c
  extends IInterface
{
  public abstract void a(float paramFloat1, float paramFloat2)
    throws RemoteException;
  
  public abstract boolean a(c paramc)
    throws RemoteException;
  
  public abstract float getBearing()
    throws RemoteException;
  
  public abstract LatLngBounds getBounds()
    throws RemoteException;
  
  public abstract float getHeight()
    throws RemoteException;
  
  public abstract String getId()
    throws RemoteException;
  
  public abstract LatLng getPosition()
    throws RemoteException;
  
  public abstract float getTransparency()
    throws RemoteException;
  
  public abstract float getWidth()
    throws RemoteException;
  
  public abstract float getZIndex()
    throws RemoteException;
  
  public abstract int hashCodeRemote()
    throws RemoteException;
  
  public abstract boolean isVisible()
    throws RemoteException;
  
  public abstract void m(d paramd)
    throws RemoteException;
  
  public abstract void remove()
    throws RemoteException;
  
  public abstract void setBearing(float paramFloat)
    throws RemoteException;
  
  public abstract void setDimensions(float paramFloat)
    throws RemoteException;
  
  public abstract void setPosition(LatLng paramLatLng)
    throws RemoteException;
  
  public abstract void setPositionFromBounds(LatLngBounds paramLatLngBounds)
    throws RemoteException;
  
  public abstract void setTransparency(float paramFloat)
    throws RemoteException;
  
  public abstract void setVisible(boolean paramBoolean)
    throws RemoteException;
  
  public abstract void setZIndex(float paramFloat)
    throws RemoteException;
  
  public static abstract class a
    extends Binder
    implements c
  {
    public static c br(IBinder paramIBinder)
    {
      if (paramIBinder == null) {
        paramIBinder = null;
      }
      for (;;)
      {
        return paramIBinder;
        IInterface localIInterface = paramIBinder.queryLocalInterface("com.google.android.gms.maps.model.internal.IGroundOverlayDelegate");
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
      Object localObject2 = null;
      Object localObject1 = null;
      int j = 0;
      int i = 0;
      boolean bool2 = true;
      boolean bool1;
      switch (paramInt1)
      {
      default: 
        bool1 = super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
      }
      for (;;)
      {
        return bool1;
        paramParcel2.writeString("com.google.android.gms.maps.model.internal.IGroundOverlayDelegate");
        bool1 = bool2;
        continue;
        paramParcel1.enforceInterface("com.google.android.gms.maps.model.internal.IGroundOverlayDelegate");
        remove();
        paramParcel2.writeNoException();
        bool1 = bool2;
        continue;
        paramParcel1.enforceInterface("com.google.android.gms.maps.model.internal.IGroundOverlayDelegate");
        paramParcel1 = getId();
        paramParcel2.writeNoException();
        paramParcel2.writeString(paramParcel1);
        bool1 = bool2;
        continue;
        paramParcel1.enforceInterface("com.google.android.gms.maps.model.internal.IGroundOverlayDelegate");
        if (paramParcel1.readInt() != 0) {
          localObject1 = LatLng.CREATOR.cM(paramParcel1);
        }
        setPosition((LatLng)localObject1);
        paramParcel2.writeNoException();
        bool1 = bool2;
        continue;
        paramParcel1.enforceInterface("com.google.android.gms.maps.model.internal.IGroundOverlayDelegate");
        paramParcel1 = getPosition();
        paramParcel2.writeNoException();
        if (paramParcel1 != null)
        {
          paramParcel2.writeInt(1);
          paramParcel1.writeToParcel(paramParcel2, 1);
          bool1 = bool2;
        }
        else
        {
          paramParcel2.writeInt(0);
          bool1 = bool2;
          continue;
          paramParcel1.enforceInterface("com.google.android.gms.maps.model.internal.IGroundOverlayDelegate");
          setDimensions(paramParcel1.readFloat());
          paramParcel2.writeNoException();
          bool1 = bool2;
          continue;
          paramParcel1.enforceInterface("com.google.android.gms.maps.model.internal.IGroundOverlayDelegate");
          a(paramParcel1.readFloat(), paramParcel1.readFloat());
          paramParcel2.writeNoException();
          bool1 = bool2;
          continue;
          paramParcel1.enforceInterface("com.google.android.gms.maps.model.internal.IGroundOverlayDelegate");
          float f = getWidth();
          paramParcel2.writeNoException();
          paramParcel2.writeFloat(f);
          bool1 = bool2;
          continue;
          paramParcel1.enforceInterface("com.google.android.gms.maps.model.internal.IGroundOverlayDelegate");
          f = getHeight();
          paramParcel2.writeNoException();
          paramParcel2.writeFloat(f);
          bool1 = bool2;
          continue;
          paramParcel1.enforceInterface("com.google.android.gms.maps.model.internal.IGroundOverlayDelegate");
          localObject1 = localObject2;
          if (paramParcel1.readInt() != 0) {
            localObject1 = LatLngBounds.CREATOR.cL(paramParcel1);
          }
          setPositionFromBounds((LatLngBounds)localObject1);
          paramParcel2.writeNoException();
          bool1 = bool2;
          continue;
          paramParcel1.enforceInterface("com.google.android.gms.maps.model.internal.IGroundOverlayDelegate");
          paramParcel1 = getBounds();
          paramParcel2.writeNoException();
          if (paramParcel1 != null)
          {
            paramParcel2.writeInt(1);
            paramParcel1.writeToParcel(paramParcel2, 1);
            bool1 = bool2;
          }
          else
          {
            paramParcel2.writeInt(0);
            bool1 = bool2;
            continue;
            paramParcel1.enforceInterface("com.google.android.gms.maps.model.internal.IGroundOverlayDelegate");
            setBearing(paramParcel1.readFloat());
            paramParcel2.writeNoException();
            bool1 = bool2;
            continue;
            paramParcel1.enforceInterface("com.google.android.gms.maps.model.internal.IGroundOverlayDelegate");
            f = getBearing();
            paramParcel2.writeNoException();
            paramParcel2.writeFloat(f);
            bool1 = bool2;
            continue;
            paramParcel1.enforceInterface("com.google.android.gms.maps.model.internal.IGroundOverlayDelegate");
            setZIndex(paramParcel1.readFloat());
            paramParcel2.writeNoException();
            bool1 = bool2;
            continue;
            paramParcel1.enforceInterface("com.google.android.gms.maps.model.internal.IGroundOverlayDelegate");
            f = getZIndex();
            paramParcel2.writeNoException();
            paramParcel2.writeFloat(f);
            bool1 = bool2;
            continue;
            paramParcel1.enforceInterface("com.google.android.gms.maps.model.internal.IGroundOverlayDelegate");
            if (paramParcel1.readInt() != 0) {}
            for (bool1 = true;; bool1 = false)
            {
              setVisible(bool1);
              paramParcel2.writeNoException();
              bool1 = bool2;
              break;
            }
            paramParcel1.enforceInterface("com.google.android.gms.maps.model.internal.IGroundOverlayDelegate");
            bool1 = isVisible();
            paramParcel2.writeNoException();
            paramInt1 = i;
            if (bool1) {
              paramInt1 = 1;
            }
            paramParcel2.writeInt(paramInt1);
            bool1 = bool2;
            continue;
            paramParcel1.enforceInterface("com.google.android.gms.maps.model.internal.IGroundOverlayDelegate");
            setTransparency(paramParcel1.readFloat());
            paramParcel2.writeNoException();
            bool1 = bool2;
            continue;
            paramParcel1.enforceInterface("com.google.android.gms.maps.model.internal.IGroundOverlayDelegate");
            f = getTransparency();
            paramParcel2.writeNoException();
            paramParcel2.writeFloat(f);
            bool1 = bool2;
            continue;
            paramParcel1.enforceInterface("com.google.android.gms.maps.model.internal.IGroundOverlayDelegate");
            bool1 = a(br(paramParcel1.readStrongBinder()));
            paramParcel2.writeNoException();
            paramInt1 = j;
            if (bool1) {
              paramInt1 = 1;
            }
            paramParcel2.writeInt(paramInt1);
            bool1 = bool2;
            continue;
            paramParcel1.enforceInterface("com.google.android.gms.maps.model.internal.IGroundOverlayDelegate");
            paramInt1 = hashCodeRemote();
            paramParcel2.writeNoException();
            paramParcel2.writeInt(paramInt1);
            bool1 = bool2;
            continue;
            paramParcel1.enforceInterface("com.google.android.gms.maps.model.internal.IGroundOverlayDelegate");
            m(d.a.am(paramParcel1.readStrongBinder()));
            paramParcel2.writeNoException();
            bool1 = bool2;
          }
        }
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
      
      public void a(float paramFloat1, float paramFloat2)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.maps.model.internal.IGroundOverlayDelegate");
          localParcel1.writeFloat(paramFloat1);
          localParcel1.writeFloat(paramFloat2);
          this.lb.transact(6, localParcel1, localParcel2, 0);
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
      public boolean a(c paramc)
        throws RemoteException
      {
        // Byte code:
        //   0: iconst_0
        //   1: istore_3
        //   2: invokestatic 28	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   5: astore 4
        //   7: invokestatic 28	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   10: astore 5
        //   12: aload 4
        //   14: ldc 30
        //   16: invokevirtual 34	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   19: aload_1
        //   20: ifnull +62 -> 82
        //   23: aload_1
        //   24: invokeinterface 56 1 0
        //   29: astore_1
        //   30: aload 4
        //   32: aload_1
        //   33: invokevirtual 59	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   36: aload_0
        //   37: getfield 18	com/google/android/gms/maps/model/internal/c$a$a:lb	Landroid/os/IBinder;
        //   40: bipush 19
        //   42: aload 4
        //   44: aload 5
        //   46: iconst_0
        //   47: invokeinterface 44 5 0
        //   52: pop
        //   53: aload 5
        //   55: invokevirtual 47	android/os/Parcel:readException	()V
        //   58: aload 5
        //   60: invokevirtual 63	android/os/Parcel:readInt	()I
        //   63: istore_2
        //   64: iload_2
        //   65: ifeq +5 -> 70
        //   68: iconst_1
        //   69: istore_3
        //   70: aload 5
        //   72: invokevirtual 50	android/os/Parcel:recycle	()V
        //   75: aload 4
        //   77: invokevirtual 50	android/os/Parcel:recycle	()V
        //   80: iload_3
        //   81: ireturn
        //   82: aconst_null
        //   83: astore_1
        //   84: goto -54 -> 30
        //   87: astore_1
        //   88: aload 5
        //   90: invokevirtual 50	android/os/Parcel:recycle	()V
        //   93: aload 4
        //   95: invokevirtual 50	android/os/Parcel:recycle	()V
        //   98: aload_1
        //   99: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	100	0	this	a
        //   0	100	1	paramc	c
        //   63	2	2	i	int
        //   1	80	3	bool	boolean
        //   5	89	4	localParcel1	Parcel
        //   10	79	5	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   12	19	87	finally
        //   23	30	87	finally
        //   30	64	87	finally
      }
      
      public IBinder asBinder()
      {
        return this.lb;
      }
      
      public float getBearing()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.maps.model.internal.IGroundOverlayDelegate");
          this.lb.transact(12, localParcel1, localParcel2, 0);
          localParcel2.readException();
          float f = localParcel2.readFloat();
          return f;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
      
      /* Error */
      public LatLngBounds getBounds()
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
        //   14: aload_0
        //   15: getfield 18	com/google/android/gms/maps/model/internal/c$a$a:lb	Landroid/os/IBinder;
        //   18: bipush 10
        //   20: aload_2
        //   21: aload_3
        //   22: iconst_0
        //   23: invokeinterface 44 5 0
        //   28: pop
        //   29: aload_3
        //   30: invokevirtual 47	android/os/Parcel:readException	()V
        //   33: aload_3
        //   34: invokevirtual 63	android/os/Parcel:readInt	()I
        //   37: ifeq +21 -> 58
        //   40: getstatic 76	com/google/android/gms/maps/model/LatLngBounds:CREATOR	Lcom/google/android/gms/maps/model/g;
        //   43: aload_3
        //   44: invokevirtual 82	com/google/android/gms/maps/model/g:cL	(Landroid/os/Parcel;)Lcom/google/android/gms/maps/model/LatLngBounds;
        //   47: astore_1
        //   48: aload_3
        //   49: invokevirtual 50	android/os/Parcel:recycle	()V
        //   52: aload_2
        //   53: invokevirtual 50	android/os/Parcel:recycle	()V
        //   56: aload_1
        //   57: areturn
        //   58: aconst_null
        //   59: astore_1
        //   60: goto -12 -> 48
        //   63: astore_1
        //   64: aload_3
        //   65: invokevirtual 50	android/os/Parcel:recycle	()V
        //   68: aload_2
        //   69: invokevirtual 50	android/os/Parcel:recycle	()V
        //   72: aload_1
        //   73: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	74	0	this	a
        //   47	13	1	localLatLngBounds	LatLngBounds
        //   63	10	1	localObject	Object
        //   3	66	2	localParcel1	Parcel
        //   7	58	3	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   8	48	63	finally
      }
      
      public float getHeight()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.maps.model.internal.IGroundOverlayDelegate");
          this.lb.transact(8, localParcel1, localParcel2, 0);
          localParcel2.readException();
          float f = localParcel2.readFloat();
          return f;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
      
      public String getId()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.maps.model.internal.IGroundOverlayDelegate");
          this.lb.transact(2, localParcel1, localParcel2, 0);
          localParcel2.readException();
          String str = localParcel2.readString();
          return str;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
      
      /* Error */
      public LatLng getPosition()
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
        //   14: aload_0
        //   15: getfield 18	com/google/android/gms/maps/model/internal/c$a$a:lb	Landroid/os/IBinder;
        //   18: iconst_4
        //   19: aload_3
        //   20: aload_2
        //   21: iconst_0
        //   22: invokeinterface 44 5 0
        //   27: pop
        //   28: aload_2
        //   29: invokevirtual 47	android/os/Parcel:readException	()V
        //   32: aload_2
        //   33: invokevirtual 63	android/os/Parcel:readInt	()I
        //   36: ifeq +21 -> 57
        //   39: getstatic 95	com/google/android/gms/maps/model/LatLng:CREATOR	Lcom/google/android/gms/maps/model/i;
        //   42: aload_2
        //   43: invokevirtual 101	com/google/android/gms/maps/model/i:cM	(Landroid/os/Parcel;)Lcom/google/android/gms/maps/model/LatLng;
        //   46: astore_1
        //   47: aload_2
        //   48: invokevirtual 50	android/os/Parcel:recycle	()V
        //   51: aload_3
        //   52: invokevirtual 50	android/os/Parcel:recycle	()V
        //   55: aload_1
        //   56: areturn
        //   57: aconst_null
        //   58: astore_1
        //   59: goto -12 -> 47
        //   62: astore_1
        //   63: aload_2
        //   64: invokevirtual 50	android/os/Parcel:recycle	()V
        //   67: aload_3
        //   68: invokevirtual 50	android/os/Parcel:recycle	()V
        //   71: aload_1
        //   72: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	73	0	this	a
        //   46	13	1	localLatLng	LatLng
        //   62	10	1	localObject	Object
        //   7	57	2	localParcel1	Parcel
        //   3	65	3	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   8	47	62	finally
      }
      
      public float getTransparency()
        throws RemoteException
      {
        Parcel localParcel2 = Parcel.obtain();
        Parcel localParcel1 = Parcel.obtain();
        try
        {
          localParcel2.writeInterfaceToken("com.google.android.gms.maps.model.internal.IGroundOverlayDelegate");
          this.lb.transact(18, localParcel2, localParcel1, 0);
          localParcel1.readException();
          float f = localParcel1.readFloat();
          return f;
        }
        finally
        {
          localParcel1.recycle();
          localParcel2.recycle();
        }
      }
      
      public float getWidth()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.maps.model.internal.IGroundOverlayDelegate");
          this.lb.transact(7, localParcel1, localParcel2, 0);
          localParcel2.readException();
          float f = localParcel2.readFloat();
          return f;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
      
      public float getZIndex()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.maps.model.internal.IGroundOverlayDelegate");
          this.lb.transact(14, localParcel1, localParcel2, 0);
          localParcel2.readException();
          float f = localParcel2.readFloat();
          return f;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
      
      public int hashCodeRemote()
        throws RemoteException
      {
        Parcel localParcel2 = Parcel.obtain();
        Parcel localParcel1 = Parcel.obtain();
        try
        {
          localParcel2.writeInterfaceToken("com.google.android.gms.maps.model.internal.IGroundOverlayDelegate");
          this.lb.transact(20, localParcel2, localParcel1, 0);
          localParcel1.readException();
          int i = localParcel1.readInt();
          return i;
        }
        finally
        {
          localParcel1.recycle();
          localParcel2.recycle();
        }
      }
      
      public boolean isVisible()
        throws RemoteException
      {
        boolean bool = false;
        Parcel localParcel2 = Parcel.obtain();
        Parcel localParcel1 = Parcel.obtain();
        try
        {
          localParcel2.writeInterfaceToken("com.google.android.gms.maps.model.internal.IGroundOverlayDelegate");
          this.lb.transact(16, localParcel2, localParcel1, 0);
          localParcel1.readException();
          int i = localParcel1.readInt();
          if (i != 0) {
            bool = true;
          }
          return bool;
        }
        finally
        {
          localParcel1.recycle();
          localParcel2.recycle();
        }
      }
      
      /* Error */
      public void m(d paramd)
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
        //   19: invokeinterface 112 1 0
        //   24: astore_1
        //   25: aload_2
        //   26: aload_1
        //   27: invokevirtual 59	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   30: aload_0
        //   31: getfield 18	com/google/android/gms/maps/model/internal/c$a$a:lb	Landroid/os/IBinder;
        //   34: bipush 21
        //   36: aload_2
        //   37: aload_3
        //   38: iconst_0
        //   39: invokeinterface 44 5 0
        //   44: pop
        //   45: aload_3
        //   46: invokevirtual 47	android/os/Parcel:readException	()V
        //   49: aload_3
        //   50: invokevirtual 50	android/os/Parcel:recycle	()V
        //   53: aload_2
        //   54: invokevirtual 50	android/os/Parcel:recycle	()V
        //   57: return
        //   58: aconst_null
        //   59: astore_1
        //   60: goto -35 -> 25
        //   63: astore_1
        //   64: aload_3
        //   65: invokevirtual 50	android/os/Parcel:recycle	()V
        //   68: aload_2
        //   69: invokevirtual 50	android/os/Parcel:recycle	()V
        //   72: aload_1
        //   73: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	74	0	this	a
        //   0	74	1	paramd	d
        //   3	66	2	localParcel1	Parcel
        //   7	58	3	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   8	14	63	finally
        //   18	25	63	finally
        //   25	49	63	finally
      }
      
      public void remove()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.maps.model.internal.IGroundOverlayDelegate");
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
      
      public void setBearing(float paramFloat)
        throws RemoteException
      {
        Parcel localParcel2 = Parcel.obtain();
        Parcel localParcel1 = Parcel.obtain();
        try
        {
          localParcel2.writeInterfaceToken("com.google.android.gms.maps.model.internal.IGroundOverlayDelegate");
          localParcel2.writeFloat(paramFloat);
          this.lb.transact(11, localParcel2, localParcel1, 0);
          localParcel1.readException();
          return;
        }
        finally
        {
          localParcel1.recycle();
          localParcel2.recycle();
        }
      }
      
      public void setDimensions(float paramFloat)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.maps.model.internal.IGroundOverlayDelegate");
          localParcel1.writeFloat(paramFloat);
          this.lb.transact(5, localParcel1, localParcel2, 0);
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
      public void setPosition(LatLng paramLatLng)
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
        //   20: invokevirtual 121	android/os/Parcel:writeInt	(I)V
        //   23: aload_1
        //   24: aload_3
        //   25: iconst_0
        //   26: invokevirtual 125	com/google/android/gms/maps/model/LatLng:writeToParcel	(Landroid/os/Parcel;I)V
        //   29: aload_0
        //   30: getfield 18	com/google/android/gms/maps/model/internal/c$a$a:lb	Landroid/os/IBinder;
        //   33: iconst_3
        //   34: aload_3
        //   35: aload_2
        //   36: iconst_0
        //   37: invokeinterface 44 5 0
        //   42: pop
        //   43: aload_2
        //   44: invokevirtual 47	android/os/Parcel:readException	()V
        //   47: aload_2
        //   48: invokevirtual 50	android/os/Parcel:recycle	()V
        //   51: aload_3
        //   52: invokevirtual 50	android/os/Parcel:recycle	()V
        //   55: return
        //   56: aload_3
        //   57: iconst_0
        //   58: invokevirtual 121	android/os/Parcel:writeInt	(I)V
        //   61: goto -32 -> 29
        //   64: astore_1
        //   65: aload_2
        //   66: invokevirtual 50	android/os/Parcel:recycle	()V
        //   69: aload_3
        //   70: invokevirtual 50	android/os/Parcel:recycle	()V
        //   73: aload_1
        //   74: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	75	0	this	a
        //   0	75	1	paramLatLng	LatLng
        //   7	59	2	localParcel1	Parcel
        //   3	67	3	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   8	14	64	finally
        //   18	29	64	finally
        //   29	47	64	finally
        //   56	61	64	finally
      }
      
      /* Error */
      public void setPositionFromBounds(LatLngBounds paramLatLngBounds)
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
        //   20: invokevirtual 121	android/os/Parcel:writeInt	(I)V
        //   23: aload_1
        //   24: aload_3
        //   25: iconst_0
        //   26: invokevirtual 128	com/google/android/gms/maps/model/LatLngBounds:writeToParcel	(Landroid/os/Parcel;I)V
        //   29: aload_0
        //   30: getfield 18	com/google/android/gms/maps/model/internal/c$a$a:lb	Landroid/os/IBinder;
        //   33: bipush 9
        //   35: aload_3
        //   36: aload_2
        //   37: iconst_0
        //   38: invokeinterface 44 5 0
        //   43: pop
        //   44: aload_2
        //   45: invokevirtual 47	android/os/Parcel:readException	()V
        //   48: aload_2
        //   49: invokevirtual 50	android/os/Parcel:recycle	()V
        //   52: aload_3
        //   53: invokevirtual 50	android/os/Parcel:recycle	()V
        //   56: return
        //   57: aload_3
        //   58: iconst_0
        //   59: invokevirtual 121	android/os/Parcel:writeInt	(I)V
        //   62: goto -33 -> 29
        //   65: astore_1
        //   66: aload_2
        //   67: invokevirtual 50	android/os/Parcel:recycle	()V
        //   70: aload_3
        //   71: invokevirtual 50	android/os/Parcel:recycle	()V
        //   74: aload_1
        //   75: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	76	0	this	a
        //   0	76	1	paramLatLngBounds	LatLngBounds
        //   7	60	2	localParcel1	Parcel
        //   3	68	3	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   8	14	65	finally
        //   18	29	65	finally
        //   29	48	65	finally
        //   57	62	65	finally
      }
      
      public void setTransparency(float paramFloat)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.maps.model.internal.IGroundOverlayDelegate");
          localParcel1.writeFloat(paramFloat);
          this.lb.transact(17, localParcel1, localParcel2, 0);
          localParcel2.readException();
          return;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
      
      public void setVisible(boolean paramBoolean)
        throws RemoteException
      {
        int i = 0;
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.maps.model.internal.IGroundOverlayDelegate");
          if (paramBoolean) {
            i = 1;
          }
          localParcel1.writeInt(i);
          this.lb.transact(15, localParcel1, localParcel2, 0);
          localParcel2.readException();
          return;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
      
      public void setZIndex(float paramFloat)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.maps.model.internal.IGroundOverlayDelegate");
          localParcel1.writeFloat(paramFloat);
          this.lb.transact(13, localParcel1, localParcel2, 0);
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


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\maps\model\internal\c.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */