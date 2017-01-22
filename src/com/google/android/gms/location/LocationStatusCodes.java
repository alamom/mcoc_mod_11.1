package com.google.android.gms.location;

import com.google.android.gms.common.api.Status;

@Deprecated
public final class LocationStatusCodes
{
  public static final int ERROR = 1;
  public static final int GEOFENCE_NOT_AVAILABLE = 1000;
  public static final int GEOFENCE_TOO_MANY_GEOFENCES = 1001;
  public static final int GEOFENCE_TOO_MANY_PENDING_INTENTS = 1002;
  public static final int SUCCESS = 0;
  
  public static int ef(int paramInt)
  {
    if (paramInt >= 0)
    {
      i = paramInt;
      if (paramInt <= 1) {}
    }
    else
    {
      if ((1000 > paramInt) || (paramInt > 1002)) {
        break label29;
      }
    }
    label29:
    for (int i = paramInt;; i = 1) {
      return i;
    }
  }
  
  public static Status eg(int paramInt)
  {
    switch (paramInt)
    {
    }
    for (;;)
    {
      return new Status(paramInt);
      paramInt = 13;
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\location\LocationStatusCodes.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */