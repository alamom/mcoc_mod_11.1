package com.google.android.gms.fitness.request;

import android.os.RemoteException;
import com.google.android.gms.common.internal.o;
import com.google.android.gms.fitness.data.BleDevice;
import java.util.HashMap;
import java.util.Map;

public class a
  extends l.a
{
  private final BleScanCallback Uf;
  
  private a(BleScanCallback paramBleScanCallback)
  {
    this.Uf = ((BleScanCallback)o.i(paramBleScanCallback));
  }
  
  public void onDeviceFound(BleDevice paramBleDevice)
    throws RemoteException
  {
    this.Uf.onDeviceFound(paramBleDevice);
  }
  
  public void onScanStopped()
    throws RemoteException
  {
    this.Uf.onScanStopped();
  }
  
  public static class a
  {
    private static final a Ug = new a();
    private final Map<BleScanCallback, a> Uh = new HashMap();
    
    public static a je()
    {
      return Ug;
    }
    
    public a a(BleScanCallback paramBleScanCallback)
    {
      synchronized (this.Uh)
      {
        a locala2 = (a)this.Uh.get(paramBleScanCallback);
        a locala1 = locala2;
        if (locala2 == null)
        {
          locala1 = new com/google/android/gms/fitness/request/a;
          locala1.<init>(paramBleScanCallback, null);
          this.Uh.put(paramBleScanCallback, locala1);
        }
        return locala1;
      }
    }
    
    public a b(BleScanCallback paramBleScanCallback)
    {
      synchronized (this.Uh)
      {
        a locala = (a)this.Uh.get(paramBleScanCallback);
        if (locala != null)
        {
          paramBleScanCallback = locala;
          return paramBleScanCallback;
        }
        locala = new com/google/android/gms/fitness/request/a;
        locala.<init>(paramBleScanCallback, null);
        paramBleScanCallback = locala;
      }
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\fitness\request\a.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */