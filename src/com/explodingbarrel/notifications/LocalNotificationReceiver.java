package com.explodingbarrel.notifications;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class LocalNotificationReceiver
  extends BroadcastReceiver
{
  private static final String TAG = "LocalNotificationReceiver";
  
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    Object localObject;
    if (paramIntent != null)
    {
      localObject = paramIntent.getAction();
      if ((localObject != "post_notification") && (localObject != null)) {
        break label88;
      }
      localObject = paramIntent.getExtras();
      if (localObject != null)
      {
        String str2 = Util.getApplicationName(paramContext);
        String str1 = ((Bundle)localObject).getString("nid");
        paramIntent = ((Bundle)localObject).getString("payload");
        paramIntent = new NotificationNote(str1, ((Bundle)localObject).getString("cat"), str2, paramIntent, paramIntent, ((Bundle)localObject).getString("group"), ((Bundle)localObject).getString("sum"), (Bundle)localObject);
      }
    }
    try
    {
      LocalNotificationManager.postNotification(paramContext, paramIntent);
      for (;;)
      {
        return;
        label88:
        if (localObject == "delete_notification")
        {
          localObject = paramIntent.getExtras();
          if (localObject != null) {
            try
            {
              paramIntent = ((Bundle)localObject).getString("nid");
              NotificationStateManager.RemoveNotification(paramContext, ((Bundle)localObject).getString("group"), paramIntent);
            }
            catch (Exception paramContext) {}
          }
        }
      }
    }
    catch (Exception paramContext)
    {
      for (;;) {}
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\explodingbarrel\notifications\LocalNotificationReceiver.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */