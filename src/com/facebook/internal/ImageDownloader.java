package com.facebook.internal;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Looper;
import java.io.Closeable;
import java.io.InputStream;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

public class ImageDownloader
{
  private static final int CACHE_READ_QUEUE_MAX_CONCURRENT = 2;
  private static final int DOWNLOAD_QUEUE_MAX_CONCURRENT = 8;
  private static WorkQueue cacheReadQueue = new WorkQueue(2);
  private static WorkQueue downloadQueue = new WorkQueue(8);
  private static Handler handler;
  private static final Map<RequestKey, DownloaderContext> pendingRequests = new HashMap();
  
  public static boolean cancelRequest(ImageRequest arg0)
  {
    boolean bool = false;
    RequestKey localRequestKey = new RequestKey(???.getImageUri(), ???.getCallerTag());
    synchronized (pendingRequests)
    {
      DownloaderContext localDownloaderContext = (DownloaderContext)pendingRequests.get(localRequestKey);
      if (localDownloaderContext != null)
      {
        bool = true;
        if (localDownloaderContext.workItem.cancel()) {
          pendingRequests.remove(localRequestKey);
        }
      }
      else
      {
        return bool;
      }
      localDownloaderContext.isCancelled = true;
    }
  }
  
  public static void clearCache(Context paramContext)
  {
    ImageResponseCache.clearCache(paramContext);
    UrlRedirectCache.clearCache(paramContext);
  }
  
  /* Error */
  private static void download(RequestKey paramRequestKey, Context paramContext)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 12
    //   3: aconst_null
    //   4: astore 13
    //   6: aconst_null
    //   7: astore 8
    //   9: aconst_null
    //   10: astore 22
    //   12: aconst_null
    //   13: astore 23
    //   15: aconst_null
    //   16: astore 24
    //   18: aconst_null
    //   19: astore 21
    //   21: aconst_null
    //   22: astore 20
    //   24: aconst_null
    //   25: astore 19
    //   27: aconst_null
    //   28: astore 18
    //   30: iconst_1
    //   31: istore 5
    //   33: iconst_1
    //   34: istore 6
    //   36: iconst_1
    //   37: istore 4
    //   39: aload 8
    //   41: astore 16
    //   43: iload 4
    //   45: istore_2
    //   46: aload 21
    //   48: astore 10
    //   50: aload 12
    //   52: astore 15
    //   54: iload 6
    //   56: istore_3
    //   57: aload 23
    //   59: astore 9
    //   61: aload 13
    //   63: astore 17
    //   65: aload 24
    //   67: astore 11
    //   69: new 114	java/net/URL
    //   72: astore 14
    //   74: aload 8
    //   76: astore 16
    //   78: iload 4
    //   80: istore_2
    //   81: aload 21
    //   83: astore 10
    //   85: aload 12
    //   87: astore 15
    //   89: iload 6
    //   91: istore_3
    //   92: aload 23
    //   94: astore 9
    //   96: aload 13
    //   98: astore 17
    //   100: aload 24
    //   102: astore 11
    //   104: aload 14
    //   106: aload_0
    //   107: getfield 118	com/facebook/internal/ImageDownloader$RequestKey:uri	Ljava/net/URI;
    //   110: invokevirtual 124	java/net/URI:toString	()Ljava/lang/String;
    //   113: invokespecial 127	java/net/URL:<init>	(Ljava/lang/String;)V
    //   116: aload 8
    //   118: astore 16
    //   120: iload 4
    //   122: istore_2
    //   123: aload 21
    //   125: astore 10
    //   127: aload 12
    //   129: astore 15
    //   131: iload 6
    //   133: istore_3
    //   134: aload 23
    //   136: astore 9
    //   138: aload 13
    //   140: astore 17
    //   142: aload 24
    //   144: astore 11
    //   146: aload 14
    //   148: invokevirtual 131	java/net/URL:openConnection	()Ljava/net/URLConnection;
    //   151: checkcast 133	java/net/HttpURLConnection
    //   154: astore 12
    //   156: aload 12
    //   158: astore 16
    //   160: iload 4
    //   162: istore_2
    //   163: aload 21
    //   165: astore 10
    //   167: aload 12
    //   169: astore 15
    //   171: iload 6
    //   173: istore_3
    //   174: aload 23
    //   176: astore 9
    //   178: aload 12
    //   180: astore 17
    //   182: aload 24
    //   184: astore 11
    //   186: aload 12
    //   188: iconst_0
    //   189: invokevirtual 137	java/net/HttpURLConnection:setInstanceFollowRedirects	(Z)V
    //   192: aload 12
    //   194: astore 16
    //   196: iload 4
    //   198: istore_2
    //   199: aload 21
    //   201: astore 10
    //   203: aload 12
    //   205: astore 15
    //   207: iload 6
    //   209: istore_3
    //   210: aload 23
    //   212: astore 9
    //   214: aload 12
    //   216: astore 17
    //   218: aload 24
    //   220: astore 11
    //   222: aload 12
    //   224: invokevirtual 141	java/net/HttpURLConnection:getResponseCode	()I
    //   227: lookupswitch	default:+33->260, 200:+875->1102, 301:+373->600, 302:+373->600
    //   260: aload 12
    //   262: astore 16
    //   264: iload 4
    //   266: istore_2
    //   267: aload 21
    //   269: astore 10
    //   271: aload 12
    //   273: astore 15
    //   275: iload 6
    //   277: istore_3
    //   278: aload 23
    //   280: astore 9
    //   282: aload 12
    //   284: astore 17
    //   286: aload 24
    //   288: astore 11
    //   290: aload 12
    //   292: invokevirtual 145	java/net/HttpURLConnection:getErrorStream	()Ljava/io/InputStream;
    //   295: astore 8
    //   297: aload 12
    //   299: astore 16
    //   301: iload 4
    //   303: istore_2
    //   304: aload 8
    //   306: astore 10
    //   308: aload 12
    //   310: astore 15
    //   312: iload 6
    //   314: istore_3
    //   315: aload 8
    //   317: astore 9
    //   319: aload 12
    //   321: astore 17
    //   323: aload 8
    //   325: astore 11
    //   327: new 147	java/lang/StringBuilder
    //   330: astore 13
    //   332: aload 12
    //   334: astore 16
    //   336: iload 4
    //   338: istore_2
    //   339: aload 8
    //   341: astore 10
    //   343: aload 12
    //   345: astore 15
    //   347: iload 6
    //   349: istore_3
    //   350: aload 8
    //   352: astore 9
    //   354: aload 12
    //   356: astore 17
    //   358: aload 8
    //   360: astore 11
    //   362: aload 13
    //   364: invokespecial 148	java/lang/StringBuilder:<init>	()V
    //   367: aload 8
    //   369: ifnull +906 -> 1275
    //   372: aload 12
    //   374: astore 16
    //   376: iload 4
    //   378: istore_2
    //   379: aload 8
    //   381: astore 10
    //   383: aload 12
    //   385: astore 15
    //   387: iload 6
    //   389: istore_3
    //   390: aload 8
    //   392: astore 9
    //   394: aload 12
    //   396: astore 17
    //   398: aload 8
    //   400: astore 11
    //   402: new 150	java/io/InputStreamReader
    //   405: astore_1
    //   406: aload 12
    //   408: astore 16
    //   410: iload 4
    //   412: istore_2
    //   413: aload 8
    //   415: astore 10
    //   417: aload 12
    //   419: astore 15
    //   421: iload 6
    //   423: istore_3
    //   424: aload 8
    //   426: astore 9
    //   428: aload 12
    //   430: astore 17
    //   432: aload 8
    //   434: astore 11
    //   436: aload_1
    //   437: aload 8
    //   439: invokespecial 153	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   442: aload 12
    //   444: astore 16
    //   446: iload 4
    //   448: istore_2
    //   449: aload 8
    //   451: astore 10
    //   453: aload 12
    //   455: astore 15
    //   457: iload 6
    //   459: istore_3
    //   460: aload 8
    //   462: astore 9
    //   464: aload 12
    //   466: astore 17
    //   468: aload 8
    //   470: astore 11
    //   472: sipush 128
    //   475: newarray <illegal type>
    //   477: astore 14
    //   479: aload 12
    //   481: astore 16
    //   483: iload 4
    //   485: istore_2
    //   486: aload 8
    //   488: astore 10
    //   490: aload 12
    //   492: astore 15
    //   494: iload 6
    //   496: istore_3
    //   497: aload 8
    //   499: astore 9
    //   501: aload 12
    //   503: astore 17
    //   505: aload 8
    //   507: astore 11
    //   509: aload_1
    //   510: aload 14
    //   512: iconst_0
    //   513: aload 14
    //   515: arraylength
    //   516: invokevirtual 157	java/io/InputStreamReader:read	([CII)I
    //   519: istore 7
    //   521: iload 7
    //   523: ifle +663 -> 1186
    //   526: aload 12
    //   528: astore 16
    //   530: iload 4
    //   532: istore_2
    //   533: aload 8
    //   535: astore 10
    //   537: aload 12
    //   539: astore 15
    //   541: iload 6
    //   543: istore_3
    //   544: aload 8
    //   546: astore 9
    //   548: aload 12
    //   550: astore 17
    //   552: aload 8
    //   554: astore 11
    //   556: aload 13
    //   558: aload 14
    //   560: iconst_0
    //   561: iload 7
    //   563: invokevirtual 161	java/lang/StringBuilder:append	([CII)Ljava/lang/StringBuilder;
    //   566: pop
    //   567: goto -88 -> 479
    //   570: astore 13
    //   572: aload 10
    //   574: invokestatic 167	com/facebook/internal/Utility:closeQuietly	(Ljava/io/Closeable;)V
    //   577: aload 16
    //   579: invokestatic 171	com/facebook/internal/Utility:disconnectQuietly	(Ljava/net/URLConnection;)V
    //   582: aload 18
    //   584: astore 14
    //   586: iload_2
    //   587: ifeq +12 -> 599
    //   590: aload_0
    //   591: aload 13
    //   593: aload 14
    //   595: iconst_0
    //   596: invokestatic 175	com/facebook/internal/ImageDownloader:issueResponse	(Lcom/facebook/internal/ImageDownloader$RequestKey;Ljava/lang/Exception;Landroid/graphics/Bitmap;Z)V
    //   599: return
    //   600: iconst_0
    //   601: istore 6
    //   603: iconst_0
    //   604: istore 7
    //   606: iconst_0
    //   607: istore 5
    //   609: aload 12
    //   611: astore 16
    //   613: iload 5
    //   615: istore_2
    //   616: aload 21
    //   618: astore 10
    //   620: aload 12
    //   622: astore 15
    //   624: iload 7
    //   626: istore_3
    //   627: aload 23
    //   629: astore 9
    //   631: aload 12
    //   633: astore 17
    //   635: aload 24
    //   637: astore 11
    //   639: aload 12
    //   641: ldc -79
    //   643: invokevirtual 181	java/net/HttpURLConnection:getHeaderField	(Ljava/lang/String;)Ljava/lang/String;
    //   646: astore 26
    //   648: aload 12
    //   650: astore 16
    //   652: iload 5
    //   654: istore_2
    //   655: aload 21
    //   657: astore 10
    //   659: aload 19
    //   661: astore 14
    //   663: aload 20
    //   665: astore 13
    //   667: iload 6
    //   669: istore 4
    //   671: aload 22
    //   673: astore 8
    //   675: aload 12
    //   677: astore 15
    //   679: iload 7
    //   681: istore_3
    //   682: aload 23
    //   684: astore 9
    //   686: aload 12
    //   688: astore 17
    //   690: aload 24
    //   692: astore 11
    //   694: aload 26
    //   696: invokestatic 185	com/facebook/internal/Utility:isNullOrEmpty	(Ljava/lang/String;)Z
    //   699: ifne +387 -> 1086
    //   702: aload 12
    //   704: astore 16
    //   706: iload 5
    //   708: istore_2
    //   709: aload 21
    //   711: astore 10
    //   713: aload 12
    //   715: astore 15
    //   717: iload 7
    //   719: istore_3
    //   720: aload 23
    //   722: astore 9
    //   724: aload 12
    //   726: astore 17
    //   728: aload 24
    //   730: astore 11
    //   732: new 120	java/net/URI
    //   735: astore 25
    //   737: aload 12
    //   739: astore 16
    //   741: iload 5
    //   743: istore_2
    //   744: aload 21
    //   746: astore 10
    //   748: aload 12
    //   750: astore 15
    //   752: iload 7
    //   754: istore_3
    //   755: aload 23
    //   757: astore 9
    //   759: aload 12
    //   761: astore 17
    //   763: aload 24
    //   765: astore 11
    //   767: aload 25
    //   769: aload 26
    //   771: invokespecial 186	java/net/URI:<init>	(Ljava/lang/String;)V
    //   774: aload 12
    //   776: astore 16
    //   778: iload 5
    //   780: istore_2
    //   781: aload 21
    //   783: astore 10
    //   785: aload 12
    //   787: astore 15
    //   789: iload 7
    //   791: istore_3
    //   792: aload 23
    //   794: astore 9
    //   796: aload 12
    //   798: astore 17
    //   800: aload 24
    //   802: astore 11
    //   804: aload_1
    //   805: aload_0
    //   806: getfield 118	com/facebook/internal/ImageDownloader$RequestKey:uri	Ljava/net/URI;
    //   809: aload 25
    //   811: invokestatic 190	com/facebook/internal/UrlRedirectCache:cacheUriRedirect	(Landroid/content/Context;Ljava/net/URI;Ljava/net/URI;)V
    //   814: aload 12
    //   816: astore 16
    //   818: iload 5
    //   820: istore_2
    //   821: aload 21
    //   823: astore 10
    //   825: aload 12
    //   827: astore 15
    //   829: iload 7
    //   831: istore_3
    //   832: aload 23
    //   834: astore 9
    //   836: aload 12
    //   838: astore 17
    //   840: aload 24
    //   842: astore 11
    //   844: aload_0
    //   845: invokestatic 194	com/facebook/internal/ImageDownloader:removePendingRequest	(Lcom/facebook/internal/ImageDownloader$RequestKey;)Lcom/facebook/internal/ImageDownloader$DownloaderContext;
    //   848: astore_1
    //   849: aload 19
    //   851: astore 14
    //   853: aload 20
    //   855: astore 13
    //   857: iload 6
    //   859: istore 4
    //   861: aload 22
    //   863: astore 8
    //   865: aload_1
    //   866: ifnull +220 -> 1086
    //   869: aload 12
    //   871: astore 16
    //   873: iload 5
    //   875: istore_2
    //   876: aload 21
    //   878: astore 10
    //   880: aload 19
    //   882: astore 14
    //   884: aload 20
    //   886: astore 13
    //   888: iload 6
    //   890: istore 4
    //   892: aload 22
    //   894: astore 8
    //   896: aload 12
    //   898: astore 15
    //   900: iload 7
    //   902: istore_3
    //   903: aload 23
    //   905: astore 9
    //   907: aload 12
    //   909: astore 17
    //   911: aload 24
    //   913: astore 11
    //   915: aload_1
    //   916: getfield 99	com/facebook/internal/ImageDownloader$DownloaderContext:isCancelled	Z
    //   919: ifne +167 -> 1086
    //   922: aload 12
    //   924: astore 16
    //   926: iload 5
    //   928: istore_2
    //   929: aload 21
    //   931: astore 10
    //   933: aload 12
    //   935: astore 15
    //   937: iload 7
    //   939: istore_3
    //   940: aload 23
    //   942: astore 9
    //   944: aload 12
    //   946: astore 17
    //   948: aload 24
    //   950: astore 11
    //   952: aload_1
    //   953: getfield 198	com/facebook/internal/ImageDownloader$DownloaderContext:request	Lcom/facebook/internal/ImageRequest;
    //   956: astore_1
    //   957: aload 12
    //   959: astore 16
    //   961: iload 5
    //   963: istore_2
    //   964: aload 21
    //   966: astore 10
    //   968: aload 12
    //   970: astore 15
    //   972: iload 7
    //   974: istore_3
    //   975: aload 23
    //   977: astore 9
    //   979: aload 12
    //   981: astore 17
    //   983: aload 24
    //   985: astore 11
    //   987: new 17	com/facebook/internal/ImageDownloader$RequestKey
    //   990: astore 8
    //   992: aload 12
    //   994: astore 16
    //   996: iload 5
    //   998: istore_2
    //   999: aload 21
    //   1001: astore 10
    //   1003: aload 12
    //   1005: astore 15
    //   1007: iload 7
    //   1009: istore_3
    //   1010: aload 23
    //   1012: astore 9
    //   1014: aload 12
    //   1016: astore 17
    //   1018: aload 24
    //   1020: astore 11
    //   1022: aload 8
    //   1024: aload 25
    //   1026: aload_0
    //   1027: getfield 202	com/facebook/internal/ImageDownloader$RequestKey:tag	Ljava/lang/Object;
    //   1030: invokespecial 76	com/facebook/internal/ImageDownloader$RequestKey:<init>	(Ljava/net/URI;Ljava/lang/Object;)V
    //   1033: aload 12
    //   1035: astore 16
    //   1037: iload 5
    //   1039: istore_2
    //   1040: aload 21
    //   1042: astore 10
    //   1044: aload 12
    //   1046: astore 15
    //   1048: iload 7
    //   1050: istore_3
    //   1051: aload 23
    //   1053: astore 9
    //   1055: aload 12
    //   1057: astore 17
    //   1059: aload 24
    //   1061: astore 11
    //   1063: aload_1
    //   1064: aload 8
    //   1066: iconst_0
    //   1067: invokestatic 206	com/facebook/internal/ImageDownloader:enqueueCacheRead	(Lcom/facebook/internal/ImageRequest;Lcom/facebook/internal/ImageDownloader$RequestKey;Z)V
    //   1070: aload 22
    //   1072: astore 8
    //   1074: iload 6
    //   1076: istore 4
    //   1078: aload 20
    //   1080: astore 13
    //   1082: aload 19
    //   1084: astore 14
    //   1086: aload 8
    //   1088: invokestatic 167	com/facebook/internal/Utility:closeQuietly	(Ljava/io/Closeable;)V
    //   1091: aload 12
    //   1093: invokestatic 171	com/facebook/internal/Utility:disconnectQuietly	(Ljava/net/URLConnection;)V
    //   1096: iload 4
    //   1098: istore_2
    //   1099: goto -513 -> 586
    //   1102: aload 12
    //   1104: astore 16
    //   1106: iload 4
    //   1108: istore_2
    //   1109: aload 21
    //   1111: astore 10
    //   1113: aload 12
    //   1115: astore 15
    //   1117: iload 6
    //   1119: istore_3
    //   1120: aload 23
    //   1122: astore 9
    //   1124: aload 12
    //   1126: astore 17
    //   1128: aload 24
    //   1130: astore 11
    //   1132: aload_1
    //   1133: aload 12
    //   1135: invokestatic 210	com/facebook/internal/ImageResponseCache:interceptAndCacheImageStream	(Landroid/content/Context;Ljava/net/HttpURLConnection;)Ljava/io/InputStream;
    //   1138: astore_1
    //   1139: aload 12
    //   1141: astore 16
    //   1143: iload 4
    //   1145: istore_2
    //   1146: aload_1
    //   1147: astore 10
    //   1149: aload 12
    //   1151: astore 15
    //   1153: iload 6
    //   1155: istore_3
    //   1156: aload_1
    //   1157: astore 9
    //   1159: aload 12
    //   1161: astore 17
    //   1163: aload_1
    //   1164: astore 11
    //   1166: aload_1
    //   1167: invokestatic 216	android/graphics/BitmapFactory:decodeStream	(Ljava/io/InputStream;)Landroid/graphics/Bitmap;
    //   1170: astore 14
    //   1172: aload 20
    //   1174: astore 13
    //   1176: iload 5
    //   1178: istore 4
    //   1180: aload_1
    //   1181: astore 8
    //   1183: goto -97 -> 1086
    //   1186: aload 12
    //   1188: astore 16
    //   1190: iload 4
    //   1192: istore_2
    //   1193: aload 8
    //   1195: astore 10
    //   1197: aload 12
    //   1199: astore 15
    //   1201: iload 6
    //   1203: istore_3
    //   1204: aload 8
    //   1206: astore 9
    //   1208: aload 12
    //   1210: astore 17
    //   1212: aload 8
    //   1214: astore 11
    //   1216: aload_1
    //   1217: invokestatic 167	com/facebook/internal/Utility:closeQuietly	(Ljava/io/Closeable;)V
    //   1220: aload 12
    //   1222: astore 16
    //   1224: iload 4
    //   1226: istore_2
    //   1227: aload 8
    //   1229: astore 10
    //   1231: aload 12
    //   1233: astore 15
    //   1235: iload 6
    //   1237: istore_3
    //   1238: aload 8
    //   1240: astore 9
    //   1242: aload 12
    //   1244: astore 17
    //   1246: aload 8
    //   1248: astore 11
    //   1250: new 218	com/facebook/FacebookException
    //   1253: dup
    //   1254: aload 13
    //   1256: invokevirtual 219	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1259: invokespecial 220	com/facebook/FacebookException:<init>	(Ljava/lang/String;)V
    //   1262: astore 13
    //   1264: aload 19
    //   1266: astore 14
    //   1268: iload 5
    //   1270: istore 4
    //   1272: goto -186 -> 1086
    //   1275: aload 12
    //   1277: astore 16
    //   1279: iload 4
    //   1281: istore_2
    //   1282: aload 8
    //   1284: astore 10
    //   1286: aload 12
    //   1288: astore 15
    //   1290: iload 6
    //   1292: istore_3
    //   1293: aload 8
    //   1295: astore 9
    //   1297: aload 12
    //   1299: astore 17
    //   1301: aload 8
    //   1303: astore 11
    //   1305: aload 13
    //   1307: aload_1
    //   1308: getstatic 225	com/facebook/android/R$string:com_facebook_image_download_unknown_error	I
    //   1311: invokevirtual 231	android/content/Context:getString	(I)Ljava/lang/String;
    //   1314: invokevirtual 234	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1317: pop
    //   1318: goto -98 -> 1220
    //   1321: astore 13
    //   1323: aload 9
    //   1325: invokestatic 167	com/facebook/internal/Utility:closeQuietly	(Ljava/io/Closeable;)V
    //   1328: aload 15
    //   1330: invokestatic 171	com/facebook/internal/Utility:disconnectQuietly	(Ljava/net/URLConnection;)V
    //   1333: aload 18
    //   1335: astore 14
    //   1337: iload_3
    //   1338: istore_2
    //   1339: goto -753 -> 586
    //   1342: astore_0
    //   1343: aload 11
    //   1345: invokestatic 167	com/facebook/internal/Utility:closeQuietly	(Ljava/io/Closeable;)V
    //   1348: aload 17
    //   1350: invokestatic 171	com/facebook/internal/Utility:disconnectQuietly	(Ljava/net/URLConnection;)V
    //   1353: aload_0
    //   1354: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	1355	0	paramRequestKey	RequestKey
    //   0	1355	1	paramContext	Context
    //   45	1294	2	i	int
    //   56	1282	3	j	int
    //   37	1243	4	k	int
    //   31	1238	5	m	int
    //   34	1257	6	n	int
    //   519	530	7	i1	int
    //   7	1295	8	localObject1	Object
    //   59	1265	9	localObject2	Object
    //   48	1237	10	localObject3	Object
    //   67	1277	11	localObject4	Object
    //   1	1297	12	localHttpURLConnection1	java.net.HttpURLConnection
    //   4	553	13	localStringBuilder	StringBuilder
    //   570	22	13	localIOException	java.io.IOException
    //   665	641	13	localObject5	Object
    //   1321	1	13	localURISyntaxException	java.net.URISyntaxException
    //   72	1264	14	localObject6	Object
    //   52	1277	15	localHttpURLConnection2	java.net.HttpURLConnection
    //   41	1237	16	localObject7	Object
    //   63	1286	17	localObject8	Object
    //   28	1306	18	localObject9	Object
    //   25	1240	19	localObject10	Object
    //   22	1151	20	localObject11	Object
    //   19	1091	21	localObject12	Object
    //   10	1061	22	localObject13	Object
    //   13	1108	23	localObject14	Object
    //   16	1113	24	localObject15	Object
    //   735	290	25	localURI	URI
    //   646	124	26	str	String
    // Exception table:
    //   from	to	target	type
    //   69	74	570	java/io/IOException
    //   104	116	570	java/io/IOException
    //   146	156	570	java/io/IOException
    //   186	192	570	java/io/IOException
    //   222	260	570	java/io/IOException
    //   290	297	570	java/io/IOException
    //   327	332	570	java/io/IOException
    //   362	367	570	java/io/IOException
    //   402	406	570	java/io/IOException
    //   436	442	570	java/io/IOException
    //   472	479	570	java/io/IOException
    //   509	521	570	java/io/IOException
    //   556	567	570	java/io/IOException
    //   639	648	570	java/io/IOException
    //   694	702	570	java/io/IOException
    //   732	737	570	java/io/IOException
    //   767	774	570	java/io/IOException
    //   804	814	570	java/io/IOException
    //   844	849	570	java/io/IOException
    //   915	922	570	java/io/IOException
    //   952	957	570	java/io/IOException
    //   987	992	570	java/io/IOException
    //   1022	1033	570	java/io/IOException
    //   1063	1070	570	java/io/IOException
    //   1132	1139	570	java/io/IOException
    //   1166	1172	570	java/io/IOException
    //   1216	1220	570	java/io/IOException
    //   1250	1264	570	java/io/IOException
    //   1305	1318	570	java/io/IOException
    //   69	74	1321	java/net/URISyntaxException
    //   104	116	1321	java/net/URISyntaxException
    //   146	156	1321	java/net/URISyntaxException
    //   186	192	1321	java/net/URISyntaxException
    //   222	260	1321	java/net/URISyntaxException
    //   290	297	1321	java/net/URISyntaxException
    //   327	332	1321	java/net/URISyntaxException
    //   362	367	1321	java/net/URISyntaxException
    //   402	406	1321	java/net/URISyntaxException
    //   436	442	1321	java/net/URISyntaxException
    //   472	479	1321	java/net/URISyntaxException
    //   509	521	1321	java/net/URISyntaxException
    //   556	567	1321	java/net/URISyntaxException
    //   639	648	1321	java/net/URISyntaxException
    //   694	702	1321	java/net/URISyntaxException
    //   732	737	1321	java/net/URISyntaxException
    //   767	774	1321	java/net/URISyntaxException
    //   804	814	1321	java/net/URISyntaxException
    //   844	849	1321	java/net/URISyntaxException
    //   915	922	1321	java/net/URISyntaxException
    //   952	957	1321	java/net/URISyntaxException
    //   987	992	1321	java/net/URISyntaxException
    //   1022	1033	1321	java/net/URISyntaxException
    //   1063	1070	1321	java/net/URISyntaxException
    //   1132	1139	1321	java/net/URISyntaxException
    //   1166	1172	1321	java/net/URISyntaxException
    //   1216	1220	1321	java/net/URISyntaxException
    //   1250	1264	1321	java/net/URISyntaxException
    //   1305	1318	1321	java/net/URISyntaxException
    //   69	74	1342	finally
    //   104	116	1342	finally
    //   146	156	1342	finally
    //   186	192	1342	finally
    //   222	260	1342	finally
    //   290	297	1342	finally
    //   327	332	1342	finally
    //   362	367	1342	finally
    //   402	406	1342	finally
    //   436	442	1342	finally
    //   472	479	1342	finally
    //   509	521	1342	finally
    //   556	567	1342	finally
    //   639	648	1342	finally
    //   694	702	1342	finally
    //   732	737	1342	finally
    //   767	774	1342	finally
    //   804	814	1342	finally
    //   844	849	1342	finally
    //   915	922	1342	finally
    //   952	957	1342	finally
    //   987	992	1342	finally
    //   1022	1033	1342	finally
    //   1063	1070	1342	finally
    //   1132	1139	1342	finally
    //   1166	1172	1342	finally
    //   1216	1220	1342	finally
    //   1250	1264	1342	finally
    //   1305	1318	1342	finally
  }
  
  public static void downloadAsync(ImageRequest paramImageRequest)
  {
    if (paramImageRequest == null) {
      return;
    }
    RequestKey localRequestKey = new RequestKey(paramImageRequest.getImageUri(), paramImageRequest.getCallerTag());
    for (;;)
    {
      synchronized (pendingRequests)
      {
        DownloaderContext localDownloaderContext = (DownloaderContext)pendingRequests.get(localRequestKey);
        if (localDownloaderContext != null)
        {
          localDownloaderContext.request = paramImageRequest;
          localDownloaderContext.isCancelled = false;
          localDownloaderContext.workItem.moveToFront();
        }
      }
      enqueueCacheRead(paramImageRequest, localRequestKey, paramImageRequest.isCachedRedirectAllowed());
    }
  }
  
  private static void enqueueCacheRead(ImageRequest paramImageRequest, RequestKey paramRequestKey, boolean paramBoolean)
  {
    enqueueRequest(paramImageRequest, paramRequestKey, cacheReadQueue, new CacheReadWorkItem(paramImageRequest.getContext(), paramRequestKey, paramBoolean));
  }
  
  private static void enqueueDownload(ImageRequest paramImageRequest, RequestKey paramRequestKey)
  {
    enqueueRequest(paramImageRequest, paramRequestKey, downloadQueue, new DownloadImageWorkItem(paramImageRequest.getContext(), paramRequestKey));
  }
  
  private static void enqueueRequest(ImageRequest paramImageRequest, RequestKey paramRequestKey, WorkQueue paramWorkQueue, Runnable paramRunnable)
  {
    synchronized (pendingRequests)
    {
      DownloaderContext localDownloaderContext = new com/facebook/internal/ImageDownloader$DownloaderContext;
      localDownloaderContext.<init>(null);
      localDownloaderContext.request = paramImageRequest;
      pendingRequests.put(paramRequestKey, localDownloaderContext);
      localDownloaderContext.workItem = paramWorkQueue.addActiveWorkItem(paramRunnable);
      return;
    }
  }
  
  private static Handler getHandler()
  {
    try
    {
      if (handler == null)
      {
        localHandler = new android/os/Handler;
        localHandler.<init>(Looper.getMainLooper());
        handler = localHandler;
      }
      Handler localHandler = handler;
      return localHandler;
    }
    finally {}
  }
  
  private static void issueResponse(RequestKey paramRequestKey, final Exception paramException, final Bitmap paramBitmap, final boolean paramBoolean)
  {
    paramRequestKey = removePendingRequest(paramRequestKey);
    if ((paramRequestKey != null) && (!paramRequestKey.isCancelled))
    {
      paramRequestKey = paramRequestKey.request;
      final ImageRequest.Callback localCallback = paramRequestKey.getCallback();
      if (localCallback != null) {
        getHandler().post(new Runnable()
        {
          public void run()
          {
            ImageResponse localImageResponse = new ImageResponse(this.val$request, paramException, paramBoolean, paramBitmap);
            localCallback.onCompleted(localImageResponse);
          }
        });
      }
    }
  }
  
  public static void prioritizeRequest(ImageRequest arg0)
  {
    Object localObject1 = new RequestKey(???.getImageUri(), ???.getCallerTag());
    synchronized (pendingRequests)
    {
      localObject1 = (DownloaderContext)pendingRequests.get(localObject1);
      if (localObject1 != null) {
        ((DownloaderContext)localObject1).workItem.moveToFront();
      }
      return;
    }
  }
  
  private static void readFromCache(RequestKey paramRequestKey, Context paramContext, boolean paramBoolean)
  {
    Object localObject2 = null;
    boolean bool2 = false;
    Object localObject1 = localObject2;
    boolean bool1 = bool2;
    if (paramBoolean)
    {
      URI localURI = UrlRedirectCache.getRedirectedUri(paramContext, paramRequestKey.uri);
      localObject1 = localObject2;
      bool1 = bool2;
      if (localURI != null)
      {
        localObject1 = ImageResponseCache.getCachedImageStream(localURI, paramContext);
        if (localObject1 == null) {
          break label92;
        }
        bool1 = true;
      }
    }
    if (!bool1) {
      localObject1 = ImageResponseCache.getCachedImageStream(paramRequestKey.uri, paramContext);
    }
    if (localObject1 != null)
    {
      paramContext = BitmapFactory.decodeStream((InputStream)localObject1);
      Utility.closeQuietly((Closeable)localObject1);
      issueResponse(paramRequestKey, null, paramContext, bool1);
    }
    for (;;)
    {
      return;
      label92:
      bool1 = false;
      break;
      paramContext = removePendingRequest(paramRequestKey);
      if ((paramContext != null) && (!paramContext.isCancelled)) {
        enqueueDownload(paramContext.request, paramRequestKey);
      }
    }
  }
  
  private static DownloaderContext removePendingRequest(RequestKey paramRequestKey)
  {
    synchronized (pendingRequests)
    {
      paramRequestKey = (DownloaderContext)pendingRequests.remove(paramRequestKey);
      return paramRequestKey;
    }
  }
  
  private static class CacheReadWorkItem
    implements Runnable
  {
    private boolean allowCachedRedirects;
    private Context context;
    private ImageDownloader.RequestKey key;
    
    CacheReadWorkItem(Context paramContext, ImageDownloader.RequestKey paramRequestKey, boolean paramBoolean)
    {
      this.context = paramContext;
      this.key = paramRequestKey;
      this.allowCachedRedirects = paramBoolean;
    }
    
    public void run()
    {
      ImageDownloader.readFromCache(this.key, this.context, this.allowCachedRedirects);
    }
  }
  
  private static class DownloadImageWorkItem
    implements Runnable
  {
    private Context context;
    private ImageDownloader.RequestKey key;
    
    DownloadImageWorkItem(Context paramContext, ImageDownloader.RequestKey paramRequestKey)
    {
      this.context = paramContext;
      this.key = paramRequestKey;
    }
    
    public void run()
    {
      ImageDownloader.download(this.key, this.context);
    }
  }
  
  private static class DownloaderContext
  {
    boolean isCancelled;
    ImageRequest request;
    WorkQueue.WorkItem workItem;
  }
  
  private static class RequestKey
  {
    private static final int HASH_MULTIPLIER = 37;
    private static final int HASH_SEED = 29;
    Object tag;
    URI uri;
    
    RequestKey(URI paramURI, Object paramObject)
    {
      this.uri = paramURI;
      this.tag = paramObject;
    }
    
    public boolean equals(Object paramObject)
    {
      boolean bool2 = false;
      boolean bool1 = bool2;
      if (paramObject != null)
      {
        bool1 = bool2;
        if ((paramObject instanceof RequestKey))
        {
          paramObject = (RequestKey)paramObject;
          if ((((RequestKey)paramObject).uri != this.uri) || (((RequestKey)paramObject).tag != this.tag)) {
            break label48;
          }
        }
      }
      label48:
      for (bool1 = true;; bool1 = false) {
        return bool1;
      }
    }
    
    public int hashCode()
    {
      return (this.uri.hashCode() + 1073) * 37 + this.tag.hashCode();
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\facebook\internal\ImageDownloader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */