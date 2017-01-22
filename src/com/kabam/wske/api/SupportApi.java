package com.kabam.wske.api;

import com.kabam.wske.client.ApiException;
import com.kabam.wske.client.ApiInvoker;
import com.kabam.wske.model.SupportLinkResource;
import java.util.HashMap;

public class SupportApi
{
  ApiInvoker apiInvoker = ApiInvoker.getInstance();
  String basePath = "http://127.0.0.1:9000";
  
  public void addHeader(String paramString1, String paramString2)
  {
    getInvoker().addDefaultHeader(paramString1, paramString2);
  }
  
  public String getBasePath()
  {
    return this.basePath;
  }
  
  public ApiInvoker getInvoker()
  {
    return this.apiInvoker;
  }
  
  public SupportLinkResource getLink(String paramString1, String paramString2)
    throws ApiException
  {
    if ((paramString1 == null) || (paramString2 == null)) {
      throw new ApiException(400, "missing required params");
    }
    paramString1 = "/support/{clientId}/{playerId}".replaceAll("\\{format\\}", "json").replaceAll("\\{clientId\\}", this.apiInvoker.escapeString(paramString1.toString())).replaceAll("\\{playerId\\}", this.apiInvoker.escapeString(paramString2.toString()));
    paramString2 = new HashMap();
    HashMap localHashMap = new HashMap();
    for (;;)
    {
      try
      {
        paramString1 = this.apiInvoker.invokeAPI(this.basePath, paramString1, "GET", paramString2, null, localHashMap, "application/json");
        if (paramString1 == null) {
          continue;
        }
        paramString1 = (SupportLinkResource)ApiInvoker.deserialize(paramString1, "", SupportLinkResource.class);
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
  
  public void setBasePath(String paramString)
  {
    this.basePath = paramString;
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\kabam\wske\api\SupportApi.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */