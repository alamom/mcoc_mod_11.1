package com.google.android.vending.licensing;

import android.text.TextUtils;
import android.util.Log;
import com.google.android.vending.licensing.util.Base64;
import com.google.android.vending.licensing.util.Base64DecoderException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;

class LicenseValidator
{
  private static final int ERROR_CONTACTING_SERVER = 257;
  private static final int ERROR_INVALID_PACKAGE_NAME = 258;
  private static final int ERROR_NON_MATCHING_UID = 259;
  private static final int ERROR_NOT_MARKET_MANAGED = 3;
  private static final int ERROR_OVER_QUOTA = 5;
  private static final int ERROR_SERVER_FAILURE = 4;
  private static final int LICENSED = 0;
  private static final int LICENSED_OLD_KEY = 2;
  private static final int NOT_LICENSED = 1;
  private static final String SIGNATURE_ALGORITHM = "SHA1withRSA";
  private static final String TAG = "LicenseValidator";
  private final LicenseCheckerCallback mCallback;
  private final DeviceLimiter mDeviceLimiter;
  private final int mNonce;
  private final String mPackageName;
  private final Policy mPolicy;
  private final String mVersionCode;
  
  LicenseValidator(Policy paramPolicy, DeviceLimiter paramDeviceLimiter, LicenseCheckerCallback paramLicenseCheckerCallback, int paramInt, String paramString1, String paramString2)
  {
    this.mPolicy = paramPolicy;
    this.mDeviceLimiter = paramDeviceLimiter;
    this.mCallback = paramLicenseCheckerCallback;
    this.mNonce = paramInt;
    this.mPackageName = paramString1;
    this.mVersionCode = paramString2;
  }
  
  private void handleApplicationError(int paramInt)
  {
    this.mCallback.applicationError(paramInt);
  }
  
  private void handleInvalidResponse()
  {
    this.mCallback.dontAllow(561);
  }
  
  private void handleResponse(int paramInt, ResponseData paramResponseData)
  {
    this.mPolicy.processServerResponse(paramInt, paramResponseData);
    if (this.mPolicy.allowAccess()) {
      this.mCallback.allow(paramInt);
    }
    for (;;)
    {
      return;
      this.mCallback.dontAllow(paramInt);
    }
  }
  
  public LicenseCheckerCallback getCallback()
  {
    return this.mCallback;
  }
  
  public int getNonce()
  {
    return this.mNonce;
  }
  
  public String getPackageName()
  {
    return this.mPackageName;
  }
  
  public void verify(PublicKey paramPublicKey, int paramInt, String paramString1, String paramString2)
  {
    PublicKey localPublicKey = null;
    Object localObject = null;
    if ((paramInt == 0) || (paramInt == 1) || (paramInt == 2)) {}
    for (;;)
    {
      try
      {
        localObject = Signature.getInstance("SHA1withRSA");
        ((Signature)localObject).initVerify(paramPublicKey);
        ((Signature)localObject).update(paramString1.getBytes());
        if (!((Signature)localObject).verify(Base64.decode(paramString2)))
        {
          Log.e("LicenseValidator", "Signature verification failed.");
          handleInvalidResponse();
          return;
        }
      }
      catch (NoSuchAlgorithmException paramPublicKey)
      {
        throw new RuntimeException(paramPublicKey);
      }
      catch (InvalidKeyException paramPublicKey)
      {
        handleApplicationError(5);
        continue;
      }
      catch (SignatureException paramPublicKey)
      {
        throw new RuntimeException(paramPublicKey);
      }
      catch (Base64DecoderException paramPublicKey)
      {
        Log.e("LicenseValidator", "Could not Base64-decode signature.");
        handleInvalidResponse();
        continue;
      }
      catch (NullPointerException paramPublicKey)
      {
        handleInvalidResponse();
        continue;
        try
        {
          localObject = ResponseData.parse(paramString1);
          if (((ResponseData)localObject).responseCode == paramInt) {
            continue;
          }
          Log.e("LicenseValidator", "Response codes don't match.");
          handleInvalidResponse();
        }
        catch (IllegalArgumentException paramPublicKey)
        {
          Log.e("LicenseValidator", "Could not parse response.");
          handleInvalidResponse();
        }
        continue;
        if (((ResponseData)localObject).nonce != this.mNonce)
        {
          Log.e("LicenseValidator", "Nonce doesn't match.");
          handleInvalidResponse();
          continue;
        }
        if (!((ResponseData)localObject).packageName.equals(this.mPackageName))
        {
          Log.e("LicenseValidator", "Package name doesn't match.");
          handleInvalidResponse();
          continue;
        }
        if (!((ResponseData)localObject).versionCode.equals(this.mVersionCode))
        {
          Log.e("LicenseValidator", "Version codes don't match.");
          handleInvalidResponse();
          continue;
        }
        paramPublicKey = ((ResponseData)localObject).userId;
        localPublicKey = paramPublicKey;
        if (TextUtils.isEmpty(paramPublicKey))
        {
          Log.e("LicenseValidator", "User identifier is empty.");
          handleInvalidResponse();
          continue;
        }
      }
      switch (paramInt)
      {
      default: 
        Log.e("LicenseValidator", "Unknown response code for license check.");
        handleInvalidResponse();
        break;
      case 0: 
      case 2: 
        handleResponse(this.mDeviceLimiter.isDeviceAllowed(localPublicKey), (ResponseData)localObject);
        break;
      case 1: 
        handleResponse(561, (ResponseData)localObject);
        break;
      case 257: 
        Log.w("LicenseValidator", "Error contacting licensing server.");
        handleResponse(291, (ResponseData)localObject);
        break;
      case 4: 
        Log.w("LicenseValidator", "An error has occurred on the licensing server.");
        handleResponse(291, (ResponseData)localObject);
        break;
      case 5: 
        Log.w("LicenseValidator", "Licensing server is refusing to talk to this device, over quota.");
        handleResponse(291, (ResponseData)localObject);
        break;
      case 258: 
        handleApplicationError(1);
        break;
      case 259: 
        handleApplicationError(2);
        break;
      case 3: 
        handleApplicationError(3);
      }
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\vending\licensing\LicenseValidator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */