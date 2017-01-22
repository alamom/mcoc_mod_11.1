package com.google.ads.mediation.customevent;

import android.app.Activity;
import com.google.ads.mediation.MediationAdRequest;

@Deprecated
public abstract interface CustomEventInterstitial
  extends CustomEvent
{
  public abstract void requestInterstitialAd(CustomEventInterstitialListener paramCustomEventInterstitialListener, Activity paramActivity, String paramString1, String paramString2, MediationAdRequest paramMediationAdRequest, Object paramObject);
  
  public abstract void showInterstitial();
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\ads\mediation\customevent\CustomEventInterstitial.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */