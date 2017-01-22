package com.google.android.gms.internal;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Rect;
import android.net.Uri;
import android.net.Uri.Builder;
import android.net.UrlQuerySanitizer;
import android.net.UrlQuerySanitizer.ParameterValuePair;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import java.io.IOException;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.nio.CharBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;
import java.util.UUID;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@ez
public final class gj
{
  private static final Object uf = new Object();
  private static final SimpleDateFormat[] wm = { new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss"), new SimpleDateFormat("yyyyMMdd") };
  private static boolean wn = true;
  private static String wo;
  private static boolean wp = false;
  
  public static String L(String paramString)
  {
    return Uri.parse(paramString).buildUpon().query(null).build().toString();
  }
  
  public static int M(String paramString)
  {
    try
    {
      i = Integer.parseInt(paramString);
      return i;
    }
    catch (NumberFormatException paramString)
    {
      for (;;)
      {
        gs.W("Could not parse value:" + paramString);
        int i = 0;
      }
    }
  }
  
  public static boolean N(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {}
    for (boolean bool = false;; bool = paramString.matches("([^\\s]+(\\.(?i)(jpg|png|gif|bmp|webp))$)")) {
      return bool;
    }
  }
  
  public static long O(String paramString)
  {
    long l1 = -1L;
    int i = 0;
    if (TextUtils.isEmpty(paramString)) {}
    for (;;)
    {
      return l1;
      SimpleDateFormat[] arrayOfSimpleDateFormat = wm;
      int j = arrayOfSimpleDateFormat.length;
      long l2;
      for (;;)
      {
        if (i >= j) {
          break label76;
        }
        SimpleDateFormat localSimpleDateFormat = arrayOfSimpleDateFormat[i];
        try
        {
          localSimpleDateFormat.setLenient(false);
          localSimpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
          l2 = localSimpleDateFormat.parse(paramString).getTime();
          l1 = l2;
        }
        catch (ParseException localParseException)
        {
          i++;
        }
      }
      try
      {
        label76:
        l2 = Long.parseLong(paramString);
        l1 = l2;
      }
      catch (NumberFormatException paramString) {}
    }
  }
  
  public static String a(Readable paramReadable)
    throws IOException
  {
    StringBuilder localStringBuilder = new StringBuilder();
    CharBuffer localCharBuffer = CharBuffer.allocate(2048);
    for (;;)
    {
      int i = paramReadable.read(localCharBuffer);
      if (i == -1) {
        break;
      }
      localCharBuffer.flip();
      localStringBuilder.append(localCharBuffer, 0, i);
    }
    return localStringBuilder.toString();
  }
  
  private static JSONArray a(Collection<?> paramCollection)
    throws JSONException
  {
    JSONArray localJSONArray = new JSONArray();
    paramCollection = paramCollection.iterator();
    while (paramCollection.hasNext()) {
      a(localJSONArray, paramCollection.next());
    }
    return localJSONArray;
  }
  
  static JSONArray a(Object[] paramArrayOfObject)
    throws JSONException
  {
    JSONArray localJSONArray = new JSONArray();
    int j = paramArrayOfObject.length;
    for (int i = 0; i < j; i++) {
      a(localJSONArray, paramArrayOfObject[i]);
    }
    return localJSONArray;
  }
  
  public static void a(Context paramContext, String paramString, WebSettings paramWebSettings)
  {
    paramWebSettings.setUserAgentString(c(paramContext, paramString));
  }
  
  public static void a(Context paramContext, String paramString, List<String> paramList)
  {
    paramList = paramList.iterator();
    while (paramList.hasNext()) {
      new gq(paramContext, paramString, (String)paramList.next()).start();
    }
  }
  
  public static void a(Context paramContext, String paramString1, List<String> paramList, String paramString2)
  {
    paramList = paramList.iterator();
    while (paramList.hasNext()) {
      new gq(paramContext, paramString1, (String)paramList.next(), paramString2).start();
    }
  }
  
  public static void a(Context paramContext, String paramString, boolean paramBoolean, HttpURLConnection paramHttpURLConnection)
  {
    a(paramContext, paramString, paramBoolean, paramHttpURLConnection, false);
  }
  
  public static void a(Context paramContext, String paramString1, boolean paramBoolean, HttpURLConnection paramHttpURLConnection, String paramString2)
  {
    paramHttpURLConnection.setConnectTimeout(60000);
    paramHttpURLConnection.setInstanceFollowRedirects(paramBoolean);
    paramHttpURLConnection.setReadTimeout(60000);
    paramHttpURLConnection.setRequestProperty("User-Agent", paramString2);
    paramHttpURLConnection.setUseCaches(false);
  }
  
  public static void a(Context paramContext, String paramString, boolean paramBoolean1, HttpURLConnection paramHttpURLConnection, boolean paramBoolean2)
  {
    paramHttpURLConnection.setConnectTimeout(60000);
    paramHttpURLConnection.setInstanceFollowRedirects(paramBoolean1);
    paramHttpURLConnection.setReadTimeout(60000);
    paramHttpURLConnection.setRequestProperty("User-Agent", c(paramContext, paramString));
    paramHttpURLConnection.setUseCaches(paramBoolean2);
  }
  
  public static void a(WebView paramWebView)
  {
    if (Build.VERSION.SDK_INT >= 11) {
      gn.a(paramWebView);
    }
  }
  
  private static void a(JSONArray paramJSONArray, Object paramObject)
    throws JSONException
  {
    if ((paramObject instanceof Bundle)) {
      paramJSONArray.put(c((Bundle)paramObject));
    }
    for (;;)
    {
      return;
      if ((paramObject instanceof Map)) {
        paramJSONArray.put(t((Map)paramObject));
      } else if ((paramObject instanceof Collection)) {
        paramJSONArray.put(a((Collection)paramObject));
      } else if ((paramObject instanceof Object[])) {
        paramJSONArray.put(a((Object[])paramObject));
      } else {
        paramJSONArray.put(paramObject);
      }
    }
  }
  
  private static void a(JSONObject paramJSONObject, String paramString, Object paramObject)
    throws JSONException
  {
    if ((paramObject instanceof Bundle)) {
      paramJSONObject.put(paramString, c((Bundle)paramObject));
    }
    for (;;)
    {
      return;
      if ((paramObject instanceof Map))
      {
        paramJSONObject.put(paramString, t((Map)paramObject));
      }
      else
      {
        if ((paramObject instanceof Collection))
        {
          if (paramString != null) {}
          for (;;)
          {
            paramJSONObject.put(paramString, a((Collection)paramObject));
            break;
            paramString = "null";
          }
        }
        if ((paramObject instanceof Object[])) {
          paramJSONObject.put(paramString, a(Arrays.asList((Object[])paramObject)));
        } else {
          paramJSONObject.put(paramString, paramObject);
        }
      }
    }
  }
  
  public static boolean a(PackageManager paramPackageManager, String paramString1, String paramString2)
  {
    if (paramPackageManager.checkPermission(paramString2, paramString1) == 0) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public static boolean a(ClassLoader paramClassLoader, Class<?> paramClass, String paramString)
  {
    boolean bool2 = false;
    try
    {
      bool1 = paramClass.isAssignableFrom(Class.forName(paramString, false, paramClassLoader));
      return bool1;
    }
    catch (Throwable paramClassLoader)
    {
      for (;;)
      {
        boolean bool1 = bool2;
      }
    }
  }
  
  public static void b(WebView paramWebView)
  {
    if (Build.VERSION.SDK_INT >= 11) {
      gn.b(paramWebView);
    }
  }
  
  public static String c(Context paramContext, String paramString)
  {
    for (;;)
    {
      Object localObject1;
      synchronized (uf)
      {
        if (wo != null)
        {
          paramContext = wo;
          return paramContext;
        }
        int i = Build.VERSION.SDK_INT;
        if (i < 17) {}
      }
      if (!gr.ds())
      {
        Handler localHandler = gr.wC;
        Runnable local1 = new com/google/android/gms/internal/gj$1;
        local1.<init>(paramContext);
        localHandler.post(local1);
        for (;;)
        {
          paramContext = wo;
          if (paramContext != null) {
            break;
          }
          try
          {
            uf.wait();
          }
          catch (InterruptedException paramContext)
          {
            wo = dn();
            paramContext = new java/lang/StringBuilder;
            paramContext.<init>();
            gs.W("Interrupted, use default user agent: " + wo);
          }
        }
      }
      try
      {
        wo = r(paramContext);
        paramContext = new java/lang/StringBuilder;
        paramContext.<init>();
        wo = wo + " (Mobile; " + paramString + ")";
        paramContext = wo;
      }
      catch (Exception paramContext)
      {
        for (;;)
        {
          wo = dn();
        }
      }
    }
  }
  
  public static Map<String, String> c(Uri paramUri)
  {
    if (paramUri == null) {}
    HashMap localHashMap;
    for (paramUri = null;; paramUri = localHashMap)
    {
      return paramUri;
      localHashMap = new HashMap();
      Object localObject = new UrlQuerySanitizer();
      ((UrlQuerySanitizer)localObject).setAllowUnregisteredParamaters(true);
      ((UrlQuerySanitizer)localObject).setUnregisteredParameterValueSanitizer(UrlQuerySanitizer.getAllButNulLegal());
      ((UrlQuerySanitizer)localObject).parseUrl(paramUri.toString());
      paramUri = ((UrlQuerySanitizer)localObject).getParameterList().iterator();
      while (paramUri.hasNext())
      {
        localObject = (UrlQuerySanitizer.ParameterValuePair)paramUri.next();
        localHashMap.put(((UrlQuerySanitizer.ParameterValuePair)localObject).mParameter, ((UrlQuerySanitizer.ParameterValuePair)localObject).mValue);
      }
    }
  }
  
  private static JSONObject c(Bundle paramBundle)
    throws JSONException
  {
    JSONObject localJSONObject = new JSONObject();
    Iterator localIterator = paramBundle.keySet().iterator();
    while (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      a(localJSONObject, str, paramBundle.get(str));
    }
    return localJSONObject;
  }
  
  public static void c(Context paramContext, String paramString1, String paramString2)
  {
    ArrayList localArrayList = new ArrayList();
    localArrayList.add(paramString2);
    a(paramContext, paramString1, localArrayList);
  }
  
  public static boolean dk()
  {
    return wn;
  }
  
  public static int dl()
  {
    if (Build.VERSION.SDK_INT >= 9) {}
    for (int i = 6;; i = 0) {
      return i;
    }
  }
  
  public static int dm()
  {
    if (Build.VERSION.SDK_INT >= 9) {}
    for (int i = 7;; i = 1) {
      return i;
    }
  }
  
  static String dn()
  {
    StringBuffer localStringBuffer = new StringBuffer(256);
    localStringBuffer.append("Mozilla/5.0 (Linux; U; Android");
    if (Build.VERSION.RELEASE != null) {
      localStringBuffer.append(" ").append(Build.VERSION.RELEASE);
    }
    localStringBuffer.append("; ").append(Locale.getDefault());
    if (Build.DEVICE != null)
    {
      localStringBuffer.append("; ").append(Build.DEVICE);
      if (Build.DISPLAY != null) {
        localStringBuffer.append(" Build/").append(Build.DISPLAY);
      }
    }
    localStringBuffer.append(") AppleWebKit/533 Version/4.0 Safari/533");
    return localStringBuffer.toString();
  }
  
  public static String jdMethod_do()
  {
    Object localObject1 = UUID.randomUUID();
    byte[] arrayOfByte1 = BigInteger.valueOf(((UUID)localObject1).getLeastSignificantBits()).toByteArray();
    byte[] arrayOfByte2 = BigInteger.valueOf(((UUID)localObject1).getMostSignificantBits()).toByteArray();
    localObject1 = new BigInteger(1, arrayOfByte1).toString();
    int i = 0;
    while (i < 2) {
      try
      {
        Object localObject3 = MessageDigest.getInstance("MD5");
        ((MessageDigest)localObject3).update(arrayOfByte1);
        ((MessageDigest)localObject3).update(arrayOfByte2);
        Object localObject2 = new byte[8];
        System.arraycopy(((MessageDigest)localObject3).digest(), 0, localObject2, 0, 8);
        localObject3 = new java/math/BigInteger;
        ((BigInteger)localObject3).<init>(1, (byte[])localObject2);
        localObject2 = ((BigInteger)localObject3).toString();
        localObject1 = localObject2;
        i++;
      }
      catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
      {
        for (;;) {}
      }
    }
    return (String)localObject1;
  }
  
  public static boolean p(Context paramContext)
  {
    boolean bool2 = false;
    Intent localIntent = new Intent();
    localIntent.setClassName(paramContext, "com.google.android.gms.ads.AdActivity");
    paramContext = paramContext.getPackageManager().resolveActivity(localIntent, 65536);
    if ((paramContext == null) || (paramContext.activityInfo == null))
    {
      gs.W("Could not find com.google.android.gms.ads.AdActivity, please make sure it is declared in AndroidManifest.xml.");
      bool1 = bool2;
      return bool1;
    }
    if ((paramContext.activityInfo.configChanges & 0x10) == 0) {
      gs.W(String.format("com.google.android.gms.ads.AdActivity requires the android:configChanges value to contain \"%s\".", new Object[] { "keyboard" }));
    }
    for (boolean bool1 = false;; bool1 = true)
    {
      if ((paramContext.activityInfo.configChanges & 0x20) == 0)
      {
        gs.W(String.format("com.google.android.gms.ads.AdActivity requires the android:configChanges value to contain \"%s\".", new Object[] { "keyboardHidden" }));
        bool1 = false;
      }
      if ((paramContext.activityInfo.configChanges & 0x80) == 0)
      {
        gs.W(String.format("com.google.android.gms.ads.AdActivity requires the android:configChanges value to contain \"%s\".", new Object[] { "orientation" }));
        bool1 = false;
      }
      if ((paramContext.activityInfo.configChanges & 0x100) == 0)
      {
        gs.W(String.format("com.google.android.gms.ads.AdActivity requires the android:configChanges value to contain \"%s\".", new Object[] { "screenLayout" }));
        bool1 = false;
      }
      if ((paramContext.activityInfo.configChanges & 0x200) == 0)
      {
        gs.W(String.format("com.google.android.gms.ads.AdActivity requires the android:configChanges value to contain \"%s\".", new Object[] { "uiMode" }));
        bool1 = false;
      }
      if ((paramContext.activityInfo.configChanges & 0x400) == 0)
      {
        gs.W(String.format("com.google.android.gms.ads.AdActivity requires the android:configChanges value to contain \"%s\".", new Object[] { "screenSize" }));
        bool1 = false;
      }
      if ((paramContext.activityInfo.configChanges & 0x800) == 0)
      {
        gs.W(String.format("com.google.android.gms.ads.AdActivity requires the android:configChanges value to contain \"%s\".", new Object[] { "smallestScreenSize" }));
        bool1 = bool2;
        break;
      }
      break;
    }
  }
  
  public static void q(Context paramContext)
  {
    if (wp) {}
    for (;;)
    {
      return;
      IntentFilter localIntentFilter = new IntentFilter();
      localIntentFilter.addAction("android.intent.action.USER_PRESENT");
      localIntentFilter.addAction("android.intent.action.SCREEN_OFF");
      paramContext.getApplicationContext().registerReceiver(new a(null), localIntentFilter);
      wp = true;
    }
  }
  
  private static String r(Context paramContext)
  {
    return new WebView(paramContext).getSettings().getUserAgentString();
  }
  
  public static int s(Context paramContext)
  {
    int i = 0;
    int j;
    if ((paramContext instanceof Activity))
    {
      paramContext = ((Activity)paramContext).getWindow();
      Rect localRect = new Rect();
      paramContext.getDecorView().getWindowVisibleDisplayFrame(localRect);
      j = localRect.top;
      i = paramContext.findViewById(16908290).getTop() - j;
    }
    for (;;)
    {
      return i + j;
      j = 0;
    }
  }
  
  public static JSONObject t(Map<String, ?> paramMap)
    throws JSONException
  {
    try
    {
      JSONObject localJSONObject = new org/json/JSONObject;
      localJSONObject.<init>();
      Iterator localIterator = paramMap.keySet().iterator();
      while (localIterator.hasNext())
      {
        String str = (String)localIterator.next();
        a(localJSONObject, str, paramMap.get(str));
      }
      return localJSONObject;
    }
    catch (ClassCastException paramMap)
    {
      throw new JSONException("Could not convert map to JSON: " + paramMap.getMessage());
    }
  }
  
  public static int[] t(Context paramContext)
  {
    WindowManager localWindowManager = (WindowManager)paramContext.getSystemService("window");
    paramContext = new DisplayMetrics();
    localWindowManager.getDefaultDisplay().getMetrics(paramContext);
    float f = 160.0F / paramContext.densityDpi;
    return new int[] { (int)(paramContext.widthPixels * f), (int)(f * paramContext.heightPixels) };
  }
  
  private static final class a
    extends BroadcastReceiver
  {
    public void onReceive(Context paramContext, Intent paramIntent)
    {
      if ("android.intent.action.USER_PRESENT".equals(paramIntent.getAction())) {
        gj.w(true);
      }
      for (;;)
      {
        return;
        if ("android.intent.action.SCREEN_OFF".equals(paramIntent.getAction())) {
          gj.w(false);
        }
      }
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\internal\gj.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */