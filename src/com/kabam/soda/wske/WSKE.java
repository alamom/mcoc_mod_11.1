package com.kabam.soda.wske;

import android.app.Activity;
import android.os.AsyncTask.Status;
import android.os.Handler;
import android.util.Log;
import com.kabam.soda.AuthSource;
import com.kabam.soda.FeatureConfig;
import com.kabam.soda.Settings;
import com.kabam.wske.model.ConfigServerResponseResource;
import com.kabam.wske.model.KabamAccountCreateBodyResource;
import com.kabam.wske.model.KabamAccountFindResponseResource;
import com.kabam.wske.model.KabamAccountLoginBodyResource;
import com.kabam.wske.model.KabamAccountLoginResponseResource;
import com.kabam.wske.model.PlayerConfigResource;
import com.kabam.wske.model.SupportLinkResource;
import com.kabam.wske.model.TranslationResource;
import java.util.List;
import java.util.Set;

public class WSKE
{
  public static final int ACCOUNT_CONFLICT = 5009;
  public static final int ACCOUNT_DOES_NOT_EXIST = 5003;
  public static final int ACC_WITHOUT_ASSOC = 1006;
  public static final int APISERVER_TIMEOUT = 5008;
  public static final int AUTH_TOKEN_PROOF_REQUIRED = 1014;
  public static final int DATABASE_ERROR = 1003;
  public static final int DUPLICATE_ENTRY = 1001;
  public static final int DUPLICATE_USER = 5001;
  public static final String EVENT_LOYALTY_FTE_VIEWED = "loyaltyFteViewed";
  public static final String EVENT_PLAY = "play";
  public static final String EVENT_REWARDS_UI_OPENED = "rewardsUiOpened";
  public static final int F500_EVENT_COUNT_EXCEEDED = 1008;
  public static final int F500_EVENT_RECORD_ERROR = 1007;
  public static final int F500_REDEEM_NOT_ENOUGH_POINTS = 1009;
  public static final int GENERIC_ERROR = 0;
  public static final int INVALID_AUTH_TOKEN = 1012;
  public static final int INVALID_CLIENT_CONTEXT = 1020;
  public static final int INVALID_EMAIL = 5007;
  public static final int INVALID_MARKETTING_EMAIL_PARAMS = 1023;
  public static final int INVALID_PASSWORD = 5006;
  public static final int INVALID_RESOURCE_VERSION = 1022;
  public static final int INVALID_SIGNED_REQUEST = 1021;
  public static final int INVALID_TIMESTAMP = 1013;
  public static final int INVALID_WSKE_TOKEN = 1005;
  public static final int JS_VALIDATION_ERR = 1000;
  public static final int KADOPTING_EXTRA_PLAYER = 5011;
  public static final String MARKETING_OPT_IN = "in";
  public static final String MARKETING_OPT_NONE = "none";
  public static final String MARKETING_OPT_OUT = "out";
  public static final int MAX_REDEMPTIONS_REACHED = 1016;
  public static final int MISSING_PARAMS = 1004;
  public static final int NO_ERROR = -1;
  public static final int PLAYER_ALREADY_ADOPTED = 5010;
  public static final int PLAYER_CERTIFICATE_EXPIRED = 1010;
  public static final int PLAYER_CERTIFICATE_INVALID = 1011;
  public static final String TAG = WSKE.class.getSimpleName();
  public static final int UNAUTHORIZED_USER = 1015;
  public static final int UNKNOWN_ERROR = 1002;
  public static final int USER_NAME_INVALID = 5004;
  public static final int USER_NAME_TAKEN = 5005;
  public static final int USER_TOO_YOUNG = 5002;
  public static final int WSKE_DOWN = 8663;
  public static final int XLATE_CONNECTION_ERROR = 1018;
  public static final int XLATE_TIMEOUT = 1019;
  public static final int ZERO_ASSOCIATIONS_LEFT = 1017;
  
  public static void createAccount(Activity paramActivity, Settings paramSettings, final WSKECallback<String> paramWSKECallback, final String paramString1, final String paramString2)
  {
    paramActivity.runOnUiThread(new Runnable()
    {
      public void run()
      {
        WSKE.executeTask(new CreateAccountAsyncTask(this.val$settings, paramWSKECallback), new CreateAccountData[] { new CreateAccountData(paramString1, paramString2) });
      }
    });
  }
  
  public static String errorName(int paramInt)
  {
    String str;
    switch (paramInt)
    {
    default: 
      str = "UNKNOWN_WSKE_ERROR_CODE:" + paramInt;
    }
    for (;;)
    {
      return str;
      str = "NO_ERROR";
      continue;
      str = "GENERIC_ERROR";
      continue;
      str = "JS_VALIDATION_ERR";
      continue;
      str = "DUPLICATE_ENTRY";
      continue;
      str = "UNKNOWN_ERROR";
      continue;
      str = "DATABASE_ERROR";
      continue;
      str = "MISSING_PARAMS";
      continue;
      str = "INVALID_WSKE_TOKEN";
      continue;
      str = "ACC_WITHOUT_ASSOC";
      continue;
      str = "F500_EVENT_RECORD_ERROR";
      continue;
      str = "F500_EVENT_COUNT_EXCEEDED";
      continue;
      str = "F500_REDEEM_NOT_ENOUGH_POINTS";
      continue;
      str = "PLAYER_CERTIFICATE_EXPIRED";
      continue;
      str = "PLAYER_CERTIFICATE_INVALID";
      continue;
      str = "INVALID_AUTH_TOKEN";
      continue;
      str = "INVALID_TIMESTAMP";
      continue;
      str = "AUTH_TOKEN_PROOF_REQUIRED";
      continue;
      str = "UNAUTHORIZED_USER";
      continue;
      str = "MAX_REDEMPTIONS_REACHED";
      continue;
      str = "ZERO_ASSOCIATIONS_LEFT";
      continue;
      str = "XLATE_CONNECTION_ERROR";
      continue;
      str = "XLATE_TIMEOUT";
      continue;
      str = "INVALID_CLIENT_CONTEXT";
      continue;
      str = "INVALID_SIGNED_REQUEST";
      continue;
      str = "INVALID_RESOURCE_VERSION";
      continue;
      str = "INVALID_MARKETTING_EMAIL_PARAMS";
      continue;
      str = "DUPLICATE_USER";
      continue;
      str = "USER_TOO_YOUNG";
      continue;
      str = "ACCOUNT_DOES_NOT_EXIST";
      continue;
      str = "USER_NAME_INVALID";
      continue;
      str = "USER_NAME_TAKEN";
      continue;
      str = "INVALID_PASSWORD";
      continue;
      str = "INVALID_EMAIL";
      continue;
      str = "APISERVER_TIMEOUT";
      continue;
      str = "ACCOUNT_CONFLICT";
      continue;
      str = "WSKE_DOWN";
    }
  }
  
  protected static <Params, Result> void executeTask(WSKEAsyncTask<Params, ?, Result> paramWSKEAsyncTask, Params... paramVarArgs)
  {
    paramWSKEAsyncTask.execute(paramVarArgs);
    final long l = FeatureConfig.getLong("WSKETimeoutMillis");
    new Handler().postDelayed(new Runnable()
    {
      public void run()
      {
        if (this.val$task.getStatus() == AsyncTask.Status.RUNNING)
        {
          if (this.val$task.getError() != null) {
            Log.e(this.val$task.TAG, this.val$task.getErrorMessage() + " - " + this.val$task.getError().getMessage());
          }
          Log.d(this.val$task.TAG, "Timeout after " + l + " milliseconds");
          this.val$task.cancel(true);
        }
      }
    }, l);
  }
  
  public static void getClientSettings(Activity paramActivity, Settings paramSettings, final WSKECallback<ConfigServerResponseResource> paramWSKECallback)
  {
    paramActivity.runOnUiThread(new Runnable()
    {
      public void run()
      {
        WSKE.executeTask(new GetClientSettingsAsyncTask(this.val$settings, paramWSKECallback), new String[] { this.val$settings.getClientId() });
      }
    });
  }
  
  public static void getFeatureConfig(Activity paramActivity, Settings paramSettings, final WSKECallback<PlayerConfigResource> paramWSKECallback, final String paramString)
  {
    paramActivity.runOnUiThread(new Runnable()
    {
      public void run()
      {
        WSKE.executeTask(new GetFeatureConfigAsyncTask(this.val$settings, paramWSKECallback), new String[] { paramString });
      }
    });
  }
  
  public static void getSupportLink(Activity paramActivity, Settings paramSettings, final WSKECallback<SupportLinkResource> paramWSKECallback, final String paramString)
  {
    paramActivity.runOnUiThread(new Runnable()
    {
      public void run()
      {
        WSKE.executeTask(new GetSupportLinkAsyncTask(this.val$settings, paramWSKECallback), new String[] { paramString });
      }
    });
  }
  
  public static void getTranslations(Activity paramActivity, Settings paramSettings, final WSKECallback<List<TranslationResource>> paramWSKECallback)
  {
    paramActivity.runOnUiThread(new Runnable()
    {
      public void run()
      {
        WSKE.executeTask(new GetTranslationsAsyncTask(this.val$settings, paramWSKECallback), new Void[0]);
      }
    });
  }
  
  public static void kabamAccountCreate(Activity paramActivity, Settings paramSettings, final String paramString1, final String paramString2, final String paramString3, final String paramString4, final String paramString5, final WSKECallback<KabamAccountLoginResponseResource> paramWSKECallback)
  {
    paramActivity.runOnUiThread(new Runnable()
    {
      public void run()
      {
        KAccountCreateAsyncTask localKAccountCreateAsyncTask = new KAccountCreateAsyncTask(this.val$settings, paramWSKECallback);
        KabamAccountCreateBodyResource localKabamAccountCreateBodyResource = new KabamAccountCreateBodyResource();
        localKabamAccountCreateBodyResource.setUserName("");
        localKabamAccountCreateBodyResource.setBirthday("");
        localKabamAccountCreateBodyResource.setPlayerId("");
        localKabamAccountCreateBodyResource.setEmail(paramString1);
        localKabamAccountCreateBodyResource.setPassword(paramString2);
        localKabamAccountCreateBodyResource.setEmailOpt(paramString3);
        if (paramString4 != null) {
          localKabamAccountCreateBodyResource.setBirthday(paramString4);
        }
        if (paramString5 != null) {
          localKabamAccountCreateBodyResource.setPlayerId(paramString5);
        }
        WSKE.executeTask(localKAccountCreateAsyncTask, new KabamAccountCreateBodyResource[] { localKabamAccountCreateBodyResource });
      }
    });
  }
  
  public static void kabamAccountGetMe(Activity paramActivity, Settings paramSettings, String paramString, final long paramLong, final WSKECallback<KabamAccountFindResponseResource> paramWSKECallback)
  {
    paramActivity.runOnUiThread(new Runnable()
    {
      public void run()
      {
        WSKE.executeTask((KAccountGetMeAsyncTask)new KAccountGetMeAsyncTask(this.val$settings, paramWSKECallback).withMilliSecondsToSleep(paramLong), new String[] { this.val$kAuthToken });
      }
    });
  }
  
  public static void kabamAccountLogin(Activity paramActivity, Settings paramSettings, final String paramString1, final String paramString2, final String paramString3, final WSKECallback<KabamAccountLoginResponseResource> paramWSKECallback)
  {
    paramActivity.runOnUiThread(new Runnable()
    {
      public void run()
      {
        KAccountLoginAsyncTask localKAccountLoginAsyncTask = new KAccountLoginAsyncTask(this.val$settings, paramWSKECallback);
        KabamAccountLoginBodyResource localKabamAccountLoginBodyResource = new KabamAccountLoginBodyResource();
        localKabamAccountLoginBodyResource.setEmail(paramString1);
        localKabamAccountLoginBodyResource.setPassword(paramString2);
        if (paramString3 == null) {
          localKabamAccountLoginBodyResource.setPlayerId("");
        }
        for (;;)
        {
          WSKE.executeTask(localKAccountLoginAsyncTask, new KabamAccountLoginBodyResource[] { localKabamAccountLoginBodyResource });
          return;
          localKabamAccountLoginBodyResource.setPlayerId(paramString3);
        }
      }
    });
  }
  
  public static void listAuthenticatedNetworks(Activity paramActivity, Settings paramSettings, final WSKECallback<Set<AuthSource>> paramWSKECallback, final String paramString)
  {
    paramActivity.runOnUiThread(new Runnable()
    {
      public void run()
      {
        WSKE.executeTask(new ListAuthenticatedNetworksAsyncTask(this.val$settings, paramWSKECallback), new String[] { paramString });
      }
    });
  }
  
  public static void sendEvent(Activity paramActivity, Settings paramSettings, final WSKECallback<Boolean> paramWSKECallback, final String paramString1, final String paramString2)
  {
    paramActivity.runOnUiThread(new Runnable()
    {
      public void run()
      {
        WSKE.executeTask(new SendEventAsyncTask(this.val$settings, paramWSKECallback), new String[] { paramString1, paramString2 });
      }
    });
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\kabam\soda\wske\WSKE.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */