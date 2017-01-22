package com.explodingbarrel.iap.util;

public class IabException
  extends Exception
{
  IabResult mResult;
  
  public IabException(int paramInt, String paramString)
  {
    this(new IabResult(paramInt, paramString));
  }
  
  public IabException(int paramInt, String paramString, Exception paramException)
  {
    this(new IabResult(paramInt, paramString), paramException);
  }
  
  public IabException(IabResult paramIabResult)
  {
    this(paramIabResult, null);
  }
  
  public IabException(IabResult paramIabResult, Exception paramException)
  {
    super(paramIabResult.getMessage(), paramException);
    this.mResult = paramIabResult;
  }
  
  public IabResult getResult()
  {
    return this.mResult;
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\explodingbarrel\iap\util\IabException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */