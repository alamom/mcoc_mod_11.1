package com.adjust.sdk.plugin;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.text.TextUtils;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Locale;

public class MacAddressUtil
{
  public static String getMacAddress(Context paramContext)
  {
    paramContext = getRawMacAddress(paramContext);
    if (paramContext == null) {}
    for (paramContext = null;; paramContext = removeSpaceString(paramContext.toUpperCase(Locale.US))) {
      return paramContext;
    }
  }
  
  private static String getRawMacAddress(Context paramContext)
  {
    String str = loadAddress("wlan0");
    if (str != null) {
      paramContext = str;
    }
    for (;;)
    {
      return paramContext;
      str = loadAddress("eth0");
      if (str != null)
      {
        paramContext = str;
        continue;
      }
      try
      {
        paramContext = ((WifiManager)paramContext.getSystemService("wifi")).getConnectionInfo().getMacAddress();
        if (paramContext != null) {}
      }
      catch (Exception paramContext)
      {
        paramContext = null;
      }
    }
  }
  
  private static String loadAddress(String paramString)
  {
    StringBuilder localStringBuilder;
    try
    {
      localStringBuilder = new java/lang/StringBuilder;
      localStringBuilder.<init>();
      String str = "/sys/class/net/" + paramString + "/address";
      localStringBuilder = new java/lang/StringBuilder;
      localStringBuilder.<init>(1000);
      paramString = new java/io/BufferedReader;
      Object localObject = new java/io/FileReader;
      ((FileReader)localObject).<init>(str);
      paramString.<init>((Reader)localObject, 1024);
      localObject = new char['Ѐ'];
      for (;;)
      {
        int i = paramString.read((char[])localObject);
        if (i == -1) {
          break;
        }
        localStringBuilder.append(String.valueOf((char[])localObject, 0, i));
      }
      return paramString;
    }
    catch (IOException paramString)
    {
      paramString = null;
    }
    for (;;)
    {
      paramString.close();
      paramString = localStringBuilder.toString();
    }
  }
  
  private static String removeSpaceString(String paramString)
  {
    if (paramString == null) {
      paramString = null;
    }
    for (;;)
    {
      return paramString;
      String str = paramString.replaceAll("\\s", "");
      paramString = str;
      if (TextUtils.isEmpty(str)) {
        paramString = null;
      }
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\adjust\sdk\plugin\MacAddressUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */