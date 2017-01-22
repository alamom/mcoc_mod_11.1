package com.kabam.wske.model;

import com.google.gson.annotations.SerializedName;

public class LoyaltyEventResource
{
  @SerializedName("created")
  private String created = null;
  @SerializedName("event")
  private String event = null;
  @SerializedName("game")
  private String game = null;
  @SerializedName("message")
  private String message = null;
  @SerializedName("points")
  private Integer points = null;
  
  public String getCreated()
  {
    return this.created;
  }
  
  public String getEvent()
  {
    return this.event;
  }
  
  public String getGame()
  {
    return this.game;
  }
  
  public String getMessage()
  {
    return this.message;
  }
  
  public Integer getPoints()
  {
    return this.points;
  }
  
  public void setCreated(String paramString)
  {
    this.created = paramString;
  }
  
  public void setEvent(String paramString)
  {
    this.event = paramString;
  }
  
  public void setGame(String paramString)
  {
    this.game = paramString;
  }
  
  public void setMessage(String paramString)
  {
    this.message = paramString;
  }
  
  public void setPoints(Integer paramInteger)
  {
    this.points = paramInteger;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("class LoyaltyEventResource {\n");
    localStringBuilder.append("  created: ").append(this.created).append("\n");
    localStringBuilder.append("  event: ").append(this.event).append("\n");
    localStringBuilder.append("  game: ").append(this.game).append("\n");
    localStringBuilder.append("  message: ").append(this.message).append("\n");
    localStringBuilder.append("  points: ").append(this.points).append("\n");
    localStringBuilder.append("}\n");
    return localStringBuilder.toString();
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\kabam\wske\model\LoyaltyEventResource.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */