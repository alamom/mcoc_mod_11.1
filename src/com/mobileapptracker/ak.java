package com.mobileapptracker;

import android.widget.Toast;

final class ak
  implements Runnable
{
  ak(MobileAppTracker paramMobileAppTracker) {}
  
  public final void run()
  {
    Toast.makeText(this.a.mContext, "MAT Allow Duplicate Requests Enabled, do not release with this enabled!!", 1).show();
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\mobileapptracker\ak.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */