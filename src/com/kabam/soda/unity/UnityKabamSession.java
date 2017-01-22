package com.kabam.soda.unity;

import android.util.Log;
import com.kabam.soda.KabamException;
import com.kabam.soda.KabamSession;
import com.kabam.soda.KabamSession.KabamAccountView;
import com.kabam.soda.Settings;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UnityKabamSession
{
  private static final String KABAM_ACCOUNT_LOGIN = "KABAM_ACCOUNT_LOGIN";
  private static final String KABAM_ACCOUNT_REGISTER = "KABAM_ACCOUNT_REGISTER";
  public static final String TAG = UnityKabamSession.class.getSimpleName();
  public static final UnitySodaCallback callback = new UnitySodaCallback();
  private static Settings settings;
  
  public static void config(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, boolean paramBoolean)
  {
    try
    {
      StringBuilder localStringBuilder = new java/lang/StringBuilder;
      localStringBuilder.<init>();
      paramString4 = "unity/" + paramString4 + " kabamSodaUnity/" + paramString5;
      paramString5 = TAG;
      localStringBuilder = new java/lang/StringBuilder;
      localStringBuilder.<init>();
      Log.d(paramString5, "User Agent Extras: " + paramString4);
      paramString5 = new com/kabam/soda/Settings;
      paramString5.<init>(paramString1, paramString2);
      settings = paramString5.withWskeUrl(paramString3).withUserAgentExtras(paramString4);
      if (paramBoolean) {
        increaseLogging();
      }
      return;
    }
    catch (RuntimeException paramString1)
    {
      Log.e(TAG, "Runtime exception in config(): " + paramString1);
      throw paramString1;
    }
  }
  
  public static String getVersion()
  {
    try
    {
      String str = KabamSession.getVersion();
      return str;
    }
    catch (RuntimeException localRuntimeException)
    {
      Log.e(TAG, "Runtime exception in getVersion(): " + localRuntimeException);
      throw localRuntimeException;
    }
  }
  
  private static void increaseLogging()
  {
    Logger.getLogger("org.apache.http.wire").setLevel(Level.FINEST);
    Logger.getLogger("org.apache.http").setLevel(Level.FINEST);
  }
  
  /* Error */
  public static void init(android.app.Activity paramActivity, Locale paramLocale)
  {
    // Byte code:
    //   0: aload_0
    //   1: astore_2
    //   2: aload_0
    //   3: ifnonnull +7 -> 10
    //   6: getstatic 119	com/unity3d/player/UnityPlayer:currentActivity	Landroid/app/Activity;
    //   9: astore_2
    //   10: aload_1
    //   11: astore_0
    //   12: aload_1
    //   13: ifnonnull +7 -> 20
    //   16: invokestatic 125	java/util/Locale:getDefault	()Ljava/util/Locale;
    //   19: astore_0
    //   20: getstatic 73	com/kabam/soda/unity/UnityKabamSession:settings	Lcom/kabam/soda/Settings;
    //   23: ifnonnull +13 -> 36
    //   26: aload_2
    //   27: aload_0
    //   28: getstatic 31	com/kabam/soda/unity/UnityKabamSession:callback	Lcom/kabam/soda/unity/UnitySodaCallback;
    //   31: invokestatic 128	com/kabam/soda/KabamSession:init	(Landroid/app/Activity;Ljava/util/Locale;Lcom/kabam/soda/SodaCallback;)Lcom/kabam/soda/KabamSession;
    //   34: pop
    //   35: return
    //   36: aload_2
    //   37: aload_0
    //   38: getstatic 31	com/kabam/soda/unity/UnityKabamSession:callback	Lcom/kabam/soda/unity/UnitySodaCallback;
    //   41: getstatic 73	com/kabam/soda/unity/UnityKabamSession:settings	Lcom/kabam/soda/Settings;
    //   44: invokestatic 131	com/kabam/soda/KabamSession:init	(Landroid/app/Activity;Ljava/util/Locale;Lcom/kabam/soda/SodaCallback;Lcom/kabam/soda/Settings;)Lcom/kabam/soda/KabamSession;
    //   47: pop
    //   48: goto -13 -> 35
    //   51: astore_0
    //   52: getstatic 24	com/kabam/soda/unity/UnityKabamSession:TAG	Ljava/lang/String;
    //   55: new 39	java/lang/StringBuilder
    //   58: dup
    //   59: invokespecial 40	java/lang/StringBuilder:<init>	()V
    //   62: ldc -123
    //   64: invokevirtual 46	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   67: aload_0
    //   68: invokevirtual 81	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   71: invokevirtual 51	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   74: invokestatic 84	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   77: pop
    //   78: aload_0
    //   79: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	80	0	paramActivity	android.app.Activity
    //   0	80	1	paramLocale	Locale
    //   1	36	2	localActivity	android.app.Activity
    // Exception table:
    //   from	to	target	type
    //   6	10	51	java/lang/RuntimeException
    //   16	20	51	java/lang/RuntimeException
    //   20	35	51	java/lang/RuntimeException
    //   36	48	51	java/lang/RuntimeException
  }
  
  public static void kabamAccountLogin()
  {
    try
    {
      KabamSession.kabamAccountLogin();
      return;
    }
    catch (KabamException localKabamException)
    {
      for (;;)
      {
        Log.e(TAG, "Couldn't kabamAccountLogin: " + localKabamException);
      }
    }
    catch (RuntimeException localRuntimeException)
    {
      Log.e(TAG, "Runtime exception in kabamAccountLogin(): " + localRuntimeException);
      throw localRuntimeException;
    }
  }
  
  public static void kabamAccountLogin(String paramString)
  {
    try
    {
      if (paramString.equals("KABAM_ACCOUNT_LOGIN")) {
        KabamSession.kabamAccountLogin(KabamSession.KabamAccountView.LOG_IN);
      }
      for (;;)
      {
        return;
        if (paramString.equals("KABAM_ACCOUNT_REGISTER")) {
          KabamSession.kabamAccountLogin(KabamSession.KabamAccountView.REGISTER);
        }
      }
    }
    catch (KabamException paramString)
    {
      for (;;)
      {
        Log.e(TAG, "Couldn't kabamAccountLogin: " + paramString);
      }
    }
    catch (RuntimeException paramString)
    {
      Log.e(TAG, "Runtime exception in kabamAccountLogin(): " + paramString);
      throw paramString;
    }
  }
  
  public static void login(String paramString1, String paramString2, Locale paramLocale)
  {
    try
    {
      KabamSession.login(paramString1, paramString2, paramLocale);
      return;
    }
    catch (KabamException paramString1)
    {
      for (;;)
      {
        Log.e(TAG, "Couldn't log in: " + paramString1);
      }
    }
    catch (RuntimeException paramString1)
    {
      Log.e(TAG, "Runtime exception in login(): " + paramString1);
      throw paramString1;
    }
  }
  
  public static void logout()
  {
    try
    {
      KabamSession.logout();
      return;
    }
    catch (KabamException localKabamException)
    {
      for (;;)
      {
        Log.e(TAG, "Couldn't log out: " + localKabamException);
      }
    }
    catch (RuntimeException localRuntimeException)
    {
      Log.e(TAG, "Runtime exception in logout(): " + localRuntimeException);
      throw localRuntimeException;
    }
  }
  
  public static void setKabamAccount(String paramString)
  {
    try
    {
      KabamSession.setKabamAccount(paramString);
      return;
    }
    catch (KabamException paramString)
    {
      for (;;)
      {
        Log.e(TAG, "Couldn't setKabamAccount: " + paramString);
      }
    }
    catch (RuntimeException paramString)
    {
      Log.e(TAG, "Runtime exception in setKabamAccount(): " + paramString);
      throw paramString;
    }
  }
  
  public static void setReceiverObjectName(String paramString)
  {
    try
    {
      callback.setReceiverObject(paramString);
      return;
    }
    catch (RuntimeException paramString)
    {
      Log.e(TAG, "Runtime exception in setReceiverObjectName(): " + paramString);
      throw paramString;
    }
  }
  
  public static void showKabamAccountUI(String paramString)
  {
    try
    {
      if (paramString.equals("KABAM_ACCOUNT_LOGIN")) {
        KabamSession.showKabamAccountUI(KabamSession.KabamAccountView.LOG_IN);
      }
      for (;;)
      {
        return;
        if (paramString.equals("KABAM_ACCOUNT_REGISTER")) {
          KabamSession.showKabamAccountUI(KabamSession.KabamAccountView.REGISTER);
        }
      }
    }
    catch (KabamException paramString)
    {
      for (;;)
      {
        Log.e(TAG, "Couldn't showKabamAccountUI: " + paramString);
      }
    }
    catch (RuntimeException paramString)
    {
      Log.e(TAG, "Runtime exception in showKabamAccountUI(): " + paramString);
      throw paramString;
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\kabam\soda\unity\UnityKabamSession.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */