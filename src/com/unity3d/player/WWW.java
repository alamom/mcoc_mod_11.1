package com.unity3d.player;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

class WWW
  extends Thread
{
  private int a;
  private int b;
  private String c;
  private byte[] d;
  private Map e;
  
  WWW(int paramInt, String paramString, byte[] paramArrayOfByte, Map paramMap)
  {
    this.b = paramInt;
    this.c = paramString;
    this.d = paramArrayOfByte;
    this.e = paramMap;
    this.a = 0;
  }
  
  private static native void doneCallback(int paramInt);
  
  private static native void errorCallback(int paramInt, String paramString);
  
  private static native boolean headerCallback(int paramInt, String paramString);
  
  private static native void progressCallback(int paramInt1, float paramFloat1, float paramFloat2, double paramDouble, int paramInt2);
  
  private static native boolean readCallback(int paramInt1, byte[] paramArrayOfByte, int paramInt2);
  
  protected boolean headerCallback(String paramString1, String paramString2)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(paramString1);
    localStringBuilder.append(": ");
    localStringBuilder.append(paramString2);
    localStringBuilder.append("\n\r");
    return headerCallback(this.b, localStringBuilder.toString());
  }
  
  protected boolean headerCallback(Map paramMap)
  {
    if ((paramMap == null) || (paramMap.size() == 0)) {}
    StringBuilder localStringBuilder;
    for (boolean bool = false;; bool = headerCallback(this.b, localStringBuilder.toString()))
    {
      return bool;
      localStringBuilder = new StringBuilder();
      paramMap = paramMap.entrySet().iterator();
      while (paramMap.hasNext())
      {
        Map.Entry localEntry = (Map.Entry)paramMap.next();
        Object localObject1 = ((List)localEntry.getValue()).iterator();
        Object localObject2;
        while (((Iterator)localObject1).hasNext())
        {
          localObject2 = (String)((Iterator)localObject1).next();
          localStringBuilder.append((String)localEntry.getKey());
          localStringBuilder.append(": ");
          localStringBuilder.append((String)localObject2);
          localStringBuilder.append("\r\n");
        }
        if (localEntry.getKey() == null)
        {
          localObject2 = ((List)localEntry.getValue()).iterator();
          while (((Iterator)localObject2).hasNext())
          {
            localObject1 = (String)((Iterator)localObject2).next();
            localStringBuilder.append("Status: ");
            localStringBuilder.append((String)localObject1);
            localStringBuilder.append("\r\n");
          }
        }
      }
    }
  }
  
  protected void progressCallback(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong1, long paramLong2)
  {
    float f1;
    float f2;
    if (paramInt4 > 0)
    {
      f1 = paramInt3 / paramInt4;
      f2 = 1.0F;
      paramInt1 = Math.max(paramInt4 - paramInt3, 0);
      d1 = 1000.0D * paramInt3 / Math.max(paramLong1 - paramLong2, 0.1D);
      double d2 = paramInt1 / d1;
      if (!Double.isInfinite(d2))
      {
        d1 = d2;
        if (!Double.isNaN(d2)) {
          break label76;
        }
      }
    }
    for (double d1 = 0.0D;; d1 = 0.0D)
    {
      label76:
      progressCallback(this.b, f2, f1, d1, paramInt4);
      do
      {
        return;
      } while (paramInt2 <= 0);
      f1 = 0.0F;
      f2 = paramInt1 / paramInt2;
    }
  }
  
  protected boolean readCallback(byte[] paramArrayOfByte, int paramInt)
  {
    return readCallback(this.b, paramArrayOfByte, paramInt);
  }
  
  public void run()
  {
    try
    {
      runSafe();
      return;
    }
    catch (Throwable localThrowable)
    {
      for (;;)
      {
        errorCallback(this.b, "Error: " + localThrowable.toString());
      }
    }
  }
  
  public void runSafe()
  {
    int i = this.a + 1;
    this.a = i;
    if (i > 5) {
      errorCallback(this.b, "Too many redirects");
    }
    URLConnection localURLConnection;
    label155:
    Object localObject4;
    int j;
    for (;;)
    {
      return;
      try
      {
        URL localURL = new java/net/URL;
        localURL.<init>(this.c);
        localURLConnection = localURL.openConnection();
        if ((!localURL.getProtocol().equalsIgnoreCase("file")) || (localURL.getHost() == null) || (localURL.getHost().length() == 0)) {
          break label155;
        }
        errorCallback(this.b, localURL.getHost() + localURL.getFile() + " is not an absolute path!");
      }
      catch (MalformedURLException localMalformedURLException)
      {
        errorCallback(this.b, localMalformedURLException.toString());
      }
      catch (IOException localIOException1)
      {
        errorCallback(this.b, localIOException1.toString());
      }
      continue;
      if (this.e != null)
      {
        localObject4 = this.e.entrySet().iterator();
        while (((Iterator)localObject4).hasNext())
        {
          localObject3 = (Map.Entry)((Iterator)localObject4).next();
          localURLConnection.addRequestProperty((String)((Map.Entry)localObject3).getKey(), (String)((Map.Entry)localObject3).getValue());
        }
      }
      if (this.d != null)
      {
        localURLConnection.setDoOutput(true);
        try
        {
          localObject3 = localURLConnection.getOutputStream();
          i = 0;
          while (i < this.d.length)
          {
            j = Math.min(1428, this.d.length - i);
            ((OutputStream)localObject3).write(this.d, i, j);
            i += j;
            progressCallback(i, this.d.length, 0, 0, 0L, 0L);
          }
        }
        catch (Exception localException1)
        {
          errorCallback(this.b, localException1.toString());
        }
      }
      else
      {
        if (!(localURLConnection instanceof HttpURLConnection)) {
          break;
        }
        localObject3 = (HttpURLConnection)localURLConnection;
        try
        {
          i = ((HttpURLConnection)localObject3).getResponseCode();
          localObject4 = ((HttpURLConnection)localObject3).getHeaderFields();
          if ((localObject4 == null) || ((i != 301) && (i != 302))) {
            break;
          }
          localObject4 = (List)((Map)localObject4).get("Location");
          if ((localObject4 == null) || (((List)localObject4).isEmpty())) {
            break;
          }
          ((HttpURLConnection)localObject3).disconnect();
          this.c = ((String)((List)localObject4).get(0));
          run();
        }
        catch (IOException localIOException2)
        {
          errorCallback(this.b, localIOException2.toString());
        }
      }
    }
    Object localObject3 = localURLConnection.getHeaderFields();
    boolean bool2 = headerCallback((Map)localObject3);
    boolean bool1;
    if (localObject3 != null)
    {
      bool1 = bool2;
      if (((Map)localObject3).containsKey("content-length")) {}
    }
    else
    {
      bool1 = bool2;
      if (localURLConnection.getContentLength() != -1)
      {
        if ((!bool2) && (!headerCallback("content-length", String.valueOf(localURLConnection.getContentLength())))) {
          break label609;
        }
        bool1 = true;
      }
    }
    label516:
    if (localObject3 != null)
    {
      bool2 = bool1;
      if (((Map)localObject3).containsKey("content-type")) {}
    }
    else
    {
      bool2 = bool1;
      if (localURLConnection.getContentType() != null) {
        if ((!bool1) && (!headerCallback("content-type", localURLConnection.getContentType()))) {
          break label615;
        }
      }
    }
    label609:
    label615:
    for (bool2 = true;; bool2 = false)
    {
      if (!bool2) {
        break label621;
      }
      errorCallback(this.b, this.c + " aborted");
      break;
      bool1 = false;
      break label516;
    }
    label621:
    if (localURLConnection.getContentLength() > 0)
    {
      j = localURLConnection.getContentLength();
      label635:
      if ((!localIOException2.getProtocol().equalsIgnoreCase("file")) && (!localIOException2.getProtocol().equalsIgnoreCase("jar"))) {
        break label942;
      }
      if (j != 0) {
        break label839;
      }
      i = 32768;
    }
    for (;;)
    {
      label670:
      int m = 0;
      for (;;)
      {
        long l;
        try
        {
          l = System.currentTimeMillis();
          localObject4 = new byte[i];
          if (!(localURLConnection instanceof HttpURLConnection)) {
            break label931;
          }
          HttpURLConnection localHttpURLConnection = (HttpURLConnection)localURLConnection;
          Object localObject1 = localHttpURLConnection.getErrorStream();
          localObject3 = new java/lang/StringBuilder;
          ((StringBuilder)localObject3).<init>();
          localObject3 = localHttpURLConnection.getResponseCode() + ": " + localHttpURLConnection.getResponseMessage();
          if (localObject1 != null) {
            break label926;
          }
          localObject1 = localURLConnection.getInputStream();
          i = 0;
          n = 0;
          if (n == -1) {
            break label891;
          }
          if (!readCallback((byte[])localObject4, n)) {
            break label850;
          }
          i = this.b;
          localObject1 = new java/lang/StringBuilder;
          ((StringBuilder)localObject1).<init>();
          errorCallback(i, this.c + " aborted");
        }
        catch (Exception localException2)
        {
          errorCallback(this.b, localException2.toString());
        }
        break;
        j = 0;
        break label635;
        label839:
        i = Math.min(j, 32768);
        break label670;
        label850:
        int k = m;
        if (i == 0)
        {
          k = m + n;
          progressCallback(0, 0, k, j, System.currentTimeMillis(), l);
        }
        int n = localException2.read((byte[])localObject4);
        m = k;
        continue;
        label891:
        if (i != 0) {
          errorCallback(this.b, (String)localObject3);
        }
        progressCallback(0, 0, m, m, 0L, 0L);
        doneCallback(this.b);
        break;
        label926:
        i = 1;
        continue;
        label931:
        localObject3 = "";
        Object localObject2 = null;
      }
      label942:
      i = 1428;
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\unity3d\player\WWW.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */