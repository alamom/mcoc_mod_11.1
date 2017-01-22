package com.google.example.games.pluginsupport;

import android.content.Intent;
import android.util.Log;
import com.google.example.games.basegameutils.GameHelper;
import com.google.example.games.basegameutils.GameHelper.SignInFailureReason;

public abstract class UiHelperActivity
  extends HelperActivity
{
  static final int RC_UI = 9876;
  boolean mAttempted = false;
  
  protected abstract void deliverFailure();
  
  protected abstract void deliverSuccess(Intent paramIntent);
  
  protected abstract Intent getUiIntent();
  
  public void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    if (paramInt1 != 9876) {
      debugLog("Ignoring activity result with request code " + paramInt1);
    }
    for (;;)
    {
      return;
      if (paramInt2 != -1)
      {
        Log.w(this.TAG, "UI cancelled.");
        deliverFailure();
        finish();
      }
      else
      {
        debugLog("UI succeeded.");
        deliverSuccess(paramIntent);
        finish();
      }
    }
  }
  
  public void onSignInFailed()
  {
    super.onSignInFailed();
    Log.e(this.TAG, "Sign-in failed on UI helper activity.");
    GameHelper.SignInFailureReason localSignInFailureReason = this.mHelper.getSignInError();
    if (localSignInFailureReason != null) {
      Log.e(this.TAG, "Sign-in failure reason: " + localSignInFailureReason.toString());
    }
    debugLog("Delivering failure to listener.");
    deliverFailure();
    finish();
  }
  
  public void onSignInSucceeded()
  {
    super.onSignInSucceeded();
    if (this.mAttempted) {
      Log.w(this.TAG, "Ignoring onSignInSuceeded because we already launched the UI.");
    }
    for (;;)
    {
      return;
      this.mAttempted = true;
      Intent localIntent = getUiIntent();
      debugLog("Launching intent");
      startActivityForResult(localIntent, 9876);
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\example\games\pluginsupport\UiHelperActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */