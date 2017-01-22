package com.facebook.android;

public class DialogError
  extends Throwable
{
  private static final long serialVersionUID = 1L;
  private int mErrorCode;
  private String mFailingUrl;
  
  @Deprecated
  public DialogError(String paramString1, int paramInt, String paramString2)
  {
    super(paramString1);
    this.mErrorCode = paramInt;
    this.mFailingUrl = paramString2;
  }
  
  @Deprecated
  public int getErrorCode()
  {
    return this.mErrorCode;
  }
  
  @Deprecated
  public String getFailingUrl()
  {
    return this.mFailingUrl;
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\facebook\android\DialogError.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */