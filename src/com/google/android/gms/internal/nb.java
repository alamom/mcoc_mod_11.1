package com.google.android.gms.internal;

import android.net.Uri;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.os.RemoteException;

public abstract interface nb
  extends IInterface
{
  public abstract void a(na paramna, Uri paramUri, Bundle paramBundle, boolean paramBoolean)
    throws RemoteException;
  
  public static abstract class a
    extends Binder
    implements nb
  {
    public static nb bA(IBinder paramIBinder)
    {
      if (paramIBinder == null) {
        paramIBinder = null;
      }
      for (;;)
      {
        return paramIBinder;
        IInterface localIInterface = paramIBinder.queryLocalInterface("com.google.android.gms.panorama.internal.IPanoramaService");
        if ((localIInterface != null) && ((localIInterface instanceof nb))) {
          paramIBinder = (nb)localIInterface;
        } else {
          paramIBinder = new a(paramIBinder);
        }
      }
    }
    
    public boolean onTransact(int paramInt1, Parcel paramParcel1, Parcel paramParcel2, int paramInt2)
      throws RemoteException
    {
      boolean bool2 = true;
      switch (paramInt1)
      {
      default: 
      case 1598968902: 
        for (bool1 = super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);; bool1 = bool2)
        {
          return bool1;
          paramParcel2.writeString("com.google.android.gms.panorama.internal.IPanoramaService");
        }
      }
      paramParcel1.enforceInterface("com.google.android.gms.panorama.internal.IPanoramaService");
      na localna = na.a.bz(paramParcel1.readStrongBinder());
      label94:
      Bundle localBundle;
      if (paramParcel1.readInt() != 0)
      {
        paramParcel2 = (Uri)Uri.CREATOR.createFromParcel(paramParcel1);
        if (paramParcel1.readInt() == 0) {
          break label148;
        }
        localBundle = (Bundle)Bundle.CREATOR.createFromParcel(paramParcel1);
        label115:
        if (paramParcel1.readInt() == 0) {
          break label154;
        }
      }
      label148:
      label154:
      for (boolean bool1 = true;; bool1 = false)
      {
        a(localna, paramParcel2, localBundle, bool1);
        bool1 = bool2;
        break;
        paramParcel2 = null;
        break label94;
        localBundle = null;
        break label115;
      }
    }
    
    private static class a
      implements nb
    {
      private IBinder lb;
      
      a(IBinder paramIBinder)
      {
        this.lb = paramIBinder;
      }
      
      public void a(na paramna, Uri paramUri, Bundle paramBundle, boolean paramBoolean)
        throws RemoteException
      {
        IBinder localIBinder = null;
        int i = 1;
        Parcel localParcel = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel.writeInterfaceToken("com.google.android.gms.panorama.internal.IPanoramaService");
            if (paramna != null) {
              localIBinder = paramna.asBinder();
            }
            localParcel.writeStrongBinder(localIBinder);
            if (paramUri != null)
            {
              localParcel.writeInt(1);
              paramUri.writeToParcel(localParcel, 0);
              if (paramBundle != null)
              {
                localParcel.writeInt(1);
                paramBundle.writeToParcel(localParcel, 0);
                if (!paramBoolean) {
                  break label130;
                }
                localParcel.writeInt(i);
                this.lb.transact(1, localParcel, null, 1);
              }
            }
            else
            {
              localParcel.writeInt(0);
              continue;
            }
            localParcel.writeInt(0);
          }
          finally
          {
            localParcel.recycle();
          }
          continue;
          label130:
          i = 0;
        }
      }
      
      public IBinder asBinder()
      {
        return this.lb;
      }
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\internal\nb.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */