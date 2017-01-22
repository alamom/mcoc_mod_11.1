package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.ads.mediation.MediationServerParameters;
import com.google.android.gms.ads.mediation.customevent.CustomEvent;
import java.util.Map;

@ez
public final class cs
  extends ct.a
{
  private Map<Class<? extends com.google.android.gms.ads.mediation.NetworkExtras>, com.google.android.gms.ads.mediation.NetworkExtras> qC;
  
  private <NETWORK_EXTRAS extends com.google.ads.mediation.NetworkExtras, SERVER_PARAMETERS extends MediationServerParameters> cu z(String paramString)
    throws RemoteException
  {
    try
    {
      Object localObject = Class.forName(paramString, false, cs.class.getClassLoader());
      if (com.google.ads.mediation.MediationAdapter.class.isAssignableFrom((Class)localObject))
      {
        localObject = (com.google.ads.mediation.MediationAdapter)((Class)localObject).newInstance();
        localObject = new cz((com.google.ads.mediation.MediationAdapter)localObject, (com.google.ads.mediation.NetworkExtras)this.qC.get(((com.google.ads.mediation.MediationAdapter)localObject).getAdditionalParametersType()));
      }
      for (paramString = (String)localObject;; paramString = (String)localObject)
      {
        return paramString;
        if (!com.google.android.gms.ads.mediation.MediationAdapter.class.isAssignableFrom((Class)localObject)) {
          break;
        }
        localObject = new cx((com.google.android.gms.ads.mediation.MediationAdapter)((Class)localObject).newInstance());
      }
      localObject = new java/lang/StringBuilder;
      ((StringBuilder)localObject).<init>();
      gs.W("Could not instantiate mediation adapter: " + paramString + " (not a valid adapter).");
      localObject = new android/os/RemoteException;
      ((RemoteException)localObject).<init>();
      throw ((Throwable)localObject);
    }
    catch (Throwable localThrowable)
    {
      gs.W("Could not instantiate mediation adapter: " + paramString + ". " + localThrowable.getMessage());
      throw new RemoteException();
    }
  }
  
  public void d(Map<Class<? extends com.google.android.gms.ads.mediation.NetworkExtras>, com.google.android.gms.ads.mediation.NetworkExtras> paramMap)
  {
    this.qC = paramMap;
  }
  
  public cu x(String paramString)
    throws RemoteException
  {
    return z(paramString);
  }
  
  public boolean y(String paramString)
    throws RemoteException
  {
    boolean bool1 = false;
    try
    {
      boolean bool2 = CustomEvent.class.isAssignableFrom(Class.forName(paramString, false, cs.class.getClassLoader()));
      bool1 = bool2;
    }
    catch (Throwable localThrowable)
    {
      for (;;)
      {
        gs.W("Could not load custom event implementation class: " + paramString + ", assuming old implementation.");
      }
    }
    return bool1;
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\internal\cs.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */