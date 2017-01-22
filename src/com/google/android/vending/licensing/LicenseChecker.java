package com.google.android.vending.licensing;

import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.vending.licensing.util.Base64;
import com.google.android.vending.licensing.util.Base64DecoderException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class LicenseChecker
  implements ServiceConnection
{
  private static final boolean DEBUG_LICENSE_ERROR = false;
  private static final String KEY_FACTORY_ALGORITHM = "RSA";
  private static final SecureRandom RANDOM = new SecureRandom();
  private static final String TAG = "LicenseChecker";
  private static final int TIMEOUT_MS = 10000;
  private final Set<LicenseValidator> mChecksInProgress = new HashSet();
  private final Context mContext;
  private Handler mHandler;
  private final String mPackageName;
  private final Queue<LicenseValidator> mPendingChecks = new LinkedList();
  private final Policy mPolicy;
  private PublicKey mPublicKey;
  private ILicensingService mService;
  private final String mVersionCode;
  
  public LicenseChecker(Context paramContext, Policy paramPolicy, String paramString)
  {
    this.mContext = paramContext;
    this.mPolicy = paramPolicy;
    this.mPublicKey = generatePublicKey(paramString);
    this.mPackageName = this.mContext.getPackageName();
    this.mVersionCode = getVersionCode(paramContext, this.mPackageName);
    paramContext = new HandlerThread("background thread");
    paramContext.start();
    this.mHandler = new Handler(paramContext.getLooper());
  }
  
  private void cleanupService()
  {
    if (this.mService != null) {}
    try
    {
      this.mContext.unbindService(this);
      this.mService = null;
      return;
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
      for (;;)
      {
        Log.e("LicenseChecker", "Unable to unbind from licensing service (already unbound)");
      }
    }
  }
  
  private void finishCheck(LicenseValidator paramLicenseValidator)
  {
    try
    {
      this.mChecksInProgress.remove(paramLicenseValidator);
      if (this.mChecksInProgress.isEmpty()) {
        cleanupService();
      }
      return;
    }
    finally
    {
      paramLicenseValidator = finally;
      throw paramLicenseValidator;
    }
  }
  
  private int generateNonce()
  {
    return RANDOM.nextInt();
  }
  
  private static PublicKey generatePublicKey(String paramString)
  {
    try
    {
      byte[] arrayOfByte = Base64.decode(paramString);
      paramString = KeyFactory.getInstance("RSA");
      X509EncodedKeySpec localX509EncodedKeySpec = new java/security/spec/X509EncodedKeySpec;
      localX509EncodedKeySpec.<init>(arrayOfByte);
      paramString = paramString.generatePublic(localX509EncodedKeySpec);
      return paramString;
    }
    catch (NoSuchAlgorithmException paramString)
    {
      throw new RuntimeException(paramString);
    }
    catch (Base64DecoderException paramString)
    {
      Log.e("LicenseChecker", "Could not decode from Base64.");
      throw new IllegalArgumentException(paramString);
    }
    catch (InvalidKeySpecException paramString)
    {
      Log.e("LicenseChecker", "Invalid key specification.");
      throw new IllegalArgumentException(paramString);
    }
  }
  
  private static String getVersionCode(Context paramContext, String paramString)
  {
    try
    {
      int i = paramContext.getPackageManager().getPackageInfo(paramString, 0).versionCode;
      paramContext = String.valueOf(i);
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      for (;;)
      {
        Log.e("LicenseChecker", "Package not found. could not get version code.");
        paramContext = "";
      }
    }
    return paramContext;
  }
  
  /* Error */
  private void handleServiceConnectionError(LicenseValidator paramLicenseValidator)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 72	com/google/android/vending/licensing/LicenseChecker:mPolicy	Lcom/google/android/vending/licensing/Policy;
    //   6: sipush 291
    //   9: aconst_null
    //   10: invokeinterface 233 3 0
    //   15: aload_0
    //   16: getfield 72	com/google/android/vending/licensing/LicenseChecker:mPolicy	Lcom/google/android/vending/licensing/Policy;
    //   19: invokeinterface 236 1 0
    //   24: ifeq +18 -> 42
    //   27: aload_1
    //   28: invokevirtual 242	com/google/android/vending/licensing/LicenseValidator:getCallback	()Lcom/google/android/vending/licensing/LicenseCheckerCallback;
    //   31: sipush 291
    //   34: invokeinterface 248 2 0
    //   39: aload_0
    //   40: monitorexit
    //   41: return
    //   42: aload_1
    //   43: invokevirtual 242	com/google/android/vending/licensing/LicenseValidator:getCallback	()Lcom/google/android/vending/licensing/LicenseCheckerCallback;
    //   46: sipush 291
    //   49: invokeinterface 251 2 0
    //   54: goto -15 -> 39
    //   57: astore_1
    //   58: aload_0
    //   59: monitorexit
    //   60: aload_1
    //   61: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	62	0	this	LicenseChecker
    //   0	62	1	paramLicenseValidator	LicenseValidator
    // Exception table:
    //   from	to	target	type
    //   2	39	57	finally
    //   42	54	57	finally
  }
  
  private void runChecks()
  {
    for (;;)
    {
      LicenseValidator localLicenseValidator = (LicenseValidator)this.mPendingChecks.poll();
      if (localLicenseValidator == null) {
        break;
      }
      try
      {
        Object localObject = new java/lang/StringBuilder;
        ((StringBuilder)localObject).<init>();
        Log.i("LicenseChecker", "Calling checkLicense on service for " + localLicenseValidator.getPackageName());
        ILicensingService localILicensingService = this.mService;
        long l = localLicenseValidator.getNonce();
        String str = localLicenseValidator.getPackageName();
        localObject = new com/google/android/vending/licensing/LicenseChecker$ResultListener;
        ((ResultListener)localObject).<init>(this, localLicenseValidator);
        localILicensingService.checkLicense(l, str, (ILicenseResultListener)localObject);
        this.mChecksInProgress.add(localLicenseValidator);
      }
      catch (RemoteException localRemoteException)
      {
        Log.w("LicenseChecker", "RemoteException in checkLicense call.", localRemoteException);
        handleServiceConnectionError(localLicenseValidator);
      }
    }
  }
  
  /* Error */
  public void checkAccess(LicenseCheckerCallback paramLicenseCheckerCallback)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 72	com/google/android/vending/licensing/LicenseChecker:mPolicy	Lcom/google/android/vending/licensing/Policy;
    //   6: invokeinterface 236 1 0
    //   11: ifeq +24 -> 35
    //   14: ldc 25
    //   16: ldc_w 302
    //   19: invokestatic 276	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;)I
    //   22: pop
    //   23: aload_1
    //   24: sipush 256
    //   27: invokeinterface 248 2 0
    //   32: aload_0
    //   33: monitorexit
    //   34: return
    //   35: new 238	com/google/android/vending/licensing/LicenseValidator
    //   38: astore_2
    //   39: aload_0
    //   40: getfield 72	com/google/android/vending/licensing/LicenseChecker:mPolicy	Lcom/google/android/vending/licensing/Policy;
    //   43: astore 4
    //   45: new 304	com/google/android/vending/licensing/NullDeviceLimiter
    //   48: astore_3
    //   49: aload_3
    //   50: invokespecial 305	com/google/android/vending/licensing/NullDeviceLimiter:<init>	()V
    //   53: aload_2
    //   54: aload 4
    //   56: aload_3
    //   57: aload_1
    //   58: aload_0
    //   59: invokespecial 307	com/google/android/vending/licensing/LicenseChecker:generateNonce	()I
    //   62: aload_0
    //   63: getfield 86	com/google/android/vending/licensing/LicenseChecker:mPackageName	Ljava/lang/String;
    //   66: aload_0
    //   67: getfield 92	com/google/android/vending/licensing/LicenseChecker:mVersionCode	Ljava/lang/String;
    //   70: invokespecial 310	com/google/android/vending/licensing/LicenseValidator:<init>	(Lcom/google/android/vending/licensing/Policy;Lcom/google/android/vending/licensing/DeviceLimiter;Lcom/google/android/vending/licensing/LicenseCheckerCallback;ILjava/lang/String;Ljava/lang/String;)V
    //   73: aload_0
    //   74: getfield 134	com/google/android/vending/licensing/LicenseChecker:mService	Lcom/google/android/vending/licensing/ILicensingService;
    //   77: ifnonnull +116 -> 193
    //   80: ldc 25
    //   82: ldc_w 312
    //   85: invokestatic 276	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;)I
    //   88: pop
    //   89: new 314	android/content/Intent
    //   92: astore 4
    //   94: new 219	java/lang/String
    //   97: astore_3
    //   98: aload_3
    //   99: ldc_w 316
    //   102: invokestatic 175	com/google/android/vending/licensing/util/Base64:decode	(Ljava/lang/String;)[B
    //   105: invokespecial 317	java/lang/String:<init>	([B)V
    //   108: aload 4
    //   110: aload_3
    //   111: invokespecial 318	android/content/Intent:<init>	(Ljava/lang/String;)V
    //   114: aload_0
    //   115: getfield 70	com/google/android/vending/licensing/LicenseChecker:mContext	Landroid/content/Context;
    //   118: aload 4
    //   120: invokestatic 324	com/explodingbarrel/Helpers:createExplicitFromImplicitIntent	(Landroid/content/Context;Landroid/content/Intent;)Landroid/content/Intent;
    //   123: astore_3
    //   124: aload_0
    //   125: getfield 70	com/google/android/vending/licensing/LicenseChecker:mContext	Landroid/content/Context;
    //   128: aload_3
    //   129: aload_0
    //   130: iconst_1
    //   131: invokevirtual 328	android/content/Context:bindService	(Landroid/content/Intent;Landroid/content/ServiceConnection;I)Z
    //   134: ifeq +34 -> 168
    //   137: aload_0
    //   138: getfield 68	com/google/android/vending/licensing/LicenseChecker:mPendingChecks	Ljava/util/Queue;
    //   141: aload_2
    //   142: invokeinterface 331 2 0
    //   147: pop
    //   148: goto -116 -> 32
    //   151: astore_2
    //   152: aload_1
    //   153: bipush 6
    //   155: invokeinterface 334 2 0
    //   160: goto -128 -> 32
    //   163: astore_1
    //   164: aload_0
    //   165: monitorexit
    //   166: aload_1
    //   167: athrow
    //   168: ldc 25
    //   170: ldc_w 336
    //   173: invokestatic 146	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   176: pop
    //   177: aload_0
    //   178: aload_2
    //   179: invokespecial 119	com/google/android/vending/licensing/LicenseChecker:handleServiceConnectionError	(Lcom/google/android/vending/licensing/LicenseValidator;)V
    //   182: goto -150 -> 32
    //   185: astore_1
    //   186: aload_1
    //   187: invokevirtual 339	com/google/android/vending/licensing/util/Base64DecoderException:printStackTrace	()V
    //   190: goto -158 -> 32
    //   193: aload_0
    //   194: getfield 68	com/google/android/vending/licensing/LicenseChecker:mPendingChecks	Ljava/util/Queue;
    //   197: aload_2
    //   198: invokeinterface 331 2 0
    //   203: pop
    //   204: aload_0
    //   205: invokespecial 341	com/google/android/vending/licensing/LicenseChecker:runChecks	()V
    //   208: goto -176 -> 32
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	211	0	this	LicenseChecker
    //   0	211	1	paramLicenseCheckerCallback	LicenseCheckerCallback
    //   38	104	2	localLicenseValidator	LicenseValidator
    //   151	47	2	localSecurityException	SecurityException
    //   48	81	3	localObject1	Object
    //   43	76	4	localObject2	Object
    // Exception table:
    //   from	to	target	type
    //   89	148	151	java/lang/SecurityException
    //   168	182	151	java/lang/SecurityException
    //   2	32	163	finally
    //   35	89	163	finally
    //   89	148	163	finally
    //   152	160	163	finally
    //   168	182	163	finally
    //   186	190	163	finally
    //   193	208	163	finally
    //   89	148	185	com/google/android/vending/licensing/util/Base64DecoderException
    //   168	182	185	com/google/android/vending/licensing/util/Base64DecoderException
  }
  
  public void onDestroy()
  {
    try
    {
      cleanupService();
      this.mHandler.getLooper().quit();
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public void onServiceConnected(ComponentName paramComponentName, IBinder paramIBinder)
  {
    try
    {
      this.mService = ILicensingService.Stub.asInterface(paramIBinder);
      runChecks();
      return;
    }
    finally
    {
      paramComponentName = finally;
      throw paramComponentName;
    }
  }
  
  public void onServiceDisconnected(ComponentName paramComponentName)
  {
    try
    {
      Log.w("LicenseChecker", "Service unexpectedly disconnected.");
      this.mService = null;
      return;
    }
    finally
    {
      paramComponentName = finally;
      throw paramComponentName;
    }
  }
  
  private class ResultListener
    extends ILicenseResultListener.Stub
  {
    private static final int ERROR_CONTACTING_SERVER = 257;
    private static final int ERROR_INVALID_PACKAGE_NAME = 258;
    private static final int ERROR_NON_MATCHING_UID = 259;
    private Runnable mOnTimeout;
    private final LicenseValidator mValidator;
    
    public ResultListener(LicenseValidator paramLicenseValidator)
    {
      this.mValidator = paramLicenseValidator;
      this.mOnTimeout = new Runnable()
      {
        public void run()
        {
          Log.i("LicenseChecker", "Check timed out.");
          LicenseChecker.this.handleServiceConnectionError(LicenseChecker.ResultListener.this.mValidator);
          LicenseChecker.this.finishCheck(LicenseChecker.ResultListener.this.mValidator);
        }
      };
      startTimeout();
    }
    
    private void clearTimeout()
    {
      Log.i("LicenseChecker", "Clearing timeout.");
      LicenseChecker.this.mHandler.removeCallbacks(this.mOnTimeout);
    }
    
    private void startTimeout()
    {
      Log.i("LicenseChecker", "Start monitoring timeout.");
      LicenseChecker.this.mHandler.postDelayed(this.mOnTimeout, 10000L);
    }
    
    public void verifyLicense(final int paramInt, final String paramString1, final String paramString2)
    {
      LicenseChecker.this.mHandler.post(new Runnable()
      {
        public void run()
        {
          Log.i("LicenseChecker", "Received response.");
          if (LicenseChecker.this.mChecksInProgress.contains(LicenseChecker.ResultListener.this.mValidator))
          {
            LicenseChecker.ResultListener.this.clearTimeout();
            LicenseChecker.ResultListener.this.mValidator.verify(LicenseChecker.this.mPublicKey, paramInt, paramString1, paramString2);
            LicenseChecker.this.finishCheck(LicenseChecker.ResultListener.this.mValidator);
          }
        }
      });
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\vending\licensing\LicenseChecker.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */