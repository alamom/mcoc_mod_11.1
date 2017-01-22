package com.kabam.wske.model;

import com.google.gson.annotations.SerializedName;

public class AuthTokenResource
{
  @SerializedName("assocTypeProof")
  private String assocTypeProof = null;
  @SerializedName("authToken")
  private String authToken = null;
  @SerializedName("authTokenProof")
  private String authTokenProof = null;
  
  public String getAssocTypeProof()
  {
    return this.assocTypeProof;
  }
  
  public String getAuthToken()
  {
    return this.authToken;
  }
  
  public String getAuthTokenProof()
  {
    return this.authTokenProof;
  }
  
  public void setAssocTypeProof(String paramString)
  {
    this.assocTypeProof = paramString;
  }
  
  public void setAuthToken(String paramString)
  {
    this.authToken = paramString;
  }
  
  public void setAuthTokenProof(String paramString)
  {
    this.authTokenProof = paramString;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("class AuthTokenResource {\n");
    localStringBuilder.append("  assocTypeProof: ").append(this.assocTypeProof).append("\n");
    localStringBuilder.append("  authToken: ").append(this.authToken).append("\n");
    localStringBuilder.append("  authTokenProof: ").append(this.authTokenProof).append("\n");
    localStringBuilder.append("}\n");
    return localStringBuilder.toString();
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\kabam\wske\model\AuthTokenResource.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */