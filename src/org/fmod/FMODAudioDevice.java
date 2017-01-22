package org.fmod;

import android.media.AudioTrack;
import android.util.Log;
import java.nio.ByteBuffer;

public class FMODAudioDevice
  implements Runnable
{
  private static int h = 0;
  private static int i = 1;
  private static int j = 2;
  private static int k = 3;
  private volatile Thread a = null;
  private volatile boolean b = false;
  private AudioTrack c = null;
  private boolean d = false;
  private ByteBuffer e = null;
  private byte[] f = null;
  private volatile a g;
  
  private native int fmodGetInfo(int paramInt);
  
  private native int fmodProcess(ByteBuffer paramByteBuffer);
  
  private void releaseAudioTrack()
  {
    if (this.c != null)
    {
      if (this.c.getState() == 1) {
        this.c.stop();
      }
      this.c.release();
      this.c = null;
    }
    this.e = null;
    this.f = null;
    this.d = false;
  }
  
  public void close()
  {
    try
    {
      stop();
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  native int fmodProcessMicData(ByteBuffer paramByteBuffer, int paramInt);
  
  public boolean isRunning()
  {
    if ((this.a != null) && (this.a.isAlive())) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public void run()
  {
    int m = 3;
    boolean bool;
    if (this.b)
    {
      if ((this.d) || (m <= 0)) {
        break label313;
      }
      releaseAudioTrack();
      int i3 = fmodGetInfo(h);
      int i1 = Math.round(AudioTrack.getMinBufferSize(i3, 3, 2) * 1.1F) & 0xFFFFFFFC;
      int i2 = fmodGetInfo(i);
      int i4 = fmodGetInfo(j);
      int n = i1;
      if (i2 * i4 * 4 > i1) {
        n = i4 * i2 * 4;
      }
      this.c = new AudioTrack(3, i3, 3, 2, n, 1);
      if (this.c.getState() == 1)
      {
        bool = true;
        label122:
        this.d = bool;
        if (!this.d) {
          break label255;
        }
        this.e = ByteBuffer.allocateDirect(i2 * 2 * 2);
        this.f = new byte[this.e.capacity()];
        this.c.play();
        m = 3;
      }
    }
    label255:
    label313:
    for (;;)
    {
      if (this.d)
      {
        if (fmodGetInfo(k) == 1)
        {
          fmodProcess(this.e);
          this.e.get(this.f, 0, this.e.capacity());
          this.c.write(this.f, 0, this.e.capacity());
          this.e.position(0);
          break;
          bool = false;
          break label122;
          Log.e("FMOD", "AudioTrack failed to initialize (status " + this.c.getState() + ")");
          releaseAudioTrack();
          m--;
          continue;
        }
        releaseAudioTrack();
        break;
        releaseAudioTrack();
        return;
      }
      break;
    }
  }
  
  public void start()
  {
    try
    {
      if (this.a != null) {
        stop();
      }
      Thread localThread = new java/lang/Thread;
      localThread.<init>(this, "FMODAudioDevice");
      this.a = localThread;
      this.a.setPriority(10);
      this.b = true;
      this.a.start();
      if (this.g != null) {
        this.g.b();
      }
      return;
    }
    finally {}
  }
  
  public int startAudioRecord(int paramInt1, int paramInt2, int paramInt3)
  {
    try
    {
      if (this.g == null)
      {
        a locala = new org/fmod/a;
        locala.<init>(this, paramInt1, paramInt2);
        this.g = locala;
        this.g.b();
      }
      paramInt1 = this.g.a();
      return paramInt1;
    }
    finally {}
  }
  
  public void stop()
  {
    try
    {
      while (this.a != null)
      {
        this.b = false;
        try
        {
          this.a.join();
          this.a = null;
        }
        catch (InterruptedException localInterruptedException) {}
      }
      if (this.g != null) {
        this.g.c();
      }
      return;
    }
    finally {}
  }
  
  public void stopAudioRecord()
  {
    try
    {
      if (this.g != null)
      {
        this.g.c();
        this.g = null;
      }
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\org\fmod\FMODAudioDevice.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */