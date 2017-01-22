package com.samsung.android.sdk.iap.lib.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import com.samsung.android.sdk.iap.lib.R.layout;
import com.samsung.android.sdk.iap.lib.R.string;
import com.samsung.android.sdk.iap.lib.R.style;
import com.samsung.android.sdk.iap.lib.helper.SamsungIapHelper;
import com.samsung.android.sdk.iap.lib.listener.OnGetInboxListener;
import com.samsung.android.sdk.iap.lib.listener.OnGetItemListener;
import com.samsung.android.sdk.iap.lib.listener.OnIapBindListener;
import com.samsung.android.sdk.iap.lib.listener.OnPaymentListener;
import com.samsung.android.sdk.iap.lib.vo.ErrorVo;
import com.samsung.android.sdk.iap.lib.vo.InboxVo;
import com.samsung.android.sdk.iap.lib.vo.ItemVo;
import com.samsung.android.sdk.iap.lib.vo.PurchaseVo;
import java.util.ArrayList;

public abstract class BaseActivity
  extends Activity
{
  private static final String TAG = BaseActivity.class.getSimpleName();
  protected ErrorVo mErrorVo = new ErrorVo();
  private int mIapMode = 0;
  protected ArrayList<InboxVo> mInbox = null;
  protected ArrayList<ItemVo> mItemList = null;
  private Dialog mProgressDialog = null;
  protected PurchaseVo mPurchaseVo = null;
  SamsungIapHelper mSamsungIapHelper = null;
  protected boolean mShowErrorDialog = true;
  protected boolean mShowSuccessDialog = true;
  
  public void bindIapService()
  {
    Log.i(TAG, "start Bind... ");
    this.mSamsungIapHelper.bindIapService(new OnIapBindListener()
    {
      public void onBindIapFinished(int paramAnonymousInt)
      {
        Log.i(BaseActivity.TAG, "Binding OK... ");
        if (paramAnonymousInt == 0) {
          BaseActivity.this.succeedBind();
        }
        for (;;)
        {
          return;
          BaseActivity.this.mErrorVo.setError(64534, BaseActivity.this.getString(R.string.IDS_SAPPS_POP_YOUR_PURCHASE_VIA_SAMSUNG_IN_APP_PURCHASE_IS_INVALID_A_FAKE_APPLICATION_HAS_BEEN_DETECTED_CHECK_THE_APP_MSG));
          BaseActivity.this.mSamsungIapHelper.showIapDialogIfNeeded(BaseActivity.this, BaseActivity.this.getString(R.string.IDS_SAPPS_POP_SAMSUNG_IN_APP_PURCHASE), BaseActivity.this.getString(R.string.IDS_SAPPS_POP_UNKNOWN_ERROR_OCCURRED) + "[Lib_Bind]", true, null, BaseActivity.this.mShowErrorDialog);
        }
      }
    });
  }
  
  public boolean checkIapPackage()
  {
    boolean bool = true;
    if (true == this.mSamsungIapHelper.isInstalledIapPackage(this))
    {
      if (true == this.mSamsungIapHelper.isValidIapPackage(this)) {
        return bool;
      }
      this.mErrorVo.setError(64534, getString(R.string.IDS_SAPPS_POP_YOUR_PURCHASE_VIA_SAMSUNG_IN_APP_PURCHASE_IS_INVALID_A_FAKE_APPLICATION_HAS_BEEN_DETECTED_CHECK_THE_APP_MSG));
      this.mSamsungIapHelper.showIapDialogIfNeeded(this, getString(R.string.IDS_SAPPS_POP_SAMSUNG_IN_APP_PURCHASE), getString(R.string.IDS_SAPPS_POP_YOUR_PURCHASE_VIA_SAMSUNG_IN_APP_PURCHASE_IS_INVALID_A_FAKE_APPLICATION_HAS_BEEN_DETECTED_CHECK_THE_APP_MSG), true, null, this.mShowErrorDialog);
    }
    for (;;)
    {
      bool = false;
      break;
      this.mSamsungIapHelper.installIapPackage(this);
    }
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    Object localObject = getIntent();
    if ((localObject != null) && (((Intent)localObject).getExtras() != null)) {
      this.mIapMode = ((Intent)localObject).getExtras().getInt("IapMode", 0);
    }
    this.mSamsungIapHelper = SamsungIapHelper.getInstance(this, this.mIapMode);
    try
    {
      localObject = new android/app/Dialog;
      ((Dialog)localObject).<init>(this, R.style.Theme_Empty);
      this.mProgressDialog = ((Dialog)localObject);
      this.mProgressDialog.setContentView(R.layout.progress_dialog);
      this.mProgressDialog.setCancelable(false);
      this.mProgressDialog.show();
      super.onCreate(paramBundle);
      return;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        localException.printStackTrace();
      }
    }
  }
  
  protected void onDestroy()
  {
    try
    {
      if (this.mProgressDialog != null)
      {
        this.mProgressDialog.dismiss();
        this.mProgressDialog = null;
      }
      if (this.mSamsungIapHelper != null)
      {
        Object localObject = this.mSamsungIapHelper.getOnGetInboxListener();
        if (localObject != null) {
          ((OnGetInboxListener)localObject).onGetItemInbox(this.mErrorVo, this.mInbox);
        }
        localObject = this.mSamsungIapHelper.getOnGetItemListener();
        if (localObject != null) {
          ((OnGetItemListener)localObject).onGetItem(this.mErrorVo, this.mItemList);
        }
        localObject = this.mSamsungIapHelper.getOnPaymentListener();
        if (localObject != null) {
          ((OnPaymentListener)localObject).onPayment(this.mErrorVo, this.mPurchaseVo);
        }
        this.mSamsungIapHelper.removeAllListener();
        this.mSamsungIapHelper.dispose();
        this.mSamsungIapHelper = null;
      }
      super.onDestroy();
      return;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        localException.printStackTrace();
      }
    }
  }
  
  public void setErrorVo(ErrorVo paramErrorVo)
  {
    this.mErrorVo = paramErrorVo;
  }
  
  public void setInbox(ArrayList<InboxVo> paramArrayList)
  {
    this.mInbox = paramArrayList;
  }
  
  public void setItemList(ArrayList<ItemVo> paramArrayList)
  {
    this.mItemList = paramArrayList;
  }
  
  public void setPurchaseVo(PurchaseVo paramPurchaseVo)
  {
    this.mPurchaseVo = paramPurchaseVo;
  }
  
  abstract void succeedBind();
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\samsung\android\sdk\iap\lib\activity\BaseActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */