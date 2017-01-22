package com.google.android.gms.auth;

import android.accounts.AccountManager;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.internal.o;
import com.google.android.gms.internal.if;
import java.io.IOException;
import java.net.URISyntaxException;

public final class GoogleAuthUtil
{
  public static final int CHANGE_TYPE_ACCOUNT_ADDED = 1;
  public static final int CHANGE_TYPE_ACCOUNT_REMOVED = 2;
  public static final int CHANGE_TYPE_ACCOUNT_RENAMED_FROM = 3;
  public static final int CHANGE_TYPE_ACCOUNT_RENAMED_TO = 4;
  private static final ComponentName Dn;
  private static final ComponentName Do;
  private static final Intent Dp;
  private static final Intent Dq;
  public static final String GOOGLE_ACCOUNT_TYPE = "com.google";
  public static final String KEY_ANDROID_PACKAGE_NAME;
  public static final String KEY_CALLER_UID;
  public static final String KEY_REQUEST_ACTIONS = "request_visible_actions";
  @Deprecated
  public static final String KEY_REQUEST_VISIBLE_ACTIVITIES = "request_visible_actions";
  public static final String KEY_SUPPRESS_PROGRESS_SCREEN = "suppressProgressScreen";
  
  static
  {
    if (Build.VERSION.SDK_INT >= 11)
    {
      KEY_CALLER_UID = "callerUid";
      if (Build.VERSION.SDK_INT < 14) {
        break label100;
      }
    }
    label100:
    for (;;)
    {
      KEY_ANDROID_PACKAGE_NAME = "androidPackageName";
      Dn = new ComponentName("com.google.android.gms", "com.google.android.gms.auth.GetToken");
      Do = new ComponentName("com.google.android.gms", "com.google.android.gms.recovery.RecoveryService");
      Dp = new Intent().setPackage("com.google.android.gms").setComponent(Dn);
      Dq = new Intent().setPackage("com.google.android.gms").setComponent(Do);
      return;
      break;
    }
  }
  
  private static void D(Context paramContext)
    throws GoogleAuthException
  {
    try
    {
      GooglePlayServicesUtil.D(paramContext);
      return;
    }
    catch (GooglePlayServicesRepairableException paramContext)
    {
      throw new GooglePlayServicesAvailabilityException(paramContext.getConnectionStatusCode(), paramContext.getMessage(), paramContext.getIntent());
    }
    catch (GooglePlayServicesNotAvailableException paramContext)
    {
      throw new GoogleAuthException(paramContext.getMessage());
    }
  }
  
  private static String a(Context paramContext, String paramString1, String paramString2, Bundle paramBundle)
    throws IOException, UserRecoverableNotifiedException, GoogleAuthException
  {
    Bundle localBundle = paramBundle;
    if (paramBundle == null) {
      localBundle = new Bundle();
    }
    try
    {
      paramString1 = getToken(paramContext, paramString1, paramString2, localBundle);
      return paramString1;
    }
    catch (GooglePlayServicesAvailabilityException paramString1)
    {
      int i = paramString1.getConnectionStatusCode();
      if (b(paramContext, i))
      {
        paramContext = new a(paramContext.getApplicationContext());
        paramContext.sendMessageDelayed(paramContext.obtainMessage(1), 30000L);
      }
      for (;;)
      {
        throw new UserRecoverableNotifiedException("User intervention required. Notification has been pushed.");
        GooglePlayServicesUtil.showErrorNotification(i, paramContext);
      }
    }
    catch (UserRecoverableAuthException paramContext)
    {
      throw new UserRecoverableNotifiedException("User intervention required. Notification has been pushed.");
    }
  }
  
  private static boolean aw(String paramString)
  {
    if (("NetworkError".equals(paramString)) || ("ServiceUnavailable".equals(paramString)) || ("Timeout".equals(paramString))) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  private static boolean ax(String paramString)
  {
    if (("BadAuthentication".equals(paramString)) || ("CaptchaRequired".equals(paramString)) || ("DeviceManagementRequiredOrSyncDisabled".equals(paramString)) || ("NeedPermission".equals(paramString)) || ("NeedsBrowser".equals(paramString)) || ("UserCancel".equals(paramString)) || ("AppDownloadRequired".equals(paramString)) || (if.DT.ft().equals(paramString)) || (if.DU.ft().equals(paramString)) || (if.DV.ft().equals(paramString)) || (if.DW.ft().equals(paramString)) || (if.DX.ft().equals(paramString)) || (if.DY.ft().equals(paramString))) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  private static boolean b(Context paramContext, int paramInt)
  {
    boolean bool1 = true;
    if (paramInt == 1) {
      paramContext = paramContext.getPackageManager();
    }
    for (;;)
    {
      try
      {
        boolean bool2 = paramContext.getApplicationInfo("com.google.android.gms", 8192).enabled;
        if (bool2) {
          return bool1;
        }
      }
      catch (PackageManager.NameNotFoundException paramContext) {}
      bool1 = false;
    }
  }
  
  /* Error */
  public static void clearToken(Context paramContext, String paramString)
    throws GooglePlayServicesAvailabilityException, GoogleAuthException, IOException
  {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 143	android/content/Context:getApplicationContext	()Landroid/content/Context;
    //   4: astore_2
    //   5: ldc -13
    //   7: invokestatic 248	com/google/android/gms/common/internal/o:aU	(Ljava/lang/String;)V
    //   10: aload_2
    //   11: invokestatic 249	com/google/android/gms/auth/GoogleAuthUtil:D	(Landroid/content/Context;)V
    //   14: new 128	android/os/Bundle
    //   17: dup
    //   18: invokespecial 129	android/os/Bundle:<init>	()V
    //   21: astore_3
    //   22: aload_0
    //   23: invokevirtual 252	android/content/Context:getApplicationInfo	()Landroid/content/pm/ApplicationInfo;
    //   26: getfield 255	android/content/pm/ApplicationInfo:packageName	Ljava/lang/String;
    //   29: astore_0
    //   30: aload_3
    //   31: ldc_w 257
    //   34: aload_0
    //   35: invokevirtual 260	android/os/Bundle:putString	(Ljava/lang/String;Ljava/lang/String;)V
    //   38: aload_3
    //   39: getstatic 51	com/google/android/gms/auth/GoogleAuthUtil:KEY_ANDROID_PACKAGE_NAME	Ljava/lang/String;
    //   42: invokevirtual 263	android/os/Bundle:containsKey	(Ljava/lang/String;)Z
    //   45: ifne +11 -> 56
    //   48: aload_3
    //   49: getstatic 51	com/google/android/gms/auth/GoogleAuthUtil:KEY_ANDROID_PACKAGE_NAME	Ljava/lang/String;
    //   52: aload_0
    //   53: invokevirtual 260	android/os/Bundle:putString	(Ljava/lang/String;Ljava/lang/String;)V
    //   56: new 265	com/google/android/gms/common/a
    //   59: dup
    //   60: invokespecial 266	com/google/android/gms/common/a:<init>	()V
    //   63: astore_0
    //   64: aload_2
    //   65: getstatic 81	com/google/android/gms/auth/GoogleAuthUtil:Dp	Landroid/content/Intent;
    //   68: aload_0
    //   69: iconst_1
    //   70: invokevirtual 270	android/content/Context:bindService	(Landroid/content/Intent;Landroid/content/ServiceConnection;I)Z
    //   73: ifeq +100 -> 173
    //   76: aload_0
    //   77: invokevirtual 274	com/google/android/gms/common/a:fW	()Landroid/os/IBinder;
    //   80: invokestatic 279	com/google/android/gms/internal/r$a:a	(Landroid/os/IBinder;)Lcom/google/android/gms/internal/r;
    //   83: aload_1
    //   84: aload_3
    //   85: invokeinterface 284 3 0
    //   90: astore_3
    //   91: aload_3
    //   92: getstatic 287	com/google/android/gms/internal/if:Ev	Ljava/lang/String;
    //   95: invokevirtual 291	android/os/Bundle:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   98: astore_1
    //   99: aload_3
    //   100: ldc_w 293
    //   103: invokevirtual 296	android/os/Bundle:getBoolean	(Ljava/lang/String;)Z
    //   106: ifne +47 -> 153
    //   109: new 89	com/google/android/gms/auth/GoogleAuthException
    //   112: astore_3
    //   113: aload_3
    //   114: aload_1
    //   115: invokespecial 118	com/google/android/gms/auth/GoogleAuthException:<init>	(Ljava/lang/String;)V
    //   118: aload_3
    //   119: athrow
    //   120: astore_1
    //   121: ldc_w 298
    //   124: ldc_w 300
    //   127: aload_1
    //   128: invokestatic 306	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   131: pop
    //   132: new 122	java/io/IOException
    //   135: astore_1
    //   136: aload_1
    //   137: ldc_w 308
    //   140: invokespecial 309	java/io/IOException:<init>	(Ljava/lang/String;)V
    //   143: aload_1
    //   144: athrow
    //   145: astore_1
    //   146: aload_2
    //   147: aload_0
    //   148: invokevirtual 313	android/content/Context:unbindService	(Landroid/content/ServiceConnection;)V
    //   151: aload_1
    //   152: athrow
    //   153: aload_2
    //   154: aload_0
    //   155: invokevirtual 313	android/content/Context:unbindService	(Landroid/content/ServiceConnection;)V
    //   158: return
    //   159: astore_1
    //   160: new 89	com/google/android/gms/auth/GoogleAuthException
    //   163: astore_1
    //   164: aload_1
    //   165: ldc_w 315
    //   168: invokespecial 118	com/google/android/gms/auth/GoogleAuthException:<init>	(Ljava/lang/String;)V
    //   171: aload_1
    //   172: athrow
    //   173: new 122	java/io/IOException
    //   176: dup
    //   177: ldc_w 317
    //   180: invokespecial 309	java/io/IOException:<init>	(Ljava/lang/String;)V
    //   183: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	184	0	paramContext	Context
    //   0	184	1	paramString	String
    //   4	150	2	localContext	Context
    //   21	98	3	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   76	120	120	android/os/RemoteException
    //   76	120	145	finally
    //   121	145	145	finally
    //   160	173	145	finally
    //   76	120	159	java/lang/InterruptedException
  }
  
  /* Error */
  public static java.util.List<AccountChangeEvent> getAccountChangeEvents(Context paramContext, int paramInt, String paramString)
    throws GoogleAuthException, IOException
  {
    // Byte code:
    //   0: aload_2
    //   1: ldc_w 321
    //   4: invokestatic 324	com/google/android/gms/common/internal/o:b	(Ljava/lang/String;Ljava/lang/Object;)Ljava/lang/String;
    //   7: pop
    //   8: ldc -13
    //   10: invokestatic 248	com/google/android/gms/common/internal/o:aU	(Ljava/lang/String;)V
    //   13: aload_0
    //   14: invokevirtual 143	android/content/Context:getApplicationContext	()Landroid/content/Context;
    //   17: astore_0
    //   18: aload_0
    //   19: invokestatic 249	com/google/android/gms/auth/GoogleAuthUtil:D	(Landroid/content/Context;)V
    //   22: new 265	com/google/android/gms/common/a
    //   25: dup
    //   26: invokespecial 266	com/google/android/gms/common/a:<init>	()V
    //   29: astore_3
    //   30: aload_0
    //   31: getstatic 81	com/google/android/gms/auth/GoogleAuthUtil:Dp	Landroid/content/Intent;
    //   34: aload_3
    //   35: iconst_1
    //   36: invokevirtual 270	android/content/Context:bindService	(Landroid/content/Intent;Landroid/content/ServiceConnection;I)Z
    //   39: ifeq +97 -> 136
    //   42: aload_3
    //   43: invokevirtual 274	com/google/android/gms/common/a:fW	()Landroid/os/IBinder;
    //   46: invokestatic 279	com/google/android/gms/internal/r$a:a	(Landroid/os/IBinder;)Lcom/google/android/gms/internal/r;
    //   49: astore 5
    //   51: new 326	com/google/android/gms/auth/AccountChangeEventsRequest
    //   54: astore 4
    //   56: aload 4
    //   58: invokespecial 327	com/google/android/gms/auth/AccountChangeEventsRequest:<init>	()V
    //   61: aload 5
    //   63: aload 4
    //   65: aload_2
    //   66: invokevirtual 331	com/google/android/gms/auth/AccountChangeEventsRequest:setAccountName	(Ljava/lang/String;)Lcom/google/android/gms/auth/AccountChangeEventsRequest;
    //   69: iload_1
    //   70: invokevirtual 335	com/google/android/gms/auth/AccountChangeEventsRequest:setEventIndex	(I)Lcom/google/android/gms/auth/AccountChangeEventsRequest;
    //   73: invokeinterface 338 2 0
    //   78: invokevirtual 344	com/google/android/gms/auth/AccountChangeEventsResponse:getEvents	()Ljava/util/List;
    //   81: astore_2
    //   82: aload_0
    //   83: aload_3
    //   84: invokevirtual 313	android/content/Context:unbindService	(Landroid/content/ServiceConnection;)V
    //   87: aload_2
    //   88: areturn
    //   89: astore_2
    //   90: ldc_w 298
    //   93: ldc_w 300
    //   96: aload_2
    //   97: invokestatic 306	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   100: pop
    //   101: new 122	java/io/IOException
    //   104: astore_2
    //   105: aload_2
    //   106: ldc_w 308
    //   109: invokespecial 309	java/io/IOException:<init>	(Ljava/lang/String;)V
    //   112: aload_2
    //   113: athrow
    //   114: astore_2
    //   115: aload_0
    //   116: aload_3
    //   117: invokevirtual 313	android/content/Context:unbindService	(Landroid/content/ServiceConnection;)V
    //   120: aload_2
    //   121: athrow
    //   122: astore_2
    //   123: new 89	com/google/android/gms/auth/GoogleAuthException
    //   126: astore_2
    //   127: aload_2
    //   128: ldc_w 315
    //   131: invokespecial 118	com/google/android/gms/auth/GoogleAuthException:<init>	(Ljava/lang/String;)V
    //   134: aload_2
    //   135: athrow
    //   136: new 122	java/io/IOException
    //   139: dup
    //   140: ldc_w 317
    //   143: invokespecial 309	java/io/IOException:<init>	(Ljava/lang/String;)V
    //   146: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	147	0	paramContext	Context
    //   0	147	1	paramInt	int
    //   0	147	2	paramString	String
    //   29	88	3	locala	com.google.android.gms.common.a
    //   54	10	4	localAccountChangeEventsRequest	AccountChangeEventsRequest
    //   49	13	5	localr	com.google.android.gms.internal.r
    // Exception table:
    //   from	to	target	type
    //   42	82	89	android/os/RemoteException
    //   42	82	114	finally
    //   90	114	114	finally
    //   123	136	114	finally
    //   42	82	122	java/lang/InterruptedException
  }
  
  public static String getAccountId(Context paramContext, String paramString)
    throws GoogleAuthException, IOException
  {
    o.b(paramString, "accountName must be provided");
    o.aU("Calling this from your main thread can lead to deadlock");
    D(paramContext.getApplicationContext());
    return getToken(paramContext, paramString, "^^_account_id_^^", new Bundle());
  }
  
  public static String getAppCert(Context paramContext, String paramString)
  {
    return "spatula";
  }
  
  public static String getToken(Context paramContext, String paramString1, String paramString2)
    throws IOException, UserRecoverableAuthException, GoogleAuthException
  {
    return getToken(paramContext, paramString1, paramString2, new Bundle());
  }
  
  /* Error */
  public static String getToken(Context paramContext, String paramString1, String paramString2, Bundle paramBundle)
    throws IOException, UserRecoverableAuthException, GoogleAuthException
  {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 143	android/content/Context:getApplicationContext	()Landroid/content/Context;
    //   4: astore 5
    //   6: ldc -13
    //   8: invokestatic 248	com/google/android/gms/common/internal/o:aU	(Ljava/lang/String;)V
    //   11: aload 5
    //   13: invokestatic 249	com/google/android/gms/auth/GoogleAuthUtil:D	(Landroid/content/Context;)V
    //   16: aload_3
    //   17: ifnonnull +109 -> 126
    //   20: new 128	android/os/Bundle
    //   23: dup
    //   24: invokespecial 129	android/os/Bundle:<init>	()V
    //   27: astore_3
    //   28: aload_0
    //   29: invokevirtual 252	android/content/Context:getApplicationInfo	()Landroid/content/pm/ApplicationInfo;
    //   32: getfield 255	android/content/pm/ApplicationInfo:packageName	Ljava/lang/String;
    //   35: astore_0
    //   36: aload_3
    //   37: ldc_w 257
    //   40: aload_0
    //   41: invokevirtual 260	android/os/Bundle:putString	(Ljava/lang/String;Ljava/lang/String;)V
    //   44: aload_3
    //   45: getstatic 51	com/google/android/gms/auth/GoogleAuthUtil:KEY_ANDROID_PACKAGE_NAME	Ljava/lang/String;
    //   48: invokevirtual 263	android/os/Bundle:containsKey	(Ljava/lang/String;)Z
    //   51: ifne +11 -> 62
    //   54: aload_3
    //   55: getstatic 51	com/google/android/gms/auth/GoogleAuthUtil:KEY_ANDROID_PACKAGE_NAME	Ljava/lang/String;
    //   58: aload_0
    //   59: invokevirtual 260	android/os/Bundle:putString	(Ljava/lang/String;Ljava/lang/String;)V
    //   62: new 265	com/google/android/gms/common/a
    //   65: dup
    //   66: invokespecial 266	com/google/android/gms/common/a:<init>	()V
    //   69: astore_0
    //   70: aload 5
    //   72: getstatic 81	com/google/android/gms/auth/GoogleAuthUtil:Dp	Landroid/content/Intent;
    //   75: aload_0
    //   76: iconst_1
    //   77: invokevirtual 270	android/content/Context:bindService	(Landroid/content/Intent;Landroid/content/ServiceConnection;I)Z
    //   80: ifeq +173 -> 253
    //   83: aload_0
    //   84: invokevirtual 274	com/google/android/gms/common/a:fW	()Landroid/os/IBinder;
    //   87: invokestatic 279	com/google/android/gms/internal/r$a:a	(Landroid/os/IBinder;)Lcom/google/android/gms/internal/r;
    //   90: aload_1
    //   91: aload_2
    //   92: aload_3
    //   93: invokeinterface 357 4 0
    //   98: astore_2
    //   99: aload_2
    //   100: ldc_w 359
    //   103: invokevirtual 291	android/os/Bundle:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   106: astore_1
    //   107: aload_1
    //   108: invokestatic 365	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   111: istore 4
    //   113: iload 4
    //   115: ifne +23 -> 138
    //   118: aload 5
    //   120: aload_0
    //   121: invokevirtual 313	android/content/Context:unbindService	(Landroid/content/ServiceConnection;)V
    //   124: aload_1
    //   125: areturn
    //   126: new 128	android/os/Bundle
    //   129: dup
    //   130: aload_3
    //   131: invokespecial 368	android/os/Bundle:<init>	(Landroid/os/Bundle;)V
    //   134: astore_3
    //   135: goto -107 -> 28
    //   138: aload_2
    //   139: ldc_w 370
    //   142: invokevirtual 291	android/os/Bundle:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   145: astore_1
    //   146: aload_2
    //   147: ldc_w 372
    //   150: invokevirtual 376	android/os/Bundle:getParcelable	(Ljava/lang/String;)Landroid/os/Parcelable;
    //   153: checkcast 69	android/content/Intent
    //   156: astore_2
    //   157: aload_1
    //   158: invokestatic 378	com/google/android/gms/auth/GoogleAuthUtil:ax	(Ljava/lang/String;)Z
    //   161: ifeq +49 -> 210
    //   164: new 126	com/google/android/gms/auth/UserRecoverableAuthException
    //   167: astore_3
    //   168: aload_3
    //   169: aload_1
    //   170: aload_2
    //   171: invokespecial 381	com/google/android/gms/auth/UserRecoverableAuthException:<init>	(Ljava/lang/String;Landroid/content/Intent;)V
    //   174: aload_3
    //   175: athrow
    //   176: astore_1
    //   177: ldc_w 298
    //   180: ldc_w 300
    //   183: aload_1
    //   184: invokestatic 306	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   187: pop
    //   188: new 122	java/io/IOException
    //   191: astore_1
    //   192: aload_1
    //   193: ldc_w 308
    //   196: invokespecial 309	java/io/IOException:<init>	(Ljava/lang/String;)V
    //   199: aload_1
    //   200: athrow
    //   201: astore_1
    //   202: aload 5
    //   204: aload_0
    //   205: invokevirtual 313	android/content/Context:unbindService	(Landroid/content/ServiceConnection;)V
    //   208: aload_1
    //   209: athrow
    //   210: aload_1
    //   211: invokestatic 383	com/google/android/gms/auth/GoogleAuthUtil:aw	(Ljava/lang/String;)Z
    //   214: ifeq +28 -> 242
    //   217: new 122	java/io/IOException
    //   220: astore_2
    //   221: aload_2
    //   222: aload_1
    //   223: invokespecial 309	java/io/IOException:<init>	(Ljava/lang/String;)V
    //   226: aload_2
    //   227: athrow
    //   228: astore_1
    //   229: new 89	com/google/android/gms/auth/GoogleAuthException
    //   232: astore_1
    //   233: aload_1
    //   234: ldc_w 315
    //   237: invokespecial 118	com/google/android/gms/auth/GoogleAuthException:<init>	(Ljava/lang/String;)V
    //   240: aload_1
    //   241: athrow
    //   242: new 89	com/google/android/gms/auth/GoogleAuthException
    //   245: astore_2
    //   246: aload_2
    //   247: aload_1
    //   248: invokespecial 118	com/google/android/gms/auth/GoogleAuthException:<init>	(Ljava/lang/String;)V
    //   251: aload_2
    //   252: athrow
    //   253: new 122	java/io/IOException
    //   256: dup
    //   257: ldc_w 317
    //   260: invokespecial 309	java/io/IOException:<init>	(Ljava/lang/String;)V
    //   263: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	264	0	paramContext	Context
    //   0	264	1	paramString1	String
    //   0	264	2	paramString2	String
    //   0	264	3	paramBundle	Bundle
    //   111	3	4	bool	boolean
    //   4	199	5	localContext	Context
    // Exception table:
    //   from	to	target	type
    //   83	113	176	android/os/RemoteException
    //   138	176	176	android/os/RemoteException
    //   210	228	176	android/os/RemoteException
    //   242	253	176	android/os/RemoteException
    //   83	113	201	finally
    //   138	176	201	finally
    //   177	201	201	finally
    //   210	228	201	finally
    //   229	242	201	finally
    //   242	253	201	finally
    //   83	113	228	java/lang/InterruptedException
    //   138	176	228	java/lang/InterruptedException
    //   210	228	228	java/lang/InterruptedException
    //   242	253	228	java/lang/InterruptedException
  }
  
  public static String getTokenWithNotification(Context paramContext, String paramString1, String paramString2, Bundle paramBundle)
    throws IOException, UserRecoverableNotifiedException, GoogleAuthException
  {
    Bundle localBundle = paramBundle;
    if (paramBundle == null) {
      localBundle = new Bundle();
    }
    localBundle.putBoolean("handle_notification", true);
    return a(paramContext, paramString1, paramString2, localBundle);
  }
  
  public static String getTokenWithNotification(Context paramContext, String paramString1, String paramString2, Bundle paramBundle, Intent paramIntent)
    throws IOException, UserRecoverableNotifiedException, GoogleAuthException
  {
    h(paramIntent);
    Bundle localBundle = paramBundle;
    if (paramBundle == null) {
      localBundle = new Bundle();
    }
    localBundle.putParcelable("callback_intent", paramIntent);
    localBundle.putBoolean("handle_notification", true);
    return a(paramContext, paramString1, paramString2, localBundle);
  }
  
  public static String getTokenWithNotification(Context paramContext, String paramString1, String paramString2, Bundle paramBundle1, String paramString3, Bundle paramBundle2)
    throws IOException, UserRecoverableNotifiedException, GoogleAuthException
  {
    if (TextUtils.isEmpty(paramString3)) {
      throw new IllegalArgumentException("Authority cannot be empty or null.");
    }
    Bundle localBundle = paramBundle1;
    if (paramBundle1 == null) {
      localBundle = new Bundle();
    }
    paramBundle1 = paramBundle2;
    if (paramBundle2 == null) {
      paramBundle1 = new Bundle();
    }
    ContentResolver.validateSyncExtrasBundle(paramBundle1);
    localBundle.putString("authority", paramString3);
    localBundle.putBundle("sync_extras", paramBundle1);
    localBundle.putBoolean("handle_notification", true);
    return a(paramContext, paramString1, paramString2, localBundle);
  }
  
  private static void h(Intent paramIntent)
  {
    if (paramIntent == null) {
      throw new IllegalArgumentException("Callback cannot be null.");
    }
    paramIntent = paramIntent.toUri(1);
    try
    {
      Intent.parseUri(paramIntent, 1);
      return;
    }
    catch (URISyntaxException paramIntent)
    {
      throw new IllegalArgumentException("Parameter callback contains invalid data. It must be serializable using toUri() and parseUri().");
    }
  }
  
  @Deprecated
  public static void invalidateToken(Context paramContext, String paramString)
  {
    AccountManager.get(paramContext).invalidateAuthToken("com.google", paramString);
  }
  
  private static class a
    extends Handler
  {
    private final Context mD;
    
    a(Context paramContext) {}
    
    public void handleMessage(Message paramMessage)
    {
      if (paramMessage.what == 1)
      {
        int i = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this.mD);
        if (GooglePlayServicesUtil.isUserRecoverableError(i)) {
          GooglePlayServicesUtil.showErrorNotification(i, this.mD);
        }
      }
      for (;;)
      {
        return;
        Log.wtf("GoogleAuthUtil", "Don't know how to handle this message: " + paramMessage.what);
      }
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\auth\GoogleAuthUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */