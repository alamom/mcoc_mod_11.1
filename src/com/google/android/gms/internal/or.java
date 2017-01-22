package com.google.android.gms.internal;

import android.content.Intent;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.os.RemoteException;
import com.google.android.gms.dynamic.d;
import com.google.android.gms.dynamic.d.a;
import com.google.android.gms.wallet.MaskedWallet;
import com.google.android.gms.wallet.MaskedWalletRequest;
import com.google.android.gms.wallet.fragment.WalletFragmentInitParams;
import com.google.android.gms.wallet.fragment.WalletFragmentOptions;

public abstract interface or
  extends IInterface
{
  public abstract void a(d paramd, WalletFragmentOptions paramWalletFragmentOptions, Bundle paramBundle)
    throws RemoteException;
  
  public abstract int getState()
    throws RemoteException;
  
  public abstract void initialize(WalletFragmentInitParams paramWalletFragmentInitParams)
    throws RemoteException;
  
  public abstract void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
    throws RemoteException;
  
  public abstract void onCreate(Bundle paramBundle)
    throws RemoteException;
  
  public abstract d onCreateView(d paramd1, d paramd2, Bundle paramBundle)
    throws RemoteException;
  
  public abstract void onPause()
    throws RemoteException;
  
  public abstract void onResume()
    throws RemoteException;
  
  public abstract void onSaveInstanceState(Bundle paramBundle)
    throws RemoteException;
  
  public abstract void onStart()
    throws RemoteException;
  
  public abstract void onStop()
    throws RemoteException;
  
  public abstract void setEnabled(boolean paramBoolean)
    throws RemoteException;
  
  public abstract void updateMaskedWallet(MaskedWallet paramMaskedWallet)
    throws RemoteException;
  
  public abstract void updateMaskedWalletRequest(MaskedWalletRequest paramMaskedWalletRequest)
    throws RemoteException;
  
  public static abstract class a
    extends Binder
    implements or
  {
    public static or bJ(IBinder paramIBinder)
    {
      if (paramIBinder == null) {
        paramIBinder = null;
      }
      for (;;)
      {
        return paramIBinder;
        IInterface localIInterface = paramIBinder.queryLocalInterface("com.google.android.gms.wallet.fragment.internal.IWalletFragmentDelegate");
        if ((localIInterface != null) && ((localIInterface instanceof or))) {
          paramIBinder = (or)localIInterface;
        } else {
          paramIBinder = new a(paramIBinder);
        }
      }
    }
    
    public boolean onTransact(int paramInt1, Parcel paramParcel1, Parcel paramParcel2, int paramInt2)
      throws RemoteException
    {
      WalletFragmentOptions localWalletFragmentOptions = null;
      boolean bool2 = true;
      boolean bool1;
      switch (paramInt1)
      {
      default: 
        bool1 = super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
      case 1598968902: 
      case 1: 
      case 2: 
      case 3: 
      case 4: 
      case 5: 
      case 6: 
      case 7: 
      case 8: 
      case 9: 
      case 10: 
      case 11: 
      case 12: 
      case 13: 
        for (;;)
        {
          return bool1;
          paramParcel2.writeString("com.google.android.gms.wallet.fragment.internal.IWalletFragmentDelegate");
          bool1 = bool2;
          continue;
          paramParcel1.enforceInterface("com.google.android.gms.wallet.fragment.internal.IWalletFragmentDelegate");
          d locald1 = d.a.am(paramParcel1.readStrongBinder());
          if (paramParcel1.readInt() != 0)
          {
            localWalletFragmentOptions = (WalletFragmentOptions)WalletFragmentOptions.CREATOR.createFromParcel(paramParcel1);
            label199:
            if (paramParcel1.readInt() == 0) {
              break label245;
            }
          }
          label245:
          for (paramParcel1 = (Bundle)Bundle.CREATOR.createFromParcel(paramParcel1);; paramParcel1 = null)
          {
            a(locald1, localWalletFragmentOptions, paramParcel1);
            paramParcel2.writeNoException();
            bool1 = bool2;
            break;
            localWalletFragmentOptions = null;
            break label199;
          }
          paramParcel1.enforceInterface("com.google.android.gms.wallet.fragment.internal.IWalletFragmentDelegate");
          if (paramParcel1.readInt() != 0) {}
          for (paramParcel1 = (Bundle)Bundle.CREATOR.createFromParcel(paramParcel1);; paramParcel1 = null)
          {
            onCreate(paramParcel1);
            paramParcel2.writeNoException();
            bool1 = bool2;
            break;
          }
          paramParcel1.enforceInterface("com.google.android.gms.wallet.fragment.internal.IWalletFragmentDelegate");
          d locald2 = d.a.am(paramParcel1.readStrongBinder());
          locald1 = d.a.am(paramParcel1.readStrongBinder());
          if (paramParcel1.readInt() != 0) {}
          for (paramParcel1 = (Bundle)Bundle.CREATOR.createFromParcel(paramParcel1);; paramParcel1 = null)
          {
            locald1 = onCreateView(locald2, locald1, paramParcel1);
            paramParcel2.writeNoException();
            paramParcel1 = localWalletFragmentOptions;
            if (locald1 != null) {
              paramParcel1 = locald1.asBinder();
            }
            paramParcel2.writeStrongBinder(paramParcel1);
            bool1 = bool2;
            break;
          }
          paramParcel1.enforceInterface("com.google.android.gms.wallet.fragment.internal.IWalletFragmentDelegate");
          onStart();
          paramParcel2.writeNoException();
          bool1 = bool2;
          continue;
          paramParcel1.enforceInterface("com.google.android.gms.wallet.fragment.internal.IWalletFragmentDelegate");
          onResume();
          paramParcel2.writeNoException();
          bool1 = bool2;
          continue;
          paramParcel1.enforceInterface("com.google.android.gms.wallet.fragment.internal.IWalletFragmentDelegate");
          onPause();
          paramParcel2.writeNoException();
          bool1 = bool2;
          continue;
          paramParcel1.enforceInterface("com.google.android.gms.wallet.fragment.internal.IWalletFragmentDelegate");
          onStop();
          paramParcel2.writeNoException();
          bool1 = bool2;
          continue;
          paramParcel1.enforceInterface("com.google.android.gms.wallet.fragment.internal.IWalletFragmentDelegate");
          if (paramParcel1.readInt() != 0) {}
          for (paramParcel1 = (Bundle)Bundle.CREATOR.createFromParcel(paramParcel1);; paramParcel1 = null)
          {
            onSaveInstanceState(paramParcel1);
            paramParcel2.writeNoException();
            if (paramParcel1 == null) {
              break label535;
            }
            paramParcel2.writeInt(1);
            paramParcel1.writeToParcel(paramParcel2, 1);
            bool1 = bool2;
            break;
          }
          label535:
          paramParcel2.writeInt(0);
          bool1 = bool2;
          continue;
          paramParcel1.enforceInterface("com.google.android.gms.wallet.fragment.internal.IWalletFragmentDelegate");
          paramInt2 = paramParcel1.readInt();
          paramInt1 = paramParcel1.readInt();
          if (paramParcel1.readInt() != 0) {}
          for (paramParcel1 = (Intent)Intent.CREATOR.createFromParcel(paramParcel1);; paramParcel1 = null)
          {
            onActivityResult(paramInt2, paramInt1, paramParcel1);
            paramParcel2.writeNoException();
            bool1 = bool2;
            break;
          }
          paramParcel1.enforceInterface("com.google.android.gms.wallet.fragment.internal.IWalletFragmentDelegate");
          if (paramParcel1.readInt() != 0) {}
          for (paramParcel1 = (WalletFragmentInitParams)WalletFragmentInitParams.CREATOR.createFromParcel(paramParcel1);; paramParcel1 = null)
          {
            initialize(paramParcel1);
            paramParcel2.writeNoException();
            bool1 = bool2;
            break;
          }
          paramParcel1.enforceInterface("com.google.android.gms.wallet.fragment.internal.IWalletFragmentDelegate");
          if (paramParcel1.readInt() != 0) {}
          for (paramParcel1 = (MaskedWalletRequest)MaskedWalletRequest.CREATOR.createFromParcel(paramParcel1);; paramParcel1 = null)
          {
            updateMaskedWalletRequest(paramParcel1);
            paramParcel2.writeNoException();
            bool1 = bool2;
            break;
          }
          paramParcel1.enforceInterface("com.google.android.gms.wallet.fragment.internal.IWalletFragmentDelegate");
          if (paramParcel1.readInt() != 0) {}
          for (bool1 = true;; bool1 = false)
          {
            setEnabled(bool1);
            paramParcel2.writeNoException();
            bool1 = bool2;
            break;
          }
          paramParcel1.enforceInterface("com.google.android.gms.wallet.fragment.internal.IWalletFragmentDelegate");
          paramInt1 = getState();
          paramParcel2.writeNoException();
          paramParcel2.writeInt(paramInt1);
          bool1 = bool2;
        }
      }
      paramParcel1.enforceInterface("com.google.android.gms.wallet.fragment.internal.IWalletFragmentDelegate");
      if (paramParcel1.readInt() != 0) {}
      for (paramParcel1 = (MaskedWallet)MaskedWallet.CREATOR.createFromParcel(paramParcel1);; paramParcel1 = null)
      {
        updateMaskedWallet(paramParcel1);
        paramParcel2.writeNoException();
        bool1 = bool2;
        break;
      }
    }
    
    private static class a
      implements or
    {
      private IBinder lb;
      
      a(IBinder paramIBinder)
      {
        this.lb = paramIBinder;
      }
      
      public void a(d paramd, WalletFragmentOptions paramWalletFragmentOptions, Bundle paramBundle)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        label127:
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.wallet.fragment.internal.IWalletFragmentDelegate");
            if (paramd != null)
            {
              paramd = paramd.asBinder();
              localParcel1.writeStrongBinder(paramd);
              if (paramWalletFragmentOptions != null)
              {
                localParcel1.writeInt(1);
                paramWalletFragmentOptions.writeToParcel(localParcel1, 0);
                if (paramBundle == null) {
                  break label127;
                }
                localParcel1.writeInt(1);
                paramBundle.writeToParcel(localParcel1, 0);
                this.lb.transact(1, localParcel1, localParcel2, 0);
                localParcel2.readException();
              }
            }
            else
            {
              paramd = null;
              continue;
            }
            localParcel1.writeInt(0);
            continue;
            localParcel1.writeInt(0);
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
        }
      }
      
      public IBinder asBinder()
      {
        return this.lb;
      }
      
      public int getState()
        throws RemoteException
      {
        Parcel localParcel2 = Parcel.obtain();
        Parcel localParcel1 = Parcel.obtain();
        try
        {
          localParcel2.writeInterfaceToken("com.google.android.gms.wallet.fragment.internal.IWalletFragmentDelegate");
          this.lb.transact(13, localParcel2, localParcel1, 0);
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
      
      /* Error */
      public void initialize(WalletFragmentInitParams paramWalletFragmentInitParams)
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
        //   18: aload_2
        //   19: iconst_1
        //   20: invokevirtual 47	android/os/Parcel:writeInt	(I)V
        //   23: aload_1
        //   24: aload_2
        //   25: iconst_0
        //   26: invokevirtual 79	com/google/android/gms/wallet/fragment/WalletFragmentInitParams:writeToParcel	(Landroid/os/Parcel;I)V
        //   29: aload_0
        //   30: getfield 18	com/google/android/gms/internal/or$a$a:lb	Landroid/os/IBinder;
        //   33: bipush 10
        //   35: aload_2
        //   36: aload_3
        //   37: iconst_0
        //   38: invokeinterface 62 5 0
        //   43: pop
        //   44: aload_3
        //   45: invokevirtual 65	android/os/Parcel:readException	()V
        //   48: aload_3
        //   49: invokevirtual 68	android/os/Parcel:recycle	()V
        //   52: aload_2
        //   53: invokevirtual 68	android/os/Parcel:recycle	()V
        //   56: return
        //   57: aload_2
        //   58: iconst_0
        //   59: invokevirtual 47	android/os/Parcel:writeInt	(I)V
        //   62: goto -33 -> 29
        //   65: astore_1
        //   66: aload_3
        //   67: invokevirtual 68	android/os/Parcel:recycle	()V
        //   70: aload_2
        //   71: invokevirtual 68	android/os/Parcel:recycle	()V
        //   74: aload_1
        //   75: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	76	0	this	a
        //   0	76	1	paramWalletFragmentInitParams	WalletFragmentInitParams
        //   3	68	2	localParcel1	Parcel
        //   7	60	3	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   8	14	65	finally
        //   18	29	65	finally
        //   29	48	65	finally
        //   57	62	65	finally
      }
      
      /* Error */
      public void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 28	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore 5
        //   5: invokestatic 28	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   8: astore 4
        //   10: aload 5
        //   12: ldc 30
        //   14: invokevirtual 34	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   17: aload 5
        //   19: iload_1
        //   20: invokevirtual 47	android/os/Parcel:writeInt	(I)V
        //   23: aload 5
        //   25: iload_2
        //   26: invokevirtual 47	android/os/Parcel:writeInt	(I)V
        //   29: aload_3
        //   30: ifnull +49 -> 79
        //   33: aload 5
        //   35: iconst_1
        //   36: invokevirtual 47	android/os/Parcel:writeInt	(I)V
        //   39: aload_3
        //   40: aload 5
        //   42: iconst_0
        //   43: invokevirtual 84	android/content/Intent:writeToParcel	(Landroid/os/Parcel;I)V
        //   46: aload_0
        //   47: getfield 18	com/google/android/gms/internal/or$a$a:lb	Landroid/os/IBinder;
        //   50: bipush 9
        //   52: aload 5
        //   54: aload 4
        //   56: iconst_0
        //   57: invokeinterface 62 5 0
        //   62: pop
        //   63: aload 4
        //   65: invokevirtual 65	android/os/Parcel:readException	()V
        //   68: aload 4
        //   70: invokevirtual 68	android/os/Parcel:recycle	()V
        //   73: aload 5
        //   75: invokevirtual 68	android/os/Parcel:recycle	()V
        //   78: return
        //   79: aload 5
        //   81: iconst_0
        //   82: invokevirtual 47	android/os/Parcel:writeInt	(I)V
        //   85: goto -39 -> 46
        //   88: astore_3
        //   89: aload 4
        //   91: invokevirtual 68	android/os/Parcel:recycle	()V
        //   94: aload 5
        //   96: invokevirtual 68	android/os/Parcel:recycle	()V
        //   99: aload_3
        //   100: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	101	0	this	a
        //   0	101	1	paramInt1	int
        //   0	101	2	paramInt2	int
        //   0	101	3	paramIntent	Intent
        //   8	82	4	localParcel1	Parcel
        //   3	92	5	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   10	29	88	finally
        //   33	46	88	finally
        //   46	68	88	finally
        //   79	85	88	finally
      }
      
      /* Error */
      public void onCreate(Bundle paramBundle)
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
        //   20: invokevirtual 47	android/os/Parcel:writeInt	(I)V
        //   23: aload_1
        //   24: aload_3
        //   25: iconst_0
        //   26: invokevirtual 56	android/os/Bundle:writeToParcel	(Landroid/os/Parcel;I)V
        //   29: aload_0
        //   30: getfield 18	com/google/android/gms/internal/or$a$a:lb	Landroid/os/IBinder;
        //   33: iconst_2
        //   34: aload_3
        //   35: aload_2
        //   36: iconst_0
        //   37: invokeinterface 62 5 0
        //   42: pop
        //   43: aload_2
        //   44: invokevirtual 65	android/os/Parcel:readException	()V
        //   47: aload_2
        //   48: invokevirtual 68	android/os/Parcel:recycle	()V
        //   51: aload_3
        //   52: invokevirtual 68	android/os/Parcel:recycle	()V
        //   55: return
        //   56: aload_3
        //   57: iconst_0
        //   58: invokevirtual 47	android/os/Parcel:writeInt	(I)V
        //   61: goto -32 -> 29
        //   64: astore_1
        //   65: aload_2
        //   66: invokevirtual 68	android/os/Parcel:recycle	()V
        //   69: aload_3
        //   70: invokevirtual 68	android/os/Parcel:recycle	()V
        //   73: aload_1
        //   74: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	75	0	this	a
        //   0	75	1	paramBundle	Bundle
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
      public d onCreateView(d paramd1, d paramd2, Bundle paramBundle)
        throws RemoteException
      {
        // Byte code:
        //   0: aconst_null
        //   1: astore 4
        //   3: invokestatic 28	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   6: astore 6
        //   8: invokestatic 28	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   11: astore 5
        //   13: aload 6
        //   15: ldc 30
        //   17: invokevirtual 34	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   20: aload_1
        //   21: ifnull +95 -> 116
        //   24: aload_1
        //   25: invokeinterface 40 1 0
        //   30: astore_1
        //   31: aload 6
        //   33: aload_1
        //   34: invokevirtual 43	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   37: aload 4
        //   39: astore_1
        //   40: aload_2
        //   41: ifnull +10 -> 51
        //   44: aload_2
        //   45: invokeinterface 40 1 0
        //   50: astore_1
        //   51: aload 6
        //   53: aload_1
        //   54: invokevirtual 43	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   57: aload_3
        //   58: ifnull +63 -> 121
        //   61: aload 6
        //   63: iconst_1
        //   64: invokevirtual 47	android/os/Parcel:writeInt	(I)V
        //   67: aload_3
        //   68: aload 6
        //   70: iconst_0
        //   71: invokevirtual 56	android/os/Bundle:writeToParcel	(Landroid/os/Parcel;I)V
        //   74: aload_0
        //   75: getfield 18	com/google/android/gms/internal/or$a$a:lb	Landroid/os/IBinder;
        //   78: iconst_3
        //   79: aload 6
        //   81: aload 5
        //   83: iconst_0
        //   84: invokeinterface 62 5 0
        //   89: pop
        //   90: aload 5
        //   92: invokevirtual 65	android/os/Parcel:readException	()V
        //   95: aload 5
        //   97: invokevirtual 91	android/os/Parcel:readStrongBinder	()Landroid/os/IBinder;
        //   100: invokestatic 97	com/google/android/gms/dynamic/d$a:am	(Landroid/os/IBinder;)Lcom/google/android/gms/dynamic/d;
        //   103: astore_1
        //   104: aload 5
        //   106: invokevirtual 68	android/os/Parcel:recycle	()V
        //   109: aload 6
        //   111: invokevirtual 68	android/os/Parcel:recycle	()V
        //   114: aload_1
        //   115: areturn
        //   116: aconst_null
        //   117: astore_1
        //   118: goto -87 -> 31
        //   121: aload 6
        //   123: iconst_0
        //   124: invokevirtual 47	android/os/Parcel:writeInt	(I)V
        //   127: goto -53 -> 74
        //   130: astore_1
        //   131: aload 5
        //   133: invokevirtual 68	android/os/Parcel:recycle	()V
        //   136: aload 6
        //   138: invokevirtual 68	android/os/Parcel:recycle	()V
        //   141: aload_1
        //   142: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	143	0	this	a
        //   0	143	1	paramd1	d
        //   0	143	2	paramd2	d
        //   0	143	3	paramBundle	Bundle
        //   1	37	4	localObject	Object
        //   11	121	5	localParcel1	Parcel
        //   6	131	6	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   13	20	130	finally
        //   24	31	130	finally
        //   31	37	130	finally
        //   44	51	130	finally
        //   51	57	130	finally
        //   61	74	130	finally
        //   74	104	130	finally
        //   121	127	130	finally
      }
      
      public void onPause()
        throws RemoteException
      {
        Parcel localParcel2 = Parcel.obtain();
        Parcel localParcel1 = Parcel.obtain();
        try
        {
          localParcel2.writeInterfaceToken("com.google.android.gms.wallet.fragment.internal.IWalletFragmentDelegate");
          this.lb.transact(6, localParcel2, localParcel1, 0);
          localParcel1.readException();
          return;
        }
        finally
        {
          localParcel1.recycle();
          localParcel2.recycle();
        }
      }
      
      public void onResume()
        throws RemoteException
      {
        Parcel localParcel2 = Parcel.obtain();
        Parcel localParcel1 = Parcel.obtain();
        try
        {
          localParcel2.writeInterfaceToken("com.google.android.gms.wallet.fragment.internal.IWalletFragmentDelegate");
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
      
      /* Error */
      public void onSaveInstanceState(Bundle paramBundle)
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
        //   15: ifnull +54 -> 69
        //   18: aload_3
        //   19: iconst_1
        //   20: invokevirtual 47	android/os/Parcel:writeInt	(I)V
        //   23: aload_1
        //   24: aload_3
        //   25: iconst_0
        //   26: invokevirtual 56	android/os/Bundle:writeToParcel	(Landroid/os/Parcel;I)V
        //   29: aload_0
        //   30: getfield 18	com/google/android/gms/internal/or$a$a:lb	Landroid/os/IBinder;
        //   33: bipush 8
        //   35: aload_3
        //   36: aload_2
        //   37: iconst_0
        //   38: invokeinterface 62 5 0
        //   43: pop
        //   44: aload_2
        //   45: invokevirtual 65	android/os/Parcel:readException	()V
        //   48: aload_2
        //   49: invokevirtual 74	android/os/Parcel:readInt	()I
        //   52: ifeq +8 -> 60
        //   55: aload_1
        //   56: aload_2
        //   57: invokevirtual 104	android/os/Bundle:readFromParcel	(Landroid/os/Parcel;)V
        //   60: aload_2
        //   61: invokevirtual 68	android/os/Parcel:recycle	()V
        //   64: aload_3
        //   65: invokevirtual 68	android/os/Parcel:recycle	()V
        //   68: return
        //   69: aload_3
        //   70: iconst_0
        //   71: invokevirtual 47	android/os/Parcel:writeInt	(I)V
        //   74: goto -45 -> 29
        //   77: astore_1
        //   78: aload_2
        //   79: invokevirtual 68	android/os/Parcel:recycle	()V
        //   82: aload_3
        //   83: invokevirtual 68	android/os/Parcel:recycle	()V
        //   86: aload_1
        //   87: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	88	0	this	a
        //   0	88	1	paramBundle	Bundle
        //   7	72	2	localParcel1	Parcel
        //   3	80	3	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   8	14	77	finally
        //   18	29	77	finally
        //   29	60	77	finally
        //   69	74	77	finally
      }
      
      public void onStart()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.wallet.fragment.internal.IWalletFragmentDelegate");
          this.lb.transact(4, localParcel1, localParcel2, 0);
          localParcel2.readException();
          return;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
      
      public void onStop()
        throws RemoteException
      {
        Parcel localParcel2 = Parcel.obtain();
        Parcel localParcel1 = Parcel.obtain();
        try
        {
          localParcel2.writeInterfaceToken("com.google.android.gms.wallet.fragment.internal.IWalletFragmentDelegate");
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
      
      public void setEnabled(boolean paramBoolean)
        throws RemoteException
      {
        int i = 0;
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.wallet.fragment.internal.IWalletFragmentDelegate");
          if (paramBoolean) {
            i = 1;
          }
          localParcel1.writeInt(i);
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
      
      /* Error */
      public void updateMaskedWallet(MaskedWallet paramMaskedWallet)
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
        //   20: invokevirtual 47	android/os/Parcel:writeInt	(I)V
        //   23: aload_1
        //   24: aload_3
        //   25: iconst_0
        //   26: invokevirtual 113	com/google/android/gms/wallet/MaskedWallet:writeToParcel	(Landroid/os/Parcel;I)V
        //   29: aload_0
        //   30: getfield 18	com/google/android/gms/internal/or$a$a:lb	Landroid/os/IBinder;
        //   33: bipush 14
        //   35: aload_3
        //   36: aload_2
        //   37: iconst_0
        //   38: invokeinterface 62 5 0
        //   43: pop
        //   44: aload_2
        //   45: invokevirtual 65	android/os/Parcel:readException	()V
        //   48: aload_2
        //   49: invokevirtual 68	android/os/Parcel:recycle	()V
        //   52: aload_3
        //   53: invokevirtual 68	android/os/Parcel:recycle	()V
        //   56: return
        //   57: aload_3
        //   58: iconst_0
        //   59: invokevirtual 47	android/os/Parcel:writeInt	(I)V
        //   62: goto -33 -> 29
        //   65: astore_1
        //   66: aload_2
        //   67: invokevirtual 68	android/os/Parcel:recycle	()V
        //   70: aload_3
        //   71: invokevirtual 68	android/os/Parcel:recycle	()V
        //   74: aload_1
        //   75: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	76	0	this	a
        //   0	76	1	paramMaskedWallet	MaskedWallet
        //   7	60	2	localParcel1	Parcel
        //   3	68	3	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   8	14	65	finally
        //   18	29	65	finally
        //   29	48	65	finally
        //   57	62	65	finally
      }
      
      /* Error */
      public void updateMaskedWalletRequest(MaskedWalletRequest paramMaskedWalletRequest)
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
        //   20: invokevirtual 47	android/os/Parcel:writeInt	(I)V
        //   23: aload_1
        //   24: aload_3
        //   25: iconst_0
        //   26: invokevirtual 118	com/google/android/gms/wallet/MaskedWalletRequest:writeToParcel	(Landroid/os/Parcel;I)V
        //   29: aload_0
        //   30: getfield 18	com/google/android/gms/internal/or$a$a:lb	Landroid/os/IBinder;
        //   33: bipush 11
        //   35: aload_3
        //   36: aload_2
        //   37: iconst_0
        //   38: invokeinterface 62 5 0
        //   43: pop
        //   44: aload_2
        //   45: invokevirtual 65	android/os/Parcel:readException	()V
        //   48: aload_2
        //   49: invokevirtual 68	android/os/Parcel:recycle	()V
        //   52: aload_3
        //   53: invokevirtual 68	android/os/Parcel:recycle	()V
        //   56: return
        //   57: aload_3
        //   58: iconst_0
        //   59: invokevirtual 47	android/os/Parcel:writeInt	(I)V
        //   62: goto -33 -> 29
        //   65: astore_1
        //   66: aload_2
        //   67: invokevirtual 68	android/os/Parcel:recycle	()V
        //   70: aload_3
        //   71: invokevirtual 68	android/os/Parcel:recycle	()V
        //   74: aload_1
        //   75: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	76	0	this	a
        //   0	76	1	paramMaskedWalletRequest	MaskedWalletRequest
        //   7	60	2	localParcel1	Parcel
        //   3	68	3	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   8	14	65	finally
        //   18	29	65	finally
        //   29	48	65	finally
        //   57	62	65	finally
      }
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\internal\or.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */