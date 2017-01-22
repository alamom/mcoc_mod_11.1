package com.unity3d.player;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnBufferingUpdateListener;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.media.MediaPlayer.OnVideoSizeChangedListener;
import android.net.Uri;
import android.util.Log;
import android.view.Display;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.MediaController;
import android.widget.MediaController.MediaPlayerControl;
import java.io.FileInputStream;
import java.io.IOException;

public final class w
  extends FrameLayout
  implements MediaPlayer.OnBufferingUpdateListener, MediaPlayer.OnCompletionListener, MediaPlayer.OnPreparedListener, MediaPlayer.OnVideoSizeChangedListener, SurfaceHolder.Callback, MediaController.MediaPlayerControl
{
  private static boolean a = false;
  private final UnityPlayer b;
  private final Context c;
  private final SurfaceView d;
  private final SurfaceHolder e;
  private final String f;
  private final int g;
  private final int h;
  private final boolean i;
  private final long j;
  private final long k;
  private final FrameLayout l;
  private final Display m;
  private int n;
  private int o;
  private int p;
  private int q;
  private MediaPlayer r;
  private MediaController s;
  private boolean t = false;
  private boolean u = false;
  private int v = 0;
  private boolean w = false;
  private int x = 0;
  private boolean y;
  
  protected w(UnityPlayer paramUnityPlayer, Context paramContext, String paramString, int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean, long paramLong1, long paramLong2)
  {
    super(paramContext);
    this.b = paramUnityPlayer;
    this.c = paramContext;
    this.l = this;
    this.d = new SurfaceView(paramContext);
    this.e = this.d.getHolder();
    this.e.addCallback(this);
    this.e.setType(3);
    this.l.setBackgroundColor(paramInt1);
    this.l.addView(this.d);
    this.m = ((WindowManager)this.c.getSystemService("window")).getDefaultDisplay();
    this.f = paramString;
    this.g = paramInt2;
    this.h = paramInt3;
    this.i = paramBoolean;
    this.j = paramLong1;
    this.k = paramLong2;
    if (a) {
      a("fileName: " + this.f);
    }
    if (a) {
      a("backgroundColor: " + paramInt1);
    }
    if (a) {
      a("controlMode: " + this.g);
    }
    if (a) {
      a("scalingMode: " + this.h);
    }
    if (a) {
      a("isURL: " + this.i);
    }
    if (a) {
      a("videoOffset: " + this.j);
    }
    if (a) {
      a("videoLength: " + this.k);
    }
    setFocusable(true);
    setFocusableInTouchMode(true);
    this.y = true;
  }
  
  private void a()
  {
    doCleanUp();
    try
    {
      Object localObject1 = new android/media/MediaPlayer;
      ((MediaPlayer)localObject1).<init>();
      this.r = ((MediaPlayer)localObject1);
      if (this.i) {
        this.r.setDataSource(this.c, Uri.parse(this.f));
      }
      for (;;)
      {
        this.r.setDisplay(this.e);
        this.r.setScreenOnWhilePlaying(true);
        this.r.setOnBufferingUpdateListener(this);
        this.r.setOnCompletionListener(this);
        this.r.setOnPreparedListener(this);
        this.r.setOnVideoSizeChangedListener(this);
        this.r.setAudioStreamType(3);
        this.r.prepare();
        if ((this.g == 0) || (this.g == 1))
        {
          localObject1 = new android/widget/MediaController;
          ((MediaController)localObject1).<init>(this.c);
          this.s = ((MediaController)localObject1);
          this.s.setMediaPlayer(this);
          this.s.setAnchorView(this);
          this.s.setEnabled(true);
          this.s.show();
        }
        return;
        if (this.k == 0L) {
          break;
        }
        localObject1 = new java/io/FileInputStream;
        ((FileInputStream)localObject1).<init>(this.f);
        this.r.setDataSource(((FileInputStream)localObject1).getFD(), this.j, this.k);
        ((FileInputStream)localObject1).close();
      }
    }
    catch (Exception localException)
    {
      for (;;)
      {
        if (a) {
          a("error: " + localException.getMessage() + localException);
        }
        onDestroy();
        continue;
        Object localObject2 = getResources().getAssets();
        try
        {
          localObject2 = ((AssetManager)localObject2).openFd(this.f);
          this.r.setDataSource(((AssetFileDescriptor)localObject2).getFileDescriptor(), ((AssetFileDescriptor)localObject2).getStartOffset(), ((AssetFileDescriptor)localObject2).getLength());
          ((AssetFileDescriptor)localObject2).close();
        }
        catch (IOException localIOException)
        {
          FileInputStream localFileInputStream = new java/io/FileInputStream;
          localFileInputStream.<init>(this.f);
          this.r.setDataSource(localFileInputStream.getFD());
          localFileInputStream.close();
        }
      }
    }
  }
  
  private static void a(String paramString)
  {
    Log.v("Video", "VideoPlayer: " + paramString);
  }
  
  private void b()
  {
    if (isPlaying()) {}
    for (;;)
    {
      return;
      if (a) {
        a("startVideoPlayback");
      }
      updateVideoLayout();
      if (!this.w) {
        start();
      }
    }
  }
  
  public final boolean canPause()
  {
    return true;
  }
  
  public final boolean canSeekBackward()
  {
    return true;
  }
  
  public final boolean canSeekForward()
  {
    return true;
  }
  
  protected final void doCleanUp()
  {
    if (this.r != null)
    {
      this.r.release();
      this.r = null;
    }
    this.p = 0;
    this.q = 0;
    this.u = false;
    this.t = false;
  }
  
  public final int getBufferPercentage()
  {
    if (this.i) {}
    for (int i1 = this.v;; i1 = 100) {
      return i1;
    }
  }
  
  public final int getCurrentPosition()
  {
    if (this.r == null) {}
    for (int i1 = 0;; i1 = this.r.getCurrentPosition()) {
      return i1;
    }
  }
  
  public final int getDuration()
  {
    if (this.r == null) {}
    for (int i1 = 0;; i1 = this.r.getDuration()) {
      return i1;
    }
  }
  
  public final boolean isPlaying()
  {
    boolean bool2 = true;
    int i1;
    boolean bool1;
    if ((this.u) && (this.t))
    {
      i1 = 1;
      if (this.r != null) {
        break label43;
      }
      if (i1 != 0) {
        break label38;
      }
      bool1 = bool2;
    }
    for (;;)
    {
      return bool1;
      i1 = 0;
      break;
      label38:
      bool1 = false;
      continue;
      label43:
      bool1 = bool2;
      if (!this.r.isPlaying())
      {
        bool1 = bool2;
        if (i1 != 0) {
          bool1 = false;
        }
      }
    }
  }
  
  public final void onBufferingUpdate(MediaPlayer paramMediaPlayer, int paramInt)
  {
    if (a) {
      a("onBufferingUpdate percent:" + paramInt);
    }
    this.v = paramInt;
  }
  
  public final void onCompletion(MediaPlayer paramMediaPlayer)
  {
    if (a) {
      a("onCompletion called");
    }
    onDestroy();
  }
  
  public final void onControllerHide() {}
  
  protected final void onDestroy()
  {
    onPause();
    doCleanUp();
    UnityPlayer.a(new Runnable()
    {
      public final void run()
      {
        w.a(w.this).hideVideoPlayer();
      }
    });
  }
  
  public final boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    boolean bool;
    if ((paramInt == 4) || ((this.g == 2) && (paramInt != 0) && (!paramKeyEvent.isSystem())))
    {
      onDestroy();
      bool = true;
    }
    for (;;)
    {
      return bool;
      if (this.s != null) {
        bool = this.s.onKeyDown(paramInt, paramKeyEvent);
      } else {
        bool = super.onKeyDown(paramInt, paramKeyEvent);
      }
    }
  }
  
  protected final void onPause()
  {
    if (a) {
      a("onPause called");
    }
    if (!this.w)
    {
      pause();
      this.w = false;
    }
    if (this.r != null) {
      this.x = this.r.getCurrentPosition();
    }
    this.y = false;
  }
  
  public final void onPrepared(MediaPlayer paramMediaPlayer)
  {
    if (a) {
      a("onPrepared called");
    }
    this.u = true;
    if ((this.u) && (this.t)) {
      b();
    }
  }
  
  protected final void onResume()
  {
    if (a) {
      a("onResume called");
    }
    if ((!this.y) && (!this.w)) {
      start();
    }
    this.y = true;
  }
  
  public final boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    int i1 = paramMotionEvent.getAction();
    boolean bool;
    if ((this.g == 2) && ((i1 & 0xFF) == 0))
    {
      onDestroy();
      bool = true;
    }
    for (;;)
    {
      return bool;
      if (this.s != null) {
        bool = this.s.onTouchEvent(paramMotionEvent);
      } else {
        bool = super.onTouchEvent(paramMotionEvent);
      }
    }
  }
  
  public final void onVideoSizeChanged(MediaPlayer paramMediaPlayer, int paramInt1, int paramInt2)
  {
    if (a) {
      a("onVideoSizeChanged called " + paramInt1 + "x" + paramInt2);
    }
    if ((paramInt1 == 0) || (paramInt2 == 0)) {
      if (a) {
        a("invalid video width(" + paramInt1 + ") or height(" + paramInt2 + ")");
      }
    }
    for (;;)
    {
      return;
      this.t = true;
      this.p = paramInt1;
      this.q = paramInt2;
      if ((this.u) && (this.t)) {
        b();
      }
    }
  }
  
  public final void pause()
  {
    if (this.r == null) {}
    for (;;)
    {
      return;
      this.r.pause();
      this.w = true;
    }
  }
  
  public final void seekTo(int paramInt)
  {
    if (this.r == null) {}
    for (;;)
    {
      return;
      this.r.seekTo(paramInt);
    }
  }
  
  public final void start()
  {
    if (this.r == null) {}
    for (;;)
    {
      return;
      this.r.start();
      this.w = false;
    }
  }
  
  public final void surfaceChanged(SurfaceHolder paramSurfaceHolder, int paramInt1, int paramInt2, int paramInt3)
  {
    if (a) {
      a("surfaceChanged called " + paramInt1 + " " + paramInt2 + "x" + paramInt3);
    }
    if ((this.n != paramInt2) || (this.o != paramInt3))
    {
      this.n = paramInt2;
      this.o = paramInt3;
      updateVideoLayout();
    }
  }
  
  public final void surfaceCreated(SurfaceHolder paramSurfaceHolder)
  {
    if (a) {
      a("surfaceCreated called");
    }
    a();
    seekTo(this.x);
  }
  
  public final void surfaceDestroyed(SurfaceHolder paramSurfaceHolder)
  {
    if (a) {
      a("surfaceDestroyed called");
    }
    doCleanUp();
  }
  
  protected final void updateVideoLayout()
  {
    if (a) {
      a("updateVideoLayout");
    }
    Object localObject;
    if ((this.n == 0) || (this.o == 0))
    {
      localObject = (WindowManager)this.c.getSystemService("window");
      this.n = ((WindowManager)localObject).getDefaultDisplay().getWidth();
      this.o = ((WindowManager)localObject).getDefaultDisplay().getHeight();
    }
    int i2 = this.n;
    int i1 = this.o;
    float f2 = this.p / this.q;
    float f1 = this.n / this.o;
    if (this.h == 1) {
      if (f1 <= f2) {
        i1 = (int)(this.n / f2);
      }
    }
    for (;;)
    {
      if (a) {
        a("frameWidth = " + i2 + "; frameHeight = " + i1);
      }
      localObject = new FrameLayout.LayoutParams(i2, i1, 17);
      this.l.updateViewLayout(this.d, (ViewGroup.LayoutParams)localObject);
      return;
      i2 = (int)(this.o * f2);
      continue;
      if (this.h == 2)
      {
        if (f1 >= f2) {
          i1 = (int)(this.n / f2);
        } else {
          i2 = (int)(this.o * f2);
        }
      }
      else if (this.h == 0)
      {
        i2 = this.p;
        i1 = this.q;
      }
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\unity3d\player\w.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */