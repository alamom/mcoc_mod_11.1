package com.google.android.gms.tagmanager;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.google.android.gms.internal.c.j;
import java.io.FileNotFoundException;
import java.io.IOException;

class co
  implements Runnable
{
  private final String aoc;
  private volatile String aoy;
  private final bn aqr;
  private final String aqs;
  private bg<c.j> aqt;
  private volatile r aqu;
  private volatile String aqv;
  private final Context mContext;
  
  co(Context paramContext, String paramString, bn parambn, r paramr)
  {
    this.mContext = paramContext;
    this.aqr = parambn;
    this.aoc = paramString;
    this.aqu = paramr;
    this.aqs = ("/r?id=" + paramString);
    this.aoy = this.aqs;
    this.aqv = null;
  }
  
  public co(Context paramContext, String paramString, r paramr)
  {
    this(paramContext, paramString, new bn(), paramr);
  }
  
  private boolean oM()
  {
    NetworkInfo localNetworkInfo = ((ConnectivityManager)this.mContext.getSystemService("connectivity")).getActiveNetworkInfo();
    if ((localNetworkInfo == null) || (!localNetworkInfo.isConnected())) {
      bh.V("...no network connectivity");
    }
    for (boolean bool = false;; bool = true) {
      return bool;
    }
  }
  
  private void oN()
  {
    if (!oM()) {
      this.aqt.a(bg.a.apM);
    }
    for (;;)
    {
      return;
      bh.V("Start loading resource from network ...");
      String str = oO();
      bm localbm = this.aqr.ox();
      try
      {
        localObject3 = localbm.cD(str);
      }
      catch (FileNotFoundException localFileNotFoundException)
      {
        Object localObject2;
        localFileNotFoundException = localFileNotFoundException;
        StringBuilder localStringBuilder = new java/lang/StringBuilder;
        localStringBuilder.<init>();
        bh.W("No data is retrieved from the given url: " + str + ". Make sure container_id: " + this.aoc + " is correct.");
        this.aqt.a(bg.a.apO);
        localbm.close();
      }
      catch (IOException localIOException1)
      {
        Object localObject3 = new java/lang/StringBuilder;
        ((StringBuilder)localObject3).<init>();
        bh.d("Error when loading resources from url: " + str + " " + localIOException1.getMessage(), localIOException1);
        this.aqt.a(bg.a.apN);
        localbm.close();
        continue;
      }
      finally
      {
        localbm.close();
      }
    }
  }
  
  void a(bg<c.j> parambg)
  {
    this.aqt = parambg;
  }
  
  void cJ(String paramString)
  {
    bh.S("Setting previous container version: " + paramString);
    this.aqv = paramString;
  }
  
  void cu(String paramString)
  {
    if (paramString == null) {}
    for (this.aoy = this.aqs;; this.aoy = paramString)
    {
      return;
      bh.S("Setting CTFE URL path: " + paramString);
    }
  }
  
  String oO()
  {
    Object localObject2 = this.aqu.od() + this.aoy + "&v=a65833898";
    Object localObject1 = localObject2;
    if (this.aqv != null)
    {
      localObject1 = localObject2;
      if (!this.aqv.trim().equals("")) {
        localObject1 = (String)localObject2 + "&pv=" + this.aqv;
      }
    }
    localObject2 = localObject1;
    if (ce.oJ().oK().equals(ce.a.aqj)) {
      localObject2 = (String)localObject1 + "&gtm_debug=x";
    }
    return (String)localObject2;
  }
  
  public void run()
  {
    if (this.aqt == null) {
      throw new IllegalStateException("callback must be set before execute");
    }
    this.aqt.ob();
    oN();
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\tagmanager\co.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */