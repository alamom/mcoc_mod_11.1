package com.explodingbarrel.iap;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class PurchaseActivity
  extends Activity
{
  public static final String BUNDLE_PAYLOAD = "type";
  public static final String BUNDLE_PRODUCT = "product";
  public static final String BUNDLE_TYPE = "type";
  public static final String TAG = "PurchaseActivity";
  String payLoad = "";
  String productId = "";
  String type = "";
  
  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    Log.d("PurchaseActivity", "onActivityResult(" + paramInt1 + "," + paramInt2 + "," + paramIntent);
    if (!Manager.HandleActivityResult(paramInt1, paramInt2, paramIntent)) {
      super.onActivityResult(paramInt1, paramInt2, paramIntent);
    }
    for (;;)
    {
      return;
      Log.d("PurchaseActivity", "onActivityResult handled by IABUtil.");
    }
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    paramBundle = getIntent().getExtras();
    this.productId = paramBundle.getString("product");
    this.type = paramBundle.getString("type");
    this.payLoad = paramBundle.getString("type");
    Log.d("PurchaseActivity", "onActivityResult(" + this.productId + "," + this.type + "," + this.payLoad);
  }
  
  protected void onStart()
  {
    super.onStart();
    Manager.LaunchPurchaseFlow(this, this.productId, this.type, this.payLoad);
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\explodingbarrel\iap\PurchaseActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */