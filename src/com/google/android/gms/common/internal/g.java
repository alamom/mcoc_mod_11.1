package com.google.android.gms.common.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.IBinder;
import android.os.Message;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

public final class g
  implements Handler.Callback
{
  private static final Object LS = new Object();
  private static g LT;
  private final HashMap<String, a> LU = new HashMap();
  private final Context mD;
  private final Handler mHandler = new Handler(paramContext.getMainLooper(), this);
  
  private g(Context paramContext)
  {
    this.mD = paramContext.getApplicationContext();
  }
  
  public static g J(Context paramContext)
  {
    synchronized (LS)
    {
      if (LT == null)
      {
        g localg = new com/google/android/gms/common/internal/g;
        localg.<init>(paramContext.getApplicationContext());
        LT = localg;
      }
      return LT;
    }
  }
  
  public boolean a(String paramString, e<?>.f parame)
  {
    for (;;)
    {
      Object localObject;
      synchronized (this.LU)
      {
        localObject = (a)this.LU.get(paramString);
        if (localObject == null)
        {
          localObject = new com/google/android/gms/common/internal/g$a;
          ((a)localObject).<init>(this, paramString);
          ((a)localObject).a(parame);
          parame = new android/content/Intent;
          parame.<init>(paramString);
          parame = parame.setPackage("com.google.android.gms");
          ((a)localObject).J(this.mD.bindService(parame, ((a)localObject).gW(), 129));
          this.LU.put(paramString, localObject);
          paramString = (String)localObject;
          boolean bool = paramString.isBound();
          return bool;
        }
        this.mHandler.removeMessages(0, localObject);
        if (((a)localObject).c(parame))
        {
          parame = new java/lang/IllegalStateException;
          localObject = new java/lang/StringBuilder;
          ((StringBuilder)localObject).<init>();
          parame.<init>("Trying to bind a GmsServiceConnection that was already connected before.  startServiceAction=" + paramString);
          throw parame;
        }
      }
      ((a)localObject).a(parame);
      switch (((a)localObject).getState())
      {
      default: 
        paramString = (String)localObject;
        break;
      case 1: 
        parame.onServiceConnected(((a)localObject).getComponentName(), ((a)localObject).getBinder());
        paramString = (String)localObject;
        break;
      case 2: 
        parame = new android/content/Intent;
        parame.<init>(paramString);
        paramString = parame.setPackage("com.google.android.gms");
        ((a)localObject).J(this.mD.bindService(paramString, ((a)localObject).gW(), 129));
        paramString = (String)localObject;
      }
    }
  }
  
  public void b(String paramString, e<?>.f parame)
  {
    Object localObject;
    synchronized (this.LU)
    {
      localObject = (a)this.LU.get(paramString);
      if (localObject == null)
      {
        parame = new java/lang/IllegalStateException;
        localObject = new java/lang/StringBuilder;
        ((StringBuilder)localObject).<init>();
        parame.<init>("Nonexistent connection status for service action: " + paramString);
        throw parame;
      }
    }
    if (!((a)localObject).c(parame))
    {
      parame = new java/lang/IllegalStateException;
      localObject = new java/lang/StringBuilder;
      ((StringBuilder)localObject).<init>();
      parame.<init>("Trying to unbind a GmsServiceConnection  that was not bound before.  startServiceAction=" + paramString);
      throw parame;
    }
    ((a)localObject).b(parame);
    if (((a)localObject).gY())
    {
      paramString = this.mHandler.obtainMessage(0, localObject);
      this.mHandler.sendMessageDelayed(paramString, 5000L);
    }
  }
  
  public boolean handleMessage(Message arg1)
  {
    boolean bool;
    switch (???.what)
    {
    default: 
      bool = false;
    }
    for (;;)
    {
      return bool;
      a locala = (a)???.obj;
      synchronized (this.LU)
      {
        if (locala.gY())
        {
          this.mD.unbindService(locala.gW());
          this.LU.remove(locala.gX());
        }
        bool = true;
      }
    }
  }
  
  final class a
  {
    private final String LV;
    private final a LW;
    private final HashSet<e<?>.f> LX;
    private boolean LY;
    private IBinder LZ;
    private ComponentName Ma;
    private int mState;
    
    public a(String paramString)
    {
      this.LV = paramString;
      this.LW = new a();
      this.LX = new HashSet();
      this.mState = 0;
    }
    
    public void J(boolean paramBoolean)
    {
      this.LY = paramBoolean;
    }
    
    public void a(e<?>.f parame)
    {
      this.LX.add(parame);
    }
    
    public void b(e<?>.f parame)
    {
      this.LX.remove(parame);
    }
    
    public boolean c(e<?>.f parame)
    {
      return this.LX.contains(parame);
    }
    
    public a gW()
    {
      return this.LW;
    }
    
    public String gX()
    {
      return this.LV;
    }
    
    public boolean gY()
    {
      return this.LX.isEmpty();
    }
    
    public IBinder getBinder()
    {
      return this.LZ;
    }
    
    public ComponentName getComponentName()
    {
      return this.Ma;
    }
    
    public int getState()
    {
      return this.mState;
    }
    
    public boolean isBound()
    {
      return this.LY;
    }
    
    public class a
      implements ServiceConnection
    {
      public a() {}
      
      public void onServiceConnected(ComponentName paramComponentName, IBinder paramIBinder)
      {
        synchronized (g.a(g.this))
        {
          g.a.a(g.a.this, paramIBinder);
          g.a.a(g.a.this, paramComponentName);
          Iterator localIterator = g.a.a(g.a.this).iterator();
          if (localIterator.hasNext()) {
            ((e.f)localIterator.next()).onServiceConnected(paramComponentName, paramIBinder);
          }
        }
        g.a.a(g.a.this, 1);
      }
      
      public void onServiceDisconnected(ComponentName paramComponentName)
      {
        synchronized (g.a(g.this))
        {
          g.a.a(g.a.this, null);
          g.a.a(g.a.this, paramComponentName);
          Iterator localIterator = g.a.a(g.a.this).iterator();
          if (localIterator.hasNext()) {
            ((e.f)localIterator.next()).onServiceDisconnected(paramComponentName);
          }
        }
        g.a.a(g.a.this, 2);
      }
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\common\internal\g.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */