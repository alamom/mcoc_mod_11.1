package com.explodingbarrel.notifications;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import org.json.JSONException;
import org.json.JSONObject;

public class GCMIntentService
  extends IntentService
{
  public static final String TAG = GCMIntentService.class.getSimpleName();
  
  public GCMIntentService()
  {
    super("GCMIntentService");
  }
  
  protected void onHandleIntent(Intent paramIntent)
  {
    paramIntent.getExtras();
    Object localObject1 = GoogleCloudMessaging.getInstance(this).getMessageType(paramIntent);
    if ("send_error".equals(localObject1)) {
      Log.d(TAG, "Message Error");
    }
    for (;;)
    {
      GCMReceiver.completeWakefulIntent(paramIntent);
      Bundle localBundle;
      String str5;
      for (;;)
      {
        return;
        if ("deleted_messages".equals(localObject1))
        {
          Log.d(TAG, "Message Deleted");
          break;
        }
        if (!"gcm".equals(localObject1)) {
          break label363;
        }
        if (!paramIntent.getAction().equals("com.google.android.c2dm.intent.RECEIVE")) {
          break;
        }
        Log.d(TAG, paramIntent.getExtras().toString());
        localBundle = paramIntent.getExtras();
        localObject2 = localBundle.getString("payload");
        localBundle.getString("cta");
        if (localObject2 != null)
        {
          localObject1 = localObject2;
          if (!((String)localObject2).isEmpty()) {}
        }
        else
        {
          localObject1 = localBundle.getString("default");
        }
        if ((localObject1 == null) || (((String)localObject1).isEmpty())) {
          break label351;
        }
        Util.sendMessage("OnMessage", localBundle.toString());
        str5 = localBundle.getString("nid");
        if (str5 != null) {
          break label184;
        }
        Log.d(TAG, "No ID found in notification!");
      }
      label184:
      String str6 = Util.getApplicationName(this);
      String str7 = localBundle.getString("data");
      String str4 = null;
      String str3 = null;
      String str2 = null;
      Object localObject2 = null;
      try
      {
        JSONObject localJSONObject = new org/json/JSONObject;
        localJSONObject.<init>(str7);
        localObject2 = localJSONObject;
      }
      catch (JSONException localJSONException)
      {
        for (;;)
        {
          Log.d(TAG, "failed to parse data from message: " + localJSONException.getMessage());
        }
      }
      if (localObject2 != null)
      {
        str4 = ((JSONObject)localObject2).optString("cat");
        str3 = ((JSONObject)localObject2).optString("sum");
        str2 = ((JSONObject)localObject2).optString("group");
      }
      localObject1 = new NotificationNote(str5, str4, str6, (String)localObject1, (String)localObject1, str2, str3, localBundle);
      try
      {
        LocalNotificationManager.postNotification(this, (NotificationNote)localObject1);
      }
      catch (Exception localException)
      {
        Log.d(TAG, "Failed to post GCM notification: " + localException.getMessage());
      }
      continue;
      label351:
      Log.d(TAG, "Message was empty!");
      continue;
      label363:
      if (localException != null)
      {
        Log.d(TAG, "Unknown messageType: " + localException);
      }
      else
      {
        String str1 = paramIntent.getAction();
        if (str1 != null) {
          Log.d(TAG, "intent was not GCM: " + str1);
        } else {
          Log.d(TAG, "intent was not GCM: unknown action");
        }
      }
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\explodingbarrel\notifications\GCMIntentService.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */