package com.google.android.vending.expansion.downloader.impl;

import android.os.Build.VERSION;

public class CustomNotificationFactory
{
  public static DownloadNotification.ICustomNotification createCustomNotification()
  {
    if (Build.VERSION.SDK_INT > 13) {}
    for (Object localObject = new V14CustomNotification();; localObject = new V3CustomNotification()) {
      return (DownloadNotification.ICustomNotification)localObject;
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\vending\expansion\downloader\impl\CustomNotificationFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */