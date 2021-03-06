package com.facebook.internal;

import android.graphics.Bitmap;

public class ImageResponse
{
  private Bitmap bitmap;
  private Exception error;
  private boolean isCachedRedirect;
  private ImageRequest request;
  
  ImageResponse(ImageRequest paramImageRequest, Exception paramException, boolean paramBoolean, Bitmap paramBitmap)
  {
    this.request = paramImageRequest;
    this.error = paramException;
    this.bitmap = paramBitmap;
    this.isCachedRedirect = paramBoolean;
  }
  
  public Bitmap getBitmap()
  {
    return this.bitmap;
  }
  
  public Exception getError()
  {
    return this.error;
  }
  
  public ImageRequest getRequest()
  {
    return this.request;
  }
  
  public boolean isCachedRedirect()
  {
    return this.isCachedRedirect;
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\facebook\internal\ImageResponse.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */