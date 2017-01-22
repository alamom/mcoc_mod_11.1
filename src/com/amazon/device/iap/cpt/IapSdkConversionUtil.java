package com.amazon.device.iap.cpt;

import com.amazon.device.iap.model.Product;
import com.amazon.device.iap.model.ProductDataResponse;
import com.amazon.device.iap.model.ProductDataResponse.RequestStatus;
import com.amazon.device.iap.model.ProductType;
import com.amazon.device.iap.model.PurchaseResponse.RequestStatus;
import com.amazon.device.iap.model.PurchaseUpdatesResponse;
import com.amazon.device.iap.model.PurchaseUpdatesResponse.RequestStatus;
import com.amazon.device.iap.model.Receipt;
import com.amazon.device.iap.model.RequestId;
import com.amazon.device.iap.model.UserData;
import com.amazon.device.iap.model.UserDataResponse;
import com.amazon.device.iap.model.UserDataResponse.RequestStatus;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public final class IapSdkConversionUtil
{
  public static AmazonUserData convertData(UserData paramUserData)
  {
    if (paramUserData == null) {}
    AmazonUserData localAmazonUserData;
    for (paramUserData = null;; paramUserData = localAmazonUserData)
    {
      return paramUserData;
      localAmazonUserData = new AmazonUserData();
      localAmazonUserData.setMarketplace(paramUserData.getMarketplace());
      localAmazonUserData.setUserId(paramUserData.getUserId());
    }
  }
  
  public static ProductData convertData(Product paramProduct)
  {
    ProductData localProductData = new ProductData();
    localProductData.setDescription(paramProduct.getDescription());
    localProductData.setPrice(paramProduct.getPrice());
    localProductData.setProductType(paramProduct.getProductType().toString());
    localProductData.setSku(paramProduct.getSku());
    localProductData.setSmallIconUrl(paramProduct.getSmallIconUrl());
    localProductData.setTitle(paramProduct.getTitle());
    return localProductData;
  }
  
  public static PurchaseReceipt convertData(Receipt paramReceipt)
  {
    if (paramReceipt == null) {}
    PurchaseReceipt localPurchaseReceipt;
    for (paramReceipt = null;; paramReceipt = localPurchaseReceipt)
    {
      return paramReceipt;
      localPurchaseReceipt = new PurchaseReceipt();
      if (paramReceipt.getCancelDate() != null) {
        localPurchaseReceipt.setCancelDate(Long.valueOf(paramReceipt.getCancelDate().getTime()));
      }
      if (paramReceipt.getPurchaseDate() != null) {
        localPurchaseReceipt.setPurchaseDate(Long.valueOf(paramReceipt.getPurchaseDate().getTime()));
      }
      localPurchaseReceipt.setProductType(paramReceipt.getProductType().toString());
      localPurchaseReceipt.setReceiptId(paramReceipt.getReceiptId());
      localPurchaseReceipt.setSku(paramReceipt.getSku());
    }
  }
  
  public static GetProductDataResponse convertResponse(ProductDataResponse paramProductDataResponse)
  {
    GetProductDataResponse localGetProductDataResponse = new GetProductDataResponse();
    HashMap localHashMap = new HashMap();
    if (paramProductDataResponse.getProductData() != null)
    {
      localObject = paramProductDataResponse.getProductData().values().iterator();
      while (((Iterator)localObject).hasNext())
      {
        Product localProduct = (Product)((Iterator)localObject).next();
        localHashMap.put(localProduct.getSku(), convertData(localProduct));
      }
    }
    localGetProductDataResponse.setProductDataMap(localHashMap);
    localGetProductDataResponse.setRequestId(paramProductDataResponse.getRequestId().toString());
    localGetProductDataResponse.setStatus(paramProductDataResponse.getRequestStatus().toString());
    Object localObject = new ArrayList();
    if (paramProductDataResponse.getUnavailableSkus() != null) {
      ((List)localObject).addAll(paramProductDataResponse.getUnavailableSkus());
    }
    localGetProductDataResponse.setUnavailableSkus((List)localObject);
    return localGetProductDataResponse;
  }
  
  public static GetPurchaseUpdatesResponse convertResponse(PurchaseUpdatesResponse paramPurchaseUpdatesResponse)
  {
    GetPurchaseUpdatesResponse localGetPurchaseUpdatesResponse = new GetPurchaseUpdatesResponse();
    localGetPurchaseUpdatesResponse.setAmazonUserData(convertData(paramPurchaseUpdatesResponse.getUserData()));
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = paramPurchaseUpdatesResponse.getReceipts().iterator();
    while (localIterator.hasNext())
    {
      Receipt localReceipt = (Receipt)localIterator.next();
      if (localReceipt != null) {
        localArrayList.add(convertData(localReceipt));
      }
    }
    localGetPurchaseUpdatesResponse.setReceipts(localArrayList);
    localGetPurchaseUpdatesResponse.setRequestId(paramPurchaseUpdatesResponse.getRequestId().toString());
    localGetPurchaseUpdatesResponse.setStatus(paramPurchaseUpdatesResponse.getRequestStatus().toString());
    localGetPurchaseUpdatesResponse.setHasMore(Boolean.valueOf(paramPurchaseUpdatesResponse.hasMore()));
    return localGetPurchaseUpdatesResponse;
  }
  
  public static GetUserDataResponse convertResponse(UserDataResponse paramUserDataResponse)
  {
    GetUserDataResponse localGetUserDataResponse = new GetUserDataResponse();
    localGetUserDataResponse.setAmazonUserData(convertData(paramUserDataResponse.getUserData()));
    localGetUserDataResponse.setRequestId(paramUserDataResponse.getRequestId().toString());
    localGetUserDataResponse.setStatus(paramUserDataResponse.getRequestStatus().toString());
    return localGetUserDataResponse;
  }
  
  public static PurchaseResponse convertResponse(com.amazon.device.iap.model.PurchaseResponse paramPurchaseResponse)
  {
    PurchaseResponse localPurchaseResponse = new PurchaseResponse();
    localPurchaseResponse.setPurchaseReceipt(convertData(paramPurchaseResponse.getReceipt()));
    localPurchaseResponse.setRequestId(paramPurchaseResponse.getRequestId().toString());
    localPurchaseResponse.setStatus(paramPurchaseResponse.getRequestStatus().toString());
    localPurchaseResponse.setAmazonUserData(convertData(paramPurchaseResponse.getUserData()));
    return localPurchaseResponse;
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\amazon\device\iap\cpt\IapSdkConversionUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */