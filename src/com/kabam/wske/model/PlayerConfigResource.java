package com.kabam.wske.model;

import com.google.gson.annotations.SerializedName;

public class PlayerConfigResource
{
  @SerializedName("clientName")
  private String clientName = null;
  @SerializedName("facebookKey")
  private Long facebookKey = null;
  @SerializedName("facebookPermissions")
  private String facebookPermissions = null;
  @SerializedName("facebookSecret")
  private String facebookSecret = null;
  @SerializedName("googleKey")
  private String googleKey = null;
  @SerializedName("googlePermissions")
  private String googlePermissions = null;
  @SerializedName("googleSecret")
  private String googleSecret = null;
  @SerializedName("loyaltyEventRetryInterval")
  private Long loyaltyEventRetryInterval = null;
  @SerializedName("loyaltyEventRetryTimeout")
  private Long loyaltyEventRetryTimeout = null;
  @SerializedName("maintenanceMessage")
  private String maintenanceMessage = null;
  @SerializedName("maintenanceMode")
  private Boolean maintenanceMode = null;
  @SerializedName("maintenanceTitle")
  private String maintenanceTitle = null;
  @SerializedName("marketingEmail")
  private String marketingEmail = null;
  @SerializedName("marketingOpt")
  private String marketingOpt = null;
  @SerializedName("marketingOptDefault")
  private String marketingOptDefault = null;
  @SerializedName("moreGamesMenu")
  private Boolean moreGamesMenu = null;
  @SerializedName("rewardsFTE")
  private Boolean rewardsFTE = null;
  @SerializedName("rewardsMenu")
  private Boolean rewardsMenu = null;
  @SerializedName("rewardsNotifications")
  private Boolean rewardsNotifications = null;
  
  public String getClientName()
  {
    return this.clientName;
  }
  
  public Long getFacebookKey()
  {
    return this.facebookKey;
  }
  
  public String getFacebookPermissions()
  {
    return this.facebookPermissions;
  }
  
  public String getFacebookSecret()
  {
    return this.facebookSecret;
  }
  
  public String getGoogleKey()
  {
    return this.googleKey;
  }
  
  public String getGooglePermissions()
  {
    return this.googlePermissions;
  }
  
  public String getGoogleSecret()
  {
    return this.googleSecret;
  }
  
  public Long getLoyaltyEventRetryInterval()
  {
    return this.loyaltyEventRetryInterval;
  }
  
  public Long getLoyaltyEventRetryTimeout()
  {
    return this.loyaltyEventRetryTimeout;
  }
  
  public String getMaintenanceMessage()
  {
    return this.maintenanceMessage;
  }
  
  public Boolean getMaintenanceMode()
  {
    return this.maintenanceMode;
  }
  
  public String getMaintenanceTitle()
  {
    return this.maintenanceTitle;
  }
  
  public String getMarketingEmail()
  {
    return this.marketingEmail;
  }
  
  public String getMarketingOpt()
  {
    return this.marketingOpt;
  }
  
  public String getMarketingOptDefault()
  {
    return this.marketingOptDefault;
  }
  
  public Boolean getMoreGamesMenu()
  {
    return this.moreGamesMenu;
  }
  
  public Boolean getRewardsFTE()
  {
    return this.rewardsFTE;
  }
  
  public Boolean getRewardsMenu()
  {
    return this.rewardsMenu;
  }
  
  public Boolean getRewardsNotifications()
  {
    return this.rewardsNotifications;
  }
  
  public void setClientName(String paramString)
  {
    this.clientName = paramString;
  }
  
  public void setFacebookKey(Long paramLong)
  {
    this.facebookKey = paramLong;
  }
  
  public void setFacebookPermissions(String paramString)
  {
    this.facebookPermissions = paramString;
  }
  
  public void setFacebookSecret(String paramString)
  {
    this.facebookSecret = paramString;
  }
  
  public void setGoogleKey(String paramString)
  {
    this.googleKey = paramString;
  }
  
  public void setGooglePermissions(String paramString)
  {
    this.googlePermissions = paramString;
  }
  
  public void setGoogleSecret(String paramString)
  {
    this.googleSecret = paramString;
  }
  
  public void setLoyaltyEventRetryInterval(Long paramLong)
  {
    this.loyaltyEventRetryInterval = paramLong;
  }
  
  public void setLoyaltyEventRetryTimeout(Long paramLong)
  {
    this.loyaltyEventRetryTimeout = paramLong;
  }
  
  public void setMaintenanceMessage(String paramString)
  {
    this.maintenanceMessage = paramString;
  }
  
  public void setMaintenanceMode(Boolean paramBoolean)
  {
    this.maintenanceMode = paramBoolean;
  }
  
  public void setMaintenanceTitle(String paramString)
  {
    this.maintenanceTitle = paramString;
  }
  
  public void setMarketingEmail(String paramString)
  {
    this.marketingEmail = paramString;
  }
  
  public void setMarketingOpt(String paramString)
  {
    this.marketingOpt = paramString;
  }
  
  public void setMarketingOptDefault(String paramString)
  {
    this.marketingOptDefault = paramString;
  }
  
  public void setMoreGamesMenu(Boolean paramBoolean)
  {
    this.moreGamesMenu = paramBoolean;
  }
  
  public void setRewardsFTE(Boolean paramBoolean)
  {
    this.rewardsFTE = paramBoolean;
  }
  
  public void setRewardsMenu(Boolean paramBoolean)
  {
    this.rewardsMenu = paramBoolean;
  }
  
  public void setRewardsNotifications(Boolean paramBoolean)
  {
    this.rewardsNotifications = paramBoolean;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("class PlayerConfigResource {\n");
    localStringBuilder.append("  clientName: ").append(this.clientName).append("\n");
    localStringBuilder.append("  facebookKey: ").append(this.facebookKey).append("\n");
    localStringBuilder.append("  facebookPermissions: ").append(this.facebookPermissions).append("\n");
    localStringBuilder.append("  facebookSecret: ").append(this.facebookSecret).append("\n");
    localStringBuilder.append("  googleKey: ").append(this.googleKey).append("\n");
    localStringBuilder.append("  googlePermissions: ").append(this.googlePermissions).append("\n");
    localStringBuilder.append("  googleSecret: ").append(this.googleSecret).append("\n");
    localStringBuilder.append("  loyaltyEventRetryInterval: ").append(this.loyaltyEventRetryInterval).append("\n");
    localStringBuilder.append("  loyaltyEventRetryTimeout: ").append(this.loyaltyEventRetryTimeout).append("\n");
    localStringBuilder.append("  maintenanceMessage: ").append(this.maintenanceMessage).append("\n");
    localStringBuilder.append("  maintenanceMode: ").append(this.maintenanceMode).append("\n");
    localStringBuilder.append("  maintenanceTitle: ").append(this.maintenanceTitle).append("\n");
    localStringBuilder.append("  marketingEmail: ").append(this.marketingEmail).append("\n");
    localStringBuilder.append("  marketingOpt: ").append(this.marketingOpt).append("\n");
    localStringBuilder.append("  marketingOptDefault: ").append(this.marketingOptDefault).append("\n");
    localStringBuilder.append("  moreGamesMenu: ").append(this.moreGamesMenu).append("\n");
    localStringBuilder.append("  rewardsFTE: ").append(this.rewardsFTE).append("\n");
    localStringBuilder.append("  rewardsMenu: ").append(this.rewardsMenu).append("\n");
    localStringBuilder.append("  rewardsNotifications: ").append(this.rewardsNotifications).append("\n");
    localStringBuilder.append("}\n");
    return localStringBuilder.toString();
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\kabam\wske\model\PlayerConfigResource.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */