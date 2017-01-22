package com.facebook.unity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import com.facebook.AppEventsLogger;
import com.facebook.FacebookException;
import com.facebook.FacebookOperationCanceledException;
import com.facebook.Session;
import com.facebook.Settings;
import com.facebook.internal.Utility;
import com.facebook.widget.FacebookDialog;
import com.facebook.widget.FacebookDialog.ShareDialogFeature;
import com.facebook.widget.WebDialog;
import com.facebook.widget.WebDialog.Builder;
import com.facebook.widget.WebDialog.FeedDialogBuilder;
import com.facebook.widget.WebDialog.OnCompleteListener;
import com.facebook.widget.WebDialog.RequestsDialogBuilder;
import com.unity3d.player.UnityPlayer;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Currency;
import java.util.Iterator;
import java.util.Set;

public class FB
{
  static final String FB_UNITY_OBJECT = "UnityFacebookSDKPlugin";
  static final String TAG = "FBUnitySDK";
  private static AppEventsLogger appEventsLogger;
  private static Boolean frictionlessRequests = Boolean.valueOf(false);
  private static Intent intent;
  
  @UnityCallable
  public static void ActivateApp(String paramString)
  {
    paramString = UnityParams.parse(paramString);
    if (paramString.hasString("app_id").booleanValue()) {
      AppEventsLogger.activateApp(getUnityActivity().getApplicationContext(), paramString.getString("app_id"));
    }
    for (;;)
    {
      return;
      AppEventsLogger.activateApp(getUnityActivity().getApplicationContext());
    }
  }
  
  @UnityCallable
  public static void AppEvents(String paramString)
  {
    Log.v("FBUnitySDK", "AppEvents(" + paramString + ")");
    UnityParams localUnityParams = UnityParams.parse(paramString);
    Bundle localBundle = new Bundle();
    if (localUnityParams.has("parameters")) {
      localBundle = localUnityParams.getParamsObject("parameters").getStringParams();
    }
    if (localUnityParams.has("logPurchase")) {
      getAppEventsLogger().logPurchase(new BigDecimal(localUnityParams.getDouble("logPurchase")), Currency.getInstance(localUnityParams.getString("currency")), localBundle);
    }
    for (;;)
    {
      return;
      if (localUnityParams.hasString("logEvent").booleanValue())
      {
        if (localUnityParams.has("valueToSum")) {
          getAppEventsLogger().logEvent(localUnityParams.getString("logEvent"), localUnityParams.getDouble("valueToSum"), localBundle);
        } else {
          getAppEventsLogger().logEvent(localUnityParams.getString("logEvent"), localBundle);
        }
      }
      else {
        Log.e("FBUnitySDK", "couldn't logPurchase or logEvent params: " + paramString);
      }
    }
  }
  
  @UnityCallable
  public static void AppRequest(String paramString)
  {
    Log.v("FBUnitySDK", "sendRequestDialog(" + paramString + ")");
    final UnityMessage localUnityMessage = new UnityMessage("OnAppRequestsComplete");
    if (!isLoggedIn()) {
      localUnityMessage.sendNotLoggedInError();
    }
    for (;;)
    {
      return;
      paramString = UnityParams.parse(paramString);
      if (paramString.hasString("callback_id").booleanValue()) {
        localUnityMessage.put("callback_id", paramString.getString("callback_id"));
      }
      paramString = paramString.getStringParams();
      if (paramString.containsKey("callback_id")) {
        paramString.remove("callback_id");
      }
      if (frictionlessRequests.booleanValue()) {
        paramString.putString("frictionless", "true");
      }
      getUnityActivity().runOnUiThread(new Runnable()
      {
        public void run()
        {
          ((WebDialog.RequestsDialogBuilder)new WebDialog.RequestsDialogBuilder(FB.getUnityActivity(), Session.getActiveSession(), this.val$params).setOnCompleteListener(new WebDialog.OnCompleteListener()
          {
            public void onComplete(Bundle paramAnonymous2Bundle, FacebookException paramAnonymous2FacebookException)
            {
              if (paramAnonymous2FacebookException != null) {
                if (paramAnonymous2FacebookException.toString().equals("com.facebook.FacebookOperationCanceledException"))
                {
                  FB.1.this.val$response.putCancelled();
                  FB.1.this.val$response.send();
                }
              }
              for (;;)
              {
                return;
                FB.1.this.val$response.sendError(paramAnonymous2FacebookException.toString());
                continue;
                if ((paramAnonymous2Bundle != null) && (paramAnonymous2Bundle.getString("request") == null)) {
                  FB.1.this.val$response.putCancelled();
                }
                Iterator localIterator = paramAnonymous2Bundle.keySet().iterator();
                while (localIterator.hasNext())
                {
                  paramAnonymous2FacebookException = (String)localIterator.next();
                  FB.1.this.val$response.put(paramAnonymous2FacebookException, paramAnonymous2Bundle.getString(paramAnonymous2FacebookException));
                }
                FB.1.this.val$response.send();
              }
            }
          })).build().show();
        }
      });
    }
  }
  
  @UnityCallable
  public static void FeedRequest(String paramString)
  {
    Log.v("FBUnitySDK", "FeedRequest(" + paramString + ")");
    final Object localObject = new UnityMessage("OnFeedRequestComplete");
    paramString = UnityParams.parse(paramString);
    if (paramString.hasString("callback_id").booleanValue()) {
      ((UnityMessage)localObject).put("callback_id", paramString.getString("callback_id"));
    }
    if (!isLoggedIn()) {
      ((UnityMessage)localObject).sendNotLoggedInError();
    }
    for (;;)
    {
      return;
      paramString = paramString.getStringParams();
      if ((!FacebookDialog.canPresentShareDialog(getUnityActivity(), new FacebookDialog.ShareDialogFeature[0])) || (FBDialogUtils.hasUnsupportedParams(FBDialogUtils.DialogType.SHARE_DIALOG, paramString)))
      {
        if (paramString.containsKey("callback_id")) {
          paramString.remove("callback_id");
        }
        getUnityActivity().runOnUiThread(new Runnable()
        {
          public void run()
          {
            ((WebDialog.FeedDialogBuilder)new WebDialog.FeedDialogBuilder(FB.getUnityActivity(), Session.getActiveSession(), this.val$params).setOnCompleteListener(new WebDialog.OnCompleteListener()
            {
              public void onComplete(Bundle paramAnonymous2Bundle, FacebookException paramAnonymous2FacebookException)
              {
                if (paramAnonymous2FacebookException == null)
                {
                  paramAnonymous2Bundle = paramAnonymous2Bundle.getString("post_id");
                  if (paramAnonymous2Bundle != null)
                  {
                    FB.4.this.val$response.putID(paramAnonymous2Bundle);
                    FB.4.this.val$response.send();
                  }
                }
                for (;;)
                {
                  return;
                  FB.4.this.val$response.putCancelled();
                  break;
                  if ((paramAnonymous2FacebookException instanceof FacebookOperationCanceledException))
                  {
                    FB.4.this.val$response.putCancelled();
                    FB.4.this.val$response.send();
                  }
                  else
                  {
                    FB.4.this.val$response.sendError(paramAnonymous2FacebookException.toString());
                  }
                }
              }
            })).build().show();
          }
        });
      }
      else
      {
        localObject = new Intent(getUnityActivity(), FBUnityDialogsActivity.class);
        ((Intent)localObject).putExtra("dialog_type", FBDialogUtils.DialogType.SHARE_DIALOG);
        ((Intent)localObject).putExtra("dialog_params", paramString);
        getUnityActivity().startActivity((Intent)localObject);
      }
    }
  }
  
  @UnityCallable
  public static void GameGroupCreate(final String paramString)
  {
    Object localObject = UnityParams.parse(paramString);
    paramString = new UnityMessage("OnGroupCreateComplete");
    if (((UnityParams)localObject).hasString("callback_id").booleanValue()) {
      paramString.put("callback_id", ((UnityParams)localObject).getString("callback_id"));
    }
    if (!isLoggedIn()) {
      paramString.sendNotLoggedInError();
    }
    for (;;)
    {
      return;
      localObject = ((UnityParams)localObject).getStringParams();
      if (((Bundle)localObject).containsKey("callback_id")) {
        ((Bundle)localObject).remove("callback_id");
      }
      getUnityActivity().runOnUiThread(new Runnable()
      {
        public void run()
        {
          ((WebDialog.Builder)new WebDialog.Builder(FB.getUnityActivity(), Session.getActiveSession(), "game_group_create", this.val$params).setOnCompleteListener(new WebDialog.OnCompleteListener()
          {
            public void onComplete(Bundle paramAnonymous2Bundle, FacebookException paramAnonymous2FacebookException)
            {
              if (paramAnonymous2FacebookException == null)
              {
                paramAnonymous2Bundle = paramAnonymous2Bundle.getString("id");
                if (paramAnonymous2Bundle != null)
                {
                  FB.2.this.val$response.putID(paramAnonymous2Bundle);
                  FB.2.this.val$response.send();
                }
              }
              for (;;)
              {
                return;
                FB.2.this.val$response.putCancelled();
                break;
                if ((paramAnonymous2FacebookException instanceof FacebookOperationCanceledException))
                {
                  FB.2.this.val$response.putCancelled();
                  FB.2.this.val$response.send();
                }
                else
                {
                  FB.2.this.val$response.sendError(paramAnonymous2FacebookException.toString());
                }
              }
            }
          })).build().show();
        }
      });
    }
  }
  
  @UnityCallable
  public static void GameGroupJoin(final String paramString)
  {
    Object localObject = UnityParams.parse(paramString);
    paramString = new UnityMessage("OnGroupCreateComplete");
    if (((UnityParams)localObject).hasString("callback_id").booleanValue()) {
      paramString.put("callback_id", ((UnityParams)localObject).getString("callback_id"));
    }
    if (!isLoggedIn()) {
      paramString.sendNotLoggedInError();
    }
    for (;;)
    {
      return;
      localObject = ((UnityParams)localObject).getStringParams();
      if (((Bundle)localObject).containsKey("callback_id")) {
        ((Bundle)localObject).remove("callback_id");
      }
      getUnityActivity().runOnUiThread(new Runnable()
      {
        public void run()
        {
          ((WebDialog.Builder)new WebDialog.Builder(FB.getUnityActivity(), Session.getActiveSession(), "game_group_join", this.val$params).setOnCompleteListener(new WebDialog.OnCompleteListener()
          {
            public void onComplete(Bundle paramAnonymous2Bundle, FacebookException paramAnonymous2FacebookException)
            {
              if (paramAnonymous2FacebookException == null)
              {
                paramAnonymous2Bundle = paramAnonymous2Bundle.getString("id");
                if (paramAnonymous2Bundle != null)
                {
                  FB.3.this.val$response.putID(paramAnonymous2Bundle);
                  FB.3.this.val$response.send();
                }
              }
              for (;;)
              {
                return;
                FB.3.this.val$response.putCancelled();
                break;
                if ((paramAnonymous2FacebookException instanceof FacebookOperationCanceledException))
                {
                  FB.3.this.val$response.putCancelled();
                  FB.3.this.val$response.send();
                }
                else
                {
                  FB.3.this.val$response.sendError(paramAnonymous2FacebookException.toString());
                }
              }
            }
          })).build().show();
        }
      });
    }
  }
  
  @UnityCallable
  public static void GetDeepLink(String paramString)
  {
    paramString = new UnityMessage("OnGetDeepLinkComplete");
    if ((intent != null) && (intent.getData() != null)) {
      paramString.put("deep_link", intent.getData().toString());
    }
    for (;;)
    {
      paramString.send();
      return;
      paramString.put("deep_link", "");
    }
  }
  
  @UnityCallable
  public static void Init(String paramString)
  {
    paramString = UnityParams.parse(paramString, "couldn't parse init params: " + paramString);
    if (paramString.hasString("frictionlessRequests").booleanValue()) {
      frictionlessRequests = Boolean.valueOf(paramString.getString("frictionlessRequests"));
    }
    if (paramString.hasString("appId").booleanValue()) {}
    for (paramString = paramString.getString("appId");; paramString = Utility.getMetadataApplicationId(getUnityActivity()))
    {
      FBLogin.init(paramString);
      return;
    }
  }
  
  @UnityCallable
  public static void Login(String paramString)
  {
    Intent localIntent = new Intent(getUnityActivity(), FBUnityLoginActivity.class);
    localIntent.putExtra("login_params", paramString);
    getUnityActivity().startActivity(localIntent);
  }
  
  @UnityCallable
  public static void Logout(String paramString)
  {
    Session.getActiveSession().closeAndClearTokenInformation();
    new UnityMessage("OnLogoutComplete").send();
  }
  
  @UnityCallable
  public static void PublishInstall(String paramString)
  {
    UnityMessage localUnityMessage = new UnityMessage("OnPublishInstallComplete");
    paramString = UnityParams.parse(paramString);
    if (paramString.hasString("callback_id").booleanValue()) {
      localUnityMessage.put("callback_id", paramString.getString("callback_id"));
    }
    AppEventsLogger.activateApp(getUnityActivity().getApplicationContext());
    localUnityMessage.send();
  }
  
  public static void SetIntent(Intent paramIntent)
  {
    intent = paramIntent;
    GetDeepLink("");
  }
  
  public static void SetLimitEventUsage(String paramString)
  {
    Settings.setLimitEventAndDataUsage(getUnityActivity().getApplicationContext(), Boolean.valueOf(paramString).booleanValue());
  }
  
  private static AppEventsLogger getAppEventsLogger()
  {
    if (appEventsLogger == null) {
      appEventsLogger = AppEventsLogger.newLogger(getUnityActivity().getApplicationContext());
    }
    return appEventsLogger;
  }
  
  @TargetApi(8)
  public static String getKeyHash()
  {
    try
    {
      localObject1 = getUnityActivity().getPackageManager().getPackageInfo(getUnityActivity().getPackageName(), 64).signatures;
      if (localObject1.length >= 0) {
        break label85;
      }
      localObject1 = localObject1[0];
      Object localObject2 = MessageDigest.getInstance("SHA");
      ((MessageDigest)localObject2).update(((Signature)localObject1).toByteArray());
      localObject1 = Base64.encodeToString(((MessageDigest)localObject2).digest(), 0);
      localObject2 = new java/lang/StringBuilder;
      ((StringBuilder)localObject2).<init>();
      Log.d("FBUnitySDK", "KeyHash: " + (String)localObject1);
    }
    catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
    {
      for (;;)
      {
        Object localObject1;
        String str = "";
      }
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      label85:
      for (;;) {}
    }
    return (String)localObject1;
  }
  
  public static Activity getUnityActivity()
  {
    return UnityPlayer.currentActivity;
  }
  
  public static boolean isLoggedIn()
  {
    if ((Session.getActiveSession() != null) && (Session.getActiveSession().isOpened())) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\facebook\unity\FB.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */