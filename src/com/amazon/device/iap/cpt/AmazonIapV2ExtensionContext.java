package com.amazon.device.iap.cpt;

import android.app.Activity;
import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.adobe.fre.FREObject;
import com.adobe.fre.FREWrongThreadException;
import com.amazon.cptplugins.AndroidResources;
import com.amazon.cptplugins.CrossPlatformTool;
import com.amazon.cptplugins.concurrent.CallbackResult;
import com.amazon.cptplugins.concurrent.SdkCallbackListener;
import com.amazon.cptplugins.concurrent.SdkEvent;
import com.amazon.cptplugins.concurrent.SdkEventListener;
import com.amazon.cptplugins.exceptions.jsonutils.AmazonError;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.HashMap;
import java.util.Map;

public class AmazonIapV2ExtensionContext
  extends FREContext
  implements SdkCallbackListener, SdkEventListener, AndroidResources
{
  private static final Gson GSON = new Gson();
  private AmazonIapV2JavaController controller;
  private Activity currentActivity;
  
  private FREObject createFREObjectFromString(String paramString)
  {
    try
    {
      paramString = FREObject.newObject(paramString);
      return paramString;
    }
    catch (FREWrongThreadException paramString)
    {
      for (;;)
      {
        paramString = null;
      }
    }
  }
  
  public void dispose() {}
  
  public void fireSdkEvent(String paramString)
  {
    SdkEvent localSdkEvent = (SdkEvent)GSON.fromJson(paramString, new TypeToken() {}.getType());
    paramString = new ServiceEventStatusEventMetadata(localSdkEvent.getEventId());
    dispatchStatusEventAsync(GSON.toJson(paramString), GSON.toJson(localSdkEvent.getResponse()));
  }
  
  public CrossPlatformTool getCrossPlatformTool()
  {
    return CrossPlatformTool.AIR;
  }
  
  public Activity getCurrentAndroidActivity()
  {
    if (this.controller.runningOnUiThread()) {
      this.currentActivity = getActivity();
    }
    return this.currentActivity;
  }
  
  public Map<String, FREFunction> getFunctions()
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("initializeAmazonIapV2ExtensionContext", new InitializeAmazonIapV2ExtensionContextFunction(null));
    localHashMap.put("getUserData", new GetUserDataFunction(null));
    localHashMap.put("purchase", new PurchaseFunction(null));
    localHashMap.put("getProductData", new GetProductDataFunction(null));
    localHashMap.put("getPurchaseUpdates", new GetPurchaseUpdatesFunction(null));
    localHashMap.put("notifyFulfillment", new NotifyFulfillmentFunction(null));
    return localHashMap;
  }
  
  public void handleSdkCallback(String paramString)
  {
    CallbackResult localCallbackResult = (CallbackResult)GSON.fromJson(paramString, new TypeToken() {}.getType());
    paramString = new AsyncResponseStatusEventMetadata(localCallbackResult.getCallerId());
    dispatchStatusEventAsync(GSON.toJson(paramString), GSON.toJson(localCallbackResult.getResponse()));
  }
  
  private class GetProductDataFunction
    implements FREFunction
  {
    private GetProductDataFunction() {}
    
    public FREObject call(FREContext paramFREContext, FREObject[] paramArrayOfFREObject)
    {
      try
      {
        AmazonIapV2ExtensionContext.this.getCurrentAndroidActivity();
        paramFREContext = paramArrayOfFREObject[0].getAsString();
        paramFREContext = FREObject.newObject(AmazonIapV2ExtensionContext.this.controller.getProductData(paramFREContext));
        return paramFREContext;
      }
      catch (Exception paramFREContext)
      {
        for (;;)
        {
          paramFREContext = AmazonIapV2ExtensionContext.this.createFREObjectFromString(AmazonIapV2ExtensionContext.GSON.toJson(new AmazonError(paramFREContext)));
        }
      }
    }
  }
  
  private class GetPurchaseUpdatesFunction
    implements FREFunction
  {
    private GetPurchaseUpdatesFunction() {}
    
    public FREObject call(FREContext paramFREContext, FREObject[] paramArrayOfFREObject)
    {
      try
      {
        AmazonIapV2ExtensionContext.this.getCurrentAndroidActivity();
        paramFREContext = paramArrayOfFREObject[0].getAsString();
        paramFREContext = FREObject.newObject(AmazonIapV2ExtensionContext.this.controller.getPurchaseUpdates(paramFREContext));
        return paramFREContext;
      }
      catch (Exception paramFREContext)
      {
        for (;;)
        {
          paramFREContext = AmazonIapV2ExtensionContext.this.createFREObjectFromString(AmazonIapV2ExtensionContext.GSON.toJson(new AmazonError(paramFREContext)));
        }
      }
    }
  }
  
  private class GetUserDataFunction
    implements FREFunction
  {
    private GetUserDataFunction() {}
    
    public FREObject call(FREContext paramFREContext, FREObject[] paramArrayOfFREObject)
    {
      try
      {
        AmazonIapV2ExtensionContext.this.getCurrentAndroidActivity();
        paramFREContext = FREObject.newObject(AmazonIapV2ExtensionContext.this.controller.getUserData(null));
        return paramFREContext;
      }
      catch (Exception paramFREContext)
      {
        for (;;)
        {
          paramFREContext = AmazonIapV2ExtensionContext.this.createFREObjectFromString(AmazonIapV2ExtensionContext.GSON.toJson(new AmazonError(paramFREContext)));
        }
      }
    }
  }
  
  private class InitializeAmazonIapV2ExtensionContextFunction
    implements FREFunction
  {
    private InitializeAmazonIapV2ExtensionContextFunction() {}
    
    public FREObject call(FREContext paramFREContext, FREObject[] paramArrayOfFREObject)
    {
      try
      {
        AmazonIapV2ExtensionContext.access$602(AmazonIapV2ExtensionContext.this, AmazonIapV2JavaControllerImpl.newInstance(AmazonIapV2ExtensionContext.this.getActivity()));
        AmazonIapV2ExtensionContext.this.controller.setSdkEventListener(AmazonIapV2ExtensionContext.this);
        AmazonIapV2ExtensionContext.this.controller.setSdkCallbackListener(AmazonIapV2ExtensionContext.this);
        AmazonIapV2ExtensionContext.this.controller.setAndroidResources(AmazonIapV2ExtensionContext.this);
        AmazonIapV2ExtensionContext.this.getCurrentAndroidActivity();
        paramFREContext = null;
      }
      catch (Exception paramFREContext)
      {
        for (;;)
        {
          paramFREContext = AmazonIapV2ExtensionContext.this.createFREObjectFromString(AmazonIapV2ExtensionContext.GSON.toJson(new AmazonError(paramFREContext)));
        }
      }
      return paramFREContext;
    }
  }
  
  private class NotifyFulfillmentFunction
    implements FREFunction
  {
    private NotifyFulfillmentFunction() {}
    
    public FREObject call(FREContext paramFREContext, FREObject[] paramArrayOfFREObject)
    {
      try
      {
        AmazonIapV2ExtensionContext.this.getCurrentAndroidActivity();
        paramFREContext = paramArrayOfFREObject[0].getAsString();
        paramFREContext = FREObject.newObject(AmazonIapV2ExtensionContext.this.controller.notifyFulfillment(paramFREContext));
        return paramFREContext;
      }
      catch (Exception paramFREContext)
      {
        for (;;)
        {
          paramFREContext = AmazonIapV2ExtensionContext.this.createFREObjectFromString(AmazonIapV2ExtensionContext.GSON.toJson(new AmazonError(paramFREContext)));
        }
      }
    }
  }
  
  private class PurchaseFunction
    implements FREFunction
  {
    private PurchaseFunction() {}
    
    public FREObject call(FREContext paramFREContext, FREObject[] paramArrayOfFREObject)
    {
      try
      {
        AmazonIapV2ExtensionContext.this.getCurrentAndroidActivity();
        paramFREContext = paramArrayOfFREObject[0].getAsString();
        paramFREContext = FREObject.newObject(AmazonIapV2ExtensionContext.this.controller.purchase(paramFREContext));
        return paramFREContext;
      }
      catch (Exception paramFREContext)
      {
        for (;;)
        {
          paramFREContext = AmazonIapV2ExtensionContext.this.createFREObjectFromString(AmazonIapV2ExtensionContext.GSON.toJson(new AmazonError(paramFREContext)));
        }
      }
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\amazon\device\iap\cpt\AmazonIapV2ExtensionContext.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */