package com.kabam.soda.wske;

import android.util.Log;
import com.kabam.soda.Settings;
import com.kabam.wske.api.AccountsApi;
import com.kabam.wske.client.ApiException;
import com.kabam.wske.model.TokenResource;

public class CreateAccountAsyncTask
  extends WSKEAsyncTask<CreateAccountData, Void, String>
{
  public CreateAccountAsyncTask(Settings paramSettings, WSKECallback<String> paramWSKECallback)
  {
    super(paramSettings, paramWSKECallback);
  }
  
  protected String doWork(CreateAccountData... paramVarArgs)
    throws ApiException
  {
    Object localObject1 = null;
    paramVarArgs = paramVarArgs[0];
    Object localObject2 = new AccountsApi();
    ((AccountsApi)localObject2).setBasePath(getBasePath());
    localObject2 = ((AccountsApi)localObject2).create(paramVarArgs.getPlayerCertificate(), this.settings.getClientId(), paramVarArgs.getPlayerId());
    paramVarArgs = (CreateAccountData[])localObject1;
    if (localObject2 != null)
    {
      paramVarArgs = ((TokenResource)localObject2).getToken();
      setWskeToken(paramVarArgs);
      Log.i(this.TAG, String.format("Obtained WSKE Token: %s", new Object[] { paramVarArgs }));
    }
    return paramVarArgs;
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\kabam\soda\wske\CreateAccountAsyncTask.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */