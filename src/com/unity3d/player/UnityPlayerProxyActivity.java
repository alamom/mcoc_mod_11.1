package com.unity3d.player;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class UnityPlayerProxyActivity
  extends Activity
{
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    Intent localIntent = new Intent(this, UnityPlayerActivity.class);
    localIntent.addFlags(65536);
    paramBundle = getIntent().getExtras();
    if (paramBundle != null) {
      localIntent.putExtras(paramBundle);
    }
    startActivity(localIntent);
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\unity3d\player\UnityPlayerProxyActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */