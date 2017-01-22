package com.kabam.wske.model;

import com.google.gson.annotations.SerializedName;

public class LoyaltyRedemptionInput
{
  @SerializedName("developerPayload")
  private String developerPayload = null;
  @SerializedName("nonce")
  private String nonce = null;
  @SerializedName("productId")
  private String productId = null;
  
  public String getDeveloperPayload()
  {
    return this.developerPayload;
  }
  
  public String getNonce()
  {
    return this.nonce;
  }
  
  public String getProductId()
  {
    return this.productId;
  }
  
  public void setDeveloperPayload(String paramString)
  {
    this.developerPayload = paramString;
  }
  
  public void setNonce(String paramString)
  {
    this.nonce = paramString;
  }
  
  public void setProductId(String paramString)
  {
    this.productId = paramString;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("class LoyaltyRedemptionInput {\n");
    localStringBuilder.append("  developerPayload: ").append(this.developerPayload).append("\n");
    localStringBuilder.append("  nonce: ").append(this.nonce).append("\n");
    localStringBuilder.append("  productId: ").append(this.productId).append("\n");
    localStringBuilder.append("}\n");
    return localStringBuilder.toString();
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\kabam\wske\model\LoyaltyRedemptionInput.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */