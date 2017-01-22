package com.google.android.gms.internal;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

public final class ix
{
  public static Bitmap a(Bitmap paramBitmap)
  {
    int j = 0;
    if (paramBitmap == null)
    {
      paramBitmap = null;
      return paramBitmap;
    }
    int m = paramBitmap.getWidth();
    int i = paramBitmap.getHeight();
    int k;
    if (m >= i)
    {
      j = m / 2 - i / 2;
      k = 0;
    }
    for (;;)
    {
      Bitmap localBitmap = Bitmap.createBitmap(i, i, Bitmap.Config.ARGB_8888);
      Canvas localCanvas = new Canvas(localBitmap);
      Paint localPaint = new Paint(1);
      localPaint.setColor(-16777216);
      localCanvas.drawCircle(i / 2, i / 2, i / 2, localPaint);
      localPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
      localCanvas.drawBitmap(paramBitmap, j, k, localPaint);
      paramBitmap = localBitmap;
      break;
      k = i / 2 - m / 2;
      i = m;
    }
  }
  
  private static Bitmap a(Drawable paramDrawable)
  {
    if (paramDrawable == null) {
      paramDrawable = null;
    }
    for (;;)
    {
      return paramDrawable;
      if ((paramDrawable instanceof BitmapDrawable))
      {
        paramDrawable = ((BitmapDrawable)paramDrawable).getBitmap();
      }
      else
      {
        Bitmap localBitmap = Bitmap.createBitmap(paramDrawable.getIntrinsicWidth(), paramDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas localCanvas = new Canvas(localBitmap);
        paramDrawable.setBounds(0, 0, localCanvas.getWidth(), localCanvas.getHeight());
        paramDrawable.draw(localCanvas);
        paramDrawable = localBitmap;
      }
    }
  }
  
  public static Drawable a(Resources paramResources, Drawable paramDrawable)
  {
    return new BitmapDrawable(paramResources, a(a(paramDrawable)));
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\internal\ix.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */