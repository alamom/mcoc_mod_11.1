package com.explodingbarrel.gpg;

import android.util.Log;
import com.google.android.gms.auth.GoogleAuthUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.plus.Account;
import com.google.android.gms.plus.Plus;
import com.unity3d.player.UnityPlayer;

public class Helpers
{
  private static final String TAG = "GpgHelpers";
  
  public static String getEmail(GoogleApiClient paramGoogleApiClient)
  {
    Object localObject = null;
    try
    {
      paramGoogleApiClient = Plus.AccountApi.getAccountName(paramGoogleApiClient);
      return paramGoogleApiClient;
    }
    catch (Exception paramGoogleApiClient)
    {
      for (;;)
      {
        Log.d("GpgHelpers", "Failed to get email " + paramGoogleApiClient);
        paramGoogleApiClient = (GoogleApiClient)localObject;
      }
    }
  }
  
  public static String getJWT(GoogleApiClient paramGoogleApiClient, String paramString)
  {
    String str = "audience:server:client_id:" + paramString;
    paramString = null;
    try
    {
      paramGoogleApiClient = GoogleAuthUtil.getToken(UnityPlayer.currentActivity, Plus.AccountApi.getAccountName(paramGoogleApiClient), str);
      return paramGoogleApiClient;
    }
    catch (Exception paramGoogleApiClient)
    {
      for (;;)
      {
        Log.d("GpgHelpers", "Failed to get jwt " + paramGoogleApiClient);
        paramGoogleApiClient = paramString;
      }
    }
  }
  
  public static String getToken(GoogleApiClient paramGoogleApiClient)
  {
    Object localObject = null;
    try
    {
      paramGoogleApiClient = GoogleAuthUtil.getToken(UnityPlayer.currentActivity, Plus.AccountApi.getAccountName(paramGoogleApiClient), "oauth2:https://www.googleapis.com/auth/plus.login");
      return paramGoogleApiClient;
    }
    catch (Exception paramGoogleApiClient)
    {
      for (;;)
      {
        Log.d("GpgHelpers", "Failed to get token " + paramGoogleApiClient);
        paramGoogleApiClient = (GoogleApiClient)localObject;
      }
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\explodingbarrel\gpg\Helpers.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */