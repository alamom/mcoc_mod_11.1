package com.kabam.wske.model;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;

public class AccountMergeListResource
{
  @SerializedName("additionalGamesPlayed")
  private List<String> additionalGamesPlayed = new ArrayList();
  
  public List<String> getAdditionalGamesPlayed()
  {
    return this.additionalGamesPlayed;
  }
  
  public void setAdditionalGamesPlayed(List<String> paramList)
  {
    this.additionalGamesPlayed = paramList;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("class AccountMergeListResource {\n");
    localStringBuilder.append("  additionalGamesPlayed: ").append(this.additionalGamesPlayed).append("\n");
    localStringBuilder.append("}\n");
    return localStringBuilder.toString();
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\kabam\wske\model\AccountMergeListResource.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */