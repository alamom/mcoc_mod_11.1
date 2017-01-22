package com.kabam.wske.model;

import com.google.gson.annotations.SerializedName;

public class KabamAccountCreateBodyResource
{
  @SerializedName("birthday")
  private String birthday = null;
  @SerializedName("email")
  private String email = null;
  @SerializedName("emailOpt")
  private String emailOpt = null;
  @SerializedName("password")
  private String password = null;
  @SerializedName("playerId")
  private String playerId = null;
  @SerializedName("userName")
  private String userName = null;
  
  public String getBirthday()
  {
    return this.birthday;
  }
  
  public String getEmail()
  {
    return this.email;
  }
  
  public String getEmailOpt()
  {
    return this.emailOpt;
  }
  
  public String getPassword()
  {
    return this.password;
  }
  
  public String getPlayerId()
  {
    return this.playerId;
  }
  
  public String getUserName()
  {
    return this.userName;
  }
  
  public void setBirthday(String paramString)
  {
    this.birthday = paramString;
  }
  
  public void setEmail(String paramString)
  {
    this.email = paramString;
  }
  
  public void setEmailOpt(String paramString)
  {
    this.emailOpt = paramString;
  }
  
  public void setPassword(String paramString)
  {
    this.password = paramString;
  }
  
  public void setPlayerId(String paramString)
  {
    this.playerId = paramString;
  }
  
  public void setUserName(String paramString)
  {
    this.userName = paramString;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("class KabamAccountCreateBodyResource {\n");
    localStringBuilder.append("  birthday: ").append(this.birthday).append("\n");
    localStringBuilder.append("  email: ").append(this.email).append("\n");
    localStringBuilder.append("  emailOpt: ").append(this.emailOpt).append("\n");
    localStringBuilder.append("  password: ").append(this.password).append("\n");
    localStringBuilder.append("  playerId: ").append(this.playerId).append("\n");
    localStringBuilder.append("  userName: ").append(this.userName).append("\n");
    localStringBuilder.append("}\n");
    return localStringBuilder.toString();
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\kabam\wske\model\KabamAccountCreateBodyResource.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */