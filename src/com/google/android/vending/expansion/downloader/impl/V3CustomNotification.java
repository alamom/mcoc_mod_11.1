package com.google.android.vending.expansion.downloader.impl;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.os.Build.VERSION;
import android.widget.RemoteViews;
import com.google.android.vending.expansion.downloader.Helpers;

public class V3CustomNotification
  implements DownloadNotification.ICustomNotification
{
  long mCurrentBytes = -1L;
  int mIcon;
  Notification mNotification = new Notification();
  PendingIntent mPendingIntent;
  CharSequence mTicker;
  long mTimeRemaining;
  CharSequence mTitle;
  long mTotalBytes = -1L;
  
  public void setCurrentBytes(long paramLong)
  {
    this.mCurrentBytes = paramLong;
  }
  
  public void setIcon(int paramInt)
  {
    this.mIcon = paramInt;
  }
  
  public void setPendingIntent(PendingIntent paramPendingIntent)
  {
    this.mPendingIntent = paramPendingIntent;
  }
  
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
    this.mTotalBytes = paramLong;
  }
  
  public Notification updateNotification(Context paramContext)
  {
    Notification localNotification = this.mNotification;
    localNotification.icon = this.mIcon;
    localNotification.flags |= 0x2;
    if (Build.VERSION.SDK_INT > 10) {
      localNotification.flags |= 0x8;
    }
    RemoteViews localRemoteViews = new RemoteViews(paramContext.getPackageName(), Helpers.getLayoutResource(paramContext, "status_bar_ongoing_event_progress_bar"));
    localRemoteViews.setTextViewText(Helpers.getIdResource(paramContext, "title"), this.mTitle);
    localRemoteViews.setViewVisibility(Helpers.getIdResource(paramContext, "description"), 0);
    localRemoteViews.setTextViewText(Helpers.getIdResource(paramContext, "description"), Helpers.getDownloadProgressString(this.mCurrentBytes, this.mTotalBytes));
    localRemoteViews.setViewVisibility(Helpers.getIdResource(paramContext, "progress_bar_frame"), 0);
    int k = Helpers.getIdResource(paramContext, "progress_bar");
    int j = (int)(this.mTotalBytes >> 8);
    int i = (int)(this.mCurrentBytes >> 8);
    if (this.mTotalBytes <= 0L) {}
    for (boolean bool = true;; bool = false)
    {
      localRemoteViews.setProgressBar(k, j, i, bool);
      localRemoteViews.setViewVisibility(Helpers.getIdResource(paramContext, "time_remaining"), 0);
      localRemoteViews.setTextViewText(Helpers.getIdResource(paramContext, "time_remaining"), paramContext.getString(Helpers.getStringResource(paramContext, "time_remaining_notification"), new Object[] { Helpers.getTimeRemaining(this.mTimeRemaining) }));
      localRemoteViews.setTextViewText(Helpers.getIdResource(paramContext, "progress_text"), Helpers.getDownloadProgressPercent(this.mCurrentBytes, this.mTotalBytes));
      localRemoteViews.setImageViewResource(Helpers.getIdResource(paramContext, "appIcon"), this.mIcon);
      localNotification.contentView = localRemoteViews;
      localNotification.contentIntent = this.mPendingIntent;
      return localNotification;
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\vending\expansion\downloader\impl\V3CustomNotification.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */