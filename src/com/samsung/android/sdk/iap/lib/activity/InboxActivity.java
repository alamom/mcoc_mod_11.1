package com.samsung.android.sdk.iap.lib.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import com.samsung.android.sdk.iap.lib.R.string;
import com.samsung.android.sdk.iap.lib.helper.SamsungIapHelper;
import com.samsung.android.sdk.iap.lib.vo.ErrorVo;

public class InboxActivity
  extends BaseActivity
{
  private static final String TAG = InboxActivity.class.getSimpleName();
  private String mEndDate = "";
  private int mEndNum = 0;
  private int mInboxApiType = 0;
  private String mItemIds = "";
  private String mStartDate = "";
  private int mStartNum = 0;
  
  private void showWrongParamMsgAndFinish()
  {
    Toast.makeText(this, R.string.IDS_SAPPS_POP_AN_INVALID_VALUE_HAS_BEEN_PROVIDED_FOR_SAMSUNG_IN_APP_PURCHASE, 1).show();
    this.mErrorVo.setError(64534, getString(R.string.IDS_SAPPS_POP_AN_INVALID_VALUE_HAS_BEEN_PROVIDED_FOR_SAMSUNG_IN_APP_PURCHASE));
    finish();
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
        bindIapService();
      }
      else
      {
        this.mErrorVo.setError(1, getString(R.string.IDS_SAPPS_POP_PAYMENT_CANCELLED));
        this.mSamsungIapHelper.showIapDialogIfNeeded(this, getString(R.string.IDS_SAPPS_POP_SAMSUNG_IN_APP_PURCHASE), getString(R.string.IDS_SAPPS_POP_PAYMENT_CANCELLED), true, null, this.mShowErrorDialog);
      }
    }
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    paramBundle = getIntent();
    if ((paramBundle != null) && (paramBundle.getExtras() != null))
    {
      paramBundle = paramBundle.getExtras();
      this.mInboxApiType = paramBundle.getInt("OpenApiType", -1);
      if (this.mInboxApiType == 1) {
        if (paramBundle.containsKey("ItemIds"))
        {
          this.mItemIds = paramBundle.getString("ItemIds");
          this.mShowErrorDialog = paramBundle.getBoolean("ShowErrorDialog", true);
        }
      }
    }
    for (;;)
    {
      if (true == checkIapPackage()) {
        this.mSamsungIapHelper.startAccountActivity(this);
      }
      return;
      showWrongParamMsgAndFinish();
      continue;
      if (this.mInboxApiType == 0)
      {
        if ((paramBundle.containsKey("StartNum")) && (paramBundle.containsKey("EndNum")) && (paramBundle.containsKey("StartDate")) && (paramBundle.containsKey("EndDate")))
        {
          this.mStartNum = paramBundle.getInt("StartNum");
          this.mEndNum = paramBundle.getInt("EndNum");
          this.mStartDate = paramBundle.getString("StartDate");
          this.mEndDate = paramBundle.getString("EndDate");
          this.mShowErrorDialog = paramBundle.getBoolean("ShowErrorDialog", true);
        }
        else
        {
          showWrongParamMsgAndFinish();
        }
      }
      else
      {
        showWrongParamMsgAndFinish();
        continue;
        showWrongParamMsgAndFinish();
      }
    }
  }
  
  protected void succeedBind()
  {
    if (this.mInboxApiType == 1) {
      this.mSamsungIapHelper.safeGetItemInboxTask(this, this.mItemIds, this.mShowErrorDialog);
    }
    for (;;)
    {
      return;
      this.mSamsungIapHelper.safeGetItemInboxTask(this, this.mStartNum, this.mEndNum, this.mStartDate, this.mEndDate, this.mShowErrorDialog);
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\samsung\android\sdk\iap\lib\activity\InboxActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */