package com.google.android.gms.analytics;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build.VERSION;
import android.text.TextUtils;
import com.google.android.gms.internal.hb;
import com.google.android.gms.internal.ju;
import com.google.android.gms.internal.jw;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.apache.http.impl.client.DefaultHttpClient;

class ab
  implements d
{
  private static final String AY = String.format("CREATE TABLE IF NOT EXISTS %s ( '%s' INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, '%s' INTEGER NOT NULL, '%s' TEXT NOT NULL, '%s' TEXT NOT NULL, '%s' INTEGER);", new Object[] { "hits2", "hit_id", "hit_time", "hit_url", "hit_string", "hit_app_id" });
  private final a AZ;
  private volatile m Ba;
  private final String Bb;
  private aa Bc;
  private long Bd;
  private final int Be;
  private final Context mContext;
  private ju yD;
  private final e yl;
  
  ab(e parame, Context paramContext)
  {
    this(parame, paramContext, "google_analytics_v4.db", 2000);
  }
  
  ab(e parame, Context paramContext, String paramString, int paramInt)
  {
    this.mContext = paramContext.getApplicationContext();
    this.Bb = paramString;
    this.yl = parame;
    this.yD = jw.hA();
    this.AZ = new a(this.mContext, this.Bb);
    this.Ba = new ag(new DefaultHttpClient(), this.mContext);
    this.Bd = 0L;
    this.Be = paramInt;
  }
  
  static String A(Map<String, String> paramMap)
  {
    ArrayList localArrayList = new ArrayList(paramMap.size());
    Iterator localIterator = paramMap.entrySet().iterator();
    while (localIterator.hasNext())
    {
      paramMap = (Map.Entry)localIterator.next();
      localArrayList.add(x.encode((String)paramMap.getKey()) + "=" + x.encode((String)paramMap.getValue()));
    }
    return TextUtils.join("&", localArrayList);
  }
  
  private void a(Map<String, String> paramMap, long paramLong, String paramString)
  {
    SQLiteDatabase localSQLiteDatabase = al("Error opening database for putHit");
    if (localSQLiteDatabase == null) {
      return;
    }
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("hit_string", A(paramMap));
    localContentValues.put("hit_time", Long.valueOf(paramLong));
    if (paramMap.containsKey("AppUID")) {}
    for (;;)
    {
      try
      {
        paramLong = Long.parseLong((String)paramMap.get("AppUID"));
        localContentValues.put("hit_app_id", Long.valueOf(paramLong));
        paramMap = paramString;
        if (paramString == null) {
          paramMap = "http://www.google-analytics.com/collect";
        }
        if (paramMap.length() == 0) {
          z.W("Empty path: not sending hit");
        }
      }
      catch (NumberFormatException paramMap)
      {
        paramLong = 0L;
        continue;
        localContentValues.put("hit_url", paramMap);
        try
        {
          localSQLiteDatabase.insert("hits2", null, localContentValues);
          this.yl.z(false);
        }
        catch (SQLiteException paramMap)
        {
          z.W("Error storing hit");
        }
      }
      break;
      paramLong = 0L;
    }
  }
  
  private void a(Map<String, String> paramMap, Collection<hb> paramCollection)
  {
    String str = "&_v".substring(1);
    if (paramCollection != null)
    {
      Iterator localIterator = paramCollection.iterator();
      while (localIterator.hasNext())
      {
        paramCollection = (hb)localIterator.next();
        if ("appendVersion".equals(paramCollection.getId())) {
          paramMap.put(str, paramCollection.getValue());
        }
      }
    }
  }
  
  private SQLiteDatabase al(String paramString)
  {
    try
    {
      SQLiteDatabase localSQLiteDatabase = this.AZ.getWritableDatabase();
      paramString = localSQLiteDatabase;
    }
    catch (SQLiteException localSQLiteException)
    {
      for (;;)
      {
        z.W(paramString);
        paramString = null;
      }
    }
    return paramString;
  }
  
  private void eM()
  {
    int i = eO() - this.Be + 1;
    if (i > 0)
    {
      List localList = F(i);
      z.V("Store full, deleting " + localList.size() + " hits to make room.");
      b((String[])localList.toArray(new String[0]));
    }
  }
  
  /* Error */
  List<String> F(int paramInt)
  {
    // Byte code:
    //   0: new 105	java/util/ArrayList
    //   3: dup
    //   4: invokespecial 312	java/util/ArrayList:<init>	()V
    //   7: astore 6
    //   9: iload_1
    //   10: ifgt +12 -> 22
    //   13: ldc_w 314
    //   16: invokestatic 233	com/google/android/gms/analytics/z:W	(Ljava/lang/String;)V
    //   19: aload 6
    //   21: areturn
    //   22: aload_0
    //   23: ldc_w 316
    //   26: invokespecial 189	com/google/android/gms/analytics/ab:al	(Ljava/lang/String;)Landroid/database/sqlite/SQLiteDatabase;
    //   29: astore 5
    //   31: aload 5
    //   33: ifnonnull +6 -> 39
    //   36: goto -17 -> 19
    //   39: ldc_w 318
    //   42: iconst_1
    //   43: anewarray 4	java/lang/Object
    //   46: dup
    //   47: iconst_0
    //   48: ldc 36
    //   50: aastore
    //   51: invokestatic 50	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   54: astore_3
    //   55: iload_1
    //   56: invokestatic 322	java/lang/Integer:toString	(I)Ljava/lang/String;
    //   59: astore 4
    //   61: aload 5
    //   63: ldc 34
    //   65: iconst_1
    //   66: anewarray 46	java/lang/String
    //   69: dup
    //   70: iconst_0
    //   71: ldc 36
    //   73: aastore
    //   74: aconst_null
    //   75: aconst_null
    //   76: aconst_null
    //   77: aconst_null
    //   78: aload_3
    //   79: aload 4
    //   81: invokevirtual 326	android/database/sqlite/SQLiteDatabase:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   84: astore 4
    //   86: aload 4
    //   88: astore_3
    //   89: aload 4
    //   91: invokeinterface 331 1 0
    //   96: ifeq +40 -> 136
    //   99: aload 4
    //   101: astore_3
    //   102: aload 6
    //   104: aload 4
    //   106: iconst_0
    //   107: invokeinterface 335 2 0
    //   112: invokestatic 338	java/lang/String:valueOf	(J)Ljava/lang/String;
    //   115: invokeinterface 167 2 0
    //   120: pop
    //   121: aload 4
    //   123: astore_3
    //   124: aload 4
    //   126: invokeinterface 341 1 0
    //   131: istore_2
    //   132: iload_2
    //   133: ifne -34 -> 99
    //   136: aload 4
    //   138: ifnull +10 -> 148
    //   141: aload 4
    //   143: invokeinterface 344 1 0
    //   148: goto -129 -> 19
    //   151: astore 5
    //   153: aconst_null
    //   154: astore 4
    //   156: aload 4
    //   158: astore_3
    //   159: new 138	java/lang/StringBuilder
    //   162: astore 7
    //   164: aload 4
    //   166: astore_3
    //   167: aload 7
    //   169: invokespecial 139	java/lang/StringBuilder:<init>	()V
    //   172: aload 4
    //   174: astore_3
    //   175: aload 7
    //   177: ldc_w 346
    //   180: invokevirtual 152	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   183: aload 5
    //   185: invokevirtual 349	android/database/sqlite/SQLiteException:getMessage	()Ljava/lang/String;
    //   188: invokevirtual 152	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   191: invokevirtual 161	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   194: invokestatic 233	com/google/android/gms/analytics/z:W	(Ljava/lang/String;)V
    //   197: aload 4
    //   199: ifnull -51 -> 148
    //   202: aload 4
    //   204: invokeinterface 344 1 0
    //   209: goto -61 -> 148
    //   212: astore_3
    //   213: aconst_null
    //   214: astore 4
    //   216: aload_3
    //   217: astore 5
    //   219: aload 4
    //   221: ifnull +10 -> 231
    //   224: aload 4
    //   226: invokeinterface 344 1 0
    //   231: aload 5
    //   233: athrow
    //   234: astore 5
    //   236: aload_3
    //   237: astore 4
    //   239: goto -20 -> 219
    //   242: astore 5
    //   244: goto -88 -> 156
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	247	0	this	ab
    //   0	247	1	paramInt	int
    //   131	2	2	bool	boolean
    //   54	121	3	localObject1	Object
    //   212	25	3	localObject2	Object
    //   59	179	4	localObject3	Object
    //   29	33	5	localSQLiteDatabase	SQLiteDatabase
    //   151	33	5	localSQLiteException1	SQLiteException
    //   217	15	5	localObject4	Object
    //   234	1	5	localObject5	Object
    //   242	1	5	localSQLiteException2	SQLiteException
    //   7	96	6	localArrayList	ArrayList
    //   162	14	7	localStringBuilder	StringBuilder
    // Exception table:
    //   from	to	target	type
    //   39	86	151	android/database/sqlite/SQLiteException
    //   39	86	212	finally
    //   89	99	234	finally
    //   102	121	234	finally
    //   124	132	234	finally
    //   159	164	234	finally
    //   167	172	234	finally
    //   175	197	234	finally
    //   89	99	242	android/database/sqlite/SQLiteException
    //   102	121	242	android/database/sqlite/SQLiteException
    //   124	132	242	android/database/sqlite/SQLiteException
  }
  
  /* Error */
  public List<w> G(int paramInt)
  {
    // Byte code:
    //   0: new 105	java/util/ArrayList
    //   3: dup
    //   4: invokespecial 312	java/util/ArrayList:<init>	()V
    //   7: astore 5
    //   9: aload_0
    //   10: ldc_w 353
    //   13: invokespecial 189	com/google/android/gms/analytics/ab:al	(Ljava/lang/String;)Landroid/database/sqlite/SQLiteDatabase;
    //   16: astore 7
    //   18: aload 7
    //   20: ifnonnull +10 -> 30
    //   23: aload 5
    //   25: astore 6
    //   27: aload 6
    //   29: areturn
    //   30: aconst_null
    //   31: astore 6
    //   33: ldc_w 318
    //   36: iconst_1
    //   37: anewarray 4	java/lang/Object
    //   40: dup
    //   41: iconst_0
    //   42: ldc 36
    //   44: aastore
    //   45: invokestatic 50	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   48: astore 8
    //   50: iload_1
    //   51: invokestatic 322	java/lang/Integer:toString	(I)Ljava/lang/String;
    //   54: astore 4
    //   56: aload 7
    //   58: ldc 34
    //   60: iconst_2
    //   61: anewarray 46	java/lang/String
    //   64: dup
    //   65: iconst_0
    //   66: ldc 36
    //   68: aastore
    //   69: dup
    //   70: iconst_1
    //   71: ldc 38
    //   73: aastore
    //   74: aconst_null
    //   75: aconst_null
    //   76: aconst_null
    //   77: aconst_null
    //   78: aload 8
    //   80: aload 4
    //   82: invokevirtual 326	android/database/sqlite/SQLiteDatabase:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   85: astore 4
    //   87: new 105	java/util/ArrayList
    //   90: astore 6
    //   92: aload 6
    //   94: invokespecial 312	java/util/ArrayList:<init>	()V
    //   97: aload 4
    //   99: invokeinterface 331 1 0
    //   104: ifeq +52 -> 156
    //   107: new 355	com/google/android/gms/analytics/w
    //   110: astore 5
    //   112: aload 5
    //   114: aconst_null
    //   115: aload 4
    //   117: iconst_0
    //   118: invokeinterface 335 2 0
    //   123: aload 4
    //   125: iconst_1
    //   126: invokeinterface 335 2 0
    //   131: invokespecial 358	com/google/android/gms/analytics/w:<init>	(Ljava/lang/String;JJ)V
    //   134: aload 6
    //   136: aload 5
    //   138: invokeinterface 167 2 0
    //   143: pop
    //   144: aload 4
    //   146: invokeinterface 341 1 0
    //   151: istore_3
    //   152: iload_3
    //   153: ifne -46 -> 107
    //   156: aload 4
    //   158: ifnull +10 -> 168
    //   161: aload 4
    //   163: invokeinterface 344 1 0
    //   168: aload 4
    //   170: astore 5
    //   172: ldc_w 318
    //   175: iconst_1
    //   176: anewarray 4	java/lang/Object
    //   179: dup
    //   180: iconst_0
    //   181: ldc 36
    //   183: aastore
    //   184: invokestatic 50	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   187: astore 9
    //   189: aload 4
    //   191: astore 5
    //   193: iload_1
    //   194: invokestatic 322	java/lang/Integer:toString	(I)Ljava/lang/String;
    //   197: astore 8
    //   199: aload 4
    //   201: astore 5
    //   203: aload 7
    //   205: ldc 34
    //   207: iconst_3
    //   208: anewarray 46	java/lang/String
    //   211: dup
    //   212: iconst_0
    //   213: ldc 36
    //   215: aastore
    //   216: dup
    //   217: iconst_1
    //   218: ldc 42
    //   220: aastore
    //   221: dup
    //   222: iconst_2
    //   223: ldc 40
    //   225: aastore
    //   226: aconst_null
    //   227: aconst_null
    //   228: aconst_null
    //   229: aconst_null
    //   230: aload 9
    //   232: aload 8
    //   234: invokevirtual 326	android/database/sqlite/SQLiteDatabase:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   237: astore 7
    //   239: aload 7
    //   241: invokeinterface 331 1 0
    //   246: ifeq +75 -> 321
    //   249: iconst_0
    //   250: istore_1
    //   251: aload 7
    //   253: checkcast 360	android/database/sqlite/SQLiteCursor
    //   256: invokevirtual 364	android/database/sqlite/SQLiteCursor:getWindow	()Landroid/database/CursorWindow;
    //   259: invokevirtual 369	android/database/CursorWindow:getNumRows	()I
    //   262: ifle +155 -> 417
    //   265: aload 6
    //   267: iload_1
    //   268: invokeinterface 372 2 0
    //   273: checkcast 355	com/google/android/gms/analytics/w
    //   276: aload 7
    //   278: iconst_1
    //   279: invokeinterface 375 2 0
    //   284: invokevirtual 378	com/google/android/gms/analytics/w:aj	(Ljava/lang/String;)V
    //   287: aload 6
    //   289: iload_1
    //   290: invokeinterface 372 2 0
    //   295: checkcast 355	com/google/android/gms/analytics/w
    //   298: aload 7
    //   300: iconst_2
    //   301: invokeinterface 375 2 0
    //   306: invokevirtual 381	com/google/android/gms/analytics/w:ak	(Ljava/lang/String;)V
    //   309: aload 7
    //   311: invokeinterface 341 1 0
    //   316: istore_3
    //   317: iload_3
    //   318: ifne +394 -> 712
    //   321: aload 7
    //   323: ifnull +10 -> 333
    //   326: aload 7
    //   328: invokeinterface 344 1 0
    //   333: goto -306 -> 27
    //   336: astore 6
    //   338: aconst_null
    //   339: astore 4
    //   341: new 138	java/lang/StringBuilder
    //   344: astore 7
    //   346: aload 7
    //   348: invokespecial 139	java/lang/StringBuilder:<init>	()V
    //   351: aload 7
    //   353: ldc_w 346
    //   356: invokevirtual 152	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   359: aload 6
    //   361: invokevirtual 349	android/database/sqlite/SQLiteException:getMessage	()Ljava/lang/String;
    //   364: invokevirtual 152	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   367: invokevirtual 161	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   370: invokestatic 233	com/google/android/gms/analytics/z:W	(Ljava/lang/String;)V
    //   373: aload 5
    //   375: astore 6
    //   377: aload 4
    //   379: ifnull -352 -> 27
    //   382: aload 4
    //   384: invokeinterface 344 1 0
    //   389: aload 5
    //   391: astore 6
    //   393: goto -366 -> 27
    //   396: astore 4
    //   398: aload 6
    //   400: astore 5
    //   402: aload 5
    //   404: ifnull +10 -> 414
    //   407: aload 5
    //   409: invokeinterface 344 1 0
    //   414: aload 4
    //   416: athrow
    //   417: ldc_w 383
    //   420: iconst_1
    //   421: anewarray 4	java/lang/Object
    //   424: dup
    //   425: iconst_0
    //   426: aload 6
    //   428: iload_1
    //   429: invokeinterface 372 2 0
    //   434: checkcast 355	com/google/android/gms/analytics/w
    //   437: invokevirtual 387	com/google/android/gms/analytics/w:eG	()J
    //   440: invokestatic 204	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   443: aastore
    //   444: invokestatic 50	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   447: invokestatic 233	com/google/android/gms/analytics/z:W	(Ljava/lang/String;)V
    //   450: goto -141 -> 309
    //   453: astore 5
    //   455: aload 7
    //   457: astore 4
    //   459: aload 5
    //   461: astore 7
    //   463: aload 4
    //   465: astore 5
    //   467: new 138	java/lang/StringBuilder
    //   470: astore 8
    //   472: aload 4
    //   474: astore 5
    //   476: aload 8
    //   478: invokespecial 139	java/lang/StringBuilder:<init>	()V
    //   481: aload 4
    //   483: astore 5
    //   485: aload 8
    //   487: ldc_w 389
    //   490: invokevirtual 152	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   493: aload 7
    //   495: invokevirtual 349	android/database/sqlite/SQLiteException:getMessage	()Ljava/lang/String;
    //   498: invokevirtual 152	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   501: invokevirtual 161	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   504: invokestatic 233	com/google/android/gms/analytics/z:W	(Ljava/lang/String;)V
    //   507: aload 4
    //   509: astore 5
    //   511: new 105	java/util/ArrayList
    //   514: astore 7
    //   516: aload 4
    //   518: astore 5
    //   520: aload 7
    //   522: invokespecial 312	java/util/ArrayList:<init>	()V
    //   525: iconst_0
    //   526: istore_1
    //   527: aload 4
    //   529: astore 5
    //   531: aload 6
    //   533: invokeinterface 390 1 0
    //   538: astore 6
    //   540: aload 4
    //   542: astore 5
    //   544: aload 6
    //   546: invokeinterface 130 1 0
    //   551: ifeq +42 -> 593
    //   554: aload 4
    //   556: astore 5
    //   558: aload 6
    //   560: invokeinterface 134 1 0
    //   565: checkcast 355	com/google/android/gms/analytics/w
    //   568: astore 8
    //   570: aload 4
    //   572: astore 5
    //   574: aload 8
    //   576: invokevirtual 393	com/google/android/gms/analytics/w:eF	()Ljava/lang/String;
    //   579: invokestatic 397	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   582: istore_3
    //   583: iload_1
    //   584: istore_2
    //   585: iload_3
    //   586: ifeq +28 -> 614
    //   589: iload_1
    //   590: ifeq +22 -> 612
    //   593: aload 4
    //   595: ifnull +10 -> 605
    //   598: aload 4
    //   600: invokeinterface 344 1 0
    //   605: aload 7
    //   607: astore 6
    //   609: goto -582 -> 27
    //   612: iconst_1
    //   613: istore_2
    //   614: aload 4
    //   616: astore 5
    //   618: aload 7
    //   620: aload 8
    //   622: invokeinterface 167 2 0
    //   627: pop
    //   628: iload_2
    //   629: istore_1
    //   630: goto -90 -> 540
    //   633: astore 4
    //   635: aload 5
    //   637: ifnull +10 -> 647
    //   640: aload 5
    //   642: invokeinterface 344 1 0
    //   647: aload 4
    //   649: athrow
    //   650: astore 4
    //   652: aload 7
    //   654: astore 5
    //   656: goto -21 -> 635
    //   659: astore 7
    //   661: goto -198 -> 463
    //   664: astore 6
    //   666: aload 4
    //   668: astore 5
    //   670: aload 6
    //   672: astore 4
    //   674: goto -272 -> 402
    //   677: astore 5
    //   679: aload 4
    //   681: astore 6
    //   683: aload 5
    //   685: astore 4
    //   687: aload 6
    //   689: astore 5
    //   691: goto -289 -> 402
    //   694: astore 6
    //   696: goto -355 -> 341
    //   699: astore 7
    //   701: aload 6
    //   703: astore 5
    //   705: aload 7
    //   707: astore 6
    //   709: goto -368 -> 341
    //   712: iinc 1 1
    //   715: goto -464 -> 251
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	718	0	this	ab
    //   0	718	1	paramInt	int
    //   584	45	2	i	int
    //   151	435	3	bool	boolean
    //   54	329	4	localObject1	Object
    //   396	19	4	localObject2	Object
    //   457	158	4	localObject3	Object
    //   633	15	4	localObject4	Object
    //   650	17	4	localObject5	Object
    //   672	14	4	localObject6	Object
    //   7	401	5	localObject7	Object
    //   453	7	5	localSQLiteException1	SQLiteException
    //   465	204	5	localObject8	Object
    //   677	7	5	localObject9	Object
    //   689	15	5	localObject10	Object
    //   25	263	6	localObject11	Object
    //   336	24	6	localSQLiteException2	SQLiteException
    //   375	233	6	localObject12	Object
    //   664	7	6	localObject13	Object
    //   681	7	6	localObject14	Object
    //   694	8	6	localSQLiteException3	SQLiteException
    //   707	1	6	localSQLiteException4	SQLiteException
    //   16	637	7	localObject15	Object
    //   659	1	7	localSQLiteException5	SQLiteException
    //   699	7	7	localSQLiteException6	SQLiteException
    //   48	573	8	localObject16	Object
    //   187	44	9	str	String
    // Exception table:
    //   from	to	target	type
    //   33	87	336	android/database/sqlite/SQLiteException
    //   33	87	396	finally
    //   239	249	453	android/database/sqlite/SQLiteException
    //   251	309	453	android/database/sqlite/SQLiteException
    //   309	317	453	android/database/sqlite/SQLiteException
    //   417	450	453	android/database/sqlite/SQLiteException
    //   172	189	633	finally
    //   193	199	633	finally
    //   203	239	633	finally
    //   467	472	633	finally
    //   476	481	633	finally
    //   485	507	633	finally
    //   511	516	633	finally
    //   520	525	633	finally
    //   531	540	633	finally
    //   544	554	633	finally
    //   558	570	633	finally
    //   574	583	633	finally
    //   618	628	633	finally
    //   239	249	650	finally
    //   251	309	650	finally
    //   309	317	650	finally
    //   417	450	650	finally
    //   172	189	659	android/database/sqlite/SQLiteException
    //   193	199	659	android/database/sqlite/SQLiteException
    //   203	239	659	android/database/sqlite/SQLiteException
    //   87	97	664	finally
    //   97	107	664	finally
    //   107	152	664	finally
    //   341	373	677	finally
    //   87	97	694	android/database/sqlite/SQLiteException
    //   97	107	699	android/database/sqlite/SQLiteException
    //   107	152	699	android/database/sqlite/SQLiteException
  }
  
  public void a(Map<String, String> paramMap, long paramLong, String paramString, Collection<hb> paramCollection)
  {
    eN();
    eM();
    a(paramMap, paramCollection);
    a(paramMap, paramLong, paramString);
  }
  
  @Deprecated
  void b(Collection<w> paramCollection)
  {
    if ((paramCollection == null) || (paramCollection.isEmpty())) {
      z.W("Empty/Null collection passed to deleteHits.");
    }
    for (;;)
    {
      return;
      String[] arrayOfString = new String[paramCollection.size()];
      paramCollection = paramCollection.iterator();
      for (int i = 0; paramCollection.hasNext(); i++) {
        arrayOfString[i] = String.valueOf(((w)paramCollection.next()).eG());
      }
      b(arrayOfString);
    }
  }
  
  void b(String[] paramArrayOfString)
  {
    boolean bool = true;
    if ((paramArrayOfString == null) || (paramArrayOfString.length == 0)) {
      z.W("Empty hitIds passed to deleteHits.");
    }
    Object localObject;
    do
    {
      return;
      localObject = al("Error opening database for deleteHits.");
    } while (localObject == null);
    String str = String.format("HIT_ID in (%s)", new Object[] { TextUtils.join(",", Collections.nCopies(paramArrayOfString.length, "?")) });
    for (;;)
    {
      try
      {
        ((SQLiteDatabase)localObject).delete("hits2", str, paramArrayOfString);
        localObject = this.yl;
        if (eO() != 0) {
          break label124;
        }
        ((e)localObject).z(bool);
      }
      catch (SQLiteException localSQLiteException)
      {
        z.W("Error deleting hits " + TextUtils.join(",", paramArrayOfString));
      }
      break;
      label124:
      bool = false;
    }
  }
  
  public m dM()
  {
    return this.Ba;
  }
  
  public void dispatch()
  {
    boolean bool = true;
    z.V("Dispatch running...");
    if (!this.Ba.dX()) {}
    for (;;)
    {
      return;
      List localList = G(40);
      if (localList.isEmpty())
      {
        z.V("...nothing to dispatch");
        this.yl.z(true);
      }
      else
      {
        if (this.Bc == null) {
          this.Bc = new aa("_t=dispatch&_v=ma4.0.3", true);
        }
        if (eO() <= localList.size()) {}
        for (;;)
        {
          int i = this.Ba.a(localList, this.Bc, bool);
          z.V("sent " + i + " of " + localList.size() + " hits");
          b(localList.subList(0, Math.min(i, localList.size())));
          if ((i != localList.size()) || (eO() <= 0)) {
            break label207;
          }
          GoogleAnalytics.getInstance(this.mContext).dispatchLocalHits();
          break;
          bool = false;
        }
        label207:
        this.Bc = null;
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
    i = ((SQLiteDatabase)localObject).delete("hits2", "HIT_TIME < ?", new String[] { Long.toString(this.yD.currentTimeMillis() - 2592000000L) });
    Object localObject = this.yl;
    if (eO() == 0) {}
    for (;;)
    {
      ((e)localObject).z(bool);
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
        localObject5 = ((SQLiteDatabase)localObject5).rawQuery("SELECT COUNT(*) from hits2", null);
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
        z.W("Error getting numStoredHits");
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
  
  public void l(long paramLong)
  {
    boolean bool = true;
    Object localObject = al("Error opening database for clearHits");
    if (localObject != null)
    {
      if (paramLong != 0L) {
        break label54;
      }
      ((SQLiteDatabase)localObject).delete("hits2", null, null);
      localObject = this.yl;
      if (eO() != 0) {
        break label82;
      }
    }
    for (;;)
    {
      ((e)localObject).z(bool);
      return;
      label54:
      ((SQLiteDatabase)localObject).delete("hits2", "hit_app_id = ?", new String[] { Long.valueOf(paramLong).toString() });
      break;
      label82:
      bool = false;
    }
  }
  
  class a
    extends SQLiteOpenHelper
  {
    private boolean Bf;
    private long Bg = 0L;
    
    a(Context paramContext, String paramString)
    {
      super(paramString, null, 1);
    }
    
    private void a(SQLiteDatabase paramSQLiteDatabase)
    {
      int j = 0;
      Cursor localCursor = paramSQLiteDatabase.rawQuery("SELECT * FROM hits2 WHERE 0", null);
      HashSet localHashSet = new HashSet();
      try
      {
        String[] arrayOfString = localCursor.getColumnNames();
        for (i = 0; i < arrayOfString.length; i++) {
          localHashSet.add(arrayOfString[i]);
        }
        localCursor.close();
        if ((!localHashSet.remove("hit_id")) || (!localHashSet.remove("hit_url")) || (!localHashSet.remove("hit_string")) || (!localHashSet.remove("hit_time"))) {
          throw new SQLiteException("Database column missing");
        }
      }
      finally
      {
        localCursor.close();
      }
      int i = j;
      if (!localHashSet.remove("hit_app_id")) {
        i = 1;
      }
      if (!localHashSet.isEmpty()) {
        throw new SQLiteException("Database has extra columns");
      }
      if (i != 0) {
        paramSQLiteDatabase.execSQL("ALTER TABLE hits2 ADD COLUMN hit_app_id");
      }
    }
    
    /* Error */
    private boolean a(String paramString, SQLiteDatabase paramSQLiteDatabase)
    {
      // Byte code:
      //   0: aconst_null
      //   1: astore 5
      //   3: aload_2
      //   4: ldc 86
      //   6: iconst_1
      //   7: anewarray 88	java/lang/String
      //   10: dup
      //   11: iconst_0
      //   12: ldc 90
      //   14: aastore
      //   15: ldc 92
      //   17: iconst_1
      //   18: anewarray 88	java/lang/String
      //   21: dup
      //   22: iconst_0
      //   23: aload_1
      //   24: aastore
      //   25: aconst_null
      //   26: aconst_null
      //   27: aconst_null
      //   28: invokevirtual 96	android/database/sqlite/SQLiteDatabase:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
      //   31: astore_2
      //   32: aload_2
      //   33: invokeinterface 99 1 0
      //   38: istore 4
      //   40: iload 4
      //   42: istore_3
      //   43: aload_2
      //   44: ifnull +12 -> 56
      //   47: aload_2
      //   48: invokeinterface 52 1 0
      //   53: iload 4
      //   55: istore_3
      //   56: iload_3
      //   57: ireturn
      //   58: astore_2
      //   59: aconst_null
      //   60: astore_2
      //   61: new 101	java/lang/StringBuilder
      //   64: astore 5
      //   66: aload 5
      //   68: invokespecial 102	java/lang/StringBuilder:<init>	()V
      //   71: aload 5
      //   73: ldc 104
      //   75: invokevirtual 108	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   78: aload_1
      //   79: invokevirtual 108	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   82: invokevirtual 112	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   85: invokestatic 117	com/google/android/gms/analytics/z:W	(Ljava/lang/String;)V
      //   88: aload_2
      //   89: ifnull +9 -> 98
      //   92: aload_2
      //   93: invokeinterface 52 1 0
      //   98: iconst_0
      //   99: istore_3
      //   100: goto -44 -> 56
      //   103: astore_1
      //   104: aload 5
      //   106: astore_2
      //   107: aload_2
      //   108: ifnull +9 -> 117
      //   111: aload_2
      //   112: invokeinterface 52 1 0
      //   117: aload_1
      //   118: athrow
      //   119: astore_1
      //   120: goto -13 -> 107
      //   123: astore_1
      //   124: goto -17 -> 107
      //   127: astore 5
      //   129: goto -68 -> 61
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	132	0	this	a
      //   0	132	1	paramString	String
      //   0	132	2	paramSQLiteDatabase	SQLiteDatabase
      //   42	58	3	bool1	boolean
      //   38	16	4	bool2	boolean
      //   1	104	5	localStringBuilder	StringBuilder
      //   127	1	5	localSQLiteException	SQLiteException
      // Exception table:
      //   from	to	target	type
      //   3	32	58	android/database/sqlite/SQLiteException
      //   3	32	103	finally
      //   32	40	119	finally
      //   61	88	123	finally
      //   32	40	127	android/database/sqlite/SQLiteException
    }
    
    public SQLiteDatabase getWritableDatabase()
    {
      if ((this.Bf) && (this.Bg + 3600000L > ab.a(ab.this).currentTimeMillis())) {
        throw new SQLiteException("Database creation failed");
      }
      Object localObject1 = null;
      this.Bf = true;
      this.Bg = ab.a(ab.this).currentTimeMillis();
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
          ab.c(ab.this).getDatabasePath(ab.b(ab.this)).delete();
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
      o.ag(paramSQLiteDatabase.getPath());
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
          if (!a("hits2", paramSQLiteDatabase))
          {
            paramSQLiteDatabase.execSQL(ab.eP());
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


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\analytics\ab.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */