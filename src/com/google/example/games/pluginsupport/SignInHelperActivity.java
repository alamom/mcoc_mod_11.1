package com.google.example.games.pluginsupport;

import android.os.Handler;
import com.google.android.gms.games.multiplayer.Invitation;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMatch;
import com.google.example.games.basegameutils.GameHelper;
import com.google.example.games.basegameutils.GameHelper.GameHelperListener;
import com.google.example.games.basegameutils.GameHelper.SignInFailureReason;

public class SignInHelperActivity
  extends HelperActivity
{
  boolean mAttempted = false;
  Handler mHandler = new Handler();
  
  private void callListener(boolean paramBoolean)
  {
    GameHelper.GameHelperListener localGameHelperListener = SignInHelperManager.getInstance().getGameHelperListener();
    SignInHelperManager.getInstance().setGameHelperListener(null);
    if (localGameHelperListener != null)
    {
      if (!paramBoolean) {
        break label29;
      }
      localGameHelperListener.onSignInSucceeded();
    }
    for (;;)
    {
      return;
      label29:
      localGameHelperListener.onSignInFailed();
    }
  }
  
  void failAndFinish()
  {
    callListener(false);
    finish();
  }
  
  public void onSignInFailed()
  {
    if (!this.mAttempted)
    {
      this.mAttempted = true;
      this.mHelper.beginUserInitiatedSignIn();
    }
    for (;;)
    {
      return;
      GameHelper.SignInFailureReason localSignInFailureReason = this.mHelper.getSignInError();
      SignInHelperManager.getInstance().setSignInErrorReason(localSignInFailureReason);
      failAndFinish();
    }
  }
  
  public void onSignInSucceeded()
  {
    Object localObject = this.mHelper.getInvitation();
    if (localObject != null) {
      SignInHelperManager.getInstance().setInvitation((Invitation)localObject);
    }
    localObject = this.mHelper.getTurnBasedMatch();
    if (localObject != null) {
      SignInHelperManager.getInstance().setTurnBasedMatch((TurnBasedMatch)localObject);
    }
    this.mHandler.postDelayed(new Runnable()
    {
      public void run()
      {
        SignInHelperActivity.this.callListener(true);
        SignInHelperActivity.this.finish();
      }
    }, 1000L);
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\example\games\pluginsupport\SignInHelperActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */