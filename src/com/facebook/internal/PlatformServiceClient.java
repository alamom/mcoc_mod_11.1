package com.facebook.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;

public abstract class PlatformServiceClient
  implements ServiceConnection
{
  private final String applicationId;
  private final Context context;
  private final Handler handler;
  private CompletedListener listener;
  private final int protocolVersion;
  private int replyMessage;
  private int requestMessage;
  private boolean running;
  private Messenger sender;
  
  public PlatformServiceClient(Context paramContext, int paramInt1, int paramInt2, int paramInt3, String paramString)
  {
    Context localContext = paramContext.getApplicationContext();
    if (localContext != null) {
      paramContext = localContext;
    }
    for (;;)
    {
      this.context = paramContext;
      this.requestMessage = paramInt1;
      this.replyMessage = paramInt2;
      this.applicationId = paramString;
      this.protocolVersion = paramInt3;
      this.handler = new Handler()
      {
        public void handleMessage(Message paramAnonymousMessage)
        {
          PlatformServiceClient.this.handleMessage(paramAnonymousMessage);
        }
      };
      return;
    }
  }
  
  private void callback(Bundle paramBundle)
  {
    if (!this.running) {}
    for (;;)
    {
      return;
      this.running = false;
      CompletedListener localCompletedListener = this.listener;
      if (localCompletedListener != null) {
        localCompletedListener.completed(paramBundle);
      }
    }
  }
  
  private void sendMessage()
  {
    Bundle localBundle = new Bundle();
    localBundle.putString("com.facebook.platform.extra.APPLICATION_ID", this.applicationId);
    populateRequestBundle(localBundle);
    Message localMessage = Message.obtain(null, this.requestMessage);
    localMessage.arg1 = this.protocolVersion;
    localMessage.setData(localBundle);
    localMessage.replyTo = new Messenger(this.handler);
    try
    {
      this.sender.send(localMessage);
      return;
    }
    catch (RemoteException localRemoteException)
    {
      for (;;)
      {
        callback(null);
      }
    }
  }
  
  public void cancel()
  {
    this.running = false;
  }
  
  protected Context getContext()
  {
    return this.context;
  }
  
  protected void handleMessage(Message paramMessage)
  {
    if (paramMessage.what == this.replyMessage)
    {
      paramMessage = paramMessage.getData();
      if (paramMessage.getString("com.facebook.platform.status.ERROR_TYPE") == null) {
        break label39;
      }
      callback(null);
    }
    for (;;)
    {
      this.context.unbindService(this);
      return;
      label39:
      callback(paramMessage);
    }
  }
  
  public void onServiceConnected(ComponentName paramComponentName, IBinder paramIBinder)
  {
    this.sender = new Messenger(paramIBinder);
    sendMessage();
  }
  
  public void onServiceDisconnected(ComponentName paramComponentName)
  {
    this.sender = null;
    try
    {
      this.context.unbindService(this);
      callback(null);
      return;
    }
    catch (IllegalArgumentException paramComponentName)
    {
      for (;;) {}
    }
  }
  
  protected abstract void populateRequestBundle(Bundle paramBundle);
  
  public void setCompletedListener(CompletedListener paramCompletedListener)
  {
    this.listener = paramCompletedListener;
  }
  
  public boolean start()
  {
    boolean bool2 = false;
    boolean bool1;
    if (this.running) {
      bool1 = bool2;
    }
    for (;;)
    {
      return bool1;
      bool1 = bool2;
      if (NativeProtocol.getLatestAvailableProtocolVersionForService(this.context, this.protocolVersion) != -1)
      {
        Intent localIntent = NativeProtocol.createPlatformServiceIntent(this.context);
        bool1 = bool2;
        if (localIntent != null)
        {
          this.running = true;
          this.context.bindService(localIntent, this, 1);
          bool1 = true;
        }
      }
    }
  }
  
  public static abstract interface CompletedListener
  {
    public abstract void completed(Bundle paramBundle);
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\facebook\internal\PlatformServiceClient.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */