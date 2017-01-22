package com.google.android.gms.fitness.service;

import android.os.RemoteException;
import com.google.android.gms.fitness.data.DataPoint;
import java.util.List;

public abstract interface SensorEventDispatcher
{
  public abstract void publish(DataPoint paramDataPoint)
    throws RemoteException;
  
  public abstract void publish(List<DataPoint> paramList)
    throws RemoteException;
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\fitness\service\SensorEventDispatcher.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */