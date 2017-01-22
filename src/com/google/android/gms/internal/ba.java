package com.google.android.gms.internal;

import com.google.android.gms.ads.doubleclick.AppEventListener;

@ez
public final class ba
  extends bf.a
{
  private final AppEventListener oi;
  
  public ba(AppEventListener paramAppEventListener)
  {
    this.oi = paramAppEventListener;
  }
  
  public void onAppEvent(String paramString1, String paramString2)
  {
    this.oi.onAppEvent(paramString1, paramString2);
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\internal\ba.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */