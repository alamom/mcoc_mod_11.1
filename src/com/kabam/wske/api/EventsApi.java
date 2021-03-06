package com.kabam.wske.api;

import com.kabam.wske.client.ApiException;
import com.kabam.wske.client.ApiInvoker;
import com.kabam.wske.model.EventResource;
import java.util.HashMap;

public class EventsApi
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
  
  public EventResource registerEvent(String paramString1, String paramString2, String paramString3)
    throws ApiException
  {
    if ((paramString1 == null) || (paramString2 == null) || (paramString3 == null)) {
      throw new ApiException(400, "missing required params");
    }
    paramString2 = "/events/{clientId}/{playerId}/{event}".replaceAll("\\{format\\}", "json").replaceAll("\\{clientId\\}", this.apiInvoker.escapeString(paramString1.toString())).replaceAll("\\{playerId\\}", this.apiInvoker.escapeString(paramString3.toString())).replaceAll("\\{event\\}", this.apiInvoker.escapeString(paramString2.toString()));
    paramString1 = new HashMap();
    paramString3 = new HashMap();
    for (;;)
    {
      try
      {
        paramString1 = this.apiInvoker.invokeAPI(this.basePath, paramString2, "POST", paramString1, null, paramString3, "application/json");
        if (paramString1 == null) {
          continue;
        }
        paramString1 = (EventResource)ApiInvoker.deserialize(paramString1, "", EventResource.class);
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


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\kabam\wske\api\EventsApi.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */