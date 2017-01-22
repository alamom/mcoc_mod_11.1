package com.mobileapptracker.gaidwrapper;

import android.content.Context;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import java.io.IOException;

public class GAIDFetcher
{
  private GAIDFetcherInterface callbackInterface;
  
  public void fetchGAID(Context paramContext)
  {
    Fetcher localFetcher = new Fetcher(null);
    localFetcher.applicationContext = paramContext;
    new Thread(localFetcher).start();
  }
  
  public void setFetcherInterface(GAIDFetcherInterface paramGAIDFetcherInterface)
  {
    this.callbackInterface = paramGAIDFetcherInterface;
  }
  
  public void useUnityFetcherInterface()
  {
    setFetcherInterface(new UnityGAIDInterface());
  }
  
  private class Fetcher
    implements Runnable
  {
    public Context applicationContext;
    
    private Fetcher() {}
    
    public void run()
    {
      try
      {
        AdvertisingIdClient.Info localInfo = AdvertisingIdClient.getAdvertisingIdInfo(this.applicationContext);
        if (GAIDFetcher.this.callbackInterface != null) {
          GAIDFetcher.this.callbackInterface.retrievedGAID(localInfo.getId(), localInfo.isLimitAdTrackingEnabled());
        }
        return;
      }
      catch (IOException localIOException)
      {
        for (;;)
        {
          if (GAIDFetcher.this.callbackInterface != null) {
            GAIDFetcher.this.callbackInterface.encounteredIOException(localIOException);
          }
        }
      }
      catch (GooglePlayServicesNotAvailableException localGooglePlayServicesNotAvailableException)
      {
        for (;;)
        {
          if (GAIDFetcher.this.callbackInterface != null) {
            GAIDFetcher.this.callbackInterface.encounteredGooglePlayServicesNotAvailableException(localGooglePlayServicesNotAvailableException);
          }
        }
      }
      catch (GooglePlayServicesRepairableException localGooglePlayServicesRepairableException)
      {
        for (;;)
        {
          if (GAIDFetcher.this.callbackInterface != null) {
            GAIDFetcher.this.callbackInterface.encounteredGooglePlayServicesRepairableException(localGooglePlayServicesRepairableException);
          }
        }
      }
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\mobileapptracker\gaidwrapper\GAIDFetcher.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */