package com.kabam.wske.model;

import com.google.gson.annotations.SerializedName;

public class RevenueEventResource
{
  @SerializedName("country")
  private String country = null;
  @SerializedName("currency")
  private String currency = null;
  @SerializedName("ip")
  private String ip = null;
  @SerializedName("metadata")
  private String metadata = null;
  @SerializedName("price")
  private Long price = null;
  @SerializedName("provider")
  private String provider = null;
  @SerializedName("receipt")
  private String receipt = null;
  @SerializedName("transactionId")
  private String transactionId = null;
  @SerializedName("transactionType")
  private String transactionType = null;
  
  public String getCountry()
  {
    return this.country;
  }
  
  public String getCurrency()
  {
    return this.currency;
  }
  
  public String getIp()
  {
    return this.ip;
  }
  
  public String getMetadata()
  {
    return this.metadata;
  }
  
  public Long getPrice()
  {
    return this.price;
  }
  
  public String getProvider()
  {
    return this.provider;
  }
  
  public String getReceipt()
  {
    return this.receipt;
  }
  
  public String getTransactionId()
  {
    return this.transactionId;
  }
  
  public String getTransactionType()
  {
    return this.transactionType;
  }
  
  public void setCountry(String paramString)
  {
    this.country = paramString;
  }
  
  public void setCurrency(String paramString)
  {
    this.currency = paramString;
  }
  
  public void setIp(String paramString)
  {
    this.ip = paramString;
  }
  
  public void setMetadata(String paramString)
  {
    this.metadata = paramString;
  }
  
  public void setPrice(Long paramLong)
  {
    this.price = paramLong;
  }
  
  public void setProvider(String paramString)
  {
    this.provider = paramString;
  }
  
  public void setReceipt(String paramString)
  {
    this.receipt = paramString;
  }
  
  public void setTransactionId(String paramString)
  {
    this.transactionId = paramString;
  }
  
  public void setTransactionType(String paramString)
  {
    this.transactionType = paramString;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("class RevenueEventResource {\n");
    localStringBuilder.append("  country: ").append(this.country).append("\n");
    localStringBuilder.append("  currency: ").append(this.currency).append("\n");
    localStringBuilder.append("  ip: ").append(this.ip).append("\n");
    localStringBuilder.append("  metadata: ").append(this.metadata).append("\n");
    localStringBuilder.append("  price: ").append(this.price).append("\n");
    localStringBuilder.append("  provider: ").append(this.provider).append("\n");
    localStringBuilder.append("  receipt: ").append(this.receipt).append("\n");
    localStringBuilder.append("  transactionId: ").append(this.transactionId).append("\n");
    localStringBuilder.append("  transactionType: ").append(this.transactionType).append("\n");
    localStringBuilder.append("}\n");
    return localStringBuilder.toString();
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\kabam\wske\model\RevenueEventResource.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */