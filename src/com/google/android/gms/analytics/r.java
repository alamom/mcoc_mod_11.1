package com.google.android.gms.analytics;

import android.content.Context;
import com.google.android.gms.internal.hb;
import com.google.android.gms.internal.ju;
import com.google.android.gms.internal.jw;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingQueue;

class r
  implements af, c.b, c.c
{
  private final Context mContext;
  private boolean yA;
  private boolean yB;
  private boolean yC;
  private ju yD;
  private long yE = 300000L;
  private d yd;
  private final f ye;
  private boolean yg;
  private volatile long yq;
  private volatile a yr;
  private volatile b ys;
  private d yt;
  private final GoogleAnalytics yu;
  private final Queue<d> yv = new ConcurrentLinkedQueue();
  private volatile int yw;
  private volatile Timer yx;
  private volatile Timer yy;
  private volatile Timer yz;
  
  r(Context paramContext, f paramf)
  {
    this(paramContext, paramf, null, GoogleAnalytics.getInstance(paramContext));
  }
  
  r(Context paramContext, f paramf, d paramd, GoogleAnalytics paramGoogleAnalytics)
  {
    this.yt = paramd;
    this.mContext = paramContext;
    this.ye = paramf;
    this.yu = paramGoogleAnalytics;
    this.yD = jw.hA();
    this.yw = 0;
    this.yr = a.yN;
  }
  
  private Timer a(Timer paramTimer)
  {
    if (paramTimer != null) {
      paramTimer.cancel();
    }
    return null;
  }
  
  private void cC()
  {
    try
    {
      if ((this.ys != null) && (this.yr == a.yI))
      {
        this.yr = a.yM;
        this.ys.disconnect();
      }
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  private void ef()
  {
    this.yx = a(this.yx);
    this.yy = a(this.yy);
    this.yz = a(this.yz);
  }
  
  private void eh()
  {
    for (;;)
    {
      Object localObject3;
      try
      {
        Object localObject1;
        if (!Thread.currentThread().equals(this.ye.getThread()))
        {
          localObject1 = this.ye.dO();
          localObject3 = new com/google/android/gms/analytics/r$1;
          ((1)localObject3).<init>(this);
          ((LinkedBlockingQueue)localObject1).add(localObject3);
          return;
        }
        if (this.yA) {
          dH();
        }
        switch (2.yG[this.yr.ordinal()])
        {
        case 3: 
        case 4: 
        case 5: 
        default: 
          break;
        case 1: 
          if (this.yv.isEmpty()) {
            break label214;
          }
          localObject1 = (d)this.yv.poll();
          localObject3 = new java/lang/StringBuilder;
          ((StringBuilder)localObject3).<init>();
          z.V("Sending hit to store  " + localObject1);
          this.yd.a(((d)localObject1).em(), ((d)localObject1).en(), ((d)localObject1).getPath(), ((d)localObject1).eo());
          break;
        case 7: 
          z.V("Blocked. Dropping hits.");
        }
      }
      finally {}
      this.yv.clear();
      continue;
      label214:
      if (this.yg)
      {
        ei();
        continue;
        if (!this.yv.isEmpty())
        {
          localObject3 = (d)this.yv.peek();
          StringBuilder localStringBuilder = new java/lang/StringBuilder;
          localStringBuilder.<init>();
          z.V("Sending hit to service   " + localObject3);
          if (!this.yu.isDryRunEnabled()) {
            this.ys.a(((d)localObject3).em(), ((d)localObject3).en(), ((d)localObject3).getPath(), ((d)localObject3).eo());
          }
          for (;;)
          {
            this.yv.poll();
            break;
            z.V("Dry run enabled. Hit not actually sent to service.");
          }
        }
        this.yq = this.yD.elapsedRealtime();
        continue;
        z.V("Need to reconnect");
        if (!this.yv.isEmpty()) {
          ek();
        }
      }
    }
  }
  
  private void ei()
  {
    this.yd.dispatch();
    this.yg = false;
  }
  
  private void ej()
  {
    try
    {
      a locala2 = this.yr;
      a locala1 = a.yJ;
      if (locala2 == locala1) {}
      for (;;)
      {
        return;
        if ((this.mContext == null) || (!"com.google.android.gms".equals(this.mContext.getPackageName()))) {
          break;
        }
        this.yr = a.yK;
        this.ys.disconnect();
        z.W("Attempted to fall back to local store from service.");
      }
      ef();
    }
    finally {}
    z.V("falling back to local store");
    if (this.yt != null) {}
    q localq;
    for (this.yd = this.yt;; this.yd = localq.ec())
    {
      this.yr = a.yJ;
      eh();
      break;
      localq = q.dZ();
      localq.a(this.mContext, this.ye);
    }
  }
  
  private void ek()
  {
    for (;;)
    {
      try
      {
        if ((!this.yC) && (this.ys != null))
        {
          Object localObject3 = this.yr;
          Object localObject1 = a.yJ;
          if (localObject3 != localObject1) {
            try
            {
              this.yw += 1;
              a(this.yy);
              this.yr = a.yH;
              localObject1 = new java/util/Timer;
              ((Timer)localObject1).<init>("Failed Connect");
              this.yy = ((Timer)localObject1);
              localObject1 = this.yy;
              localObject3 = new com/google/android/gms/analytics/r$c;
              ((c)localObject3).<init>(this, null);
              ((Timer)localObject1).schedule((TimerTask)localObject3, 3000L);
              z.V("connecting to Analytics service");
              this.ys.connect();
              return;
            }
            catch (SecurityException localSecurityException)
            {
              z.W("security exception on connectToService");
              ej();
              continue;
            }
          }
        }
        z.W("client not initialized.");
      }
      finally {}
      ej();
    }
  }
  
  private void el()
  {
    this.yx = a(this.yx);
    this.yx = new Timer("Service Reconnect");
    this.yx.schedule(new e(null), 5000L);
  }
  
  /* Error */
  public void a(int paramInt, android.content.Intent paramIntent)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getstatic 363	com/google/android/gms/analytics/r$a:yL	Lcom/google/android/gms/analytics/r$a;
    //   6: putfield 108	com/google/android/gms/analytics/r:yr	Lcom/google/android/gms/analytics/r$a;
    //   9: aload_0
    //   10: getfield 103	com/google/android/gms/analytics/r:yw	I
    //   13: iconst_2
    //   14: if_icmpge +41 -> 55
    //   17: new 200	java/lang/StringBuilder
    //   20: astore_2
    //   21: aload_2
    //   22: invokespecial 201	java/lang/StringBuilder:<init>	()V
    //   25: aload_2
    //   26: ldc_w 365
    //   29: invokevirtual 207	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   32: iload_1
    //   33: invokevirtual 368	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   36: ldc_w 370
    //   39: invokevirtual 207	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   42: invokevirtual 214	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   45: invokestatic 298	com/google/android/gms/analytics/z:W	(Ljava/lang/String;)V
    //   48: aload_0
    //   49: invokespecial 372	com/google/android/gms/analytics/r:el	()V
    //   52: aload_0
    //   53: monitorexit
    //   54: return
    //   55: new 200	java/lang/StringBuilder
    //   58: astore_2
    //   59: aload_2
    //   60: invokespecial 201	java/lang/StringBuilder:<init>	()V
    //   63: aload_2
    //   64: ldc_w 365
    //   67: invokevirtual 207	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   70: iload_1
    //   71: invokevirtual 368	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   74: ldc_w 374
    //   77: invokevirtual 207	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   80: invokevirtual 214	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   83: invokestatic 298	com/google/android/gms/analytics/z:W	(Ljava/lang/String;)V
    //   86: aload_0
    //   87: invokespecial 122	com/google/android/gms/analytics/r:ej	()V
    //   90: goto -38 -> 52
    //   93: astore_2
    //   94: aload_0
    //   95: monitorexit
    //   96: aload_2
    //   97: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	98	0	this	r
    //   0	98	1	paramInt	int
    //   0	98	2	paramIntent	android.content.Intent
    // Exception table:
    //   from	to	target	type
    //   2	52	93	finally
    //   55	90	93	finally
  }
  
  public void b(Map<String, String> paramMap, long paramLong, String paramString, List<hb> paramList)
  {
    z.V("putHit called");
    this.yv.add(new d(paramMap, paramLong, paramString, paramList));
    eh();
  }
  
  public void dH()
  {
    z.V("clearHits called");
    this.yv.clear();
    switch (2.yG[this.yr.ordinal()])
    {
    default: 
      this.yA = true;
    }
    for (;;)
    {
      return;
      this.yd.l(0L);
      this.yA = false;
      continue;
      this.ys.dH();
      this.yA = false;
    }
  }
  
  public void dN()
  {
    for (;;)
    {
      try
      {
        boolean bool = this.yC;
        if (bool) {
          return;
        }
        z.V("setForceLocalDispatch called.");
        this.yC = true;
        switch (2.yG[this.yr.ordinal()])
        {
        case 1: 
        case 4: 
        case 5: 
        case 6: 
        default: 
          break;
        case 2: 
          cC();
          break;
        case 3: 
          this.yB = true;
        }
      }
      finally {}
    }
  }
  
  public void dispatch()
  {
    switch (2.yG[this.yr.ordinal()])
    {
    default: 
      this.yg = true;
    }
    for (;;)
    {
      return;
      ei();
    }
  }
  
  public void eg()
  {
    if (this.ys != null) {}
    for (;;)
    {
      return;
      this.ys = new c(this.mContext, this, this);
      ek();
    }
  }
  
  /* Error */
  public void onConnected()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: aload_0
    //   4: aload_0
    //   5: getfield 147	com/google/android/gms/analytics/r:yy	Ljava/util/Timer;
    //   8: invokespecial 145	com/google/android/gms/analytics/r:a	(Ljava/util/Timer;)Ljava/util/Timer;
    //   11: putfield 147	com/google/android/gms/analytics/r:yy	Ljava/util/Timer;
    //   14: aload_0
    //   15: iconst_0
    //   16: putfield 103	com/google/android/gms/analytics/r:yw	I
    //   19: ldc_w 402
    //   22: invokestatic 220	com/google/android/gms/analytics/z:V	(Ljava/lang/String;)V
    //   25: aload_0
    //   26: getstatic 128	com/google/android/gms/analytics/r$a:yI	Lcom/google/android/gms/analytics/r$a;
    //   29: putfield 108	com/google/android/gms/analytics/r:yr	Lcom/google/android/gms/analytics/r$a;
    //   32: aload_0
    //   33: getfield 393	com/google/android/gms/analytics/r:yB	Z
    //   36: ifeq +15 -> 51
    //   39: aload_0
    //   40: invokespecial 357	com/google/android/gms/analytics/r:cC	()V
    //   43: aload_0
    //   44: iconst_0
    //   45: putfield 393	com/google/android/gms/analytics/r:yB	Z
    //   48: aload_0
    //   49: monitorexit
    //   50: return
    //   51: aload_0
    //   52: invokespecial 118	com/google/android/gms/analytics/r:eh	()V
    //   55: aload_0
    //   56: aload_0
    //   57: aload_0
    //   58: getfield 149	com/google/android/gms/analytics/r:yz	Ljava/util/Timer;
    //   61: invokespecial 145	com/google/android/gms/analytics/r:a	(Ljava/util/Timer;)Ljava/util/Timer;
    //   64: putfield 149	com/google/android/gms/analytics/r:yz	Ljava/util/Timer;
    //   67: new 111	java/util/Timer
    //   70: astore_1
    //   71: aload_1
    //   72: ldc_w 404
    //   75: invokespecial 325	java/util/Timer:<init>	(Ljava/lang/String;)V
    //   78: aload_0
    //   79: aload_1
    //   80: putfield 149	com/google/android/gms/analytics/r:yz	Ljava/util/Timer;
    //   83: aload_0
    //   84: getfield 149	com/google/android/gms/analytics/r:yz	Ljava/util/Timer;
    //   87: astore_2
    //   88: new 19	com/google/android/gms/analytics/r$b
    //   91: astore_1
    //   92: aload_1
    //   93: aload_0
    //   94: aconst_null
    //   95: invokespecial 405	com/google/android/gms/analytics/r$b:<init>	(Lcom/google/android/gms/analytics/r;Lcom/google/android/gms/analytics/r$1;)V
    //   98: aload_2
    //   99: aload_1
    //   100: aload_0
    //   101: getfield 85	com/google/android/gms/analytics/r:yE	J
    //   104: invokevirtual 334	java/util/Timer:schedule	(Ljava/util/TimerTask;J)V
    //   107: goto -59 -> 48
    //   110: astore_1
    //   111: aload_0
    //   112: monitorexit
    //   113: aload_1
    //   114: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	115	0	this	r
    //   70	30	1	localObject1	Object
    //   110	4	1	localObject2	Object
    //   87	12	2	localTimer	Timer
    // Exception table:
    //   from	to	target	type
    //   2	48	110	finally
    //   51	107	110	finally
  }
  
  public void onDisconnected()
  {
    for (;;)
    {
      try
      {
        if (this.yr == a.yK)
        {
          z.V("Service blocked.");
          ef();
          return;
        }
        if (this.yr == a.yM)
        {
          z.V("Disconnected from service");
          ef();
          this.yr = a.yN;
          continue;
        }
        z.V("Unexpected disconnect.");
      }
      finally {}
      this.yr = a.yL;
      if (this.yw < 2) {
        el();
      } else {
        ej();
      }
    }
  }
  
  private static enum a
  {
    private a() {}
  }
  
  private class b
    extends TimerTask
  {
    private b() {}
    
    public void run()
    {
      if ((r.b(r.this) == r.a.yI) && (r.e(r.this).isEmpty()) && (r.f(r.this) + r.g(r.this) < r.h(r.this).elapsedRealtime()))
      {
        z.V("Disconnecting due to inactivity");
        r.i(r.this);
      }
      for (;;)
      {
        return;
        r.j(r.this).schedule(new b(r.this), r.g(r.this));
      }
    }
  }
  
  private class c
    extends TimerTask
  {
    private c() {}
    
    public void run()
    {
      if (r.b(r.this) == r.a.yH) {
        r.c(r.this);
      }
    }
  }
  
  private static class d
  {
    private final Map<String, String> yP;
    private final long yQ;
    private final String yR;
    private final List<hb> yS;
    
    public d(Map<String, String> paramMap, long paramLong, String paramString, List<hb> paramList)
    {
      this.yP = paramMap;
      this.yQ = paramLong;
      this.yR = paramString;
      this.yS = paramList;
    }
    
    public Map<String, String> em()
    {
      return this.yP;
    }
    
    public long en()
    {
      return this.yQ;
    }
    
    public List<hb> eo()
    {
      return this.yS;
    }
    
    public String getPath()
    {
      return this.yR;
    }
    
    public String toString()
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("PATH: ");
      localStringBuilder.append(this.yR);
      if (this.yP != null)
      {
        localStringBuilder.append("  PARAMS: ");
        Iterator localIterator = this.yP.entrySet().iterator();
        while (localIterator.hasNext())
        {
          Map.Entry localEntry = (Map.Entry)localIterator.next();
          localStringBuilder.append((String)localEntry.getKey());
          localStringBuilder.append("=");
          localStringBuilder.append((String)localEntry.getValue());
          localStringBuilder.append(",  ");
        }
      }
      return localStringBuilder.toString();
    }
  }
  
  private class e
    extends TimerTask
  {
    private e() {}
    
    public void run()
    {
      r.d(r.this);
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\analytics\r.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */