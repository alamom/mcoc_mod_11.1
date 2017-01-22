package com.kabam.wske.model;

import com.google.gson.annotations.SerializedName;

public class ServedAdCampaignResource
{
  @SerializedName("assetUrl")
  private String assetUrl = null;
  @SerializedName("destinationUrl")
  private String destinationUrl = null;
  
  public String getAssetUrl()
  {
    return this.assetUrl;
  }
  
  public String getDestinationUrl()
  {
    return this.destinationUrl;
  }
  
  public void setAssetUrl(String paramString)
  {
    this.assetUrl = paramString;
  }
  
  public void setDestinationUrl(String paramString)
  {
    this.destinationUrl = paramString;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("class ServedAdCampaignResource {\n");
    localStringBuilder.append("  assetUrl: ").append(this.assetUrl).append("\n");
    localStringBuilder.append("  destinationUrl: ").append(this.destinationUrl).append("\n");
    localStringBuilder.append("}\n");
    return localStringBuilder.toString();
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\kabam\wske\model\ServedAdCampaignResource.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */