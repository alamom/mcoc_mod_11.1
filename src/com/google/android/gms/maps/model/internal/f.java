package com.google.android.gms.maps.model.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.d;
import com.google.android.gms.dynamic.d.a;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.i;

public abstract interface f
  extends IInterface
{
  public abstract float getAlpha()
    throws RemoteException;
  
  public abstract String getId()
    throws RemoteException;
  
  public abstract LatLng getPosition()
    throws RemoteException;
  
  public abstract float getRotation()
    throws RemoteException;
  
  public abstract String getSnippet()
    throws RemoteException;
  
  public abstract String getTitle()
    throws RemoteException;
  
  public abstract boolean h(f paramf)
    throws RemoteException;
  
  public abstract int hashCodeRemote()
    throws RemoteException;
  
  public abstract void hideInfoWindow()
    throws RemoteException;
  
  public abstract boolean isDraggable()
    throws RemoteException;
  
  public abstract boolean isFlat()
    throws RemoteException;
  
  public abstract boolean isInfoWindowShown()
    throws RemoteException;
  
  public abstract boolean isVisible()
    throws RemoteException;
  
  public abstract void n(d paramd)
    throws RemoteException;
  
  public abstract void remove()
    throws RemoteException;
  
  public abstract void setAlpha(float paramFloat)
    throws RemoteException;
  
  public abstract void setAnchor(float paramFloat1, float paramFloat2)
    throws RemoteException;
  
  public abstract void setDraggable(boolean paramBoolean)
    throws RemoteException;
  
  public abstract void setFlat(boolean paramBoolean)
    throws RemoteException;
  
  public abstract void setInfoWindowAnchor(float paramFloat1, float paramFloat2)
    throws RemoteException;
  
  public abstract void setPosition(LatLng paramLatLng)
    throws RemoteException;
  
  public abstract void setRotation(float paramFloat)
    throws RemoteException;
  
  public abstract void setSnippet(String paramString)
    throws RemoteException;
  
  public abstract void setTitle(String paramString)
    throws RemoteException;
  
  public abstract void setVisible(boolean paramBoolean)
    throws RemoteException;
  
  public abstract void showInfoWindow()
    throws RemoteException;
  
  public static abstract class a
    extends Binder
    implements f
  {
    public static f bu(IBinder paramIBinder)
    {
      if (paramIBinder == null) {
        paramIBinder = null;
      }
      for (;;)
      {
        return paramIBinder;
        IInterface localIInterface = paramIBinder.queryLocalInterface("com.google.android.gms.maps.model.internal.IMarkerDelegate");
        if ((localIInterface != null) && ((localIInterface instanceof f))) {
          paramIBinder = (f)localIInterface;
        } else {
          paramIBinder = new a(paramIBinder);
        }
      }
    }
    
    public boolean onTransact(int paramInt1, Parcel paramParcel1, Parcel paramParcel2, int paramInt2)
      throws RemoteException
    {
      int i = 0;
      int j = 0;
      boolean bool3 = false;
      int k = 0;
      int m = 0;
      boolean bool4 = false;
      int n = 0;
      boolean bool1 = false;
      boolean bool2 = true;
      switch (paramInt1)
      {
      default: 
        bool1 = super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
      }
      for (;;)
      {
        return bool1;
        paramParcel2.writeString("com.google.android.gms.maps.model.internal.IMarkerDelegate");
        bool1 = bool2;
        continue;
        paramParcel1.enforceInterface("com.google.android.gms.maps.model.internal.IMarkerDelegate");
        remove();
        paramParcel2.writeNoException();
        bool1 = bool2;
        continue;
        paramParcel1.enforceInterface("com.google.android.gms.maps.model.internal.IMarkerDelegate");
        paramParcel1 = getId();
        paramParcel2.writeNoException();
        paramParcel2.writeString(paramParcel1);
        bool1 = bool2;
        continue;
        paramParcel1.enforceInterface("com.google.android.gms.maps.model.internal.IMarkerDelegate");
        if (paramParcel1.readInt() != 0) {}
        for (paramParcel1 = LatLng.CREATOR.cM(paramParcel1);; paramParcel1 = null)
        {
          setPosition(paramParcel1);
          paramParcel2.writeNoException();
          bool1 = bool2;
          break;
        }
        paramParcel1.enforceInterface("com.google.android.gms.maps.model.internal.IMarkerDelegate");
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
          paramParcel1.enforceInterface("com.google.android.gms.maps.model.internal.IMarkerDelegate");
          setTitle(paramParcel1.readString());
          paramParcel2.writeNoException();
          bool1 = bool2;
          continue;
          paramParcel1.enforceInterface("com.google.android.gms.maps.model.internal.IMarkerDelegate");
          paramParcel1 = getTitle();
          paramParcel2.writeNoException();
          paramParcel2.writeString(paramParcel1);
          bool1 = bool2;
          continue;
          paramParcel1.enforceInterface("com.google.android.gms.maps.model.internal.IMarkerDelegate");
          setSnippet(paramParcel1.readString());
          paramParcel2.writeNoException();
          bool1 = bool2;
          continue;
          paramParcel1.enforceInterface("com.google.android.gms.maps.model.internal.IMarkerDelegate");
          paramParcel1 = getSnippet();
          paramParcel2.writeNoException();
          paramParcel2.writeString(paramParcel1);
          bool1 = bool2;
          continue;
          paramParcel1.enforceInterface("com.google.android.gms.maps.model.internal.IMarkerDelegate");
          if (paramParcel1.readInt() != 0) {
            bool1 = true;
          }
          setDraggable(bool1);
          paramParcel2.writeNoException();
          bool1 = bool2;
          continue;
          paramParcel1.enforceInterface("com.google.android.gms.maps.model.internal.IMarkerDelegate");
          bool1 = isDraggable();
          paramParcel2.writeNoException();
          paramInt1 = i;
          if (bool1) {
            paramInt1 = 1;
          }
          paramParcel2.writeInt(paramInt1);
          bool1 = bool2;
          continue;
          paramParcel1.enforceInterface("com.google.android.gms.maps.model.internal.IMarkerDelegate");
          showInfoWindow();
          paramParcel2.writeNoException();
          bool1 = bool2;
          continue;
          paramParcel1.enforceInterface("com.google.android.gms.maps.model.internal.IMarkerDelegate");
          hideInfoWindow();
          paramParcel2.writeNoException();
          bool1 = bool2;
          continue;
          paramParcel1.enforceInterface("com.google.android.gms.maps.model.internal.IMarkerDelegate");
          bool1 = isInfoWindowShown();
          paramParcel2.writeNoException();
          paramInt1 = j;
          if (bool1) {
            paramInt1 = 1;
          }
          paramParcel2.writeInt(paramInt1);
          bool1 = bool2;
          continue;
          paramParcel1.enforceInterface("com.google.android.gms.maps.model.internal.IMarkerDelegate");
          bool1 = bool3;
          if (paramParcel1.readInt() != 0) {
            bool1 = true;
          }
          setVisible(bool1);
          paramParcel2.writeNoException();
          bool1 = bool2;
          continue;
          paramParcel1.enforceInterface("com.google.android.gms.maps.model.internal.IMarkerDelegate");
          bool1 = isVisible();
          paramParcel2.writeNoException();
          paramInt1 = k;
          if (bool1) {
            paramInt1 = 1;
          }
          paramParcel2.writeInt(paramInt1);
          bool1 = bool2;
          continue;
          paramParcel1.enforceInterface("com.google.android.gms.maps.model.internal.IMarkerDelegate");
          bool1 = h(bu(paramParcel1.readStrongBinder()));
          paramParcel2.writeNoException();
          paramInt1 = m;
          if (bool1) {
            paramInt1 = 1;
          }
          paramParcel2.writeInt(paramInt1);
          bool1 = bool2;
          continue;
          paramParcel1.enforceInterface("com.google.android.gms.maps.model.internal.IMarkerDelegate");
          paramInt1 = hashCodeRemote();
          paramParcel2.writeNoException();
          paramParcel2.writeInt(paramInt1);
          bool1 = bool2;
          continue;
          paramParcel1.enforceInterface("com.google.android.gms.maps.model.internal.IMarkerDelegate");
          n(d.a.am(paramParcel1.readStrongBinder()));
          paramParcel2.writeNoException();
          bool1 = bool2;
          continue;
          paramParcel1.enforceInterface("com.google.android.gms.maps.model.internal.IMarkerDelegate");
          setAnchor(paramParcel1.readFloat(), paramParcel1.readFloat());
          paramParcel2.writeNoException();
          bool1 = bool2;
          continue;
          paramParcel1.enforceInterface("com.google.android.gms.maps.model.internal.IMarkerDelegate");
          bool1 = bool4;
          if (paramParcel1.readInt() != 0) {
            bool1 = true;
          }
          setFlat(bool1);
          paramParcel2.writeNoException();
          bool1 = bool2;
          continue;
          paramParcel1.enforceInterface("com.google.android.gms.maps.model.internal.IMarkerDelegate");
          bool1 = isFlat();
          paramParcel2.writeNoException();
          paramInt1 = n;
          if (bool1) {
            paramInt1 = 1;
          }
          paramParcel2.writeInt(paramInt1);
          bool1 = bool2;
          continue;
          paramParcel1.enforceInterface("com.google.android.gms.maps.model.internal.IMarkerDelegate");
          setRotation(paramParcel1.readFloat());
          paramParcel2.writeNoException();
          bool1 = bool2;
          continue;
          paramParcel1.enforceInterface("com.google.android.gms.maps.model.internal.IMarkerDelegate");
          float f = getRotation();
          paramParcel2.writeNoException();
          paramParcel2.writeFloat(f);
          bool1 = bool2;
          continue;
          paramParcel1.enforceInterface("com.google.android.gms.maps.model.internal.IMarkerDelegate");
          setInfoWindowAnchor(paramParcel1.readFloat(), paramParcel1.readFloat());
          paramParcel2.writeNoException();
          bool1 = bool2;
          continue;
          paramParcel1.enforceInterface("com.google.android.gms.maps.model.internal.IMarkerDelegate");
          setAlpha(paramParcel1.readFloat());
          paramParcel2.writeNoException();
          bool1 = bool2;
          continue;
          paramParcel1.enforceInterface("com.google.android.gms.maps.model.internal.IMarkerDelegate");
          f = getAlpha();
          paramParcel2.writeNoException();
          paramParcel2.writeFloat(f);
          bool1 = bool2;
        }
      }
    }
    
    private static class a
      implements f
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
      
      public float getAlpha()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.maps.model.internal.IMarkerDelegate");
          this.lb.transact(26, localParcel1, localParcel2, 0);
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
          localParcel1.writeInterfaceToken("com.google.android.gms.maps.model.internal.IMarkerDelegate");
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
        //   0: invokestatic 31	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore_3
        //   4: invokestatic 31	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   7: astore_2
        //   8: aload_3
        //   9: ldc 33
        //   11: invokevirtual 37	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   14: aload_0
        //   15: getfield 18	com/google/android/gms/maps/model/internal/f$a$a:lb	Landroid/os/IBinder;
        //   18: iconst_4
        //   19: aload_3
        //   20: aload_2
        //   21: iconst_0
        //   22: invokeinterface 43 5 0
        //   27: pop
        //   28: aload_2
        //   29: invokevirtual 46	android/os/Parcel:readException	()V
        //   32: aload_2
        //   33: invokevirtual 64	android/os/Parcel:readInt	()I
        //   36: ifeq +21 -> 57
        //   39: getstatic 70	com/google/android/gms/maps/model/LatLng:CREATOR	Lcom/google/android/gms/maps/model/i;
        //   42: aload_2
        //   43: invokevirtual 76	com/google/android/gms/maps/model/i:cM	(Landroid/os/Parcel;)Lcom/google/android/gms/maps/model/LatLng;
        //   46: astore_1
        //   47: aload_2
        //   48: invokevirtual 52	android/os/Parcel:recycle	()V
        //   51: aload_3
        //   52: invokevirtual 52	android/os/Parcel:recycle	()V
        //   55: aload_1
        //   56: areturn
        //   57: aconst_null
        //   58: astore_1
        //   59: goto -12 -> 47
        //   62: astore_1
        //   63: aload_2
        //   64: invokevirtual 52	android/os/Parcel:recycle	()V
        //   67: aload_3
        //   68: invokevirtual 52	android/os/Parcel:recycle	()V
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
      
      public float getRotation()
        throws RemoteException
      {
        Parcel localParcel2 = Parcel.obtain();
        Parcel localParcel1 = Parcel.obtain();
        try
        {
          localParcel2.writeInterfaceToken("com.google.android.gms.maps.model.internal.IMarkerDelegate");
          this.lb.transact(23, localParcel2, localParcel1, 0);
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
      
      public String getSnippet()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.maps.model.internal.IMarkerDelegate");
          this.lb.transact(8, localParcel1, localParcel2, 0);
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
      
      public String getTitle()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.maps.model.internal.IMarkerDelegate");
          this.lb.transact(6, localParcel1, localParcel2, 0);
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
      public boolean h(f paramf)
        throws RemoteException
      {
        // Byte code:
        //   0: iconst_0
        //   1: istore_3
        //   2: invokestatic 31	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   5: astore 4
        //   7: invokestatic 31	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   10: astore 5
        //   12: aload 4
        //   14: ldc 33
        //   16: invokevirtual 37	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   19: aload_1
        //   20: ifnull +62 -> 82
        //   23: aload_1
        //   24: invokeinterface 83 1 0
        //   29: astore_1
        //   30: aload 4
        //   32: aload_1
        //   33: invokevirtual 86	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   36: aload_0
        //   37: getfield 18	com/google/android/gms/maps/model/internal/f$a$a:lb	Landroid/os/IBinder;
        //   40: bipush 16
        //   42: aload 4
        //   44: aload 5
        //   46: iconst_0
        //   47: invokeinterface 43 5 0
        //   52: pop
        //   53: aload 5
        //   55: invokevirtual 46	android/os/Parcel:readException	()V
        //   58: aload 5
        //   60: invokevirtual 64	android/os/Parcel:readInt	()I
        //   63: istore_2
        //   64: iload_2
        //   65: ifeq +5 -> 70
        //   68: iconst_1
        //   69: istore_3
        //   70: aload 5
        //   72: invokevirtual 52	android/os/Parcel:recycle	()V
        //   75: aload 4
        //   77: invokevirtual 52	android/os/Parcel:recycle	()V
        //   80: iload_3
        //   81: ireturn
        //   82: aconst_null
        //   83: astore_1
        //   84: goto -54 -> 30
        //   87: astore_1
        //   88: aload 5
        //   90: invokevirtual 52	android/os/Parcel:recycle	()V
        //   93: aload 4
        //   95: invokevirtual 52	android/os/Parcel:recycle	()V
        //   98: aload_1
        //   99: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	100	0	this	a
        //   0	100	1	paramf	f
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
      
      public int hashCodeRemote()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.maps.model.internal.IMarkerDelegate");
          this.lb.transact(17, localParcel1, localParcel2, 0);
          localParcel2.readException();
          int i = localParcel2.readInt();
          return i;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
      
      public void hideInfoWindow()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.maps.model.internal.IMarkerDelegate");
          this.lb.transact(12, localParcel1, localParcel2, 0);
          localParcel2.readException();
          return;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
      
      public boolean isDraggable()
        throws RemoteException
      {
        boolean bool = false;
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.maps.model.internal.IMarkerDelegate");
          this.lb.transact(10, localParcel1, localParcel2, 0);
          localParcel2.readException();
          int i = localParcel2.readInt();
          if (i != 0) {
            bool = true;
          }
          return bool;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
      
      public boolean isFlat()
        throws RemoteException
      {
        boolean bool = false;
        Parcel localParcel2 = Parcel.obtain();
        Parcel localParcel1 = Parcel.obtain();
        try
        {
          localParcel2.writeInterfaceToken("com.google.android.gms.maps.model.internal.IMarkerDelegate");
          this.lb.transact(21, localParcel2, localParcel1, 0);
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
      
      public boolean isInfoWindowShown()
        throws RemoteException
      {
        boolean bool = false;
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.maps.model.internal.IMarkerDelegate");
          this.lb.transact(13, localParcel1, localParcel2, 0);
          localParcel2.readException();
          int i = localParcel2.readInt();
          if (i != 0) {
            bool = true;
          }
          return bool;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
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
          localParcel2.writeInterfaceToken("com.google.android.gms.maps.model.internal.IMarkerDelegate");
          this.lb.transact(15, localParcel2, localParcel1, 0);
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
      public void n(d paramd)
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
        //   14: aload_1
        //   15: ifnull +43 -> 58
        //   18: aload_1
        //   19: invokeinterface 98 1 0
        //   24: astore_1
        //   25: aload_2
        //   26: aload_1
        //   27: invokevirtual 86	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   30: aload_0
        //   31: getfield 18	com/google/android/gms/maps/model/internal/f$a$a:lb	Landroid/os/IBinder;
        //   34: bipush 18
        //   36: aload_2
        //   37: aload_3
        //   38: iconst_0
        //   39: invokeinterface 43 5 0
        //   44: pop
        //   45: aload_3
        //   46: invokevirtual 46	android/os/Parcel:readException	()V
        //   49: aload_3
        //   50: invokevirtual 52	android/os/Parcel:recycle	()V
        //   53: aload_2
        //   54: invokevirtual 52	android/os/Parcel:recycle	()V
        //   57: return
        //   58: aconst_null
        //   59: astore_1
        //   60: goto -35 -> 25
        //   63: astore_1
        //   64: aload_3
        //   65: invokevirtual 52	android/os/Parcel:recycle	()V
        //   68: aload_2
        //   69: invokevirtual 52	android/os/Parcel:recycle	()V
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
        Parcel localParcel2 = Parcel.obtain();
        Parcel localParcel1 = Parcel.obtain();
        try
        {
          localParcel2.writeInterfaceToken("com.google.android.gms.maps.model.internal.IMarkerDelegate");
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
      
      public void setAlpha(float paramFloat)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.maps.model.internal.IMarkerDelegate");
          localParcel1.writeFloat(paramFloat);
          this.lb.transact(25, localParcel1, localParcel2, 0);
          localParcel2.readException();
          return;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
      
      public void setAnchor(float paramFloat1, float paramFloat2)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.maps.model.internal.IMarkerDelegate");
          localParcel1.writeFloat(paramFloat1);
          localParcel1.writeFloat(paramFloat2);
          this.lb.transact(19, localParcel1, localParcel2, 0);
          localParcel2.readException();
          return;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
      
      public void setDraggable(boolean paramBoolean)
        throws RemoteException
      {
        int i = 0;
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.maps.model.internal.IMarkerDelegate");
          if (paramBoolean) {
            i = 1;
          }
          localParcel1.writeInt(i);
          this.lb.transact(9, localParcel1, localParcel2, 0);
          localParcel2.readException();
          return;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
      
      public void setFlat(boolean paramBoolean)
        throws RemoteException
      {
        int i = 0;
        Parcel localParcel2 = Parcel.obtain();
        Parcel localParcel1 = Parcel.obtain();
        try
        {
          localParcel2.writeInterfaceToken("com.google.android.gms.maps.model.internal.IMarkerDelegate");
          if (paramBoolean) {
            i = 1;
          }
          localParcel2.writeInt(i);
          this.lb.transact(20, localParcel2, localParcel1, 0);
          localParcel1.readException();
          return;
        }
        finally
        {
          localParcel1.recycle();
          localParcel2.recycle();
        }
      }
      
      public void setInfoWindowAnchor(float paramFloat1, float paramFloat2)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.maps.model.internal.IMarkerDelegate");
          localParcel1.writeFloat(paramFloat1);
          localParcel1.writeFloat(paramFloat2);
          this.lb.transact(24, localParcel1, localParcel2, 0);
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
        //   0: invokestatic 31	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore_3
        //   4: invokestatic 31	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   7: astore_2
        //   8: aload_3
        //   9: ldc 33
        //   11: invokevirtual 37	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   14: aload_1
        //   15: ifnull +41 -> 56
        //   18: aload_3
        //   19: iconst_1
        //   20: invokevirtual 112	android/os/Parcel:writeInt	(I)V
        //   23: aload_1
        //   24: aload_3
        //   25: iconst_0
        //   26: invokevirtual 120	com/google/android/gms/maps/model/LatLng:writeToParcel	(Landroid/os/Parcel;I)V
        //   29: aload_0
        //   30: getfield 18	com/google/android/gms/maps/model/internal/f$a$a:lb	Landroid/os/IBinder;
        //   33: iconst_3
        //   34: aload_3
        //   35: aload_2
        //   36: iconst_0
        //   37: invokeinterface 43 5 0
        //   42: pop
        //   43: aload_2
        //   44: invokevirtual 46	android/os/Parcel:readException	()V
        //   47: aload_2
        //   48: invokevirtual 52	android/os/Parcel:recycle	()V
        //   51: aload_3
        //   52: invokevirtual 52	android/os/Parcel:recycle	()V
        //   55: return
        //   56: aload_3
        //   57: iconst_0
        //   58: invokevirtual 112	android/os/Parcel:writeInt	(I)V
        //   61: goto -32 -> 29
        //   64: astore_1
        //   65: aload_2
        //   66: invokevirtual 52	android/os/Parcel:recycle	()V
        //   69: aload_3
        //   70: invokevirtual 52	android/os/Parcel:recycle	()V
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
      
      public void setRotation(float paramFloat)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.maps.model.internal.IMarkerDelegate");
          localParcel1.writeFloat(paramFloat);
          this.lb.transact(22, localParcel1, localParcel2, 0);
          localParcel2.readException();
          return;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
      
      public void setSnippet(String paramString)
        throws RemoteException
      {
        Parcel localParcel2 = Parcel.obtain();
        Parcel localParcel1 = Parcel.obtain();
        try
        {
          localParcel2.writeInterfaceToken("com.google.android.gms.maps.model.internal.IMarkerDelegate");
          localParcel2.writeString(paramString);
          this.lb.transact(7, localParcel2, localParcel1, 0);
          localParcel1.readException();
          return;
        }
        finally
        {
          localParcel1.recycle();
          localParcel2.recycle();
        }
      }
      
      public void setTitle(String paramString)
        throws RemoteException
      {
        Parcel localParcel2 = Parcel.obtain();
        Parcel localParcel1 = Parcel.obtain();
        try
        {
          localParcel2.writeInterfaceToken("com.google.android.gms.maps.model.internal.IMarkerDelegate");
          localParcel2.writeString(paramString);
          this.lb.transact(5, localParcel2, localParcel1, 0);
          localParcel1.readException();
          return;
        }
        finally
        {
          localParcel1.recycle();
          localParcel2.recycle();
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
          localParcel1.writeInterfaceToken("com.google.android.gms.maps.model.internal.IMarkerDelegate");
          if (paramBoolean) {
            i = 1;
          }
          localParcel1.writeInt(i);
          this.lb.transact(14, localParcel1, localParcel2, 0);
          localParcel2.readException();
          return;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
      
      public void showInfoWindow()
        throws RemoteException
      {
        Parcel localParcel2 = Parcel.obtain();
        Parcel localParcel1 = Parcel.obtain();
        try
        {
          localParcel2.writeInterfaceToken("com.google.android.gms.maps.model.internal.IMarkerDelegate");
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
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\maps\model\internal\f.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */