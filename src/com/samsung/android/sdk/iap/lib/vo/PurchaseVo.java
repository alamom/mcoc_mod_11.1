package com.samsung.android.sdk.iap.lib.vo;

import android.util.Log;
import org.json.JSONException;
import org.json.JSONObject;

public class PurchaseVo
  extends BaseVo
{
  private static final String TAG = PurchaseVo.class.getSimpleName();
  private String mJsonString;
  private String mPaymentId;
  private String mPurchaseDate;
  private String mPurchaseId;
  private String mVerifyUrl;
  
  public PurchaseVo(String paramString)
  {
    super(paramString);
    setJsonString(paramString);
    Log.i(TAG, this.mJsonString);
    try
    {
      JSONObject localJSONObject = new org/json/JSONObject;
      localJSONObject.<init>(paramString);
      setPaymentId(localJSONObject.optString("mPaymentId"));
      setPurchaseId(localJSONObject.optString("mPurchaseId"));
      setPurchaseDate(getDateString(localJSONObject.optLong("mPurchaseDate")));
      setVerifyUrl(localJSONObject.optString("mVerifyUrl"));
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
    return str + "PaymentID    : " + getPaymentId() + "\n" + "PurchaseId   : " + getPurchaseId() + "\n" + "PurchaseDate : " + getPurchaseDate() + "\n" + "VerifyUrl    : " + getVerifyUrl();
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
  
  public String getVerifyUrl()
  {
    return this.mVerifyUrl;
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
  
  public void setVerifyUrl(String paramString)
  {
    this.mVerifyUrl = paramString;
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\samsung\android\sdk\iap\lib\vo\PurchaseVo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */