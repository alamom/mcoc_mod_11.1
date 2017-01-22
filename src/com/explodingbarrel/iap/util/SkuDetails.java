package com.explodingbarrel.iap.util;

import org.json.JSONException;
import org.json.JSONObject;

public class SkuDetails
{
  private final String mDescription;
  private final String mItemType;
  private final String mJson;
  private final String mPrice;
  private final long mPriceAmountMicros;
  private final String mPriceCurrencyCode;
  private final String mSku;
  private final String mTitle;
  private final String mType;
  
  public SkuDetails(String paramString)
    throws JSONException
  {
    this("inapp", paramString);
  }
  
  public SkuDetails(String paramString1, String paramString2)
    throws JSONException
  {
    this.mItemType = paramString1;
    this.mJson = paramString2;
    paramString1 = new JSONObject(this.mJson);
    this.mSku = paramString1.optString("productId");
    this.mType = paramString1.optString("type");
    this.mPrice = paramString1.optString("price");
    this.mPriceAmountMicros = paramString1.optLong("price_amount_micros");
    this.mPriceCurrencyCode = paramString1.optString("price_currency_code");
    this.mTitle = paramString1.optString("title");
    this.mDescription = paramString1.optString("description");
  }
  
  public String getDescription()
  {
    return this.mDescription;
  }
  
  public String getJson()
  {
    return this.mJson;
  }
  
  public String getPrice()
  {
    return this.mPrice;
  }
  
  public long getPriceAmountMicros()
  {
    return this.mPriceAmountMicros;
  }
  
  public String getPriceCurrencyCode()
  {
    return this.mPriceCurrencyCode;
  }
  
  public String getSku()
  {
    return this.mSku;
  }
  
  public String getTitle()
  {
    return this.mTitle;
  }
  
  public String getType()
  {
    return this.mType;
  }
  
  public String toString()
  {
    return "SkuDetails:" + this.mJson;
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\explodingbarrel\iap\util\SkuDetails.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */