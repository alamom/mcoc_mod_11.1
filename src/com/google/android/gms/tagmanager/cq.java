package com.google.android.gms.tagmanager;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.Resources.NotFoundException;
import com.google.android.gms.internal.c.f;
import com.google.android.gms.internal.ol.a;
import com.google.android.gms.internal.pm;
import com.google.android.gms.internal.pn;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.json.JSONException;

class cq
  implements o.f
{
  private final String aoc;
  private final ExecutorService aqA;
  private bg<ol.a> aqt;
  private final Context mContext;
  
  cq(Context paramContext, String paramString)
  {
    this.mContext = paramContext;
    this.aoc = paramString;
    this.aqA = Executors.newSingleThreadExecutor();
  }
  
  private cr.c a(ByteArrayOutputStream paramByteArrayOutputStream)
  {
    Object localObject = null;
    try
    {
      paramByteArrayOutputStream = ba.cG(paramByteArrayOutputStream.toString("UTF-8"));
      return paramByteArrayOutputStream;
    }
    catch (UnsupportedEncodingException paramByteArrayOutputStream)
    {
      for (;;)
      {
        bh.S("Failed to convert binary resource to string for JSON parsing; the file format is not UTF-8 format.");
        paramByteArrayOutputStream = (ByteArrayOutputStream)localObject;
      }
    }
    catch (JSONException paramByteArrayOutputStream)
    {
      for (;;)
      {
        bh.W("Failed to extract the container from the resource file. Resource is a UTF-8 encoded string but doesn't contain a JSON container");
        paramByteArrayOutputStream = (ByteArrayOutputStream)localObject;
      }
    }
  }
  
  private void d(ol.a parama)
    throws IllegalArgumentException
  {
    if ((parama.gs == null) && (parama.ass == null)) {
      throw new IllegalArgumentException("Resource and SupplementedResource are NULL.");
    }
  }
  
  private cr.c k(byte[] paramArrayOfByte)
  {
    try
    {
      cr.c localc = cr.b(c.f.a(paramArrayOfByte));
      paramArrayOfByte = localc;
      if (localc != null)
      {
        bh.V("The container was successfully loaded from the resource (using binary file)");
        paramArrayOfByte = localc;
      }
    }
    catch (pm paramArrayOfByte)
    {
      for (;;)
      {
        bh.T("The resource file is corrupted. The container cannot be extracted from the binary file");
        paramArrayOfByte = null;
      }
    }
    catch (cr.g paramArrayOfByte)
    {
      for (;;)
      {
        bh.W("The resource file is invalid. The container from the binary file is invalid");
        paramArrayOfByte = null;
      }
    }
    return paramArrayOfByte;
  }
  
  public void a(bg<ol.a> parambg)
  {
    this.aqt = parambg;
  }
  
  public void b(final ol.a parama)
  {
    this.aqA.execute(new Runnable()
    {
      public void run()
      {
        cq.this.c(parama);
      }
    });
  }
  
  boolean c(ol.a parama)
  {
    boolean bool = false;
    localFile = oS();
    try
    {
      FileOutputStream localFileOutputStream = new java/io/FileOutputStream;
      localFileOutputStream.<init>(localFile);
      try
      {
        localFileOutputStream.close();
        throw parama;
      }
      catch (IOException localIOException)
      {
        for (;;)
        {
          bh.W("error closing stream for writing resource to disk");
        }
      }
    }
    catch (FileNotFoundException parama)
    {
      for (;;)
      {
        try
        {
          localFileOutputStream.write(pn.f(parama));
          bool = true;
        }
        catch (IOException parama)
        {
          parama = parama;
          bh.W("Error writing resource to disk. Removing resource from disk.");
          localFile.delete();
          try
          {
            localFileOutputStream.close();
          }
          catch (IOException parama)
          {
            bh.W("error closing stream for writing resource to disk");
          }
          continue;
        }
        finally {}
        try
        {
          localFileOutputStream.close();
          return bool;
          parama = parama;
          bh.T("Error opening resource file for writing");
        }
        catch (IOException parama)
        {
          bh.W("error closing stream for writing resource to disk");
        }
      }
    }
  }
  
  public cr.c ff(int paramInt)
  {
    for (;;)
    {
      try
      {
        localObject1 = this.mContext.getResources().openRawResource(paramInt);
        bh.V("Attempting to load a container from the resource ID " + paramInt + " (" + this.mContext.getResources().getResourceName(paramInt) + ")");
      }
      catch (Resources.NotFoundException localNotFoundException)
      {
        Object localObject1;
        ByteArrayOutputStream localByteArrayOutputStream;
        bh.W("Failed to load the container. No default container resource found with the resource ID " + paramInt);
        cr.c localc = null;
        continue;
        localc = k(localByteArrayOutputStream.toByteArray());
        continue;
      }
      try
      {
        localByteArrayOutputStream = new java/io/ByteArrayOutputStream;
        localByteArrayOutputStream.<init>();
        cr.b((InputStream)localObject1, localByteArrayOutputStream);
        localObject1 = a(localByteArrayOutputStream);
        if (localObject1 == null) {
          continue;
        }
        bh.V("The container was successfully loaded from the resource (using JSON file format)");
      }
      catch (IOException localIOException)
      {
        bh.W("Error reading the default container with resource ID " + paramInt + " (" + this.mContext.getResources().getResourceName(paramInt) + ")");
        Object localObject2 = null;
      }
    }
    return (cr.c)localObject1;
  }
  
  /* Error */
  void oR()
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 121	com/google/android/gms/tagmanager/cq:aqt	Lcom/google/android/gms/tagmanager/bg;
    //   4: ifnonnull +13 -> 17
    //   7: new 232	java/lang/IllegalStateException
    //   10: dup
    //   11: ldc -22
    //   13: invokespecial 235	java/lang/IllegalStateException:<init>	(Ljava/lang/String;)V
    //   16: athrow
    //   17: aload_0
    //   18: getfield 121	com/google/android/gms/tagmanager/cq:aqt	Lcom/google/android/gms/tagmanager/bg;
    //   21: invokeinterface 240 1 0
    //   26: ldc -14
    //   28: invokestatic 111	com/google/android/gms/tagmanager/bh:V	(Ljava/lang/String;)V
    //   31: invokestatic 248	com/google/android/gms/tagmanager/ce:oJ	()Lcom/google/android/gms/tagmanager/ce;
    //   34: invokevirtual 252	com/google/android/gms/tagmanager/ce:oK	()Lcom/google/android/gms/tagmanager/ce$a;
    //   37: getstatic 258	com/google/android/gms/tagmanager/ce$a:aqi	Lcom/google/android/gms/tagmanager/ce$a;
    //   40: if_acmpeq +15 -> 55
    //   43: invokestatic 248	com/google/android/gms/tagmanager/ce:oJ	()Lcom/google/android/gms/tagmanager/ce;
    //   46: invokevirtual 252	com/google/android/gms/tagmanager/ce:oK	()Lcom/google/android/gms/tagmanager/ce$a;
    //   49: getstatic 261	com/google/android/gms/tagmanager/ce$a:aqj	Lcom/google/android/gms/tagmanager/ce$a;
    //   52: if_acmpne +32 -> 84
    //   55: aload_0
    //   56: getfield 28	com/google/android/gms/tagmanager/cq:aoc	Ljava/lang/String;
    //   59: invokestatic 248	com/google/android/gms/tagmanager/ce:oJ	()Lcom/google/android/gms/tagmanager/ce;
    //   62: invokevirtual 264	com/google/android/gms/tagmanager/ce:getContainerId	()Ljava/lang/String;
    //   65: invokevirtual 270	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   68: ifeq +16 -> 84
    //   71: aload_0
    //   72: getfield 121	com/google/android/gms/tagmanager/cq:aqt	Lcom/google/android/gms/tagmanager/bg;
    //   75: getstatic 276	com/google/android/gms/tagmanager/bg$a:apM	Lcom/google/android/gms/tagmanager/bg$a;
    //   78: invokeinterface 279 2 0
    //   83: return
    //   84: new 281	java/io/FileInputStream
    //   87: astore_1
    //   88: aload_1
    //   89: aload_0
    //   90: invokevirtual 142	com/google/android/gms/tagmanager/cq:oS	()Ljava/io/File;
    //   93: invokespecial 282	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   96: new 47	java/io/ByteArrayOutputStream
    //   99: astore_2
    //   100: aload_2
    //   101: invokespecial 212	java/io/ByteArrayOutputStream:<init>	()V
    //   104: aload_1
    //   105: aload_2
    //   106: invokestatic 215	com/google/android/gms/tagmanager/cr:b	(Ljava/io/InputStream;Ljava/io/OutputStream;)V
    //   109: aload_2
    //   110: invokevirtual 225	java/io/ByteArrayOutputStream:toByteArray	()[B
    //   113: invokestatic 286	com/google/android/gms/internal/ol$a:l	([B)Lcom/google/android/gms/internal/ol$a;
    //   116: astore_2
    //   117: aload_0
    //   118: aload_2
    //   119: invokespecial 288	com/google/android/gms/tagmanager/cq:d	(Lcom/google/android/gms/internal/ol$a;)V
    //   122: aload_0
    //   123: getfield 121	com/google/android/gms/tagmanager/cq:aqt	Lcom/google/android/gms/tagmanager/bg;
    //   126: aload_2
    //   127: invokeinterface 291 2 0
    //   132: aload_1
    //   133: invokevirtual 292	java/io/FileInputStream:close	()V
    //   136: ldc_w 294
    //   139: invokestatic 111	com/google/android/gms/tagmanager/bh:V	(Ljava/lang/String;)V
    //   142: goto -59 -> 83
    //   145: astore_1
    //   146: ldc_w 296
    //   149: invokestatic 65	com/google/android/gms/tagmanager/bh:S	(Ljava/lang/String;)V
    //   152: aload_0
    //   153: getfield 121	com/google/android/gms/tagmanager/cq:aqt	Lcom/google/android/gms/tagmanager/bg;
    //   156: getstatic 276	com/google/android/gms/tagmanager/bg$a:apM	Lcom/google/android/gms/tagmanager/bg$a;
    //   159: invokeinterface 279 2 0
    //   164: goto -81 -> 83
    //   167: astore_1
    //   168: ldc_w 298
    //   171: invokestatic 70	com/google/android/gms/tagmanager/bh:W	(Ljava/lang/String;)V
    //   174: goto -38 -> 136
    //   177: astore_2
    //   178: aload_0
    //   179: getfield 121	com/google/android/gms/tagmanager/cq:aqt	Lcom/google/android/gms/tagmanager/bg;
    //   182: getstatic 301	com/google/android/gms/tagmanager/bg$a:apN	Lcom/google/android/gms/tagmanager/bg$a;
    //   185: invokeinterface 279 2 0
    //   190: ldc_w 303
    //   193: invokestatic 70	com/google/android/gms/tagmanager/bh:W	(Ljava/lang/String;)V
    //   196: aload_1
    //   197: invokevirtual 292	java/io/FileInputStream:close	()V
    //   200: goto -64 -> 136
    //   203: astore_1
    //   204: ldc_w 298
    //   207: invokestatic 70	com/google/android/gms/tagmanager/bh:W	(Ljava/lang/String;)V
    //   210: goto -74 -> 136
    //   213: astore_2
    //   214: aload_0
    //   215: getfield 121	com/google/android/gms/tagmanager/cq:aqt	Lcom/google/android/gms/tagmanager/bg;
    //   218: getstatic 301	com/google/android/gms/tagmanager/bg$a:apN	Lcom/google/android/gms/tagmanager/bg$a;
    //   221: invokeinterface 279 2 0
    //   226: ldc_w 305
    //   229: invokestatic 70	com/google/android/gms/tagmanager/bh:W	(Ljava/lang/String;)V
    //   232: aload_1
    //   233: invokevirtual 292	java/io/FileInputStream:close	()V
    //   236: goto -100 -> 136
    //   239: astore_1
    //   240: ldc_w 298
    //   243: invokestatic 70	com/google/android/gms/tagmanager/bh:W	(Ljava/lang/String;)V
    //   246: goto -110 -> 136
    //   249: astore_2
    //   250: aload_1
    //   251: invokevirtual 292	java/io/FileInputStream:close	()V
    //   254: aload_2
    //   255: athrow
    //   256: astore_1
    //   257: ldc_w 298
    //   260: invokestatic 70	com/google/android/gms/tagmanager/bh:W	(Ljava/lang/String;)V
    //   263: goto -9 -> 254
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	266	0	this	cq
    //   87	46	1	localFileInputStream	java.io.FileInputStream
    //   145	1	1	localFileNotFoundException	FileNotFoundException
    //   167	30	1	localIOException1	IOException
    //   203	30	1	localIOException2	IOException
    //   239	12	1	localIOException3	IOException
    //   256	1	1	localIOException4	IOException
    //   99	28	2	localObject1	Object
    //   177	1	2	localIOException5	IOException
    //   213	1	2	localIllegalArgumentException	IllegalArgumentException
    //   249	6	2	localObject2	Object
    // Exception table:
    //   from	to	target	type
    //   84	96	145	java/io/FileNotFoundException
    //   132	136	167	java/io/IOException
    //   96	132	177	java/io/IOException
    //   196	200	203	java/io/IOException
    //   96	132	213	java/lang/IllegalArgumentException
    //   232	236	239	java/io/IOException
    //   96	132	249	finally
    //   178	196	249	finally
    //   214	232	249	finally
    //   250	254	256	java/io/IOException
  }
  
  File oS()
  {
    String str = "resource_" + this.aoc;
    return new File(this.mContext.getDir("google_tagmanager", 0), str);
  }
  
  public void oc()
  {
    this.aqA.execute(new Runnable()
    {
      public void run()
      {
        cq.this.oR();
      }
    });
  }
  
  public void release()
  {
    try
    {
      this.aqA.shutdown();
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\tagmanager\cq.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */