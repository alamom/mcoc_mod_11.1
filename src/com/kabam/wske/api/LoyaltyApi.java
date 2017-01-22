package com.kabam.wske.api;

import com.kabam.wske.client.ApiException;
import com.kabam.wske.client.ApiInvoker;
import com.kabam.wske.model.LoyaltyEventResource;
import com.kabam.wske.model.LoyaltyInformationResource;
import com.kabam.wske.model.LoyaltyRedeemableResource;
import com.kabam.wske.model.LoyaltyRedemptionInput;
import com.kabam.wske.model.LoyaltyRedemptionPatchInput;
import com.kabam.wske.model.LoyaltyRedemptionReceipt;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoyaltyApi
{
  ApiInvoker apiInvoker = ApiInvoker.getInstance();
  String basePath = "http://127.0.0.1:9000";
  
  public void addHeader(String paramString1, String paramString2)
  {
    getInvoker().addDefaultHeader(paramString1, paramString2);
  }
  
  public LoyaltyRedemptionReceipt createOrder(LoyaltyRedemptionInput paramLoyaltyRedemptionInput, String paramString1, String paramString2)
    throws ApiException
  {
    if ((paramLoyaltyRedemptionInput == null) || (paramString1 == null) || (paramString2 == null)) {
      throw new ApiException(400, "missing required params");
    }
    paramString2 = "/loyalty/redemptions/{clientId}/{playerId}".replaceAll("\\{format\\}", "json").replaceAll("\\{clientId\\}", this.apiInvoker.escapeString(paramString1.toString())).replaceAll("\\{playerId\\}", this.apiInvoker.escapeString(paramString2.toString()));
    paramString1 = new HashMap();
    HashMap localHashMap = new HashMap();
    for (;;)
    {
      try
      {
        paramLoyaltyRedemptionInput = this.apiInvoker.invokeAPI(this.basePath, paramString2, "POST", paramString1, paramLoyaltyRedemptionInput, localHashMap, "application/json");
        if (paramLoyaltyRedemptionInput == null) {
          continue;
        }
        paramLoyaltyRedemptionInput = (LoyaltyRedemptionReceipt)ApiInvoker.deserialize(paramLoyaltyRedemptionInput, "", LoyaltyRedemptionReceipt.class);
      }
      catch (ApiException paramLoyaltyRedemptionInput)
      {
        if (paramLoyaltyRedemptionInput.getCode() != 404) {
          continue;
        }
        paramLoyaltyRedemptionInput = null;
        continue;
        throw paramLoyaltyRedemptionInput;
      }
      return paramLoyaltyRedemptionInput;
      paramLoyaltyRedemptionInput = null;
    }
  }
  
  public String getBasePath()
  {
    return this.basePath;
  }
  
  public ApiInvoker getInvoker()
  {
    return this.apiInvoker;
  }
  
  public LoyaltyInformationResource getLoyaltyInformation(String paramString1, String paramString2)
    throws ApiException
  {
    if ((paramString1 == null) || (paramString2 == null)) {
      throw new ApiException(400, "missing required params");
    }
    String str = "/loyalty/information/{clientId}/{playerId}".replaceAll("\\{format\\}", "json").replaceAll("\\{clientId\\}", this.apiInvoker.escapeString(paramString1.toString())).replaceAll("\\{playerId\\}", this.apiInvoker.escapeString(paramString2.toString()));
    paramString2 = new HashMap();
    for (paramString1 = new HashMap();; paramString1 = null)
    {
      try
      {
        paramString1 = this.apiInvoker.invokeAPI(this.basePath, str, "GET", paramString2, null, paramString1, "application/json");
        if (paramString1 == null) {
          continue;
        }
        paramString1 = (LoyaltyInformationResource)ApiInvoker.deserialize(paramString1, "", LoyaltyInformationResource.class);
      }
      catch (ApiException paramString1)
      {
        while (paramString1.getCode() == 404) {
          paramString1 = null;
        }
        throw paramString1;
      }
      return paramString1;
    }
  }
  
  public LoyaltyRedemptionReceipt getReceipt(String paramString1, String paramString2, String paramString3)
    throws ApiException
  {
    if ((paramString1 == null) || (paramString2 == null) || (paramString3 == null)) {
      throw new ApiException(400, "missing required params");
    }
    paramString3 = "/loyalty/redemptions/{clientId}/{playerId}/{transactionId}".replaceAll("\\{format\\}", "json").replaceAll("\\{clientId\\}", this.apiInvoker.escapeString(paramString1.toString())).replaceAll("\\{playerId\\}", this.apiInvoker.escapeString(paramString2.toString())).replaceAll("\\{transactionId\\}", this.apiInvoker.escapeString(paramString3.toString()));
    paramString2 = new HashMap();
    for (paramString1 = new HashMap();; paramString1 = null)
    {
      try
      {
        paramString1 = this.apiInvoker.invokeAPI(this.basePath, paramString3, "GET", paramString2, null, paramString1, "application/json");
        if (paramString1 == null) {
          continue;
        }
        paramString1 = (LoyaltyRedemptionReceipt)ApiInvoker.deserialize(paramString1, "", LoyaltyRedemptionReceipt.class);
      }
      catch (ApiException paramString1)
      {
        while (paramString1.getCode() == 404) {
          paramString1 = null;
        }
        throw paramString1;
      }
      return paramString1;
    }
  }
  
  public List<LoyaltyEventResource> listEvents(String paramString1, Integer paramInteger, String paramString2, String paramString3)
    throws ApiException
  {
    if ((paramString1 == null) || (paramString2 == null)) {
      throw new ApiException(400, "missing required params");
    }
    paramString2 = "/loyalty/events/{clientId}/{playerId}".replaceAll("\\{format\\}", "json").replaceAll("\\{clientId\\}", this.apiInvoker.escapeString(paramString1.toString())).replaceAll("\\{playerId\\}", this.apiInvoker.escapeString(paramString2.toString()));
    HashMap localHashMap = new HashMap();
    paramString1 = new HashMap();
    if (!"null".equals(String.valueOf(paramInteger))) {
      localHashMap.put("page", String.valueOf(paramInteger));
    }
    if (!"null".equals(String.valueOf(paramString3))) {
      localHashMap.put("since", String.valueOf(paramString3));
    }
    for (;;)
    {
      try
      {
        paramString1 = this.apiInvoker.invokeAPI(this.basePath, paramString2, "GET", localHashMap, null, paramString1, "application/json");
        if (paramString1 == null) {
          continue;
        }
        paramString1 = (List)ApiInvoker.deserialize(paramString1, "Array", LoyaltyEventResource.class);
      }
      catch (ApiException paramString1)
      {
        if (paramString1.getCode() != 404) {
          continue;
        }
        paramString1 = null;
        continue;
        throw paramString1;
      }
      return paramString1;
      paramString1 = null;
    }
  }
  
  public List<LoyaltyRedemptionReceipt> listReceipts(String paramString1, String paramString2, String paramString3)
    throws ApiException
  {
    if ((paramString1 == null) || (paramString2 == null)) {
      throw new ApiException(400, "missing required params");
    }
    String str = "/loyalty/redemptions/{clientId}/{playerId}".replaceAll("\\{format\\}", "json").replaceAll("\\{clientId\\}", this.apiInvoker.escapeString(paramString1.toString())).replaceAll("\\{playerId\\}", this.apiInvoker.escapeString(paramString2.toString()));
    paramString1 = new HashMap();
    paramString2 = new HashMap();
    if (!"null".equals(String.valueOf(paramString3))) {
      paramString1.put("status", String.valueOf(paramString3));
    }
    for (;;)
    {
      try
      {
        paramString1 = this.apiInvoker.invokeAPI(this.basePath, str, "GET", paramString1, null, paramString2, "application/json");
        if (paramString1 == null) {
          continue;
        }
        paramString1 = (List)ApiInvoker.deserialize(paramString1, "Array", LoyaltyRedemptionReceipt.class);
      }
      catch (ApiException paramString1)
      {
        if (paramString1.getCode() != 404) {
          continue;
        }
        paramString1 = null;
        continue;
        throw paramString1;
      }
      return paramString1;
      paramString1 = null;
    }
  }
  
  public List<LoyaltyRedeemableResource> listRedeemables(String paramString1, String paramString2)
    throws ApiException
  {
    if ((paramString1 == null) || (paramString2 == null)) {
      throw new ApiException(400, "missing required params");
    }
    paramString2 = "/loyalty/redeemables/{clientId}/{playerId}".replaceAll("\\{format\\}", "json").replaceAll("\\{clientId\\}", this.apiInvoker.escapeString(paramString1.toString())).replaceAll("\\{playerId\\}", this.apiInvoker.escapeString(paramString2.toString()));
    HashMap localHashMap = new HashMap();
    for (paramString1 = new HashMap();; paramString1 = null)
    {
      try
      {
        paramString1 = this.apiInvoker.invokeAPI(this.basePath, paramString2, "GET", localHashMap, null, paramString1, "application/json");
        if (paramString1 == null) {
          continue;
        }
        paramString1 = (List)ApiInvoker.deserialize(paramString1, "Array", LoyaltyRedeemableResource.class);
      }
      catch (ApiException paramString1)
      {
        while (paramString1.getCode() == 404) {
          paramString1 = null;
        }
        throw paramString1;
      }
      return paramString1;
    }
  }
  
  public void modifyReceipt(LoyaltyRedemptionPatchInput paramLoyaltyRedemptionPatchInput, String paramString1, String paramString2, String paramString3)
    throws ApiException
  {
    if ((paramLoyaltyRedemptionPatchInput == null) || (paramString1 == null) || (paramString2 == null) || (paramString3 == null)) {
      throw new ApiException(400, "missing required params");
    }
    paramString2 = "/loyalty/redemptions/{clientId}/{playerId}/{transactionId}".replaceAll("\\{format\\}", "json").replaceAll("\\{clientId\\}", this.apiInvoker.escapeString(paramString1.toString())).replaceAll("\\{playerId\\}", this.apiInvoker.escapeString(paramString2.toString())).replaceAll("\\{transactionId\\}", this.apiInvoker.escapeString(paramString3.toString()));
    paramString3 = new HashMap();
    paramString1 = new HashMap();
    try
    {
      paramLoyaltyRedemptionPatchInput = this.apiInvoker.invokeAPI(this.basePath, paramString2, "PATCH", paramString3, paramLoyaltyRedemptionPatchInput, paramString1, "application/json");
      if (paramLoyaltyRedemptionPatchInput != null) {}
      return;
    }
    catch (ApiException paramLoyaltyRedemptionPatchInput)
    {
      while (paramLoyaltyRedemptionPatchInput.getCode() == 404) {}
      throw paramLoyaltyRedemptionPatchInput;
    }
  }
  
  public void setBasePath(String paramString)
  {
    this.basePath = paramString;
  }
  
  public void showRedeemable(String paramString1, String paramString2)
    throws ApiException
  {
    if ((paramString1 == null) || (paramString2 == null)) {
      throw new ApiException(400, "missing required params");
    }
    String str = "/loyalty/redeemables/{clientId}/product/{productId}".replaceAll("\\{format\\}", "json").replaceAll("\\{clientId\\}", this.apiInvoker.escapeString(paramString1.toString())).replaceAll("\\{productId\\}", this.apiInvoker.escapeString(paramString2.toString()));
    paramString2 = new HashMap();
    paramString1 = new HashMap();
    try
    {
      paramString1 = this.apiInvoker.invokeAPI(this.basePath, str, "GET", paramString2, null, paramString1, "application/json");
      if (paramString1 != null) {}
      return;
    }
    catch (ApiException paramString1)
    {
      while (paramString1.getCode() == 404) {}
      throw paramString1;
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\kabam\wske\api\LoyaltyApi.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */