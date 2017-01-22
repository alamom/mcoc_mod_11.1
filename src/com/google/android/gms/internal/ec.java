package com.google.android.gms.internal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.SystemClock;

@ez
public class ec
{
  private static final Object mw = new Object();
  private static final String sG = String.format("CREATE TABLE IF NOT EXISTS %s ( %s INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, %s TEXT NOT NULL, %s TEXT NOT NULL, %s INTEGER)", new Object[] { "InAppPurchase", "purchase_id", "product_id", "developer_payload", "record_time" });
  private static ec sI;
  private final a sH;
  
  private ec(Context paramContext)
  {
    this.sH = new a(paramContext, "google_inapp_purchase.db");
  }
  
  public static ec j(Context paramContext)
  {
    synchronized (mw)
    {
      if (sI == null)
      {
        ec localec = new com/google/android/gms/internal/ec;
        localec.<init>(paramContext);
        sI = localec;
      }
      paramContext = sI;
      return paramContext;
    }
  }
  
  public ea a(Cursor paramCursor)
  {
    if (paramCursor == null) {}
    for (paramCursor = null;; paramCursor = new ea(paramCursor.getLong(0), paramCursor.getString(1), paramCursor.getString(2))) {
      return paramCursor;
    }
  }
  
  public void a(ea paramea)
  {
    if (paramea == null) {}
    for (;;)
    {
      return;
      SQLiteDatabase localSQLiteDatabase;
      synchronized (mw)
      {
        localSQLiteDatabase = getWritableDatabase();
        if (localSQLiteDatabase != null) {}
      }
      localSQLiteDatabase.delete("InAppPurchase", String.format("%s = %d", new Object[] { "purchase_id", Long.valueOf(paramea.sA) }), null);
    }
  }
  
  public void b(ea paramea)
  {
    if (paramea == null) {}
    for (;;)
    {
      return;
      SQLiteDatabase localSQLiteDatabase;
      synchronized (mw)
      {
        localSQLiteDatabase = getWritableDatabase();
        if (localSQLiteDatabase != null) {}
      }
      ContentValues localContentValues = new android/content/ContentValues;
      localContentValues.<init>();
      localContentValues.put("product_id", paramea.sC);
      localContentValues.put("developer_payload", paramea.sB);
      localContentValues.put("record_time", Long.valueOf(SystemClock.elapsedRealtime()));
      paramea.sA = localSQLiteDatabase.insert("InAppPurchase", null, localContentValues);
      if (getRecordCount() > 20000L) {
        cr();
      }
    }
  }
  
  /* Error */
  public void cr()
  {
    // Byte code:
    //   0: getstatic 43	com/google/android/gms/internal/ec:mw	Ljava/lang/Object;
    //   3: astore 4
    //   5: aload 4
    //   7: monitorenter
    //   8: aload_0
    //   9: invokevirtual 81	com/google/android/gms/internal/ec:getWritableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   12: astore_1
    //   13: aload_1
    //   14: ifnonnull +7 -> 21
    //   17: aload 4
    //   19: monitorexit
    //   20: return
    //   21: aload_1
    //   22: ldc 22
    //   24: aconst_null
    //   25: aconst_null
    //   26: aconst_null
    //   27: aconst_null
    //   28: aconst_null
    //   29: ldc -117
    //   31: ldc -115
    //   33: invokevirtual 145	android/database/sqlite/SQLiteDatabase:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   36: astore_2
    //   37: aload_2
    //   38: ifnull +25 -> 63
    //   41: aload_2
    //   42: astore_1
    //   43: aload_2
    //   44: invokeinterface 149 1 0
    //   49: ifeq +14 -> 63
    //   52: aload_2
    //   53: astore_1
    //   54: aload_0
    //   55: aload_0
    //   56: aload_2
    //   57: invokevirtual 151	com/google/android/gms/internal/ec:a	(Landroid/database/Cursor;)Lcom/google/android/gms/internal/ea;
    //   60: invokevirtual 153	com/google/android/gms/internal/ec:a	(Lcom/google/android/gms/internal/ea;)V
    //   63: aload_2
    //   64: ifnull +9 -> 73
    //   67: aload_2
    //   68: invokeinterface 156 1 0
    //   73: aload 4
    //   75: monitorexit
    //   76: goto -56 -> 20
    //   79: astore_1
    //   80: aload 4
    //   82: monitorexit
    //   83: aload_1
    //   84: athrow
    //   85: astore_3
    //   86: aconst_null
    //   87: astore_2
    //   88: aload_2
    //   89: astore_1
    //   90: new 158	java/lang/StringBuilder
    //   93: astore 5
    //   95: aload_2
    //   96: astore_1
    //   97: aload 5
    //   99: invokespecial 159	java/lang/StringBuilder:<init>	()V
    //   102: aload_2
    //   103: astore_1
    //   104: aload 5
    //   106: ldc -95
    //   108: invokevirtual 165	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   111: aload_3
    //   112: invokevirtual 168	android/database/sqlite/SQLiteException:getMessage	()Ljava/lang/String;
    //   115: invokevirtual 165	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   118: invokevirtual 171	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   121: invokestatic 177	com/google/android/gms/internal/gs:W	(Ljava/lang/String;)V
    //   124: aload_2
    //   125: ifnull -52 -> 73
    //   128: aload_2
    //   129: invokeinterface 156 1 0
    //   134: goto -61 -> 73
    //   137: astore_2
    //   138: aconst_null
    //   139: astore_1
    //   140: aload_1
    //   141: ifnull +9 -> 150
    //   144: aload_1
    //   145: invokeinterface 156 1 0
    //   150: aload_2
    //   151: athrow
    //   152: astore_2
    //   153: goto -13 -> 140
    //   156: astore_3
    //   157: goto -69 -> 88
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	160	0	this	ec
    //   12	42	1	localObject1	Object
    //   79	5	1	localObject2	Object
    //   89	56	1	localObject3	Object
    //   36	93	2	localCursor	Cursor
    //   137	14	2	localObject4	Object
    //   152	1	2	localObject5	Object
    //   85	27	3	localSQLiteException1	SQLiteException
    //   156	1	3	localSQLiteException2	SQLiteException
    //   3	78	4	localObject6	Object
    //   93	12	5	localStringBuilder	StringBuilder
    // Exception table:
    //   from	to	target	type
    //   8	13	79	finally
    //   17	20	79	finally
    //   67	73	79	finally
    //   73	76	79	finally
    //   80	83	79	finally
    //   128	134	79	finally
    //   144	150	79	finally
    //   150	152	79	finally
    //   21	37	85	android/database/sqlite/SQLiteException
    //   21	37	137	finally
    //   43	52	152	finally
    //   54	63	152	finally
    //   90	95	152	finally
    //   97	102	152	finally
    //   104	124	152	finally
    //   43	52	156	android/database/sqlite/SQLiteException
    //   54	63	156	android/database/sqlite/SQLiteException
  }
  
  /* Error */
  public java.util.List<ea> d(long paramLong)
  {
    // Byte code:
    //   0: getstatic 43	com/google/android/gms/internal/ec:mw	Ljava/lang/Object;
    //   3: astore 7
    //   5: aload 7
    //   7: monitorenter
    //   8: new 181	java/util/LinkedList
    //   11: astore 8
    //   13: aload 8
    //   15: invokespecial 182	java/util/LinkedList:<init>	()V
    //   18: lload_1
    //   19: lconst_0
    //   20: lcmp
    //   21: ifgt +9 -> 30
    //   24: aload 7
    //   26: monitorexit
    //   27: aload 8
    //   29: areturn
    //   30: aload_0
    //   31: invokevirtual 81	com/google/android/gms/internal/ec:getWritableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   34: astore 4
    //   36: aload 4
    //   38: ifnonnull +9 -> 47
    //   41: aload 7
    //   43: monitorexit
    //   44: goto -17 -> 27
    //   47: aload 4
    //   49: ldc 22
    //   51: aconst_null
    //   52: aconst_null
    //   53: aconst_null
    //   54: aconst_null
    //   55: aconst_null
    //   56: ldc -117
    //   58: lload_1
    //   59: invokestatic 185	java/lang/String:valueOf	(J)Ljava/lang/String;
    //   62: invokevirtual 145	android/database/sqlite/SQLiteDatabase:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   65: astore 5
    //   67: aload 5
    //   69: astore 4
    //   71: aload 5
    //   73: invokeinterface 149 1 0
    //   78: ifeq +37 -> 115
    //   81: aload 5
    //   83: astore 4
    //   85: aload 8
    //   87: aload_0
    //   88: aload 5
    //   90: invokevirtual 151	com/google/android/gms/internal/ec:a	(Landroid/database/Cursor;)Lcom/google/android/gms/internal/ea;
    //   93: invokeinterface 191 2 0
    //   98: pop
    //   99: aload 5
    //   101: astore 4
    //   103: aload 5
    //   105: invokeinterface 194 1 0
    //   110: istore_3
    //   111: iload_3
    //   112: ifne -31 -> 81
    //   115: aload 5
    //   117: ifnull +10 -> 127
    //   120: aload 5
    //   122: invokeinterface 156 1 0
    //   127: aload 7
    //   129: monitorexit
    //   130: goto -103 -> 27
    //   133: astore 6
    //   135: aconst_null
    //   136: astore 5
    //   138: aload 5
    //   140: astore 4
    //   142: new 158	java/lang/StringBuilder
    //   145: astore 9
    //   147: aload 5
    //   149: astore 4
    //   151: aload 9
    //   153: invokespecial 159	java/lang/StringBuilder:<init>	()V
    //   156: aload 5
    //   158: astore 4
    //   160: aload 9
    //   162: ldc -60
    //   164: invokevirtual 165	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   167: aload 6
    //   169: invokevirtual 168	android/database/sqlite/SQLiteException:getMessage	()Ljava/lang/String;
    //   172: invokevirtual 165	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   175: invokevirtual 171	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   178: invokestatic 177	com/google/android/gms/internal/gs:W	(Ljava/lang/String;)V
    //   181: aload 5
    //   183: ifnull -56 -> 127
    //   186: aload 5
    //   188: invokeinterface 156 1 0
    //   193: goto -66 -> 127
    //   196: astore 4
    //   198: aload 7
    //   200: monitorexit
    //   201: aload 4
    //   203: athrow
    //   204: astore 5
    //   206: aconst_null
    //   207: astore 4
    //   209: aload 4
    //   211: ifnull +10 -> 221
    //   214: aload 4
    //   216: invokeinterface 156 1 0
    //   221: aload 5
    //   223: athrow
    //   224: astore 5
    //   226: goto -17 -> 209
    //   229: astore 6
    //   231: goto -93 -> 138
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	234	0	this	ec
    //   0	234	1	paramLong	long
    //   110	2	3	bool	boolean
    //   34	125	4	localObject1	Object
    //   196	6	4	localObject2	Object
    //   207	8	4	localObject3	Object
    //   65	122	5	localCursor	Cursor
    //   204	18	5	localObject4	Object
    //   224	1	5	localObject5	Object
    //   133	35	6	localSQLiteException1	SQLiteException
    //   229	1	6	localSQLiteException2	SQLiteException
    //   3	196	7	localObject6	Object
    //   11	75	8	localLinkedList	java.util.LinkedList
    //   145	16	9	localStringBuilder	StringBuilder
    // Exception table:
    //   from	to	target	type
    //   47	67	133	android/database/sqlite/SQLiteException
    //   8	18	196	finally
    //   24	27	196	finally
    //   30	36	196	finally
    //   41	44	196	finally
    //   120	127	196	finally
    //   127	130	196	finally
    //   186	193	196	finally
    //   198	201	196	finally
    //   214	221	196	finally
    //   221	224	196	finally
    //   47	67	204	finally
    //   71	81	224	finally
    //   85	99	224	finally
    //   103	111	224	finally
    //   142	147	224	finally
    //   151	156	224	finally
    //   160	181	224	finally
    //   71	81	229	android/database/sqlite/SQLiteException
    //   85	99	229	android/database/sqlite/SQLiteException
    //   103	111	229	android/database/sqlite/SQLiteException
  }
  
  public int getRecordCount()
  {
    localObject4 = null;
    Object localObject1 = null;
    int i = 0;
    for (;;)
    {
      Object localObject5;
      synchronized (mw)
      {
        localObject5 = getWritableDatabase();
        if (localObject5 == null) {
          return i;
        }
      }
      try
      {
        localObject5 = ((SQLiteDatabase)localObject5).rawQuery("select count(*) from InAppPurchase", null);
        localObject1 = localObject5;
        localObject4 = localObject5;
        if (((Cursor)localObject5).moveToFirst())
        {
          localObject1 = localObject5;
          localObject4 = localObject5;
          int j = ((Cursor)localObject5).getInt(0);
          i = j;
          if (localObject5 != null) {
            ((Cursor)localObject5).close();
          }
          continue;
          localObject2 = finally;
          throw ((Throwable)localObject2);
        }
      }
      catch (SQLiteException localSQLiteException)
      {
        for (;;)
        {
          localObject4 = localObject2;
          StringBuilder localStringBuilder = new java/lang/StringBuilder;
          localObject4 = localObject2;
          localStringBuilder.<init>();
          localObject4 = localObject2;
          gs.W("Error getting record count" + localSQLiteException.getMessage());
          if (localObject2 != null) {
            ((Cursor)localObject2).close();
          }
        }
      }
      finally
      {
        if (localObject4 == null) {
          break;
        }
        ((Cursor)localObject4).close();
      }
    }
  }
  
  public SQLiteDatabase getWritableDatabase()
  {
    try
    {
      SQLiteDatabase localSQLiteDatabase = this.sH.getWritableDatabase();
      return localSQLiteDatabase;
    }
    catch (SQLiteException localSQLiteException)
    {
      for (;;)
      {
        gs.W("Error opening writable conversion tracking database");
        Object localObject = null;
      }
    }
  }
  
  public class a
    extends SQLiteOpenHelper
  {
    public a(Context paramContext, String paramString)
    {
      super(paramString, null, 4);
    }
    
    public void onCreate(SQLiteDatabase paramSQLiteDatabase)
    {
      paramSQLiteDatabase.execSQL(ec.cs());
    }
    
    public void onUpgrade(SQLiteDatabase paramSQLiteDatabase, int paramInt1, int paramInt2)
    {
      gs.U("Database updated from version " + paramInt1 + " to version " + paramInt2);
      paramSQLiteDatabase.execSQL("DROP TABLE IF EXISTS InAppPurchase");
      onCreate(paramSQLiteDatabase);
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\internal\ec.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */