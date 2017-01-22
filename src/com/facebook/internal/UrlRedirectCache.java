package com.facebook.internal;

import android.content.Context;
import com.facebook.LoggingBehavior;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;

class UrlRedirectCache
{
  private static final String REDIRECT_CONTENT_TAG = TAG + "_Redirect";
  static final String TAG = UrlRedirectCache.class.getSimpleName();
  private static volatile FileLruCache urlRedirectCache;
  
  static void cacheUriRedirect(Context paramContext, URI paramURI1, URI paramURI2)
  {
    if ((paramURI1 == null) || (paramURI2 == null)) {}
    for (;;)
    {
      return;
      Context localContext2 = null;
      Context localContext1 = null;
      try
      {
        paramContext = getCache(paramContext).openPutStream(paramURI1.toString(), REDIRECT_CONTENT_TAG);
        localContext1 = paramContext;
        localContext2 = paramContext;
        paramContext.write(paramURI2.toString().getBytes());
        Utility.closeQuietly(paramContext);
      }
      catch (IOException paramContext)
      {
        Utility.closeQuietly(localContext1);
      }
      finally
      {
        Utility.closeQuietly(localContext2);
      }
    }
  }
  
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
      if (urlRedirectCache == null)
      {
        FileLruCache localFileLruCache = new com/facebook/internal/FileLruCache;
        paramContext = paramContext.getApplicationContext();
        String str = TAG;
        FileLruCache.Limits localLimits = new com/facebook/internal/FileLruCache$Limits;
        localLimits.<init>();
        localFileLruCache.<init>(paramContext, str, localLimits);
        urlRedirectCache = localFileLruCache;
      }
      paramContext = urlRedirectCache;
      return paramContext;
    }
    finally {}
  }
  
  /* Error */
  static URI getRedirectedUri(Context paramContext, URI paramURI)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 7
    //   3: aload_1
    //   4: ifnonnull +8 -> 12
    //   7: aload 7
    //   9: astore_0
    //   10: aload_0
    //   11: areturn
    //   12: aload_1
    //   13: invokevirtual 48	java/net/URI:toString	()Ljava/lang/String;
    //   16: astore_1
    //   17: aconst_null
    //   18: astore 6
    //   20: aconst_null
    //   21: astore 4
    //   23: aconst_null
    //   24: astore 5
    //   26: aload_0
    //   27: invokestatic 45	com/facebook/internal/UrlRedirectCache:getCache	(Landroid/content/Context;)Lcom/facebook/internal/FileLruCache;
    //   30: astore 8
    //   32: iconst_0
    //   33: istore_2
    //   34: aconst_null
    //   35: astore_0
    //   36: aload_1
    //   37: astore 4
    //   39: aload 8
    //   41: aload 4
    //   43: getstatic 35	com/facebook/internal/UrlRedirectCache:REDIRECT_CONTENT_TAG	Ljava/lang/String;
    //   46: invokevirtual 116	com/facebook/internal/FileLruCache:get	(Ljava/lang/String;Ljava/lang/String;)Ljava/io/InputStream;
    //   49: astore 5
    //   51: aload 5
    //   53: ifnull +149 -> 202
    //   56: iconst_1
    //   57: istore_2
    //   58: new 118	java/io/InputStreamReader
    //   61: astore_1
    //   62: aload_1
    //   63: aload 5
    //   65: invokespecial 121	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   68: aload_1
    //   69: astore 5
    //   71: aload_1
    //   72: astore 6
    //   74: aload_1
    //   75: astore 4
    //   77: sipush 128
    //   80: newarray <illegal type>
    //   82: astore_0
    //   83: aload_1
    //   84: astore 5
    //   86: aload_1
    //   87: astore 6
    //   89: aload_1
    //   90: astore 4
    //   92: new 21	java/lang/StringBuilder
    //   95: astore 9
    //   97: aload_1
    //   98: astore 5
    //   100: aload_1
    //   101: astore 6
    //   103: aload_1
    //   104: astore 4
    //   106: aload 9
    //   108: invokespecial 24	java/lang/StringBuilder:<init>	()V
    //   111: aload_1
    //   112: astore 5
    //   114: aload_1
    //   115: astore 6
    //   117: aload_1
    //   118: astore 4
    //   120: aload_1
    //   121: aload_0
    //   122: iconst_0
    //   123: aload_0
    //   124: arraylength
    //   125: invokevirtual 125	java/io/InputStreamReader:read	([CII)I
    //   128: istore_3
    //   129: iload_3
    //   130: ifle +36 -> 166
    //   133: aload_1
    //   134: astore 5
    //   136: aload_1
    //   137: astore 6
    //   139: aload_1
    //   140: astore 4
    //   142: aload 9
    //   144: aload_0
    //   145: iconst_0
    //   146: iload_3
    //   147: invokevirtual 128	java/lang/StringBuilder:append	([CII)Ljava/lang/StringBuilder;
    //   150: pop
    //   151: goto -40 -> 111
    //   154: astore_0
    //   155: aload 5
    //   157: invokestatic 72	com/facebook/internal/Utility:closeQuietly	(Ljava/io/Closeable;)V
    //   160: aload 7
    //   162: astore_0
    //   163: goto -153 -> 10
    //   166: aload_1
    //   167: astore 5
    //   169: aload_1
    //   170: astore 6
    //   172: aload_1
    //   173: astore 4
    //   175: aload_1
    //   176: invokestatic 72	com/facebook/internal/Utility:closeQuietly	(Ljava/io/Closeable;)V
    //   179: aload_1
    //   180: astore 5
    //   182: aload_1
    //   183: astore 6
    //   185: aload_1
    //   186: astore 4
    //   188: aload 9
    //   190: invokevirtual 33	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   193: astore_0
    //   194: aload_0
    //   195: astore 4
    //   197: aload_1
    //   198: astore_0
    //   199: goto -160 -> 39
    //   202: iload_2
    //   203: ifeq +22 -> 225
    //   206: new 47	java/net/URI
    //   209: dup
    //   210: aload 4
    //   212: invokespecial 131	java/net/URI:<init>	(Ljava/lang/String;)V
    //   215: astore_1
    //   216: aload_0
    //   217: invokestatic 72	com/facebook/internal/Utility:closeQuietly	(Ljava/io/Closeable;)V
    //   220: aload_1
    //   221: astore_0
    //   222: goto -212 -> 10
    //   225: aload_0
    //   226: invokestatic 72	com/facebook/internal/Utility:closeQuietly	(Ljava/io/Closeable;)V
    //   229: aload 7
    //   231: astore_0
    //   232: goto -222 -> 10
    //   235: astore_0
    //   236: aload 6
    //   238: invokestatic 72	com/facebook/internal/Utility:closeQuietly	(Ljava/io/Closeable;)V
    //   241: aload 7
    //   243: astore_0
    //   244: goto -234 -> 10
    //   247: astore_1
    //   248: aload 4
    //   250: invokestatic 72	com/facebook/internal/Utility:closeQuietly	(Ljava/io/Closeable;)V
    //   253: aload_1
    //   254: athrow
    //   255: astore_1
    //   256: aload_0
    //   257: astore 4
    //   259: goto -11 -> 248
    //   262: astore_1
    //   263: aload_0
    //   264: astore 6
    //   266: goto -30 -> 236
    //   269: astore_1
    //   270: aload_0
    //   271: astore 5
    //   273: goto -118 -> 155
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	276	0	paramContext	Context
    //   0	276	1	paramURI	URI
    //   33	170	2	i	int
    //   128	19	3	j	int
    //   21	237	4	localObject1	Object
    //   24	248	5	localObject2	Object
    //   18	247	6	localObject3	Object
    //   1	241	7	localObject4	Object
    //   30	10	8	localFileLruCache	FileLruCache
    //   95	94	9	localStringBuilder	StringBuilder
    // Exception table:
    //   from	to	target	type
    //   26	32	154	java/net/URISyntaxException
    //   77	83	154	java/net/URISyntaxException
    //   92	97	154	java/net/URISyntaxException
    //   106	111	154	java/net/URISyntaxException
    //   120	129	154	java/net/URISyntaxException
    //   142	151	154	java/net/URISyntaxException
    //   175	179	154	java/net/URISyntaxException
    //   188	194	154	java/net/URISyntaxException
    //   26	32	235	java/io/IOException
    //   77	83	235	java/io/IOException
    //   92	97	235	java/io/IOException
    //   106	111	235	java/io/IOException
    //   120	129	235	java/io/IOException
    //   142	151	235	java/io/IOException
    //   175	179	235	java/io/IOException
    //   188	194	235	java/io/IOException
    //   26	32	247	finally
    //   77	83	247	finally
    //   92	97	247	finally
    //   106	111	247	finally
    //   120	129	247	finally
    //   142	151	247	finally
    //   175	179	247	finally
    //   188	194	247	finally
    //   39	51	255	finally
    //   58	68	255	finally
    //   206	216	255	finally
    //   39	51	262	java/io/IOException
    //   58	68	262	java/io/IOException
    //   206	216	262	java/io/IOException
    //   39	51	269	java/net/URISyntaxException
    //   58	68	269	java/net/URISyntaxException
    //   206	216	269	java/net/URISyntaxException
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\facebook\internal\UrlRedirectCache.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */