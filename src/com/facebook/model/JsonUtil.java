package com.facebook.model;

import android.annotation.SuppressLint;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;

class JsonUtil
{
  static void jsonObjectClear(JSONObject paramJSONObject)
  {
    paramJSONObject = paramJSONObject.keys();
    while (paramJSONObject.hasNext())
    {
      paramJSONObject.next();
      paramJSONObject.remove();
    }
  }
  
  static boolean jsonObjectContainsValue(JSONObject paramJSONObject, Object paramObject)
  {
    Iterator localIterator = paramJSONObject.keys();
    Object localObject;
    do
    {
      if (!localIterator.hasNext()) {
        break;
      }
      localObject = paramJSONObject.opt((String)localIterator.next());
    } while ((localObject == null) || (!localObject.equals(paramObject)));
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  static Set<Map.Entry<String, Object>> jsonObjectEntrySet(JSONObject paramJSONObject)
  {
    HashSet localHashSet = new HashSet();
    Iterator localIterator = paramJSONObject.keys();
    while (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      localHashSet.add(new JSONObjectEntry(str, paramJSONObject.opt(str)));
    }
    return localHashSet;
  }
  
  static Set<String> jsonObjectKeySet(JSONObject paramJSONObject)
  {
    HashSet localHashSet = new HashSet();
    paramJSONObject = paramJSONObject.keys();
    while (paramJSONObject.hasNext()) {
      localHashSet.add(paramJSONObject.next());
    }
    return localHashSet;
  }
  
  static void jsonObjectPutAll(JSONObject paramJSONObject, Map<String, Object> paramMap)
  {
    paramMap = paramMap.entrySet().iterator();
    while (paramMap.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)paramMap.next();
      try
      {
        paramJSONObject.putOpt((String)localEntry.getKey(), localEntry.getValue());
      }
      catch (JSONException paramJSONObject)
      {
        throw new IllegalArgumentException(paramJSONObject);
      }
    }
  }
  
  static Collection<Object> jsonObjectValues(JSONObject paramJSONObject)
  {
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = paramJSONObject.keys();
    while (localIterator.hasNext()) {
      localArrayList.add(paramJSONObject.opt((String)localIterator.next()));
    }
    return localArrayList;
  }
  
  private static final class JSONObjectEntry
    implements Map.Entry<String, Object>
  {
    private final String key;
    private final Object value;
    
    JSONObjectEntry(String paramString, Object paramObject)
    {
      this.key = paramString;
      this.value = paramObject;
    }
    
    @SuppressLint({"FieldGetter"})
    public String getKey()
    {
      return this.key;
    }
    
    public Object getValue()
    {
      return this.value;
    }
    
    public Object setValue(Object paramObject)
    {
      throw new UnsupportedOperationException("JSONObjectEntry is immutable");
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\facebook\model\JsonUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */