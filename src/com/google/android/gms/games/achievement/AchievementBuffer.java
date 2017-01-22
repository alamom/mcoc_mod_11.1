package com.google.android.gms.games.achievement;

import com.google.android.gms.common.data.DataBuffer;
import com.google.android.gms.common.data.DataHolder;

public final class AchievementBuffer
  extends DataBuffer<Achievement>
{
  public AchievementBuffer(DataHolder paramDataHolder)
  {
    super(paramDataHolder);
  }
  
  public Achievement get(int paramInt)
  {
    return new AchievementRef(this.II, paramInt);
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\games\achievement\AchievementBuffer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */