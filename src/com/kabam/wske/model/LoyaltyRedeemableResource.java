package com.kabam.wske.model;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;

public class LoyaltyRedeemableResource
{
  @SerializedName("canRedeem")
  private Boolean canRedeem = null;
  @SerializedName("clientId")
  private String clientId = null;
  @SerializedName("created")
  private Long created = null;
  @SerializedName("description")
  private String description = null;
  @SerializedName("iconUrl")
  private String iconUrl = null;
  @SerializedName("inGameCurrency")
  private Integer inGameCurrency = null;
  @SerializedName("items")
  private List<LoyaltyRedeemableItemResource> items = new ArrayList();
  @SerializedName("price")
  private Integer price = null;
  @SerializedName("productId")
  private String productId = null;
  @SerializedName("updated")
  private Long updated = null;
  
  public Boolean getCanRedeem()
  {
    return this.canRedeem;
  }
  
  public String getClientId()
  {
    return this.clientId;
  }
  
  public Long getCreated()
  {
    return this.created;
  }
  
  public String getDescription()
  {
    return this.description;
  }
  
  public String getIconUrl()
  {
    return this.iconUrl;
  }
  
  public Integer getInGameCurrency()
  {
    return this.inGameCurrency;
  }
  
  public List<LoyaltyRedeemableItemResource> getItems()
  {
    return this.items;
  }
  
  public Integer getPrice()
  {
    return this.price;
  }
  
  public String getProductId()
  {
    return this.productId;
  }
  
  public Long getUpdated()
  {
    return this.updated;
  }
  
  public void setCanRedeem(Boolean paramBoolean)
  {
    this.canRedeem = paramBoolean;
  }
  
  public void setClientId(String paramString)
  {
    this.clientId = paramString;
  }
  
  public void setCreated(Long paramLong)
  {
    this.created = paramLong;
  }
  
  public void setDescription(String paramString)
  {
    this.description = paramString;
  }
  
  public void setIconUrl(String paramString)
  {
    this.iconUrl = paramString;
  }
  
  public void setInGameCurrency(Integer paramInteger)
  {
    this.inGameCurrency = paramInteger;
  }
  
  public void setItems(List<LoyaltyRedeemableItemResource> paramList)
  {
    this.items = paramList;
  }
  
  public void setPrice(Integer paramInteger)
  {
    this.price = paramInteger;
  }
  
  public void setProductId(String paramString)
  {
    this.productId = paramString;
  }
  
  public void setUpdated(Long paramLong)
  {
    this.updated = paramLong;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("class LoyaltyRedeemableResource {\n");
    localStringBuilder.append("  canRedeem: ").append(this.canRedeem).append("\n");
    localStringBuilder.append("  clientId: ").append(this.clientId).append("\n");
    localStringBuilder.append("  created: ").append(this.created).append("\n");
    localStringBuilder.append("  description: ").append(this.description).append("\n");
    localStringBuilder.append("  iconUrl: ").append(this.iconUrl).append("\n");
    localStringBuilder.append("  inGameCurrency: ").append(this.inGameCurrency).append("\n");
    localStringBuilder.append("  items: ").append(this.items).append("\n");
    localStringBuilder.append("  price: ").append(this.price).append("\n");
    localStringBuilder.append("  productId: ").append(this.productId).append("\n");
    localStringBuilder.append("  updated: ").append(this.updated).append("\n");
    localStringBuilder.append("}\n");
    return localStringBuilder.toString();
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\kabam\wske\model\LoyaltyRedeemableResource.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */