package com.amazon.device.iap.cpt;

import com.amazon.cptplugins.SdkEvent;

public class ResetInput
  implements Comparable<ResetInput>, SdkEvent
{
  private Boolean reset;
  
  public int compareTo(ResetInput paramResetInput)
  {
    int j = 1;
    int i;
    if (paramResetInput == null) {
      i = j;
    }
    for (;;)
    {
      return i;
      if (paramResetInput == this)
      {
        i = 0;
      }
      else
      {
        Boolean localBoolean = getReset();
        paramResetInput = paramResetInput.getReset();
        if (localBoolean != paramResetInput)
        {
          if (localBoolean == null)
          {
            i = -1;
            continue;
          }
          i = j;
          if (paramResetInput == null) {
            continue;
          }
          if (!(localBoolean instanceof Comparable)) {
            break label84;
          }
          j = ((Comparable)localBoolean).compareTo(paramResetInput);
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
          } while (localBoolean.equals(paramResetInput));
          i = localBoolean.hashCode();
          k = paramResetInput.hashCode();
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
      if ((paramObject instanceof ResetInput))
      {
        if (compareTo((ResetInput)paramObject) != 0) {
          bool = false;
        }
      }
      else {
        bool = false;
      }
    }
  }
  
  public Boolean getReset()
  {
    return this.reset;
  }
  
  public int hashCode()
  {
    if (getReset() == null) {}
    for (int i = 0;; i = getReset().hashCode()) {
      return i + 527;
    }
  }
  
  public void setReset(Boolean paramBoolean)
  {
    this.reset = paramBoolean;
  }
  
  public ResetInput withReset(Boolean paramBoolean)
  {
    setReset(paramBoolean);
    return this;
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\amazon\device\iap\cpt\ResetInput.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */