package com.google.android.gms.analytics;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

class p
  extends BroadcastReceiver
{
  static final String ya = p.class.getName();
  private final ae yb;
  
  p(ae paramae)
  {
    this.yb = paramae;
  }
  
  public static void A(Context paramContext)
  {
    Intent localIntent = new Intent("com.google.analytics.RADIO_POWERED");
    localIntent.addCategory(paramContext.getPackageName());
    localIntent.putExtra(ya, true);
    paramContext.sendBroadcast(localIntent);
  }
  
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    boolean bool1 = false;
    paramContext = paramIntent.getAction();
    if ("android.net.conn.CONNECTIVITY_CHANGE".equals(paramContext))
    {
      boolean bool2 = paramIntent.getBooleanExtra("noConnectivity", false);
      paramContext = this.yb;
      if (!bool2) {
        bool1 = true;
      }
      paramContext.A(bool1);
    }
    for (;;)
    {
      return;
      if (("com.google.analytics.RADIO_POWERED".equals(paramContext)) && (!paramIntent.hasExtra(ya))) {
        this.yb.ed();
      }
    }
  }
  
  public void z(Context paramContext)
  {
    IntentFilter localIntentFilter = new IntentFilter();
    localIntentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
    paramContext.registerReceiver(this, localIntentFilter);
    localIntentFilter = new IntentFilter();
    localIntentFilter.addAction("com.google.analytics.RADIO_POWERED");
    localIntentFilter.addCategory(paramContext.getPackageName());
    paramContext.registerReceiver(this, localIntentFilter);
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\analytics\p.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */