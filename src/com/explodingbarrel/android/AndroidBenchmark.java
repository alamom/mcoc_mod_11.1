package com.explodingbarrel.android;

import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.ConfigurationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.opengl.GLES10;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Environment;
import android.os.StatFs;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import com.unity3d.player.UnityPlayer;
import java.io.File;
import java.util.Iterator;
import java.util.List;
import org.json.JSONObject;

class AndroidBenchmark
{
  private static final String TAG = "AndroidBenchmark";
  
  public static String GetDeviceInfo()
  {
    try
    {
      localObject1 = new org/json/JSONObject;
      ((JSONObject)localObject1).<init>();
      ((JSONObject)localObject1).put("board", Build.BOARD);
      ((JSONObject)localObject1).put("bootloader", Build.BOOTLOADER);
      ((JSONObject)localObject1).put("brand", Build.BRAND);
      ((JSONObject)localObject1).put("device", Build.DEVICE);
      ((JSONObject)localObject1).put("display", Build.DISPLAY);
      ((JSONObject)localObject1).put("fingerprint", Build.FINGERPRINT);
      ((JSONObject)localObject1).put("hardware", Build.HARDWARE);
      ((JSONObject)localObject1).put("manufacturer", Build.MANUFACTURER);
      ((JSONObject)localObject1).put("model", Build.MODEL);
      ((JSONObject)localObject1).put("product", Build.PRODUCT);
      ((JSONObject)localObject1).put("root", isRooted());
      Object localObject2 = (ActivityManager)getContext().getSystemService("activity");
      Object localObject3;
      if (localObject2 != null)
      {
        localObject3 = ((ActivityManager)localObject2).getDeviceConfigurationInfo();
        if (localObject3 != null)
        {
          ((JSONObject)localObject1).put("gles_version", ((ConfigurationInfo)localObject3).reqGlEsVersion);
          ((JSONObject)localObject1).put("keyboard_type", ((ConfigurationInfo)localObject3).reqKeyboardType);
          ((JSONObject)localObject1).put("navigation", ((ConfigurationInfo)localObject3).reqNavigation);
          ((JSONObject)localObject1).put("touch_screen", ((ConfigurationInfo)localObject3).reqTouchScreen);
        }
        localObject3 = new android/app/ActivityManager$MemoryInfo;
        ((ActivityManager.MemoryInfo)localObject3).<init>();
        ((ActivityManager)localObject2).getMemoryInfo((ActivityManager.MemoryInfo)localObject3);
        ((JSONObject)localObject1).put("memory_available", ((ActivityManager.MemoryInfo)localObject3).availMem);
        ((JSONObject)localObject1).put("memory_threshold", ((ActivityManager.MemoryInfo)localObject3).threshold);
      }
      ((JSONObject)localObject1).put("gles_extensions", GLES10.glGetString(7939));
      ((JSONObject)localObject1).put("gles_renderer", GLES10.glGetString(7937));
      ((JSONObject)localObject1).put("gles_vendor", GLES10.glGetString(7936));
      ((JSONObject)localObject1).put("gles_version_string", GLES10.glGetString(7938));
      ((JSONObject)localObject1).put("installed_on_sd", isInstalledOnSdCard());
      ((JSONObject)localObject1).put("external_storage_removable", Environment.isExternalStorageRemovable());
      ((JSONObject)localObject1).put("external_storage_emulated", Environment.isExternalStorageEmulated());
      ((JSONObject)localObject1).put("external_storage_state", Environment.getExternalStorageState());
      localObject2 = new android/os/StatFs;
      ((StatFs)localObject2).<init>(Environment.getRootDirectory().getAbsolutePath());
      long l;
      if (localObject2 != null)
      {
        l = ((StatFs)localObject2).getBlockSize();
        ((JSONObject)localObject1).put("internal_storage_size", ((StatFs)localObject2).getBlockCount() * l);
        ((JSONObject)localObject1).put("internal_storage_available", ((StatFs)localObject2).getAvailableBlocks() * l);
      }
      localObject2 = new android/os/StatFs;
      ((StatFs)localObject2).<init>(Environment.getExternalStorageDirectory().getAbsolutePath());
      if (localObject2 != null)
      {
        l = ((StatFs)localObject2).getBlockSize();
        ((JSONObject)localObject1).put("external_storage_size", ((StatFs)localObject2).getBlockCount() * l);
        ((JSONObject)localObject1).put("external_storage_available", ((StatFs)localObject2).getAvailableBlocks() * l);
      }
      localObject2 = (WindowManager)getContext().getSystemService("window");
      if (localObject2 != null)
      {
        localObject2 = ((WindowManager)localObject2).getDefaultDisplay();
        if (localObject2 != null)
        {
          localObject3 = new android/util/DisplayMetrics;
          ((DisplayMetrics)localObject3).<init>();
          ((Display)localObject2).getMetrics((DisplayMetrics)localObject3);
          ((JSONObject)localObject1).put("display_pixel_width", ((DisplayMetrics)localObject3).widthPixels);
          ((JSONObject)localObject1).put("display_pixel_height", ((DisplayMetrics)localObject3).heightPixels);
          ((JSONObject)localObject1).put("display_xdpi", ((DisplayMetrics)localObject3).xdpi);
          ((JSONObject)localObject1).put("display_ydpi", ((DisplayMetrics)localObject3).ydpi);
          ((JSONObject)localObject1).put("display_width", ((DisplayMetrics)localObject3).widthPixels / ((DisplayMetrics)localObject3).xdpi);
          ((JSONObject)localObject1).put("display_height", ((DisplayMetrics)localObject3).heightPixels / ((DisplayMetrics)localObject3).ydpi);
        }
      }
      localObject2 = (ConnectivityManager)getContext().getSystemService("connectivity");
      if (localObject2 != null)
      {
        localObject3 = ((ConnectivityManager)localObject2).getActiveNetworkInfo();
        if (localObject3 != null) {
          ((JSONObject)localObject1).put("active_network", ((NetworkInfo)localObject3).getTypeName());
        }
        localObject3 = ((ConnectivityManager)localObject2).getNetworkInfo(1);
        if ((localObject3 != null) && (((NetworkInfo)localObject3).isConnected()))
        {
          localObject3 = (WifiManager)getContext().getSystemService("wifi");
          if (localObject3 != null)
          {
            localObject3 = ((WifiManager)localObject3).getConnectionInfo();
            if (localObject3 != null)
            {
              ((JSONObject)localObject1).put("wifi_mbps", ((WifiInfo)localObject3).getLinkSpeed());
              ((JSONObject)localObject1).put("wifi_signal_strength", ((WifiInfo)localObject3).getRssi());
            }
          }
        }
        localObject2 = ((ConnectivityManager)localObject2).getNetworkInfo(0);
        if ((localObject2 != null) && (((NetworkInfo)localObject2).isConnected()))
        {
          localObject2 = (TelephonyManager)getContext().getSystemService("phone");
          if (localObject2 != null)
          {
            ((JSONObject)localObject1).put("telephony_phone_type", ((TelephonyManager)localObject2).getPhoneType());
            ((JSONObject)localObject1).put("telephony_network_type", ((TelephonyManager)localObject2).getNetworkType());
            ((JSONObject)localObject1).put("telephony_network_operator", ((TelephonyManager)localObject2).getNetworkOperatorName());
            ((JSONObject)localObject1).put("telephony_data_state", ((TelephonyManager)localObject2).getDataState());
            ((JSONObject)localObject1).put("telephony_sim_state", ((TelephonyManager)localObject2).getSimState());
            ((JSONObject)localObject1).put("telephony_sim_iso", ((TelephonyManager)localObject2).getSimCountryIso());
            if (((TelephonyManager)localObject2).getSimState() == 5) {
              ((JSONObject)localObject1).put("telephony_sim_operator", ((TelephonyManager)localObject2).getSimOperatorName());
            }
          }
        }
      }
      ((JSONObject)localObject1).put("proc_cpuinfo", readProcFile("cpuinfo"));
      ((JSONObject)localObject1).put("proc_version", readProcFile("version"));
      localObject1 = ((JSONObject)localObject1).toString();
    }
    catch (Exception localException)
    {
      for (;;)
      {
        Object localObject1;
        Log.d("AndroidBenchmark", "DeviceInfo : Failed - " + localException.getMessage());
        String str = "{}";
      }
    }
    return (String)localObject1;
  }
  
  public static String GetPath()
  {
    String str;
    try
    {
      JSONObject localJSONObject = new org/json/JSONObject;
      localJSONObject.<init>();
      Object localObject = (ActivityManager)getContext().getSystemService("activity");
      if (localObject != null)
      {
        localObject = ((ActivityManager)localObject).getRunningAppProcesses().iterator();
        while (((Iterator)localObject).hasNext())
        {
          ActivityManager.RunningAppProcessInfo localRunningAppProcessInfo = (ActivityManager.RunningAppProcessInfo)((Iterator)localObject).next();
          localJSONObject.put(Integer.toString(localRunningAppProcessInfo.pid), localRunningAppProcessInfo.processName);
          continue;
          return str;
        }
      }
    }
    catch (Exception localException)
    {
      Log.d("AndroidBenchmark", "GetPath : Failed - " + localException.getMessage());
      str = "{}";
    }
    for (;;)
    {
      str = str.toString();
    }
  }
  
  static Context getContext()
  {
    return UnityPlayer.currentActivity;
  }
  
  public static boolean isInstalledOnSdCard()
  {
    boolean bool2 = false;
    boolean bool1;
    if (Build.VERSION.SDK_INT < 7) {
      bool1 = bool2;
    }
    for (;;)
    {
      return bool1;
      Context localContext = getContext();
      PackageManager localPackageManager = localContext.getPackageManager();
      bool1 = bool2;
      if (localPackageManager != null) {
        try
        {
          int i = localPackageManager.getPackageInfo(localContext.getPackageName(), 0).applicationInfo.flags;
          bool1 = bool2;
          if ((i & 0x40000) == 262144) {
            bool1 = true;
          }
        }
        catch (PackageManager.NameNotFoundException localNameNotFoundException)
        {
          bool1 = bool2;
        }
      }
    }
  }
  
  private static boolean isRooted()
  {
    int i = 0;
    Object localObject = Build.TAGS;
    if ((localObject != null) && (((String)localObject).contains("test-keys"))) {}
    for (boolean bool = true;; bool = false)
    {
      localObject = new String[9];
      localObject[0] = "/system/app/Superuser.apk";
      localObject[1] = "/sbin/su";
      localObject[2] = "/system/bin/su";
      localObject[3] = "/system/xbin/su";
      localObject[4] = "/data/local/xbin/su";
      localObject[5] = "/data/local/bin/su";
      localObject[6] = "/system/sd/xbin/su";
      localObject[7] = "/system/bin/failsafe/su";
      localObject[8] = "/data/local/su";
      int j = localObject.length;
      while (i < j)
      {
        if (new File(localObject[i]).exists()) {
          bool = true;
        }
        i++;
      }
    }
    return bool;
  }
  
  /* Error */
  public static String readProcFile(String paramString)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_2
    //   2: aconst_null
    //   3: astore_1
    //   4: new 364	java/lang/StringBuilder
    //   7: dup
    //   8: invokespecial 365	java/lang/StringBuilder:<init>	()V
    //   11: astore_3
    //   12: invokestatic 496	java/lang/Runtime:getRuntime	()Ljava/lang/Runtime;
    //   15: astore 4
    //   17: new 364	java/lang/StringBuilder
    //   20: astore 5
    //   22: aload 5
    //   24: invokespecial 365	java/lang/StringBuilder:<init>	()V
    //   27: aload 4
    //   29: aload 5
    //   31: ldc_w 498
    //   34: invokevirtual 371	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   37: aload_0
    //   38: invokevirtual 371	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   41: invokevirtual 375	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   44: invokevirtual 502	java/lang/Runtime:exec	(Ljava/lang/String;)Ljava/lang/Process;
    //   47: invokevirtual 508	java/lang/Process:getInputStream	()Ljava/io/InputStream;
    //   50: astore 5
    //   52: new 510	java/io/BufferedReader
    //   55: astore_0
    //   56: new 512	java/io/InputStreamReader
    //   59: astore 4
    //   61: aload 4
    //   63: aload 5
    //   65: invokespecial 515	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   68: aload_0
    //   69: aload 4
    //   71: invokespecial 518	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   74: aload_0
    //   75: invokevirtual 521	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   78: astore_1
    //   79: aload_1
    //   80: ifnull +44 -> 124
    //   83: aload_1
    //   84: invokevirtual 524	java/lang/String:isEmpty	()Z
    //   87: ifne -13 -> 74
    //   90: aload_3
    //   91: aload_1
    //   92: invokevirtual 371	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   95: pop
    //   96: aload_3
    //   97: ldc_w 526
    //   100: invokestatic 531	java/lang/System:getProperty	(Ljava/lang/String;)Ljava/lang/String;
    //   103: invokevirtual 371	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   106: pop
    //   107: goto -33 -> 74
    //   110: astore_1
    //   111: aload_0
    //   112: ifnull +7 -> 119
    //   115: aload_0
    //   116: invokevirtual 534	java/io/BufferedReader:close	()V
    //   119: aload_3
    //   120: invokevirtual 375	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   123: areturn
    //   124: aload_0
    //   125: ifnull +45 -> 170
    //   128: aload_0
    //   129: invokevirtual 534	java/io/BufferedReader:close	()V
    //   132: goto -13 -> 119
    //   135: astore_0
    //   136: goto -17 -> 119
    //   139: astore_1
    //   140: aload_2
    //   141: astore_0
    //   142: aload_0
    //   143: ifnull +7 -> 150
    //   146: aload_0
    //   147: invokevirtual 534	java/io/BufferedReader:close	()V
    //   150: aload_1
    //   151: athrow
    //   152: astore_0
    //   153: goto -34 -> 119
    //   156: astore_0
    //   157: goto -7 -> 150
    //   160: astore_1
    //   161: goto -19 -> 142
    //   164: astore_0
    //   165: aload_1
    //   166: astore_0
    //   167: goto -56 -> 111
    //   170: goto -51 -> 119
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	173	0	paramString	String
    //   3	89	1	str	String
    //   110	1	1	localIOException	java.io.IOException
    //   139	12	1	localObject1	Object
    //   160	6	1	localObject2	Object
    //   1	140	2	localObject3	Object
    //   11	109	3	localStringBuilder	StringBuilder
    //   15	55	4	localObject4	Object
    //   20	44	5	localObject5	Object
    // Exception table:
    //   from	to	target	type
    //   74	79	110	java/io/IOException
    //   83	107	110	java/io/IOException
    //   128	132	135	java/io/IOException
    //   12	74	139	finally
    //   115	119	152	java/io/IOException
    //   146	150	156	java/io/IOException
    //   74	79	160	finally
    //   83	107	160	finally
    //   12	74	164	java/io/IOException
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\explodingbarrel\android\AndroidBenchmark.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */