package com.amazon.device.iap.cpt;

import com.amazon.cptplugins.SdkEvent;

public class ProductData
  implements Comparable<ProductData>, SdkEvent
{
  private String description;
  private String price;
  private String productType;
  private String sku;
  private String smallIconUrl;
  private String title;
  
  public int compareTo(ProductData paramProductData)
  {
    int i;
    if (paramProductData == null) {
      i = 1;
    }
    for (;;)
    {
      return i;
      if (paramProductData == this)
      {
        i = 0;
      }
      else
      {
        String str2 = getSku();
        String str1 = paramProductData.getSku();
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
            break label113;
          }
          j = ((Comparable)str2).compareTo(str1);
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
            str1 = getProductType();
            str2 = paramProductData.getProductType();
            if (str1 == str2) {
              break label192;
            }
            if (str1 != null) {
              break label155;
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
        label155:
        if (str2 == null)
        {
          i = 1;
        }
        else if ((str1 instanceof Comparable))
        {
          j = ((Comparable)str1).compareTo(str2);
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
              str1 = getPrice();
              str2 = paramProductData.getPrice();
              if (str1 == str2) {
                break label300;
              }
              if (str1 != null) {
                break label263;
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
          label263:
          if (str2 == null)
          {
            i = 1;
          }
          else if ((str1 instanceof Comparable))
          {
            j = ((Comparable)str1).compareTo(str2);
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
                str1 = getTitle();
                str2 = paramProductData.getTitle();
                if (str1 == str2) {
                  break label408;
                }
                if (str1 != null) {
                  break label371;
                }
                i = -1;
                break;
              } while (str1.equals(str2));
              j = str1.hashCode();
              i = str2.hashCode();
              if (j < i)
              {
                i = -1;
                break;
              }
            } while (j <= i);
            i = 1;
            continue;
            label371:
            if (str2 == null)
            {
              i = 1;
            }
            else if ((str1 instanceof Comparable))
            {
              j = ((Comparable)str1).compareTo(str2);
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
                  str2 = getDescription();
                  str1 = paramProductData.getDescription();
                  if (str2 == str1) {
                    break label516;
                  }
                  if (str2 != null) {
                    break label479;
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
              label479:
              if (str1 == null)
              {
                i = 1;
              }
              else if ((str2 instanceof Comparable))
              {
                j = ((Comparable)str2).compareTo(str1);
                i = j;
                if (j != 0) {}
              }
              else
              {
                label516:
                do
                {
                  do
                  {
                    str1 = getSmallIconUrl();
                    paramProductData = paramProductData.getSmallIconUrl();
                    if (str1 == paramProductData) {
                      break label620;
                    }
                    if (str1 != null) {
                      break label585;
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
                label585:
                if (paramProductData == null)
                {
                  i = 1;
                }
                else if ((str1 instanceof Comparable))
                {
                  j = ((Comparable)str1).compareTo(paramProductData);
                  i = j;
                  if (j != 0) {}
                }
                else
                {
                  label620:
                  do
                  {
                    do
                    {
                      i = 0;
                      break;
                    } while (str1.equals(paramProductData));
                    j = str1.hashCode();
                    i = paramProductData.hashCode();
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
  }
  
  public boolean equals(Object paramObject)
  {
    boolean bool = true;
    if (paramObject == this) {}
    for (;;)
    {
      return bool;
      if ((paramObject instanceof ProductData))
      {
        if (compareTo((ProductData)paramObject) != 0) {
          bool = false;
        }
      }
      else {
        bool = false;
      }
    }
  }
  
  public String getDescription()
  {
    return this.description;
  }
  
  public String getPrice()
  {
    return this.price;
  }
  
  public String getProductType()
  {
    return this.productType;
  }
  
  public String getSku()
  {
    return this.sku;
  }
  
  public String getSmallIconUrl()
  {
    return this.smallIconUrl;
  }
  
  public String getTitle()
  {
    return this.title;
  }
  
  public int hashCode()
  {
    int i1 = 0;
    int i;
    int j;
    label21:
    int k;
    label30:
    int m;
    label40:
    int n;
    if (getSku() == null)
    {
      i = 0;
      if (getProductType() != null) {
        break label102;
      }
      j = 0;
      if (getPrice() != null) {
        break label113;
      }
      k = 0;
      if (getTitle() != null) {
        break label124;
      }
      m = 0;
      if (getDescription() != null) {
        break label136;
      }
      n = 0;
      label50:
      if (getSmallIconUrl() != null) {
        break label148;
      }
    }
    for (;;)
    {
      return 31 * (31 * (31 * (31 * (31 * (i + 527) + j) + k) + m) + n) + i1;
      i = getSku().hashCode();
      break;
      label102:
      j = getProductType().hashCode();
      break label21;
      label113:
      k = getPrice().hashCode();
      break label30;
      label124:
      m = getTitle().hashCode();
      break label40;
      label136:
      n = getDescription().hashCode();
      break label50;
      label148:
      i1 = getSmallIconUrl().hashCode();
    }
  }
  
  public void setDescription(String paramString)
  {
    this.description = paramString;
  }
  
  public void setPrice(String paramString)
  {
    this.price = paramString;
  }
  
  public void setProductType(String paramString)
  {
    this.productType = paramString;
  }
  
  public void setSku(String paramString)
  {
    this.sku = paramString;
  }
  
  public void setSmallIconUrl(String paramString)
  {
    this.smallIconUrl = paramString;
  }
  
  public void setTitle(String paramString)
  {
    this.title = paramString;
  }
  
  public ProductData withDescription(String paramString)
  {
    setDescription(paramString);
    return this;
  }
  
  public ProductData withPrice(String paramString)
  {
    setPrice(paramString);
    return this;
  }
  
  public ProductData withProductType(String paramString)
  {
    setProductType(paramString);
    return this;
  }
  
  public ProductData withSku(String paramString)
  {
    setSku(paramString);
    return this;
  }
  
  public ProductData withSmallIconUrl(String paramString)
  {
    setSmallIconUrl(paramString);
    return this;
  }
  
  public ProductData withTitle(String paramString)
  {
    setTitle(paramString);
    return this;
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\amazon\device\iap\cpt\ProductData.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */