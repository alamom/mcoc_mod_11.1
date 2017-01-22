package com.kabam.soda.wske;

import android.util.Log;
import com.kabam.soda.Settings;
import com.kabam.wske.api.ConfigsApi;
import com.kabam.wske.client.ApiException;
import com.kabam.wske.model.PlayerConfigResource;
import java.util.Arrays;

public class GetFeatureConfigAsyncTask
  extends WSKEAsyncTask<String, Void, PlayerConfigResource>
{
  private static final String TAG = GetFeatureConfigAsyncTask.class.getSimpleName();
  
  public GetFeatureConfigAsyncTask(Settings paramSettings, WSKECallback<PlayerConfigResource> paramWSKECallback)
  {
    super(paramSettings, paramWSKECallback);
  }
  
  protected PlayerConfigResource doWork(String... paramVarArgs)
    throws ApiException
  {
    if (paramVarArgs.length > 1) {
      throw new IllegalArgumentException("too many args: " + Arrays.toString(paramVarArgs));
    }
    String str = paramVarArgs[0];
    Log.d(TAG, String.format("GetFeatureConfig, Client ID: %s, Player ID: %s", new Object[] { this.settings.getClientId(), str }));
    paramVarArgs = new ConfigsApi();
    paramVarArgs.setBasePath(getBasePath());
    paramVarArgs = paramVarArgs.getConfigs(this.settings.getClientId(), null, str);
    Log.d(TAG, "Result: " + paramVarArgs);
    return paramVarArgs;
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\kabam\soda\wske\GetFeatureConfigAsyncTask.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */