package com.google.android.vending.expansion.downloader;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;

public class DownloaderServiceMarshaller
{
  public static final int MSG_REQUEST_ABORT_DOWNLOAD = 1;
  public static final int MSG_REQUEST_CLIENT_UPDATE = 6;
  public static final int MSG_REQUEST_CONTINUE_DOWNLOAD = 4;
  public static final int MSG_REQUEST_DOWNLOAD_STATE = 5;
  public static final int MSG_REQUEST_PAUSE_DOWNLOAD = 2;
  public static final int MSG_SET_DOWNLOAD_FLAGS = 3;
  public static final String PARAMS_FLAGS = "flags";
  public static final String PARAM_MESSENGER = "EMH";
  
  public static IDownloaderService CreateProxy(Messenger paramMessenger)
  {
    return new Proxy(paramMessenger);
  }
  
  public static IStub CreateStub(IDownloaderService paramIDownloaderService)
  {
    return new Stub(paramIDownloaderService);
  }
  
  private static class Proxy
    implements IDownloaderService
  {
    private Messenger mMsg;
    
    public Proxy(Messenger paramMessenger)
    {
      this.mMsg = paramMessenger;
    }
    
    private void send(int paramInt, Bundle paramBundle)
    {
      Message localMessage = Message.obtain(null, paramInt);
      localMessage.setData(paramBundle);
      try
      {
        this.mMsg.send(localMessage);
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
    
    public void onClientUpdated(Messenger paramMessenger)
    {
      Bundle localBundle = new Bundle(1);
      localBundle.putParcelable("EMH", paramMessenger);
      send(6, localBundle);
    }
    
    public void requestAbortDownload()
    {
      send(1, new Bundle());
    }
    
    public void requestContinueDownload()
    {
      send(4, new Bundle());
    }
    
    public void requestDownloadStatus()
    {
      send(5, new Bundle());
    }
    
    public void requestPauseDownload()
    {
      send(2, new Bundle());
    }
    
    public void setDownloadFlags(int paramInt)
    {
      Bundle localBundle = new Bundle();
      localBundle.putInt("flags", paramInt);
      send(3, localBundle);
    }
  }
  
  private static class Stub
    implements IStub
  {
    private IDownloaderService mItf = null;
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
          DownloaderServiceMarshaller.Stub.this.mItf.requestAbortDownload();
          continue;
          DownloaderServiceMarshaller.Stub.this.mItf.requestContinueDownload();
          continue;
          DownloaderServiceMarshaller.Stub.this.mItf.requestPauseDownload();
          continue;
          DownloaderServiceMarshaller.Stub.this.mItf.setDownloadFlags(paramAnonymousMessage.getData().getInt("flags"));
          continue;
          DownloaderServiceMarshaller.Stub.this.mItf.requestDownloadStatus();
          continue;
          DownloaderServiceMarshaller.Stub.this.mItf.onClientUpdated((Messenger)paramAnonymousMessage.getData().getParcelable("EMH"));
        }
      }
    });
    
    public Stub(IDownloaderService paramIDownloaderService)
    {
      this.mItf = paramIDownloaderService;
    }
    
    public void connect(Context paramContext) {}
    
    public void disconnect(Context paramContext) {}
    
    public Messenger getMessenger()
    {
      return this.mMessenger;
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\vending\expansion\downloader\DownloaderServiceMarshaller.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */