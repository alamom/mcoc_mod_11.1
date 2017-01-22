package com.unity3d.player;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageItemInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.PermissionInfo;
import android.os.Bundle;
import java.util.LinkedList;
import java.util.List;

public final class n
  implements i
{
  private static boolean a(PackageItemInfo paramPackageItemInfo)
  {
    try
    {
      bool = paramPackageItemInfo.metaData.getBoolean("unityplayer.SkipPermissionsDialog");
      return bool;
    }
    catch (Exception paramPackageItemInfo)
    {
      for (;;)
      {
        boolean bool = false;
      }
    }
  }
  
  public final void a(Activity paramActivity, Runnable paramRunnable)
  {
    int i = 0;
    if (paramActivity == null) {}
    for (;;)
    {
      return;
      Object localObject1 = paramActivity.getPackageManager();
      LinkedList localLinkedList;
      try
      {
        ActivityInfo localActivityInfo = ((PackageManager)localObject1).getActivityInfo(paramActivity.getComponentName(), 128);
        localObject2 = ((PackageManager)localObject1).getApplicationInfo(paramActivity.getPackageName(), 128);
        if ((a(localActivityInfo)) || (a((PackageItemInfo)localObject2))) {
          paramRunnable.run();
        }
      }
      catch (Exception localException)
      {
        try
        {
          Object localObject2 = ((PackageManager)localObject1).getPackageInfo(paramActivity.getPackageName(), 4096);
          if (((PackageInfo)localObject2).requestedPermissions == null) {
            ((PackageInfo)localObject2).requestedPermissions = new String[0];
          }
          localLinkedList = new java/util/LinkedList;
          localLinkedList.<init>();
          String[] arrayOfString = ((PackageInfo)localObject2).requestedPermissions;
          int j = arrayOfString.length;
          for (;;)
          {
            if (i < j)
            {
              localObject2 = arrayOfString[i];
              try
              {
                if (((PackageManager)localObject1).getPermissionInfo((String)localObject2, 128).protectionLevel != 1) {}
                for (;;)
                {
                  i++;
                  break;
                  if (paramActivity.checkCallingOrSelfPermission((String)localObject2) != 0) {
                    localLinkedList.add(localObject2);
                  }
                }
              }
              catch (PackageManager.NameNotFoundException localNameNotFoundException)
              {
                for (;;)
                {
                  StringBuilder localStringBuilder = new java/lang/StringBuilder;
                  localStringBuilder.<init>("Failed to get permission info for ");
                  m.Log(5, (String)localObject2 + ", manifest likely missing custom permission declaration");
                  localStringBuilder = new java/lang/StringBuilder;
                  localStringBuilder.<init>("Permission ");
                  m.Log(5, (String)localObject2 + " ignored");
                }
              }
            }
          }
        }
        catch (Exception paramActivity)
        {
          m.Log(6, "Unable to query for permission: " + paramActivity.getMessage());
        }
      }
      if (localLinkedList.isEmpty())
      {
        paramRunnable.run();
      }
      else
      {
        localObject1 = paramActivity.getFragmentManager();
        paramActivity = new com/unity3d/player/n$1;
        paramActivity.<init>(this, localLinkedList, (FragmentManager)localObject1, paramRunnable);
        paramRunnable = ((FragmentManager)localObject1).beginTransaction();
        paramRunnable.add(0, paramActivity);
        paramRunnable.commit();
      }
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\unity3d\player\n.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */