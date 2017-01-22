package com.google.android.vending.expansion.downloader.impl;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

public abstract class CustomIntentService
  extends Service
{
  private static final String LOG_TAG = "CancellableIntentService";
  private static final int WHAT_MESSAGE = -10;
  private String mName;
  private boolean mRedelivery;
  private volatile ServiceHandler mServiceHandler;
  private volatile Looper mServiceLooper;
  
  public CustomIntentService(String paramString)
  {
    this.mName = paramString;
  }
  
  public IBinder onBind(Intent paramIntent)
  {
    return null;
  }
  
  public void onCreate()
  {
    super.onCreate();
    HandlerThread localHandlerThread = new HandlerThread("IntentService[" + this.mName + "]");
    localHandlerThread.start();
    this.mServiceLooper = localHandlerThread.getLooper();
    this.mServiceHandler = new ServiceHandler(this.mServiceLooper);
  }
  
  public void onDestroy()
  {
    Thread localThread = this.mServiceLooper.getThread();
    if ((localThread != null) && (localThread.isAlive())) {
      localThread.interrupt();
    }
    this.mServiceLooper.quit();
    Log.d("CancellableIntentService", "onDestroy");
  }
  
  protected abstract void onHandleIntent(Intent paramIntent);
  
  public void onStart(Intent paramIntent, int paramInt)
  {
    if (!this.mServiceHandler.hasMessages(-10))
    {
      Message localMessage = this.mServiceHandler.obtainMessage();
      localMessage.arg1 = paramInt;
      localMessage.obj = paramIntent;
      localMessage.what = -10;
      this.mServiceHandler.sendMessage(localMessage);
    }
  }
  
  public int onStartCommand(Intent paramIntent, int paramInt1, int paramInt2)
  {
    onStart(paramIntent, paramInt2);
    if (this.mRedelivery) {}
    for (paramInt1 = 3;; paramInt1 = 2) {
      return paramInt1;
    }
  }
  
  public void setIntentRedelivery(boolean paramBoolean)
  {
    this.mRedelivery = paramBoolean;
  }
  
  protected abstract boolean shouldStop();
  
  private final class ServiceHandler
    extends Handler
  {
    public ServiceHandler(Looper paramLooper)
    {
      super();
    }
    
    public void handleMessage(Message paramMessage)
    {
      CustomIntentService.this.onHandleIntent((Intent)paramMessage.obj);
      if (CustomIntentService.this.shouldStop())
      {
        Log.d("CancellableIntentService", "stopSelf");
        CustomIntentService.this.stopSelf(paramMessage.arg1);
        Log.d("CancellableIntentService", "afterStopSelf");
      }
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\vending\expansion\downloader\impl\CustomIntentService.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */