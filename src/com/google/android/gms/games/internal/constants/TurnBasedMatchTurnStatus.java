package com.google.android.gms.games.internal.constants;

import com.google.android.gms.games.internal.GamesLog;

public final class TurnBasedMatchTurnStatus
{
  public static String dH(int paramInt)
  {
    String str;
    switch (paramInt)
    {
    default: 
      GamesLog.q("MatchTurnStatus", "Unknown match turn status: " + paramInt);
      str = "TURN_STATUS_UNKNOWN";
    }
    for (;;)
    {
      return str;
      str = "TURN_STATUS_INVITED";
      continue;
      str = "TURN_STATUS_MY_TURN";
      continue;
      str = "TURN_STATUS_THEIR_TURN";
      continue;
      str = "TURN_STATUS_COMPLETE";
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\games\internal\constants\TurnBasedMatchTurnStatus.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */