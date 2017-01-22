package com.explodingbarrel.deeplinking;

import android.app.Activity;
import android.net.Uri;
import android.util.Log;
import com.facebook.AppLinkData;
import com.facebook.AppLinkData.CompletionHandler;
import com.unity3d.player.UnityPlayer;
import java.util.ArrayList;
import java.util.Iterator;

public class DeepLinkingManager
{
  private static final String TAG = "DeepLinkingManager";
  private static final String UNITY_PLUGIN_NAME = "deeplinking_plugin_android";
  public static DeepLinkingManager _this = null;
  private ArrayList<String> _pendingDeepLinks = new ArrayList();
  boolean _supported = false;
  
  public static void AddAppLaunchDeepLink(String paramString)
  {
    if (_this == null)
    {
      Log.d("DeepLinkingManager", "Constructing DeepLinking Manager - AddAppLaunchDeepLink");
      _this = new DeepLinkingManager();
    }
    Log.d("DeepLinkingManager", "Adding Pending DeepLink");
    _this._pendingDeepLinks.add(paramString);
  }
  
  public static void FetchDeferredDeepLink(String paramString)
  {
    if (_this != null) {
      _this._FetchDeferredDeepLink(paramString);
    }
  }
  
  public static void Init()
  {
    if (_this == null)
    {
      Log.d("DeepLinkingManager", "Constructing DeepLinking Manager - Init");
      _this = new DeepLinkingManager();
    }
    _this._Init();
  }
  
  private static void SendMessage(String paramString1, String paramString2)
  {
    UnityPlayer.UnitySendMessage("deeplinking_plugin_android", paramString1, paramString2);
  }
  
  private void _FetchDeferredDeepLink(String paramString)
  {
    Log.d("DeepLinkingManager", "Fetching Deferred Deep Link");
    try
    {
      Log.d("DeepLinkingManager", "Calling Facebook for Deferred Deep Link");
      Activity localActivity = UnityPlayer.currentActivity;
      AppLinkData.CompletionHandler local1 = new com/explodingbarrel/deeplinking/DeepLinkingManager$1;
      local1.<init>(this, paramString);
      AppLinkData.fetchDeferredAppLinkData(localActivity, local1);
      _this._supported = true;
      return;
    }
    catch (Exception paramString)
    {
      for (;;)
      {
        _this._supported = false;
        Log.d("DeepLinkingManager", "Fetching Deferred Deep Link Failed: " + paramString);
        SendMessage("OnDeferredDeepLinkFailed", paramString.toString());
      }
    }
  }
  
  private void _Init()
  {
    Log.d("DeepLinkingManager", "Initializing DeepLinking");
    Iterator localIterator = _this._pendingDeepLinks.iterator();
    while (localIterator.hasNext()) {
      SendMessage("OnAppLink", (String)localIterator.next());
    }
    _this._pendingDeepLinks.clear();
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\explodingbarrel\deeplinking\DeepLinkingManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */