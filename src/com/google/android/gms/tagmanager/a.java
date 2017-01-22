package com.google.android.gms.tagmanager;

import android.content.Context;
import android.os.Process;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.internal.ju;
import com.google.android.gms.internal.jw;
import java.io.IOException;

class a
{
  private static a anQ;
  private static Object xz = new Object();
  private volatile long anM = 900000L;
  private volatile long anN = 30000L;
  private volatile long anO;
  private a anP = new a()
  {
    public AdvertisingIdClient.Info nM()
    {
      Object localObject = null;
      try
      {
        AdvertisingIdClient.Info localInfo = AdvertisingIdClient.getAdvertisingIdInfo(a.a(a.this));
        localObject = localInfo;
      }
      catch (IllegalStateException localIllegalStateException)
      {
        for (;;)
        {
          bh.W("IllegalStateException getting Advertising Id Info");
        }
      }
      catch (GooglePlayServicesRepairableException localGooglePlayServicesRepairableException)
      {
        for (;;)
        {
          bh.W("GooglePlayServicesRepairableException getting Advertising Id Info");
        }
      }
      catch (IOException localIOException)
      {
        for (;;)
        {
          bh.W("IOException getting Ad Id Info");
        }
      }
      catch (GooglePlayServicesNotAvailableException localGooglePlayServicesNotAvailableException)
      {
        for (;;)
        {
          bh.W("GooglePlayServicesNotAvailableException getting Advertising Id Info");
        }
      }
      catch (Exception localException)
      {
        for (;;)
        {
          bh.W("Unknown exception. Could not get the Advertising Id Info.");
        }
      }
      return (AdvertisingIdClient.Info)localObject;
    }
  };
  private volatile boolean mClosed = false;
  private final Context mContext;
  private final Thread wf;
  private volatile AdvertisingIdClient.Info xB;
  private final ju yD;
  
  private a(Context paramContext)
  {
    this(paramContext, null, jw.hA());
  }
  
  a(Context paramContext, a parama, ju paramju)
  {
    this.yD = paramju;
    if (paramContext != null) {}
    for (this.mContext = paramContext.getApplicationContext();; this.mContext = paramContext)
    {
      if (parama != null) {
        this.anP = parama;
      }
      this.wf = new Thread(new Runnable()
      {
        public void run()
        {
          a.b(a.this);
        }
      });
      return;
    }
  }
  
  static a W(Context paramContext)
  {
    if (anQ == null) {}
    synchronized (xz)
    {
      if (anQ == null)
      {
        a locala = new com/google/android/gms/tagmanager/a;
        locala.<init>(paramContext);
        anQ = locala;
        anQ.start();
      }
      return anQ;
    }
  }
  
  private void nK()
  {
    Process.setThreadPriority(10);
    while (!this.mClosed) {
      try
      {
        this.xB = this.anP.nM();
        Thread.sleep(this.anM);
      }
      catch (InterruptedException localInterruptedException)
      {
        bh.U("sleep interrupted in AdvertiserDataPoller thread; continuing");
      }
    }
  }
  
  private void nL()
  {
    if (this.yD.currentTimeMillis() - this.anO < this.anN) {}
    for (;;)
    {
      return;
      interrupt();
      this.anO = this.yD.currentTimeMillis();
    }
  }
  
  void interrupt()
  {
    this.wf.interrupt();
  }
  
  public boolean isLimitAdTrackingEnabled()
  {
    nL();
    if (this.xB == null) {}
    for (boolean bool = true;; bool = this.xB.isLimitAdTrackingEnabled()) {
      return bool;
    }
  }
  
  public String nJ()
  {
    nL();
    if (this.xB == null) {}
    for (String str = null;; str = this.xB.getId()) {
      return str;
    }
  }
  
  void start()
  {
    this.wf.start();
  }
  
  public static abstract interface a
  {
    public abstract AdvertisingIdClient.Info nM();
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\tagmanager\a.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */