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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

class v
  implements DataLayer.c
{
  private static final String aoQ = String.format("CREATE TABLE IF NOT EXISTS %s ( '%s' INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, '%s' STRING NOT NULL, '%s' BLOB NOT NULL, '%s' INTEGER NOT NULL);", new Object[] { "datalayer", "ID", "key", "value", "expires" });
  private final Executor aoR;
  private a aoS;
  private int aoT;
  private final Context mContext;
  private ju yD;
  
  public v(Context paramContext)
  {
    this(paramContext, jw.hA(), "google_tagmanager.db", 2000, Executors.newSingleThreadExecutor());
  }
  
  v(Context paramContext, ju paramju, String paramString, int paramInt, Executor paramExecutor)
  {
    this.mContext = paramContext;
    this.yD = paramju;
    this.aoT = paramInt;
    this.aoR = paramExecutor;
    this.aoS = new a(this.mContext, paramString);
  }
  
  private SQLiteDatabase al(String paramString)
  {
    try
    {
      SQLiteDatabase localSQLiteDatabase = this.aoS.getWritableDatabase();
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
  
  /* Error */
  private void b(List<b> paramList, long paramLong)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 78	com/google/android/gms/tagmanager/v:yD	Lcom/google/android/gms/internal/ju;
    //   6: invokeinterface 121 1 0
    //   11: lstore 4
    //   13: aload_0
    //   14: lload 4
    //   16: invokespecial 125	com/google/android/gms/tagmanager/v:x	(J)V
    //   19: aload_0
    //   20: aload_1
    //   21: invokeinterface 131 1 0
    //   26: invokespecial 135	com/google/android/gms/tagmanager/v:fg	(I)V
    //   29: aload_0
    //   30: aload_1
    //   31: lload 4
    //   33: lload_2
    //   34: ladd
    //   35: invokespecial 138	com/google/android/gms/tagmanager/v:c	(Ljava/util/List;J)V
    //   38: aload_0
    //   39: invokespecial 141	com/google/android/gms/tagmanager/v:ol	()V
    //   42: aload_0
    //   43: monitorexit
    //   44: return
    //   45: astore_1
    //   46: aload_0
    //   47: invokespecial 141	com/google/android/gms/tagmanager/v:ol	()V
    //   50: aload_1
    //   51: athrow
    //   52: astore_1
    //   53: aload_0
    //   54: monitorexit
    //   55: aload_1
    //   56: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	57	0	this	v
    //   0	57	1	paramList	List<b>
    //   0	57	2	paramLong	long
    //   11	21	4	l	long
    // Exception table:
    //   from	to	target	type
    //   2	38	45	finally
    //   38	42	52	finally
    //   46	52	52	finally
  }
  
  private void c(List<b> paramList, long paramLong)
  {
    SQLiteDatabase localSQLiteDatabase = al("Error opening database for writeEntryToDatabase.");
    if (localSQLiteDatabase == null) {}
    for (;;)
    {
      return;
      paramList = paramList.iterator();
      while (paramList.hasNext())
      {
        b localb = (b)paramList.next();
        ContentValues localContentValues = new ContentValues();
        localContentValues.put("expires", Long.valueOf(paramLong));
        localContentValues.put("key", localb.JO);
        localContentValues.put("value", localb.aoZ);
        localSQLiteDatabase.insert("datalayer", null, localContentValues);
      }
    }
  }
  
  private void cy(String paramString)
  {
    Object localObject = al("Error opening database for clearKeysWithPrefix.");
    if (localObject == null) {}
    for (;;)
    {
      return;
      try
      {
        localStringBuilder = new java/lang/StringBuilder;
        localStringBuilder.<init>();
        int i = ((SQLiteDatabase)localObject).delete("datalayer", "key = ? OR key LIKE ?", new String[] { paramString, paramString + ".%" });
        localObject = new java/lang/StringBuilder;
        ((StringBuilder)localObject).<init>();
        bh.V("Cleared " + i + " items");
        ol();
      }
      catch (SQLiteException localSQLiteException)
      {
        StringBuilder localStringBuilder = new java/lang/StringBuilder;
        localStringBuilder.<init>();
        bh.W("Error deleting entries with key prefix: " + paramString + " (" + localSQLiteException + ").");
        ol();
      }
      finally
      {
        ol();
      }
    }
  }
  
  private void fg(int paramInt)
  {
    paramInt = ok() - this.aoT + paramInt;
    if (paramInt > 0)
    {
      List localList = fh(paramInt);
      bh.U("DataLayer store full, deleting " + localList.size() + " entries to make room.");
      i((String[])localList.toArray(new String[0]));
    }
  }
  
  /* Error */
  private List<String> fh(int paramInt)
  {
    // Byte code:
    //   0: new 259	java/util/ArrayList
    //   3: dup
    //   4: invokespecial 260	java/util/ArrayList:<init>	()V
    //   7: astore 6
    //   9: iload_1
    //   10: ifgt +12 -> 22
    //   13: ldc_w 262
    //   16: invokestatic 114	com/google/android/gms/tagmanager/bh:W	(Ljava/lang/String;)V
    //   19: aload 6
    //   21: areturn
    //   22: aload_0
    //   23: ldc_w 264
    //   26: invokespecial 147	com/google/android/gms/tagmanager/v:al	(Ljava/lang/String;)Landroid/database/sqlite/SQLiteDatabase;
    //   29: astore_3
    //   30: aload_3
    //   31: ifnonnull +6 -> 37
    //   34: goto -15 -> 19
    //   37: ldc_w 266
    //   40: iconst_1
    //   41: anewarray 4	java/lang/Object
    //   44: dup
    //   45: iconst_0
    //   46: ldc 38
    //   48: aastore
    //   49: invokestatic 50	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   52: astore 4
    //   54: iload_1
    //   55: invokestatic 271	java/lang/Integer:toString	(I)Ljava/lang/String;
    //   58: astore 5
    //   60: aload_3
    //   61: ldc 36
    //   63: iconst_1
    //   64: anewarray 46	java/lang/String
    //   67: dup
    //   68: iconst_0
    //   69: ldc 38
    //   71: aastore
    //   72: aconst_null
    //   73: aconst_null
    //   74: aconst_null
    //   75: aconst_null
    //   76: aload 4
    //   78: aload 5
    //   80: invokevirtual 275	android/database/sqlite/SQLiteDatabase:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   83: astore 4
    //   85: aload 4
    //   87: astore_3
    //   88: aload 4
    //   90: invokeinterface 280 1 0
    //   95: ifeq +40 -> 135
    //   98: aload 4
    //   100: astore_3
    //   101: aload 6
    //   103: aload 4
    //   105: iconst_0
    //   106: invokeinterface 284 2 0
    //   111: invokestatic 287	java/lang/String:valueOf	(J)Ljava/lang/String;
    //   114: invokeinterface 291 2 0
    //   119: pop
    //   120: aload 4
    //   122: astore_3
    //   123: aload 4
    //   125: invokeinterface 294 1 0
    //   130: istore_2
    //   131: iload_2
    //   132: ifne -34 -> 98
    //   135: aload 4
    //   137: ifnull +10 -> 147
    //   140: aload 4
    //   142: invokeinterface 297 1 0
    //   147: goto -128 -> 19
    //   150: astore 5
    //   152: aconst_null
    //   153: astore 4
    //   155: aload 4
    //   157: astore_3
    //   158: new 197	java/lang/StringBuilder
    //   161: astore 7
    //   163: aload 4
    //   165: astore_3
    //   166: aload 7
    //   168: invokespecial 198	java/lang/StringBuilder:<init>	()V
    //   171: aload 4
    //   173: astore_3
    //   174: aload 7
    //   176: ldc_w 299
    //   179: invokevirtual 204	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   182: aload 5
    //   184: invokevirtual 302	android/database/sqlite/SQLiteException:getMessage	()Ljava/lang/String;
    //   187: invokevirtual 204	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   190: invokevirtual 210	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   193: invokestatic 114	com/google/android/gms/tagmanager/bh:W	(Ljava/lang/String;)V
    //   196: aload 4
    //   198: ifnull -51 -> 147
    //   201: aload 4
    //   203: invokeinterface 297 1 0
    //   208: goto -61 -> 147
    //   211: astore_3
    //   212: aconst_null
    //   213: astore 4
    //   215: aload_3
    //   216: astore 5
    //   218: aload 4
    //   220: ifnull +10 -> 230
    //   223: aload 4
    //   225: invokeinterface 297 1 0
    //   230: aload 5
    //   232: athrow
    //   233: astore 5
    //   235: aload_3
    //   236: astore 4
    //   238: goto -20 -> 218
    //   241: astore 5
    //   243: goto -88 -> 155
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	246	0	this	v
    //   0	246	1	paramInt	int
    //   130	2	2	bool	boolean
    //   29	145	3	localObject1	Object
    //   211	25	3	localObject2	Object
    //   52	185	4	localObject3	Object
    //   58	21	5	str	String
    //   150	33	5	localSQLiteException1	SQLiteException
    //   216	15	5	localObject4	Object
    //   233	1	5	localObject5	Object
    //   241	1	5	localSQLiteException2	SQLiteException
    //   7	95	6	localArrayList	ArrayList
    //   161	14	7	localStringBuilder	StringBuilder
    // Exception table:
    //   from	to	target	type
    //   37	85	150	android/database/sqlite/SQLiteException
    //   37	85	211	finally
    //   88	98	233	finally
    //   101	120	233	finally
    //   123	131	233	finally
    //   158	163	233	finally
    //   166	171	233	finally
    //   174	196	233	finally
    //   88	98	241	android/database/sqlite/SQLiteException
    //   101	120	241	android/database/sqlite/SQLiteException
    //   123	131	241	android/database/sqlite/SQLiteException
  }
  
  private List<DataLayer.a> h(List<b> paramList)
  {
    ArrayList localArrayList = new ArrayList();
    paramList = paramList.iterator();
    while (paramList.hasNext())
    {
      b localb = (b)paramList.next();
      localArrayList.add(new DataLayer.a(localb.JO, j(localb.aoZ)));
    }
    return localArrayList;
  }
  
  private List<b> i(List<DataLayer.a> paramList)
  {
    ArrayList localArrayList = new ArrayList();
    paramList = paramList.iterator();
    while (paramList.hasNext())
    {
      DataLayer.a locala = (DataLayer.a)paramList.next();
      localArrayList.add(new b(locala.JO, m(locala.wq)));
    }
    return localArrayList;
  }
  
  private void i(String[] paramArrayOfString)
  {
    if ((paramArrayOfString == null) || (paramArrayOfString.length == 0)) {}
    for (;;)
    {
      return;
      SQLiteDatabase localSQLiteDatabase = al("Error opening database for deleteEntries.");
      if (localSQLiteDatabase != null)
      {
        String str = String.format("%s in (%s)", new Object[] { "ID", TextUtils.join(",", Collections.nCopies(paramArrayOfString.length, "?")) });
        try
        {
          localSQLiteDatabase.delete("datalayer", str, paramArrayOfString);
        }
        catch (SQLiteException localSQLiteException)
        {
          bh.W("Error deleting entries " + Arrays.toString(paramArrayOfString));
        }
      }
    }
  }
  
  /* Error */
  private Object j(byte[] paramArrayOfByte)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_2
    //   2: new 360	java/io/ByteArrayInputStream
    //   5: dup
    //   6: aload_1
    //   7: invokespecial 363	java/io/ByteArrayInputStream:<init>	([B)V
    //   10: astore 4
    //   12: new 365	java/io/ObjectInputStream
    //   15: astore_1
    //   16: aload_1
    //   17: aload 4
    //   19: invokespecial 368	java/io/ObjectInputStream:<init>	(Ljava/io/InputStream;)V
    //   22: aload_1
    //   23: invokevirtual 371	java/io/ObjectInputStream:readObject	()Ljava/lang/Object;
    //   26: astore_3
    //   27: aload_3
    //   28: astore_2
    //   29: aload_1
    //   30: ifnull +7 -> 37
    //   33: aload_1
    //   34: invokevirtual 372	java/io/ObjectInputStream:close	()V
    //   37: aload 4
    //   39: invokevirtual 373	java/io/ByteArrayInputStream:close	()V
    //   42: aload_2
    //   43: areturn
    //   44: astore_1
    //   45: aconst_null
    //   46: astore_1
    //   47: aload_1
    //   48: ifnull +7 -> 55
    //   51: aload_1
    //   52: invokevirtual 372	java/io/ObjectInputStream:close	()V
    //   55: aload 4
    //   57: invokevirtual 373	java/io/ByteArrayInputStream:close	()V
    //   60: goto -18 -> 42
    //   63: astore_1
    //   64: goto -22 -> 42
    //   67: astore_1
    //   68: aconst_null
    //   69: astore_1
    //   70: aload_1
    //   71: ifnull +7 -> 78
    //   74: aload_1
    //   75: invokevirtual 372	java/io/ObjectInputStream:close	()V
    //   78: aload 4
    //   80: invokevirtual 373	java/io/ByteArrayInputStream:close	()V
    //   83: goto -41 -> 42
    //   86: astore_1
    //   87: goto -45 -> 42
    //   90: astore_2
    //   91: aconst_null
    //   92: astore_1
    //   93: aload_1
    //   94: ifnull +7 -> 101
    //   97: aload_1
    //   98: invokevirtual 372	java/io/ObjectInputStream:close	()V
    //   101: aload 4
    //   103: invokevirtual 373	java/io/ByteArrayInputStream:close	()V
    //   106: aload_2
    //   107: athrow
    //   108: astore_1
    //   109: goto -3 -> 106
    //   112: astore_2
    //   113: goto -20 -> 93
    //   116: astore_3
    //   117: goto -47 -> 70
    //   120: astore_3
    //   121: goto -74 -> 47
    //   124: astore_1
    //   125: goto -83 -> 42
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	128	0	this	v
    //   0	128	1	paramArrayOfByte	byte[]
    //   1	42	2	localObject1	Object
    //   90	17	2	localObject2	Object
    //   112	1	2	localObject3	Object
    //   26	2	3	localObject4	Object
    //   116	1	3	localClassNotFoundException	ClassNotFoundException
    //   120	1	3	localIOException	java.io.IOException
    //   10	92	4	localByteArrayInputStream	java.io.ByteArrayInputStream
    // Exception table:
    //   from	to	target	type
    //   12	22	44	java/io/IOException
    //   51	55	63	java/io/IOException
    //   55	60	63	java/io/IOException
    //   12	22	67	java/lang/ClassNotFoundException
    //   74	78	86	java/io/IOException
    //   78	83	86	java/io/IOException
    //   12	22	90	finally
    //   97	101	108	java/io/IOException
    //   101	106	108	java/io/IOException
    //   22	27	112	finally
    //   22	27	116	java/lang/ClassNotFoundException
    //   22	27	120	java/io/IOException
    //   33	37	124	java/io/IOException
    //   37	42	124	java/io/IOException
  }
  
  /* Error */
  private byte[] m(Object paramObject)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_3
    //   2: new 375	java/io/ByteArrayOutputStream
    //   5: dup
    //   6: invokespecial 376	java/io/ByteArrayOutputStream:<init>	()V
    //   9: astore 4
    //   11: new 378	java/io/ObjectOutputStream
    //   14: astore_2
    //   15: aload_2
    //   16: aload 4
    //   18: invokespecial 381	java/io/ObjectOutputStream:<init>	(Ljava/io/OutputStream;)V
    //   21: aload_2
    //   22: aload_1
    //   23: invokevirtual 385	java/io/ObjectOutputStream:writeObject	(Ljava/lang/Object;)V
    //   26: aload 4
    //   28: invokevirtual 389	java/io/ByteArrayOutputStream:toByteArray	()[B
    //   31: astore_1
    //   32: aload_2
    //   33: ifnull +7 -> 40
    //   36: aload_2
    //   37: invokevirtual 390	java/io/ObjectOutputStream:close	()V
    //   40: aload 4
    //   42: invokevirtual 391	java/io/ByteArrayOutputStream:close	()V
    //   45: aload_1
    //   46: areturn
    //   47: astore_1
    //   48: aconst_null
    //   49: astore_2
    //   50: aload_2
    //   51: ifnull +7 -> 58
    //   54: aload_2
    //   55: invokevirtual 390	java/io/ObjectOutputStream:close	()V
    //   58: aload 4
    //   60: invokevirtual 391	java/io/ByteArrayOutputStream:close	()V
    //   63: aload_3
    //   64: astore_1
    //   65: goto -20 -> 45
    //   68: astore_1
    //   69: aload_3
    //   70: astore_1
    //   71: goto -26 -> 45
    //   74: astore_1
    //   75: aconst_null
    //   76: astore_2
    //   77: aload_2
    //   78: ifnull +7 -> 85
    //   81: aload_2
    //   82: invokevirtual 390	java/io/ObjectOutputStream:close	()V
    //   85: aload 4
    //   87: invokevirtual 391	java/io/ByteArrayOutputStream:close	()V
    //   90: aload_1
    //   91: athrow
    //   92: astore_2
    //   93: goto -3 -> 90
    //   96: astore_1
    //   97: goto -20 -> 77
    //   100: astore_1
    //   101: goto -51 -> 50
    //   104: astore_2
    //   105: goto -60 -> 45
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	108	0	this	v
    //   0	108	1	paramObject	Object
    //   14	68	2	localObjectOutputStream	java.io.ObjectOutputStream
    //   92	1	2	localIOException1	java.io.IOException
    //   104	1	2	localIOException2	java.io.IOException
    //   1	69	3	localObject	Object
    //   9	77	4	localByteArrayOutputStream	java.io.ByteArrayOutputStream
    // Exception table:
    //   from	to	target	type
    //   11	21	47	java/io/IOException
    //   54	58	68	java/io/IOException
    //   58	63	68	java/io/IOException
    //   11	21	74	finally
    //   81	85	92	java/io/IOException
    //   85	90	92	java/io/IOException
    //   21	32	96	finally
    //   21	32	100	java/io/IOException
    //   36	40	104	java/io/IOException
    //   40	45	104	java/io/IOException
  }
  
  private List<DataLayer.a> oi()
  {
    try
    {
      x(this.yD.currentTimeMillis());
      List localList = h(oj());
      return localList;
    }
    finally
    {
      ol();
    }
  }
  
  /* Error */
  private List<b> oj()
  {
    // Byte code:
    //   0: aload_0
    //   1: ldc_w 399
    //   4: invokespecial 147	com/google/android/gms/tagmanager/v:al	(Ljava/lang/String;)Landroid/database/sqlite/SQLiteDatabase;
    //   7: astore_1
    //   8: new 259	java/util/ArrayList
    //   11: dup
    //   12: invokespecial 260	java/util/ArrayList:<init>	()V
    //   15: astore_2
    //   16: aload_1
    //   17: ifnonnull +5 -> 22
    //   20: aload_2
    //   21: areturn
    //   22: aload_1
    //   23: ldc 36
    //   25: iconst_2
    //   26: anewarray 46	java/lang/String
    //   29: dup
    //   30: iconst_0
    //   31: ldc 40
    //   33: aastore
    //   34: dup
    //   35: iconst_1
    //   36: ldc 42
    //   38: aastore
    //   39: aconst_null
    //   40: aconst_null
    //   41: aconst_null
    //   42: aconst_null
    //   43: ldc 38
    //   45: aconst_null
    //   46: invokevirtual 275	android/database/sqlite/SQLiteDatabase:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   49: astore_1
    //   50: aload_1
    //   51: invokeinterface 294 1 0
    //   56: ifeq +45 -> 101
    //   59: new 17	com/google/android/gms/tagmanager/v$b
    //   62: astore_3
    //   63: aload_3
    //   64: aload_1
    //   65: iconst_0
    //   66: invokeinterface 402 2 0
    //   71: aload_1
    //   72: iconst_1
    //   73: invokeinterface 406 2 0
    //   78: invokespecial 326	com/google/android/gms/tagmanager/v$b:<init>	(Ljava/lang/String;[B)V
    //   81: aload_2
    //   82: aload_3
    //   83: invokeinterface 291 2 0
    //   88: pop
    //   89: goto -39 -> 50
    //   92: astore_2
    //   93: aload_1
    //   94: invokeinterface 297 1 0
    //   99: aload_2
    //   100: athrow
    //   101: aload_1
    //   102: invokeinterface 297 1 0
    //   107: goto -87 -> 20
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	110	0	this	v
    //   7	95	1	localObject1	Object
    //   15	67	2	localArrayList	ArrayList
    //   92	8	2	localObject2	Object
    //   62	21	3	localb	b
    // Exception table:
    //   from	to	target	type
    //   50	89	92	finally
  }
  
  private int ok()
  {
    Object localObject3 = null;
    Object localObject1 = null;
    int i = 0;
    int k = 0;
    Object localObject5 = al("Error opening database for getNumStoredEntries.");
    int j;
    if (localObject5 == null) {
      j = k;
    }
    for (;;)
    {
      return j;
      try
      {
        localObject5 = ((SQLiteDatabase)localObject5).rawQuery("SELECT COUNT(*) from datalayer", null);
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
        bh.W("Error getting numStoredEntries");
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
  
  private void ol()
  {
    try
    {
      this.aoS.close();
      return;
    }
    catch (SQLiteException localSQLiteException)
    {
      for (;;) {}
    }
  }
  
  private void x(long paramLong)
  {
    Object localObject = al("Error opening database for deleteOlderThan.");
    if (localObject == null) {}
    for (;;)
    {
      return;
      try
      {
        int i = ((SQLiteDatabase)localObject).delete("datalayer", "expires <= ?", new String[] { Long.toString(paramLong) });
        localObject = new java/lang/StringBuilder;
        ((StringBuilder)localObject).<init>();
        bh.V("Deleted " + i + " expired items");
      }
      catch (SQLiteException localSQLiteException)
      {
        bh.W("Error deleting old entries.");
      }
    }
  }
  
  public void a(final DataLayer.c.a parama)
  {
    this.aoR.execute(new Runnable()
    {
      public void run()
      {
        parama.g(v.a(v.this));
      }
    });
  }
  
  public void a(final List<DataLayer.a> paramList, final long paramLong)
  {
    paramList = i(paramList);
    this.aoR.execute(new Runnable()
    {
      public void run()
      {
        v.a(v.this, paramList, paramLong);
      }
    });
  }
  
  public void cx(final String paramString)
  {
    this.aoR.execute(new Runnable()
    {
      public void run()
      {
        v.a(v.this, paramString);
      }
    });
  }
  
  class a
    extends SQLiteOpenHelper
  {
    a(Context paramContext, String paramString)
    {
      super(paramString, null, 1);
    }
    
    private void a(SQLiteDatabase paramSQLiteDatabase)
    {
      paramSQLiteDatabase = paramSQLiteDatabase.rawQuery("SELECT * FROM datalayer WHERE 0", null);
      HashSet localHashSet = new HashSet();
      try
      {
        String[] arrayOfString = paramSQLiteDatabase.getColumnNames();
        for (int i = 0; i < arrayOfString.length; i++) {
          localHashSet.add(arrayOfString[i]);
        }
        paramSQLiteDatabase.close();
        if ((!localHashSet.remove("key")) || (!localHashSet.remove("value")) || (!localHashSet.remove("ID")) || (!localHashSet.remove("expires"))) {
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
      //   4: ldc 73
      //   6: iconst_1
      //   7: anewarray 75	java/lang/String
      //   10: dup
      //   11: iconst_0
      //   12: ldc 77
      //   14: aastore
      //   15: ldc 79
      //   17: iconst_1
      //   18: anewarray 75	java/lang/String
      //   21: dup
      //   22: iconst_0
      //   23: aload_1
      //   24: aastore
      //   25: aconst_null
      //   26: aconst_null
      //   27: aconst_null
      //   28: invokevirtual 83	android/database/sqlite/SQLiteDatabase:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
      //   31: astore_2
      //   32: aload_2
      //   33: invokeinterface 86 1 0
      //   38: istore 4
      //   40: iload 4
      //   42: istore_3
      //   43: aload_2
      //   44: ifnull +12 -> 56
      //   47: aload_2
      //   48: invokeinterface 46 1 0
      //   53: iload 4
      //   55: istore_3
      //   56: iload_3
      //   57: ireturn
      //   58: astore_2
      //   59: aconst_null
      //   60: astore_2
      //   61: new 88	java/lang/StringBuilder
      //   64: astore 5
      //   66: aload 5
      //   68: invokespecial 89	java/lang/StringBuilder:<init>	()V
      //   71: aload 5
      //   73: ldc 91
      //   75: invokevirtual 95	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   78: aload_1
      //   79: invokevirtual 95	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   82: invokevirtual 99	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   85: invokestatic 104	com/google/android/gms/tagmanager/bh:W	(Ljava/lang/String;)V
      //   88: aload_2
      //   89: ifnull +9 -> 98
      //   92: aload_2
      //   93: invokeinterface 46 1 0
      //   98: iconst_0
      //   99: istore_3
      //   100: goto -44 -> 56
      //   103: astore_1
      //   104: aload 5
      //   106: astore_2
      //   107: aload_2
      //   108: ifnull +9 -> 117
      //   111: aload_2
      //   112: invokeinterface 46 1 0
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
      Object localObject1 = null;
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
          v.b(v.this).getDatabasePath("google_tagmanager.db").delete();
        }
      }
      localObject2 = localObject1;
      if (localObject1 == null) {
        localObject2 = super.getWritableDatabase();
      }
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
          if (!a("datalayer", paramSQLiteDatabase))
          {
            paramSQLiteDatabase.execSQL(v.om());
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
  
  private static class b
  {
    final String JO;
    final byte[] aoZ;
    
    b(String paramString, byte[] paramArrayOfByte)
    {
      this.JO = paramString;
      this.aoZ = paramArrayOfByte;
    }
    
    public String toString()
    {
      return "KeyAndSerialized: key = " + this.JO + " serialized hash = " + Arrays.hashCode(this.aoZ);
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\tagmanager\v.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */