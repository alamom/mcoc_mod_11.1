package com.kabam.wske.api;

import com.kabam.wske.client.ApiException;
import com.kabam.wske.client.ApiInvoker;
import com.kabam.wske.model.AccountMergeListResource;
import com.kabam.wske.model.AuthTokenResource;
import com.kabam.wske.model.AuthenticatedNetworkResource;
import com.kabam.wske.model.PlayerMarketingEmailResource;
import com.kabam.wske.model.TokenResource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AccountsApi
{
  ApiInvoker apiInvoker = ApiInvoker.getInstance();
  String basePath = "http://127.0.0.1:9000";
  
  public void addHeader(String paramString1, String paramString2)
  {
    getInvoker().addDefaultHeader(paramString1, paramString2);
  }
  
  public TokenResource create(String paramString1, String paramString2, String paramString3)
    throws ApiException
  {
    if ((paramString1 == null) || (paramString2 == null) || (paramString3 == null)) {
      throw new ApiException(400, "missing required params");
    }
    paramString2 = "/accounts/{clientId}/{playerId}".replaceAll("\\{format\\}", "json").replaceAll("\\{clientId\\}", this.apiInvoker.escapeString(paramString2.toString())).replaceAll("\\{playerId\\}", this.apiInvoker.escapeString(paramString3.toString()));
    HashMap localHashMap = new HashMap();
    paramString3 = new HashMap();
    paramString3.put("X-KBM-Player-Certificate", paramString1);
    for (;;)
    {
      try
      {
        paramString1 = this.apiInvoker.invokeAPI(this.basePath, paramString2, "PUT", localHashMap, null, paramString3, "application/json");
        if (paramString1 == null) {
          continue;
        }
        paramString1 = (TokenResource)ApiInvoker.deserialize(paramString1, "", TokenResource.class);
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
  
  public String getBasePath()
  {
    return this.basePath;
  }
  
  public ApiInvoker getInvoker()
  {
    return this.apiInvoker;
  }
  
  public List<AuthenticatedNetworkResource> listAuthenticatedNetworks(String paramString1, String paramString2)
    throws ApiException
  {
    if ((paramString1 == null) || (paramString2 == null)) {
      throw new ApiException(400, "missing required params");
    }
    paramString1 = "/accounts/{clientId}/{playerId}/authTokens".replaceAll("\\{format\\}", "json").replaceAll("\\{clientId\\}", this.apiInvoker.escapeString(paramString1.toString())).replaceAll("\\{playerId\\}", this.apiInvoker.escapeString(paramString2.toString()));
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
        paramString1 = (List)ApiInvoker.deserialize(paramString1, "Array", AuthenticatedNetworkResource.class);
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
  
  public void removeToken(String paramString1, AuthTokenResource paramAuthTokenResource, String paramString2, String paramString3)
    throws ApiException
  {
    if ((paramString1 == null) || (paramAuthTokenResource == null) || (paramString2 == null) || (paramString3 == null)) {
      throw new ApiException(400, "missing required params");
    }
    paramString2 = "/accounts/{clientId}/{playerId}/authTokens/{associationType}".replaceAll("\\{format\\}", "json").replaceAll("\\{clientId\\}", this.apiInvoker.escapeString(paramString2.toString())).replaceAll("\\{playerId\\}", this.apiInvoker.escapeString(paramString3.toString())).replaceAll("\\{associationType\\}", this.apiInvoker.escapeString(paramString1.toString()));
    paramString1 = new HashMap();
    paramString3 = new HashMap();
    try
    {
      paramString1 = this.apiInvoker.invokeAPI(this.basePath, paramString2, "DELETE", paramString1, paramAuthTokenResource, paramString3, "application/json");
      if (paramString1 != null) {}
      return;
    }
    catch (ApiException paramString1)
    {
      while (paramString1.getCode() == 404) {}
      throw paramString1;
    }
  }
  
  public AccountMergeListResource sendToken(String paramString1, AuthTokenResource paramAuthTokenResource, String paramString2, String paramString3)
    throws ApiException
  {
    if ((paramString1 == null) || (paramAuthTokenResource == null) || (paramString2 == null) || (paramString3 == null)) {
      throw new ApiException(400, "missing required params");
    }
    paramString1 = "/accounts/{clientId}/{playerId}/authTokens/{associationType}".replaceAll("\\{format\\}", "json").replaceAll("\\{clientId\\}", this.apiInvoker.escapeString(paramString2.toString())).replaceAll("\\{playerId\\}", this.apiInvoker.escapeString(paramString3.toString())).replaceAll("\\{associationType\\}", this.apiInvoker.escapeString(paramString1.toString()));
    paramString2 = new HashMap();
    paramString3 = new HashMap();
    for (;;)
    {
      try
      {
        paramString1 = this.apiInvoker.invokeAPI(this.basePath, paramString1, "POST", paramString2, paramAuthTokenResource, paramString3, "application/json");
        if (paramString1 == null) {
          continue;
        }
        paramString1 = (AccountMergeListResource)ApiInvoker.deserialize(paramString1, "", AccountMergeListResource.class);
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
  
  public void updateMarketingEmail(PlayerMarketingEmailResource paramPlayerMarketingEmailResource, String paramString1, String paramString2)
    throws ApiException
  {
    if ((paramPlayerMarketingEmailResource == null) || (paramString1 == null) || (paramString2 == null)) {
      throw new ApiException(400, "missing required params");
    }
    paramString1 = "/accounts/{clientId}/{playerId}/marketing/email".replaceAll("\\{format\\}", "json").replaceAll("\\{clientId\\}", this.apiInvoker.escapeString(paramString1.toString())).replaceAll("\\{playerId\\}", this.apiInvoker.escapeString(paramString2.toString()));
    HashMap localHashMap = new HashMap();
    paramString2 = new HashMap();
    try
    {
      paramPlayerMarketingEmailResource = this.apiInvoker.invokeAPI(this.basePath, paramString1, "POST", localHashMap, paramPlayerMarketingEmailResource, paramString2, "application/json");
      if (paramPlayerMarketingEmailResource != null) {}
      return;
    }
    catch (ApiException paramPlayerMarketingEmailResource)
    {
      while (paramPlayerMarketingEmailResource.getCode() == 404) {}
      throw paramPlayerMarketingEmailResource;
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\kabam\wske\api\AccountsApi.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */