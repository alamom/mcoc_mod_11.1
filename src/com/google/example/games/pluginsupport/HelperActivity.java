package com.google.example.games.pluginsupport;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import com.google.example.games.basegameutils.GameHelper;
import com.google.example.games.basegameutils.GameHelper.GameHelperListener;

public class HelperActivity
  extends Activity
  implements GameHelper.GameHelperListener
{
  static final int BG_COLOR = 1090519039;
  public static String EXTRA_DEBUG_ENABLED = "EXTRA_DEBUG_ENABLED";
  String TAG = "PluginSupport";
  protected boolean mDebugEnabled = false;
  protected GameHelper mHelper = null;
  
  protected void debugLog(String paramString)
  {
    if (this.mDebugEnabled) {
      Log.d(this.TAG, paramString);
    }
  }
  
  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    debugLog("onActivityResult(" + paramInt1 + ", " + paramInt2 + ", " + paramIntent);
    this.mHelper.onActivityResult(paramInt1, paramInt2, paramIntent);
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
  }
  
  public void onCreate(Bundle paramBundle)
  {
    if ((getIntent() != null) && (getIntent().getBooleanExtra(EXTRA_DEBUG_ENABLED, false))) {}
    for (boolean bool = true;; bool = false)
    {
      this.mDebugEnabled = bool;
      debugLog("onCreate()");
      this.mHelper = new GameHelper(this, 7);
      this.mHelper.enableDebugLog(this.mDebugEnabled, this.TAG + "/GameHelper");
      this.mHelper.setShowErrorDialogs(false);
      this.mHelper.setMaxAutoSignInAttempts(0);
      this.mHelper.setup(this);
      View localView = new View(this);
      localView.setBackgroundColor(1090519039);
      setContentView(localView);
      super.onCreate(paramBundle);
      return;
    }
  }
  
  public void onSignInFailed()
  {
    debugLog("onSignInFailed()");
  }
  
  public void onSignInSucceeded()
  {
    debugLog("onSignInSuceeded()");
  }
  
  protected void onStart()
  {
    debugLog("onStart()");
    this.mHelper.onStart(this);
    super.onStart();
  }
  
  protected void onStop()
  {
    debugLog("onStop()");
    this.mHelper.onStop();
    super.onStop();
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\example\games\pluginsupport\HelperActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */