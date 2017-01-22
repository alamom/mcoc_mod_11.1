package com.kabam.soda.wske;

import com.kabam.soda.Settings;
import com.kabam.wske.api.SettingsApi;
import com.kabam.wske.client.ApiException;
import com.kabam.wske.model.ConfigServerResponseResource;

public class GetClientSettingsAsyncTask
  extends WSKEAsyncTask<String, Void, ConfigServerResponseResource>
{
  public GetClientSettingsAsyncTask(Settings paramSettings, WSKECallback<ConfigServerResponseResource> paramWSKECallback)
  {
    super(paramSettings, paramWSKECallback);
  }
  
  protected ConfigServerResponseResource doWork(String... paramVarArgs)
    throws ApiException
  {
    SettingsApi localSettingsApi = new SettingsApi();
    localSettingsApi.setBasePath(getBasePath());
    return localSettingsApi.config(paramVarArgs[0]);
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\kabam\soda\wske\GetClientSettingsAsyncTask.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */