package com.google.android.gms.internal;

import android.text.TextUtils;
import com.google.android.gms.common.images.WebImage;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class iu
{
  private static final ip Gr = new ip("MetadataUtils");
  private static final String[] HA = { "Z", "+hh", "+hhmm", "+hh:mm" };
  private static final String HB = "yyyyMMdd'T'HHmmss" + HA[0];
  
  public static String a(Calendar paramCalendar)
  {
    if (paramCalendar == null)
    {
      Gr.b("Calendar object cannot be null", new Object[0]);
      paramCalendar = null;
    }
    for (;;)
    {
      return paramCalendar;
      String str = HB;
      Object localObject = str;
      if (paramCalendar.get(11) == 0)
      {
        localObject = str;
        if (paramCalendar.get(12) == 0)
        {
          localObject = str;
          if (paramCalendar.get(13) == 0) {
            localObject = "yyyyMMdd";
          }
        }
      }
      localObject = new SimpleDateFormat((String)localObject);
      ((SimpleDateFormat)localObject).setTimeZone(paramCalendar.getTimeZone());
      localObject = ((SimpleDateFormat)localObject).format(paramCalendar.getTime());
      paramCalendar = (Calendar)localObject;
      if (((String)localObject).endsWith("+0000")) {
        paramCalendar = ((String)localObject).replace("+0000", HA[0]);
      }
    }
  }
  
  /* Error */
  public static void a(List<WebImage> paramList, JSONObject paramJSONObject)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokeinterface 106 1 0
    //   6: aload_1
    //   7: ldc 108
    //   9: invokevirtual 114	org/json/JSONObject:getJSONArray	(Ljava/lang/String;)Lorg/json/JSONArray;
    //   12: astore_1
    //   13: aload_1
    //   14: invokevirtual 120	org/json/JSONArray:length	()I
    //   17: istore_3
    //   18: iconst_0
    //   19: istore_2
    //   20: iload_2
    //   21: iload_3
    //   22: if_icmpge +38 -> 60
    //   25: aload_1
    //   26: iload_2
    //   27: invokevirtual 124	org/json/JSONArray:getJSONObject	(I)Lorg/json/JSONObject;
    //   30: astore 5
    //   32: new 126	com/google/android/gms/common/images/WebImage
    //   35: astore 4
    //   37: aload 4
    //   39: aload 5
    //   41: invokespecial 129	com/google/android/gms/common/images/WebImage:<init>	(Lorg/json/JSONObject;)V
    //   44: aload_0
    //   45: aload 4
    //   47: invokeinterface 133 2 0
    //   52: pop
    //   53: iinc 2 1
    //   56: goto -36 -> 20
    //   59: astore_0
    //   60: return
    //   61: astore 4
    //   63: goto -10 -> 53
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	66	0	paramList	List<WebImage>
    //   0	66	1	paramJSONObject	JSONObject
    //   19	35	2	i	int
    //   17	6	3	j	int
    //   35	11	4	localWebImage	WebImage
    //   61	1	4	localIllegalArgumentException	IllegalArgumentException
    //   30	10	5	localJSONObject	JSONObject
    // Exception table:
    //   from	to	target	type
    //   0	18	59	org/json/JSONException
    //   25	32	59	org/json/JSONException
    //   32	53	59	org/json/JSONException
    //   32	53	61	java/lang/IllegalArgumentException
  }
  
  public static void a(JSONObject paramJSONObject, List<WebImage> paramList)
  {
    JSONArray localJSONArray;
    if ((paramList != null) && (!paramList.isEmpty()))
    {
      localJSONArray = new JSONArray();
      paramList = paramList.iterator();
      while (paramList.hasNext()) {
        localJSONArray.put(((WebImage)paramList.next()).bK());
      }
    }
    try
    {
      paramJSONObject.put("images", localJSONArray);
      return;
    }
    catch (JSONException paramJSONObject)
    {
      for (;;) {}
    }
  }
  
  public static Calendar aL(String paramString)
  {
    if (TextUtils.isEmpty(paramString))
    {
      Gr.b("Input string is empty or null", new Object[0]);
      paramString = null;
    }
    for (;;)
    {
      return paramString;
      Object localObject2 = aM(paramString);
      if (TextUtils.isEmpty((CharSequence)localObject2))
      {
        Gr.b("Invalid date format", new Object[0]);
        paramString = null;
      }
      else
      {
        Object localObject3 = aN(paramString);
        paramString = "yyyyMMdd";
        Object localObject1 = localObject2;
        if (!TextUtils.isEmpty((CharSequence)localObject3))
        {
          localObject1 = (String)localObject2 + "T" + (String)localObject3;
          if (((String)localObject3).length() != "HHmmss".length()) {
            break label137;
          }
        }
        for (paramString = "yyyyMMdd'T'HHmmss";; paramString = HB)
        {
          localObject2 = GregorianCalendar.getInstance();
          try
          {
            localObject3 = new java/text/SimpleDateFormat;
            ((SimpleDateFormat)localObject3).<init>(paramString);
            paramString = ((SimpleDateFormat)localObject3).parse((String)localObject1);
            ((Calendar)localObject2).setTime(paramString);
            paramString = (String)localObject2;
          }
          catch (ParseException paramString)
          {
            label137:
            Gr.b("Error parsing string: %s", new Object[] { paramString.getMessage() });
            paramString = null;
          }
        }
      }
    }
  }
  
  private static String aM(String paramString)
  {
    Object localObject = null;
    if (TextUtils.isEmpty(paramString))
    {
      Gr.b("Input string is empty or null", new Object[0]);
      paramString = (String)localObject;
    }
    for (;;)
    {
      return paramString;
      try
      {
        paramString = paramString.substring(0, "yyyyMMdd".length());
      }
      catch (IndexOutOfBoundsException paramString)
      {
        Gr.c("Error extracting the date: %s", new Object[] { paramString.getMessage() });
        paramString = (String)localObject;
      }
    }
  }
  
  private static String aN(String paramString)
  {
    Object localObject = null;
    if (TextUtils.isEmpty(paramString))
    {
      Gr.b("string is empty or null", new Object[0]);
      paramString = (String)localObject;
    }
    for (;;)
    {
      return paramString;
      int i = paramString.indexOf('T');
      if (i != "yyyyMMdd".length())
      {
        Gr.b("T delimeter is not found", new Object[0]);
        paramString = (String)localObject;
      }
      else
      {
        String str;
        try
        {
          str = paramString.substring(i + 1);
          if (str.length() != "HHmmss".length()) {
            break label108;
          }
          paramString = str;
        }
        catch (IndexOutOfBoundsException paramString)
        {
          Gr.b("Error extracting the time substring: %s", new Object[] { paramString.getMessage() });
          paramString = (String)localObject;
        }
        continue;
        label108:
        switch (str.charAt("HHmmss".length()))
        {
        default: 
          paramString = (String)localObject;
          break;
        case '+': 
        case '-': 
          paramString = (String)localObject;
          if (aO(str)) {
            paramString = str.replaceAll("([\\+\\-]\\d\\d):(\\d\\d)", "$1$2");
          }
          break;
        case 'Z': 
          paramString = (String)localObject;
          if (str.length() == "HHmmss".length() + HA[0].length()) {
            paramString = str.substring(0, str.length() - 1) + "+0000";
          }
          break;
        }
      }
    }
  }
  
  private static boolean aO(String paramString)
  {
    boolean bool2 = true;
    int j = paramString.length();
    int i = "HHmmss".length();
    boolean bool1 = bool2;
    if (j != HA[1].length() + i)
    {
      bool1 = bool2;
      if (j != HA[2].length() + i) {
        if (j != i + HA[3].length()) {
          break label67;
        }
      }
    }
    label67:
    for (bool1 = bool2;; bool1 = false) {
      return bool1;
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\internal\iu.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */