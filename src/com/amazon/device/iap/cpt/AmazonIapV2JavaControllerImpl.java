package com.amazon.device.iap.cpt;

import android.app.Activity;
import android.content.Context;
import android.os.Looper;
import com.amazon.cptplugins.AndroidResources;
import com.amazon.cptplugins.CrossPlatformTool;
import com.amazon.cptplugins.concurrent.SdkCallbackListener;
import com.amazon.cptplugins.concurrent.SdkEvent;
import com.amazon.cptplugins.concurrent.SdkEventListener;
import com.amazon.cptplugins.exceptions.jsonutils.AmazonError;
import com.amazon.cptplugins.json.JsonSerializer;
import com.amazon.cptplugins.json.JsonSerializerImpl;
import com.amazon.cptplugins.validation.Assert;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AmazonIapV2JavaControllerImpl
  implements AmazonIapV2JavaController
{
  private static final int MAX_BLOCKED_CONCURRENT_ASYNC_CALLS = 10;
  private static final String TAG = "AmazonIapV2JavaControllerImpl";
  private static volatile AndroidResources androidResources = null;
  private final AmazonIapV2 amazonIapV2 = new AmazonIapV2Impl();
  private volatile Context context = null;
  private final ExecutorService executor;
  private final JsonSerializer json;
  private volatile SdkCallbackListener sdkCallbackListener = null;
  private volatile SdkEventListener sdkEventListener = null;
  
  private AmazonIapV2JavaControllerImpl(JsonSerializer paramJsonSerializer, ExecutorService paramExecutorService)
  {
    this.json = paramJsonSerializer;
    this.executor = paramExecutorService;
  }
  
  private static Activity delegateGetCurrentAndroidActivity()
  {
    Assert.notNull(androidResources, "androidResoures");
    return androidResources.getCurrentAndroidActivity();
  }
  
  public static AmazonIapV2JavaControllerImpl newInstance()
  {
    AmazonIapV2JavaControllerImpl localAmazonIapV2JavaControllerImpl = new AmazonIapV2JavaControllerImpl(new JsonSerializerImpl(), Executors.newFixedThreadPool(10));
    localAmazonIapV2JavaControllerImpl.amazonIapV2.setAmazonIapV2JavaController(localAmazonIapV2JavaControllerImpl);
    return localAmazonIapV2JavaControllerImpl;
  }
  
  public static AmazonIapV2JavaControllerImpl newInstance(Context paramContext)
  {
    AmazonIapV2JavaControllerImpl localAmazonIapV2JavaControllerImpl = new AmazonIapV2JavaControllerImpl(new JsonSerializerImpl(), Executors.newFixedThreadPool(10));
    localAmazonIapV2JavaControllerImpl.setContext(paramContext);
    localAmazonIapV2JavaControllerImpl.amazonIapV2.setAmazonIapV2JavaController(localAmazonIapV2JavaControllerImpl);
    return localAmazonIapV2JavaControllerImpl;
  }
  
  public void fireGetProductDataResponse(GetProductDataResponse paramGetProductDataResponse)
  {
    paramGetProductDataResponse = new SdkEvent("getProductDataResponse", paramGetProductDataResponse);
    paramGetProductDataResponse = this.json.toJson(paramGetProductDataResponse);
    if (this.sdkEventListener != null) {
      this.sdkEventListener.fireSdkEvent(paramGetProductDataResponse);
    }
  }
  
  public void fireGetPurchaseUpdatesResponse(GetPurchaseUpdatesResponse paramGetPurchaseUpdatesResponse)
  {
    paramGetPurchaseUpdatesResponse = new SdkEvent("getPurchaseUpdatesResponse", paramGetPurchaseUpdatesResponse);
    paramGetPurchaseUpdatesResponse = this.json.toJson(paramGetPurchaseUpdatesResponse);
    if (this.sdkEventListener != null) {
      this.sdkEventListener.fireSdkEvent(paramGetPurchaseUpdatesResponse);
    }
  }
  
  public void fireGetUserDataResponse(GetUserDataResponse paramGetUserDataResponse)
  {
    paramGetUserDataResponse = new SdkEvent("getUserDataResponse", paramGetUserDataResponse);
    paramGetUserDataResponse = this.json.toJson(paramGetUserDataResponse);
    if (this.sdkEventListener != null) {
      this.sdkEventListener.fireSdkEvent(paramGetUserDataResponse);
    }
  }
  
  public void firePurchaseResponse(PurchaseResponse paramPurchaseResponse)
  {
    paramPurchaseResponse = new SdkEvent("purchaseResponse", paramPurchaseResponse);
    paramPurchaseResponse = this.json.toJson(paramPurchaseResponse);
    if (this.sdkEventListener != null) {
      this.sdkEventListener.fireSdkEvent(paramPurchaseResponse);
    }
  }
  
  public Context getContext()
  {
    return this.context;
  }
  
  public CrossPlatformTool getCrossPlatformTool()
  {
    return androidResources.getCrossPlatformTool();
  }
  
  public Activity getCurrentAndroidActivity()
  {
    return delegateGetCurrentAndroidActivity();
  }
  
  public String getProductData(String paramString)
  {
    try
    {
      Object localObject = new com/amazon/device/iap/cpt/AmazonIapV2JavaControllerImpl$2;
      ((2)localObject).<init>(this);
      localObject = ((2)localObject).getType();
      paramString = (SkusInput)this.json.fromJson(paramString, (Type)localObject);
      paramString = this.amazonIapV2.getProductData(paramString);
      paramString = this.json.toJson(paramString);
      return paramString;
    }
    catch (Exception paramString)
    {
      for (;;)
      {
        paramString = this.json.toJson(new AmazonError(paramString));
      }
    }
  }
  
  public String getPurchaseUpdates(String paramString)
  {
    try
    {
      Object localObject = new com/amazon/device/iap/cpt/AmazonIapV2JavaControllerImpl$3;
      ((3)localObject).<init>(this);
      localObject = ((3)localObject).getType();
      paramString = (ResetInput)this.json.fromJson(paramString, (Type)localObject);
      paramString = this.amazonIapV2.getPurchaseUpdates(paramString);
      paramString = this.json.toJson(paramString);
      return paramString;
    }
    catch (Exception paramString)
    {
      for (;;)
      {
        paramString = this.json.toJson(new AmazonError(paramString));
      }
    }
  }
  
  public String getUserData(String paramString)
  {
    try
    {
      paramString = this.amazonIapV2.getUserData();
      paramString = this.json.toJson(paramString);
      return paramString;
    }
    catch (Exception paramString)
    {
      for (;;)
      {
        paramString = this.json.toJson(new AmazonError(paramString));
      }
    }
  }
  
  public void handleSdkCallback(String paramString)
  {
    if (this.sdkCallbackListener != null) {
      this.sdkCallbackListener.handleSdkCallback(paramString);
    }
  }
  
  public String notifyFulfillment(String paramString)
  {
    try
    {
      Object localObject = new com/amazon/device/iap/cpt/AmazonIapV2JavaControllerImpl$4;
      ((4)localObject).<init>(this);
      localObject = ((4)localObject).getType();
      paramString = (NotifyFulfillmentInput)this.json.fromJson(paramString, (Type)localObject);
      this.amazonIapV2.notifyFulfillment(paramString);
      paramString = "{}";
    }
    catch (Exception paramString)
    {
      for (;;)
      {
        paramString = this.json.toJson(new AmazonError(paramString));
      }
    }
    return paramString;
  }
  
  public String purchase(String paramString)
  {
    try
    {
      Object localObject = new com/amazon/device/iap/cpt/AmazonIapV2JavaControllerImpl$1;
      ((1)localObject).<init>(this);
      localObject = ((1)localObject).getType();
      paramString = (SkuInput)this.json.fromJson(paramString, (Type)localObject);
      paramString = this.amazonIapV2.purchase(paramString);
      paramString = this.json.toJson(paramString);
      return paramString;
    }
    catch (Exception paramString)
    {
      for (;;)
      {
        paramString = this.json.toJson(new AmazonError(paramString));
      }
    }
  }
  
  public boolean runningOnUiThread()
  {
    if (Looper.myLooper() == Looper.getMainLooper()) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public void setAndroidResources(AndroidResources paramAndroidResources)
  {
    androidResources = paramAndroidResources;
  }
  
  public void setContext(Context paramContext)
  {
    this.context = paramContext;
  }
  
  public void setSdkCallbackListener(SdkCallbackListener paramSdkCallbackListener)
  {
    this.sdkCallbackListener = paramSdkCallbackListener;
  }
  
  public void setSdkEventListener(SdkEventListener paramSdkEventListener)
  {
    this.sdkEventListener = paramSdkEventListener;
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\amazon\device\iap\cpt\AmazonIapV2JavaControllerImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */