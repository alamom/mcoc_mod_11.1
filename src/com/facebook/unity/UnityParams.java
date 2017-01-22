package com.facebook.unity;

import android.os.Bundle;
import android.util.Log;
import java.io.Serializable;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class UnityParams
{
  JSONObject json;
  
  public UnityParams(String paramString)
    throws JSONException
  {
    this.json = new JSONObject(paramString);
  }
  
  public UnityParams(Map<String, Serializable> paramMap)
  {
    this.json = new JSONObject(paramMap);
  }
  
  public UnityParams(JSONObject paramJSONObject)
  {
    this.json = paramJSONObject;
  }
  
  public static UnityParams parse(String paramString)
  {
    return parse(paramString, "couldn't parse params: " + paramString);
  }
  
  public static UnityParams parse(String paramString1, String paramString2)
  {
    try
    {
      UnityParams localUnityParams = new com/facebook/unity/UnityParams;
      localUnityParams.<init>(paramString1);
      paramString1 = localUnityParams;
    }
    catch (JSONException paramString1)
    {
      for (;;)
      {
        Log.e("FBUnitySDK", paramString2);
        paramString1 = null;
      }
    }
    return paramString1;
  }
  
  public double getDouble(String paramString)
  {
    try
    {
      d = this.json.getDouble(paramString);
      return d;
    }
    catch (JSONException localJSONException)
    {
      for (;;)
      {
        Log.e("FBUnitySDK", "cannot get double " + paramString + " from " + toString());
        double d = 0.0D;
      }
    }
  }
  
  public UnityParams getParamsObject(String paramString)
  {
    try
    {
      UnityParams localUnityParams = new com/facebook/unity/UnityParams;
      localUnityParams.<init>(this.json.getJSONObject(paramString));
      paramString = localUnityParams;
    }
    catch (JSONException localJSONException)
    {
      for (;;)
      {
        Log.e("FBUnitySDK", "cannot get object " + paramString + " from " + toString());
        paramString = null;
      }
    }
    return paramString;
  }
  
  public String getString(String paramString)
  {
    try
    {
      String str = this.json.getString(paramString);
      paramString = str;
    }
    catch (JSONException localJSONException)
    {
      for (;;)
      {
        Log.e("FBUnitySDK", "cannot get string " + paramString + " from " + toString());
        paramString = "";
      }
    }
    return paramString;
  }
  
  public Bundle getStringParams()
  {
    Bundle localBundle = new Bundle();
    Iterator localIterator = this.json.keys();
    while (localIterator.hasNext())
    {
      String str1 = (String)localIterator.next();
      try
      {
        String str2 = this.json.getString(str1);
        if (str2 != null) {
          localBundle.putString(str1, str2);
        }
      }
      catch (JSONException localJSONException) {}
    }
    return localBundle;
  }
  
  public boolean has(String paramString)
  {
    if ((this.json.has(paramString)) && (!this.json.isNull(paramString))) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public Boolean hasString(String paramString)
  {
    if ((has(paramString)) && (getString(paramString) != "")) {}
    for (boolean bool = true;; bool = false) {
      return Boolean.valueOf(bool);
    }
  }
  
  public void put(String paramString, Object paramObject)
  {
    try
    {
      this.json.put(paramString, paramObject);
      return;
    }
    catch (JSONException paramObject)
    {
      for (;;)
      {
        Log.e("FBUnitySDK", "couldn't add key " + paramString + " to " + toString());
      }
    }
  }
  
  public String toString()
  {
    return this.json.toString();
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\facebook\unity\UnityParams.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */