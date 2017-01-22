package com.google.android.gms.tagmanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build.VERSION;
import android.text.TextUtils;
import com.google.android.gms.internal.ju;
import com.google.android.gms.internal.jw;
import java.io.File;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.http.impl.client.DefaultHttpClient;

class cb
  implements at
{
  private static final String AY = String.format("CREATE TABLE IF NOT EXISTS %s ( '%s' INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, '%s' INTEGER NOT NULL, '%s' TEXT NOT NULL,'%s' INTEGER NOT NULL);", new Object[] { "gtm_hits", "hit_id", "hit_time", "hit_url", "hit_first_send_time" });
  private final String Bb;
  private long Bd;
  private final int Be;
  private final b apW;
  private volatile ab apX;
  private final au apY;
  private final Context mContext;
  private ju yD;
  
  cb(au paramau, Context paramContext)
  {
    this(paramau, paramContext, "gtm_urls.db", 2000);
  }
  
  cb(au paramau, Context paramContext, String paramString, int paramInt)
  {
    this.mContext = paramContext.getApplicationContext();
    this.Bb = paramString;
    this.apY = paramau;
    this.yD = jw.hA();
    this.apW = new b(this.mContext, this.Bb);
    this.apX = new db(new DefaultHttpClient(), this.mContext, new a());
    this.Bd = 0L;
    this.Be = paramInt;
  }
  
  private SQLiteDatabase al(String paramString)
  {
    try
    {
      SQLiteDatabase localSQLiteDatabase = this.apW.getWritableDatabase();
      paramString = localSQLiteDatabase;
    }
    catch (SQLiteException localSQLiteException)
    {
      for (;;)
      {
        bh.W(paramString);
        paramString = null;
      }
    }
    return paramString;
  }
  
  private void c(long paramLong1, long paramLong2)
  {
    SQLiteDatabase localSQLiteDatabase = al("Error opening database for getNumStoredHits.");
    if (localSQLiteDatabase == null) {}
    for (;;)
    {
      return;
      ContentValues localContentValues = new ContentValues();
      localContentValues.put("hit_first_send_time", Long.valueOf(paramLong2));
      try
      {
        localSQLiteDatabase.update("gtm_hits", localContentValues, "hit_id=?", new String[] { String.valueOf(paramLong1) });
      }
      catch (SQLiteException localSQLiteException)
      {
        bh.W("Error setting HIT_FIRST_DISPATCH_TIME for hitId: " + paramLong1);
        y(paramLong1);
      }
    }
  }
  
  private void eM()
  {
    int i = eO() - this.Be + 1;
    if (i > 0)
    {
      List localList = F(i);
      bh.V("Store full, deleting " + localList.size() + " hits to make room.");
      b((String[])localList.toArray(new String[0]));
    }
  }
  
  private void g(long paramLong, String paramString)
  {
    SQLiteDatabase localSQLiteDatabase = al("Error opening database for putHit");
    if (localSQLiteDatabase == null) {}
    for (;;)
    {
      return;
      ContentValues localContentValues = new ContentValues();
      localContentValues.put("hit_time", Long.valueOf(paramLong));
      localContentValues.put("hit_url", paramString);
      localContentValues.put("hit_first_send_time", Integer.valueOf(0));
      try
      {
        localSQLiteDatabase.insert("gtm_hits", null, localContentValues);
        this.apY.z(false);
      }
      catch (SQLiteException paramString)
      {
        bh.W("Error storing hit");
      }
    }
  }
  
  private void y(long paramLong)
  {
    b(new String[] { String.valueOf(paramLong) });
  }
  
  /* Error */
  List<String> F(int paramInt)
  {
    // Byte code:
    //   0: new 237	java/util/ArrayList
    //   3: dup
    //   4: invokespecial 238	java/util/ArrayList:<init>	()V
    //   7: astore 6
    //   9: iload_1
    //   10: ifgt +11 -> 21
    //   13: ldc -16
    //   15: invokestatic 128	com/google/android/gms/tagmanager/bh:W	(Ljava/lang/String;)V
    //   18: aload 6
    //   20: areturn
    //   21: aload_0
    //   22: ldc -14
    //   24: invokespecial 134	com/google/android/gms/tagmanager/cb:al	(Ljava/lang/String;)Landroid/database/sqlite/SQLiteDatabase;
    //   27: astore_3
    //   28: aload_3
    //   29: ifnonnull +6 -> 35
    //   32: goto -14 -> 18
    //   35: ldc -12
    //   37: iconst_1
    //   38: anewarray 4	java/lang/Object
    //   41: dup
    //   42: iconst_0
    //   43: ldc 37
    //   45: aastore
    //   46: invokestatic 49	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   49: astore 4
    //   51: iload_1
    //   52: invokestatic 247	java/lang/Integer:toString	(I)Ljava/lang/String;
    //   55: astore 5
    //   57: aload_3
    //   58: ldc 35
    //   60: iconst_1
    //   61: anewarray 45	java/lang/String
    //   64: dup
    //   65: iconst_0
    //   66: ldc 37
    //   68: aastore
    //   69: aconst_null
    //   70: aconst_null
    //   71: aconst_null
    //   72: aconst_null
    //   73: aload 4
    //   75: aload 5
    //   77: invokevirtual 251	android/database/sqlite/SQLiteDatabase:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   80: astore 4
    //   82: aload 4
    //   84: astore_3
    //   85: aload 4
    //   87: invokeinterface 257 1 0
    //   92: ifeq +40 -> 132
    //   95: aload 4
    //   97: astore_3
    //   98: aload 6
    //   100: aload 4
    //   102: iconst_0
    //   103: invokeinterface 261 2 0
    //   108: invokestatic 152	java/lang/String:valueOf	(J)Ljava/lang/String;
    //   111: invokeinterface 265 2 0
    //   116: pop
    //   117: aload 4
    //   119: astore_3
    //   120: aload 4
    //   122: invokeinterface 268 1 0
    //   127: istore_2
    //   128: iload_2
    //   129: ifne -34 -> 95
    //   132: aload 4
    //   134: ifnull +10 -> 144
    //   137: aload 4
    //   139: invokeinterface 271 1 0
    //   144: goto -126 -> 18
    //   147: astore 5
    //   149: aconst_null
    //   150: astore 4
    //   152: aload 4
    //   154: astore_3
    //   155: new 160	java/lang/StringBuilder
    //   158: astore 7
    //   160: aload 4
    //   162: astore_3
    //   163: aload 7
    //   165: invokespecial 161	java/lang/StringBuilder:<init>	()V
    //   168: aload 4
    //   170: astore_3
    //   171: aload 7
    //   173: ldc_w 273
    //   176: invokevirtual 167	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   179: aload 5
    //   181: invokevirtual 276	android/database/sqlite/SQLiteException:getMessage	()Ljava/lang/String;
    //   184: invokevirtual 167	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   187: invokevirtual 174	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   190: invokestatic 128	com/google/android/gms/tagmanager/bh:W	(Ljava/lang/String;)V
    //   193: aload 4
    //   195: ifnull -51 -> 144
    //   198: aload 4
    //   200: invokeinterface 271 1 0
    //   205: goto -61 -> 144
    //   208: astore_3
    //   209: aconst_null
    //   210: astore 4
    //   212: aload_3
    //   213: astore 5
    //   215: aload 4
    //   217: ifnull +10 -> 227
    //   220: aload 4
    //   222: invokeinterface 271 1 0
    //   227: aload 5
    //   229: athrow
    //   230: astore 5
    //   232: aload_3
    //   233: astore 4
    //   235: goto -20 -> 215
    //   238: astore 5
    //   240: goto -88 -> 152
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	243	0	this	cb
    //   0	243	1	paramInt	int
    //   127	2	2	bool	boolean
    //   27	144	3	localObject1	Object
    //   208	25	3	localObject2	Object
    //   49	185	4	localObject3	Object
    //   55	21	5	str	String
    //   147	33	5	localSQLiteException1	SQLiteException
    //   213	15	5	localObject4	Object
    //   230	1	5	localObject5	Object
    //   238	1	5	localSQLiteException2	SQLiteException
    //   7	92	6	localArrayList	java.util.ArrayList
    //   158	14	7	localStringBuilder	StringBuilder
    // Exception table:
    //   from	to	target	type
    //   35	82	147	android/database/sqlite/SQLiteException
    //   35	82	208	finally
    //   85	95	230	finally
    //   98	117	230	finally
    //   120	128	230	finally
    //   155	160	230	finally
    //   163	168	230	finally
    //   171	193	230	finally
    //   85	95	238	android/database/sqlite/SQLiteException
    //   98	117	238	android/database/sqlite/SQLiteException
    //   120	128	238	android/database/sqlite/SQLiteException
  }
  
  /* Error */
  public List<ap> G(int paramInt)
  {
    // Byte code:
    //   0: new 237	java/util/ArrayList
    //   3: dup
    //   4: invokespecial 238	java/util/ArrayList:<init>	()V
    //   7: astore 5
    //   9: aload_0
    //   10: ldc_w 281
    //   13: invokespecial 134	com/google/android/gms/tagmanager/cb:al	(Ljava/lang/String;)Landroid/database/sqlite/SQLiteDatabase;
    //   16: astore 7
    //   18: aload 7
    //   20: ifnonnull +10 -> 30
    //   23: aload 5
    //   25: astore 6
    //   27: aload 6
    //   29: areturn
    //   30: aconst_null
    //   31: astore 6
    //   33: ldc -12
    //   35: iconst_1
    //   36: anewarray 4	java/lang/Object
    //   39: dup
    //   40: iconst_0
    //   41: ldc 37
    //   43: aastore
    //   44: invokestatic 49	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   47: astore 8
    //   49: iload_1
    //   50: invokestatic 247	java/lang/Integer:toString	(I)Ljava/lang/String;
    //   53: astore 4
    //   55: aload 7
    //   57: ldc 35
    //   59: iconst_3
    //   60: anewarray 45	java/lang/String
    //   63: dup
    //   64: iconst_0
    //   65: ldc 37
    //   67: aastore
    //   68: dup
    //   69: iconst_1
    //   70: ldc 39
    //   72: aastore
    //   73: dup
    //   74: iconst_2
    //   75: ldc 43
    //   77: aastore
    //   78: aconst_null
    //   79: aconst_null
    //   80: aconst_null
    //   81: aconst_null
    //   82: aload 8
    //   84: aload 4
    //   86: invokevirtual 251	android/database/sqlite/SQLiteDatabase:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   89: astore 4
    //   91: new 237	java/util/ArrayList
    //   94: astore 6
    //   96: aload 6
    //   98: invokespecial 238	java/util/ArrayList:<init>	()V
    //   101: aload 4
    //   103: invokeinterface 257 1 0
    //   108: ifeq +59 -> 167
    //   111: new 283	com/google/android/gms/tagmanager/ap
    //   114: astore 5
    //   116: aload 5
    //   118: aload 4
    //   120: iconst_0
    //   121: invokeinterface 261 2 0
    //   126: aload 4
    //   128: iconst_1
    //   129: invokeinterface 261 2 0
    //   134: aload 4
    //   136: iconst_2
    //   137: invokeinterface 261 2 0
    //   142: invokespecial 286	com/google/android/gms/tagmanager/ap:<init>	(JJJ)V
    //   145: aload 6
    //   147: aload 5
    //   149: invokeinterface 265 2 0
    //   154: pop
    //   155: aload 4
    //   157: invokeinterface 268 1 0
    //   162: istore_3
    //   163: iload_3
    //   164: ifne -53 -> 111
    //   167: aload 4
    //   169: ifnull +10 -> 179
    //   172: aload 4
    //   174: invokeinterface 271 1 0
    //   179: aload 4
    //   181: astore 5
    //   183: ldc -12
    //   185: iconst_1
    //   186: anewarray 4	java/lang/Object
    //   189: dup
    //   190: iconst_0
    //   191: ldc 37
    //   193: aastore
    //   194: invokestatic 49	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   197: astore 9
    //   199: aload 4
    //   201: astore 5
    //   203: iload_1
    //   204: invokestatic 247	java/lang/Integer:toString	(I)Ljava/lang/String;
    //   207: astore 8
    //   209: aload 4
    //   211: astore 5
    //   213: aload 7
    //   215: ldc 35
    //   217: iconst_2
    //   218: anewarray 45	java/lang/String
    //   221: dup
    //   222: iconst_0
    //   223: ldc 37
    //   225: aastore
    //   226: dup
    //   227: iconst_1
    //   228: ldc 41
    //   230: aastore
    //   231: aconst_null
    //   232: aconst_null
    //   233: aconst_null
    //   234: aconst_null
    //   235: aload 9
    //   237: aload 8
    //   239: invokevirtual 251	android/database/sqlite/SQLiteDatabase:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   242: astore 7
    //   244: aload 7
    //   246: invokeinterface 257 1 0
    //   251: ifeq +53 -> 304
    //   254: iconst_0
    //   255: istore_1
    //   256: aload 7
    //   258: checkcast 288	android/database/sqlite/SQLiteCursor
    //   261: invokevirtual 292	android/database/sqlite/SQLiteCursor:getWindow	()Landroid/database/CursorWindow;
    //   264: invokevirtual 297	android/database/CursorWindow:getNumRows	()I
    //   267: ifle +141 -> 408
    //   270: aload 6
    //   272: iload_1
    //   273: invokeinterface 301 2 0
    //   278: checkcast 283	com/google/android/gms/tagmanager/ap
    //   281: aload 7
    //   283: iconst_1
    //   284: invokeinterface 304 2 0
    //   289: invokevirtual 307	com/google/android/gms/tagmanager/ap:ak	(Ljava/lang/String;)V
    //   292: aload 7
    //   294: invokeinterface 268 1 0
    //   299: istore_3
    //   300: iload_3
    //   301: ifne +406 -> 707
    //   304: aload 7
    //   306: ifnull +10 -> 316
    //   309: aload 7
    //   311: invokeinterface 271 1 0
    //   316: goto -289 -> 27
    //   319: astore 6
    //   321: aconst_null
    //   322: astore 7
    //   324: aload 5
    //   326: astore 4
    //   328: aload 7
    //   330: astore 5
    //   332: new 160	java/lang/StringBuilder
    //   335: astore 7
    //   337: aload 7
    //   339: invokespecial 161	java/lang/StringBuilder:<init>	()V
    //   342: aload 7
    //   344: ldc_w 273
    //   347: invokevirtual 167	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   350: aload 6
    //   352: invokevirtual 276	android/database/sqlite/SQLiteException:getMessage	()Ljava/lang/String;
    //   355: invokevirtual 167	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   358: invokevirtual 174	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   361: invokestatic 128	com/google/android/gms/tagmanager/bh:W	(Ljava/lang/String;)V
    //   364: aload 4
    //   366: astore 6
    //   368: aload 5
    //   370: ifnull -343 -> 27
    //   373: aload 5
    //   375: invokeinterface 271 1 0
    //   380: aload 4
    //   382: astore 6
    //   384: goto -357 -> 27
    //   387: astore 4
    //   389: aload 6
    //   391: astore 5
    //   393: aload 5
    //   395: ifnull +10 -> 405
    //   398: aload 5
    //   400: invokeinterface 271 1 0
    //   405: aload 4
    //   407: athrow
    //   408: ldc_w 309
    //   411: iconst_1
    //   412: anewarray 4	java/lang/Object
    //   415: dup
    //   416: iconst_0
    //   417: aload 6
    //   419: iload_1
    //   420: invokeinterface 301 2 0
    //   425: checkcast 283	com/google/android/gms/tagmanager/ap
    //   428: invokevirtual 313	com/google/android/gms/tagmanager/ap:eG	()J
    //   431: invokestatic 143	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   434: aastore
    //   435: invokestatic 49	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   438: invokestatic 128	com/google/android/gms/tagmanager/bh:W	(Ljava/lang/String;)V
    //   441: goto -149 -> 292
    //   444: astore 5
    //   446: aload 7
    //   448: astore 4
    //   450: aload 5
    //   452: astore 7
    //   454: aload 4
    //   456: astore 5
    //   458: new 160	java/lang/StringBuilder
    //   461: astore 8
    //   463: aload 4
    //   465: astore 5
    //   467: aload 8
    //   469: invokespecial 161	java/lang/StringBuilder:<init>	()V
    //   472: aload 4
    //   474: astore 5
    //   476: aload 8
    //   478: ldc_w 315
    //   481: invokevirtual 167	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   484: aload 7
    //   486: invokevirtual 276	android/database/sqlite/SQLiteException:getMessage	()Ljava/lang/String;
    //   489: invokevirtual 167	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   492: invokevirtual 174	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   495: invokestatic 128	com/google/android/gms/tagmanager/bh:W	(Ljava/lang/String;)V
    //   498: aload 4
    //   500: astore 5
    //   502: new 237	java/util/ArrayList
    //   505: astore 7
    //   507: aload 4
    //   509: astore 5
    //   511: aload 7
    //   513: invokespecial 238	java/util/ArrayList:<init>	()V
    //   516: iconst_0
    //   517: istore_1
    //   518: aload 4
    //   520: astore 5
    //   522: aload 6
    //   524: invokeinterface 319 1 0
    //   529: astore 8
    //   531: aload 4
    //   533: astore 5
    //   535: aload 8
    //   537: invokeinterface 324 1 0
    //   542: ifeq +42 -> 584
    //   545: aload 4
    //   547: astore 5
    //   549: aload 8
    //   551: invokeinterface 328 1 0
    //   556: checkcast 283	com/google/android/gms/tagmanager/ap
    //   559: astore 6
    //   561: aload 4
    //   563: astore 5
    //   565: aload 6
    //   567: invokevirtual 331	com/google/android/gms/tagmanager/ap:ou	()Ljava/lang/String;
    //   570: invokestatic 337	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   573: istore_3
    //   574: iload_1
    //   575: istore_2
    //   576: iload_3
    //   577: ifeq +28 -> 605
    //   580: iload_1
    //   581: ifeq +22 -> 603
    //   584: aload 4
    //   586: ifnull +10 -> 596
    //   589: aload 4
    //   591: invokeinterface 271 1 0
    //   596: aload 7
    //   598: astore 6
    //   600: goto -573 -> 27
    //   603: iconst_1
    //   604: istore_2
    //   605: aload 4
    //   607: astore 5
    //   609: aload 7
    //   611: aload 6
    //   613: invokeinterface 265 2 0
    //   618: pop
    //   619: iload_2
    //   620: istore_1
    //   621: goto -90 -> 531
    //   624: astore 4
    //   626: aload 5
    //   628: ifnull +10 -> 638
    //   631: aload 5
    //   633: invokeinterface 271 1 0
    //   638: aload 4
    //   640: athrow
    //   641: astore 4
    //   643: aload 7
    //   645: astore 5
    //   647: goto -21 -> 626
    //   650: astore 7
    //   652: goto -198 -> 454
    //   655: astore 6
    //   657: aload 4
    //   659: astore 5
    //   661: aload 6
    //   663: astore 4
    //   665: goto -272 -> 393
    //   668: astore 4
    //   670: goto -277 -> 393
    //   673: astore 6
    //   675: aload 4
    //   677: astore 7
    //   679: aload 5
    //   681: astore 4
    //   683: aload 7
    //   685: astore 5
    //   687: goto -355 -> 332
    //   690: astore 7
    //   692: aload 4
    //   694: astore 5
    //   696: aload 6
    //   698: astore 4
    //   700: aload 7
    //   702: astore 6
    //   704: goto -372 -> 332
    //   707: iinc 1 1
    //   710: goto -454 -> 256
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	713	0	this	cb
    //   0	713	1	paramInt	int
    //   575	45	2	i	int
    //   162	415	3	bool	boolean
    //   53	328	4	localObject1	Object
    //   387	19	4	localObject2	Object
    //   448	158	4	localObject3	Object
    //   624	15	4	localObject4	Object
    //   641	17	4	localObject5	Object
    //   663	1	4	localObject6	Object
    //   668	8	4	localObject7	Object
    //   681	18	4	localObject8	Object
    //   7	392	5	localObject9	Object
    //   444	7	5	localSQLiteException1	SQLiteException
    //   456	239	5	localObject10	Object
    //   25	246	6	localObject11	Object
    //   319	32	6	localSQLiteException2	SQLiteException
    //   366	246	6	localObject12	Object
    //   655	7	6	localObject13	Object
    //   673	24	6	localSQLiteException3	SQLiteException
    //   702	1	6	localSQLiteException4	SQLiteException
    //   16	628	7	localObject14	Object
    //   650	1	7	localSQLiteException5	SQLiteException
    //   677	7	7	localObject15	Object
    //   690	11	7	localSQLiteException6	SQLiteException
    //   47	503	8	localObject16	Object
    //   197	39	9	str	String
    // Exception table:
    //   from	to	target	type
    //   33	91	319	android/database/sqlite/SQLiteException
    //   33	91	387	finally
    //   244	254	444	android/database/sqlite/SQLiteException
    //   256	292	444	android/database/sqlite/SQLiteException
    //   292	300	444	android/database/sqlite/SQLiteException
    //   408	441	444	android/database/sqlite/SQLiteException
    //   183	199	624	finally
    //   203	209	624	finally
    //   213	244	624	finally
    //   458	463	624	finally
    //   467	472	624	finally
    //   476	498	624	finally
    //   502	507	624	finally
    //   511	516	624	finally
    //   522	531	624	finally
    //   535	545	624	finally
    //   549	561	624	finally
    //   565	574	624	finally
    //   609	619	624	finally
    //   244	254	641	finally
    //   256	292	641	finally
    //   292	300	641	finally
    //   408	441	641	finally
    //   183	199	650	android/database/sqlite/SQLiteException
    //   203	209	650	android/database/sqlite/SQLiteException
    //   213	244	650	android/database/sqlite/SQLiteException
    //   91	101	655	finally
    //   101	111	655	finally
    //   111	163	655	finally
    //   332	364	668	finally
    //   91	101	673	android/database/sqlite/SQLiteException
    //   101	111	690	android/database/sqlite/SQLiteException
    //   111	163	690	android/database/sqlite/SQLiteException
  }
  
  void b(String[] paramArrayOfString)
  {
    boolean bool = true;
    if ((paramArrayOfString == null) || (paramArrayOfString.length == 0)) {}
    SQLiteDatabase localSQLiteDatabase;
    do
    {
      return;
      localSQLiteDatabase = al("Error opening database for deleteHits.");
    } while (localSQLiteDatabase == null);
    String str = String.format("HIT_ID in (%s)", new Object[] { TextUtils.join(",", Collections.nCopies(paramArrayOfString.length, "?")) });
    for (;;)
    {
      try
      {
        localSQLiteDatabase.delete("gtm_hits", str, paramArrayOfString);
        paramArrayOfString = this.apY;
        if (eO() != 0) {
          break label96;
        }
        paramArrayOfString.z(bool);
      }
      catch (SQLiteException paramArrayOfString)
      {
        bh.W("Error deleting hits");
      }
      break;
      label96:
      bool = false;
    }
  }
  
  public void dispatch()
  {
    bh.V("GTM Dispatch running...");
    if (!this.apX.dX()) {}
    for (;;)
    {
      return;
      List localList = G(40);
      if (localList.isEmpty())
      {
        bh.V("...nothing to dispatch");
        this.apY.z(true);
      }
      else
      {
        this.apX.j(localList);
        if (oH() > 0) {
          cy.pw().dispatch();
        }
      }
    }
  }
  
  int eN()
  {
    boolean bool = true;
    int i = 0;
    long l = this.yD.currentTimeMillis();
    if (l <= this.Bd + 86400000L) {}
    do
    {
      return i;
      this.Bd = l;
      localObject = al("Error opening database for deleteStaleHits.");
    } while (localObject == null);
    i = ((SQLiteDatabase)localObject).delete("gtm_hits", "HIT_TIME < ?", new String[] { Long.toString(this.yD.currentTimeMillis() - 2592000000L) });
    Object localObject = this.apY;
    if (eO() == 0) {}
    for (;;)
    {
      ((au)localObject).z(bool);
      break;
      bool = false;
    }
  }
  
  int eO()
  {
    Object localObject3 = null;
    Object localObject1 = null;
    int i = 0;
    int k = 0;
    Object localObject5 = al("Error opening database for getNumStoredHits.");
    int j;
    if (localObject5 == null) {
      j = k;
    }
    for (;;)
    {
      return j;
      try
      {
        localObject5 = ((SQLiteDatabase)localObject5).rawQuery("SELECT COUNT(*) from gtm_hits", null);
        localObject1 = localObject5;
        localObject3 = localObject5;
        if (((Cursor)localObject5).moveToFirst())
        {
          localObject1 = localObject5;
          localObject3 = localObject5;
          long l = ((Cursor)localObject5).getLong(0);
          i = (int)l;
        }
        j = i;
        if (localObject5 == null) {
          continue;
        }
        ((Cursor)localObject5).close();
        j = i;
      }
      catch (SQLiteException localSQLiteException)
      {
        localObject4 = localObject1;
        bh.W("Error getting numStoredHits");
        j = k;
        if (localObject1 == null) {
          continue;
        }
        ((Cursor)localObject1).close();
        j = k;
      }
      finally
      {
        Object localObject4;
        if (localObject4 != null) {
          ((Cursor)localObject4).close();
        }
      }
    }
  }
  
  public void f(long paramLong, String paramString)
  {
    eN();
    eM();
    g(paramLong, paramString);
  }
  
  /* Error */
  int oH()
  {
    // Byte code:
    //   0: iconst_0
    //   1: istore_1
    //   2: aconst_null
    //   3: astore 4
    //   5: aload_0
    //   6: ldc -124
    //   8: invokespecial 134	com/google/android/gms/tagmanager/cb:al	(Ljava/lang/String;)Landroid/database/sqlite/SQLiteDatabase;
    //   11: astore_3
    //   12: aload_3
    //   13: ifnonnull +5 -> 18
    //   16: iload_1
    //   17: ireturn
    //   18: aload_3
    //   19: ldc 35
    //   21: iconst_2
    //   22: anewarray 45	java/lang/String
    //   25: dup
    //   26: iconst_0
    //   27: ldc 37
    //   29: aastore
    //   30: dup
    //   31: iconst_1
    //   32: ldc 43
    //   34: aastore
    //   35: ldc_w 424
    //   38: aconst_null
    //   39: aconst_null
    //   40: aconst_null
    //   41: aconst_null
    //   42: invokevirtual 427	android/database/sqlite/SQLiteDatabase:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   45: astore_3
    //   46: aload_3
    //   47: invokeinterface 430 1 0
    //   52: istore_2
    //   53: iload_2
    //   54: istore_1
    //   55: aload_3
    //   56: ifnull +11 -> 67
    //   59: aload_3
    //   60: invokeinterface 271 1 0
    //   65: iload_2
    //   66: istore_1
    //   67: goto -51 -> 16
    //   70: astore_3
    //   71: aconst_null
    //   72: astore_3
    //   73: ldc_w 432
    //   76: invokestatic 128	com/google/android/gms/tagmanager/bh:W	(Ljava/lang/String;)V
    //   79: aload_3
    //   80: ifnull +60 -> 140
    //   83: aload_3
    //   84: invokeinterface 271 1 0
    //   89: iconst_0
    //   90: istore_1
    //   91: goto -24 -> 67
    //   94: astore_3
    //   95: aload 4
    //   97: ifnull +10 -> 107
    //   100: aload 4
    //   102: invokeinterface 271 1 0
    //   107: aload_3
    //   108: athrow
    //   109: astore 4
    //   111: aload_3
    //   112: astore 5
    //   114: aload 4
    //   116: astore_3
    //   117: aload 5
    //   119: astore 4
    //   121: goto -26 -> 95
    //   124: astore 5
    //   126: aload_3
    //   127: astore 4
    //   129: aload 5
    //   131: astore_3
    //   132: goto -37 -> 95
    //   135: astore 4
    //   137: goto -64 -> 73
    //   140: iconst_0
    //   141: istore_1
    //   142: goto -75 -> 67
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	145	0	this	cb
    //   1	141	1	i	int
    //   52	14	2	j	int
    //   11	49	3	localObject1	Object
    //   70	1	3	localSQLiteException1	SQLiteException
    //   72	12	3	localObject2	Object
    //   94	18	3	localObject3	Object
    //   116	16	3	localObject4	Object
    //   3	98	4	localObject5	Object
    //   109	6	4	localObject6	Object
    //   119	9	4	localObject7	Object
    //   135	1	4	localSQLiteException2	SQLiteException
    //   112	6	5	localObject8	Object
    //   124	6	5	localObject9	Object
    // Exception table:
    //   from	to	target	type
    //   18	46	70	android/database/sqlite/SQLiteException
    //   18	46	94	finally
    //   46	53	109	finally
    //   73	79	124	finally
    //   46	53	135	android/database/sqlite/SQLiteException
  }
  
  class a
    implements db.a
  {
    a() {}
    
    public void a(ap paramap)
    {
      cb.a(cb.this, paramap.eG());
    }
    
    public void b(ap paramap)
    {
      cb.a(cb.this, paramap.eG());
      bh.V("Permanent failure dispatching hitId: " + paramap.eG());
    }
    
    public void c(ap paramap)
    {
      long l = paramap.ot();
      if (l == 0L) {
        cb.a(cb.this, paramap.eG(), cb.a(cb.this).currentTimeMillis());
      }
      for (;;)
      {
        return;
        if (l + 14400000L < cb.a(cb.this).currentTimeMillis())
        {
          cb.a(cb.this, paramap.eG());
          bh.V("Giving up on failed hitId: " + paramap.eG());
        }
      }
    }
  }
  
  class b
    extends SQLiteOpenHelper
  {
    private boolean Bf;
    private long Bg = 0L;
    
    b(Context paramContext, String paramString)
    {
      super(paramString, null, 1);
    }
    
    private void a(SQLiteDatabase paramSQLiteDatabase)
    {
      paramSQLiteDatabase = paramSQLiteDatabase.rawQuery("SELECT * FROM gtm_hits WHERE 0", null);
      HashSet localHashSet = new HashSet();
      try
      {
        String[] arrayOfString = paramSQLiteDatabase.getColumnNames();
        for (int i = 0; i < arrayOfString.length; i++) {
          localHashSet.add(arrayOfString[i]);
        }
        paramSQLiteDatabase.close();
        if ((!localHashSet.remove("hit_id")) || (!localHashSet.remove("hit_url")) || (!localHashSet.remove("hit_time")) || (!localHashSet.remove("hit_first_send_time"))) {
          throw new SQLiteException("Database column missing");
        }
      }
      finally
      {
        paramSQLiteDatabase.close();
      }
      if (!localHashSet.isEmpty()) {
        throw new SQLiteException("Database has extra columns");
      }
    }
    
    /* Error */
    private boolean a(String paramString, SQLiteDatabase paramSQLiteDatabase)
    {
      // Byte code:
      //   0: aconst_null
      //   1: astore 5
      //   3: aload_2
      //   4: ldc 80
      //   6: iconst_1
      //   7: anewarray 82	java/lang/String
      //   10: dup
      //   11: iconst_0
      //   12: ldc 84
      //   14: aastore
      //   15: ldc 86
      //   17: iconst_1
      //   18: anewarray 82	java/lang/String
      //   21: dup
      //   22: iconst_0
      //   23: aload_1
      //   24: aastore
      //   25: aconst_null
      //   26: aconst_null
      //   27: aconst_null
      //   28: invokevirtual 90	android/database/sqlite/SQLiteDatabase:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
      //   31: astore_2
      //   32: aload_2
      //   33: invokeinterface 93 1 0
      //   38: istore_3
      //   39: iload_3
      //   40: istore 4
      //   42: aload_2
      //   43: ifnull +12 -> 55
      //   46: aload_2
      //   47: invokeinterface 53 1 0
      //   52: iload_3
      //   53: istore 4
      //   55: iload 4
      //   57: ireturn
      //   58: astore_2
      //   59: aconst_null
      //   60: astore_2
      //   61: new 95	java/lang/StringBuilder
      //   64: astore 5
      //   66: aload 5
      //   68: invokespecial 96	java/lang/StringBuilder:<init>	()V
      //   71: aload 5
      //   73: ldc 98
      //   75: invokevirtual 102	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   78: aload_1
      //   79: invokevirtual 102	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   82: invokevirtual 106	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   85: invokestatic 111	com/google/android/gms/tagmanager/bh:W	(Ljava/lang/String;)V
      //   88: aload_2
      //   89: ifnull +9 -> 98
      //   92: aload_2
      //   93: invokeinterface 53 1 0
      //   98: iconst_0
      //   99: istore 4
      //   101: goto -46 -> 55
      //   104: astore_1
      //   105: aload 5
      //   107: astore_2
      //   108: aload_2
      //   109: ifnull +9 -> 118
      //   112: aload_2
      //   113: invokeinterface 53 1 0
      //   118: aload_1
      //   119: athrow
      //   120: astore_1
      //   121: goto -13 -> 108
      //   124: astore_1
      //   125: goto -17 -> 108
      //   128: astore 5
      //   130: goto -69 -> 61
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	133	0	this	b
      //   0	133	1	paramString	String
      //   0	133	2	paramSQLiteDatabase	SQLiteDatabase
      //   38	15	3	bool1	boolean
      //   40	60	4	bool2	boolean
      //   1	105	5	localStringBuilder	StringBuilder
      //   128	1	5	localSQLiteException	SQLiteException
      // Exception table:
      //   from	to	target	type
      //   3	32	58	android/database/sqlite/SQLiteException
      //   3	32	104	finally
      //   32	39	120	finally
      //   61	88	124	finally
      //   32	39	128	android/database/sqlite/SQLiteException
    }
    
    public SQLiteDatabase getWritableDatabase()
    {
      if ((this.Bf) && (this.Bg + 3600000L > cb.a(cb.this).currentTimeMillis())) {
        throw new SQLiteException("Database creation failed");
      }
      Object localObject1 = null;
      this.Bf = true;
      this.Bg = cb.a(cb.this).currentTimeMillis();
      try
      {
        localObject2 = super.getWritableDatabase();
        localObject1 = localObject2;
      }
      catch (SQLiteException localSQLiteException)
      {
        for (;;)
        {
          Object localObject2;
          cb.c(cb.this).getDatabasePath(cb.b(cb.this)).delete();
        }
      }
      localObject2 = localObject1;
      if (localObject1 == null) {
        localObject2 = super.getWritableDatabase();
      }
      this.Bf = false;
      return (SQLiteDatabase)localObject2;
    }
    
    public void onCreate(SQLiteDatabase paramSQLiteDatabase)
    {
      ak.ag(paramSQLiteDatabase.getPath());
    }
    
    public void onOpen(SQLiteDatabase paramSQLiteDatabase)
    {
      Cursor localCursor;
      if (Build.VERSION.SDK_INT < 15) {
        localCursor = paramSQLiteDatabase.rawQuery("PRAGMA journal_mode=memory", null);
      }
      for (;;)
      {
        try
        {
          localCursor.moveToFirst();
          localCursor.close();
          if (!a("gtm_hits", paramSQLiteDatabase))
          {
            paramSQLiteDatabase.execSQL(cb.oI());
            return;
          }
        }
        finally
        {
          localCursor.close();
        }
        a(paramSQLiteDatabase);
      }
    }
    
    public void onUpgrade(SQLiteDatabase paramSQLiteDatabase, int paramInt1, int paramInt2) {}
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\tagmanager\cb.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */