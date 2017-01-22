package com.google.android.gms.ads.mediation.admob;

import android.os.Bundle;
import com.google.ads.mediation.NetworkExtras;

@Deprecated
public final class AdMobExtras
  implements NetworkExtras
{
  private final Bundle mExtras;
  
  public AdMobExtras(Bundle paramBundle)
  {
    if (paramBundle != null) {}
    for (paramBundle = new Bundle(paramBundle);; paramBundle = null)
    {
      this.mExtras = paramBundle;
      return;
    }
  }
  
  public Bundle getExtras()
  {
    return this.mExtras;
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\ads\mediation\admob\AdMobExtras.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */