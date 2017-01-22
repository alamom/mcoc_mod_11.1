package com.kabam.soda.wske;

public class CreateAccountData
{
  private String playerCertificate;
  private String playerId;
  
  public CreateAccountData(String paramString1, String paramString2)
  {
    this.playerId = paramString1;
    this.playerCertificate = paramString2;
  }
  
  public String getPlayerCertificate()
  {
    return this.playerCertificate;
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
    return String.format("<Player Id: %s, Player Certificate: %s>", new Object[] { this.playerId, this.playerCertificate });
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\kabam\soda\wske\CreateAccountData.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */