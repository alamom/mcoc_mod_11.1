package com.explodingbarrel;

import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityCompat.OnRequestPermissionsResultCallback;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import com.explodingbarrel.android.CustomExceptionHandler;
import com.explodingbarrel.deeplinking.DeepLinkingManager;
import com.explodingbarrel.notifications.RemoteNotificationManager;
import com.explodingbarrel.notifications.Util;
import com.unity3d.player.UnityPlayer;
import com.unity3d.player.UnityPlayerNativeActivity;
import java.util.AbstractMap.SimpleImmutableEntry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import org.json.JSONObject;

public class Activity
  extends UnityPlayerNativeActivity
  implements ActivityCompat.OnRequestPermissionsResultCallback
{
  private static final String TAG = "Activity";
  private static boolean _appSettingsLaunched = false;
  private static int _intentRequestCode = 42;
  static final HashMap<String, String> _permissionsMap = new HashMap() {};
  boolean _immersive = false;
  private String _onPermissionDeniedCallbackName = "OnPermissionDenied";
  private String _onPermissionDeniedEmphaticallyCallbackName = "OnPermissionDeniedEmphatically";
  private String _onPermissionGrantedCallbackName = "OnPermissionGranted";
  private HashMap<Integer, String> _requestMap = new HashMap();
  private Handler mHandler = new Handler();
  private Runnable resetImmersive = new Runnable()
  {
    @SuppressLint({"NewApi"})
    public void run()
    {
      if (Activity.this._immersive) {
        if (Build.VERSION.SDK_INT < 19) {
          Log.d("Activity", "updateView() skipped due to SDK version " + Build.VERSION.SDK_INT);
        }
      }
      for (;;)
      {
        return;
        int i = 0x1006 | 0x100 | 0x200 | 0x400;
        View localView = Activity.this.getWindow().getDecorView();
        if (localView != null) {
          localView.setSystemUiVisibility(i);
        }
        Log.d("Activity", "updateView() updated view flags " + i);
        continue;
        Log.d("Activity", "updateView() skipping immersive update ");
      }
    }
  };
  
  private void requestPermission(final String[] paramArrayOfString, final int paramInt)
  {
    UnityPlayer.currentActivity.runOnUiThread(new Runnable()
    {
      public void run()
      {
        ActivityCompat.requestPermissions(Activity.this, paramArrayOfString, paramInt);
      }
    });
  }
  
  private void sendRequestPermissionResultToClient(Boolean paramBoolean1, Boolean paramBoolean2, int paramInt)
  {
    String str = (String)this._requestMap.get(Integer.valueOf(paramInt));
    if (str != null)
    {
      if (!paramBoolean1.booleanValue()) {
        break label56;
      }
      paramBoolean1 = this._onPermissionGrantedCallbackName;
    }
    for (;;)
    {
      UnityPlayer.UnitySendMessage(str, paramBoolean1, Integer.toString(paramInt));
      this._requestMap.remove(Integer.valueOf(paramInt));
      return;
      label56:
      if (paramBoolean2.booleanValue()) {
        paramBoolean1 = this._onPermissionDeniedEmphaticallyCallbackName;
      } else {
        paramBoolean1 = this._onPermissionDeniedCallbackName;
      }
    }
  }
  
  public String checkPermissionsArray(String paramString, int paramInt)
  {
    Object localObject1 = paramString.split(",");
    paramString = new ArrayList();
    int j = localObject1.length;
    int i = 0;
    if (i < j)
    {
      Object localObject3 = localObject1[i];
      localObject2 = (String)_permissionsMap.get(localObject3);
      if ((localObject2 != null) && (ActivityCompat.checkSelfPermission(this, (String)localObject2) != 0))
      {
        if (!ActivityCompat.shouldShowRequestPermissionRationale(this, (String)localObject2)) {
          break label94;
        }
        paramString.add(new AbstractMap.SimpleImmutableEntry(localObject3, DenialInfo.Ungranted));
      }
      for (;;)
      {
        i++;
        break;
        label94:
        paramString.add(new AbstractMap.SimpleImmutableEntry(localObject3, DenialInfo.UngrantedWithoutPermissionRationale));
      }
    }
    localObject1 = new StringBuilder();
    Object localObject2 = paramString.iterator();
    while (((Iterator)localObject2).hasNext())
    {
      paramString = (Map.Entry)((Iterator)localObject2).next();
      if (paramInt == 1) {
        ((StringBuilder)localObject1).append((String)paramString.getKey()).append(",").append(paramString.getValue()).append(" ");
      } else {
        ((StringBuilder)localObject1).append((String)paramString.getKey()).append(",");
      }
    }
    localObject1 = ((StringBuilder)localObject1).toString();
    paramString = (String)localObject1;
    if (((String)localObject1).length() > 0)
    {
      Log.i("Activity", "RETURNING " + (String)localObject1);
      paramString = ((String)localObject1).toString().substring(0, ((String)localObject1).length() - 1);
    }
    return paramString;
  }
  
  public void launchAppSettings()
  {
    if (_appSettingsLaunched) {}
    for (;;)
    {
      return;
      try
      {
        Intent localIntent = new android/content/Intent;
        localIntent.<init>("android.settings.APPLICATION_DETAILS_SETTINGS");
        StringBuilder localStringBuilder = new java/lang/StringBuilder;
        localStringBuilder.<init>();
        localIntent.setData(Uri.parse("package:" + getPackageName()));
        startActivityForResult(localIntent, _intentRequestCode);
        _appSettingsLaunched = true;
      }
      catch (ActivityNotFoundException localActivityNotFoundException)
      {
        startActivity(new Intent("android.settings.MANAGE_APPLICATIONS_SETTINGS"));
        _appSettingsLaunched = true;
      }
    }
  }
  
  protected void notifyMessageOpened(Bundle paramBundle)
  {
    if (paramBundle == null) {}
    for (;;)
    {
      return;
      paramBundle = paramBundle.getBundle("notificationData");
      if (paramBundle != null)
      {
        paramBundle = Util.bundleToJSON(paramBundle);
        if (paramBundle != null) {
          Util.sendMessage("MessageOpened", paramBundle.toString());
        }
      }
    }
  }
  
  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    if (paramInt1 == _intentRequestCode) {
      _appSettingsLaunched = false;
    }
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    try
    {
      CustomExceptionHandler.getInstance(this);
      if (paramBundle != null) {
        Log.d("Activity", paramBundle.toString());
      }
    }
    catch (Throwable localThrowable1)
    {
      try
      {
        for (;;)
        {
          Class.forName("android.os.AsyncTask");
          super.onCreate(paramBundle);
          try
          {
            this._immersive = getPackageManager().getApplicationInfo(getPackageName(), 128).metaData.getBoolean("activity.immersive", false);
            Log.d("Activity", "onCreate() _immersive = " + this._immersive);
            paramBundle = getIntent().getExtras();
            if (paramBundle != null)
            {
              Bundle localBundle = paramBundle.getBundle("notificationData");
              if (localBundle != null)
              {
                paramBundle = localBundle.getString("cta");
                if (paramBundle != null) {
                  RemoteNotificationManager.writeCTAToFile(paramBundle, this);
                }
                paramBundle = Util.bundleToJSON(localBundle);
                if (paramBundle != null) {
                  RemoteNotificationManager.writePayloadToFile(paramBundle.toString(), this);
                }
              }
            }
            try
            {
              paramBundle = getIntent().getDataString();
              if ((paramBundle != null) && (paramBundle.length() > 0)) {
                DeepLinkingManager.AddAppLaunchDeepLink(paramBundle.toString());
              }
              return;
            }
            catch (Throwable paramBundle)
            {
              for (;;)
              {
                Log.d("Activity", "onCreate() deeplinking error: " + paramBundle);
              }
            }
            localThrowable1 = localThrowable1;
            Log.d("Activity", "onCreate() CustomExceptionHandler error: " + localThrowable1);
          }
          catch (Throwable paramBundle)
          {
            for (;;)
            {
              Log.d("Activity", "onCreate() failed = " + paramBundle);
            }
          }
        }
      }
      catch (Throwable localThrowable2)
      {
        for (;;) {}
      }
    }
  }
  
  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    if ((Build.VERSION.SDK_INT >= 19) && ((paramInt == 25) || (paramInt == 24))) {
      this.mHandler.postDelayed(this.resetImmersive, 500L);
    }
    return super.onKeyDown(paramInt, paramKeyEvent);
  }
  
  protected void onNewIntent(Intent paramIntent)
  {
    paramIntent = paramIntent.getExtras();
    if (paramIntent != null)
    {
      Object localObject = paramIntent.getBundle("notificationData");
      if (localObject != null)
      {
        localObject = ((Bundle)localObject).getString("cta");
        if (localObject != null) {
          UnityPlayer.UnitySendMessage("deeplinking_plugin_android", "OnAppLink", (String)localObject);
        }
      }
      notifyMessageOpened(paramIntent);
    }
  }
  
  public void onRequestPermissionsResult(int paramInt, String[] paramArrayOfString, int[] paramArrayOfInt)
  {
    Boolean localBoolean1 = Boolean.valueOf(true);
    Boolean localBoolean2 = Boolean.valueOf(false);
    Object localObject = localBoolean2;
    Boolean localBoolean3 = localBoolean1;
    if (paramArrayOfString.length == paramArrayOfInt.length)
    {
      int i = 0;
      localObject = localBoolean2;
      localBoolean3 = localBoolean1;
      if (i < paramArrayOfString.length)
      {
        boolean bool;
        if (paramArrayOfInt[i] != 0)
        {
          localBoolean1 = Boolean.valueOf(false);
          if (!ActivityCompat.shouldShowRequestPermissionRationale(this, paramArrayOfString[i]))
          {
            bool = true;
            label72:
            localBoolean2 = Boolean.valueOf(bool);
            localObject = paramArrayOfString[i];
            if (localBoolean2.booleanValue()) {
              break label137;
            }
            bool = true;
            label96:
            Log.i("Activity", String.format("Permission %s was denied. Should we ask again? %s", new Object[] { localObject, Boolean.valueOf(bool) }));
          }
        }
        for (;;)
        {
          i++;
          break;
          bool = false;
          break label72;
          label137:
          bool = false;
          break label96;
          Log.i("Activity", String.format("Permission %s was granted", new Object[] { paramArrayOfString[i] }));
        }
      }
    }
    sendRequestPermissionResultToClient(localBoolean3, (Boolean)localObject, paramInt);
  }
  
  public void onWindowFocusChanged(boolean paramBoolean)
  {
    super.onWindowFocusChanged(paramBoolean);
    if (paramBoolean) {
      this.mHandler.postDelayed(this.resetImmersive, 500L);
    }
  }
  
  public void requestAppPermissionsArray(String paramString1, String paramString2, int paramInt)
  {
    if (this._requestMap.get(Integer.valueOf(paramInt)) != null) {
      sendRequestPermissionResultToClient(Boolean.valueOf(false), Boolean.valueOf(false), -1);
    }
    for (;;)
    {
      return;
      this._requestMap.put(Integer.valueOf(paramInt), paramString2);
      paramString1 = paramString1.split(",");
      paramString2 = new ArrayList();
      int j = paramString1.length;
      int m = paramString1.length;
      int i = 0;
      while (i < m)
      {
        Object localObject = paramString1[i];
        localObject = (String)_permissionsMap.get(localObject);
        int k = j;
        if (localObject != null)
        {
          if (ActivityCompat.checkSelfPermission(this, (String)localObject) != 0)
          {
            Log.i("Activity", "Will request permission : " + (String)localObject);
            paramString2.add(localObject);
          }
          k = j - 1;
        }
        i++;
        j = k;
      }
      if (paramString2.size() > 0) {
        requestPermission((String[])paramString2.toArray(new String[paramString2.size()]), paramInt);
      } else if (j == 0) {
        sendRequestPermissionResultToClient(Boolean.valueOf(true), Boolean.valueOf(false), paramInt);
      }
    }
  }
  
  private static enum DenialInfo
  {
    Ungranted,  UngrantedWithoutPermissionRationale;
    
    private DenialInfo() {}
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\explodingbarrel\Activity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */