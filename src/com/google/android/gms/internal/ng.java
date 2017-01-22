package com.google.android.gms.internal;

import android.app.PendingIntent;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.util.Log;

public class ng
{
  private final no akF;
  private nm akG;
  
  public ng(Context paramContext, int paramInt, String paramString1, String paramString2, a parama, boolean paramBoolean)
  {
    String str = paramContext.getPackageName();
    try
    {
      i = paramContext.getPackageManager().getPackageInfo(str, 0).versionCode;
      this.akG = new nm(str, i, paramInt, paramString1, paramString2, paramBoolean);
      this.akF = new no(paramContext, new nl(parama));
      return;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      for (;;)
      {
        Log.wtf("PlayLogger", "This can't happen.");
        int i = j;
      }
    }
  }
  
  public void a(long paramLong, String paramString, byte[] paramArrayOfByte, String... paramVarArgs)
  {
    this.akF.b(this.akG, new ni(paramLong, paramString, paramArrayOfByte, paramVarArgs));
  }
  
  public void b(String paramString, byte[] paramArrayOfByte, String... paramVarArgs)
  {
    a(System.currentTimeMillis(), paramString, paramArrayOfByte, paramVarArgs);
  }
  
  public void start()
  {
    this.akF.start();
  }
  
  public void stop()
  {
    this.akF.stop();
  }
  
  public static abstract interface a
  {
    public abstract void b(PendingIntent paramPendingIntent);
    
    public abstract void mU();
    
    public abstract void mV();
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\internal\ng.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */