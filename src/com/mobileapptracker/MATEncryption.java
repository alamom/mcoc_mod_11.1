package com.mobileapptracker;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class MATEncryption
{
  private IvParameterSpec a;
  private SecretKeySpec b;
  private Cipher c;
  
  public MATEncryption(String paramString1, String paramString2)
  {
    this.a = new IvParameterSpec(paramString2.getBytes());
    this.b = new SecretKeySpec(paramString1.getBytes(), "AES");
    try
    {
      this.c = Cipher.getInstance("AES/CBC/NoPadding");
      return;
    }
    catch (NoSuchAlgorithmException paramString1)
    {
      for (;;)
      {
        paramString1.printStackTrace();
      }
    }
    catch (NoSuchPaddingException paramString1)
    {
      for (;;)
      {
        paramString1.printStackTrace();
      }
    }
  }
  
  private static String a(String paramString)
  {
    int j = paramString.length();
    for (int i = 0; i < 16 - j % 16; i++) {
      paramString = paramString + ' ';
    }
    return paramString;
  }
  
  public static String bytesToHex(byte[] paramArrayOfByte)
  {
    Object localObject;
    if (paramArrayOfByte == null) {
      localObject = null;
    }
    int j;
    int i;
    do
    {
      return (String)localObject;
      j = paramArrayOfByte.length;
      str = "";
      i = 0;
      localObject = str;
    } while (i >= j);
    if ((paramArrayOfByte[i] & 0xFF) < 16) {}
    for (String str = str + "0" + Integer.toHexString(paramArrayOfByte[i] & 0xFF);; str = str + Integer.toHexString(paramArrayOfByte[i] & 0xFF))
    {
      i++;
      break;
    }
  }
  
  public static byte[] hexToBytes(String paramString)
  {
    Object localObject = null;
    if (paramString == null) {}
    while (paramString.length() < 2) {
      return (byte[])localObject;
    }
    int j = paramString.length() / 2;
    byte[] arrayOfByte = new byte[j];
    for (int i = 0;; i++)
    {
      localObject = arrayOfByte;
      if (i >= j) {
        break;
      }
      arrayOfByte[i] = ((byte)Integer.parseInt(paramString.substring(i * 2, i * 2 + 2), 16));
    }
  }
  
  public static String md5(String paramString)
  {
    if (paramString == null) {
      paramString = "";
    }
    for (;;)
    {
      return paramString;
      try
      {
        MessageDigest localMessageDigest = MessageDigest.getInstance("MD5");
        localMessageDigest.update(paramString.getBytes());
        paramString = bytesToHex(localMessageDigest.digest());
      }
      catch (NoSuchAlgorithmException paramString)
      {
        paramString.printStackTrace();
        paramString = "";
      }
    }
  }
  
  public static String sha1(String paramString)
  {
    if (paramString == null) {
      paramString = "";
    }
    for (;;)
    {
      return paramString;
      try
      {
        MessageDigest localMessageDigest = MessageDigest.getInstance("SHA-1");
        localMessageDigest.update(paramString.getBytes());
        paramString = bytesToHex(localMessageDigest.digest());
      }
      catch (NoSuchAlgorithmException paramString)
      {
        paramString.printStackTrace();
        paramString = "";
      }
    }
  }
  
  public static String sha256(String paramString)
  {
    if (paramString == null) {
      paramString = "";
    }
    for (;;)
    {
      return paramString;
      try
      {
        MessageDigest localMessageDigest = MessageDigest.getInstance("SHA-256");
        localMessageDigest.update(paramString.getBytes());
        paramString = bytesToHex(localMessageDigest.digest());
      }
      catch (NoSuchAlgorithmException paramString)
      {
        paramString.printStackTrace();
        paramString = "";
      }
    }
  }
  
  public byte[] decrypt(String paramString)
  {
    if ((paramString == null) || (paramString.length() == 0)) {
      throw new Exception("Empty string");
    }
    try
    {
      this.c.init(2, this.b, this.a);
      paramString = this.c.doFinal(hexToBytes(paramString));
      return paramString;
    }
    catch (Exception paramString)
    {
      throw new Exception("[decrypt] " + paramString.getMessage());
    }
  }
  
  public byte[] encrypt(String paramString)
  {
    if ((paramString == null) || (paramString.length() == 0)) {
      throw new Exception("Empty string");
    }
    try
    {
      this.c.init(1, this.b, this.a);
      paramString = this.c.doFinal(a(paramString).getBytes());
      return paramString;
    }
    catch (Exception paramString)
    {
      throw new Exception("[encrypt] " + paramString.getMessage());
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\mobileapptracker\MATEncryption.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */