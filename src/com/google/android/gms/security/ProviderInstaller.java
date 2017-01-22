package com.google.android.gms.security;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.internal.o;
import java.lang.reflect.Method;

public class ProviderInstaller
{
  public static final String PROVIDER_NAME = "GmsCore_OpenSSL";
  private static Method anK = null;
  private static final Object uf = new Object();
  
  private static void V(Context paramContext)
    throws ClassNotFoundException, NoSuchMethodException
  {
    anK = paramContext.getClassLoader().loadClass("com.google.android.gms.common.security.ProviderInstallerImpl").getMethod("insertProvider", new Class[] { Context.class });
  }
  
  public static void installIfNeeded(Context arg0)
    throws GooglePlayServicesRepairableException, GooglePlayServicesNotAvailableException
  {
    o.b(???, "Context must not be null");
    GooglePlayServicesUtil.D(???);
    Context localContext = GooglePlayServicesUtil.getRemoteContext(???);
    if (localContext == null)
    {
      Log.e("ProviderInstaller", "Failed to get remote context");
      throw new GooglePlayServicesNotAvailableException(8);
    }
    synchronized (uf)
    {
      try
      {
        if (anK == null) {
          V(localContext);
        }
        anK.invoke(null, new Object[] { localContext });
        return;
      }
      catch (Exception localException)
      {
        StringBuilder localStringBuilder = new java/lang/StringBuilder;
        localStringBuilder.<init>();
        Log.e("ProviderInstaller", "Failed to install provider: " + localException.getMessage());
        GooglePlayServicesNotAvailableException localGooglePlayServicesNotAvailableException = new com/google/android/gms/common/GooglePlayServicesNotAvailableException;
        localGooglePlayServicesNotAvailableException.<init>(8);
        throw localGooglePlayServicesNotAvailableException;
      }
    }
  }
  
  public static void installIfNeededAsync(Context paramContext, final ProviderInstallListener paramProviderInstallListener)
  {
    o.b(paramContext, "Context must not be null");
    o.b(paramProviderInstallListener, "Listener must not be null");
    o.aT("Must be called on the UI thread");
    new AsyncTask()
    {
      protected Integer b(Void... paramAnonymousVarArgs)
      {
        try
        {
          ProviderInstaller.installIfNeeded(this.mV);
          paramAnonymousVarArgs = Integer.valueOf(0);
        }
        catch (GooglePlayServicesRepairableException paramAnonymousVarArgs)
        {
          for (;;)
          {
            paramAnonymousVarArgs = Integer.valueOf(paramAnonymousVarArgs.getConnectionStatusCode());
          }
        }
        catch (GooglePlayServicesNotAvailableException paramAnonymousVarArgs)
        {
          for (;;)
          {
            paramAnonymousVarArgs = Integer.valueOf(paramAnonymousVarArgs.errorCode);
          }
        }
        return paramAnonymousVarArgs;
      }
      
      protected void d(Integer paramAnonymousInteger)
      {
        if (paramAnonymousInteger.intValue() == 0) {
          paramProviderInstallListener.onProviderInstalled();
        }
        for (;;)
        {
          return;
          Intent localIntent = GooglePlayServicesUtil.ai(paramAnonymousInteger.intValue());
          paramProviderInstallListener.onProviderInstallFailed(paramAnonymousInteger.intValue(), localIntent);
        }
      }
    }.execute(new Void[0]);
  }
  
  public static abstract interface ProviderInstallListener
  {
    public abstract void onProviderInstallFailed(int paramInt, Intent paramIntent);
    
    public abstract void onProviderInstalled();
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\security\ProviderInstaller.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */