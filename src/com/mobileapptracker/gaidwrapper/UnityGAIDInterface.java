package com.mobileapptracker.gaidwrapper;

import android.util.Log;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import java.io.IOException;

public class UnityGAIDInterface
  implements GAIDFetcherInterface
{
  public void encounteredGooglePlayServicesNotAvailableException(GooglePlayServicesNotAvailableException paramGooglePlayServicesNotAvailableException)
  {
    Log.d("matgaidunity", "Google Play Services not available fetching GAID");
  }
  
  public void encounteredGooglePlayServicesRepairableException(GooglePlayServicesRepairableException paramGooglePlayServicesRepairableException)
  {
    Log.d("matgaidunity", "Google Play Services repairable exception fetching GAID :" + paramGooglePlayServicesRepairableException.toString());
  }
  
  public void encounteredIOException(IOException paramIOException)
  {
    Log.d("matgaidunity", "IO exception fetching GAID");
  }
  
  native void nativeRetrievedGAID(String paramString, boolean paramBoolean);
  
  public void retrievedGAID(String paramString, boolean paramBoolean)
  {
    nativeRetrievedGAID(paramString, paramBoolean);
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\mobileapptracker\gaidwrapper\UnityGAIDInterface.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */