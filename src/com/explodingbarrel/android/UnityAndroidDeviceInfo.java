package com.explodingbarrel.android;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.provider.Settings.Secure;
import android.util.Base64;
import android.util.Log;
import com.unity3d.player.UnityPlayer;
import java.security.MessageDigest;
import org.json.JSONObject;

class UnityAndroidDeviceInfo
{
  private static final String TAG = "UnityAndroidDeviceInfo";
  
  public static String GetDeviceID()
  {
    Log.d("UnityAndroidDeviceInfo", "DeviceInfo GetDeviceID");
    String str2 = "";
    for (String str1 = str2;; str1 = str2)
    {
      try
      {
        Object localObject = UnityPlayer.currentActivity;
        if (localObject == null) {
          break label70;
        }
        str1 = str2;
        str2 = Settings.Secure.getString(((Activity)localObject).getContentResolver(), "android_id");
        str1 = str2;
        localObject = new java/lang/StringBuilder;
        str1 = str2;
        ((StringBuilder)localObject).<init>();
        str1 = str2;
        Log.d("UnityAndroidDeviceInfo", "DeviceInfo : Success - id = " + str2);
        str1 = str2;
      }
      catch (Exception localException)
      {
        for (;;)
        {
          label70:
          Log.d("UnityAndroidDeviceInfo", "DeviceInfo : Failed - " + localException.getMessage());
        }
      }
      return str1;
      str1 = str2;
      Log.d("UnityAndroidDeviceInfo", "DeviceInfo : Failed - Couldn't get the current activity from Unity");
    }
  }
  
  public static String GetDeviceInfo()
  {
    try
    {
      Object localObject = new org/json/JSONObject;
      ((JSONObject)localObject).<init>();
      ((JSONObject)localObject).put("board", Build.BOARD);
      ((JSONObject)localObject).put("bootloader", Build.BOOTLOADER);
      ((JSONObject)localObject).put("brand", Build.BRAND);
      ((JSONObject)localObject).put("device", Build.DEVICE);
      ((JSONObject)localObject).put("display", Build.DISPLAY);
      ((JSONObject)localObject).put("fingerprint", Build.FINGERPRINT);
      ((JSONObject)localObject).put("hardware", Build.HARDWARE);
      ((JSONObject)localObject).put("host", Build.HOST);
      ((JSONObject)localObject).put("id", Build.ID);
      ((JSONObject)localObject).put("manufacturer", Build.MANUFACTURER);
      ((JSONObject)localObject).put("model", Build.MODEL);
      ((JSONObject)localObject).put("product", Build.PRODUCT);
      ((JSONObject)localObject).put("serial", Build.SERIAL);
      localObject = ((JSONObject)localObject).toString();
      return (String)localObject;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        Log.d("UnityAndroidDeviceInfo", "DeviceInfo : Failed - " + localException.getMessage());
        String str = "{}";
      }
    }
  }
  
  public static String GetPackageInstallerPlatform()
  {
    for (;;)
    {
      try
      {
        localObject = UnityPlayer.currentActivity;
        String str2 = ((Activity)localObject).getApplicationContext().getPackageName();
        localObject = ((Activity)localObject).getPackageManager().getInstallerPackageName(str2);
        if (localObject != null) {
          continue;
        }
        localObject = "android";
      }
      catch (Exception localException)
      {
        Object localObject;
        boolean bool;
        Log.d("UnityAndroidDeviceInfo", "PackageInstallerType : Failed - " + localException.getMessage());
        String str1 = "android";
        continue;
      }
      return (String)localObject;
      if (((String)localObject).contains("amazon"))
      {
        localObject = "amazonapp";
      }
      else
      {
        bool = ((String)localObject).contains("samsung");
        if (!bool) {}
      }
    }
  }
  
  public static String WifiMacAddress()
  {
    Log.d("UnityAndroidDeviceInfo", "DeviceInfo WifiMacAddress");
    Object localObject2 = "";
    for (localObject1 = localObject2;; localObject1 = localObject2)
    {
      try
      {
        Object localObject3 = UnityPlayer.currentActivity;
        if (localObject3 == null) {
          break label175;
        }
        localObject1 = localObject2;
        localObject3 = (WifiManager)((Activity)localObject3).getSystemService("wifi");
        if (localObject3 == null) {
          break label160;
        }
        localObject1 = localObject2;
        localObject3 = ((WifiManager)localObject3).getConnectionInfo();
        if (localObject3 == null) {
          break;
        }
        localObject1 = localObject2;
        localObject3 = ((WifiInfo)localObject3).getMacAddress();
        if (localObject3 == null) {
          break label98;
        }
        localObject2 = localObject3;
        localObject1 = localObject2;
        localObject3 = new java/lang/StringBuilder;
        localObject1 = localObject2;
        ((StringBuilder)localObject3).<init>();
        localObject1 = localObject2;
        Log.d("UnityAndroidDeviceInfo", "DeviceInfo : Success - mac address = " + (String)localObject2);
        localObject1 = localObject2;
      }
      catch (Exception localException)
      {
        for (;;)
        {
          label98:
          Log.d("UnityAndroidDeviceInfo", "DeviceInfo : Failed - " + localException.getMessage());
          continue;
          localObject1 = localException;
          Log.d("UnityAndroidDeviceInfo", "DeviceInfo : Failed - Couldn't get the WifiInfo from the WifiManager");
          localObject1 = localException;
          continue;
          localObject1 = localException;
          Log.d("UnityAndroidDeviceInfo", "DeviceInfo : Failed - Couldn't get the WifiManager");
          localObject1 = localException;
          continue;
          localObject1 = localException;
          Log.d("UnityAndroidDeviceInfo", "DeviceInfo : Failed - Couldn't get the current activity from Unity");
          localObject1 = localException;
        }
      }
      return (String)localObject1;
      localObject1 = localObject2;
      Log.d("UnityAndroidDeviceInfo", "DeviceInfo : Failed - Couldn't get the WifiInfo.getMacAddress return null");
    }
  }
  
  public static String getHashedPackageInfo(String paramString, int paramInt)
  {
    String str2 = "";
    str1 = str2;
    try
    {
      Object localObject = UnityPlayer.currentActivity;
      str1 = str2;
      if (localObject != null)
      {
        str1 = str2;
        localObject = ((Activity)localObject).getPackageManager().getPackageInfo(paramString, paramInt).signatures;
        str1 = str2;
        int i = localObject.length;
        paramInt = 0;
        paramString = str2;
        for (;;)
        {
          str1 = paramString;
          if (paramInt >= i) {
            break;
          }
          str2 = localObject[paramInt];
          str1 = paramString;
          MessageDigest localMessageDigest = MessageDigest.getInstance("SHA");
          str1 = paramString;
          localMessageDigest.update(str2.toByteArray());
          str1 = paramString;
          paramString = Base64.encodeToString(localMessageDigest.digest(), 2);
          paramInt++;
        }
      }
      return str1;
    }
    catch (Exception paramString) {}
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\explodingbarrel\android\UnityAndroidDeviceInfo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */