package com.google.android.gms.analytics;

public final class n
{
  public static String A(int paramInt)
  {
    return d("&promo", paramInt);
  }
  
  public static String B(int paramInt)
  {
    return d("pi", paramInt);
  }
  
  public static String C(int paramInt)
  {
    return d("&il", paramInt);
  }
  
  public static String D(int paramInt)
  {
    return d("cd", paramInt);
  }
  
  public static String E(int paramInt)
  {
    return d("cm", paramInt);
  }
  
  private static String d(String paramString, int paramInt)
  {
    if (paramInt < 1) {
      z.T("index out of range for " + paramString + " (" + paramInt + ")");
    }
    for (paramString = "";; paramString = paramString + paramInt) {
      return paramString;
    }
  }
  
  static String x(int paramInt)
  {
    return d("&cd", paramInt);
  }
  
  static String y(int paramInt)
  {
    return d("&cm", paramInt);
  }
  
  public static String z(int paramInt)
  {
    return d("&pr", paramInt);
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\analytics\n.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */