package com.kabam.soda;

public class Base16Encoder
{
  private static final char[] HEX = { 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 97, 98, 99, 100, 101, 102 };
  
  public static byte[] decode(String paramString)
  {
    byte[] arrayOfByte = new byte[paramString.length() / 2];
    int k = 0;
    if (k < arrayOfByte.length)
    {
      int j = paramString.charAt(k * 2);
      int m = paramString.charAt(k * 2 + 1);
      int i;
      if ((j >= 48) && (j <= 57))
      {
        i = j - 48;
        label55:
        if ((m < 48) || (m > 57)) {
          break label115;
        }
        j = m - 48;
      }
      for (;;)
      {
        arrayOfByte[k] = ((byte)((i << 4) + j));
        k++;
        break;
        i = j;
        if (j < 97) {
          break label55;
        }
        i = j;
        if (j > 102) {
          break label55;
        }
        i = j - 87;
        break label55;
        label115:
        j = m;
        if (m >= 97)
        {
          j = m;
          if (m <= 102) {
            j = m - 87;
          }
        }
      }
    }
    return arrayOfByte;
  }
  
  public static String encode(byte[] paramArrayOfByte)
  {
    StringBuffer localStringBuffer = new StringBuffer(paramArrayOfByte.length * 2);
    for (int i = 0; i < paramArrayOfByte.length; i++) {
      for (int j = 1; j >= 0; j--) {
        localStringBuffer.append(HEX[(paramArrayOfByte[i] >> j * 4 & 0xF)]);
      }
    }
    return localStringBuffer.toString();
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\kabam\soda\Base16Encoder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */