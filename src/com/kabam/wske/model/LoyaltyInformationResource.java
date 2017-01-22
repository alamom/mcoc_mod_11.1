package com.kabam.wske.model;

import com.google.gson.annotations.SerializedName;

public class LoyaltyInformationResource
{
  @SerializedName("points")
  private Integer points = null;
  
  public Integer getPoints()
  {
    return this.points;
  }
  
  public void setPoints(Integer paramInteger)
  {
    this.points = paramInteger;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("class LoyaltyInformationResource {\n");
    localStringBuilder.append("  points: ").append(this.points).append("\n");
    localStringBuilder.append("}\n");
    return localStringBuilder.toString();
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\kabam\wske\model\LoyaltyInformationResource.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */