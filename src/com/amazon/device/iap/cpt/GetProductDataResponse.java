package com.amazon.device.iap.cpt;

import com.amazon.cptplugins.SdkEvent;
import java.util.List;
import java.util.Map;

public class GetProductDataResponse
  implements Comparable<GetProductDataResponse>, SdkEvent
{
  private Map<String, ProductData> productDataMap;
  private String requestId;
  private String status;
  private List<String> unavailableSkus;
  
  public int compareTo(GetProductDataResponse paramGetProductDataResponse)
  {
    int i;
    if (paramGetProductDataResponse == null) {
      i = 1;
    }
    for (;;)
    {
      return i;
      if (paramGetProductDataResponse == this)
      {
        i = 0;
      }
      else
      {
        Object localObject1 = getRequestId();
        Object localObject2 = paramGetProductDataResponse.getRequestId();
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
            localObject2 = getProductDataMap();
            localObject1 = paramGetProductDataResponse.getProductDataMap();
            if (localObject2 == localObject1) {
              break label192;
            }
            if (localObject2 != null) {
              break label155;
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
              localObject1 = getUnavailableSkus();
              localObject2 = paramGetProductDataResponse.getUnavailableSkus();
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
                localObject1 = getStatus();
                paramGetProductDataResponse = paramGetProductDataResponse.getStatus();
                if (localObject1 == paramGetProductDataResponse) {
                  break label404;
                }
                if (localObject1 != null) {
                  break label369;
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
            label369:
            if (paramGetProductDataResponse == null)
            {
              i = 1;
            }
            else if ((localObject1 instanceof Comparable))
            {
              j = ((Comparable)localObject1).compareTo(paramGetProductDataResponse);
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
                } while (localObject1.equals(paramGetProductDataResponse));
                i = localObject1.hashCode();
                j = paramGetProductDataResponse.hashCode();
                if (i < j)
                {
                  i = -1;
                  break;
                }
              } while (i <= j);
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
      if ((paramObject instanceof GetProductDataResponse))
      {
        if (compareTo((GetProductDataResponse)paramObject) != 0) {
          bool = false;
        }
      }
      else {
        bool = false;
      }
    }
  }
  
  public Map<String, ProductData> getProductDataMap()
  {
    return this.productDataMap;
  }
  
  public String getRequestId()
  {
    return this.requestId;
  }
  
  public String getStatus()
  {
    return this.status;
  }
  
  public List<String> getUnavailableSkus()
  {
    return this.unavailableSkus;
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
      if (getProductDataMap() != null) {
        break label70;
      }
      j = 0;
      if (getUnavailableSkus() != null) {
        break label83;
      }
      k = 0;
      label30:
      if (getStatus() != null) {
        break label96;
      }
    }
    for (;;)
    {
      return 31 * (31 * (31 * (i + 527) + j) + k) + m;
      i = getRequestId().hashCode();
      break;
      label70:
      j = getProductDataMap().hashCode();
      break label21;
      label83:
      k = getUnavailableSkus().hashCode();
      break label30;
      label96:
      m = getStatus().hashCode();
    }
  }
  
  public void setProductDataMap(Map<String, ProductData> paramMap)
  {
    this.productDataMap = paramMap;
  }
  
  public void setRequestId(String paramString)
  {
    this.requestId = paramString;
  }
  
  public void setStatus(String paramString)
  {
    this.status = paramString;
  }
  
  public void setUnavailableSkus(List<String> paramList)
  {
    this.unavailableSkus = paramList;
  }
  
  public GetProductDataResponse withProductDataMap(Map<String, ProductData> paramMap)
  {
    setProductDataMap(paramMap);
    return this;
  }
  
  public GetProductDataResponse withRequestId(String paramString)
  {
    setRequestId(paramString);
    return this;
  }
  
  public GetProductDataResponse withStatus(String paramString)
  {
    setStatus(paramString);
    return this;
  }
  
  public GetProductDataResponse withUnavailableSkus(List<String> paramList)
  {
    setUnavailableSkus(paramList);
    return this;
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\amazon\device\iap\cpt\GetProductDataResponse.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */