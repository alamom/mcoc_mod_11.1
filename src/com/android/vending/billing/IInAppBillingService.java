package com.android.vending.billing;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.os.RemoteException;
import java.util.List;

public abstract interface IInAppBillingService
  extends IInterface
{
  public abstract int consumePurchase(int paramInt, String paramString1, String paramString2)
    throws RemoteException;
  
  public abstract Bundle getBuyIntent(int paramInt, String paramString1, String paramString2, String paramString3, String paramString4)
    throws RemoteException;
  
  public abstract Bundle getBuyIntentToReplaceSkus(int paramInt, String paramString1, List<String> paramList, String paramString2, String paramString3, String paramString4)
    throws RemoteException;
  
  public abstract Bundle getPurchases(int paramInt, String paramString1, String paramString2, String paramString3)
    throws RemoteException;
  
  public abstract Bundle getSkuDetails(int paramInt, String paramString1, String paramString2, Bundle paramBundle)
    throws RemoteException;
  
  public abstract int isBillingSupported(int paramInt, String paramString1, String paramString2)
    throws RemoteException;
  
  public abstract int isPromoEligible(int paramInt, String paramString1, String paramString2)
    throws RemoteException;
  
  public static abstract class Stub
    extends Binder
    implements IInAppBillingService
  {
    private static final String DESCRIPTOR = "com.android.vending.billing.IInAppBillingService";
    static final int TRANSACTION_consumePurchase = 5;
    static final int TRANSACTION_getBuyIntent = 3;
    static final int TRANSACTION_getBuyIntentToReplaceSkus = 7;
    static final int TRANSACTION_getPurchases = 4;
    static final int TRANSACTION_getSkuDetails = 2;
    static final int TRANSACTION_isBillingSupported = 1;
    static final int TRANSACTION_isPromoEligible = 6;
    
    public Stub()
    {
      attachInterface(this, "com.android.vending.billing.IInAppBillingService");
    }
    
    public static IInAppBillingService asInterface(IBinder paramIBinder)
    {
      if (paramIBinder == null) {
        paramIBinder = null;
      }
      for (;;)
      {
        return paramIBinder;
        IInterface localIInterface = paramIBinder.queryLocalInterface("com.android.vending.billing.IInAppBillingService");
        if ((localIInterface != null) && ((localIInterface instanceof IInAppBillingService))) {
          paramIBinder = (IInAppBillingService)localIInterface;
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
      boolean bool;
      switch (paramInt1)
      {
      default: 
        bool = super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
      case 1598968902: 
      case 1: 
      case 2: 
      case 3: 
      case 4: 
      case 5: 
      case 6: 
        for (;;)
        {
          return bool;
          paramParcel2.writeString("com.android.vending.billing.IInAppBillingService");
          bool = true;
          continue;
          paramParcel1.enforceInterface("com.android.vending.billing.IInAppBillingService");
          paramInt1 = isBillingSupported(paramParcel1.readInt(), paramParcel1.readString(), paramParcel1.readString());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(paramInt1);
          bool = true;
          continue;
          paramParcel1.enforceInterface("com.android.vending.billing.IInAppBillingService");
          paramInt1 = paramParcel1.readInt();
          String str2 = paramParcel1.readString();
          String str1 = paramParcel1.readString();
          if (paramParcel1.readInt() != 0)
          {
            paramParcel1 = (Bundle)Bundle.CREATOR.createFromParcel(paramParcel1);
            label183:
            paramParcel1 = getSkuDetails(paramInt1, str2, str1, paramParcel1);
            paramParcel2.writeNoException();
            if (paramParcel1 == null) {
              break label224;
            }
            paramParcel2.writeInt(1);
            paramParcel1.writeToParcel(paramParcel2, 1);
          }
          for (;;)
          {
            bool = true;
            break;
            paramParcel1 = null;
            break label183;
            label224:
            paramParcel2.writeInt(0);
          }
          paramParcel1.enforceInterface("com.android.vending.billing.IInAppBillingService");
          paramParcel1 = getBuyIntent(paramParcel1.readInt(), paramParcel1.readString(), paramParcel1.readString(), paramParcel1.readString(), paramParcel1.readString());
          paramParcel2.writeNoException();
          if (paramParcel1 != null)
          {
            paramParcel2.writeInt(1);
            paramParcel1.writeToParcel(paramParcel2, 1);
          }
          for (;;)
          {
            bool = true;
            break;
            paramParcel2.writeInt(0);
          }
          paramParcel1.enforceInterface("com.android.vending.billing.IInAppBillingService");
          paramParcel1 = getPurchases(paramParcel1.readInt(), paramParcel1.readString(), paramParcel1.readString(), paramParcel1.readString());
          paramParcel2.writeNoException();
          if (paramParcel1 != null)
          {
            paramParcel2.writeInt(1);
            paramParcel1.writeToParcel(paramParcel2, 1);
          }
          for (;;)
          {
            bool = true;
            break;
            paramParcel2.writeInt(0);
          }
          paramParcel1.enforceInterface("com.android.vending.billing.IInAppBillingService");
          paramInt1 = consumePurchase(paramParcel1.readInt(), paramParcel1.readString(), paramParcel1.readString());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(paramInt1);
          bool = true;
          continue;
          paramParcel1.enforceInterface("com.android.vending.billing.IInAppBillingService");
          paramInt1 = isPromoEligible(paramParcel1.readInt(), paramParcel1.readString(), paramParcel1.readString());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(paramInt1);
          bool = true;
        }
      }
      paramParcel1.enforceInterface("com.android.vending.billing.IInAppBillingService");
      paramParcel1 = getBuyIntentToReplaceSkus(paramParcel1.readInt(), paramParcel1.readString(), paramParcel1.createStringArrayList(), paramParcel1.readString(), paramParcel1.readString(), paramParcel1.readString());
      paramParcel2.writeNoException();
      if (paramParcel1 != null)
      {
        paramParcel2.writeInt(1);
        paramParcel1.writeToParcel(paramParcel2, 1);
      }
      for (;;)
      {
        bool = true;
        break;
        paramParcel2.writeInt(0);
      }
    }
    
    private static class Proxy
      implements IInAppBillingService
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
      
      public int consumePurchase(int paramInt, String paramString1, String paramString2)
        throws RemoteException
      {
        Parcel localParcel2 = Parcel.obtain();
        Parcel localParcel1 = Parcel.obtain();
        try
        {
          localParcel2.writeInterfaceToken("com.android.vending.billing.IInAppBillingService");
          localParcel2.writeInt(paramInt);
          localParcel2.writeString(paramString1);
          localParcel2.writeString(paramString2);
          this.mRemote.transact(5, localParcel2, localParcel1, 0);
          localParcel1.readException();
          paramInt = localParcel1.readInt();
          return paramInt;
        }
        finally
        {
          localParcel1.recycle();
          localParcel2.recycle();
        }
      }
      
      /* Error */
      public Bundle getBuyIntent(int paramInt, String paramString1, String paramString2, String paramString3, String paramString4)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 32	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore 6
        //   5: invokestatic 32	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   8: astore 7
        //   10: aload 6
        //   12: ldc 34
        //   14: invokevirtual 38	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   17: aload 6
        //   19: iload_1
        //   20: invokevirtual 42	android/os/Parcel:writeInt	(I)V
        //   23: aload 6
        //   25: aload_2
        //   26: invokevirtual 45	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   29: aload 6
        //   31: aload_3
        //   32: invokevirtual 45	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   35: aload 6
        //   37: aload 4
        //   39: invokevirtual 45	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   42: aload 6
        //   44: aload 5
        //   46: invokevirtual 45	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   49: aload_0
        //   50: getfield 19	com/android/vending/billing/IInAppBillingService$Stub$Proxy:mRemote	Landroid/os/IBinder;
        //   53: iconst_3
        //   54: aload 6
        //   56: aload 7
        //   58: iconst_0
        //   59: invokeinterface 51 5 0
        //   64: pop
        //   65: aload 7
        //   67: invokevirtual 54	android/os/Parcel:readException	()V
        //   70: aload 7
        //   72: invokevirtual 58	android/os/Parcel:readInt	()I
        //   75: ifeq +29 -> 104
        //   78: getstatic 70	android/os/Bundle:CREATOR	Landroid/os/Parcelable$Creator;
        //   81: aload 7
        //   83: invokeinterface 76 2 0
        //   88: checkcast 66	android/os/Bundle
        //   91: astore_2
        //   92: aload 7
        //   94: invokevirtual 61	android/os/Parcel:recycle	()V
        //   97: aload 6
        //   99: invokevirtual 61	android/os/Parcel:recycle	()V
        //   102: aload_2
        //   103: areturn
        //   104: aconst_null
        //   105: astore_2
        //   106: goto -14 -> 92
        //   109: astore_2
        //   110: aload 7
        //   112: invokevirtual 61	android/os/Parcel:recycle	()V
        //   115: aload 6
        //   117: invokevirtual 61	android/os/Parcel:recycle	()V
        //   120: aload_2
        //   121: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	122	0	this	Proxy
        //   0	122	1	paramInt	int
        //   0	122	2	paramString1	String
        //   0	122	3	paramString2	String
        //   0	122	4	paramString3	String
        //   0	122	5	paramString4	String
        //   3	113	6	localParcel1	Parcel
        //   8	103	7	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   10	92	109	finally
      }
      
      /* Error */
      public Bundle getBuyIntentToReplaceSkus(int paramInt, String paramString1, List<String> paramList, String paramString2, String paramString3, String paramString4)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 32	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore 8
        //   5: invokestatic 32	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   8: astore 7
        //   10: aload 8
        //   12: ldc 34
        //   14: invokevirtual 38	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   17: aload 8
        //   19: iload_1
        //   20: invokevirtual 42	android/os/Parcel:writeInt	(I)V
        //   23: aload 8
        //   25: aload_2
        //   26: invokevirtual 45	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   29: aload 8
        //   31: aload_3
        //   32: invokevirtual 82	android/os/Parcel:writeStringList	(Ljava/util/List;)V
        //   35: aload 8
        //   37: aload 4
        //   39: invokevirtual 45	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   42: aload 8
        //   44: aload 5
        //   46: invokevirtual 45	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   49: aload 8
        //   51: aload 6
        //   53: invokevirtual 45	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   56: aload_0
        //   57: getfield 19	com/android/vending/billing/IInAppBillingService$Stub$Proxy:mRemote	Landroid/os/IBinder;
        //   60: bipush 7
        //   62: aload 8
        //   64: aload 7
        //   66: iconst_0
        //   67: invokeinterface 51 5 0
        //   72: pop
        //   73: aload 7
        //   75: invokevirtual 54	android/os/Parcel:readException	()V
        //   78: aload 7
        //   80: invokevirtual 58	android/os/Parcel:readInt	()I
        //   83: ifeq +29 -> 112
        //   86: getstatic 70	android/os/Bundle:CREATOR	Landroid/os/Parcelable$Creator;
        //   89: aload 7
        //   91: invokeinterface 76 2 0
        //   96: checkcast 66	android/os/Bundle
        //   99: astore_2
        //   100: aload 7
        //   102: invokevirtual 61	android/os/Parcel:recycle	()V
        //   105: aload 8
        //   107: invokevirtual 61	android/os/Parcel:recycle	()V
        //   110: aload_2
        //   111: areturn
        //   112: aconst_null
        //   113: astore_2
        //   114: goto -14 -> 100
        //   117: astore_2
        //   118: aload 7
        //   120: invokevirtual 61	android/os/Parcel:recycle	()V
        //   123: aload 8
        //   125: invokevirtual 61	android/os/Parcel:recycle	()V
        //   128: aload_2
        //   129: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	130	0	this	Proxy
        //   0	130	1	paramInt	int
        //   0	130	2	paramString1	String
        //   0	130	3	paramList	List<String>
        //   0	130	4	paramString2	String
        //   0	130	5	paramString3	String
        //   0	130	6	paramString4	String
        //   8	111	7	localParcel1	Parcel
        //   3	121	8	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   10	100	117	finally
      }
      
      public String getInterfaceDescriptor()
      {
        return "com.android.vending.billing.IInAppBillingService";
      }
      
      /* Error */
      public Bundle getPurchases(int paramInt, String paramString1, String paramString2, String paramString3)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 32	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore 6
        //   5: invokestatic 32	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   8: astore 5
        //   10: aload 6
        //   12: ldc 34
        //   14: invokevirtual 38	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   17: aload 6
        //   19: iload_1
        //   20: invokevirtual 42	android/os/Parcel:writeInt	(I)V
        //   23: aload 6
        //   25: aload_2
        //   26: invokevirtual 45	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   29: aload 6
        //   31: aload_3
        //   32: invokevirtual 45	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   35: aload 6
        //   37: aload 4
        //   39: invokevirtual 45	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   42: aload_0
        //   43: getfield 19	com/android/vending/billing/IInAppBillingService$Stub$Proxy:mRemote	Landroid/os/IBinder;
        //   46: iconst_4
        //   47: aload 6
        //   49: aload 5
        //   51: iconst_0
        //   52: invokeinterface 51 5 0
        //   57: pop
        //   58: aload 5
        //   60: invokevirtual 54	android/os/Parcel:readException	()V
        //   63: aload 5
        //   65: invokevirtual 58	android/os/Parcel:readInt	()I
        //   68: ifeq +29 -> 97
        //   71: getstatic 70	android/os/Bundle:CREATOR	Landroid/os/Parcelable$Creator;
        //   74: aload 5
        //   76: invokeinterface 76 2 0
        //   81: checkcast 66	android/os/Bundle
        //   84: astore_2
        //   85: aload 5
        //   87: invokevirtual 61	android/os/Parcel:recycle	()V
        //   90: aload 6
        //   92: invokevirtual 61	android/os/Parcel:recycle	()V
        //   95: aload_2
        //   96: areturn
        //   97: aconst_null
        //   98: astore_2
        //   99: goto -14 -> 85
        //   102: astore_2
        //   103: aload 5
        //   105: invokevirtual 61	android/os/Parcel:recycle	()V
        //   108: aload 6
        //   110: invokevirtual 61	android/os/Parcel:recycle	()V
        //   113: aload_2
        //   114: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	115	0	this	Proxy
        //   0	115	1	paramInt	int
        //   0	115	2	paramString1	String
        //   0	115	3	paramString2	String
        //   0	115	4	paramString3	String
        //   8	96	5	localParcel1	Parcel
        //   3	106	6	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   10	85	102	finally
      }
      
      public Bundle getSkuDetails(int paramInt, String paramString1, String paramString2, Bundle paramBundle)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.android.vending.billing.IInAppBillingService");
            localParcel1.writeInt(paramInt);
            localParcel1.writeString(paramString1);
            localParcel1.writeString(paramString2);
            if (paramBundle != null)
            {
              localParcel1.writeInt(1);
              paramBundle.writeToParcel(localParcel1, 0);
              this.mRemote.transact(2, localParcel1, localParcel2, 0);
              localParcel2.readException();
              if (localParcel2.readInt() != 0)
              {
                paramString1 = (Bundle)Bundle.CREATOR.createFromParcel(localParcel2);
                return paramString1;
              }
            }
            else
            {
              localParcel1.writeInt(0);
              continue;
            }
            paramString1 = null;
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
        }
      }
      
      public int isBillingSupported(int paramInt, String paramString1, String paramString2)
        throws RemoteException
      {
        Parcel localParcel2 = Parcel.obtain();
        Parcel localParcel1 = Parcel.obtain();
        try
        {
          localParcel2.writeInterfaceToken("com.android.vending.billing.IInAppBillingService");
          localParcel2.writeInt(paramInt);
          localParcel2.writeString(paramString1);
          localParcel2.writeString(paramString2);
          this.mRemote.transact(1, localParcel2, localParcel1, 0);
          localParcel1.readException();
          paramInt = localParcel1.readInt();
          return paramInt;
        }
        finally
        {
          localParcel1.recycle();
          localParcel2.recycle();
        }
      }
      
      public int isPromoEligible(int paramInt, String paramString1, String paramString2)
        throws RemoteException
      {
        Parcel localParcel2 = Parcel.obtain();
        Parcel localParcel1 = Parcel.obtain();
        try
        {
          localParcel2.writeInterfaceToken("com.android.vending.billing.IInAppBillingService");
          localParcel2.writeInt(paramInt);
          localParcel2.writeString(paramString1);
          localParcel2.writeString(paramString2);
          this.mRemote.transact(6, localParcel2, localParcel1, 0);
          localParcel1.readException();
          paramInt = localParcel1.readInt();
          return paramInt;
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


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\android\vending\billing\IInAppBillingService.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */