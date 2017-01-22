package com.google.android.vending.expansion.downloader.impl;

import android.text.format.Time;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class HttpDateTime
{
  private static final Pattern HTTP_DATE_ANSIC_PATTERN = Pattern.compile("[ ]([A-Za-z]{3,9})[ ]+([0-9]{1,2})[ ]([0-9]{1,2}:[0-9][0-9]:[0-9][0-9])[ ]([0-9]{2,4})");
  private static final String HTTP_DATE_ANSIC_REGEXP = "[ ]([A-Za-z]{3,9})[ ]+([0-9]{1,2})[ ]([0-9]{1,2}:[0-9][0-9]:[0-9][0-9])[ ]([0-9]{2,4})";
  private static final Pattern HTTP_DATE_RFC_PATTERN = Pattern.compile("([0-9]{1,2})[- ]([A-Za-z]{3,9})[- ]([0-9]{2,4})[ ]([0-9]{1,2}:[0-9][0-9]:[0-9][0-9])");
  private static final String HTTP_DATE_RFC_REGEXP = "([0-9]{1,2})[- ]([A-Za-z]{3,9})[- ]([0-9]{2,4})[ ]([0-9]{1,2}:[0-9][0-9]:[0-9][0-9])";
  
  private static int getDate(String paramString)
  {
    if (paramString.length() == 2) {}
    for (int i = (paramString.charAt(0) - '0') * 10 + (paramString.charAt(1) - '0');; i = paramString.charAt(0) - '0') {
      return i;
    }
  }
  
  private static int getMonth(String paramString)
  {
    int i = 0;
    switch (Character.toLowerCase(paramString.charAt(0)) + Character.toLowerCase(paramString.charAt(1)) + Character.toLowerCase(paramString.charAt(2)) - 291)
    {
    default: 
      throw new IllegalArgumentException();
    case 10: 
      i = 1;
    }
    for (;;)
    {
      return i;
      i = 2;
      continue;
      i = 3;
      continue;
      i = 4;
      continue;
      i = 5;
      continue;
      i = 6;
      continue;
      i = 7;
      continue;
      i = 8;
      continue;
      i = 9;
      continue;
      i = 10;
      continue;
      i = 11;
    }
  }
  
  private static TimeOfDay getTime(String paramString)
  {
    int k = 0 + 1;
    int i = paramString.charAt(0) - '0';
    int j;
    if (paramString.charAt(k) != ':')
    {
      j = k + 1;
      i = i * 10 + (paramString.charAt(k) - '0');
    }
    for (;;)
    {
      j++;
      int m = j + 1;
      j = paramString.charAt(j);
      k = paramString.charAt(m);
      int n = m + 1 + 1;
      m = n + 1;
      return new TimeOfDay(i, (j - 48) * 10 + (k - 48), (paramString.charAt(n) - '0') * 10 + (paramString.charAt(m) - '0'));
      j = k;
    }
  }
  
  private static int getYear(String paramString)
  {
    int i;
    if (paramString.length() == 2)
    {
      i = (paramString.charAt(0) - '0') * 10 + (paramString.charAt(1) - '0');
      if (i >= 70) {
        i += 1900;
      }
    }
    for (;;)
    {
      return i;
      i += 2000;
      continue;
      if (paramString.length() == 3) {
        i = (paramString.charAt(0) - '0') * 100 + (paramString.charAt(1) - '0') * 10 + (paramString.charAt(2) - '0') + 1900;
      } else if (paramString.length() == 4) {
        i = (paramString.charAt(0) - '0') * 1000 + (paramString.charAt(1) - '0') * 100 + (paramString.charAt(2) - '0') * 10 + (paramString.charAt(3) - '0');
      } else {
        i = 1970;
      }
    }
  }
  
  public static long parse(String paramString)
    throws IllegalArgumentException
  {
    Object localObject = HTTP_DATE_RFC_PATTERN.matcher(paramString);
    int k;
    int j;
    int i;
    if (((Matcher)localObject).find())
    {
      k = getDate(((Matcher)localObject).group(1));
      j = getMonth(((Matcher)localObject).group(2));
      i = getYear(((Matcher)localObject).group(3));
      paramString = getTime(((Matcher)localObject).group(4));
    }
    for (;;)
    {
      int m = k;
      k = j;
      j = i;
      if (i >= 2038)
      {
        j = 2038;
        k = 0;
        m = 1;
      }
      localObject = new Time("UTC");
      ((Time)localObject).set(paramString.second, paramString.minute, paramString.hour, m, k, j);
      return ((Time)localObject).toMillis(false);
      localObject = HTTP_DATE_ANSIC_PATTERN.matcher(paramString);
      if (!((Matcher)localObject).find()) {
        break;
      }
      j = getMonth(((Matcher)localObject).group(1));
      k = getDate(((Matcher)localObject).group(2));
      paramString = getTime(((Matcher)localObject).group(3));
      i = getYear(((Matcher)localObject).group(4));
    }
    throw new IllegalArgumentException();
  }
  
  private static class TimeOfDay
  {
    int hour;
    int minute;
    int second;
    
    TimeOfDay(int paramInt1, int paramInt2, int paramInt3)
    {
      this.hour = paramInt1;
      this.minute = paramInt2;
      this.second = paramInt3;
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\vending\expansion\downloader\impl\HttpDateTime.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */