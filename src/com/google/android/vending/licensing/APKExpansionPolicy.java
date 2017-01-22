package com.google.android.vending.licensing;

import android.content.Context;
import android.util.Log;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;

public class APKExpansionPolicy
  implements Policy
{
  private static final String DEFAULT_MAX_RETRIES = "0";
  private static final String DEFAULT_RETRY_COUNT = "0";
  private static final String DEFAULT_RETRY_UNTIL = "0";
  private static final String DEFAULT_VALIDITY_TIMESTAMP = "0";
  public static final int MAIN_FILE_URL_INDEX = 0;
  private static final long MILLIS_PER_MINUTE = 60000L;
  public static final int PATCH_FILE_URL_INDEX = 1;
  private static final String PREFS_FILE = "com.android.vending.licensing.APKExpansionPolicy";
  private static final String PREF_LAST_RESPONSE = "lastResponse";
  private static final String PREF_MAX_RETRIES = "maxRetries";
  private static final String PREF_RETRY_COUNT = "retryCount";
  private static final String PREF_RETRY_UNTIL = "retryUntil";
  private static final String PREF_VALIDITY_TIMESTAMP = "validityTimestamp";
  private static final String TAG = "APKExpansionPolicy";
  private Vector<String> mExpansionFileNames = new Vector();
  private Vector<Long> mExpansionFileSizes = new Vector();
  private Vector<String> mExpansionURLs = new Vector();
  private int mLastResponse;
  private long mLastResponseTime = 0L;
  private long mMaxRetries;
  private PreferenceObfuscator mPreferences;
  private long mRetryCount;
  private long mRetryUntil;
  private long mValidityTimestamp;
  
  public APKExpansionPolicy(Context paramContext, Obfuscator paramObfuscator)
  {
    this.mPreferences = new PreferenceObfuscator(paramContext.getSharedPreferences("com.android.vending.licensing.APKExpansionPolicy", 0), paramObfuscator);
    this.mLastResponse = Integer.parseInt(this.mPreferences.getString("lastResponse", Integer.toString(291)));
    this.mValidityTimestamp = Long.parseLong(this.mPreferences.getString("validityTimestamp", "0"));
    this.mRetryUntil = Long.parseLong(this.mPreferences.getString("retryUntil", "0"));
    this.mMaxRetries = Long.parseLong(this.mPreferences.getString("maxRetries", "0"));
    this.mRetryCount = Long.parseLong(this.mPreferences.getString("retryCount", "0"));
  }
  
  private Map<String, String> decodeExtras(String paramString)
  {
    localHashMap = new HashMap();
    try
    {
      Object localObject1 = new java/net/URI;
      Object localObject2 = new java/lang/StringBuilder;
      ((StringBuilder)localObject2).<init>();
      ((URI)localObject1).<init>("?" + paramString);
      localObject2 = URLEncodedUtils.parse((URI)localObject1, "UTF-8").iterator();
      while (((Iterator)localObject2).hasNext())
      {
        localObject1 = (NameValuePair)((Iterator)localObject2).next();
        paramString = ((NameValuePair)localObject1).getName();
        int i = 0;
        while (localHashMap.containsKey(paramString))
        {
          paramString = new java/lang/StringBuilder;
          paramString.<init>();
          paramString = paramString.append(((NameValuePair)localObject1).getName());
          i++;
          paramString = i;
        }
        localHashMap.put(paramString, ((NameValuePair)localObject1).getValue());
      }
      return localHashMap;
    }
    catch (URISyntaxException paramString)
    {
      Log.w("APKExpansionPolicy", "Invalid syntax error while decoding extras data from server.");
    }
  }
  
  private void setLastResponse(int paramInt)
  {
    this.mLastResponseTime = System.currentTimeMillis();
    this.mLastResponse = paramInt;
    this.mPreferences.putString("lastResponse", Integer.toString(paramInt));
  }
  
  private void setMaxRetries(String paramString)
  {
    try
    {
      long l = Long.parseLong(paramString);
      Long localLong = Long.valueOf(l);
      str = paramString;
      paramString = localLong;
    }
    catch (NumberFormatException paramString)
    {
      for (;;)
      {
        Log.w("APKExpansionPolicy", "Licence retry count (GR) missing, grace period disabled");
        String str = "0";
        paramString = Long.valueOf(0L);
      }
    }
    this.mMaxRetries = paramString.longValue();
    this.mPreferences.putString("maxRetries", str);
  }
  
  private void setRetryCount(long paramLong)
  {
    this.mRetryCount = paramLong;
    this.mPreferences.putString("retryCount", Long.toString(paramLong));
  }
  
  private void setRetryUntil(String paramString)
  {
    try
    {
      long l = Long.parseLong(paramString);
      localLong = Long.valueOf(l);
    }
    catch (NumberFormatException paramString)
    {
      for (;;)
      {
        Log.w("APKExpansionPolicy", "License retry timestamp (GT) missing, grace period disabled");
        paramString = "0";
        Long localLong = Long.valueOf(0L);
      }
    }
    this.mRetryUntil = localLong.longValue();
    this.mPreferences.putString("retryUntil", paramString);
  }
  
  private void setValidityTimestamp(String paramString)
  {
    try
    {
      long l = Long.parseLong(paramString);
      localLong = Long.valueOf(l);
    }
    catch (NumberFormatException paramString)
    {
      for (;;)
      {
        Log.w("APKExpansionPolicy", "License validity timestamp (VT) missing, caching for a minute");
        Long localLong = Long.valueOf(System.currentTimeMillis() + 60000L);
        paramString = Long.toString(localLong.longValue());
      }
    }
    this.mValidityTimestamp = localLong.longValue();
    this.mPreferences.putString("validityTimestamp", paramString);
  }
  
  public boolean allowAccess()
  {
    boolean bool2 = false;
    long l = System.currentTimeMillis();
    boolean bool1;
    if (this.mLastResponse == 256)
    {
      bool1 = bool2;
      if (l <= this.mValidityTimestamp) {
        bool1 = true;
      }
    }
    for (;;)
    {
      return bool1;
      bool1 = bool2;
      if (this.mLastResponse == 291)
      {
        bool1 = bool2;
        if (l < this.mLastResponseTime + 60000L) {
          if (l > this.mRetryUntil)
          {
            bool1 = bool2;
            if (this.mRetryCount > this.mMaxRetries) {}
          }
          else
          {
            bool1 = true;
          }
        }
      }
    }
  }
  
  public String getExpansionFileName(int paramInt)
  {
    if (paramInt < this.mExpansionFileNames.size()) {}
    for (String str = (String)this.mExpansionFileNames.elementAt(paramInt);; str = null) {
      return str;
    }
  }
  
  public long getExpansionFileSize(int paramInt)
  {
    if (paramInt < this.mExpansionFileSizes.size()) {}
    for (long l = ((Long)this.mExpansionFileSizes.elementAt(paramInt)).longValue();; l = -1L) {
      return l;
    }
  }
  
  public String getExpansionURL(int paramInt)
  {
    if (paramInt < this.mExpansionURLs.size()) {}
    for (String str = (String)this.mExpansionURLs.elementAt(paramInt);; str = null) {
      return str;
    }
  }
  
  public int getExpansionURLCount()
  {
    return this.mExpansionURLs.size();
  }
  
  public long getMaxRetries()
  {
    return this.mMaxRetries;
  }
  
  public long getRetryCount()
  {
    return this.mRetryCount;
  }
  
  public long getRetryUntil()
  {
    return this.mRetryUntil;
  }
  
  public long getValidityTimestamp()
  {
    return this.mValidityTimestamp;
  }
  
  public void processServerResponse(int paramInt, ResponseData paramResponseData)
  {
    Map localMap;
    if (paramInt != 291)
    {
      setRetryCount(0L);
      if (paramInt == 256)
      {
        localMap = decodeExtras(paramResponseData.extra);
        this.mLastResponse = paramInt;
        setValidityTimestamp(Long.toString(System.currentTimeMillis() + 60000L));
        paramResponseData = localMap.keySet().iterator();
      }
    }
    else
    {
      for (;;)
      {
        if (!paramResponseData.hasNext()) {
          break label342;
        }
        String str = (String)paramResponseData.next();
        if (str.equals("VT"))
        {
          setValidityTimestamp((String)localMap.get(str));
          continue;
          setRetryCount(this.mRetryCount + 1L);
          break;
        }
        if (str.equals("GT")) {
          setRetryUntil((String)localMap.get(str));
        } else if (str.equals("GR")) {
          setMaxRetries((String)localMap.get(str));
        } else if (str.startsWith("FILE_URL")) {
          setExpansionURL(Integer.parseInt(str.substring("FILE_URL".length())) - 1, (String)localMap.get(str));
        } else if (str.startsWith("FILE_NAME")) {
          setExpansionFileName(Integer.parseInt(str.substring("FILE_NAME".length())) - 1, (String)localMap.get(str));
        } else if (str.startsWith("FILE_SIZE")) {
          setExpansionFileSize(Integer.parseInt(str.substring("FILE_SIZE".length())) - 1, Long.parseLong((String)localMap.get(str)));
        }
      }
    }
    if (paramInt == 561)
    {
      setValidityTimestamp("0");
      setRetryUntil("0");
      setMaxRetries("0");
    }
    label342:
    setLastResponse(paramInt);
    this.mPreferences.commit();
  }
  
  public void resetPolicy()
  {
    this.mPreferences.putString("lastResponse", Integer.toString(291));
    setRetryUntil("0");
    setMaxRetries("0");
    setRetryCount(Long.parseLong("0"));
    setValidityTimestamp("0");
    this.mPreferences.commit();
  }
  
  public void setExpansionFileName(int paramInt, String paramString)
  {
    if (paramInt >= this.mExpansionFileNames.size()) {
      this.mExpansionFileNames.setSize(paramInt + 1);
    }
    this.mExpansionFileNames.set(paramInt, paramString);
  }
  
  public void setExpansionFileSize(int paramInt, long paramLong)
  {
    if (paramInt >= this.mExpansionFileSizes.size()) {
      this.mExpansionFileSizes.setSize(paramInt + 1);
    }
    this.mExpansionFileSizes.set(paramInt, Long.valueOf(paramLong));
  }
  
  public void setExpansionURL(int paramInt, String paramString)
  {
    if (paramInt >= this.mExpansionURLs.size()) {
      this.mExpansionURLs.setSize(paramInt + 1);
    }
    this.mExpansionURLs.set(paramInt, paramString);
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\vending\licensing\APKExpansionPolicy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */