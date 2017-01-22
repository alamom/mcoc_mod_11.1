package com.amazon.device.iap.cpt;

public abstract interface AmazonIapV2
{
  public abstract RequestOutput getProductData(SkusInput paramSkusInput);
  
  public abstract RequestOutput getPurchaseUpdates(ResetInput paramResetInput);
  
  public abstract RequestOutput getUserData();
  
  public abstract void notifyFulfillment(NotifyFulfillmentInput paramNotifyFulfillmentInput);
  
  public abstract RequestOutput purchase(SkuInput paramSkuInput);
  
  public abstract void setAmazonIapV2JavaController(AmazonIapV2JavaController paramAmazonIapV2JavaController);
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\amazon\device\iap\cpt\AmazonIapV2.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */