package com.google.android.gms.common.images;

import android.app.ActivityManager;
import android.content.ComponentCallbacks2;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.ParcelFileDescriptor;
import android.os.ResultReceiver;
import android.os.SystemClock;
import android.widget.ImageView;
import com.google.android.gms.internal.iz;
import com.google.android.gms.internal.ja;
import com.google.android.gms.internal.kc;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public final class ImageManager
{
  private static final Object Ks = new Object();
  private static HashSet<Uri> Kt = new HashSet();
  private static ImageManager Ku;
  private static ImageManager Kv;
  private final Map<Uri, ImageReceiver> KA;
  private final Map<Uri, Long> KB;
  private final ExecutorService Kw;
  private final b Kx;
  private final iz Ky;
  private final Map<a, ImageReceiver> Kz;
  private final Context mContext;
  private final Handler mHandler;
  
  private ImageManager(Context paramContext, boolean paramBoolean)
  {
    this.mContext = paramContext.getApplicationContext();
    this.mHandler = new Handler(Looper.getMainLooper());
    this.Kw = Executors.newFixedThreadPool(4);
    if (paramBoolean)
    {
      this.Kx = new b(this.mContext);
      if (kc.hE()) {
        gG();
      }
    }
    for (;;)
    {
      this.Ky = new iz();
      this.Kz = new HashMap();
      this.KA = new HashMap();
      this.KB = new HashMap();
      return;
      this.Kx = null;
    }
  }
  
  private Bitmap a(a.a parama)
  {
    if (this.Kx == null) {}
    for (parama = null;; parama = (Bitmap)this.Kx.get(parama)) {
      return parama;
    }
  }
  
  public static ImageManager c(Context paramContext, boolean paramBoolean)
  {
    if (paramBoolean) {
      if (Kv == null) {
        Kv = new ImageManager(paramContext, true);
      }
    }
    for (paramContext = Kv;; paramContext = Ku)
    {
      return paramContext;
      if (Ku == null) {
        Ku = new ImageManager(paramContext, false);
      }
    }
  }
  
  public static ImageManager create(Context paramContext)
  {
    return c(paramContext, false);
  }
  
  private void gG()
  {
    this.mContext.registerComponentCallbacks(new e(this.Kx));
  }
  
  public void a(a parama)
  {
    com.google.android.gms.common.internal.a.aT("ImageManager.loadImage() must be called in the main thread");
    new d(parama).run();
  }
  
  public void loadImage(ImageView paramImageView, int paramInt)
  {
    a(new a.b(paramImageView, paramInt));
  }
  
  public void loadImage(ImageView paramImageView, Uri paramUri)
  {
    a(new a.b(paramImageView, paramUri));
  }
  
  public void loadImage(ImageView paramImageView, Uri paramUri, int paramInt)
  {
    paramImageView = new a.b(paramImageView, paramUri);
    paramImageView.aw(paramInt);
    a(paramImageView);
  }
  
  public void loadImage(OnImageLoadedListener paramOnImageLoadedListener, Uri paramUri)
  {
    a(new a.c(paramOnImageLoadedListener, paramUri));
  }
  
  public void loadImage(OnImageLoadedListener paramOnImageLoadedListener, Uri paramUri, int paramInt)
  {
    paramOnImageLoadedListener = new a.c(paramOnImageLoadedListener, paramUri);
    paramOnImageLoadedListener.aw(paramInt);
    a(paramOnImageLoadedListener);
  }
  
  private final class ImageReceiver
    extends ResultReceiver
  {
    private final ArrayList<a> KC;
    private final Uri mUri;
    
    ImageReceiver(Uri paramUri)
    {
      super();
      this.mUri = paramUri;
      this.KC = new ArrayList();
    }
    
    public void b(a parama)
    {
      com.google.android.gms.common.internal.a.aT("ImageReceiver.addImageRequest() must be called in the main thread");
      this.KC.add(parama);
    }
    
    public void c(a parama)
    {
      com.google.android.gms.common.internal.a.aT("ImageReceiver.removeImageRequest() must be called in the main thread");
      this.KC.remove(parama);
    }
    
    public void gJ()
    {
      Intent localIntent = new Intent("com.google.android.gms.common.images.LOAD_IMAGE");
      localIntent.putExtra("com.google.android.gms.extras.uri", this.mUri);
      localIntent.putExtra("com.google.android.gms.extras.resultReceiver", this);
      localIntent.putExtra("com.google.android.gms.extras.priority", 3);
      ImageManager.b(ImageManager.this).sendBroadcast(localIntent);
    }
    
    public void onReceiveResult(int paramInt, Bundle paramBundle)
    {
      paramBundle = (ParcelFileDescriptor)paramBundle.getParcelable("com.google.android.gms.extra.fileDescriptor");
      ImageManager.f(ImageManager.this).execute(new ImageManager.c(ImageManager.this, this.mUri, paramBundle));
    }
  }
  
  public static abstract interface OnImageLoadedListener
  {
    public abstract void onImageLoaded(Uri paramUri, Drawable paramDrawable, boolean paramBoolean);
  }
  
  private static final class a
  {
    static int a(ActivityManager paramActivityManager)
    {
      return paramActivityManager.getLargeMemoryClass();
    }
  }
  
  private static final class b
    extends ja<a.a, Bitmap>
  {
    public b(Context paramContext)
    {
      super();
    }
    
    private static int I(Context paramContext)
    {
      ActivityManager localActivityManager = (ActivityManager)paramContext.getSystemService("activity");
      if ((paramContext.getApplicationInfo().flags & 0x100000) != 0)
      {
        i = 1;
        if ((i == 0) || (!kc.hB())) {
          break label55;
        }
      }
      label55:
      for (int i = ImageManager.a.a(localActivityManager);; i = localActivityManager.getMemoryClass())
      {
        return (int)(i * 1048576 * 0.33F);
        i = 0;
        break;
      }
    }
    
    protected int a(a.a parama, Bitmap paramBitmap)
    {
      return paramBitmap.getHeight() * paramBitmap.getRowBytes();
    }
    
    protected void a(boolean paramBoolean, a.a parama, Bitmap paramBitmap1, Bitmap paramBitmap2)
    {
      super.entryRemoved(paramBoolean, parama, paramBitmap1, paramBitmap2);
    }
  }
  
  private final class c
    implements Runnable
  {
    private final ParcelFileDescriptor KE;
    private final Uri mUri;
    
    public c(Uri paramUri, ParcelFileDescriptor paramParcelFileDescriptor)
    {
      this.mUri = paramUri;
      this.KE = paramParcelFileDescriptor;
    }
    
    /* Error */
    public void run()
    {
      // Byte code:
      //   0: ldc 36
      //   2: invokestatic 42	com/google/android/gms/common/internal/a:aU	(Ljava/lang/String;)V
      //   5: iconst_0
      //   6: istore_1
      //   7: iconst_0
      //   8: istore_2
      //   9: aconst_null
      //   10: astore_3
      //   11: aconst_null
      //   12: astore 4
      //   14: aload_0
      //   15: getfield 26	com/google/android/gms/common/images/ImageManager$c:KE	Landroid/os/ParcelFileDescriptor;
      //   18: ifnull +23 -> 41
      //   21: aload_0
      //   22: getfield 26	com/google/android/gms/common/images/ImageManager$c:KE	Landroid/os/ParcelFileDescriptor;
      //   25: invokevirtual 48	android/os/ParcelFileDescriptor:getFileDescriptor	()Ljava/io/FileDescriptor;
      //   28: invokestatic 54	android/graphics/BitmapFactory:decodeFileDescriptor	(Ljava/io/FileDescriptor;)Landroid/graphics/Bitmap;
      //   31: astore_3
      //   32: iload_2
      //   33: istore_1
      //   34: aload_0
      //   35: getfield 26	com/google/android/gms/common/images/ImageManager$c:KE	Landroid/os/ParcelFileDescriptor;
      //   38: invokevirtual 57	android/os/ParcelFileDescriptor:close	()V
      //   41: new 59	java/util/concurrent/CountDownLatch
      //   44: dup
      //   45: iconst_1
      //   46: invokespecial 62	java/util/concurrent/CountDownLatch:<init>	(I)V
      //   49: astore 4
      //   51: aload_0
      //   52: getfield 19	com/google/android/gms/common/images/ImageManager$c:KD	Lcom/google/android/gms/common/images/ImageManager;
      //   55: invokestatic 66	com/google/android/gms/common/images/ImageManager:g	(Lcom/google/android/gms/common/images/ImageManager;)Landroid/os/Handler;
      //   58: new 68	com/google/android/gms/common/images/ImageManager$f
      //   61: dup
      //   62: aload_0
      //   63: getfield 19	com/google/android/gms/common/images/ImageManager$c:KD	Lcom/google/android/gms/common/images/ImageManager;
      //   66: aload_0
      //   67: getfield 24	com/google/android/gms/common/images/ImageManager$c:mUri	Landroid/net/Uri;
      //   70: aload_3
      //   71: iload_1
      //   72: aload 4
      //   74: invokespecial 71	com/google/android/gms/common/images/ImageManager$f:<init>	(Lcom/google/android/gms/common/images/ImageManager;Landroid/net/Uri;Landroid/graphics/Bitmap;ZLjava/util/concurrent/CountDownLatch;)V
      //   77: invokevirtual 77	android/os/Handler:post	(Ljava/lang/Runnable;)Z
      //   80: pop
      //   81: aload 4
      //   83: invokevirtual 80	java/util/concurrent/CountDownLatch:await	()V
      //   86: return
      //   87: astore_3
      //   88: ldc 82
      //   90: new 84	java/lang/StringBuilder
      //   93: dup
      //   94: invokespecial 85	java/lang/StringBuilder:<init>	()V
      //   97: ldc 87
      //   99: invokevirtual 91	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   102: aload_0
      //   103: getfield 24	com/google/android/gms/common/images/ImageManager$c:mUri	Landroid/net/Uri;
      //   106: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
      //   109: invokevirtual 98	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   112: aload_3
      //   113: invokestatic 104	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
      //   116: pop
      //   117: iconst_1
      //   118: istore_1
      //   119: aload 4
      //   121: astore_3
      //   122: goto -88 -> 34
      //   125: astore 4
      //   127: ldc 82
      //   129: ldc 106
      //   131: aload 4
      //   133: invokestatic 104	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
      //   136: pop
      //   137: goto -96 -> 41
      //   140: astore_3
      //   141: ldc 82
      //   143: new 84	java/lang/StringBuilder
      //   146: dup
      //   147: invokespecial 85	java/lang/StringBuilder:<init>	()V
      //   150: ldc 108
      //   152: invokevirtual 91	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   155: aload_0
      //   156: getfield 24	com/google/android/gms/common/images/ImageManager$c:mUri	Landroid/net/Uri;
      //   159: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
      //   162: invokevirtual 98	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   165: invokestatic 112	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;)I
      //   168: pop
      //   169: goto -83 -> 86
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	172	0	this	c
      //   6	113	1	bool1	boolean
      //   8	25	2	bool2	boolean
      //   10	61	3	localBitmap	Bitmap
      //   87	26	3	localOutOfMemoryError	OutOfMemoryError
      //   121	1	3	localObject	Object
      //   140	1	3	localInterruptedException	InterruptedException
      //   12	108	4	localCountDownLatch	CountDownLatch
      //   125	7	4	localIOException	java.io.IOException
      // Exception table:
      //   from	to	target	type
      //   21	32	87	java/lang/OutOfMemoryError
      //   34	41	125	java/io/IOException
      //   81	86	140	java/lang/InterruptedException
    }
  }
  
  private final class d
    implements Runnable
  {
    private final a KF;
    
    public d(a parama)
    {
      this.KF = parama;
    }
    
    public void run()
    {
      com.google.android.gms.common.internal.a.aT("LoadImageRunnable must be executed on the main thread");
      Object localObject1 = (ImageManager.ImageReceiver)ImageManager.a(ImageManager.this).get(this.KF);
      if (localObject1 != null)
      {
        ImageManager.a(ImageManager.this).remove(this.KF);
        ((ImageManager.ImageReceiver)localObject1).c(this.KF);
      }
      a.a locala = this.KF.KH;
      if (locala.uri == null) {
        this.KF.a(ImageManager.b(ImageManager.this), ImageManager.c(ImageManager.this), true);
      }
      for (;;)
      {
        return;
        localObject1 = ImageManager.a(ImageManager.this, locala);
        if (localObject1 != null)
        {
          this.KF.a(ImageManager.b(ImageManager.this), (Bitmap)localObject1, true);
          continue;
        }
        localObject1 = (Long)ImageManager.d(ImageManager.this).get(locala.uri);
        if (localObject1 != null)
        {
          if (SystemClock.elapsedRealtime() - ((Long)localObject1).longValue() < 3600000L)
          {
            this.KF.a(ImageManager.b(ImageManager.this), ImageManager.c(ImageManager.this), true);
            continue;
          }
          ImageManager.d(ImageManager.this).remove(locala.uri);
        }
        this.KF.a(ImageManager.b(ImageManager.this), ImageManager.c(ImageManager.this));
        ??? = (ImageManager.ImageReceiver)ImageManager.e(ImageManager.this).get(locala.uri);
        localObject1 = ???;
        if (??? == null)
        {
          localObject1 = new ImageManager.ImageReceiver(ImageManager.this, locala.uri);
          ImageManager.e(ImageManager.this).put(locala.uri, localObject1);
        }
        ((ImageManager.ImageReceiver)localObject1).b(this.KF);
        if (!(this.KF instanceof a.c)) {
          ImageManager.a(ImageManager.this).put(this.KF, localObject1);
        }
        synchronized (ImageManager.gH())
        {
          if (!ImageManager.gI().contains(locala.uri))
          {
            ImageManager.gI().add(locala.uri);
            ((ImageManager.ImageReceiver)localObject1).gJ();
          }
        }
      }
    }
  }
  
  private static final class e
    implements ComponentCallbacks2
  {
    private final ImageManager.b Kx;
    
    public e(ImageManager.b paramb)
    {
      this.Kx = paramb;
    }
    
    public void onConfigurationChanged(Configuration paramConfiguration) {}
    
    public void onLowMemory()
    {
      this.Kx.evictAll();
    }
    
    public void onTrimMemory(int paramInt)
    {
      if (paramInt >= 60) {
        this.Kx.evictAll();
      }
      for (;;)
      {
        return;
        if (paramInt >= 20) {
          this.Kx.trimToSize(this.Kx.size() / 2);
        }
      }
    }
  }
  
  private final class f
    implements Runnable
  {
    private boolean KG;
    private final Bitmap mBitmap;
    private final Uri mUri;
    private final CountDownLatch mg;
    
    public f(Uri paramUri, Bitmap paramBitmap, boolean paramBoolean, CountDownLatch paramCountDownLatch)
    {
      this.mUri = paramUri;
      this.mBitmap = paramBitmap;
      this.KG = paramBoolean;
      this.mg = paramCountDownLatch;
    }
    
    private void a(ImageManager.ImageReceiver paramImageReceiver, boolean paramBoolean)
    {
      paramImageReceiver = ImageManager.ImageReceiver.a(paramImageReceiver);
      int j = paramImageReceiver.size();
      int i = 0;
      if (i < j)
      {
        a locala = (a)paramImageReceiver.get(i);
        if (paramBoolean) {
          locala.a(ImageManager.b(ImageManager.this), this.mBitmap, false);
        }
        for (;;)
        {
          if (!(locala instanceof a.c)) {
            ImageManager.a(ImageManager.this).remove(locala);
          }
          i++;
          break;
          ImageManager.d(ImageManager.this).put(this.mUri, Long.valueOf(SystemClock.elapsedRealtime()));
          locala.a(ImageManager.b(ImageManager.this), ImageManager.c(ImageManager.this), false);
        }
      }
    }
    
    public void run()
    {
      com.google.android.gms.common.internal.a.aT("OnBitmapLoadedRunnable must be executed in the main thread");
      boolean bool;
      if (this.mBitmap != null)
      {
        bool = true;
        if (ImageManager.h(ImageManager.this) == null) {
          break label97;
        }
        if (!this.KG) {
          break label67;
        }
        ImageManager.h(ImageManager.this).evictAll();
        System.gc();
        this.KG = false;
        ImageManager.g(ImageManager.this).post(this);
      }
      for (;;)
      {
        return;
        bool = false;
        break;
        label67:
        if (bool) {
          ImageManager.h(ImageManager.this).put(new a.a(this.mUri), this.mBitmap);
        }
        label97:
        ??? = (ImageManager.ImageReceiver)ImageManager.e(ImageManager.this).remove(this.mUri);
        if (??? != null) {
          a((ImageManager.ImageReceiver)???, bool);
        }
        this.mg.countDown();
        synchronized (ImageManager.gH())
        {
          ImageManager.gI().remove(this.mUri);
        }
      }
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\common\images\ImageManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */