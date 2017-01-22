package com.google.android.gms.games.leaderboard;

import android.os.Bundle;

public final class LeaderboardScoreBufferHeader
{
  private final Bundle Nh;
  
  public LeaderboardScoreBufferHeader(Bundle paramBundle)
  {
    Bundle localBundle = paramBundle;
    if (paramBundle == null) {
      localBundle = new Bundle();
    }
    this.Nh = localBundle;
  }
  
  public Bundle lB()
  {
    return this.Nh;
  }
  
  public static final class Builder {}
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\games\leaderboard\LeaderboardScoreBufferHeader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */