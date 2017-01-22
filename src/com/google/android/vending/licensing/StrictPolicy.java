package com.google.android.vending.licensing;

public class StrictPolicy
  implements Policy
{
  private int mLastResponse = 291;
  
  public boolean allowAccess()
  {
    if (this.mLastResponse == 256) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public void processServerResponse(int paramInt, ResponseData paramResponseData)
  {
    this.mLastResponse = paramInt;
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\vending\licensing\StrictPolicy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */