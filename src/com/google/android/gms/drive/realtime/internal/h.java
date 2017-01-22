package com.google.android.gms.drive.realtime.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract interface h
  extends IInterface
{
  public abstract void c(boolean paramBoolean1, boolean paramBoolean2)
    throws RemoteException;
  
  public static abstract class a
    extends Binder
    implements h
  {
    public static h ad(IBinder paramIBinder)
    {
      if (paramIBinder == null) {
        paramIBinder = null;
      }
      for (;;)
      {
        return paramIBinder;
        IInterface localIInterface = paramIBinder.queryLocalInterface("com.google.android.gms.drive.realtime.internal.IDocumentSaveStateEventCallback");
        if ((localIInterface != null) && ((localIInterface instanceof h))) {
          paramIBinder = (h)localIInterface;
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
      boolean bool2 = false;
      boolean bool3 = true;
      switch (paramInt1)
      {
      default: 
      case 1598968902: 
        for (bool1 = super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);; bool1 = bool3)
        {
          return bool1;
          paramParcel2.writeString("com.google.android.gms.drive.realtime.internal.IDocumentSaveStateEventCallback");
        }
      }
      paramParcel1.enforceInterface("com.google.android.gms.drive.realtime.internal.IDocumentSaveStateEventCallback");
      if (paramParcel1.readInt() != 0) {}
      for (boolean bool1 = true;; bool1 = false)
      {
        if (paramParcel1.readInt() != 0) {
          bool2 = true;
        }
        c(bool1, bool2);
        paramParcel2.writeNoException();
        bool1 = bool3;
        break;
      }
    }
    
    private static class a
      implements h
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
      public void c(boolean paramBoolean1, boolean paramBoolean2)
        throws RemoteException
      {
        // Byte code:
        //   0: iconst_1
        //   1: istore 4
        //   3: invokestatic 31	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   6: astore 6
        //   8: invokestatic 31	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   11: astore 7
        //   13: aload 6
        //   15: ldc 33
        //   17: invokevirtual 37	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   20: iload_1
        //   21: ifeq +56 -> 77
        //   24: iconst_1
        //   25: istore_3
        //   26: aload 6
        //   28: iload_3
        //   29: invokevirtual 41	android/os/Parcel:writeInt	(I)V
        //   32: iload_2
        //   33: ifeq +49 -> 82
        //   36: iload 4
        //   38: istore_3
        //   39: aload 6
        //   41: iload_3
        //   42: invokevirtual 41	android/os/Parcel:writeInt	(I)V
        //   45: aload_0
        //   46: getfield 18	com/google/android/gms/drive/realtime/internal/h$a$a:lb	Landroid/os/IBinder;
        //   49: iconst_1
        //   50: aload 6
        //   52: aload 7
        //   54: iconst_0
        //   55: invokeinterface 47 5 0
        //   60: pop
        //   61: aload 7
        //   63: invokevirtual 50	android/os/Parcel:readException	()V
        //   66: aload 7
        //   68: invokevirtual 53	android/os/Parcel:recycle	()V
        //   71: aload 6
        //   73: invokevirtual 53	android/os/Parcel:recycle	()V
        //   76: return
        //   77: iconst_0
        //   78: istore_3
        //   79: goto -53 -> 26
        //   82: iconst_0
        //   83: istore_3
        //   84: goto -45 -> 39
        //   87: astore 5
        //   89: aload 7
        //   91: invokevirtual 53	android/os/Parcel:recycle	()V
        //   94: aload 6
        //   96: invokevirtual 53	android/os/Parcel:recycle	()V
        //   99: aload 5
        //   101: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	102	0	this	a
        //   0	102	1	paramBoolean1	boolean
        //   0	102	2	paramBoolean2	boolean
        //   25	59	3	i	int
        //   1	36	4	j	int
        //   87	13	5	localObject	Object
        //   6	89	6	localParcel1	Parcel
        //   11	79	7	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   13	20	87	finally
        //   26	32	87	finally
        //   39	66	87	finally
      }
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\drive\realtime\internal\h.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */