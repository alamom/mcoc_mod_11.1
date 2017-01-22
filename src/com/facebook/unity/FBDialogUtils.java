package com.facebook.unity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import com.facebook.widget.FacebookDialog.ShareDialogBuilder;
import java.util.Arrays;
import java.util.List;

public class FBDialogUtils
{
  private static final List<String> SUPPORTED_SHARE_DIALOG_PARAMS = Arrays.asList(new String[] { "callback_id", "name", "caption", "description", "link", "picture", "place", "friends", "ref" });
  
  public static FacebookDialog.ShareDialogBuilder createShareDialogBuilder(Activity paramActivity, Bundle paramBundle)
  {
    paramActivity = new FacebookDialog.ShareDialogBuilder(paramActivity);
    if (paramBundle.containsKey("name")) {
      paramActivity.setName(paramBundle.getString("name"));
    }
    if (paramBundle.containsKey("caption")) {
      paramActivity.setCaption(paramBundle.getString("caption"));
    }
    if (paramBundle.containsKey("description")) {
      paramActivity.setDescription(paramBundle.getString("description"));
    }
    if (paramBundle.containsKey("link")) {
      paramActivity.setLink(paramBundle.getString("link"));
    }
    if (paramBundle.containsKey("picture")) {
      paramActivity.setPicture(paramBundle.getString("picture"));
    }
    if (paramBundle.containsKey("place")) {
      paramActivity.setPlace(paramBundle.getString("place"));
    }
    if (paramBundle.containsKey("ref")) {
      paramActivity.setRef(paramBundle.getString("ref"));
    }
    return paramActivity;
  }
  
  public static boolean hasUnsupportedParams(DialogType paramDialogType, Bundle paramBundle)
  {
    boolean bool = false;
    switch (paramDialogType)
    {
    default: 
      Log.e("FBUnitySDK", "Unrecognized Dialog Type");
    }
    for (;;)
    {
      return bool;
      if (!SUPPORTED_SHARE_DIALOG_PARAMS.containsAll(paramBundle.keySet())) {
        bool = true;
      }
    }
  }
  
  public static enum DialogType
  {
    SHARE_DIALOG;
    
    private DialogType() {}
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\facebook\unity\FBDialogUtils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */