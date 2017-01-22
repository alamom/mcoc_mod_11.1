package com.mobileapptracker;

import android.util.Log;
import java.util.Date;
import java.util.UUID;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

final class f
{
  private static MATParameters a;
  
  /* Error */
  public static String a(MATEvent paramMATEvent)
  {
    // Byte code:
    //   0: ldc 2
    //   2: monitorenter
    //   3: invokestatic 13	com/mobileapptracker/MATParameters:getInstance	()Lcom/mobileapptracker/MATParameters;
    //   6: putstatic 15	com/mobileapptracker/f:a	Lcom/mobileapptracker/MATParameters;
    //   9: new 17	java/lang/StringBuilder
    //   12: astore_1
    //   13: aload_1
    //   14: invokespecial 21	java/lang/StringBuilder:<init>	()V
    //   17: new 17	java/lang/StringBuilder
    //   20: astore_2
    //   21: aload_2
    //   22: ldc 23
    //   24: invokespecial 26	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   27: aload_1
    //   28: aload_2
    //   29: getstatic 15	com/mobileapptracker/f:a	Lcom/mobileapptracker/MATParameters;
    //   32: invokevirtual 30	com/mobileapptracker/MATParameters:getConnectionType	()Ljava/lang/String;
    //   35: invokevirtual 34	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   38: invokevirtual 37	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   41: invokevirtual 34	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   44: pop
    //   45: aload_1
    //   46: ldc 39
    //   48: getstatic 15	com/mobileapptracker/f:a	Lcom/mobileapptracker/MATParameters;
    //   51: invokevirtual 42	com/mobileapptracker/MATParameters:getAge	()Ljava/lang/String;
    //   54: invokestatic 45	com/mobileapptracker/f:a	(Ljava/lang/StringBuilder;Ljava/lang/String;Ljava/lang/String;)V
    //   57: aload_1
    //   58: ldc 47
    //   60: getstatic 15	com/mobileapptracker/f:a	Lcom/mobileapptracker/MATParameters;
    //   63: invokevirtual 50	com/mobileapptracker/MATParameters:getAltitude	()Ljava/lang/String;
    //   66: invokestatic 45	com/mobileapptracker/f:a	(Ljava/lang/StringBuilder;Ljava/lang/String;Ljava/lang/String;)V
    //   69: aload_1
    //   70: ldc 52
    //   72: getstatic 15	com/mobileapptracker/f:a	Lcom/mobileapptracker/MATParameters;
    //   75: invokevirtual 55	com/mobileapptracker/MATParameters:getAndroidId	()Ljava/lang/String;
    //   78: invokestatic 45	com/mobileapptracker/f:a	(Ljava/lang/StringBuilder;Ljava/lang/String;Ljava/lang/String;)V
    //   81: aload_1
    //   82: ldc 57
    //   84: getstatic 15	com/mobileapptracker/f:a	Lcom/mobileapptracker/MATParameters;
    //   87: invokevirtual 60	com/mobileapptracker/MATParameters:getAndroidIdMd5	()Ljava/lang/String;
    //   90: invokestatic 45	com/mobileapptracker/f:a	(Ljava/lang/StringBuilder;Ljava/lang/String;Ljava/lang/String;)V
    //   93: aload_1
    //   94: ldc 62
    //   96: getstatic 15	com/mobileapptracker/f:a	Lcom/mobileapptracker/MATParameters;
    //   99: invokevirtual 65	com/mobileapptracker/MATParameters:getAndroidIdSha1	()Ljava/lang/String;
    //   102: invokestatic 45	com/mobileapptracker/f:a	(Ljava/lang/StringBuilder;Ljava/lang/String;Ljava/lang/String;)V
    //   105: aload_1
    //   106: ldc 67
    //   108: getstatic 15	com/mobileapptracker/f:a	Lcom/mobileapptracker/MATParameters;
    //   111: invokevirtual 70	com/mobileapptracker/MATParameters:getAndroidIdSha256	()Ljava/lang/String;
    //   114: invokestatic 45	com/mobileapptracker/f:a	(Ljava/lang/StringBuilder;Ljava/lang/String;Ljava/lang/String;)V
    //   117: aload_1
    //   118: ldc 72
    //   120: getstatic 15	com/mobileapptracker/f:a	Lcom/mobileapptracker/MATParameters;
    //   123: invokevirtual 75	com/mobileapptracker/MATParameters:getAppAdTrackingEnabled	()Ljava/lang/String;
    //   126: invokestatic 45	com/mobileapptracker/f:a	(Ljava/lang/StringBuilder;Ljava/lang/String;Ljava/lang/String;)V
    //   129: aload_1
    //   130: ldc 77
    //   132: getstatic 15	com/mobileapptracker/f:a	Lcom/mobileapptracker/MATParameters;
    //   135: invokevirtual 80	com/mobileapptracker/MATParameters:getAppName	()Ljava/lang/String;
    //   138: invokestatic 45	com/mobileapptracker/f:a	(Ljava/lang/StringBuilder;Ljava/lang/String;Ljava/lang/String;)V
    //   141: aload_1
    //   142: ldc 82
    //   144: getstatic 15	com/mobileapptracker/f:a	Lcom/mobileapptracker/MATParameters;
    //   147: invokevirtual 85	com/mobileapptracker/MATParameters:getAppVersion	()Ljava/lang/String;
    //   150: invokestatic 45	com/mobileapptracker/f:a	(Ljava/lang/StringBuilder;Ljava/lang/String;Ljava/lang/String;)V
    //   153: aload_1
    //   154: ldc 87
    //   156: getstatic 15	com/mobileapptracker/f:a	Lcom/mobileapptracker/MATParameters;
    //   159: invokevirtual 90	com/mobileapptracker/MATParameters:getAppVersionName	()Ljava/lang/String;
    //   162: invokestatic 45	com/mobileapptracker/f:a	(Ljava/lang/StringBuilder;Ljava/lang/String;Ljava/lang/String;)V
    //   165: aload_1
    //   166: ldc 92
    //   168: getstatic 15	com/mobileapptracker/f:a	Lcom/mobileapptracker/MATParameters;
    //   171: invokevirtual 95	com/mobileapptracker/MATParameters:getCountryCode	()Ljava/lang/String;
    //   174: invokestatic 45	com/mobileapptracker/f:a	(Ljava/lang/StringBuilder;Ljava/lang/String;Ljava/lang/String;)V
    //   177: aload_1
    //   178: ldc 97
    //   180: getstatic 15	com/mobileapptracker/f:a	Lcom/mobileapptracker/MATParameters;
    //   183: invokevirtual 100	com/mobileapptracker/MATParameters:getDeviceBrand	()Ljava/lang/String;
    //   186: invokestatic 45	com/mobileapptracker/f:a	(Ljava/lang/StringBuilder;Ljava/lang/String;Ljava/lang/String;)V
    //   189: aload_1
    //   190: ldc 102
    //   192: getstatic 15	com/mobileapptracker/f:a	Lcom/mobileapptracker/MATParameters;
    //   195: invokevirtual 105	com/mobileapptracker/MATParameters:getDeviceCarrier	()Ljava/lang/String;
    //   198: invokestatic 45	com/mobileapptracker/f:a	(Ljava/lang/StringBuilder;Ljava/lang/String;Ljava/lang/String;)V
    //   201: aload_1
    //   202: ldc 107
    //   204: getstatic 15	com/mobileapptracker/f:a	Lcom/mobileapptracker/MATParameters;
    //   207: invokevirtual 110	com/mobileapptracker/MATParameters:getDeviceCpuType	()Ljava/lang/String;
    //   210: invokestatic 45	com/mobileapptracker/f:a	(Ljava/lang/StringBuilder;Ljava/lang/String;Ljava/lang/String;)V
    //   213: aload_1
    //   214: ldc 112
    //   216: getstatic 15	com/mobileapptracker/f:a	Lcom/mobileapptracker/MATParameters;
    //   219: invokevirtual 115	com/mobileapptracker/MATParameters:getDeviceCpuSubtype	()Ljava/lang/String;
    //   222: invokestatic 45	com/mobileapptracker/f:a	(Ljava/lang/StringBuilder;Ljava/lang/String;Ljava/lang/String;)V
    //   225: aload_1
    //   226: ldc 117
    //   228: getstatic 15	com/mobileapptracker/f:a	Lcom/mobileapptracker/MATParameters;
    //   231: invokevirtual 120	com/mobileapptracker/MATParameters:getDeviceModel	()Ljava/lang/String;
    //   234: invokestatic 45	com/mobileapptracker/f:a	(Ljava/lang/StringBuilder;Ljava/lang/String;Ljava/lang/String;)V
    //   237: aload_1
    //   238: ldc 122
    //   240: getstatic 15	com/mobileapptracker/f:a	Lcom/mobileapptracker/MATParameters;
    //   243: invokevirtual 125	com/mobileapptracker/MATParameters:getDeviceId	()Ljava/lang/String;
    //   246: invokestatic 45	com/mobileapptracker/f:a	(Ljava/lang/StringBuilder;Ljava/lang/String;Ljava/lang/String;)V
    //   249: aload_1
    //   250: ldc 127
    //   252: getstatic 15	com/mobileapptracker/f:a	Lcom/mobileapptracker/MATParameters;
    //   255: invokevirtual 130	com/mobileapptracker/MATParameters:getExistingUser	()Ljava/lang/String;
    //   258: invokestatic 45	com/mobileapptracker/f:a	(Ljava/lang/StringBuilder;Ljava/lang/String;Ljava/lang/String;)V
    //   261: aload_1
    //   262: ldc -124
    //   264: getstatic 15	com/mobileapptracker/f:a	Lcom/mobileapptracker/MATParameters;
    //   267: invokevirtual 135	com/mobileapptracker/MATParameters:getFacebookUserId	()Ljava/lang/String;
    //   270: invokestatic 45	com/mobileapptracker/f:a	(Ljava/lang/StringBuilder;Ljava/lang/String;Ljava/lang/String;)V
    //   273: aload_1
    //   274: ldc -119
    //   276: getstatic 15	com/mobileapptracker/f:a	Lcom/mobileapptracker/MATParameters;
    //   279: invokevirtual 140	com/mobileapptracker/MATParameters:getGender	()Ljava/lang/String;
    //   282: invokestatic 45	com/mobileapptracker/f:a	(Ljava/lang/StringBuilder;Ljava/lang/String;Ljava/lang/String;)V
    //   285: aload_1
    //   286: ldc -114
    //   288: getstatic 15	com/mobileapptracker/f:a	Lcom/mobileapptracker/MATParameters;
    //   291: invokevirtual 145	com/mobileapptracker/MATParameters:getGoogleAdvertisingId	()Ljava/lang/String;
    //   294: invokestatic 45	com/mobileapptracker/f:a	(Ljava/lang/StringBuilder;Ljava/lang/String;Ljava/lang/String;)V
    //   297: aload_1
    //   298: ldc -109
    //   300: getstatic 15	com/mobileapptracker/f:a	Lcom/mobileapptracker/MATParameters;
    //   303: invokevirtual 150	com/mobileapptracker/MATParameters:getGoogleAdTrackingLimited	()Ljava/lang/String;
    //   306: invokestatic 45	com/mobileapptracker/f:a	(Ljava/lang/StringBuilder;Ljava/lang/String;Ljava/lang/String;)V
    //   309: aload_1
    //   310: ldc -104
    //   312: getstatic 15	com/mobileapptracker/f:a	Lcom/mobileapptracker/MATParameters;
    //   315: invokevirtual 155	com/mobileapptracker/MATParameters:getGoogleUserId	()Ljava/lang/String;
    //   318: invokestatic 45	com/mobileapptracker/f:a	(Ljava/lang/StringBuilder;Ljava/lang/String;Ljava/lang/String;)V
    //   321: aload_1
    //   322: ldc -99
    //   324: getstatic 15	com/mobileapptracker/f:a	Lcom/mobileapptracker/MATParameters;
    //   327: invokevirtual 160	com/mobileapptracker/MATParameters:getInstallDate	()Ljava/lang/String;
    //   330: invokestatic 45	com/mobileapptracker/f:a	(Ljava/lang/StringBuilder;Ljava/lang/String;Ljava/lang/String;)V
    //   333: aload_1
    //   334: ldc -94
    //   336: getstatic 15	com/mobileapptracker/f:a	Lcom/mobileapptracker/MATParameters;
    //   339: invokevirtual 165	com/mobileapptracker/MATParameters:getInstaller	()Ljava/lang/String;
    //   342: invokestatic 45	com/mobileapptracker/f:a	(Ljava/lang/StringBuilder;Ljava/lang/String;Ljava/lang/String;)V
    //   345: aload_1
    //   346: ldc -89
    //   348: getstatic 15	com/mobileapptracker/f:a	Lcom/mobileapptracker/MATParameters;
    //   351: invokevirtual 170	com/mobileapptracker/MATParameters:getInstallReferrer	()Ljava/lang/String;
    //   354: invokestatic 45	com/mobileapptracker/f:a	(Ljava/lang/StringBuilder;Ljava/lang/String;Ljava/lang/String;)V
    //   357: aload_1
    //   358: ldc -84
    //   360: getstatic 15	com/mobileapptracker/f:a	Lcom/mobileapptracker/MATParameters;
    //   363: invokevirtual 175	com/mobileapptracker/MATParameters:getIsPayingUser	()Ljava/lang/String;
    //   366: invokestatic 45	com/mobileapptracker/f:a	(Ljava/lang/StringBuilder;Ljava/lang/String;Ljava/lang/String;)V
    //   369: aload_1
    //   370: ldc -79
    //   372: getstatic 15	com/mobileapptracker/f:a	Lcom/mobileapptracker/MATParameters;
    //   375: invokevirtual 180	com/mobileapptracker/MATParameters:getLanguage	()Ljava/lang/String;
    //   378: invokestatic 45	com/mobileapptracker/f:a	(Ljava/lang/StringBuilder;Ljava/lang/String;Ljava/lang/String;)V
    //   381: aload_1
    //   382: ldc -74
    //   384: getstatic 15	com/mobileapptracker/f:a	Lcom/mobileapptracker/MATParameters;
    //   387: invokevirtual 185	com/mobileapptracker/MATParameters:getLastOpenLogId	()Ljava/lang/String;
    //   390: invokestatic 45	com/mobileapptracker/f:a	(Ljava/lang/StringBuilder;Ljava/lang/String;Ljava/lang/String;)V
    //   393: aload_1
    //   394: ldc -69
    //   396: getstatic 15	com/mobileapptracker/f:a	Lcom/mobileapptracker/MATParameters;
    //   399: invokevirtual 190	com/mobileapptracker/MATParameters:getLatitude	()Ljava/lang/String;
    //   402: invokestatic 45	com/mobileapptracker/f:a	(Ljava/lang/StringBuilder;Ljava/lang/String;Ljava/lang/String;)V
    //   405: aload_1
    //   406: ldc -64
    //   408: getstatic 15	com/mobileapptracker/f:a	Lcom/mobileapptracker/MATParameters;
    //   411: invokevirtual 195	com/mobileapptracker/MATParameters:getLongitude	()Ljava/lang/String;
    //   414: invokestatic 45	com/mobileapptracker/f:a	(Ljava/lang/StringBuilder;Ljava/lang/String;Ljava/lang/String;)V
    //   417: aload_1
    //   418: ldc -59
    //   420: getstatic 15	com/mobileapptracker/f:a	Lcom/mobileapptracker/MATParameters;
    //   423: invokevirtual 200	com/mobileapptracker/MATParameters:getMacAddress	()Ljava/lang/String;
    //   426: invokestatic 45	com/mobileapptracker/f:a	(Ljava/lang/StringBuilder;Ljava/lang/String;Ljava/lang/String;)V
    //   429: aload_1
    //   430: ldc -54
    //   432: getstatic 15	com/mobileapptracker/f:a	Lcom/mobileapptracker/MATParameters;
    //   435: invokevirtual 205	com/mobileapptracker/MATParameters:getMatId	()Ljava/lang/String;
    //   438: invokestatic 45	com/mobileapptracker/f:a	(Ljava/lang/StringBuilder;Ljava/lang/String;Ljava/lang/String;)V
    //   441: aload_1
    //   442: ldc -49
    //   444: getstatic 15	com/mobileapptracker/f:a	Lcom/mobileapptracker/MATParameters;
    //   447: invokevirtual 210	com/mobileapptracker/MATParameters:getMCC	()Ljava/lang/String;
    //   450: invokestatic 45	com/mobileapptracker/f:a	(Ljava/lang/StringBuilder;Ljava/lang/String;Ljava/lang/String;)V
    //   453: aload_1
    //   454: ldc -44
    //   456: getstatic 15	com/mobileapptracker/f:a	Lcom/mobileapptracker/MATParameters;
    //   459: invokevirtual 215	com/mobileapptracker/MATParameters:getMNC	()Ljava/lang/String;
    //   462: invokestatic 45	com/mobileapptracker/f:a	(Ljava/lang/StringBuilder;Ljava/lang/String;Ljava/lang/String;)V
    //   465: aload_1
    //   466: ldc -39
    //   468: getstatic 15	com/mobileapptracker/f:a	Lcom/mobileapptracker/MATParameters;
    //   471: invokevirtual 220	com/mobileapptracker/MATParameters:getOpenLogId	()Ljava/lang/String;
    //   474: invokestatic 45	com/mobileapptracker/f:a	(Ljava/lang/StringBuilder;Ljava/lang/String;Ljava/lang/String;)V
    //   477: aload_1
    //   478: ldc -34
    //   480: getstatic 15	com/mobileapptracker/f:a	Lcom/mobileapptracker/MATParameters;
    //   483: invokevirtual 225	com/mobileapptracker/MATParameters:getOsVersion	()Ljava/lang/String;
    //   486: invokestatic 45	com/mobileapptracker/f:a	(Ljava/lang/StringBuilder;Ljava/lang/String;Ljava/lang/String;)V
    //   489: aload_1
    //   490: ldc -29
    //   492: getstatic 15	com/mobileapptracker/f:a	Lcom/mobileapptracker/MATParameters;
    //   495: invokevirtual 230	com/mobileapptracker/MATParameters:getPluginName	()Ljava/lang/String;
    //   498: invokestatic 45	com/mobileapptracker/f:a	(Ljava/lang/StringBuilder;Ljava/lang/String;Ljava/lang/String;)V
    //   501: aload_1
    //   502: ldc -24
    //   504: getstatic 15	com/mobileapptracker/f:a	Lcom/mobileapptracker/MATParameters;
    //   507: invokevirtual 235	com/mobileapptracker/MATParameters:getPurchaseStatus	()Ljava/lang/String;
    //   510: invokestatic 45	com/mobileapptracker/f:a	(Ljava/lang/StringBuilder;Ljava/lang/String;Ljava/lang/String;)V
    //   513: aload_1
    //   514: ldc -19
    //   516: getstatic 15	com/mobileapptracker/f:a	Lcom/mobileapptracker/MATParameters;
    //   519: invokevirtual 240	com/mobileapptracker/MATParameters:getReferrerDelay	()Ljava/lang/String;
    //   522: invokestatic 45	com/mobileapptracker/f:a	(Ljava/lang/StringBuilder;Ljava/lang/String;Ljava/lang/String;)V
    //   525: aload_1
    //   526: ldc -14
    //   528: getstatic 15	com/mobileapptracker/f:a	Lcom/mobileapptracker/MATParameters;
    //   531: invokevirtual 245	com/mobileapptracker/MATParameters:getScreenDensity	()Ljava/lang/String;
    //   534: invokestatic 45	com/mobileapptracker/f:a	(Ljava/lang/StringBuilder;Ljava/lang/String;Ljava/lang/String;)V
    //   537: new 17	java/lang/StringBuilder
    //   540: astore_2
    //   541: aload_2
    //   542: invokespecial 21	java/lang/StringBuilder:<init>	()V
    //   545: aload_1
    //   546: ldc -9
    //   548: aload_2
    //   549: getstatic 15	com/mobileapptracker/f:a	Lcom/mobileapptracker/MATParameters;
    //   552: invokevirtual 250	com/mobileapptracker/MATParameters:getScreenWidth	()Ljava/lang/String;
    //   555: invokevirtual 34	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   558: ldc -4
    //   560: invokevirtual 34	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   563: getstatic 15	com/mobileapptracker/f:a	Lcom/mobileapptracker/MATParameters;
    //   566: invokevirtual 255	com/mobileapptracker/MATParameters:getScreenHeight	()Ljava/lang/String;
    //   569: invokevirtual 34	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   572: invokevirtual 37	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   575: invokestatic 45	com/mobileapptracker/f:a	(Ljava/lang/StringBuilder;Ljava/lang/String;Ljava/lang/String;)V
    //   578: aload_1
    //   579: ldc_w 257
    //   582: getstatic 15	com/mobileapptracker/f:a	Lcom/mobileapptracker/MATParameters;
    //   585: invokevirtual 260	com/mobileapptracker/MATParameters:getSdkVersion	()Ljava/lang/String;
    //   588: invokestatic 45	com/mobileapptracker/f:a	(Ljava/lang/StringBuilder;Ljava/lang/String;Ljava/lang/String;)V
    //   591: aload_1
    //   592: ldc_w 262
    //   595: getstatic 15	com/mobileapptracker/f:a	Lcom/mobileapptracker/MATParameters;
    //   598: invokevirtual 265	com/mobileapptracker/MATParameters:getTRUSTeId	()Ljava/lang/String;
    //   601: invokestatic 45	com/mobileapptracker/f:a	(Ljava/lang/StringBuilder;Ljava/lang/String;Ljava/lang/String;)V
    //   604: aload_1
    //   605: ldc_w 267
    //   608: getstatic 15	com/mobileapptracker/f:a	Lcom/mobileapptracker/MATParameters;
    //   611: invokevirtual 270	com/mobileapptracker/MATParameters:getTwitterUserId	()Ljava/lang/String;
    //   614: invokestatic 45	com/mobileapptracker/f:a	(Ljava/lang/StringBuilder;Ljava/lang/String;Ljava/lang/String;)V
    //   617: aload_1
    //   618: ldc_w 272
    //   621: getstatic 15	com/mobileapptracker/f:a	Lcom/mobileapptracker/MATParameters;
    //   624: invokevirtual 275	com/mobileapptracker/MATParameters:getUserAgent	()Ljava/lang/String;
    //   627: invokestatic 45	com/mobileapptracker/f:a	(Ljava/lang/StringBuilder;Ljava/lang/String;Ljava/lang/String;)V
    //   630: aload_1
    //   631: ldc_w 277
    //   634: getstatic 15	com/mobileapptracker/f:a	Lcom/mobileapptracker/MATParameters;
    //   637: invokevirtual 280	com/mobileapptracker/MATParameters:getUserEmailMd5	()Ljava/lang/String;
    //   640: invokestatic 45	com/mobileapptracker/f:a	(Ljava/lang/StringBuilder;Ljava/lang/String;Ljava/lang/String;)V
    //   643: aload_1
    //   644: ldc_w 282
    //   647: getstatic 15	com/mobileapptracker/f:a	Lcom/mobileapptracker/MATParameters;
    //   650: invokevirtual 285	com/mobileapptracker/MATParameters:getUserEmailSha1	()Ljava/lang/String;
    //   653: invokestatic 45	com/mobileapptracker/f:a	(Ljava/lang/StringBuilder;Ljava/lang/String;Ljava/lang/String;)V
    //   656: aload_1
    //   657: ldc_w 287
    //   660: getstatic 15	com/mobileapptracker/f:a	Lcom/mobileapptracker/MATParameters;
    //   663: invokevirtual 290	com/mobileapptracker/MATParameters:getUserEmailSha256	()Ljava/lang/String;
    //   666: invokestatic 45	com/mobileapptracker/f:a	(Ljava/lang/StringBuilder;Ljava/lang/String;Ljava/lang/String;)V
    //   669: aload_1
    //   670: ldc_w 292
    //   673: getstatic 15	com/mobileapptracker/f:a	Lcom/mobileapptracker/MATParameters;
    //   676: invokevirtual 295	com/mobileapptracker/MATParameters:getUserId	()Ljava/lang/String;
    //   679: invokestatic 45	com/mobileapptracker/f:a	(Ljava/lang/StringBuilder;Ljava/lang/String;Ljava/lang/String;)V
    //   682: aload_1
    //   683: ldc_w 297
    //   686: getstatic 15	com/mobileapptracker/f:a	Lcom/mobileapptracker/MATParameters;
    //   689: invokevirtual 300	com/mobileapptracker/MATParameters:getUserNameMd5	()Ljava/lang/String;
    //   692: invokestatic 45	com/mobileapptracker/f:a	(Ljava/lang/StringBuilder;Ljava/lang/String;Ljava/lang/String;)V
    //   695: aload_1
    //   696: ldc_w 302
    //   699: getstatic 15	com/mobileapptracker/f:a	Lcom/mobileapptracker/MATParameters;
    //   702: invokevirtual 305	com/mobileapptracker/MATParameters:getUserNameSha1	()Ljava/lang/String;
    //   705: invokestatic 45	com/mobileapptracker/f:a	(Ljava/lang/StringBuilder;Ljava/lang/String;Ljava/lang/String;)V
    //   708: aload_1
    //   709: ldc_w 307
    //   712: getstatic 15	com/mobileapptracker/f:a	Lcom/mobileapptracker/MATParameters;
    //   715: invokevirtual 310	com/mobileapptracker/MATParameters:getUserNameSha256	()Ljava/lang/String;
    //   718: invokestatic 45	com/mobileapptracker/f:a	(Ljava/lang/StringBuilder;Ljava/lang/String;Ljava/lang/String;)V
    //   721: aload_1
    //   722: ldc_w 312
    //   725: getstatic 15	com/mobileapptracker/f:a	Lcom/mobileapptracker/MATParameters;
    //   728: invokevirtual 315	com/mobileapptracker/MATParameters:getPhoneNumberMd5	()Ljava/lang/String;
    //   731: invokestatic 45	com/mobileapptracker/f:a	(Ljava/lang/StringBuilder;Ljava/lang/String;Ljava/lang/String;)V
    //   734: aload_1
    //   735: ldc_w 317
    //   738: getstatic 15	com/mobileapptracker/f:a	Lcom/mobileapptracker/MATParameters;
    //   741: invokevirtual 320	com/mobileapptracker/MATParameters:getPhoneNumberSha1	()Ljava/lang/String;
    //   744: invokestatic 45	com/mobileapptracker/f:a	(Ljava/lang/StringBuilder;Ljava/lang/String;Ljava/lang/String;)V
    //   747: aload_1
    //   748: ldc_w 322
    //   751: getstatic 15	com/mobileapptracker/f:a	Lcom/mobileapptracker/MATParameters;
    //   754: invokevirtual 325	com/mobileapptracker/MATParameters:getPhoneNumberSha256	()Ljava/lang/String;
    //   757: invokestatic 45	com/mobileapptracker/f:a	(Ljava/lang/StringBuilder;Ljava/lang/String;Ljava/lang/String;)V
    //   760: aload_1
    //   761: ldc_w 327
    //   764: aload_0
    //   765: invokevirtual 332	com/mobileapptracker/MATEvent:getAttribute1	()Ljava/lang/String;
    //   768: invokestatic 45	com/mobileapptracker/f:a	(Ljava/lang/StringBuilder;Ljava/lang/String;Ljava/lang/String;)V
    //   771: aload_1
    //   772: ldc_w 334
    //   775: aload_0
    //   776: invokevirtual 337	com/mobileapptracker/MATEvent:getAttribute2	()Ljava/lang/String;
    //   779: invokestatic 45	com/mobileapptracker/f:a	(Ljava/lang/StringBuilder;Ljava/lang/String;Ljava/lang/String;)V
    //   782: aload_1
    //   783: ldc_w 339
    //   786: aload_0
    //   787: invokevirtual 342	com/mobileapptracker/MATEvent:getAttribute3	()Ljava/lang/String;
    //   790: invokestatic 45	com/mobileapptracker/f:a	(Ljava/lang/StringBuilder;Ljava/lang/String;Ljava/lang/String;)V
    //   793: aload_1
    //   794: ldc_w 344
    //   797: aload_0
    //   798: invokevirtual 347	com/mobileapptracker/MATEvent:getAttribute4	()Ljava/lang/String;
    //   801: invokestatic 45	com/mobileapptracker/f:a	(Ljava/lang/StringBuilder;Ljava/lang/String;Ljava/lang/String;)V
    //   804: aload_1
    //   805: ldc_w 349
    //   808: aload_0
    //   809: invokevirtual 352	com/mobileapptracker/MATEvent:getAttribute5	()Ljava/lang/String;
    //   812: invokestatic 45	com/mobileapptracker/f:a	(Ljava/lang/StringBuilder;Ljava/lang/String;Ljava/lang/String;)V
    //   815: aload_1
    //   816: ldc_w 354
    //   819: aload_0
    //   820: invokevirtual 357	com/mobileapptracker/MATEvent:getContentId	()Ljava/lang/String;
    //   823: invokestatic 45	com/mobileapptracker/f:a	(Ljava/lang/StringBuilder;Ljava/lang/String;Ljava/lang/String;)V
    //   826: aload_1
    //   827: ldc_w 359
    //   830: aload_0
    //   831: invokevirtual 362	com/mobileapptracker/MATEvent:getContentType	()Ljava/lang/String;
    //   834: invokestatic 45	com/mobileapptracker/f:a	(Ljava/lang/StringBuilder;Ljava/lang/String;Ljava/lang/String;)V
    //   837: aload_0
    //   838: invokevirtual 365	com/mobileapptracker/MATEvent:getCurrencyCode	()Ljava/lang/String;
    //   841: ifnull +199 -> 1040
    //   844: aload_1
    //   845: ldc_w 367
    //   848: aload_0
    //   849: invokevirtual 365	com/mobileapptracker/MATEvent:getCurrencyCode	()Ljava/lang/String;
    //   852: invokestatic 45	com/mobileapptracker/f:a	(Ljava/lang/StringBuilder;Ljava/lang/String;Ljava/lang/String;)V
    //   855: aload_0
    //   856: invokevirtual 371	com/mobileapptracker/MATEvent:getDate1	()Ljava/util/Date;
    //   859: ifnull +24 -> 883
    //   862: aload_1
    //   863: ldc_w 373
    //   866: aload_0
    //   867: invokevirtual 371	com/mobileapptracker/MATEvent:getDate1	()Ljava/util/Date;
    //   870: invokevirtual 379	java/util/Date:getTime	()J
    //   873: ldc2_w 380
    //   876: ldiv
    //   877: invokestatic 386	java/lang/Long:toString	(J)Ljava/lang/String;
    //   880: invokestatic 45	com/mobileapptracker/f:a	(Ljava/lang/StringBuilder;Ljava/lang/String;Ljava/lang/String;)V
    //   883: aload_0
    //   884: invokevirtual 389	com/mobileapptracker/MATEvent:getDate2	()Ljava/util/Date;
    //   887: ifnull +24 -> 911
    //   890: aload_1
    //   891: ldc_w 391
    //   894: aload_0
    //   895: invokevirtual 389	com/mobileapptracker/MATEvent:getDate2	()Ljava/util/Date;
    //   898: invokevirtual 379	java/util/Date:getTime	()J
    //   901: ldc2_w 380
    //   904: ldiv
    //   905: invokestatic 386	java/lang/Long:toString	(J)Ljava/lang/String;
    //   908: invokestatic 45	com/mobileapptracker/f:a	(Ljava/lang/StringBuilder;Ljava/lang/String;Ljava/lang/String;)V
    //   911: aload_0
    //   912: invokevirtual 395	com/mobileapptracker/MATEvent:getLevel	()I
    //   915: ifeq +17 -> 932
    //   918: aload_1
    //   919: ldc_w 397
    //   922: aload_0
    //   923: invokevirtual 395	com/mobileapptracker/MATEvent:getLevel	()I
    //   926: invokestatic 402	java/lang/Integer:toString	(I)Ljava/lang/String;
    //   929: invokestatic 45	com/mobileapptracker/f:a	(Ljava/lang/StringBuilder;Ljava/lang/String;Ljava/lang/String;)V
    //   932: aload_0
    //   933: invokevirtual 405	com/mobileapptracker/MATEvent:getQuantity	()I
    //   936: ifeq +17 -> 953
    //   939: aload_1
    //   940: ldc_w 407
    //   943: aload_0
    //   944: invokevirtual 405	com/mobileapptracker/MATEvent:getQuantity	()I
    //   947: invokestatic 402	java/lang/Integer:toString	(I)Ljava/lang/String;
    //   950: invokestatic 45	com/mobileapptracker/f:a	(Ljava/lang/StringBuilder;Ljava/lang/String;Ljava/lang/String;)V
    //   953: aload_0
    //   954: invokevirtual 411	com/mobileapptracker/MATEvent:getRating	()D
    //   957: dconst_0
    //   958: dcmpl
    //   959: ifeq +17 -> 976
    //   962: aload_1
    //   963: ldc_w 413
    //   966: aload_0
    //   967: invokevirtual 411	com/mobileapptracker/MATEvent:getRating	()D
    //   970: invokestatic 418	java/lang/Double:toString	(D)Ljava/lang/String;
    //   973: invokestatic 45	com/mobileapptracker/f:a	(Ljava/lang/StringBuilder;Ljava/lang/String;Ljava/lang/String;)V
    //   976: aload_1
    //   977: ldc_w 420
    //   980: aload_0
    //   981: invokevirtual 423	com/mobileapptracker/MATEvent:getSearchString	()Ljava/lang/String;
    //   984: invokestatic 45	com/mobileapptracker/f:a	(Ljava/lang/StringBuilder;Ljava/lang/String;Ljava/lang/String;)V
    //   987: aload_1
    //   988: ldc_w 425
    //   991: aload_0
    //   992: invokevirtual 428	com/mobileapptracker/MATEvent:getRefId	()Ljava/lang/String;
    //   995: invokestatic 45	com/mobileapptracker/f:a	(Ljava/lang/StringBuilder;Ljava/lang/String;Ljava/lang/String;)V
    //   998: aload_1
    //   999: ldc_w 430
    //   1002: aload_0
    //   1003: invokevirtual 433	com/mobileapptracker/MATEvent:getRevenue	()D
    //   1006: invokestatic 418	java/lang/Double:toString	(D)Ljava/lang/String;
    //   1009: invokestatic 45	com/mobileapptracker/f:a	(Ljava/lang/StringBuilder;Ljava/lang/String;Ljava/lang/String;)V
    //   1012: aload_0
    //   1013: invokevirtual 436	com/mobileapptracker/MATEvent:getDeviceForm	()Ljava/lang/String;
    //   1016: ifnull +14 -> 1030
    //   1019: aload_1
    //   1020: ldc_w 438
    //   1023: aload_0
    //   1024: invokevirtual 436	com/mobileapptracker/MATEvent:getDeviceForm	()Ljava/lang/String;
    //   1027: invokestatic 45	com/mobileapptracker/f:a	(Ljava/lang/StringBuilder;Ljava/lang/String;Ljava/lang/String;)V
    //   1030: aload_1
    //   1031: invokevirtual 37	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1034: astore_0
    //   1035: ldc 2
    //   1037: monitorexit
    //   1038: aload_0
    //   1039: areturn
    //   1040: aload_1
    //   1041: ldc_w 367
    //   1044: getstatic 15	com/mobileapptracker/f:a	Lcom/mobileapptracker/MATParameters;
    //   1047: invokevirtual 439	com/mobileapptracker/MATParameters:getCurrencyCode	()Ljava/lang/String;
    //   1050: invokestatic 45	com/mobileapptracker/f:a	(Ljava/lang/StringBuilder;Ljava/lang/String;Ljava/lang/String;)V
    //   1053: goto -198 -> 855
    //   1056: astore_0
    //   1057: ldc 2
    //   1059: monitorexit
    //   1060: aload_0
    //   1061: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	1062	0	paramMATEvent	MATEvent
    //   12	1029	1	localStringBuilder1	StringBuilder
    //   20	529	2	localStringBuilder2	StringBuilder
    // Exception table:
    //   from	to	target	type
    //   3	855	1056	finally
    //   855	883	1056	finally
    //   883	911	1056	finally
    //   911	932	1056	finally
    //   932	953	1056	finally
    //   953	976	1056	finally
    //   976	1030	1056	finally
    //   1030	1035	1056	finally
    //   1040	1053	1056	finally
  }
  
  public static String a(MATEvent paramMATEvent, MATPreloadData paramMATPreloadData, boolean paramBoolean)
  {
    a = MATParameters.getInstance();
    StringBuilder localStringBuilder = new StringBuilder("https://").append(a.getAdvertiserId()).append(".");
    if (paramBoolean) {
      localStringBuilder.append("debug.engine.mobileapptracking.com");
    }
    for (;;)
    {
      localStringBuilder.append("/serve?ver=").append(a.getSdkVersion());
      localStringBuilder.append("&transaction_id=").append(UUID.randomUUID().toString());
      a(localStringBuilder, "sdk", "android");
      a(localStringBuilder, "action", a.getAction());
      a(localStringBuilder, "advertiser_id", a.getAdvertiserId());
      a(localStringBuilder, "package_name", a.getPackageName());
      a(localStringBuilder, "referral_source", a.getReferralSource());
      a(localStringBuilder, "referral_url", a.getReferralUrl());
      a(localStringBuilder, "site_id", a.getSiteId());
      a(localStringBuilder, "tracking_id", a.getTrackingId());
      if (paramMATEvent.getEventId() != 0) {
        a(localStringBuilder, "site_event_id", Integer.toString(paramMATEvent.getEventId()));
      }
      if (!a.getAction().equals("session")) {
        a(localStringBuilder, "site_event_name", paramMATEvent.getEventName());
      }
      if (paramMATPreloadData != null)
      {
        localStringBuilder.append("&attr_set=1");
        a(localStringBuilder, "publisher_id", paramMATPreloadData.publisherId);
        a(localStringBuilder, "offer_id", paramMATPreloadData.offerId);
        a(localStringBuilder, "agency_id", paramMATPreloadData.agencyId);
        a(localStringBuilder, "publisher_ref_id", paramMATPreloadData.publisherReferenceId);
        a(localStringBuilder, "publisher_sub_publisher", paramMATPreloadData.publisherSubPublisher);
        a(localStringBuilder, "publisher_sub_site", paramMATPreloadData.publisherSubSite);
        a(localStringBuilder, "publisher_sub_campaign", paramMATPreloadData.publisherSubCampaign);
        a(localStringBuilder, "publisher_sub_adgroup", paramMATPreloadData.publisherSubAdgroup);
        a(localStringBuilder, "publisher_sub_ad", paramMATPreloadData.publisherSubAd);
        a(localStringBuilder, "publisher_sub_keyword", paramMATPreloadData.publisherSubKeyword);
        a(localStringBuilder, "advertiser_sub_publisher", paramMATPreloadData.advertiserSubPublisher);
        a(localStringBuilder, "advertiser_sub_site", paramMATPreloadData.advertiserSubSite);
        a(localStringBuilder, "advertiser_sub_campaign", paramMATPreloadData.advertiserSubCampaign);
        a(localStringBuilder, "advertiser_sub_adgroup", paramMATPreloadData.advertiserSubAdgroup);
        a(localStringBuilder, "advertiser_sub_ad", paramMATPreloadData.advertiserSubAd);
        a(localStringBuilder, "advertiser_sub_keyword", paramMATPreloadData.advertiserSubKeyword);
        a(localStringBuilder, "publisher_sub1", paramMATPreloadData.publisherSub1);
        a(localStringBuilder, "publisher_sub2", paramMATPreloadData.publisherSub2);
        a(localStringBuilder, "publisher_sub3", paramMATPreloadData.publisherSub3);
        a(localStringBuilder, "publisher_sub4", paramMATPreloadData.publisherSub4);
        a(localStringBuilder, "publisher_sub5", paramMATPreloadData.publisherSub5);
      }
      paramMATEvent = a.getAllowDuplicates();
      if ((paramMATEvent != null) && (Integer.parseInt(paramMATEvent) == 1)) {
        localStringBuilder.append("&skip_dup=1");
      }
      if (paramBoolean) {
        localStringBuilder.append("&debug=1");
      }
      return localStringBuilder.toString();
      localStringBuilder.append("engine.mobileapptracking.com");
    }
  }
  
  public static String a(String paramString, MATEncryption paramMATEncryption)
  {
    try
    {
      StringBuilder localStringBuilder = new java/lang/StringBuilder;
      localStringBuilder.<init>(paramString);
      Object localObject = MATParameters.getInstance();
      a = (MATParameters)localObject;
      if (localObject != null)
      {
        localObject = a.getGoogleAdvertisingId();
        if ((localObject != null) && (!paramString.contains("&google_aid=")))
        {
          a(localStringBuilder, "google_aid", (String)localObject);
          a(localStringBuilder, "google_ad_tracking_disabled", a.getGoogleAdTrackingLimited());
        }
        localObject = a.getAndroidId();
        if ((localObject != null) && (!paramString.contains("&android_id="))) {
          a(localStringBuilder, "android_id", (String)localObject);
        }
        localObject = a.getInstallReferrer();
        if ((localObject != null) && (!paramString.contains("&install_referrer="))) {
          a(localStringBuilder, "install_referrer", (String)localObject);
        }
        localObject = a.getUserAgent();
        if ((localObject != null) && (!paramString.contains("&conversion_user_agent="))) {
          a(localStringBuilder, "conversion_user_agent", (String)localObject);
        }
        localObject = a.getFacebookUserId();
        if ((localObject != null) && (!paramString.contains("&facebook_user_id="))) {
          a(localStringBuilder, "facebook_user_id", (String)localObject);
        }
      }
      if (!paramString.contains("&system_date="))
      {
        paramString = new java/util/Date;
        paramString.<init>();
        a(localStringBuilder, "system_date", Long.toString(paramString.getTime() / 1000L));
      }
      paramString = localStringBuilder.toString();
      try
      {
        paramMATEncryption = MATEncryption.bytesToHex(paramMATEncryption.encrypt(paramString));
        paramString = paramMATEncryption;
      }
      catch (Exception paramMATEncryption)
      {
        for (;;)
        {
          paramMATEncryption.printStackTrace();
        }
      }
      return paramString;
    }
    finally {}
  }
  
  public static JSONObject a(JSONArray paramJSONArray1, String paramString1, String paramString2, JSONArray paramJSONArray2)
  {
    try
    {
      JSONObject localJSONObject = new org/json/JSONObject;
      localJSONObject.<init>();
      if (paramJSONArray1 != null) {}
      try
      {
        localJSONObject.put("data", paramJSONArray1);
        if (paramString1 != null) {
          localJSONObject.put("store_iap_data", paramString1);
        }
        if (paramString2 != null) {
          localJSONObject.put("store_iap_signature", paramString2);
        }
        if (paramJSONArray2 != null) {
          localJSONObject.put("user_emails", paramJSONArray2);
        }
      }
      catch (JSONException paramJSONArray1)
      {
        for (;;)
        {
          Log.d("MobileAppTracker", "Could not build JSON body of request");
          paramJSONArray1.printStackTrace();
        }
      }
      return localJSONObject;
    }
    finally {}
  }
  
  /* Error */
  private static void a(StringBuilder paramStringBuilder, String paramString1, String paramString2)
  {
    // Byte code:
    //   0: ldc 2
    //   2: monitorenter
    //   3: aload_2
    //   4: ifnull +58 -> 62
    //   7: aload_2
    //   8: ldc_w 706
    //   11: invokevirtual 510	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   14: istore_3
    //   15: iload_3
    //   16: ifne +46 -> 62
    //   19: new 17	java/lang/StringBuilder
    //   22: astore 4
    //   24: aload 4
    //   26: ldc_w 708
    //   29: invokespecial 26	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   32: aload_0
    //   33: aload 4
    //   35: aload_1
    //   36: invokevirtual 34	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   39: ldc_w 710
    //   42: invokevirtual 34	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   45: aload_2
    //   46: ldc_w 712
    //   49: invokestatic 718	java/net/URLEncoder:encode	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   52: invokevirtual 34	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   55: invokevirtual 37	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   58: invokevirtual 34	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   61: pop
    //   62: ldc 2
    //   64: monitorexit
    //   65: return
    //   66: astore 4
    //   68: new 17	java/lang/StringBuilder
    //   71: astore_0
    //   72: aload_0
    //   73: ldc_w 720
    //   76: invokespecial 26	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   79: ldc_w 693
    //   82: aload_0
    //   83: aload_2
    //   84: invokevirtual 34	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   87: ldc_w 722
    //   90: invokevirtual 34	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   93: aload_1
    //   94: invokevirtual 34	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   97: invokevirtual 37	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   100: invokestatic 725	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;)I
    //   103: pop
    //   104: aload 4
    //   106: invokevirtual 726	java/io/UnsupportedEncodingException:printStackTrace	()V
    //   109: goto -47 -> 62
    //   112: astore_0
    //   113: ldc 2
    //   115: monitorexit
    //   116: aload_0
    //   117: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	118	0	paramStringBuilder	StringBuilder
    //   0	118	1	paramString1	String
    //   0	118	2	paramString2	String
    //   14	2	3	bool	boolean
    //   22	12	4	localStringBuilder	StringBuilder
    //   66	39	4	localUnsupportedEncodingException	java.io.UnsupportedEncodingException
    // Exception table:
    //   from	to	target	type
    //   19	62	66	java/io/UnsupportedEncodingException
    //   7	15	112	finally
    //   19	62	112	finally
    //   68	109	112	finally
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\mobileapptracker\f.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */