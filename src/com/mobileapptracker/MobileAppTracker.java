package com.mobileapptracker;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.location.Location;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.json.JSONException;
import org.json.JSONObject;

public class MobileAppTracker
{
  public static final int GENDER_FEMALE = 1;
  public static final int GENDER_MALE = 0;
  private static volatile MobileAppTracker s = null;
  boolean a;
  boolean b;
  boolean c;
  ExecutorService d;
  private final String e = "heF9BATUfWuISyO8";
  protected MATEventQueue eventQueue;
  private b f;
  private MATPreloadData g;
  private g h;
  private MATEncryption i;
  protected boolean initialized;
  protected boolean isRegistered;
  private MATResponse j;
  private boolean k;
  private boolean l;
  private int m;
  protected Context mContext;
  protected MATTestRequest matRequest;
  private boolean n;
  protected BroadcastReceiver networkStateReceiver;
  private boolean o;
  private boolean p;
  protected MATParameters params;
  protected ExecutorService pubQueue;
  private long q;
  private long r;
  
  private String a(int paramInt)
  {
    b localb;
    Context localContext;
    if (this.n)
    {
      localObject = this.f;
      b.b(this.params.getUserAgent());
      localb = this.f;
      localContext = this.mContext;
      localObject = this.h;
    }
    for (Object localObject = localb.a(localContext, paramInt);; localObject = "") {
      return (String)localObject;
    }
  }
  
  /* Error */
  private void a(MATEvent paramMATEvent)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 93	com/mobileapptracker/MobileAppTracker:initialized	Z
    //   6: istore_3
    //   7: iload_3
    //   8: ifne +6 -> 14
    //   11: aload_0
    //   12: monitorexit
    //   13: return
    //   14: aload_0
    //   15: invokevirtual 96	com/mobileapptracker/MobileAppTracker:dumpQueue	()V
    //   18: aload_0
    //   19: getfield 70	com/mobileapptracker/MobileAppTracker:params	Lcom/mobileapptracker/MATParameters;
    //   22: ldc 98
    //   24: invokevirtual 101	com/mobileapptracker/MATParameters:setAction	(Ljava/lang/String;)V
    //   27: new 103	java/util/Date
    //   30: astore 5
    //   32: aload 5
    //   34: invokespecial 104	java/util/Date:<init>	()V
    //   37: aload_1
    //   38: invokevirtual 109	com/mobileapptracker/MATEvent:getEventName	()Ljava/lang/String;
    //   41: ifnull +94 -> 135
    //   44: aload_1
    //   45: invokevirtual 109	com/mobileapptracker/MATEvent:getEventName	()Ljava/lang/String;
    //   48: astore 4
    //   50: aload_0
    //   51: getfield 111	com/mobileapptracker/MobileAppTracker:p	Z
    //   54: ifeq +7 -> 61
    //   57: aload_1
    //   58: invokestatic 115	com/mobileapptracker/c:a	(Lcom/mobileapptracker/MATEvent;)V
    //   61: aload 4
    //   63: ldc 117
    //   65: invokevirtual 123	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   68: ifne -57 -> 11
    //   71: aload 4
    //   73: ldc 125
    //   75: invokevirtual 123	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   78: ifne +33 -> 111
    //   81: aload 4
    //   83: ldc 127
    //   85: invokevirtual 123	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   88: ifne +23 -> 111
    //   91: aload 4
    //   93: ldc -127
    //   95: invokevirtual 123	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   98: ifne +13 -> 111
    //   101: aload 4
    //   103: ldc -125
    //   105: invokevirtual 123	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   108: ifeq +27 -> 135
    //   111: aload_0
    //   112: getfield 70	com/mobileapptracker/MobileAppTracker:params	Lcom/mobileapptracker/MATParameters;
    //   115: ldc -125
    //   117: invokevirtual 101	com/mobileapptracker/MATParameters:setAction	(Ljava/lang/String;)V
    //   120: new 103	java/util/Date
    //   123: aload 5
    //   125: invokevirtual 135	java/util/Date:getTime	()J
    //   128: ldc2_w 136
    //   131: ladd
    //   132: invokespecial 140	java/util/Date:<init>	(J)V
    //   135: aload_1
    //   136: invokevirtual 144	com/mobileapptracker/MATEvent:getRevenue	()D
    //   139: dconst_0
    //   140: dcmpl
    //   141: ifle +14 -> 155
    //   144: aload_0
    //   145: getfield 70	com/mobileapptracker/MobileAppTracker:params	Lcom/mobileapptracker/MATParameters;
    //   148: iconst_1
    //   149: invokestatic 149	java/lang/Integer:toString	(I)Ljava/lang/String;
    //   152: invokevirtual 152	com/mobileapptracker/MATParameters:setIsPayingUser	(Ljava/lang/String;)V
    //   155: aload_1
    //   156: aload_0
    //   157: getfield 154	com/mobileapptracker/MobileAppTracker:g	Lcom/mobileapptracker/MATPreloadData;
    //   160: aload_0
    //   161: getfield 156	com/mobileapptracker/MobileAppTracker:k	Z
    //   164: invokestatic 161	com/mobileapptracker/f:a	(Lcom/mobileapptracker/MATEvent;Lcom/mobileapptracker/MATPreloadData;Z)Ljava/lang/String;
    //   167: astore 5
    //   169: aload_1
    //   170: invokestatic 164	com/mobileapptracker/f:a	(Lcom/mobileapptracker/MATEvent;)Ljava/lang/String;
    //   173: astore 4
    //   175: new 166	org/json/JSONArray
    //   178: astore 6
    //   180: aload 6
    //   182: invokespecial 167	org/json/JSONArray:<init>	()V
    //   185: aload_1
    //   186: invokevirtual 171	com/mobileapptracker/MATEvent:getEventItems	()Ljava/util/List;
    //   189: ifnull +46 -> 235
    //   192: iconst_0
    //   193: istore_2
    //   194: iload_2
    //   195: aload_1
    //   196: invokevirtual 171	com/mobileapptracker/MATEvent:getEventItems	()Ljava/util/List;
    //   199: invokeinterface 177 1 0
    //   204: if_icmpge +31 -> 235
    //   207: aload 6
    //   209: aload_1
    //   210: invokevirtual 171	com/mobileapptracker/MATEvent:getEventItems	()Ljava/util/List;
    //   213: iload_2
    //   214: invokeinterface 181 2 0
    //   219: checkcast 183	com/mobileapptracker/MATEventItem
    //   222: invokevirtual 187	com/mobileapptracker/MATEventItem:toJSON	()Lorg/json/JSONObject;
    //   225: invokevirtual 191	org/json/JSONArray:put	(Ljava/lang/Object;)Lorg/json/JSONArray;
    //   228: pop
    //   229: iinc 2 1
    //   232: goto -38 -> 194
    //   235: aload 6
    //   237: aload_1
    //   238: invokevirtual 194	com/mobileapptracker/MATEvent:getReceiptData	()Ljava/lang/String;
    //   241: aload_1
    //   242: invokevirtual 197	com/mobileapptracker/MATEvent:getReceiptSignature	()Ljava/lang/String;
    //   245: aload_0
    //   246: getfield 70	com/mobileapptracker/MobileAppTracker:params	Lcom/mobileapptracker/MATParameters;
    //   249: invokevirtual 201	com/mobileapptracker/MATParameters:getUserEmails	()Lorg/json/JSONArray;
    //   252: invokestatic 204	com/mobileapptracker/f:a	(Lorg/json/JSONArray;Ljava/lang/String;Ljava/lang/String;Lorg/json/JSONArray;)Lorg/json/JSONObject;
    //   255: astore 6
    //   257: aload_0
    //   258: getfield 206	com/mobileapptracker/MobileAppTracker:matRequest	Lcom/mobileapptracker/MATTestRequest;
    //   261: ifnull +18 -> 279
    //   264: aload_0
    //   265: getfield 206	com/mobileapptracker/MobileAppTracker:matRequest	Lcom/mobileapptracker/MATTestRequest;
    //   268: aload 5
    //   270: aload 4
    //   272: aload 6
    //   274: invokeinterface 212 4 0
    //   279: aload_0
    //   280: aload 5
    //   282: aload 4
    //   284: aload 6
    //   286: aload_0
    //   287: getfield 214	com/mobileapptracker/MobileAppTracker:o	Z
    //   290: invokevirtual 218	com/mobileapptracker/MobileAppTracker:addEventToQueue	(Ljava/lang/String;Ljava/lang/String;Lorg/json/JSONObject;Z)V
    //   293: aload_0
    //   294: iconst_0
    //   295: putfield 214	com/mobileapptracker/MobileAppTracker:o	Z
    //   298: aload_0
    //   299: invokevirtual 96	com/mobileapptracker/MobileAppTracker:dumpQueue	()V
    //   302: aload_0
    //   303: getfield 220	com/mobileapptracker/MobileAppTracker:j	Lcom/mobileapptracker/MATResponse;
    //   306: ifnull -295 -> 11
    //   309: aload_0
    //   310: getfield 220	com/mobileapptracker/MobileAppTracker:j	Lcom/mobileapptracker/MATResponse;
    //   313: aload_1
    //   314: invokevirtual 223	com/mobileapptracker/MATEvent:getRefId	()Ljava/lang/String;
    //   317: invokeinterface 228 2 0
    //   322: goto -311 -> 11
    //   325: astore_1
    //   326: aload_0
    //   327: monitorexit
    //   328: aload_1
    //   329: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	330	0	this	MobileAppTracker
    //   0	330	1	paramMATEvent	MATEvent
    //   193	37	2	i1	int
    //   6	2	3	bool	boolean
    //   48	235	4	str	String
    //   30	251	5	localObject1	Object
    //   178	107	6	localObject2	Object
    // Exception table:
    //   from	to	target	type
    //   2	7	325	finally
    //   14	61	325	finally
    //   61	111	325	finally
    //   111	135	325	finally
    //   135	155	325	finally
    //   155	192	325	finally
    //   194	229	325	finally
    //   235	279	325	finally
    //   279	322	325	finally
  }
  
  public static MobileAppTracker getInstance()
  {
    try
    {
      MobileAppTracker localMobileAppTracker = s;
      return localMobileAppTracker;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public static MobileAppTracker init(Context paramContext, String paramString1, String paramString2)
  {
    try
    {
      if (s == null)
      {
        MobileAppTracker localMobileAppTracker = new com/mobileapptracker/MobileAppTracker;
        localMobileAppTracker.<init>();
        s = localMobileAppTracker;
        localMobileAppTracker.mContext = paramContext.getApplicationContext();
        s.pubQueue = Executors.newSingleThreadExecutor();
        s.initAll(paramString1, paramString2);
      }
      paramContext = s;
      return paramContext;
    }
    finally {}
  }
  
  public static boolean isOnline(Context paramContext)
  {
    paramContext = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getActiveNetworkInfo();
    if ((paramContext != null) && (paramContext.isConnected())) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  protected void addEventToQueue(String paramString1, String paramString2, JSONObject paramJSONObject, boolean paramBoolean)
  {
    ExecutorService localExecutorService = this.d;
    MATEventQueue localMATEventQueue = this.eventQueue;
    localMATEventQueue.getClass();
    localExecutorService.execute(new MATEventQueue.Add(localMATEventQueue, paramString1, paramString2, paramJSONObject, paramBoolean));
  }
  
  protected void dumpQueue()
  {
    if (!isOnline(this.mContext)) {}
    for (;;)
    {
      return;
      ExecutorService localExecutorService = this.d;
      MATEventQueue localMATEventQueue = this.eventQueue;
      localMATEventQueue.getClass();
      localExecutorService.execute(new MATEventQueue.Dump(localMATEventQueue));
    }
  }
  
  public String getAction()
  {
    return this.params.getAction();
  }
  
  public String getAdvertiserId()
  {
    return this.params.getAdvertiserId();
  }
  
  public int getAge()
  {
    return Integer.parseInt(this.params.getAge());
  }
  
  public double getAltitude()
  {
    return Double.parseDouble(this.params.getAltitude());
  }
  
  public boolean getAppAdTrackingEnabled()
  {
    boolean bool = true;
    if (Integer.parseInt(this.params.getAppAdTrackingEnabled()) == 1) {}
    for (;;)
    {
      return bool;
      bool = false;
    }
  }
  
  public String getAppName()
  {
    return this.params.getAppName();
  }
  
  public int getAppVersion()
  {
    return Integer.parseInt(this.params.getAppVersion());
  }
  
  public String getConnectionType()
  {
    return this.params.getConnectionType();
  }
  
  public String getCountryCode()
  {
    return this.params.getCountryCode();
  }
  
  public String getCurrencyCode()
  {
    return this.params.getCurrencyCode();
  }
  
  public String getDeviceBrand()
  {
    return this.params.getDeviceBrand();
  }
  
  public String getDeviceCarrier()
  {
    return this.params.getDeviceCarrier();
  }
  
  public String getDeviceModel()
  {
    return this.params.getDeviceModel();
  }
  
  public boolean getExistingUser()
  {
    boolean bool = true;
    if (Integer.parseInt(this.params.getExistingUser()) == 1) {}
    for (;;)
    {
      return bool;
      bool = false;
    }
  }
  
  public String getFacebookUserId()
  {
    return this.params.getFacebookUserId();
  }
  
  public int getGender()
  {
    return Integer.parseInt(this.params.getGender());
  }
  
  public boolean getGoogleAdTrackingLimited()
  {
    if (Integer.parseInt(this.params.getGoogleAdTrackingLimited()) == 0) {}
    for (boolean bool = false;; bool = true) {
      return bool;
    }
  }
  
  public String getGoogleAdvertisingId()
  {
    return this.params.getGoogleAdvertisingId();
  }
  
  public String getGoogleUserId()
  {
    return this.params.getGoogleUserId();
  }
  
  public long getInstallDate()
  {
    return Long.parseLong(this.params.getInstallDate());
  }
  
  public String getInstallReferrer()
  {
    return this.params.getInstallReferrer();
  }
  
  public boolean getIsPayingUser()
  {
    return this.params.getIsPayingUser().equals("1");
  }
  
  public String getLanguage()
  {
    return this.params.getLanguage();
  }
  
  public String getLastOpenLogId()
  {
    return this.params.getLastOpenLogId();
  }
  
  public double getLatitude()
  {
    return Double.parseDouble(this.params.getLatitude());
  }
  
  public double getLongitude()
  {
    return Double.parseDouble(this.params.getLongitude());
  }
  
  public String getMCC()
  {
    return this.params.getMCC();
  }
  
  public String getMNC()
  {
    return this.params.getMNC();
  }
  
  public String getMatId()
  {
    return this.params.getMatId();
  }
  
  public String getOpenLogId()
  {
    return this.params.getOpenLogId();
  }
  
  public String getOsVersion()
  {
    return this.params.getOsVersion();
  }
  
  public String getPackageName()
  {
    return this.params.getPackageName();
  }
  
  public String getPluginName()
  {
    return this.params.getPluginName();
  }
  
  public String getRefId()
  {
    return this.params.getRefId();
  }
  
  public String getReferralSource()
  {
    return this.params.getReferralSource();
  }
  
  public String getReferralUrl()
  {
    return this.params.getReferralUrl();
  }
  
  public Double getRevenue()
  {
    return Double.valueOf(Double.parseDouble(this.params.getRevenue()));
  }
  
  public String getSDKVersion()
  {
    return this.params.getSdkVersion();
  }
  
  public String getScreenDensity()
  {
    return this.params.getScreenDensity();
  }
  
  public String getScreenHeight()
  {
    return this.params.getScreenHeight();
  }
  
  public String getScreenWidth()
  {
    return this.params.getScreenWidth();
  }
  
  public String getSiteId()
  {
    return this.params.getSiteId();
  }
  
  public String getTRUSTeId()
  {
    return this.params.getTRUSTeId();
  }
  
  public String getTwitterUserId()
  {
    return this.params.getTwitterUserId();
  }
  
  public String getUserAgent()
  {
    return this.params.getUserAgent();
  }
  
  public String getUserEmail()
  {
    return this.params.getUserEmail();
  }
  
  public String getUserId()
  {
    return this.params.getUserId();
  }
  
  public String getUserName()
  {
    return this.params.getUserName();
  }
  
  protected void initAll(String paramString1, String paramString2)
  {
    this.f = b.a(paramString1, paramString2, this.mContext.getPackageName());
    this.params = MATParameters.init(this, this.mContext, paramString1, paramString2);
    this.d = Executors.newSingleThreadExecutor();
    this.h = new g();
    this.i = new MATEncryption(paramString2.trim(), "heF9BATUfWuISyO8");
    this.q = System.currentTimeMillis();
    boolean bool;
    if (!this.mContext.getSharedPreferences("com.mobileapptracking", 0).getString("mat_referrer", "").equals("")) {
      bool = true;
    }
    for (;;)
    {
      this.b = bool;
      this.n = false;
      this.o = true;
      this.initialized = false;
      this.isRegistered = false;
      this.k = false;
      this.p = false;
      this.eventQueue = new MATEventQueue(this.mContext, this);
      dumpQueue();
      this.networkStateReceiver = new h(this);
      if (this.isRegistered) {}
      try
      {
        this.mContext.unregisterReceiver(this.networkStateReceiver);
        this.isRegistered = false;
        paramString1 = new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
        this.mContext.registerReceiver(this.networkStateReceiver, paramString1);
        this.isRegistered = true;
        this.initialized = true;
        return;
        bool = false;
      }
      catch (IllegalArgumentException paramString1)
      {
        for (;;) {}
      }
    }
  }
  
  protected boolean makeRequest(String paramString1, String paramString2, JSONObject paramJSONObject)
  {
    boolean bool2 = true;
    int i1 = 0;
    if (this.k) {
      Log.d("MobileAppTracker", "Sending event to server...");
    }
    paramString2 = f.a(paramString2, this.i);
    paramString1 = g.a(paramString1 + "&data=" + paramString2, paramJSONObject, this.k);
    if (paramString1 == null)
    {
      bool1 = bool2;
      if (this.j != null) {
        this.j.didFailWithError(paramString1);
      }
    }
    for (boolean bool1 = bool2;; bool1 = false)
    {
      return bool1;
      if (paramString1.has("success")) {
        break;
      }
      if (this.k) {
        Log.d("MobileAppTracker", "Request failed, event will remain in queue");
      }
    }
    if (this.j != null) {}
    for (;;)
    {
      try
      {
        bool1 = paramString1.getString("success").equals("true");
        if (bool1) {
          i1 = 1;
        }
        if (i1 == 0) {
          break label256;
        }
        this.j.didSucceedWithData(paramString1);
        bool1 = bool2;
        try
        {
          if (!paramString1.getString("site_event_type").equals("open")) {
            break;
          }
          paramString1 = paramString1.getString("log_id");
          if (getOpenLogId().equals("")) {
            this.params.setOpenLogId(paramString1);
          }
          this.params.setLastOpenLogId(paramString1);
          bool1 = bool2;
        }
        catch (JSONException paramString1)
        {
          bool1 = bool2;
        }
      }
      catch (JSONException paramString1)
      {
        paramString1.printStackTrace();
        bool1 = false;
      }
      break;
      label256:
      this.j.didFailWithError(paramString1);
    }
  }
  
  public void measureEvent(int paramInt)
  {
    measureEvent(new MATEvent(paramInt));
  }
  
  public void measureEvent(MATEvent paramMATEvent)
  {
    this.pubQueue.execute(new s(this, paramMATEvent));
  }
  
  public void measureEvent(String paramString)
  {
    measureEvent(new MATEvent(paramString));
  }
  
  public void measureSession()
  {
    SharedPreferences localSharedPreferences = this.mContext.getSharedPreferences("com.mobileapptracking", 0);
    if (!localSharedPreferences.contains("mat_installed"))
    {
      localSharedPreferences.edit().putBoolean("mat_installed", true).commit();
      this.n = true;
    }
    this.c = false;
    measureEvent(new MATEvent("session"));
  }
  
  public void setAdvertiserId(String paramString)
  {
    this.pubQueue.execute(new ad(this, paramString));
  }
  
  public void setAge(int paramInt)
  {
    this.pubQueue.execute(new an(this, paramInt));
  }
  
  public void setAllowDuplicates(boolean paramBoolean)
  {
    this.pubQueue.execute(new aj(this, paramBoolean));
    if (paramBoolean) {
      new Handler(Looper.getMainLooper()).post(new ak(this));
    }
  }
  
  public void setAltitude(double paramDouble)
  {
    this.pubQueue.execute(new ao(this, paramDouble));
  }
  
  public void setAndroidId(String paramString)
  {
    b localb = this.f;
    b.c(paramString);
    if (this.params != null) {
      this.params.setAndroidId(paramString);
    }
    if (this.l) {
      a(this.m);
    }
  }
  
  public void setAndroidIdMd5(String paramString)
  {
    this.pubQueue.execute(new ap(this, paramString));
  }
  
  public void setAndroidIdSha1(String paramString)
  {
    this.pubQueue.execute(new aq(this, paramString));
  }
  
  public void setAndroidIdSha256(String paramString)
  {
    this.pubQueue.execute(new ar(this, paramString));
  }
  
  public void setAppAdTrackingEnabled(boolean paramBoolean)
  {
    this.pubQueue.execute(new as(this, paramBoolean));
  }
  
  public void setCurrencyCode(String paramString)
  {
    this.pubQueue.execute(new i(this, paramString));
  }
  
  public void setDebugMode(boolean paramBoolean)
  {
    this.k = paramBoolean;
    if (paramBoolean) {
      new Handler(Looper.getMainLooper()).post(new al(this));
    }
  }
  
  public void setDeferredDeeplink(boolean paramBoolean, int paramInt)
  {
    this.l = paramBoolean;
    this.m = paramInt;
  }
  
  public void setDeviceBrand(String paramString)
  {
    this.pubQueue.execute(new j(this, paramString));
  }
  
  public void setDeviceId(String paramString)
  {
    this.pubQueue.execute(new k(this, paramString));
  }
  
  public void setDeviceModel(String paramString)
  {
    this.pubQueue.execute(new l(this, paramString));
  }
  
  public void setEmailCollection(boolean paramBoolean)
  {
    this.pubQueue.execute(new am(this, paramBoolean));
  }
  
  public void setExistingUser(boolean paramBoolean)
  {
    this.pubQueue.execute(new m(this, paramBoolean));
  }
  
  public void setFacebookEventLogging(boolean paramBoolean1, Context paramContext, boolean paramBoolean2)
  {
    this.p = paramBoolean1;
    if (paramBoolean1) {
      c.a(paramContext, paramBoolean2);
    }
  }
  
  public void setFacebookUserId(String paramString)
  {
    this.pubQueue.execute(new n(this, paramString));
  }
  
  public void setGender(int paramInt)
  {
    this.pubQueue.execute(new o(this, paramInt));
  }
  
  public void setGoogleAdvertisingId(String paramString, boolean paramBoolean)
  {
    int i1;
    if (paramBoolean) {
      i1 = 1;
    }
    for (;;)
    {
      ??? = this.f;
      b.a(paramString, i1);
      if (this.params != null)
      {
        this.params.setGoogleAdvertisingId(paramString);
        this.params.setGoogleAdTrackingLimited(Integer.toString(i1));
      }
      this.a = true;
      if ((this.b) && (!this.c)) {}
      synchronized (this.d)
      {
        this.d.notifyAll();
        this.c = true;
        if (this.l) {
          a(this.m);
        }
        return;
        i1 = 0;
      }
    }
  }
  
  public void setGoogleUserId(String paramString)
  {
    this.pubQueue.execute(new p(this, paramString));
  }
  
  public void setInstallReferrer(String paramString)
  {
    this.b = true;
    this.r = System.currentTimeMillis();
    if (this.params != null) {
      this.params.setReferrerDelay(this.r - this.q);
    }
    this.pubQueue.execute(new q(this, paramString));
  }
  
  public void setIsPayingUser(boolean paramBoolean)
  {
    this.pubQueue.execute(new r(this, paramBoolean));
  }
  
  public void setLatitude(double paramDouble)
  {
    this.pubQueue.execute(new t(this, paramDouble));
  }
  
  public void setLocation(Location paramLocation)
  {
    this.pubQueue.execute(new u(this, paramLocation));
  }
  
  public void setLongitude(double paramDouble)
  {
    this.pubQueue.execute(new v(this, paramDouble));
  }
  
  public void setMATResponse(MATResponse paramMATResponse)
  {
    this.j = paramMATResponse;
    b localb = this.f;
    b.a(paramMATResponse);
  }
  
  public void setMacAddress(String paramString)
  {
    this.pubQueue.execute(new w(this, paramString));
  }
  
  public void setOsVersion(String paramString)
  {
    this.pubQueue.execute(new x(this, paramString));
  }
  
  public void setPackageName(String paramString)
  {
    b localb = this.f;
    b.a(paramString);
    this.pubQueue.execute(new y(this, paramString));
  }
  
  public void setPhoneNumber(String paramString)
  {
    this.pubQueue.execute(new z(this, paramString));
  }
  
  public void setPluginName(String paramString)
  {
    if (Arrays.asList(a.a).contains(paramString)) {
      this.pubQueue.execute(new ai(this, paramString));
    }
    while (!this.k) {
      return;
    }
    throw new IllegalArgumentException("Plugin name not acceptable");
  }
  
  public void setPreloadedApp(MATPreloadData paramMATPreloadData)
  {
    this.g = paramMATPreloadData;
  }
  
  public void setReferralSources(Activity paramActivity)
  {
    this.pubQueue.execute(new aa(this, paramActivity));
  }
  
  public void setSiteId(String paramString)
  {
    this.pubQueue.execute(new ab(this, paramString));
  }
  
  public void setTRUSTeId(String paramString)
  {
    this.pubQueue.execute(new ac(this, paramString));
  }
  
  public void setTwitterUserId(String paramString)
  {
    this.pubQueue.execute(new ae(this, paramString));
  }
  
  public void setUserEmail(String paramString)
  {
    this.pubQueue.execute(new af(this, paramString));
  }
  
  public void setUserId(String paramString)
  {
    this.pubQueue.execute(new ag(this, paramString));
  }
  
  public void setUserName(String paramString)
  {
    this.pubQueue.execute(new ah(this, paramString));
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\mobileapptracker\MobileAppTracker.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */