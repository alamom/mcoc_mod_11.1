package com.google.android.gms.games.quest;

import com.google.android.gms.common.data.DataBuffer;

public final class MilestoneBuffer
  extends DataBuffer<Milestone>
{
  public Milestone get(int paramInt)
  {
    return new MilestoneRef(this.II, paramInt);
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\games\quest\MilestoneBuffer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */