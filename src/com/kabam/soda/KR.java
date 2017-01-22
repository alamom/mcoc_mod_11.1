package com.kabam.soda;

import android.content.res.Resources;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class KR
{
  private static Map<String, Integer> cache = new ConcurrentHashMap();
  public static final String id_account_create_stub = "id/kabam_soda_account_create_stub";
  public static final String id_account_loggedin_stub = "id/kabam_soda_account_loggedin_stub";
  public static final String id_account_login_stub = "id/kabam_soda_account_login_stub";
  public static final String id_accountcreate_birthday = "id/kabam_soda_accountcreate_birthday";
  public static final String id_accountcreate_birthdaylayout = "id/kabam_soda_accountcreate_birthdaylayout";
  public static final String id_accountcreate_birthdayspinner = "id/kabam_soda_accountcreate_birthdayspinner";
  public static final String id_accountcreate_button = "id/kabam_soda_accountcreate_button";
  public static final String id_accountcreate_cancelbutton = "id/kabam_soda_accountcreate_cancelbutton";
  public static final String id_accountcreate_email = "id/kabam_soda_accountcreate_email";
  public static final String id_accountcreate_error = "id/kabam_soda_accountcreate_error";
  public static final String id_accountcreate_haveaccountbutton = "id/kabam_soda_accountcreate_haveaccountbutton";
  public static final String id_accountcreate_password = "id/kabam_soda_accountcreate_password";
  public static final String id_accountloggedin_closebutton = "id/kabam_soda_accountloggedin_closebutton";
  public static final String id_accountloggedin_email = "id/kabam_soda_accountloggedin_email";
  public static final String id_accountloggedin_text = "id/kabam_soda_accountloggedin_text";
  public static final String id_accountlogin_button = "id/kabam_soda_accountlogin_button";
  public static final String id_accountlogin_cancelbutton = "id/kabam_soda_accountlogin_cancelbutton";
  public static final String id_accountlogin_createaccountbutton = "id/kabam_soda_accountlogin_createaccountbutton";
  public static final String id_accountlogin_email = "id/kabam_soda_accountlogin_email";
  public static final String id_accountlogin_error = "id/kabam_soda_accountlogin_error";
  public static final String id_accountlogin_forgotpassword = "id/kabam_soda_accountlogin_forgotpassword";
  public static final String id_accountlogin_password = "id/kabam_soda_accountlogin_password";
  public static final String id_accountrelogin_alert = "id/kabam_soda_accountrelogin_alert";
  public static final String id_accountrelogin_cancelbutton = "id/kabam_soda_accountrelogin_cancelbutton";
  public static final String id_accountrelogin_retry_layout = "id/kabam_soda_accountrelogin_retry_layout";
  public static final String id_accountrelogin_statusline1 = "id/kabam_soda_accountrelogin_statusline1";
  public static final String id_accountrelogin_statusline2 = "id/kabam_soda_accountrelogin_statusline2";
  public static final String id_accountrelogin_success = "id/kabam_soda_accountrelogin_success";
  public static final String id_accountrelogin_success_layout = "id/kabam_soda_accountrelogin_success_layout";
  public static final String id_marketingemail_checkbox = "id/kabam_soda_marketingemail_checkbox";
  public static final String id_redeem_authterms = "id/kabam_soda_redeem_authterms";
  public static final String layout_account_relogin = "layout/kabam_soda_account_relogin_container";
  public static final String layout_container = "layout/kabam_soda_container";
  private static String packageName;
  private static Resources res;
  
  public static int get(String paramString)
  {
    Integer localInteger2 = (Integer)cache.get(paramString);
    Integer localInteger1;
    if (localInteger2 != null)
    {
      localInteger1 = localInteger2;
      if (localInteger2.intValue() != 0) {}
    }
    else
    {
      localInteger1 = Integer.valueOf(res.getIdentifier(paramString, null, packageName));
      cache.put(paramString, localInteger1);
    }
    return localInteger1.intValue();
  }
  
  public static void init(Resources paramResources, String paramString)
  {
    res = paramResources;
    packageName = paramString;
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\kabam\soda\KR.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */