package com.amazon.device.iap.cpt;

import com.amazon.cptplugins.validation.Validate;
import com.amazon.device.iap.PurchasingListener;
import com.amazon.device.iap.PurchasingService;
import com.amazon.device.iap.model.FulfillmentResult;
import com.amazon.device.iap.model.ProductDataResponse;
import com.amazon.device.iap.model.PurchaseUpdatesResponse;
import com.amazon.device.iap.model.RequestId;
import com.amazon.device.iap.model.UserDataResponse;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

public class AmazonIapV2Impl
  implements PurchasingListener, AmazonIapV2
{
  public static final String BUILD_NUMBER = "9999.9.9.9";
  public static final String VERSION = "1.0.0";
  private static boolean initialized = false;
  private AmazonIapV2JavaController controller;
  private final Logger logger = Logger.getLogger("AmazonIapV2");
  
  public AmazonIapV2Impl()
  {
    this.logger.info("Start Amazon IAP V2 - Version 1.0.0, Build 9999.9.9.9");
  }
  
  private void checkInited()
  {
    if (!initialized) {
      throw new IllegalArgumentException("Iap SDK not properly intialized.");
    }
  }
  
  public RequestOutput getProductData(SkusInput paramSkusInput)
  {
    this.logger.info("getProductData called");
    checkInited();
    Validate.notNull(paramSkusInput, "Skus for getProductData");
    Validate.notNull(paramSkusInput.getSkus(), "Sku for initiateGetProductDataRequest");
    HashSet localHashSet = new HashSet();
    localHashSet.addAll(paramSkusInput.getSkus());
    paramSkusInput = new StringBuffer("getProductData for skus:");
    Iterator localIterator = localHashSet.iterator();
    while (localIterator.hasNext()) {
      paramSkusInput.append((String)localIterator.next()).append(",");
    }
    this.logger.info(paramSkusInput.toString());
    paramSkusInput = new RequestOutput();
    paramSkusInput.setRequestId(PurchasingService.getProductData(localHashSet).toString());
    return paramSkusInput;
  }
  
  public RequestOutput getPurchaseUpdates(ResetInput paramResetInput)
  {
    this.logger.info("getPurchaseUpdates called");
    checkInited();
    if ((paramResetInput != null) && (Boolean.TRUE.equals(paramResetInput.getReset()))) {}
    for (boolean bool = true;; bool = false)
    {
      this.logger.info("getPurchases with reset:" + bool);
      paramResetInput = new RequestOutput();
      paramResetInput.setRequestId(PurchasingService.getPurchaseUpdates(bool).toString());
      return paramResetInput;
    }
  }
  
  public RequestOutput getUserData()
  {
    this.logger.info("getUserData called");
    checkInited();
    RequestOutput localRequestOutput = new RequestOutput();
    localRequestOutput.setRequestId(PurchasingService.getUserData().toString());
    return localRequestOutput;
  }
  
  public void notifyFulfillment(NotifyFulfillmentInput paramNotifyFulfillmentInput)
  {
    this.logger.info("notifyFulfillment called");
    checkInited();
    Validate.notNull(paramNotifyFulfillmentInput, "receiptId for notifyFulfillment");
    Validate.notNull(paramNotifyFulfillmentInput.getReceiptId(), "receiptId for notifyFulfillment");
    try
    {
      FulfillmentResult localFulfillmentResult = FulfillmentResult.valueOf(paramNotifyFulfillmentInput.getFulfillmentResult());
      PurchasingService.notifyFulfillment(paramNotifyFulfillmentInput.getReceiptId(), localFulfillmentResult);
      return;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        this.logger.warning("Unsupported fulfillment result [" + paramNotifyFulfillmentInput.getFulfillmentResult() + "], values must be :[" + Arrays.toString(FulfillmentResult.values()) + "]");
      }
    }
  }
  
  public void onProductDataResponse(ProductDataResponse paramProductDataResponse)
  {
    this.logger.info("onProductDataResponse:" + paramProductDataResponse.toString());
    paramProductDataResponse = IapSdkConversionUtil.convertResponse(paramProductDataResponse);
    this.logger.info("GetProductDataResult:" + paramProductDataResponse.getRequestId() + "/" + paramProductDataResponse.getStatus() + "/" + paramProductDataResponse.getProductDataMap().size());
    this.controller.fireGetProductDataResponse(paramProductDataResponse);
  }
  
  public void onPurchaseResponse(com.amazon.device.iap.model.PurchaseResponse paramPurchaseResponse)
  {
    this.logger.info("onPurchaseResponse:" + paramPurchaseResponse.toString());
    PurchaseResponse localPurchaseResponse = IapSdkConversionUtil.convertResponse(paramPurchaseResponse);
    Logger localLogger = this.logger;
    StringBuilder localStringBuilder = new StringBuilder().append("PurchaseResult:").append(localPurchaseResponse.getRequestId()).append("/").append(localPurchaseResponse.getStatus()).append("/");
    if (localPurchaseResponse.getPurchaseReceipt() != null) {}
    for (paramPurchaseResponse = localPurchaseResponse.getPurchaseReceipt().getReceiptId();; paramPurchaseResponse = "n/a")
    {
      localLogger.info(paramPurchaseResponse);
      this.controller.firePurchaseResponse(localPurchaseResponse);
      return;
    }
  }
  
  public void onPurchaseUpdatesResponse(PurchaseUpdatesResponse paramPurchaseUpdatesResponse)
  {
    this.logger.info("onPurchaseUpdatesResponse:" + paramPurchaseUpdatesResponse.toString());
    paramPurchaseUpdatesResponse = IapSdkConversionUtil.convertResponse(paramPurchaseUpdatesResponse);
    this.logger.info("GetPurchasesResult:" + paramPurchaseUpdatesResponse.getRequestId() + "/" + paramPurchaseUpdatesResponse.getStatus() + "/" + paramPurchaseUpdatesResponse.getReceipts().size());
    this.controller.fireGetPurchaseUpdatesResponse(paramPurchaseUpdatesResponse);
  }
  
  public void onUserDataResponse(UserDataResponse paramUserDataResponse)
  {
    this.logger.info("onUserDataResponse:" + paramUserDataResponse.toString());
    GetUserDataResponse localGetUserDataResponse = IapSdkConversionUtil.convertResponse(paramUserDataResponse);
    Logger localLogger = this.logger;
    StringBuilder localStringBuilder = new StringBuilder().append("GetUserDataResult:").append(localGetUserDataResponse.getRequestId()).append("/").append(localGetUserDataResponse.getStatus()).append("/");
    if (localGetUserDataResponse.getAmazonUserData() != null) {}
    for (paramUserDataResponse = localGetUserDataResponse.getAmazonUserData().getUserId();; paramUserDataResponse = "n/a")
    {
      localLogger.info(paramUserDataResponse);
      this.controller.fireGetUserDataResponse(localGetUserDataResponse);
      return;
    }
  }
  
  public RequestOutput purchase(SkuInput paramSkuInput)
  {
    this.logger.info("purchase called");
    checkInited();
    Validate.notNull(paramSkuInput, "Sku for purchase");
    Validate.notNull(paramSkuInput.getSku(), "Sku for purchase");
    this.logger.info("purchase sku:" + paramSkuInput.getSku());
    RequestOutput localRequestOutput = new RequestOutput();
    localRequestOutput.setRequestId(PurchasingService.purchase(paramSkuInput.getSku()).toString());
    return localRequestOutput;
  }
  
  public void setAmazonIapV2JavaController(AmazonIapV2JavaController paramAmazonIapV2JavaController)
  {
    PurchasingService.registerListener(paramAmazonIapV2JavaController.getContext(), this);
    this.controller = paramAmazonIapV2JavaController;
    initialized = true;
    this.logger.info("cpt iap plugin initialized");
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\amazon\device\iap\cpt\AmazonIapV2Impl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */