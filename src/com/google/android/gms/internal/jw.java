package com.google.android.gms.internal;

import android.os.SystemClock;

public final class jw
  implements ju
{
  private static jw MS;
  
  public static ju hA()
  {
    try
    {
      if (MS == null)
      {
        localjw = new com/google/android/gms/internal/jw;
        localjw.<init>();
        MS = localjw;
      }
      jw localjw = MS;
      return localjw;
    }
    finally {}
  }
  
  public long currentTimeMillis()
  {
    return System.currentTimeMillis();
  }
  
  public long elapsedRealtime()
  {
    return SystemClock.elapsedRealtime();
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\internal\jw.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */