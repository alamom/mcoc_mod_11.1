package com.google.android.gms.wearable.internal;

import com.google.android.gms.wearable.DataEvent;
import com.google.android.gms.wearable.DataItem;

public class g
  implements DataEvent
{
  private int FD;
  private DataItem avs;
  
  public g(DataEvent paramDataEvent)
  {
    this.FD = paramDataEvent.getType();
    this.avs = ((DataItem)paramDataEvent.getDataItem().freeze());
  }
  
  public DataItem getDataItem()
  {
    return this.avs;
  }
  
  public int getType()
  {
    return this.FD;
  }
  
  public boolean isDataValid()
  {
    return true;
  }
  
  public DataEvent pW()
  {
    return this;
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\wearable\internal\g.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */