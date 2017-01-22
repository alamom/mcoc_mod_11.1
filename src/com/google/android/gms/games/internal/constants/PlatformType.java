package com.google.android.gms.games.internal.constants;

public final class PlatformType
{
  public static String dH(int paramInt)
  {
    String str;
    switch (paramInt)
    {
    default: 
      throw new IllegalArgumentException("Unknown platform type: " + paramInt);
    case 0: 
      str = "ANDROID";
    }
    for (;;)
    {
      return str;
      str = "IOS";
      continue;
      str = "WEB_APP";
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\games\internal\constants\PlatformType.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */