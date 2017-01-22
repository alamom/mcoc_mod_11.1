package com.kabam.wske.model;

import com.google.gson.annotations.SerializedName;

public class KabamAccountLoginBodyResource
{
  @SerializedName("email")
  private String email = null;
  @SerializedName("password")
  private String password = null;
  @SerializedName("playerId")
  private String playerId = null;
  
  public String getEmail()
  {
    return this.email;
  }
  
  public String getPassword()
  {
    return this.password;
  }
  
  public String getPlayerId()
  {
    return this.playerId;
  }
  
  public void setEmail(String paramString)
  {
    this.email = paramString;
  }
  
  public void setPassword(String paramString)
  {
    this.password = paramString;
  }
  
  public void setPlayerId(String paramString)
  {
    this.playerId = paramString;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("class KabamAccountLoginBodyResource {\n");
    localStringBuilder.append("  email: ").append(this.email).append("\n");
    localStringBuilder.append("  password: ").append(this.password).append("\n");
    localStringBuilder.append("  playerId: ").append(this.playerId).append("\n");
    localStringBuilder.append("}\n");
    return localStringBuilder.toString();
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\kabam\wske\model\KabamAccountLoginBodyResource.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */