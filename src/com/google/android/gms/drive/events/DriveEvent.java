package com.google.android.gms.drive.events;

import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public abstract interface DriveEvent
  extends SafeParcelable
{
  public abstract int getType();
  
  public static abstract interface Listener<E extends DriveEvent>
    extends c
  {
    public abstract void onEvent(E paramE);
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\drive\events\DriveEvent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */