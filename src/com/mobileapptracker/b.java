package com.mobileapptracker;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

final class b
{
  private static volatile b i;
  private String a = null;
  private String b = null;
  private String c = null;
  private String d = null;
  private int e = 0;
  private String f = null;
  private String g = null;
  private MATResponse h = null;
  
  public static b a(String paramString1, String paramString2, String paramString3)
  {
    try
    {
      b localb = new com/mobileapptracker/b;
      localb.<init>();
      i = localb;
      localb.a = paramString1;
      i.b = paramString2;
      i.c = paramString3;
      paramString1 = i;
      return paramString1;
    }
    finally
    {
      paramString1 = finally;
      throw paramString1;
    }
  }
  
  public static String a()
  {
    return i.a;
  }
  
  public static void a(MATResponse paramMATResponse)
  {
    i.h = paramMATResponse;
  }
  
  public static void a(String paramString)
  {
    i.c = paramString;
  }
  
  public static void a(String paramString, int paramInt)
  {
    i.d = paramString;
    i.e = paramInt;
  }
  
  public static String b()
  {
    return i.b;
  }
  
  public static void b(String paramString)
  {
    i.g = paramString;
  }
  
  public static String c()
  {
    return i.c;
  }
  
  public static void c(String paramString)
  {
    i.f = paramString;
  }
  
  public static String d()
  {
    return i.g;
  }
  
  public static String e()
  {
    return i.d;
  }
  
  public static int f()
  {
    return i.e;
  }
  
  public static String g()
  {
    return i.f;
  }
  
  public final String a(Context paramContext, int paramInt)
  {
    String str2 = "";
    Object localObject = str2;
    if (i.a != null)
    {
      localObject = str2;
      if (i.b != null)
      {
        if (i.c != null) {
          break label46;
        }
        localObject = str2;
      }
    }
    for (;;)
    {
      return (String)localObject;
      label46:
      if (i.d == null)
      {
        localObject = str2;
        if (i.f == null) {}
      }
      else
      {
        String str1 = str2;
        try
        {
          localObject = i;
          str1 = str2;
          str2 = g.a(paramInt);
          localObject = str2;
          str1 = str2;
          if (str2.length() != 0)
          {
            str1 = str2;
            if (this.h != null)
            {
              str1 = str2;
              this.h.didReceiveDeeplink(str2);
            }
            str1 = str2;
            localObject = new android/content/Intent;
            str1 = str2;
            ((Intent)localObject).<init>("android.intent.action.VIEW");
            str1 = str2;
            ((Intent)localObject).setData(Uri.parse(str2));
            str1 = str2;
            ((Intent)localObject).setFlags(268435456);
            str1 = str2;
            paramContext.startActivity((Intent)localObject);
            localObject = str2;
          }
        }
        catch (Exception paramContext)
        {
          localObject = str1;
        }
      }
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\mobileapptracker\b.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */