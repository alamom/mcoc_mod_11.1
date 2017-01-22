package com.kabam.wske.api;

import com.kabam.wske.client.ApiException;
import com.kabam.wske.client.ApiInvoker;
import com.kabam.wske.model.ConfigServerResponseResource;
import java.util.HashMap;

public class SettingsApi
{
  ApiInvoker apiInvoker = ApiInvoker.getInstance();
  String basePath = "http://127.0.0.1:9000";
  
  public void addHeader(String paramString1, String paramString2)
  {
    getInvoker().addDefaultHeader(paramString1, paramString2);
  }
  
  public ConfigServerResponseResource config(String paramString)
    throws ApiException
  {
    if (paramString == null) {
      throw new ApiException(400, "missing required params");
    }
    String str = "/settings/{clientId}".replaceAll("\\{format\\}", "json").replaceAll("\\{clientId\\}", this.apiInvoker.escapeString(paramString.toString()));
    HashMap localHashMap = new HashMap();
    for (paramString = new HashMap();; paramString = null)
    {
      try
      {
        paramString = this.apiInvoker.invokeAPI(this.basePath, str, "GET", localHashMap, null, paramString, "application/json");
        if (paramString == null) {
          continue;
        }
        paramString = (ConfigServerResponseResource)ApiInvoker.deserialize(paramString, "", ConfigServerResponseResource.class);
      }
      catch (ApiException paramString)
      {
        while (paramString.getCode() == 404) {
          paramString = null;
        }
        throw paramString;
      }
      return paramString;
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
  
  public void setBasePath(String paramString)
  {
    this.basePath = paramString;
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\kabam\wske\api\SettingsApi.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */