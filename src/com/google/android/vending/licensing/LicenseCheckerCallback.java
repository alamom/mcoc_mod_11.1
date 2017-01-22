package com.google.android.vending.licensing;

public abstract interface LicenseCheckerCallback
{
  public static final int ERROR_CHECK_IN_PROGRESS = 4;
  public static final int ERROR_INVALID_PACKAGE_NAME = 1;
  public static final int ERROR_INVALID_PUBLIC_KEY = 5;
  public static final int ERROR_MISSING_PERMISSION = 6;
  public static final int ERROR_NON_MATCHING_UID = 2;
  public static final int ERROR_NOT_MARKET_MANAGED = 3;
  
  public abstract void allow(int paramInt);
  
  public abstract void applicationError(int paramInt);
  
  public abstract void dontAllow(int paramInt);
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\vending\licensing\LicenseCheckerCallback.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */