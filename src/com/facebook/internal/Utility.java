package com.facebook.internal;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.net.Uri.Builder;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.Settings.Secure;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import com.facebook.FacebookException;
import com.facebook.Request;
import com.facebook.Response;
import com.facebook.Settings;
import com.facebook.model.GraphObject;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

public final class Utility
{
  private static final String APPLICATION_FIELDS = "fields";
  private static final String APP_SETTINGS_PREFS_KEY_FORMAT = "com.facebook.internal.APP_SETTINGS.%s";
  private static final String APP_SETTINGS_PREFS_STORE = "com.facebook.internal.preferences.APP_SETTINGS";
  private static final String APP_SETTING_DIALOG_CONFIGS = "android_dialog_configs";
  private static final String[] APP_SETTING_FIELDS = { "supports_attribution", "supports_implicit_sdk_logging", "gdpv4_nux_content", "gdpv4_nux_enabled", "android_dialog_configs" };
  private static final String APP_SETTING_NUX_CONTENT = "gdpv4_nux_content";
  private static final String APP_SETTING_NUX_ENABLED = "gdpv4_nux_enabled";
  private static final String APP_SETTING_SUPPORTS_ATTRIBUTION = "supports_attribution";
  private static final String APP_SETTING_SUPPORTS_IMPLICIT_SDK_LOGGING = "supports_implicit_sdk_logging";
  public static final int DEFAULT_STREAM_BUFFER_SIZE = 8192;
  private static final String DIALOG_CONFIG_DIALOG_NAME_FEATURE_NAME_SEPARATOR = "\\|";
  private static final String DIALOG_CONFIG_NAME_KEY = "name";
  private static final String DIALOG_CONFIG_URL_KEY = "url";
  private static final String DIALOG_CONFIG_VERSIONS_KEY = "versions";
  private static final String EXTRA_APP_EVENTS_INFO_FORMAT_VERSION = "a1";
  private static final String HASH_ALGORITHM_MD5 = "MD5";
  private static final String HASH_ALGORITHM_SHA1 = "SHA-1";
  static final String LOG_TAG = "FacebookSDK";
  private static final String URL_SCHEME = "https";
  private static final String UTF8 = "UTF-8";
  private static Map<String, FetchedAppSettings> fetchedAppSettings = new ConcurrentHashMap();
  private static AsyncTask<Void, Void, GraphObject> initialAppSettingsLoadTask;
  
  public static <T> boolean areObjectsEqual(T paramT1, T paramT2)
  {
    boolean bool;
    if (paramT1 == null) {
      if (paramT2 == null) {
        bool = true;
      }
    }
    for (;;)
    {
      return bool;
      bool = false;
      continue;
      bool = paramT1.equals(paramT2);
    }
  }
  
  public static <T> ArrayList<T> arrayList(T... paramVarArgs)
  {
    ArrayList localArrayList = new ArrayList(paramVarArgs.length);
    int j = paramVarArgs.length;
    for (int i = 0; i < j; i++) {
      localArrayList.add(paramVarArgs[i]);
    }
    return localArrayList;
  }
  
  public static <T> List<T> asListNoNulls(T... paramVarArgs)
  {
    ArrayList localArrayList = new ArrayList();
    int j = paramVarArgs.length;
    for (int i = 0; i < j; i++)
    {
      T ? = paramVarArgs[i];
      if (? != null) {
        localArrayList.add(?);
      }
    }
    return localArrayList;
  }
  
  public static Uri buildUri(String paramString1, String paramString2, Bundle paramBundle)
  {
    Uri.Builder localBuilder = new Uri.Builder();
    localBuilder.scheme("https");
    localBuilder.authority(paramString1);
    localBuilder.path(paramString2);
    Iterator localIterator = paramBundle.keySet().iterator();
    while (localIterator.hasNext())
    {
      paramString2 = (String)localIterator.next();
      paramString1 = paramBundle.get(paramString2);
      if ((paramString1 instanceof String)) {
        localBuilder.appendQueryParameter(paramString2, (String)paramString1);
      }
    }
    return localBuilder.build();
  }
  
  public static void clearCaches(Context paramContext)
  {
    ImageDownloader.clearCache(paramContext);
  }
  
  private static void clearCookiesForDomain(Context paramContext, String paramString)
  {
    CookieSyncManager.createInstance(paramContext).sync();
    paramContext = CookieManager.getInstance();
    Object localObject = paramContext.getCookie(paramString);
    if (localObject == null) {}
    for (;;)
    {
      return;
      String[] arrayOfString = ((String)localObject).split(";");
      int j = arrayOfString.length;
      for (int i = 0; i < j; i++)
      {
        localObject = arrayOfString[i].split("=");
        if (localObject.length > 0) {
          paramContext.setCookie(paramString, localObject[0].trim() + "=;expires=Sat, 1 Jan 2000 00:00:01 UTC;");
        }
      }
      paramContext.removeExpiredCookie();
    }
  }
  
  public static void clearFacebookCookies(Context paramContext)
  {
    clearCookiesForDomain(paramContext, "facebook.com");
    clearCookiesForDomain(paramContext, ".facebook.com");
    clearCookiesForDomain(paramContext, "https://facebook.com");
    clearCookiesForDomain(paramContext, "https://.facebook.com");
  }
  
  public static void closeQuietly(Closeable paramCloseable)
  {
    if (paramCloseable != null) {}
    try
    {
      paramCloseable.close();
      return;
    }
    catch (IOException paramCloseable)
    {
      for (;;) {}
    }
  }
  
  public static String coerceValueIfNullOrEmpty(String paramString1, String paramString2)
  {
    if (isNullOrEmpty(paramString1)) {}
    for (;;)
    {
      return paramString2;
      paramString2 = paramString1;
    }
  }
  
  static Map<String, Object> convertJSONObjectToHashMap(JSONObject paramJSONObject)
  {
    HashMap localHashMap = new HashMap();
    JSONArray localJSONArray = paramJSONObject.names();
    int i = 0;
    while (i < localJSONArray.length()) {
      try
      {
        String str = localJSONArray.getString(i);
        Object localObject2 = paramJSONObject.get(str);
        Object localObject1 = localObject2;
        if ((localObject2 instanceof JSONObject)) {
          localObject1 = convertJSONObjectToHashMap((JSONObject)localObject2);
        }
        localHashMap.put(str, localObject1);
        i++;
      }
      catch (JSONException localJSONException)
      {
        for (;;) {}
      }
    }
    return localHashMap;
  }
  
  public static void deleteDirectory(File paramFile)
  {
    if (!paramFile.exists()) {}
    for (;;)
    {
      return;
      if (paramFile.isDirectory())
      {
        File[] arrayOfFile = paramFile.listFiles();
        int j = arrayOfFile.length;
        for (int i = 0; i < j; i++) {
          deleteDirectory(arrayOfFile[i]);
        }
      }
      paramFile.delete();
    }
  }
  
  public static void disconnectQuietly(URLConnection paramURLConnection)
  {
    if ((paramURLConnection instanceof HttpURLConnection)) {
      ((HttpURLConnection)paramURLConnection).disconnect();
    }
  }
  
  public static String getActivityName(Context paramContext)
  {
    if (paramContext == null) {
      paramContext = "null";
    }
    for (;;)
    {
      return paramContext;
      if (paramContext == paramContext.getApplicationContext()) {
        paramContext = "unknown";
      } else {
        paramContext = paramContext.getClass().getSimpleName();
      }
    }
  }
  
  private static GraphObject getAppSettingsQueryResponse(String paramString)
  {
    Bundle localBundle = new Bundle();
    localBundle.putString("fields", TextUtils.join(",", APP_SETTING_FIELDS));
    paramString = Request.newGraphPathRequest(null, paramString, null);
    paramString.setSkipClientToken(true);
    paramString.setParameters(localBundle);
    return paramString.executeAndWait().getGraphObject();
  }
  
  public static DialogFeatureConfig getDialogFeatureConfig(String paramString1, String paramString2, String paramString3)
  {
    Object localObject2 = null;
    Object localObject1 = localObject2;
    if (!isNullOrEmpty(paramString2))
    {
      if (!isNullOrEmpty(paramString3)) {
        break label25;
      }
      localObject1 = localObject2;
    }
    for (;;)
    {
      return (DialogFeatureConfig)localObject1;
      label25:
      paramString1 = (FetchedAppSettings)fetchedAppSettings.get(paramString1);
      localObject1 = localObject2;
      if (paramString1 != null)
      {
        paramString1 = (Map)paramString1.getDialogConfigurations().get(paramString2);
        localObject1 = localObject2;
        if (paramString1 != null) {
          localObject1 = (DialogFeatureConfig)paramString1.get(paramString3);
        }
      }
    }
  }
  
  public static String getHashedDeviceAndAppID(Context paramContext, String paramString)
  {
    paramContext = Settings.Secure.getString(paramContext.getContentResolver(), "android_id");
    if (paramContext == null) {}
    for (paramContext = null;; paramContext = sha1hash(paramContext + paramString)) {
      return paramContext;
    }
  }
  
  public static String getMetadataApplicationId(Context paramContext)
  {
    Validate.notNull(paramContext, "context");
    Settings.loadDefaultsFromMetadata(paramContext);
    return Settings.getApplicationId();
  }
  
  public static Method getMethodQuietly(Class<?> paramClass, String paramString, Class<?>... paramVarArgs)
  {
    try
    {
      paramClass = paramClass.getMethod(paramString, paramVarArgs);
      return paramClass;
    }
    catch (NoSuchMethodException paramClass)
    {
      for (;;)
      {
        paramClass = null;
      }
    }
  }
  
  public static Method getMethodQuietly(String paramString1, String paramString2, Class<?>... paramVarArgs)
  {
    try
    {
      paramString1 = getMethodQuietly(Class.forName(paramString1), paramString2, paramVarArgs);
      return paramString1;
    }
    catch (ClassNotFoundException paramString1)
    {
      for (;;)
      {
        paramString1 = null;
      }
    }
  }
  
  public static Object getStringPropertyAsJSON(JSONObject paramJSONObject, String paramString1, String paramString2)
    throws JSONException
  {
    paramString1 = paramJSONObject.opt(paramString1);
    paramJSONObject = paramString1;
    if (paramString1 != null)
    {
      paramJSONObject = paramString1;
      if ((paramString1 instanceof String)) {
        paramJSONObject = new JSONTokener((String)paramString1).nextValue();
      }
    }
    if ((paramJSONObject != null) && (!(paramJSONObject instanceof JSONObject)) && (!(paramJSONObject instanceof JSONArray))) {
      if (paramString2 != null)
      {
        paramString1 = new JSONObject();
        paramString1.putOpt(paramString2, paramJSONObject);
        paramJSONObject = paramString1;
      }
    }
    for (;;)
    {
      return paramJSONObject;
      throw new FacebookException("Got an unexpected non-JSON object.");
    }
  }
  
  private static String hashBytes(MessageDigest paramMessageDigest, byte[] paramArrayOfByte)
  {
    paramMessageDigest.update(paramArrayOfByte);
    paramMessageDigest = paramMessageDigest.digest();
    paramArrayOfByte = new StringBuilder();
    int j = paramMessageDigest.length;
    for (int i = 0; i < j; i++)
    {
      int k = paramMessageDigest[i];
      paramArrayOfByte.append(Integer.toHexString(k >> 4 & 0xF));
      paramArrayOfByte.append(Integer.toHexString(k >> 0 & 0xF));
    }
    return paramArrayOfByte.toString();
  }
  
  private static String hashWithAlgorithm(String paramString1, String paramString2)
  {
    return hashWithAlgorithm(paramString1, paramString2.getBytes());
  }
  
  private static String hashWithAlgorithm(String paramString, byte[] paramArrayOfByte)
  {
    try
    {
      paramString = MessageDigest.getInstance(paramString);
      paramString = hashBytes(paramString, paramArrayOfByte);
    }
    catch (NoSuchAlgorithmException paramString)
    {
      for (;;)
      {
        paramString = null;
      }
    }
    return paramString;
  }
  
  public static int[] intersectRanges(int[] paramArrayOfInt1, int[] paramArrayOfInt2)
  {
    if (paramArrayOfInt1 == null) {}
    for (;;)
    {
      return paramArrayOfInt2;
      if (paramArrayOfInt2 == null)
      {
        paramArrayOfInt2 = paramArrayOfInt1;
      }
      else
      {
        int[] arrayOfInt = new int[paramArrayOfInt1.length + paramArrayOfInt2.length];
        int m = 0;
        int i2 = 0;
        int i1 = 0;
        int i = m;
        if (i2 < paramArrayOfInt1.length)
        {
          i = m;
          if (i1 < paramArrayOfInt2.length)
          {
            int n = Integer.MIN_VALUE;
            int i3 = Integer.MAX_VALUE;
            int k = paramArrayOfInt1[i2];
            i = Integer.MAX_VALUE;
            int i4 = paramArrayOfInt2[i1];
            int j = Integer.MAX_VALUE;
            if (i2 < paramArrayOfInt1.length - 1) {
              i = paramArrayOfInt1[(i2 + 1)];
            }
            if (i1 < paramArrayOfInt2.length - 1) {
              j = paramArrayOfInt2[(i1 + 1)];
            }
            if (k < i4) {
              if (i > i4)
              {
                n = i4;
                if (i > j)
                {
                  i = j;
                  k = i1 + 2;
                  j = i2;
                }
              }
            }
            for (;;)
            {
              i2 = j;
              i1 = k;
              if (n == Integer.MIN_VALUE) {
                break;
              }
              i1 = m + 1;
              arrayOfInt[m] = n;
              if (i == Integer.MAX_VALUE) {
                break label288;
              }
              m = i1 + 1;
              arrayOfInt[i1] = i;
              i2 = j;
              i1 = k;
              break;
              j = i2 + 2;
              k = i1;
              continue;
              j = i2 + 2;
              k = i1;
              i = i3;
              continue;
              if (j > k)
              {
                n = k;
                if (j > i)
                {
                  j = i2 + 2;
                  k = i1;
                }
                else
                {
                  i = j;
                  k = i1 + 2;
                  j = i2;
                }
              }
              else
              {
                k = i1 + 2;
                j = i2;
                i = i3;
              }
            }
            label288:
            i = i1;
          }
        }
        paramArrayOfInt2 = Arrays.copyOf(arrayOfInt, i);
      }
    }
  }
  
  public static Object invokeMethodQuietly(Object paramObject, Method paramMethod, Object... paramVarArgs)
  {
    Object localObject = null;
    try
    {
      paramObject = paramMethod.invoke(paramObject, paramVarArgs);
      return paramObject;
    }
    catch (IllegalAccessException paramObject)
    {
      for (;;)
      {
        paramObject = localObject;
      }
    }
    catch (InvocationTargetException paramObject)
    {
      for (;;)
      {
        paramObject = localObject;
      }
    }
  }
  
  public static boolean isNullOrEmpty(String paramString)
  {
    if ((paramString == null) || (paramString.length() == 0)) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public static <T> boolean isNullOrEmpty(Collection<T> paramCollection)
  {
    if ((paramCollection == null) || (paramCollection.size() == 0)) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public static <T> boolean isSubset(Collection<T> paramCollection1, Collection<T> paramCollection2)
  {
    boolean bool = false;
    if ((paramCollection2 == null) || (paramCollection2.size() == 0)) {
      if ((paramCollection1 == null) || (paramCollection1.size() == 0)) {
        bool = true;
      }
    }
    for (;;)
    {
      return bool;
      paramCollection2 = new HashSet(paramCollection2);
      paramCollection1 = paramCollection1.iterator();
      for (;;)
      {
        if (paramCollection1.hasNext()) {
          if (!paramCollection2.contains(paramCollection1.next())) {
            break;
          }
        }
      }
      bool = true;
    }
  }
  
  public static void loadAppSettingsAsync(final Context paramContext, String paramString)
  {
    if ((isNullOrEmpty(paramString)) || (fetchedAppSettings.containsKey(paramString)) || (initialAppSettingsLoadTask != null)) {}
    for (;;)
    {
      return;
      final Object localObject = String.format("com.facebook.internal.APP_SETTINGS.%s", new Object[] { paramString });
      initialAppSettingsLoadTask = new AsyncTask()
      {
        protected GraphObject doInBackground(Void... paramAnonymousVarArgs)
        {
          return Utility.getAppSettingsQueryResponse(this.val$applicationId);
        }
        
        protected void onPostExecute(GraphObject paramAnonymousGraphObject)
        {
          if (paramAnonymousGraphObject != null)
          {
            paramAnonymousGraphObject = paramAnonymousGraphObject.getInnerJSONObject();
            Utility.parseAppSettingsFromJSON(this.val$applicationId, paramAnonymousGraphObject);
            paramContext.getSharedPreferences("com.facebook.internal.preferences.APP_SETTINGS", 0).edit().putString(localObject, paramAnonymousGraphObject.toString()).apply();
          }
          Utility.access$202(null);
        }
      };
      initialAppSettingsLoadTask.execute((Void[])null);
      String str = paramContext.getSharedPreferences("com.facebook.internal.preferences.APP_SETTINGS", 0).getString((String)localObject, null);
      if (!isNullOrEmpty(str)) {
        paramContext = null;
      }
      try
      {
        localObject = new org/json/JSONObject;
        ((JSONObject)localObject).<init>(str);
        paramContext = (Context)localObject;
      }
      catch (JSONException localJSONException)
      {
        for (;;)
        {
          logd("FacebookSDK", localJSONException);
        }
      }
      if (paramContext != null) {
        parseAppSettingsFromJSON(paramString, paramContext);
      }
    }
  }
  
  public static void logd(String paramString, Exception paramException)
  {
    if ((Settings.isDebugEnabled()) && (paramString != null) && (paramException != null)) {
      Log.d(paramString, paramException.getClass().getSimpleName() + ": " + paramException.getMessage());
    }
  }
  
  public static void logd(String paramString1, String paramString2)
  {
    if ((Settings.isDebugEnabled()) && (paramString1 != null) && (paramString2 != null)) {
      Log.d(paramString1, paramString2);
    }
  }
  
  public static void logd(String paramString1, String paramString2, Throwable paramThrowable)
  {
    if ((Settings.isDebugEnabled()) && (!isNullOrEmpty(paramString1))) {
      Log.d(paramString1, paramString2, paramThrowable);
    }
  }
  
  static String md5hash(String paramString)
  {
    return hashWithAlgorithm("MD5", paramString);
  }
  
  private static FetchedAppSettings parseAppSettingsFromJSON(String paramString, JSONObject paramJSONObject)
  {
    paramJSONObject = new FetchedAppSettings(paramJSONObject.optBoolean("supports_attribution", false), paramJSONObject.optBoolean("supports_implicit_sdk_logging", false), paramJSONObject.optString("gdpv4_nux_content", ""), paramJSONObject.optBoolean("gdpv4_nux_enabled", false), parseDialogConfigurations(paramJSONObject.optJSONObject("android_dialog_configs")), null);
    fetchedAppSettings.put(paramString, paramJSONObject);
    return paramJSONObject;
  }
  
  private static Map<String, Map<String, DialogFeatureConfig>> parseDialogConfigurations(JSONObject paramJSONObject)
  {
    HashMap localHashMap = new HashMap();
    if (paramJSONObject != null)
    {
      JSONArray localJSONArray = paramJSONObject.optJSONArray("data");
      if (localJSONArray != null)
      {
        int i = 0;
        if (i < localJSONArray.length())
        {
          DialogFeatureConfig localDialogFeatureConfig = DialogFeatureConfig.parseDialogConfig(localJSONArray.optJSONObject(i));
          if (localDialogFeatureConfig == null) {}
          for (;;)
          {
            i++;
            break;
            String str = localDialogFeatureConfig.getDialogName();
            Map localMap = (Map)localHashMap.get(str);
            paramJSONObject = localMap;
            if (localMap == null)
            {
              paramJSONObject = new HashMap();
              localHashMap.put(str, paramJSONObject);
            }
            paramJSONObject.put(localDialogFeatureConfig.getFeatureName(), localDialogFeatureConfig);
          }
        }
      }
    }
    return localHashMap;
  }
  
  public static Bundle parseUrlQueryString(String paramString)
  {
    int i = 0;
    Bundle localBundle = new Bundle();
    if (!isNullOrEmpty(paramString))
    {
      paramString = paramString.split("&");
      int j = paramString.length;
      for (;;)
      {
        if (i < j)
        {
          String[] arrayOfString = paramString[i].split("=");
          try
          {
            if (arrayOfString.length == 2) {
              localBundle.putString(URLDecoder.decode(arrayOfString[0], "UTF-8"), URLDecoder.decode(arrayOfString[1], "UTF-8"));
            }
            for (;;)
            {
              i++;
              break;
              if (arrayOfString.length == 1) {
                localBundle.putString(URLDecoder.decode(arrayOfString[0], "UTF-8"), "");
              }
            }
          }
          catch (UnsupportedEncodingException localUnsupportedEncodingException)
          {
            for (;;)
            {
              logd("FacebookSDK", localUnsupportedEncodingException);
            }
          }
        }
      }
    }
    return localBundle;
  }
  
  public static void putObjectInBundle(Bundle paramBundle, String paramString, Object paramObject)
  {
    if ((paramObject instanceof String)) {
      paramBundle.putString(paramString, (String)paramObject);
    }
    for (;;)
    {
      return;
      if ((paramObject instanceof Parcelable))
      {
        paramBundle.putParcelable(paramString, (Parcelable)paramObject);
      }
      else
      {
        if (!(paramObject instanceof byte[])) {
          break;
        }
        paramBundle.putByteArray(paramString, (byte[])paramObject);
      }
    }
    throw new FacebookException("attempted to add unsupported type to Bundle");
  }
  
  public static FetchedAppSettings queryAppSettings(String paramString, boolean paramBoolean)
  {
    if ((!paramBoolean) && (fetchedAppSettings.containsKey(paramString))) {
      paramString = (FetchedAppSettings)fetchedAppSettings.get(paramString);
    }
    for (;;)
    {
      return paramString;
      GraphObject localGraphObject = getAppSettingsQueryResponse(paramString);
      if (localGraphObject == null) {
        paramString = null;
      } else {
        paramString = parseAppSettingsFromJSON(paramString, localGraphObject.getInnerJSONObject());
      }
    }
  }
  
  /* Error */
  public static String readStreamToString(java.io.InputStream paramInputStream)
    throws IOException
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 4
    //   3: aconst_null
    //   4: astore_3
    //   5: new 673	java/io/BufferedInputStream
    //   8: astore_2
    //   9: aload_2
    //   10: aload_0
    //   11: invokespecial 676	java/io/BufferedInputStream:<init>	(Ljava/io/InputStream;)V
    //   14: new 678	java/io/InputStreamReader
    //   17: astore 4
    //   19: aload 4
    //   21: aload_2
    //   22: invokespecial 679	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   25: new 217	java/lang/StringBuilder
    //   28: astore_0
    //   29: aload_0
    //   30: invokespecial 218	java/lang/StringBuilder:<init>	()V
    //   33: sipush 2048
    //   36: newarray <illegal type>
    //   38: astore_3
    //   39: aload 4
    //   41: aload_3
    //   42: invokevirtual 683	java/io/InputStreamReader:read	([C)I
    //   45: istore_1
    //   46: iload_1
    //   47: iconst_m1
    //   48: if_icmpeq +36 -> 84
    //   51: aload_0
    //   52: aload_3
    //   53: iconst_0
    //   54: iload_1
    //   55: invokevirtual 686	java/lang/StringBuilder:append	([CII)Ljava/lang/StringBuilder;
    //   58: pop
    //   59: goto -20 -> 39
    //   62: astore_0
    //   63: aload 4
    //   65: astore_3
    //   66: aload_2
    //   67: astore 4
    //   69: aload_0
    //   70: astore_2
    //   71: aload 4
    //   73: astore_0
    //   74: aload_0
    //   75: invokestatic 688	com/facebook/internal/Utility:closeQuietly	(Ljava/io/Closeable;)V
    //   78: aload_3
    //   79: invokestatic 688	com/facebook/internal/Utility:closeQuietly	(Ljava/io/Closeable;)V
    //   82: aload_2
    //   83: athrow
    //   84: aload_0
    //   85: invokevirtual 231	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   88: astore_0
    //   89: aload_2
    //   90: invokestatic 688	com/facebook/internal/Utility:closeQuietly	(Ljava/io/Closeable;)V
    //   93: aload 4
    //   95: invokestatic 688	com/facebook/internal/Utility:closeQuietly	(Ljava/io/Closeable;)V
    //   98: aload_0
    //   99: areturn
    //   100: astore_2
    //   101: aload 4
    //   103: astore_0
    //   104: goto -30 -> 74
    //   107: astore 4
    //   109: aload_2
    //   110: astore_0
    //   111: aload 4
    //   113: astore_2
    //   114: goto -40 -> 74
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	117	0	paramInputStream	java.io.InputStream
    //   45	10	1	i	int
    //   8	82	2	localObject1	Object
    //   100	10	2	localObject2	Object
    //   113	1	2	localObject3	Object
    //   4	75	3	localObject4	Object
    //   1	101	4	localObject5	Object
    //   107	5	4	localObject6	Object
    // Exception table:
    //   from	to	target	type
    //   25	39	62	finally
    //   39	46	62	finally
    //   51	59	62	finally
    //   84	89	62	finally
    //   5	14	100	finally
    //   14	25	107	finally
  }
  
  public static boolean safeGetBooleanFromResponse(GraphObject paramGraphObject, String paramString)
  {
    Object localObject = Boolean.valueOf(false);
    if (paramGraphObject != null) {
      localObject = paramGraphObject.getProperty(paramString);
    }
    if (!(localObject instanceof Boolean)) {}
    for (paramGraphObject = Boolean.valueOf(false);; paramGraphObject = (GraphObject)localObject) {
      return ((Boolean)paramGraphObject).booleanValue();
    }
  }
  
  public static String safeGetStringFromResponse(GraphObject paramGraphObject, String paramString)
  {
    Object localObject = "";
    if (paramGraphObject != null) {
      localObject = paramGraphObject.getProperty(paramString);
    }
    if (!(localObject instanceof String)) {}
    for (paramGraphObject = "";; paramGraphObject = (GraphObject)localObject) {
      return (String)paramGraphObject;
    }
  }
  
  public static void setAppEventAttributionParameters(GraphObject paramGraphObject, AttributionIdentifiers paramAttributionIdentifiers, String paramString, boolean paramBoolean)
  {
    boolean bool2 = true;
    if ((paramAttributionIdentifiers != null) && (paramAttributionIdentifiers.getAttributionId() != null)) {
      paramGraphObject.setProperty("attribution", paramAttributionIdentifiers.getAttributionId());
    }
    boolean bool1;
    if ((paramAttributionIdentifiers != null) && (paramAttributionIdentifiers.getAndroidAdvertiserId() != null))
    {
      paramGraphObject.setProperty("advertiser_id", paramAttributionIdentifiers.getAndroidAdvertiserId());
      if (!paramAttributionIdentifiers.isTrackingLimited())
      {
        bool1 = true;
        paramGraphObject.setProperty("advertiser_tracking_enabled", Boolean.valueOf(bool1));
        label75:
        if (paramBoolean) {
          break label119;
        }
      }
    }
    label119:
    for (paramBoolean = bool2;; paramBoolean = false)
    {
      paramGraphObject.setProperty("application_tracking_enabled", Boolean.valueOf(paramBoolean));
      return;
      bool1 = false;
      break;
      if (paramString == null) {
        break label75;
      }
      paramGraphObject.setProperty("advertiser_id", paramString);
      break label75;
    }
  }
  
  public static void setAppEventExtendedDeviceInfoParameters(GraphObject paramGraphObject, Context paramContext)
  {
    JSONArray localJSONArray = new JSONArray();
    localJSONArray.put("a1");
    String str2 = paramContext.getPackageName();
    int j = -1;
    str1 = "";
    int i = j;
    try
    {
      paramContext = paramContext.getPackageManager().getPackageInfo(str2, 0);
      i = j;
      j = paramContext.versionCode;
      i = j;
      paramContext = paramContext.versionName;
      i = j;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      for (;;)
      {
        paramContext = str1;
      }
    }
    localJSONArray.put(str2);
    localJSONArray.put(i);
    localJSONArray.put(paramContext);
    paramGraphObject.setProperty("extinfo", localJSONArray.toString());
  }
  
  static String sha1hash(String paramString)
  {
    return hashWithAlgorithm("SHA-1", paramString);
  }
  
  static String sha1hash(byte[] paramArrayOfByte)
  {
    return hashWithAlgorithm("SHA-1", paramArrayOfByte);
  }
  
  public static boolean stringsEqualOrEmpty(String paramString1, String paramString2)
  {
    boolean bool2 = TextUtils.isEmpty(paramString1);
    boolean bool1 = TextUtils.isEmpty(paramString2);
    if ((bool2) && (bool1)) {
      bool1 = true;
    }
    for (;;)
    {
      return bool1;
      if ((!bool2) && (!bool1)) {
        bool1 = paramString1.equals(paramString2);
      } else {
        bool1 = false;
      }
    }
  }
  
  public static JSONArray tryGetJSONArrayFromResponse(GraphObject paramGraphObject, String paramString)
  {
    if (paramGraphObject == null) {
      paramGraphObject = null;
    }
    for (;;)
    {
      return paramGraphObject;
      paramGraphObject = paramGraphObject.getProperty(paramString);
      if (!(paramGraphObject instanceof JSONArray)) {
        paramGraphObject = null;
      } else {
        paramGraphObject = (JSONArray)paramGraphObject;
      }
    }
  }
  
  public static JSONObject tryGetJSONObjectFromResponse(GraphObject paramGraphObject, String paramString)
  {
    if (paramGraphObject == null) {
      paramGraphObject = null;
    }
    for (;;)
    {
      return paramGraphObject;
      paramGraphObject = paramGraphObject.getProperty(paramString);
      if (!(paramGraphObject instanceof JSONObject)) {
        paramGraphObject = null;
      } else {
        paramGraphObject = (JSONObject)paramGraphObject;
      }
    }
  }
  
  public static <T> Collection<T> unmodifiableCollection(T... paramVarArgs)
  {
    return Collections.unmodifiableCollection(Arrays.asList(paramVarArgs));
  }
  
  public static class DialogFeatureConfig
  {
    private String dialogName;
    private Uri fallbackUrl;
    private String featureName;
    private int[] featureVersionSpec;
    
    private DialogFeatureConfig(String paramString1, String paramString2, Uri paramUri, int[] paramArrayOfInt)
    {
      this.dialogName = paramString1;
      this.featureName = paramString2;
      this.fallbackUrl = paramUri;
      this.featureVersionSpec = paramArrayOfInt;
    }
    
    private static DialogFeatureConfig parseDialogConfig(JSONObject paramJSONObject)
    {
      String str1 = null;
      Object localObject1 = paramJSONObject.optString("name");
      if (Utility.isNullOrEmpty((String)localObject1)) {
        localObject1 = str1;
      }
      for (;;)
      {
        return (DialogFeatureConfig)localObject1;
        Object localObject2 = ((String)localObject1).split("\\|");
        localObject1 = str1;
        if (localObject2.length == 2)
        {
          String str2 = localObject2[0];
          localObject2 = localObject2[1];
          localObject1 = str1;
          if (!Utility.isNullOrEmpty(str2))
          {
            localObject1 = str1;
            if (!Utility.isNullOrEmpty((String)localObject2))
            {
              str1 = paramJSONObject.optString("url");
              localObject1 = null;
              if (!Utility.isNullOrEmpty(str1)) {
                localObject1 = Uri.parse(str1);
              }
              localObject1 = new DialogFeatureConfig(str2, (String)localObject2, (Uri)localObject1, parseVersionSpec(paramJSONObject.optJSONArray("versions")));
            }
          }
        }
      }
    }
    
    private static int[] parseVersionSpec(JSONArray paramJSONArray)
    {
      Object localObject = null;
      if (paramJSONArray != null)
      {
        int m = paramJSONArray.length();
        int[] arrayOfInt = new int[m];
        int j = 0;
        for (;;)
        {
          localObject = arrayOfInt;
          if (j < m)
          {
            int k = paramJSONArray.optInt(j, -1);
            int i = k;
            if (k == -1)
            {
              localObject = paramJSONArray.optString(j);
              i = k;
              if (Utility.isNullOrEmpty((String)localObject)) {}
            }
            try
            {
              i = Integer.parseInt((String)localObject);
              arrayOfInt[j] = i;
              j++;
            }
            catch (NumberFormatException localNumberFormatException)
            {
              for (;;)
              {
                Utility.logd("FacebookSDK", localNumberFormatException);
                i = -1;
              }
            }
          }
        }
      }
      return localNumberFormatException;
    }
    
    public String getDialogName()
    {
      return this.dialogName;
    }
    
    public Uri getFallbackUrl()
    {
      return this.fallbackUrl;
    }
    
    public String getFeatureName()
    {
      return this.featureName;
    }
    
    public int[] getVersionSpec()
    {
      return this.featureVersionSpec;
    }
  }
  
  public static class FetchedAppSettings
  {
    private Map<String, Map<String, Utility.DialogFeatureConfig>> dialogConfigMap;
    private String nuxContent;
    private boolean nuxEnabled;
    private boolean supportsAttribution;
    private boolean supportsImplicitLogging;
    
    private FetchedAppSettings(boolean paramBoolean1, boolean paramBoolean2, String paramString, boolean paramBoolean3, Map<String, Map<String, Utility.DialogFeatureConfig>> paramMap)
    {
      this.supportsAttribution = paramBoolean1;
      this.supportsImplicitLogging = paramBoolean2;
      this.nuxContent = paramString;
      this.nuxEnabled = paramBoolean3;
      this.dialogConfigMap = paramMap;
    }
    
    public Map<String, Map<String, Utility.DialogFeatureConfig>> getDialogConfigurations()
    {
      return this.dialogConfigMap;
    }
    
    public String getNuxContent()
    {
      return this.nuxContent;
    }
    
    public boolean getNuxEnabled()
    {
      return this.nuxEnabled;
    }
    
    public boolean supportsAttribution()
    {
      return this.supportsAttribution;
    }
    
    public boolean supportsImplicitLogging()
    {
      return this.supportsImplicitLogging;
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\facebook\internal\Utility.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */