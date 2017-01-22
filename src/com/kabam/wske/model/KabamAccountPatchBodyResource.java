package com.kabam.wske.model;

import com.google.gson.annotations.SerializedName;

public class KabamAccountPatchBodyResource
{
  @SerializedName("emailOpt")
  private String emailOpt = null;
  @SerializedName("userName")
  private String userName = null;
  
  public String getEmailOpt()
  {
    return this.emailOpt;
  }
  
  public String getUserName()
  {
    return this.userName;
  }
  
  public void setEmailOpt(String paramString)
  {
    this.emailOpt = paramString;
  }
  
  public void setUserName(String paramString)
  {
    this.userName = paramString;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("class KabamAccountPatchBodyResource {\n");
    localStringBuilder.append("  emailOpt: ").append(this.emailOpt).append("\n");
    localStringBuilder.append("  userName: ").append(this.userName).append("\n");
    localStringBuilder.append("}\n");
    return localStringBuilder.toString();
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\kabam\wske\model\KabamAccountPatchBodyResource.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */