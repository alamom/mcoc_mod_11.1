package com.kabam.soda.wske;

import android.os.AsyncTask;
import android.util.Log;
import com.kabam.soda.KabamSession;
import com.kabam.soda.Settings;
import com.kabam.wske.client.ApiInvoker;
import java.util.LinkedHashSet;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public abstract class WSKEAsyncTask<Params, Progress, Result>
  extends AsyncTask<Params, Progress, Result>
{
  public static final String ACCEPT_LANGUAGE_HEADER = "Accept-Language";
  public static final String DEVICE_HEADER = "X-KBM-Device";
  public static final String GEO_COUNTRY_HEADER = "Geo-Country";
  public static final Set<Class<? extends WSKEAsyncTask<?, ?, ?>>> PASSWORDED_CALLS;
  public static final String TOKEN_NEEDED_MESSAGE = "No authentication challenges found";
  public static final Set<Class<? extends WSKEAsyncTask<?, ?, ?>>> UNAUTHENTICATED_CALLS = new LinkedHashSet();
  public static final String USER_AGENT_HEADER = "User-Agent";
  public static final String WSKE_TOKEN_HEADER = "X-KBM-Token";
  private static final AtomicBoolean initialized = new AtomicBoolean(false);
  private static final AtomicReference<String> wskeToken;
  protected final String TAG = getClass().getSimpleName();
  protected final WSKECallback<Result> callback;
  private Throwable error;
  private int errorCode = 0;
  private String errorMessage;
  private long milliSecondsToSleep;
  protected final Settings settings;
  
  static
  {
    UNAUTHENTICATED_CALLS.add(CreateAccountAsyncTask.class);
    UNAUTHENTICATED_CALLS.add(GetTranslationsAsyncTask.class);
    UNAUTHENTICATED_CALLS.add(KAccountLoginAsyncTask.class);
    UNAUTHENTICATED_CALLS.add(KAccountCreateAsyncTask.class);
    UNAUTHENTICATED_CALLS.add(KAccountGetMeAsyncTask.class);
    UNAUTHENTICATED_CALLS.add(GetClientSettingsAsyncTask.class);
    PASSWORDED_CALLS = new LinkedHashSet();
    PASSWORDED_CALLS.add(KAccountLoginAsyncTask.class);
    PASSWORDED_CALLS.add(KAccountCreateAsyncTask.class);
    wskeToken = new AtomicReference();
  }
  
  public WSKEAsyncTask(Settings paramSettings, WSKECallback<Result> paramWSKECallback)
  {
    this.callback = paramWSKECallback;
    this.settings = paramSettings;
    clientInit(paramSettings);
  }
  
  protected static void clientInit(Settings paramSettings)
  {
    if (initialized.compareAndSet(false, true))
    {
      if (("production".equals(paramSettings.getEnv())) || ("sandbox".equals(paramSettings.getEnv())) || ("beta".equals(paramSettings.getEnv()))) {
        break label66;
      }
      Log.d(WSKEAsyncTask.class.getSimpleName(), "Disabling SSL Certificate Verification");
      ApiInvoker.getInstance().ignoreSSLCertificates(true);
    }
    for (;;)
    {
      return;
      label66:
      Log.d(WSKEAsyncTask.class.getSimpleName(), "SSL Certificate Verification enabled");
    }
  }
  
  public static String getWskeToken()
  {
    return (String)wskeToken.get();
  }
  
  public static void invalidateWskeToken()
  {
    Log.d("WSKEAsyncTask", "getClass - invalidateWskeToken!");
    wskeToken.set(null);
    ApiInvoker.getInstance().addDefaultHeader("X-KBM-Token", "");
  }
  
  protected static void setWskeToken(String paramString)
  {
    wskeToken.set(paramString);
    ApiInvoker.getInstance().addDefaultHeader("X-KBM-Token", paramString);
  }
  
  /* Error */
  protected Result doInBackground(Params... paramVarArgs)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 5
    //   3: ldc -65
    //   5: iconst_1
    //   6: anewarray 94	java/lang/Object
    //   9: dup
    //   10: iconst_0
    //   11: aload_1
    //   12: invokestatic 197	java/util/Arrays:toString	([Ljava/lang/Object;)Ljava/lang/String;
    //   15: aastore
    //   16: invokestatic 201	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   19: astore 6
    //   21: aload 6
    //   23: astore 4
    //   25: getstatic 75	com/kabam/soda/wske/WSKEAsyncTask:PASSWORDED_CALLS	Ljava/util/Set;
    //   28: aload_0
    //   29: invokevirtual 98	java/lang/Object:getClass	()Ljava/lang/Class;
    //   32: invokeinterface 204 2 0
    //   37: ifeq +14 -> 51
    //   40: aload 6
    //   42: ldc -50
    //   44: ldc -48
    //   46: invokevirtual 212	java/lang/String:replaceAll	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   49: astore 4
    //   51: aload_0
    //   52: getfield 106	com/kabam/soda/wske/WSKEAsyncTask:TAG	Ljava/lang/String;
    //   55: aload 4
    //   57: invokestatic 144	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   60: pop
    //   61: invokestatic 150	com/kabam/wske/client/ApiInvoker:getInstance	()Lcom/kabam/wske/client/ApiInvoker;
    //   64: aload_0
    //   65: getfield 110	com/kabam/soda/wske/WSKEAsyncTask:settings	Lcom/kabam/soda/Settings;
    //   68: invokevirtual 215	com/kabam/soda/Settings:getClientId	()Ljava/lang/String;
    //   71: invokevirtual 218	com/kabam/wske/client/ApiInvoker:setWSKEClientId	(Ljava/lang/String;)V
    //   74: invokestatic 150	com/kabam/wske/client/ApiInvoker:getInstance	()Lcom/kabam/wske/client/ApiInvoker;
    //   77: aload_0
    //   78: getfield 110	com/kabam/soda/wske/WSKEAsyncTask:settings	Lcom/kabam/soda/Settings;
    //   81: invokevirtual 221	com/kabam/soda/Settings:getMobileKey	()Ljava/lang/String;
    //   84: invokevirtual 224	com/kabam/wske/client/ApiInvoker:setWSKEKey	(Ljava/lang/String;)V
    //   87: aload_0
    //   88: getfield 110	com/kabam/soda/wske/WSKEAsyncTask:settings	Lcom/kabam/soda/Settings;
    //   91: invokestatic 230	com/kabam/soda/Utility:getUserAgent	(Lcom/kabam/soda/Settings;)Ljava/lang/String;
    //   94: astore 7
    //   96: invokestatic 150	com/kabam/wske/client/ApiInvoker:getInstance	()Lcom/kabam/wske/client/ApiInvoker;
    //   99: ldc 25
    //   101: aload 7
    //   103: invokevirtual 175	com/kabam/wske/client/ApiInvoker:addDefaultHeader	(Ljava/lang/String;Ljava/lang/String;)V
    //   106: aload_0
    //   107: getfield 106	com/kabam/soda/wske/WSKEAsyncTask:TAG	Ljava/lang/String;
    //   110: astore 4
    //   112: new 232	java/lang/StringBuilder
    //   115: astore 6
    //   117: aload 6
    //   119: invokespecial 233	java/lang/StringBuilder:<init>	()V
    //   122: aload 4
    //   124: aload 6
    //   126: ldc -21
    //   128: invokevirtual 239	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   131: aload 7
    //   133: invokevirtual 239	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   136: invokevirtual 241	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   139: invokestatic 144	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   142: pop
    //   143: invokestatic 150	com/kabam/wske/client/ApiInvoker:getInstance	()Lcom/kabam/wske/client/ApiInvoker;
    //   146: ldc 12
    //   148: invokestatic 244	com/kabam/soda/Utility:getDeviceHeader	()Ljava/lang/String;
    //   151: invokevirtual 175	com/kabam/wske/client/ApiInvoker:addDefaultHeader	(Ljava/lang/String;Ljava/lang/String;)V
    //   154: invokestatic 150	com/kabam/wske/client/ApiInvoker:getInstance	()Lcom/kabam/wske/client/ApiInvoker;
    //   157: ldc 9
    //   159: aload_0
    //   160: invokevirtual 247	com/kabam/soda/wske/WSKEAsyncTask:getAcceptLanguage	()Ljava/lang/String;
    //   163: invokevirtual 175	com/kabam/wske/client/ApiInvoker:addDefaultHeader	(Ljava/lang/String;Ljava/lang/String;)V
    //   166: invokestatic 250	com/kabam/soda/Utility:getCountryCode	()Ljava/lang/String;
    //   169: astore 4
    //   171: invokestatic 150	com/kabam/wske/client/ApiInvoker:getInstance	()Lcom/kabam/wske/client/ApiInvoker;
    //   174: ldc 15
    //   176: aload 4
    //   178: invokevirtual 175	com/kabam/wske/client/ApiInvoker:addDefaultHeader	(Ljava/lang/String;Ljava/lang/String;)V
    //   181: aload_0
    //   182: getfield 106	com/kabam/soda/wske/WSKEAsyncTask:TAG	Ljava/lang/String;
    //   185: astore 7
    //   187: new 232	java/lang/StringBuilder
    //   190: astore 6
    //   192: aload 6
    //   194: invokespecial 233	java/lang/StringBuilder:<init>	()V
    //   197: aload 7
    //   199: aload 6
    //   201: ldc -4
    //   203: invokevirtual 239	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   206: aload 4
    //   208: invokevirtual 239	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   211: invokevirtual 241	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   214: invokestatic 144	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   217: pop
    //   218: getstatic 55	com/kabam/soda/wske/WSKEAsyncTask:UNAUTHENTICATED_CALLS	Ljava/util/Set;
    //   221: aload_0
    //   222: invokevirtual 98	java/lang/Object:getClass	()Ljava/lang/Class;
    //   225: invokeinterface 204 2 0
    //   230: ifne +258 -> 488
    //   233: invokestatic 257	com/kabam/soda/KabamSession:getPlayerId	()Ljava/lang/String;
    //   236: ifnonnull +172 -> 408
    //   239: new 183	com/kabam/soda/wske/NoPlayerIdException
    //   242: astore_1
    //   243: aload_1
    //   244: invokespecial 258	com/kabam/soda/wske/NoPlayerIdException:<init>	()V
    //   247: aload_1
    //   248: athrow
    //   249: astore_1
    //   250: iconst_0
    //   251: istore_3
    //   252: new 260	org/json/JSONObject
    //   255: astore 4
    //   257: aload 4
    //   259: aload_1
    //   260: invokevirtual 263	com/kabam/wske/client/ApiException:getMessage	()Ljava/lang/String;
    //   263: invokespecial 265	org/json/JSONObject:<init>	(Ljava/lang/String;)V
    //   266: aload 4
    //   268: ldc_w 266
    //   271: invokevirtual 270	org/json/JSONObject:getInt	(Ljava/lang/String;)I
    //   274: istore_2
    //   275: iload_2
    //   276: istore_3
    //   277: aload_1
    //   278: invokevirtual 274	com/kabam/wske/client/ApiException:getCode	()I
    //   281: sipush 401
    //   284: if_icmpne +80 -> 364
    //   287: iload_2
    //   288: istore_3
    //   289: aload_0
    //   290: invokevirtual 98	java/lang/Object:getClass	()Ljava/lang/Class;
    //   293: ldc 71
    //   295: if_acmpne +27 -> 322
    //   298: iload_2
    //   299: istore_3
    //   300: iload_2
    //   301: sipush 1005
    //   304: if_icmpne +18 -> 322
    //   307: sipush 1012
    //   310: istore_3
    //   311: aload_0
    //   312: getfield 106	com/kabam/soda/wske/WSKEAsyncTask:TAG	Ljava/lang/String;
    //   315: ldc_w 276
    //   318: invokestatic 279	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;)I
    //   321: pop
    //   322: iload_3
    //   323: lookupswitch	default:+41->364, 1001:+242->565, 1005:+242->565, 1006:+277->600, 1010:+242->565
    //   364: aload_0
    //   365: new 232	java/lang/StringBuilder
    //   368: dup
    //   369: invokespecial 233	java/lang/StringBuilder:<init>	()V
    //   372: ldc_w 281
    //   375: invokevirtual 239	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   378: aload_1
    //   379: invokevirtual 274	com/kabam/wske/client/ApiException:getCode	()I
    //   382: invokevirtual 284	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   385: ldc_w 286
    //   388: invokevirtual 239	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   391: iload_3
    //   392: invokevirtual 284	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   395: invokevirtual 241	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   398: aload_1
    //   399: iload_3
    //   400: invokevirtual 290	com/kabam/soda/wske/WSKEAsyncTask:setError	(Ljava/lang/String;Ljava/lang/Throwable;I)V
    //   403: aload 5
    //   405: astore_1
    //   406: aload_1
    //   407: areturn
    //   408: invokestatic 292	com/kabam/soda/wske/WSKEAsyncTask:getWskeToken	()Ljava/lang/String;
    //   411: astore 4
    //   413: new 232	java/lang/StringBuilder
    //   416: astore 6
    //   418: aload 6
    //   420: invokespecial 233	java/lang/StringBuilder:<init>	()V
    //   423: ldc -93
    //   425: aload 6
    //   427: ldc_w 294
    //   430: invokevirtual 239	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   433: aload 4
    //   435: invokevirtual 239	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   438: ldc_w 296
    //   441: invokevirtual 239	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   444: aload_0
    //   445: invokevirtual 98	java/lang/Object:getClass	()Ljava/lang/Class;
    //   448: invokevirtual 299	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   451: invokevirtual 241	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   454: invokestatic 144	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   457: pop
    //   458: aload 4
    //   460: ifnonnull +28 -> 488
    //   463: new 185	com/kabam/soda/wske/NotAuthenticatedException
    //   466: astore_1
    //   467: aload_1
    //   468: invokespecial 300	com/kabam/soda/wske/NotAuthenticatedException:<init>	()V
    //   471: aload_1
    //   472: athrow
    //   473: astore_1
    //   474: aload_0
    //   475: ldc_w 302
    //   478: aload_1
    //   479: invokevirtual 305	com/kabam/soda/wske/WSKEAsyncTask:setError	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   482: aload 5
    //   484: astore_1
    //   485: goto -79 -> 406
    //   488: aload_0
    //   489: getfield 307	com/kabam/soda/wske/WSKEAsyncTask:milliSecondsToSleep	J
    //   492: lconst_0
    //   493: lcmp
    //   494: ifle +10 -> 504
    //   497: aload_0
    //   498: getfield 307	com/kabam/soda/wske/WSKEAsyncTask:milliSecondsToSleep	J
    //   501: invokestatic 313	java/lang/Thread:sleep	(J)V
    //   504: aload_0
    //   505: aload_1
    //   506: invokevirtual 316	com/kabam/soda/wske/WSKEAsyncTask:doWork	([Ljava/lang/Object;)Ljava/lang/Object;
    //   509: astore_1
    //   510: goto -104 -> 406
    //   513: astore 4
    //   515: aload_0
    //   516: getfield 106	com/kabam/soda/wske/WSKEAsyncTask:TAG	Ljava/lang/String;
    //   519: new 232	java/lang/StringBuilder
    //   522: dup
    //   523: invokespecial 233	java/lang/StringBuilder:<init>	()V
    //   526: ldc_w 318
    //   529: invokevirtual 239	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   532: aload_1
    //   533: invokevirtual 263	com/kabam/wske/client/ApiException:getMessage	()Ljava/lang/String;
    //   536: invokevirtual 239	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   539: ldc_w 320
    //   542: invokevirtual 239	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   545: aload 4
    //   547: invokevirtual 321	org/json/JSONException:getMessage	()Ljava/lang/String;
    //   550: invokevirtual 239	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   553: invokevirtual 241	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   556: invokestatic 279	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;)I
    //   559: pop
    //   560: iload_3
    //   561: istore_2
    //   562: goto -287 -> 275
    //   565: invokestatic 324	com/kabam/soda/KabamSession:doCertificateExpiry	()V
    //   568: aload_0
    //   569: new 232	java/lang/StringBuilder
    //   572: dup
    //   573: invokespecial 233	java/lang/StringBuilder:<init>	()V
    //   576: ldc_w 326
    //   579: invokevirtual 239	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   582: iload_3
    //   583: invokevirtual 284	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   586: invokevirtual 241	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   589: aload_1
    //   590: iload_3
    //   591: invokevirtual 290	com/kabam/soda/wske/WSKEAsyncTask:setError	(Ljava/lang/String;Ljava/lang/Throwable;I)V
    //   594: aload 5
    //   596: astore_1
    //   597: goto -191 -> 406
    //   600: aload_0
    //   601: new 232	java/lang/StringBuilder
    //   604: dup
    //   605: invokespecial 233	java/lang/StringBuilder:<init>	()V
    //   608: ldc_w 328
    //   611: invokevirtual 239	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   614: iload_3
    //   615: invokevirtual 284	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   618: invokevirtual 241	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   621: aload_1
    //   622: iload_3
    //   623: invokevirtual 290	com/kabam/soda/wske/WSKEAsyncTask:setError	(Ljava/lang/String;Ljava/lang/Throwable;I)V
    //   626: aload 5
    //   628: astore_1
    //   629: goto -223 -> 406
    //   632: astore_1
    //   633: invokestatic 324	com/kabam/soda/KabamSession:doCertificateExpiry	()V
    //   636: aload_0
    //   637: ldc_w 330
    //   640: aload_1
    //   641: invokevirtual 305	com/kabam/soda/wske/WSKEAsyncTask:setError	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   644: aload 5
    //   646: astore_1
    //   647: goto -241 -> 406
    //   650: astore_1
    //   651: aload_0
    //   652: ldc_w 332
    //   655: aload_1
    //   656: invokevirtual 305	com/kabam/soda/wske/WSKEAsyncTask:setError	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   659: aload 5
    //   661: astore_1
    //   662: goto -256 -> 406
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	665	0	this	WSKEAsyncTask
    //   0	665	1	paramVarArgs	Params[]
    //   274	288	2	i	int
    //   251	372	3	j	int
    //   23	436	4	localObject1	Object
    //   513	33	4	localJSONException	org.json.JSONException
    //   1	659	5	localObject2	Object
    //   19	407	6	localObject3	Object
    //   94	104	7	str	String
    // Exception table:
    //   from	to	target	type
    //   61	249	249	com/kabam/wske/client/ApiException
    //   408	458	249	com/kabam/wske/client/ApiException
    //   463	473	249	com/kabam/wske/client/ApiException
    //   488	504	249	com/kabam/wske/client/ApiException
    //   504	510	249	com/kabam/wske/client/ApiException
    //   61	249	473	com/kabam/soda/wske/NoPlayerIdException
    //   408	458	473	com/kabam/soda/wske/NoPlayerIdException
    //   463	473	473	com/kabam/soda/wske/NoPlayerIdException
    //   488	504	473	com/kabam/soda/wske/NoPlayerIdException
    //   504	510	473	com/kabam/soda/wske/NoPlayerIdException
    //   252	275	513	org/json/JSONException
    //   61	249	632	com/kabam/soda/wske/NotAuthenticatedException
    //   408	458	632	com/kabam/soda/wske/NotAuthenticatedException
    //   463	473	632	com/kabam/soda/wske/NotAuthenticatedException
    //   488	504	632	com/kabam/soda/wske/NotAuthenticatedException
    //   504	510	632	com/kabam/soda/wske/NotAuthenticatedException
    //   61	249	650	java/lang/Throwable
    //   408	458	650	java/lang/Throwable
    //   463	473	650	java/lang/Throwable
    //   488	504	650	java/lang/Throwable
    //   504	510	650	java/lang/Throwable
  }
  
  protected abstract Result doWork(Params... paramVarArgs)
    throws Throwable;
  
  protected String getAcceptLanguage()
  {
    if (KabamSession.getLocale().getCountry().equals("")) {}
    for (String str = KabamSession.getLocale().getLanguage();; str = KabamSession.getLocale().getLanguage() + "-" + KabamSession.getLocale().getCountry().toLowerCase(Locale.US)) {
      return str;
    }
  }
  
  protected String getBasePath()
  {
    return this.settings.getBaseUrl();
  }
  
  public Throwable getError()
  {
    return this.error;
  }
  
  public int getErrorCode()
  {
    return this.errorCode;
  }
  
  public String getErrorMessage()
  {
    return this.errorMessage;
  }
  
  public long getMilliSecondsToSleep()
  {
    return this.milliSecondsToSleep;
  }
  
  protected void onCancelled(Result paramResult)
  {
    setError(getClass().getSimpleName() + " timed out", new TimeoutException());
    if (this.callback != null) {
      this.callback.onError(this.errorMessage, this.error, this.errorCode);
    }
  }
  
  protected void onPostExecute(Result paramResult)
  {
    super.onPostExecute(paramResult);
    Log.d(this.TAG, String.format("onPostExecute(%s)", new Object[] { paramResult }));
    if (this.callback != null)
    {
      if (this.error == null) {
        break label63;
      }
      this.callback.onError(this.errorMessage, this.error, this.errorCode);
    }
    for (;;)
    {
      return;
      label63:
      if (paramResult != null) {
        this.callback.onSuccess(paramResult);
      }
    }
  }
  
  protected void onPreExecute()
  {
    super.onPreExecute();
    Log.d(this.TAG, "onPreExecute()");
  }
  
  protected void setError(String paramString)
  {
    Log.e(this.TAG, paramString + ": " + this.error.getMessage());
    this.errorMessage = paramString;
  }
  
  protected void setError(String paramString, Throwable paramThrowable)
  {
    Log.e(this.TAG, paramString + ": " + paramThrowable.getMessage());
    this.errorMessage = paramString;
    this.error = paramThrowable;
  }
  
  protected void setError(String paramString, Throwable paramThrowable, int paramInt)
  {
    Log.e(this.TAG, paramString + ": " + paramThrowable.getMessage());
    this.errorMessage = paramString;
    this.error = paramThrowable;
    this.errorCode = paramInt;
  }
  
  public WSKEAsyncTask<Params, Progress, Result> withMilliSecondsToSleep(long paramLong)
  {
    this.milliSecondsToSleep = paramLong;
    return this;
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\kabam\soda\wske\WSKEAsyncTask.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */