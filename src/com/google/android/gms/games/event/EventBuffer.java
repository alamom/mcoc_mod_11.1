package com.google.android.gms.games.event;

import com.google.android.gms.common.data.DataBuffer;
import com.google.android.gms.common.data.DataHolder;

public final class EventBuffer
  extends DataBuffer<Event>
{
  public EventBuffer(DataHolder paramDataHolder)
  {
    super(paramDataHolder);
  }
  
  public Event get(int paramInt)
  {
    return new EventRef(this.II, paramInt);
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\games\event\EventBuffer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */