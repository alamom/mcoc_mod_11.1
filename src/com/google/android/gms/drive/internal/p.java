package com.google.android.gms.drive.internal;

import com.google.android.gms.common.api.BaseImplementation.a;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.drive.Drive;

abstract class p<R extends Result>
  extends BaseImplementation.a<R, q>
{
  public p()
  {
    super(Drive.CU);
  }
  
  static abstract class a
    extends p<Status>
  {
    protected Status d(Status paramStatus)
    {
      return paramStatus;
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\drive\internal\p.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */