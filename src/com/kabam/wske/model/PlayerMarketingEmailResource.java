package com.kabam.wske.model;

import com.google.gson.annotations.SerializedName;

public class PlayerMarketingEmailResource
{
  @SerializedName("email")
  private String email = null;
  @SerializedName("opt")
  private String opt = null;
  
  public String getEmail()
  {
    return this.email;
  }
  
  public String getOpt()
  {
    return this.opt;
  }
  
  public void setEmail(String paramString)
  {
    this.email = paramString;
  }
  
  public void setOpt(String paramString)
  {
    this.opt = paramString;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("class PlayerMarketingEmailResource {\n");
    localStringBuilder.append("  email: ").append(this.email).append("\n");
    localStringBuilder.append("  opt: ").append(this.opt).append("\n");
    localStringBuilder.append("}\n");
    return localStringBuilder.toString();
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\kabam\wske\model\PlayerMarketingEmailResource.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */