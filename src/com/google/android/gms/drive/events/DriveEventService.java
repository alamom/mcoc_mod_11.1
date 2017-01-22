package com.google.android.gms.drive.events;

import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.internal.o;
import com.google.android.gms.drive.internal.OnEventResponse;
import com.google.android.gms.drive.internal.ad.a;
import com.google.android.gms.drive.internal.v;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public abstract class DriveEventService
  extends Service
  implements ChangeListener, CompletionListener
{
  public static final String ACTION_HANDLE_EVENT = "com.google.android.gms.drive.events.HANDLE_EVENT";
  private CountDownLatch NV;
  a NW;
  int NX = -1;
  private final String mName;
  
  protected DriveEventService()
  {
    this("DriveEventService");
  }
  
  protected DriveEventService(String paramString)
  {
    this.mName = paramString;
  }
  
  private void a(OnEventResponse paramOnEventResponse)
  {
    paramOnEventResponse = paramOnEventResponse.ih();
    v.n("DriveEventService", "handleEventMessage: " + paramOnEventResponse);
    for (;;)
    {
      try
      {
        switch (paramOnEventResponse.getType())
        {
        default: 
          String str = this.mName;
          StringBuilder localStringBuilder = new java/lang/StringBuilder;
          localStringBuilder.<init>();
          v.p(str, "Unhandled event: " + paramOnEventResponse);
          return;
        }
      }
      catch (Exception localException)
      {
        v.a(this.mName, localException, "Error handling event: " + paramOnEventResponse);
        continue;
        onCompletion((CompletionEvent)paramOnEventResponse);
        continue;
      }
      onChange((ChangeEvent)paramOnEventResponse);
    }
  }
  
  private boolean bc(int paramInt)
  {
    boolean bool2 = false;
    String[] arrayOfString = getPackageManager().getPackagesForUid(paramInt);
    boolean bool1 = bool2;
    int i;
    if (arrayOfString != null) {
      i = arrayOfString.length;
    }
    for (paramInt = 0;; paramInt++)
    {
      bool1 = bool2;
      if (paramInt < i)
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
  
  private void hV()
    throws SecurityException
  {
    int i = getCallingUid();
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
  
  protected int getCallingUid()
  {
    return Binder.getCallingUid();
  }
  
  public final IBinder onBind(Intent paramIntent)
  {
    for (;;)
    {
      try
      {
        if ("com.google.android.gms.drive.events.HANDLE_EVENT".equals(paramIntent.getAction()))
        {
          if (this.NW == null)
          {
            paramIntent = new java/util/concurrent/CountDownLatch;
            paramIntent.<init>(1);
            Object localObject = new java/util/concurrent/CountDownLatch;
            ((CountDownLatch)localObject).<init>(1);
            this.NV = ((CountDownLatch)localObject);
            localObject = new com/google/android/gms/drive/events/DriveEventService$1;
            ((1)localObject).<init>(this, paramIntent);
            ((1)localObject).start();
          }
          try
          {
            paramIntent.await(5000L, TimeUnit.MILLISECONDS);
            paramIntent = new com/google/android/gms/drive/events/DriveEventService$b;
            paramIntent.<init>(this);
            paramIntent = paramIntent.asBinder();
            return paramIntent;
          }
          catch (InterruptedException localInterruptedException)
          {
            paramIntent = new java/lang/RuntimeException;
            paramIntent.<init>("Unable to start event handler", localInterruptedException);
            throw paramIntent;
          }
        }
        paramIntent = null;
      }
      finally {}
    }
  }
  
  public void onChange(ChangeEvent paramChangeEvent)
  {
    v.p(this.mName, "Unhandled change event: " + paramChangeEvent);
  }
  
  public void onCompletion(CompletionEvent paramCompletionEvent)
  {
    v.p(this.mName, "Unhandled completion event: " + paramCompletionEvent);
  }
  
  /* Error */
  public void onDestroy()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: ldc 31
    //   4: ldc -54
    //   6: invokestatic 82	com/google/android/gms/drive/internal/v:n	(Ljava/lang/String;Ljava/lang/String;)V
    //   9: aload_0
    //   10: getfield 160	com/google/android/gms/drive/events/DriveEventService:NW	Lcom/google/android/gms/drive/events/DriveEventService$a;
    //   13: ifnull +44 -> 57
    //   16: aload_0
    //   17: getfield 160	com/google/android/gms/drive/events/DriveEventService:NW	Lcom/google/android/gms/drive/events/DriveEventService$a;
    //   20: invokestatic 205	com/google/android/gms/drive/events/DriveEventService$a:a	(Lcom/google/android/gms/drive/events/DriveEventService$a;)Landroid/os/Message;
    //   23: astore_1
    //   24: aload_0
    //   25: getfield 160	com/google/android/gms/drive/events/DriveEventService:NW	Lcom/google/android/gms/drive/events/DriveEventService$a;
    //   28: aload_1
    //   29: invokevirtual 209	com/google/android/gms/drive/events/DriveEventService$a:sendMessage	(Landroid/os/Message;)Z
    //   32: pop
    //   33: aload_0
    //   34: aconst_null
    //   35: putfield 160	com/google/android/gms/drive/events/DriveEventService:NW	Lcom/google/android/gms/drive/events/DriveEventService$a;
    //   38: aload_0
    //   39: getfield 113	com/google/android/gms/drive/events/DriveEventService:NV	Ljava/util/concurrent/CountDownLatch;
    //   42: ldc2_w 172
    //   45: getstatic 179	java/util/concurrent/TimeUnit:MILLISECONDS	Ljava/util/concurrent/TimeUnit;
    //   48: invokevirtual 183	java/util/concurrent/CountDownLatch:await	(JLjava/util/concurrent/TimeUnit;)Z
    //   51: pop
    //   52: aload_0
    //   53: aconst_null
    //   54: putfield 113	com/google/android/gms/drive/events/DriveEventService:NV	Ljava/util/concurrent/CountDownLatch;
    //   57: aload_0
    //   58: invokespecial 211	android/app/Service:onDestroy	()V
    //   61: aload_0
    //   62: monitorexit
    //   63: return
    //   64: astore_1
    //   65: aload_0
    //   66: monitorexit
    //   67: aload_1
    //   68: athrow
    //   69: astore_1
    //   70: goto -18 -> 52
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	73	0	this	DriveEventService
    //   23	6	1	localMessage	Message
    //   64	4	1	localObject	Object
    //   69	1	1	localInterruptedException	InterruptedException
    // Exception table:
    //   from	to	target	type
    //   2	38	64	finally
    //   38	52	64	finally
    //   52	57	64	finally
    //   57	61	64	finally
    //   38	52	69	java/lang/InterruptedException
  }
  
  public boolean onUnbind(Intent paramIntent)
  {
    return true;
  }
  
  final class a
    extends Handler
  {
    a() {}
    
    private Message b(OnEventResponse paramOnEventResponse)
    {
      return obtainMessage(1, paramOnEventResponse);
    }
    
    private Message hW()
    {
      return obtainMessage(2);
    }
    
    public void handleMessage(Message paramMessage)
    {
      v.n("DriveEventService", "handleMessage message type:" + paramMessage.what);
      switch (paramMessage.what)
      {
      default: 
        v.p("DriveEventService", "Unexpected message type:" + paramMessage.what);
      }
      for (;;)
      {
        return;
        DriveEventService.a(DriveEventService.this, (OnEventResponse)paramMessage.obj);
        continue;
        getLooper().quit();
      }
    }
  }
  
  final class b
    extends ad.a
  {
    b() {}
    
    public void c(OnEventResponse paramOnEventResponse)
      throws RemoteException
    {
      synchronized (DriveEventService.this)
      {
        StringBuilder localStringBuilder = new java/lang/StringBuilder;
        localStringBuilder.<init>();
        v.n("DriveEventService", "onEvent: " + paramOnEventResponse);
        o.i(DriveEventService.this.NW);
        DriveEventService.a(DriveEventService.this);
        paramOnEventResponse = DriveEventService.a.a(DriveEventService.this.NW, paramOnEventResponse);
        DriveEventService.this.NW.sendMessage(paramOnEventResponse);
        return;
      }
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\drive\events\DriveEventService.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */