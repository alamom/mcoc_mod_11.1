package com.mobileapptracker;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;
import java.net.URLDecoder;

public class Tracker
  extends BroadcastReceiver
{
  public void onReceive(Context paramContext, Intent arg2)
  {
    if (??? != null) {}
    for (;;)
    {
      try
      {
        if (???.getAction().equals("com.android.vending.INSTALL_REFERRER"))
        {
          ??? = ???.getStringExtra("referrer");
          if (??? != null)
          {
            ??? = URLDecoder.decode(???, "UTF-8");
            StringBuilder localStringBuilder = new java/lang/StringBuilder;
            localStringBuilder.<init>("MAT received referrer ");
            Log.d("MobileAppTracker", ???);
            paramContext.getSharedPreferences("com.mobileapptracking", 0).edit().putString("mat_referrer", ???).commit();
            paramContext = MobileAppTracker.getInstance();
            if (paramContext != null)
            {
              paramContext.setInstallReferrer(???);
              if ((!paramContext.a) || (paramContext.c)) {}
            }
          }
        }
      }
      catch (Exception paramContext)
      {
        paramContext.printStackTrace();
        continue;
      }
      synchronized (paramContext.d)
      {
        paramContext.d.notifyAll();
        paramContext.c = true;
        return;
      }
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\mobileapptracker\Tracker.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */