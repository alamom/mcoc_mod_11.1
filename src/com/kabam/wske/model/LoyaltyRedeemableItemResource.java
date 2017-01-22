package com.kabam.wske.model;

import com.google.gson.annotations.SerializedName;

public class LoyaltyRedeemableItemResource
{
  @SerializedName("developerData")
  private String developerData = null;
  @SerializedName("id")
  private String id = null;
  @SerializedName("quantity")
  private Integer quantity = null;
  
  public String getDeveloperData()
  {
    return this.developerData;
  }
  
  public String getId()
  {
    return this.id;
  }
  
  public Integer getQuantity()
  {
    return this.quantity;
  }
  
  public void setDeveloperData(String paramString)
  {
    this.developerData = paramString;
  }
  
  public void setId(String paramString)
  {
    this.id = paramString;
  }
  
  public void setQuantity(Integer paramInteger)
  {
    this.quantity = paramInteger;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("class LoyaltyRedeemableItemResource {\n");
    localStringBuilder.append("  developerData: ").append(this.developerData).append("\n");
    localStringBuilder.append("  id: ").append(this.id).append("\n");
    localStringBuilder.append("  quantity: ").append(this.quantity).append("\n");
    localStringBuilder.append("}\n");
    return localStringBuilder.toString();
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\kabam\wske\model\LoyaltyRedeemableItemResource.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */