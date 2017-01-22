package com.google.example.games.pluginsupport;

import android.app.Activity;
import android.content.Intent;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.multiplayer.realtime.RealTimeMultiplayer;
import com.google.android.gms.games.multiplayer.realtime.RoomConfig;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer;
import com.google.example.games.basegameutils.GameHelper;
import java.util.ArrayList;

public class SelectOpponentsHelperActivity
  extends UiHelperActivity
{
  public static final String EXTRA_IS_RTMP = "EXTRA_IS_RTMP";
  public static final String EXTRA_MAX_OPPONENTS = "EXTRA_MAX_OPPONENTS";
  public static final String EXTRA_MIN_OPPONENTS = "EXTRA_MIN_OPPONENTS";
  static Listener sListener = null;
  Object mDummyObject = new Object();
  
  public static void launch(boolean paramBoolean1, Activity paramActivity, Listener paramListener, boolean paramBoolean2, int paramInt1, int paramInt2)
  {
    setListener(paramListener);
    paramListener = new Intent(paramActivity, SelectOpponentsHelperActivity.class);
    paramListener.putExtra(EXTRA_DEBUG_ENABLED, paramBoolean2);
    paramListener.putExtra("EXTRA_IS_RTMP", paramBoolean1);
    paramListener.putExtra("EXTRA_MIN_OPPONENTS", paramInt1);
    paramListener.putExtra("EXTRA_MAX_OPPONENTS", paramInt2);
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
      sListener.onSelectOpponentsResult(false, this.mDummyObject, false, this.mDummyObject);
      sListener = null;
    }
  }
  
  protected void deliverSuccess(Intent paramIntent)
  {
    boolean bool = false;
    ArrayList localArrayList = paramIntent.getStringArrayListExtra("players");
    debugLog("Invitee count: " + localArrayList.size());
    Object localObject = null;
    int i = paramIntent.getIntExtra("min_automatch_players", 0);
    int j = paramIntent.getIntExtra("max_automatch_players", 0);
    if ((i > 0) || (j > 0))
    {
      paramIntent = RoomConfig.createAutoMatchCriteria(i, j, 0L);
      debugLog("Automatch criteria: " + paramIntent);
    }
    for (;;)
    {
      if (sListener != null)
      {
        debugLog("Calling listener.");
        Listener localListener = sListener;
        if (paramIntent != null) {
          bool = true;
        }
        localObject = paramIntent;
        if (paramIntent == null) {
          localObject = this.mDummyObject;
        }
        localListener.onSelectOpponentsResult(true, localArrayList, bool, localObject);
        sListener = null;
      }
      return;
      debugLog("No automatch criteria.");
      paramIntent = (Intent)localObject;
    }
  }
  
  protected Intent getUiIntent()
  {
    int i = getIntent().getIntExtra("EXTRA_MIN_OPPONENTS", 1);
    int j = getIntent().getIntExtra("EXTRA_MAX_OPPONENTS", i);
    if (getIntent().getBooleanExtra("EXTRA_IS_RTMP", true)) {}
    for (Intent localIntent = Games.RealTimeMultiplayer.getSelectOpponentsIntent(this.mHelper.getApiClient(), i, j);; localIntent = Games.TurnBasedMultiplayer.getSelectOpponentsIntent(this.mHelper.getApiClient(), i, j, true)) {
      return localIntent;
    }
  }
  
  public static abstract interface Listener
  {
    public abstract void onSelectOpponentsResult(boolean paramBoolean1, Object paramObject1, boolean paramBoolean2, Object paramObject2);
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\example\games\pluginsupport\SelectOpponentsHelperActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */