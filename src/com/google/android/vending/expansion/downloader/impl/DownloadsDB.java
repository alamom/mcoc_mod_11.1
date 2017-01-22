package com.google.android.vending.expansion.downloader.impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDoneException;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.provider.BaseColumns;
import android.util.Log;

public class DownloadsDB
{
  private static final int CONTROL_IDX = 7;
  private static final int CURRENTBYTES_IDX = 4;
  private static final String DATABASE_NAME = "DownloadsDB";
  private static final int DATABASE_VERSION = 7;
  private static final String[] DC_PROJECTION = { "FN", "URI", "ETAG", "TOTALBYTES", "CURRENTBYTES", "LASTMOD", "STATUS", "CONTROL", "FAILCOUNT", "RETRYAFTER", "REDIRECTCOUNT", "FILEIDX" };
  private static final int ETAG_IDX = 2;
  private static final int FILENAME_IDX = 0;
  private static final int INDEX_IDX = 11;
  private static final int LASTMOD_IDX = 5;
  public static final String LOG_TAG = DownloadsDB.class.getName();
  private static final int NUM_FAILED_IDX = 8;
  private static final int REDIRECT_COUNT_IDX = 10;
  private static final int RETRY_AFTER_IDX = 9;
  private static final int STATUS_IDX = 6;
  private static final int TOTALBYTES_IDX = 3;
  private static final int URI_IDX = 1;
  private static DownloadsDB mDownloadsDB;
  int mFlags;
  SQLiteStatement mGetDownloadByIndex;
  final SQLiteOpenHelper mHelper;
  long mMetadataRowID = -1L;
  int mStatus = -1;
  SQLiteStatement mUpdateCurrentBytes;
  int mVersionCode = -1;
  
  private DownloadsDB(Context paramContext)
  {
    this.mHelper = new DownloadsContentDBHelper(paramContext);
    paramContext = this.mHelper.getReadableDatabase().rawQuery("SELECT APKVERSION,_id,DOWNLOADSTATUS,DOWNLOADFLAGS FROM MetadataColumns LIMIT 1", null);
    if ((paramContext != null) && (paramContext.moveToFirst()))
    {
      this.mVersionCode = paramContext.getInt(0);
      this.mMetadataRowID = paramContext.getLong(1);
      this.mStatus = paramContext.getInt(2);
      this.mFlags = paramContext.getInt(3);
      paramContext.close();
    }
    mDownloadsDB = this;
  }
  
  /* Error */
  public static DownloadsDB getDB(Context paramContext)
  {
    // Byte code:
    //   0: ldc 2
    //   2: monitorenter
    //   3: getstatic 148	com/google/android/vending/expansion/downloader/impl/DownloadsDB:mDownloadsDB	Lcom/google/android/vending/expansion/downloader/impl/DownloadsDB;
    //   6: ifnonnull +17 -> 23
    //   9: new 2	com/google/android/vending/expansion/downloader/impl/DownloadsDB
    //   12: dup
    //   13: aload_0
    //   14: invokespecial 151	com/google/android/vending/expansion/downloader/impl/DownloadsDB:<init>	(Landroid/content/Context;)V
    //   17: astore_0
    //   18: ldc 2
    //   20: monitorexit
    //   21: aload_0
    //   22: areturn
    //   23: getstatic 148	com/google/android/vending/expansion/downloader/impl/DownloadsDB:mDownloadsDB	Lcom/google/android/vending/expansion/downloader/impl/DownloadsDB;
    //   26: astore_0
    //   27: goto -9 -> 18
    //   30: astore_0
    //   31: ldc 2
    //   33: monitorexit
    //   34: aload_0
    //   35: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	36	0	paramContext	Context
    // Exception table:
    //   from	to	target	type
    //   3	18	30	finally
    //   23	27	30	finally
  }
  
  private SQLiteStatement getDownloadByIndexStatement()
  {
    if (this.mGetDownloadByIndex == null) {
      this.mGetDownloadByIndex = this.mHelper.getReadableDatabase().compileStatement("SELECT _id FROM DownloadColumns WHERE FILEIDX = ?");
    }
    return this.mGetDownloadByIndex;
  }
  
  private SQLiteStatement getUpdateCurrentBytesStatement()
  {
    if (this.mUpdateCurrentBytes == null) {
      this.mUpdateCurrentBytes = this.mHelper.getReadableDatabase().compileStatement("UPDATE DownloadColumns SET CURRENTBYTES = ? WHERE FILEIDX = ?");
    }
    return this.mUpdateCurrentBytes;
  }
  
  public void close()
  {
    this.mHelper.close();
  }
  
  /* Error */
  protected DownloadInfo getDownloadInfoByFileName(String paramString)
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 113	com/google/android/vending/expansion/downloader/impl/DownloadsDB:mHelper	Landroid/database/sqlite/SQLiteOpenHelper;
    //   4: invokevirtual 119	android/database/sqlite/SQLiteOpenHelper:getReadableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   7: astore_3
    //   8: aconst_null
    //   9: astore_2
    //   10: aload_3
    //   11: ldc -86
    //   13: getstatic 96	com/google/android/vending/expansion/downloader/impl/DownloadsDB:DC_PROJECTION	[Ljava/lang/String;
    //   16: ldc -84
    //   18: iconst_1
    //   19: anewarray 70	java/lang/String
    //   22: dup
    //   23: iconst_0
    //   24: aload_1
    //   25: aastore
    //   26: aconst_null
    //   27: aconst_null
    //   28: aconst_null
    //   29: invokevirtual 176	android/database/sqlite/SQLiteDatabase:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   32: astore_3
    //   33: aload_3
    //   34: ifnull +38 -> 72
    //   37: aload_3
    //   38: astore_2
    //   39: aload_3
    //   40: invokeinterface 133 1 0
    //   45: ifeq +27 -> 72
    //   48: aload_3
    //   49: astore_2
    //   50: aload_0
    //   51: aload_3
    //   52: invokevirtual 180	com/google/android/vending/expansion/downloader/impl/DownloadsDB:getDownloadInfoFromCursor	(Landroid/database/Cursor;)Lcom/google/android/vending/expansion/downloader/impl/DownloadInfo;
    //   55: astore_1
    //   56: aload_1
    //   57: astore_2
    //   58: aload_3
    //   59: ifnull +11 -> 70
    //   62: aload_3
    //   63: invokeinterface 146 1 0
    //   68: aload_1
    //   69: astore_2
    //   70: aload_2
    //   71: areturn
    //   72: aload_3
    //   73: ifnull +9 -> 82
    //   76: aload_3
    //   77: invokeinterface 146 1 0
    //   82: aconst_null
    //   83: astore_2
    //   84: goto -14 -> 70
    //   87: astore_1
    //   88: aload_2
    //   89: ifnull +9 -> 98
    //   92: aload_2
    //   93: invokeinterface 146 1 0
    //   98: aload_1
    //   99: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	100	0	this	DownloadsDB
    //   0	100	1	paramString	String
    //   9	84	2	localObject1	Object
    //   7	70	3	localObject2	Object
    // Exception table:
    //   from	to	target	type
    //   10	33	87	finally
    //   39	48	87	finally
    //   50	56	87	finally
  }
  
  public DownloadInfo getDownloadInfoFromCursor(Cursor paramCursor)
  {
    DownloadInfo localDownloadInfo = new DownloadInfo(paramCursor.getInt(11), paramCursor.getString(0), getClass().getPackage().getName());
    setDownloadInfoFromCursor(localDownloadInfo, paramCursor);
    return localDownloadInfo;
  }
  
  public DownloadInfo[] getDownloads()
  {
    DownloadInfo[] arrayOfDownloadInfo = null;
    Object localObject3 = this.mHelper.getReadableDatabase();
    Object localObject1 = null;
    label150:
    for (;;)
    {
      try
      {
        localObject3 = ((SQLiteDatabase)localObject3).query("DownloadColumns", DC_PROJECTION, null, null, null, null, null);
        int i;
        if (localObject3 != null)
        {
          localObject1 = localObject3;
          if (((Cursor)localObject3).moveToFirst())
          {
            localObject1 = localObject3;
            arrayOfDownloadInfo = new DownloadInfo[((Cursor)localObject3).getCount()];
            i = 0;
            localObject1 = localObject3;
            arrayOfDownloadInfo[i] = getDownloadInfoFromCursor((Cursor)localObject3);
            localObject1 = localObject3;
            boolean bool = ((Cursor)localObject3).moveToNext();
            if (bool) {
              break label150;
            }
            localObject1 = arrayOfDownloadInfo;
            if (localObject3 != null)
            {
              ((Cursor)localObject3).close();
              localObject1 = arrayOfDownloadInfo;
            }
            return (DownloadInfo[])localObject1;
          }
        }
        localObject1 = arrayOfDownloadInfo;
        if (localObject3 != null)
        {
          ((Cursor)localObject3).close();
          localObject1 = arrayOfDownloadInfo;
          continue;
          i++;
        }
      }
      finally
      {
        if (localObject1 != null) {
          ((Cursor)localObject1).close();
        }
      }
    }
  }
  
  public int getFlags()
  {
    return this.mFlags;
  }
  
  public long getIDByIndex(int paramInt)
  {
    SQLiteStatement localSQLiteStatement = getDownloadByIndexStatement();
    localSQLiteStatement.clearBindings();
    localSQLiteStatement.bindLong(1, paramInt);
    try
    {
      l = localSQLiteStatement.simpleQueryForLong();
      return l;
    }
    catch (SQLiteDoneException localSQLiteDoneException)
    {
      for (;;)
      {
        long l = -1L;
      }
    }
  }
  
  public long getIDForDownloadInfo(DownloadInfo paramDownloadInfo)
  {
    return getIDByIndex(paramDownloadInfo.mIndex);
  }
  
  public int getLastCheckedVersionCode()
  {
    return this.mVersionCode;
  }
  
  /* Error */
  public boolean isDownloadRequired()
  {
    // Byte code:
    //   0: iconst_1
    //   1: istore_2
    //   2: iconst_1
    //   3: istore_3
    //   4: aload_0
    //   5: getfield 113	com/google/android/vending/expansion/downloader/impl/DownloadsDB:mHelper	Landroid/database/sqlite/SQLiteOpenHelper;
    //   8: invokevirtual 119	android/database/sqlite/SQLiteOpenHelper:getReadableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   11: ldc -13
    //   13: aconst_null
    //   14: invokevirtual 127	android/database/sqlite/SQLiteDatabase:rawQuery	(Ljava/lang/String;[Ljava/lang/String;)Landroid/database/Cursor;
    //   17: astore 5
    //   19: aload 5
    //   21: ifnull +51 -> 72
    //   24: aload 5
    //   26: invokeinterface 133 1 0
    //   31: ifeq +41 -> 72
    //   34: aload 5
    //   36: iconst_0
    //   37: invokeinterface 137 2 0
    //   42: istore_1
    //   43: iload_1
    //   44: ifne +23 -> 67
    //   47: iload_3
    //   48: istore_2
    //   49: iload_2
    //   50: istore_3
    //   51: aload 5
    //   53: ifnull +12 -> 65
    //   56: aload 5
    //   58: invokeinterface 146 1 0
    //   63: iload_2
    //   64: istore_3
    //   65: iload_3
    //   66: ireturn
    //   67: iconst_0
    //   68: istore_2
    //   69: goto -20 -> 49
    //   72: iload_2
    //   73: istore_3
    //   74: aload 5
    //   76: ifnull -11 -> 65
    //   79: aload 5
    //   81: invokeinterface 146 1 0
    //   86: iload_2
    //   87: istore_3
    //   88: goto -23 -> 65
    //   91: astore 4
    //   93: aload 5
    //   95: ifnull +10 -> 105
    //   98: aload 5
    //   100: invokeinterface 146 1 0
    //   105: aload 4
    //   107: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	108	0	this	DownloadsDB
    //   42	2	1	i	int
    //   1	86	2	bool1	boolean
    //   3	85	3	bool2	boolean
    //   91	15	4	localObject	Object
    //   17	82	5	localCursor	Cursor
    // Exception table:
    //   from	to	target	type
    //   24	43	91	finally
  }
  
  public void setDownloadInfoFromCursor(DownloadInfo paramDownloadInfo, Cursor paramCursor)
  {
    paramDownloadInfo.mUri = paramCursor.getString(1);
    paramDownloadInfo.mETag = paramCursor.getString(2);
    paramDownloadInfo.mTotalBytes = paramCursor.getLong(3);
    paramDownloadInfo.mCurrentBytes = paramCursor.getLong(4);
    paramDownloadInfo.mLastMod = paramCursor.getLong(5);
    paramDownloadInfo.mStatus = paramCursor.getInt(6);
    paramDownloadInfo.mControl = paramCursor.getInt(7);
    paramDownloadInfo.mNumFailed = paramCursor.getInt(8);
    paramDownloadInfo.mRetryAfter = paramCursor.getInt(9);
    paramDownloadInfo.mRedirectCount = paramCursor.getInt(10);
  }
  
  public boolean updateDownload(DownloadInfo paramDownloadInfo)
  {
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("FILEIDX", Integer.valueOf(paramDownloadInfo.mIndex));
    localContentValues.put("FN", paramDownloadInfo.mFileName);
    localContentValues.put("URI", paramDownloadInfo.mUri);
    localContentValues.put("ETAG", paramDownloadInfo.mETag);
    localContentValues.put("TOTALBYTES", Long.valueOf(paramDownloadInfo.mTotalBytes));
    localContentValues.put("CURRENTBYTES", Long.valueOf(paramDownloadInfo.mCurrentBytes));
    localContentValues.put("LASTMOD", Long.valueOf(paramDownloadInfo.mLastMod));
    localContentValues.put("STATUS", Integer.valueOf(paramDownloadInfo.mStatus));
    localContentValues.put("CONTROL", Integer.valueOf(paramDownloadInfo.mControl));
    localContentValues.put("FAILCOUNT", Integer.valueOf(paramDownloadInfo.mNumFailed));
    localContentValues.put("RETRYAFTER", Integer.valueOf(paramDownloadInfo.mRetryAfter));
    localContentValues.put("REDIRECTCOUNT", Integer.valueOf(paramDownloadInfo.mRedirectCount));
    return updateDownload(paramDownloadInfo, localContentValues);
  }
  
  public boolean updateDownload(DownloadInfo paramDownloadInfo, ContentValues paramContentValues)
  {
    boolean bool = false;
    if (paramDownloadInfo == null) {}
    for (long l = -1L;; l = getIDForDownloadInfo(paramDownloadInfo))
    {
      try
      {
        localSQLiteDatabase = this.mHelper.getWritableDatabase();
        if (l == -1L) {
          break;
        }
        paramDownloadInfo = new java/lang/StringBuilder;
        paramDownloadInfo.<init>();
        int i = localSQLiteDatabase.update("DownloadColumns", paramContentValues, "DownloadColumns._id = " + l, null);
        if (1 == i) {}
      }
      catch (SQLiteException paramDownloadInfo)
      {
        for (;;)
        {
          SQLiteDatabase localSQLiteDatabase;
          paramDownloadInfo.printStackTrace();
        }
      }
      return bool;
    }
    l = localSQLiteDatabase.insert("DownloadColumns", "URI", paramContentValues);
    if (-1L != l) {}
    for (bool = true;; bool = false) {
      break;
    }
  }
  
  public void updateDownloadCurrentBytes(DownloadInfo paramDownloadInfo)
  {
    SQLiteStatement localSQLiteStatement = getUpdateCurrentBytesStatement();
    localSQLiteStatement.clearBindings();
    localSQLiteStatement.bindLong(1, paramDownloadInfo.mCurrentBytes);
    localSQLiteStatement.bindLong(2, paramDownloadInfo.mIndex);
    localSQLiteStatement.execute();
  }
  
  public boolean updateFlags(int paramInt)
  {
    boolean bool2 = true;
    boolean bool1 = bool2;
    if (this.mFlags != paramInt)
    {
      ContentValues localContentValues = new ContentValues();
      localContentValues.put("DOWNLOADFLAGS", Integer.valueOf(paramInt));
      if (!updateMetadata(localContentValues)) {
        break label51;
      }
      this.mFlags = paramInt;
    }
    label51:
    for (bool1 = bool2;; bool1 = false) {
      return bool1;
    }
  }
  
  /* Error */
  public boolean updateFromDb(DownloadInfo paramDownloadInfo)
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 113	com/google/android/vending/expansion/downloader/impl/DownloadsDB:mHelper	Landroid/database/sqlite/SQLiteOpenHelper;
    //   4: invokevirtual 119	android/database/sqlite/SQLiteOpenHelper:getReadableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   7: astore 4
    //   9: aconst_null
    //   10: astore_3
    //   11: aload 4
    //   13: ldc -86
    //   15: getstatic 96	com/google/android/vending/expansion/downloader/impl/DownloadsDB:DC_PROJECTION	[Ljava/lang/String;
    //   18: ldc_w 354
    //   21: iconst_1
    //   22: anewarray 70	java/lang/String
    //   25: dup
    //   26: iconst_0
    //   27: aload_1
    //   28: getfield 289	com/google/android/vending/expansion/downloader/impl/DownloadInfo:mFileName	Ljava/lang/String;
    //   31: aastore
    //   32: aconst_null
    //   33: aconst_null
    //   34: aconst_null
    //   35: invokevirtual 176	android/database/sqlite/SQLiteDatabase:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   38: astore 4
    //   40: aload 4
    //   42: ifnull +42 -> 84
    //   45: aload 4
    //   47: astore_3
    //   48: aload 4
    //   50: invokeinterface 133 1 0
    //   55: ifeq +29 -> 84
    //   58: aload 4
    //   60: astore_3
    //   61: aload_0
    //   62: aload_1
    //   63: aload 4
    //   65: invokevirtual 204	com/google/android/vending/expansion/downloader/impl/DownloadsDB:setDownloadInfoFromCursor	(Lcom/google/android/vending/expansion/downloader/impl/DownloadInfo;Landroid/database/Cursor;)V
    //   68: aload 4
    //   70: ifnull +10 -> 80
    //   73: aload 4
    //   75: invokeinterface 146 1 0
    //   80: iconst_1
    //   81: istore_2
    //   82: iload_2
    //   83: ireturn
    //   84: aload 4
    //   86: ifnull +10 -> 96
    //   89: aload 4
    //   91: invokeinterface 146 1 0
    //   96: iconst_0
    //   97: istore_2
    //   98: goto -16 -> 82
    //   101: astore_1
    //   102: aload_3
    //   103: ifnull +9 -> 112
    //   106: aload_3
    //   107: invokeinterface 146 1 0
    //   112: aload_1
    //   113: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	114	0	this	DownloadsDB
    //   0	114	1	paramDownloadInfo	DownloadInfo
    //   81	17	2	bool	boolean
    //   10	97	3	localObject1	Object
    //   7	83	4	localObject2	Object
    // Exception table:
    //   from	to	target	type
    //   11	40	101	finally
    //   48	58	101	finally
    //   61	68	101	finally
  }
  
  public boolean updateMetadata(int paramInt1, int paramInt2)
  {
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("APKVERSION", Integer.valueOf(paramInt1));
    localContentValues.put("DOWNLOADSTATUS", Integer.valueOf(paramInt2));
    if (updateMetadata(localContentValues))
    {
      this.mVersionCode = paramInt1;
      this.mStatus = paramInt2;
    }
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public boolean updateMetadata(ContentValues paramContentValues)
  {
    boolean bool = false;
    SQLiteDatabase localSQLiteDatabase = this.mHelper.getWritableDatabase();
    long l;
    if (-1L == this.mMetadataRowID)
    {
      l = localSQLiteDatabase.insert("MetadataColumns", "APKVERSION", paramContentValues);
      if (-1L != l) {}
    }
    for (;;)
    {
      return bool;
      this.mMetadataRowID = l;
      do
      {
        bool = true;
        break;
      } while (localSQLiteDatabase.update("MetadataColumns", paramContentValues, "_id = " + this.mMetadataRowID, null) != 0);
    }
  }
  
  public boolean updateStatus(int paramInt)
  {
    boolean bool2 = true;
    boolean bool1 = bool2;
    if (this.mStatus != paramInt)
    {
      ContentValues localContentValues = new ContentValues();
      localContentValues.put("DOWNLOADSTATUS", Integer.valueOf(paramInt));
      if (!updateMetadata(localContentValues)) {
        break label51;
      }
      this.mStatus = paramInt;
    }
    label51:
    for (bool1 = bool2;; bool1 = false) {
      return bool1;
    }
  }
  
  public static class DownloadColumns
    implements BaseColumns
  {
    public static final String CONTROL = "CONTROL";
    public static final String CURRENTBYTES = "CURRENTBYTES";
    public static final String ETAG = "ETAG";
    public static final String FILENAME = "FN";
    public static final String INDEX = "FILEIDX";
    public static final String LASTMOD = "LASTMOD";
    public static final String NUM_FAILED = "FAILCOUNT";
    public static final String REDIRECT_COUNT = "REDIRECTCOUNT";
    public static final String RETRY_AFTER = "RETRYAFTER";
    public static final String[][] SCHEMA;
    public static final String STATUS = "STATUS";
    public static final String TABLE_NAME = "DownloadColumns";
    public static final String TOTALBYTES = "TOTALBYTES";
    public static final String URI = "URI";
    public static final String _ID = "DownloadColumns._id";
    
    static
    {
      String[] arrayOfString1 = { "_id", "INTEGER PRIMARY KEY" };
      String[] arrayOfString2 = { "FILEIDX", "INTEGER UNIQUE" };
      String[] arrayOfString3 = { "URI", "TEXT" };
      String[] arrayOfString4 = { "FN", "TEXT UNIQUE" };
      String[] arrayOfString5 = { "ETAG", "TEXT" };
      String[] arrayOfString6 = { "TOTALBYTES", "INTEGER" };
      String[] arrayOfString7 = { "CURRENTBYTES", "INTEGER" };
      String[] arrayOfString8 = { "LASTMOD", "INTEGER" };
      String[] arrayOfString9 = { "STATUS", "INTEGER" };
      String[] arrayOfString10 = { "FAILCOUNT", "INTEGER" };
      String[] arrayOfString11 = { "RETRYAFTER", "INTEGER" };
      SCHEMA = new String[][] { arrayOfString1, arrayOfString2, arrayOfString3, arrayOfString4, arrayOfString5, arrayOfString6, arrayOfString7, arrayOfString8, arrayOfString9, { "CONTROL", "INTEGER" }, arrayOfString10, arrayOfString11, { "REDIRECTCOUNT", "INTEGER" } };
    }
  }
  
  protected static class DownloadsContentDBHelper
    extends SQLiteOpenHelper
  {
    private static final String[][][] sSchemas = { DownloadsDB.DownloadColumns.SCHEMA, DownloadsDB.MetadataColumns.SCHEMA };
    private static final String[] sTables = { "DownloadColumns", "MetadataColumns" };
    
    DownloadsContentDBHelper(Context paramContext)
    {
      super("DownloadsDB", null, 7);
    }
    
    private String createTableQueryFromArray(String paramString, String[][] paramArrayOfString)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("CREATE TABLE ");
      localStringBuilder.append(paramString);
      localStringBuilder.append(" (");
      int j = paramArrayOfString.length;
      for (int i = 0;; i++)
      {
        if (i >= j)
        {
          localStringBuilder.setLength(localStringBuilder.length() - 1);
          localStringBuilder.append(");");
          return localStringBuilder.toString();
        }
        paramString = paramArrayOfString[i];
        localStringBuilder.append(' ');
        localStringBuilder.append(paramString[0]);
        localStringBuilder.append(' ');
        localStringBuilder.append(paramString[1]);
        localStringBuilder.append(',');
      }
    }
    
    private void dropTables(SQLiteDatabase paramSQLiteDatabase)
    {
      String[] arrayOfString = sTables;
      int j = arrayOfString.length;
      int i = 0;
      for (;;)
      {
        if (i < j)
        {
          String str = arrayOfString[i];
          try
          {
            StringBuilder localStringBuilder = new java/lang/StringBuilder;
            localStringBuilder.<init>();
            paramSQLiteDatabase.execSQL("DROP TABLE IF EXISTS " + str);
            i++;
          }
          catch (Exception localException)
          {
            for (;;)
            {
              localException.printStackTrace();
            }
          }
        }
      }
    }
    
    public void onCreate(SQLiteDatabase paramSQLiteDatabase)
    {
      int j = sSchemas.length;
      int i = 0;
      for (;;)
      {
        if (i < j) {
          try
          {
            String[][] arrayOfString = (String[][])sSchemas[i];
            paramSQLiteDatabase.execSQL(createTableQueryFromArray(sTables[i], arrayOfString));
            i++;
          }
          catch (Exception paramSQLiteDatabase)
          {
            for (;;)
            {
              paramSQLiteDatabase.printStackTrace();
            }
          }
        }
      }
    }
    
    public void onUpgrade(SQLiteDatabase paramSQLiteDatabase, int paramInt1, int paramInt2)
    {
      Log.w(DownloadsContentDBHelper.class.getName(), "Upgrading database from version " + paramInt1 + " to " + paramInt2 + ", which will destroy all old data");
      dropTables(paramSQLiteDatabase);
      onCreate(paramSQLiteDatabase);
    }
  }
  
  public static class MetadataColumns
    implements BaseColumns
  {
    public static final String APKVERSION = "APKVERSION";
    public static final String DOWNLOAD_STATUS = "DOWNLOADSTATUS";
    public static final String FLAGS = "DOWNLOADFLAGS";
    public static final String[][] SCHEMA;
    public static final String TABLE_NAME = "MetadataColumns";
    public static final String _ID = "MetadataColumns._id";
    
    static
    {
      String[] arrayOfString1 = { "_id", "INTEGER PRIMARY KEY" };
      String[] arrayOfString2 = { "DOWNLOADSTATUS", "INTEGER" };
      String[] arrayOfString3 = { "DOWNLOADFLAGS", "INTEGER" };
      SCHEMA = new String[][] { arrayOfString1, { "APKVERSION", "INTEGER" }, arrayOfString2, arrayOfString3 };
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\vending\expansion\downloader\impl\DownloadsDB.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */