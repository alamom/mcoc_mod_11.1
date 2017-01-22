package com.facebook.unity;

import android.content.Intent;
import android.os.Bundle;
import com.unity3d.player.UnityPlayerNativeActivity;

@Deprecated
public class FBUnityPlayerNativeActivity
  extends UnityPlayerNativeActivity
{
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    FB.SetIntent(getIntent());
  }
  
  public void onNewIntent(Intent paramIntent)
  {
    super.onNewIntent(paramIntent);
    FB.SetIntent(paramIntent);
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\facebook\unity\FBUnityPlayerNativeActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */