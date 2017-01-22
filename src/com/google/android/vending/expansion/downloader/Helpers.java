package com.google.android.vending.expansion.downloader;

import android.content.Context;
import android.content.res.Resources;
import android.os.Environment;
import android.os.StatFs;
import android.os.SystemClock;
import android.util.Log;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Helpers
{
  private static final Pattern CONTENT_DISPOSITION_PATTERN = Pattern.compile("attachment;\\s*filename\\s*=\\s*\"([^\"]*)\"");
  public static Random sRandom = new Random(SystemClock.uptimeMillis());
  
  static void deleteFile(String paramString)
  {
    try
    {
      File localFile = new java/io/File;
      localFile.<init>(paramString);
      localFile.delete();
      return;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        Log.w("LVLDL", "file: '" + paramString + "' couldn't be deleted", localException);
      }
    }
  }
  
  public static boolean doesFileExist(Context paramContext, String paramString, long paramLong, boolean paramBoolean)
  {
    paramContext = new File(generateSaveFileName(paramContext, paramString));
    if (paramContext.exists()) {
      if (paramContext.length() != paramLong) {}
    }
    for (paramBoolean = true;; paramBoolean = false)
    {
      return paramBoolean;
      if (paramBoolean) {
        paramContext.delete();
      }
    }
  }
  
  public static String generateSaveFileName(Context paramContext, String paramString)
  {
    return getSaveFilePath(paramContext) + File.separator + paramString;
  }
  
  public static long getAvailableBytes(File paramFile)
  {
    paramFile = new StatFs(paramFile.getPath());
    long l = paramFile.getAvailableBlocks();
    return paramFile.getBlockSize() * (l - 4L);
  }
  
  public static String getDownloadProgressPercent(long paramLong1, long paramLong2)
  {
    if (paramLong2 == 0L) {}
    for (String str = "";; str = Long.toString(100L * paramLong1 / paramLong2) + "%") {
      return str;
    }
  }
  
  public static String getDownloadProgressString(long paramLong1, long paramLong2)
  {
    if (paramLong2 == 0L) {}
    for (String str = "";; str = String.format("%.2f", new Object[] { Float.valueOf((float)paramLong1 / 1048576.0F) }) + "MB /" + String.format("%.2f", new Object[] { Float.valueOf((float)paramLong2 / 1048576.0F) }) + "MB") {
      return str;
    }
  }
  
  public static String getDownloadProgressStringNotification(long paramLong1, long paramLong2)
  {
    if (paramLong2 == 0L) {}
    for (String str = "";; str = getDownloadProgressString(paramLong1, paramLong2) + " (" + getDownloadProgressPercent(paramLong1, paramLong2) + ")") {
      return str;
    }
  }
  
  public static int getDownloaderStringResourceIDFromState(Context paramContext, int paramInt)
  {
    switch (paramInt)
    {
    default: 
      paramInt = getStringResource(paramContext, "state_unknown");
    }
    for (;;)
    {
      return paramInt;
      paramInt = getStringResource(paramContext, "state_idle");
      continue;
      paramInt = getStringResource(paramContext, "state_fetching_url");
      continue;
      paramInt = getStringResource(paramContext, "state_connecting");
      continue;
      paramInt = getStringResource(paramContext, "state_downloading");
      continue;
      paramInt = getStringResource(paramContext, "state_completed");
      continue;
      paramInt = getStringResource(paramContext, "state_paused_network_unavailable");
      continue;
      paramInt = getStringResource(paramContext, "state_paused_by_request");
      continue;
      paramInt = getStringResource(paramContext, "state_paused_wifi_disabled");
      continue;
      paramInt = getStringResource(paramContext, "state_paused_wifi_unavailable");
      continue;
      paramInt = getStringResource(paramContext, "state_paused_wifi_disabled");
      continue;
      paramInt = getStringResource(paramContext, "state_paused_wifi_unavailable");
      continue;
      paramInt = getStringResource(paramContext, "state_paused_roaming");
      continue;
      paramInt = getStringResource(paramContext, "state_paused_network_setup_failure");
      continue;
      paramInt = getStringResource(paramContext, "state_paused_sdcard_unavailable");
      continue;
      paramInt = getStringResource(paramContext, "state_failed_unlicensed");
      continue;
      paramInt = getStringResource(paramContext, "state_failed_fetching_url");
      continue;
      paramInt = getStringResource(paramContext, "state_failed_sdcard_full");
      continue;
      paramInt = getStringResource(paramContext, "state_failed_cancelled");
    }
  }
  
  public static String getExpansionAPKFileName(Context paramContext, boolean paramBoolean, int paramInt)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    if (paramBoolean) {}
    for (String str = "main.";; str = "patch.") {
      return str + paramInt + "." + paramContext.getPackageName() + ".obb";
    }
  }
  
  public static File getFilesystemRoot(String paramString)
  {
    File localFile = Environment.getDownloadCacheDirectory();
    if (paramString.startsWith(localFile.getPath())) {}
    for (paramString = localFile;; paramString = localFile)
    {
      return paramString;
      localFile = Environment.getExternalStorageDirectory();
      if (!paramString.startsWith(localFile.getPath())) {
        break;
      }
    }
    throw new IllegalArgumentException("Cannot determine filesystem root for " + paramString);
  }
  
  public static int getIdResource(Context paramContext, String paramString)
  {
    return paramContext.getResources().getIdentifier(paramString, "id", paramContext.getPackageName());
  }
  
  public static int getLayoutResource(Context paramContext, String paramString)
  {
    return paramContext.getResources().getIdentifier(paramString, "layout", paramContext.getPackageName());
  }
  
  public static String getSaveFilePath(Context paramContext)
  {
    File localFile = Environment.getExternalStorageDirectory();
    return localFile.toString() + Constants.EXP_PATH + paramContext.getPackageName();
  }
  
  public static String getSpeedString(float paramFloat)
  {
    return String.format("%.2f", new Object[] { Float.valueOf(1000.0F * paramFloat / 1024.0F) });
  }
  
  public static int getStringResource(Context paramContext, String paramString)
  {
    return paramContext.getResources().getIdentifier(paramString, "string", paramContext.getPackageName());
  }
  
  public static String getTimeRemaining(long paramLong)
  {
    if (paramLong > 3600000L) {}
    for (SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());; localSimpleDateFormat = new SimpleDateFormat("mm:ss", Locale.getDefault())) {
      return localSimpleDateFormat.format(new Date(paramLong - TimeZone.getDefault().getRawOffset()));
    }
  }
  
  public static boolean isExternalMediaMounted()
  {
    if (!Environment.getExternalStorageState().equals("mounted")) {}
    for (boolean bool = false;; bool = true) {
      return bool;
    }
  }
  
  public static boolean isFilenameValid(String paramString)
  {
    paramString = paramString.replaceFirst("/+", "/");
    if ((paramString.startsWith(Environment.getDownloadCacheDirectory().toString())) || (paramString.startsWith(Environment.getExternalStorageDirectory().toString()))) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  static String parseContentDisposition(String paramString)
  {
    try
    {
      paramString = CONTENT_DISPOSITION_PATTERN.matcher(paramString);
      if (paramString.find())
      {
        paramString = paramString.group(1);
        return paramString;
      }
    }
    catch (IllegalStateException paramString)
    {
      for (;;)
      {
        paramString = null;
      }
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\vending\expansion\downloader\Helpers.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */