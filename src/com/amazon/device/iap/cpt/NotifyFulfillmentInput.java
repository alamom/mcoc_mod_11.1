package com.amazon.device.iap.cpt;

import com.amazon.cptplugins.SdkEvent;

public class NotifyFulfillmentInput
  implements Comparable<NotifyFulfillmentInput>, SdkEvent
{
  private String fulfillmentResult;
  private String receiptId;
  
  public int compareTo(NotifyFulfillmentInput paramNotifyFulfillmentInput)
  {
    int i;
    if (paramNotifyFulfillmentInput == null) {
      i = 1;
    }
    for (;;)
    {
      return i;
      if (paramNotifyFulfillmentInput == this)
      {
        i = 0;
      }
      else
      {
        String str2 = getReceiptId();
        String str1 = paramNotifyFulfillmentInput.getReceiptId();
        int j;
        if (str2 != str1)
        {
          if (str2 == null)
          {
            i = -1;
            continue;
          }
          if (str1 == null)
          {
            i = 1;
            continue;
          }
          if (!(str2 instanceof Comparable)) {
            break label111;
          }
          j = ((Comparable)str2).compareTo(str1);
          i = j;
          if (j != 0) {
            continue;
          }
        }
        label111:
        do
        {
          do
          {
            str1 = getFulfillmentResult();
            paramNotifyFulfillmentInput = paramNotifyFulfillmentInput.getFulfillmentResult();
            if (str1 == paramNotifyFulfillmentInput) {
              break label188;
            }
            if (str1 != null) {
              break label153;
            }
            i = -1;
            break;
          } while (str2.equals(str1));
          j = str2.hashCode();
          i = str1.hashCode();
          if (j < i)
          {
            i = -1;
            break;
          }
        } while (j <= i);
        i = 1;
        continue;
        label153:
        if (paramNotifyFulfillmentInput == null)
        {
          i = 1;
        }
        else if ((str1 instanceof Comparable))
        {
          j = ((Comparable)str1).compareTo(paramNotifyFulfillmentInput);
          i = j;
          if (j != 0) {}
        }
        else
        {
          label188:
          do
          {
            do
            {
              i = 0;
              break;
            } while (str1.equals(paramNotifyFulfillmentInput));
            j = str1.hashCode();
            i = paramNotifyFulfillmentInput.hashCode();
            if (j < i)
            {
              i = -1;
              break;
            }
          } while (j <= i);
          i = 1;
        }
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
      if ((paramObject instanceof NotifyFulfillmentInput))
      {
        if (compareTo((NotifyFulfillmentInput)paramObject) != 0) {
          bool = false;
        }
      }
      else {
        bool = false;
      }
    }
  }
  
  public String getFulfillmentResult()
  {
    return this.fulfillmentResult;
  }
  
  public String getReceiptId()
  {
    return this.receiptId;
  }
  
  public int hashCode()
  {
    int j = 0;
    int i;
    if (getReceiptId() == null)
    {
      i = 0;
      if (getFulfillmentResult() != null) {
        break label40;
      }
    }
    for (;;)
    {
      return 31 * (i + 527) + j;
      i = getReceiptId().hashCode();
      break;
      label40:
      j = getFulfillmentResult().hashCode();
    }
  }
  
  public void setFulfillmentResult(String paramString)
  {
    this.fulfillmentResult = paramString;
  }
  
  public void setReceiptId(String paramString)
  {
    this.receiptId = paramString;
  }
  
  public NotifyFulfillmentInput withFulfillmentResult(String paramString)
  {
    setFulfillmentResult(paramString);
    return this;
  }
  
  public NotifyFulfillmentInput withReceiptId(String paramString)
  {
    setReceiptId(paramString);
    return this;
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\amazon\device\iap\cpt\NotifyFulfillmentInput.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */