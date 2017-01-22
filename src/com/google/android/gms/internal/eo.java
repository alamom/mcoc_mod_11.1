package com.google.android.gms.internal;

import android.content.Intent;
import android.os.RemoteException;
import com.google.android.gms.ads.purchase.InAppPurchaseResult;

@ez
public class eo
  implements InAppPurchaseResult
{
  private final ek sL;
  
  public eo(ek paramek)
  {
    this.sL = paramek;
  }
  
  public void finishPurchase()
  {
    try
    {
      this.sL.finishPurchase();
      return;
    }
    catch (RemoteException localRemoteException)
    {
      for (;;)
      {
        gs.d("Could not forward finishPurchase to InAppPurchaseResult", localRemoteException);
      }
    }
  }
  
  public String getProductId()
  {
    try
    {
      String str = this.sL.getProductId();
      return str;
    }
    catch (RemoteException localRemoteException)
    {
      for (;;)
      {
        gs.d("Could not forward getProductId to InAppPurchaseResult", localRemoteException);
        Object localObject = null;
      }
    }
  }
  
  public Intent getPurchaseData()
  {
    try
    {
      Intent localIntent = this.sL.getPurchaseData();
      return localIntent;
    }
    catch (RemoteException localRemoteException)
    {
      for (;;)
      {
        gs.d("Could not forward getPurchaseData to InAppPurchaseResult", localRemoteException);
        Object localObject = null;
      }
    }
  }
  
  public int getResultCode()
  {
    try
    {
      i = this.sL.getResultCode();
      return i;
    }
    catch (RemoteException localRemoteException)
    {
      for (;;)
      {
        gs.d("Could not forward getPurchaseData to InAppPurchaseResult", localRemoteException);
        int i = 0;
      }
    }
  }
  
  public boolean isVerified()
  {
    try
    {
      bool = this.sL.isVerified();
      return bool;
    }
    catch (RemoteException localRemoteException)
    {
      for (;;)
      {
        gs.d("Could not forward isVerified to InAppPurchaseResult", localRemoteException);
        boolean bool = false;
      }
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\internal\eo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */