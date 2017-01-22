package com.kabam.soda.wske;

import com.kabam.soda.Settings;
import com.kabam.wske.api.KabamApi;
import com.kabam.wske.client.ApiException;
import com.kabam.wske.model.KabamAccountFindResponseResource;

public class KAccountGetMeAsyncTask
  extends WSKEAsyncTask<String, Void, KabamAccountFindResponseResource>
{
  public KAccountGetMeAsyncTask(Settings paramSettings, WSKECallback<KabamAccountFindResponseResource> paramWSKECallback)
  {
    super(paramSettings, paramWSKECallback);
  }
  
  protected KabamAccountFindResponseResource doWork(String... paramVarArgs)
    throws ApiException, Throwable
  {
    KabamApi localKabamApi = new KabamApi();
    localKabamApi.setBasePath(getBasePath());
    return localKabamApi.me(paramVarArgs[0]);
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\kabam\soda\wske\KAccountGetMeAsyncTask.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */