package com.amazon.cptplugins.concurrent;

public class CallbackResult<T>
{
  private final String callerId;
  private final T response;
  
  public CallbackResult(String paramString, T paramT)
  {
    this.callerId = paramString;
    this.response = paramT;
  }
  
  public String getCallerId()
  {
    return this.callerId;
  }
  
  public T getResponse()
  {
    return (T)this.response;
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\amazon\cptplugins\concurrent\CallbackResult.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */