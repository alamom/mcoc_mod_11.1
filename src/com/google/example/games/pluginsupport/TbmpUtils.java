package com.google.example.games.pluginsupport;

import android.os.Bundle;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMatchConfig;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMatchConfig.Builder;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer.InitiateMatchResult;
import java.util.ArrayList;

public class TbmpUtils
{
  public static PendingResult<TurnBasedMultiplayer.InitiateMatchResult> create(GoogleApiClient paramGoogleApiClient, ArrayList<String> paramArrayList, int paramInt, Bundle paramBundle)
  {
    TurnBasedMatchConfig.Builder localBuilder = TurnBasedMatchConfig.builder();
    if (paramInt > 0) {
      localBuilder.setVariant(paramInt);
    }
    if (paramArrayList != null) {
      localBuilder.addInvitedPlayers(paramArrayList);
    }
    localBuilder.setAutoMatchCriteria(paramBundle);
    return Games.TurnBasedMultiplayer.createMatch(paramGoogleApiClient, localBuilder.build());
  }
  
  public static PendingResult<TurnBasedMultiplayer.InitiateMatchResult> createQuickMatch(GoogleApiClient paramGoogleApiClient, int paramInt1, int paramInt2, int paramInt3)
  {
    return create(paramGoogleApiClient, null, paramInt3, TurnBasedMatchConfig.createAutoMatchCriteria(paramInt1, paramInt2, 0L));
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\example\games\pluginsupport\TbmpUtils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */