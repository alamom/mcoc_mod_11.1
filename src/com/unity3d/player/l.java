package com.unity3d.player;

import android.view.Choreographer;
import android.view.Choreographer.FrameCallback;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public final class l
  implements h
{
  private Choreographer a = null;
  private long b = 0L;
  private Choreographer.FrameCallback c;
  private boolean d = false;
  private boolean e = false;
  private Lock f = new ReentrantLock();
  
  public final void a()
  {
    this.f.lock();
    if (this.a != null) {
      this.a.removeFrameCallback(this.c);
    }
    this.e = false;
    this.a = null;
    this.f.unlock();
  }
  
  public final void a(final UnityPlayer paramUnityPlayer)
  {
    this.f.lock();
    if (this.a == null)
    {
      this.a = Choreographer.getInstance();
      if (this.a != null)
      {
        m.Log(4, "Choreographer available: Enabling VSYNC timing");
        this.c = new Choreographer.FrameCallback()
        {
          public final void doFrame(long paramAnonymousLong)
          {
            
            if (v.c()) {
              paramUnityPlayer.nativeAddVSyncTime(paramAnonymousLong);
            }
            UnityPlayer.unlockNativeAccess();
            l.a(l.this).lock();
            if ((l.b(l.this) != null) && (!l.c(l.this)))
            {
              l.b(l.this).postFrameCallback(l.d(l.this));
              l.a(l.this, true);
            }
            for (;;)
            {
              l.a(l.this).unlock();
              return;
              l.a(l.this, false);
            }
          }
        };
        this.a.postFrameCallback(this.c);
        this.e = true;
      }
    }
    this.f.unlock();
  }
  
  public final void b()
  {
    this.f.lock();
    this.d = true;
    this.f.unlock();
  }
  
  public final void c()
  {
    this.f.lock();
    if (this.d)
    {
      if ((this.a != null) && (this.c != null) && (!this.e))
      {
        this.a.postFrameCallback(this.c);
        this.e = true;
      }
      this.d = false;
    }
    this.f.unlock();
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\unity3d\player\l.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */