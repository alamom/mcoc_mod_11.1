package com.google.android.vending.expansion.downloader.impl;

import android.app.Notification;
import android.app.Notification.Builder;
import android.app.PendingIntent;
import android.content.Context;
import com.google.android.vending.expansion.downloader.Helpers;

public class V14CustomNotification
  implements DownloadNotification.ICustomNotification
{
  long mCurrentKB = -1L;
  int mIcon;
  PendingIntent mPendingIntent;
  CharSequence mTicker;
  long mTimeRemaining;
  CharSequence mTitle;
  long mTotalKB = -1L;
  
  public void setCurrentBytes(long paramLong)
  {
    this.mCurrentKB = paramLong;
  }
  
  public void setIcon(int paramInt)
  {
    this.mIcon = paramInt;
  }
  
  public void setPendingIntent(PendingIntent paramPendingIntent)
  {
    this.mPendingIntent = paramPendingIntent;
  }
  
  void setProgress(Notification.Builder paramBuilder) {}
  
  public void setTicker(CharSequence paramCharSequence)
  {
    this.mTicker = paramCharSequence;
  }
  
  public void setTimeRemaining(long paramLong)
  {
    this.mTimeRemaining = paramLong;
  }
  
  public void setTitle(CharSequence paramCharSequence)
  {
    this.mTitle = paramCharSequence;
  }
  
  public void setTotalBytes(long paramLong)
  {
    this.mTotalKB = paramLong;
  }
  
  public Notification updateNotification(Context paramContext)
  {
    Notification.Builder localBuilder = new Notification.Builder(paramContext);
    localBuilder.setContentTitle(this.mTitle);
    if ((this.mTotalKB > 0L) && (-1L != this.mCurrentKB))
    {
      localBuilder.setProgress((int)(this.mTotalKB >> 8), (int)(this.mCurrentKB >> 8), false);
      localBuilder.setContentText(Helpers.getDownloadProgressString(this.mCurrentKB, this.mTotalKB));
      localBuilder.setContentInfo(paramContext.getString(Helpers.getStringResource(paramContext, "time_remaining_notification"), new Object[] { Helpers.getTimeRemaining(this.mTimeRemaining) }));
      if (this.mIcon == 0) {
        break label167;
      }
      localBuilder.setSmallIcon(this.mIcon);
    }
    for (;;)
    {
      localBuilder.setOngoing(true);
      localBuilder.setTicker(this.mTicker);
      localBuilder.setContentIntent(this.mPendingIntent);
      localBuilder.setOnlyAlertOnce(true);
      return localBuilder.getNotification();
      localBuilder.setProgress(0, 0, true);
      break;
      label167:
      localBuilder.setSmallIcon(17301633);
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\vending\expansion\downloader\impl\V14CustomNotification.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */