package com.amazon.device.iap.cpt;

public class AsyncResponseStatusEventMetadata
  extends StatusEventMetadata
{
  private final String requestId;
  
  public AsyncResponseStatusEventMetadata(String paramString)
  {
    super(StatusEventType.ASYNC_OPERATION_RESPONSE);
    this.requestId = paramString;
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\amazon\device\iap\cpt\AsyncResponseStatusEventMetadata.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */