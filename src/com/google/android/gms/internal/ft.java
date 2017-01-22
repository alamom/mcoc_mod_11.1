package com.google.android.gms.internal;

import java.util.Map;
import java.util.concurrent.Future;

@ez
public final class ft
{
  private gv md;
  private final Object mw = new Object();
  private String uq;
  private gk<fv> ur = new gk();
  public final by us = new by()
  {
    public void a(gv arg1, Map<String, String> paramAnonymousMap)
    {
      synchronized (ft.a(ft.this))
      {
        if (ft.b(ft.this).isDone()) {
          return;
        }
        fv localfv = new com/google/android/gms/internal/fv;
        localfv.<init>(1, paramAnonymousMap);
        paramAnonymousMap = new java/lang/StringBuilder;
        paramAnonymousMap.<init>();
        gs.W("Invalid " + localfv.getType() + " request error: " + localfv.cL());
        ft.b(ft.this).a(localfv);
      }
    }
  };
  public final by ut = new by()
  {
    public void a(gv paramAnonymousgv, Map<String, String> paramAnonymousMap)
    {
      for (;;)
      {
        fv localfv;
        String str;
        synchronized (ft.a(ft.this))
        {
          if (ft.b(ft.this).isDone()) {
            return;
          }
          localfv = new com/google/android/gms/internal/fv;
          localfv.<init>(-2, paramAnonymousMap);
          str = localfv.getUrl();
          if (str == null) {
            gs.W("URL missing in loadAdUrl GMSG.");
          }
        }
        if (str.contains("%40mediation_adapters%40"))
        {
          paramAnonymousgv = str.replaceAll("%40mediation_adapters%40", gf.a(paramAnonymousgv.getContext(), (String)paramAnonymousMap.get("check_adapters"), ft.c(ft.this)));
          localfv.setUrl(paramAnonymousgv);
          paramAnonymousMap = new java/lang/StringBuilder;
          paramAnonymousMap.<init>();
          gs.V("Ad request URL modified to " + paramAnonymousgv);
        }
        ft.b(ft.this).a(localfv);
      }
    }
  };
  
  public ft(String paramString)
  {
    this.uq = paramString;
  }
  
  public void b(gv paramgv)
  {
    this.md = paramgv;
  }
  
  public Future<fv> cK()
  {
    return this.ur;
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\internal\ft.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */