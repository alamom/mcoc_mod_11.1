package com.google.android.gms.wearable;

import com.google.android.gms.common.data.Freezable;

public abstract interface DataEvent
  extends Freezable<DataEvent>
{
  public static final int TYPE_CHANGED = 1;
  public static final int TYPE_DELETED = 2;
  
  public abstract DataItem getDataItem();
  
  public abstract int getType();
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\wearable\DataEvent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */