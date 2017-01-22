package com.kabam.soda.wske;

import com.kabam.soda.Settings;
import com.kabam.wske.api.EventsApi;

public class SendEventAsyncTask
  extends WSKEAsyncTask<String, Void, Boolean>
{
  public SendEventAsyncTask(Settings paramSettings, WSKECallback<Boolean> paramWSKECallback)
  {
    super(paramSettings, paramWSKECallback);
  }
  
  protected Boolean doWork(String... paramVarArgs)
    throws Throwable
  {
    Object localObject = Boolean.FALSE;
    EventsApi localEventsApi = new EventsApi();
    localEventsApi.setBasePath(getBasePath());
    localObject = paramVarArgs[0];
    paramVarArgs = paramVarArgs[1];
    localEventsApi.registerEvent(this.settings.getClientId(), paramVarArgs, (String)localObject);
    return Boolean.TRUE;
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\kabam\soda\wske\SendEventAsyncTask.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */