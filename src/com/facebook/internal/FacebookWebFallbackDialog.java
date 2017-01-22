package com.facebook.internal;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.webkit.WebView;
import com.facebook.FacebookException;
import com.facebook.widget.FacebookDialog;
import com.facebook.widget.FacebookDialog.Callback;
import com.facebook.widget.FacebookDialog.PendingCall;
import com.facebook.widget.WebDialog;
import com.facebook.widget.WebDialog.OnCompleteListener;
import org.json.JSONException;
import org.json.JSONObject;

public class FacebookWebFallbackDialog
  extends WebDialog
{
  private static final int OS_BACK_BUTTON_RESPONSE_TIMEOUT_MILLISECONDS = 1500;
  private static final String TAG = FacebookWebFallbackDialog.class.getName();
  private boolean waitingForDialogToClose;
  
  private FacebookWebFallbackDialog(Context paramContext, String paramString1, String paramString2)
  {
    super(paramContext, paramString1);
    setExpectedRedirectUrl(paramString2);
  }
  
  public static boolean presentWebFallback(Context paramContext, String paramString1, String paramString2, final FacebookDialog.PendingCall paramPendingCall, final FacebookDialog.Callback paramCallback)
  {
    boolean bool = false;
    if (Utility.isNullOrEmpty(paramString1)) {}
    for (;;)
    {
      return bool;
      paramString1 = new FacebookWebFallbackDialog(paramContext, paramString1, String.format("fb%s://bridge/", new Object[] { paramString2 }));
      paramString1.setOnCompleteListener(new WebDialog.OnCompleteListener()
      {
        public void onComplete(Bundle paramAnonymousBundle, FacebookException paramAnonymousFacebookException)
        {
          Intent localIntent = new Intent();
          paramAnonymousFacebookException = paramAnonymousBundle;
          if (paramAnonymousBundle == null) {
            paramAnonymousFacebookException = new Bundle();
          }
          localIntent.putExtras(paramAnonymousFacebookException);
          FacebookDialog.handleActivityResult(this.val$context, paramPendingCall, paramPendingCall.getRequestCode(), localIntent, paramCallback);
        }
      });
      paramString1.show();
      bool = true;
    }
  }
  
  public void dismiss()
  {
    WebView localWebView = getWebView();
    if ((isListenerCalled()) || (localWebView == null) || (!localWebView.isShown())) {
      super.dismiss();
    }
    for (;;)
    {
      return;
      if (!this.waitingForDialogToClose)
      {
        this.waitingForDialogToClose = true;
        localWebView.loadUrl("javascript:" + "(function() {  var event = document.createEvent('Event');  event.initEvent('fbPlatformDialogMustClose',true,true);  document.dispatchEvent(event);})();");
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable()
        {
          public void run()
          {
            if (!FacebookWebFallbackDialog.this.isListenerCalled()) {
              FacebookWebFallbackDialog.this.sendCancelToListener();
            }
          }
        }, 1500L);
      }
    }
  }
  
  protected Bundle parseResponseUri(String paramString)
  {
    Bundle localBundle = Utility.parseUrlQueryString(Uri.parse(paramString).getQuery());
    Object localObject = localBundle.getString("bridge_args");
    localBundle.remove("bridge_args");
    if (!Utility.isNullOrEmpty((String)localObject)) {}
    try
    {
      paramString = new org/json/JSONObject;
      paramString.<init>((String)localObject);
      localBundle.putBundle("com.facebook.platform.protocol.BRIDGE_ARGS", BundleJSONConverter.convertToBundle(paramString));
      localObject = localBundle.getString("method_results");
      localBundle.remove("method_results");
      if (!Utility.isNullOrEmpty((String)localObject))
      {
        paramString = (String)localObject;
        if (Utility.isNullOrEmpty((String)localObject)) {
          paramString = "{}";
        }
      }
    }
    catch (JSONException paramString)
    {
      try
      {
        localObject = new org/json/JSONObject;
        ((JSONObject)localObject).<init>(paramString);
        localBundle.putBundle("com.facebook.platform.protocol.RESULT_ARGS", BundleJSONConverter.convertToBundle((JSONObject)localObject));
        localBundle.remove("version");
        localBundle.putInt("com.facebook.platform.protocol.PROTOCOL_VERSION", NativeProtocol.getLatestKnownVersion());
        return localBundle;
        paramString = paramString;
        Utility.logd(TAG, "Unable to parse bridge_args JSON", paramString);
      }
      catch (JSONException paramString)
      {
        for (;;)
        {
          Utility.logd(TAG, "Unable to parse bridge_args JSON", paramString);
        }
      }
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\facebook\internal\FacebookWebFallbackDialog.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */