package com.mobileapptracker.gaidwrapper;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import java.io.IOException;

public abstract interface GAIDFetcherInterface
{
  public abstract void encounteredGooglePlayServicesNotAvailableException(GooglePlayServicesNotAvailableException paramGooglePlayServicesNotAvailableException);
  
  public abstract void encounteredGooglePlayServicesRepairableException(GooglePlayServicesRepairableException paramGooglePlayServicesRepairableException);
  
  public abstract void encounteredIOException(IOException paramIOException);
  
  public abstract void retrievedGAID(String paramString, boolean paramBoolean);
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\mobileapptracker\gaidwrapper\GAIDFetcherInterface.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */