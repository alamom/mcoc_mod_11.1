package com.google.android.gms.internal;

import android.text.TextUtils;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class jz
{
  private static final Pattern MT = Pattern.compile("\\\\.");
  private static final Pattern MU = Pattern.compile("[\\\\\"/\b\f\n\r\t]");
  
  public static String bf(String paramString)
  {
    Object localObject1 = paramString;
    Matcher localMatcher;
    Object localObject2;
    if (!TextUtils.isEmpty(paramString))
    {
      localMatcher = MU.matcher(paramString);
      localObject2 = null;
      while (localMatcher.find())
      {
        localObject1 = localObject2;
        if (localObject2 == null) {
          localObject1 = new StringBuffer();
        }
        switch (localMatcher.group().charAt(0))
        {
        default: 
          localObject2 = localObject1;
          break;
        case '\b': 
          localMatcher.appendReplacement((StringBuffer)localObject1, "\\\\b");
          localObject2 = localObject1;
          break;
        case '"': 
          localMatcher.appendReplacement((StringBuffer)localObject1, "\\\\\\\"");
          localObject2 = localObject1;
          break;
        case '\\': 
          localMatcher.appendReplacement((StringBuffer)localObject1, "\\\\\\\\");
          localObject2 = localObject1;
          break;
        case '/': 
          localMatcher.appendReplacement((StringBuffer)localObject1, "\\\\/");
          localObject2 = localObject1;
          break;
        case '\f': 
          localMatcher.appendReplacement((StringBuffer)localObject1, "\\\\f");
          localObject2 = localObject1;
          break;
        case '\n': 
          localMatcher.appendReplacement((StringBuffer)localObject1, "\\\\n");
          localObject2 = localObject1;
          break;
        case '\r': 
          localMatcher.appendReplacement((StringBuffer)localObject1, "\\\\r");
          localObject2 = localObject1;
          break;
        case '\t': 
          localMatcher.appendReplacement((StringBuffer)localObject1, "\\\\t");
          localObject2 = localObject1;
        }
      }
      if (localObject2 != null) {
        break label241;
      }
    }
    for (localObject1 = paramString;; localObject1 = ((StringBuffer)localObject2).toString())
    {
      return (String)localObject1;
      label241:
      localMatcher.appendTail((StringBuffer)localObject2);
    }
  }
  
  public static boolean d(Object paramObject1, Object paramObject2)
  {
    boolean bool2 = false;
    JSONObject localJSONObject;
    boolean bool1;
    if (((paramObject1 instanceof JSONObject)) && ((paramObject2 instanceof JSONObject)))
    {
      paramObject1 = (JSONObject)paramObject1;
      localJSONObject = (JSONObject)paramObject2;
      if (((JSONObject)paramObject1).length() != localJSONObject.length()) {
        bool1 = bool2;
      }
    }
    for (;;)
    {
      return bool1;
      Iterator localIterator = ((JSONObject)paramObject1).keys();
      label51:
      if (localIterator.hasNext())
      {
        paramObject2 = (String)localIterator.next();
        bool1 = bool2;
        if (!localJSONObject.has((String)paramObject2)) {}
      }
      else
      {
        try
        {
          bool1 = d(((JSONObject)paramObject1).get((String)paramObject2), localJSONObject.get((String)paramObject2));
          if (bool1) {
            break label51;
          }
          bool1 = bool2;
        }
        catch (JSONException paramObject1)
        {
          int i;
          bool1 = bool2;
        }
        bool1 = true;
        continue;
        if (((paramObject1 instanceof JSONArray)) && ((paramObject2 instanceof JSONArray)))
        {
          paramObject1 = (JSONArray)paramObject1;
          paramObject2 = (JSONArray)paramObject2;
          bool1 = bool2;
          if (((JSONArray)paramObject1).length() != ((JSONArray)paramObject2).length()) {
            continue;
          }
          i = 0;
          if (i >= ((JSONArray)paramObject1).length()) {}
        }
        try
        {
          boolean bool3 = d(((JSONArray)paramObject1).get(i), ((JSONArray)paramObject2).get(i));
          bool1 = bool2;
          if (!bool3) {
            continue;
          }
          i++;
        }
        catch (JSONException paramObject1)
        {
          bool1 = bool2;
        }
        bool1 = true;
        continue;
        bool1 = paramObject1.equals(paramObject2);
        continue;
      }
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\internal\jz.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */