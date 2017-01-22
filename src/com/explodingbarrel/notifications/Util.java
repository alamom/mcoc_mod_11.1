package com.explodingbarrel.notifications;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;
import com.explodingbarrel.util.BundleJSONConverter;
import com.unity3d.player.UnityPlayer;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Util
{
  private static final String TAG = "NotificationsUtil";
  
  public static Object JSON_wrap(Object paramObject)
  {
    Object localObject;
    if (paramObject == null) {
      localObject = JSONObject.NULL;
    }
    for (;;)
    {
      return localObject;
      localObject = paramObject;
      if (!(paramObject instanceof JSONArray))
      {
        localObject = paramObject;
        if (!(paramObject instanceof JSONObject))
        {
          localObject = paramObject;
          if (!paramObject.equals(JSONObject.NULL)) {
            try
            {
              if ((paramObject instanceof Collection))
              {
                localObject = new org/json/JSONArray;
                ((JSONArray)localObject).<init>((Collection)paramObject);
              }
              else if (paramObject.getClass().isArray())
              {
                localObject = new JSONArray(paramObject);
              }
              else if ((paramObject instanceof Map))
              {
                localObject = new JSONObject((Map)paramObject);
              }
              else
              {
                localObject = paramObject;
                if (!(paramObject instanceof Boolean))
                {
                  localObject = paramObject;
                  if (!(paramObject instanceof Byte))
                  {
                    localObject = paramObject;
                    if (!(paramObject instanceof Character))
                    {
                      localObject = paramObject;
                      if (!(paramObject instanceof Double))
                      {
                        localObject = paramObject;
                        if (!(paramObject instanceof Float))
                        {
                          localObject = paramObject;
                          if (!(paramObject instanceof Integer))
                          {
                            localObject = paramObject;
                            if (!(paramObject instanceof Long))
                            {
                              localObject = paramObject;
                              if (!(paramObject instanceof Short))
                              {
                                localObject = paramObject;
                                if (!(paramObject instanceof String)) {
                                  if (paramObject.getClass().getPackage().getName().startsWith("java.")) {
                                    localObject = paramObject.toString();
                                  }
                                }
                              }
                            }
                          }
                        }
                      }
                    }
                  }
                }
              }
            }
            catch (Exception paramObject)
            {
              localObject = null;
            }
          }
        }
      }
    }
  }
  
  public static Bundle JSONtoBundle(JSONObject paramJSONObject)
  {
    try
    {
      paramJSONObject = BundleJSONConverter.convertToBundle(paramJSONObject);
      return paramJSONObject;
    }
    catch (JSONException paramJSONObject)
    {
      for (;;)
      {
        Log.d("NotificationsUtil", "failed to convert JSON to bundle: " + paramJSONObject.getMessage());
        paramJSONObject = null;
      }
    }
  }
  
  public static JSONObject bundleToJSON(Bundle paramBundle)
  {
    try
    {
      paramBundle = BundleJSONConverter.convertToJSON(paramBundle);
      return paramBundle;
    }
    catch (JSONException paramBundle)
    {
      for (;;)
      {
        Log.d("NotificationsUtil", "failed to convert bundle to JSON: " + paramBundle.getMessage());
        paramBundle = null;
      }
    }
  }
  
  public static SharedPreferences getAppPreferences(Context paramContext)
  {
    return paramContext.getSharedPreferences(Activity.class.getSimpleName(), 0);
  }
  
  public static String getApplicationName(Context paramContext)
  {
    PackageManager localPackageManager = paramContext.getPackageManager();
    try
    {
      paramContext = localPackageManager.getApplicationInfo(paramContext.getPackageName(), 0);
      if (paramContext != null)
      {
        paramContext = localPackageManager.getApplicationLabel(paramContext);
        return (String)paramContext;
      }
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      for (;;)
      {
        paramContext = null;
        continue;
        paramContext = "(unknown)";
      }
    }
  }
  
  public static JSONArray jsonArrayRemoveAt(JSONArray paramJSONArray, int paramInt)
  {
    Object localObject;
    if (paramJSONArray == null)
    {
      localObject = null;
      return (JSONArray)localObject;
    }
    JSONArray localJSONArray = new JSONArray();
    paramInt = 0;
    for (;;)
    {
      localObject = localJSONArray;
      if (paramInt >= paramJSONArray.length()) {
        break;
      }
      try
      {
        localJSONArray.put(paramJSONArray.get(paramInt));
        paramInt++;
      }
      catch (JSONException localJSONException)
      {
        for (;;)
        {
          Log.d("NotificationsUtil", "Error removing element from JSONArray: " + localJSONException.getMessage());
        }
      }
    }
  }
  
  public static String jsonGetString(JSONObject paramJSONObject, String paramString)
  {
    try
    {
      paramJSONObject = paramJSONObject.getString(paramString);
      return paramJSONObject;
    }
    catch (JSONException paramJSONObject)
    {
      for (;;)
      {
        Log.d("NotificationsUtil", "Error getting json string for key " + paramString);
        paramJSONObject = null;
      }
    }
  }
  
  public static JSONObject parseJSON(String paramString)
  {
    Object localObject = null;
    if (paramString.length() == 0) {
      paramString = (String)localObject;
    }
    for (;;)
    {
      return paramString;
      try
      {
        JSONObject localJSONObject = new org/json/JSONObject;
        localJSONObject.<init>(paramString);
        paramString = localJSONObject;
      }
      catch (JSONException paramString)
      {
        Log.d("NotificationsUtil", "failed to parse json: " + paramString.getMessage());
        paramString = (String)localObject;
      }
    }
  }
  
  public static void printBundle(Bundle paramBundle)
  {
    Iterator localIterator = paramBundle.keySet().iterator();
    while (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      try
      {
        StringBuilder localStringBuilder = new java/lang/StringBuilder;
        localStringBuilder.<init>();
        Log.d("NotificationsUtil", str + " : " + paramBundle.get(str).toString());
      }
      catch (Exception localException) {}
    }
  }
  
  public static void sendMessage(final String paramString1, String paramString2)
  {
    try
    {
      if (TextUtils.isEmpty(paramString2)) {
        UnityPlayer.UnitySendMessage("GCMReceiver", paramString1, "");
      }
      for (;;)
      {
        return;
        UnityPlayer.UnitySendMessage("GCMReceiver", paramString1, paramString2);
      }
    }
    catch (UnsatisfiedLinkError localUnsatisfiedLinkError)
    {
      for (;;)
      {
        Log.d("NotificationsUtil", "UnsatisfiedLinkError! " + localUnsatisfiedLinkError.toString());
        paramString1 = new TimerTask()
        {
          public void run()
          {
            try
            {
              if (TextUtils.isEmpty(this.val$message)) {
                UnityPlayer.UnitySendMessage("GCMReceiver", paramString1, "");
              }
              for (;;)
              {
                return;
                UnityPlayer.UnitySendMessage("GCMReceiver", paramString1, this.val$message);
              }
            }
            catch (UnsatisfiedLinkError localUnsatisfiedLinkError)
            {
              for (;;)
              {
                Log.d("NotificationsUtil", "Delayed message failed! " + paramString1 + " : " + this.val$message);
              }
            }
          }
        };
        new Timer().schedule(paramString1, 2000L);
      }
    }
  }
  
  public static void showToast(String paramString)
  {
    UnityPlayer.currentActivity.runOnUiThread(new Runnable()
    {
      public void run()
      {
        Toast.makeText(UnityPlayer.currentActivity, this.val$message, 0).show();
      }
    });
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\explodingbarrel\notifications\Util.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */