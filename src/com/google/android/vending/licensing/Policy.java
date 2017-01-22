package com.google.android.vending.licensing;

public abstract interface Policy
{
  public static final int LICENSED = 256;
  public static final int NOT_LICENSED = 561;
  public static final int RETRY = 291;
  
  public abstract boolean allowAccess();
  
  public abstract void processServerResponse(int paramInt, ResponseData paramResponseData);
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\vending\licensing\Policy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */