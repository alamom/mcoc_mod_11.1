package com.google.android.gms.internal;

import android.content.Context;
import android.text.TextUtils;
import java.math.BigInteger;
import java.util.Locale;

@ez
public final class gf
{
  private static final Object uf = new Object();
  private static String we;
  
  public static String a(Context paramContext, String paramString1, String paramString2)
  {
    synchronized (uf)
    {
      if ((we == null) && (!TextUtils.isEmpty(paramString1))) {
        b(paramContext, paramString1, paramString2);
      }
      paramContext = we;
      return paramContext;
    }
  }
  
  private static void b(Context paramContext, String paramString1, String paramString2)
  {
    try
    {
      ClassLoader localClassLoader = paramContext.createPackageContext(paramString2, 3).getClassLoader();
      paramString2 = Class.forName("com.google.ads.mediation.MediationAdapter", false, localClassLoader);
      paramContext = new BigInteger(new byte[1]);
      String[] arrayOfString = paramString1.split(",");
      int i = 0;
      while (i < arrayOfString.length)
      {
        paramString1 = paramContext;
        if (gj.a(localClassLoader, paramString2, arrayOfString[i])) {
          paramString1 = paramContext.setBit(i);
        }
        i++;
        paramContext = paramString1;
        continue;
        return;
      }
    }
    catch (Throwable paramContext)
    {
      we = "err";
    }
    for (;;)
    {
      we = String.format(Locale.US, "%X", new Object[] { paramContext });
    }
  }
  
  public static String di()
  {
    synchronized (uf)
    {
      String str = we;
      return str;
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\internal\gf.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */