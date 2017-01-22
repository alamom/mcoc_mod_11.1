package com.kabam.soda;

import android.content.Context;
import android.util.Log;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.HitBuilders.AppViewBuilder;
import com.google.android.gms.analytics.HitBuilders.EventBuilder;
import com.google.android.gms.analytics.Tracker;

public class Analytics
{
  public static final String ACTION_ACCOUNT_CREATION_FAIL = "Account Creation Fail";
  public static final String ACTION_ACCOUNT_CREATION_SUCCESS = "Account Creation Success";
  public static final String ACTION_ALREADY_HAVE_ACCOUNT = "Already Have Account";
  public static final String ACTION_CANCEL = "Cancel";
  public static final String ACTION_CREATE_ACCOUNT = "Create Account";
  public static final String ACTION_EMAIL_FOCUS = "Email Focus";
  public static final String ACTION_FORGOT_PASSWORD = "Forgot Password";
  public static final String ACTION_LOGIN_FAIL = "Login Fail";
  public static final String ACTION_LOGIN_SUCCESS = "Login Success";
  public static final String ACTION_LOGOUT_SUCCESS = "Log Out Success";
  public static final String ACTION_LOG_IN = "Log In";
  public static final String ACTION_PASSWORD_FOCUS = "Password Focus";
  public static final String ACTION_TERMS_OF_SERVICE = "Terms of Service";
  public static final String CATEGORY_CREATE_ACCOUNT = "Create Account";
  public static final String CATEGORY_KABAM_LOGIN = "Kabam Login";
  public static final String CATEGORY_KABAM_LOGOUT = "Kabam Log Out";
  public static final String LABEL_OPTIN = "OptIn";
  public static final String LABEL_OPTOUT = "OptOut";
  public static final String SCREEN_CREATE_ACCOUNT = "CreateAccount";
  public static final String SCREEN_CREATE_SUCCESS = "CreateSuccess";
  public static final String SCREEN_LOGIN = "Login";
  public static final String SCREEN_LOGIN_SUCCESS = "LoginSuccess";
  private static final String TAG = Analytics.class.getSimpleName();
  private static final String TRACKER_ID_DEV = "UA-17595911-21";
  private static final String TRACKER_ID_PROD = "UA-17595911-20";
  private static Tracker tracker;
  
  private static Tracker getTracker(Context paramContext)
  {
    for (;;)
    {
      try
      {
        if (tracker == null)
        {
          localObject2 = KabamSession.getSettings();
          localObject1 = GoogleAnalytics.getInstance(paramContext.getApplicationContext());
          if (!"production".equals(((Settings)localObject2).getEnv())) {
            continue;
          }
          paramContext = "UA-17595911-20";
          tracker = ((GoogleAnalytics)localObject1).newTracker(paramContext);
        }
      }
      catch (Exception paramContext)
      {
        Object localObject1 = TAG;
        Object localObject2 = new java/lang/StringBuilder;
        ((StringBuilder)localObject2).<init>();
        Log.e((String)localObject1, "Error getting settings / setting tracker id: " + paramContext);
        continue;
      }
      finally {}
      paramContext = tracker;
      return paramContext;
      paramContext = "UA-17595911-21";
    }
  }
  
  public static void sendEvent(String paramString1, String paramString2, String paramString3, Context paramContext)
  {
    sendEvent(paramString1, paramString2, paramString3, null, paramContext);
  }
  
  public static void sendEvent(String paramString1, String paramString2, String paramString3, Long paramLong, Context paramContext)
  {
    HitBuilders.EventBuilder localEventBuilder = new HitBuilders.EventBuilder().setCategory(paramString1).setAction(paramString2);
    String str = paramString3;
    if ("".equals(paramString3)) {
      str = null;
    }
    paramString2 = paramString1 + " > " + paramString2;
    paramString1 = paramString2;
    if (str != null)
    {
      paramString1 = paramString2 + " > " + str;
      localEventBuilder.setLabel(str);
    }
    paramString2 = paramString1;
    if (paramLong != null)
    {
      paramString2 = paramString1 + " value=" + paramLong;
      localEventBuilder.setValue(paramLong.longValue());
    }
    Log.d(TAG, paramString2);
    paramString1 = getTracker(paramContext);
    if (paramString1 != null) {
      paramString1.send(localEventBuilder.build());
    }
  }
  
  public static void sendScreen(String paramString, Context paramContext)
  {
    paramContext = getTracker(paramContext);
    if (paramContext != null)
    {
      paramContext.setScreenName(paramString);
      Log.d(TAG, String.format("GA ScreenView: %s", new Object[] { paramString }));
      paramContext.send(new HitBuilders.AppViewBuilder().build());
    }
    for (;;)
    {
      return;
      Log.w(TAG, String.format("GA ScreenView without tracker: %s", new Object[] { paramString }));
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\kabam\soda\Analytics.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */