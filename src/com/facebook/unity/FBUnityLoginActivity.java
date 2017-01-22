package com.facebook.unity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import com.facebook.Session;

public class FBUnityLoginActivity
  extends Activity
{
  public static final String LOGIN_PARAMS = "login_params";
  
  public void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    if (Session.getActiveSession() != null) {
      FBLogin.onActivityResult(this, paramInt1, paramInt2, paramIntent);
    }
    for (;;)
    {
      return;
      finish();
    }
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    FBLogin.login(getIntent().getStringExtra("login_params"), this);
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\facebook\unity\FBUnityLoginActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */