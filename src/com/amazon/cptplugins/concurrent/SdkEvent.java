package com.amazon.cptplugins.concurrent;

public class SdkEvent<T>
{
  private final String eventId;
  private final T response;
  
  public SdkEvent(String paramString)
  {
    this.eventId = paramString;
    this.response = null;
  }
  
  public SdkEvent(String paramString, T paramT)
  {
    this.eventId = paramString;
    this.response = paramT;
  }
  
  public String getEventId()
  {
    return this.eventId;
  }
  
  public T getResponse()
  {
    return (T)this.response;
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\amazon\cptplugins\concurrent\SdkEvent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */