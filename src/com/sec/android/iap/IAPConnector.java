package com.sec.android.iap;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.os.RemoteException;

public abstract interface IAPConnector
  extends IInterface
{
  public abstract Bundle getItemList(int paramInt1, String paramString1, String paramString2, int paramInt2, int paramInt3, String paramString3)
    throws RemoteException;
  
  public abstract Bundle getItemsInbox(String paramString1, String paramString2, int paramInt1, int paramInt2, String paramString3, String paramString4)
    throws RemoteException;
  
  public abstract Bundle getItemsInbox2(String paramString1, String paramString2)
    throws RemoteException;
  
  public abstract Bundle init(int paramInt)
    throws RemoteException;
  
  public abstract boolean requestCmd(IAPServiceCallback paramIAPServiceCallback, Bundle paramBundle)
    throws RemoteException;
  
  public abstract boolean unregisterCallback(IAPServiceCallback paramIAPServiceCallback)
    throws RemoteException;
  
  public static abstract class Stub
    extends Binder
    implements IAPConnector
  {
    private static final String DESCRIPTOR = "com.sec.android.iap.IAPConnector";
    static final int TRANSACTION_getItemList = 4;
    static final int TRANSACTION_getItemsInbox = 5;
    static final int TRANSACTION_getItemsInbox2 = 6;
    static final int TRANSACTION_init = 3;
    static final int TRANSACTION_requestCmd = 1;
    static final int TRANSACTION_unregisterCallback = 2;
    
    public Stub()
    {
      attachInterface(this, "com.sec.android.iap.IAPConnector");
    }
    
    public static IAPConnector asInterface(IBinder paramIBinder)
    {
      if (paramIBinder == null) {
        paramIBinder = null;
      }
      for (;;)
      {
        return paramIBinder;
        IInterface localIInterface = paramIBinder.queryLocalInterface("com.sec.android.iap.IAPConnector");
        if ((localIInterface != null) && ((localIInterface instanceof IAPConnector))) {
          paramIBinder = (IAPConnector)localIInterface;
        } else {
          paramIBinder = new Proxy(paramIBinder);
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
      int i = 0;
      boolean bool1 = true;
      switch (paramInt1)
      {
      default: 
        bool1 = super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
      }
      for (;;)
      {
        return bool1;
        paramParcel2.writeString("com.sec.android.iap.IAPConnector");
        continue;
        paramParcel1.enforceInterface("com.sec.android.iap.IAPConnector");
        IAPServiceCallback localIAPServiceCallback = IAPServiceCallback.Stub.asInterface(paramParcel1.readStrongBinder());
        if (paramParcel1.readInt() != 0)
        {
          paramParcel1 = (Bundle)Bundle.CREATOR.createFromParcel(paramParcel1);
          label130:
          bool2 = requestCmd(localIAPServiceCallback, paramParcel1);
          paramParcel2.writeNoException();
          if (!bool2) {
            break label163;
          }
        }
        label163:
        for (paramInt1 = 1;; paramInt1 = 0)
        {
          paramParcel2.writeInt(paramInt1);
          break;
          paramParcel1 = null;
          break label130;
        }
        paramParcel1.enforceInterface("com.sec.android.iap.IAPConnector");
        boolean bool2 = unregisterCallback(IAPServiceCallback.Stub.asInterface(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        paramInt1 = i;
        if (bool2) {
          paramInt1 = 1;
        }
        paramParcel2.writeInt(paramInt1);
        continue;
        paramParcel1.enforceInterface("com.sec.android.iap.IAPConnector");
        paramParcel1 = init(paramParcel1.readInt());
        paramParcel2.writeNoException();
        if (paramParcel1 != null)
        {
          paramParcel2.writeInt(1);
          paramParcel1.writeToParcel(paramParcel2, 1);
        }
        else
        {
          paramParcel2.writeInt(0);
          continue;
          paramParcel1.enforceInterface("com.sec.android.iap.IAPConnector");
          paramParcel1 = getItemList(paramParcel1.readInt(), paramParcel1.readString(), paramParcel1.readString(), paramParcel1.readInt(), paramParcel1.readInt(), paramParcel1.readString());
          paramParcel2.writeNoException();
          if (paramParcel1 != null)
          {
            paramParcel2.writeInt(1);
            paramParcel1.writeToParcel(paramParcel2, 1);
          }
          else
          {
            paramParcel2.writeInt(0);
            continue;
            paramParcel1.enforceInterface("com.sec.android.iap.IAPConnector");
            paramParcel1 = getItemsInbox(paramParcel1.readString(), paramParcel1.readString(), paramParcel1.readInt(), paramParcel1.readInt(), paramParcel1.readString(), paramParcel1.readString());
            paramParcel2.writeNoException();
            if (paramParcel1 != null)
            {
              paramParcel2.writeInt(1);
              paramParcel1.writeToParcel(paramParcel2, 1);
            }
            else
            {
              paramParcel2.writeInt(0);
              continue;
              paramParcel1.enforceInterface("com.sec.android.iap.IAPConnector");
              paramParcel1 = getItemsInbox2(paramParcel1.readString(), paramParcel1.readString());
              paramParcel2.writeNoException();
              if (paramParcel1 != null)
              {
                paramParcel2.writeInt(1);
                paramParcel1.writeToParcel(paramParcel2, 1);
              }
              else
              {
                paramParcel2.writeInt(0);
              }
            }
          }
        }
      }
    }
    
    private static class Proxy
      implements IAPConnector
    {
      private IBinder mRemote;
      
      Proxy(IBinder paramIBinder)
      {
        this.mRemote = paramIBinder;
      }
      
      public IBinder asBinder()
      {
        return this.mRemote;
      }
      
      public String getInterfaceDescriptor()
      {
        return "com.sec.android.iap.IAPConnector";
      }
      
      /* Error */
      public Bundle getItemList(int paramInt1, String paramString1, String paramString2, int paramInt2, int paramInt3, String paramString3)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 36	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore 7
        //   5: invokestatic 36	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   8: astore 8
        //   10: aload 7
        //   12: ldc 26
        //   14: invokevirtual 40	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   17: aload 7
        //   19: iload_1
        //   20: invokevirtual 44	android/os/Parcel:writeInt	(I)V
        //   23: aload 7
        //   25: aload_2
        //   26: invokevirtual 47	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   29: aload 7
        //   31: aload_3
        //   32: invokevirtual 47	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   35: aload 7
        //   37: iload 4
        //   39: invokevirtual 44	android/os/Parcel:writeInt	(I)V
        //   42: aload 7
        //   44: iload 5
        //   46: invokevirtual 44	android/os/Parcel:writeInt	(I)V
        //   49: aload 7
        //   51: aload 6
        //   53: invokevirtual 47	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   56: aload_0
        //   57: getfield 19	com/sec/android/iap/IAPConnector$Stub$Proxy:mRemote	Landroid/os/IBinder;
        //   60: iconst_4
        //   61: aload 7
        //   63: aload 8
        //   65: iconst_0
        //   66: invokeinterface 53 5 0
        //   71: pop
        //   72: aload 8
        //   74: invokevirtual 56	android/os/Parcel:readException	()V
        //   77: aload 8
        //   79: invokevirtual 60	android/os/Parcel:readInt	()I
        //   82: ifeq +29 -> 111
        //   85: getstatic 66	android/os/Bundle:CREATOR	Landroid/os/Parcelable$Creator;
        //   88: aload 8
        //   90: invokeinterface 72 2 0
        //   95: checkcast 62	android/os/Bundle
        //   98: astore_2
        //   99: aload 8
        //   101: invokevirtual 75	android/os/Parcel:recycle	()V
        //   104: aload 7
        //   106: invokevirtual 75	android/os/Parcel:recycle	()V
        //   109: aload_2
        //   110: areturn
        //   111: aconst_null
        //   112: astore_2
        //   113: goto -14 -> 99
        //   116: astore_2
        //   117: aload 8
        //   119: invokevirtual 75	android/os/Parcel:recycle	()V
        //   122: aload 7
        //   124: invokevirtual 75	android/os/Parcel:recycle	()V
        //   127: aload_2
        //   128: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	129	0	this	Proxy
        //   0	129	1	paramInt1	int
        //   0	129	2	paramString1	String
        //   0	129	3	paramString2	String
        //   0	129	4	paramInt2	int
        //   0	129	5	paramInt3	int
        //   0	129	6	paramString3	String
        //   3	120	7	localParcel1	Parcel
        //   8	110	8	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   10	99	116	finally
      }
      
      /* Error */
      public Bundle getItemsInbox(String paramString1, String paramString2, int paramInt1, int paramInt2, String paramString3, String paramString4)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 36	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore 8
        //   5: invokestatic 36	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   8: astore 7
        //   10: aload 8
        //   12: ldc 26
        //   14: invokevirtual 40	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   17: aload 8
        //   19: aload_1
        //   20: invokevirtual 47	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   23: aload 8
        //   25: aload_2
        //   26: invokevirtual 47	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   29: aload 8
        //   31: iload_3
        //   32: invokevirtual 44	android/os/Parcel:writeInt	(I)V
        //   35: aload 8
        //   37: iload 4
        //   39: invokevirtual 44	android/os/Parcel:writeInt	(I)V
        //   42: aload 8
        //   44: aload 5
        //   46: invokevirtual 47	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   49: aload 8
        //   51: aload 6
        //   53: invokevirtual 47	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   56: aload_0
        //   57: getfield 19	com/sec/android/iap/IAPConnector$Stub$Proxy:mRemote	Landroid/os/IBinder;
        //   60: iconst_5
        //   61: aload 8
        //   63: aload 7
        //   65: iconst_0
        //   66: invokeinterface 53 5 0
        //   71: pop
        //   72: aload 7
        //   74: invokevirtual 56	android/os/Parcel:readException	()V
        //   77: aload 7
        //   79: invokevirtual 60	android/os/Parcel:readInt	()I
        //   82: ifeq +29 -> 111
        //   85: getstatic 66	android/os/Bundle:CREATOR	Landroid/os/Parcelable$Creator;
        //   88: aload 7
        //   90: invokeinterface 72 2 0
        //   95: checkcast 62	android/os/Bundle
        //   98: astore_1
        //   99: aload 7
        //   101: invokevirtual 75	android/os/Parcel:recycle	()V
        //   104: aload 8
        //   106: invokevirtual 75	android/os/Parcel:recycle	()V
        //   109: aload_1
        //   110: areturn
        //   111: aconst_null
        //   112: astore_1
        //   113: goto -14 -> 99
        //   116: astore_1
        //   117: aload 7
        //   119: invokevirtual 75	android/os/Parcel:recycle	()V
        //   122: aload 8
        //   124: invokevirtual 75	android/os/Parcel:recycle	()V
        //   127: aload_1
        //   128: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	129	0	this	Proxy
        //   0	129	1	paramString1	String
        //   0	129	2	paramString2	String
        //   0	129	3	paramInt1	int
        //   0	129	4	paramInt2	int
        //   0	129	5	paramString3	String
        //   0	129	6	paramString4	String
        //   8	110	7	localParcel1	Parcel
        //   3	120	8	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   10	99	116	finally
      }
      
      /* Error */
      public Bundle getItemsInbox2(String paramString1, String paramString2)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 36	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore_3
        //   4: invokestatic 36	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   7: astore 4
        //   9: aload_3
        //   10: ldc 26
        //   12: invokevirtual 40	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   15: aload_3
        //   16: aload_1
        //   17: invokevirtual 47	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   20: aload_3
        //   21: aload_2
        //   22: invokevirtual 47	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   25: aload_0
        //   26: getfield 19	com/sec/android/iap/IAPConnector$Stub$Proxy:mRemote	Landroid/os/IBinder;
        //   29: bipush 6
        //   31: aload_3
        //   32: aload 4
        //   34: iconst_0
        //   35: invokeinterface 53 5 0
        //   40: pop
        //   41: aload 4
        //   43: invokevirtual 56	android/os/Parcel:readException	()V
        //   46: aload 4
        //   48: invokevirtual 60	android/os/Parcel:readInt	()I
        //   51: ifeq +28 -> 79
        //   54: getstatic 66	android/os/Bundle:CREATOR	Landroid/os/Parcelable$Creator;
        //   57: aload 4
        //   59: invokeinterface 72 2 0
        //   64: checkcast 62	android/os/Bundle
        //   67: astore_1
        //   68: aload 4
        //   70: invokevirtual 75	android/os/Parcel:recycle	()V
        //   73: aload_3
        //   74: invokevirtual 75	android/os/Parcel:recycle	()V
        //   77: aload_1
        //   78: areturn
        //   79: aconst_null
        //   80: astore_1
        //   81: goto -13 -> 68
        //   84: astore_1
        //   85: aload 4
        //   87: invokevirtual 75	android/os/Parcel:recycle	()V
        //   90: aload_3
        //   91: invokevirtual 75	android/os/Parcel:recycle	()V
        //   94: aload_1
        //   95: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	96	0	this	Proxy
        //   0	96	1	paramString1	String
        //   0	96	2	paramString2	String
        //   3	88	3	localParcel1	Parcel
        //   7	79	4	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   9	68	84	finally
      }
      
      /* Error */
      public Bundle init(int paramInt)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 36	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore 4
        //   5: invokestatic 36	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   8: astore_3
        //   9: aload 4
        //   11: ldc 26
        //   13: invokevirtual 40	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   16: aload 4
        //   18: iload_1
        //   19: invokevirtual 44	android/os/Parcel:writeInt	(I)V
        //   22: aload_0
        //   23: getfield 19	com/sec/android/iap/IAPConnector$Stub$Proxy:mRemote	Landroid/os/IBinder;
        //   26: iconst_3
        //   27: aload 4
        //   29: aload_3
        //   30: iconst_0
        //   31: invokeinterface 53 5 0
        //   36: pop
        //   37: aload_3
        //   38: invokevirtual 56	android/os/Parcel:readException	()V
        //   41: aload_3
        //   42: invokevirtual 60	android/os/Parcel:readInt	()I
        //   45: ifeq +27 -> 72
        //   48: getstatic 66	android/os/Bundle:CREATOR	Landroid/os/Parcelable$Creator;
        //   51: aload_3
        //   52: invokeinterface 72 2 0
        //   57: checkcast 62	android/os/Bundle
        //   60: astore_2
        //   61: aload_3
        //   62: invokevirtual 75	android/os/Parcel:recycle	()V
        //   65: aload 4
        //   67: invokevirtual 75	android/os/Parcel:recycle	()V
        //   70: aload_2
        //   71: areturn
        //   72: aconst_null
        //   73: astore_2
        //   74: goto -13 -> 61
        //   77: astore_2
        //   78: aload_3
        //   79: invokevirtual 75	android/os/Parcel:recycle	()V
        //   82: aload 4
        //   84: invokevirtual 75	android/os/Parcel:recycle	()V
        //   87: aload_2
        //   88: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	89	0	this	Proxy
        //   0	89	1	paramInt	int
        //   60	14	2	localBundle	Bundle
        //   77	11	2	localObject	Object
        //   8	71	3	localParcel1	Parcel
        //   3	80	4	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   9	61	77	finally
      }
      
      public boolean requestCmd(IAPServiceCallback paramIAPServiceCallback, Bundle paramBundle)
        throws RemoteException
      {
        boolean bool = true;
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        label125:
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.sec.android.iap.IAPConnector");
            if (paramIAPServiceCallback != null)
            {
              paramIAPServiceCallback = paramIAPServiceCallback.asBinder();
              localParcel1.writeStrongBinder(paramIAPServiceCallback);
              if (paramBundle != null)
              {
                localParcel1.writeInt(1);
                paramBundle.writeToParcel(localParcel1, 0);
                this.mRemote.transact(1, localParcel1, localParcel2, 0);
                localParcel2.readException();
                int i = localParcel2.readInt();
                if (i == 0) {
                  break label125;
                }
                return bool;
              }
            }
            else
            {
              paramIAPServiceCallback = null;
              continue;
            }
            localParcel1.writeInt(0);
            continue;
            bool = false;
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
        }
      }
      
      /* Error */
      public boolean unregisterCallback(IAPServiceCallback paramIAPServiceCallback)
        throws RemoteException
      {
        // Byte code:
        //   0: iconst_0
        //   1: istore_3
        //   2: invokestatic 36	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   5: astore 5
        //   7: invokestatic 36	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   10: astore 4
        //   12: aload 5
        //   14: ldc 26
        //   16: invokevirtual 40	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   19: aload_1
        //   20: ifnull +61 -> 81
        //   23: aload_1
        //   24: invokeinterface 88 1 0
        //   29: astore_1
        //   30: aload 5
        //   32: aload_1
        //   33: invokevirtual 91	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   36: aload_0
        //   37: getfield 19	com/sec/android/iap/IAPConnector$Stub$Proxy:mRemote	Landroid/os/IBinder;
        //   40: iconst_2
        //   41: aload 5
        //   43: aload 4
        //   45: iconst_0
        //   46: invokeinterface 53 5 0
        //   51: pop
        //   52: aload 4
        //   54: invokevirtual 56	android/os/Parcel:readException	()V
        //   57: aload 4
        //   59: invokevirtual 60	android/os/Parcel:readInt	()I
        //   62: istore_2
        //   63: iload_2
        //   64: ifeq +5 -> 69
        //   67: iconst_1
        //   68: istore_3
        //   69: aload 4
        //   71: invokevirtual 75	android/os/Parcel:recycle	()V
        //   74: aload 5
        //   76: invokevirtual 75	android/os/Parcel:recycle	()V
        //   79: iload_3
        //   80: ireturn
        //   81: aconst_null
        //   82: astore_1
        //   83: goto -53 -> 30
        //   86: astore_1
        //   87: aload 4
        //   89: invokevirtual 75	android/os/Parcel:recycle	()V
        //   92: aload 5
        //   94: invokevirtual 75	android/os/Parcel:recycle	()V
        //   97: aload_1
        //   98: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	99	0	this	Proxy
        //   0	99	1	paramIAPServiceCallback	IAPServiceCallback
        //   62	2	2	i	int
        //   1	79	3	bool	boolean
        //   10	78	4	localParcel1	Parcel
        //   5	88	5	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   12	19	86	finally
        //   23	30	86	finally
        //   30	63	86	finally
      }
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\sec\android\iap\IAPConnector.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */