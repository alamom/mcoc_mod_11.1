package com.google.android.gms.auth.api;

import android.app.PendingIntent;
import com.google.android.gms.common.api.Status;

public class GoogleAuthApiException
  extends Exception
{
  private Status CM;
  private PendingIntent mPendingIntent;
  
  public GoogleAuthApiException(String paramString, Status paramStatus)
  {
    super(paramString);
    this.CM = paramStatus;
  }
  
  public GoogleAuthApiException(String paramString, Status paramStatus, PendingIntent paramPendingIntent)
  {
    super(paramString);
    this.CM = paramStatus;
    this.mPendingIntent = paramPendingIntent;
  }
  
  public PendingIntent getPendingIntent()
  {
    return this.mPendingIntent;
  }
  
  public Status getStatus()
  {
    return this.CM;
  }
  
  public boolean isUserRecoverable()
  {
    if (this.mPendingIntent != null) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\auth\api\GoogleAuthApiException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */