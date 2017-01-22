package com.amazon.device.iap.cpt;

import android.app.Activity;
import android.content.Context;
import com.amazon.cptplugins.AndroidResources;
import com.amazon.cptplugins.CrossPlatformTool;
import com.amazon.cptplugins.concurrent.CallbackHandler;
import com.amazon.cptplugins.concurrent.SdkCallbackListener;
import com.amazon.cptplugins.concurrent.SdkEventListener;

public abstract interface AmazonIapV2JavaController
  extends CallbackHandler
{
  public abstract void fireGetProductDataResponse(GetProductDataResponse paramGetProductDataResponse);
  
  public abstract void fireGetPurchaseUpdatesResponse(GetPurchaseUpdatesResponse paramGetPurchaseUpdatesResponse);
  
  public abstract void fireGetUserDataResponse(GetUserDataResponse paramGetUserDataResponse);
  
  public abstract void firePurchaseResponse(PurchaseResponse paramPurchaseResponse);
  
  public abstract Context getContext();
  
  public abstract CrossPlatformTool getCrossPlatformTool();
  
  public abstract Activity getCurrentAndroidActivity();
  
  public abstract String getProductData(String paramString);
  
  public abstract String getPurchaseUpdates(String paramString);
  
  public abstract String getUserData(String paramString);
  
  public abstract String notifyFulfillment(String paramString);
  
  public abstract String purchase(String paramString);
  
  public abstract boolean runningOnUiThread();
  
  public abstract void setAndroidResources(AndroidResources paramAndroidResources);
  
  public abstract void setContext(Context paramContext);
  
  public abstract void setSdkCallbackListener(SdkCallbackListener paramSdkCallbackListener);
  
  public abstract void setSdkEventListener(SdkEventListener paramSdkEventListener);
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\amazon\device\iap\cpt\AmazonIapV2JavaController.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */