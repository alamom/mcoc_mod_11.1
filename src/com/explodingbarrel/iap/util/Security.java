package com.explodingbarrel.iap.util;

import android.text.TextUtils;
import android.util.Log;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;

public class Security
{
  private static final String KEY_FACTORY_ALGORITHM = "RSA";
  private static final String SIGNATURE_ALGORITHM = "SHA1withRSA";
  private static final String TAG = "IABUtil/Security";
  
  public static PublicKey generatePublicKey(String paramString)
  {
    try
    {
      paramString = Base64.decode(paramString);
      KeyFactory localKeyFactory = KeyFactory.getInstance("RSA");
      X509EncodedKeySpec localX509EncodedKeySpec = new java/security/spec/X509EncodedKeySpec;
      localX509EncodedKeySpec.<init>(paramString);
      paramString = localKeyFactory.generatePublic(localX509EncodedKeySpec);
      return paramString;
    }
    catch (NoSuchAlgorithmException paramString)
    {
      throw new RuntimeException(paramString);
    }
    catch (InvalidKeySpecException paramString)
    {
      Log.e("IABUtil/Security", "Invalid key specification.");
      throw new IllegalArgumentException(paramString);
    }
    catch (Base64DecoderException paramString)
    {
      Log.e("IABUtil/Security", "Base64 decoding failed.");
      throw new IllegalArgumentException(paramString);
    }
  }
  
  public static boolean verify(PublicKey paramPublicKey, String paramString1, String paramString2)
  {
    boolean bool = false;
    try
    {
      Signature localSignature = Signature.getInstance("SHA1withRSA");
      localSignature.initVerify(paramPublicKey);
      localSignature.update(paramString1.getBytes());
      if (!localSignature.verify(Base64.decode(paramString2))) {
        Log.e("IABUtil/Security", "Signature verification failed.");
      }
      for (;;)
      {
        return bool;
        bool = true;
      }
    }
    catch (NoSuchAlgorithmException paramPublicKey)
    {
      for (;;)
      {
        Log.e("IABUtil/Security", "NoSuchAlgorithmException.");
      }
    }
    catch (InvalidKeyException paramPublicKey)
    {
      for (;;)
      {
        Log.e("IABUtil/Security", "Invalid key specification.");
      }
    }
    catch (SignatureException paramPublicKey)
    {
      for (;;)
      {
        Log.e("IABUtil/Security", "Signature exception.");
      }
    }
    catch (Base64DecoderException paramPublicKey)
    {
      for (;;)
      {
        Log.e("IABUtil/Security", "Base64 decoding failed.");
      }
    }
  }
  
  public static boolean verifyPurchase(String paramString1, String paramString2, String paramString3)
  {
    boolean bool = false;
    if (paramString2 == null) {
      Log.e("IABUtil/Security", "data is null");
    }
    for (;;)
    {
      return bool;
      if ((!TextUtils.isEmpty(paramString3)) && (!verify(generatePublicKey(paramString1), paramString2, paramString3))) {
        Log.w("IABUtil/Security", "signature does not match data.");
      } else {
        bool = true;
      }
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\explodingbarrel\iap\util\Security.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */