package com.samsung.android.sdk.iap.lib.vo;

import android.util.Log;
import org.json.JSONException;
import org.json.JSONObject;

public class InboxVo
  extends BaseVo
{
  private static final String TAG = InboxVo.class.getSimpleName();
  private String mJsonString = "";
  private String mPaymentId;
  private String mPurchaseDate;
  private String mPurchaseId;
  private String mSubscriptionEndDate;
  private String mType;
  
  public InboxVo(String paramString)
  {
    super(paramString);
    setJsonString(paramString);
    Log.i(TAG, this.mJsonString);
    try
    {
      JSONObject localJSONObject = new org/json/JSONObject;
      localJSONObject.<init>(paramString);
      setType(localJSONObject.optString("mType"));
      setPaymentId(localJSONObject.optString("mPaymentId"));
      setPurchaseId(localJSONObject.optString("mPurchaseId"));
      setPurchaseDate(getDateString(localJSONObject.optLong("mPurchaseDate")));
      setSubscriptionEndDate(getDateString(localJSONObject.optLong("mSubscriptionEndDate")));
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
    return str + "Type                : " + getType() + "\n" + "PurchaseDate        : " + getPurchaseDate() + "\n" + "SubscriptionEndDate : " + getSubscriptionEndDate() + "\n" + "PurchaseId          : " + getPurchaseId() + "\n" + "PaymentID           : " + getPaymentId();
  }
  
  public String getJsonString()
  {
    return this.mJsonString;
  }
  
  public String getPaymentId()
  {
    return this.mPaymentId;
  }
  
  public String getPurchaseDate()
  {
    return this.mPurchaseDate;
  }
  
  public String getPurchaseId()
  {
    return this.mPurchaseId;
  }
  
  public String getSubscriptionEndDate()
  {
    return this.mSubscriptionEndDate;
  }
  
  public String getType()
  {
    return this.mType;
  }
  
  public void setJsonString(String paramString)
  {
    this.mJsonString = paramString;
  }
  
  public void setPaymentId(String paramString)
  {
    this.mPaymentId = paramString;
  }
  
  public void setPurchaseDate(String paramString)
  {
    this.mPurchaseDate = paramString;
  }
  
  public void setPurchaseId(String paramString)
  {
    this.mPurchaseId = paramString;
  }
  
  public void setSubscriptionEndDate(String paramString)
  {
    this.mSubscriptionEndDate = paramString;
  }
  
  public void setType(String paramString)
  {
    this.mType = paramString;
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\samsung\android\sdk\iap\lib\vo\InboxVo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */