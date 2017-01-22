package com.kabam.wske.model;

import com.google.gson.annotations.SerializedName;

public class EventResource
{
  @SerializedName("id")
  private Integer id = null;
  @SerializedName("points")
  private Integer points = null;
  
  public Integer getId()
  {
    return this.id;
  }
  
  public Integer getPoints()
  {
    return this.points;
  }
  
  public void setId(Integer paramInteger)
  {
    this.id = paramInteger;
  }
  
  public void setPoints(Integer paramInteger)
  {
    this.points = paramInteger;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("class EventResource {\n");
    localStringBuilder.append("  id: ").append(this.id).append("\n");
    localStringBuilder.append("  points: ").append(this.points).append("\n");
    localStringBuilder.append("}\n");
    return localStringBuilder.toString();
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\kabam\wske\model\EventResource.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */