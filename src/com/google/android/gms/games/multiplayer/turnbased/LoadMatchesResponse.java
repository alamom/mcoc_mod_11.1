package com.google.android.gms.games.multiplayer.turnbased;

import android.os.Bundle;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.games.internal.constants.TurnBasedMatchTurnStatus;
import com.google.android.gms.games.multiplayer.InvitationBuffer;

public final class LoadMatchesResponse
{
  private final TurnBasedMatchBuffer acA;
  private final TurnBasedMatchBuffer acB;
  private final TurnBasedMatchBuffer acC;
  private final InvitationBuffer acz;
  
  public LoadMatchesResponse(Bundle paramBundle)
  {
    DataHolder localDataHolder = a(paramBundle, 0);
    if (localDataHolder != null)
    {
      this.acz = new InvitationBuffer(localDataHolder);
      localDataHolder = a(paramBundle, 1);
      if (localDataHolder == null) {
        break label101;
      }
      this.acA = new TurnBasedMatchBuffer(localDataHolder);
      label48:
      localDataHolder = a(paramBundle, 2);
      if (localDataHolder == null) {
        break label109;
      }
      this.acB = new TurnBasedMatchBuffer(localDataHolder);
      label70:
      paramBundle = a(paramBundle, 3);
      if (paramBundle == null) {
        break label117;
      }
    }
    label101:
    label109:
    label117:
    for (this.acC = new TurnBasedMatchBuffer(paramBundle);; this.acC = null)
    {
      return;
      this.acz = null;
      break;
      this.acA = null;
      break label48;
      this.acB = null;
      break label70;
    }
  }
  
  private static DataHolder a(Bundle paramBundle, int paramInt)
  {
    String str = TurnBasedMatchTurnStatus.dH(paramInt);
    if (!paramBundle.containsKey(str)) {}
    for (paramBundle = null;; paramBundle = (DataHolder)paramBundle.getParcelable(str)) {
      return paramBundle;
    }
  }
  
  public void close()
  {
    if (this.acz != null) {
      this.acz.close();
    }
    if (this.acA != null) {
      this.acA.close();
    }
    if (this.acB != null) {
      this.acB.close();
    }
    if (this.acC != null) {
      this.acC.close();
    }
  }
  
  public TurnBasedMatchBuffer getCompletedMatches()
  {
    return this.acC;
  }
  
  public InvitationBuffer getInvitations()
  {
    return this.acz;
  }
  
  public TurnBasedMatchBuffer getMyTurnMatches()
  {
    return this.acA;
  }
  
  public TurnBasedMatchBuffer getTheirTurnMatches()
  {
    return this.acB;
  }
  
  public boolean hasData()
  {
    boolean bool2 = true;
    boolean bool1;
    if ((this.acz != null) && (this.acz.getCount() > 0)) {
      bool1 = bool2;
    }
    for (;;)
    {
      return bool1;
      if (this.acA != null)
      {
        bool1 = bool2;
        if (this.acA.getCount() > 0) {}
      }
      else if (this.acB != null)
      {
        bool1 = bool2;
        if (this.acB.getCount() > 0) {}
      }
      else if (this.acC != null)
      {
        bool1 = bool2;
        if (this.acC.getCount() > 0) {}
      }
      else
      {
        bool1 = false;
      }
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\games\multiplayer\turnbased\LoadMatchesResponse.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */