package com.explodingbarrel.android;

import android.util.Log;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

class JarExtraction
{
  private static final String TAG = "JarExtraction";
  byte[] Buffer = null;
  int BytesWritten = 0;
  String FilePath = null;
  InputStream JIS = null;
  String JarPath = null;
  String OutputPath = null;
  int StepCount = 0;
  FileOutputStream Writer = null;
  int kBufferSize = 1024;
  
  public JarExtraction(String paramString1, String paramString2, String paramString3)
  {
    this.JarPath = paramString1;
    this.FilePath = paramString2;
    this.OutputPath = paramString3;
    Log.d("JarExtraction", "JarExtraction jarPath = " + this.JarPath + " filePath = " + this.FilePath + " outputPath = " + this.OutputPath);
  }
  
  public void CompleteExtractFromJar()
  {
    try
    {
      StringBuilder localStringBuilder = new java/lang/StringBuilder;
      localStringBuilder.<init>();
      Log.d("JarExtraction", "JarExtraction ExtractionComplete " + this.OutputPath + " Bytes Written:" + this.BytesWritten);
      this.Writer.close();
      this.JIS.close();
      return;
    }
    catch (Exception localException)
    {
      for (;;) {}
    }
  }
  
  public boolean StartExtractFromJar()
  {
    bool2 = false;
    try
    {
      Object localObject = new java/util/jar/JarFile;
      ((JarFile)localObject).<init>(this.JarPath);
      bool1 = bool2;
      if (localObject != null)
      {
        JarEntry localJarEntry = ((JarFile)localObject).getJarEntry(this.FilePath);
        bool1 = bool2;
        if (localJarEntry != null)
        {
          StringBuilder localStringBuilder = new java/lang/StringBuilder;
          localStringBuilder.<init>();
          Log.d("JarExtraction", "JarExtraction Extracting " + this.FilePath + " from " + this.JarPath + " to " + this.OutputPath);
          this.JIS = ((JarFile)localObject).getInputStream(localJarEntry);
          localObject = new java/io/FileOutputStream;
          ((FileOutputStream)localObject).<init>(this.OutputPath);
          this.Writer = ((FileOutputStream)localObject);
          this.Buffer = new byte[this.kBufferSize];
          this.StepCount = 0;
          this.BytesWritten = 0;
          bool1 = true;
        }
      }
    }
    catch (Exception localException)
    {
      for (;;)
      {
        boolean bool1 = bool2;
      }
    }
    return bool1;
  }
  
  public boolean StepExtractFromJar(int paramInt)
  {
    boolean bool1 = false;
    if (this.StepCount % 1000 == 0) {
      Log.d("JarExtraction", "JarExtraction StepExtractFromJar Steps:" + this.StepCount + " Bytes Written:" + this.BytesWritten);
    }
    this.StepCount += 1;
    for (;;)
    {
      boolean bool2 = bool1;
      if (paramInt > 0)
      {
        bool2 = bool1;
        if (!bool1) {
          try
          {
            int i = this.JIS.read(this.Buffer, 0, this.kBufferSize);
            if (i == -1) {
              break label137;
            }
            paramInt -= i;
            this.Writer.write(this.Buffer, 0, i);
            this.BytesWritten += i;
          }
          catch (Exception localException)
          {
            bool2 = true;
          }
        }
      }
      return bool2;
      label137:
      bool1 = true;
    }
  }
  
  public boolean SyncExtractFromJar()
  {
    boolean bool = StartExtractFromJar();
    if (bool == true)
    {
      while (!StepExtractFromJar(this.kBufferSize)) {}
      CompleteExtractFromJar();
    }
    return bool;
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\explodingbarrel\android\JarExtraction.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */