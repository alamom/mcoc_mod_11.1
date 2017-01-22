package com.google.android.gms.common.internal.safeparcel;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class a
{
  public static String[] A(Parcel paramParcel, int paramInt)
  {
    paramInt = a(paramParcel, paramInt);
    int i = paramParcel.dataPosition();
    if (paramInt == 0) {}
    String[] arrayOfString;
    for (paramParcel = null;; paramParcel = arrayOfString)
    {
      return paramParcel;
      arrayOfString = paramParcel.createStringArray();
      paramParcel.setDataPosition(paramInt + i);
    }
  }
  
  public static int B(Parcel paramParcel)
  {
    return paramParcel.readInt();
  }
  
  public static ArrayList<Integer> B(Parcel paramParcel, int paramInt)
  {
    int i = a(paramParcel, paramInt);
    int j = paramParcel.dataPosition();
    if (i == 0) {}
    ArrayList localArrayList;
    for (paramParcel = null;; paramParcel = localArrayList)
    {
      return paramParcel;
      localArrayList = new ArrayList();
      int k = paramParcel.readInt();
      for (paramInt = 0; paramInt < k; paramInt++) {
        localArrayList.add(Integer.valueOf(paramParcel.readInt()));
      }
      paramParcel.setDataPosition(j + i);
    }
  }
  
  public static int C(Parcel paramParcel)
  {
    int j = B(paramParcel);
    int k = a(paramParcel, j);
    int i = paramParcel.dataPosition();
    if (aD(j) != 20293) {
      throw new a("Expected object header. Got 0x" + Integer.toHexString(j), paramParcel);
    }
    j = i + k;
    if ((j < i) || (j > paramParcel.dataSize())) {
      throw new a("Size read is invalid start=" + i + " end=" + j, paramParcel);
    }
    return j;
  }
  
  public static ArrayList<String> C(Parcel paramParcel, int paramInt)
  {
    int i = a(paramParcel, paramInt);
    paramInt = paramParcel.dataPosition();
    if (i == 0) {}
    ArrayList localArrayList;
    for (paramParcel = null;; paramParcel = localArrayList)
    {
      return paramParcel;
      localArrayList = paramParcel.createStringArrayList();
      paramParcel.setDataPosition(i + paramInt);
    }
  }
  
  public static Parcel D(Parcel paramParcel, int paramInt)
  {
    paramInt = a(paramParcel, paramInt);
    int i = paramParcel.dataPosition();
    if (paramInt == 0) {}
    Parcel localParcel;
    for (paramParcel = null;; paramParcel = localParcel)
    {
      return paramParcel;
      localParcel = Parcel.obtain();
      localParcel.appendFrom(paramParcel, i, paramInt);
      paramParcel.setDataPosition(paramInt + i);
    }
  }
  
  public static Parcel[] E(Parcel paramParcel, int paramInt)
  {
    Parcel[] arrayOfParcel = null;
    int i = a(paramParcel, paramInt);
    int j = paramParcel.dataPosition();
    if (i == 0) {}
    for (paramParcel = arrayOfParcel;; paramParcel = arrayOfParcel)
    {
      return paramParcel;
      int k = paramParcel.readInt();
      arrayOfParcel = new Parcel[k];
      paramInt = 0;
      if (paramInt < k)
      {
        int n = paramParcel.readInt();
        if (n != 0)
        {
          int m = paramParcel.dataPosition();
          Parcel localParcel = Parcel.obtain();
          localParcel.appendFrom(paramParcel, m, n);
          arrayOfParcel[paramInt] = localParcel;
          paramParcel.setDataPosition(n + m);
        }
        for (;;)
        {
          paramInt++;
          break;
          arrayOfParcel[paramInt] = null;
        }
      }
      paramParcel.setDataPosition(j + i);
    }
  }
  
  public static int a(Parcel paramParcel, int paramInt)
  {
    if ((paramInt & 0xFFFF0000) != -65536) {}
    for (paramInt = paramInt >> 16 & 0xFFFF;; paramInt = paramParcel.readInt()) {
      return paramInt;
    }
  }
  
  public static <T extends Parcelable> T a(Parcel paramParcel, int paramInt, Parcelable.Creator<T> paramCreator)
  {
    int i = a(paramParcel, paramInt);
    paramInt = paramParcel.dataPosition();
    if (i == 0) {}
    for (paramParcel = null;; paramParcel = paramCreator)
    {
      return paramParcel;
      paramCreator = (Parcelable)paramCreator.createFromParcel(paramParcel);
      paramParcel.setDataPosition(i + paramInt);
    }
  }
  
  private static void a(Parcel paramParcel, int paramInt1, int paramInt2)
  {
    paramInt1 = a(paramParcel, paramInt1);
    if (paramInt1 != paramInt2) {
      throw new a("Expected size " + paramInt2 + " got " + paramInt1 + " (0x" + Integer.toHexString(paramInt1) + ")", paramParcel);
    }
  }
  
  private static void a(Parcel paramParcel, int paramInt1, int paramInt2, int paramInt3)
  {
    if (paramInt2 != paramInt3) {
      throw new a("Expected size " + paramInt3 + " got " + paramInt2 + " (0x" + Integer.toHexString(paramInt2) + ")", paramParcel);
    }
  }
  
  public static void a(Parcel paramParcel, int paramInt, List paramList, ClassLoader paramClassLoader)
  {
    int i = a(paramParcel, paramInt);
    paramInt = paramParcel.dataPosition();
    if (i == 0) {}
    for (;;)
    {
      return;
      paramParcel.readList(paramList, paramClassLoader);
      paramParcel.setDataPosition(i + paramInt);
    }
  }
  
  public static int aD(int paramInt)
  {
    return 0xFFFF & paramInt;
  }
  
  public static void b(Parcel paramParcel, int paramInt)
  {
    paramParcel.setDataPosition(a(paramParcel, paramInt) + paramParcel.dataPosition());
  }
  
  public static <T> T[] b(Parcel paramParcel, int paramInt, Parcelable.Creator<T> paramCreator)
  {
    int i = a(paramParcel, paramInt);
    paramInt = paramParcel.dataPosition();
    if (i == 0) {}
    for (paramParcel = null;; paramParcel = paramCreator)
    {
      return paramParcel;
      paramCreator = paramParcel.createTypedArray(paramCreator);
      paramParcel.setDataPosition(i + paramInt);
    }
  }
  
  public static <T> ArrayList<T> c(Parcel paramParcel, int paramInt, Parcelable.Creator<T> paramCreator)
  {
    paramInt = a(paramParcel, paramInt);
    int i = paramParcel.dataPosition();
    if (paramInt == 0) {}
    for (paramParcel = null;; paramParcel = paramCreator)
    {
      return paramParcel;
      paramCreator = paramParcel.createTypedArrayList(paramCreator);
      paramParcel.setDataPosition(paramInt + i);
    }
  }
  
  public static boolean c(Parcel paramParcel, int paramInt)
  {
    a(paramParcel, paramInt, 4);
    if (paramParcel.readInt() != 0) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public static Boolean d(Parcel paramParcel, int paramInt)
  {
    int i = a(paramParcel, paramInt);
    if (i == 0)
    {
      paramParcel = null;
      return paramParcel;
    }
    a(paramParcel, paramInt, i, 4);
    if (paramParcel.readInt() != 0) {}
    for (boolean bool = true;; bool = false)
    {
      paramParcel = Boolean.valueOf(bool);
      break;
    }
  }
  
  public static byte e(Parcel paramParcel, int paramInt)
  {
    a(paramParcel, paramInt, 4);
    return (byte)paramParcel.readInt();
  }
  
  public static short f(Parcel paramParcel, int paramInt)
  {
    a(paramParcel, paramInt, 4);
    return (short)paramParcel.readInt();
  }
  
  public static int g(Parcel paramParcel, int paramInt)
  {
    a(paramParcel, paramInt, 4);
    return paramParcel.readInt();
  }
  
  public static Integer h(Parcel paramParcel, int paramInt)
  {
    int i = a(paramParcel, paramInt);
    if (i == 0) {}
    for (paramParcel = null;; paramParcel = Integer.valueOf(paramParcel.readInt()))
    {
      return paramParcel;
      a(paramParcel, paramInt, i, 4);
    }
  }
  
  public static long i(Parcel paramParcel, int paramInt)
  {
    a(paramParcel, paramInt, 8);
    return paramParcel.readLong();
  }
  
  public static Long j(Parcel paramParcel, int paramInt)
  {
    int i = a(paramParcel, paramInt);
    if (i == 0) {}
    for (paramParcel = null;; paramParcel = Long.valueOf(paramParcel.readLong()))
    {
      return paramParcel;
      a(paramParcel, paramInt, i, 8);
    }
  }
  
  public static BigInteger k(Parcel paramParcel, int paramInt)
  {
    int i = a(paramParcel, paramInt);
    paramInt = paramParcel.dataPosition();
    if (i == 0) {}
    byte[] arrayOfByte;
    for (paramParcel = null;; paramParcel = new BigInteger(arrayOfByte))
    {
      return paramParcel;
      arrayOfByte = paramParcel.createByteArray();
      paramParcel.setDataPosition(i + paramInt);
    }
  }
  
  public static float l(Parcel paramParcel, int paramInt)
  {
    a(paramParcel, paramInt, 4);
    return paramParcel.readFloat();
  }
  
  public static double m(Parcel paramParcel, int paramInt)
  {
    a(paramParcel, paramInt, 8);
    return paramParcel.readDouble();
  }
  
  public static BigDecimal n(Parcel paramParcel, int paramInt)
  {
    paramInt = a(paramParcel, paramInt);
    int j = paramParcel.dataPosition();
    if (paramInt == 0) {}
    byte[] arrayOfByte;
    int i;
    for (paramParcel = null;; paramParcel = new BigDecimal(new BigInteger(arrayOfByte), i))
    {
      return paramParcel;
      arrayOfByte = paramParcel.createByteArray();
      i = paramParcel.readInt();
      paramParcel.setDataPosition(paramInt + j);
    }
  }
  
  public static String o(Parcel paramParcel, int paramInt)
  {
    paramInt = a(paramParcel, paramInt);
    int i = paramParcel.dataPosition();
    if (paramInt == 0) {}
    String str;
    for (paramParcel = null;; paramParcel = str)
    {
      return paramParcel;
      str = paramParcel.readString();
      paramParcel.setDataPosition(paramInt + i);
    }
  }
  
  public static IBinder p(Parcel paramParcel, int paramInt)
  {
    int i = a(paramParcel, paramInt);
    paramInt = paramParcel.dataPosition();
    if (i == 0) {}
    IBinder localIBinder;
    for (paramParcel = null;; paramParcel = localIBinder)
    {
      return paramParcel;
      localIBinder = paramParcel.readStrongBinder();
      paramParcel.setDataPosition(i + paramInt);
    }
  }
  
  public static Bundle q(Parcel paramParcel, int paramInt)
  {
    paramInt = a(paramParcel, paramInt);
    int i = paramParcel.dataPosition();
    if (paramInt == 0) {}
    Bundle localBundle;
    for (paramParcel = null;; paramParcel = localBundle)
    {
      return paramParcel;
      localBundle = paramParcel.readBundle();
      paramParcel.setDataPosition(paramInt + i);
    }
  }
  
  public static byte[] r(Parcel paramParcel, int paramInt)
  {
    paramInt = a(paramParcel, paramInt);
    int i = paramParcel.dataPosition();
    if (paramInt == 0) {}
    byte[] arrayOfByte;
    for (paramParcel = null;; paramParcel = arrayOfByte)
    {
      return paramParcel;
      arrayOfByte = paramParcel.createByteArray();
      paramParcel.setDataPosition(paramInt + i);
    }
  }
  
  public static byte[][] s(Parcel paramParcel, int paramInt)
  {
    int k = a(paramParcel, paramInt);
    int j = paramParcel.dataPosition();
    if (k == 0) {}
    byte[][] arrayOfByte;
    for (paramParcel = (byte[][])null;; paramParcel = arrayOfByte)
    {
      return paramParcel;
      int i = paramParcel.readInt();
      arrayOfByte = new byte[i][];
      for (paramInt = 0; paramInt < i; paramInt++) {
        arrayOfByte[paramInt] = paramParcel.createByteArray();
      }
      paramParcel.setDataPosition(j + k);
    }
  }
  
  public static boolean[] t(Parcel paramParcel, int paramInt)
  {
    paramInt = a(paramParcel, paramInt);
    int i = paramParcel.dataPosition();
    if (paramInt == 0) {}
    boolean[] arrayOfBoolean;
    for (paramParcel = null;; paramParcel = arrayOfBoolean)
    {
      return paramParcel;
      arrayOfBoolean = paramParcel.createBooleanArray();
      paramParcel.setDataPosition(paramInt + i);
    }
  }
  
  public static int[] u(Parcel paramParcel, int paramInt)
  {
    paramInt = a(paramParcel, paramInt);
    int i = paramParcel.dataPosition();
    if (paramInt == 0) {}
    int[] arrayOfInt;
    for (paramParcel = null;; paramParcel = arrayOfInt)
    {
      return paramParcel;
      arrayOfInt = paramParcel.createIntArray();
      paramParcel.setDataPosition(paramInt + i);
    }
  }
  
  public static long[] v(Parcel paramParcel, int paramInt)
  {
    paramInt = a(paramParcel, paramInt);
    int i = paramParcel.dataPosition();
    if (paramInt == 0) {}
    long[] arrayOfLong;
    for (paramParcel = null;; paramParcel = arrayOfLong)
    {
      return paramParcel;
      arrayOfLong = paramParcel.createLongArray();
      paramParcel.setDataPosition(paramInt + i);
    }
  }
  
  public static BigInteger[] w(Parcel paramParcel, int paramInt)
  {
    int k = a(paramParcel, paramInt);
    int i = paramParcel.dataPosition();
    if (k == 0) {}
    BigInteger[] arrayOfBigInteger;
    for (paramParcel = null;; paramParcel = arrayOfBigInteger)
    {
      return paramParcel;
      int j = paramParcel.readInt();
      arrayOfBigInteger = new BigInteger[j];
      for (paramInt = 0; paramInt < j; paramInt++) {
        arrayOfBigInteger[paramInt] = new BigInteger(paramParcel.createByteArray());
      }
      paramParcel.setDataPosition(i + k);
    }
  }
  
  public static float[] x(Parcel paramParcel, int paramInt)
  {
    paramInt = a(paramParcel, paramInt);
    int i = paramParcel.dataPosition();
    if (paramInt == 0) {}
    float[] arrayOfFloat;
    for (paramParcel = null;; paramParcel = arrayOfFloat)
    {
      return paramParcel;
      arrayOfFloat = paramParcel.createFloatArray();
      paramParcel.setDataPosition(paramInt + i);
    }
  }
  
  public static double[] y(Parcel paramParcel, int paramInt)
  {
    int i = a(paramParcel, paramInt);
    paramInt = paramParcel.dataPosition();
    if (i == 0) {}
    double[] arrayOfDouble;
    for (paramParcel = null;; paramParcel = arrayOfDouble)
    {
      return paramParcel;
      arrayOfDouble = paramParcel.createDoubleArray();
      paramParcel.setDataPosition(i + paramInt);
    }
  }
  
  public static BigDecimal[] z(Parcel paramParcel, int paramInt)
  {
    int j = a(paramParcel, paramInt);
    int i = paramParcel.dataPosition();
    if (j == 0) {}
    BigDecimal[] arrayOfBigDecimal;
    for (paramParcel = null;; paramParcel = arrayOfBigDecimal)
    {
      return paramParcel;
      int k = paramParcel.readInt();
      arrayOfBigDecimal = new BigDecimal[k];
      for (paramInt = 0; paramInt < k; paramInt++)
      {
        byte[] arrayOfByte = paramParcel.createByteArray();
        int m = paramParcel.readInt();
        arrayOfBigDecimal[paramInt] = new BigDecimal(new BigInteger(arrayOfByte), m);
      }
      paramParcel.setDataPosition(i + j);
    }
  }
  
  public static class a
    extends RuntimeException
  {
    public a(String paramString, Parcel paramParcel)
    {
      super();
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\common\internal\safeparcel\a.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */