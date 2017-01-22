package com.facebook.unity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import com.facebook.FacebookOperationCanceledException;
import com.facebook.UiLifecycleHelper;
import com.facebook.widget.FacebookDialog;
import com.facebook.widget.FacebookDialog.Callback;
import com.facebook.widget.FacebookDialog.PendingCall;
import com.facebook.widget.FacebookDialog.ShareDialogBuilder;

public class FBUnityDialogsActivity
  extends Activity
{
  public static final String DIALOG_PARAMS = "dialog_params";
  public static final String DIALOG_TYPE = "dialog_type";
  private FBDialogUtils.DialogType dialogType;
  private Bundle params;
  private UiLifecycleHelper uiHelper;
  
  public void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    switch (this.dialogType)
    {
    default: 
      Log.e("FBUnitySDK", "Unrecognized Dialog Type");
    }
    for (;;)
    {
      return;
      final UnityMessage localUnityMessage = new UnityMessage("OnFeedRequestComplete");
      String str = this.params.getString("callback_id");
      if (str != null) {
        localUnityMessage.put("callback_id", str);
      }
      this.uiHelper.onActivityResult(paramInt1, paramInt2, paramIntent, new FacebookDialog.Callback()
      {
        public void onComplete(FacebookDialog.PendingCall paramAnonymousPendingCall, Bundle paramAnonymousBundle)
        {
          if ((FacebookDialog.getNativeDialogDidComplete(paramAnonymousBundle)) && (FacebookDialog.getNativeDialogCompletionGesture(paramAnonymousBundle).equals("post")))
          {
            paramAnonymousPendingCall = FacebookDialog.getNativeDialogPostId(paramAnonymousBundle);
            if (paramAnonymousPendingCall != null) {
              localUnityMessage.putID(paramAnonymousPendingCall);
            }
            localUnityMessage.put("posted", Boolean.valueOf(true));
          }
          for (;;)
          {
            localUnityMessage.send();
            return;
            localUnityMessage.putCancelled();
          }
        }
        
        public void onError(FacebookDialog.PendingCall paramAnonymousPendingCall, Exception paramAnonymousException, Bundle paramAnonymousBundle)
        {
          if ((paramAnonymousException instanceof FacebookOperationCanceledException))
          {
            localUnityMessage.putCancelled();
            localUnityMessage.send();
          }
          for (;;)
          {
            return;
            localUnityMessage.sendError(paramAnonymousException.toString());
          }
        }
      });
      finish();
    }
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    this.uiHelper = new UiLifecycleHelper(this, null);
    this.uiHelper.onCreate(paramBundle);
    this.params = getIntent().getBundleExtra("dialog_params");
    this.dialogType = ((FBDialogUtils.DialogType)getIntent().getSerializableExtra("dialog_type"));
    switch (this.dialogType)
    {
    default: 
      Log.e("FBUnitySDK", "Unrecognized Dialog Type");
    }
    for (;;)
    {
      return;
      paramBundle = FBDialogUtils.createShareDialogBuilder(this, this.params);
      this.uiHelper.trackPendingDialogCall(paramBundle.build().present());
    }
  }
  
  public void onDestroy()
  {
    super.onDestroy();
    this.uiHelper.onDestroy();
  }
  
  public void onPause()
  {
    super.onPause();
    this.uiHelper.onPause();
  }
  
  protected void onResume()
  {
    super.onResume();
    this.uiHelper.onResume();
  }
  
  protected void onSaveInstanceState(Bundle paramBundle)
  {
    super.onSaveInstanceState(paramBundle);
    this.uiHelper.onSaveInstanceState(paramBundle);
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\facebook\unity\FBUnityDialogsActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */