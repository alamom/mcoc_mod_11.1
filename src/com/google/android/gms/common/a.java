package com.google.android.gms.common;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class a
  implements ServiceConnection
{
  boolean HC = false;
  private final BlockingQueue<IBinder> HD = new LinkedBlockingQueue();
  
  public IBinder fW()
    throws InterruptedException
  {
    if (this.HC) {
      throw new IllegalStateException();
    }
    this.HC = true;
    return (IBinder)this.HD.take();
  }
  
  public void onServiceConnected(ComponentName paramComponentName, IBinder paramIBinder)
  {
    this.HD.add(paramIBinder);
  }
  
  public void onServiceDisconnected(ComponentName paramComponentName) {}
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\common\a.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */