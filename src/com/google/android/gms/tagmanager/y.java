package com.google.android.gms.tagmanager;

import android.content.Context;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

class y
  implements aq
{
  private static y apb;
  private static final Object xz = new Object();
  private cg aos;
  private String apc;
  private String apd;
  private ar ape;
  
  private y(Context paramContext)
  {
    this(as.Z(paramContext), new cw());
  }
  
  y(ar paramar, cg paramcg)
  {
    this.ape = paramar;
    this.aos = paramcg;
  }
  
  public static aq X(Context paramContext)
  {
    synchronized (xz)
    {
      if (apb == null)
      {
        y localy = new com/google/android/gms/tagmanager/y;
        localy.<init>(paramContext);
        apb = localy;
      }
      paramContext = apb;
      return paramContext;
    }
  }
  
  public boolean cz(String paramString)
  {
    boolean bool = false;
    if (!this.aos.eJ()) {
      bh.W("Too many urls sent too quickly with the TagManagerSender, rate limiting invoked.");
    }
    for (;;)
    {
      return bool;
      Object localObject = paramString;
      if (this.apc != null)
      {
        localObject = paramString;
        if (this.apd == null) {}
      }
      try
      {
        localObject = new java/lang/StringBuilder;
        ((StringBuilder)localObject).<init>();
        localObject = this.apc + "?" + this.apd + "=" + URLEncoder.encode(paramString, "UTF-8");
        paramString = new java/lang/StringBuilder;
        paramString.<init>();
        bh.V("Sending wrapped url hit: " + (String)localObject);
        this.ape.cC((String)localObject);
        bool = true;
      }
      catch (UnsupportedEncodingException paramString)
      {
        bh.d("Error wrapping URL for testing.", paramString);
      }
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\tagmanager\y.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */