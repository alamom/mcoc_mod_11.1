package com.google.android.gms.wearable;

import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Binder;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.util.Log;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.wearable.internal.ae.a;
import com.google.android.gms.wearable.internal.ah;
import com.google.android.gms.wearable.internal.ak;

public abstract class WearableListenerService
  extends Service
  implements DataApi.DataListener, MessageApi.MessageListener, NodeApi.NodeListener
{
  public static final String BIND_LISTENER_INTENT_ACTION = "com.google.android.gms.wearable.BIND_LISTENER";
  private String BZ;
  private IBinder LZ;
  private volatile int NX = -1;
  private Handler avc;
  private Object avd = new Object();
  private boolean ave;
  
  private boolean bc(int paramInt)
  {
    boolean bool2 = false;
    String[] arrayOfString = getPackageManager().getPackagesForUid(paramInt);
    boolean bool1 = bool2;
    if (arrayOfString != null) {}
    for (paramInt = 0;; paramInt++)
    {
      bool1 = bool2;
      if (paramInt < arrayOfString.length)
      {
        if ("com.google.android.gms".equals(arrayOfString[paramInt])) {
          bool1 = true;
        }
      }
      else {
        return bool1;
      }
    }
  }
  
  private void pU()
    throws SecurityException
  {
    int i = Binder.getCallingUid();
    if (i == this.NX) {}
    for (;;)
    {
      return;
      if ((!GooglePlayServicesUtil.b(getPackageManager(), "com.google.android.gms")) || (!bc(i))) {
        break;
      }
      this.NX = i;
    }
    throw new SecurityException("Caller is not GooglePlayServices");
  }
  
  public final IBinder onBind(Intent paramIntent)
  {
    if ("com.google.android.gms.wearable.BIND_LISTENER".equals(paramIntent.getAction())) {}
    for (paramIntent = this.LZ;; paramIntent = null) {
      return paramIntent;
    }
  }
  
  public void onCreate()
  {
    super.onCreate();
    if (Log.isLoggable("WearableLS", 3)) {
      Log.d("WearableLS", "onCreate: " + getPackageName());
    }
    this.BZ = getPackageName();
    HandlerThread localHandlerThread = new HandlerThread("WearableListenerService");
    localHandlerThread.start();
    this.avc = new Handler(localHandlerThread.getLooper());
    this.LZ = new a(null);
  }
  
  public void onDataChanged(DataEventBuffer paramDataEventBuffer) {}
  
  public void onDestroy()
  {
    synchronized (this.avd)
    {
      this.ave = true;
      this.avc.getLooper().quit();
      super.onDestroy();
      return;
    }
  }
  
  public void onMessageReceived(MessageEvent paramMessageEvent) {}
  
  public void onPeerConnected(Node paramNode) {}
  
  public void onPeerDisconnected(Node paramNode) {}
  
  private class a
    extends ae.a
  {
    private a() {}
    
    public void Z(DataHolder paramDataHolder)
    {
      if (Log.isLoggable("WearableLS", 3)) {
        Log.d("WearableLS", "onDataItemChanged: " + WearableListenerService.a(WearableListenerService.this) + ": " + paramDataHolder);
      }
      WearableListenerService.b(WearableListenerService.this);
      synchronized (WearableListenerService.c(WearableListenerService.this))
      {
        if (WearableListenerService.d(WearableListenerService.this))
        {
          paramDataHolder.close();
          return;
        }
        Handler localHandler = WearableListenerService.e(WearableListenerService.this);
        Runnable local1 = new com/google/android/gms/wearable/WearableListenerService$a$1;
        local1.<init>(this, paramDataHolder);
        localHandler.post(local1);
      }
    }
    
    public void a(ah paramah)
    {
      if (Log.isLoggable("WearableLS", 3)) {
        Log.d("WearableLS", "onMessageReceived: " + paramah);
      }
      WearableListenerService.b(WearableListenerService.this);
      synchronized (WearableListenerService.c(WearableListenerService.this))
      {
        if (WearableListenerService.d(WearableListenerService.this)) {
          return;
        }
        Handler localHandler = WearableListenerService.e(WearableListenerService.this);
        Runnable local2 = new com/google/android/gms/wearable/WearableListenerService$a$2;
        local2.<init>(this, paramah);
        localHandler.post(local2);
      }
    }
    
    public void a(ak paramak)
    {
      if (Log.isLoggable("WearableLS", 3)) {
        Log.d("WearableLS", "onPeerConnected: " + WearableListenerService.a(WearableListenerService.this) + ": " + paramak);
      }
      WearableListenerService.b(WearableListenerService.this);
      synchronized (WearableListenerService.c(WearableListenerService.this))
      {
        if (WearableListenerService.d(WearableListenerService.this)) {
          return;
        }
        Handler localHandler = WearableListenerService.e(WearableListenerService.this);
        Runnable local3 = new com/google/android/gms/wearable/WearableListenerService$a$3;
        local3.<init>(this, paramak);
        localHandler.post(local3);
      }
    }
    
    public void b(ak paramak)
    {
      if (Log.isLoggable("WearableLS", 3)) {
        Log.d("WearableLS", "onPeerDisconnected: " + WearableListenerService.a(WearableListenerService.this) + ": " + paramak);
      }
      WearableListenerService.b(WearableListenerService.this);
      synchronized (WearableListenerService.c(WearableListenerService.this))
      {
        if (WearableListenerService.d(WearableListenerService.this)) {
          return;
        }
        Handler localHandler = WearableListenerService.e(WearableListenerService.this);
        Runnable local4 = new com/google/android/gms/wearable/WearableListenerService$a$4;
        local4.<init>(this, paramak);
        localHandler.post(local4);
      }
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\wearable\WearableListenerService.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */