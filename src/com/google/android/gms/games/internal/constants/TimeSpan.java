package com.google.android.gms.games.internal.constants;

public final class TimeSpan
{
  private TimeSpan()
  {
    throw new AssertionError("Uninstantiable");
  }
  
  public static String dH(int paramInt)
  {
    String str;
    switch (paramInt)
    {
    default: 
      throw new IllegalArgumentException("Unknown time span " + paramInt);
    case 0: 
      str = "DAILY";
    }
    for (;;)
    {
      return str;
      str = "WEEKLY";
      continue;
      str = "ALL_TIME";
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\games\internal\constants\TimeSpan.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */