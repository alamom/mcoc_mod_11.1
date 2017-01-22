package com.google.android.gms.internal;

import java.util.PriorityQueue;

public class as
{
  static long a(int paramInt1, int paramInt2, long paramLong1, long paramLong2, long paramLong3)
  {
    return ((paramLong1 + 1073807359L - (paramInt1 + 2147483647L) % 1073807359L * paramLong2 % 1073807359L) % 1073807359L * paramLong3 % 1073807359L + (paramInt2 + 2147483647L) % 1073807359L) % 1073807359L;
  }
  
  static long a(long paramLong, int paramInt)
  {
    long l;
    if (paramInt == 0) {
      l = 1L;
    }
    for (;;)
    {
      return l;
      l = paramLong;
      if (paramInt != 1) {
        if (paramInt % 2 == 0) {
          l = a(paramLong * paramLong % 1073807359L, paramInt / 2) % 1073807359L;
        } else {
          l = a(paramLong * paramLong % 1073807359L, paramInt / 2) % 1073807359L * paramLong % 1073807359L;
        }
      }
    }
  }
  
  static String a(String[] paramArrayOfString, int paramInt1, int paramInt2)
  {
    if (paramArrayOfString.length < paramInt1 + paramInt2) {
      gs.T("Unable to construct shingle");
    }
    StringBuffer localStringBuffer;
    for (paramArrayOfString = "";; paramArrayOfString = localStringBuffer.toString())
    {
      return paramArrayOfString;
      localStringBuffer = new StringBuffer();
      for (int i = paramInt1; i < paramInt1 + paramInt2 - 1; i++)
      {
        localStringBuffer.append(paramArrayOfString[i]);
        localStringBuffer.append(' ');
      }
      localStringBuffer.append(paramArrayOfString[(paramInt1 + paramInt2 - 1)]);
    }
  }
  
  private static void a(int paramInt1, long paramLong, int paramInt2, String[] paramArrayOfString, int paramInt3, PriorityQueue<a> paramPriorityQueue)
  {
    paramPriorityQueue.add(new a(paramLong, a(paramArrayOfString, paramInt2, paramInt3)));
    if (paramPriorityQueue.size() > paramInt1) {
      paramPriorityQueue.poll();
    }
  }
  
  public static void a(String[] paramArrayOfString, int paramInt1, int paramInt2, PriorityQueue<a> paramPriorityQueue)
  {
    long l1 = b(paramArrayOfString, 0, paramInt2);
    a(paramInt1, l1, 0, paramArrayOfString, paramInt2, paramPriorityQueue);
    long l2 = a(16785407L, paramInt2 - 1);
    for (int i = 1; i < paramArrayOfString.length - paramInt2 + 1; i++)
    {
      l1 = a(aq.o(paramArrayOfString[(i - 1)]), aq.o(paramArrayOfString[(i + paramInt2 - 1)]), l1, l2, 16785407L);
      a(paramInt1, l1, i, paramArrayOfString, paramInt2, paramPriorityQueue);
    }
  }
  
  private static long b(String[] paramArrayOfString, int paramInt1, int paramInt2)
  {
    long l = (aq.o(paramArrayOfString[paramInt1]) + 2147483647L) % 1073807359L;
    for (int i = paramInt1 + 1; i < paramInt1 + paramInt2; i++) {
      l = (l * 16785407L % 1073807359L + (aq.o(paramArrayOfString[i]) + 2147483647L) % 1073807359L) % 1073807359L;
    }
    return l;
  }
  
  public static class a
  {
    final String nQ;
    final long value;
    
    a(long paramLong, String paramString)
    {
      this.value = paramLong;
      this.nQ = paramString;
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\internal\as.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */