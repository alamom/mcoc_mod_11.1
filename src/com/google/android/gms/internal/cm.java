package com.google.android.gms.internal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@ez
public final class cm
{
  public final List<cl> qd;
  public final long qe;
  public final List<String> qf;
  public final List<String> qg;
  public final List<String> qh;
  public final String qi;
  public final long qj;
  public int qk;
  public int ql;
  
  public cm(String paramString)
    throws JSONException
  {
    paramString = new JSONObject(paramString);
    if (gs.u(2)) {
      gs.V("Mediation Response JSON: " + paramString.toString(2));
    }
    JSONArray localJSONArray = paramString.getJSONArray("ad_networks");
    ArrayList localArrayList = new ArrayList(localJSONArray.length());
    int k = -1;
    int i = 0;
    while (i < localJSONArray.length())
    {
      cl localcl = new cl(localJSONArray.getJSONObject(i));
      localArrayList.add(localcl);
      int j = k;
      if (k < 0)
      {
        j = k;
        if (a(localcl)) {
          j = i;
        }
      }
      i++;
      k = j;
    }
    this.qk = k;
    this.ql = localJSONArray.length();
    this.qd = Collections.unmodifiableList(localArrayList);
    this.qi = paramString.getString("qdata");
    paramString = paramString.optJSONObject("settings");
    long l;
    if (paramString != null)
    {
      this.qe = paramString.optLong("ad_network_timeout_millis", -1L);
      this.qf = cr.a(paramString, "click_urls");
      this.qg = cr.a(paramString, "imp_urls");
      this.qh = cr.a(paramString, "nofill_urls");
      l = paramString.optLong("refresh", -1L);
      if (l > 0L) {
        l *= 1000L;
      }
    }
    for (this.qj = l;; this.qj = -1L)
    {
      return;
      l = -1L;
      break;
      this.qe = -1L;
      this.qf = null;
      this.qg = null;
      this.qh = null;
    }
  }
  
  private boolean a(cl paramcl)
  {
    paramcl = paramcl.pY.iterator();
    do
    {
      if (!paramcl.hasNext()) {
        break;
      }
    } while (!((String)paramcl.next()).equals("com.google.ads.mediation.admob.AdMobAdapter"));
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\internal\cm.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */