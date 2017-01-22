package com.mobileapptracker;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.location.Location;
import org.json.JSONArray;

public class MATParameters
{
  private static MATParameters c = null;
  private String A = null;
  private String B = null;
  private String C = null;
  private String D = null;
  private String E = null;
  private String F = null;
  private String G = null;
  private String H = null;
  private String I = null;
  private String J = null;
  private Location K = null;
  private String L = null;
  private String M = null;
  private String N = null;
  private String O = null;
  private String P = null;
  private String Q = null;
  private String R;
  private String S;
  private String T;
  private String U = null;
  private String V = null;
  private String W = null;
  private String X = null;
  private String Y = null;
  private String Z = null;
  private Context a;
  private String aa = null;
  private String ab = null;
  private String ac = null;
  private String ad = null;
  private String ae = null;
  private String af = null;
  private String ag = null;
  private String ah = null;
  private String ai = null;
  private String aj;
  private String ak;
  private String al;
  private JSONArray am = null;
  private String an;
  private String ao;
  private String ap;
  private MobileAppTracker b;
  private String d = null;
  private String e = null;
  private String f = null;
  private String g = null;
  private String h = null;
  private String i = null;
  private String j = null;
  private String k = null;
  private String l = null;
  private String m = null;
  private String n = null;
  private String o = null;
  private String p = null;
  private String q = null;
  private String r = null;
  private String s = null;
  private String t = null;
  private String u = null;
  private String v = null;
  private String w = null;
  private String x = null;
  private String y = null;
  private String z = null;
  
  private String a(String paramString)
  {
    try
    {
      paramString = this.a.getSharedPreferences("com.mobileapptracking", 0).getString(paramString, "");
      return paramString;
    }
    finally
    {
      paramString = finally;
      throw paramString;
    }
  }
  
  private void a(String paramString1, String paramString2)
  {
    try
    {
      this.a.getSharedPreferences("com.mobileapptracking", 0).edit().putString(paramString1, paramString2).commit();
      return;
    }
    finally
    {
      paramString1 = finally;
      throw paramString1;
    }
  }
  
  /* Error */
  private boolean a(Context paramContext, String paramString1, String paramString2)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: aload_2
    //   4: invokevirtual 249	java/lang/String:trim	()Ljava/lang/String;
    //   7: invokevirtual 253	com/mobileapptracker/MATParameters:setAdvertiserId	(Ljava/lang/String;)V
    //   10: aload_0
    //   11: aload_3
    //   12: invokevirtual 249	java/lang/String:trim	()Ljava/lang/String;
    //   15: invokevirtual 256	com/mobileapptracker/MATParameters:setConversionKey	(Ljava/lang/String;)V
    //   18: aload_0
    //   19: ldc_w 258
    //   22: invokevirtual 261	com/mobileapptracker/MATParameters:setCurrencyCode	(Ljava/lang/String;)V
    //   25: new 263	java/lang/Thread
    //   28: astore_3
    //   29: new 265	com/mobileapptracker/d
    //   32: astore_2
    //   33: aload_2
    //   34: aload_0
    //   35: aload_1
    //   36: invokespecial 268	com/mobileapptracker/d:<init>	(Lcom/mobileapptracker/MATParameters;Landroid/content/Context;)V
    //   39: aload_3
    //   40: aload_2
    //   41: invokespecial 271	java/lang/Thread:<init>	(Ljava/lang/Runnable;)V
    //   44: aload_3
    //   45: invokevirtual 274	java/lang/Thread:start	()V
    //   48: new 276	android/os/Handler
    //   51: astore_2
    //   52: aload_2
    //   53: invokestatic 282	android/os/Looper:getMainLooper	()Landroid/os/Looper;
    //   56: invokespecial 285	android/os/Handler:<init>	(Landroid/os/Looper;)V
    //   59: new 287	com/mobileapptracker/e
    //   62: astore_3
    //   63: aload_3
    //   64: aload_0
    //   65: aload_0
    //   66: getfield 204	com/mobileapptracker/MATParameters:a	Landroid/content/Context;
    //   69: invokespecial 288	com/mobileapptracker/e:<init>	(Lcom/mobileapptracker/MATParameters;Landroid/content/Context;)V
    //   72: aload_2
    //   73: aload_3
    //   74: invokevirtual 292	android/os/Handler:post	(Ljava/lang/Runnable;)Z
    //   77: pop
    //   78: aload_0
    //   79: getfield 204	com/mobileapptracker/MATParameters:a	Landroid/content/Context;
    //   82: invokevirtual 295	android/content/Context:getPackageName	()Ljava/lang/String;
    //   85: astore_3
    //   86: aload_0
    //   87: aload_3
    //   88: invokevirtual 298	com/mobileapptracker/MATParameters:setPackageName	(Ljava/lang/String;)V
    //   91: aload_0
    //   92: getfield 204	com/mobileapptracker/MATParameters:a	Landroid/content/Context;
    //   95: invokevirtual 302	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   98: astore_2
    //   99: aload_0
    //   100: aload_2
    //   101: aload_2
    //   102: aload_3
    //   103: iconst_0
    //   104: invokevirtual 308	android/content/pm/PackageManager:getApplicationInfo	(Ljava/lang/String;I)Landroid/content/pm/ApplicationInfo;
    //   107: invokevirtual 312	android/content/pm/PackageManager:getApplicationLabel	(Landroid/content/pm/ApplicationInfo;)Ljava/lang/CharSequence;
    //   110: invokeinterface 317 1 0
    //   115: invokevirtual 320	com/mobileapptracker/MATParameters:setAppName	(Ljava/lang/String;)V
    //   118: aload_2
    //   119: aload_3
    //   120: iconst_0
    //   121: invokevirtual 308	android/content/pm/PackageManager:getApplicationInfo	(Ljava/lang/String;I)Landroid/content/pm/ApplicationInfo;
    //   124: getfield 325	android/content/pm/ApplicationInfo:sourceDir	Ljava/lang/String;
    //   127: astore 9
    //   129: new 327	java/io/File
    //   132: astore 10
    //   134: aload 10
    //   136: aload 9
    //   138: invokespecial 329	java/io/File:<init>	(Ljava/lang/String;)V
    //   141: aload 10
    //   143: invokevirtual 333	java/io/File:lastModified	()J
    //   146: lstore 6
    //   148: new 335	java/util/Date
    //   151: astore 9
    //   153: aload 9
    //   155: lload 6
    //   157: invokespecial 338	java/util/Date:<init>	(J)V
    //   160: aload_0
    //   161: aload 9
    //   163: invokevirtual 341	java/util/Date:getTime	()J
    //   166: ldc2_w 342
    //   169: ldiv
    //   170: invokestatic 348	java/lang/Long:toString	(J)Ljava/lang/String;
    //   173: invokevirtual 351	com/mobileapptracker/MATParameters:setInstallDate	(Ljava/lang/String;)V
    //   176: aload_2
    //   177: aload_3
    //   178: iconst_0
    //   179: invokevirtual 355	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   182: astore 9
    //   184: aload_0
    //   185: aload 9
    //   187: getfield 360	android/content/pm/PackageInfo:versionCode	I
    //   190: invokestatic 365	java/lang/Integer:toString	(I)Ljava/lang/String;
    //   193: invokevirtual 368	com/mobileapptracker/MATParameters:setAppVersion	(Ljava/lang/String;)V
    //   196: aload_0
    //   197: aload 9
    //   199: getfield 371	android/content/pm/PackageInfo:versionName	Ljava/lang/String;
    //   202: invokevirtual 374	com/mobileapptracker/MATParameters:setAppVersionName	(Ljava/lang/String;)V
    //   205: aload_0
    //   206: aload_2
    //   207: aload_3
    //   208: invokevirtual 377	android/content/pm/PackageManager:getInstallerPackageName	(Ljava/lang/String;)Ljava/lang/String;
    //   211: invokevirtual 380	com/mobileapptracker/MATParameters:setInstaller	(Ljava/lang/String;)V
    //   214: aload_0
    //   215: getstatic 385	android/os/Build:MODEL	Ljava/lang/String;
    //   218: invokevirtual 388	com/mobileapptracker/MATParameters:setDeviceModel	(Ljava/lang/String;)V
    //   221: aload_0
    //   222: getstatic 391	android/os/Build:MANUFACTURER	Ljava/lang/String;
    //   225: invokevirtual 394	com/mobileapptracker/MATParameters:setDeviceBrand	(Ljava/lang/String;)V
    //   228: aload_0
    //   229: ldc_w 396
    //   232: invokestatic 401	java/lang/System:getProperty	(Ljava/lang/String;)Ljava/lang/String;
    //   235: invokevirtual 404	com/mobileapptracker/MATParameters:setDeviceCpuType	(Ljava/lang/String;)V
    //   238: aload_0
    //   239: getstatic 409	android/os/Build$VERSION:RELEASE	Ljava/lang/String;
    //   242: invokevirtual 412	com/mobileapptracker/MATParameters:setOsVersion	(Ljava/lang/String;)V
    //   245: aload_0
    //   246: aload_1
    //   247: invokevirtual 416	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   250: invokevirtual 422	android/content/res/Resources:getDisplayMetrics	()Landroid/util/DisplayMetrics;
    //   253: getfield 427	android/util/DisplayMetrics:density	F
    //   256: invokestatic 432	java/lang/Float:toString	(F)Ljava/lang/String;
    //   259: invokevirtual 435	com/mobileapptracker/MATParameters:setScreenDensity	(Ljava/lang/String;)V
    //   262: aload_1
    //   263: ldc_w 437
    //   266: invokevirtual 441	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   269: checkcast 443	android/view/WindowManager
    //   272: astore_1
    //   273: getstatic 446	android/os/Build$VERSION:SDK_INT	I
    //   276: bipush 13
    //   278: if_icmplt +234 -> 512
    //   281: new 448	android/graphics/Point
    //   284: astore_2
    //   285: aload_2
    //   286: invokespecial 449	android/graphics/Point:<init>	()V
    //   289: aload_1
    //   290: invokeinterface 453 1 0
    //   295: aload_2
    //   296: invokevirtual 459	android/view/Display:getSize	(Landroid/graphics/Point;)V
    //   299: aload_2
    //   300: getfield 461	android/graphics/Point:x	I
    //   303: istore 4
    //   305: aload_2
    //   306: getfield 463	android/graphics/Point:y	I
    //   309: istore 5
    //   311: aload_0
    //   312: iload 4
    //   314: invokestatic 365	java/lang/Integer:toString	(I)Ljava/lang/String;
    //   317: invokevirtual 466	com/mobileapptracker/MATParameters:setScreenWidth	(Ljava/lang/String;)V
    //   320: aload_0
    //   321: iload 5
    //   323: invokestatic 365	java/lang/Integer:toString	(I)Ljava/lang/String;
    //   326: invokevirtual 469	com/mobileapptracker/MATParameters:setScreenHeight	(Ljava/lang/String;)V
    //   329: aload_0
    //   330: getfield 204	com/mobileapptracker/MATParameters:a	Landroid/content/Context;
    //   333: ldc_w 471
    //   336: invokevirtual 441	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   339: checkcast 473	android/net/ConnectivityManager
    //   342: iconst_1
    //   343: invokevirtual 477	android/net/ConnectivityManager:getNetworkInfo	(I)Landroid/net/NetworkInfo;
    //   346: invokevirtual 482	android/net/NetworkInfo:isConnected	()Z
    //   349: ifeq +188 -> 537
    //   352: aload_0
    //   353: ldc_w 484
    //   356: invokevirtual 487	com/mobileapptracker/MATParameters:setConnectionType	(Ljava/lang/String;)V
    //   359: aload_0
    //   360: invokestatic 493	java/util/Locale:getDefault	()Ljava/util/Locale;
    //   363: getstatic 497	java/util/Locale:US	Ljava/util/Locale;
    //   366: invokevirtual 501	java/util/Locale:getDisplayLanguage	(Ljava/util/Locale;)Ljava/lang/String;
    //   369: invokevirtual 504	com/mobileapptracker/MATParameters:setLanguage	(Ljava/lang/String;)V
    //   372: aload_0
    //   373: getfield 204	com/mobileapptracker/MATParameters:a	Landroid/content/Context;
    //   376: ldc_w 506
    //   379: invokevirtual 441	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   382: checkcast 508	android/telephony/TelephonyManager
    //   385: astore_1
    //   386: aload_1
    //   387: ifnull +165 -> 552
    //   390: aload_1
    //   391: invokevirtual 511	android/telephony/TelephonyManager:getNetworkCountryIso	()Ljava/lang/String;
    //   394: ifnull +11 -> 405
    //   397: aload_0
    //   398: aload_1
    //   399: invokevirtual 511	android/telephony/TelephonyManager:getNetworkCountryIso	()Ljava/lang/String;
    //   402: invokevirtual 514	com/mobileapptracker/MATParameters:setCountryCode	(Ljava/lang/String;)V
    //   405: aload_0
    //   406: aload_1
    //   407: invokevirtual 517	android/telephony/TelephonyManager:getNetworkOperatorName	()Ljava/lang/String;
    //   410: invokevirtual 520	com/mobileapptracker/MATParameters:setDeviceCarrier	(Ljava/lang/String;)V
    //   413: aload_1
    //   414: invokevirtual 523	android/telephony/TelephonyManager:getNetworkOperator	()Ljava/lang/String;
    //   417: astore_2
    //   418: aload_2
    //   419: ifnull +26 -> 445
    //   422: aload_2
    //   423: iconst_0
    //   424: iconst_3
    //   425: invokevirtual 527	java/lang/String:substring	(II)Ljava/lang/String;
    //   428: astore_1
    //   429: aload_2
    //   430: iconst_3
    //   431: invokevirtual 529	java/lang/String:substring	(I)Ljava/lang/String;
    //   434: astore_2
    //   435: aload_0
    //   436: aload_1
    //   437: invokevirtual 532	com/mobileapptracker/MATParameters:setMCC	(Ljava/lang/String;)V
    //   440: aload_0
    //   441: aload_2
    //   442: invokevirtual 535	com/mobileapptracker/MATParameters:setMNC	(Ljava/lang/String;)V
    //   445: aload_0
    //   446: invokevirtual 538	com/mobileapptracker/MATParameters:getMatId	()Ljava/lang/String;
    //   449: astore_1
    //   450: aload_1
    //   451: ifnull +10 -> 461
    //   454: aload_1
    //   455: invokevirtual 542	java/lang/String:length	()I
    //   458: ifne +13 -> 471
    //   461: aload_0
    //   462: invokestatic 548	java/util/UUID:randomUUID	()Ljava/util/UUID;
    //   465: invokevirtual 549	java/util/UUID:toString	()Ljava/lang/String;
    //   468: invokevirtual 552	com/mobileapptracker/MATParameters:setMatId	(Ljava/lang/String;)V
    //   471: iconst_1
    //   472: istore 8
    //   474: aload_0
    //   475: monitorexit
    //   476: iload 8
    //   478: ireturn
    //   479: astore 9
    //   481: aload_0
    //   482: ldc_w 554
    //   485: invokevirtual 368	com/mobileapptracker/MATParameters:setAppVersion	(Ljava/lang/String;)V
    //   488: goto -283 -> 205
    //   491: astore_1
    //   492: ldc_w 556
    //   495: ldc_w 558
    //   498: invokestatic 563	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   501: pop
    //   502: aload_1
    //   503: invokevirtual 566	java/lang/Exception:printStackTrace	()V
    //   506: iconst_0
    //   507: istore 8
    //   509: goto -35 -> 474
    //   512: aload_1
    //   513: invokeinterface 453 1 0
    //   518: invokevirtual 569	android/view/Display:getWidth	()I
    //   521: istore 4
    //   523: aload_1
    //   524: invokeinterface 453 1 0
    //   529: invokevirtual 572	android/view/Display:getHeight	()I
    //   532: istore 5
    //   534: goto -223 -> 311
    //   537: aload_0
    //   538: ldc_w 574
    //   541: invokevirtual 487	com/mobileapptracker/MATParameters:setConnectionType	(Ljava/lang/String;)V
    //   544: goto -185 -> 359
    //   547: astore_1
    //   548: aload_0
    //   549: monitorexit
    //   550: aload_1
    //   551: athrow
    //   552: aload_0
    //   553: invokestatic 493	java/util/Locale:getDefault	()Ljava/util/Locale;
    //   556: invokevirtual 577	java/util/Locale:getCountry	()Ljava/lang/String;
    //   559: invokevirtual 514	com/mobileapptracker/MATParameters:setCountryCode	(Ljava/lang/String;)V
    //   562: goto -117 -> 445
    //   565: astore_1
    //   566: goto -121 -> 445
    //   569: astore 9
    //   571: goto -395 -> 176
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	574	0	this	MATParameters
    //   0	574	1	paramContext	Context
    //   0	574	2	paramString1	String
    //   0	574	3	paramString2	String
    //   303	219	4	i1	int
    //   309	224	5	i2	int
    //   146	10	6	l1	long
    //   472	36	8	bool	boolean
    //   127	71	9	localObject	Object
    //   479	1	9	localNameNotFoundException1	android.content.pm.PackageManager.NameNotFoundException
    //   569	1	9	localNameNotFoundException2	android.content.pm.PackageManager.NameNotFoundException
    //   132	10	10	localFile	java.io.File
    // Exception table:
    //   from	to	target	type
    //   176	205	479	android/content/pm/PackageManager$NameNotFoundException
    //   2	99	491	java/lang/Exception
    //   99	176	491	java/lang/Exception
    //   176	205	491	java/lang/Exception
    //   205	311	491	java/lang/Exception
    //   311	359	491	java/lang/Exception
    //   359	386	491	java/lang/Exception
    //   390	405	491	java/lang/Exception
    //   405	418	491	java/lang/Exception
    //   422	445	491	java/lang/Exception
    //   445	450	491	java/lang/Exception
    //   454	461	491	java/lang/Exception
    //   461	471	491	java/lang/Exception
    //   481	488	491	java/lang/Exception
    //   512	534	491	java/lang/Exception
    //   537	544	491	java/lang/Exception
    //   552	562	491	java/lang/Exception
    //   2	99	547	finally
    //   99	176	547	finally
    //   176	205	547	finally
    //   205	311	547	finally
    //   311	359	547	finally
    //   359	386	547	finally
    //   390	405	547	finally
    //   405	418	547	finally
    //   422	445	547	finally
    //   445	450	547	finally
    //   454	461	547	finally
    //   461	471	547	finally
    //   481	488	547	finally
    //   492	506	547	finally
    //   512	534	547	finally
    //   537	544	547	finally
    //   552	562	547	finally
    //   422	445	565	java/lang/IndexOutOfBoundsException
    //   99	176	569	android/content/pm/PackageManager$NameNotFoundException
  }
  
  public static MATParameters getInstance()
  {
    return c;
  }
  
  public static MATParameters init(MobileAppTracker paramMobileAppTracker, Context paramContext, String paramString1, String paramString2)
  {
    if (c == null)
    {
      MATParameters localMATParameters = new MATParameters();
      c = localMATParameters;
      localMATParameters.b = paramMobileAppTracker;
      c.a = paramContext;
      c.a(paramContext, paramString1, paramString2);
    }
    return c;
  }
  
  public void clear()
  {
    c = null;
  }
  
  public String getAction()
  {
    try
    {
      String str = this.d;
      return str;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public String getAdvertiserId()
  {
    try
    {
      String str = this.e;
      return str;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public String getAge()
  {
    try
    {
      String str = this.f;
      return str;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public String getAllowDuplicates()
  {
    try
    {
      String str = this.g;
      return str;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public String getAltitude()
  {
    try
    {
      String str = this.h;
      return str;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public String getAndroidId()
  {
    try
    {
      String str = this.i;
      return str;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public String getAndroidIdMd5()
  {
    try
    {
      String str = this.j;
      return str;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public String getAndroidIdSha1()
  {
    try
    {
      String str = this.k;
      return str;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public String getAndroidIdSha256()
  {
    try
    {
      String str = this.l;
      return str;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public String getAppAdTrackingEnabled()
  {
    try
    {
      String str = this.m;
      return str;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public String getAppName()
  {
    try
    {
      String str = this.n;
      return str;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public String getAppVersion()
  {
    try
    {
      String str = this.o;
      return str;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public String getAppVersionName()
  {
    try
    {
      String str = this.p;
      return str;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public String getConnectionType()
  {
    try
    {
      String str = this.q;
      return str;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public String getConversionKey()
  {
    try
    {
      String str = this.r;
      return str;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public String getCountryCode()
  {
    try
    {
      String str = this.s;
      return str;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public String getCurrencyCode()
  {
    try
    {
      String str = this.t;
      return str;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public String getDeviceBrand()
  {
    try
    {
      String str = this.u;
      return str;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public String getDeviceCarrier()
  {
    try
    {
      String str = this.v;
      return str;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public String getDeviceCpuSubtype()
  {
    try
    {
      String str = this.x;
      return str;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public String getDeviceCpuType()
  {
    try
    {
      String str = this.w;
      return str;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public String getDeviceId()
  {
    try
    {
      String str = this.y;
      return str;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public String getDeviceModel()
  {
    try
    {
      String str = this.z;
      return str;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public String getExistingUser()
  {
    try
    {
      String str = this.A;
      return str;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public String getFacebookUserId()
  {
    try
    {
      String str = this.B;
      return str;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public String getGender()
  {
    try
    {
      String str = this.C;
      return str;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public String getGoogleAdTrackingLimited()
  {
    try
    {
      String str = this.E;
      return str;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public String getGoogleAdvertisingId()
  {
    try
    {
      String str = this.D;
      return str;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public String getGoogleUserId()
  {
    try
    {
      String str = this.F;
      return str;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public String getInstallDate()
  {
    try
    {
      String str = this.G;
      return str;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public String getInstallReferrer()
  {
    try
    {
      String str = a("mat_referrer");
      return str;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public String getInstaller()
  {
    try
    {
      String str = this.H;
      return str;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public String getIsPayingUser()
  {
    try
    {
      String str = a("mat_is_paying_user");
      return str;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public String getLanguage()
  {
    try
    {
      String str = this.I;
      return str;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public String getLastOpenLogId()
  {
    try
    {
      String str = a("mat_log_id_last_open");
      return str;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public String getLatitude()
  {
    try
    {
      String str = this.J;
      return str;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public Location getLocation()
  {
    try
    {
      Location localLocation = this.K;
      return localLocation;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public String getLongitude()
  {
    try
    {
      String str = this.L;
      return str;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public String getMCC()
  {
    try
    {
      String str = this.N;
      return str;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public String getMNC()
  {
    try
    {
      String str = this.O;
      return str;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public String getMacAddress()
  {
    try
    {
      String str = this.M;
      return str;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  /* Error */
  public String getMatId()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 204	com/mobileapptracker/MATParameters:a	Landroid/content/Context;
    //   6: ldc_w 637
    //   9: iconst_0
    //   10: invokevirtual 212	android/content/Context:getSharedPreferences	(Ljava/lang/String;I)Landroid/content/SharedPreferences;
    //   13: ldc_w 637
    //   16: invokeinterface 641 2 0
    //   21: ifeq +29 -> 50
    //   24: aload_0
    //   25: getfield 204	com/mobileapptracker/MATParameters:a	Landroid/content/Context;
    //   28: ldc_w 637
    //   31: iconst_0
    //   32: invokevirtual 212	android/content/Context:getSharedPreferences	(Ljava/lang/String;I)Landroid/content/SharedPreferences;
    //   35: ldc_w 637
    //   38: ldc -42
    //   40: invokeinterface 220 3 0
    //   45: astore_1
    //   46: aload_0
    //   47: monitorexit
    //   48: aload_1
    //   49: areturn
    //   50: aload_0
    //   51: ldc_w 637
    //   54: invokespecial 620	com/mobileapptracker/MATParameters:a	(Ljava/lang/String;)Ljava/lang/String;
    //   57: astore_1
    //   58: goto -12 -> 46
    //   61: astore_1
    //   62: aload_0
    //   63: monitorexit
    //   64: aload_1
    //   65: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	66	0	this	MATParameters
    //   45	13	1	str	String
    //   61	4	1	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   2	46	61	finally
    //   50	58	61	finally
  }
  
  public String getOpenLogId()
  {
    try
    {
      String str = a("mat_log_id_open");
      return str;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public String getOsVersion()
  {
    try
    {
      String str = this.P;
      return str;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public String getPackageName()
  {
    try
    {
      String str = this.Q;
      return str;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public String getPhoneNumber()
  {
    try
    {
      String str = a("mat_phone_number");
      return str;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public String getPhoneNumberMd5()
  {
    try
    {
      String str = this.R;
      return str;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public String getPhoneNumberSha1()
  {
    try
    {
      String str = this.S;
      return str;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public String getPhoneNumberSha256()
  {
    try
    {
      String str = this.T;
      return str;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public String getPluginName()
  {
    try
    {
      String str = this.U;
      return str;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public String getPurchaseStatus()
  {
    try
    {
      String str = this.V;
      return str;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public String getRefId()
  {
    try
    {
      String str = this.Z;
      return str;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public String getReferralSource()
  {
    try
    {
      String str = this.W;
      return str;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public String getReferralUrl()
  {
    try
    {
      String str = this.X;
      return str;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public String getReferrerDelay()
  {
    try
    {
      String str = this.Y;
      return str;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public String getRevenue()
  {
    try
    {
      String str = this.aa;
      return str;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public String getScreenDensity()
  {
    try
    {
      String str = this.ab;
      return str;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public String getScreenHeight()
  {
    try
    {
      String str = this.ac;
      return str;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public String getScreenWidth()
  {
    try
    {
      String str = this.ad;
      return str;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public String getSdkVersion()
  {
    return "3.9.1";
  }
  
  public String getSiteId()
  {
    try
    {
      String str = this.ae;
      return str;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public String getTRUSTeId()
  {
    try
    {
      String str = this.ag;
      return str;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public String getTrackingId()
  {
    try
    {
      String str = this.af;
      return str;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public String getTwitterUserId()
  {
    try
    {
      String str = this.ah;
      return str;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public String getUserAgent()
  {
    try
    {
      String str = this.ai;
      return str;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public String getUserEmail()
  {
    try
    {
      String str = a("mat_user_email");
      return str;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public String getUserEmailMd5()
  {
    try
    {
      String str = this.aj;
      return str;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public String getUserEmailSha1()
  {
    try
    {
      String str = this.ak;
      return str;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public String getUserEmailSha256()
  {
    try
    {
      String str = this.al;
      return str;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public JSONArray getUserEmails()
  {
    try
    {
      JSONArray localJSONArray = this.am;
      return localJSONArray;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public String getUserId()
  {
    try
    {
      String str = a("mat_user_id");
      return str;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public String getUserName()
  {
    try
    {
      String str = a("mat_user_name");
      return str;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public String getUserNameMd5()
  {
    try
    {
      String str = this.an;
      return str;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public String getUserNameSha1()
  {
    try
    {
      String str = this.ao;
      return str;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public String getUserNameSha256()
  {
    try
    {
      String str = this.ap;
      return str;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public void setAction(String paramString)
  {
    try
    {
      this.d = paramString;
      return;
    }
    finally
    {
      paramString = finally;
      throw paramString;
    }
  }
  
  public void setAdvertiserId(String paramString)
  {
    try
    {
      this.e = paramString;
      return;
    }
    finally
    {
      paramString = finally;
      throw paramString;
    }
  }
  
  public void setAge(String paramString)
  {
    try
    {
      this.f = paramString;
      return;
    }
    finally
    {
      paramString = finally;
      throw paramString;
    }
  }
  
  public void setAllowDuplicates(String paramString)
  {
    try
    {
      this.g = paramString;
      return;
    }
    finally
    {
      paramString = finally;
      throw paramString;
    }
  }
  
  public void setAltitude(String paramString)
  {
    try
    {
      this.h = paramString;
      return;
    }
    finally
    {
      paramString = finally;
      throw paramString;
    }
  }
  
  public void setAndroidId(String paramString)
  {
    try
    {
      this.i = paramString;
      return;
    }
    finally
    {
      paramString = finally;
      throw paramString;
    }
  }
  
  public void setAndroidIdMd5(String paramString)
  {
    try
    {
      this.j = paramString;
      return;
    }
    finally
    {
      paramString = finally;
      throw paramString;
    }
  }
  
  public void setAndroidIdSha1(String paramString)
  {
    try
    {
      this.k = paramString;
      return;
    }
    finally
    {
      paramString = finally;
      throw paramString;
    }
  }
  
  public void setAndroidIdSha256(String paramString)
  {
    try
    {
      this.l = paramString;
      return;
    }
    finally
    {
      paramString = finally;
      throw paramString;
    }
  }
  
  public void setAppAdTrackingEnabled(String paramString)
  {
    try
    {
      this.m = paramString;
      return;
    }
    finally
    {
      paramString = finally;
      throw paramString;
    }
  }
  
  public void setAppName(String paramString)
  {
    try
    {
      this.n = paramString;
      return;
    }
    finally
    {
      paramString = finally;
      throw paramString;
    }
  }
  
  public void setAppVersion(String paramString)
  {
    try
    {
      this.o = paramString;
      return;
    }
    finally
    {
      paramString = finally;
      throw paramString;
    }
  }
  
  public void setAppVersionName(String paramString)
  {
    try
    {
      this.p = paramString;
      return;
    }
    finally
    {
      paramString = finally;
      throw paramString;
    }
  }
  
  public void setConnectionType(String paramString)
  {
    try
    {
      this.q = paramString;
      return;
    }
    finally
    {
      paramString = finally;
      throw paramString;
    }
  }
  
  public void setConversionKey(String paramString)
  {
    try
    {
      this.r = paramString;
      return;
    }
    finally
    {
      paramString = finally;
      throw paramString;
    }
  }
  
  public void setCountryCode(String paramString)
  {
    try
    {
      this.s = paramString;
      return;
    }
    finally
    {
      paramString = finally;
      throw paramString;
    }
  }
  
  public void setCurrencyCode(String paramString)
  {
    try
    {
      this.t = paramString;
      return;
    }
    finally
    {
      paramString = finally;
      throw paramString;
    }
  }
  
  public void setDeviceBrand(String paramString)
  {
    try
    {
      this.u = paramString;
      return;
    }
    finally
    {
      paramString = finally;
      throw paramString;
    }
  }
  
  public void setDeviceCarrier(String paramString)
  {
    try
    {
      this.v = paramString;
      return;
    }
    finally
    {
      paramString = finally;
      throw paramString;
    }
  }
  
  public void setDeviceCpuSubtype(String paramString)
  {
    try
    {
      this.x = paramString;
      return;
    }
    finally
    {
      paramString = finally;
      throw paramString;
    }
  }
  
  public void setDeviceCpuType(String paramString)
  {
    try
    {
      this.w = paramString;
      return;
    }
    finally
    {
      paramString = finally;
      throw paramString;
    }
  }
  
  public void setDeviceId(String paramString)
  {
    try
    {
      this.y = paramString;
      return;
    }
    finally
    {
      paramString = finally;
      throw paramString;
    }
  }
  
  public void setDeviceModel(String paramString)
  {
    try
    {
      this.z = paramString;
      return;
    }
    finally
    {
      paramString = finally;
      throw paramString;
    }
  }
  
  public void setExistingUser(String paramString)
  {
    try
    {
      this.A = paramString;
      return;
    }
    finally
    {
      paramString = finally;
      throw paramString;
    }
  }
  
  public void setFacebookUserId(String paramString)
  {
    try
    {
      this.B = paramString;
      return;
    }
    finally
    {
      paramString = finally;
      throw paramString;
    }
  }
  
  public void setGender(String paramString)
  {
    try
    {
      this.C = paramString;
      return;
    }
    finally
    {
      paramString = finally;
      throw paramString;
    }
  }
  
  public void setGoogleAdTrackingLimited(String paramString)
  {
    try
    {
      this.E = paramString;
      return;
    }
    finally
    {
      paramString = finally;
      throw paramString;
    }
  }
  
  public void setGoogleAdvertisingId(String paramString)
  {
    try
    {
      this.D = paramString;
      return;
    }
    finally
    {
      paramString = finally;
      throw paramString;
    }
  }
  
  public void setGoogleUserId(String paramString)
  {
    try
    {
      this.F = paramString;
      return;
    }
    finally
    {
      paramString = finally;
      throw paramString;
    }
  }
  
  public void setInstallDate(String paramString)
  {
    try
    {
      this.G = paramString;
      return;
    }
    finally
    {
      paramString = finally;
      throw paramString;
    }
  }
  
  public void setInstallReferrer(String paramString)
  {
    try
    {
      a("mat_referrer", paramString);
      return;
    }
    finally
    {
      paramString = finally;
      throw paramString;
    }
  }
  
  public void setInstaller(String paramString)
  {
    try
    {
      this.H = paramString;
      return;
    }
    finally
    {
      paramString = finally;
      throw paramString;
    }
  }
  
  public void setIsPayingUser(String paramString)
  {
    try
    {
      a("mat_is_paying_user", paramString);
      return;
    }
    finally
    {
      paramString = finally;
      throw paramString;
    }
  }
  
  public void setLanguage(String paramString)
  {
    try
    {
      this.I = paramString;
      return;
    }
    finally
    {
      paramString = finally;
      throw paramString;
    }
  }
  
  public void setLastOpenLogId(String paramString)
  {
    try
    {
      a("mat_log_id_last_open", paramString);
      return;
    }
    finally
    {
      paramString = finally;
      throw paramString;
    }
  }
  
  public void setLatitude(String paramString)
  {
    try
    {
      this.J = paramString;
      return;
    }
    finally
    {
      paramString = finally;
      throw paramString;
    }
  }
  
  public void setLocation(Location paramLocation)
  {
    try
    {
      this.K = paramLocation;
      return;
    }
    finally
    {
      paramLocation = finally;
      throw paramLocation;
    }
  }
  
  public void setLongitude(String paramString)
  {
    try
    {
      this.L = paramString;
      return;
    }
    finally
    {
      paramString = finally;
      throw paramString;
    }
  }
  
  public void setMCC(String paramString)
  {
    try
    {
      this.N = paramString;
      return;
    }
    finally
    {
      paramString = finally;
      throw paramString;
    }
  }
  
  public void setMNC(String paramString)
  {
    try
    {
      this.O = paramString;
      return;
    }
    finally
    {
      paramString = finally;
      throw paramString;
    }
  }
  
  public void setMacAddress(String paramString)
  {
    try
    {
      this.M = paramString;
      return;
    }
    finally
    {
      paramString = finally;
      throw paramString;
    }
  }
  
  public void setMatId(String paramString)
  {
    try
    {
      a("mat_id", paramString);
      return;
    }
    finally
    {
      paramString = finally;
      throw paramString;
    }
  }
  
  public void setOpenLogId(String paramString)
  {
    try
    {
      a("mat_log_id_open", paramString);
      return;
    }
    finally
    {
      paramString = finally;
      throw paramString;
    }
  }
  
  public void setOsVersion(String paramString)
  {
    try
    {
      this.P = paramString;
      return;
    }
    finally
    {
      paramString = finally;
      throw paramString;
    }
  }
  
  public void setPackageName(String paramString)
  {
    try
    {
      this.Q = paramString;
      return;
    }
    finally
    {
      paramString = finally;
      throw paramString;
    }
  }
  
  public void setPhoneNumber(String paramString)
  {
    try
    {
      a("mat_phone_number", paramString);
      setPhoneNumberMd5(MATEncryption.md5(paramString));
      setPhoneNumberSha1(MATEncryption.sha1(paramString));
      setPhoneNumberSha256(MATEncryption.sha256(paramString));
      return;
    }
    finally
    {
      paramString = finally;
      throw paramString;
    }
  }
  
  public void setPhoneNumberMd5(String paramString)
  {
    try
    {
      this.R = paramString;
      return;
    }
    finally
    {
      paramString = finally;
      throw paramString;
    }
  }
  
  public void setPhoneNumberSha1(String paramString)
  {
    try
    {
      this.S = paramString;
      return;
    }
    finally
    {
      paramString = finally;
      throw paramString;
    }
  }
  
  public void setPhoneNumberSha256(String paramString)
  {
    try
    {
      this.T = paramString;
      return;
    }
    finally
    {
      paramString = finally;
      throw paramString;
    }
  }
  
  public void setPluginName(String paramString)
  {
    try
    {
      this.U = null;
      return;
    }
    finally
    {
      paramString = finally;
      throw paramString;
    }
  }
  
  public void setPurchaseStatus(String paramString)
  {
    try
    {
      this.V = paramString;
      return;
    }
    finally
    {
      paramString = finally;
      throw paramString;
    }
  }
  
  public void setRefId(String paramString)
  {
    try
    {
      this.Z = paramString;
      return;
    }
    finally
    {
      paramString = finally;
      throw paramString;
    }
  }
  
  public void setReferralSource(String paramString)
  {
    try
    {
      this.W = paramString;
      return;
    }
    finally
    {
      paramString = finally;
      throw paramString;
    }
  }
  
  public void setReferralUrl(String paramString)
  {
    try
    {
      this.X = paramString;
      return;
    }
    finally
    {
      paramString = finally;
      throw paramString;
    }
  }
  
  public void setReferrerDelay(long paramLong)
  {
    try
    {
      this.Y = Long.toString(paramLong);
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public void setRevenue(String paramString)
  {
    try
    {
      this.aa = paramString;
      return;
    }
    finally
    {
      paramString = finally;
      throw paramString;
    }
  }
  
  public void setScreenDensity(String paramString)
  {
    try
    {
      this.ab = paramString;
      return;
    }
    finally
    {
      paramString = finally;
      throw paramString;
    }
  }
  
  public void setScreenHeight(String paramString)
  {
    try
    {
      this.ac = paramString;
      return;
    }
    finally
    {
      paramString = finally;
      throw paramString;
    }
  }
  
  public void setScreenWidth(String paramString)
  {
    try
    {
      this.ad = paramString;
      return;
    }
    finally
    {
      paramString = finally;
      throw paramString;
    }
  }
  
  public void setSiteId(String paramString)
  {
    try
    {
      this.ae = paramString;
      return;
    }
    finally
    {
      paramString = finally;
      throw paramString;
    }
  }
  
  public void setTRUSTeId(String paramString)
  {
    try
    {
      this.ag = paramString;
      return;
    }
    finally
    {
      paramString = finally;
      throw paramString;
    }
  }
  
  public void setTrackingId(String paramString)
  {
    try
    {
      this.af = paramString;
      return;
    }
    finally
    {
      paramString = finally;
      throw paramString;
    }
  }
  
  public void setTwitterUserId(String paramString)
  {
    try
    {
      this.ah = paramString;
      return;
    }
    finally
    {
      paramString = finally;
      throw paramString;
    }
  }
  
  public void setUserEmail(String paramString)
  {
    try
    {
      a("mat_user_email", paramString);
      setUserEmailMd5(MATEncryption.md5(paramString));
      setUserEmailSha1(MATEncryption.sha1(paramString));
      setUserEmailSha256(MATEncryption.sha256(paramString));
      return;
    }
    finally
    {
      paramString = finally;
      throw paramString;
    }
  }
  
  public void setUserEmailMd5(String paramString)
  {
    try
    {
      this.aj = paramString;
      return;
    }
    finally
    {
      paramString = finally;
      throw paramString;
    }
  }
  
  public void setUserEmailSha1(String paramString)
  {
    try
    {
      this.ak = paramString;
      return;
    }
    finally
    {
      paramString = finally;
      throw paramString;
    }
  }
  
  public void setUserEmailSha256(String paramString)
  {
    try
    {
      this.al = paramString;
      return;
    }
    finally
    {
      paramString = finally;
      throw paramString;
    }
  }
  
  public void setUserEmails(String[] paramArrayOfString)
  {
    try
    {
      JSONArray localJSONArray = new org/json/JSONArray;
      localJSONArray.<init>();
      this.am = localJSONArray;
      for (int i1 = 0; i1 < paramArrayOfString.length; i1++) {
        this.am.put(paramArrayOfString[i1]);
      }
      return;
    }
    finally {}
  }
  
  public void setUserId(String paramString)
  {
    try
    {
      a("mat_user_id", paramString);
      return;
    }
    finally
    {
      paramString = finally;
      throw paramString;
    }
  }
  
  public void setUserName(String paramString)
  {
    try
    {
      a("mat_user_name", paramString);
      setUserNameMd5(MATEncryption.md5(paramString));
      setUserNameSha1(MATEncryption.sha1(paramString));
      setUserNameSha256(MATEncryption.sha256(paramString));
      return;
    }
    finally
    {
      paramString = finally;
      throw paramString;
    }
  }
  
  public void setUserNameMd5(String paramString)
  {
    try
    {
      this.an = paramString;
      return;
    }
    finally
    {
      paramString = finally;
      throw paramString;
    }
  }
  
  public void setUserNameSha1(String paramString)
  {
    try
    {
      this.ao = paramString;
      return;
    }
    finally
    {
      paramString = finally;
      throw paramString;
    }
  }
  
  public void setUserNameSha256(String paramString)
  {
    try
    {
      this.ap = paramString;
      return;
    }
    finally
    {
      paramString = finally;
      throw paramString;
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\mobileapptracker\MATParameters.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */