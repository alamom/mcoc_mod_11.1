package com.kabam.soda.wske;

public abstract interface WSKECallback<Result>
{
  public abstract void onError(String paramString, Throwable paramThrowable, int paramInt);
  
  public abstract void onSuccess(Result paramResult);
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\kabam\soda\wske\WSKECallback.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */