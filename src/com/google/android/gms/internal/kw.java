package com.google.android.gms.internal;

import com.google.android.gms.fitness.data.DataSource;

public class kw
{
  private static final ThreadLocal<String> Ty = new ThreadLocal();
  
  public static String bt(String paramString)
  {
    return s(paramString, (String)Ty.get());
  }
  
  public static DataSource c(DataSource paramDataSource)
  {
    DataSource localDataSource;
    if (!paramDataSource.iO()) {
      localDataSource = paramDataSource;
    }
    for (;;)
    {
      return localDataSource;
      String str = (String)Ty.get();
      localDataSource = paramDataSource;
      if (!jc())
      {
        localDataSource = paramDataSource;
        if (!str.equals(paramDataSource.getAppPackageName())) {
          localDataSource = paramDataSource.iP();
        }
      }
    }
  }
  
  public static boolean jc()
  {
    String str = (String)Ty.get();
    if ((str == null) || (str.startsWith("com.google"))) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  private static String s(String paramString1, String paramString2)
  {
    Object localObject = paramString1;
    if (paramString1 != null) {
      if (paramString2 != null) {
        break label14;
      }
    }
    for (localObject = paramString1;; localObject = Integer.toHexString(kb.a((byte[])localObject, 0, localObject.length, 0)))
    {
      return (String)localObject;
      label14:
      localObject = new byte[paramString1.length() + paramString2.length()];
      System.arraycopy(paramString1.getBytes(), 0, localObject, 0, paramString1.length());
      System.arraycopy(paramString2.getBytes(), 0, localObject, paramString1.length(), paramString2.length());
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\internal\kw.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */