package com.adjust.sdk;

import android.content.Context;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream.GetField;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.json.JSONException;
import org.json.JSONObject;

public class Util
{
  private static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'Z";
  private static SimpleDateFormat dateFormat;
  private static final String fieldReadErrorMessage = "Unable to read '%s' field in migration device with message (%s)";
  
  public static boolean checkPermission(Context paramContext, String paramString)
  {
    if (paramContext.checkCallingOrSelfPermission(paramString) == 0) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  protected static String createUuid()
  {
    return UUID.randomUUID().toString();
  }
  
  public static String dateFormat(long paramLong)
  {
    if (dateFormat == null) {
      dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'Z", Locale.US);
    }
    return dateFormat.format(Long.valueOf(paramLong));
  }
  
  public static boolean equalBoolean(Boolean paramBoolean1, Boolean paramBoolean2)
  {
    return equalObject(paramBoolean1, paramBoolean2);
  }
  
  public static boolean equalEnum(Enum paramEnum1, Enum paramEnum2)
  {
    return equalObject(paramEnum1, paramEnum2);
  }
  
  public static boolean equalInt(Integer paramInteger1, Integer paramInteger2)
  {
    return equalObject(paramInteger1, paramInteger2);
  }
  
  public static boolean equalLong(Long paramLong1, Long paramLong2)
  {
    return equalObject(paramLong1, paramLong2);
  }
  
  public static boolean equalObject(Object paramObject1, Object paramObject2)
  {
    boolean bool;
    if ((paramObject1 == null) || (paramObject2 == null)) {
      if ((paramObject1 == null) && (paramObject2 == null)) {
        bool = true;
      }
    }
    for (;;)
    {
      return bool;
      bool = false;
      continue;
      bool = paramObject1.equals(paramObject2);
    }
  }
  
  public static boolean equalString(String paramString1, String paramString2)
  {
    return equalObject(paramString1, paramString2);
  }
  
  public static boolean equalsDouble(Double paramDouble1, Double paramDouble2)
  {
    boolean bool = true;
    if ((paramDouble1 == null) || (paramDouble2 == null)) {
      if ((paramDouble1 != null) || (paramDouble2 != null)) {}
    }
    for (;;)
    {
      return bool;
      bool = false;
      continue;
      if (Double.doubleToLongBits(paramDouble1.doubleValue()) != Double.doubleToLongBits(paramDouble2.doubleValue())) {
        bool = false;
      }
    }
  }
  
  public static boolean equalsMap(Map paramMap1, Map paramMap2)
  {
    boolean bool;
    if ((paramMap1 == null) || (paramMap2 == null)) {
      if ((paramMap1 == null) && (paramMap2 == null)) {
        bool = true;
      }
    }
    for (;;)
    {
      return bool;
      bool = false;
      continue;
      bool = paramMap1.entrySet().equals(paramMap2.entrySet());
    }
  }
  
  public static HttpClient getHttpClient()
  {
    BasicHttpParams localBasicHttpParams = new BasicHttpParams();
    HttpConnectionParams.setConnectionTimeout(localBasicHttpParams, 60000);
    HttpConnectionParams.setSoTimeout(localBasicHttpParams, 60000);
    return AdjustFactory.getHttpClient(localBasicHttpParams);
  }
  
  private static ILogger getLogger()
  {
    return AdjustFactory.getLogger();
  }
  
  public static String getPlayAdId(Context paramContext)
  {
    return Reflection.getPlayAdId(paramContext);
  }
  
  public static int hashBoolean(Boolean paramBoolean)
  {
    if (paramBoolean == null) {}
    for (int i = 0;; i = paramBoolean.hashCode()) {
      return i;
    }
  }
  
  public static int hashEnum(Enum paramEnum)
  {
    if (paramEnum == null) {}
    for (int i = 0;; i = paramEnum.hashCode()) {
      return i;
    }
  }
  
  public static int hashLong(Long paramLong)
  {
    if (paramLong == null) {}
    for (int i = 0;; i = paramLong.hashCode()) {
      return i;
    }
  }
  
  public static int hashMap(Map paramMap)
  {
    if (paramMap == null) {}
    for (int i = 0;; i = paramMap.entrySet().hashCode()) {
      return i;
    }
  }
  
  public static int hashString(String paramString)
  {
    if (paramString == null) {}
    for (int i = 0;; i = paramString.hashCode()) {
      return i;
    }
  }
  
  public static Boolean isPlayTrackingEnabled(Context paramContext)
  {
    return Reflection.isPlayTrackingEnabled(paramContext);
  }
  
  public static JSONObject parseJsonResponse(HttpResponse paramHttpResponse)
  {
    if (paramHttpResponse == null) {
      paramHttpResponse = null;
    }
    for (;;)
    {
      return paramHttpResponse;
      Object localObject1 = null;
      try
      {
        Object localObject2 = new java/io/ByteArrayOutputStream;
        ((ByteArrayOutputStream)localObject2).<init>();
        paramHttpResponse.getEntity().writeTo((OutputStream)localObject2);
        ((ByteArrayOutputStream)localObject2).close();
        localObject2 = ((ByteArrayOutputStream)localObject2).toString().trim();
        localObject1 = localObject2;
        getLogger().verbose("Response: %s", new Object[] { localObject1 });
        if (localObject1 == null) {
          paramHttpResponse = null;
        }
      }
      catch (Exception localException)
      {
        for (;;)
        {
          getLogger().error("Failed to parse response (%s)", new Object[] { localException.getMessage() });
        }
        Object localObject3 = null;
        try
        {
          JSONObject localJSONObject = new org/json/JSONObject;
          localJSONObject.<init>((String)localObject1);
          localObject1 = localJSONObject;
          if (localObject1 == null) {
            paramHttpResponse = null;
          }
        }
        catch (JSONException localJSONException)
        {
          for (;;)
          {
            getLogger().error("Failed to parse json response: %s (%s)", new Object[] { localObject1, localJSONException.getMessage() });
            localObject1 = localObject3;
          }
          String str = ((JSONObject)localObject1).optString("message", null);
          localObject3 = str;
          if (str == null) {
            localObject3 = "No message found";
          }
          if (paramHttpResponse.getStatusLine().getStatusCode() == 200)
          {
            getLogger().info("%s", new Object[] { localObject3 });
            paramHttpResponse = (HttpResponse)localObject1;
          }
          else
          {
            getLogger().error("%s", new Object[] { localObject3 });
            paramHttpResponse = (HttpResponse)localObject1;
          }
        }
      }
    }
  }
  
  public static String quote(String paramString)
  {
    String str;
    if (paramString == null) {
      str = null;
    }
    for (;;)
    {
      return str;
      str = paramString;
      if (Pattern.compile("\\s").matcher(paramString).find()) {
        str = String.format(Locale.US, "'%s'", new Object[] { paramString });
      }
    }
  }
  
  public static boolean readBooleanField(ObjectInputStream.GetField paramGetField, String paramString, boolean paramBoolean)
  {
    try
    {
      boolean bool = paramGetField.get(paramString, paramBoolean);
      paramBoolean = bool;
    }
    catch (Exception paramGetField)
    {
      for (;;)
      {
        getLogger().debug("Unable to read '%s' field in migration device with message (%s)", new Object[] { paramString, paramGetField.getMessage() });
      }
    }
    return paramBoolean;
  }
  
  public static int readIntField(ObjectInputStream.GetField paramGetField, String paramString, int paramInt)
  {
    try
    {
      int i = paramGetField.get(paramString, paramInt);
      paramInt = i;
    }
    catch (Exception paramGetField)
    {
      for (;;)
      {
        getLogger().debug("Unable to read '%s' field in migration device with message (%s)", new Object[] { paramString, paramGetField.getMessage() });
      }
    }
    return paramInt;
  }
  
  public static long readLongField(ObjectInputStream.GetField paramGetField, String paramString, long paramLong)
  {
    try
    {
      long l = paramGetField.get(paramString, paramLong);
      paramLong = l;
    }
    catch (Exception paramGetField)
    {
      for (;;)
      {
        getLogger().debug("Unable to read '%s' field in migration device with message (%s)", new Object[] { paramString, paramGetField.getMessage() });
      }
    }
    return paramLong;
  }
  
  /* Error */
  public static <T> T readObject(Context paramContext, String paramString1, String paramString2)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 6
    //   3: aconst_null
    //   4: astore 4
    //   6: aconst_null
    //   7: astore 9
    //   9: aconst_null
    //   10: astore 10
    //   12: aconst_null
    //   13: astore 8
    //   15: aconst_null
    //   16: astore 7
    //   18: aconst_null
    //   19: astore 11
    //   21: aload 9
    //   23: astore_3
    //   24: aload 8
    //   26: astore 5
    //   28: aload_0
    //   29: aload_1
    //   30: invokevirtual 292	android/content/Context:openFileInput	(Ljava/lang/String;)Ljava/io/FileInputStream;
    //   33: astore 12
    //   35: aload 12
    //   37: astore_0
    //   38: aload_0
    //   39: astore 4
    //   41: aload 9
    //   43: astore_3
    //   44: aload_0
    //   45: astore 6
    //   47: aload 8
    //   49: astore 5
    //   51: new 294	java/io/BufferedInputStream
    //   54: astore_1
    //   55: aload_0
    //   56: astore 4
    //   58: aload 9
    //   60: astore_3
    //   61: aload_0
    //   62: astore 6
    //   64: aload 8
    //   66: astore 5
    //   68: aload_1
    //   69: aload 12
    //   71: invokespecial 297	java/io/BufferedInputStream:<init>	(Ljava/io/InputStream;)V
    //   74: aload_1
    //   75: astore_0
    //   76: aload_0
    //   77: astore 4
    //   79: aload 9
    //   81: astore_3
    //   82: aload_0
    //   83: astore 6
    //   85: aload 8
    //   87: astore 5
    //   89: new 299	java/io/ObjectInputStream
    //   92: astore 12
    //   94: aload_0
    //   95: astore 4
    //   97: aload 9
    //   99: astore_3
    //   100: aload_0
    //   101: astore 6
    //   103: aload 8
    //   105: astore 5
    //   107: aload 12
    //   109: aload_1
    //   110: invokespecial 300	java/io/ObjectInputStream:<init>	(Ljava/io/InputStream;)V
    //   113: aload 12
    //   115: astore 8
    //   117: aload 11
    //   119: astore_0
    //   120: aload 8
    //   122: astore 4
    //   124: aload 9
    //   126: astore_3
    //   127: aload 10
    //   129: astore_1
    //   130: aload 12
    //   132: invokevirtual 303	java/io/ObjectInputStream:readObject	()Ljava/lang/Object;
    //   135: astore 5
    //   137: aload 5
    //   139: astore_0
    //   140: aload 8
    //   142: astore 4
    //   144: aload 5
    //   146: astore_3
    //   147: aload 5
    //   149: astore_1
    //   150: aload 5
    //   152: astore 7
    //   154: invokestatic 188	com/adjust/sdk/Util:getLogger	()Lcom/adjust/sdk/ILogger;
    //   157: ldc_w 305
    //   160: iconst_2
    //   161: anewarray 4	java/lang/Object
    //   164: dup
    //   165: iconst_0
    //   166: aload_2
    //   167: aastore
    //   168: dup
    //   169: iconst_1
    //   170: aload 5
    //   172: aastore
    //   173: invokeinterface 270 3 0
    //   178: aload 5
    //   180: astore_3
    //   181: aload 8
    //   183: astore 4
    //   185: aload 4
    //   187: ifnull +10 -> 197
    //   190: aload 4
    //   192: invokeinterface 308 1 0
    //   197: aload_3
    //   198: areturn
    //   199: astore_1
    //   200: aload 8
    //   202: astore 4
    //   204: aload_0
    //   205: astore_3
    //   206: aload 8
    //   208: astore 6
    //   210: aload_0
    //   211: astore 5
    //   213: invokestatic 188	com/adjust/sdk/Util:getLogger	()Lcom/adjust/sdk/ILogger;
    //   216: ldc_w 310
    //   219: iconst_2
    //   220: anewarray 4	java/lang/Object
    //   223: dup
    //   224: iconst_0
    //   225: aload_2
    //   226: aastore
    //   227: dup
    //   228: iconst_1
    //   229: aload_1
    //   230: invokevirtual 311	java/lang/ClassNotFoundException:getMessage	()Ljava/lang/String;
    //   233: aastore
    //   234: invokeinterface 204 3 0
    //   239: aload 8
    //   241: astore 4
    //   243: aload_0
    //   244: astore_3
    //   245: goto -60 -> 185
    //   248: astore_0
    //   249: invokestatic 188	com/adjust/sdk/Util:getLogger	()Lcom/adjust/sdk/ILogger;
    //   252: ldc_w 313
    //   255: iconst_1
    //   256: anewarray 4	java/lang/Object
    //   259: dup
    //   260: iconst_0
    //   261: aload_2
    //   262: aastore
    //   263: invokeinterface 196 3 0
    //   268: goto -83 -> 185
    //   271: astore_0
    //   272: aload 8
    //   274: astore 4
    //   276: aload_1
    //   277: astore_3
    //   278: aload 8
    //   280: astore 6
    //   282: aload_1
    //   283: astore 5
    //   285: invokestatic 188	com/adjust/sdk/Util:getLogger	()Lcom/adjust/sdk/ILogger;
    //   288: ldc_w 315
    //   291: iconst_2
    //   292: anewarray 4	java/lang/Object
    //   295: dup
    //   296: iconst_0
    //   297: aload_2
    //   298: aastore
    //   299: dup
    //   300: iconst_1
    //   301: aload_0
    //   302: invokevirtual 316	java/lang/ClassCastException:getMessage	()Ljava/lang/String;
    //   305: aastore
    //   306: invokeinterface 204 3 0
    //   311: aload 8
    //   313: astore 4
    //   315: aload_1
    //   316: astore_3
    //   317: goto -132 -> 185
    //   320: astore_0
    //   321: invokestatic 188	com/adjust/sdk/Util:getLogger	()Lcom/adjust/sdk/ILogger;
    //   324: ldc_w 318
    //   327: iconst_2
    //   328: anewarray 4	java/lang/Object
    //   331: dup
    //   332: iconst_0
    //   333: aload_2
    //   334: aastore
    //   335: dup
    //   336: iconst_1
    //   337: aload_0
    //   338: aastore
    //   339: invokeinterface 204 3 0
    //   344: aload 6
    //   346: astore 4
    //   348: aload 5
    //   350: astore_3
    //   351: goto -166 -> 185
    //   354: astore_0
    //   355: aload 8
    //   357: astore 4
    //   359: aload 7
    //   361: astore_3
    //   362: aload 8
    //   364: astore 6
    //   366: aload 7
    //   368: astore 5
    //   370: invokestatic 188	com/adjust/sdk/Util:getLogger	()Lcom/adjust/sdk/ILogger;
    //   373: ldc_w 320
    //   376: iconst_2
    //   377: anewarray 4	java/lang/Object
    //   380: dup
    //   381: iconst_0
    //   382: aload_2
    //   383: aastore
    //   384: dup
    //   385: iconst_1
    //   386: aload_0
    //   387: invokevirtual 201	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   390: aastore
    //   391: invokeinterface 204 3 0
    //   396: aload 8
    //   398: astore 4
    //   400: aload 7
    //   402: astore_3
    //   403: goto -218 -> 185
    //   406: astore_0
    //   407: invokestatic 188	com/adjust/sdk/Util:getLogger	()Lcom/adjust/sdk/ILogger;
    //   410: ldc_w 322
    //   413: iconst_2
    //   414: anewarray 4	java/lang/Object
    //   417: dup
    //   418: iconst_0
    //   419: aload_2
    //   420: aastore
    //   421: dup
    //   422: iconst_1
    //   423: aload_0
    //   424: aastore
    //   425: invokeinterface 204 3 0
    //   430: goto -233 -> 197
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	433	0	paramContext	Context
    //   0	433	1	paramString1	String
    //   0	433	2	paramString2	String
    //   23	380	3	localObject1	Object
    //   4	395	4	localObject2	Object
    //   26	343	5	localObject3	Object
    //   1	364	6	localObject4	Object
    //   16	385	7	localObject5	Object
    //   13	384	8	localObject6	Object
    //   7	118	9	localObject7	Object
    //   10	118	10	localObject8	Object
    //   19	99	11	localObject9	Object
    //   33	98	12	localObject10	Object
    // Exception table:
    //   from	to	target	type
    //   130	137	199	java/lang/ClassNotFoundException
    //   154	178	199	java/lang/ClassNotFoundException
    //   28	35	248	java/io/FileNotFoundException
    //   51	55	248	java/io/FileNotFoundException
    //   68	74	248	java/io/FileNotFoundException
    //   89	94	248	java/io/FileNotFoundException
    //   107	113	248	java/io/FileNotFoundException
    //   130	137	248	java/io/FileNotFoundException
    //   154	178	248	java/io/FileNotFoundException
    //   213	239	248	java/io/FileNotFoundException
    //   285	311	248	java/io/FileNotFoundException
    //   370	396	248	java/io/FileNotFoundException
    //   130	137	271	java/lang/ClassCastException
    //   154	178	271	java/lang/ClassCastException
    //   28	35	320	java/lang/Exception
    //   51	55	320	java/lang/Exception
    //   68	74	320	java/lang/Exception
    //   89	94	320	java/lang/Exception
    //   107	113	320	java/lang/Exception
    //   213	239	320	java/lang/Exception
    //   285	311	320	java/lang/Exception
    //   370	396	320	java/lang/Exception
    //   130	137	354	java/lang/Exception
    //   154	178	354	java/lang/Exception
    //   190	197	406	java/lang/Exception
  }
  
  public static <T> T readObjectField(ObjectInputStream.GetField paramGetField, String paramString, T paramT)
  {
    try
    {
      paramGetField = paramGetField.get(paramString, paramT);
      return paramGetField;
    }
    catch (Exception paramGetField)
    {
      for (;;)
      {
        getLogger().debug("Unable to read '%s' field in migration device with message (%s)", new Object[] { paramString, paramGetField.getMessage() });
        paramGetField = paramT;
      }
    }
  }
  
  public static String readStringField(ObjectInputStream.GetField paramGetField, String paramString1, String paramString2)
  {
    return (String)readObjectField(paramGetField, paramString1, paramString2);
  }
  
  /* Error */
  public static <T> void writeObject(T paramT, Context paramContext, String paramString1, String paramString2)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 4
    //   3: aload_1
    //   4: aload_2
    //   5: iconst_0
    //   6: invokevirtual 342	android/content/Context:openFileOutput	(Ljava/lang/String;I)Ljava/io/FileOutputStream;
    //   9: astore 5
    //   11: aload 5
    //   13: astore_1
    //   14: aload_1
    //   15: astore 4
    //   17: new 344	java/io/BufferedOutputStream
    //   20: astore_2
    //   21: aload_1
    //   22: astore 4
    //   24: aload_2
    //   25: aload 5
    //   27: invokespecial 346	java/io/BufferedOutputStream:<init>	(Ljava/io/OutputStream;)V
    //   30: aload_2
    //   31: astore_1
    //   32: aload_1
    //   33: astore 4
    //   35: new 348	java/io/ObjectOutputStream
    //   38: astore 5
    //   40: aload_1
    //   41: astore 4
    //   43: aload 5
    //   45: aload_2
    //   46: invokespecial 349	java/io/ObjectOutputStream:<init>	(Ljava/io/OutputStream;)V
    //   49: aload 5
    //   51: astore_1
    //   52: aload_1
    //   53: astore 4
    //   55: aload 5
    //   57: aload_0
    //   58: invokevirtual 352	java/io/ObjectOutputStream:writeObject	(Ljava/lang/Object;)V
    //   61: aload_1
    //   62: astore 4
    //   64: invokestatic 188	com/adjust/sdk/Util:getLogger	()Lcom/adjust/sdk/ILogger;
    //   67: ldc_w 354
    //   70: iconst_2
    //   71: anewarray 4	java/lang/Object
    //   74: dup
    //   75: iconst_0
    //   76: aload_3
    //   77: aastore
    //   78: dup
    //   79: iconst_1
    //   80: aload_0
    //   81: aastore
    //   82: invokeinterface 270 3 0
    //   87: aload_1
    //   88: ifnull +9 -> 97
    //   91: aload_1
    //   92: invokeinterface 308 1 0
    //   97: return
    //   98: astore_0
    //   99: aload_1
    //   100: astore 4
    //   102: invokestatic 188	com/adjust/sdk/Util:getLogger	()Lcom/adjust/sdk/ILogger;
    //   105: ldc_w 356
    //   108: iconst_1
    //   109: anewarray 4	java/lang/Object
    //   112: dup
    //   113: iconst_0
    //   114: aload_3
    //   115: aastore
    //   116: invokeinterface 204 3 0
    //   121: goto -34 -> 87
    //   124: astore_0
    //   125: invokestatic 188	com/adjust/sdk/Util:getLogger	()Lcom/adjust/sdk/ILogger;
    //   128: ldc_w 358
    //   131: iconst_2
    //   132: anewarray 4	java/lang/Object
    //   135: dup
    //   136: iconst_0
    //   137: aload_3
    //   138: aastore
    //   139: dup
    //   140: iconst_1
    //   141: aload_0
    //   142: aastore
    //   143: invokeinterface 204 3 0
    //   148: aload 4
    //   150: astore_1
    //   151: goto -64 -> 87
    //   154: astore_0
    //   155: invokestatic 188	com/adjust/sdk/Util:getLogger	()Lcom/adjust/sdk/ILogger;
    //   158: ldc_w 360
    //   161: iconst_2
    //   162: anewarray 4	java/lang/Object
    //   165: dup
    //   166: iconst_0
    //   167: aload_3
    //   168: aastore
    //   169: dup
    //   170: iconst_1
    //   171: aload_0
    //   172: aastore
    //   173: invokeinterface 204 3 0
    //   178: goto -81 -> 97
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	181	0	paramT	T
    //   0	181	1	paramContext	Context
    //   0	181	2	paramString1	String
    //   0	181	3	paramString2	String
    //   1	148	4	localContext	Context
    //   9	47	5	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   55	61	98	java/io/NotSerializableException
    //   64	87	98	java/io/NotSerializableException
    //   3	11	124	java/lang/Exception
    //   17	21	124	java/lang/Exception
    //   24	30	124	java/lang/Exception
    //   35	40	124	java/lang/Exception
    //   43	49	124	java/lang/Exception
    //   55	61	124	java/lang/Exception
    //   64	87	124	java/lang/Exception
    //   102	121	124	java/lang/Exception
    //   91	97	154	java/lang/Exception
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\adjust\sdk\Util.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */