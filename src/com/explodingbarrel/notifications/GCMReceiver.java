package com.explodingbarrel.notifications;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.WakefulBroadcastReceiver;
import com.google.android.gms.gcm.GoogleCloudMessaging;

public class GCMReceiver
  extends WakefulBroadcastReceiver
{
  private static final String TAG = "GCMReceiverJAVA";
  
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    GoogleCloudMessaging.getInstance(paramContext).getMessageType(paramIntent);
    startWakefulService(paramContext, paramIntent.setComponent(new ComponentName(paramContext.getPackageName(), GCMIntentService.class.getName())));
    setResultCode(-1);
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\explodingbarrel\notifications\GCMReceiver.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */