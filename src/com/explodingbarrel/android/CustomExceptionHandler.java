package com.explodingbarrel.android;

import android.content.Context;
import android.util.Log;
import com.unity3d.player.UnityPlayer;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;

public class CustomExceptionHandler
  implements Thread.UncaughtExceptionHandler
{
  private static CustomExceptionHandler _instance;
  private Thread.UncaughtExceptionHandler _defaultEH = Thread.getDefaultUncaughtExceptionHandler();
  private Context context;
  private final String crashLogFilename = "java_crash.log";
  
  public CustomExceptionHandler(Context paramContext)
  {
    if (paramContext != null) {}
    for (this.context = paramContext;; this.context = getContext()) {
      return;
    }
  }
  
  private static Context getContext()
  {
    return UnityPlayer.currentActivity;
  }
  
  public static CustomExceptionHandler getInstance(Context paramContext)
  {
    if (_instance == null) {
      _instance = new CustomExceptionHandler(paramContext);
    }
    try
    {
      Thread.setDefaultUncaughtExceptionHandler(_instance);
      return _instance;
    }
    catch (Throwable paramContext)
    {
      for (;;)
      {
        Log.d("CustomExceptionHandler", "CustomExceptionHandler: exception: " + paramContext);
      }
    }
  }
  
  private void writeFile()
  {
    try
    {
      FileOutputStream localFileOutputStream = this.context.openFileOutput("java_crash.log", 0);
      OutputStreamWriter localOutputStreamWriter = new java/io/OutputStreamWriter;
      localOutputStreamWriter.<init>(localFileOutputStream);
      localOutputStreamWriter.write("this is a test of writing a file");
      localOutputStreamWriter.close();
      return;
    }
    catch (Throwable localThrowable)
    {
      for (;;)
      {
        Log.d("CustomExceptionHandler", "writeFile: exception: " + localThrowable);
      }
    }
  }
  
  public void getCallStack()
  {
    StackTraceElement[] arrayOfStackTraceElement = Thread.currentThread().getStackTrace();
    StringBuilder localStringBuilder = new StringBuilder();
    int j = arrayOfStackTraceElement.length;
    for (int i = 0; i < j; i++)
    {
      localStringBuilder.append(arrayOfStackTraceElement[i].toString());
      localStringBuilder.append("\n");
    }
    Log.d("CustomExceptionHandler", "call-stack: " + localStringBuilder.toString());
  }
  
  public String getDataDirectory()
  {
    return this.context.getExternalFilesDir(null).getAbsolutePath();
  }
  
  public String getLastCrash()
  {
    Object localObject2 = null;
    Object localObject1 = localObject2;
    FileInputStream localFileInputStream;
    StringBuilder localStringBuilder;
    try
    {
      if (!this.context.getFileStreamPath("java_crash.log").exists()) {
        localObject1 = null;
      }
      for (;;)
      {
        return (String)localObject1;
        localObject1 = localObject2;
        localFileInputStream = this.context.openFileInput("java_crash.log");
        localObject1 = localObject2;
        Object localObject3 = new java/io/InputStreamReader;
        localObject1 = localObject2;
        ((InputStreamReader)localObject3).<init>(localFileInputStream);
        localObject1 = localObject2;
        BufferedReader localBufferedReader = new java/io/BufferedReader;
        localObject1 = localObject2;
        localBufferedReader.<init>((Reader)localObject3);
        localObject1 = localObject2;
        localStringBuilder = new java/lang/StringBuilder;
        localObject1 = localObject2;
        localStringBuilder.<init>();
        for (;;)
        {
          localObject1 = localObject2;
          localObject3 = localBufferedReader.readLine();
          if (localObject3 == null) {
            break;
          }
          localObject1 = localObject2;
          localStringBuilder.append((String)localObject3);
        }
      }
    }
    catch (Throwable localThrowable)
    {
      Log.d("CustomExceptionHandler", "getLastCrash: exception: " + localThrowable);
    }
    for (;;)
    {
      localObject1 = localThrowable;
      String str = localStringBuilder.toString();
      localObject1 = str;
      localFileInputStream.close();
      localObject1 = str;
      this.context.deleteFile("java_crash.log");
      localObject1 = str;
    }
  }
  
  public void throwException()
  {
    try
    {
      Log.d("CustomExceptionHandler", "throwException");
      throw new IllegalArgumentException("throwing unhandled exception");
    }
    catch (Throwable localThrowable)
    {
      for (;;) {}
    }
  }
  
  public void throwExceptionOnThread()
  {
    try
    {
      Log.d("CustomExceptionHandler", "throwExceptionOnThread");
      new Thread()
      {
        public void run()
        {
          Log.d("CustomExceptionHandler", "thread running");
          throw new RuntimeException("throwing unhandled exception");
        }
      }.start();
      return;
    }
    catch (Throwable localThrowable)
    {
      for (;;) {}
    }
  }
  
  public void uncaughtException(Thread paramThread, Throwable paramThrowable)
  {
    try
    {
      Object localObject2 = new java/io/StringWriter;
      ((StringWriter)localObject2).<init>();
      Object localObject1 = new java/io/PrintWriter;
      ((PrintWriter)localObject1).<init>((Writer)localObject2);
      paramThrowable.printStackTrace((PrintWriter)localObject1);
      localObject1 = ((StringWriter)localObject2).toString();
      ((String)localObject1).replaceAll("\"", "'");
      localObject2 = paramThrowable.toString();
      Object localObject3 = new java/io/StringWriter;
      ((StringWriter)localObject3).<init>();
      new PrintWriter((Writer)localObject3);
      FileOutputStream localFileOutputStream = this.context.openFileOutput("java_crash.log", 0);
      localObject3 = new java/io/OutputStreamWriter;
      ((OutputStreamWriter)localObject3).<init>(localFileOutputStream);
      ((OutputStreamWriter)localObject3).write("{\"callstack\":\"");
      ((OutputStreamWriter)localObject3).write((String)localObject1);
      ((OutputStreamWriter)localObject3).write("\" , \"type\":\"");
      ((OutputStreamWriter)localObject3).write("exception\" , \"name\":\"");
      ((OutputStreamWriter)localObject3).write((String)localObject2);
      ((OutputStreamWriter)localObject3).write("\"}");
      ((OutputStreamWriter)localObject3).close();
      if (this._defaultEH != null) {
        this._defaultEH.uncaughtException(paramThread, paramThrowable);
      }
      return;
    }
    catch (Throwable paramThread)
    {
      for (;;)
      {
        Log.d("CustomExceptionHandler", "exception: " + paramThread);
      }
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\explodingbarrel\android\CustomExceptionHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */