package com.samsung.android.sdk.iap.lib.vo;

import android.util.Log;
import org.json.JSONException;
import org.json.JSONObject;

public class ItemVo
  extends BaseVo
{
  private static final String TAG = ItemVo.class.getSimpleName();
  private String mJsonString;
  private String mSubscriptionDurationMultiplier;
  private String mSubscriptionDurationUnit;
  private String mType;
  
  public ItemVo() {}
  
  public ItemVo(String paramString)
  {
    super(paramString);
    setJsonString(paramString);
    Log.i(TAG, this.mJsonString);
    try
    {
      JSONObject localJSONObject = new org/json/JSONObject;
      localJSONObject.<init>(paramString);
      setType(localJSONObject.optString("mType"));
      setSubscriptionDurationUnit(localJSONObject.optString("mSubscriptionDurationUnit"));
      setSubscriptionDurationMultiplier(localJSONObject.optString("mSubscriptionDurationMultiplier"));
      return;
    }
    catch (JSONException paramString)
    {
      for (;;)
      {
        paramString.printStackTrace();
      }
    }
  }
  
  public String dump()
  {
    String str = super.dump() + "\n";
    return str + "Type : " + getType() + "\n" + "SubscriptionDurationUnit : " + getSubscriptionDurationUnit() + "\n" + "SubscriptionDurationMultiplier : " + getSubscriptionDurationMultiplier();
  }
  
  public String getJsonString()
  {
    return this.mJsonString;
  }
  
  public String getSubscriptionDurationMultiplier()
  {
    return this.mSubscriptionDurationMultiplier;
  }
  
  public String getSubscriptionDurationUnit()
  {
    return this.mSubscriptionDurationUnit;
  }
  
  public String getType()
  {
    return this.mType;
  }
  
  public void setJsonString(String paramString)
  {
    this.mJsonString = paramString;
  }
  
  public void setSubscriptionDurationMultiplier(String paramString)
  {
    this.mSubscriptionDurationMultiplier = paramString;
  }
  
  public void setSubscriptionDurationUnit(String paramString)
  {
    this.mSubscriptionDurationUnit = paramString;
  }
  
  public void setType(String paramString)
  {
    this.mType = paramString;
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\samsung\android\sdk\iap\lib\vo\ItemVo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */