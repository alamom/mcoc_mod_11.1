package com.amazon.cptplugins.exceptions.jsonutils;

public class AmazonError
{
  private final String error;
  
  public AmazonError(Exception paramException)
  {
    this.error = paramException.toString();
  }
  
  public String getError()
  {
    return this.error;
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\amazon\cptplugins\exceptions\jsonutils\AmazonError.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */