package com.google.example.games.pluginsupport;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.multiplayer.Invitation;
import com.google.android.gms.games.multiplayer.Invitations;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMatch;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer;
import com.google.example.games.basegameutils.GameHelper;

public class InvitationInboxHelperActivity
  extends UiHelperActivity
{
  private static final String EXTRA_IS_RTMP = "EXTRA_IS_RTMP";
  static Listener sListener = null;
  
  public static void launch(boolean paramBoolean1, Activity paramActivity, Listener paramListener, boolean paramBoolean2)
  {
    setListener(paramListener);
    paramListener = new Intent(paramActivity, InvitationInboxHelperActivity.class);
    paramListener.putExtra("EXTRA_IS_RTMP", paramBoolean1);
    paramListener.putExtra(EXTRA_DEBUG_ENABLED, paramBoolean2);
    paramActivity.startActivity(paramListener);
  }
  
  public static void setListener(Listener paramListener)
  {
    sListener = paramListener;
  }
  
  protected void deliverFailure()
  {
    if (sListener != null)
    {
      debugLog("Delivering failure to listener.");
      sListener.onInvitationInboxResult(false, "");
      sListener = null;
    }
  }
  
  protected void deliverSuccess(Intent paramIntent)
  {
    debugLog("Parsing invitation/match from UI's returned data.");
    Invitation localInvitation = (Invitation)paramIntent.getExtras().getParcelable("invitation");
    TurnBasedMatch localTurnBasedMatch = (TurnBasedMatch)paramIntent.getExtras().getParcelable("turn_based_match");
    StringBuilder localStringBuilder = new StringBuilder().append("Invitation: ");
    if (localInvitation == null)
    {
      paramIntent = "null";
      debugLog(paramIntent);
      localStringBuilder = new StringBuilder().append("Match: ");
      if (localTurnBasedMatch != null) {
        break label138;
      }
      paramIntent = "null";
      label87:
      debugLog(paramIntent);
      if (localInvitation == null) {
        break label144;
      }
      debugLog("Calling listener to deliver invitation.");
      sListener.onInvitationInboxResult(true, localInvitation.getInvitationId());
    }
    for (;;)
    {
      sListener = null;
      return;
      paramIntent = localInvitation.toString();
      break;
      label138:
      paramIntent = "[TurnBasedMatch]";
      break label87;
      label144:
      if (localTurnBasedMatch != null)
      {
        debugLog("Calling listener to deliver match.");
        sListener.onTurnBasedMatch(localTurnBasedMatch);
      }
      else
      {
        Log.w(this.TAG, "Invitation inbox result came with no invitation and no match!");
        debugLog("Calling listener to deliver failure.");
        sListener.onInvitationInboxResult(false, null);
      }
    }
  }
  
  protected Intent getUiIntent()
  {
    if (getIntent().getBooleanExtra("EXTRA_IS_RTMP", true)) {}
    for (Intent localIntent = Games.Invitations.getInvitationInboxIntent(this.mHelper.getApiClient());; localIntent = Games.TurnBasedMultiplayer.getInboxIntent(this.mHelper.getApiClient())) {
      return localIntent;
    }
  }
  
  public static abstract interface Listener
  {
    public abstract void onInvitationInboxResult(boolean paramBoolean, String paramString);
    
    public abstract void onTurnBasedMatch(TurnBasedMatch paramTurnBasedMatch);
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\example\games\pluginsupport\InvitationInboxHelperActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */