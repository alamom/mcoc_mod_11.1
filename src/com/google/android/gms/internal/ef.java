package com.google.android.gms.internal;

import android.text.TextUtils;
import android.util.Base64;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;

@ez
public class ef
{
  public static PublicKey F(String paramString)
  {
    try
    {
      paramString = Base64.decode(paramString, 0);
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
      gs.T("Invalid key specification.");
      throw new IllegalArgumentException(paramString);
    }
  }
  
  public static boolean a(PublicKey paramPublicKey, String paramString1, String paramString2)
  {
    boolean bool = false;
    try
    {
      Signature localSignature = Signature.getInstance("SHA1withRSA");
      localSignature.initVerify(paramPublicKey);
      localSignature.update(paramString1.getBytes());
      if (!localSignature.verify(Base64.decode(paramString2, 0))) {
        gs.T("Signature verification failed.");
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
        gs.T("NoSuchAlgorithmException.");
      }
    }
    catch (InvalidKeyException paramPublicKey)
    {
      for (;;)
      {
        gs.T("Invalid key specification.");
      }
    }
    catch (SignatureException paramPublicKey)
    {
      for (;;)
      {
        gs.T("Signature exception.");
      }
    }
  }
  
  public static boolean b(String paramString1, String paramString2, String paramString3)
  {
    if ((TextUtils.isEmpty(paramString2)) || (TextUtils.isEmpty(paramString1)) || (TextUtils.isEmpty(paramString3))) {
      gs.T("Purchase verification failed: missing data.");
    }
    for (boolean bool = false;; bool = a(F(paramString1), paramString2, paramString3)) {
      return bool;
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\internal\ef.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */