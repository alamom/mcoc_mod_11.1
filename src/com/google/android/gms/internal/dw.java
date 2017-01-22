package com.google.android.gms.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import java.lang.reflect.Method;

@ez
public class dw
{
  private final Context mContext;
  private Object sk;
  
  public dw(Context paramContext)
  {
    this.mContext = paramContext;
  }
  
  public Bundle a(String paramString1, String paramString2, String paramString3)
  {
    try
    {
      Class localClass = this.mContext.getClassLoader().loadClass("com.android.vending.billing.IInAppBillingService");
      paramString1 = (Bundle)localClass.getDeclaredMethod("getBuyIntent", new Class[] { Integer.TYPE, String.class, String.class, String.class, String.class }).invoke(localClass.cast(this.sk), new Object[] { Integer.valueOf(3), paramString1, paramString2, "inapp", paramString3 });
      return paramString1;
    }
    catch (Exception paramString1)
    {
      for (;;)
      {
        gs.d("IInAppBillingService is not available, please add com.android.vending.billing.IInAppBillingService to project.", paramString1);
        paramString1 = null;
      }
    }
  }
  
  public int c(String paramString1, String paramString2)
  {
    try
    {
      Class localClass = this.mContext.getClassLoader().loadClass("com.android.vending.billing.IInAppBillingService");
      i = ((Integer)localClass.getDeclaredMethod("consumePurchase", new Class[] { Integer.TYPE, String.class, String.class }).invoke(localClass.cast(this.sk), new Object[] { Integer.valueOf(3), paramString1, paramString2 })).intValue();
      return i;
    }
    catch (Exception paramString1)
    {
      for (;;)
      {
        gs.d("IInAppBillingService is not available, please add com.android.vending.billing.IInAppBillingService to project.", paramString1);
        int i = 5;
      }
    }
  }
  
  public Bundle d(String paramString1, String paramString2)
  {
    try
    {
      Class localClass = this.mContext.getClassLoader().loadClass("com.android.vending.billing.IInAppBillingService");
      paramString1 = (Bundle)localClass.getDeclaredMethod("getPurchases", new Class[] { Integer.TYPE, String.class, String.class, String.class }).invoke(localClass.cast(this.sk), new Object[] { Integer.valueOf(3), paramString1, "inapp", paramString2 });
      return paramString1;
    }
    catch (Exception paramString1)
    {
      for (;;)
      {
        gs.d("IInAppBillingService is not available, please add com.android.vending.billing.IInAppBillingService to project.", paramString1);
        paramString1 = null;
      }
    }
  }
  
  public void destroy()
  {
    this.sk = null;
  }
  
  public void r(IBinder paramIBinder)
  {
    try
    {
      this.sk = this.mContext.getClassLoader().loadClass("com.android.vending.billing.IInAppBillingService$Stub").getDeclaredMethod("asInterface", new Class[] { IBinder.class }).invoke(null, new Object[] { paramIBinder });
      return;
    }
    catch (Exception paramIBinder)
    {
      for (;;)
      {
        gs.W("IInAppBillingService is not available, please add com.android.vending.billing.IInAppBillingService to project.");
      }
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\internal\dw.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */