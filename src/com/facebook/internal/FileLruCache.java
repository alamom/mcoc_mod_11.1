package com.facebook.internal;

import android.content.Context;
import com.facebook.LoggingBehavior;
import com.facebook.Settings;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.InvalidParameterException;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicLong;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

public final class FileLruCache
{
  private static final String HEADER_CACHEKEY_KEY = "key";
  private static final String HEADER_CACHE_CONTENT_TAG_KEY = "tag";
  static final String TAG = FileLruCache.class.getSimpleName();
  private static final AtomicLong bufferIndex = new AtomicLong();
  private final File directory;
  private boolean isTrimInProgress;
  private boolean isTrimPending;
  private AtomicLong lastClearCacheTime = new AtomicLong(0L);
  private final Limits limits;
  private final Object lock;
  private final String tag;
  
  public FileLruCache(Context paramContext, String paramString, Limits paramLimits)
  {
    this.tag = paramString;
    this.limits = paramLimits;
    this.directory = new File(paramContext.getCacheDir(), paramString);
    this.lock = new Object();
    if ((this.directory.mkdirs()) || (this.directory.isDirectory())) {
      BufferFile.deleteAll(this.directory);
    }
  }
  
  private void postTrim()
  {
    synchronized (this.lock)
    {
      if (!this.isTrimPending)
      {
        this.isTrimPending = true;
        Executor localExecutor = Settings.getExecutor();
        Runnable local3 = new com/facebook/internal/FileLruCache$3;
        local3.<init>(this);
        localExecutor.execute(local3);
      }
      return;
    }
  }
  
  private void renameToTargetAndTrim(String paramString, File paramFile)
  {
    if (!paramFile.renameTo(new File(this.directory, Utility.md5hash(paramString)))) {
      paramFile.delete();
    }
    postTrim();
  }
  
  /* Error */
  private void trim()
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 99	com/facebook/internal/FileLruCache:lock	Ljava/lang/Object;
    //   4: astore 11
    //   6: aload 11
    //   8: monitorenter
    //   9: aload_0
    //   10: iconst_0
    //   11: putfield 128	com/facebook/internal/FileLruCache:isTrimPending	Z
    //   14: aload_0
    //   15: iconst_1
    //   16: putfield 159	com/facebook/internal/FileLruCache:isTrimInProgress	Z
    //   19: aload 11
    //   21: monitorexit
    //   22: getstatic 165	com/facebook/LoggingBehavior:CACHE	Lcom/facebook/LoggingBehavior;
    //   25: getstatic 65	com/facebook/internal/FileLruCache:TAG	Ljava/lang/String;
    //   28: ldc -89
    //   30: invokestatic 173	com/facebook/internal/Logger:log	(Lcom/facebook/LoggingBehavior;Ljava/lang/String;Ljava/lang/String;)V
    //   33: new 175	java/util/PriorityQueue
    //   36: astore 11
    //   38: aload 11
    //   40: invokespecial 176	java/util/PriorityQueue:<init>	()V
    //   43: lconst_0
    //   44: lstore 9
    //   46: lconst_0
    //   47: lstore 5
    //   49: aload_0
    //   50: getfield 97	com/facebook/internal/FileLruCache:directory	Ljava/io/File;
    //   53: invokestatic 180	com/facebook/internal/FileLruCache$BufferFile:excludeBufferFiles	()Ljava/io/FilenameFilter;
    //   56: invokevirtual 184	java/io/File:listFiles	(Ljava/io/FilenameFilter;)[Ljava/io/File;
    //   59: astore 15
    //   61: lload 5
    //   63: lstore_3
    //   64: lload 9
    //   66: lstore 7
    //   68: aload 15
    //   70: ifnull +143 -> 213
    //   73: aload 15
    //   75: arraylength
    //   76: istore_2
    //   77: iconst_0
    //   78: istore_1
    //   79: lload 5
    //   81: lstore_3
    //   82: lload 9
    //   84: lstore 7
    //   86: iload_1
    //   87: iload_2
    //   88: if_icmpge +125 -> 213
    //   91: aload 15
    //   93: iload_1
    //   94: aaload
    //   95: astore 16
    //   97: new 28	com/facebook/internal/FileLruCache$ModifiedFile
    //   100: astore 13
    //   102: aload 13
    //   104: aload 16
    //   106: invokespecial 186	com/facebook/internal/FileLruCache$ModifiedFile:<init>	(Ljava/io/File;)V
    //   109: aload 11
    //   111: aload 13
    //   113: invokevirtual 190	java/util/PriorityQueue:add	(Ljava/lang/Object;)Z
    //   116: pop
    //   117: getstatic 165	com/facebook/LoggingBehavior:CACHE	Lcom/facebook/LoggingBehavior;
    //   120: astore 17
    //   122: getstatic 65	com/facebook/internal/FileLruCache:TAG	Ljava/lang/String;
    //   125: astore 12
    //   127: new 192	java/lang/StringBuilder
    //   130: astore 14
    //   132: aload 14
    //   134: invokespecial 193	java/lang/StringBuilder:<init>	()V
    //   137: aload 17
    //   139: aload 12
    //   141: aload 14
    //   143: ldc -61
    //   145: invokevirtual 199	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   148: aload 13
    //   150: invokevirtual 203	com/facebook/internal/FileLruCache$ModifiedFile:getModified	()J
    //   153: invokestatic 209	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   156: invokevirtual 212	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   159: ldc -42
    //   161: invokevirtual 199	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   164: aload 13
    //   166: invokevirtual 217	com/facebook/internal/FileLruCache$ModifiedFile:getFile	()Ljava/io/File;
    //   169: invokevirtual 220	java/io/File:getName	()Ljava/lang/String;
    //   172: invokevirtual 199	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   175: invokevirtual 223	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   178: invokestatic 173	com/facebook/internal/Logger:log	(Lcom/facebook/LoggingBehavior;Ljava/lang/String;Ljava/lang/String;)V
    //   181: aload 16
    //   183: invokevirtual 226	java/io/File:length	()J
    //   186: lstore_3
    //   187: lload 9
    //   189: lload_3
    //   190: ladd
    //   191: lstore 9
    //   193: lload 5
    //   195: lconst_1
    //   196: ladd
    //   197: lstore 5
    //   199: iinc 1 1
    //   202: goto -123 -> 79
    //   205: astore 12
    //   207: aload 11
    //   209: monitorexit
    //   210: aload 12
    //   212: athrow
    //   213: lload 7
    //   215: aload_0
    //   216: getfield 84	com/facebook/internal/FileLruCache:limits	Lcom/facebook/internal/FileLruCache$Limits;
    //   219: invokevirtual 230	com/facebook/internal/FileLruCache$Limits:getByteCount	()I
    //   222: i2l
    //   223: lcmp
    //   224: ifgt +16 -> 240
    //   227: lload_3
    //   228: aload_0
    //   229: getfield 84	com/facebook/internal/FileLruCache:limits	Lcom/facebook/internal/FileLruCache$Limits;
    //   232: invokevirtual 233	com/facebook/internal/FileLruCache$Limits:getFileCount	()I
    //   235: i2l
    //   236: lcmp
    //   237: ifle +113 -> 350
    //   240: aload 11
    //   242: invokevirtual 237	java/util/PriorityQueue:remove	()Ljava/lang/Object;
    //   245: checkcast 28	com/facebook/internal/FileLruCache$ModifiedFile
    //   248: invokevirtual 217	com/facebook/internal/FileLruCache$ModifiedFile:getFile	()Ljava/io/File;
    //   251: astore 12
    //   253: getstatic 165	com/facebook/LoggingBehavior:CACHE	Lcom/facebook/LoggingBehavior;
    //   256: astore 13
    //   258: getstatic 65	com/facebook/internal/FileLruCache:TAG	Ljava/lang/String;
    //   261: astore 14
    //   263: new 192	java/lang/StringBuilder
    //   266: astore 15
    //   268: aload 15
    //   270: invokespecial 193	java/lang/StringBuilder:<init>	()V
    //   273: aload 13
    //   275: aload 14
    //   277: aload 15
    //   279: ldc -17
    //   281: invokevirtual 199	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   284: aload 12
    //   286: invokevirtual 220	java/io/File:getName	()Ljava/lang/String;
    //   289: invokevirtual 199	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   292: invokevirtual 223	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   295: invokestatic 173	com/facebook/internal/Logger:log	(Lcom/facebook/LoggingBehavior;Ljava/lang/String;Ljava/lang/String;)V
    //   298: lload 7
    //   300: aload 12
    //   302: invokevirtual 226	java/io/File:length	()J
    //   305: lsub
    //   306: lstore 7
    //   308: lload_3
    //   309: lconst_1
    //   310: lsub
    //   311: lstore_3
    //   312: aload 12
    //   314: invokevirtual 155	java/io/File:delete	()Z
    //   317: pop
    //   318: goto -105 -> 213
    //   321: astore 12
    //   323: aload_0
    //   324: getfield 99	com/facebook/internal/FileLruCache:lock	Ljava/lang/Object;
    //   327: astore 11
    //   329: aload 11
    //   331: monitorenter
    //   332: aload_0
    //   333: iconst_0
    //   334: putfield 159	com/facebook/internal/FileLruCache:isTrimInProgress	Z
    //   337: aload_0
    //   338: getfield 99	com/facebook/internal/FileLruCache:lock	Ljava/lang/Object;
    //   341: invokevirtual 242	java/lang/Object:notifyAll	()V
    //   344: aload 11
    //   346: monitorexit
    //   347: aload 12
    //   349: athrow
    //   350: aload_0
    //   351: getfield 99	com/facebook/internal/FileLruCache:lock	Ljava/lang/Object;
    //   354: astore 12
    //   356: aload 12
    //   358: monitorenter
    //   359: aload_0
    //   360: iconst_0
    //   361: putfield 159	com/facebook/internal/FileLruCache:isTrimInProgress	Z
    //   364: aload_0
    //   365: getfield 99	com/facebook/internal/FileLruCache:lock	Ljava/lang/Object;
    //   368: invokevirtual 242	java/lang/Object:notifyAll	()V
    //   371: aload 12
    //   373: monitorexit
    //   374: return
    //   375: astore 11
    //   377: aload 12
    //   379: monitorexit
    //   380: aload 11
    //   382: athrow
    //   383: astore 12
    //   385: aload 11
    //   387: monitorexit
    //   388: aload 12
    //   390: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	391	0	this	FileLruCache
    //   78	122	1	i	int
    //   76	13	2	j	int
    //   63	249	3	l1	long
    //   47	151	5	l2	long
    //   66	241	7	l3	long
    //   44	148	9	l4	long
    //   375	11	11	localObject2	Object
    //   125	15	12	str	String
    //   205	6	12	localObject3	Object
    //   251	62	12	localFile1	File
    //   321	27	12	localObject4	Object
    //   383	6	12	localObject6	Object
    //   100	174	13	localObject7	Object
    //   130	146	14	localObject8	Object
    //   59	219	15	localObject9	Object
    //   95	87	16	localFile2	File
    //   120	18	17	localLoggingBehavior	LoggingBehavior
    // Exception table:
    //   from	to	target	type
    //   9	22	205	finally
    //   207	210	205	finally
    //   22	43	321	finally
    //   49	61	321	finally
    //   73	77	321	finally
    //   97	187	321	finally
    //   213	240	321	finally
    //   240	308	321	finally
    //   312	318	321	finally
    //   359	374	375	finally
    //   377	380	375	finally
    //   332	347	383	finally
    //   385	388	383	finally
  }
  
  public void clearCache()
  {
    final File[] arrayOfFile = this.directory.listFiles(BufferFile.excludeBufferFiles());
    this.lastClearCacheTime.set(System.currentTimeMillis());
    if (arrayOfFile != null) {
      Settings.getExecutor().execute(new Runnable()
      {
        public void run()
        {
          File[] arrayOfFile = arrayOfFile;
          int j = arrayOfFile.length;
          for (int i = 0; i < j; i++) {
            arrayOfFile[i].delete();
          }
        }
      });
    }
  }
  
  public InputStream get(String paramString)
    throws IOException
  {
    return get(paramString, null);
  }
  
  /* Error */
  public InputStream get(String paramString1, String paramString2)
    throws IOException
  {
    // Byte code:
    //   0: new 86	java/io/File
    //   3: dup
    //   4: aload_0
    //   5: getfield 97	com/facebook/internal/FileLruCache:directory	Ljava/io/File;
    //   8: aload_1
    //   9: invokestatic 148	com/facebook/internal/Utility:md5hash	(Ljava/lang/String;)Ljava/lang/String;
    //   12: invokespecial 95	java/io/File:<init>	(Ljava/io/File;Ljava/lang/String;)V
    //   15: astore 7
    //   17: new 264	java/io/FileInputStream
    //   20: astore 6
    //   22: aload 6
    //   24: aload 7
    //   26: invokespecial 265	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   29: new 267	java/io/BufferedInputStream
    //   32: dup
    //   33: aload 6
    //   35: sipush 8192
    //   38: invokespecial 270	java/io/BufferedInputStream:<init>	(Ljava/io/InputStream;I)V
    //   41: astore 6
    //   43: aload 6
    //   45: invokestatic 274	com/facebook/internal/FileLruCache$StreamHeader:readHeader	(Ljava/io/InputStream;)Lorg/json/JSONObject;
    //   48: astore 9
    //   50: aload 9
    //   52: ifnonnull +22 -> 74
    //   55: iconst_0
    //   56: ifne +8 -> 64
    //   59: aload 6
    //   61: invokevirtual 277	java/io/BufferedInputStream:close	()V
    //   64: aconst_null
    //   65: astore_1
    //   66: aload_1
    //   67: areturn
    //   68: astore_1
    //   69: aconst_null
    //   70: astore_1
    //   71: goto -5 -> 66
    //   74: aload 9
    //   76: ldc 39
    //   78: invokevirtual 282	org/json/JSONObject:optString	(Ljava/lang/String;)Ljava/lang/String;
    //   81: astore 8
    //   83: aload 8
    //   85: ifnull +14 -> 99
    //   88: aload 8
    //   90: aload_1
    //   91: invokevirtual 287	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   94: istore_3
    //   95: iload_3
    //   96: ifne +17 -> 113
    //   99: iconst_0
    //   100: ifne +8 -> 108
    //   103: aload 6
    //   105: invokevirtual 277	java/io/BufferedInputStream:close	()V
    //   108: aconst_null
    //   109: astore_1
    //   110: goto -44 -> 66
    //   113: aload 9
    //   115: ldc 42
    //   117: aconst_null
    //   118: invokevirtual 290	org/json/JSONObject:optString	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   121: astore_1
    //   122: aload_2
    //   123: ifnonnull +7 -> 130
    //   126: aload_1
    //   127: ifnonnull +17 -> 144
    //   130: aload_2
    //   131: ifnull +27 -> 158
    //   134: aload_2
    //   135: aload_1
    //   136: invokevirtual 287	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   139: istore_3
    //   140: iload_3
    //   141: ifne +17 -> 158
    //   144: iconst_0
    //   145: ifne +8 -> 153
    //   148: aload 6
    //   150: invokevirtual 277	java/io/BufferedInputStream:close	()V
    //   153: aconst_null
    //   154: astore_1
    //   155: goto -89 -> 66
    //   158: new 292	java/util/Date
    //   161: astore_1
    //   162: aload_1
    //   163: invokespecial 293	java/util/Date:<init>	()V
    //   166: aload_1
    //   167: invokevirtual 296	java/util/Date:getTime	()J
    //   170: lstore 4
    //   172: getstatic 165	com/facebook/LoggingBehavior:CACHE	Lcom/facebook/LoggingBehavior;
    //   175: astore_2
    //   176: getstatic 65	com/facebook/internal/FileLruCache:TAG	Ljava/lang/String;
    //   179: astore 8
    //   181: new 192	java/lang/StringBuilder
    //   184: astore_1
    //   185: aload_1
    //   186: invokespecial 193	java/lang/StringBuilder:<init>	()V
    //   189: aload_2
    //   190: aload 8
    //   192: aload_1
    //   193: ldc_w 298
    //   196: invokevirtual 199	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   199: lload 4
    //   201: invokestatic 209	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   204: invokevirtual 212	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   207: ldc_w 300
    //   210: invokevirtual 199	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   213: aload 7
    //   215: invokevirtual 220	java/io/File:getName	()Ljava/lang/String;
    //   218: invokevirtual 199	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   221: invokevirtual 223	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   224: invokestatic 173	com/facebook/internal/Logger:log	(Lcom/facebook/LoggingBehavior;Ljava/lang/String;Ljava/lang/String;)V
    //   227: aload 7
    //   229: lload 4
    //   231: invokevirtual 304	java/io/File:setLastModified	(J)Z
    //   234: pop
    //   235: iconst_1
    //   236: ifne +8 -> 244
    //   239: aload 6
    //   241: invokevirtual 277	java/io/BufferedInputStream:close	()V
    //   244: aload 6
    //   246: astore_1
    //   247: goto -181 -> 66
    //   250: astore_1
    //   251: iconst_0
    //   252: ifne +8 -> 260
    //   255: aload 6
    //   257: invokevirtual 277	java/io/BufferedInputStream:close	()V
    //   260: aload_1
    //   261: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	262	0	this	FileLruCache
    //   0	262	1	paramString1	String
    //   0	262	2	paramString2	String
    //   94	47	3	bool	boolean
    //   170	60	4	l	long
    //   20	236	6	localObject	Object
    //   15	213	7	localFile	File
    //   81	110	8	str	String
    //   48	66	9	localJSONObject	JSONObject
    // Exception table:
    //   from	to	target	type
    //   17	29	68	java/io/IOException
    //   43	50	250	finally
    //   74	83	250	finally
    //   88	95	250	finally
    //   113	122	250	finally
    //   134	140	250	finally
    //   158	235	250	finally
  }
  
  public InputStream interceptAndPut(String paramString, InputStream paramInputStream)
    throws IOException
  {
    return new CopyingInputStream(paramInputStream, openPutStream(paramString));
  }
  
  OutputStream openPutStream(String paramString)
    throws IOException
  {
    return openPutStream(paramString, null);
  }
  
  /* Error */
  public OutputStream openPutStream(final String paramString1, String paramString2)
    throws IOException
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 97	com/facebook/internal/FileLruCache:directory	Ljava/io/File;
    //   4: invokestatic 324	com/facebook/internal/FileLruCache$BufferFile:newFile	(Ljava/io/File;)Ljava/io/File;
    //   7: astore 4
    //   9: aload 4
    //   11: invokevirtual 155	java/io/File:delete	()Z
    //   14: pop
    //   15: aload 4
    //   17: invokevirtual 327	java/io/File:createNewFile	()Z
    //   20: ifne +35 -> 55
    //   23: new 258	java/io/IOException
    //   26: dup
    //   27: new 192	java/lang/StringBuilder
    //   30: dup
    //   31: invokespecial 193	java/lang/StringBuilder:<init>	()V
    //   34: ldc_w 329
    //   37: invokevirtual 199	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   40: aload 4
    //   42: invokevirtual 332	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   45: invokevirtual 199	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   48: invokevirtual 223	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   51: invokespecial 335	java/io/IOException:<init>	(Ljava/lang/String;)V
    //   54: athrow
    //   55: new 337	java/io/FileOutputStream
    //   58: dup
    //   59: aload 4
    //   61: invokespecial 338	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   64: astore_3
    //   65: new 340	java/io/BufferedOutputStream
    //   68: dup
    //   69: new 19	com/facebook/internal/FileLruCache$CloseCallbackOutputStream
    //   72: dup
    //   73: aload_3
    //   74: new 6	com/facebook/internal/FileLruCache$1
    //   77: dup
    //   78: aload_0
    //   79: invokestatic 248	java/lang/System:currentTimeMillis	()J
    //   82: aload 4
    //   84: aload_1
    //   85: invokespecial 343	com/facebook/internal/FileLruCache$1:<init>	(Lcom/facebook/internal/FileLruCache;JLjava/io/File;Ljava/lang/String;)V
    //   88: invokespecial 346	com/facebook/internal/FileLruCache$CloseCallbackOutputStream:<init>	(Ljava/io/OutputStream;Lcom/facebook/internal/FileLruCache$StreamCloseCallback;)V
    //   91: sipush 8192
    //   94: invokespecial 349	java/io/BufferedOutputStream:<init>	(Ljava/io/OutputStream;I)V
    //   97: astore_3
    //   98: new 279	org/json/JSONObject
    //   101: astore 4
    //   103: aload 4
    //   105: invokespecial 350	org/json/JSONObject:<init>	()V
    //   108: aload 4
    //   110: ldc 39
    //   112: aload_1
    //   113: invokevirtual 354	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   116: pop
    //   117: aload_2
    //   118: invokestatic 358	com/facebook/internal/Utility:isNullOrEmpty	(Ljava/lang/String;)Z
    //   121: ifne +12 -> 133
    //   124: aload 4
    //   126: ldc 42
    //   128: aload_2
    //   129: invokevirtual 354	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   132: pop
    //   133: aload_3
    //   134: aload 4
    //   136: invokestatic 362	com/facebook/internal/FileLruCache$StreamHeader:writeHeader	(Ljava/io/OutputStream;Lorg/json/JSONObject;)V
    //   139: iconst_1
    //   140: ifne +7 -> 147
    //   143: aload_3
    //   144: invokevirtual 363	java/io/BufferedOutputStream:close	()V
    //   147: aload_3
    //   148: areturn
    //   149: astore_1
    //   150: getstatic 165	com/facebook/LoggingBehavior:CACHE	Lcom/facebook/LoggingBehavior;
    //   153: iconst_5
    //   154: getstatic 65	com/facebook/internal/FileLruCache:TAG	Ljava/lang/String;
    //   157: new 192	java/lang/StringBuilder
    //   160: dup
    //   161: invokespecial 193	java/lang/StringBuilder:<init>	()V
    //   164: ldc_w 365
    //   167: invokevirtual 199	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   170: aload_1
    //   171: invokevirtual 212	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   174: invokevirtual 223	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   177: invokestatic 368	com/facebook/internal/Logger:log	(Lcom/facebook/LoggingBehavior;ILjava/lang/String;Ljava/lang/String;)V
    //   180: new 258	java/io/IOException
    //   183: dup
    //   184: aload_1
    //   185: invokevirtual 371	java/io/FileNotFoundException:getMessage	()Ljava/lang/String;
    //   188: invokespecial 335	java/io/IOException:<init>	(Ljava/lang/String;)V
    //   191: athrow
    //   192: astore_1
    //   193: getstatic 165	com/facebook/LoggingBehavior:CACHE	Lcom/facebook/LoggingBehavior;
    //   196: astore 5
    //   198: getstatic 65	com/facebook/internal/FileLruCache:TAG	Ljava/lang/String;
    //   201: astore 4
    //   203: new 192	java/lang/StringBuilder
    //   206: astore_2
    //   207: aload_2
    //   208: invokespecial 193	java/lang/StringBuilder:<init>	()V
    //   211: aload 5
    //   213: iconst_5
    //   214: aload 4
    //   216: aload_2
    //   217: ldc_w 373
    //   220: invokevirtual 199	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   223: aload_1
    //   224: invokevirtual 212	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   227: invokevirtual 223	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   230: invokestatic 368	com/facebook/internal/Logger:log	(Lcom/facebook/LoggingBehavior;ILjava/lang/String;Ljava/lang/String;)V
    //   233: new 258	java/io/IOException
    //   236: astore_2
    //   237: aload_2
    //   238: aload_1
    //   239: invokevirtual 374	org/json/JSONException:getMessage	()Ljava/lang/String;
    //   242: invokespecial 335	java/io/IOException:<init>	(Ljava/lang/String;)V
    //   245: aload_2
    //   246: athrow
    //   247: astore_1
    //   248: iconst_0
    //   249: ifne +7 -> 256
    //   252: aload_3
    //   253: invokevirtual 363	java/io/BufferedOutputStream:close	()V
    //   256: aload_1
    //   257: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	258	0	this	FileLruCache
    //   0	258	1	paramString1	String
    //   0	258	2	paramString2	String
    //   64	189	3	localObject1	Object
    //   7	208	4	localObject2	Object
    //   196	16	5	localLoggingBehavior	LoggingBehavior
    // Exception table:
    //   from	to	target	type
    //   55	65	149	java/io/FileNotFoundException
    //   98	133	192	org/json/JSONException
    //   133	139	192	org/json/JSONException
    //   98	133	247	finally
    //   133	139	247	finally
    //   193	247	247	finally
  }
  
  long sizeInBytesForTest()
  {
    long l2;
    synchronized (this.lock)
    {
      for (;;)
      {
        if (!this.isTrimPending)
        {
          boolean bool = this.isTrimInProgress;
          if (!bool) {
            break;
          }
        }
        try
        {
          this.lock.wait();
        }
        catch (InterruptedException localInterruptedException) {}
      }
      ??? = this.directory.listFiles();
      long l1 = 0L;
      l2 = l1;
      if (??? != null)
      {
        int j = ???.length;
        int i = 0;
        l2 = l1;
        if (i < j)
        {
          l1 += ???[i].length();
          i++;
        }
      }
    }
    return l2;
  }
  
  public String toString()
  {
    return "{FileLruCache: tag:" + this.tag + " file:" + this.directory.getName() + "}";
  }
  
  private static class BufferFile
  {
    private static final String FILE_NAME_PREFIX = "buffer";
    private static final FilenameFilter filterExcludeBufferFiles = new FilenameFilter()
    {
      public boolean accept(File paramAnonymousFile, String paramAnonymousString)
      {
        if (!paramAnonymousString.startsWith("buffer")) {}
        for (boolean bool = true;; bool = false) {
          return bool;
        }
      }
    };
    private static final FilenameFilter filterExcludeNonBufferFiles = new FilenameFilter()
    {
      public boolean accept(File paramAnonymousFile, String paramAnonymousString)
      {
        return paramAnonymousString.startsWith("buffer");
      }
    };
    
    static void deleteAll(File paramFile)
    {
      paramFile = paramFile.listFiles(excludeNonBufferFiles());
      if (paramFile != null)
      {
        int j = paramFile.length;
        for (int i = 0; i < j; i++) {
          paramFile[i].delete();
        }
      }
    }
    
    static FilenameFilter excludeBufferFiles()
    {
      return filterExcludeBufferFiles;
    }
    
    static FilenameFilter excludeNonBufferFiles()
    {
      return filterExcludeNonBufferFiles;
    }
    
    static File newFile(File paramFile)
    {
      return new File(paramFile, "buffer" + Long.valueOf(FileLruCache.bufferIndex.incrementAndGet()).toString());
    }
  }
  
  private static class CloseCallbackOutputStream
    extends OutputStream
  {
    final FileLruCache.StreamCloseCallback callback;
    final OutputStream innerStream;
    
    CloseCallbackOutputStream(OutputStream paramOutputStream, FileLruCache.StreamCloseCallback paramStreamCloseCallback)
    {
      this.innerStream = paramOutputStream;
      this.callback = paramStreamCloseCallback;
    }
    
    public void close()
      throws IOException
    {
      try
      {
        this.innerStream.close();
        return;
      }
      finally
      {
        this.callback.onClose();
      }
    }
    
    public void flush()
      throws IOException
    {
      this.innerStream.flush();
    }
    
    public void write(int paramInt)
      throws IOException
    {
      this.innerStream.write(paramInt);
    }
    
    public void write(byte[] paramArrayOfByte)
      throws IOException
    {
      this.innerStream.write(paramArrayOfByte);
    }
    
    public void write(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
      throws IOException
    {
      this.innerStream.write(paramArrayOfByte, paramInt1, paramInt2);
    }
  }
  
  private static final class CopyingInputStream
    extends InputStream
  {
    final InputStream input;
    final OutputStream output;
    
    CopyingInputStream(InputStream paramInputStream, OutputStream paramOutputStream)
    {
      this.input = paramInputStream;
      this.output = paramOutputStream;
    }
    
    public int available()
      throws IOException
    {
      return this.input.available();
    }
    
    public void close()
      throws IOException
    {
      try
      {
        this.input.close();
        return;
      }
      finally
      {
        this.output.close();
      }
    }
    
    public void mark(int paramInt)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean markSupported()
    {
      return false;
    }
    
    public int read()
      throws IOException
    {
      int i = this.input.read();
      if (i >= 0) {
        this.output.write(i);
      }
      return i;
    }
    
    public int read(byte[] paramArrayOfByte)
      throws IOException
    {
      int i = this.input.read(paramArrayOfByte);
      if (i > 0) {
        this.output.write(paramArrayOfByte, 0, i);
      }
      return i;
    }
    
    public int read(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
      throws IOException
    {
      paramInt2 = this.input.read(paramArrayOfByte, paramInt1, paramInt2);
      if (paramInt2 > 0) {
        this.output.write(paramArrayOfByte, paramInt1, paramInt2);
      }
      return paramInt2;
    }
    
    public void reset()
    {
      try
      {
        UnsupportedOperationException localUnsupportedOperationException = new java/lang/UnsupportedOperationException;
        localUnsupportedOperationException.<init>();
        throw localUnsupportedOperationException;
      }
      finally {}
    }
    
    public long skip(long paramLong)
      throws IOException
    {
      byte[] arrayOfByte = new byte['Ѐ'];
      int i;
      for (long l = 0L;; l += i) {
        if (l < paramLong)
        {
          i = read(arrayOfByte, 0, (int)Math.min(paramLong - l, arrayOfByte.length));
          if (i >= 0) {}
        }
        else
        {
          return l;
        }
      }
    }
  }
  
  public static final class Limits
  {
    private int byteCount = 1048576;
    private int fileCount = 1024;
    
    int getByteCount()
    {
      return this.byteCount;
    }
    
    int getFileCount()
    {
      return this.fileCount;
    }
    
    void setByteCount(int paramInt)
    {
      if (paramInt < 0) {
        throw new InvalidParameterException("Cache byte-count limit must be >= 0");
      }
      this.byteCount = paramInt;
    }
    
    void setFileCount(int paramInt)
    {
      if (paramInt < 0) {
        throw new InvalidParameterException("Cache file count limit must be >= 0");
      }
      this.fileCount = paramInt;
    }
  }
  
  private static final class ModifiedFile
    implements Comparable<ModifiedFile>
  {
    private static final int HASH_MULTIPLIER = 37;
    private static final int HASH_SEED = 29;
    private final File file;
    private final long modified;
    
    ModifiedFile(File paramFile)
    {
      this.file = paramFile;
      this.modified = paramFile.lastModified();
    }
    
    public int compareTo(ModifiedFile paramModifiedFile)
    {
      int i;
      if (getModified() < paramModifiedFile.getModified()) {
        i = -1;
      }
      for (;;)
      {
        return i;
        if (getModified() > paramModifiedFile.getModified()) {
          i = 1;
        } else {
          i = getFile().compareTo(paramModifiedFile.getFile());
        }
      }
    }
    
    public boolean equals(Object paramObject)
    {
      if (((paramObject instanceof ModifiedFile)) && (compareTo((ModifiedFile)paramObject) == 0)) {}
      for (boolean bool = true;; bool = false) {
        return bool;
      }
    }
    
    File getFile()
    {
      return this.file;
    }
    
    long getModified()
    {
      return this.modified;
    }
    
    public int hashCode()
    {
      return (this.file.hashCode() + 1073) * 37 + (int)(this.modified % 2147483647L);
    }
  }
  
  private static abstract interface StreamCloseCallback
  {
    public abstract void onClose();
  }
  
  private static final class StreamHeader
  {
    private static final int HEADER_VERSION = 0;
    
    static JSONObject readHeader(InputStream paramInputStream)
      throws IOException
    {
      if (paramInputStream.read() != 0) {
        paramInputStream = null;
      }
      for (;;)
      {
        return paramInputStream;
        int j = 0;
        for (int i = 0;; i++)
        {
          if (i >= 3) {
            break label63;
          }
          int k = paramInputStream.read();
          if (k == -1)
          {
            Logger.log(LoggingBehavior.CACHE, FileLruCache.TAG, "readHeader: stream.read returned -1 while reading header size");
            paramInputStream = null;
            break;
          }
          j = (j << 8) + (k & 0xFF);
        }
        label63:
        Object localObject1 = new byte[j];
        i = 0;
        for (;;)
        {
          if (i >= localObject1.length) {
            break label149;
          }
          j = paramInputStream.read((byte[])localObject1, i, localObject1.length - i);
          if (j < 1)
          {
            Logger.log(LoggingBehavior.CACHE, FileLruCache.TAG, "readHeader: stream.read stopped at " + Integer.valueOf(i) + " when expected " + localObject1.length);
            paramInputStream = null;
            break;
          }
          i += j;
        }
        label149:
        paramInputStream = new JSONTokener(new String((byte[])localObject1));
        try
        {
          Object localObject2 = paramInputStream.nextValue();
          if (!(localObject2 instanceof JSONObject))
          {
            paramInputStream = LoggingBehavior.CACHE;
            String str = FileLruCache.TAG;
            localObject1 = new java/lang/StringBuilder;
            ((StringBuilder)localObject1).<init>();
            Logger.log(paramInputStream, str, "readHeader: expected JSONObject, got " + localObject2.getClass().getCanonicalName());
            paramInputStream = null;
            continue;
          }
          paramInputStream = (JSONObject)localObject2;
        }
        catch (JSONException paramInputStream)
        {
          throw new IOException(paramInputStream.getMessage());
        }
      }
    }
    
    static void writeHeader(OutputStream paramOutputStream, JSONObject paramJSONObject)
      throws IOException
    {
      paramJSONObject = paramJSONObject.toString().getBytes();
      paramOutputStream.write(0);
      paramOutputStream.write(paramJSONObject.length >> 16 & 0xFF);
      paramOutputStream.write(paramJSONObject.length >> 8 & 0xFF);
      paramOutputStream.write(paramJSONObject.length >> 0 & 0xFF);
      paramOutputStream.write(paramJSONObject);
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\facebook\internal\FileLruCache.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */