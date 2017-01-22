package com.kabam.wske.model;

import com.google.gson.annotations.SerializedName;

public class LoyaltyRedemptionReceipt
{
  @SerializedName("receipt")
  private String receipt = null;
  @SerializedName("signature")
  private String signature = null;
  
  public String getReceipt()
  {
    return this.receipt;
  }
  
  public String getSignature()
  {
    return this.signature;
  }
  
  public void setReceipt(String paramString)
  {
    this.receipt = paramString;
  }
  
  public void setSignature(String paramString)
  {
    this.signature = paramString;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("class LoyaltyRedemptionReceipt {\n");
    localStringBuilder.append("  receipt: ").append(this.receipt).append("\n");
    localStringBuilder.append("  signature: ").append(this.signature).append("\n");
    localStringBuilder.append("}\n");
    return localStringBuilder.toString();
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\kabam\wske\model\LoyaltyRedemptionReceipt.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */