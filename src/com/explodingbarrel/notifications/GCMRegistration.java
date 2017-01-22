package com.explodingbarrel.notifications;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.unity3d.player.UnityPlayer;
import java.io.IOException;

public class GCMRegistration
{
  public static final String EXTRA_MESSAGE = "message";
  private static final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
  private static final String PROPERTY_APP_VERSION = "appVersion";
  public static final String PROPERTY_REG_ID = "registration_id";
  public static final String TAG = "GCMRegistration";
  private static String regid;
  
  public static void Register(String paramString, Context paramContext)
  {
    if (paramContext == null) {
      Log.i("GCMRegistration", "Null context!  GCM will not be registered");
    }
    for (;;)
    {
      return;
      if (TextUtils.isEmpty(paramString)) {
        Log.i("GCMRegistration", "No senderId!  GCM will not be registered");
      } else {
        registerForGCM(paramString, paramContext);
      }
    }
  }
  
  private static boolean checkPlayServices(final Context paramContext)
  {
    boolean bool = false;
    if (paramContext == null) {
      Log.i("GCMRegistration", "Failed to check for GooglePlay services: context was null");
    }
    for (;;)
    {
      return bool;
      int i = GooglePlayServicesUtil.isGooglePlayServicesAvailable(paramContext);
      if (i != 0)
      {
        if (GooglePlayServicesUtil.isUserRecoverableError(i))
        {
          Log.d("GCMRegistration", "Google Play Services update required");
          UnityPlayer.currentActivity.runOnUiThread(new Runnable()
          {
            public void run()
            {
              Dialog localDialog = GooglePlayServicesUtil.getErrorDialog(this.val$resultCode, (Activity)paramContext, 9000);
              if (localDialog != null) {
                localDialog.show();
              }
            }
          });
        }
        else
        {
          Log.i("GCMRegistration", "Error checking for google play services " + i);
        }
      }
      else {
        bool = true;
      }
    }
  }
  
  private static int getAppVersion(Context paramContext)
  {
    int i = 0;
    if (paramContext == null) {
      Log.i("GCMRegistration", "Failed to register for GCM: context was null");
    }
    for (;;)
    {
      return i;
      try
      {
        i = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0).versionCode;
      }
      catch (PackageManager.NameNotFoundException paramContext)
      {
        throw new RuntimeException("Could not get package name: " + paramContext);
      }
    }
  }
  
  private static String getRegistrationId(Context paramContext)
  {
    String str;
    if (paramContext == null)
    {
      Log.i("GCMRegistration", "Failed to get registration ID: context was null");
      str = "";
    }
    for (;;)
    {
      return str;
      SharedPreferences localSharedPreferences = Util.getAppPreferences(paramContext);
      str = localSharedPreferences.getString("registration_id", "");
      if (str.isEmpty()) {
        str = "";
      } else if (localSharedPreferences.getInt("appVersion", Integer.MIN_VALUE) != getAppVersion(paramContext)) {
        str = "";
      }
    }
  }
  
  private static void registerForGCM(String paramString, Context paramContext)
  {
    if (paramContext == null) {
      Log.i("GCMRegistration", "Failed to register for GCM: context was null");
    }
    for (;;)
    {
      return;
      if (checkPlayServices(paramContext))
      {
        GoogleCloudMessaging.getInstance(paramContext);
        regid = getRegistrationId(paramContext);
        Log.d("GCMRegistration", "Registering for GCM with sender ID: " + paramString);
        if (regid.isEmpty())
        {
          registerInBackground(paramString, paramContext);
        }
        else
        {
          Log.d("GCMRegistration", "Found existing GCM registration id: " + regid);
          sendRegistrationIdToUnity(regid);
        }
      }
    }
  }
  
  private static void registerInBackground(String paramString, final Context paramContext)
  {
    if (TextUtils.isEmpty(paramString)) {
      Log.i("GCMRegistration", "Failed to register for GCM: senderId was empty");
    }
    for (;;)
    {
      return;
      if (paramContext == null) {
        Log.i("GCMRegistration", "Failed to register for GCM: context was null");
      } else {
        new Handler(Looper.getMainLooper()).post(new Runnable()
        {
          public void run()
          {
            GCMRegistration.BackgroundRegistration localBackgroundRegistration = new GCMRegistration.BackgroundRegistration(this.val$senderId, paramContext);
            if (localBackgroundRegistration != null) {
              localBackgroundRegistration.execute(new String[] { null, null, null });
            }
            for (;;)
            {
              return;
              Log.d("GCMRegistration", "Failed to register for GCM: unable to initialize AsyncTask");
            }
          }
        });
      }
    }
  }
  
  private static void sendRegistrationIdToUnity(String paramString)
  {
    Util.sendMessage("OnRegistered", paramString);
  }
  
  private static void storeRegistrationId(String paramString, Context paramContext)
  {
    if (paramContext == null) {
      Log.i("GCMRegistration", "Failed to register for GCM: context was null");
    }
    for (;;)
    {
      return;
      SharedPreferences localSharedPreferences = Util.getAppPreferences(paramContext);
      if (localSharedPreferences == null)
      {
        Log.i("GCMRegistration", "Unable to store GCM registration ID locally!");
      }
      else
      {
        int i = getAppVersion(paramContext);
        paramContext = localSharedPreferences.edit();
        paramContext.putString("registration_id", paramString);
        paramContext.putInt("appVersion", i);
        paramContext.commit();
      }
    }
  }
  
  private static class BackgroundRegistration
    extends AsyncTask<String, Void, String>
  {
    private Context mContext;
    private String mSenderId;
    
    public BackgroundRegistration(String paramString, Context paramContext)
    {
      this.mSenderId = paramString;
      this.mContext = paramContext;
    }
    
    protected String doInBackground(String... paramVarArgs)
    {
      for (paramVarArgs = null;; paramVarArgs = "Failed to retrieve gcm instance")
      {
        try
        {
          GoogleCloudMessaging localGoogleCloudMessaging = GoogleCloudMessaging.getInstance(this.mContext);
          if (localGoogleCloudMessaging == null) {
            continue;
          }
          GCMRegistration.access$002(localGoogleCloudMessaging.register(new String[] { this.mSenderId }));
        }
        catch (IOException paramVarArgs)
        {
          for (;;)
          {
            paramVarArgs = paramVarArgs.getMessage();
          }
        }
        return paramVarArgs;
      }
    }
    
    protected void onPostExecute(String paramString)
    {
      if (paramString != null) {
        Log.i("GCMRegistration", "Failed to register for GCM: " + paramString);
      }
      for (;;)
      {
        return;
        GCMRegistration.sendRegistrationIdToUnity(GCMRegistration.regid);
        GCMRegistration.storeRegistrationId(GCMRegistration.regid, this.mContext);
      }
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\explodingbarrel\notifications\GCMRegistration.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */