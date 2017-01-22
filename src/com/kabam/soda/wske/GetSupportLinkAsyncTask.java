package com.kabam.soda.wske;

import com.kabam.soda.Settings;
import com.kabam.wske.api.SupportApi;
import com.kabam.wske.model.SupportLinkResource;

public class GetSupportLinkAsyncTask
  extends WSKEAsyncTask<String, Void, SupportLinkResource>
{
  public GetSupportLinkAsyncTask(Settings paramSettings, WSKECallback<SupportLinkResource> paramWSKECallback)
  {
    super(paramSettings, paramWSKECallback);
  }
  
  protected SupportLinkResource doWork(String... paramVarArgs)
    throws Throwable
  {
    SupportApi localSupportApi = new SupportApi();
    localSupportApi.setBasePath(getBasePath());
    paramVarArgs = paramVarArgs[0];
    return localSupportApi.getLink(this.settings.getClientId(), paramVarArgs);
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\kabam\soda\wske\GetSupportLinkAsyncTask.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */