package com.explodingbarrel.notifications;

import android.content.Context;
import android.util.Log;
import com.unity3d.player.UnityPlayer;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.Reader;

public class RemoteNotificationManager
{
  private static final String LCM_FILE_LOC = "lcmCTA.txt";
  private static final String NOTIF_FILE_LOC = "notifPayloads.txt";
  private static final String TAG = "RemoteNotificationManager";
  private static final String UNITY_PLUGIN_NAME = "deeplinking_plugin_android";
  private static Context _ctaContext = null;
  private static Context _payloadContext = null;
  public static RemoteNotificationManager _this = null;
  
  public static void Init()
  {
    if (_this == null)
    {
      Log.d("RemoteNotificationManager", "Constructing RemoteNotificationManager");
      _this = new RemoteNotificationManager();
    }
    if (_ctaContext != null) {
      _this._handleCTA();
    }
    if (_payloadContext != null) {
      _this._handlePayloads();
    }
  }
  
  private static void SendMessage(String paramString1, String paramString2)
  {
    UnityPlayer.UnitySendMessage("deeplinking_plugin_android", paramString1, paramString2);
  }
  
  private void _handleCTA()
  {
    File localFile = new File(_ctaContext.getFilesDir(), "lcmCTA.txt");
    if (localFile.exists()) {
      try
      {
        BufferedReader localBufferedReader = new java/io/BufferedReader;
        Object localObject = new java/io/InputStreamReader;
        ((InputStreamReader)localObject).<init>(_ctaContext.openFileInput("lcmCTA.txt"));
        localBufferedReader.<init>((Reader)localObject);
        for (;;)
        {
          localObject = localBufferedReader.readLine();
          if (localObject == null) {
            break;
          }
          SendMessage("OnAppLink", (String)localObject);
        }
        return;
      }
      catch (Exception localException)
      {
        Log.d("RemoteNotificationManager", "failed to read CTA file. Exception: " + localException.toString());
        localFile.delete();
      }
    }
  }
  
  private void _handlePayloads()
  {
    File localFile = new File(_payloadContext.getFilesDir(), "notifPayloads.txt");
    if (localFile.exists()) {
      try
      {
        BufferedReader localBufferedReader = new java/io/BufferedReader;
        Object localObject = new java/io/InputStreamReader;
        ((InputStreamReader)localObject).<init>(_payloadContext.openFileInput("notifPayloads.txt"));
        localBufferedReader.<init>((Reader)localObject);
        for (;;)
        {
          localObject = localBufferedReader.readLine();
          if (localObject == null) {
            break;
          }
          Util.sendMessage("MessageOpened", (String)localObject);
        }
        return;
      }
      catch (Exception localException)
      {
        Log.d("RemoteNotificationManager", "failed to read notifications file. Exception: " + localException.toString());
        localFile.delete();
      }
    }
  }
  
  private static void _writeFile(String paramString1, Context paramContext, String paramString2)
  {
    try
    {
      File localFile = new java/io/File;
      localFile.<init>(paramContext.getFilesDir(), paramString2);
      if (!localFile.exists())
      {
        Log.d("RemoteNotificationManager", "File does not exist, creating");
        localFile.createNewFile();
      }
      paramContext = new java/io/FileWriter;
      paramContext.<init>(localFile, true);
      paramString2 = new java/lang/StringBuilder;
      paramString2.<init>();
      paramContext.append(paramString1 + "\n");
      paramContext.flush();
      paramContext.close();
      return;
    }
    catch (Exception paramString1)
    {
      for (;;)
      {
        Log.d("RemoteNotificationManager", "failed to write to file. Exception: " + paramString1.toString());
      }
    }
  }
  
  public static void writeCTAToFile(String paramString, Context paramContext)
  {
    if (_ctaContext == null)
    {
      _ctaContext = paramContext;
      Log.d("RemoteNotificationManager", "setting the cta context from GCMIntentService");
    }
    if (_this == null)
    {
      Log.d("RemoteNotificationManager", "Constructing RemoteNotificationManager - writeCTAToFile");
      _this = new RemoteNotificationManager();
    }
    _writeFile(paramString, paramContext, "lcmCTA.txt");
  }
  
  public static void writePayloadToFile(String paramString, Context paramContext)
  {
    if (_payloadContext == null)
    {
      _payloadContext = paramContext;
      Log.d("RemoteNotificationManager", "setting the payload context from GCMIntentService");
    }
    if (_this == null)
    {
      Log.d("RemoteNotificationManager", "Constructing RemoteNotificationManager - writePayloadToFile");
      _this = new RemoteNotificationManager();
    }
    _writeFile(paramString, paramContext, "notifPayloads.txt");
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\explodingbarrel\notifications\RemoteNotificationManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */