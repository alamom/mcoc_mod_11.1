package com.google.android.gms.analytics;

import android.content.Context;
import java.util.Map;

class ah
  extends j<ai>
{
  public ah(Context paramContext)
  {
    super(paramContext, new a());
  }
  
  private static class a
    implements j.a<ai>
  {
    private final ai BB = new ai();
    
    public void c(String paramString, int paramInt)
    {
      if ("ga_sessionTimeout".equals(paramString)) {
        this.BB.BE = paramInt;
      }
      for (;;)
      {
        return;
        z.W("int configuration name not recognized:  " + paramString);
      }
    }
    
    public void d(String paramString, boolean paramBoolean)
    {
      int j = 1;
      int k = 1;
      int i = 1;
      if ("ga_autoActivityTracking".equals(paramString))
      {
        paramString = this.BB;
        if (paramBoolean) {
          paramString.BF = i;
        }
      }
      for (;;)
      {
        return;
        i = 0;
        break;
        if ("ga_anonymizeIp".equals(paramString))
        {
          paramString = this.BB;
          if (paramBoolean) {}
          for (i = j;; i = 0)
          {
            paramString.BG = i;
            break;
          }
        }
        if ("ga_reportUncaughtExceptions".equals(paramString))
        {
          paramString = this.BB;
          if (paramBoolean) {}
          for (i = k;; i = 0)
          {
            paramString.BH = i;
            break;
          }
        }
        z.W("bool configuration name not recognized:  " + paramString);
      }
    }
    
    public ai eY()
    {
      return this.BB;
    }
    
    public void f(String paramString1, String paramString2)
    {
      this.BB.BI.put(paramString1, paramString2);
    }
    
    public void g(String paramString1, String paramString2)
    {
      if ("ga_trackingId".equals(paramString1)) {
        this.BB.BC = paramString2;
      }
      for (;;)
      {
        return;
        if ("ga_sampleFrequency".equals(paramString1)) {
          try
          {
            this.BB.BD = Double.parseDouble(paramString2);
          }
          catch (NumberFormatException paramString1)
          {
            z.T("Error parsing ga_sampleFrequency value: " + paramString2);
          }
        } else {
          z.W("string configuration name not recognized:  " + paramString1);
        }
      }
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\analytics\ah.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */