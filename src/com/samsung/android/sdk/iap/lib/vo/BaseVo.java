package com.samsung.android.sdk.iap.lib.vo;

import android.text.format.DateFormat;
import org.json.JSONException;
import org.json.JSONObject;

public class BaseVo
{
  private String mCurrencyCode;
  private String mCurrencyUnit;
  private String mItemDesc;
  private String mItemDownloadUrl;
  private String mItemId;
  private String mItemImageUrl;
  private String mItemName;
  private Double mItemPrice;
  private String mItemPriceString;
  
  public BaseVo() {}
  
  public BaseVo(String paramString)
  {
    try
    {
      JSONObject localJSONObject = new org/json/JSONObject;
      localJSONObject.<init>(paramString);
      setItemId(localJSONObject.optString("mItemId"));
      setItemName(localJSONObject.optString("mItemName"));
      setItemPrice(Double.valueOf(localJSONObject.optDouble("mItemPrice")));
      setCurrencyUnit(localJSONObject.optString("mCurrencyUnit"));
      setCurrencyCode(localJSONObject.optString("mCurrencyCode"));
      setItemDesc(localJSONObject.optString("mItemDesc"));
      setItemImageUrl(localJSONObject.optString("mItemImageUrl"));
      setItemDownloadUrl(localJSONObject.optString("mItemDownloadUrl"));
      setItemPriceString(localJSONObject.optString("mItemPriceString"));
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
    return "ItemId          : " + getItemId() + "\n" + "ItemName        : " + getItemName() + "\n" + "ItemPrice       : " + getItemPrice() + "\n" + "ItemPriceString : " + getItemPriceString() + "\n" + "CurrencyUnit    : " + getCurrencyUnit() + "\n" + "CurrencyCode    : " + getCurrencyCode() + "\n" + "ItemDesc        : " + getItemDesc() + "\n" + "ItemImageUrl    : " + getItemImageUrl() + "\n" + "ItemDownloadUrl : " + getItemDownloadUrl();
  }
  
  public String getCurrencyCode()
  {
    return this.mCurrencyCode;
  }
  
  public String getCurrencyUnit()
  {
    return this.mCurrencyUnit;
  }
  
  protected String getDateString(long paramLong)
  {
    try
    {
      String str1 = DateFormat.format("yyyy.MM.dd HH:mm:ss", paramLong).toString();
      return str1;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        localException.printStackTrace();
        String str2 = "";
      }
    }
  }
  
  public String getItemDesc()
  {
    return this.mItemDesc;
  }
  
  public String getItemDownloadUrl()
  {
    return this.mItemDownloadUrl;
  }
  
  public String getItemId()
  {
    return this.mItemId;
  }
  
  public String getItemImageUrl()
  {
    return this.mItemImageUrl;
  }
  
  public String getItemName()
  {
    return this.mItemName;
  }
  
  public Double getItemPrice()
  {
    return this.mItemPrice;
  }
  
  public String getItemPriceString()
  {
    return this.mItemPriceString;
  }
  
  public void setCurrencyCode(String paramString)
  {
    this.mCurrencyCode = paramString;
  }
  
  public void setCurrencyUnit(String paramString)
  {
    this.mCurrencyUnit = paramString;
  }
  
  public void setItemDesc(String paramString)
  {
    this.mItemDesc = paramString;
  }
  
  public void setItemDownloadUrl(String paramString)
  {
    this.mItemDownloadUrl = paramString;
  }
  
  public void setItemId(String paramString)
  {
    this.mItemId = paramString;
  }
  
  public void setItemImageUrl(String paramString)
  {
    this.mItemImageUrl = paramString;
  }
  
  public void setItemName(String paramString)
  {
    this.mItemName = paramString;
  }
  
  public void setItemPrice(Double paramDouble)
  {
    this.mItemPrice = paramDouble;
  }
  
  public void setItemPriceString(String paramString)
  {
    this.mItemPriceString = paramString;
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\samsung\android\sdk\iap\lib\vo\BaseVo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */