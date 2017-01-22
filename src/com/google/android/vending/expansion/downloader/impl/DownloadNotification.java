package com.google.android.vending.expansion.downloader.impl;

import android.app.Notification;
import android.app.Notification.Builder;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.os.Messenger;
import com.google.android.vending.expansion.downloader.DownloadProgressInfo;
import com.google.android.vending.expansion.downloader.DownloaderClientMarshaller;
import com.google.android.vending.expansion.downloader.Helpers;
import com.google.android.vending.expansion.downloader.IDownloaderClient;

public class DownloadNotification
  implements IDownloaderClient
{
  static final String LOGTAG = "DownloadNotification";
  static final int NOTIFICATION_ID = "DownloadNotification".hashCode();
  private IDownloaderClient mClientProxy;
  private PendingIntent mContentIntent;
  private final Context mContext;
  private Notification mCurrentNotification;
  private String mCurrentText;
  private String mCurrentTitle;
  final ICustomNotification mCustomNotification;
  private CharSequence mLabel;
  private Notification mNotification;
  private final NotificationManager mNotificationManager;
  private DownloadProgressInfo mProgressInfo;
  private int mState = -1;
  
  DownloadNotification(Context paramContext, CharSequence paramCharSequence)
  {
    this.mContext = paramContext;
    this.mLabel = paramCharSequence;
    this.mNotificationManager = ((NotificationManager)this.mContext.getSystemService("notification"));
    this.mCustomNotification = CustomNotificationFactory.createCustomNotification();
    this.mNotification = new Notification();
    this.mCurrentNotification = this.mNotification;
  }
  
  public PendingIntent getClientIntent()
  {
    return this.mContentIntent;
  }
  
  public void onDownloadProgress(DownloadProgressInfo paramDownloadProgressInfo)
  {
    this.mProgressInfo = paramDownloadProgressInfo;
    if (this.mClientProxy != null) {
      this.mClientProxy.onDownloadProgress(paramDownloadProgressInfo);
    }
    if (paramDownloadProgressInfo.mOverallTotal <= 0L) {
      this.mNotification = new Notification.Builder(this.mContext).setContentIntent(this.mContentIntent).setContentTitle(this.mLabel).setContentText(this.mCurrentText).setSmallIcon(17301633).setTicker(this.mCurrentTitle).build();
    }
    for (this.mCurrentNotification = this.mNotification;; this.mCurrentNotification = this.mCustomNotification.updateNotification(this.mContext))
    {
      this.mNotificationManager.notify(NOTIFICATION_ID, this.mCurrentNotification);
      return;
      this.mCustomNotification.setCurrentBytes(paramDownloadProgressInfo.mOverallProgress);
      this.mCustomNotification.setTotalBytes(paramDownloadProgressInfo.mOverallTotal);
      this.mCustomNotification.setIcon(17301633);
      this.mCustomNotification.setPendingIntent(this.mContentIntent);
      this.mCustomNotification.setTicker(this.mLabel + ": " + this.mCurrentText);
      this.mCustomNotification.setTitle(this.mLabel);
      this.mCustomNotification.setTimeRemaining(paramDownloadProgressInfo.mTimeRemaining);
    }
  }
  
  public void onDownloadStateChanged(int paramInt)
  {
    if (this.mClientProxy != null) {
      this.mClientProxy.onDownloadStateChanged(paramInt);
    }
    if (paramInt != this.mState)
    {
      this.mState = paramInt;
      if ((paramInt != 1) && (this.mContentIntent != null)) {}
    }
    else
    {
      return;
    }
    int k;
    int j;
    int i;
    label158:
    Notification localNotification;
    switch (paramInt)
    {
    case 1: 
    case 6: 
    case 8: 
    case 9: 
    case 10: 
    case 11: 
    case 12: 
    case 13: 
    case 14: 
    default: 
      k = 17301642;
      j = Helpers.getDownloaderStringResourceIDFromState(this.mContext, paramInt);
      i = 1;
      paramInt = k;
      this.mCurrentText = this.mContext.getString(j);
      this.mCurrentTitle = this.mLabel.toString();
      this.mCurrentNotification = new Notification.Builder(this.mContext).setContentIntent(this.mContentIntent).setContentTitle(this.mCurrentTitle).setContentText(this.mCurrentText).setSmallIcon(paramInt).setTicker(this.mLabel + ": " + this.mCurrentText).build();
      if (i != 0) {
        localNotification = this.mCurrentNotification;
      }
      break;
    }
    for (localNotification.flags |= 0x2;; localNotification.flags |= 0x10)
    {
      this.mNotificationManager.notify(NOTIFICATION_ID, this.mCurrentNotification);
      break;
      paramInt = 17301642;
      j = Helpers.getStringResource(this.mContext, "state_unknown");
      i = 0;
      break label158;
      i = 17301633;
      j = Helpers.getDownloaderStringResourceIDFromState(this.mContext, paramInt);
      k = 1;
      paramInt = i;
      i = k;
      break label158;
      k = 17301634;
      j = Helpers.getDownloaderStringResourceIDFromState(this.mContext, paramInt);
      i = 1;
      paramInt = k;
      break label158;
      i = 17301634;
      j = Helpers.getDownloaderStringResourceIDFromState(this.mContext, paramInt);
      k = 0;
      paramInt = i;
      i = k;
      break label158;
      k = 17301642;
      j = Helpers.getDownloaderStringResourceIDFromState(this.mContext, paramInt);
      i = 0;
      paramInt = k;
      break label158;
      localNotification = this.mCurrentNotification;
      localNotification.flags &= 0xFFFFFFFD;
      localNotification = this.mCurrentNotification;
    }
  }
  
  public void onServiceConnected(Messenger paramMessenger) {}
  
  public void resendState()
  {
    if (this.mClientProxy != null) {
      this.mClientProxy.onDownloadStateChanged(this.mState);
    }
  }
  
  public void setClientIntent(PendingIntent paramPendingIntent)
  {
    this.mContentIntent = paramPendingIntent;
  }
  
  public void setMessenger(Messenger paramMessenger)
  {
    this.mClientProxy = DownloaderClientMarshaller.CreateProxy(paramMessenger);
    if (this.mProgressInfo != null) {
      this.mClientProxy.onDownloadProgress(this.mProgressInfo);
    }
    if (this.mState != -1) {
      this.mClientProxy.onDownloadStateChanged(this.mState);
    }
  }
  
  public static abstract interface ICustomNotification
  {
    public abstract void setCurrentBytes(long paramLong);
    
    public abstract void setIcon(int paramInt);
    
    public abstract void setPendingIntent(PendingIntent paramPendingIntent);
    
    public abstract void setTicker(CharSequence paramCharSequence);
    
    public abstract void setTimeRemaining(long paramLong);
    
    public abstract void setTitle(CharSequence paramCharSequence);
    
    public abstract void setTotalBytes(long paramLong);
    
    public abstract Notification updateNotification(Context paramContext);
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\vending\expansion\downloader\impl\DownloadNotification.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */