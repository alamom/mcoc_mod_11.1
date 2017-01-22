package com.kabam.wske.model;

import com.google.gson.annotations.SerializedName;

public class KabamAccountForgotBodyResource
{
  @SerializedName("email")
  private String email = null;
  
  public String getEmail()
  {
    return this.email;
  }
  
  public void setEmail(String paramString)
  {
    this.email = paramString;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("class KabamAccountForgotBodyResource {\n");
    localStringBuilder.append("  email: ").append(this.email).append("\n");
    localStringBuilder.append("}\n");
    return localStringBuilder.toString();
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\kabam\wske\model\KabamAccountForgotBodyResource.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */