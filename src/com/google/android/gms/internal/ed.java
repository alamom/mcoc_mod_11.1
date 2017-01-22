package com.google.android.gms.internal;

import android.content.Intent;
import android.os.Bundle;
import org.json.JSONException;
import org.json.JSONObject;

@ez
public final class ed
{
  public static String D(String paramString)
  {
    Object localObject = null;
    if (paramString == null) {
      paramString = (String)localObject;
    }
    for (;;)
    {
      return paramString;
      try
      {
        JSONObject localJSONObject = new org/json/JSONObject;
        localJSONObject.<init>(paramString);
        paramString = localJSONObject.getString("developerPayload");
      }
      catch (JSONException paramString)
      {
        gs.W("Fail to parse purchase data");
        paramString = (String)localObject;
      }
    }
  }
  
  public static String E(String paramString)
  {
    Object localObject = null;
    if (paramString == null) {
      paramString = (String)localObject;
    }
    for (;;)
    {
      return paramString;
      try
      {
        JSONObject localJSONObject = new org/json/JSONObject;
        localJSONObject.<init>(paramString);
        paramString = localJSONObject.getString("purchaseToken");
      }
      catch (JSONException paramString)
      {
        gs.W("Fail to parse purchase data");
        paramString = (String)localObject;
      }
    }
  }
  
  public static int b(Bundle paramBundle)
  {
    paramBundle = paramBundle.get("RESPONSE_CODE");
    int i;
    if (paramBundle == null)
    {
      gs.W("Bundle with null response code, assuming OK (known issue)");
      i = 0;
    }
    for (;;)
    {
      return i;
      if ((paramBundle instanceof Integer))
      {
        i = ((Integer)paramBundle).intValue();
      }
      else if ((paramBundle instanceof Long))
      {
        i = (int)((Long)paramBundle).longValue();
      }
      else
      {
        gs.W("Unexpected type for intent response code. " + paramBundle.getClass().getName());
        i = 5;
      }
    }
  }
  
  public static int d(Intent paramIntent)
  {
    paramIntent = paramIntent.getExtras().get("RESPONSE_CODE");
    int i;
    if (paramIntent == null)
    {
      gs.W("Intent with no response code, assuming OK (known issue)");
      i = 0;
    }
    for (;;)
    {
      return i;
      if ((paramIntent instanceof Integer))
      {
        i = ((Integer)paramIntent).intValue();
      }
      else if ((paramIntent instanceof Long))
      {
        i = (int)((Long)paramIntent).longValue();
      }
      else
      {
        gs.W("Unexpected type for intent response code. " + paramIntent.getClass().getName());
        i = 5;
      }
    }
  }
  
  public static String e(Intent paramIntent)
  {
    if (paramIntent == null) {}
    for (paramIntent = null;; paramIntent = paramIntent.getStringExtra("INAPP_PURCHASE_DATA")) {
      return paramIntent;
    }
  }
  
  public static String f(Intent paramIntent)
  {
    if (paramIntent == null) {}
    for (paramIntent = null;; paramIntent = paramIntent.getStringExtra("INAPP_DATA_SIGNATURE")) {
      return paramIntent;
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\internal\ed.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */