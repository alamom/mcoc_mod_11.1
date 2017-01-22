package com.amazon.device.iap.cpt;

import com.amazon.cptplugins.SdkEvent;

public class GetUserDataResponse
  implements Comparable<GetUserDataResponse>, SdkEvent
{
  private AmazonUserData amazonUserData;
  private String requestId;
  private String status;
  
  public int compareTo(GetUserDataResponse paramGetUserDataResponse)
  {
    int i;
    if (paramGetUserDataResponse == null) {
      i = 1;
    }
    for (;;)
    {
      return i;
      if (paramGetUserDataResponse == this)
      {
        i = 0;
      }
      else
      {
        Object localObject2 = getRequestId();
        Object localObject1 = paramGetUserDataResponse.getRequestId();
        int j;
        if (localObject2 != localObject1)
        {
          if (localObject2 == null)
          {
            i = -1;
            continue;
          }
          if (localObject1 == null)
          {
            i = 1;
            continue;
          }
          if (!(localObject2 instanceof Comparable)) {
            break label113;
          }
          j = ((Comparable)localObject2).compareTo(localObject1);
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
            localObject1 = getAmazonUserData();
            localObject2 = paramGetUserDataResponse.getAmazonUserData();
            if (localObject1 == localObject2) {
              break label192;
            }
            if (localObject1 != null) {
              break label155;
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
        label155:
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
          label192:
          do
          {
            do
            {
              localObject1 = getStatus();
              paramGetUserDataResponse = paramGetUserDataResponse.getStatus();
              if (localObject1 == paramGetUserDataResponse) {
                break label296;
              }
              if (localObject1 != null) {
                break label261;
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
          label261:
          if (paramGetUserDataResponse == null)
          {
            i = 1;
          }
          else if ((localObject1 instanceof Comparable))
          {
            j = ((Comparable)localObject1).compareTo(paramGetUserDataResponse);
            i = j;
            if (j != 0) {}
          }
          else
          {
            label296:
            do
            {
              do
              {
                i = 0;
                break;
              } while (localObject1.equals(paramGetUserDataResponse));
              i = localObject1.hashCode();
              j = paramGetUserDataResponse.hashCode();
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
  
  public boolean equals(Object paramObject)
  {
    boolean bool = true;
    if (paramObject == this) {}
    for (;;)
    {
      return bool;
      if ((paramObject instanceof GetUserDataResponse))
      {
        if (compareTo((GetUserDataResponse)paramObject) != 0) {
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
    int k = 0;
    int i;
    int j;
    if (getRequestId() == null)
    {
      i = 0;
      if (getAmazonUserData() != null) {
        break label54;
      }
      j = 0;
      label20:
      if (getStatus() != null) {
        break label65;
      }
    }
    for (;;)
    {
      return 31 * (31 * (i + 527) + j) + k;
      i = getRequestId().hashCode();
      break;
      label54:
      j = getAmazonUserData().hashCode();
      break label20;
      label65:
      k = getStatus().hashCode();
    }
  }
  
  public void setAmazonUserData(AmazonUserData paramAmazonUserData)
  {
    this.amazonUserData = paramAmazonUserData;
  }
  
  public void setRequestId(String paramString)
  {
    this.requestId = paramString;
  }
  
  public void setStatus(String paramString)
  {
    this.status = paramString;
  }
  
  public GetUserDataResponse withAmazonUserData(AmazonUserData paramAmazonUserData)
  {
    setAmazonUserData(paramAmazonUserData);
    return this;
  }
  
  public GetUserDataResponse withRequestId(String paramString)
  {
    setRequestId(paramString);
    return this;
  }
  
  public GetUserDataResponse withStatus(String paramString)
  {
    setStatus(paramString);
    return this;
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\amazon\device\iap\cpt\GetUserDataResponse.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */