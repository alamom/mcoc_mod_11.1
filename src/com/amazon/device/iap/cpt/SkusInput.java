package com.amazon.device.iap.cpt;

import com.amazon.cptplugins.SdkEvent;
import java.util.List;

public class SkusInput
  implements Comparable<SkusInput>, SdkEvent
{
  private List<String> skus;
  
  public int compareTo(SkusInput paramSkusInput)
  {
    int j = 1;
    int i;
    if (paramSkusInput == null) {
      i = j;
    }
    for (;;)
    {
      return i;
      if (paramSkusInput == this)
      {
        i = 0;
      }
      else
      {
        List localList = getSkus();
        paramSkusInput = paramSkusInput.getSkus();
        if (localList != paramSkusInput)
        {
          if (localList == null)
          {
            i = -1;
            continue;
          }
          i = j;
          if (paramSkusInput == null) {
            continue;
          }
          if (!(localList instanceof Comparable)) {
            break label84;
          }
          j = ((Comparable)localList).compareTo(paramSkusInput);
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
          } while (localList.equals(paramSkusInput));
          i = localList.hashCode();
          k = paramSkusInput.hashCode();
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
      if ((paramObject instanceof SkusInput))
      {
        if (compareTo((SkusInput)paramObject) != 0) {
          bool = false;
        }
      }
      else {
        bool = false;
      }
    }
  }
  
  public List<String> getSkus()
  {
    return this.skus;
  }
  
  public int hashCode()
  {
    if (getSkus() == null) {}
    for (int i = 0;; i = getSkus().hashCode()) {
      return i + 527;
    }
  }
  
  public void setSkus(List<String> paramList)
  {
    this.skus = paramList;
  }
  
  public SkusInput withSkus(List<String> paramList)
  {
    setSkus(paramList);
    return this;
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\amazon\device\iap\cpt\SkusInput.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */