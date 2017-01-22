package com.explodingbarrel.iap.util;

public class IabResult
{
  String mMessage;
  int mResponse;
  
  public IabResult(int paramInt, String paramString)
  {
    this.mResponse = paramInt;
    if ((paramString == null) || (paramString.trim().length() == 0)) {}
    for (this.mMessage = IabHelper.getResponseDesc(paramInt);; this.mMessage = (paramString + " (response: " + IabHelper.getResponseDesc(paramInt) + ")")) {
      return;
    }
  }
  
  public String getMessage()
  {
    return this.mMessage;
  }
  
  public int getResponse()
  {
    return this.mResponse;
  }
  
  public boolean isFailure()
  {
    if (!isSuccess()) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public boolean isSuccess()
  {
    if (this.mResponse == 0) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public String toString()
  {
    return "IabResult: " + getMessage();
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\explodingbarrel\iap\util\IabResult.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */