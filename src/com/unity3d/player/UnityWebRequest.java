package com.unity3d.player;

import java.nio.ByteBuffer;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;

class UnityWebRequest
  implements Runnable
{
  private static final String[] e = { "TLSv1.2", "TLSv1.1" };
  private static volatile SSLSocketFactory f;
  private long a;
  private String b;
  private String c;
  private Map d;
  
  UnityWebRequest(long paramLong, String paramString1, Map paramMap, String paramString2)
  {
    this.a = paramLong;
    this.b = paramString2;
    this.c = paramString1;
    this.d = paramMap;
  }
  
  private static native void contentLengthCallback(long paramLong, int paramInt);
  
  private static native boolean downloadCallback(long paramLong, ByteBuffer paramByteBuffer, int paramInt);
  
  private static native void errorCallback(long paramLong, int paramInt, String paramString);
  
  private static SSLSocketFactory getSSLSocketFactory()
  {
    Object localObject1 = null;
    if (q.g) {}
    for (;;)
    {
      return (SSLSocketFactory)localObject1;
      if (f != null)
      {
        localObject1 = f;
        continue;
      }
      label132:
      synchronized (e)
      {
        String[] arrayOfString2 = e;
        int j = arrayOfString2.length;
        int i = 0;
        for (;;)
        {
          if (i >= j) {
            break label132;
          }
          String str = arrayOfString2[i];
          try
          {
            localObject3 = SSLContext.getInstance(str);
            ((SSLContext)localObject3).init(null, null, null);
            localObject3 = ((SSLContext)localObject3).getSocketFactory();
            f = (SSLSocketFactory)localObject3;
            localObject1 = localObject3;
          }
          catch (Exception localException)
          {
            Object localObject3 = new java/lang/StringBuilder;
            ((StringBuilder)localObject3).<init>("UnityWebRequest: No support for ");
            m.Log(5, str + " (" + localException.getMessage() + ")");
            i++;
          }
        }
      }
    }
  }
  
  private static native void headerCallback(long paramLong, String paramString1, String paramString2);
  
  private static native void responseCodeCallback(long paramLong, int paramInt);
  
  private static native int uploadCallback(long paramLong, ByteBuffer paramByteBuffer);
  
  protected void badProtocolCallback(String paramString)
  {
    errorCallback(this.a, 4, paramString);
  }
  
  protected void contentLengthCallback(int paramInt)
  {
    contentLengthCallback(this.a, paramInt);
  }
  
  protected boolean downloadCallback(ByteBuffer paramByteBuffer, int paramInt)
  {
    return downloadCallback(this.a, paramByteBuffer, paramInt);
  }
  
  protected void errorCallback(String paramString)
  {
    errorCallback(this.a, 2, paramString);
  }
  
  protected void headerCallback(String paramString1, String paramString2)
  {
    headerCallback(this.a, paramString1, paramString2);
  }
  
  protected void headerCallback(Map paramMap)
  {
    if ((paramMap == null) || (paramMap.size() == 0)) {
      return;
    }
    Iterator localIterator = paramMap.entrySet().iterator();
    while (localIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      Object localObject = (String)localEntry.getKey();
      paramMap = (Map)localObject;
      if (localObject == null) {
        paramMap = "Status";
      }
      localObject = ((List)localEntry.getValue()).iterator();
      while (((Iterator)localObject).hasNext()) {
        headerCallback(paramMap, (String)((Iterator)localObject).next());
      }
    }
  }
  
  protected void malformattedUrlCallback(String paramString)
  {
    errorCallback(this.a, 5, paramString);
  }
  
  protected void responseCodeCallback(int paramInt)
  {
    responseCodeCallback(this.a, paramInt);
  }
  
  /* Error */
  public void run()
  {
    // Byte code:
    //   0: new 174	java/net/URL
    //   3: astore_2
    //   4: aload_2
    //   5: aload_0
    //   6: getfield 36	com/unity3d/player/UnityWebRequest:b	Ljava/lang/String;
    //   9: invokespecial 175	java/net/URL:<init>	(Ljava/lang/String;)V
    //   12: aload_2
    //   13: invokevirtual 179	java/net/URL:openConnection	()Ljava/net/URLConnection;
    //   16: astore 4
    //   18: aload 4
    //   20: instanceof 181
    //   23: ifeq +20 -> 43
    //   26: invokestatic 183	com/unity3d/player/UnityWebRequest:getSSLSocketFactory	()Ljavax/net/ssl/SSLSocketFactory;
    //   29: astore_3
    //   30: aload_3
    //   31: ifnull +12 -> 43
    //   34: aload 4
    //   36: checkcast 181	javax/net/ssl/HttpsURLConnection
    //   39: aload_3
    //   40: invokevirtual 187	javax/net/ssl/HttpsURLConnection:setSSLSocketFactory	(Ljavax/net/ssl/SSLSocketFactory;)V
    //   43: aload_2
    //   44: invokevirtual 190	java/net/URL:getProtocol	()Ljava/lang/String;
    //   47: ldc -64
    //   49: invokevirtual 196	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   52: ifeq +44 -> 96
    //   55: aload_2
    //   56: invokevirtual 199	java/net/URL:getHost	()Ljava/lang/String;
    //   59: invokevirtual 202	java/lang/String:isEmpty	()Z
    //   62: ifne +34 -> 96
    //   65: aload_0
    //   66: ldc -52
    //   68: invokevirtual 206	com/unity3d/player/UnityWebRequest:malformattedUrlCallback	(Ljava/lang/String;)V
    //   71: return
    //   72: astore_2
    //   73: aload_0
    //   74: aload_2
    //   75: invokevirtual 207	java/net/MalformedURLException:toString	()Ljava/lang/String;
    //   78: invokevirtual 206	com/unity3d/player/UnityWebRequest:malformattedUrlCallback	(Ljava/lang/String;)V
    //   81: goto -10 -> 71
    //   84: astore_2
    //   85: aload_0
    //   86: aload_2
    //   87: invokevirtual 208	java/io/IOException:toString	()Ljava/lang/String;
    //   90: invokevirtual 210	com/unity3d/player/UnityWebRequest:errorCallback	(Ljava/lang/String;)V
    //   93: goto -22 -> 71
    //   96: aload 4
    //   98: instanceof 212
    //   101: ifeq +12 -> 113
    //   104: aload_0
    //   105: ldc -42
    //   107: invokevirtual 216	com/unity3d/player/UnityWebRequest:badProtocolCallback	(Ljava/lang/String;)V
    //   110: goto -39 -> 71
    //   113: aload 4
    //   115: instanceof 218
    //   118: ifeq +22 -> 140
    //   121: aload 4
    //   123: checkcast 218	java/net/HttpURLConnection
    //   126: astore_3
    //   127: aload_3
    //   128: aload_0
    //   129: getfield 38	com/unity3d/player/UnityWebRequest:c	Ljava/lang/String;
    //   132: invokevirtual 221	java/net/HttpURLConnection:setRequestMethod	(Ljava/lang/String;)V
    //   135: aload_3
    //   136: iconst_0
    //   137: invokevirtual 225	java/net/HttpURLConnection:setInstanceFollowRedirects	(Z)V
    //   140: aload_0
    //   141: getfield 40	com/unity3d/player/UnityWebRequest:d	Ljava/util/Map;
    //   144: ifnull +78 -> 222
    //   147: aload_0
    //   148: getfield 40	com/unity3d/player/UnityWebRequest:d	Ljava/util/Map;
    //   151: invokeinterface 127 1 0
    //   156: invokeinterface 133 1 0
    //   161: astore 5
    //   163: aload 5
    //   165: invokeinterface 139 1 0
    //   170: ifeq +52 -> 222
    //   173: aload 5
    //   175: invokeinterface 143 1 0
    //   180: checkcast 145	java/util/Map$Entry
    //   183: astore_3
    //   184: aload 4
    //   186: aload_3
    //   187: invokeinterface 148 1 0
    //   192: checkcast 21	java/lang/String
    //   195: aload_3
    //   196: invokeinterface 153 1 0
    //   201: checkcast 21	java/lang/String
    //   204: invokevirtual 230	java/net/URLConnection:addRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
    //   207: goto -44 -> 163
    //   210: astore_2
    //   211: aload_0
    //   212: aload_2
    //   213: invokevirtual 231	java/net/ProtocolException:toString	()Ljava/lang/String;
    //   216: invokevirtual 216	com/unity3d/player/UnityWebRequest:badProtocolCallback	(Ljava/lang/String;)V
    //   219: goto -148 -> 71
    //   222: aload_0
    //   223: aconst_null
    //   224: invokevirtual 234	com/unity3d/player/UnityWebRequest:uploadCallback	(Ljava/nio/ByteBuffer;)I
    //   227: istore_1
    //   228: iload_1
    //   229: ifle +75 -> 304
    //   232: aload 4
    //   234: iconst_1
    //   235: invokevirtual 237	java/net/URLConnection:setDoOutput	(Z)V
    //   238: iload_1
    //   239: sipush 1428
    //   242: invokestatic 243	java/lang/Math:min	(II)I
    //   245: invokestatic 249	java/nio/ByteBuffer:allocateDirect	(I)Ljava/nio/ByteBuffer;
    //   248: astore 5
    //   250: aload 4
    //   252: invokevirtual 253	java/net/URLConnection:getOutputStream	()Ljava/io/OutputStream;
    //   255: astore_3
    //   256: aload_0
    //   257: aload 5
    //   259: invokevirtual 234	com/unity3d/player/UnityWebRequest:uploadCallback	(Ljava/nio/ByteBuffer;)I
    //   262: istore_1
    //   263: iload_1
    //   264: ifle +40 -> 304
    //   267: aload_3
    //   268: aload 5
    //   270: invokevirtual 257	java/nio/ByteBuffer:array	()[B
    //   273: aload 5
    //   275: invokevirtual 260	java/nio/ByteBuffer:arrayOffset	()I
    //   278: iload_1
    //   279: invokevirtual 266	java/io/OutputStream:write	([BII)V
    //   282: aload_0
    //   283: aload 5
    //   285: invokevirtual 234	com/unity3d/player/UnityWebRequest:uploadCallback	(Ljava/nio/ByteBuffer;)I
    //   288: istore_1
    //   289: goto -26 -> 263
    //   292: astore_2
    //   293: aload_0
    //   294: aload_2
    //   295: invokevirtual 267	java/lang/Exception:toString	()Ljava/lang/String;
    //   298: invokevirtual 210	com/unity3d/player/UnityWebRequest:errorCallback	(Ljava/lang/String;)V
    //   301: goto -230 -> 71
    //   304: aload 4
    //   306: instanceof 218
    //   309: ifeq +17 -> 326
    //   312: aload 4
    //   314: checkcast 218	java/net/HttpURLConnection
    //   317: astore_3
    //   318: aload_0
    //   319: aload_3
    //   320: invokevirtual 270	java/net/HttpURLConnection:getResponseCode	()I
    //   323: invokevirtual 272	com/unity3d/player/UnityWebRequest:responseCodeCallback	(I)V
    //   326: aload 4
    //   328: invokevirtual 276	java/net/URLConnection:getHeaderFields	()Ljava/util/Map;
    //   331: astore_3
    //   332: aload_0
    //   333: aload_3
    //   334: invokevirtual 278	com/unity3d/player/UnityWebRequest:headerCallback	(Ljava/util/Map;)V
    //   337: aload_3
    //   338: ifnull +15 -> 353
    //   341: aload_3
    //   342: ldc_w 280
    //   345: invokeinterface 284 2 0
    //   350: ifne +27 -> 377
    //   353: aload 4
    //   355: invokevirtual 287	java/net/URLConnection:getContentLength	()I
    //   358: iconst_m1
    //   359: if_icmpeq +18 -> 377
    //   362: aload_0
    //   363: ldc_w 280
    //   366: aload 4
    //   368: invokevirtual 287	java/net/URLConnection:getContentLength	()I
    //   371: invokestatic 291	java/lang/String:valueOf	(I)Ljava/lang/String;
    //   374: invokevirtual 158	com/unity3d/player/UnityWebRequest:headerCallback	(Ljava/lang/String;Ljava/lang/String;)V
    //   377: aload_3
    //   378: ifnull +15 -> 393
    //   381: aload_3
    //   382: ldc_w 293
    //   385: invokeinterface 284 2 0
    //   390: ifne +23 -> 413
    //   393: aload 4
    //   395: invokevirtual 296	java/net/URLConnection:getContentType	()Ljava/lang/String;
    //   398: ifnull +15 -> 413
    //   401: aload_0
    //   402: ldc_w 293
    //   405: aload 4
    //   407: invokevirtual 296	java/net/URLConnection:getContentType	()Ljava/lang/String;
    //   410: invokevirtual 158	com/unity3d/player/UnityWebRequest:headerCallback	(Ljava/lang/String;Ljava/lang/String;)V
    //   413: aload 4
    //   415: invokevirtual 287	java/net/URLConnection:getContentLength	()I
    //   418: istore_1
    //   419: iload_1
    //   420: ifle +8 -> 428
    //   423: aload_0
    //   424: iload_1
    //   425: invokevirtual 298	com/unity3d/player/UnityWebRequest:contentLengthCallback	(I)V
    //   428: aload_2
    //   429: invokevirtual 190	java/net/URL:getProtocol	()Ljava/lang/String;
    //   432: ldc -64
    //   434: invokevirtual 196	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   437: ifeq +183 -> 620
    //   440: iload_1
    //   441: ifne +118 -> 559
    //   444: ldc_w 299
    //   447: istore_1
    //   448: aload 4
    //   450: instanceof 218
    //   453: ifeq +162 -> 615
    //   456: aload 4
    //   458: checkcast 218	java/net/HttpURLConnection
    //   461: astore_2
    //   462: aload_0
    //   463: aload_2
    //   464: invokevirtual 270	java/net/HttpURLConnection:getResponseCode	()I
    //   467: invokevirtual 272	com/unity3d/player/UnityWebRequest:responseCodeCallback	(I)V
    //   470: aload_2
    //   471: invokevirtual 303	java/net/HttpURLConnection:getErrorStream	()Ljava/io/InputStream;
    //   474: astore_2
    //   475: aload_2
    //   476: astore_3
    //   477: aload_2
    //   478: ifnonnull +9 -> 487
    //   481: aload 4
    //   483: invokevirtual 306	java/net/URLConnection:getInputStream	()Ljava/io/InputStream;
    //   486: astore_3
    //   487: aload_3
    //   488: invokestatic 312	java/nio/channels/Channels:newChannel	(Ljava/io/InputStream;)Ljava/nio/channels/ReadableByteChannel;
    //   491: astore_3
    //   492: iload_1
    //   493: invokestatic 249	java/nio/ByteBuffer:allocateDirect	(I)Ljava/nio/ByteBuffer;
    //   496: astore_2
    //   497: aload_3
    //   498: aload_2
    //   499: invokeinterface 317 2 0
    //   504: istore_1
    //   505: iload_1
    //   506: iconst_m1
    //   507: if_icmpeq +63 -> 570
    //   510: aload_0
    //   511: aload_2
    //   512: iload_1
    //   513: invokevirtual 319	com/unity3d/player/UnityWebRequest:downloadCallback	(Ljava/nio/ByteBuffer;I)Z
    //   516: ifeq +54 -> 570
    //   519: aload_2
    //   520: invokevirtual 323	java/nio/ByteBuffer:clear	()Ljava/nio/Buffer;
    //   523: pop
    //   524: aload_3
    //   525: aload_2
    //   526: invokeinterface 317 2 0
    //   531: istore_1
    //   532: goto -27 -> 505
    //   535: astore_3
    //   536: aload_0
    //   537: aload_3
    //   538: invokevirtual 324	java/net/UnknownHostException:toString	()Ljava/lang/String;
    //   541: invokevirtual 327	com/unity3d/player/UnityWebRequest:unknownHostCallback	(Ljava/lang/String;)V
    //   544: goto -218 -> 326
    //   547: astore_2
    //   548: aload_0
    //   549: aload_2
    //   550: invokevirtual 208	java/io/IOException:toString	()Ljava/lang/String;
    //   553: invokevirtual 210	com/unity3d/player/UnityWebRequest:errorCallback	(Ljava/lang/String;)V
    //   556: goto -485 -> 71
    //   559: iload_1
    //   560: ldc_w 299
    //   563: invokestatic 243	java/lang/Math:min	(II)I
    //   566: istore_1
    //   567: goto -119 -> 448
    //   570: aload_3
    //   571: invokeinterface 330 1 0
    //   576: goto -505 -> 71
    //   579: astore_2
    //   580: aload_0
    //   581: aload_2
    //   582: invokevirtual 324	java/net/UnknownHostException:toString	()Ljava/lang/String;
    //   585: invokevirtual 327	com/unity3d/player/UnityWebRequest:unknownHostCallback	(Ljava/lang/String;)V
    //   588: goto -517 -> 71
    //   591: astore_2
    //   592: aload_0
    //   593: aload_2
    //   594: invokevirtual 331	javax/net/ssl/SSLHandshakeException:toString	()Ljava/lang/String;
    //   597: invokevirtual 334	com/unity3d/player/UnityWebRequest:sslCannotConnectCallback	(Ljava/lang/String;)V
    //   600: goto -529 -> 71
    //   603: astore_2
    //   604: aload_0
    //   605: aload_2
    //   606: invokevirtual 267	java/lang/Exception:toString	()Ljava/lang/String;
    //   609: invokevirtual 210	com/unity3d/player/UnityWebRequest:errorCallback	(Ljava/lang/String;)V
    //   612: goto -541 -> 71
    //   615: aconst_null
    //   616: astore_2
    //   617: goto -142 -> 475
    //   620: sipush 1428
    //   623: istore_1
    //   624: goto -176 -> 448
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	627	0	this	UnityWebRequest
    //   227	397	1	i	int
    //   3	53	2	localURL	java.net.URL
    //   72	3	2	localMalformedURLException	java.net.MalformedURLException
    //   84	3	2	localIOException1	java.io.IOException
    //   210	3	2	localProtocolException	java.net.ProtocolException
    //   292	137	2	localException1	Exception
    //   461	65	2	localObject1	Object
    //   547	3	2	localIOException2	java.io.IOException
    //   579	3	2	localUnknownHostException1	java.net.UnknownHostException
    //   591	3	2	localSSLHandshakeException	javax.net.ssl.SSLHandshakeException
    //   603	3	2	localException2	Exception
    //   616	1	2	localObject2	Object
    //   29	496	3	localObject3	Object
    //   535	36	3	localUnknownHostException2	java.net.UnknownHostException
    //   16	466	4	localURLConnection	java.net.URLConnection
    //   161	123	5	localObject4	Object
    // Exception table:
    //   from	to	target	type
    //   0	30	72	java/net/MalformedURLException
    //   34	43	72	java/net/MalformedURLException
    //   0	30	84	java/io/IOException
    //   34	43	84	java/io/IOException
    //   121	140	210	java/net/ProtocolException
    //   238	263	292	java/lang/Exception
    //   267	289	292	java/lang/Exception
    //   318	326	535	java/net/UnknownHostException
    //   318	326	547	java/io/IOException
    //   448	475	579	java/net/UnknownHostException
    //   481	487	579	java/net/UnknownHostException
    //   487	505	579	java/net/UnknownHostException
    //   510	532	579	java/net/UnknownHostException
    //   570	576	579	java/net/UnknownHostException
    //   448	475	591	javax/net/ssl/SSLHandshakeException
    //   481	487	591	javax/net/ssl/SSLHandshakeException
    //   487	505	591	javax/net/ssl/SSLHandshakeException
    //   510	532	591	javax/net/ssl/SSLHandshakeException
    //   570	576	591	javax/net/ssl/SSLHandshakeException
    //   448	475	603	java/lang/Exception
    //   481	487	603	java/lang/Exception
    //   487	505	603	java/lang/Exception
    //   510	532	603	java/lang/Exception
    //   570	576	603	java/lang/Exception
  }
  
  protected void sslCannotConnectCallback(String paramString)
  {
    errorCallback(this.a, 16, paramString);
  }
  
  protected void unknownHostCallback(String paramString)
  {
    errorCallback(this.a, 7, paramString);
  }
  
  protected int uploadCallback(ByteBuffer paramByteBuffer)
  {
    return uploadCallback(this.a, paramByteBuffer);
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\unity3d\player\UnityWebRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */