package com.matunityutils;

import com.mobileapptracker.MATResponse;
import com.unity3d.player.UnityPlayer;
import org.json.JSONObject;

public class MATUnityResponse
  implements MATResponse
{
  public static final String UNITY_RECEIVER_OBJECT = "MobileAppTracker";
  
  public void didFailWithError(JSONObject paramJSONObject)
  {
    if (paramJSONObject != null) {
      UnityPlayer.UnitySendMessage("MobileAppTracker", "trackerDidFail", paramJSONObject.toString());
    }
    for (;;)
    {
      return;
      UnityPlayer.UnitySendMessage("MobileAppTracker", "trackerDidFail", "");
    }
  }
  
  public void didReceiveDeeplink(String paramString)
  {
    UnityPlayer.UnitySendMessage("MobileAppTracker", "trackerDidReceiveDeepLink", paramString);
  }
  
  public void didSucceedWithData(JSONObject paramJSONObject)
  {
    UnityPlayer.UnitySendMessage("MobileAppTracker", "trackerDidSucceed", paramJSONObject.toString());
  }
  
  public void enqueuedActionWithRefId(String paramString)
  {
    UnityPlayer.UnitySendMessage("MobileAppTracker", "trackerDidEnqueueRequest", paramString);
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\matunityutils\MATUnityResponse.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */