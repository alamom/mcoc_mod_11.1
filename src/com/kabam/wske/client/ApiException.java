package com.kabam.wske.client;

public class ApiException
  extends Exception
{
  int code = 0;
  String message = null;
  
  public ApiException() {}
  
  public ApiException(int paramInt, String paramString)
  {
    this.code = paramInt;
    this.message = paramString;
  }
  
  public int getCode()
  {
    return this.code;
  }
  
  public String getMessage()
  {
    return this.message;
  }
  
  public void setCode(int paramInt)
  {
    this.code = paramInt;
  }
  
  public void setMessage(String paramString)
  {
    this.message = paramString;
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\kabam\wske\client\ApiException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */