package com.google.android.gms.games.internal.constants;

import com.google.android.gms.games.internal.GamesLog;

public final class RequestType
{
  public static String dH(int paramInt)
  {
    String str;
    switch (paramInt)
    {
    default: 
      GamesLog.q("RequestType", "Unknown request type: " + paramInt);
      str = "UNKNOWN_TYPE";
    }
    for (;;)
    {
      return str;
      str = "GIFT";
      continue;
      str = "WISH";
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\games\internal\constants\RequestType.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */