package com.google.android.gms.tagmanager;

import android.content.Context;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Logger;
import com.google.android.gms.analytics.Tracker;

class df
{
  private GoogleAnalytics arQ;
  private Context mContext;
  private Tracker xY;
  
  df(Context paramContext)
  {
    this.mContext = paramContext;
  }
  
  private void cV(String paramString)
  {
    try
    {
      if (this.arQ == null)
      {
        this.arQ = GoogleAnalytics.getInstance(this.mContext);
        GoogleAnalytics localGoogleAnalytics = this.arQ;
        a locala = new com/google/android/gms/tagmanager/df$a;
        locala.<init>();
        localGoogleAnalytics.setLogger(locala);
        this.xY = this.arQ.newTracker(paramString);
      }
      return;
    }
    finally
    {
      paramString = finally;
      throw paramString;
    }
  }
  
  public Tracker cU(String paramString)
  {
    cV(paramString);
    return this.xY;
  }
  
  static class a
    implements Logger
  {
    private static int fn(int paramInt)
    {
      int j = 3;
      int i = j;
      switch (paramInt)
      {
      default: 
        i = j;
      }
      for (;;)
      {
        return i;
        i = 2;
        continue;
        i = 1;
        continue;
        i = 0;
      }
    }
    
    public void error(Exception paramException)
    {
      bh.b("", paramException);
    }
    
    public void error(String paramString)
    {
      bh.T(paramString);
    }
    
    public int getLogLevel()
    {
      return fn(bh.getLogLevel());
    }
    
    public void info(String paramString)
    {
      bh.U(paramString);
    }
    
    public void setLogLevel(int paramInt)
    {
      bh.W("GA uses GTM logger. Please use TagManager.setLogLevel(int) instead.");
    }
    
    public void verbose(String paramString)
    {
      bh.V(paramString);
    }
    
    public void warn(String paramString)
    {
      bh.W(paramString);
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\tagmanager\df.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */