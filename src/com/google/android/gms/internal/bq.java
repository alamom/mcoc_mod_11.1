package com.google.android.gms.internal;

import org.json.JSONException;
import org.json.JSONObject;

@ez
public class bq
{
  private u pw;
  private ah px;
  private JSONObject py;
  
  public bq(u paramu, ah paramah, JSONObject paramJSONObject)
  {
    this.pw = paramu;
    this.px = paramah;
    this.py = paramJSONObject;
  }
  
  public void as()
  {
    this.pw.aj();
  }
  
  public void b(String paramString, int paramInt)
  {
    try
    {
      JSONObject localJSONObject = new org/json/JSONObject;
      localJSONObject.<init>();
      localJSONObject.put("asset", paramInt);
      localJSONObject.put("template", paramString);
      paramString = new org/json/JSONObject;
      paramString.<init>();
      paramString.put("ad", this.py);
      paramString.put("click", localJSONObject);
      this.px.a("google.afma.nativeAds.handleClick", paramString);
      return;
    }
    catch (JSONException paramString)
    {
      for (;;)
      {
        gs.b("Unable to create click JSON.", paramString);
      }
    }
  }
  
  public static abstract interface a
  {
    public abstract void a(bq parambq);
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\internal\bq.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */