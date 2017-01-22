package com.samsung.android.sdk.iap.lib.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import com.samsung.android.sdk.iap.lib.R.string;
import com.samsung.android.sdk.iap.lib.helper.SamsungIapHelper;
import com.samsung.android.sdk.iap.lib.vo.ErrorVo;

public class ItemActivity
  extends BaseActivity
{
  private static final String TAG = ItemActivity.class.getSimpleName();
  private int mEndNum = 15;
  private String mItemType = null;
  private int mStartNum = 1;
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    paramBundle = getIntent();
    if ((paramBundle != null) && (paramBundle.getExtras() != null) && (paramBundle.getExtras().containsKey("StartNum")) && (paramBundle.getExtras().containsKey("EndNum")) && (paramBundle.getExtras().containsKey("ItemType")))
    {
      paramBundle = paramBundle.getExtras();
      this.mStartNum = paramBundle.getInt("StartNum");
      this.mEndNum = paramBundle.getInt("EndNum");
      this.mItemType = paramBundle.getString("ItemType");
      this.mShowErrorDialog = paramBundle.getBoolean("ShowErrorDialog", true);
      if (checkIapPackage() == true) {
        bindIapService();
      }
    }
    for (;;)
    {
      return;
      Toast.makeText(this, R.string.IDS_SAPPS_POP_AN_INVALID_VALUE_HAS_BEEN_PROVIDED_FOR_SAMSUNG_IN_APP_PURCHASE, 1).show();
      this.mErrorVo.setError(64534, getString(R.string.IDS_SAPPS_POP_AN_INVALID_VALUE_HAS_BEEN_PROVIDED_FOR_SAMSUNG_IN_APP_PURCHASE));
      finish();
    }
  }
  
  protected void succeedBind()
  {
    this.mSamsungIapHelper.safeGetItemList(this, this.mStartNum, this.mEndNum, this.mItemType, this.mShowErrorDialog);
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\samsung\android\sdk\iap\lib\activity\ItemActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */