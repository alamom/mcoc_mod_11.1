package com.mobileapptracker;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

final class h
  extends BroadcastReceiver
{
  h(MobileAppTracker paramMobileAppTracker) {}
  
  public final void onReceive(Context paramContext, Intent paramIntent)
  {
    if (this.a.isRegistered) {
      this.a.dumpQueue();
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\mobileapptracker\h.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */