package com.google.android.gms.analytics;

import android.text.TextUtils;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

class aj
{
  private static final char[] BJ = { 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 65, 66, 67, 68, 69, 70 };
  
  static String C(boolean paramBoolean)
  {
    if (paramBoolean) {}
    for (String str = "1";; str = "0") {
      return str;
    }
  }
  
  public static double a(String paramString, double paramDouble)
  {
    if (paramString == null) {}
    for (;;)
    {
      return paramDouble;
      try
      {
        double d = Double.parseDouble(paramString);
        paramDouble = d;
      }
      catch (NumberFormatException paramString) {}
    }
  }
  
  static String a(Locale paramLocale)
  {
    Object localObject = null;
    if (paramLocale == null) {}
    for (;;)
    {
      return (String)localObject;
      if (!TextUtils.isEmpty(paramLocale.getLanguage()))
      {
        localObject = new StringBuilder();
        ((StringBuilder)localObject).append(paramLocale.getLanguage().toLowerCase());
        if (!TextUtils.isEmpty(paramLocale.getCountry())) {
          ((StringBuilder)localObject).append("-").append(paramLocale.getCountry().toLowerCase());
        }
        localObject = ((StringBuilder)localObject).toString();
      }
    }
  }
  
  public static void a(Map<String, String> paramMap, String paramString, l paraml)
  {
    if (!paramMap.containsKey(paramString)) {
      paramMap.put(paramString, paraml.getValue(paramString));
    }
  }
  
  public static void a(Map<String, String> paramMap, String paramString1, String paramString2)
  {
    if (!paramMap.containsKey(paramString1)) {
      paramMap.put(paramString1, paramString2);
    }
  }
  
  public static Map<String, String> an(String paramString)
  {
    HashMap localHashMap = new HashMap();
    paramString = paramString.split("&");
    int j = paramString.length;
    int i = 0;
    if (i < j)
    {
      String[] arrayOfString = paramString[i].split("=");
      if (arrayOfString.length > 1) {
        localHashMap.put(arrayOfString[0], arrayOfString[1]);
      }
      for (;;)
      {
        i++;
        break;
        if ((arrayOfString.length == 1) && (arrayOfString[0].length() != 0)) {
          localHashMap.put(arrayOfString[0], null);
        }
      }
    }
    return localHashMap;
  }
  
  public static String ao(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {
      paramString = null;
    }
    for (;;)
    {
      return paramString;
      Object localObject1 = paramString;
      Object localObject2;
      if (paramString.contains("?"))
      {
        localObject2 = paramString.split("[\\?]");
        localObject1 = paramString;
        if (localObject2.length > 1) {
          localObject1 = localObject2[1];
        }
      }
      if (((String)localObject1).contains("%3D")) {}
      do
      {
        try
        {
          paramString = URLDecoder.decode((String)localObject1, "UTF-8");
          localObject1 = an(paramString);
          paramString = new String[9];
          paramString[0] = "dclid";
          paramString[1] = "utm_source";
          paramString[2] = "gclid";
          paramString[3] = "utm_campaign";
          paramString[4] = "utm_medium";
          paramString[5] = "utm_term";
          paramString[6] = "utm_content";
          paramString[7] = "utm_id";
          paramString[8] = "gmob_t";
          localObject2 = new StringBuilder();
          for (int i = 0; i < paramString.length; i++) {
            if (!TextUtils.isEmpty((CharSequence)((Map)localObject1).get(paramString[i])))
            {
              if (((StringBuilder)localObject2).length() > 0) {
                ((StringBuilder)localObject2).append("&");
              }
              ((StringBuilder)localObject2).append(paramString[i]).append("=").append((String)((Map)localObject1).get(paramString[i]));
            }
          }
        }
        catch (UnsupportedEncodingException paramString)
        {
          paramString = null;
        }
        paramString = (String)localObject1;
      } while (((String)localObject1).contains("="));
      paramString = null;
      continue;
      paramString = ((StringBuilder)localObject2).toString();
    }
  }
  
  public static MessageDigest ap(String paramString)
  {
    int i = 0;
    if (i < 2) {}
    for (;;)
    {
      try
      {
        MessageDigest localMessageDigest = MessageDigest.getInstance(paramString);
        if (localMessageDigest != null) {
          return localMessageDigest;
        }
      }
      catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
      {
        i++;
      }
      break;
      Object localObject = null;
    }
  }
  
  public static boolean e(String paramString, boolean paramBoolean)
  {
    boolean bool = paramBoolean;
    if (paramString != null)
    {
      if ((!paramString.equalsIgnoreCase("true")) && (!paramString.equalsIgnoreCase("yes")) && (!paramString.equalsIgnoreCase("1"))) {
        break label37;
      }
      bool = true;
    }
    for (;;)
    {
      return bool;
      label37:
      if ((!paramString.equalsIgnoreCase("false")) && (!paramString.equalsIgnoreCase("no")))
      {
        bool = paramBoolean;
        if (!paramString.equalsIgnoreCase("0")) {}
      }
      else
      {
        bool = false;
      }
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\analytics\aj.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */