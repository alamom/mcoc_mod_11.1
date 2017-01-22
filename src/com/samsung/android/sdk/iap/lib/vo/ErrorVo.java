package com.samsung.android.sdk.iap.lib.vo;

public class ErrorVo
{
  private int mErrorCode = 0;
  private String mErrorString = "";
  private String mExtraString = "";
  
  public String dump()
  {
    return "ErrorCode    : " + getErrorCode() + "\n" + "ErrorString  : " + getErrorString() + "\n" + "ExtraString  : " + getExtraString();
  }
  
  public int getErrorCode()
  {
    return this.mErrorCode;
  }
  
  public String getErrorString()
  {
    return this.mErrorString;
  }
  
  public String getExtraString()
  {
    return this.mExtraString;
  }
  
  public void setError(int paramInt, String paramString)
  {
    this.mErrorCode = paramInt;
    this.mErrorString = paramString;
  }
  
  public void setExtraString(String paramString)
  {
    this.mExtraString = paramString;
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\samsung\android\sdk\iap\lib\vo\ErrorVo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */