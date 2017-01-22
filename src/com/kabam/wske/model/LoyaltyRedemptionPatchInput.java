package com.kabam.wske.model;

import com.google.gson.annotations.SerializedName;

public class LoyaltyRedemptionPatchInput
{
  @SerializedName("status")
  private String status = null;
  
  public String getStatus()
  {
    return this.status;
  }
  
  public void setStatus(String paramString)
  {
    this.status = paramString;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("class LoyaltyRedemptionPatchInput {\n");
    localStringBuilder.append("  status: ").append(this.status).append("\n");
    localStringBuilder.append("}\n");
    return localStringBuilder.toString();
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\kabam\wske\model\LoyaltyRedemptionPatchInput.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */