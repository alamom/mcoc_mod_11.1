package com.google.android.gms.common.api;

import android.content.Context;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.f;
import com.google.android.gms.common.internal.f.b;
import com.google.android.gms.common.internal.o;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

final class b
  implements GoogleApiClient
{
  private final Looper IH;
  final int IU;
  private final Lock IV = new ReentrantLock();
  private final Condition IW = this.IV.newCondition();
  private final f IX = new f(paramContext, paramLooper, this.Jp);
  private final int IY;
  final Queue<c<?>> IZ = new LinkedList();
  private final a Iz = new a()
  {
    public void b(b.c<?> paramAnonymousc)
    {
      b.this.Jn.remove(paramAnonymousc);
    }
  };
  private ConnectionResult Ja;
  private int Jb;
  private volatile int Jc = 4;
  private volatile int Jd;
  private boolean Je = false;
  private int Jf;
  private long Jg = 5000L;
  final Handler Jh;
  private final Bundle Ji = new Bundle();
  private final Map<Api.c<?>, Api.a> Jj = new HashMap();
  private final List<String> Jk;
  private boolean Jl;
  private final Set<c<?>> Jm = Collections.newSetFromMap(new WeakHashMap());
  final Set<c<?>> Jn = Collections.newSetFromMap(new ConcurrentHashMap());
  private final GoogleApiClient.ConnectionCallbacks Jo = new GoogleApiClient.ConnectionCallbacks()
  {
    public void onConnected(Bundle paramAnonymousBundle)
    {
      b.a(b.this).lock();
      try
      {
        if (b.b(b.this) == 1)
        {
          if (paramAnonymousBundle != null) {
            b.c(b.this).putAll(paramAnonymousBundle);
          }
          b.d(b.this);
        }
        return;
      }
      finally
      {
        b.a(b.this).unlock();
      }
    }
    
    public void onConnectionSuspended(int paramAnonymousInt)
    {
      b.a(b.this).lock();
      for (;;)
      {
        try
        {
          b.a(b.this, paramAnonymousInt);
          switch (paramAnonymousInt)
          {
          default: 
            return;
          }
        }
        finally
        {
          b.a(b.this).unlock();
        }
        b.this.connect();
        continue;
        boolean bool = b.e(b.this);
        if (bool)
        {
          b.a(b.this).unlock();
        }
        else
        {
          b.b(b.this, b.this.IU);
          b.this.Jh.sendMessageDelayed(b.this.Jh.obtainMessage(1), b.f(b.this));
        }
      }
    }
  };
  private final f.b Jp = new f.b()
  {
    public Bundle fC()
    {
      return null;
    }
    
    public boolean gq()
    {
      return b.g(b.this);
    }
    
    public boolean isConnected()
    {
      return b.this.isConnected();
    }
  };
  
  public b(Context paramContext, Looper paramLooper, ClientSettings paramClientSettings, Map<Api<?>, Api.ApiOptions> paramMap, Set<GoogleApiClient.ConnectionCallbacks> paramSet, Set<GoogleApiClient.OnConnectionFailedListener> paramSet1, int paramInt1, int paramInt2)
  {
    this.IH = paramLooper;
    this.Jh = new b(paramLooper);
    this.IY = paramInt1;
    this.IU = paramInt2;
    paramSet = paramSet.iterator();
    final Object localObject1;
    while (paramSet.hasNext())
    {
      localObject1 = (GoogleApiClient.ConnectionCallbacks)paramSet.next();
      this.IX.registerConnectionCallbacks((GoogleApiClient.ConnectionCallbacks)localObject1);
    }
    paramSet = paramSet1.iterator();
    while (paramSet.hasNext())
    {
      paramSet1 = (GoogleApiClient.OnConnectionFailedListener)paramSet.next();
      this.IX.registerConnectionFailedListener(paramSet1);
    }
    paramSet = paramMap.keySet().iterator();
    while (paramSet.hasNext())
    {
      paramSet1 = (Api)paramSet.next();
      localObject1 = paramSet1.gb();
      Object localObject2 = paramMap.get(paramSet1);
      this.Jj.put(paramSet1.ge(), a((Api.b)localObject1, localObject2, paramContext, paramLooper, paramClientSettings, this.Jo, new GoogleApiClient.OnConnectionFailedListener()
      {
        public void onConnectionFailed(ConnectionResult paramAnonymousConnectionResult)
        {
          b.a(b.this).lock();
          try
          {
            if ((b.i(b.this) == null) || (localObject1.getPriority() < b.j(b.this)))
            {
              b.a(b.this, paramAnonymousConnectionResult);
              b.c(b.this, localObject1.getPriority());
            }
            b.d(b.this);
            return;
          }
          finally
          {
            b.a(b.this).unlock();
          }
        }
      }));
    }
    this.Jk = Collections.unmodifiableList(paramClientSettings.getScopes());
  }
  
  private static <C extends Api.a, O> C a(Api.b<C, O> paramb, Object paramObject, Context paramContext, Looper paramLooper, ClientSettings paramClientSettings, GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener)
  {
    return paramb.a(paramContext, paramLooper, paramClientSettings, paramObject, paramConnectionCallbacks, paramOnConnectionFailedListener);
  }
  
  /* Error */
  private <A extends Api.a> void a(c<A> paramc)
    throws DeadObjectException
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 79	com/google/android/gms/common/api/b:IV	Ljava/util/concurrent/locks/Lock;
    //   4: invokeinterface 244 1 0
    //   9: aload_1
    //   10: invokeinterface 245 1 0
    //   15: ifnull +66 -> 81
    //   18: iconst_1
    //   19: istore_2
    //   20: iload_2
    //   21: ldc -9
    //   23: invokestatic 252	com/google/android/gms/common/internal/o:b	(ZLjava/lang/Object;)V
    //   26: aload_0
    //   27: getfield 126	com/google/android/gms/common/api/b:Jn	Ljava/util/Set;
    //   30: aload_1
    //   31: invokeinterface 256 2 0
    //   36: pop
    //   37: aload_1
    //   38: aload_0
    //   39: getfield 131	com/google/android/gms/common/api/b:Iz	Lcom/google/android/gms/common/api/b$a;
    //   42: invokeinterface 259 2 0
    //   47: aload_0
    //   48: invokespecial 262	com/google/android/gms/common/api/b:go	()Z
    //   51: ifeq +35 -> 86
    //   54: new 264	com/google/android/gms/common/api/Status
    //   57: astore_3
    //   58: aload_3
    //   59: bipush 8
    //   61: invokespecial 267	com/google/android/gms/common/api/Status:<init>	(I)V
    //   64: aload_1
    //   65: aload_3
    //   66: invokeinterface 271 2 0
    //   71: aload_0
    //   72: getfield 79	com/google/android/gms/common/api/b:IV	Ljava/util/concurrent/locks/Lock;
    //   75: invokeinterface 274 1 0
    //   80: return
    //   81: iconst_0
    //   82: istore_2
    //   83: goto -63 -> 20
    //   86: aload_1
    //   87: aload_0
    //   88: aload_1
    //   89: invokeinterface 245 1 0
    //   94: invokevirtual 277	com/google/android/gms/common/api/b:a	(Lcom/google/android/gms/common/api/Api$c;)Lcom/google/android/gms/common/api/Api$a;
    //   97: invokeinterface 280 2 0
    //   102: aload_0
    //   103: getfield 79	com/google/android/gms/common/api/b:IV	Ljava/util/concurrent/locks/Lock;
    //   106: invokeinterface 274 1 0
    //   111: goto -31 -> 80
    //   114: astore_1
    //   115: aload_0
    //   116: getfield 79	com/google/android/gms/common/api/b:IV	Ljava/util/concurrent/locks/Lock;
    //   119: invokeinterface 274 1 0
    //   124: aload_1
    //   125: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	126	0	this	b
    //   0	126	1	paramc	c<A>
    //   19	64	2	bool	boolean
    //   57	9	3	localStatus	Status
    // Exception table:
    //   from	to	target	type
    //   9	18	114	finally
    //   20	71	114	finally
    //   86	102	114	finally
  }
  
  private void aj(int paramInt)
  {
    this.IV.lock();
    Object localObject3;
    try
    {
      if (this.Jc == 3) {
        break label374;
      }
      if (paramInt != -1) {
        break label241;
      }
      if (isConnecting())
      {
        Iterator localIterator = this.IZ.iterator();
        while (localIterator.hasNext())
        {
          localObject3 = (c)localIterator.next();
          if (((c)localObject3).gj() != 1)
          {
            ((c)localObject3).cancel();
            localIterator.remove();
          }
        }
      }
      this.IZ.clear();
    }
    finally
    {
      this.IV.unlock();
    }
    Object localObject2 = this.Jn.iterator();
    while (((Iterator)localObject2).hasNext()) {
      ((c)((Iterator)localObject2).next()).cancel();
    }
    this.Jn.clear();
    localObject2 = this.Jm.iterator();
    while (((Iterator)localObject2).hasNext()) {
      ((c)((Iterator)localObject2).next()).clear();
    }
    this.Jm.clear();
    if ((this.Ja == null) && (!this.IZ.isEmpty()))
    {
      this.Je = true;
      this.IV.unlock();
    }
    for (;;)
    {
      return;
      label241:
      boolean bool2 = isConnecting();
      boolean bool1 = isConnected();
      this.Jc = 3;
      if (bool2)
      {
        if (paramInt == -1) {
          this.Ja = null;
        }
        this.IW.signalAll();
      }
      this.Jl = false;
      localObject3 = this.Jj.values().iterator();
      while (((Iterator)localObject3).hasNext())
      {
        localObject2 = (Api.a)((Iterator)localObject3).next();
        if (((Api.a)localObject2).isConnected()) {
          ((Api.a)localObject2).disconnect();
        }
      }
      this.Jl = true;
      this.Jc = 4;
      if (bool1)
      {
        if (paramInt != -1) {
          this.IX.aB(paramInt);
        }
        this.Jl = false;
      }
      label374:
      this.IV.unlock();
    }
  }
  
  private void gm()
  {
    this.Jf -= 1;
    if (this.Jf == 0)
    {
      if (this.Ja == null) {
        break label81;
      }
      this.Je = false;
      aj(3);
      if (!go()) {
        break label67;
      }
      this.Jh.sendMessageDelayed(this.Jh.obtainMessage(1), this.Jg);
      this.Jl = false;
    }
    for (;;)
    {
      return;
      label67:
      this.IX.b(this.Ja);
      break;
      label81:
      this.Jc = 2;
      gp();
      this.IW.signalAll();
      gn();
      if (!this.Je) {
        break label123;
      }
      this.Je = false;
      aj(-1);
    }
    label123:
    if (this.Ji.isEmpty()) {}
    for (Bundle localBundle = null;; localBundle = this.Ji)
    {
      this.IX.d(localBundle);
      break;
    }
  }
  
  private void gn()
  {
    this.IV.lock();
    for (;;)
    {
      try
      {
        if ((isConnected()) || (go()))
        {
          bool = true;
          o.a(bool, "GoogleApiClient is not connected yet.");
          bool = this.IZ.isEmpty();
          if (bool) {
            break;
          }
          try
          {
            a((c)this.IZ.remove());
          }
          catch (DeadObjectException localDeadObjectException)
          {
            Log.w("GoogleApiClientImpl", "Service died while flushing queue", localDeadObjectException);
          }
          continue;
        }
        boolean bool = false;
      }
      finally
      {
        this.IV.unlock();
      }
    }
    this.IV.unlock();
  }
  
  private boolean go()
  {
    if (this.Jd != 0) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  private void gp()
  {
    this.IV.lock();
    try
    {
      this.Jd = 0;
      this.Jh.removeMessages(1);
      return;
    }
    finally
    {
      this.IV.unlock();
    }
  }
  
  public <C extends Api.a> C a(Api.c<C> paramc)
  {
    paramc = (Api.a)this.Jj.get(paramc);
    o.b(paramc, "Appropriate Api was not requested.");
    return paramc;
  }
  
  /* Error */
  public <A extends Api.a, R extends Result, T extends BaseImplementation.a<R, A>> T a(T paramT)
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 79	com/google/android/gms/common/api/b:IV	Ljava/util/concurrent/locks/Lock;
    //   4: invokeinterface 244 1 0
    //   9: new 412	com/google/android/gms/common/api/BaseImplementation$CallbackHandler
    //   12: astore_2
    //   13: aload_2
    //   14: aload_0
    //   15: invokevirtual 416	com/google/android/gms/common/api/b:getLooper	()Landroid/os/Looper;
    //   18: invokespecial 419	com/google/android/gms/common/api/BaseImplementation$CallbackHandler:<init>	(Landroid/os/Looper;)V
    //   21: aload_1
    //   22: aload_2
    //   23: invokevirtual 424	com/google/android/gms/common/api/BaseImplementation$a:a	(Lcom/google/android/gms/common/api/BaseImplementation$CallbackHandler;)V
    //   26: aload_0
    //   27: invokevirtual 315	com/google/android/gms/common/api/b:isConnected	()Z
    //   30: ifeq +20 -> 50
    //   33: aload_0
    //   34: aload_1
    //   35: invokevirtual 426	com/google/android/gms/common/api/b:b	(Lcom/google/android/gms/common/api/BaseImplementation$a;)Lcom/google/android/gms/common/api/BaseImplementation$a;
    //   38: pop
    //   39: aload_0
    //   40: getfield 79	com/google/android/gms/common/api/b:IV	Ljava/util/concurrent/locks/Lock;
    //   43: invokeinterface 274 1 0
    //   48: aload_1
    //   49: areturn
    //   50: aload_0
    //   51: getfield 92	com/google/android/gms/common/api/b:IZ	Ljava/util/Queue;
    //   54: aload_1
    //   55: invokeinterface 427 2 0
    //   60: pop
    //   61: goto -22 -> 39
    //   64: astore_1
    //   65: aload_0
    //   66: getfield 79	com/google/android/gms/common/api/b:IV	Ljava/util/concurrent/locks/Lock;
    //   69: invokeinterface 274 1 0
    //   74: aload_1
    //   75: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	76	0	this	b
    //   0	76	1	paramT	T
    //   12	11	2	localCallbackHandler	BaseImplementation.CallbackHandler
    // Exception table:
    //   from	to	target	type
    //   9	39	64	finally
    //   50	61	64	finally
  }
  
  public boolean a(Scope paramScope)
  {
    return this.Jk.contains(paramScope.gs());
  }
  
  public <A extends Api.a, T extends BaseImplementation.a<? extends Result, A>> T b(T paramT)
  {
    boolean bool;
    if ((isConnected()) || (go())) {
      bool = true;
    }
    for (;;)
    {
      o.a(bool, "GoogleApiClient is not connected yet.");
      gn();
      try
      {
        a(paramT);
        return paramT;
        bool = false;
      }
      catch (DeadObjectException localDeadObjectException)
      {
        for (;;)
        {
          aj(1);
        }
      }
    }
  }
  
  /* Error */
  public ConnectionResult blockingConnect()
  {
    // Byte code:
    //   0: invokestatic 450	android/os/Looper:myLooper	()Landroid/os/Looper;
    //   3: invokestatic 453	android/os/Looper:getMainLooper	()Landroid/os/Looper;
    //   6: if_acmpeq +75 -> 81
    //   9: iconst_1
    //   10: istore_1
    //   11: iload_1
    //   12: ldc_w 455
    //   15: invokestatic 383	com/google/android/gms/common/internal/o:a	(ZLjava/lang/Object;)V
    //   18: aload_0
    //   19: getfield 79	com/google/android/gms/common/api/b:IV	Ljava/util/concurrent/locks/Lock;
    //   22: invokeinterface 244 1 0
    //   27: aload_0
    //   28: invokevirtual 458	com/google/android/gms/common/api/b:connect	()V
    //   31: aload_0
    //   32: invokevirtual 289	com/google/android/gms/common/api/b:isConnecting	()Z
    //   35: istore_1
    //   36: iload_1
    //   37: ifeq +49 -> 86
    //   40: aload_0
    //   41: getfield 87	com/google/android/gms/common/api/b:IW	Ljava/util/concurrent/locks/Condition;
    //   44: invokeinterface 461 1 0
    //   49: goto -18 -> 31
    //   52: astore_2
    //   53: invokestatic 467	java/lang/Thread:currentThread	()Ljava/lang/Thread;
    //   56: invokevirtual 470	java/lang/Thread:interrupt	()V
    //   59: new 472	com/google/android/gms/common/ConnectionResult
    //   62: dup
    //   63: bipush 15
    //   65: aconst_null
    //   66: invokespecial 475	com/google/android/gms/common/ConnectionResult:<init>	(ILandroid/app/PendingIntent;)V
    //   69: astore_2
    //   70: aload_0
    //   71: getfield 79	com/google/android/gms/common/api/b:IV	Ljava/util/concurrent/locks/Lock;
    //   74: invokeinterface 274 1 0
    //   79: aload_2
    //   80: areturn
    //   81: iconst_0
    //   82: istore_1
    //   83: goto -72 -> 11
    //   86: aload_0
    //   87: invokevirtual 315	com/google/android/gms/common/api/b:isConnected	()Z
    //   90: ifeq +19 -> 109
    //   93: getstatic 478	com/google/android/gms/common/ConnectionResult:HE	Lcom/google/android/gms/common/ConnectionResult;
    //   96: astore_2
    //   97: aload_0
    //   98: getfield 79	com/google/android/gms/common/api/b:IV	Ljava/util/concurrent/locks/Lock;
    //   101: invokeinterface 274 1 0
    //   106: goto -27 -> 79
    //   109: aload_0
    //   110: getfield 231	com/google/android/gms/common/api/b:Ja	Lcom/google/android/gms/common/ConnectionResult;
    //   113: ifnull +20 -> 133
    //   116: aload_0
    //   117: getfield 231	com/google/android/gms/common/api/b:Ja	Lcom/google/android/gms/common/ConnectionResult;
    //   120: astore_2
    //   121: aload_0
    //   122: getfield 79	com/google/android/gms/common/api/b:IV	Ljava/util/concurrent/locks/Lock;
    //   125: invokeinterface 274 1 0
    //   130: goto -51 -> 79
    //   133: new 472	com/google/android/gms/common/ConnectionResult
    //   136: dup
    //   137: bipush 13
    //   139: aconst_null
    //   140: invokespecial 475	com/google/android/gms/common/ConnectionResult:<init>	(ILandroid/app/PendingIntent;)V
    //   143: astore_2
    //   144: aload_0
    //   145: getfield 79	com/google/android/gms/common/api/b:IV	Ljava/util/concurrent/locks/Lock;
    //   148: invokeinterface 274 1 0
    //   153: goto -74 -> 79
    //   156: astore_2
    //   157: aload_0
    //   158: getfield 79	com/google/android/gms/common/api/b:IV	Ljava/util/concurrent/locks/Lock;
    //   161: invokeinterface 274 1 0
    //   166: aload_2
    //   167: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	168	0	this	b
    //   10	73	1	bool	boolean
    //   52	1	2	localInterruptedException	InterruptedException
    //   69	75	2	localConnectionResult	ConnectionResult
    //   156	11	2	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   40	49	52	java/lang/InterruptedException
    //   27	31	156	finally
    //   31	36	156	finally
    //   40	49	156	finally
    //   53	70	156	finally
    //   86	97	156	finally
    //   109	121	156	finally
    //   133	144	156	finally
  }
  
  /* Error */
  public ConnectionResult blockingConnect(long paramLong, java.util.concurrent.TimeUnit paramTimeUnit)
  {
    // Byte code:
    //   0: invokestatic 450	android/os/Looper:myLooper	()Landroid/os/Looper;
    //   3: invokestatic 453	android/os/Looper:getMainLooper	()Landroid/os/Looper;
    //   6: if_acmpeq +88 -> 94
    //   9: iconst_1
    //   10: istore 4
    //   12: iload 4
    //   14: ldc_w 455
    //   17: invokestatic 383	com/google/android/gms/common/internal/o:a	(ZLjava/lang/Object;)V
    //   20: aload_0
    //   21: getfield 79	com/google/android/gms/common/api/b:IV	Ljava/util/concurrent/locks/Lock;
    //   24: invokeinterface 244 1 0
    //   29: aload_0
    //   30: invokevirtual 458	com/google/android/gms/common/api/b:connect	()V
    //   33: aload_3
    //   34: lload_1
    //   35: invokevirtual 485	java/util/concurrent/TimeUnit:toNanos	(J)J
    //   38: lstore_1
    //   39: aload_0
    //   40: invokevirtual 289	com/google/android/gms/common/api/b:isConnecting	()Z
    //   43: istore 4
    //   45: iload 4
    //   47: ifeq +83 -> 130
    //   50: aload_0
    //   51: getfield 87	com/google/android/gms/common/api/b:IW	Ljava/util/concurrent/locks/Condition;
    //   54: lload_1
    //   55: invokeinterface 488 3 0
    //   60: lstore 5
    //   62: lload 5
    //   64: lstore_1
    //   65: lload 5
    //   67: lconst_0
    //   68: lcmp
    //   69: ifgt -30 -> 39
    //   72: new 472	com/google/android/gms/common/ConnectionResult
    //   75: astore_3
    //   76: aload_3
    //   77: bipush 14
    //   79: aconst_null
    //   80: invokespecial 475	com/google/android/gms/common/ConnectionResult:<init>	(ILandroid/app/PendingIntent;)V
    //   83: aload_0
    //   84: getfield 79	com/google/android/gms/common/api/b:IV	Ljava/util/concurrent/locks/Lock;
    //   87: invokeinterface 274 1 0
    //   92: aload_3
    //   93: areturn
    //   94: iconst_0
    //   95: istore 4
    //   97: goto -85 -> 12
    //   100: astore_3
    //   101: invokestatic 467	java/lang/Thread:currentThread	()Ljava/lang/Thread;
    //   104: invokevirtual 470	java/lang/Thread:interrupt	()V
    //   107: new 472	com/google/android/gms/common/ConnectionResult
    //   110: dup
    //   111: bipush 15
    //   113: aconst_null
    //   114: invokespecial 475	com/google/android/gms/common/ConnectionResult:<init>	(ILandroid/app/PendingIntent;)V
    //   117: astore_3
    //   118: aload_0
    //   119: getfield 79	com/google/android/gms/common/api/b:IV	Ljava/util/concurrent/locks/Lock;
    //   122: invokeinterface 274 1 0
    //   127: goto -35 -> 92
    //   130: aload_0
    //   131: invokevirtual 315	com/google/android/gms/common/api/b:isConnected	()Z
    //   134: ifeq +19 -> 153
    //   137: getstatic 478	com/google/android/gms/common/ConnectionResult:HE	Lcom/google/android/gms/common/ConnectionResult;
    //   140: astore_3
    //   141: aload_0
    //   142: getfield 79	com/google/android/gms/common/api/b:IV	Ljava/util/concurrent/locks/Lock;
    //   145: invokeinterface 274 1 0
    //   150: goto -58 -> 92
    //   153: aload_0
    //   154: getfield 231	com/google/android/gms/common/api/b:Ja	Lcom/google/android/gms/common/ConnectionResult;
    //   157: ifnull +20 -> 177
    //   160: aload_0
    //   161: getfield 231	com/google/android/gms/common/api/b:Ja	Lcom/google/android/gms/common/ConnectionResult;
    //   164: astore_3
    //   165: aload_0
    //   166: getfield 79	com/google/android/gms/common/api/b:IV	Ljava/util/concurrent/locks/Lock;
    //   169: invokeinterface 274 1 0
    //   174: goto -82 -> 92
    //   177: new 472	com/google/android/gms/common/ConnectionResult
    //   180: dup
    //   181: bipush 13
    //   183: aconst_null
    //   184: invokespecial 475	com/google/android/gms/common/ConnectionResult:<init>	(ILandroid/app/PendingIntent;)V
    //   187: astore_3
    //   188: aload_0
    //   189: getfield 79	com/google/android/gms/common/api/b:IV	Ljava/util/concurrent/locks/Lock;
    //   192: invokeinterface 274 1 0
    //   197: goto -105 -> 92
    //   200: astore_3
    //   201: aload_0
    //   202: getfield 79	com/google/android/gms/common/api/b:IV	Ljava/util/concurrent/locks/Lock;
    //   205: invokeinterface 274 1 0
    //   210: aload_3
    //   211: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	212	0	this	b
    //   0	212	1	paramLong	long
    //   0	212	3	paramTimeUnit	java.util.concurrent.TimeUnit
    //   10	86	4	bool	boolean
    //   60	6	5	l	long
    // Exception table:
    //   from	to	target	type
    //   50	62	100	java/lang/InterruptedException
    //   72	83	100	java/lang/InterruptedException
    //   29	39	200	finally
    //   39	45	200	finally
    //   50	62	200	finally
    //   72	83	200	finally
    //   101	118	200	finally
    //   130	141	200	finally
    //   153	165	200	finally
    //   177	188	200	finally
  }
  
  public <L> c<L> c(L paramL)
  {
    o.b(paramL, "Listener must not be null");
    this.IV.lock();
    try
    {
      c localc = new com/google/android/gms/common/api/c;
      localc.<init>(this.IH, paramL);
      this.Jm.add(localc);
      return localc;
    }
    finally
    {
      this.IV.unlock();
    }
  }
  
  public void connect()
  {
    this.IV.lock();
    for (;;)
    {
      try
      {
        this.Je = false;
        if (!isConnected())
        {
          boolean bool = isConnecting();
          if (!bool) {}
        }
        else
        {
          return;
        }
        this.Jl = true;
        this.Ja = null;
        this.Jc = 1;
        this.Ji.clear();
        this.Jf = this.Jj.size();
        Iterator localIterator = this.Jj.values().iterator();
        if (localIterator.hasNext()) {
          ((Api.a)localIterator.next()).connect();
        }
      }
      finally
      {
        this.IV.unlock();
      }
    }
  }
  
  public void disconnect()
  {
    gp();
    aj(-1);
  }
  
  public Looper getLooper()
  {
    return this.IH;
  }
  
  public boolean isConnected()
  {
    if (this.Jc == 2) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public boolean isConnecting()
  {
    boolean bool = true;
    if (this.Jc == 1) {}
    for (;;)
    {
      return bool;
      bool = false;
    }
  }
  
  public boolean isConnectionCallbacksRegistered(GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks)
  {
    return this.IX.isConnectionCallbacksRegistered(paramConnectionCallbacks);
  }
  
  public boolean isConnectionFailedListenerRegistered(GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener)
  {
    return this.IX.isConnectionFailedListenerRegistered(paramOnConnectionFailedListener);
  }
  
  public void reconnect()
  {
    disconnect();
    connect();
  }
  
  public void registerConnectionCallbacks(GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks)
  {
    this.IX.registerConnectionCallbacks(paramConnectionCallbacks);
  }
  
  public void registerConnectionFailedListener(GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener)
  {
    this.IX.registerConnectionFailedListener(paramOnConnectionFailedListener);
  }
  
  public void stopAutoManage(FragmentActivity paramFragmentActivity)
  {
    if (this.IY >= 0) {}
    for (boolean bool = true;; bool = false)
    {
      o.a(bool, "Called stopAutoManage but automatic lifecycle management is not enabled.");
      d.a(paramFragmentActivity).al(this.IY);
      return;
    }
  }
  
  public void unregisterConnectionCallbacks(GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks)
  {
    this.IX.unregisterConnectionCallbacks(paramConnectionCallbacks);
  }
  
  public void unregisterConnectionFailedListener(GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener)
  {
    this.IX.unregisterConnectionFailedListener(paramOnConnectionFailedListener);
  }
  
  static abstract interface a
  {
    public abstract void b(b.c<?> paramc);
  }
  
  class b
    extends Handler
  {
    b(Looper paramLooper)
    {
      super();
    }
    
    public void handleMessage(Message paramMessage)
    {
      if (paramMessage.what == 1) {
        b.a(b.this).lock();
      }
      for (;;)
      {
        try
        {
          if ((!b.this.isConnected()) && (!b.this.isConnecting()))
          {
            boolean bool = b.e(b.this);
            if (bool) {}
          }
          else
          {
            return;
          }
          b.h(b.this);
          b.this.connect();
          b.a(b.this).unlock();
          continue;
          Log.wtf("GoogleApiClientImpl", "Don't know how to handle this message.");
        }
        finally
        {
          b.a(b.this).unlock();
        }
      }
    }
  }
  
  static abstract interface c<A extends Api.a>
  {
    public abstract void a(b.a parama);
    
    public abstract void b(A paramA)
      throws DeadObjectException;
    
    public abstract void cancel();
    
    public abstract Api.c<A> ge();
    
    public abstract int gj();
    
    public abstract void m(Status paramStatus);
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\common\api\b.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */