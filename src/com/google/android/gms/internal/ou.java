package com.google.android.gms.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.os.RemoteException;
import com.google.android.gms.dynamic.c;
import com.google.android.gms.dynamic.c.a;
import com.google.android.gms.dynamic.d;
import com.google.android.gms.dynamic.d.a;
import com.google.android.gms.wallet.fragment.WalletFragmentOptions;

public abstract interface ou
  extends IInterface
{
  public abstract or a(d paramd, c paramc, WalletFragmentOptions paramWalletFragmentOptions, os paramos)
    throws RemoteException;
  
  public static abstract class a
    extends Binder
    implements ou
  {
    public static ou bM(IBinder paramIBinder)
    {
      if (paramIBinder == null) {
        paramIBinder = null;
      }
      for (;;)
      {
        return paramIBinder;
        IInterface localIInterface = paramIBinder.queryLocalInterface("com.google.android.gms.wallet.internal.IWalletDynamiteCreator");
        if ((localIInterface != null) && ((localIInterface instanceof ou))) {
          paramIBinder = (ou)localIInterface;
        } else {
          paramIBinder = new a(paramIBinder);
        }
      }
    }
    
    public boolean onTransact(int paramInt1, Parcel paramParcel1, Parcel paramParcel2, int paramInt2)
      throws RemoteException
    {
      Object localObject2 = null;
      boolean bool;
      switch (paramInt1)
      {
      default: 
      case 1598968902: 
        for (bool = super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);; bool = true)
        {
          return bool;
          paramParcel2.writeString("com.google.android.gms.wallet.internal.IWalletDynamiteCreator");
        }
      }
      paramParcel1.enforceInterface("com.google.android.gms.wallet.internal.IWalletDynamiteCreator");
      d locald = d.a.am(paramParcel1.readStrongBinder());
      c localc = c.a.al(paramParcel1.readStrongBinder());
      if (paramParcel1.readInt() != 0) {}
      for (Object localObject1 = (WalletFragmentOptions)WalletFragmentOptions.CREATOR.createFromParcel(paramParcel1);; localObject1 = null)
      {
        localObject1 = a(locald, localc, (WalletFragmentOptions)localObject1, os.a.bK(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        paramParcel1 = (Parcel)localObject2;
        if (localObject1 != null) {
          paramParcel1 = ((or)localObject1).asBinder();
        }
        paramParcel2.writeStrongBinder(paramParcel1);
        bool = true;
        break;
      }
    }
    
    private static class a
      implements ou
    {
      private IBinder lb;
      
      a(IBinder paramIBinder)
      {
        this.lb = paramIBinder;
      }
      
      /* Error */
      public or a(d paramd, c paramc, WalletFragmentOptions paramWalletFragmentOptions, os paramos)
        throws RemoteException
      {
        // Byte code:
        //   0: aconst_null
        //   1: astore 5
        //   3: invokestatic 28	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   6: astore 7
        //   8: invokestatic 28	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   11: astore 6
        //   13: aload 7
        //   15: ldc 30
        //   17: invokevirtual 34	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   20: aload_1
        //   21: ifnull +114 -> 135
        //   24: aload_1
        //   25: invokeinterface 40 1 0
        //   30: astore_1
        //   31: aload 7
        //   33: aload_1
        //   34: invokevirtual 43	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   37: aload_2
        //   38: ifnull +102 -> 140
        //   41: aload_2
        //   42: invokeinterface 46 1 0
        //   47: astore_1
        //   48: aload 7
        //   50: aload_1
        //   51: invokevirtual 43	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   54: aload_3
        //   55: ifnull +90 -> 145
        //   58: aload 7
        //   60: iconst_1
        //   61: invokevirtual 50	android/os/Parcel:writeInt	(I)V
        //   64: aload_3
        //   65: aload 7
        //   67: iconst_0
        //   68: invokevirtual 56	com/google/android/gms/wallet/fragment/WalletFragmentOptions:writeToParcel	(Landroid/os/Parcel;I)V
        //   71: aload 5
        //   73: astore_1
        //   74: aload 4
        //   76: ifnull +11 -> 87
        //   79: aload 4
        //   81: invokeinterface 59 1 0
        //   86: astore_1
        //   87: aload 7
        //   89: aload_1
        //   90: invokevirtual 43	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   93: aload_0
        //   94: getfield 18	com/google/android/gms/internal/ou$a$a:lb	Landroid/os/IBinder;
        //   97: iconst_1
        //   98: aload 7
        //   100: aload 6
        //   102: iconst_0
        //   103: invokeinterface 65 5 0
        //   108: pop
        //   109: aload 6
        //   111: invokevirtual 68	android/os/Parcel:readException	()V
        //   114: aload 6
        //   116: invokevirtual 71	android/os/Parcel:readStrongBinder	()Landroid/os/IBinder;
        //   119: invokestatic 77	com/google/android/gms/internal/or$a:bJ	(Landroid/os/IBinder;)Lcom/google/android/gms/internal/or;
        //   122: astore_1
        //   123: aload 6
        //   125: invokevirtual 80	android/os/Parcel:recycle	()V
        //   128: aload 7
        //   130: invokevirtual 80	android/os/Parcel:recycle	()V
        //   133: aload_1
        //   134: areturn
        //   135: aconst_null
        //   136: astore_1
        //   137: goto -106 -> 31
        //   140: aconst_null
        //   141: astore_1
        //   142: goto -94 -> 48
        //   145: aload 7
        //   147: iconst_0
        //   148: invokevirtual 50	android/os/Parcel:writeInt	(I)V
        //   151: goto -80 -> 71
        //   154: astore_1
        //   155: aload 6
        //   157: invokevirtual 80	android/os/Parcel:recycle	()V
        //   160: aload 7
        //   162: invokevirtual 80	android/os/Parcel:recycle	()V
        //   165: aload_1
        //   166: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	167	0	this	a
        //   0	167	1	paramd	d
        //   0	167	2	paramc	c
        //   0	167	3	paramWalletFragmentOptions	WalletFragmentOptions
        //   0	167	4	paramos	os
        //   1	71	5	localObject	Object
        //   11	145	6	localParcel1	Parcel
        //   6	155	7	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   13	20	154	finally
        //   24	31	154	finally
        //   31	37	154	finally
        //   41	48	154	finally
        //   48	54	154	finally
        //   58	71	154	finally
        //   79	87	154	finally
        //   87	123	154	finally
        //   145	151	154	finally
      }
      
      public IBinder asBinder()
      {
        return this.lb;
      }
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\internal\ou.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */