package com.google.android.gms.games.leaderboard;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.d;

public final class LeaderboardVariantRef
  extends d
  implements LeaderboardVariant
{
  LeaderboardVariantRef(DataHolder paramDataHolder, int paramInt)
  {
    super(paramDataHolder, paramInt);
  }
  
  public boolean equals(Object paramObject)
  {
    return LeaderboardVariantEntity.a(this, paramObject);
  }
  
  public int getCollection()
  {
    return getInteger("collection");
  }
  
  public String getDisplayPlayerRank()
  {
    return getString("player_display_rank");
  }
  
  public String getDisplayPlayerScore()
  {
    return getString("player_display_score");
  }
  
  public long getNumScores()
  {
    if (aS("total_scores")) {}
    for (long l = -1L;; l = getLong("total_scores")) {
      return l;
    }
  }
  
  public long getPlayerRank()
  {
    if (aS("player_rank")) {}
    for (long l = -1L;; l = getLong("player_rank")) {
      return l;
    }
  }
  
  public String getPlayerScoreTag()
  {
    return getString("player_score_tag");
  }
  
  public long getRawPlayerScore()
  {
    if (aS("player_raw_score")) {}
    for (long l = -1L;; l = getLong("player_raw_score")) {
      return l;
    }
  }
  
  public int getTimeSpan()
  {
    return getInteger("timespan");
  }
  
  public boolean hasPlayerInfo()
  {
    if (!aS("player_raw_score")) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public int hashCode()
  {
    return LeaderboardVariantEntity.a(this);
  }
  
  public String lD()
  {
    return getString("top_page_token_next");
  }
  
  public String lE()
  {
    return getString("window_page_token_prev");
  }
  
  public String lF()
  {
    return getString("window_page_token_next");
  }
  
  public LeaderboardVariant lG()
  {
    return new LeaderboardVariantEntity(this);
  }
  
  public String toString()
  {
    return LeaderboardVariantEntity.b(this);
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\games\leaderboard\LeaderboardVariantRef.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */