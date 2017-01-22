package android.support.v4.util;

import java.io.PrintWriter;

public class TimeUtils
{
  public static final int HUNDRED_DAY_FIELD_LEN = 19;
  private static final int SECONDS_PER_DAY = 86400;
  private static final int SECONDS_PER_HOUR = 3600;
  private static final int SECONDS_PER_MINUTE = 60;
  private static char[] sFormatStr = new char[24];
  private static final Object sFormatSync = new Object();
  
  private static int accumField(int paramInt1, int paramInt2, boolean paramBoolean, int paramInt3)
  {
    if ((paramInt1 > 99) || ((paramBoolean) && (paramInt3 >= 3))) {
      paramInt1 = paramInt2 + 3;
    }
    for (;;)
    {
      return paramInt1;
      if ((paramInt1 > 9) || ((paramBoolean) && (paramInt3 >= 2))) {
        paramInt1 = paramInt2 + 2;
      } else if ((paramBoolean) || (paramInt1 > 0)) {
        paramInt1 = paramInt2 + 1;
      } else {
        paramInt1 = 0;
      }
    }
  }
  
  public static void formatDuration(long paramLong1, long paramLong2, PrintWriter paramPrintWriter)
  {
    if (paramLong1 == 0L) {
      paramPrintWriter.print("--");
    }
    for (;;)
    {
      return;
      formatDuration(paramLong1 - paramLong2, paramPrintWriter, 0);
    }
  }
  
  public static void formatDuration(long paramLong, PrintWriter paramPrintWriter)
  {
    formatDuration(paramLong, paramPrintWriter, 0);
  }
  
  public static void formatDuration(long paramLong, PrintWriter paramPrintWriter, int paramInt)
  {
    synchronized (sFormatSync)
    {
      paramInt = formatDurationLocked(paramLong, paramInt);
      String str = new java/lang/String;
      str.<init>(sFormatStr, 0, paramInt);
      paramPrintWriter.print(str);
      return;
    }
  }
  
  public static void formatDuration(long paramLong, StringBuilder paramStringBuilder)
  {
    synchronized (sFormatSync)
    {
      int i = formatDurationLocked(paramLong, 0);
      paramStringBuilder.append(sFormatStr, 0, i);
      return;
    }
  }
  
  private static int formatDurationLocked(long paramLong, int paramInt)
  {
    if (sFormatStr.length < paramInt) {
      sFormatStr = new char[paramInt];
    }
    char[] arrayOfChar = sFormatStr;
    if (paramLong == 0L)
    {
      while (paramInt - 1 < 0) {
        arrayOfChar[0] = ' ';
      }
      arrayOfChar[0] = '0';
      paramInt = 1;
      return paramInt;
    }
    int i;
    int i5;
    int m;
    int i1;
    int n;
    int k;
    int i3;
    int i4;
    boolean bool;
    if (paramLong > 0L)
    {
      i = 43;
      i5 = (int)(paramLong % 1000L);
      j = (int)Math.floor(paramLong / 1000L);
      m = 0;
      i1 = 0;
      n = 0;
      k = j;
      if (j > 86400)
      {
        m = j / 86400;
        k = j - 86400 * m;
      }
      j = k;
      if (k > 3600)
      {
        i1 = k / 3600;
        j = k - i1 * 3600;
      }
      k = j;
      if (j > 60)
      {
        n = j / 60;
        k = j - n * 60;
      }
      i3 = 0;
      i4 = 0;
      if (paramInt == 0) {
        break label348;
      }
      j = accumField(m, 1, false, 0);
      if (j <= 0) {
        break label324;
      }
      bool = true;
      label203:
      j += accumField(i1, 1, bool, 2);
      if (j <= 0) {
        break label330;
      }
      bool = true;
      label225:
      j += accumField(n, 1, bool, 2);
      if (j <= 0) {
        break label336;
      }
      bool = true;
      label247:
      i2 = j + accumField(k, 1, bool, 2);
      if (i2 <= 0) {
        break label342;
      }
    }
    label324:
    label330:
    label336:
    label342:
    for (int j = 3;; j = 0)
    {
      i2 += accumField(i5, 2, true, j) + 1;
      j = i4;
      for (;;)
      {
        i3 = j;
        if (i2 >= paramInt) {
          break;
        }
        arrayOfChar[j] = ' ';
        j++;
        i2++;
      }
      i = 45;
      paramLong = -paramLong;
      break;
      bool = false;
      break label203;
      bool = false;
      break label225;
      bool = false;
      break label247;
    }
    label348:
    arrayOfChar[i3] = i;
    int i2 = i3 + 1;
    if (paramInt != 0)
    {
      paramInt = 1;
      label366:
      m = printField(arrayOfChar, m, 'd', i2, false, 0);
      if (m == i2) {
        break label527;
      }
      bool = true;
      label391:
      if (paramInt == 0) {
        break label533;
      }
      j = 2;
      label398:
      m = printField(arrayOfChar, i1, 'h', m, bool, j);
      if (m == i2) {
        break label539;
      }
      bool = true;
      label425:
      if (paramInt == 0) {
        break label545;
      }
      j = 2;
      label432:
      m = printField(arrayOfChar, n, 'm', m, bool, j);
      if (m == i2) {
        break label551;
      }
      bool = true;
      label459:
      if (paramInt == 0) {
        break label557;
      }
      j = 2;
      label466:
      j = printField(arrayOfChar, k, 's', m, bool, j);
      if ((paramInt == 0) || (j == i2)) {
        break label563;
      }
    }
    label527:
    label533:
    label539:
    label545:
    label551:
    label557:
    label563:
    for (paramInt = 3;; paramInt = 0)
    {
      paramInt = printField(arrayOfChar, i5, 'm', j, true, paramInt);
      arrayOfChar[paramInt] = 's';
      paramInt++;
      break;
      paramInt = 0;
      break label366;
      bool = false;
      break label391;
      j = 0;
      break label398;
      bool = false;
      break label425;
      j = 0;
      break label432;
      bool = false;
      break label459;
      j = 0;
      break label466;
    }
  }
  
  private static int printField(char[] paramArrayOfChar, int paramInt1, char paramChar, int paramInt2, boolean paramBoolean, int paramInt3)
  {
    int i;
    if (!paramBoolean)
    {
      i = paramInt2;
      if (paramInt1 <= 0) {}
    }
    else
    {
      int j;
      if ((!paramBoolean) || (paramInt3 < 3))
      {
        i = paramInt1;
        j = paramInt2;
        if (paramInt1 <= 99) {}
      }
      else
      {
        i = paramInt1 / 100;
        paramArrayOfChar[paramInt2] = ((char)(i + 48));
        j = paramInt2 + 1;
        i = paramInt1 - i * 100;
      }
      if (((!paramBoolean) || (paramInt3 < 2)) && (i <= 9))
      {
        paramInt3 = i;
        paramInt1 = j;
        if (paramInt2 == j) {}
      }
      else
      {
        paramInt2 = i / 10;
        paramArrayOfChar[j] = ((char)(paramInt2 + 48));
        paramInt1 = j + 1;
        paramInt3 = i - paramInt2 * 10;
      }
      paramArrayOfChar[paramInt1] = ((char)(paramInt3 + 48));
      paramInt1++;
      paramArrayOfChar[paramInt1] = paramChar;
      i = paramInt1 + 1;
    }
    return i;
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\android\support\v4\util\TimeUtils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */