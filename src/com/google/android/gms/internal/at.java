package com.google.android.gms.internal;

import com.google.android.gms.ads.AdListener;

@ez
public final class at
  extends bc.a
{
  private final AdListener nR;
  
  public at(AdListener paramAdListener)
  {
    this.nR = paramAdListener;
  }
  
  public void onAdClosed()
  {
    this.nR.onAdClosed();
  }
  
  public void onAdFailedToLoad(int paramInt)
  {
    this.nR.onAdFailedToLoad(paramInt);
  }
  
  public void onAdLeftApplication()
  {
    this.nR.onAdLeftApplication();
  }
  
  public void onAdLoaded()
  {
    this.nR.onAdLoaded();
  }
  
  public void onAdOpened()
  {
    this.nR.onAdOpened();
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\internal\at.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */