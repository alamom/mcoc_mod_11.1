package com.facebook;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.facebook.internal.NativeProtocol;

public class FacebookBroadcastReceiver
  extends BroadcastReceiver
{
  protected void onFailedAppCall(String paramString1, String paramString2, Bundle paramBundle) {}
  
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    String str = paramIntent.getStringExtra("com.facebook.platform.protocol.CALL_ID");
    paramContext = paramIntent.getStringExtra("com.facebook.platform.protocol.PROTOCOL_ACTION");
    Bundle localBundle;
    if ((str != null) && (paramContext != null))
    {
      localBundle = paramIntent.getExtras();
      if (!NativeProtocol.isErrorResult(paramIntent)) {
        break label45;
      }
      onFailedAppCall(str, paramContext, localBundle);
    }
    for (;;)
    {
      return;
      label45:
      onSuccessfulAppCall(str, paramContext, localBundle);
    }
  }
  
  protected void onSuccessfulAppCall(String paramString1, String paramString2, Bundle paramBundle) {}
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\facebook\FacebookBroadcastReceiver.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */