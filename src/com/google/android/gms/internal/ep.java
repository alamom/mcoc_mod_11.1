package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.ads.purchase.InAppPurchase;

@ez
public class ep
  implements InAppPurchase
{
  private final eg sx;
  
  public ep(eg parameg)
  {
    this.sx = parameg;
  }
  
  public String getProductId()
  {
    try
    {
      String str = this.sx.getProductId();
      return str;
    }
    catch (RemoteException localRemoteException)
    {
      for (;;)
      {
        gs.d("Could not forward getProductId to InAppPurchase", localRemoteException);
        Object localObject = null;
      }
    }
  }
  
  public void recordPlayBillingResolution(int paramInt)
  {
    try
    {
      this.sx.recordPlayBillingResolution(paramInt);
      return;
    }
    catch (RemoteException localRemoteException)
    {
      for (;;)
      {
        gs.d("Could not forward recordPlayBillingResolution to InAppPurchase", localRemoteException);
      }
    }
  }
  
  public void recordResolution(int paramInt)
  {
    try
    {
      this.sx.recordResolution(paramInt);
      return;
    }
    catch (RemoteException localRemoteException)
    {
      for (;;)
      {
        gs.d("Could not forward recordResolution to InAppPurchase", localRemoteException);
      }
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\internal\ep.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */