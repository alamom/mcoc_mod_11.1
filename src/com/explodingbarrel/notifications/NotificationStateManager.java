package com.explodingbarrel.notifications;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.util.Log;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class NotificationStateManager
{
  private static String TAG = "NotificationStateManager";
  private static String prefName = "notifications";
  
  public static void AddNotification(Context paramContext, String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, Bundle paramBundle)
  {
    if ((Build.VERSION.SDK_INT < 16) || (paramString1 == null)) {}
    for (;;)
    {
      return;
      SharedPreferences localSharedPreferences = paramContext.getSharedPreferences(prefName, 0);
      JSONObject localJSONObject = getNotificationsState(localSharedPreferences);
      if (localJSONObject == null)
      {
        Log.d(TAG, "Failed to update notification state");
      }
      else
      {
        JSONArray localJSONArray = localJSONObject.optJSONArray(paramString1);
        paramContext = localJSONArray;
        if (localJSONArray == null) {
          paramContext = new JSONArray();
        }
        paramString2 = notificationData(paramString2, paramString3, paramString4, paramString5, paramBundle);
        if (paramString2 == null)
        {
          Log.d(TAG, "Failed to generate note data!");
        }
        else
        {
          paramContext.put(paramString2);
          try
          {
            localJSONObject.put(paramString1, paramContext);
            paramContext = localJSONObject.toString();
            paramString1 = localSharedPreferences.edit();
            paramString1.putString("note_state", paramContext);
            paramString1.commit();
          }
          catch (JSONException paramContext)
          {
            Log.d(TAG, "Failed to update state: " + paramContext.getMessage());
          }
        }
      }
    }
  }
  
  public static void ClearAllState(Context paramContext)
  {
    if (Build.VERSION.SDK_INT < 16) {}
    for (;;)
    {
      return;
      paramContext = paramContext.getSharedPreferences(prefName, 0).edit();
      paramContext.putString("note_state", "{}");
      paramContext.commit();
    }
  }
  
  public static void RemoveNotification(Context paramContext, String paramString1, String paramString2)
  {
    if ((Build.VERSION.SDK_INT < 16) || (paramString1 == null)) {}
    for (;;)
    {
      return;
      SharedPreferences localSharedPreferences = paramContext.getSharedPreferences(prefName, 0);
      JSONObject localJSONObject1 = getNotificationsState(localSharedPreferences);
      if (localJSONObject1 == null)
      {
        Log.d(TAG, "Failed to update notification state");
        continue;
      }
      paramContext = localJSONObject1.optJSONArray(paramString1);
      if (paramContext == null) {
        continue;
      }
      int k = -1;
      int i = 0;
      int j = k;
      if (i < paramContext.length())
      {
        JSONObject localJSONObject2 = paramContext.optJSONObject(i);
        if (localJSONObject2 == null) {
          Log.d(TAG, "not an object!");
        }
        while (!localJSONObject2.optString("id").equals(paramString2))
        {
          i++;
          break;
        }
        j = i;
      }
      if (j < 0) {
        continue;
      }
      if (Build.VERSION.SDK_INT < 19) {
        paramContext = Util.jsonArrayRemoveAt(paramContext, j);
      }
      try
      {
        for (;;)
        {
          localJSONObject1.put(paramString1, paramContext);
          paramContext = localJSONObject1.toString();
          paramString1 = localSharedPreferences.edit();
          paramString1.putString("note_state", paramContext);
          paramString1.commit();
          break;
          paramContext.remove(j);
        }
      }
      catch (JSONException paramContext)
      {
        for (;;)
        {
          Log.d(TAG, "Failed to update state: " + paramContext.getMessage());
        }
      }
    }
  }
  
  public static NotificationNote[] getNotificationsForGroup(Context paramContext, String paramString)
  {
    Object localObject2 = null;
    Object localObject1 = localObject2;
    if (Build.VERSION.SDK_INT >= 16)
    {
      if (paramString != null) {
        break label26;
      }
      localObject1 = localObject2;
    }
    label26:
    JSONArray localJSONArray;
    int j;
    int i;
    do
    {
      for (;;)
      {
        return (NotificationNote[])localObject1;
        paramContext = getNotificationsState(paramContext.getSharedPreferences(prefName, 0));
        localJSONArray = paramContext.optJSONArray(paramString);
        if (localJSONArray == null)
        {
          Log.d(TAG, "No notes found for this group: " + paramString + "\n" + paramContext.toString());
          localObject1 = localObject2;
        }
        else
        {
          j = localJSONArray.length();
          if (j > 0) {
            break;
          }
          Log.d(TAG, "No notes found for this group: " + paramString + "\n" + paramContext.toString());
          localObject1 = localObject2;
        }
      }
      paramContext = new NotificationNote[j];
      i = 0;
      localObject1 = paramContext;
    } while (i >= j);
    paramString = localJSONArray.optJSONObject(i);
    if (paramString == null) {}
    for (;;)
    {
      i++;
      break;
      paramContext[i] = new NotificationNote(paramString);
    }
  }
  
  private static JSONObject getNotificationsState(SharedPreferences paramSharedPreferences)
  {
    paramSharedPreferences = paramSharedPreferences.getString("note_state", null);
    if (paramSharedPreferences == null) {}
    try
    {
      paramSharedPreferences = new org/json/JSONObject;
      paramSharedPreferences.<init>();
      for (;;)
      {
        return paramSharedPreferences;
        paramSharedPreferences = new JSONObject(paramSharedPreferences);
      }
    }
    catch (JSONException paramSharedPreferences)
    {
      for (;;)
      {
        Log.d(TAG, "JSON deserialization failed! " + paramSharedPreferences.getMessage());
        paramSharedPreferences = new JSONObject();
      }
    }
  }
  
  private static JSONObject notificationData(String paramString1, String paramString2, String paramString3, String paramString4, Bundle paramBundle)
  {
    JSONObject localJSONObject = new JSONObject();
    try
    {
      localJSONObject.put("id", paramString1);
      localJSONObject.put("title", paramString2);
      localJSONObject.put("ticker", paramString3);
      localJSONObject.put("text", paramString4);
      localJSONObject.put("data", Util.bundleToJSON(paramBundle));
      paramString1 = localJSONObject;
    }
    catch (JSONException paramString1)
    {
      for (;;)
      {
        paramString1 = null;
      }
    }
    return paramString1;
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\explodingbarrel\notifications\NotificationStateManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */