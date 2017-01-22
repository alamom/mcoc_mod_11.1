package com.adjust.sdk;

import org.json.JSONObject;

public abstract interface IAttributionHandler
{
  public abstract void checkAttribution(JSONObject paramJSONObject);
  
  public abstract void getAttribution();
  
  public abstract void init(IActivityHandler paramIActivityHandler, ActivityPackage paramActivityPackage, boolean paramBoolean1, boolean paramBoolean2);
  
  public abstract void pauseSending();
  
  public abstract void resumeSending();
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\adjust\sdk\IAttributionHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */