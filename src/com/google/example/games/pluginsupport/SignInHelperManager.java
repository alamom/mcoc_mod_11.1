package com.google.example.games.pluginsupport;

import android.app.Activity;
import android.content.Intent;
import com.google.android.gms.games.multiplayer.Invitation;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMatch;
import com.google.example.games.basegameutils.GameHelper;
import com.google.example.games.basegameutils.GameHelper.GameHelperListener;
import com.google.example.games.basegameutils.GameHelper.SignInFailureReason;

public class SignInHelperManager
{
  private static final int RC_UNUSED = 99999;
  private static final String TAG = "SignInHelperManager";
  private static SignInHelperManager sInstance = new SignInHelperManager();
  Invitation mInvitation = null;
  GameHelper.GameHelperListener mListener = null;
  TurnBasedMatch mMatch = null;
  int mSignInErrorActivityResponse = 0;
  int mSignInErrorCode = 0;
  
  public static SignInHelperManager getInstance()
  {
    return sInstance;
  }
  
  public static void launchSignIn(Activity paramActivity, GameHelper.GameHelperListener paramGameHelperListener, boolean paramBoolean)
  {
    sInstance.setGameHelperListener(paramGameHelperListener);
    paramGameHelperListener = new Intent(paramActivity, SignInHelperActivity.class);
    paramGameHelperListener.putExtra(SignInHelperActivity.EXTRA_DEBUG_ENABLED, paramBoolean);
    paramActivity.startActivity(paramGameHelperListener);
  }
  
  public static void showErrorDialog(Activity paramActivity)
  {
    if (sInstance.mSignInErrorCode != 0) {
      GameHelper.showFailureDialog(paramActivity, sInstance.mSignInErrorActivityResponse, sInstance.mSignInErrorCode);
    }
  }
  
  public void forgetInvitation()
  {
    this.mInvitation = null;
  }
  
  public void forgetTurnBasedMatch()
  {
    this.mMatch = null;
  }
  
  public GameHelper.GameHelperListener getGameHelperListener()
  {
    return this.mListener;
  }
  
  public Invitation getInvitation()
  {
    return this.mInvitation;
  }
  
  public TurnBasedMatch getTurnBasedMatch()
  {
    return this.mMatch;
  }
  
  public boolean hasInvitation()
  {
    if (this.mInvitation != null) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public boolean hasTurnBasedMatch()
  {
    if (this.mMatch != null) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public void setGameHelperListener(GameHelper.GameHelperListener paramGameHelperListener)
  {
    this.mListener = paramGameHelperListener;
  }
  
  public void setInvitation(Invitation paramInvitation)
  {
    this.mInvitation = paramInvitation;
  }
  
  void setSignInErrorReason(GameHelper.SignInFailureReason paramSignInFailureReason)
  {
    if (paramSignInFailureReason != null) {
      this.mSignInErrorActivityResponse = paramSignInFailureReason.getActivityResultCode();
    }
    for (this.mSignInErrorCode = paramSignInFailureReason.getServiceErrorCode();; this.mSignInErrorCode = 0)
    {
      return;
      this.mSignInErrorActivityResponse = -1;
    }
  }
  
  public void setTurnBasedMatch(TurnBasedMatch paramTurnBasedMatch)
  {
    this.mMatch = paramTurnBasedMatch;
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\example\games\pluginsupport\SignInHelperManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */