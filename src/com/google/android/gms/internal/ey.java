package com.google.android.gms.internal;

import android.content.Context;
import android.net.Uri.Builder;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Looper;
import android.text.TextUtils;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.LinkedList;

@ez
public class ey
  implements Thread.UncaughtExceptionHandler
{
  private Context mContext;
  private Thread.UncaughtExceptionHandler sR;
  private Thread.UncaughtExceptionHandler sS;
  private gt sT;
  
  public ey(Context paramContext, gt paramgt, Thread.UncaughtExceptionHandler paramUncaughtExceptionHandler1, Thread.UncaughtExceptionHandler paramUncaughtExceptionHandler2)
  {
    this.sR = paramUncaughtExceptionHandler1;
    this.sS = paramUncaughtExceptionHandler2;
    this.mContext = paramContext;
    this.sT = paramgt;
  }
  
  public static ey a(Context paramContext, Thread paramThread, gt paramgt)
  {
    if ((paramContext == null) || (paramThread == null) || (paramgt == null)) {
      paramContext = null;
    }
    for (;;)
    {
      return paramContext;
      gb.bD();
      if (!k(paramContext))
      {
        paramContext = null;
      }
      else
      {
        Thread.UncaughtExceptionHandler localUncaughtExceptionHandler = paramThread.getUncaughtExceptionHandler();
        paramContext = new ey(paramContext, paramgt, localUncaughtExceptionHandler, Thread.getDefaultUncaughtExceptionHandler());
        if ((localUncaughtExceptionHandler == null) || (!(localUncaughtExceptionHandler instanceof ey))) {
          try
          {
            paramThread.setUncaughtExceptionHandler(paramContext);
          }
          catch (SecurityException paramContext)
          {
            gs.c("Fail to set UncaughtExceptionHandler.", paramContext);
            paramContext = null;
          }
        } else {
          paramContext = (ey)localUncaughtExceptionHandler;
        }
      }
    }
  }
  
  private String cw()
  {
    String str2 = Build.MANUFACTURER;
    String str1 = Build.MODEL;
    if (str1.startsWith(str2)) {}
    for (;;)
    {
      return str1;
      str1 = str2 + " " + str1;
    }
  }
  
  private Throwable d(Throwable paramThrowable)
  {
    Object localObject = gb.bD();
    if ((localObject != null) && (((Bundle)localObject).getBoolean("gads:sdk_crash_report_full_stacktrace", false))) {
      return paramThrowable;
    }
    localObject = new LinkedList();
    while (paramThrowable != null)
    {
      ((LinkedList)localObject).push(paramThrowable);
      paramThrowable = paramThrowable.getCause();
    }
    paramThrowable = null;
    label52:
    Throwable localThrowable;
    if (!((LinkedList)localObject).isEmpty())
    {
      localThrowable = (Throwable)((LinkedList)localObject).pop();
      StackTraceElement[] arrayOfStackTraceElement = localThrowable.getStackTrace();
      ArrayList localArrayList = new ArrayList();
      localArrayList.add(new StackTraceElement(localThrowable.getClass().getName(), "<filtered>", "<filtered>", 1));
      int k = arrayOfStackTraceElement.length;
      int i = 0;
      int j = 0;
      if (i < k)
      {
        StackTraceElement localStackTraceElement = arrayOfStackTraceElement[i];
        if (G(localStackTraceElement.getClassName()))
        {
          localArrayList.add(localStackTraceElement);
          j = 1;
        }
        for (;;)
        {
          i++;
          break;
          if (H(localStackTraceElement.getClassName())) {
            localArrayList.add(localStackTraceElement);
          } else {
            localArrayList.add(new StackTraceElement("<filtered>", "<filtered>", "<filtered>", 1));
          }
        }
      }
      if (j == 0) {
        break label267;
      }
      if (paramThrowable == null)
      {
        paramThrowable = new Throwable(localThrowable.getMessage());
        label228:
        paramThrowable.setStackTrace((StackTraceElement[])localArrayList.toArray(new StackTraceElement[0]));
      }
    }
    label267:
    for (;;)
    {
      break label52;
      paramThrowable = new Throwable(localThrowable.getMessage(), paramThrowable);
      break label228;
      break;
    }
  }
  
  private static boolean k(Context paramContext)
  {
    boolean bool = false;
    paramContext = gb.bD();
    if (paramContext == null) {}
    for (;;)
    {
      return bool;
      if (paramContext.getBoolean("gads:sdk_crash_report_enabled", false)) {
        bool = true;
      }
    }
  }
  
  protected boolean G(String paramString)
  {
    boolean bool2 = false;
    boolean bool1;
    if (TextUtils.isEmpty(paramString)) {
      bool1 = bool2;
    }
    for (;;)
    {
      return bool1;
      if (paramString.startsWith("com.google.android.gms.ads")) {
        bool1 = true;
      } else if (paramString.startsWith("com.google.ads")) {
        bool1 = true;
      } else {
        try
        {
          bool1 = Class.forName(paramString).isAnnotationPresent(ez.class);
        }
        catch (Exception localException)
        {
          gs.a("Fail to check class type for class " + paramString, localException);
          bool1 = bool2;
        }
      }
    }
  }
  
  protected boolean H(String paramString)
  {
    boolean bool = false;
    if (TextUtils.isEmpty(paramString)) {}
    for (;;)
    {
      return bool;
      if ((paramString.startsWith("android.")) || (paramString.startsWith("java."))) {
        bool = true;
      }
    }
  }
  
  protected boolean a(Throwable paramThrowable)
  {
    boolean bool1 = true;
    boolean bool2 = false;
    if (paramThrowable == null) {
      bool1 = bool2;
    }
    int j;
    int k;
    do
    {
      return bool1;
      j = 0;
      k = 0;
      while (paramThrowable != null)
      {
        for (StackTraceElement localStackTraceElement : paramThrowable.getStackTrace())
        {
          if (G(localStackTraceElement.getClassName())) {
            k = 1;
          }
          if (getClass().getName().equals(localStackTraceElement.getClassName())) {
            j = 1;
          }
        }
        paramThrowable = paramThrowable.getCause();
      }
    } while ((k != 0) && (j == 0));
    for (;;)
    {
      bool1 = false;
    }
  }
  
  public void b(Throwable paramThrowable)
  {
    if (!k(this.mContext)) {}
    for (;;)
    {
      return;
      paramThrowable = d(paramThrowable);
      if (paramThrowable != null)
      {
        ArrayList localArrayList = new ArrayList();
        localArrayList.add(c(paramThrowable));
        gj.a(this.mContext, this.sT.wD, localArrayList, gb.de());
      }
    }
  }
  
  protected String c(Throwable paramThrowable)
  {
    StringWriter localStringWriter = new StringWriter();
    paramThrowable.printStackTrace(new PrintWriter(localStringWriter));
    return new Uri.Builder().scheme("https").path("//pagead2.googlesyndication.com/pagead/gen_204").appendQueryParameter("id", "gmob-apps-report-exception").appendQueryParameter("os", Build.VERSION.RELEASE).appendQueryParameter("api", String.valueOf(Build.VERSION.SDK_INT)).appendQueryParameter("device", cw()).appendQueryParameter("js", this.sT.wD).appendQueryParameter("appid", this.mContext.getApplicationContext().getPackageName()).appendQueryParameter("stacktrace", localStringWriter.toString()).toString();
  }
  
  public void uncaughtException(Thread paramThread, Throwable paramThrowable)
  {
    if (a(paramThrowable))
    {
      b(paramThrowable);
      if (Looper.getMainLooper().getThread() == paramThread) {}
    }
    for (;;)
    {
      return;
      if (this.sR != null) {
        this.sR.uncaughtException(paramThread, paramThrowable);
      } else if (this.sS != null) {
        this.sS.uncaughtException(paramThread, paramThrowable);
      }
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\internal\ey.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */