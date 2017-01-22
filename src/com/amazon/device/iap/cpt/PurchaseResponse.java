package com.amazon.device.iap.cpt;

import com.amazon.cptplugins.SdkEvent;

public class PurchaseResponse
  implements Comparable<PurchaseResponse>, SdkEvent
{
  private AmazonUserData amazonUserData;
  private PurchaseReceipt purchaseReceipt;
  private String requestId;
  private String status;
  
  public int compareTo(PurchaseResponse paramPurchaseResponse)
  {
    int i;
    if (paramPurchaseResponse == null) {
      i = 1;
    }
    for (;;)
    {
      return i;
      if (paramPurchaseResponse == this)
      {
        i = 0;
      }
      else
      {
        Object localObject1 = getRequestId();
        Object localObject2 = paramPurchaseResponse.getRequestId();
        int j;
        if (localObject1 != localObject2)
        {
          if (localObject1 == null)
          {
            i = -1;
            continue;
          }
          if (localObject2 == null)
          {
            i = 1;
            continue;
          }
          if (!(localObject1 instanceof Comparable)) {
            break label113;
          }
          j = ((Comparable)localObject1).compareTo(localObject2);
          i = j;
          if (j != 0) {
            continue;
          }
        }
        label113:
        do
        {
          do
          {
            localObject2 = getAmazonUserData();
            localObject1 = paramPurchaseResponse.getAmazonUserData();
            if (localObject2 == localObject1) {
              break label192;
            }
            if (localObject2 != null) {
              break label155;
            }
            i = -1;
            break;
          } while (localObject1.equals(localObject2));
          j = localObject1.hashCode();
          i = localObject2.hashCode();
          if (j < i)
          {
            i = -1;
            break;
          }
        } while (j <= i);
        i = 1;
        continue;
        label155:
        if (localObject1 == null)
        {
          i = 1;
        }
        else if ((localObject2 instanceof Comparable))
        {
          j = ((Comparable)localObject2).compareTo(localObject1);
          i = j;
          if (j != 0) {}
        }
        else
        {
          label192:
          do
          {
            do
            {
              localObject2 = getPurchaseReceipt();
              localObject1 = paramPurchaseResponse.getPurchaseReceipt();
              if (localObject2 == localObject1) {
                break label300;
              }
              if (localObject2 != null) {
                break label263;
              }
              i = -1;
              break;
            } while (localObject2.equals(localObject1));
            i = localObject2.hashCode();
            j = localObject1.hashCode();
            if (i < j)
            {
              i = -1;
              break;
            }
          } while (i <= j);
          i = 1;
          continue;
          label263:
          if (localObject1 == null)
          {
            i = 1;
          }
          else if ((localObject2 instanceof Comparable))
          {
            j = ((Comparable)localObject2).compareTo(localObject1);
            i = j;
            if (j != 0) {}
          }
          else
          {
            label300:
            do
            {
              do
              {
                localObject1 = getStatus();
                paramPurchaseResponse = paramPurchaseResponse.getStatus();
                if (localObject1 == paramPurchaseResponse) {
                  break label404;
                }
                if (localObject1 != null) {
                  break label369;
                }
                i = -1;
                break;
              } while (localObject2.equals(localObject1));
              j = localObject2.hashCode();
              i = localObject1.hashCode();
              if (j < i)
              {
                i = -1;
                break;
              }
            } while (j <= i);
            i = 1;
            continue;
            label369:
            if (paramPurchaseResponse == null)
            {
              i = 1;
            }
            else if ((localObject1 instanceof Comparable))
            {
              j = ((Comparable)localObject1).compareTo(paramPurchaseResponse);
              i = j;
              if (j != 0) {}
            }
            else
            {
              label404:
              do
              {
                do
                {
                  i = 0;
                  break;
                } while (localObject1.equals(paramPurchaseResponse));
                j = localObject1.hashCode();
                i = paramPurchaseResponse.hashCode();
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
    }
  }
  
  public boolean equals(Object paramObject)
  {
    boolean bool = true;
    if (paramObject == this) {}
    for (;;)
    {
      return bool;
      if ((paramObject instanceof PurchaseResponse))
      {
        if (compareTo((PurchaseResponse)paramObject) != 0) {
          bool = false;
        }
      }
      else {
        bool = false;
      }
    }
  }
  
  public AmazonUserData getAmazonUserData()
  {
    return this.amazonUserData;
  }
  
  public PurchaseReceipt getPurchaseReceipt()
  {
    return this.purchaseReceipt;
  }
  
  public String getRequestId()
  {
    return this.requestId;
  }
  
  public String getStatus()
  {
    return this.status;
  }
  
  public int hashCode()
  {
    int m = 0;
    int i;
    int j;
    label21:
    int k;
    if (getRequestId() == null)
    {
      i = 0;
      if (getAmazonUserData() != null) {
        break label70;
      }
      j = 0;
      if (getPurchaseReceipt() != null) {
        break label81;
      }
      k = 0;
      label30:
      if (getStatus() != null) {
        break label92;
      }
    }
    for (;;)
    {
      return 31 * (31 * (31 * (i + 527) + j) + k) + m;
      i = getRequestId().hashCode();
      break;
      label70:
      j = getAmazonUserData().hashCode();
      break label21;
      label81:
      k = getPurchaseReceipt().hashCode();
      break label30;
      label92:
      m = getStatus().hashCode();
    }
  }
  
  public void setAmazonUserData(AmazonUserData paramAmazonUserData)
  {
    this.amazonUserData = paramAmazonUserData;
  }
  
  public void setPurchaseReceipt(PurchaseReceipt paramPurchaseReceipt)
  {
    this.purchaseReceipt = paramPurchaseReceipt;
  }
  
  public void setRequestId(String paramString)
  {
    this.requestId = paramString;
  }
  
  public void setStatus(String paramString)
  {
    this.status = paramString;
  }
  
  public PurchaseResponse withAmazonUserData(AmazonUserData paramAmazonUserData)
  {
    setAmazonUserData(paramAmazonUserData);
    return this;
  }
  
  public PurchaseResponse withPurchaseReceipt(PurchaseReceipt paramPurchaseReceipt)
  {
    setPurchaseReceipt(paramPurchaseReceipt);
    return this;
  }
  
  public PurchaseResponse withRequestId(String paramString)
  {
    setRequestId(paramString);
    return this;
  }
  
  public PurchaseResponse withStatus(String paramString)
  {
    setStatus(paramString);
    return this;
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\amazon\device\iap\cpt\PurchaseResponse.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */