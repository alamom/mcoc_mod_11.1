package com.mobileapptracker;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;

final class aa
  implements Runnable
{
  aa(MobileAppTracker paramMobileAppTracker, Activity paramActivity) {}
  
  public final void run()
  {
    this.b.params.setReferralSource(this.a.getCallingPackage());
    Object localObject = this.a.getIntent();
    if (localObject != null)
    {
      localObject = ((Intent)localObject).getData();
      if (localObject != null) {
        this.b.params.setReferralUrl(((Uri)localObject).toString());
      }
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\mobileapptracker\aa.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */