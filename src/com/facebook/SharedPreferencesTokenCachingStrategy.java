package com.facebook;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import com.facebook.internal.Logger;
import com.facebook.internal.Utility;
import com.facebook.internal.Validate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SharedPreferencesTokenCachingStrategy
  extends TokenCachingStrategy
{
  private static final String DEFAULT_CACHE_KEY = "com.facebook.SharedPreferencesTokenCachingStrategy.DEFAULT_KEY";
  private static final String JSON_VALUE = "value";
  private static final String JSON_VALUE_ENUM_TYPE = "enumType";
  private static final String JSON_VALUE_TYPE = "valueType";
  private static final String TAG = SharedPreferencesTokenCachingStrategy.class.getSimpleName();
  private static final String TYPE_BOOLEAN = "bool";
  private static final String TYPE_BOOLEAN_ARRAY = "bool[]";
  private static final String TYPE_BYTE = "byte";
  private static final String TYPE_BYTE_ARRAY = "byte[]";
  private static final String TYPE_CHAR = "char";
  private static final String TYPE_CHAR_ARRAY = "char[]";
  private static final String TYPE_DOUBLE = "double";
  private static final String TYPE_DOUBLE_ARRAY = "double[]";
  private static final String TYPE_ENUM = "enum";
  private static final String TYPE_FLOAT = "float";
  private static final String TYPE_FLOAT_ARRAY = "float[]";
  private static final String TYPE_INTEGER = "int";
  private static final String TYPE_INTEGER_ARRAY = "int[]";
  private static final String TYPE_LONG = "long";
  private static final String TYPE_LONG_ARRAY = "long[]";
  private static final String TYPE_SHORT = "short";
  private static final String TYPE_SHORT_ARRAY = "short[]";
  private static final String TYPE_STRING = "string";
  private static final String TYPE_STRING_LIST = "stringList";
  private SharedPreferences cache;
  private String cacheKey;
  
  public SharedPreferencesTokenCachingStrategy(Context paramContext)
  {
    this(paramContext, null);
  }
  
  public SharedPreferencesTokenCachingStrategy(Context paramContext, String paramString)
  {
    Validate.notNull(paramContext, "context");
    String str = paramString;
    if (Utility.isNullOrEmpty(paramString)) {
      str = "com.facebook.SharedPreferencesTokenCachingStrategy.DEFAULT_KEY";
    }
    this.cacheKey = str;
    paramString = paramContext.getApplicationContext();
    if (paramString != null) {
      paramContext = paramString;
    }
    this.cache = paramContext.getSharedPreferences(this.cacheKey, 0);
  }
  
  private void deserializeKey(String paramString, Bundle paramBundle)
    throws JSONException
  {
    Object localObject1 = new JSONObject(this.cache.getString(paramString, "{}"));
    Object localObject2 = ((JSONObject)localObject1).getString("valueType");
    if (((String)localObject2).equals("bool")) {
      paramBundle.putBoolean(paramString, ((JSONObject)localObject1).getBoolean("value"));
    }
    for (;;)
    {
      return;
      int i;
      if (((String)localObject2).equals("bool[]"))
      {
        localObject2 = ((JSONObject)localObject1).getJSONArray("value");
        localObject1 = new boolean[((JSONArray)localObject2).length()];
        for (i = 0; i < localObject1.length; i++) {
          localObject1[i] = ((JSONArray)localObject2).getBoolean(i);
        }
        paramBundle.putBooleanArray(paramString, (boolean[])localObject1);
      }
      else if (((String)localObject2).equals("byte"))
      {
        paramBundle.putByte(paramString, (byte)((JSONObject)localObject1).getInt("value"));
      }
      else if (((String)localObject2).equals("byte[]"))
      {
        localObject2 = ((JSONObject)localObject1).getJSONArray("value");
        localObject1 = new byte[((JSONArray)localObject2).length()];
        for (i = 0; i < localObject1.length; i++) {
          localObject1[i] = ((byte)((JSONArray)localObject2).getInt(i));
        }
        paramBundle.putByteArray(paramString, (byte[])localObject1);
      }
      else if (((String)localObject2).equals("short"))
      {
        paramBundle.putShort(paramString, (short)((JSONObject)localObject1).getInt("value"));
      }
      else if (((String)localObject2).equals("short[]"))
      {
        localObject1 = ((JSONObject)localObject1).getJSONArray("value");
        localObject2 = new short[((JSONArray)localObject1).length()];
        for (i = 0; i < localObject2.length; i++) {
          localObject2[i] = ((short)((JSONArray)localObject1).getInt(i));
        }
        paramBundle.putShortArray(paramString, (short[])localObject2);
      }
      else if (((String)localObject2).equals("int"))
      {
        paramBundle.putInt(paramString, ((JSONObject)localObject1).getInt("value"));
      }
      else if (((String)localObject2).equals("int[]"))
      {
        localObject2 = ((JSONObject)localObject1).getJSONArray("value");
        localObject1 = new int[((JSONArray)localObject2).length()];
        for (i = 0; i < localObject1.length; i++) {
          localObject1[i] = ((JSONArray)localObject2).getInt(i);
        }
        paramBundle.putIntArray(paramString, (int[])localObject1);
      }
      else if (((String)localObject2).equals("long"))
      {
        paramBundle.putLong(paramString, ((JSONObject)localObject1).getLong("value"));
      }
      else if (((String)localObject2).equals("long[]"))
      {
        localObject2 = ((JSONObject)localObject1).getJSONArray("value");
        localObject1 = new long[((JSONArray)localObject2).length()];
        for (i = 0; i < localObject1.length; i++) {
          localObject1[i] = ((JSONArray)localObject2).getLong(i);
        }
        paramBundle.putLongArray(paramString, (long[])localObject1);
      }
      else if (((String)localObject2).equals("float"))
      {
        paramBundle.putFloat(paramString, (float)((JSONObject)localObject1).getDouble("value"));
      }
      else if (((String)localObject2).equals("float[]"))
      {
        localObject1 = ((JSONObject)localObject1).getJSONArray("value");
        localObject2 = new float[((JSONArray)localObject1).length()];
        for (i = 0; i < localObject2.length; i++) {
          localObject2[i] = ((float)((JSONArray)localObject1).getDouble(i));
        }
        paramBundle.putFloatArray(paramString, (float[])localObject2);
      }
      else if (((String)localObject2).equals("double"))
      {
        paramBundle.putDouble(paramString, ((JSONObject)localObject1).getDouble("value"));
      }
      else if (((String)localObject2).equals("double[]"))
      {
        localObject1 = ((JSONObject)localObject1).getJSONArray("value");
        localObject2 = new double[((JSONArray)localObject1).length()];
        for (i = 0; i < localObject2.length; i++) {
          localObject2[i] = ((JSONArray)localObject1).getDouble(i);
        }
        paramBundle.putDoubleArray(paramString, (double[])localObject2);
      }
      else if (((String)localObject2).equals("char"))
      {
        localObject1 = ((JSONObject)localObject1).getString("value");
        if ((localObject1 != null) && (((String)localObject1).length() == 1)) {
          paramBundle.putChar(paramString, ((String)localObject1).charAt(0));
        }
      }
      else
      {
        Object localObject3;
        if (((String)localObject2).equals("char[]"))
        {
          localObject2 = ((JSONObject)localObject1).getJSONArray("value");
          localObject1 = new char[((JSONArray)localObject2).length()];
          for (i = 0; i < localObject1.length; i++)
          {
            localObject3 = ((JSONArray)localObject2).getString(i);
            if ((localObject3 != null) && (((String)localObject3).length() == 1)) {
              localObject1[i] = ((String)localObject3).charAt(0);
            }
          }
          paramBundle.putCharArray(paramString, (char[])localObject1);
        }
        else if (((String)localObject2).equals("string"))
        {
          paramBundle.putString(paramString, ((JSONObject)localObject1).getString("value"));
        }
        else if (((String)localObject2).equals("stringList"))
        {
          localObject3 = ((JSONObject)localObject1).getJSONArray("value");
          int j = ((JSONArray)localObject3).length();
          localObject2 = new ArrayList(j);
          i = 0;
          if (i < j)
          {
            localObject1 = ((JSONArray)localObject3).get(i);
            if (localObject1 == JSONObject.NULL) {}
            for (localObject1 = null;; localObject1 = (String)localObject1)
            {
              ((ArrayList)localObject2).add(i, localObject1);
              i++;
              break;
            }
          }
          paramBundle.putStringArrayList(paramString, (ArrayList)localObject2);
        }
        else if (((String)localObject2).equals("enum"))
        {
          try
          {
            paramBundle.putSerializable(paramString, Enum.valueOf(Class.forName(((JSONObject)localObject1).getString("enumType")), ((JSONObject)localObject1).getString("value")));
          }
          catch (ClassNotFoundException paramString) {}catch (IllegalArgumentException paramString) {}
        }
      }
    }
  }
  
  private void serializeKey(String paramString, Bundle paramBundle, SharedPreferences.Editor paramEditor)
    throws JSONException
  {
    int j = 0;
    int k = 0;
    int m = 0;
    int n = 0;
    int i1 = 0;
    int i2 = 0;
    int i3 = 0;
    int i = 0;
    Object localObject2 = paramBundle.get(paramString);
    if (localObject2 == null) {}
    label983:
    for (;;)
    {
      return;
      paramBundle = null;
      Object localObject1 = null;
      JSONObject localJSONObject = new JSONObject();
      if ((localObject2 instanceof Byte))
      {
        paramBundle = "byte";
        localJSONObject.put("value", ((Byte)localObject2).intValue());
      }
      for (;;)
      {
        if (paramBundle == null) {
          break label983;
        }
        localJSONObject.put("valueType", paramBundle);
        if (localObject1 != null) {
          localJSONObject.putOpt("value", localObject1);
        }
        paramEditor.putString(paramString, localJSONObject.toString());
        break;
        if ((localObject2 instanceof Short))
        {
          paramBundle = "short";
          localJSONObject.put("value", ((Short)localObject2).intValue());
        }
        else if ((localObject2 instanceof Integer))
        {
          paramBundle = "int";
          localJSONObject.put("value", ((Integer)localObject2).intValue());
        }
        else if ((localObject2 instanceof Long))
        {
          paramBundle = "long";
          localJSONObject.put("value", ((Long)localObject2).longValue());
        }
        else if ((localObject2 instanceof Float))
        {
          paramBundle = "float";
          localJSONObject.put("value", ((Float)localObject2).doubleValue());
        }
        else if ((localObject2 instanceof Double))
        {
          paramBundle = "double";
          localJSONObject.put("value", ((Double)localObject2).doubleValue());
        }
        else if ((localObject2 instanceof Boolean))
        {
          paramBundle = "bool";
          localJSONObject.put("value", ((Boolean)localObject2).booleanValue());
        }
        else if ((localObject2 instanceof Character))
        {
          paramBundle = "char";
          localJSONObject.put("value", localObject2.toString());
        }
        else if ((localObject2 instanceof String))
        {
          paramBundle = "string";
          localJSONObject.put("value", (String)localObject2);
        }
        else if ((localObject2 instanceof Enum))
        {
          paramBundle = "enum";
          localJSONObject.put("value", localObject2.toString());
          localJSONObject.put("enumType", localObject2.getClass().getName());
        }
        else
        {
          JSONArray localJSONArray = new JSONArray();
          String str;
          if ((localObject2 instanceof byte[]))
          {
            str = "byte[]";
            localObject2 = (byte[])localObject2;
            j = localObject2.length;
            for (;;)
            {
              localObject1 = localJSONArray;
              paramBundle = str;
              if (i >= j) {
                break;
              }
              localJSONArray.put(localObject2[i]);
              i++;
            }
          }
          if ((localObject2 instanceof short[]))
          {
            str = "short[]";
            localObject2 = (short[])localObject2;
            k = localObject2.length;
            for (i = j;; i++)
            {
              localObject1 = localJSONArray;
              paramBundle = str;
              if (i >= k) {
                break;
              }
              localJSONArray.put(localObject2[i]);
            }
          }
          if ((localObject2 instanceof int[]))
          {
            str = "int[]";
            localObject2 = (int[])localObject2;
            j = localObject2.length;
            for (i = k;; i++)
            {
              localObject1 = localJSONArray;
              paramBundle = str;
              if (i >= j) {
                break;
              }
              localJSONArray.put(localObject2[i]);
            }
          }
          if ((localObject2 instanceof long[]))
          {
            str = "long[]";
            localObject2 = (long[])localObject2;
            j = localObject2.length;
            for (i = m;; i++)
            {
              localObject1 = localJSONArray;
              paramBundle = str;
              if (i >= j) {
                break;
              }
              localJSONArray.put(localObject2[i]);
            }
          }
          if ((localObject2 instanceof float[]))
          {
            str = "float[]";
            localObject2 = (float[])localObject2;
            j = localObject2.length;
            for (i = n;; i++)
            {
              localObject1 = localJSONArray;
              paramBundle = str;
              if (i >= j) {
                break;
              }
              localJSONArray.put(localObject2[i]);
            }
          }
          if ((localObject2 instanceof double[]))
          {
            str = "double[]";
            localObject2 = (double[])localObject2;
            j = localObject2.length;
            for (i = i1;; i++)
            {
              localObject1 = localJSONArray;
              paramBundle = str;
              if (i >= j) {
                break;
              }
              localJSONArray.put(localObject2[i]);
            }
          }
          if ((localObject2 instanceof boolean[]))
          {
            str = "bool[]";
            localObject2 = (boolean[])localObject2;
            j = localObject2.length;
            for (i = i2;; i++)
            {
              localObject1 = localJSONArray;
              paramBundle = str;
              if (i >= j) {
                break;
              }
              localJSONArray.put(localObject2[i]);
            }
          }
          if ((localObject2 instanceof char[]))
          {
            str = "char[]";
            localObject2 = (char[])localObject2;
            j = localObject2.length;
            for (i = i3;; i++)
            {
              localObject1 = localJSONArray;
              paramBundle = str;
              if (i >= j) {
                break;
              }
              localJSONArray.put(String.valueOf(localObject2[i]));
            }
          }
          if ((localObject2 instanceof List))
          {
            str = "stringList";
            localObject2 = ((List)localObject2).iterator();
            for (;;)
            {
              localObject1 = localJSONArray;
              paramBundle = str;
              if (!((Iterator)localObject2).hasNext()) {
                break;
              }
              localObject1 = (String)((Iterator)localObject2).next();
              paramBundle = (Bundle)localObject1;
              if (localObject1 == null) {
                paramBundle = JSONObject.NULL;
              }
              localJSONArray.put(paramBundle);
            }
          }
          localObject1 = null;
        }
      }
    }
  }
  
  public void clear()
  {
    this.cache.edit().clear().apply();
  }
  
  public Bundle load()
  {
    Bundle localBundle = new Bundle();
    Iterator localIterator = this.cache.getAll().keySet().iterator();
    Object localObject;
    for (;;)
    {
      localObject = localBundle;
      if (localIterator.hasNext())
      {
        localObject = (String)localIterator.next();
        try
        {
          deserializeKey((String)localObject, localBundle);
        }
        catch (JSONException localJSONException)
        {
          Logger.log(LoggingBehavior.CACHE, 5, TAG, "Error reading cached value for key: '" + (String)localObject + "' -- " + localJSONException);
          localObject = null;
        }
      }
    }
    return (Bundle)localObject;
  }
  
  public void save(Bundle paramBundle)
  {
    Validate.notNull(paramBundle, "bundle");
    SharedPreferences.Editor localEditor = this.cache.edit();
    Iterator localIterator = paramBundle.keySet().iterator();
    for (;;)
    {
      if (localIterator.hasNext())
      {
        String str = (String)localIterator.next();
        try
        {
          serializeKey(str, paramBundle, localEditor);
        }
        catch (JSONException paramBundle)
        {
          Logger.log(LoggingBehavior.CACHE, 5, TAG, "Error processing value for key: '" + str + "' -- " + paramBundle);
        }
      }
    }
    for (;;)
    {
      return;
      localEditor.apply();
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\facebook\SharedPreferencesTokenCachingStrategy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */