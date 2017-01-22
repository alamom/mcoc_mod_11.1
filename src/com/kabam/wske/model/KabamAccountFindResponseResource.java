package com.kabam.wske.model;

import com.google.gson.annotations.SerializedName;

public class KabamAccountFindResponseResource
{
  @SerializedName("authToken")
  private String authToken = null;
  @SerializedName("birthday")
  private String birthday = null;
  @SerializedName("email")
  private String email = null;
  @SerializedName("emailOpt")
  private String emailOpt = null;
  @SerializedName("expires")
  private Long expires = null;
  @SerializedName("kId")
  private String kId = null;
  @SerializedName("userName")
  private String userName = null;
  
  public String getAuthToken()
  {
    return this.authToken;
  }
  
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
  
  public Long getExpires()
  {
    return this.expires;
  }
  
  public String getKId()
  {
    return this.kId;
  }
  
  public String getUserName()
  {
    return this.userName;
  }
  
  public void setAuthToken(String paramString)
  {
    this.authToken = paramString;
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
  
  public void setExpires(Long paramLong)
  {
    this.expires = paramLong;
  }
  
  public void setKId(String paramString)
  {
    this.kId = paramString;
  }
  
  public void setUserName(String paramString)
  {
    this.userName = paramString;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("class KabamAccountFindResponseResource {\n");
    localStringBuilder.append("  authToken: ").append(this.authToken).append("\n");
    localStringBuilder.append("  birthday: ").append(this.birthday).append("\n");
    localStringBuilder.append("  email: ").append(this.email).append("\n");
    localStringBuilder.append("  emailOpt: ").append(this.emailOpt).append("\n");
    localStringBuilder.append("  expires: ").append(this.expires).append("\n");
    localStringBuilder.append("  kId: ").append(this.kId).append("\n");
    localStringBuilder.append("  userName: ").append(this.userName).append("\n");
    localStringBuilder.append("}\n");
    return localStringBuilder.toString();
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\kabam\wske\model\KabamAccountFindResponseResource.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */