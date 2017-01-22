package com.google.android.gms.internal;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.View.MeasureSpec;
import android.webkit.WebView;

@ez
public class fc
  implements Runnable
{
  private final int lf;
  private final int lg;
  protected final gv md;
  private final Handler td;
  private final long te;
  private long tf;
  private gw.a tg;
  protected boolean th;
  protected boolean ti;
  
  public fc(gw.a parama, gv paramgv, int paramInt1, int paramInt2)
  {
    this(parama, paramgv, paramInt1, paramInt2, 200L, 50L);
  }
  
  public fc(gw.a parama, gv paramgv, int paramInt1, int paramInt2, long paramLong1, long paramLong2)
  {
    this.te = paramLong1;
    this.tf = paramLong2;
    this.td = new Handler(Looper.getMainLooper());
    this.md = paramgv;
    this.tg = parama;
    this.th = false;
    this.ti = false;
    this.lg = paramInt2;
    this.lf = paramInt1;
  }
  
  public void a(fk paramfk, ha paramha)
  {
    this.md.setWebViewClient(paramha);
    gv localgv = this.md;
    if (TextUtils.isEmpty(paramfk.rP)) {}
    for (paramha = null;; paramha = gj.L(paramfk.rP))
    {
      localgv.loadDataWithBaseURL(paramha, paramfk.tG, "text/html", "UTF-8", null);
      return;
    }
  }
  
  public void b(fk paramfk)
  {
    a(paramfk, new ha(this, this.md, paramfk.tP));
  }
  
  public boolean cA()
  {
    try
    {
      boolean bool = this.th;
      return bool;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public boolean cB()
  {
    return this.ti;
  }
  
  public void cy()
  {
    this.td.postDelayed(this, this.te);
  }
  
  public void cz()
  {
    try
    {
      this.th = true;
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public void run()
  {
    if ((this.md == null) || (cA())) {
      this.tg.a(this.md);
    }
    for (;;)
    {
      return;
      new a(this.md).execute(new Void[0]);
    }
  }
  
  protected final class a
    extends AsyncTask<Void, Void, Boolean>
  {
    private final WebView tj;
    private Bitmap tk;
    
    public a(WebView paramWebView)
    {
      this.tj = paramWebView;
    }
    
    /* Error */
    protected Boolean a(Void... paramVarArgs)
    {
      // Byte code:
      //   0: aload_0
      //   1: monitorenter
      //   2: aload_0
      //   3: getfield 27	com/google/android/gms/internal/fc$a:tk	Landroid/graphics/Bitmap;
      //   6: invokevirtual 33	android/graphics/Bitmap:getWidth	()I
      //   9: istore 6
      //   11: aload_0
      //   12: getfield 27	com/google/android/gms/internal/fc$a:tk	Landroid/graphics/Bitmap;
      //   15: invokevirtual 36	android/graphics/Bitmap:getHeight	()I
      //   18: istore 7
      //   20: iload 6
      //   22: ifeq +8 -> 30
      //   25: iload 7
      //   27: ifne +12 -> 39
      //   30: iconst_0
      //   31: invokestatic 42	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
      //   34: astore_1
      //   35: aload_0
      //   36: monitorexit
      //   37: aload_1
      //   38: areturn
      //   39: iconst_0
      //   40: istore_2
      //   41: iconst_0
      //   42: istore_3
      //   43: iload_2
      //   44: iload 6
      //   46: if_icmpge +49 -> 95
      //   49: iconst_0
      //   50: istore 4
      //   52: iload 4
      //   54: iload 7
      //   56: if_icmpge +33 -> 89
      //   59: iload_3
      //   60: istore 5
      //   62: aload_0
      //   63: getfield 27	com/google/android/gms/internal/fc$a:tk	Landroid/graphics/Bitmap;
      //   66: iload_2
      //   67: iload 4
      //   69: invokevirtual 46	android/graphics/Bitmap:getPixel	(II)I
      //   72: ifeq +8 -> 80
      //   75: iload_3
      //   76: iconst_1
      //   77: iadd
      //   78: istore 5
      //   80: iinc 4 10
      //   83: iload 5
      //   85: istore_3
      //   86: goto -34 -> 52
      //   89: iinc 2 10
      //   92: goto -49 -> 43
      //   95: iload_3
      //   96: i2d
      //   97: iload 6
      //   99: iload 7
      //   101: imul
      //   102: i2d
      //   103: ldc2_w 47
      //   106: ddiv
      //   107: ddiv
      //   108: ldc2_w 49
      //   111: dcmpl
      //   112: ifle +15 -> 127
      //   115: iconst_1
      //   116: istore 8
      //   118: iload 8
      //   120: invokestatic 42	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
      //   123: astore_1
      //   124: goto -89 -> 35
      //   127: iconst_0
      //   128: istore 8
      //   130: goto -12 -> 118
      //   133: astore_1
      //   134: aload_0
      //   135: monitorexit
      //   136: aload_1
      //   137: athrow
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	138	0	this	a
      //   0	138	1	paramVarArgs	Void[]
      //   40	50	2	i	int
      //   42	54	3	j	int
      //   50	31	4	k	int
      //   60	24	5	m	int
      //   9	93	6	n	int
      //   18	84	7	i1	int
      //   116	13	8	bool	boolean
      // Exception table:
      //   from	to	target	type
      //   2	20	133	finally
      //   62	75	133	finally
    }
    
    protected void a(Boolean paramBoolean)
    {
      fc.c(fc.this);
      if ((paramBoolean.booleanValue()) || (fc.this.cA()) || (fc.d(fc.this) <= 0L))
      {
        fc.this.ti = paramBoolean.booleanValue();
        fc.e(fc.this).a(fc.this.md);
      }
      for (;;)
      {
        return;
        if (fc.d(fc.this) > 0L)
        {
          if (gs.u(2)) {
            gs.S("Ad not detected, scheduling another run.");
          }
          fc.g(fc.this).postDelayed(fc.this, fc.f(fc.this));
        }
      }
    }
    
    protected void onPreExecute()
    {
      try
      {
        this.tk = Bitmap.createBitmap(fc.a(fc.this), fc.b(fc.this), Bitmap.Config.ARGB_8888);
        this.tj.setVisibility(0);
        this.tj.measure(View.MeasureSpec.makeMeasureSpec(fc.a(fc.this), 0), View.MeasureSpec.makeMeasureSpec(fc.b(fc.this), 0));
        this.tj.layout(0, 0, fc.a(fc.this), fc.b(fc.this));
        Canvas localCanvas = new android/graphics/Canvas;
        localCanvas.<init>(this.tk);
        this.tj.draw(localCanvas);
        this.tj.invalidate();
        return;
      }
      finally
      {
        localObject = finally;
        throw ((Throwable)localObject);
      }
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\internal\fc.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */