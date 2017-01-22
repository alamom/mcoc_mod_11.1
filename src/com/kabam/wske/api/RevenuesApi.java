package com.kabam.wske.api;

import com.kabam.wske.client.ApiException;
import com.kabam.wske.client.ApiInvoker;
import com.kabam.wske.model.RevenueEvent;
import com.kabam.wske.model.RevenueEventResource;
import java.util.HashMap;

public class RevenuesApi
{
  ApiInvoker apiInvoker = ApiInvoker.getInstance();
  String basePath = "http://127.0.0.1:9000";
  
  public void addHeader(String paramString1, String paramString2)
  {
    getInvoker().addDefaultHeader(paramString1, paramString2);
  }
  
  public void createRevenueEventClient(RevenueEventResource paramRevenueEventResource, String paramString1, String paramString2)
    throws ApiException
  {
    if ((paramRevenueEventResource == null) || (paramString1 == null) || (paramString2 == null)) {
      throw new ApiException(400, "missing required params");
    }
    paramString2 = "/revenues/{clientId}/{playerId}".replaceAll("\\{format\\}", "json").replaceAll("\\{clientId\\}", this.apiInvoker.escapeString(paramString1.toString())).replaceAll("\\{playerId\\}", this.apiInvoker.escapeString(paramString2.toString()));
    HashMap localHashMap = new HashMap();
    paramString1 = new HashMap();
    try
    {
      paramRevenueEventResource = this.apiInvoker.invokeAPI(this.basePath, paramString2, "POST", localHashMap, paramRevenueEventResource, paramString1, "application/json");
      if (paramRevenueEventResource != null) {}
      return;
    }
    catch (ApiException paramRevenueEventResource)
    {
      while (paramRevenueEventResource.getCode() == 404) {}
      throw paramRevenueEventResource;
    }
  }
  
  public void createRevenueEventServer(RevenueEventResource paramRevenueEventResource, String paramString1, String paramString2)
    throws ApiException
  {
    if ((paramRevenueEventResource == null) || (paramString1 == null) || (paramString2 == null)) {
      throw new ApiException(400, "missing required params");
    }
    String str = "/revenues/server/{clientId}/{playerId}".replaceAll("\\{format\\}", "json").replaceAll("\\{clientId\\}", this.apiInvoker.escapeString(paramString1.toString())).replaceAll("\\{playerId\\}", this.apiInvoker.escapeString(paramString2.toString()));
    paramString1 = new HashMap();
    paramString2 = new HashMap();
    try
    {
      paramRevenueEventResource = this.apiInvoker.invokeAPI(this.basePath, str, "POST", paramString1, paramRevenueEventResource, paramString2, "application/json");
      if (paramRevenueEventResource != null) {}
      return;
    }
    catch (ApiException paramRevenueEventResource)
    {
      while (paramRevenueEventResource.getCode() == 404) {}
      throw paramRevenueEventResource;
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
  
  public RevenueEvent getRevenueEvent(String paramString)
    throws ApiException
  {
    if (paramString == null) {
      throw new ApiException(400, "missing required params");
    }
    paramString = "/revenues/{transactionId}".replaceAll("\\{format\\}", "json").replaceAll("\\{transactionId\\}", this.apiInvoker.escapeString(paramString.toString()));
    HashMap localHashMap2 = new HashMap();
    HashMap localHashMap1 = new HashMap();
    for (;;)
    {
      try
      {
        paramString = this.apiInvoker.invokeAPI(this.basePath, paramString, "GET", localHashMap2, null, localHashMap1, "application/json");
        if (paramString == null) {
          continue;
        }
        paramString = (RevenueEvent)ApiInvoker.deserialize(paramString, "", RevenueEvent.class);
      }
      catch (ApiException paramString)
      {
        if (paramString.getCode() != 404) {
          continue;
        }
        paramString = null;
        continue;
        throw paramString;
      }
      return paramString;
      paramString = null;
    }
  }
  
  public void setBasePath(String paramString)
  {
    this.basePath = paramString;
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\kabam\wske\api\RevenuesApi.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */