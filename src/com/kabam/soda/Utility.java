package com.kabam.soda;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import android.os.Build.VERSION;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.text.Html;
import android.text.Spannable;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.style.URLSpan;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.util.Patterns;
import java.io.UnsupportedEncodingException;
import java.net.NetworkInterface;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utility
{
  public static String PREFS_NAME = "KABAM_SODA_PREFS";
  private static final String STORE_AMAZON_APPS = "com.amazon.apps";
  private static final String STORE_GOOGLE_PLAY = "com.google.play";
  private static final String TAG = Utility.class.getSimpleName();
  private static String androidId;
  private static String deviceHeader;
  private static String deviceId;
  private static String macAddress;
  private static String userAgent;
  
  public static String digest(String paramString1, String paramString2)
  {
    try
    {
      paramString2 = MessageDigest.getInstance(paramString2).digest(paramString1.getBytes());
      paramString1 = new java/lang/StringBuilder;
      paramString1.<init>();
      for (int i = 0; i < paramString2.length; i++) {
        paramString1.append(Integer.toHexString(paramString2[i] & 0xFF | 0x100).substring(1, 3));
      }
      paramString1 = paramString1.toString();
    }
    catch (NoSuchAlgorithmException paramString1)
    {
      for (;;)
      {
        paramString1 = "";
      }
    }
    return paramString1;
  }
  
  public static String getAndroidId(Context paramContext)
  {
    if (androidId == null) {}
    try
    {
      androidId = Settings.Secure.getString(paramContext.getContentResolver(), "android_id");
      return androidId;
    }
    catch (RuntimeException paramContext)
    {
      for (;;)
      {
        Log.e(TAG, "Couldn't get Android id.  Error: " + paramContext.getMessage(), paramContext);
      }
    }
  }
  
  public static String getCountryCode()
  {
    Object localObject = null;
    try
    {
      String str = getCountryCode(KabamSession.getClientActivity());
      localObject = str;
    }
    catch (KabamException localKabamException)
    {
      for (;;)
      {
        Log.e(TAG, "Couldn't get country code because " + localKabamException.getMessage(), localKabamException);
      }
    }
    return (String)localObject;
  }
  
  private static String getCountryCode(Context paramContext)
  {
    String str;
    if (paramContext != null) {
      if ((paramContext.getApplicationInfo().flags & 0x2) != 0)
      {
        str = paramContext.getSharedPreferences(PREFS_NAME, 0).getString("countryCode", null);
        if ((str != null) && (str != ""))
        {
          Log.d(TAG, "Override countryCode with saved value of: " + str);
          paramContext = str;
        }
      }
    }
    for (;;)
    {
      return paramContext;
      paramContext = (TelephonyManager)paramContext.getSystemService("phone");
      if (paramContext != null)
      {
        str = paramContext.getSimCountryIso();
        if ((str != null) && (str.length() == 2))
        {
          Log.d(TAG, "simCountry: " + str.toUpperCase(Locale.US));
          paramContext = str.toUpperCase(Locale.US);
          continue;
        }
        if (paramContext.getPhoneType() != 2)
        {
          paramContext = paramContext.getNetworkCountryIso();
          if ((paramContext != null) && (paramContext.length() == 2))
          {
            Log.d(TAG, "networkCountry: " + paramContext.toUpperCase(Locale.US));
            paramContext = paramContext.toUpperCase(Locale.US);
            continue;
          }
        }
      }
      paramContext = null;
    }
  }
  
  public static String getDeviceHeader()
  {
    Object localObject = null;
    try
    {
      String str = getDeviceHeader(KabamSession.getClientActivity());
      localObject = str;
    }
    catch (KabamException localKabamException)
    {
      for (;;)
      {
        Log.e(TAG, "Couldn't get device header due to a problem with the Kabam session.  Error Message: " + localKabamException.getMessage(), localKabamException);
      }
    }
    return (String)localObject;
  }
  
  public static String getDeviceHeader(Context paramContext)
  {
    String str1;
    String str3;
    String str4;
    String str2;
    String str5;
    if ((deviceHeader == null) && (paramContext != null))
    {
      str1 = getMacAddress();
      if ((str1 != null) && (!str1.trim().isEmpty())) {
        break label187;
      }
      str3 = "";
      str4 = getAndroidId(paramContext);
      AdvertisingIdentifier localAdvertisingIdentifier = AdvertisingIdentifier.getInstance(paramContext);
      str1 = "";
      str2 = "";
      str5 = localAdvertisingIdentifier.getId();
      switch (localAdvertisingIdentifier.getType())
      {
      default: 
        if ((str5 == null) || (str5.trim().isEmpty()))
        {
          str1 = "";
          label107:
          if ((str4 != null) && (!str4.trim().isEmpty())) {
            break label295;
          }
          str4 = "";
          label127:
          paramContext = getDeviceId(paramContext);
          if ((paramContext != null) && (!paramContext.trim().isEmpty())) {
            break label326;
          }
        }
        break;
      }
    }
    label187:
    label295:
    label326:
    for (paramContext = "";; paramContext = "matDeviceId/" + sanitizeUserAgentToken(paramContext))
    {
      deviceHeader = paramContext + str1 + str2 + str3 + str4;
      return deviceHeader;
      str3 = " mac/" + sanitizeUserAgentToken(str1).toUpperCase(Locale.ENGLISH);
      break;
      if ((str5 == null) || (str5.trim().isEmpty())) {}
      for (str2 = "";; str2 = " googleplayAdvertisingId/" + sanitizeUserAgentToken(str5)) {
        break;
      }
      str1 = " androidId/" + sanitizeUserAgentToken(str5);
      break label107;
      str4 = " odin/" + sanitizeUserAgentToken(sha1(str4));
      break label127;
    }
  }
  
  public static String getDeviceId(Context paramContext)
  {
    if (deviceId == null) {}
    try
    {
      deviceId = ((TelephonyManager)paramContext.getSystemService("phone")).getDeviceId();
      if (deviceId == null) {
        deviceId = Build.SERIAL;
      }
      return deviceId;
    }
    catch (RuntimeException paramContext)
    {
      for (;;)
      {
        Log.e(TAG, "Couldn't get device id.  Error: " + paramContext.getMessage(), paramContext);
      }
    }
  }
  
  private static String getInAppPurchaseStore(Context paramContext)
  {
    String str2 = "com.google.play";
    String str1;
    if (paramContext == null) {
      str1 = str2;
    }
    for (;;)
    {
      return str1;
      PackageManager localPackageManager = paramContext.getPackageManager();
      str1 = str2;
      if (localPackageManager != null)
      {
        paramContext = localPackageManager.getInstallerPackageName(paramContext.getPackageName());
        Log.d(TAG, "getInstallerPackageName() == " + paramContext);
        if ((paramContext != null) && (paramContext.startsWith("com.amazon")))
        {
          str1 = "com.amazon.apps";
        }
        else
        {
          str1 = str2;
          if (Build.MANUFACTURER.equalsIgnoreCase("amazon")) {
            str1 = "com.amazon.apps";
          }
        }
      }
    }
  }
  
  public static String getMacAddress()
  {
    if (macAddress == null) {
      macAddress = getMacAddress(null);
    }
    return macAddress;
  }
  
  public static String getMacAddress(String paramString)
  {
    str = "";
    try
    {
      Iterator localIterator = Collections.list(NetworkInterface.getNetworkInterfaces()).iterator();
      do
      {
        do
        {
          localObject = str;
          if (!localIterator.hasNext()) {
            break;
          }
          localObject = (NetworkInterface)localIterator.next();
        } while ((paramString != null) && (!((NetworkInterface)localObject).getName().equalsIgnoreCase(paramString)));
        localObject = ((NetworkInterface)localObject).getHardwareAddress();
      } while (localObject == null);
      paramString = new java/lang/StringBuilder;
      paramString.<init>();
      for (int i = 0; i < localObject.length; i++) {
        paramString.append(String.format("%02X:", new Object[] { Byte.valueOf(localObject[i]) }));
      }
      if (paramString.length() > 0) {
        paramString.deleteCharAt(paramString.length() - 1);
      }
      localObject = paramString.toString();
    }
    catch (Exception paramString)
    {
      for (;;)
      {
        Log.e(TAG, "Couldn't retrieve MAC address.", paramString);
        Object localObject = str;
      }
    }
    return (String)localObject;
  }
  
  public static String getUserAgent(Settings paramSettings)
  {
    if (userAgent == null) {}
    try
    {
      localActivity = KabamSession.getClientActivity();
      localObject1 = Build.MANUFACTURER;
      str1 = Build.MODEL;
      if (!str1.startsWith((String)localObject1)) {
        break label383;
      }
    }
    catch (KabamException paramSettings)
    {
      try
      {
        for (;;)
        {
          Activity localActivity;
          Object localObject1;
          String str2;
          Object localObject2;
          localObject4 = new java/lang/StringBuilder;
          ((StringBuilder)localObject4).<init>();
          userAgent = "kabamSoda/" + URLEncoder.encode(paramSettings, "UTF-8") + " platform/android" + " platformVersion/" + URLEncoder.encode(Build.VERSION.RELEASE, "UTF-8") + " model/" + URLEncoder.encode(sanitizeUserAgentToken(str1), "UTF-8") + " carrier/" + URLEncoder.encode(sanitizeUserAgentToken(str2), "UTF-8") + " appVersion/" + URLEncoder.encode((String)localObject1, "UTF-8") + " app/" + URLEncoder.encode((String)localObject2, "UTF-8") + " store/" + URLEncoder.encode(getInAppPurchaseStore(localActivity), "UTF-8") + str3;
          for (paramSettings = userAgent;; paramSettings = "")
          {
            return paramSettings;
            paramSettings = paramSettings;
            Log.w(TAG, "getUserAgent(): " + paramSettings.getMessage());
          }
          String str1 = (String)localObject1 + "_" + str1;
        }
        String str3 = " " + str3;
      }
      catch (UnsupportedEncodingException paramSettings)
      {
        for (;;)
        {
          Log.d(TAG, "UTF-8 unsupported?! " + paramSettings);
        }
      }
    }
    str2 = "";
    if (localActivity != null) {
      str2 = ((TelephonyManager)localActivity.getSystemService("phone")).getNetworkOperatorName();
    }
    str3 = "0.0.0";
    localObject4 = "unknown";
    localObject2 = localObject4;
    localObject1 = str3;
    if (localActivity != null) {
      localObject1 = str3;
    }
    try
    {
      PackageInfo localPackageInfo = localActivity.getPackageManager().getPackageInfo(localActivity.getPackageName(), 0);
      localObject1 = str3;
      localObject2 = localPackageInfo.versionName;
      localObject1 = localObject2;
      str3 = localPackageInfo.packageName;
      localObject1 = localObject2;
      localObject2 = str3;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      for (;;)
      {
        Object localObject3 = localObject4;
      }
    }
    str3 = paramSettings.getUserAgentExtras();
    if ((str3 == null) || (str3.isEmpty()))
    {
      str3 = "";
      localObject4 = paramSettings.getVersion();
      paramSettings = (Settings)localObject4;
      if (localObject4 != null)
      {
        paramSettings = (Settings)localObject4;
        if (!((String)localObject4).isEmpty())
        {
          paramSettings = (Settings)localObject4;
          if (((String)localObject4).startsWith("v")) {
            paramSettings = ((String)localObject4).substring(1);
          }
        }
      }
    }
  }
  
  public static final boolean isValidEmail(CharSequence paramCharSequence)
  {
    if ((!TextUtils.isEmpty(paramCharSequence)) && (Patterns.EMAIL_ADDRESS.matcher(paramCharSequence).matches())) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  private static String sanitizeUserAgentToken(String paramString)
  {
    if (paramString == null) {}
    for (paramString = "";; paramString = paramString.replaceAll("\\\\| ", "_"))
    {
      return paramString;
      try
      {
        String str = URLDecoder.decode(paramString, "UTF-8");
        paramString = str;
      }
      catch (UnsupportedEncodingException localUnsupportedEncodingException)
      {
        for (;;)
        {
          Log.d(TAG, "UTF-8 unsupported?!");
        }
      }
    }
  }
  
  public static String sha1(String paramString)
  {
    return digest(paramString, "SHA-1");
  }
  
  public static final Spannable stripUnderlines(String paramString)
  {
    Spannable localSpannable = (Spannable)Html.fromHtml(paramString);
    for (Object localObject : (URLSpan[])localSpannable.getSpans(0, localSpannable.length(), URLSpan.class)) {
      localSpannable.setSpan(new UnderlineSpan()
      {
        public void updateDrawState(TextPaint paramAnonymousTextPaint)
        {
          paramAnonymousTextPaint.setUnderlineText(false);
        }
      }, localSpannable.getSpanStart(localObject), localSpannable.getSpanEnd(localObject), 0);
    }
    return localSpannable;
  }
  
  public static String toRFC2616Date(Date paramDate)
  {
    return new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z", Locale.US).format(paramDate);
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\kabam\soda\Utility.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */