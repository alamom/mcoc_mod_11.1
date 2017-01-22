package com.explodingbarrel.notifications;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat.Builder;
import android.support.v4.app.NotificationCompat.InboxStyle;
import android.support.v4.app.NotificationCompat.Style;
import android.text.TextUtils;
import android.util.Log;
import java.util.Date;
import java.util.Random;

public class LocalNotificationManager
{
  public static final String CATEGORY_KEY = "cat";
  public static final String GROUP_KEY = "group";
  public static final String ID_KEY = "nid";
  public static final String MESSAGE_KEY = "payload";
  public static final String METADATA_KEY = "data";
  public static final String SUMMARY_KEY = "sum";
  public static final String TAG = "NotificationManager";
  public static final String TYPE_KEY = "type";
  private static int icon = -1;
  
  public static void cancelNotification(Context paramContext, String paramString)
  {
    AlarmManager localAlarmManager = (AlarmManager)paramContext.getSystemService("alarm");
    Intent localIntent = new Intent(paramContext, LocalNotificationReceiver.class);
    paramContext = PendingIntent.getBroadcast(paramContext, paramString.hashCode(), localIntent, 134217728);
    if (paramContext != null) {
      localAlarmManager.cancel(paramContext);
    }
    for (;;)
    {
      return;
      Log.d("NotificationManager", "Failed to cancel notification: " + paramString);
    }
  }
  
  public static void cancelNotifications(Context paramContext, String[] paramArrayOfString)
  {
    AlarmManager localAlarmManager = (AlarmManager)paramContext.getSystemService("alarm");
    Intent localIntent = new Intent(paramContext, LocalNotificationReceiver.class);
    int i = 0;
    if (i < paramArrayOfString.length)
    {
      String str = paramArrayOfString[i];
      PendingIntent localPendingIntent = PendingIntent.getBroadcast(paramContext, str.hashCode(), localIntent, 134217728);
      if (localPendingIntent != null) {
        localAlarmManager.cancel(localPendingIntent);
      }
      for (;;)
      {
        i++;
        break;
        Log.d("NotificationManager", "Failed to cancel notification: " + str);
      }
    }
  }
  
  public static void clearAllNotifications(Context paramContext)
  {
    ((NotificationManager)paramContext.getSystemService("notification")).cancelAll();
    NotificationStateManager.ClearAllState(paramContext);
  }
  
  public static void clearNotification(Context paramContext, String paramString)
  {
    ((NotificationManager)paramContext.getSystemService("notification")).cancel(paramString.hashCode());
  }
  
  public static void postNotification(Context paramContext, NotificationNote paramNotificationNote)
  {
    postNotification(paramContext, paramNotificationNote.id, paramNotificationNote.category, paramNotificationNote.title, paramNotificationNote.text, paramNotificationNote.ticker, paramNotificationNote.group, paramNotificationNote.summary, paramNotificationNote.data);
  }
  
  public static void postNotification(Context paramContext, String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, String paramString7, Bundle paramBundle)
  {
    String str = paramString1;
    if (paramString1 == null)
    {
      Log.d("NotificationManager", "Missing notification ID!");
      str = "someunknownid";
    }
    if ((paramString3 == null) || (paramString4 == null) || (paramString4 == null) || (paramString5 == null)) {
      Log.d("NotificationManager", "Missing notification text!");
    }
    for (;;)
    {
      return;
      if (icon < 0) {
        icon = paramContext.getResources().getIdentifier("notification_icon", "drawable", paramContext.getPackageName());
      }
      paramString1 = null;
      try
      {
        localObject = Class.forName("com.explodingbarrel.Activity");
        paramString1 = (String)localObject;
        localObject = paramString1;
        if (paramString1 == null) {
          Log.d("NotificationManager", "Game class was null!");
        }
      }
      catch (ClassNotFoundException localClassNotFoundException)
      {
        for (;;)
        {
          try
          {
            Object localObject = Class.forName("com.unity3d.player.UnityPlayerNativeActivity");
            paramString1 = new Intent(paramContext, (Class)localObject);
            paramString1.setAction("launch_app");
            if (paramBundle != null) {
              paramString1.putExtra("notificationData", paramBundle);
            }
            if ((Build.VERSION.SDK_INT >= 16) && (!TextUtils.isEmpty(paramString6))) {
              break label188;
            }
            postNotificationStandalone(paramContext, str, paramString2, null, paramString3, paramString4, paramString5, paramString1);
          }
          catch (ClassNotFoundException paramContext)
          {
            paramContext.printStackTrace();
          }
          localClassNotFoundException = localClassNotFoundException;
          localClassNotFoundException.printStackTrace();
        }
      }
      continue;
      label188:
      postNotificationGrouped(paramContext, str, paramString2, paramString6, paramString3, paramString4, paramString5, paramString7, paramString1);
    }
  }
  
  private static void postNotificationGrouped(Context paramContext, String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, String paramString7, Intent paramIntent)
  {
    NotificationNote[] arrayOfNotificationNote = NotificationStateManager.getNotificationsForGroup(paramContext, paramString3);
    if ((arrayOfNotificationNote == null) || (arrayOfNotificationNote.length == 0))
    {
      Log.d("NotificationManager", "Bailing early");
      postNotificationStandalone(paramContext, paramString1, paramString2, paramString3, paramString4, paramString5, paramString6, paramIntent);
    }
    for (;;)
    {
      return;
      for (int i = 0; i < arrayOfNotificationNote.length; i++) {
        clearNotification(paramContext, arrayOfNotificationNote[i].id);
      }
      clearNotification(paramContext, paramString3);
      Object localObject1 = PendingIntent.getActivity(paramContext, paramString2.hashCode(), paramIntent, 0);
      Object localObject2 = paramString7.replace("[x]", "" + (arrayOfNotificationNote.length + 1));
      paramString2 = (NotificationManager)paramContext.getSystemService("notification");
      paramString7 = new NotificationCompat.Builder(paramContext);
      paramString7.setSmallIcon(icon);
      paramString7.setContentTitle((CharSequence)localObject2);
      paramString7.setContentIntent((PendingIntent)localObject1);
      paramString7.setAutoCancel(true);
      paramString7.setDefaults(-1);
      paramString7.setGroup(paramString3);
      paramString7.setGroupSummary(true);
      localObject2 = new NotificationCompat.InboxStyle();
      for (i = 0; i < arrayOfNotificationNote.length; i++)
      {
        localObject1 = arrayOfNotificationNote[i];
        if (localObject1 != null) {
          ((NotificationCompat.InboxStyle)localObject2).addLine(((NotificationNote)localObject1).text);
        }
      }
      ((NotificationCompat.InboxStyle)localObject2).addLine(paramString5);
      ((NotificationCompat.InboxStyle)localObject2).setSummaryText(paramString4);
      paramString7.setStyle((NotificationCompat.Style)localObject2);
      paramString2.notify(paramString3.hashCode(), paramString7.build());
      NotificationStateManager.AddNotification(paramContext, paramString3, paramString1, paramString4, paramString6, paramString5, paramIntent.getExtras());
    }
  }
  
  private static void postNotificationStandalone(Context paramContext, String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, Intent paramIntent)
  {
    PendingIntent localPendingIntent = PendingIntent.getActivity(paramContext, paramString1.hashCode(), paramIntent, 0);
    Object localObject = new Intent(paramContext, LocalNotificationReceiver.class);
    ((Intent)localObject).setAction("delete_notification");
    ((Intent)localObject).putExtra("nid", paramString1);
    if (paramString2 != null) {
      ((Intent)localObject).putExtra("cat", paramString2);
    }
    if (!TextUtils.isEmpty(paramString3)) {
      ((Intent)localObject).putExtra("group", paramString3);
    }
    localObject = PendingIntent.getBroadcast(paramContext, new Random(System.currentTimeMillis()).nextInt(), (Intent)localObject, 0);
    NotificationManager localNotificationManager = (NotificationManager)paramContext.getSystemService("notification");
    paramString2 = new NotificationCompat.Builder(paramContext);
    paramString2.setSmallIcon(icon);
    paramString2.setContentTitle(paramString4);
    paramString2.setContentText(paramString5);
    paramString2.setContentIntent(localPendingIntent);
    paramString2.setTicker(paramString6);
    paramString2.setAutoCancel(true);
    paramString2.setDefaults(-1);
    paramString2.setDeleteIntent((PendingIntent)localObject);
    if (!TextUtils.isEmpty(paramString3)) {
      paramString2.setGroup(paramString3);
    }
    localNotificationManager.notify(paramString1.hashCode(), paramString2.build());
    NotificationStateManager.AddNotification(paramContext, paramString3, paramString1, paramString4, paramString6, paramString5, paramIntent.getExtras());
  }
  
  public static String scheduleLocalNotification(int paramInt, String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, Context paramContext)
  {
    int i = paramString3.hashCode();
    Log.i("NotificationManager", "Scheduling local notification " + i + " " + paramString3 + " " + paramInt + "  " + paramString2 + "  " + paramString6);
    paramString3 = new Intent(paramContext, LocalNotificationReceiver.class);
    paramString3.setAction("post_notification");
    paramString3.putExtra("payload", paramString2);
    paramString3.putExtra("data", paramString6);
    paramString3.putExtra("type", "local");
    paramString3.putExtra("cat", paramString1);
    paramString3.putExtra("sum", paramString5);
    paramString3.putExtra("group", paramString4);
    paramString2 = Util.jsonGetString(Util.parseJSON(paramString6), "nid");
    paramString1 = paramString2;
    if (paramString2 == null)
    {
      paramString1 = Integer.toString((int)new Date().getTime());
      Log.d("NotificationManager", "Notification ID not provided.  Generated id: " + paramString1);
    }
    paramString3.putExtra("nid", paramString1);
    long l = paramInt * 1000L;
    paramString2 = PendingIntent.getBroadcast(paramContext, i, paramString3, 134217728);
    paramString3 = (AlarmManager)paramContext.getSystemService("alarm");
    try
    {
      paramString3.set(0, l, paramString2);
      paramString2 = new java/lang/StringBuilder;
      paramString2.<init>();
      Log.d("NotificationManager", "Notification " + paramString1 + " scheduled  at " + l + "ms");
      return paramString1;
    }
    catch (Exception paramString2)
    {
      for (;;)
      {
        Log.d("NotificationManager", "Failed to schedule notification " + paramString1 + " - " + paramString2.getMessage());
      }
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\explodingbarrel\notifications\LocalNotificationManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */