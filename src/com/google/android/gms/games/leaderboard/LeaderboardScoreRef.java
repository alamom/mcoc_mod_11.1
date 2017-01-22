package com.google.android.gms.games.leaderboard;

import android.database.CharArrayBuffer;
import android.net.Uri;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.d;
import com.google.android.gms.games.Player;
import com.google.android.gms.games.PlayerRef;

public final class LeaderboardScoreRef
  extends d
  implements LeaderboardScore
{
  private final PlayerRef abL;
  
  LeaderboardScoreRef(DataHolder paramDataHolder, int paramInt)
  {
    super(paramDataHolder, paramInt);
    this.abL = new PlayerRef(paramDataHolder, paramInt);
  }
  
  public boolean equals(Object paramObject)
  {
    return LeaderboardScoreEntity.a(this, paramObject);
  }
  
  public String getDisplayRank()
  {
    return getString("display_rank");
  }
  
  public void getDisplayRank(CharArrayBuffer paramCharArrayBuffer)
  {
    a("display_rank", paramCharArrayBuffer);
  }
  
  public String getDisplayScore()
  {
    return getString("display_score");
  }
  
  public void getDisplayScore(CharArrayBuffer paramCharArrayBuffer)
  {
    a("display_score", paramCharArrayBuffer);
  }
  
  public long getRank()
  {
    return getLong("rank");
  }
  
  public long getRawScore()
  {
    return getLong("raw_score");
  }
  
  public Player getScoreHolder()
  {
    if (aS("external_player_id")) {}
    for (Object localObject = null;; localObject = this.abL) {
      return (Player)localObject;
    }
  }
  
  public String getScoreHolderDisplayName()
  {
    if (aS("external_player_id")) {}
    for (String str = getString("default_display_name");; str = this.abL.getDisplayName()) {
      return str;
    }
  }
  
  public void getScoreHolderDisplayName(CharArrayBuffer paramCharArrayBuffer)
  {
    if (aS("external_player_id")) {
      a("default_display_name", paramCharArrayBuffer);
    }
    for (;;)
    {
      return;
      this.abL.getDisplayName(paramCharArrayBuffer);
    }
  }
  
  public Uri getScoreHolderHiResImageUri()
  {
    if (aS("external_player_id")) {}
    for (Uri localUri = null;; localUri = this.abL.getHiResImageUri()) {
      return localUri;
    }
  }
  
  public String getScoreHolderHiResImageUrl()
  {
    if (aS("external_player_id")) {}
    for (String str = null;; str = this.abL.getHiResImageUrl()) {
      return str;
    }
  }
  
  public Uri getScoreHolderIconImageUri()
  {
    if (aS("external_player_id")) {}
    for (Uri localUri = aR("default_display_image_uri");; localUri = this.abL.getIconImageUri()) {
      return localUri;
    }
  }
  
  public String getScoreHolderIconImageUrl()
  {
    if (aS("external_player_id")) {}
    for (String str = getString("default_display_image_url");; str = this.abL.getIconImageUrl()) {
      return str;
    }
  }
  
  public String getScoreTag()
  {
    return getString("score_tag");
  }
  
  public long getTimestampMillis()
  {
    return getLong("achieved_timestamp");
  }
  
  public int hashCode()
  {
    return LeaderboardScoreEntity.a(this);
  }
  
  public LeaderboardScore lC()
  {
    return new LeaderboardScoreEntity(this);
  }
  
  public String toString()
  {
    return LeaderboardScoreEntity.b(this);
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\games\leaderboard\LeaderboardScoreRef.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */