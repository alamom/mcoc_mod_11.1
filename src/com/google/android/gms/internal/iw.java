package com.google.android.gms.internal;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.Callback;
import android.graphics.drawable.Drawable.ConstantState;
import android.os.SystemClock;

public final class iw
  extends Drawable
  implements Drawable.Callback
{
  private boolean KL = true;
  private int KR = 0;
  private long KS;
  private int KT;
  private int KU = 255;
  private int KV;
  private int KW = 0;
  private boolean KX;
  private b KY;
  private Drawable KZ;
  private Drawable La;
  private boolean Lb;
  private boolean Lc;
  private boolean Ld;
  private int Le;
  private int mFrom;
  
  public iw(Drawable paramDrawable1, Drawable paramDrawable2)
  {
    this(null);
    Object localObject = paramDrawable1;
    if (paramDrawable1 == null) {
      localObject = a.gL();
    }
    this.KZ = ((Drawable)localObject);
    ((Drawable)localObject).setCallback(this);
    paramDrawable1 = this.KY;
    paramDrawable1.Li |= ((Drawable)localObject).getChangingConfigurations();
    paramDrawable1 = paramDrawable2;
    if (paramDrawable2 == null) {
      paramDrawable1 = a.gL();
    }
    this.La = paramDrawable1;
    paramDrawable1.setCallback(this);
    paramDrawable2 = this.KY;
    paramDrawable2.Li |= paramDrawable1.getChangingConfigurations();
  }
  
  iw(b paramb)
  {
    this.KY = new b(paramb);
  }
  
  public boolean canConstantState()
  {
    if (!this.Lb) {
      if ((this.KZ.getConstantState() == null) || (this.La.getConstantState() == null)) {
        break label44;
      }
    }
    label44:
    for (boolean bool = true;; bool = false)
    {
      this.Lc = bool;
      this.Lb = true;
      return this.Lc;
    }
  }
  
  public void draw(Canvas paramCanvas)
  {
    int j = 1;
    int i = 1;
    int k = 0;
    boolean bool;
    Drawable localDrawable1;
    Drawable localDrawable2;
    switch (this.KR)
    {
    default: 
      j = this.KW;
      bool = this.KL;
      localDrawable1 = this.KZ;
      localDrawable2 = this.La;
      if (i != 0)
      {
        if ((!bool) || (j == 0)) {
          localDrawable1.draw(paramCanvas);
        }
        if (j == this.KU)
        {
          localDrawable2.setAlpha(this.KU);
          localDrawable2.draw(paramCanvas);
        }
      }
      break;
    }
    for (;;)
    {
      return;
      this.KS = SystemClock.uptimeMillis();
      this.KR = 2;
      i = k;
      break;
      if (this.KS < 0L) {
        break;
      }
      float f1 = (float)(SystemClock.uptimeMillis() - this.KS) / this.KV;
      if (f1 >= 1.0F) {}
      for (i = j;; i = 0)
      {
        if (i != 0) {
          this.KR = 0;
        }
        f1 = Math.min(f1, 1.0F);
        float f2 = this.mFrom;
        this.KW = ((int)(f1 * (this.KT - this.mFrom) + f2));
        break;
      }
      if (bool) {
        localDrawable1.setAlpha(this.KU - j);
      }
      localDrawable1.draw(paramCanvas);
      if (bool) {
        localDrawable1.setAlpha(this.KU);
      }
      if (j > 0)
      {
        localDrawable2.setAlpha(j);
        localDrawable2.draw(paramCanvas);
        localDrawable2.setAlpha(this.KU);
      }
      invalidateSelf();
    }
  }
  
  public Drawable gK()
  {
    return this.La;
  }
  
  public int getChangingConfigurations()
  {
    return super.getChangingConfigurations() | this.KY.Lh | this.KY.Li;
  }
  
  public Drawable.ConstantState getConstantState()
  {
    if (canConstantState()) {
      this.KY.Lh = getChangingConfigurations();
    }
    for (b localb = this.KY;; localb = null) {
      return localb;
    }
  }
  
  public int getIntrinsicHeight()
  {
    return Math.max(this.KZ.getIntrinsicHeight(), this.La.getIntrinsicHeight());
  }
  
  public int getIntrinsicWidth()
  {
    return Math.max(this.KZ.getIntrinsicWidth(), this.La.getIntrinsicWidth());
  }
  
  public int getOpacity()
  {
    if (!this.Ld)
    {
      this.Le = Drawable.resolveOpacity(this.KZ.getOpacity(), this.La.getOpacity());
      this.Ld = true;
    }
    return this.Le;
  }
  
  public void invalidateDrawable(Drawable paramDrawable)
  {
    if (kc.hB())
    {
      paramDrawable = getCallback();
      if (paramDrawable != null) {
        paramDrawable.invalidateDrawable(this);
      }
    }
  }
  
  public Drawable mutate()
  {
    if ((!this.KX) && (super.mutate() == this))
    {
      if (!canConstantState()) {
        throw new IllegalStateException("One or more children of this LayerDrawable does not have constant state; this drawable cannot be mutated.");
      }
      this.KZ.mutate();
      this.La.mutate();
      this.KX = true;
    }
    return this;
  }
  
  protected void onBoundsChange(Rect paramRect)
  {
    this.KZ.setBounds(paramRect);
    this.La.setBounds(paramRect);
  }
  
  public void scheduleDrawable(Drawable paramDrawable, Runnable paramRunnable, long paramLong)
  {
    if (kc.hB())
    {
      paramDrawable = getCallback();
      if (paramDrawable != null) {
        paramDrawable.scheduleDrawable(this, paramRunnable, paramLong);
      }
    }
  }
  
  public void setAlpha(int paramInt)
  {
    if (this.KW == this.KU) {
      this.KW = paramInt;
    }
    this.KU = paramInt;
    invalidateSelf();
  }
  
  public void setColorFilter(ColorFilter paramColorFilter)
  {
    this.KZ.setColorFilter(paramColorFilter);
    this.La.setColorFilter(paramColorFilter);
  }
  
  public void startTransition(int paramInt)
  {
    this.mFrom = 0;
    this.KT = this.KU;
    this.KW = 0;
    this.KV = paramInt;
    this.KR = 1;
    invalidateSelf();
  }
  
  public void unscheduleDrawable(Drawable paramDrawable, Runnable paramRunnable)
  {
    if (kc.hB())
    {
      paramDrawable = getCallback();
      if (paramDrawable != null) {
        paramDrawable.unscheduleDrawable(this, paramRunnable);
      }
    }
  }
  
  private static final class a
    extends Drawable
  {
    private static final a Lf = new a();
    private static final a Lg = new a(null);
    
    public void draw(Canvas paramCanvas) {}
    
    public Drawable.ConstantState getConstantState()
    {
      return Lg;
    }
    
    public int getOpacity()
    {
      return -2;
    }
    
    public void setAlpha(int paramInt) {}
    
    public void setColorFilter(ColorFilter paramColorFilter) {}
    
    private static final class a
      extends Drawable.ConstantState
    {
      public int getChangingConfigurations()
      {
        return 0;
      }
      
      public Drawable newDrawable()
      {
        return iw.a.gL();
      }
    }
  }
  
  static final class b
    extends Drawable.ConstantState
  {
    int Lh;
    int Li;
    
    b(b paramb)
    {
      if (paramb != null)
      {
        this.Lh = paramb.Lh;
        this.Li = paramb.Li;
      }
    }
    
    public int getChangingConfigurations()
    {
      return this.Lh;
    }
    
    public Drawable newDrawable()
    {
      return new iw(this);
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\internal\iw.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */