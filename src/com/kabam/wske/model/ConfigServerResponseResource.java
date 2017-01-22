package com.kabam.wske.model;

import com.google.gson.annotations.SerializedName;

public class ConfigServerResponseResource
{
  @SerializedName("kabamConfig")
  private KabamAccountsConfig kabamConfig = null;
  @SerializedName("sodaConfig")
  private SodaConfig sodaConfig = null;
  
  public KabamAccountsConfig getKabamConfig()
  {
    return this.kabamConfig;
  }
  
  public SodaConfig getSodaConfig()
  {
    return this.sodaConfig;
  }
  
  public void setKabamConfig(KabamAccountsConfig paramKabamAccountsConfig)
  {
    this.kabamConfig = paramKabamAccountsConfig;
  }
  
  public void setSodaConfig(SodaConfig paramSodaConfig)
  {
    this.sodaConfig = paramSodaConfig;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("class ConfigServerResponseResource {\n");
    localStringBuilder.append("  kabamConfig: ").append(this.kabamConfig).append("\n");
    localStringBuilder.append("  sodaConfig: ").append(this.sodaConfig).append("\n");
    localStringBuilder.append("}\n");
    return localStringBuilder.toString();
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\kabam\wske\model\ConfigServerResponseResource.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */