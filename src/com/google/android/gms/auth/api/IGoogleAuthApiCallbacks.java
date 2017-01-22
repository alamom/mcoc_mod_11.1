package com.google.android.gms.auth.api;

import android.app.PendingIntent;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.os.RemoteException;

public abstract interface IGoogleAuthApiCallbacks
  extends IInterface
{
  public abstract void onConnectionSuccess(GoogleAuthApiResponse paramGoogleAuthApiResponse)
    throws RemoteException;
  
  public abstract void onError(int paramInt, String paramString, PendingIntent paramPendingIntent)
    throws RemoteException;
  
  public static abstract class Stub
    extends Binder
    implements IGoogleAuthApiCallbacks
  {
    public Stub()
    {
      attachInterface(this, "com.google.android.gms.auth.api.IGoogleAuthApiCallbacks");
    }
    
    public static IGoogleAuthApiCallbacks asInterface(IBinder paramIBinder)
    {
      if (paramIBinder == null) {
        paramIBinder = null;
      }
      for (;;)
      {
        return paramIBinder;
        IInterface localIInterface = paramIBinder.queryLocalInterface("com.google.android.gms.auth.api.IGoogleAuthApiCallbacks");
        if ((localIInterface != null) && ((localIInterface instanceof IGoogleAuthApiCallbacks))) {
          paramIBinder = (IGoogleAuthApiCallbacks)localIInterface;
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
      Object localObject = null;
      String str = null;
      boolean bool;
      switch (paramInt1)
      {
      default: 
        bool = super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
      }
      for (;;)
      {
        return bool;
        paramParcel2.writeString("com.google.android.gms.auth.api.IGoogleAuthApiCallbacks");
        bool = true;
        continue;
        paramParcel1.enforceInterface("com.google.android.gms.auth.api.IGoogleAuthApiCallbacks");
        localObject = str;
        if (paramParcel1.readInt() != 0) {
          localObject = GoogleAuthApiResponse.CREATOR.createFromParcel(paramParcel1);
        }
        onConnectionSuccess((GoogleAuthApiResponse)localObject);
        paramParcel2.writeNoException();
        bool = true;
        continue;
        paramParcel1.enforceInterface("com.google.android.gms.auth.api.IGoogleAuthApiCallbacks");
        paramInt1 = paramParcel1.readInt();
        str = paramParcel1.readString();
        if (paramParcel1.readInt() != 0) {
          localObject = (PendingIntent)PendingIntent.CREATOR.createFromParcel(paramParcel1);
        }
        onError(paramInt1, str, (PendingIntent)localObject);
        paramParcel2.writeNoException();
        bool = true;
      }
    }
    
    private static class a
      implements IGoogleAuthApiCallbacks
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
      public void onConnectionSuccess(GoogleAuthApiResponse paramGoogleAuthApiResponse)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 32	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore_3
        //   4: invokestatic 32	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   7: astore_2
        //   8: aload_3
        //   9: ldc 34
        //   11: invokevirtual 38	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   14: aload_1
        //   15: ifnull +41 -> 56
        //   18: aload_3
        //   19: iconst_1
        //   20: invokevirtual 42	android/os/Parcel:writeInt	(I)V
        //   23: aload_1
        //   24: aload_3
        //   25: iconst_0
        //   26: invokevirtual 48	com/google/android/gms/auth/api/GoogleAuthApiResponse:writeToParcel	(Landroid/os/Parcel;I)V
        //   29: aload_0
        //   30: getfield 19	com/google/android/gms/auth/api/IGoogleAuthApiCallbacks$Stub$a:lb	Landroid/os/IBinder;
        //   33: iconst_1
        //   34: aload_3
        //   35: aload_2
        //   36: iconst_0
        //   37: invokeinterface 54 5 0
        //   42: pop
        //   43: aload_2
        //   44: invokevirtual 57	android/os/Parcel:readException	()V
        //   47: aload_2
        //   48: invokevirtual 60	android/os/Parcel:recycle	()V
        //   51: aload_3
        //   52: invokevirtual 60	android/os/Parcel:recycle	()V
        //   55: return
        //   56: aload_3
        //   57: iconst_0
        //   58: invokevirtual 42	android/os/Parcel:writeInt	(I)V
        //   61: goto -32 -> 29
        //   64: astore_1
        //   65: aload_2
        //   66: invokevirtual 60	android/os/Parcel:recycle	()V
        //   69: aload_3
        //   70: invokevirtual 60	android/os/Parcel:recycle	()V
        //   73: aload_1
        //   74: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	75	0	this	a
        //   0	75	1	paramGoogleAuthApiResponse	GoogleAuthApiResponse
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
      public void onError(int paramInt, String paramString, PendingIntent paramPendingIntent)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 32	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore 5
        //   5: invokestatic 32	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   8: astore 4
        //   10: aload 5
        //   12: ldc 34
        //   14: invokevirtual 38	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   17: aload 5
        //   19: iload_1
        //   20: invokevirtual 42	android/os/Parcel:writeInt	(I)V
        //   23: aload 5
        //   25: aload_2
        //   26: invokevirtual 66	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   29: aload_3
        //   30: ifnull +48 -> 78
        //   33: aload 5
        //   35: iconst_1
        //   36: invokevirtual 42	android/os/Parcel:writeInt	(I)V
        //   39: aload_3
        //   40: aload 5
        //   42: iconst_0
        //   43: invokevirtual 69	android/app/PendingIntent:writeToParcel	(Landroid/os/Parcel;I)V
        //   46: aload_0
        //   47: getfield 19	com/google/android/gms/auth/api/IGoogleAuthApiCallbacks$Stub$a:lb	Landroid/os/IBinder;
        //   50: iconst_2
        //   51: aload 5
        //   53: aload 4
        //   55: iconst_0
        //   56: invokeinterface 54 5 0
        //   61: pop
        //   62: aload 4
        //   64: invokevirtual 57	android/os/Parcel:readException	()V
        //   67: aload 4
        //   69: invokevirtual 60	android/os/Parcel:recycle	()V
        //   72: aload 5
        //   74: invokevirtual 60	android/os/Parcel:recycle	()V
        //   77: return
        //   78: aload 5
        //   80: iconst_0
        //   81: invokevirtual 42	android/os/Parcel:writeInt	(I)V
        //   84: goto -38 -> 46
        //   87: astore_2
        //   88: aload 4
        //   90: invokevirtual 60	android/os/Parcel:recycle	()V
        //   93: aload 5
        //   95: invokevirtual 60	android/os/Parcel:recycle	()V
        //   98: aload_2
        //   99: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	100	0	this	a
        //   0	100	1	paramInt	int
        //   0	100	2	paramString	String
        //   0	100	3	paramPendingIntent	PendingIntent
        //   8	81	4	localParcel1	Parcel
        //   3	91	5	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   10	29	87	finally
        //   33	46	87	finally
        //   46	67	87	finally
        //   78	84	87	finally
      }
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\auth\api\IGoogleAuthApiCallbacks.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */