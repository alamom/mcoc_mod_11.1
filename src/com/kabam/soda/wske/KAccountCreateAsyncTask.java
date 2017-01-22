package com.kabam.soda.wske;

import com.kabam.soda.Settings;
import com.kabam.wske.api.KabamApi;
import com.kabam.wske.client.ApiException;
import com.kabam.wske.model.KabamAccountCreateBodyResource;
import com.kabam.wske.model.KabamAccountLoginResponseResource;

public class KAccountCreateAsyncTask
  extends WSKEAsyncTask<KabamAccountCreateBodyResource, Void, KabamAccountLoginResponseResource>
{
  public KAccountCreateAsyncTask(Settings paramSettings, WSKECallback<KabamAccountLoginResponseResource> paramWSKECallback)
  {
    super(paramSettings, paramWSKECallback);
  }
  
  protected KabamAccountLoginResponseResource doWork(KabamAccountCreateBodyResource... paramVarArgs)
    throws ApiException
  {
    KabamApi localKabamApi = new KabamApi();
    localKabamApi.setBasePath(getBasePath());
    return localKabamApi.create(paramVarArgs[0]);
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\kabam\soda\wske\KAccountCreateAsyncTask.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */