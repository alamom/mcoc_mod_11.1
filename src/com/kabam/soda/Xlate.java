package com.kabam.soda;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.Resources;
import android.text.Html;
import android.util.Log;
import android.widget.TextView;
import com.kabam.soda.wske.WSKE;
import com.kabam.soda.wske.WSKECallback;
import com.kabam.wske.model.SupportLinkResource;
import com.kabam.wske.model.TranslationResource;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class Xlate
{
  public static final String DEFAULT_LANGUAGE_KEY;
  private static final long MINUTES_TO_UPDATE = 30L;
  private static final long SECONDS_TO_REQUEST = 10L;
  public static final String SUBSTITUTION_KEY_NUM_ATTEMPTS = "numAttempts";
  private static final String TAG = Xlate.class.getSimpleName();
  public static final String TRANSLATION_DATE_REQUESTED_KEY;
  public static final String TRANSLATION_DATE_UPDATED_KEY;
  public static final String accountcreate_birthday = "kabam.soda.accountcreate.birthday";
  public static final String accountcreate_birthday_heading = "kabam.soda.accountcreate.birthday.heading";
  public static final String accountcreate_button = "kabam.soda.accountcreate.button";
  public static final String accountcreate_cancelbutton = "kabam.soda.accountcreate.cancelbutton";
  public static final String accountcreate_error_duplicateuser = "kabam.soda.accountcreate.error.duplicateuser";
  public static final String accountcreate_error_invalidemail = "kabam.soda.accountcreate.error.invalidemail";
  public static final String accountcreate_error_invalidpassword = "kabam.soda.accountcreate.error.invalidpassword";
  public static final String accountcreate_error_kaadoptingextraplayer = "kabam.soda.accountcreate.error.kaadoptingextraplayer";
  public static final String accountcreate_error_missingbirthday = "kabam.soda.accountcreate.error.missingbirthday";
  public static final String accountcreate_error_playeralreadyadopted = "kabam.soda.accountcreate.error.playeralreadyadopted";
  public static final String accountcreate_error_tooyoung = "kabam.soda.accountcreate.error.tooyoung";
  public static final String accountcreate_error_unknown = "kabam.soda.accountcreate.error.unknown";
  public static final String accountcreate_haveaccountbutton = "kabam.soda.accountcreate.haveaccountbutton";
  public static final String accountcreate_marketingemail_checkbox = "kabam.soda.accountcreate.marketingemail.checkbox";
  public static final String accountcreate_signingup = "kabam.soda.accountcreate.signingup";
  public static final String accountloggedin_text = "kabam.soda.accountloggedin.text";
  public static final String accountlogin_button = "kabam.soda.accountlogin.button";
  public static final String accountlogin_cancelbutton = "kabam.soda.accountlogin.cancelbutton";
  public static final String accountlogin_createaccountbutton = "kabam.soda.accountlogin.createaccountbutton";
  public static final String accountlogin_email = "kabam.soda.accountlogin.email";
  public static final String accountlogin_error_badcredentials = "kabam.soda.accountlogin.error.badcredentials";
  public static final String accountlogin_error_missingemail = "kabam.soda.accountlogin.error.missingemail";
  public static final String accountlogin_error_missingpassword = "kabam.soda.accountlogin.error.missingpassword";
  public static final String accountlogin_error_unknown = "kabam.soda.accountlogin.error.unknown";
  public static final String accountlogin_forgotpassword_text = "kabam.soda.accountlogin.forgotpassword.text";
  public static final String accountlogin_forgotpassword_url = "kabam.soda.accountlogin.forgotpassword.url";
  public static final String accountlogin_loggingin = "kabam.soda.accountlogin.loggingin";
  public static final String accountlogin_password = "kabam.soda.accountlogin.password";
  public static final String accountrelogin_alert = "kabam.soda.accountrelogin.alert";
  public static final String accountrelogin_statusline1 = "kabam.soda.accountrelogin.statusline1";
  public static final String accountrelogin_statusline2 = "kabam.soda.accountrelogin.statusline2";
  public static final String accountrelogin_success = "kabam.soda.accountrelogin.success";
  private static String currentLanguage = "en";
  private static Map<String, String> englishMap = new HashMap();
  private static Map<String, String> languageMap;
  public static final String redeem_authterms = "kabam.soda.redeem.authterms";
  
  static
  {
    DEFAULT_LANGUAGE_KEY = Utility.PREFS_NAME + "_DEFAULT_LANGUAGE_KEY";
    TRANSLATION_DATE_UPDATED_KEY = Utility.PREFS_NAME + "_TRANSLATION_DATE_UPDATED_KEY";
    TRANSLATION_DATE_REQUESTED_KEY = Utility.PREFS_NAME + "_TRANSLATION_DATE_REQUESTED_KEY";
    languageMap = new HashMap();
  }
  
  public static String getStringResourceByName(String paramString, Context paramContext)
  {
    String str = paramContext.getPackageName();
    int i = paramContext.getResources().getIdentifier(paramString.replace(".", "_"), "string", str);
    if (i != 0) {}
    for (paramString = paramContext.getString(i);; paramString = "") {
      return paramString;
    }
  }
  
  public static String getTranslation(String paramString, Context paramContext)
  {
    for (;;)
    {
      try
      {
        if (languageMap.isEmpty())
        {
          localStringBuilder = new java/lang/StringBuilder;
          localStringBuilder.<init>();
          languageMap = paramContext.getSharedPreferences(Utility.PREFS_NAME + "_" + currentLanguage, 0).getAll();
        }
        if (!languageMap.containsKey(paramString)) {
          continue;
        }
        paramContext = ((String)languageMap.get(paramString)).toString();
        paramString = paramContext;
      }
      catch (NullPointerException paramContext)
      {
        StringBuilder localStringBuilder;
        Log.e(TAG, "Null Pointer Exception - translation key / Local : " + paramString + " / " + currentLanguage, paramContext);
        Log.w(TAG, "No translation for " + paramString);
        paramString = "";
        continue;
      }
      catch (Exception paramContext)
      {
        Log.e(TAG, "Exception: " + paramContext, paramContext);
        continue;
      }
      return paramString;
      if (englishMap.isEmpty())
      {
        localStringBuilder = new java/lang/StringBuilder;
        localStringBuilder.<init>();
        englishMap = paramContext.getSharedPreferences(Utility.PREFS_NAME + "_en", 0).getAll();
      }
      if (englishMap.containsKey(paramString))
      {
        paramContext = ((String)englishMap.get(paramString)).toString();
        paramString = paramContext;
      }
      else
      {
        if (getStringResourceByName(paramString, paramContext) == "") {
          continue;
        }
        paramContext = getStringResourceByName(paramString, paramContext);
        paramString = paramContext;
      }
    }
  }
  
  public static String getTranslation(String paramString, Context paramContext, Map<String, String> paramMap)
  {
    paramString = getTranslation(paramString, paramContext);
    paramContext = paramString;
    if (paramMap != null)
    {
      Iterator localIterator = paramMap.keySet().iterator();
      for (;;)
      {
        paramContext = paramString;
        if (!localIterator.hasNext()) {
          break;
        }
        paramContext = (String)localIterator.next();
        paramString = paramString.replace("{" + paramContext + "}", (CharSequence)paramMap.get(paramContext));
      }
    }
    return paramContext;
  }
  
  public static void loadTranslations(Context paramContext, Boolean paramBoolean)
  {
    try
    {
      currentLanguage = KabamSession.getLocale().toString();
      Object localObject = paramContext.getSharedPreferences(Utility.PREFS_NAME, 0);
      if (shouldGetTranslationsFromWSKE(((SharedPreferences)localObject).getString(DEFAULT_LANGUAGE_KEY, ""), ((SharedPreferences)localObject).getLong(TRANSLATION_DATE_UPDATED_KEY, 0L), ((SharedPreferences)localObject).getLong(TRANSLATION_DATE_REQUESTED_KEY, 0L), paramBoolean).booleanValue())
      {
        paramBoolean = ((SharedPreferences)localObject).edit();
        paramBoolean.putLong(TRANSLATION_DATE_REQUESTED_KEY, System.currentTimeMillis());
        paramBoolean.apply();
        Activity localActivity = (Activity)paramContext;
        Settings localSettings = KabamSession.getSettings();
        localObject = new com/kabam/soda/Xlate$1;
        ((1)localObject).<init>(paramContext, paramBoolean);
        WSKE.getTranslations(localActivity, localSettings, (WSKECallback)localObject);
      }
      return;
    }
    catch (NullPointerException paramContext)
    {
      for (;;)
      {
        Log.e(TAG, "Null Pointer exception", paramContext);
      }
    }
    catch (Exception paramContext)
    {
      for (;;)
      {
        Log.e(TAG, "Exception", paramContext);
      }
    }
  }
  
  public static void setSupportLink(Context paramContext)
    throws KabamException
  {
    SharedPreferences.Editor localEditor = paramContext.getSharedPreferences(Utility.PREFS_NAME + "_" + KabamSession.getLocale().toString(), 0).edit();
    WSKE.getSupportLink((Activity)paramContext, KabamSession.getSettings(), new WSKECallback()
    {
      public void onError(String paramAnonymousString, Throwable paramAnonymousThrowable, int paramAnonymousInt)
      {
        String str = "getSupportLink() onError(): " + paramAnonymousInt + " = " + paramAnonymousString;
        paramAnonymousString = str;
        if (paramAnonymousThrowable != null) {
          paramAnonymousString = str + " because " + paramAnonymousThrowable;
        }
        Log.e(Xlate.TAG, paramAnonymousString);
      }
      
      public void onSuccess(SupportLinkResource paramAnonymousSupportLinkResource)
      {
        if (paramAnonymousSupportLinkResource.getUrl() != null)
        {
          this.val$editor.putString("kabam.soda.support.link", paramAnonymousSupportLinkResource.getUrl());
          this.val$editor.apply();
          Xlate.languageMap.put("kabam.soda.support.link", paramAnonymousSupportLinkResource.getUrl());
          if (KabamSession.getLocale().getLanguage().equals(Locale.ENGLISH.toString())) {
            Xlate.englishMap.put("kabam.soda.support.link", paramAnonymousSupportLinkResource.getUrl());
          }
        }
      }
    }, KabamSession.getPlayerId());
  }
  
  public static void setTextAndContentDescription(TextView paramTextView, String paramString, Context paramContext)
  {
    paramString = Html.fromHtml(getTranslation(paramString, paramContext));
    if (paramTextView != null)
    {
      paramTextView.setText(paramString);
      paramTextView.setContentDescription(paramString);
    }
  }
  
  public static Boolean shouldGetTranslationsFromWSKE(String paramString, long paramLong1, long paramLong2, Boolean paramBoolean)
  {
    paramLong1 = System.currentTimeMillis() - paramLong1;
    paramLong2 = System.currentTimeMillis() - paramLong2;
    Log.d(TAG, "timeSinceLastUpdate=" + paramLong1 + " timeSinceLastRequest=" + paramLong2);
    Log.d(TAG, "storedLanguage=" + paramString + " currentLanguage=" + currentLanguage);
    if (paramBoolean.booleanValue()) {
      paramString = Boolean.valueOf(true);
    }
    for (;;)
    {
      return paramString;
      if (paramLong2 < TimeUnit.SECONDS.toMillis(10L)) {
        paramString = Boolean.valueOf(false);
      } else if ((!paramString.equals(currentLanguage)) || (paramLong1 > TimeUnit.MINUTES.toMillis(30L))) {
        paramString = Boolean.valueOf(true);
      } else {
        paramString = Boolean.valueOf(false);
      }
    }
  }
  
  private static Map<String, String> storeTranslations(Locale paramLocale, List<TranslationResource> paramList, Context paramContext)
  {
    paramLocale = paramContext.getSharedPreferences(Utility.PREFS_NAME + "_" + paramLocale.toString(), 0);
    paramContext = paramLocale.edit();
    paramList = paramList.iterator();
    while (paramList.hasNext())
    {
      TranslationResource localTranslationResource = (TranslationResource)paramList.next();
      paramContext.putString(localTranslationResource.getPhrase(), localTranslationResource.getTrans());
    }
    paramContext.apply();
    return paramLocale.getAll();
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\kabam\soda\Xlate.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */