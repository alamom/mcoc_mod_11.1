package com.google.android.gms.maps.model;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

public abstract class UrlTileProvider
  implements TileProvider
{
  private final int lf;
  private final int lg;
  
  public UrlTileProvider(int paramInt1, int paramInt2)
  {
    this.lf = paramInt1;
    this.lg = paramInt2;
  }
  
  private static long a(InputStream paramInputStream, OutputStream paramOutputStream)
    throws IOException
  {
    byte[] arrayOfByte = new byte['က'];
    int i;
    for (long l = 0L;; l += i)
    {
      i = paramInputStream.read(arrayOfByte);
      if (i == -1) {
        return l;
      }
      paramOutputStream.write(arrayOfByte, 0, i);
    }
  }
  
  private static byte[] e(InputStream paramInputStream)
    throws IOException
  {
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    a(paramInputStream, localByteArrayOutputStream);
    return localByteArrayOutputStream.toByteArray();
  }
  
  public final Tile getTile(int paramInt1, int paramInt2, int paramInt3)
  {
    URL localURL = getTileUrl(paramInt1, paramInt2, paramInt3);
    Tile localTile;
    if (localURL == null) {
      localTile = NO_TILE;
    }
    for (;;)
    {
      return localTile;
      try
      {
        localTile = new com/google/android/gms/maps/model/Tile;
        localTile.<init>(this.lf, this.lg, e(localURL.openStream()));
      }
      catch (IOException localIOException)
      {
        Object localObject = null;
      }
    }
  }
  
  public abstract URL getTileUrl(int paramInt1, int paramInt2, int paramInt3);
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\maps\model\UrlTileProvider.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */