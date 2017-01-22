package com.amazon.device.iap.cpt;

import com.amazon.cptplugins.SdkEvent;

public class SkuInput
  implements Comparable<SkuInput>, SdkEvent
{
  private String sku;
  
  public int compareTo(SkuInput paramSkuInput)
  {
    int j = 1;
    int i;
    if (paramSkuInput == null) {
      i = j;
    }
    for (;;)
    {
      return i;
      if (paramSkuInput == this)
      {
        i = 0;
      }
      else
      {
        String str = getSku();
        paramSkuInput = paramSkuInput.getSku();
        if (str != paramSkuInput)
        {
          if (str == null)
          {
            i = -1;
            continue;
          }
          i = j;
          if (paramSkuInput == null) {
            continue;
          }
          if (!(str instanceof Comparable)) {
            break label84;
          }
          j = ((Comparable)str).compareTo(paramSkuInput);
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
          } while (str.equals(paramSkuInput));
          i = str.hashCode();
          k = paramSkuInput.hashCode();
          if (i < k)
          {
            i = -1;
            break;
          }
        } while (i <= k);
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
      if ((paramObject instanceof SkuInput))
      {
        if (compareTo((SkuInput)paramObject) != 0) {
          bool = false;
        }
      }
      else {
        bool = false;
      }
    }
  }
  
  public String getSku()
  {
    return this.sku;
  }
  
  public int hashCode()
  {
    if (getSku() == null) {}
    for (int i = 0;; i = getSku().hashCode()) {
      return i + 527;
    }
  }
  
  public void setSku(String paramString)
  {
    this.sku = paramString;
  }
  
  public SkuInput withSku(String paramString)
  {
    setSku(paramString);
    return this;
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\amazon\device\iap\cpt\SkuInput.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */