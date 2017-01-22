package com.google.android.vending.licensing;

import com.google.android.vending.licensing.util.Base64;
import com.google.android.vending.licensing.util.Base64DecoderException;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

public class AESObfuscator
  implements Obfuscator
{
  private static final String CIPHER_ALGORITHM = "AES/CBC/PKCS5Padding";
  private static final byte[] IV = { 16, 74, 71, -80, 32, 101, -47, 72, 117, -14, 0, -29, 70, 65, -12, 74 };
  private static final String KEYGEN_ALGORITHM = "PBEWITHSHAAND256BITAES-CBC-BC";
  private static final String UTF8 = "UTF-8";
  private static final String header = "com.android.vending.licensing.AESObfuscator-1|";
  private Cipher mDecryptor;
  private Cipher mEncryptor;
  
  public AESObfuscator(byte[] paramArrayOfByte, String paramString1, String paramString2)
  {
    try
    {
      SecretKeyFactory localSecretKeyFactory = SecretKeyFactory.getInstance("PBEWITHSHAAND256BITAES-CBC-BC");
      PBEKeySpec localPBEKeySpec = new javax/crypto/spec/PBEKeySpec;
      StringBuilder localStringBuilder = new java/lang/StringBuilder;
      localStringBuilder.<init>();
      localPBEKeySpec.<init>((paramString1 + paramString2).toCharArray(), paramArrayOfByte, 1024, 256);
      paramString1 = localSecretKeyFactory.generateSecret(localPBEKeySpec);
      paramArrayOfByte = new javax/crypto/spec/SecretKeySpec;
      paramArrayOfByte.<init>(paramString1.getEncoded(), "AES");
      this.mEncryptor = Cipher.getInstance("AES/CBC/PKCS5Padding");
      paramString2 = this.mEncryptor;
      paramString1 = new javax/crypto/spec/IvParameterSpec;
      paramString1.<init>(IV);
      paramString2.init(1, paramArrayOfByte, paramString1);
      this.mDecryptor = Cipher.getInstance("AES/CBC/PKCS5Padding");
      paramString2 = this.mDecryptor;
      paramString1 = new javax/crypto/spec/IvParameterSpec;
      paramString1.<init>(IV);
      paramString2.init(2, paramArrayOfByte, paramString1);
      return;
    }
    catch (GeneralSecurityException paramArrayOfByte)
    {
      throw new RuntimeException("Invalid environment", paramArrayOfByte);
    }
  }
  
  public String obfuscate(String paramString1, String paramString2)
  {
    if (paramString1 == null) {
      paramString1 = null;
    }
    for (;;)
    {
      return paramString1;
      try
      {
        Cipher localCipher = this.mEncryptor;
        StringBuilder localStringBuilder = new java/lang/StringBuilder;
        localStringBuilder.<init>();
        paramString1 = Base64.encode(localCipher.doFinal(("com.android.vending.licensing.AESObfuscator-1|" + paramString2 + paramString1).getBytes("UTF-8")));
      }
      catch (UnsupportedEncodingException paramString1)
      {
        throw new RuntimeException("Invalid environment", paramString1);
      }
      catch (GeneralSecurityException paramString1)
      {
        throw new RuntimeException("Invalid environment", paramString1);
      }
    }
  }
  
  public String unobfuscate(String paramString1, String paramString2)
    throws ValidationException
  {
    if (paramString1 == null) {
      paramString1 = null;
    }
    for (;;)
    {
      return paramString1;
      try
      {
        str = new java/lang/String;
        str.<init>(this.mDecryptor.doFinal(Base64.decode(paramString1)), "UTF-8");
        Object localObject = new java/lang/StringBuilder;
        ((StringBuilder)localObject).<init>();
        if (str.indexOf("com.android.vending.licensing.AESObfuscator-1|" + paramString2) != 0)
        {
          localObject = new com/google/android/vending/licensing/ValidationException;
          paramString2 = new java/lang/StringBuilder;
          paramString2.<init>();
          ((ValidationException)localObject).<init>("Header not found (invalid data or key):" + paramString1);
          throw ((Throwable)localObject);
        }
      }
      catch (Base64DecoderException paramString2)
      {
        String str;
        throw new ValidationException(paramString2.getMessage() + ":" + paramString1);
        paramString2 = str.substring("com.android.vending.licensing.AESObfuscator-1|".length() + paramString2.length(), str.length());
        paramString1 = paramString2;
      }
      catch (IllegalBlockSizeException paramString2)
      {
        throw new ValidationException(paramString2.getMessage() + ":" + paramString1);
      }
      catch (BadPaddingException paramString2)
      {
        throw new ValidationException(paramString2.getMessage() + ":" + paramString1);
      }
      catch (UnsupportedEncodingException paramString1)
      {
        throw new RuntimeException("Invalid environment", paramString1);
      }
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\vending\licensing\AESObfuscator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */