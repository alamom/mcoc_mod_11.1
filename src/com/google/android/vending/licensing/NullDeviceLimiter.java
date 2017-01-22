package com.google.android.vending.licensing;

public class NullDeviceLimiter
  implements DeviceLimiter
{
  public int isDeviceAllowed(String paramString)
  {
    return 256;
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\vending\licensing\NullDeviceLimiter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */