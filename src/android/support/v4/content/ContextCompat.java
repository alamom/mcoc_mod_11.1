package android.support.v4.content;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Environment;
import android.os.Process;
import android.support.annotation.NonNull;
import java.io.File;

public class ContextCompat
{
  private static final String DIR_ANDROID = "Android";
  private static final String DIR_CACHE = "cache";
  private static final String DIR_DATA = "data";
  private static final String DIR_FILES = "files";
  private static final String DIR_OBB = "obb";
  private static final String TAG = "ContextCompat";
  
  private static File buildPath(File paramFile, String... paramVarArgs)
  {
    int j = paramVarArgs.length;
    int i = 0;
    String str;
    if (i < j)
    {
      str = paramVarArgs[i];
      if (paramFile == null) {
        paramFile = new File(str);
      }
    }
    for (;;)
    {
      i++;
      break;
      if (str != null)
      {
        paramFile = new File(paramFile, str);
        continue;
        return paramFile;
      }
    }
  }
  
  public static int checkSelfPermission(@NonNull Context paramContext, @NonNull String paramString)
  {
    if (paramString == null) {
      throw new IllegalArgumentException("permission is null");
    }
    return paramContext.checkPermission(paramString, Process.myPid(), Process.myUid());
  }
  
  /* Error */
  private static File createFilesDir(File paramFile)
  {
    // Byte code:
    //   0: ldc 2
    //   2: monitorenter
    //   3: aload_0
    //   4: astore_2
    //   5: aload_0
    //   6: invokevirtual 68	java/io/File:exists	()Z
    //   9: ifne +23 -> 32
    //   12: aload_0
    //   13: astore_2
    //   14: aload_0
    //   15: invokevirtual 71	java/io/File:mkdirs	()Z
    //   18: ifne +14 -> 32
    //   21: aload_0
    //   22: invokevirtual 68	java/io/File:exists	()Z
    //   25: istore_1
    //   26: iload_1
    //   27: ifeq +10 -> 37
    //   30: aload_0
    //   31: astore_2
    //   32: ldc 2
    //   34: monitorexit
    //   35: aload_2
    //   36: areturn
    //   37: new 73	java/lang/StringBuilder
    //   40: astore_2
    //   41: aload_2
    //   42: invokespecial 74	java/lang/StringBuilder:<init>	()V
    //   45: ldc 23
    //   47: aload_2
    //   48: ldc 76
    //   50: invokevirtual 80	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   53: aload_0
    //   54: invokevirtual 84	java/io/File:getPath	()Ljava/lang/String;
    //   57: invokevirtual 80	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   60: invokevirtual 87	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   63: invokestatic 93	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;)I
    //   66: pop
    //   67: aconst_null
    //   68: astore_2
    //   69: goto -37 -> 32
    //   72: astore_0
    //   73: ldc 2
    //   75: monitorexit
    //   76: aload_0
    //   77: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	78	0	paramFile	File
    //   25	2	1	bool	boolean
    //   4	65	2	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   5	12	72	finally
    //   14	26	72	finally
    //   37	67	72	finally
  }
  
  public static final int getColor(Context paramContext, int paramInt)
  {
    if (Build.VERSION.SDK_INT >= 23) {}
    for (paramInt = ContextCompatApi23.getColor(paramContext, paramInt);; paramInt = paramContext.getResources().getColor(paramInt)) {
      return paramInt;
    }
  }
  
  public static final ColorStateList getColorStateList(Context paramContext, int paramInt)
  {
    if (Build.VERSION.SDK_INT >= 23) {}
    for (paramContext = ContextCompatApi23.getColorStateList(paramContext, paramInt);; paramContext = paramContext.getResources().getColorStateList(paramInt)) {
      return paramContext;
    }
  }
  
  public static final Drawable getDrawable(Context paramContext, int paramInt)
  {
    if (Build.VERSION.SDK_INT >= 21) {}
    for (paramContext = ContextCompatApi21.getDrawable(paramContext, paramInt);; paramContext = paramContext.getResources().getDrawable(paramInt)) {
      return paramContext;
    }
  }
  
  public static File[] getExternalCacheDirs(Context paramContext)
  {
    int i = Build.VERSION.SDK_INT;
    if (i >= 19)
    {
      paramContext = ContextCompatKitKat.getExternalCacheDirs(paramContext);
      return paramContext;
    }
    if (i >= 8) {}
    for (paramContext = ContextCompatFroyo.getExternalCacheDir(paramContext);; paramContext = buildPath(Environment.getExternalStorageDirectory(), new String[] { "Android", "data", paramContext.getPackageName(), "cache" }))
    {
      File[] arrayOfFile = new File[1];
      arrayOfFile[0] = paramContext;
      paramContext = arrayOfFile;
      break;
    }
  }
  
  public static File[] getExternalFilesDirs(Context paramContext, String paramString)
  {
    int i = Build.VERSION.SDK_INT;
    if (i >= 19)
    {
      paramContext = ContextCompatKitKat.getExternalFilesDirs(paramContext, paramString);
      return paramContext;
    }
    if (i >= 8) {}
    for (paramContext = ContextCompatFroyo.getExternalFilesDir(paramContext, paramString);; paramContext = buildPath(Environment.getExternalStorageDirectory(), new String[] { "Android", "data", paramContext.getPackageName(), "files", paramString }))
    {
      paramString = new File[1];
      paramString[0] = paramContext;
      paramContext = paramString;
      break;
    }
  }
  
  public static File[] getObbDirs(Context paramContext)
  {
    int i = Build.VERSION.SDK_INT;
    if (i >= 19)
    {
      paramContext = ContextCompatKitKat.getObbDirs(paramContext);
      return paramContext;
    }
    if (i >= 11) {}
    for (paramContext = ContextCompatHoneycomb.getObbDir(paramContext);; paramContext = buildPath(Environment.getExternalStorageDirectory(), new String[] { "Android", "obb", paramContext.getPackageName() }))
    {
      File[] arrayOfFile = new File[1];
      arrayOfFile[0] = paramContext;
      paramContext = arrayOfFile;
      break;
    }
  }
  
  public static boolean startActivities(Context paramContext, Intent[] paramArrayOfIntent)
  {
    return startActivities(paramContext, paramArrayOfIntent, null);
  }
  
  public static boolean startActivities(Context paramContext, Intent[] paramArrayOfIntent, Bundle paramBundle)
  {
    boolean bool = true;
    int i = Build.VERSION.SDK_INT;
    if (i >= 16) {
      ContextCompatJellybean.startActivities(paramContext, paramArrayOfIntent, paramBundle);
    }
    for (;;)
    {
      return bool;
      if (i >= 11) {
        ContextCompatHoneycomb.startActivities(paramContext, paramArrayOfIntent);
      } else {
        bool = false;
      }
    }
  }
  
  public final File getCodeCacheDir(Context paramContext)
  {
    if (Build.VERSION.SDK_INT >= 21) {}
    for (paramContext = ContextCompatApi21.getCodeCacheDir(paramContext);; paramContext = createFilesDir(new File(paramContext.getApplicationInfo().dataDir, "code_cache"))) {
      return paramContext;
    }
  }
  
  public final File getNoBackupFilesDir(Context paramContext)
  {
    if (Build.VERSION.SDK_INT >= 21) {}
    for (paramContext = ContextCompatApi21.getNoBackupFilesDir(paramContext);; paramContext = createFilesDir(new File(paramContext.getApplicationInfo().dataDir, "no_backup"))) {
      return paramContext;
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\android\support\v4\content\ContextCompat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */