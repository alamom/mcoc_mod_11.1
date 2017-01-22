package com.google.android.vending.expansion.downloader;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;
import android.util.Log;

class SystemFacade
{
  private Context mContext;
  private NotificationManager mNotificationManager;
  
  public SystemFacade(Context paramContext)
  {
    this.mContext = paramContext;
    this.mNotificationManager = ((NotificationManager)this.mContext.getSystemService("notification"));
  }
  
  public void cancelAllNotifications()
  {
    this.mNotificationManager.cancelAll();
  }
  
  public void cancelNotification(long paramLong)
  {
    this.mNotificationManager.cancel((int)paramLong);
  }
  
  public long currentTimeMillis()
  {
    return System.currentTimeMillis();
  }
  
  public Integer getActiveNetworkType()
  {
    Integer localInteger = null;
    Object localObject = (ConnectivityManager)this.mContext.getSystemService("connectivity");
    if (localObject == null) {
      Log.w("LVLDL", "couldn't get connectivity manager");
    }
    for (;;)
    {
      return localInteger;
      localObject = ((ConnectivityManager)localObject).getActiveNetworkInfo();
      if (localObject != null) {
        localInteger = Integer.valueOf(((NetworkInfo)localObject).getType());
      }
    }
  }
  
  public Long getMaxBytesOverMobile()
  {
    return Long.valueOf(2147483647L);
  }
  
  public Long getRecommendedMaxBytesOverMobile()
  {
    return Long.valueOf(2097152L);
  }
  
  public boolean isNetworkRoaming()
  {
    boolean bool = false;
    Object localObject = (ConnectivityManager)this.mContext.getSystemService("connectivity");
    if (localObject == null)
    {
      Log.w("LVLDL", "couldn't get connectivity manager");
      return bool;
    }
    localObject = ((ConnectivityManager)localObject).getActiveNetworkInfo();
    if ((localObject != null) && (((NetworkInfo)localObject).getType() == 0)) {}
    for (int i = 1;; i = 0)
    {
      localObject = (TelephonyManager)this.mContext.getSystemService("phone");
      if (localObject != null) {
        break label80;
      }
      Log.w("LVLDL", "couldn't get telephony manager");
      break;
    }
    label80:
    if ((i != 0) && (((TelephonyManager)localObject).isNetworkRoaming())) {}
    for (bool = true;; bool = false) {
      break;
    }
  }
  
  public void postNotification(long paramLong, Notification paramNotification)
  {
    this.mNotificationManager.notify((int)paramLong, paramNotification);
  }
  
  public void sendBroadcast(Intent paramIntent)
  {
    this.mContext.sendBroadcast(paramIntent);
  }
  
  public void startThread(Thread paramThread)
  {
    paramThread.start();
  }
  
  public boolean userOwnsPackage(int paramInt, String paramString)
    throws PackageManager.NameNotFoundException
  {
    boolean bool = false;
    if (this.mContext.getPackageManager().getApplicationInfo(paramString, 0).uid == paramInt) {
      bool = true;
    }
    return bool;
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\vending\expansion\downloader\SystemFacade.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */