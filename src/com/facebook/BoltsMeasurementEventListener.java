package com.facebook;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import java.util.Iterator;
import java.util.Set;

public class BoltsMeasurementEventListener
  extends BroadcastReceiver
{
  private static final String BOLTS_MEASUREMENT_EVENT_PREFIX = "bf_";
  private static final String MEASUREMENT_EVENT_ARGS_KEY = "event_args";
  private static final String MEASUREMENT_EVENT_NAME_KEY = "event_name";
  private static final String MEASUREMENT_EVENT_NOTIFICATION_NAME = "com.parse.bolts.measurement_event";
  private static BoltsMeasurementEventListener _instance;
  private Context applicationContext;
  
  private BoltsMeasurementEventListener(Context paramContext)
  {
    this.applicationContext = paramContext.getApplicationContext();
  }
  
  private void close()
  {
    LocalBroadcastManager.getInstance(this.applicationContext).unregisterReceiver(this);
  }
  
  static BoltsMeasurementEventListener getInstance(Context paramContext)
  {
    if (_instance != null) {}
    for (paramContext = _instance;; paramContext = _instance)
    {
      return paramContext;
      _instance = new BoltsMeasurementEventListener(paramContext);
      _instance.open();
    }
  }
  
  private void open()
  {
    LocalBroadcastManager.getInstance(this.applicationContext).registerReceiver(this, new IntentFilter("com.parse.bolts.measurement_event"));
  }
  
  protected void finalize()
    throws Throwable
  {
    try
    {
      close();
      return;
    }
    finally
    {
      super.finalize();
    }
  }
  
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    paramContext = AppEventsLogger.newLogger(paramContext);
    String str1 = "bf_" + paramIntent.getStringExtra("event_name");
    Bundle localBundle = paramIntent.getBundleExtra("event_args");
    paramIntent = new Bundle();
    Iterator localIterator = localBundle.keySet().iterator();
    while (localIterator.hasNext())
    {
      String str2 = (String)localIterator.next();
      paramIntent.putString(str2.replaceAll("[^0-9a-zA-Z _-]", "-").replaceAll("^[ -]*", "").replaceAll("[ -]*$", ""), (String)localBundle.get(str2));
    }
    paramContext.logEvent(str1, paramIntent);
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\facebook\BoltsMeasurementEventListener.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */