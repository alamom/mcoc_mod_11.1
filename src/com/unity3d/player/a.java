package com.unity3d.player;

import android.graphics.ImageFormat;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.hardware.Camera.PreviewCallback;
import android.hardware.Camera.Size;
import android.view.SurfaceHolder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

final class a
{
  Camera a;
  Camera.Parameters b;
  Camera.Size c;
  int d;
  int[] e;
  b f;
  private final Object[] g = new Object[0];
  private final int h;
  private final int i;
  private final int j;
  private final int k;
  
  public a(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    this.h = paramInt1;
    this.i = a(paramInt2, 640);
    this.j = a(paramInt3, 480);
    this.k = a(paramInt4, 24);
  }
  
  private static final int a(int paramInt1, int paramInt2)
  {
    if (paramInt1 != 0) {}
    for (;;)
    {
      return paramInt1;
      paramInt1 = paramInt2;
    }
  }
  
  private static void a(Camera.Parameters paramParameters)
  {
    if (paramParameters.getSupportedColorEffects() != null) {
      paramParameters.setColorEffect("none");
    }
    if (paramParameters.getSupportedFocusModes().contains("continuous-video")) {
      paramParameters.setFocusMode("continuous-video");
    }
  }
  
  private void b(a parama)
  {
    synchronized (this.g)
    {
      this.a = Camera.open(this.h);
      this.b = this.a.getParameters();
      this.c = f();
      this.e = e();
      this.d = d();
      a(this.b);
      this.b.setPreviewSize(this.c.width, this.c.height);
      this.b.setPreviewFpsRange(this.e[0], this.e[1]);
      this.a.setParameters(this.b);
      Camera.PreviewCallback local1 = new com/unity3d/player/a$1;
      local1.<init>(this, parama);
      int m = this.c.width * this.c.height * this.d / 8 + 4096;
      this.a.addCallbackBuffer(new byte[m]);
      this.a.addCallbackBuffer(new byte[m]);
      this.a.setPreviewCallbackWithBuffer(local1);
      return;
    }
  }
  
  private final int d()
  {
    this.b.setPreviewFormat(17);
    return ImageFormat.getBitsPerPixel(17);
  }
  
  private final int[] e()
  {
    double d3 = this.k * 1000;
    Object localObject2 = this.b.getSupportedPreviewFpsRange();
    Object localObject1 = localObject2;
    if (localObject2 == null) {
      localObject1 = new ArrayList();
    }
    localObject2 = new int[2];
    localObject2[0] = (this.k * 1000);
    localObject2[1] = (this.k * 1000);
    double d1 = Double.MAX_VALUE;
    Iterator localIterator = ((List)localObject1).iterator();
    localObject1 = localObject2;
    if (localIterator.hasNext())
    {
      localObject2 = (int[])localIterator.next();
      double d2 = Math.abs(Math.log(d3 / localObject2[0])) + Math.abs(Math.log(d3 / localObject2[1]));
      if (d2 >= d1) {
        break label154;
      }
      localObject1 = localObject2;
      d1 = d2;
    }
    label154:
    for (;;)
    {
      break;
      return (int[])localObject1;
    }
  }
  
  private final Camera.Size f()
  {
    double d3 = this.i;
    double d4 = this.j;
    Object localObject2 = this.b.getSupportedPreviewSizes();
    Object localObject1 = null;
    double d1 = Double.MAX_VALUE;
    Iterator localIterator = ((List)localObject2).iterator();
    if (localIterator.hasNext())
    {
      localObject2 = (Camera.Size)localIterator.next();
      double d2 = Math.abs(Math.log(d3 / ((Camera.Size)localObject2).width)) + Math.abs(Math.log(d4 / ((Camera.Size)localObject2).height));
      if (d2 >= d1) {
        break label111;
      }
      localObject1 = localObject2;
      d1 = d2;
    }
    label111:
    for (;;)
    {
      break;
      return (Camera.Size)localObject1;
    }
  }
  
  public final int a()
  {
    return this.h;
  }
  
  public final void a(a parama)
  {
    synchronized (this.g)
    {
      if (this.a == null) {
        b(parama);
      }
      if ((q.a) && (q.i.a(this.a)))
      {
        this.a.startPreview();
        return;
      }
      if (this.f == null)
      {
        parama = new com/unity3d/player/a$2;
        parama.<init>(this);
        this.f = parama;
        this.f.a();
      }
    }
  }
  
  public final void a(byte[] paramArrayOfByte)
  {
    synchronized (this.g)
    {
      if (this.a != null) {
        this.a.addCallbackBuffer(paramArrayOfByte);
      }
      return;
    }
  }
  
  public final Camera.Size b()
  {
    return this.c;
  }
  
  public final void c()
  {
    synchronized (this.g)
    {
      if (this.a != null)
      {
        this.a.setPreviewCallbackWithBuffer(null);
        this.a.stopPreview();
        this.a.release();
        this.a = null;
      }
      if (this.f != null)
      {
        this.f.b();
        this.f = null;
      }
      return;
    }
  }
  
  static abstract interface a
  {
    public abstract void onCameraFrame(a parama, byte[] paramArrayOfByte);
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\unity3d\player\a.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */