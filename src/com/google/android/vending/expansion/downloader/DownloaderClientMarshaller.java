package com.google.android.vending.expansion.downloader;

import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import com.google.android.vending.expansion.downloader.impl.DownloaderService;

public class DownloaderClientMarshaller
{
  public static final int DOWNLOAD_REQUIRED = 2;
  public static final int LVL_CHECK_REQUIRED = 1;
  public static final int MSG_ONDOWNLOADPROGRESS = 11;
  public static final int MSG_ONDOWNLOADSTATE_CHANGED = 10;
  public static final int MSG_ONSERVICECONNECTED = 12;
  public static final int NO_DOWNLOAD_REQUIRED = 0;
  public static final String PARAM_MESSENGER = "EMH";
  public static final String PARAM_NEW_STATE = "newState";
  public static final String PARAM_PROGRESS = "progress";
  
  public static IDownloaderClient CreateProxy(Messenger paramMessenger)
  {
    return new Proxy(paramMessenger);
  }
  
  public static IStub CreateStub(IDownloaderClient paramIDownloaderClient, Class<?> paramClass)
  {
    return new Stub(paramIDownloaderClient, paramClass);
  }
  
  public static int startDownloadServiceIfRequired(Context paramContext, PendingIntent paramPendingIntent, Class<?> paramClass)
    throws PackageManager.NameNotFoundException
  {
    return DownloaderService.startDownloadServiceIfRequired(paramContext, paramPendingIntent, paramClass);
  }
  
  public static int startDownloadServiceIfRequired(Context paramContext, Intent paramIntent, Class<?> paramClass)
    throws PackageManager.NameNotFoundException
  {
    return DownloaderService.startDownloadServiceIfRequired(paramContext, paramIntent, paramClass);
  }
  
  private static class Proxy
    implements IDownloaderClient
  {
    private Messenger mServiceMessenger;
    
    public Proxy(Messenger paramMessenger)
    {
      this.mServiceMessenger = paramMessenger;
    }
    
    private void send(int paramInt, Bundle paramBundle)
    {
      Message localMessage = Message.obtain(null, paramInt);
      localMessage.setData(paramBundle);
      try
      {
        this.mServiceMessenger.send(localMessage);
        return;
      }
      catch (RemoteException paramBundle)
      {
        for (;;)
        {
          paramBundle.printStackTrace();
        }
      }
    }
    
    public void onDownloadProgress(DownloadProgressInfo paramDownloadProgressInfo)
    {
      Bundle localBundle = new Bundle(1);
      localBundle.putParcelable("progress", paramDownloadProgressInfo);
      send(11, localBundle);
    }
    
    public void onDownloadStateChanged(int paramInt)
    {
      Bundle localBundle = new Bundle(1);
      localBundle.putInt("newState", paramInt);
      send(10, localBundle);
    }
    
    public void onServiceConnected(Messenger paramMessenger) {}
  }
  
  private static class Stub
    implements IStub
  {
    private boolean mBound;
    private ServiceConnection mConnection = new ServiceConnection()
    {
      public void onServiceConnected(ComponentName paramAnonymousComponentName, IBinder paramAnonymousIBinder)
      {
        DownloaderClientMarshaller.Stub.access$202(DownloaderClientMarshaller.Stub.this, new Messenger(paramAnonymousIBinder));
        DownloaderClientMarshaller.Stub.this.mItf.onServiceConnected(DownloaderClientMarshaller.Stub.this.mServiceMessenger);
      }
      
      public void onServiceDisconnected(ComponentName paramAnonymousComponentName)
      {
        DownloaderClientMarshaller.Stub.access$202(DownloaderClientMarshaller.Stub.this, null);
      }
    };
    private Context mContext;
    private Class<?> mDownloaderServiceClass;
    private IDownloaderClient mItf = null;
    final Messenger mMessenger = new Messenger(new Handler()
    {
      public void handleMessage(Message paramAnonymousMessage)
      {
        switch (paramAnonymousMessage.what)
        {
        }
        for (;;)
        {
          return;
          Bundle localBundle = paramAnonymousMessage.getData();
          if (DownloaderClientMarshaller.Stub.this.mContext != null)
          {
            localBundle.setClassLoader(DownloaderClientMarshaller.Stub.this.mContext.getClassLoader());
            paramAnonymousMessage = (DownloadProgressInfo)paramAnonymousMessage.getData().getParcelable("progress");
            DownloaderClientMarshaller.Stub.this.mItf.onDownloadProgress(paramAnonymousMessage);
            continue;
            DownloaderClientMarshaller.Stub.this.mItf.onDownloadStateChanged(paramAnonymousMessage.getData().getInt("newState"));
            continue;
            DownloaderClientMarshaller.Stub.this.mItf.onServiceConnected((Messenger)paramAnonymousMessage.getData().getParcelable("EMH"));
          }
        }
      }
    });
    private Messenger mServiceMessenger;
    
    public Stub(IDownloaderClient paramIDownloaderClient, Class<?> paramClass)
    {
      this.mItf = paramIDownloaderClient;
      this.mDownloaderServiceClass = paramClass;
    }
    
    public void connect(Context paramContext)
    {
      this.mContext = paramContext;
      Intent localIntent = new Intent(paramContext, this.mDownloaderServiceClass);
      localIntent.putExtra("EMH", this.mMessenger);
      if (!paramContext.bindService(localIntent, this.mConnection, 2)) {}
      for (;;)
      {
        return;
        this.mBound = true;
      }
    }
    
    public void disconnect(Context paramContext)
    {
      if (this.mBound)
      {
        paramContext.unbindService(this.mConnection);
        this.mBound = false;
      }
      this.mContext = null;
    }
    
    public Messenger getMessenger()
    {
      return this.mMessenger;
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\vending\expansion\downloader\DownloaderClientMarshaller.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */