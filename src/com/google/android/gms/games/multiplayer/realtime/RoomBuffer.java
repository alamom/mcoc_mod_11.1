package com.google.android.gms.games.multiplayer.realtime;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.g;

public final class RoomBuffer
  extends g<Room>
{
  public RoomBuffer(DataHolder paramDataHolder)
  {
    super(paramDataHolder);
  }
  
  protected String gD()
  {
    return "external_match_id";
  }
  
  protected Room k(int paramInt1, int paramInt2)
  {
    return new RoomRef(this.II, paramInt1, paramInt2);
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\games\multiplayer\realtime\RoomBuffer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */