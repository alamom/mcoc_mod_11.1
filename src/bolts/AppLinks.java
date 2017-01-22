package bolts;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

public final class AppLinks
{
  static final String KEY_NAME_APPLINK_DATA = "al_applink_data";
  static final String KEY_NAME_EXTRAS = "extras";
  static final String KEY_NAME_TARGET = "target_url";
  
  public static Bundle getAppLinkData(Intent paramIntent)
  {
    return paramIntent.getBundleExtra("al_applink_data");
  }
  
  public static Bundle getAppLinkExtras(Intent paramIntent)
  {
    paramIntent = getAppLinkData(paramIntent);
    if (paramIntent == null) {}
    for (paramIntent = null;; paramIntent = paramIntent.getBundle("extras")) {
      return paramIntent;
    }
  }
  
  public static Uri getTargetUrl(Intent paramIntent)
  {
    Object localObject = getAppLinkData(paramIntent);
    if (localObject != null)
    {
      localObject = ((Bundle)localObject).getString("target_url");
      if (localObject == null) {}
    }
    for (paramIntent = Uri.parse((String)localObject);; paramIntent = paramIntent.getData()) {
      return paramIntent;
    }
  }
  
  public static Uri getTargetUrlFromInboundIntent(Context paramContext, Intent paramIntent)
  {
    Object localObject2 = null;
    Object localObject3 = getAppLinkData(paramIntent);
    Object localObject1 = localObject2;
    if (localObject3 != null)
    {
      localObject3 = ((Bundle)localObject3).getString("target_url");
      localObject1 = localObject2;
      if (localObject3 != null)
      {
        MeasurementEvent.sendBroadcastEvent(paramContext, "al_nav_in", paramIntent, null);
        localObject1 = Uri.parse((String)localObject3);
      }
    }
    return (Uri)localObject1;
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\bolts\AppLinks.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */