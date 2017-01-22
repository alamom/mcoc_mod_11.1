package com.facebook.internal;

import android.content.Context;
import com.facebook.LoggingBehavior;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

class ImageResponseCache
{
  static final String TAG = ImageResponseCache.class.getSimpleName();
  private static volatile FileLruCache imageCache;
  
  static void clearCache(Context paramContext)
  {
    try
    {
      getCache(paramContext).clearCache();
      return;
    }
    catch (IOException paramContext)
    {
      for (;;)
      {
        Logger.log(LoggingBehavior.CACHE, 5, TAG, "clearCache failed " + paramContext.getMessage());
      }
    }
  }
  
  static FileLruCache getCache(Context paramContext)
    throws IOException
  {
    try
    {
      if (imageCache == null)
      {
        FileLruCache localFileLruCache = new com/facebook/internal/FileLruCache;
        Context localContext = paramContext.getApplicationContext();
        paramContext = TAG;
        FileLruCache.Limits localLimits = new com/facebook/internal/FileLruCache$Limits;
        localLimits.<init>();
        localFileLruCache.<init>(localContext, paramContext, localLimits);
        imageCache = localFileLruCache;
      }
      paramContext = imageCache;
      return paramContext;
    }
    finally {}
  }
  
  static InputStream getCachedImageStream(URI paramURI, Context paramContext)
  {
    Object localObject2 = null;
    Object localObject1 = localObject2;
    if (paramURI != null)
    {
      localObject1 = localObject2;
      if (!isCDNURL(paramURI)) {}
    }
    try
    {
      localObject1 = getCache(paramContext).get(paramURI.toString());
      return (InputStream)localObject1;
    }
    catch (IOException paramURI)
    {
      for (;;)
      {
        Logger.log(LoggingBehavior.CACHE, 5, TAG, paramURI.toString());
        localObject1 = localObject2;
      }
    }
  }
  
  static InputStream interceptAndCacheImageStream(Context paramContext, HttpURLConnection paramHttpURLConnection)
    throws IOException
  {
    Object localObject1 = null;
    Object localObject2;
    InputStream localInputStream;
    if (paramHttpURLConnection.getResponseCode() == 200)
    {
      localObject2 = paramHttpURLConnection.getURL();
      localInputStream = paramHttpURLConnection.getInputStream();
      localObject1 = localInputStream;
    }
    try
    {
      if (isCDNURL(((URL)localObject2).toURI()))
      {
        paramContext = getCache(paramContext);
        localObject2 = ((URL)localObject2).toString();
        localObject1 = new com/facebook/internal/ImageResponseCache$BufferedHttpInputStream;
        ((BufferedHttpInputStream)localObject1).<init>(localInputStream, paramHttpURLConnection);
        localObject1 = paramContext.interceptAndPut((String)localObject2, (InputStream)localObject1);
      }
      return (InputStream)localObject1;
    }
    catch (URISyntaxException paramContext)
    {
      for (;;)
      {
        localObject1 = localInputStream;
      }
    }
    catch (IOException paramContext)
    {
      for (;;)
      {
        localObject1 = localInputStream;
      }
    }
  }
  
  private static boolean isCDNURL(URI paramURI)
  {
    boolean bool = true;
    if (paramURI != null)
    {
      paramURI = paramURI.getHost();
      if (!paramURI.endsWith("fbcdn.net")) {}
    }
    for (;;)
    {
      return bool;
      if ((!paramURI.startsWith("fbcdn")) || (!paramURI.endsWith("akamaihd.net"))) {
        bool = false;
      }
    }
  }
  
  private static class BufferedHttpInputStream
    extends BufferedInputStream
  {
    HttpURLConnection connection;
    
    BufferedHttpInputStream(InputStream paramInputStream, HttpURLConnection paramHttpURLConnection)
    {
      super(8192);
      this.connection = paramHttpURLConnection;
    }
    
    public void close()
      throws IOException
    {
      super.close();
      Utility.disconnectQuietly(this.connection);
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\facebook\internal\ImageResponseCache.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */