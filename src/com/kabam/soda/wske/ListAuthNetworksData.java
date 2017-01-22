package com.kabam.soda.wske;

import com.kabam.soda.AuthSource;

public class ListAuthNetworksData
{
  private AuthSource authSource;
  private String playerId;
  
  public ListAuthNetworksData(String paramString, AuthSource paramAuthSource)
  {
    this.playerId = paramString;
    this.authSource = paramAuthSource;
  }
  
  public AuthSource getAuthSource()
  {
    return this.authSource;
  }
  
  public String getPlayerId()
  {
    return this.playerId;
  }
  
  public int hashCode()
  {
    return toString().hashCode();
  }
  
  public String toString()
  {
    return String.format("<Player Id: %s, Auth Source: %s>", new Object[] { this.playerId, this.authSource });
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\kabam\soda\wske\ListAuthNetworksData.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */