package com.mobileapptracker;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Context;
import android.util.Patterns;
import java.util.HashMap;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

final class am
  implements Runnable
{
  am(MobileAppTracker paramMobileAppTracker, boolean paramBoolean) {}
  
  public final void run()
  {
    int j = 0;
    int i;
    if (this.b.mContext.checkCallingOrSelfPermission("android.permission.GET_ACCOUNTS") == 0) {
      i = 1;
    }
    while ((this.a) && (i != 0))
    {
      Object localObject = AccountManager.get(this.b.mContext).getAccountsByType("com.google");
      if (localObject.length > 0) {
        this.b.params.setUserEmail(localObject[0].name);
      }
      HashMap localHashMap = new HashMap();
      Account[] arrayOfAccount = AccountManager.get(this.b.mContext).getAccounts();
      int k = arrayOfAccount.length;
      i = j;
      for (;;)
      {
        if (i < k)
        {
          localObject = arrayOfAccount[i];
          if (Patterns.EMAIL_ADDRESS.matcher(((Account)localObject).name).matches()) {
            localHashMap.put(((Account)localObject).name, ((Account)localObject).type);
          }
          i++;
          continue;
          i = 0;
          break;
        }
      }
      localObject = localHashMap.keySet();
      localObject = (String[])((Set)localObject).toArray(new String[((Set)localObject).size()]);
      this.b.params.setUserEmails((String[])localObject);
    }
    for (;;)
    {
      return;
      this.b.params.setUserEmail(null);
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\mobileapptracker\am.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */