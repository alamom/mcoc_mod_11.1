package com.kabam.wske.model;

import com.google.gson.annotations.SerializedName;

public class TokenResource
{
  @SerializedName("clientId")
  private String clientId = null;
  @SerializedName("expires")
  private Long expires = null;
  @SerializedName("playerId")
  private String playerId = null;
  @SerializedName("token")
  private String token = null;
  
  public String getClientId()
  {
    return this.clientId;
  }
  
  public Long getExpires()
  {
    return this.expires;
  }
  
  public String getPlayerId()
  {
    return this.playerId;
  }
  
  public String getToken()
  {
    return this.token;
  }
  
  public void setClientId(String paramString)
  {
    this.clientId = paramString;
  }
  
  public void setExpires(Long paramLong)
  {
    this.expires = paramLong;
  }
  
  public void setPlayerId(String paramString)
  {
    this.playerId = paramString;
  }
  
  public void setToken(String paramString)
  {
    this.token = paramString;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("class TokenResource {\n");
    localStringBuilder.append("  clientId: ").append(this.clientId).append("\n");
    localStringBuilder.append("  expires: ").append(this.expires).append("\n");
    localStringBuilder.append("  playerId: ").append(this.playerId).append("\n");
    localStringBuilder.append("  token: ").append(this.token).append("\n");
    localStringBuilder.append("}\n");
    return localStringBuilder.toString();
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\kabam\wske\model\TokenResource.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */