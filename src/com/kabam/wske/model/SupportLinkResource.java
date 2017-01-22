package com.kabam.wske.model;

import com.google.gson.annotations.SerializedName;

public class SupportLinkResource
{
  @SerializedName("url")
  private String url = null;
  
  public String getUrl()
  {
    return this.url;
  }
  
  public void setUrl(String paramString)
  {
    this.url = paramString;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("class SupportLinkResource {\n");
    localStringBuilder.append("  url: ").append(this.url).append("\n");
    localStringBuilder.append("}\n");
    return localStringBuilder.toString();
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\kabam\wske\model\SupportLinkResource.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */