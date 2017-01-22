package com.google.android.gms.games.multiplayer.turnbased;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.g;

public final class TurnBasedMatchBuffer
  extends g<TurnBasedMatch>
{
  public TurnBasedMatchBuffer(DataHolder paramDataHolder)
  {
    super(paramDataHolder);
  }
  
  protected String gD()
  {
    return "external_match_id";
  }
  
  protected TurnBasedMatch l(int paramInt1, int paramInt2)
  {
    return new TurnBasedMatchRef(this.II, paramInt1, paramInt2);
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\games\multiplayer\turnbased\TurnBasedMatchBuffer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */