package com.kabam.wske.api;

import com.kabam.wske.client.ApiException;
import com.kabam.wske.client.ApiInvoker;
import com.kabam.wske.model.KabamAccountCreateBodyResource;
import com.kabam.wske.model.KabamAccountFindResponseResource;
import com.kabam.wske.model.KabamAccountForgotBodyResource;
import com.kabam.wske.model.KabamAccountLoginBodyResource;
import com.kabam.wske.model.KabamAccountLoginResponseResource;
import com.kabam.wske.model.KabamAccountPatchBodyResource;
import java.util.HashMap;
import java.util.Map;

public class KabamApi
{
  ApiInvoker apiInvoker = ApiInvoker.getInstance();
  String basePath = "http://127.0.0.1:9000";
  
  public void addHeader(String paramString1, String paramString2)
  {
    getInvoker().addDefaultHeader(paramString1, paramString2);
  }
  
  public KabamAccountLoginResponseResource create(KabamAccountCreateBodyResource paramKabamAccountCreateBodyResource)
    throws ApiException
  {
    if (paramKabamAccountCreateBodyResource == null) {
      throw new ApiException(400, "missing required params");
    }
    String str = "/kabam/accounts".replaceAll("\\{format\\}", "json");
    HashMap localHashMap2 = new HashMap();
    HashMap localHashMap1 = new HashMap();
    for (;;)
    {
      try
      {
        paramKabamAccountCreateBodyResource = this.apiInvoker.invokeAPI(this.basePath, str, "POST", localHashMap2, paramKabamAccountCreateBodyResource, localHashMap1, "application/json");
        if (paramKabamAccountCreateBodyResource == null) {
          continue;
        }
        paramKabamAccountCreateBodyResource = (KabamAccountLoginResponseResource)ApiInvoker.deserialize(paramKabamAccountCreateBodyResource, "", KabamAccountLoginResponseResource.class);
      }
      catch (ApiException paramKabamAccountCreateBodyResource)
      {
        if (paramKabamAccountCreateBodyResource.getCode() != 404) {
          continue;
        }
        paramKabamAccountCreateBodyResource = null;
        continue;
        throw paramKabamAccountCreateBodyResource;
      }
      return paramKabamAccountCreateBodyResource;
      paramKabamAccountCreateBodyResource = null;
    }
  }
  
  public void forgot(KabamAccountForgotBodyResource paramKabamAccountForgotBodyResource)
    throws ApiException
  {
    if (paramKabamAccountForgotBodyResource == null) {
      throw new ApiException(400, "missing required params");
    }
    String str = "/kabam/accounts/resetTokens".replaceAll("\\{format\\}", "json");
    HashMap localHashMap1 = new HashMap();
    HashMap localHashMap2 = new HashMap();
    try
    {
      paramKabamAccountForgotBodyResource = this.apiInvoker.invokeAPI(this.basePath, str, "POST", localHashMap1, paramKabamAccountForgotBodyResource, localHashMap2, "application/json");
      if (paramKabamAccountForgotBodyResource != null) {}
      return;
    }
    catch (ApiException paramKabamAccountForgotBodyResource)
    {
      while (paramKabamAccountForgotBodyResource.getCode() == 404) {}
      throw paramKabamAccountForgotBodyResource;
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
  
  public KabamAccountLoginResponseResource login(KabamAccountLoginBodyResource paramKabamAccountLoginBodyResource)
    throws ApiException
  {
    if (paramKabamAccountLoginBodyResource == null) {
      throw new ApiException(400, "missing required params");
    }
    String str = "/kabam/accounts/authTokens".replaceAll("\\{format\\}", "json");
    HashMap localHashMap2 = new HashMap();
    HashMap localHashMap1 = new HashMap();
    for (;;)
    {
      try
      {
        paramKabamAccountLoginBodyResource = this.apiInvoker.invokeAPI(this.basePath, str, "POST", localHashMap2, paramKabamAccountLoginBodyResource, localHashMap1, "application/json");
        if (paramKabamAccountLoginBodyResource == null) {
          continue;
        }
        paramKabamAccountLoginBodyResource = (KabamAccountLoginResponseResource)ApiInvoker.deserialize(paramKabamAccountLoginBodyResource, "", KabamAccountLoginResponseResource.class);
      }
      catch (ApiException paramKabamAccountLoginBodyResource)
      {
        if (paramKabamAccountLoginBodyResource.getCode() != 404) {
          continue;
        }
        paramKabamAccountLoginBodyResource = null;
        continue;
        throw paramKabamAccountLoginBodyResource;
      }
      return paramKabamAccountLoginBodyResource;
      paramKabamAccountLoginBodyResource = null;
    }
  }
  
  public void logout(String paramString)
    throws ApiException
  {
    if (paramString == null) {
      throw new ApiException(400, "missing required params");
    }
    String str = "/kabam/accounts/authTokens/{token}".replaceAll("\\{format\\}", "json").replaceAll("\\{token\\}", this.apiInvoker.escapeString(paramString.toString()));
    HashMap localHashMap = new HashMap();
    paramString = new HashMap();
    try
    {
      paramString = this.apiInvoker.invokeAPI(this.basePath, str, "DELETE", localHashMap, null, paramString, "application/json");
      if (paramString != null) {}
      return;
    }
    catch (ApiException paramString)
    {
      while (paramString.getCode() == 404) {}
      throw paramString;
    }
  }
  
  public KabamAccountFindResponseResource me(String paramString)
    throws ApiException
  {
    if (paramString == null) {
      throw new ApiException(400, "missing required params");
    }
    String str = "/kabam/accounts/me".replaceAll("\\{format\\}", "json");
    HashMap localHashMap1 = new HashMap();
    HashMap localHashMap2 = new HashMap();
    localHashMap2.put("X-KBM-AuthToken", paramString);
    for (;;)
    {
      try
      {
        paramString = this.apiInvoker.invokeAPI(this.basePath, str, "GET", localHashMap1, null, localHashMap2, "application/json");
        if (paramString == null) {
          continue;
        }
        paramString = (KabamAccountFindResponseResource)ApiInvoker.deserialize(paramString, "", KabamAccountFindResponseResource.class);
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
  
  public void patch(KabamAccountPatchBodyResource paramKabamAccountPatchBodyResource, String paramString)
    throws ApiException
  {
    if ((paramKabamAccountPatchBodyResource == null) || (paramString == null)) {
      throw new ApiException(400, "missing required params");
    }
    String str = "/kabam/accounts/id/{id}".replaceAll("\\{format\\}", "json").replaceAll("\\{id\\}", this.apiInvoker.escapeString(paramString.toString()));
    paramString = new HashMap();
    HashMap localHashMap = new HashMap();
    try
    {
      paramKabamAccountPatchBodyResource = this.apiInvoker.invokeAPI(this.basePath, str, "PATCH", paramString, paramKabamAccountPatchBodyResource, localHashMap, "application/json");
      if (paramKabamAccountPatchBodyResource != null) {}
      return;
    }
    catch (ApiException paramKabamAccountPatchBodyResource)
    {
      while (paramKabamAccountPatchBodyResource.getCode() == 404) {}
      throw paramKabamAccountPatchBodyResource;
    }
  }
  
  public void setBasePath(String paramString)
  {
    this.basePath = paramString;
  }
  
  public KabamAccountFindResponseResource validate(String paramString)
    throws ApiException
  {
    if (paramString == null) {
      throw new ApiException(400, "missing required params");
    }
    String str = "/kabam/accounts/authTokens/{token}".replaceAll("\\{format\\}", "json").replaceAll("\\{token\\}", this.apiInvoker.escapeString(paramString.toString()));
    paramString = new HashMap();
    HashMap localHashMap = new HashMap();
    for (;;)
    {
      try
      {
        paramString = this.apiInvoker.invokeAPI(this.basePath, str, "GET", paramString, null, localHashMap, "application/json");
        if (paramString == null) {
          continue;
        }
        paramString = (KabamAccountFindResponseResource)ApiInvoker.deserialize(paramString, "", KabamAccountFindResponseResource.class);
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


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\kabam\wske\api\KabamApi.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */