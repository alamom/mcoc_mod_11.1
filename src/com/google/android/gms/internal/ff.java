package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.common.GooglePlayServicesUtil;

@ez
public final class ff
{
  public static gg a(Context paramContext, fi paramfi, a parama)
  {
    if (paramfi.lD.wG) {}
    for (paramContext = b(paramContext, paramfi, parama);; paramContext = c(paramContext, paramfi, parama)) {
      return paramContext;
    }
  }
  
  private static gg b(Context paramContext, fi paramfi, a parama)
  {
    gs.S("Fetching ad response from local ad request service.");
    paramContext = new fg.a(paramContext, paramfi, parama);
    paramContext.start();
    return paramContext;
  }
  
  private static gg c(Context paramContext, fi paramfi, a parama)
  {
    gs.S("Fetching ad response from remote ad request service.");
    if (GooglePlayServicesUtil.isGooglePlayServicesAvailable(paramContext) != 0) {
      gs.W("Failed to connect to remote ad request service.");
    }
    for (paramContext = null;; paramContext = new fg.b(paramContext, paramfi, parama)) {
      return paramContext;
    }
  }
  
  public static abstract interface a
  {
    public abstract void a(fk paramfk);
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\internal\ff.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */