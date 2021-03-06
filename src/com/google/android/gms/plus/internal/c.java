package com.google.android.gms.plus.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.d;
import com.google.android.gms.dynamic.d.a;

public abstract interface c
  extends IInterface
{
  public abstract d a(d paramd, int paramInt1, int paramInt2, String paramString, int paramInt3)
    throws RemoteException;
  
  public abstract d a(d paramd, int paramInt1, int paramInt2, String paramString1, String paramString2)
    throws RemoteException;
  
  public static abstract class a
    extends Binder
    implements c
  {
    public static c bF(IBinder paramIBinder)
    {
      if (paramIBinder == null) {
        paramIBinder = null;
      }
      for (;;)
      {
        return paramIBinder;
        IInterface localIInterface = paramIBinder.queryLocalInterface("com.google.android.gms.plus.internal.IPlusOneButtonCreator");
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
      Object localObject = null;
      boolean bool;
      switch (paramInt1)
      {
      default: 
        bool = super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
      }
      for (;;)
      {
        return bool;
        paramParcel2.writeString("com.google.android.gms.plus.internal.IPlusOneButtonCreator");
        bool = true;
        continue;
        paramParcel1.enforceInterface("com.google.android.gms.plus.internal.IPlusOneButtonCreator");
        paramParcel1 = a(d.a.am(paramParcel1.readStrongBinder()), paramParcel1.readInt(), paramParcel1.readInt(), paramParcel1.readString(), paramParcel1.readInt());
        paramParcel2.writeNoException();
        if (paramParcel1 != null) {}
        for (paramParcel1 = paramParcel1.asBinder();; paramParcel1 = null)
        {
          paramParcel2.writeStrongBinder(paramParcel1);
          bool = true;
          break;
        }
        paramParcel1.enforceInterface("com.google.android.gms.plus.internal.IPlusOneButtonCreator");
        d locald = a(d.a.am(paramParcel1.readStrongBinder()), paramParcel1.readInt(), paramParcel1.readInt(), paramParcel1.readString(), paramParcel1.readString());
        paramParcel2.writeNoException();
        paramParcel1 = (Parcel)localObject;
        if (locald != null) {
          paramParcel1 = locald.asBinder();
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
      public d a(d paramd, int paramInt1, int paramInt2, String paramString, int paramInt3)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 28	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore 6
        //   5: invokestatic 28	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   8: astore 7
        //   10: aload 6
        //   12: ldc 30
        //   14: invokevirtual 34	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   17: aload_1
        //   18: ifnull +84 -> 102
        //   21: aload_1
        //   22: invokeinterface 40 1 0
        //   27: astore_1
        //   28: aload 6
        //   30: aload_1
        //   31: invokevirtual 43	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   34: aload 6
        //   36: iload_2
        //   37: invokevirtual 47	android/os/Parcel:writeInt	(I)V
        //   40: aload 6
        //   42: iload_3
        //   43: invokevirtual 47	android/os/Parcel:writeInt	(I)V
        //   46: aload 6
        //   48: aload 4
        //   50: invokevirtual 50	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   53: aload 6
        //   55: iload 5
        //   57: invokevirtual 47	android/os/Parcel:writeInt	(I)V
        //   60: aload_0
        //   61: getfield 18	com/google/android/gms/plus/internal/c$a$a:lb	Landroid/os/IBinder;
        //   64: iconst_1
        //   65: aload 6
        //   67: aload 7
        //   69: iconst_0
        //   70: invokeinterface 56 5 0
        //   75: pop
        //   76: aload 7
        //   78: invokevirtual 59	android/os/Parcel:readException	()V
        //   81: aload 7
        //   83: invokevirtual 62	android/os/Parcel:readStrongBinder	()Landroid/os/IBinder;
        //   86: invokestatic 68	com/google/android/gms/dynamic/d$a:am	(Landroid/os/IBinder;)Lcom/google/android/gms/dynamic/d;
        //   89: astore_1
        //   90: aload 7
        //   92: invokevirtual 71	android/os/Parcel:recycle	()V
        //   95: aload 6
        //   97: invokevirtual 71	android/os/Parcel:recycle	()V
        //   100: aload_1
        //   101: areturn
        //   102: aconst_null
        //   103: astore_1
        //   104: goto -76 -> 28
        //   107: astore_1
        //   108: aload 7
        //   110: invokevirtual 71	android/os/Parcel:recycle	()V
        //   113: aload 6
        //   115: invokevirtual 71	android/os/Parcel:recycle	()V
        //   118: aload_1
        //   119: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	120	0	this	a
        //   0	120	1	paramd	d
        //   0	120	2	paramInt1	int
        //   0	120	3	paramInt2	int
        //   0	120	4	paramString	String
        //   0	120	5	paramInt3	int
        //   3	111	6	localParcel1	Parcel
        //   8	101	7	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   10	17	107	finally
        //   21	28	107	finally
        //   28	90	107	finally
      }
      
      /* Error */
      public d a(d paramd, int paramInt1, int paramInt2, String paramString1, String paramString2)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 28	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore 6
        //   5: invokestatic 28	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   8: astore 7
        //   10: aload 6
        //   12: ldc 30
        //   14: invokevirtual 34	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   17: aload_1
        //   18: ifnull +84 -> 102
        //   21: aload_1
        //   22: invokeinterface 40 1 0
        //   27: astore_1
        //   28: aload 6
        //   30: aload_1
        //   31: invokevirtual 43	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   34: aload 6
        //   36: iload_2
        //   37: invokevirtual 47	android/os/Parcel:writeInt	(I)V
        //   40: aload 6
        //   42: iload_3
        //   43: invokevirtual 47	android/os/Parcel:writeInt	(I)V
        //   46: aload 6
        //   48: aload 4
        //   50: invokevirtual 50	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   53: aload 6
        //   55: aload 5
        //   57: invokevirtual 50	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   60: aload_0
        //   61: getfield 18	com/google/android/gms/plus/internal/c$a$a:lb	Landroid/os/IBinder;
        //   64: iconst_2
        //   65: aload 6
        //   67: aload 7
        //   69: iconst_0
        //   70: invokeinterface 56 5 0
        //   75: pop
        //   76: aload 7
        //   78: invokevirtual 59	android/os/Parcel:readException	()V
        //   81: aload 7
        //   83: invokevirtual 62	android/os/Parcel:readStrongBinder	()Landroid/os/IBinder;
        //   86: invokestatic 68	com/google/android/gms/dynamic/d$a:am	(Landroid/os/IBinder;)Lcom/google/android/gms/dynamic/d;
        //   89: astore_1
        //   90: aload 7
        //   92: invokevirtual 71	android/os/Parcel:recycle	()V
        //   95: aload 6
        //   97: invokevirtual 71	android/os/Parcel:recycle	()V
        //   100: aload_1
        //   101: areturn
        //   102: aconst_null
        //   103: astore_1
        //   104: goto -76 -> 28
        //   107: astore_1
        //   108: aload 7
        //   110: invokevirtual 71	android/os/Parcel:recycle	()V
        //   113: aload 6
        //   115: invokevirtual 71	android/os/Parcel:recycle	()V
        //   118: aload_1
        //   119: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	120	0	this	a
        //   0	120	1	paramd	d
        //   0	120	2	paramInt1	int
        //   0	120	3	paramInt2	int
        //   0	120	4	paramString1	String
        //   0	120	5	paramString2	String
        //   3	111	6	localParcel1	Parcel
        //   8	101	7	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   10	17	107	finally
        //   21	28	107	finally
        //   28	90	107	finally
      }
      
      public IBinder asBinder()
      {
        return this.lb;
      }
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\plus\internal\c.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */