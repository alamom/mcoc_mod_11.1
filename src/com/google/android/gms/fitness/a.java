package com.google.android.gms.fitness;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public final class a
{
  public static String bq(String paramString)
  {
    String str;
    if (paramString.equals("https://www.googleapis.com/auth/fitness.activity.read")) {
      str = "https://www.googleapis.com/auth/fitness.activity.write";
    }
    for (;;)
    {
      return str;
      if (paramString.equals("https://www.googleapis.com/auth/fitness.location.read"))
      {
        str = "https://www.googleapis.com/auth/fitness.location.write";
      }
      else
      {
        str = paramString;
        if (paramString.equals("https://www.googleapis.com/auth/fitness.body.read")) {
          str = "https://www.googleapis.com/auth/fitness.body.write";
        }
      }
    }
  }
  
  public static String[] d(List<String> paramList)
  {
    HashSet localHashSet = new HashSet(paramList.size());
    Iterator localIterator = paramList.iterator();
    while (localIterator.hasNext())
    {
      String str2 = (String)localIterator.next();
      String str1 = bq(str2);
      if ((str1.equals(str2)) || (!paramList.contains(str1))) {
        localHashSet.add(str2);
      }
    }
    return (String[])localHashSet.toArray(new String[localHashSet.size()]);
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\fitness\a.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */