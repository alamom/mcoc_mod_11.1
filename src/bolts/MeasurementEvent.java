package bolts;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONObject;

public class MeasurementEvent
{
  public static final String APP_LINK_NAVIGATE_IN_EVENT_NAME = "al_nav_in";
  public static final String APP_LINK_NAVIGATE_OUT_EVENT_NAME = "al_nav_out";
  public static final String MEASUREMENT_EVENT_ARGS_KEY = "event_args";
  public static final String MEASUREMENT_EVENT_NAME_KEY = "event_name";
  public static final String MEASUREMENT_EVENT_NOTIFICATION_NAME = "com.parse.bolts.measurement_event";
  private Context appContext;
  private Bundle args;
  private String name;
  
  private MeasurementEvent(Context paramContext, String paramString, Bundle paramBundle)
  {
    this.appContext = paramContext.getApplicationContext();
    this.name = paramString;
    this.args = paramBundle;
  }
  
  private static Bundle getApplinkLogData(Context paramContext, String paramString, Bundle paramBundle, Intent paramIntent)
  {
    Bundle localBundle = new Bundle();
    paramContext = paramIntent.resolveActivity(paramContext.getPackageManager());
    if (paramContext != null) {
      localBundle.putString("class", paramContext.getShortClassName());
    }
    if ("al_nav_out".equals(paramString))
    {
      if (paramContext != null) {
        localBundle.putString("package", paramContext.getPackageName());
      }
      if (paramIntent.getData() != null) {
        localBundle.putString("outputURL", paramIntent.getData().toString());
      }
      if (paramIntent.getScheme() != null) {
        localBundle.putString("outputURLScheme", paramIntent.getScheme());
      }
      paramContext = paramBundle.keySet().iterator();
    }
    for (;;)
    {
      if (!paramContext.hasNext()) {
        break label402;
      }
      paramString = (String)paramContext.next();
      Object localObject = paramBundle.get(paramString);
      if ((localObject instanceof Bundle))
      {
        Iterator localIterator = ((Bundle)localObject).keySet().iterator();
        for (;;)
        {
          if (!localIterator.hasNext()) {
            break label345;
          }
          String str = (String)localIterator.next();
          paramIntent = objectToJSONString(((Bundle)localObject).get(str));
          if (paramString.equals("referer_app_link"))
          {
            if (str.equalsIgnoreCase("url"))
            {
              localBundle.putString("refererURL", paramIntent);
              continue;
              if (!"al_nav_in".equals(paramString)) {
                break;
              }
              if (paramIntent.getData() != null) {
                localBundle.putString("inputURL", paramIntent.getData().toString());
              }
              if (paramIntent.getScheme() == null) {
                break;
              }
              localBundle.putString("inputURLScheme", paramIntent.getScheme());
              break;
            }
            if (str.equalsIgnoreCase("app_name"))
            {
              localBundle.putString("refererAppName", paramIntent);
              continue;
            }
            if (str.equalsIgnoreCase("package"))
            {
              localBundle.putString("sourceApplication", paramIntent);
              continue;
            }
          }
          localBundle.putString(paramString + "/" + str, paramIntent);
        }
      }
      else
      {
        label345:
        paramIntent = objectToJSONString(localObject);
        if (paramString.equals("target_url"))
        {
          paramString = Uri.parse(paramIntent);
          localBundle.putString("targetURL", paramString.toString());
          localBundle.putString("targetURLHost", paramString.getHost());
        }
        else
        {
          localBundle.putString(paramString, paramIntent);
        }
      }
    }
    label402:
    return localBundle;
  }
  
  private static String objectToJSONString(Object paramObject)
  {
    Object localObject1 = null;
    if (paramObject == null) {
      paramObject = localObject1;
    }
    for (;;)
    {
      return (String)paramObject;
      if (((paramObject instanceof JSONArray)) || ((paramObject instanceof JSONObject))) {
        paramObject = paramObject.toString();
      } else {
        try
        {
          Object localObject2;
          if ((paramObject instanceof Collection))
          {
            localObject2 = new org/json/JSONArray;
            ((JSONArray)localObject2).<init>((Collection)paramObject);
            paramObject = ((JSONArray)localObject2).toString();
          }
          else if ((paramObject instanceof Map))
          {
            localObject2 = new org/json/JSONObject;
            ((JSONObject)localObject2).<init>((Map)paramObject);
            paramObject = ((JSONObject)localObject2).toString();
          }
          else
          {
            paramObject = paramObject.toString();
          }
        }
        catch (Exception paramObject)
        {
          paramObject = localObject1;
        }
      }
    }
  }
  
  private void sendBroadcast()
  {
    if (this.name == null) {
      Log.d(getClass().getName(), "Event name is required");
    }
    try
    {
      Object localObject1 = Class.forName("android.support.v4.content.LocalBroadcastManager");
      Object localObject2 = ((Class)localObject1).getMethod("getInstance", new Class[] { Context.class });
      localObject1 = ((Class)localObject1).getMethod("sendBroadcast", new Class[] { Intent.class });
      localObject2 = ((Method)localObject2).invoke(null, new Object[] { this.appContext });
      Intent localIntent = new android/content/Intent;
      localIntent.<init>("com.parse.bolts.measurement_event");
      localIntent.putExtra("event_name", this.name);
      localIntent.putExtra("event_args", this.args);
      ((Method)localObject1).invoke(localObject2, new Object[] { localIntent });
      return;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        Log.d(getClass().getName(), "LocalBroadcastManager in android support library is required to raise bolts event.");
      }
    }
  }
  
  static void sendBroadcastEvent(Context paramContext, String paramString, Intent paramIntent, Map<String, String> paramMap)
  {
    Object localObject2 = new Bundle();
    Object localObject1 = localObject2;
    if (paramIntent != null)
    {
      localObject1 = AppLinks.getAppLinkData(paramIntent);
      if (localObject1 == null) {
        break label95;
      }
      localObject1 = getApplinkLogData(paramContext, paramString, (Bundle)localObject1, paramIntent);
    }
    while (paramMap != null)
    {
      paramIntent = paramMap.keySet().iterator();
      for (;;)
      {
        if (paramIntent.hasNext())
        {
          localObject2 = (String)paramIntent.next();
          ((Bundle)localObject1).putString((String)localObject2, (String)paramMap.get(localObject2));
          continue;
          label95:
          localObject1 = paramIntent.getData();
          if (localObject1 != null) {
            ((Bundle)localObject2).putString("intentData", ((Uri)localObject1).toString());
          }
          Bundle localBundle = paramIntent.getExtras();
          localObject1 = localObject2;
          if (localBundle == null) {
            break;
          }
          paramIntent = localBundle.keySet().iterator();
          for (;;)
          {
            localObject1 = localObject2;
            if (!paramIntent.hasNext()) {
              break;
            }
            localObject1 = (String)paramIntent.next();
            ((Bundle)localObject2).putString((String)localObject1, objectToJSONString(localBundle.get((String)localObject1)));
          }
        }
      }
    }
    new MeasurementEvent(paramContext, paramString, (Bundle)localObject1).sendBroadcast();
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\bolts\MeasurementEvent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */