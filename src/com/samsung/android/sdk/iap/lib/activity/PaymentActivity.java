package com.samsung.android.sdk.iap.lib.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import com.samsung.android.sdk.iap.lib.R.string;
import com.samsung.android.sdk.iap.lib.helper.SamsungIapHelper;
import com.samsung.android.sdk.iap.lib.listener.OnInitIapListener;
import com.samsung.android.sdk.iap.lib.vo.ErrorVo;
import com.samsung.android.sdk.iap.lib.vo.PurchaseVo;

public class PaymentActivity
  extends BaseActivity
  implements OnInitIapListener
{
  private static final String TAG = PaymentActivity.class.getSimpleName();
  private String mItemId = null;
  
  private void finishPurchase(Intent paramIntent)
  {
    if ((paramIntent != null) && (paramIntent.getExtras() != null))
    {
      paramIntent = paramIntent.getExtras();
      this.mErrorVo.setError(paramIntent.getInt("STATUS_CODE"), paramIntent.getString("ERROR_STRING"));
      if (this.mErrorVo.getErrorCode() == 0)
      {
        this.mPurchaseVo = new PurchaseVo(paramIntent.getString("RESULT_OBJECT"));
        this.mSamsungIapHelper.verifyPurchaseResult(this, this.mPurchaseVo, this.mShowSuccessDialog, this.mShowErrorDialog);
      }
    }
    for (;;)
    {
      return;
      this.mSamsungIapHelper.showIapDialogIfNeeded(this, getString(R.string.IDS_SAPPS_POP_SAMSUNG_IN_APP_PURCHASE), this.mErrorVo.getErrorString(), true, null, this.mShowErrorDialog);
      continue;
      this.mErrorVo.setError(64534, getString(R.string.IDS_SAPPS_POP_UNKNOWN_ERROR_OCCURRED));
      this.mSamsungIapHelper.showIapDialogIfNeeded(this, getString(R.string.IDS_SAPPS_POP_SAMSUNG_IN_APP_PURCHASE), getString(R.string.IDS_SAPPS_POP_UNKNOWN_ERROR_OCCURRED) + "[Lib_Payment]", true, null, this.mShowErrorDialog);
    }
  }
  
  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    switch (paramInt1)
    {
    }
    for (;;)
    {
      return;
      if (-1 == paramInt2)
      {
        finishPurchase(paramIntent);
      }
      else if (paramInt2 == 0)
      {
        this.mErrorVo.setError(1, getString(R.string.IDS_SAPPS_POP_PAYMENT_CANCELLED));
        this.mSamsungIapHelper.showIapDialogIfNeeded(this, getString(R.string.IDS_SAPPS_POP_SAMSUNG_IN_APP_PURCHASE), this.mErrorVo.getErrorString(), true, null, this.mShowErrorDialog);
        continue;
        Log.i(TAG, "Samsung Account Result : " + paramInt2);
        if (-1 == paramInt2)
        {
          bindIapService();
        }
        else
        {
          this.mErrorVo.setError(1, getString(R.string.IDS_SAPPS_POP_PAYMENT_CANCELLED));
          this.mSamsungIapHelper.showIapDialogIfNeeded(this, getString(R.string.IDS_SAPPS_POP_SAMSUNG_IN_APP_PURCHASE), getString(R.string.IDS_SAPPS_POP_PAYMENT_CANCELLED), true, null, this.mShowErrorDialog);
        }
      }
    }
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    paramBundle = getIntent();
    if ((paramBundle != null) && (paramBundle.getExtras() != null) && (paramBundle.getExtras().containsKey("ItemId")))
    {
      paramBundle = paramBundle.getExtras();
      this.mItemId = paramBundle.getString("ItemId");
      this.mShowSuccessDialog = paramBundle.getBoolean("ShowSuccessDialog", true);
      this.mShowErrorDialog = paramBundle.getBoolean("ShowErrorDialog", true);
    }
    for (;;)
    {
      this.mSamsungIapHelper.setOnInitIapListener(this);
      if (checkIapPackage() == true)
      {
        Log.i(TAG, "Samsung Account Login...");
        this.mSamsungIapHelper.startAccountActivity(this);
      }
      return;
      Toast.makeText(this, R.string.IDS_SAPPS_POP_AN_INVALID_VALUE_HAS_BEEN_PROVIDED_FOR_SAMSUNG_IN_APP_PURCHASE, 1).show();
      this.mErrorVo.setError(64534, getString(R.string.IDS_SAPPS_POP_AN_INVALID_VALUE_HAS_BEEN_PROVIDED_FOR_SAMSUNG_IN_APP_PURCHASE));
      finish();
    }
  }
  
  public void onSucceedInitIap()
  {
    this.mSamsungIapHelper.startPaymentActivity(this, 1, this.mItemId);
  }
  
  protected void succeedBind()
  {
    if (this.mSamsungIapHelper != null) {
      this.mSamsungIapHelper.safeInitIap(this, this.mShowErrorDialog);
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\samsung\android\sdk\iap\lib\activity\PaymentActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */