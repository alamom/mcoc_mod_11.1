package com.google.android.gms.tagmanager;

class j
{
  public static byte[] cm(String paramString)
  {
    int j = paramString.length();
    if (j % 2 != 0) {
      throw new IllegalArgumentException("purported base16 string has odd number of characters");
    }
    byte[] arrayOfByte = new byte[j / 2];
    for (int i = 0; i < j; i += 2)
    {
      int m = Character.digit(paramString.charAt(i), 16);
      int k = Character.digit(paramString.charAt(i + 1), 16);
      if ((m == -1) || (k == -1)) {
        throw new IllegalArgumentException("purported base16 string has illegal char");
      }
      arrayOfByte[(i / 2)] = ((byte)((m << 4) + k));
    }
    return arrayOfByte;
  }
  
  public static String d(byte[] paramArrayOfByte)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    int j = paramArrayOfByte.length;
    for (int i = 0; i < j; i++)
    {
      int k = paramArrayOfByte[i];
      if ((k & 0xF0) == 0) {
        localStringBuilder.append("0");
      }
      localStringBuilder.append(Integer.toHexString(k & 0xFF));
    }
    return localStringBuilder.toString().toUpperCase();
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\tagmanager\j.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */