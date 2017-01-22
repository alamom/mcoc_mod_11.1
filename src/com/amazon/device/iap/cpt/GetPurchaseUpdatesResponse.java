package com.amazon.device.iap.cpt;

import com.amazon.cptplugins.SdkEvent;
import java.util.List;

public class GetPurchaseUpdatesResponse
  implements Comparable<GetPurchaseUpdatesResponse>, SdkEvent
{
  private AmazonUserData amazonUserData;
  private Boolean hasMore;
  private List<PurchaseReceipt> receipts;
  private String requestId;
  private String status;
  
  public int compareTo(GetPurchaseUpdatesResponse paramGetPurchaseUpdatesResponse)
  {
    int i;
    if (paramGetPurchaseUpdatesResponse == null) {
      i = 1;
    }
    for (;;)
    {
      return i;
      if (paramGetPurchaseUpdatesResponse == this)
      {
        i = 0;
      }
      else
      {
        Object localObject1 = getRequestId();
        Object localObject2 = paramGetPurchaseUpdatesResponse.getRequestId();
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
            localObject1 = paramGetPurchaseUpdatesResponse.getAmazonUserData();
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
              localObject1 = getReceipts();
              localObject2 = paramGetPurchaseUpdatesResponse.getReceipts();
              if (localObject1 == localObject2) {
                break label300;
              }
              if (localObject1 != null) {
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
          if (localObject2 == null)
          {
            i = 1;
          }
          else if ((localObject1 instanceof Comparable))
          {
            j = ((Comparable)localObject1).compareTo(localObject2);
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
                localObject2 = getStatus();
                localObject1 = paramGetPurchaseUpdatesResponse.getStatus();
                if (localObject2 == localObject1) {
                  break label408;
                }
                if (localObject2 != null) {
                  break label371;
                }
                i = -1;
                break;
              } while (localObject1.equals(localObject2));
              i = localObject1.hashCode();
              j = localObject2.hashCode();
              if (i < j)
              {
                i = -1;
                break;
              }
            } while (i <= j);
            i = 1;
            continue;
            label371:
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
              label408:
              do
              {
                do
                {
                  localObject1 = getHasMore();
                  paramGetPurchaseUpdatesResponse = paramGetPurchaseUpdatesResponse.getHasMore();
                  if (localObject1 == paramGetPurchaseUpdatesResponse) {
                    break label512;
                  }
                  if (localObject1 != null) {
                    break label477;
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
              label477:
              if (paramGetPurchaseUpdatesResponse == null)
              {
                i = 1;
              }
              else if ((localObject1 instanceof Comparable))
              {
                j = ((Comparable)localObject1).compareTo(paramGetPurchaseUpdatesResponse);
                i = j;
                if (j != 0) {}
              }
              else
              {
                label512:
                do
                {
                  do
                  {
                    i = 0;
                    break;
                  } while (localObject1.equals(paramGetPurchaseUpdatesResponse));
                  j = localObject1.hashCode();
                  i = paramGetPurchaseUpdatesResponse.hashCode();
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
  }
  
  public boolean equals(Object paramObject)
  {
    boolean bool = true;
    if (paramObject == this) {}
    for (;;)
    {
      return bool;
      if ((paramObject instanceof GetPurchaseUpdatesResponse))
      {
        if (compareTo((GetPurchaseUpdatesResponse)paramObject) != 0) {
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
  
  public Boolean getHasMore()
  {
    return this.hasMore;
  }
  
  public List<PurchaseReceipt> getReceipts()
  {
    return this.receipts;
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
    int n = 0;
    int i;
    int j;
    label21:
    int k;
    label30:
    int m;
    if (getRequestId() == null)
    {
      i = 0;
      if (getAmazonUserData() != null) {
        break label86;
      }
      j = 0;
      if (getReceipts() != null) {
        break label97;
      }
      k = 0;
      if (getStatus() != null) {
        break label110;
      }
      m = 0;
      label40:
      if (getHasMore() != null) {
        break label122;
      }
    }
    for (;;)
    {
      return 31 * (31 * (31 * (31 * (i + 527) + j) + k) + m) + n;
      i = getRequestId().hashCode();
      break;
      label86:
      j = getAmazonUserData().hashCode();
      break label21;
      label97:
      k = getReceipts().hashCode();
      break label30;
      label110:
      m = getStatus().hashCode();
      break label40;
      label122:
      n = getHasMore().hashCode();
    }
  }
  
  public void setAmazonUserData(AmazonUserData paramAmazonUserData)
  {
    this.amazonUserData = paramAmazonUserData;
  }
  
  public void setHasMore(Boolean paramBoolean)
  {
    this.hasMore = paramBoolean;
  }
  
  public void setReceipts(List<PurchaseReceipt> paramList)
  {
    this.receipts = paramList;
  }
  
  public void setRequestId(String paramString)
  {
    this.requestId = paramString;
  }
  
  public void setStatus(String paramString)
  {
    this.status = paramString;
  }
  
  public GetPurchaseUpdatesResponse withAmazonUserData(AmazonUserData paramAmazonUserData)
  {
    setAmazonUserData(paramAmazonUserData);
    return this;
  }
  
  public GetPurchaseUpdatesResponse withHasMore(Boolean paramBoolean)
  {
    setHasMore(paramBoolean);
    return this;
  }
  
  public GetPurchaseUpdatesResponse withReceipts(List<PurchaseReceipt> paramList)
  {
    setReceipts(paramList);
    return this;
  }
  
  public GetPurchaseUpdatesResponse withRequestId(String paramString)
  {
    setRequestId(paramString);
    return this;
  }
  
  public GetPurchaseUpdatesResponse withStatus(String paramString)
  {
    setStatus(paramString);
    return this;
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\amazon\device\iap\cpt\GetPurchaseUpdatesResponse.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */