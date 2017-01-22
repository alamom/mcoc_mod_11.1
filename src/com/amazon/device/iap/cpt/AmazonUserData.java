package com.amazon.device.iap.cpt;

import com.amazon.cptplugins.SdkEvent;

public class AmazonUserData
  implements Comparable<AmazonUserData>, SdkEvent
{
  private String marketplace;
  private String userId;
  
  public int compareTo(AmazonUserData paramAmazonUserData)
  {
    int i;
    if (paramAmazonUserData == null) {
      i = 1;
    }
    for (;;)
    {
      return i;
      if (paramAmazonUserData == this)
      {
        i = 0;
      }
      else
      {
        String str1 = getUserId();
        String str2 = paramAmazonUserData.getUserId();
        int j;
        if (str1 != str2)
        {
          if (str1 == null)
          {
            i = -1;
            continue;
          }
          if (str2 == null)
          {
            i = 1;
            continue;
          }
          if (!(str1 instanceof Comparable)) {
            break label111;
          }
          j = ((Comparable)str1).compareTo(str2);
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
            str1 = getMarketplace();
            paramAmazonUserData = paramAmazonUserData.getMarketplace();
            if (str1 == paramAmazonUserData) {
              break label188;
            }
            if (str1 != null) {
              break label153;
            }
            i = -1;
            break;
          } while (str1.equals(str2));
          i = str1.hashCode();
          j = str2.hashCode();
          if (i < j)
          {
            i = -1;
            break;
          }
        } while (i <= j);
        i = 1;
        continue;
        label153:
        if (paramAmazonUserData == null)
        {
          i = 1;
        }
        else if ((str1 instanceof Comparable))
        {
          j = ((Comparable)str1).compareTo(paramAmazonUserData);
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
            } while (str1.equals(paramAmazonUserData));
            j = str1.hashCode();
            i = paramAmazonUserData.hashCode();
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
      if ((paramObject instanceof AmazonUserData))
      {
        if (compareTo((AmazonUserData)paramObject) != 0) {
          bool = false;
        }
      }
      else {
        bool = false;
      }
    }
  }
  
  public String getMarketplace()
  {
    return this.marketplace;
  }
  
  public String getUserId()
  {
    return this.userId;
  }
  
  public int hashCode()
  {
    int j = 0;
    int i;
    if (getUserId() == null)
    {
      i = 0;
      if (getMarketplace() != null) {
        break label40;
      }
    }
    for (;;)
    {
      return 31 * (i + 527) + j;
      i = getUserId().hashCode();
      break;
      label40:
      j = getMarketplace().hashCode();
    }
  }
  
  public void setMarketplace(String paramString)
  {
    this.marketplace = paramString;
  }
  
  public void setUserId(String paramString)
  {
    this.userId = paramString;
  }
  
  public AmazonUserData withMarketplace(String paramString)
  {
    setMarketplace(paramString);
    return this;
  }
  
  public AmazonUserData withUserId(String paramString)
  {
    setUserId(paramString);
    return this;
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\amazon\device\iap\cpt\AmazonUserData.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */