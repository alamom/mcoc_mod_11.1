package com.google.android.gms.internal;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.StatusCreator;
import com.google.android.gms.wallet.FullWallet;
import com.google.android.gms.wallet.MaskedWallet;

public abstract interface ow
  extends IInterface
{
  public abstract void a(int paramInt, FullWallet paramFullWallet, Bundle paramBundle)
    throws RemoteException;
  
  public abstract void a(int paramInt, MaskedWallet paramMaskedWallet, Bundle paramBundle)
    throws RemoteException;
  
  public abstract void a(int paramInt, boolean paramBoolean, Bundle paramBundle)
    throws RemoteException;
  
  public abstract void a(Status paramStatus, op paramop, Bundle paramBundle)
    throws RemoteException;
  
  public abstract void i(int paramInt, Bundle paramBundle)
    throws RemoteException;
  
  public static abstract class a
    extends Binder
    implements ow
  {
    public a()
    {
      attachInterface(this, "com.google.android.gms.wallet.internal.IWalletServiceCallbacks");
    }
    
    public static ow bO(IBinder paramIBinder)
    {
      if (paramIBinder == null) {
        paramIBinder = null;
      }
      for (;;)
      {
        return paramIBinder;
        IInterface localIInterface = paramIBinder.queryLocalInterface("com.google.android.gms.wallet.internal.IWalletServiceCallbacks");
        if ((localIInterface != null) && ((localIInterface instanceof ow))) {
          paramIBinder = (ow)localIInterface;
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
      boolean bool2 = true;
      boolean bool1;
      Object localObject;
      switch (paramInt1)
      {
      default: 
      case 1598968902: 
        for (bool1 = super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);; bool1 = bool2)
        {
          return bool1;
          paramParcel2.writeString("com.google.android.gms.wallet.internal.IWalletServiceCallbacks");
        }
      case 1: 
        paramParcel1.enforceInterface("com.google.android.gms.wallet.internal.IWalletServiceCallbacks");
        paramInt1 = paramParcel1.readInt();
        if (paramParcel1.readInt() != 0)
        {
          localObject = (MaskedWallet)MaskedWallet.CREATOR.createFromParcel(paramParcel1);
          if (paramParcel1.readInt() == 0) {
            break label168;
          }
        }
        for (paramParcel1 = (Bundle)Bundle.CREATOR.createFromParcel(paramParcel1);; paramParcel1 = null)
        {
          a(paramInt1, (MaskedWallet)localObject, paramParcel1);
          paramParcel2.writeNoException();
          bool1 = bool2;
          break;
          localObject = null;
          break label123;
        }
      case 2: 
        paramParcel1.enforceInterface("com.google.android.gms.wallet.internal.IWalletServiceCallbacks");
        paramInt1 = paramParcel1.readInt();
        if (paramParcel1.readInt() != 0)
        {
          localObject = (FullWallet)FullWallet.CREATOR.createFromParcel(paramParcel1);
          if (paramParcel1.readInt() == 0) {
            break label250;
          }
        }
        for (paramParcel1 = (Bundle)Bundle.CREATOR.createFromParcel(paramParcel1);; paramParcel1 = null)
        {
          a(paramInt1, (FullWallet)localObject, paramParcel1);
          paramParcel2.writeNoException();
          bool1 = bool2;
          break;
          localObject = null;
          break label205;
        }
      case 3: 
        paramParcel1.enforceInterface("com.google.android.gms.wallet.internal.IWalletServiceCallbacks");
        paramInt1 = paramParcel1.readInt();
        if (paramParcel1.readInt() != 0)
        {
          bool1 = true;
          if (paramParcel1.readInt() == 0) {
            break label321;
          }
        }
        for (paramParcel1 = (Bundle)Bundle.CREATOR.createFromParcel(paramParcel1);; paramParcel1 = null)
        {
          a(paramInt1, bool1, paramParcel1);
          paramParcel2.writeNoException();
          bool1 = bool2;
          break;
          bool1 = false;
          break label276;
        }
      case 4: 
        label123:
        label168:
        label205:
        label250:
        label276:
        label321:
        paramParcel1.enforceInterface("com.google.android.gms.wallet.internal.IWalletServiceCallbacks");
        paramInt1 = paramParcel1.readInt();
        if (paramParcel1.readInt() != 0) {}
        for (paramParcel1 = (Bundle)Bundle.CREATOR.createFromParcel(paramParcel1);; paramParcel1 = null)
        {
          i(paramInt1, paramParcel1);
          paramParcel2.writeNoException();
          bool1 = bool2;
          break;
        }
      }
      paramParcel1.enforceInterface("com.google.android.gms.wallet.internal.IWalletServiceCallbacks");
      label401:
      op localop;
      if (paramParcel1.readInt() != 0)
      {
        localObject = Status.CREATOR.createFromParcel(paramParcel1);
        if (paramParcel1.readInt() == 0) {
          break label468;
        }
        localop = (op)op.CREATOR.createFromParcel(paramParcel1);
        label422:
        if (paramParcel1.readInt() == 0) {
          break label474;
        }
      }
      label468:
      label474:
      for (paramParcel1 = (Bundle)Bundle.CREATOR.createFromParcel(paramParcel1);; paramParcel1 = null)
      {
        a((Status)localObject, localop, paramParcel1);
        paramParcel2.writeNoException();
        bool1 = bool2;
        break;
        localObject = null;
        break label401;
        localop = null;
        break label422;
      }
    }
    
    private static class a
      implements ow
    {
      private IBinder lb;
      
      a(IBinder paramIBinder)
      {
        this.lb = paramIBinder;
      }
      
      public void a(int paramInt, FullWallet paramFullWallet, Bundle paramBundle)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.wallet.internal.IWalletServiceCallbacks");
            localParcel1.writeInt(paramInt);
            if (paramFullWallet != null)
            {
              localParcel1.writeInt(1);
              paramFullWallet.writeToParcel(localParcel1, 0);
              if (paramBundle != null)
              {
                localParcel1.writeInt(1);
                paramBundle.writeToParcel(localParcel1, 0);
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
      
      public void a(int paramInt, MaskedWallet paramMaskedWallet, Bundle paramBundle)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.wallet.internal.IWalletServiceCallbacks");
            localParcel1.writeInt(paramInt);
            if (paramMaskedWallet != null)
            {
              localParcel1.writeInt(1);
              paramMaskedWallet.writeToParcel(localParcel1, 0);
              if (paramBundle != null)
              {
                localParcel1.writeInt(1);
                paramBundle.writeToParcel(localParcel1, 0);
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
      
      /* Error */
      public void a(int paramInt, boolean paramBoolean, Bundle paramBundle)
        throws RemoteException
      {
        // Byte code:
        //   0: iconst_1
        //   1: istore 4
        //   3: invokestatic 28	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   6: astore 5
        //   8: invokestatic 28	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   11: astore 6
        //   13: aload 5
        //   15: ldc 30
        //   17: invokevirtual 34	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   20: aload 5
        //   22: iload_1
        //   23: invokevirtual 38	android/os/Parcel:writeInt	(I)V
        //   26: iload_2
        //   27: ifeq +61 -> 88
        //   30: iload 4
        //   32: istore_1
        //   33: aload 5
        //   35: iload_1
        //   36: invokevirtual 38	android/os/Parcel:writeInt	(I)V
        //   39: aload_3
        //   40: ifnull +53 -> 93
        //   43: aload 5
        //   45: iconst_1
        //   46: invokevirtual 38	android/os/Parcel:writeInt	(I)V
        //   49: aload_3
        //   50: aload 5
        //   52: iconst_0
        //   53: invokevirtual 47	android/os/Bundle:writeToParcel	(Landroid/os/Parcel;I)V
        //   56: aload_0
        //   57: getfield 18	com/google/android/gms/internal/ow$a$a:lb	Landroid/os/IBinder;
        //   60: iconst_3
        //   61: aload 5
        //   63: aload 6
        //   65: iconst_0
        //   66: invokeinterface 53 5 0
        //   71: pop
        //   72: aload 6
        //   74: invokevirtual 56	android/os/Parcel:readException	()V
        //   77: aload 6
        //   79: invokevirtual 59	android/os/Parcel:recycle	()V
        //   82: aload 5
        //   84: invokevirtual 59	android/os/Parcel:recycle	()V
        //   87: return
        //   88: iconst_0
        //   89: istore_1
        //   90: goto -57 -> 33
        //   93: aload 5
        //   95: iconst_0
        //   96: invokevirtual 38	android/os/Parcel:writeInt	(I)V
        //   99: goto -43 -> 56
        //   102: astore_3
        //   103: aload 6
        //   105: invokevirtual 59	android/os/Parcel:recycle	()V
        //   108: aload 5
        //   110: invokevirtual 59	android/os/Parcel:recycle	()V
        //   113: aload_3
        //   114: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	115	0	this	a
        //   0	115	1	paramInt	int
        //   0	115	2	paramBoolean	boolean
        //   0	115	3	paramBundle	Bundle
        //   1	30	4	i	int
        //   6	103	5	localParcel1	Parcel
        //   11	93	6	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   13	26	102	finally
        //   33	39	102	finally
        //   43	56	102	finally
        //   56	77	102	finally
        //   93	99	102	finally
      }
      
      public void a(Status paramStatus, op paramop, Bundle paramBundle)
        throws RemoteException
      {
        Parcel localParcel2 = Parcel.obtain();
        Parcel localParcel1 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel2.writeInterfaceToken("com.google.android.gms.wallet.internal.IWalletServiceCallbacks");
            if (paramStatus != null)
            {
              localParcel2.writeInt(1);
              paramStatus.writeToParcel(localParcel2, 0);
              if (paramop != null)
              {
                localParcel2.writeInt(1);
                paramop.writeToParcel(localParcel2, 0);
                if (paramBundle == null) {
                  break label131;
                }
                localParcel2.writeInt(1);
                paramBundle.writeToParcel(localParcel2, 0);
                this.lb.transact(5, localParcel2, localParcel1, 0);
                localParcel1.readException();
              }
            }
            else
            {
              localParcel2.writeInt(0);
              continue;
            }
            localParcel2.writeInt(0);
          }
          finally
          {
            localParcel1.recycle();
            localParcel2.recycle();
          }
          continue;
          label131:
          localParcel2.writeInt(0);
        }
      }
      
      public IBinder asBinder()
      {
        return this.lb;
      }
      
      /* Error */
      public void i(int paramInt, Bundle paramBundle)
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
        //   23: ifnull +45 -> 68
        //   26: aload 4
        //   28: iconst_1
        //   29: invokevirtual 38	android/os/Parcel:writeInt	(I)V
        //   32: aload_2
        //   33: aload 4
        //   35: iconst_0
        //   36: invokevirtual 47	android/os/Bundle:writeToParcel	(Landroid/os/Parcel;I)V
        //   39: aload_0
        //   40: getfield 18	com/google/android/gms/internal/ow$a$a:lb	Landroid/os/IBinder;
        //   43: iconst_4
        //   44: aload 4
        //   46: aload_3
        //   47: iconst_0
        //   48: invokeinterface 53 5 0
        //   53: pop
        //   54: aload_3
        //   55: invokevirtual 56	android/os/Parcel:readException	()V
        //   58: aload_3
        //   59: invokevirtual 59	android/os/Parcel:recycle	()V
        //   62: aload 4
        //   64: invokevirtual 59	android/os/Parcel:recycle	()V
        //   67: return
        //   68: aload 4
        //   70: iconst_0
        //   71: invokevirtual 38	android/os/Parcel:writeInt	(I)V
        //   74: goto -35 -> 39
        //   77: astore_2
        //   78: aload_3
        //   79: invokevirtual 59	android/os/Parcel:recycle	()V
        //   82: aload 4
        //   84: invokevirtual 59	android/os/Parcel:recycle	()V
        //   87: aload_2
        //   88: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	89	0	this	a
        //   0	89	1	paramInt	int
        //   0	89	2	paramBundle	Bundle
        //   8	71	3	localParcel1	Parcel
        //   3	80	4	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   9	22	77	finally
        //   26	39	77	finally
        //   39	58	77	finally
        //   68	74	77	finally
      }
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\internal\ow.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */