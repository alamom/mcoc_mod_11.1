package com.explodingbarrel.nanigans;

import android.app.Activity;
import android.content.Context;
import com.unity3d.player.UnityPlayer;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Manager
{
  private static final double CLIENT_VERSION = 1.0D;
  static Manager _this = null;
  private static final ExecutorService executorService = Executors.newCachedThreadPool();
  private String FB_APP_ID = "";
  
  public Manager(String paramString)
  {
    this.FB_APP_ID = paramString;
  }
  
  public static void Init(String paramString)
  {
    _this = new Manager(paramString);
  }
  
  public static void Install(String paramString)
  {
    if (_this != null) {
      _this.trackNanigansEvent(paramString, "install", "main", new NanigansEventParameter[0]);
    }
  }
  
  public static void Visit(String paramString)
  {
    if (_this != null) {
      _this.trackNanigansEvent(paramString, "visit", "dau", new NanigansEventParameter[0]);
    }
  }
  
  static Activity getApplicationContext()
  {
    return UnityPlayer.currentActivity;
  }
  
  public Future<String> trackNanigansEvent(String paramString1, String paramString2, String paramString3, String paramString4)
  {
    return trackNanigansEvent(paramString1, paramString2, paramString3, new NanigansEventParameter[] { new NanigansEventParameter("value", new String[] { paramString4 }) });
  }
  
  public Future<String> trackNanigansEvent(final String paramString1, final String paramString2, final String paramString3, final NanigansEventParameter... paramVarArgs)
  {
    executorService.submit(new Callable()
    {
      private final Context context;
      
      /* Error */
      public String call()
        throws java.lang.Exception
      {
        // Byte code:
        //   0: aconst_null
        //   1: astore 7
        //   3: aload_0
        //   4: getfield 37	com/explodingbarrel/nanigans/Manager$1RequestCallable:context	Landroid/content/Context;
        //   7: invokevirtual 54	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
        //   10: ldc 56
        //   12: invokestatic 62	android/provider/Settings$Secure:getString	(Landroid/content/ContentResolver;Ljava/lang/String;)Ljava/lang/String;
        //   15: astore 5
        //   17: aload 5
        //   19: astore 7
        //   21: aconst_null
        //   22: astore 5
        //   24: aload 5
        //   26: astore 8
        //   28: aload_0
        //   29: getfield 37	com/explodingbarrel/nanigans/Manager$1RequestCallable:context	Landroid/content/Context;
        //   32: ldc 64
        //   34: invokevirtual 68	android/content/Context:checkCallingOrSelfPermission	(Ljava/lang/String;)I
        //   37: ifne +20 -> 57
        //   40: aload_0
        //   41: getfield 37	com/explodingbarrel/nanigans/Manager$1RequestCallable:context	Landroid/content/Context;
        //   44: ldc 70
        //   46: invokevirtual 74	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
        //   49: checkcast 76	android/telephony/TelephonyManager
        //   52: invokevirtual 79	android/telephony/TelephonyManager:getDeviceId	()Ljava/lang/String;
        //   55: astore 8
        //   57: aconst_null
        //   58: astore 9
        //   60: invokestatic 85	java/util/Calendar:getInstance	()Ljava/util/Calendar;
        //   63: invokevirtual 89	java/util/Calendar:getTimeZone	()Ljava/util/TimeZone;
        //   66: iconst_0
        //   67: iconst_0
        //   68: invokevirtual 95	java/util/TimeZone:getDisplayName	(ZI)Ljava/lang/String;
        //   71: astore 5
        //   73: aload 5
        //   75: astore 9
        //   77: aload_0
        //   78: getfield 37	com/explodingbarrel/nanigans/Manager$1RequestCallable:context	Landroid/content/Context;
        //   81: ldc 97
        //   83: iconst_0
        //   84: invokevirtual 101	android/content/Context:getSharedPreferences	(Ljava/lang/String;I)Landroid/content/SharedPreferences;
        //   87: astore 6
        //   89: aload 6
        //   91: ldc 103
        //   93: aconst_null
        //   94: invokeinterface 108 3 0
        //   99: astore 5
        //   101: aload 5
        //   103: astore 10
        //   105: aload 5
        //   107: ifnonnull +47 -> 154
        //   110: invokestatic 114	java/util/UUID:randomUUID	()Ljava/util/UUID;
        //   113: invokevirtual 117	java/util/UUID:toString	()Ljava/lang/String;
        //   116: ldc 119
        //   118: ldc 121
        //   120: invokevirtual 126	java/lang/String:replaceAll	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   123: astore 10
        //   125: aload 6
        //   127: invokeinterface 130 1 0
        //   132: astore 5
        //   134: aload 5
        //   136: ldc 103
        //   138: aload 10
        //   140: invokeinterface 136 3 0
        //   145: pop
        //   146: aload 5
        //   148: invokeinterface 140 1 0
        //   153: pop
        //   154: aload_0
        //   155: getfield 26	com/explodingbarrel/nanigans/Manager$1RequestCallable:val$type	Ljava/lang/String;
        //   158: ifnull +16 -> 174
        //   161: aload_0
        //   162: getfield 26	com/explodingbarrel/nanigans/Manager$1RequestCallable:val$type	Ljava/lang/String;
        //   165: invokevirtual 143	java/lang/String:trim	()Ljava/lang/String;
        //   168: invokevirtual 147	java/lang/String:length	()I
        //   171: ifne +27 -> 198
        //   174: getstatic 153	java/lang/System:out	Ljava/io/PrintStream;
        //   177: ldc -101
        //   179: invokevirtual 161	java/io/PrintStream:println	(Ljava/lang/String;)V
        //   182: aconst_null
        //   183: astore 5
        //   185: aload 5
        //   187: areturn
        //   188: astore 5
        //   190: aload 5
        //   192: invokevirtual 164	java/lang/Exception:printStackTrace	()V
        //   195: goto -174 -> 21
        //   198: aload_0
        //   199: getfield 28	com/explodingbarrel/nanigans/Manager$1RequestCallable:val$name	Ljava/lang/String;
        //   202: ifnull +16 -> 218
        //   205: aload_0
        //   206: getfield 28	com/explodingbarrel/nanigans/Manager$1RequestCallable:val$name	Ljava/lang/String;
        //   209: invokevirtual 143	java/lang/String:trim	()Ljava/lang/String;
        //   212: invokevirtual 147	java/lang/String:length	()I
        //   215: ifne +17 -> 232
        //   218: getstatic 153	java/lang/System:out	Ljava/io/PrintStream;
        //   221: ldc -90
        //   223: invokevirtual 161	java/io/PrintStream:println	(Ljava/lang/String;)V
        //   226: aconst_null
        //   227: astore 5
        //   229: goto -44 -> 185
        //   232: aconst_null
        //   233: astore 12
        //   235: aconst_null
        //   236: astore 11
        //   238: aload_0
        //   239: getfield 26	com/explodingbarrel/nanigans/Manager$1RequestCallable:val$type	Ljava/lang/String;
        //   242: ldc -88
        //   244: invokevirtual 172	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
        //   247: ifne +19 -> 266
        //   250: aload 11
        //   252: astore 5
        //   254: aload_0
        //   255: getfield 26	com/explodingbarrel/nanigans/Manager$1RequestCallable:val$type	Ljava/lang/String;
        //   258: ldc -82
        //   260: invokevirtual 172	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
        //   263: ifeq +70 -> 333
        //   266: aload 12
        //   268: astore 6
        //   270: invokestatic 178	com/explodingbarrel/nanigans/Manager:getApplicationContext	()Landroid/app/Activity;
        //   273: invokevirtual 181	android/app/Activity:getContentResolver	()Landroid/content/ContentResolver;
        //   276: ldc -73
        //   278: invokestatic 189	android/net/Uri:parse	(Ljava/lang/String;)Landroid/net/Uri;
        //   281: iconst_1
        //   282: anewarray 123	java/lang/String
        //   285: dup
        //   286: iconst_0
        //   287: ldc -65
        //   289: aastore
        //   290: aconst_null
        //   291: aconst_null
        //   292: aconst_null
        //   293: invokevirtual 197	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
        //   296: astore 5
        //   298: aload 5
        //   300: ifnull +17 -> 317
        //   303: aload 12
        //   305: astore 6
        //   307: aload 5
        //   309: invokeinterface 202 1 0
        //   314: ifne +118 -> 432
        //   317: aload 12
        //   319: astore 6
        //   321: getstatic 153	java/lang/System:out	Ljava/io/PrintStream;
        //   324: ldc -52
        //   326: invokevirtual 161	java/io/PrintStream:println	(Ljava/lang/String;)V
        //   329: aload 11
        //   331: astore 5
        //   333: aload_0
        //   334: getfield 30	com/explodingbarrel/nanigans/Manager$1RequestCallable:val$eventParameter	[Lcom/explodingbarrel/nanigans/Manager$NanigansEventParameter;
        //   337: ifnonnull +182 -> 519
        //   340: iconst_0
        //   341: istore_1
        //   342: new 206	java/util/ArrayList
        //   345: dup
        //   346: iload_1
        //   347: bipush 6
        //   349: iadd
        //   350: invokespecial 209	java/util/ArrayList:<init>	(I)V
        //   353: astore 6
        //   355: iconst_0
        //   356: istore 4
        //   358: iconst_0
        //   359: istore_3
        //   360: aload_0
        //   361: getfield 30	com/explodingbarrel/nanigans/Manager$1RequestCallable:val$eventParameter	[Lcom/explodingbarrel/nanigans/Manager$NanigansEventParameter;
        //   364: ifnull +196 -> 560
        //   367: aload_0
        //   368: getfield 30	com/explodingbarrel/nanigans/Manager$1RequestCallable:val$eventParameter	[Lcom/explodingbarrel/nanigans/Manager$NanigansEventParameter;
        //   371: astore 12
        //   373: aload 12
        //   375: arraylength
        //   376: istore_2
        //   377: iconst_0
        //   378: istore_1
        //   379: iload_3
        //   380: istore 4
        //   382: iload_1
        //   383: iload_2
        //   384: if_icmpge +176 -> 560
        //   387: aload 12
        //   389: iload_1
        //   390: aaload
        //   391: astore 11
        //   393: iload_3
        //   394: istore 4
        //   396: aload 11
        //   398: ifnull +25 -> 423
        //   401: iload_3
        //   402: istore 4
        //   404: aload 11
        //   406: invokestatic 215	com/explodingbarrel/nanigans/Manager$NanigansEventParameter:access$000	(Lcom/explodingbarrel/nanigans/Manager$NanigansEventParameter;)Ljava/lang/String;
        //   409: ifnull +14 -> 423
        //   412: aload 11
        //   414: invokestatic 219	com/explodingbarrel/nanigans/Manager$NanigansEventParameter:access$100	(Lcom/explodingbarrel/nanigans/Manager$NanigansEventParameter;)[Ljava/lang/String;
        //   417: ifnonnull +111 -> 528
        //   420: iload_3
        //   421: istore 4
        //   423: iinc 1 1
        //   426: iload 4
        //   428: istore_3
        //   429: goto -50 -> 379
        //   432: aload 12
        //   434: astore 6
        //   436: aload 5
        //   438: aload 5
        //   440: ldc -65
        //   442: invokeinterface 222 2 0
        //   447: invokeinterface 225 2 0
        //   452: astore 11
        //   454: aload 11
        //   456: ifnull +22 -> 478
        //   459: aload 11
        //   461: astore 5
        //   463: aload 11
        //   465: astore 6
        //   467: aload 11
        //   469: invokevirtual 143	java/lang/String:trim	()Ljava/lang/String;
        //   472: invokevirtual 147	java/lang/String:length	()I
        //   475: ifne -142 -> 333
        //   478: aload 11
        //   480: astore 6
        //   482: getstatic 153	java/lang/System:out	Ljava/io/PrintStream;
        //   485: ldc -29
        //   487: invokevirtual 161	java/io/PrintStream:println	(Ljava/lang/String;)V
        //   490: aload 11
        //   492: astore 5
        //   494: goto -161 -> 333
        //   497: astore 5
        //   499: getstatic 153	java/lang/System:out	Ljava/io/PrintStream;
        //   502: ldc -27
        //   504: invokevirtual 161	java/io/PrintStream:println	(Ljava/lang/String;)V
        //   507: aload 5
        //   509: invokevirtual 164	java/lang/Exception:printStackTrace	()V
        //   512: aload 6
        //   514: astore 5
        //   516: goto -183 -> 333
        //   519: aload_0
        //   520: getfield 30	com/explodingbarrel/nanigans/Manager$1RequestCallable:val$eventParameter	[Lcom/explodingbarrel/nanigans/Manager$NanigansEventParameter;
        //   523: arraylength
        //   524: istore_1
        //   525: goto -183 -> 342
        //   528: aload 6
        //   530: aload 11
        //   532: invokeinterface 235 2 0
        //   537: pop
        //   538: iload_3
        //   539: istore 4
        //   541: iload_3
        //   542: ifne -119 -> 423
        //   545: aload 11
        //   547: invokestatic 215	com/explodingbarrel/nanigans/Manager$NanigansEventParameter:access$000	(Lcom/explodingbarrel/nanigans/Manager$NanigansEventParameter;)Ljava/lang/String;
        //   550: ldc -19
        //   552: invokevirtual 240	java/lang/String:equals	(Ljava/lang/Object;)Z
        //   555: istore 4
        //   557: goto -134 -> 423
        //   560: iload 4
        //   562: ifne +44 -> 606
        //   565: aload 6
        //   567: new 211	com/explodingbarrel/nanigans/Manager$NanigansEventParameter
        //   570: dup
        //   571: aload_0
        //   572: getfield 24	com/explodingbarrel/nanigans/Manager$1RequestCallable:this$0	Lcom/explodingbarrel/nanigans/Manager;
        //   575: ldc -19
        //   577: iconst_1
        //   578: anewarray 123	java/lang/String
        //   581: dup
        //   582: iconst_0
        //   583: invokestatic 114	java/util/UUID:randomUUID	()Ljava/util/UUID;
        //   586: invokevirtual 117	java/util/UUID:toString	()Ljava/lang/String;
        //   589: ldc 119
        //   591: ldc 121
        //   593: invokevirtual 126	java/lang/String:replaceAll	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   596: aastore
        //   597: invokespecial 243	com/explodingbarrel/nanigans/Manager$NanigansEventParameter:<init>	(Lcom/explodingbarrel/nanigans/Manager;Ljava/lang/String;[Ljava/lang/String;)V
        //   600: invokeinterface 235 2 0
        //   605: pop
        //   606: aload 7
        //   608: ifnull +33 -> 641
        //   611: aload 6
        //   613: new 211	com/explodingbarrel/nanigans/Manager$NanigansEventParameter
        //   616: dup
        //   617: aload_0
        //   618: getfield 24	com/explodingbarrel/nanigans/Manager$1RequestCallable:this$0	Lcom/explodingbarrel/nanigans/Manager;
        //   621: ldc -11
        //   623: iconst_1
        //   624: anewarray 123	java/lang/String
        //   627: dup
        //   628: iconst_0
        //   629: aload 7
        //   631: aastore
        //   632: invokespecial 243	com/explodingbarrel/nanigans/Manager$NanigansEventParameter:<init>	(Lcom/explodingbarrel/nanigans/Manager;Ljava/lang/String;[Ljava/lang/String;)V
        //   635: invokeinterface 235 2 0
        //   640: pop
        //   641: aload 8
        //   643: ifnull +33 -> 676
        //   646: aload 6
        //   648: new 211	com/explodingbarrel/nanigans/Manager$NanigansEventParameter
        //   651: dup
        //   652: aload_0
        //   653: getfield 24	com/explodingbarrel/nanigans/Manager$1RequestCallable:this$0	Lcom/explodingbarrel/nanigans/Manager;
        //   656: ldc -9
        //   658: iconst_1
        //   659: anewarray 123	java/lang/String
        //   662: dup
        //   663: iconst_0
        //   664: aload 8
        //   666: aastore
        //   667: invokespecial 243	com/explodingbarrel/nanigans/Manager$NanigansEventParameter:<init>	(Lcom/explodingbarrel/nanigans/Manager;Ljava/lang/String;[Ljava/lang/String;)V
        //   670: invokeinterface 235 2 0
        //   675: pop
        //   676: aload 9
        //   678: ifnull +33 -> 711
        //   681: aload 6
        //   683: new 211	com/explodingbarrel/nanigans/Manager$NanigansEventParameter
        //   686: dup
        //   687: aload_0
        //   688: getfield 24	com/explodingbarrel/nanigans/Manager$1RequestCallable:this$0	Lcom/explodingbarrel/nanigans/Manager;
        //   691: ldc -7
        //   693: iconst_1
        //   694: anewarray 123	java/lang/String
        //   697: dup
        //   698: iconst_0
        //   699: aload 9
        //   701: aastore
        //   702: invokespecial 243	com/explodingbarrel/nanigans/Manager$NanigansEventParameter:<init>	(Lcom/explodingbarrel/nanigans/Manager;Ljava/lang/String;[Ljava/lang/String;)V
        //   705: invokeinterface 235 2 0
        //   710: pop
        //   711: aload 6
        //   713: new 211	com/explodingbarrel/nanigans/Manager$NanigansEventParameter
        //   716: dup
        //   717: aload_0
        //   718: getfield 24	com/explodingbarrel/nanigans/Manager$1RequestCallable:this$0	Lcom/explodingbarrel/nanigans/Manager;
        //   721: ldc -5
        //   723: iconst_1
        //   724: anewarray 123	java/lang/String
        //   727: dup
        //   728: iconst_0
        //   729: getstatic 257	android/os/Build$VERSION:SDK_INT	I
        //   732: invokestatic 261	java/lang/Integer:toString	(I)Ljava/lang/String;
        //   735: aastore
        //   736: invokespecial 243	com/explodingbarrel/nanigans/Manager$NanigansEventParameter:<init>	(Lcom/explodingbarrel/nanigans/Manager;Ljava/lang/String;[Ljava/lang/String;)V
        //   739: invokeinterface 235 2 0
        //   744: pop
        //   745: aload 6
        //   747: new 211	com/explodingbarrel/nanigans/Manager$NanigansEventParameter
        //   750: dup
        //   751: aload_0
        //   752: getfield 24	com/explodingbarrel/nanigans/Manager$1RequestCallable:this$0	Lcom/explodingbarrel/nanigans/Manager;
        //   755: ldc_w 263
        //   758: iconst_1
        //   759: anewarray 123	java/lang/String
        //   762: dup
        //   763: iconst_0
        //   764: ldc_w 265
        //   767: aastore
        //   768: invokespecial 243	com/explodingbarrel/nanigans/Manager$NanigansEventParameter:<init>	(Lcom/explodingbarrel/nanigans/Manager;Ljava/lang/String;[Ljava/lang/String;)V
        //   771: invokeinterface 235 2 0
        //   776: pop
        //   777: aload 6
        //   779: new 211	com/explodingbarrel/nanigans/Manager$NanigansEventParameter
        //   782: dup
        //   783: aload_0
        //   784: getfield 24	com/explodingbarrel/nanigans/Manager$1RequestCallable:this$0	Lcom/explodingbarrel/nanigans/Manager;
        //   787: ldc_w 267
        //   790: iconst_1
        //   791: anewarray 123	java/lang/String
        //   794: dup
        //   795: iconst_0
        //   796: aload_0
        //   797: getfield 26	com/explodingbarrel/nanigans/Manager$1RequestCallable:val$type	Ljava/lang/String;
        //   800: aastore
        //   801: invokespecial 243	com/explodingbarrel/nanigans/Manager$NanigansEventParameter:<init>	(Lcom/explodingbarrel/nanigans/Manager;Ljava/lang/String;[Ljava/lang/String;)V
        //   804: invokeinterface 235 2 0
        //   809: pop
        //   810: aload 6
        //   812: new 211	com/explodingbarrel/nanigans/Manager$NanigansEventParameter
        //   815: dup
        //   816: aload_0
        //   817: getfield 24	com/explodingbarrel/nanigans/Manager$1RequestCallable:this$0	Lcom/explodingbarrel/nanigans/Manager;
        //   820: ldc_w 269
        //   823: iconst_1
        //   824: anewarray 123	java/lang/String
        //   827: dup
        //   828: iconst_0
        //   829: aload_0
        //   830: getfield 24	com/explodingbarrel/nanigans/Manager$1RequestCallable:this$0	Lcom/explodingbarrel/nanigans/Manager;
        //   833: invokestatic 273	com/explodingbarrel/nanigans/Manager:access$200	(Lcom/explodingbarrel/nanigans/Manager;)Ljava/lang/String;
        //   836: aastore
        //   837: invokespecial 243	com/explodingbarrel/nanigans/Manager$NanigansEventParameter:<init>	(Lcom/explodingbarrel/nanigans/Manager;Ljava/lang/String;[Ljava/lang/String;)V
        //   840: invokeinterface 235 2 0
        //   845: pop
        //   846: aload 5
        //   848: ifnull +34 -> 882
        //   851: aload 6
        //   853: new 211	com/explodingbarrel/nanigans/Manager$NanigansEventParameter
        //   856: dup
        //   857: aload_0
        //   858: getfield 24	com/explodingbarrel/nanigans/Manager$1RequestCallable:this$0	Lcom/explodingbarrel/nanigans/Manager;
        //   861: ldc_w 275
        //   864: iconst_1
        //   865: anewarray 123	java/lang/String
        //   868: dup
        //   869: iconst_0
        //   870: aload 5
        //   872: aastore
        //   873: invokespecial 243	com/explodingbarrel/nanigans/Manager$NanigansEventParameter:<init>	(Lcom/explodingbarrel/nanigans/Manager;Ljava/lang/String;[Ljava/lang/String;)V
        //   876: invokeinterface 235 2 0
        //   881: pop
        //   882: aload_0
        //   883: getfield 32	com/explodingbarrel/nanigans/Manager$1RequestCallable:val$uid	Ljava/lang/String;
        //   886: ifnull +36 -> 922
        //   889: aload 6
        //   891: new 211	com/explodingbarrel/nanigans/Manager$NanigansEventParameter
        //   894: dup
        //   895: aload_0
        //   896: getfield 24	com/explodingbarrel/nanigans/Manager$1RequestCallable:this$0	Lcom/explodingbarrel/nanigans/Manager;
        //   899: ldc_w 277
        //   902: iconst_1
        //   903: anewarray 123	java/lang/String
        //   906: dup
        //   907: iconst_0
        //   908: aload_0
        //   909: getfield 32	com/explodingbarrel/nanigans/Manager$1RequestCallable:val$uid	Ljava/lang/String;
        //   912: aastore
        //   913: invokespecial 243	com/explodingbarrel/nanigans/Manager$NanigansEventParameter:<init>	(Lcom/explodingbarrel/nanigans/Manager;Ljava/lang/String;[Ljava/lang/String;)V
        //   916: invokeinterface 235 2 0
        //   921: pop
        //   922: aload 6
        //   924: new 211	com/explodingbarrel/nanigans/Manager$NanigansEventParameter
        //   927: dup
        //   928: aload_0
        //   929: getfield 24	com/explodingbarrel/nanigans/Manager$1RequestCallable:this$0	Lcom/explodingbarrel/nanigans/Manager;
        //   932: ldc_w 279
        //   935: iconst_1
        //   936: anewarray 123	java/lang/String
        //   939: dup
        //   940: iconst_0
        //   941: aload_0
        //   942: getfield 28	com/explodingbarrel/nanigans/Manager$1RequestCallable:val$name	Ljava/lang/String;
        //   945: aastore
        //   946: invokespecial 243	com/explodingbarrel/nanigans/Manager$NanigansEventParameter:<init>	(Lcom/explodingbarrel/nanigans/Manager;Ljava/lang/String;[Ljava/lang/String;)V
        //   949: invokeinterface 235 2 0
        //   954: pop
        //   955: aload 6
        //   957: new 211	com/explodingbarrel/nanigans/Manager$NanigansEventParameter
        //   960: dup
        //   961: aload_0
        //   962: getfield 24	com/explodingbarrel/nanigans/Manager$1RequestCallable:this$0	Lcom/explodingbarrel/nanigans/Manager;
        //   965: ldc 103
        //   967: iconst_1
        //   968: anewarray 123	java/lang/String
        //   971: dup
        //   972: iconst_0
        //   973: aload 10
        //   975: aastore
        //   976: invokespecial 243	com/explodingbarrel/nanigans/Manager$NanigansEventParameter:<init>	(Lcom/explodingbarrel/nanigans/Manager;Ljava/lang/String;[Ljava/lang/String;)V
        //   979: invokeinterface 235 2 0
        //   984: pop
        //   985: aload 6
        //   987: new 211	com/explodingbarrel/nanigans/Manager$NanigansEventParameter
        //   990: dup
        //   991: aload_0
        //   992: getfield 24	com/explodingbarrel/nanigans/Manager$1RequestCallable:this$0	Lcom/explodingbarrel/nanigans/Manager;
        //   995: ldc_w 281
        //   998: iconst_1
        //   999: anewarray 123	java/lang/String
        //   1002: dup
        //   1003: iconst_0
        //   1004: dconst_1
        //   1005: invokestatic 286	java/lang/Double:toString	(D)Ljava/lang/String;
        //   1008: aastore
        //   1009: invokespecial 243	com/explodingbarrel/nanigans/Manager$NanigansEventParameter:<init>	(Lcom/explodingbarrel/nanigans/Manager;Ljava/lang/String;[Ljava/lang/String;)V
        //   1012: invokeinterface 235 2 0
        //   1017: pop
        //   1018: aconst_null
        //   1019: astore 13
        //   1021: aconst_null
        //   1022: astore 14
        //   1024: aconst_null
        //   1025: astore 12
        //   1027: aconst_null
        //   1028: astore 10
        //   1030: aconst_null
        //   1031: astore 11
        //   1033: aload 10
        //   1035: astore 7
        //   1037: aload 13
        //   1039: astore 5
        //   1041: aload 6
        //   1043: invokeinterface 290 1 0
        //   1048: astore 15
        //   1050: aconst_null
        //   1051: astore 9
        //   1053: aload 10
        //   1055: astore 7
        //   1057: aload 13
        //   1059: astore 5
        //   1061: aload 14
        //   1063: astore 8
        //   1065: aload 15
        //   1067: invokeinterface 295 1 0
        //   1072: ifeq +306 -> 1378
        //   1075: aload 10
        //   1077: astore 7
        //   1079: aload 13
        //   1081: astore 5
        //   1083: aload 14
        //   1085: astore 8
        //   1087: aload 15
        //   1089: invokeinterface 298 1 0
        //   1094: checkcast 211	com/explodingbarrel/nanigans/Manager$NanigansEventParameter
        //   1097: astore 16
        //   1099: aload 16
        //   1101: ifnull -48 -> 1053
        //   1104: aload 10
        //   1106: astore 7
        //   1108: aload 13
        //   1110: astore 5
        //   1112: aload 14
        //   1114: astore 8
        //   1116: aload 16
        //   1118: invokestatic 215	com/explodingbarrel/nanigans/Manager$NanigansEventParameter:access$000	(Lcom/explodingbarrel/nanigans/Manager$NanigansEventParameter;)Ljava/lang/String;
        //   1121: ifnull -68 -> 1053
        //   1124: aload 10
        //   1126: astore 7
        //   1128: aload 13
        //   1130: astore 5
        //   1132: aload 14
        //   1134: astore 8
        //   1136: aload 16
        //   1138: invokestatic 219	com/explodingbarrel/nanigans/Manager$NanigansEventParameter:access$100	(Lcom/explodingbarrel/nanigans/Manager$NanigansEventParameter;)[Ljava/lang/String;
        //   1141: ifnull -88 -> 1053
        //   1144: aload 9
        //   1146: ifnonnull +106 -> 1252
        //   1149: aload 10
        //   1151: astore 7
        //   1153: aload 13
        //   1155: astore 5
        //   1157: aload 14
        //   1159: astore 8
        //   1161: new 300	java/lang/StringBuilder
        //   1164: astore 6
        //   1166: aload 10
        //   1168: astore 7
        //   1170: aload 13
        //   1172: astore 5
        //   1174: aload 14
        //   1176: astore 8
        //   1178: aload 6
        //   1180: ldc_w 302
        //   1183: invokespecial 304	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
        //   1186: aload 10
        //   1188: astore 7
        //   1190: aload 13
        //   1192: astore 5
        //   1194: aload 16
        //   1196: invokestatic 219	com/explodingbarrel/nanigans/Manager$NanigansEventParameter:access$100	(Lcom/explodingbarrel/nanigans/Manager$NanigansEventParameter;)[Ljava/lang/String;
        //   1199: arraylength
        //   1200: iconst_1
        //   1201: if_icmpne +79 -> 1280
        //   1204: aload 10
        //   1206: astore 7
        //   1208: aload 13
        //   1210: astore 5
        //   1212: aload 6
        //   1214: aload 16
        //   1216: invokestatic 215	com/explodingbarrel/nanigans/Manager$NanigansEventParameter:access$000	(Lcom/explodingbarrel/nanigans/Manager$NanigansEventParameter;)Ljava/lang/String;
        //   1219: invokevirtual 308	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1222: ldc_w 310
        //   1225: invokevirtual 308	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1228: aload 16
        //   1230: invokestatic 219	com/explodingbarrel/nanigans/Manager$NanigansEventParameter:access$100	(Lcom/explodingbarrel/nanigans/Manager$NanigansEventParameter;)[Ljava/lang/String;
        //   1233: iconst_0
        //   1234: aaload
        //   1235: ldc_w 312
        //   1238: invokestatic 317	java/net/URLEncoder:encode	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   1241: invokevirtual 308	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1244: pop
        //   1245: aload 6
        //   1247: astore 9
        //   1249: goto -196 -> 1053
        //   1252: aload 10
        //   1254: astore 7
        //   1256: aload 13
        //   1258: astore 5
        //   1260: aload 14
        //   1262: astore 8
        //   1264: aload 9
        //   1266: ldc_w 319
        //   1269: invokevirtual 308	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1272: pop
        //   1273: aload 9
        //   1275: astore 6
        //   1277: goto -91 -> 1186
        //   1280: iconst_0
        //   1281: istore_1
        //   1282: aload 10
        //   1284: astore 7
        //   1286: aload 13
        //   1288: astore 5
        //   1290: iload_1
        //   1291: aload 16
        //   1293: invokestatic 219	com/explodingbarrel/nanigans/Manager$NanigansEventParameter:access$100	(Lcom/explodingbarrel/nanigans/Manager$NanigansEventParameter;)[Ljava/lang/String;
        //   1296: arraylength
        //   1297: if_icmpge -52 -> 1245
        //   1300: iload_1
        //   1301: ifeq +20 -> 1321
        //   1304: aload 10
        //   1306: astore 7
        //   1308: aload 13
        //   1310: astore 5
        //   1312: aload 6
        //   1314: ldc_w 319
        //   1317: invokevirtual 308	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1320: pop
        //   1321: aload 10
        //   1323: astore 7
        //   1325: aload 13
        //   1327: astore 5
        //   1329: aload 6
        //   1331: aload 16
        //   1333: invokestatic 215	com/explodingbarrel/nanigans/Manager$NanigansEventParameter:access$000	(Lcom/explodingbarrel/nanigans/Manager$NanigansEventParameter;)Ljava/lang/String;
        //   1336: invokevirtual 308	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1339: ldc_w 321
        //   1342: invokevirtual 308	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1345: iload_1
        //   1346: invokevirtual 324	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
        //   1349: ldc_w 326
        //   1352: invokevirtual 308	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1355: aload 16
        //   1357: invokestatic 219	com/explodingbarrel/nanigans/Manager$NanigansEventParameter:access$100	(Lcom/explodingbarrel/nanigans/Manager$NanigansEventParameter;)[Ljava/lang/String;
        //   1360: iload_1
        //   1361: aaload
        //   1362: ldc_w 312
        //   1365: invokestatic 317	java/net/URLEncoder:encode	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   1368: invokevirtual 308	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1371: pop
        //   1372: iinc 1 1
        //   1375: goto -93 -> 1282
        //   1378: aload 10
        //   1380: astore 7
        //   1382: aload 13
        //   1384: astore 5
        //   1386: aload 14
        //   1388: astore 8
        //   1390: new 328	java/net/URL
        //   1393: astore 6
        //   1395: aload 10
        //   1397: astore 7
        //   1399: aload 13
        //   1401: astore 5
        //   1403: aload 14
        //   1405: astore 8
        //   1407: aload 6
        //   1409: aload 9
        //   1411: invokevirtual 329	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   1414: invokespecial 330	java/net/URL:<init>	(Ljava/lang/String;)V
        //   1417: aload 10
        //   1419: astore 7
        //   1421: aload 13
        //   1423: astore 5
        //   1425: aload 14
        //   1427: astore 8
        //   1429: aload 6
        //   1431: invokevirtual 334	java/net/URL:openStream	()Ljava/io/InputStream;
        //   1434: astore 6
        //   1436: aload 10
        //   1438: astore 7
        //   1440: aload 6
        //   1442: astore 5
        //   1444: aload 6
        //   1446: astore 8
        //   1448: new 336	java/io/ByteArrayOutputStream
        //   1451: dup
        //   1452: invokespecial 337	java/io/ByteArrayOutputStream:<init>	()V
        //   1455: astore 10
        //   1457: sipush 1024
        //   1460: newarray <illegal type>
        //   1462: astore 5
        //   1464: aload 6
        //   1466: aload 5
        //   1468: invokevirtual 343	java/io/InputStream:read	([B)I
        //   1471: iconst_m1
        //   1472: if_icmpeq +79 -> 1551
        //   1475: aload 10
        //   1477: aload 5
        //   1479: invokevirtual 347	java/io/ByteArrayOutputStream:write	([B)V
        //   1482: goto -18 -> 1464
        //   1485: astore 5
        //   1487: aload 10
        //   1489: astore 9
        //   1491: aload 5
        //   1493: astore 10
        //   1495: aload 9
        //   1497: astore 7
        //   1499: aload 6
        //   1501: astore 5
        //   1503: aload 10
        //   1505: invokevirtual 164	java/lang/Exception:printStackTrace	()V
        //   1508: aconst_null
        //   1509: astore 7
        //   1511: aload 6
        //   1513: ifnull +8 -> 1521
        //   1516: aload 6
        //   1518: invokevirtual 350	java/io/InputStream:close	()V
        //   1521: aload 7
        //   1523: astore 5
        //   1525: aload 9
        //   1527: ifnull -1342 -> 185
        //   1530: aload 9
        //   1532: invokevirtual 351	java/io/ByteArrayOutputStream:close	()V
        //   1535: aload 7
        //   1537: astore 5
        //   1539: goto -1354 -> 185
        //   1542: astore 5
        //   1544: aload 7
        //   1546: astore 5
        //   1548: goto -1363 -> 185
        //   1551: getstatic 153	java/lang/System:out	Ljava/io/PrintStream;
        //   1554: astore 5
        //   1556: new 300	java/lang/StringBuilder
        //   1559: astore 7
        //   1561: aload 7
        //   1563: invokespecial 352	java/lang/StringBuilder:<init>	()V
        //   1566: aload 5
        //   1568: aload 7
        //   1570: ldc_w 354
        //   1573: invokevirtual 308	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1576: aload 9
        //   1578: invokevirtual 329	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   1581: invokevirtual 308	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1584: ldc_w 356
        //   1587: invokevirtual 308	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1590: aload 10
        //   1592: ldc_w 312
        //   1595: invokevirtual 359	java/io/ByteArrayOutputStream:toString	(Ljava/lang/String;)Ljava/lang/String;
        //   1598: invokevirtual 308	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1601: invokevirtual 329	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   1604: invokevirtual 161	java/io/PrintStream:println	(Ljava/lang/String;)V
        //   1607: aload 9
        //   1609: invokevirtual 329	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   1612: astore 7
        //   1614: aload 6
        //   1616: ifnull +8 -> 1624
        //   1619: aload 6
        //   1621: invokevirtual 350	java/io/InputStream:close	()V
        //   1624: aload 7
        //   1626: astore 5
        //   1628: aload 10
        //   1630: ifnull -1445 -> 185
        //   1633: aload 10
        //   1635: invokevirtual 351	java/io/ByteArrayOutputStream:close	()V
        //   1638: aload 7
        //   1640: astore 5
        //   1642: goto -1457 -> 185
        //   1645: astore 5
        //   1647: aload 7
        //   1649: astore 5
        //   1651: goto -1466 -> 185
        //   1654: astore 8
        //   1656: aload 5
        //   1658: astore 6
        //   1660: aload 8
        //   1662: astore 5
        //   1664: aload 6
        //   1666: ifnull +8 -> 1674
        //   1669: aload 6
        //   1671: invokevirtual 350	java/io/InputStream:close	()V
        //   1674: aload 7
        //   1676: ifnull +8 -> 1684
        //   1679: aload 7
        //   1681: invokevirtual 351	java/io/ByteArrayOutputStream:close	()V
        //   1684: aload 5
        //   1686: athrow
        //   1687: astore 5
        //   1689: goto -65 -> 1624
        //   1692: astore 5
        //   1694: goto -173 -> 1521
        //   1697: astore 6
        //   1699: goto -25 -> 1674
        //   1702: astore 6
        //   1704: goto -20 -> 1684
        //   1707: astore 5
        //   1709: aload 10
        //   1711: astore 7
        //   1713: goto -49 -> 1664
        //   1716: astore 10
        //   1718: aload 11
        //   1720: astore 9
        //   1722: aload 12
        //   1724: astore 6
        //   1726: goto -231 -> 1495
        //   1729: astore 10
        //   1731: aload 11
        //   1733: astore 9
        //   1735: aload 8
        //   1737: astore 6
        //   1739: goto -244 -> 1495
        //   1742: astore 5
        //   1744: goto -1667 -> 77
        //   1747: astore 6
        //   1749: aload 5
        //   1751: astore 8
        //   1753: goto -1696 -> 57
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	1756	0	this	1RequestCallable
        //   341	1032	1	i	int
        //   376	9	2	j	int
        //   359	183	3	bool1	boolean
        //   356	205	4	bool2	boolean
        //   15	171	5	localObject1	Object
        //   188	3	5	localException1	Exception
        //   227	266	5	localObject2	Object
        //   497	11	5	localException2	Exception
        //   514	964	5	localObject3	Object
        //   1485	7	5	localException3	Exception
        //   1501	37	5	localObject4	Object
        //   1542	1	5	localException4	Exception
        //   1546	95	5	localObject5	Object
        //   1645	1	5	localException5	Exception
        //   1649	36	5	localObject6	Object
        //   1687	1	5	localException6	Exception
        //   1692	1	5	localException7	Exception
        //   1707	1	5	localObject7	Object
        //   1742	8	5	localException8	Exception
        //   87	1583	6	localObject8	Object
        //   1697	1	6	localException9	Exception
        //   1702	1	6	localException10	Exception
        //   1724	14	6	localObject9	Object
        //   1747	1	6	localException11	Exception
        //   1	1711	7	localObject10	Object
        //   26	1421	8	localObject11	Object
        //   1654	82	8	localObject12	Object
        //   1751	1	8	localException12	Exception
        //   58	1676	9	localObject13	Object
        //   103	1607	10	localObject14	Object
        //   1716	1	10	localException13	Exception
        //   1729	1	10	localException14	Exception
        //   236	1496	11	localObject15	Object
        //   233	1490	12	arrayOfNanigansEventParameter	Manager.NanigansEventParameter[]
        //   1019	403	13	localObject16	Object
        //   1022	404	14	localObject17	Object
        //   1048	40	15	localIterator	java.util.Iterator
        //   1097	259	16	localNanigansEventParameter	Manager.NanigansEventParameter
        // Exception table:
        //   from	to	target	type
        //   3	17	188	java/lang/Exception
        //   270	298	497	java/lang/Exception
        //   307	317	497	java/lang/Exception
        //   321	329	497	java/lang/Exception
        //   436	454	497	java/lang/Exception
        //   467	478	497	java/lang/Exception
        //   482	490	497	java/lang/Exception
        //   1457	1464	1485	java/lang/Exception
        //   1464	1482	1485	java/lang/Exception
        //   1551	1614	1485	java/lang/Exception
        //   1530	1535	1542	java/lang/Exception
        //   1633	1638	1645	java/lang/Exception
        //   1041	1050	1654	finally
        //   1065	1075	1654	finally
        //   1087	1099	1654	finally
        //   1116	1124	1654	finally
        //   1136	1144	1654	finally
        //   1161	1166	1654	finally
        //   1178	1186	1654	finally
        //   1194	1204	1654	finally
        //   1212	1245	1654	finally
        //   1264	1273	1654	finally
        //   1290	1300	1654	finally
        //   1312	1321	1654	finally
        //   1329	1372	1654	finally
        //   1390	1395	1654	finally
        //   1407	1417	1654	finally
        //   1429	1436	1654	finally
        //   1448	1457	1654	finally
        //   1503	1508	1654	finally
        //   1619	1624	1687	java/lang/Exception
        //   1516	1521	1692	java/lang/Exception
        //   1669	1674	1697	java/lang/Exception
        //   1679	1684	1702	java/lang/Exception
        //   1457	1464	1707	finally
        //   1464	1482	1707	finally
        //   1551	1614	1707	finally
        //   1041	1050	1716	java/lang/Exception
        //   1194	1204	1716	java/lang/Exception
        //   1212	1245	1716	java/lang/Exception
        //   1290	1300	1716	java/lang/Exception
        //   1312	1321	1716	java/lang/Exception
        //   1329	1372	1716	java/lang/Exception
        //   1065	1075	1729	java/lang/Exception
        //   1087	1099	1729	java/lang/Exception
        //   1116	1124	1729	java/lang/Exception
        //   1136	1144	1729	java/lang/Exception
        //   1161	1166	1729	java/lang/Exception
        //   1178	1186	1729	java/lang/Exception
        //   1264	1273	1729	java/lang/Exception
        //   1390	1395	1729	java/lang/Exception
        //   1407	1417	1729	java/lang/Exception
        //   1429	1436	1729	java/lang/Exception
        //   1448	1457	1729	java/lang/Exception
        //   60	73	1742	java/lang/Exception
        //   28	57	1747	java/lang/Exception
      }
    });
  }
  
  public class NanigansEventParameter
  {
    private final String name;
    private final String[] value;
    
    public NanigansEventParameter(String paramString, String... paramVarArgs)
    {
      this.name = paramString;
      this.value = paramVarArgs;
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\explodingbarrel\nanigans\Manager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */