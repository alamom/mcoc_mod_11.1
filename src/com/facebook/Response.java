package com.facebook;

import android.content.Context;
import com.facebook.internal.FileLruCache;
import com.facebook.internal.FileLruCache.Limits;
import com.facebook.internal.Logger;
import com.facebook.internal.Utility;
import com.facebook.model.GraphObject;
import com.facebook.model.GraphObject.Factory;
import com.facebook.model.GraphObjectList;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

public class Response
{
  private static final String BODY_KEY = "body";
  private static final String CODE_KEY = "code";
  private static final int INVALID_SESSION_FACEBOOK_ERROR_CODE = 190;
  public static final String NON_JSON_RESPONSE_PROPERTY = "FACEBOOK_NON_JSON_RESULT";
  private static final String RESPONSE_CACHE_TAG = "ResponseCache";
  private static final String RESPONSE_LOG_TAG = "Response";
  public static final String SUCCESS_KEY = "success";
  private static FileLruCache responseCache;
  private final HttpURLConnection connection;
  private final FacebookRequestError error;
  private final GraphObject graphObject;
  private final GraphObjectList<GraphObject> graphObjectList;
  private final boolean isFromCache;
  private final String rawResponse;
  private final Request request;
  
  static
  {
    if (!Response.class.desiredAssertionStatus()) {}
    for (boolean bool = true;; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }
  
  Response(Request paramRequest, HttpURLConnection paramHttpURLConnection, FacebookRequestError paramFacebookRequestError)
  {
    this(paramRequest, paramHttpURLConnection, null, null, null, false, paramFacebookRequestError);
  }
  
  Response(Request paramRequest, HttpURLConnection paramHttpURLConnection, String paramString, GraphObject paramGraphObject, GraphObjectList<GraphObject> paramGraphObjectList, boolean paramBoolean, FacebookRequestError paramFacebookRequestError)
  {
    this.request = paramRequest;
    this.connection = paramHttpURLConnection;
    this.rawResponse = paramString;
    this.graphObject = paramGraphObject;
    this.graphObjectList = paramGraphObjectList;
    this.isFromCache = paramBoolean;
    this.error = paramFacebookRequestError;
  }
  
  Response(Request paramRequest, HttpURLConnection paramHttpURLConnection, String paramString, GraphObject paramGraphObject, boolean paramBoolean)
  {
    this(paramRequest, paramHttpURLConnection, paramString, paramGraphObject, null, paramBoolean, null);
  }
  
  Response(Request paramRequest, HttpURLConnection paramHttpURLConnection, String paramString, GraphObjectList<GraphObject> paramGraphObjectList, boolean paramBoolean)
  {
    this(paramRequest, paramHttpURLConnection, paramString, null, paramGraphObjectList, paramBoolean, null);
  }
  
  static List<Response> constructErrorResponses(List<Request> paramList, HttpURLConnection paramHttpURLConnection, FacebookException paramFacebookException)
  {
    int j = paramList.size();
    ArrayList localArrayList = new ArrayList(j);
    for (int i = 0; i < j; i++) {
      localArrayList.add(new Response((Request)paramList.get(i), paramHttpURLConnection, new FacebookRequestError(paramHttpURLConnection, paramFacebookException)));
    }
    return localArrayList;
  }
  
  private static Response createResponseFromObject(Request paramRequest, HttpURLConnection paramHttpURLConnection, Object paramObject1, boolean paramBoolean, Object paramObject2)
    throws JSONException
  {
    Object localObject = paramObject1;
    if ((paramObject1 instanceof JSONObject))
    {
      localObject = (JSONObject)paramObject1;
      paramObject1 = FacebookRequestError.checkResponseAndCreateError((JSONObject)localObject, paramObject2, paramHttpURLConnection);
      if (paramObject1 != null)
      {
        if (((FacebookRequestError)paramObject1).getErrorCode() == 190)
        {
          paramObject2 = paramRequest.getSession();
          if (paramObject2 != null) {
            ((Session)paramObject2).closeAndClearTokenInformation();
          }
        }
        paramRequest = new Response(paramRequest, paramHttpURLConnection, (FacebookRequestError)paramObject1);
      }
    }
    for (;;)
    {
      return paramRequest;
      paramObject1 = Utility.getStringPropertyAsJSON((JSONObject)localObject, "body", "FACEBOOK_NON_JSON_RESULT");
      if ((paramObject1 instanceof JSONObject))
      {
        paramObject2 = GraphObject.Factory.create((JSONObject)paramObject1);
        paramRequest = new Response(paramRequest, paramHttpURLConnection, paramObject1.toString(), (GraphObject)paramObject2, paramBoolean);
      }
      else if ((paramObject1 instanceof JSONArray))
      {
        paramObject2 = GraphObject.Factory.createList((JSONArray)paramObject1, GraphObject.class);
        paramRequest = new Response(paramRequest, paramHttpURLConnection, paramObject1.toString(), (GraphObjectList)paramObject2, paramBoolean);
      }
      else
      {
        localObject = JSONObject.NULL;
        if (localObject != JSONObject.NULL) {
          break;
        }
        paramRequest = new Response(paramRequest, paramHttpURLConnection, localObject.toString(), (GraphObject)null, paramBoolean);
      }
    }
    throw new FacebookException("Got unexpected object type in response, class: " + localObject.getClass().getSimpleName());
  }
  
  private static List<Response> createResponsesFromObject(HttpURLConnection paramHttpURLConnection, List<Request> paramList, Object paramObject, boolean paramBoolean)
    throws FacebookException, JSONException
  {
    assert ((paramHttpURLConnection != null) || (paramBoolean));
    int j = paramList.size();
    ArrayList localArrayList = new ArrayList(j);
    Object localObject1 = paramObject;
    Request localRequest;
    if (j == 1) {
      localRequest = (Request)paramList.get(0);
    }
    for (;;)
    {
      try
      {
        JSONObject localJSONObject = new org/json/JSONObject;
        localJSONObject.<init>();
        localJSONObject.put("body", paramObject);
        if (paramHttpURLConnection == null) {
          continue;
        }
        i = paramHttpURLConnection.getResponseCode();
        localJSONObject.put("code", i);
        localObject1 = new org/json/JSONArray;
        ((JSONArray)localObject1).<init>();
        ((JSONArray)localObject1).put(localJSONObject);
      }
      catch (JSONException localJSONException1)
      {
        localArrayList.add(new Response(localRequest, paramHttpURLConnection, new FacebookRequestError(paramHttpURLConnection, localJSONException1)));
        Object localObject2 = paramObject;
        continue;
      }
      catch (IOException localIOException)
      {
        localArrayList.add(new Response(localRequest, paramHttpURLConnection, new FacebookRequestError(paramHttpURLConnection, localIOException)));
        Object localObject3 = paramObject;
        continue;
        localObject3 = (JSONArray)localObject3;
        int i = 0;
        if (i >= ((JSONArray)localObject3).length()) {
          break label358;
        }
        localRequest = (Request)paramList.get(i);
        try
        {
          localArrayList.add(createResponseFromObject(localRequest, paramHttpURLConnection, ((JSONArray)localObject3).get(i), paramBoolean, paramObject));
          i++;
        }
        catch (JSONException localJSONException2)
        {
          localArrayList.add(new Response(localRequest, paramHttpURLConnection, new FacebookRequestError(paramHttpURLConnection, localJSONException2)));
          continue;
        }
        catch (FacebookException localFacebookException)
        {
          localArrayList.add(new Response(localRequest, paramHttpURLConnection, new FacebookRequestError(paramHttpURLConnection, localFacebookException)));
          continue;
        }
      }
      if (((localObject1 instanceof JSONArray)) && (((JSONArray)localObject1).length() == j)) {
        continue;
      }
      throw new FacebookException("Unexpected number of results");
      i = 200;
    }
    label358:
    return localArrayList;
  }
  
  static List<Response> createResponsesFromStream(InputStream paramInputStream, HttpURLConnection paramHttpURLConnection, RequestBatch paramRequestBatch, boolean paramBoolean)
    throws FacebookException, JSONException, IOException
  {
    paramInputStream = Utility.readStreamToString(paramInputStream);
    Logger.log(LoggingBehavior.INCLUDE_RAW_RESPONSES, "Response", "Response (raw)\n  Size: %d\n  Response:\n%s\n", new Object[] { Integer.valueOf(paramInputStream.length()), paramInputStream });
    return createResponsesFromString(paramInputStream, paramHttpURLConnection, paramRequestBatch, paramBoolean);
  }
  
  static List<Response> createResponsesFromString(String paramString, HttpURLConnection paramHttpURLConnection, RequestBatch paramRequestBatch, boolean paramBoolean)
    throws FacebookException, JSONException, IOException
  {
    paramHttpURLConnection = createResponsesFromObject(paramHttpURLConnection, paramRequestBatch, new JSONTokener(paramString).nextValue(), paramBoolean);
    Logger.log(LoggingBehavior.REQUESTS, "Response", "Response\n  Id: %s\n  Size: %d\n  Responses:\n%s\n", new Object[] { paramRequestBatch.getId(), Integer.valueOf(paramString.length()), paramHttpURLConnection });
    return paramHttpURLConnection;
  }
  
  /* Error */
  static List<Response> fromHttpConnection(HttpURLConnection paramHttpURLConnection, RequestBatch paramRequestBatch)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 11
    //   3: aconst_null
    //   4: astore 5
    //   6: aconst_null
    //   7: astore 6
    //   9: aconst_null
    //   10: astore 7
    //   12: aconst_null
    //   13: astore 4
    //   15: aconst_null
    //   16: astore 9
    //   18: aconst_null
    //   19: astore 10
    //   21: aload 4
    //   23: astore_2
    //   24: aload_1
    //   25: instanceof 290
    //   28: ifeq +168 -> 196
    //   31: aload_1
    //   32: checkcast 290	com/facebook/internal/CacheableRequestBatch
    //   35: astore 12
    //   37: invokestatic 294	com/facebook/Response:getResponseCache	()Lcom/facebook/internal/FileLruCache;
    //   40: astore 8
    //   42: aload 12
    //   44: invokevirtual 297	com/facebook/internal/CacheableRequestBatch:getCacheKeyOverride	()Ljava/lang/String;
    //   47: astore_2
    //   48: aload_2
    //   49: astore_3
    //   50: aload_2
    //   51: invokestatic 301	com/facebook/internal/Utility:isNullOrEmpty	(Ljava/lang/String;)Z
    //   54: ifeq +20 -> 74
    //   57: aload_1
    //   58: invokevirtual 302	com/facebook/RequestBatch:size	()I
    //   61: iconst_1
    //   62: if_icmpne +107 -> 169
    //   65: aload_1
    //   66: iconst_0
    //   67: invokevirtual 305	com/facebook/RequestBatch:get	(I)Lcom/facebook/Request;
    //   70: invokevirtual 308	com/facebook/Request:getUrlForSingleRequest	()Ljava/lang/String;
    //   73: astore_3
    //   74: aload 8
    //   76: astore 9
    //   78: aload_3
    //   79: astore 10
    //   81: aload 4
    //   83: astore_2
    //   84: aload 12
    //   86: invokevirtual 311	com/facebook/internal/CacheableRequestBatch:getForceRoundTrip	()Z
    //   89: ifne +107 -> 196
    //   92: aload 8
    //   94: astore 9
    //   96: aload_3
    //   97: astore 10
    //   99: aload 4
    //   101: astore_2
    //   102: aload 8
    //   104: ifnull +92 -> 196
    //   107: aload 8
    //   109: astore 9
    //   111: aload_3
    //   112: astore 10
    //   114: aload 4
    //   116: astore_2
    //   117: aload_3
    //   118: invokestatic 301	com/facebook/internal/Utility:isNullOrEmpty	(Ljava/lang/String;)Z
    //   121: ifne +75 -> 196
    //   124: aload 11
    //   126: astore 4
    //   128: aload 8
    //   130: aload_3
    //   131: invokevirtual 316	com/facebook/internal/FileLruCache:get	(Ljava/lang/String;)Ljava/io/InputStream;
    //   134: astore_2
    //   135: aload_2
    //   136: ifnull +49 -> 185
    //   139: aload_2
    //   140: astore 4
    //   142: aload_2
    //   143: astore 5
    //   145: aload_2
    //   146: astore 6
    //   148: aload_2
    //   149: astore 7
    //   151: aload_2
    //   152: aconst_null
    //   153: aload_1
    //   154: iconst_1
    //   155: invokestatic 318	com/facebook/Response:createResponsesFromStream	(Ljava/io/InputStream;Ljava/net/HttpURLConnection;Lcom/facebook/RequestBatch;Z)Ljava/util/List;
    //   158: astore 9
    //   160: aload 9
    //   162: astore_0
    //   163: aload_2
    //   164: invokestatic 322	com/facebook/internal/Utility:closeQuietly	(Ljava/io/Closeable;)V
    //   167: aload_0
    //   168: areturn
    //   169: getstatic 276	com/facebook/LoggingBehavior:REQUESTS	Lcom/facebook/LoggingBehavior;
    //   172: ldc 31
    //   174: ldc_w 324
    //   177: invokestatic 327	com/facebook/internal/Logger:log	(Lcom/facebook/LoggingBehavior;Ljava/lang/String;Ljava/lang/String;)V
    //   180: aload_2
    //   181: astore_3
    //   182: goto -108 -> 74
    //   185: aload_2
    //   186: invokestatic 322	com/facebook/internal/Utility:closeQuietly	(Ljava/io/Closeable;)V
    //   189: aload_3
    //   190: astore 10
    //   192: aload 8
    //   194: astore 9
    //   196: aload_2
    //   197: astore 8
    //   199: aload_2
    //   200: astore 6
    //   202: aload_2
    //   203: astore 5
    //   205: aload_2
    //   206: astore 7
    //   208: aload_2
    //   209: astore_3
    //   210: aload_0
    //   211: invokevirtual 214	java/net/HttpURLConnection:getResponseCode	()I
    //   214: sipush 400
    //   217: if_icmplt +120 -> 337
    //   220: aload_2
    //   221: astore 8
    //   223: aload_2
    //   224: astore 6
    //   226: aload_2
    //   227: astore 5
    //   229: aload_2
    //   230: astore 7
    //   232: aload_2
    //   233: astore_3
    //   234: aload_0
    //   235: invokevirtual 331	java/net/HttpURLConnection:getErrorStream	()Ljava/io/InputStream;
    //   238: astore_2
    //   239: aload_2
    //   240: astore 8
    //   242: aload_2
    //   243: astore 6
    //   245: aload_2
    //   246: astore 5
    //   248: aload_2
    //   249: astore 7
    //   251: aload_2
    //   252: astore_3
    //   253: aload_2
    //   254: aload_0
    //   255: aload_1
    //   256: iconst_0
    //   257: invokestatic 318	com/facebook/Response:createResponsesFromStream	(Ljava/io/InputStream;Ljava/net/HttpURLConnection;Lcom/facebook/RequestBatch;Z)Ljava/util/List;
    //   260: astore 4
    //   262: aload 4
    //   264: astore_0
    //   265: aload_2
    //   266: invokestatic 322	com/facebook/internal/Utility:closeQuietly	(Ljava/io/Closeable;)V
    //   269: goto -102 -> 167
    //   272: astore_2
    //   273: aload 4
    //   275: invokestatic 322	com/facebook/internal/Utility:closeQuietly	(Ljava/io/Closeable;)V
    //   278: aload 8
    //   280: astore 9
    //   282: aload_3
    //   283: astore 10
    //   285: aload 4
    //   287: astore_2
    //   288: goto -92 -> 196
    //   291: astore_2
    //   292: aload 5
    //   294: invokestatic 322	com/facebook/internal/Utility:closeQuietly	(Ljava/io/Closeable;)V
    //   297: aload 8
    //   299: astore 9
    //   301: aload_3
    //   302: astore 10
    //   304: aload 5
    //   306: astore_2
    //   307: goto -111 -> 196
    //   310: astore_2
    //   311: aload 6
    //   313: invokestatic 322	com/facebook/internal/Utility:closeQuietly	(Ljava/io/Closeable;)V
    //   316: aload 8
    //   318: astore 9
    //   320: aload_3
    //   321: astore 10
    //   323: aload 6
    //   325: astore_2
    //   326: goto -130 -> 196
    //   329: astore_0
    //   330: aload 7
    //   332: invokestatic 322	com/facebook/internal/Utility:closeQuietly	(Ljava/io/Closeable;)V
    //   335: aload_0
    //   336: athrow
    //   337: aload_2
    //   338: astore 8
    //   340: aload_2
    //   341: astore 6
    //   343: aload_2
    //   344: astore 5
    //   346: aload_2
    //   347: astore 7
    //   349: aload_2
    //   350: astore_3
    //   351: aload_0
    //   352: invokevirtual 334	java/net/HttpURLConnection:getInputStream	()Ljava/io/InputStream;
    //   355: astore 4
    //   357: aload 4
    //   359: astore_2
    //   360: aload 9
    //   362: ifnull -123 -> 239
    //   365: aload 4
    //   367: astore_2
    //   368: aload 10
    //   370: ifnull -131 -> 239
    //   373: aload 4
    //   375: astore_2
    //   376: aload 4
    //   378: ifnull -139 -> 239
    //   381: aload 4
    //   383: astore 8
    //   385: aload 4
    //   387: astore 6
    //   389: aload 4
    //   391: astore 5
    //   393: aload 4
    //   395: astore 7
    //   397: aload 4
    //   399: astore_3
    //   400: aload 9
    //   402: aload 10
    //   404: aload 4
    //   406: invokevirtual 338	com/facebook/internal/FileLruCache:interceptAndPut	(Ljava/lang/String;Ljava/io/InputStream;)Ljava/io/InputStream;
    //   409: astore 9
    //   411: aload 4
    //   413: astore_2
    //   414: aload 9
    //   416: ifnull -177 -> 239
    //   419: aload 9
    //   421: astore_2
    //   422: goto -183 -> 239
    //   425: astore_2
    //   426: aload 8
    //   428: astore_3
    //   429: getstatic 276	com/facebook/LoggingBehavior:REQUESTS	Lcom/facebook/LoggingBehavior;
    //   432: ldc 34
    //   434: ldc_w 340
    //   437: iconst_1
    //   438: anewarray 4	java/lang/Object
    //   441: dup
    //   442: iconst_0
    //   443: aload_2
    //   444: aastore
    //   445: invokestatic 259	com/facebook/internal/Logger:log	(Lcom/facebook/LoggingBehavior;Ljava/lang/String;Ljava/lang/String;[Ljava/lang/Object;)V
    //   448: aload 8
    //   450: astore_3
    //   451: aload_1
    //   452: aload_0
    //   453: aload_2
    //   454: invokestatic 342	com/facebook/Response:constructErrorResponses	(Ljava/util/List;Ljava/net/HttpURLConnection;Lcom/facebook/FacebookException;)Ljava/util/List;
    //   457: astore_0
    //   458: aload 8
    //   460: invokestatic 322	com/facebook/internal/Utility:closeQuietly	(Ljava/io/Closeable;)V
    //   463: goto -296 -> 167
    //   466: astore_2
    //   467: aload 6
    //   469: astore_3
    //   470: getstatic 276	com/facebook/LoggingBehavior:REQUESTS	Lcom/facebook/LoggingBehavior;
    //   473: ldc 34
    //   475: ldc_w 340
    //   478: iconst_1
    //   479: anewarray 4	java/lang/Object
    //   482: dup
    //   483: iconst_0
    //   484: aload_2
    //   485: aastore
    //   486: invokestatic 259	com/facebook/internal/Logger:log	(Lcom/facebook/LoggingBehavior;Ljava/lang/String;Ljava/lang/String;[Ljava/lang/Object;)V
    //   489: aload 6
    //   491: astore_3
    //   492: new 176	com/facebook/FacebookException
    //   495: astore 4
    //   497: aload 6
    //   499: astore_3
    //   500: aload 4
    //   502: aload_2
    //   503: invokespecial 345	com/facebook/FacebookException:<init>	(Ljava/lang/Throwable;)V
    //   506: aload 6
    //   508: astore_3
    //   509: aload_1
    //   510: aload_0
    //   511: aload 4
    //   513: invokestatic 342	com/facebook/Response:constructErrorResponses	(Ljava/util/List;Ljava/net/HttpURLConnection;Lcom/facebook/FacebookException;)Ljava/util/List;
    //   516: astore_0
    //   517: aload 6
    //   519: invokestatic 322	com/facebook/internal/Utility:closeQuietly	(Ljava/io/Closeable;)V
    //   522: goto -355 -> 167
    //   525: astore_2
    //   526: aload 5
    //   528: astore_3
    //   529: getstatic 276	com/facebook/LoggingBehavior:REQUESTS	Lcom/facebook/LoggingBehavior;
    //   532: ldc 34
    //   534: ldc_w 340
    //   537: iconst_1
    //   538: anewarray 4	java/lang/Object
    //   541: dup
    //   542: iconst_0
    //   543: aload_2
    //   544: aastore
    //   545: invokestatic 259	com/facebook/internal/Logger:log	(Lcom/facebook/LoggingBehavior;Ljava/lang/String;Ljava/lang/String;[Ljava/lang/Object;)V
    //   548: aload 5
    //   550: astore_3
    //   551: new 176	com/facebook/FacebookException
    //   554: astore 4
    //   556: aload 5
    //   558: astore_3
    //   559: aload 4
    //   561: aload_2
    //   562: invokespecial 345	com/facebook/FacebookException:<init>	(Ljava/lang/Throwable;)V
    //   565: aload 5
    //   567: astore_3
    //   568: aload_1
    //   569: aload_0
    //   570: aload 4
    //   572: invokestatic 342	com/facebook/Response:constructErrorResponses	(Ljava/util/List;Ljava/net/HttpURLConnection;Lcom/facebook/FacebookException;)Ljava/util/List;
    //   575: astore_0
    //   576: aload 5
    //   578: invokestatic 322	com/facebook/internal/Utility:closeQuietly	(Ljava/io/Closeable;)V
    //   581: goto -414 -> 167
    //   584: astore 4
    //   586: aload 7
    //   588: astore_3
    //   589: getstatic 276	com/facebook/LoggingBehavior:REQUESTS	Lcom/facebook/LoggingBehavior;
    //   592: ldc 34
    //   594: ldc_w 340
    //   597: iconst_1
    //   598: anewarray 4	java/lang/Object
    //   601: dup
    //   602: iconst_0
    //   603: aload 4
    //   605: aastore
    //   606: invokestatic 259	com/facebook/internal/Logger:log	(Lcom/facebook/LoggingBehavior;Ljava/lang/String;Ljava/lang/String;[Ljava/lang/Object;)V
    //   609: aload 7
    //   611: astore_3
    //   612: new 176	com/facebook/FacebookException
    //   615: astore_2
    //   616: aload 7
    //   618: astore_3
    //   619: aload_2
    //   620: aload 4
    //   622: invokespecial 345	com/facebook/FacebookException:<init>	(Ljava/lang/Throwable;)V
    //   625: aload 7
    //   627: astore_3
    //   628: aload_1
    //   629: aload_0
    //   630: aload_2
    //   631: invokestatic 342	com/facebook/Response:constructErrorResponses	(Ljava/util/List;Ljava/net/HttpURLConnection;Lcom/facebook/FacebookException;)Ljava/util/List;
    //   634: astore_0
    //   635: aload 7
    //   637: invokestatic 322	com/facebook/internal/Utility:closeQuietly	(Ljava/io/Closeable;)V
    //   640: goto -473 -> 167
    //   643: astore_0
    //   644: aload_3
    //   645: invokestatic 322	com/facebook/internal/Utility:closeQuietly	(Ljava/io/Closeable;)V
    //   648: aload_0
    //   649: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	650	0	paramHttpURLConnection	HttpURLConnection
    //   0	650	1	paramRequestBatch	RequestBatch
    //   23	243	2	localObject1	Object
    //   272	1	2	localFacebookException1	FacebookException
    //   287	1	2	localObject2	Object
    //   291	1	2	localJSONException1	JSONException
    //   306	1	2	localObject3	Object
    //   310	1	2	localIOException1	IOException
    //   325	97	2	localObject4	Object
    //   425	29	2	localFacebookException2	FacebookException
    //   466	37	2	localJSONException2	JSONException
    //   525	37	2	localIOException2	IOException
    //   615	16	2	localFacebookException3	FacebookException
    //   49	596	3	localObject5	Object
    //   13	558	4	localObject6	Object
    //   584	37	4	localSecurityException	SecurityException
    //   4	573	5	localObject7	Object
    //   7	511	6	localObject8	Object
    //   10	626	7	localObject9	Object
    //   40	419	8	localObject10	Object
    //   16	404	9	localObject11	Object
    //   19	384	10	localObject12	Object
    //   1	124	11	localObject13	Object
    //   35	50	12	localCacheableRequestBatch	com.facebook.internal.CacheableRequestBatch
    // Exception table:
    //   from	to	target	type
    //   128	135	272	com/facebook/FacebookException
    //   151	160	272	com/facebook/FacebookException
    //   128	135	291	org/json/JSONException
    //   151	160	291	org/json/JSONException
    //   128	135	310	java/io/IOException
    //   151	160	310	java/io/IOException
    //   128	135	329	finally
    //   151	160	329	finally
    //   210	220	425	com/facebook/FacebookException
    //   234	239	425	com/facebook/FacebookException
    //   253	262	425	com/facebook/FacebookException
    //   351	357	425	com/facebook/FacebookException
    //   400	411	425	com/facebook/FacebookException
    //   210	220	466	org/json/JSONException
    //   234	239	466	org/json/JSONException
    //   253	262	466	org/json/JSONException
    //   351	357	466	org/json/JSONException
    //   400	411	466	org/json/JSONException
    //   210	220	525	java/io/IOException
    //   234	239	525	java/io/IOException
    //   253	262	525	java/io/IOException
    //   351	357	525	java/io/IOException
    //   400	411	525	java/io/IOException
    //   210	220	584	java/lang/SecurityException
    //   234	239	584	java/lang/SecurityException
    //   253	262	584	java/lang/SecurityException
    //   351	357	584	java/lang/SecurityException
    //   400	411	584	java/lang/SecurityException
    //   210	220	643	finally
    //   234	239	643	finally
    //   253	262	643	finally
    //   351	357	643	finally
    //   400	411	643	finally
    //   429	448	643	finally
    //   451	458	643	finally
    //   470	489	643	finally
    //   492	497	643	finally
    //   500	506	643	finally
    //   509	517	643	finally
    //   529	548	643	finally
    //   551	556	643	finally
    //   559	565	643	finally
    //   568	576	643	finally
    //   589	609	643	finally
    //   612	616	643	finally
    //   619	625	643	finally
    //   628	635	643	finally
  }
  
  static FileLruCache getResponseCache()
  {
    if (responseCache == null)
    {
      Context localContext = Session.getStaticContext();
      if (localContext != null) {
        responseCache = new FileLruCache(localContext, "ResponseCache", new FileLruCache.Limits());
      }
    }
    return responseCache;
  }
  
  public final HttpURLConnection getConnection()
  {
    return this.connection;
  }
  
  public final FacebookRequestError getError()
  {
    return this.error;
  }
  
  public final GraphObject getGraphObject()
  {
    return this.graphObject;
  }
  
  public final <T extends GraphObject> T getGraphObjectAs(Class<T> paramClass)
  {
    if (this.graphObject == null) {}
    for (paramClass = null;; paramClass = this.graphObject.cast(paramClass))
    {
      return paramClass;
      if (paramClass == null) {
        throw new NullPointerException("Must pass in a valid interface that extends GraphObject");
      }
    }
  }
  
  public final GraphObjectList<GraphObject> getGraphObjectList()
  {
    return this.graphObjectList;
  }
  
  public final <T extends GraphObject> GraphObjectList<T> getGraphObjectListAs(Class<T> paramClass)
  {
    if (this.graphObjectList == null) {}
    for (paramClass = null;; paramClass = this.graphObjectList.castToListOf(paramClass)) {
      return paramClass;
    }
  }
  
  public final boolean getIsFromCache()
  {
    return this.isFromCache;
  }
  
  public String getRawResponse()
  {
    return this.rawResponse;
  }
  
  public Request getRequest()
  {
    return this.request;
  }
  
  public Request getRequestForPagedResults(PagingDirection paramPagingDirection)
  {
    Session localSession = null;
    Object localObject1 = localSession;
    Object localObject2;
    if (this.graphObject != null)
    {
      localObject2 = ((PagedResults)this.graphObject.cast(PagedResults.class)).getPaging();
      localObject1 = localSession;
      if (localObject2 != null)
      {
        if (paramPagingDirection != PagingDirection.NEXT) {
          break label65;
        }
        localObject1 = ((PagingInfo)localObject2).getNext();
      }
    }
    if (Utility.isNullOrEmpty((String)localObject1)) {
      paramPagingDirection = null;
    }
    for (;;)
    {
      return paramPagingDirection;
      label65:
      localObject1 = ((PagingInfo)localObject2).getPrevious();
      break;
      if ((localObject1 != null) && (((String)localObject1).equals(this.request.getUrlForSingleRequest()))) {
        paramPagingDirection = null;
      } else {
        try
        {
          paramPagingDirection = new com/facebook/Request;
          localSession = this.request.getSession();
          localObject2 = new java/net/URL;
          ((URL)localObject2).<init>((String)localObject1);
          paramPagingDirection.<init>(localSession, (URL)localObject2);
        }
        catch (MalformedURLException paramPagingDirection)
        {
          paramPagingDirection = null;
        }
      }
    }
  }
  
  public String toString()
  {
    for (;;)
    {
      try
      {
        if (this.connection == null) {
          continue;
        }
        i = this.connection.getResponseCode();
        str1 = String.format("%d", new Object[] { Integer.valueOf(i) });
      }
      catch (IOException localIOException)
      {
        int i;
        String str1;
        String str2 = "unknown";
        continue;
      }
      return "{Response: " + " responseCode: " + str1 + ", graphObject: " + this.graphObject + ", error: " + this.error + ", isFromCache:" + this.isFromCache + "}";
      i = 200;
    }
  }
  
  static abstract interface PagedResults
    extends GraphObject
  {
    public abstract GraphObjectList<GraphObject> getData();
    
    public abstract Response.PagingInfo getPaging();
  }
  
  public static enum PagingDirection
  {
    NEXT,  PREVIOUS;
    
    private PagingDirection() {}
  }
  
  static abstract interface PagingInfo
    extends GraphObject
  {
    public abstract String getNext();
    
    public abstract String getPrevious();
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\facebook\Response.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */