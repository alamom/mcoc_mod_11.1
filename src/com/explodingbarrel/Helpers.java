package com.explodingbarrel;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.util.Log;
import java.util.List;

public class Helpers
{
  private static final String TAG = "EBHelpers";
  
  public static Intent createExplicitFromImplicitIntent(Context paramContext, Intent paramIntent)
  {
    paramContext = paramContext.getPackageManager().queryIntentServices(paramIntent, 0);
    if ((paramContext == null) || (paramContext.size() == 0)) {}
    for (;;)
    {
      return paramIntent;
      Object localObject = (ResolveInfo)paramContext.get(0);
      paramContext = ((ResolveInfo)localObject).serviceInfo.packageName;
      String str = ((ResolveInfo)localObject).serviceInfo.name;
      localObject = new ComponentName(paramContext, str);
      Log.i("EBHelpers", "createExplicitFromImplicitIntent: " + paramContext + " " + str);
      paramIntent = new Intent(paramIntent);
      paramIntent.setComponent((ComponentName)localObject);
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\explodingbarrel\Helpers.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */