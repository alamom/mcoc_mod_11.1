package com.kabam.wske.model;

import com.google.gson.annotations.SerializedName;

public class KabamAccountsConfig
{
  @SerializedName("accountAskBirthday")
  private Boolean accountAskBirthday = null;
  @SerializedName("accountKabamAccountCreation")
  private Boolean accountKabamAccountCreation = null;
  @SerializedName("accountTooYoungLockoutDays")
  private Integer accountTooYoungLockoutDays = null;
  
  public Boolean getAccountAskBirthday()
  {
    return this.accountAskBirthday;
  }
  
  public Boolean getAccountKabamAccountCreation()
  {
    return this.accountKabamAccountCreation;
  }
  
  public Integer getAccountTooYoungLockoutDays()
  {
    return this.accountTooYoungLockoutDays;
  }
  
  public void setAccountAskBirthday(Boolean paramBoolean)
  {
    this.accountAskBirthday = paramBoolean;
  }
  
  public void setAccountKabamAccountCreation(Boolean paramBoolean)
  {
    this.accountKabamAccountCreation = paramBoolean;
  }
  
  public void setAccountTooYoungLockoutDays(Integer paramInteger)
  {
    this.accountTooYoungLockoutDays = paramInteger;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("class KabamAccountsConfig {\n");
    localStringBuilder.append("  accountAskBirthday: ").append(this.accountAskBirthday).append("\n");
    localStringBuilder.append("  accountKabamAccountCreation: ").append(this.accountKabamAccountCreation).append("\n");
    localStringBuilder.append("  accountTooYoungLockoutDays: ").append(this.accountTooYoungLockoutDays).append("\n");
    localStringBuilder.append("}\n");
    return localStringBuilder.toString();
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\kabam\wske\model\KabamAccountsConfig.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */