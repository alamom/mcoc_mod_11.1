package com.kabam.soda.wske;

import android.util.Log;
import com.kabam.soda.AuthSource;
import com.kabam.soda.Settings;
import com.kabam.wske.api.AccountsApi;
import com.kabam.wske.client.ApiException;
import com.kabam.wske.model.AuthenticatedNetworkResource;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

public class ListAuthenticatedNetworksAsyncTask
  extends WSKEAsyncTask<String, Void, Set<AuthSource>>
{
  private static final String TAG = ListAuthenticatedNetworksAsyncTask.class.getSimpleName();
  
  public ListAuthenticatedNetworksAsyncTask(Settings paramSettings, WSKECallback<Set<AuthSource>> paramWSKECallback)
  {
    super(paramSettings, paramWSKECallback);
  }
  
  protected Set<AuthSource> doWork(String... paramVarArgs)
    throws ApiException
  {
    LinkedHashSet localLinkedHashSet = new LinkedHashSet();
    paramVarArgs = paramVarArgs[0];
    Log.d(TAG, String.format("ListAuthenticatedNetworks, Client ID: %s, Player ID: %s", new Object[] { this.settings.getClientId(), paramVarArgs }));
    Object localObject = new AccountsApi();
    ((AccountsApi)localObject).setBasePath(getBasePath());
    paramVarArgs = ((AccountsApi)localObject).listAuthenticatedNetworks(this.settings.getClientId(), paramVarArgs).iterator();
    while (paramVarArgs.hasNext())
    {
      localObject = (AuthenticatedNetworkResource)paramVarArgs.next();
      if ((localObject != null) && (((AuthenticatedNetworkResource)localObject).getNetwork() != null) && (!((AuthenticatedNetworkResource)localObject).getNetwork().isEmpty()))
      {
        String str = ((AuthenticatedNetworkResource)localObject).getNetwork();
        try
        {
          localLinkedHashSet.add(AuthSource.valueOf(str.toUpperCase(Locale.ENGLISH)));
        }
        catch (IllegalArgumentException localIllegalArgumentException)
        {
          Log.w(TAG, String.format("Invalid network type returned: %s", new Object[] { str }));
        }
      }
    }
    return localLinkedHashSet;
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\kabam\soda\wske\ListAuthenticatedNetworksAsyncTask.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */