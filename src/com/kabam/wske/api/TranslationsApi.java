package com.kabam.wske.api;

import com.kabam.wske.client.ApiException;
import com.kabam.wske.client.ApiInvoker;
import com.kabam.wske.model.TranslationResource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TranslationsApi
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
  
  public void setBasePath(String paramString)
  {
    this.basePath = paramString;
  }
  
  public List<TranslationResource> translate(String paramString)
    throws ApiException
  {
    String str = "/translations".replaceAll("\\{format\\}", "json");
    HashMap localHashMap2 = new HashMap();
    HashMap localHashMap1 = new HashMap();
    if (!"null".equals(String.valueOf(paramString))) {
      localHashMap2.put("phrase", String.valueOf(paramString));
    }
    for (;;)
    {
      try
      {
        paramString = this.apiInvoker.invokeAPI(this.basePath, str, "GET", localHashMap2, null, localHashMap1, "application/json");
        if (paramString == null) {
          continue;
        }
        paramString = (List)ApiInvoker.deserialize(paramString, "Array", TranslationResource.class);
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
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\kabam\wske\api\TranslationsApi.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */