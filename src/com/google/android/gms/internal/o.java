package com.google.android.gms.internal;

import java.nio.ByteBuffer;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class o
{
  private final SecureRandom kW;
  private final m ky;
  
  public o(m paramm, SecureRandom paramSecureRandom)
  {
    this.ky = paramm;
    this.kW = paramSecureRandom;
  }
  
  static void c(byte[] paramArrayOfByte)
  {
    for (int i = 0; i < paramArrayOfByte.length; i++) {
      paramArrayOfByte[i] = ((byte)(paramArrayOfByte[i] ^ 0x44));
    }
  }
  
  public byte[] b(String paramString)
    throws o.a
  {
    try
    {
      paramString = this.ky.a(paramString, false);
      if (paramString.length != 32)
      {
        paramString = new com/google/android/gms/internal/o$a;
        paramString.<init>(this);
        throw paramString;
      }
    }
    catch (IllegalArgumentException paramString)
    {
      throw new a(paramString);
    }
    ByteBuffer localByteBuffer = ByteBuffer.wrap(paramString, 4, 16);
    paramString = new byte[16];
    localByteBuffer.get(paramString);
    c(paramString);
    return paramString;
  }
  
  public byte[] c(byte[] paramArrayOfByte, String paramString)
    throws o.a
  {
    if (paramArrayOfByte.length != 16) {
      throw new a();
    }
    try
    {
      arrayOfByte = this.ky.a(paramString, false);
      if (arrayOfByte.length <= 16)
      {
        paramArrayOfByte = new com/google/android/gms/internal/o$a;
        paramArrayOfByte.<init>(this);
        throw paramArrayOfByte;
      }
    }
    catch (NoSuchAlgorithmException paramArrayOfByte)
    {
      throw new a(paramArrayOfByte);
      Object localObject = ByteBuffer.allocate(arrayOfByte.length);
      ((ByteBuffer)localObject).put(arrayOfByte);
      ((ByteBuffer)localObject).flip();
      paramString = new byte[16];
      byte[] arrayOfByte = new byte[arrayOfByte.length - 16];
      ((ByteBuffer)localObject).get(paramString);
      ((ByteBuffer)localObject).get(arrayOfByte);
      localObject = new javax/crypto/spec/SecretKeySpec;
      ((SecretKeySpec)localObject).<init>(paramArrayOfByte, "AES");
      paramArrayOfByte = Cipher.getInstance("AES/CBC/PKCS5Padding");
      IvParameterSpec localIvParameterSpec = new javax/crypto/spec/IvParameterSpec;
      localIvParameterSpec.<init>(paramString);
      paramArrayOfByte.init(2, (Key)localObject, localIvParameterSpec);
      paramArrayOfByte = paramArrayOfByte.doFinal(arrayOfByte);
      return paramArrayOfByte;
    }
    catch (InvalidKeyException paramArrayOfByte)
    {
      throw new a(paramArrayOfByte);
    }
    catch (IllegalBlockSizeException paramArrayOfByte)
    {
      throw new a(paramArrayOfByte);
    }
    catch (NoSuchPaddingException paramArrayOfByte)
    {
      throw new a(paramArrayOfByte);
    }
    catch (BadPaddingException paramArrayOfByte)
    {
      throw new a(paramArrayOfByte);
    }
    catch (InvalidAlgorithmParameterException paramArrayOfByte)
    {
      throw new a(paramArrayOfByte);
    }
    catch (IllegalArgumentException paramArrayOfByte)
    {
      throw new a(paramArrayOfByte);
    }
  }
  
  public class a
    extends Exception
  {
    public a() {}
    
    public a(Throwable paramThrowable)
    {
      super();
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\internal\o.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */