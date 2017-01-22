package com.samsung.android.sdk.iap.lib.vo;

import android.util.Log;
import org.json.JSONException;
import org.json.JSONObject;

public class VerificationVo
{
  private static final String TAG = VerificationVo.class.getSimpleName();
  private String mItemDesc;
  private String mItemId;
  private String mItemName;
  private String mPaymentAmount;
  private String mPaymentId;
  private String mPurchaseDate;
  private String mStatus;
  
  public VerificationVo(String paramString)
  {
    try
    {
      JSONObject localJSONObject = new org/json/JSONObject;
      localJSONObject.<init>(paramString);
      Log.i(TAG, localJSONObject.toString(4));
      setItemId(localJSONObject.optString("itemId"));
      setItemName(localJSONObject.optString("itemName"));
      setItemDesc(localJSONObject.optString("itemDesc"));
      setPurchaseDate(localJSONObject.optString("purchaseDate"));
      setPaymentId(localJSONObject.optString("paymentId"));
      setPaymentAmount(localJSONObject.optString("paymentAmount"));
      setStatus(localJSONObject.optString("status"));
      Log.i(TAG, dump());
      return;
    }
    catch (JSONException paramString)
    {
      for (;;)
      {
        paramString.printStackTrace();
      }
    }
    catch (Exception paramString)
    {
      for (;;)
      {
        paramString.printStackTrace();
      }
    }
  }
  
  public String dump()
  {
    return "ItemId        : " + getItemId() + "\n" + "ItemName      : " + getItemName() + "\n" + "ItemDesc      : " + getItemDesc() + "\n" + "PurchaseDate  : " + getPurchaseDate() + "\n" + "PaymentId     : " + getPaymentId() + "\n" + "PaymentAmount : " + getPaymentAmount() + "\n" + "Status        : " + getStatus();
  }
  
  public String getItemDesc()
  {
    return this.mItemDesc;
  }
  
  public String getItemId()
  {
    return this.mItemId;
  }
  
  public String getItemName()
  {
    return this.mItemName;
  }
  
  public String getPaymentAmount()
  {
    return this.mPaymentAmount;
  }
  
  public String getPaymentId()
  {
    return this.mPaymentId;
  }
  
  public String getPurchaseDate()
  {
    return this.mPurchaseDate;
  }
  
  public String getStatus()
  {
    return this.mStatus;
  }
  
  public void setItemDesc(String paramString)
  {
    this.mItemDesc = paramString;
  }
  
  public void setItemId(String paramString)
  {
    this.mItemId = paramString;
  }
  
  public void setItemName(String paramString)
  {
    this.mItemName = paramString;
  }
  
  public void setPaymentAmount(String paramString)
  {
    this.mPaymentAmount = paramString;
  }
  
  public void setPaymentId(String paramString)
  {
    this.mPaymentId = paramString;
  }
  
  public void setPurchaseDate(String paramString)
  {
    this.mPurchaseDate = paramString;
  }
  
  public void setStatus(String paramString)
  {
    this.mStatus = paramString;
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\samsung\android\sdk\iap\lib\vo\VerificationVo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */