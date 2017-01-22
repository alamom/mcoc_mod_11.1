package com.kabam.soda;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Build.VERSION;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import com.kabam.soda.wske.WSKE;
import com.kabam.soda.wske.WSKEAsyncTask;
import com.kabam.soda.wske.WSKECallback;
import com.kabam.wske.model.KabamAccountFindResponseResource;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

@SuppressLint({"InlinedApi"})
public class KabamSession
{
  private static final String AUTH_PREFS_KEY = "KabamSessionAuthToken";
  private static final String KAUTH_EMAIL_KEY = "KAuthEmail";
  private static final String KAUTH_TOKEN_KEY = "KAuthToken";
  private static final List<Integer> NETWORK_TYPES;
  private static final long PLAY_EVENT_TIME_INTERVAL;
  private static final String TAG = KabamSession.class.getSimpleName();
  public static final String TOKEN_EXTRA_KEY = "TOKEN";
  private static Set<AuthSource> authenticatedNetworks = new LinkedHashSet();
  private static boolean clientFullscreen;
  private static AtomicReference<Date> lastPlayEvent;
  private static final AtomicReference<Locale> locale;
  private static final AtomicReference<KabamSession> session = new AtomicReference();
  private final AtomicReference<String> authToken = new AtomicReference();
  private final SodaCallback callback;
  private final AtomicReference<Activity> clientActivity = new AtomicReference();
  private final AtomicReference<String> emailAddr = new AtomicReference();
  private final AtomicReference<String> kabamAuthToken = new AtomicReference();
  private final AtomicReference<String> playerCertificate = new AtomicReference();
  private final AtomicReference<String> playerId = new AtomicReference();
  private final AtomicReference<Settings> settings = new AtomicReference();
  private final AtomicReference<String> unAuthEmailAddr = new AtomicReference();
  
  static
  {
    locale = new AtomicReference(Locale.getDefault());
    NETWORK_TYPES = new LinkedList();
    NETWORK_TYPES.add(Integer.valueOf(1));
    NETWORK_TYPES.add(Integer.valueOf(0));
    NETWORK_TYPES.add(Integer.valueOf(6));
    if (Build.VERSION.SDK_INT >= 13) {
      NETWORK_TYPES.add(Integer.valueOf(7));
    }
    lastPlayEvent = new AtomicReference();
    PLAY_EVENT_TIME_INTERVAL = TimeUnit.SECONDS.toMillis(60L);
    clientFullscreen = false;
  }
  
  private KabamSession(Activity paramActivity, SodaCallback paramSodaCallback, Settings paramSettings)
  {
    this.clientActivity.set(paramActivity);
    this.callback = paramSodaCallback;
    this.settings.set(paramSettings);
    loadAuthPrefsFromSharedPreferences();
  }
  
  static void addAuthenticatedNetwork(AuthSource paramAuthSource)
  {
    authenticatedNetworks.add(paramAuthSource);
  }
  
  public static void doCertificateExpiry()
  {
    for (;;)
    {
      try
      {
        str = (String)getSession().playerCertificate.getAndSet(null);
        if (str == null) {
          return;
        }
      }
      catch (KabamException localKabamException)
      {
        String str;
        Log.e(TAG, "doCertificateExpiry(): " + localKabamException.getMessage());
        continue;
      }
      getSession().callback.onCertificateExpiry(getPlayerId(), str);
    }
  }
  
  static AuthSource getAuthSource()
  {
    if (getAuthenticatedNetworks().size() != 0) {}
    for (AuthSource localAuthSource = (AuthSource)getAuthenticatedNetworks().iterator().next();; localAuthSource = null) {
      return localAuthSource;
    }
  }
  
  public static String getAuthToken()
  {
    try
    {
      String str = (String)getSession().authToken.get();
      return str;
    }
    catch (KabamException localKabamException)
    {
      for (;;)
      {
        Object localObject = null;
      }
    }
  }
  
  public static Set<AuthSource> getAuthenticatedNetworks()
  {
    return authenticatedNetworks;
  }
  
  static SodaCallback getCallback()
    throws KabamException
  {
    return getSession().callback;
  }
  
  public static Activity getClientActivity()
    throws KabamException
  {
    Activity localActivity = (Activity)getSession().clientActivity.get();
    if (localActivity == null) {
      throw new KabamException("KabamSession has no host Activity");
    }
    return localActivity;
  }
  
  static String getEmailAddr()
  {
    try
    {
      String str = (String)getSession().emailAddr.get();
      return str;
    }
    catch (KabamException localKabamException)
    {
      for (;;)
      {
        Object localObject = null;
      }
    }
  }
  
  public static String getKabamAccount()
    throws KabamException
  {
    return (String)getSession().kabamAuthToken.get();
  }
  
  public static String getKabamAuthToken()
    throws KabamException
  {
    return getSession().kabamAuthToken.toString();
  }
  
  public static Locale getLocale()
  {
    return (Locale)locale.get();
  }
  
  public static String getPlayerId()
  {
    try
    {
      String str = (String)getSession().playerId.get();
      return str;
    }
    catch (KabamException localKabamException)
    {
      for (;;)
      {
        Object localObject = null;
      }
    }
  }
  
  private static KabamSession getSession()
    throws KabamException
  {
    KabamSession localKabamSession = (KabamSession)session.get();
    if (localKabamSession == null) {
      throw new KabamException("No KabamSession");
    }
    return localKabamSession;
  }
  
  public static Settings getSettings()
    throws KabamException
  {
    Settings localSettings = (Settings)getSession().settings.get();
    if (localSettings == null) {
      throw new KabamException("KabamSession has no client config Settings");
    }
    return localSettings;
  }
  
  static String getUnAuthEmailAddr()
  {
    try
    {
      String str = (String)getSession().unAuthEmailAddr.get();
      return str;
    }
    catch (KabamException localKabamException)
    {
      for (;;)
      {
        Object localObject = null;
      }
    }
  }
  
  public static String getVersion()
  {
    return "v3.0.0";
  }
  
  public static KabamSession init(Activity paramActivity, Locale paramLocale, SodaCallback paramSodaCallback)
  {
    return init(paramActivity, paramLocale, paramSodaCallback, new Settings(paramActivity));
  }
  
  public static KabamSession init(Activity paramActivity, Locale paramLocale, SodaCallback paramSodaCallback, Settings paramSettings)
  {
    if (paramActivity == null)
    {
      Log.e(TAG, "game activity is null");
      paramActivity = null;
      return paramActivity;
    }
    KR.init(paramActivity.getResources(), paramActivity.getPackageName());
    if ((KabamSession)session.getAndSet(new KabamSession(paramActivity, paramSodaCallback, paramSettings)) == null)
    {
      Log.i(TAG, "New Kabam session created.");
      label61:
      if (paramLocale != null) {
        locale.set(paramLocale);
      }
      if ((paramActivity.getWindow().getAttributes().flags & 0x400) == 0) {
        break label165;
      }
    }
    label165:
    for (boolean bool = true;; bool = false)
    {
      clientFullscreen = bool;
      Log.d(TAG, "clientFullscreen: " + clientFullscreen);
      Xlate.loadTranslations(paramActivity, Boolean.valueOf(true));
      FeatureConfig.getClientSettings(paramActivity, paramSettings);
      paramActivity = (KabamSession)session.get();
      break;
      Log.i(TAG, "Kabam session replaced with a new Kabam session.");
      break label61;
    }
  }
  
  public static boolean isClientFullscreen()
  {
    return clientFullscreen;
  }
  
  public static boolean isKabamAccountLoggedIn()
  {
    bool2 = false;
    try
    {
      String str = (String)getSession().emailAddr.get();
      bool1 = bool2;
      if (str != null)
      {
        boolean bool3 = str.isEmpty();
        bool1 = bool2;
        if (!bool3) {
          bool1 = true;
        }
      }
    }
    catch (KabamException localKabamException)
    {
      for (;;)
      {
        Log.e(TAG, "! isKabamAccountLoggedIn() because " + localKabamException.getLocalizedMessage());
        boolean bool1 = bool2;
      }
    }
    return bool1;
  }
  
  public static void kabamAccountLogin()
    throws KabamException
  {
    kabamAccountLogin(null);
  }
  
  public static void kabamAccountLogin(KabamAccountView paramKabamAccountView)
    throws KabamException
  {
    String str = (String)getSession().kabamAuthToken.get();
    if ((paramKabamAccountView == null) && (str == null)) {
      getCallback().onKabamAccountError(SodaCallback.AccountError.NO_AUTH_TOKEN);
    }
    for (;;)
    {
      return;
      setKabamAccount(str, paramKabamAccountView);
    }
  }
  
  private void loadAuthPrefsFromSharedPreferences()
  {
    Object localObject = (Activity)this.clientActivity.get();
    if (localObject == null) {}
    for (;;)
    {
      return;
      localObject = ((Activity)localObject).getSharedPreferences("KabamSessionAuthToken", 0);
      String str2 = (String)this.kabamAuthToken.get();
      if (str2 == null)
      {
        str1 = ((SharedPreferences)localObject).getString("KAuthToken", null);
        this.kabamAuthToken.compareAndSet(str2, str1);
        Log.d(TAG, "Loaded KAuth Token : " + str1);
      }
      String str1 = (String)this.emailAddr.get();
      if (str1 == null)
      {
        localObject = ((SharedPreferences)localObject).getString("KAuthEmail", null);
        this.emailAddr.compareAndSet(str1, localObject);
        Log.d(TAG, "Loaded KAuth Email : " + (String)localObject);
      }
    }
  }
  
  public static void login(String paramString1, String paramString2, Locale paramLocale)
    throws KabamException
  {
    if (paramString1 == null) {
      Log.e(TAG, "login(): Player ID is null.");
    }
    for (;;)
    {
      return;
      if (paramString2 == null)
      {
        Log.e(TAG, "login(): Player certificate is null.");
      }
      else
      {
        if (paramLocale != null) {
          locale.set(paramLocale);
        }
        paramLocale = (String)getSession().playerId.get();
        if ((paramLocale != null) && (!paramLocale.equals(paramString1))) {
          logout();
        }
        Xlate.loadTranslations(getClientActivity(), Boolean.valueOf(false));
        getSession().playerId.set(paramString1);
        Log.i(TAG, String.format("User logged in: %s", new Object[] { paramString1 }));
        getSession().playerCertificate.set(paramString2);
        WSKE.createAccount(getClientActivity(), getSettings(), new WSKECallback()
        {
          public void onError(String paramAnonymousString, Throwable paramAnonymousThrowable, int paramAnonymousInt)
          {
            Log.e(KabamSession.TAG, "Could not create wske account: " + paramAnonymousString);
          }
          
          public void onSuccess(String paramAnonymousString)
          {
            Log.d(KabamSession.TAG, "login - WSKE.createAccount.callback.onSuccess");
            try
            {
              KabamSession.refreshAuthenticatedNetworks();
              FeatureConfig.get();
              KabamSession.maybeSendPlayEvent(this.val$playerId);
              Xlate.setSupportLink(KabamSession.getClientActivity());
              return;
            }
            catch (KabamException paramAnonymousString)
            {
              for (;;)
              {
                Log.e(KabamSession.TAG, paramAnonymousString.getMessage());
              }
            }
          }
        }, paramString1, paramString2);
      }
    }
  }
  
  public static void logout()
    throws KabamException
  {
    setAuthToken(null);
    String str = (String)getSession().playerId.getAndSet(null);
    if (str != null)
    {
      Log.i(TAG, String.format("User logged out: %s", new Object[] { str }));
      WSKEAsyncTask.invalidateWskeToken();
    }
    Analytics.sendEvent("Kabam Log Out", "Log Out Success", null, getClientActivity());
    setKabamAuthToken(null);
    getSession().emailAddr.getAndSet(null);
    getSession().unAuthEmailAddr.getAndSet(null);
    removeAllAuthenticatedNetworks();
  }
  
  private static void maybeSendPlayEvent(String paramString)
    throws KabamException
  {
    Date localDate2 = new Date();
    Date localDate1 = (Date)lastPlayEvent.get();
    long l = 0L;
    if (localDate1 != null) {
      l = Math.abs(localDate2.getTime() - localDate1.getTime());
    }
    if ((localDate1 == null) || (l > PLAY_EVENT_TIME_INTERVAL)) {
      sendPlayEvent(paramString, localDate2);
    }
  }
  
  public static void pause() {}
  
  static void refreshAuthenticatedNetworks()
  {
    try
    {
      String str = getPlayerId();
      if (str != null)
      {
        Activity localActivity = getClientActivity();
        Settings localSettings = getSettings();
        WSKECallback local3 = new com/kabam/soda/KabamSession$3;
        local3.<init>();
        WSKE.listAuthenticatedNetworks(localActivity, localSettings, local3, str);
      }
      return;
    }
    catch (KabamException localKabamException)
    {
      for (;;)
      {
        Log.e(TAG, "Error refreshing authenticated networks: " + localKabamException);
      }
    }
  }
  
  static void removeAllAuthenticatedNetworks()
  {
    try
    {
      authenticatedNetworks.clear();
      getSession().saveAuthPrefsToSharedPreferences();
      return;
    }
    catch (KabamException localKabamException)
    {
      for (;;)
      {
        Log.e(TAG, "Error removing authenticated networks: " + localKabamException.getLocalizedMessage());
      }
    }
  }
  
  public static void resume(Activity paramActivity)
  {
    try
    {
      setClientActivity(paramActivity);
      paramActivity = getPlayerId();
      if (paramActivity != null) {
        maybeSendPlayEvent(paramActivity);
      }
      Xlate.loadTranslations(getClientActivity(), Boolean.valueOf(false));
      return;
    }
    catch (KabamException paramActivity)
    {
      for (;;)
      {
        Log.e(TAG, "resume(): " + paramActivity.getMessage());
      }
    }
  }
  
  private void saveAuthPrefsToSharedPreferences()
  {
    Object localObject = (Activity)this.clientActivity.get();
    if (localObject == null) {
      return;
    }
    localObject = ((Activity)localObject).getSharedPreferences("KabamSessionAuthToken", 0).edit();
    String str = (String)this.kabamAuthToken.get();
    if (str != null)
    {
      ((SharedPreferences.Editor)localObject).putString("KAuthToken", str);
      Log.d(TAG, "Saved KAuth Token: " + str);
      label81:
      str = (String)this.emailAddr.get();
      if (str == null) {
        break label164;
      }
      ((SharedPreferences.Editor)localObject).putString("KAuthEmail", str);
      Log.d(TAG, "Saved KAuth Email" + str);
    }
    for (;;)
    {
      ((SharedPreferences.Editor)localObject).apply();
      break;
      ((SharedPreferences.Editor)localObject).remove("KAuthToken");
      Log.d(TAG, "Cleared KAuth Token");
      break label81;
      label164:
      ((SharedPreferences.Editor)localObject).remove("KAuthEmail");
      Log.d(TAG, "Cleared KAuth Email");
    }
  }
  
  private static void sendPlayEvent(String paramString, Date paramDate)
    throws KabamException
  {
    WSKE.sendEvent(getClientActivity(), getSettings(), new WSKECallback()
    {
      public void onError(String paramAnonymousString, Throwable paramAnonymousThrowable, int paramAnonymousInt)
      {
        Log.e(KabamSession.TAG, "Could not sendPlayEvent(): " + paramAnonymousString);
      }
      
      public void onSuccess(Boolean paramAnonymousBoolean)
      {
        Log.d(KabamSession.TAG, "Play event successfully sent");
        KabamSession.lastPlayEvent.set(new Date());
      }
    }, paramString, "play");
  }
  
  public static void setAuthToken(String paramString)
  {
    try
    {
      getSession().authToken.set(paramString);
      getSession().saveAuthPrefsToSharedPreferences();
      return;
    }
    catch (KabamException paramString)
    {
      for (;;) {}
    }
  }
  
  private static void setClientActivity(Activity paramActivity)
    throws KabamException
  {
    if ((Activity)getSession().clientActivity.getAndSet(paramActivity) == null) {
      Log.d(TAG, "Client Activity set");
    }
    for (;;)
    {
      return;
      Log.d(TAG, "Client Activity replaced");
    }
  }
  
  static void setEmailAddr(String paramString)
  {
    try
    {
      getSession().emailAddr.set(paramString);
      getSession().saveAuthPrefsToSharedPreferences();
      return;
    }
    catch (KabamException paramString)
    {
      for (;;)
      {
        Log.e(TAG, "cannot setEmailAddr() because " + paramString.getLocalizedMessage());
      }
    }
  }
  
  public static void setKabamAccount(String paramString)
    throws KabamException
  {
    setKabamAccount(paramString, null);
  }
  
  private static void setKabamAccount(String paramString, KabamAccountView paramKabamAccountView)
    throws KabamException
  {
    setKabamAuthToken(paramString);
    if (paramString == null) {
      if (paramKabamAccountView != null) {
        showKabamAccountUI(paramKabamAccountView);
      }
    }
    for (;;)
    {
      return;
      Settings localSettings = getSettings();
      WSKE.kabamAccountGetMe(getClientActivity(), localSettings, paramString, 0L, new WSKECallback()
      {
        public void onError(String paramAnonymousString, Throwable paramAnonymousThrowable, int paramAnonymousInt)
        {
          Log.d(KabamSession.TAG, "kabamAccountGetMe error: " + paramAnonymousInt + " == " + paramAnonymousString + " because " + paramAnonymousThrowable.getLocalizedMessage());
          for (;;)
          {
            try
            {
              if (this.val$view == null)
              {
                if (AccountActivity.wskeErrorToAccountError(paramAnonymousInt) != SodaCallback.AccountError.AUTH_TOKEN_NOT_VALIDATED) {
                  KabamSession.setKabamAuthToken(null);
                }
                KabamSession.getCallback().onKabamAccountError(AccountActivity.wskeErrorToAccountError(paramAnonymousInt));
                return;
              }
            }
            catch (KabamException paramAnonymousString)
            {
              Log.e(KabamSession.TAG, "setKabamAccount() onError() failed: " + paramAnonymousString.getLocalizedMessage());
              continue;
            }
            paramAnonymousThrowable = KabamSession.getClientActivity();
            paramAnonymousString = new com/kabam/soda/KabamSession$4$1;
            paramAnonymousString.<init>(this);
            paramAnonymousThrowable.runOnUiThread(paramAnonymousString);
          }
        }
        
        public void onSuccess(KabamAccountFindResponseResource paramAnonymousKabamAccountFindResponseResource)
        {
          Log.d(KabamSession.TAG, "kabamAccountGetMe onSuccess(): " + paramAnonymousKabamAccountFindResponseResource.toString());
          KabamSession.setEmailAddr(paramAnonymousKabamAccountFindResponseResource.getEmail());
          try
          {
            KabamSession.getCallback().onKabamLogin(paramAnonymousKabamAccountFindResponseResource.getAuthToken());
            return;
          }
          catch (KabamException paramAnonymousKabamAccountFindResponseResource)
          {
            for (;;)
            {
              Log.e(KabamSession.TAG, "Cannot call onKabamLogin() because " + paramAnonymousKabamAccountFindResponseResource.getLocalizedMessage());
            }
          }
        }
      });
    }
  }
  
  static void setKabamAuthToken(String paramString)
    throws KabamException
  {
    String str = (String)getSession().kabamAuthToken.getAndSet(paramString);
    if ((paramString == null) || (!paramString.equals(str))) {
      getSession().emailAddr.set(null);
    }
    getSession().saveAuthPrefsToSharedPreferences();
  }
  
  public static void setLocale(Locale paramLocale)
  {
    locale.set(paramLocale);
  }
  
  static void setUnAuthEmailAddr(String paramString)
  {
    try
    {
      getSession().unAuthEmailAddr.set(paramString);
      return;
    }
    catch (KabamException paramString)
    {
      for (;;)
      {
        Log.e(TAG, "cannot setUnAuthEmailAddr() because " + paramString.getLocalizedMessage());
      }
    }
  }
  
  public static void showKabamAccountUI(KabamAccountView paramKabamAccountView)
    throws KabamException
  {
    Activity localActivity = getClientActivity();
    if (localActivity == null)
    {
      Log.e(TAG, "Could not showKabamAccountUI because no Activity has been passed to KabamSession.init()");
      throw new KabamException("Could not showKabamAccountUI because no Activity has been passed to KabamSession.init()");
    }
    Intent localIntent = new Intent(localActivity, AccountActivity.class);
    localIntent.putExtra("FIRST_SCREEN_EXTRA_KEY", paramKabamAccountView);
    localActivity.startActivity(localIntent);
  }
  
  private static void showRetryUI()
    throws KabamException
  {
    String str = (String)getSession().kabamAuthToken.get();
    if (str == null) {}
    for (;;)
    {
      return;
      Activity localActivity = getClientActivity();
      if (localActivity == null)
      {
        Log.e(TAG, "Could not showRetryUI because no Activity has been passed to KabamSession.init()");
        throw new KabamException("Could not showRetryUI because no Activity has been passed to KabamSession.init()");
      }
      Log.d(TAG, "Creating intent for showRetryUI");
      Intent localIntent = new Intent(localActivity, AccountActivity.class);
      localIntent.putExtra("SCREEN", AccountActivity.Screen.VALIDATE_ACCOUNT_RETRY);
      localIntent.putExtra("TOKEN", str);
      localActivity.startActivity(localIntent);
    }
  }
  
  public static void stop() {}
  
  public static enum KabamAccountView
  {
    LOG_IN,  REGISTER;
    
    private KabamAccountView() {}
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\kabam\soda\KabamSession.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */