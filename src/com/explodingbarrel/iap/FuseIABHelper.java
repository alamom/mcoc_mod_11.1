package com.explodingbarrel.iap;

import android.content.Context;
import android.os.RemoteException;
import android.util.Log;
import com.android.vending.billing.IInAppBillingService;
import com.explodingbarrel.iap.util.IabHelper;

class FuseIABHelper
  extends IabHelper
{
  public static final int BILLING_RESPONSE_PROMO_ELIGIBLE = 9;
  public static final int BILLING_RESPONSE_PROMO_NOT_ELIGIBLE = 10;
  private static String TAG = "FuseIABHelper";
  
  public FuseIABHelper(Context paramContext, String paramString)
  {
    super(paramContext, paramString);
  }
  
  public int isPromoEligible(String paramString)
  {
    int i = -1;
    if (this.mService == null) {
      Log.d(TAG, "BillingService is null.  IABHelper setup has likely not been called yet!");
    }
    for (;;)
    {
      return i;
      try
      {
        int j = this.mService.isPromoEligible(4, paramString, "inapp");
        i = j;
      }
      catch (RemoteException paramString)
      {
        Log.d(TAG, "RemoteException: " + paramString.getMessage());
      }
      catch (Exception paramString)
      {
        Log.d(TAG, "Got unknown exception checking promo eligible: " + paramString.getMessage());
      }
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\explodingbarrel\iap\FuseIABHelper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */