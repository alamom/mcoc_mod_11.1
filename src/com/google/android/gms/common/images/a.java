package com.google.android.gms.common.images;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.widget.ImageView;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.internal.iw;
import com.google.android.gms.internal.ix;
import com.google.android.gms.internal.iy;
import com.google.android.gms.internal.iz;
import com.google.android.gms.internal.iz.a;
import java.lang.ref.WeakReference;

public abstract class a
{
  final a KH;
  protected int KI = 0;
  protected int KJ = 0;
  protected ImageManager.OnImageLoadedListener KK;
  private boolean KL = true;
  private boolean KM = false;
  protected int KN;
  
  public a(Uri paramUri, int paramInt)
  {
    this.KH = new a(paramUri);
    this.KJ = paramInt;
  }
  
  private Drawable a(Context paramContext, iz paramiz, int paramInt)
  {
    Resources localResources = paramContext.getResources();
    if (this.KN > 0)
    {
      iz.a locala = new iz.a(paramInt, this.KN);
      Drawable localDrawable = (Drawable)paramiz.get(locala);
      paramContext = localDrawable;
      if (localDrawable == null)
      {
        localDrawable = localResources.getDrawable(paramInt);
        paramContext = localDrawable;
        if ((this.KN & 0x1) != 0) {
          paramContext = a(localResources, localDrawable);
        }
        paramiz.put(locala, paramContext);
      }
    }
    for (;;)
    {
      return paramContext;
      paramContext = localResources.getDrawable(paramInt);
    }
  }
  
  protected Drawable a(Resources paramResources, Drawable paramDrawable)
  {
    return ix.a(paramResources, paramDrawable);
  }
  
  protected iw a(Drawable paramDrawable1, Drawable paramDrawable2)
  {
    if (paramDrawable1 != null)
    {
      localDrawable = paramDrawable1;
      if (!(paramDrawable1 instanceof iw)) {}
    }
    for (Drawable localDrawable = ((iw)paramDrawable1).gK();; localDrawable = null) {
      return new iw(localDrawable, paramDrawable2);
    }
  }
  
  void a(Context paramContext, Bitmap paramBitmap, boolean paramBoolean)
  {
    com.google.android.gms.common.internal.a.f(paramBitmap);
    Bitmap localBitmap = paramBitmap;
    if ((this.KN & 0x1) != 0) {
      localBitmap = ix.a(paramBitmap);
    }
    paramContext = new BitmapDrawable(paramContext.getResources(), localBitmap);
    if (this.KK != null) {
      this.KK.onImageLoaded(this.KH.uri, paramContext, true);
    }
    a(paramContext, paramBoolean, false, true);
  }
  
  void a(Context paramContext, iz paramiz)
  {
    Drawable localDrawable = null;
    if (this.KI != 0) {
      localDrawable = a(paramContext, paramiz, this.KI);
    }
    a(localDrawable, false, true, false);
  }
  
  void a(Context paramContext, iz paramiz, boolean paramBoolean)
  {
    Drawable localDrawable = null;
    if (this.KJ != 0) {
      localDrawable = a(paramContext, paramiz, this.KJ);
    }
    if (this.KK != null) {
      this.KK.onImageLoaded(this.KH.uri, localDrawable, false);
    }
    a(localDrawable, paramBoolean, false, false);
  }
  
  protected abstract void a(Drawable paramDrawable, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3);
  
  public void aw(int paramInt)
  {
    this.KJ = paramInt;
  }
  
  protected boolean b(boolean paramBoolean1, boolean paramBoolean2)
  {
    if ((this.KL) && (!paramBoolean2) && ((!paramBoolean1) || (this.KM))) {}
    for (paramBoolean1 = true;; paramBoolean1 = false) {
      return paramBoolean1;
    }
  }
  
  static final class a
  {
    public final Uri uri;
    
    public a(Uri paramUri)
    {
      this.uri = paramUri;
    }
    
    public boolean equals(Object paramObject)
    {
      boolean bool;
      if (!(paramObject instanceof a)) {
        bool = false;
      }
      for (;;)
      {
        return bool;
        if (this == paramObject) {
          bool = true;
        } else {
          bool = n.equal(((a)paramObject).uri, this.uri);
        }
      }
    }
    
    public int hashCode()
    {
      return n.hashCode(new Object[] { this.uri });
    }
  }
  
  public static final class b
    extends a
  {
    private WeakReference<ImageView> KO;
    
    public b(ImageView paramImageView, int paramInt)
    {
      super(paramInt);
      com.google.android.gms.common.internal.a.f(paramImageView);
      this.KO = new WeakReference(paramImageView);
    }
    
    public b(ImageView paramImageView, Uri paramUri)
    {
      super(0);
      com.google.android.gms.common.internal.a.f(paramImageView);
      this.KO = new WeakReference(paramImageView);
    }
    
    private void a(ImageView paramImageView, Drawable paramDrawable, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3)
    {
      if ((!paramBoolean2) && (!paramBoolean3)) {}
      for (int i = 1; (i != 0) && ((paramImageView instanceof iy)); i = 0)
      {
        int j = ((iy)paramImageView).gM();
        if ((this.KJ == 0) || (j != this.KJ)) {
          break;
        }
        return;
      }
      paramBoolean1 = b(paramBoolean1, paramBoolean2);
      if (paramBoolean1) {
        paramDrawable = a(paramImageView.getDrawable(), paramDrawable);
      }
      for (;;)
      {
        paramImageView.setImageDrawable(paramDrawable);
        iy localiy;
        if ((paramImageView instanceof iy))
        {
          localiy = (iy)paramImageView;
          if (!paramBoolean3) {
            break label151;
          }
          paramImageView = this.KH.uri;
          label110:
          localiy.g(paramImageView);
          if (i == 0) {
            break label156;
          }
        }
        label151:
        label156:
        for (i = this.KJ;; i = 0)
        {
          localiy.ay(i);
          if (!paramBoolean1) {
            break;
          }
          ((iw)paramDrawable).startTransition(250);
          break;
          paramImageView = null;
          break label110;
        }
      }
    }
    
    protected void a(Drawable paramDrawable, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3)
    {
      ImageView localImageView = (ImageView)this.KO.get();
      if (localImageView != null) {
        a(localImageView, paramDrawable, paramBoolean1, paramBoolean2, paramBoolean3);
      }
    }
    
    public boolean equals(Object paramObject)
    {
      boolean bool = false;
      if (!(paramObject instanceof b)) {}
      for (;;)
      {
        return bool;
        if (this != paramObject) {
          break;
        }
        bool = true;
      }
      Object localObject = (b)paramObject;
      paramObject = (ImageView)this.KO.get();
      localObject = (ImageView)((b)localObject).KO.get();
      if ((localObject != null) && (paramObject != null) && (n.equal(localObject, paramObject))) {}
      for (bool = true;; bool = false) {
        break;
      }
    }
    
    public int hashCode()
    {
      return 0;
    }
  }
  
  public static final class c
    extends a
  {
    private WeakReference<ImageManager.OnImageLoadedListener> KP;
    
    public c(ImageManager.OnImageLoadedListener paramOnImageLoadedListener, Uri paramUri)
    {
      super(0);
      com.google.android.gms.common.internal.a.f(paramOnImageLoadedListener);
      this.KP = new WeakReference(paramOnImageLoadedListener);
    }
    
    protected void a(Drawable paramDrawable, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3)
    {
      if (!paramBoolean2)
      {
        ImageManager.OnImageLoadedListener localOnImageLoadedListener = (ImageManager.OnImageLoadedListener)this.KP.get();
        if (localOnImageLoadedListener != null) {
          localOnImageLoadedListener.onImageLoaded(this.KH.uri, paramDrawable, paramBoolean3);
        }
      }
    }
    
    public boolean equals(Object paramObject)
    {
      boolean bool = false;
      if (!(paramObject instanceof c)) {}
      for (;;)
      {
        return bool;
        if (this != paramObject) {
          break;
        }
        bool = true;
      }
      paramObject = (c)paramObject;
      ImageManager.OnImageLoadedListener localOnImageLoadedListener2 = (ImageManager.OnImageLoadedListener)this.KP.get();
      ImageManager.OnImageLoadedListener localOnImageLoadedListener1 = (ImageManager.OnImageLoadedListener)((c)paramObject).KP.get();
      if ((localOnImageLoadedListener1 != null) && (localOnImageLoadedListener2 != null) && (n.equal(localOnImageLoadedListener1, localOnImageLoadedListener2)) && (n.equal(((c)paramObject).KH, this.KH))) {}
      for (bool = true;; bool = false) {
        break;
      }
    }
    
    public int hashCode()
    {
      return n.hashCode(new Object[] { this.KH });
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\common\images\a.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */