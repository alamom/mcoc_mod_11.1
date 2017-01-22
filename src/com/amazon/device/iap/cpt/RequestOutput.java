package com.amazon.device.iap.cpt;

import com.amazon.cptplugins.SdkEvent;

public class RequestOutput
  implements Comparable<RequestOutput>, SdkEvent
{
  private String requestId;
  
  public int compareTo(RequestOutput paramRequestOutput)
  {
    int j = 1;
    int i;
    if (paramRequestOutput == null) {
      i = j;
    }
    for (;;)
    {
      return i;
      if (paramRequestOutput == this)
      {
        i = 0;
      }
      else
      {
        String str = getRequestId();
        paramRequestOutput = paramRequestOutput.getRequestId();
        if (str != paramRequestOutput)
        {
          if (str == null)
          {
            i = -1;
            continue;
          }
          i = j;
          if (paramRequestOutput == null) {
            continue;
          }
          if (!(str instanceof Comparable)) {
            break label84;
          }
          j = ((Comparable)str).compareTo(paramRequestOutput);
          i = j;
          if (j != 0) {
            continue;
          }
        }
        label84:
        int k;
        do
        {
          do
          {
            i = 0;
            break;
          } while (str.equals(paramRequestOutput));
          k = str.hashCode();
          i = paramRequestOutput.hashCode();
          if (k < i)
          {
            i = -1;
            break;
          }
        } while (k <= i);
        i = j;
      }
    }
  }
  
  public boolean equals(Object paramObject)
  {
    boolean bool = true;
    if (paramObject == this) {}
    for (;;)
    {
      return bool;
      if ((paramObject instanceof RequestOutput))
      {
        if (compareTo((RequestOutput)paramObject) != 0) {
          bool = false;
        }
      }
      else {
        bool = false;
      }
    }
  }
  
  public String getRequestId()
  {
    return this.requestId;
  }
  
  public int hashCode()
  {
    if (getRequestId() == null) {}
    for (int i = 0;; i = getRequestId().hashCode()) {
      return i + 527;
    }
  }
  
  public void setRequestId(String paramString)
  {
    this.requestId = paramString;
  }
  
  public RequestOutput withRequestId(String paramString)
  {
    setRequestId(paramString);
    return this;
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\amazon\device\iap\cpt\RequestOutput.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */