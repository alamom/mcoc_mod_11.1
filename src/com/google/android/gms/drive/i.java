package com.google.android.gms.drive;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.o;

public abstract class i
  implements Parcelable
{
  private volatile transient boolean NL = false;
  
  protected abstract void I(Parcel paramParcel, int paramInt);
  
  public final boolean hT()
  {
    return this.NL;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    if (!hT()) {}
    for (boolean bool = true;; bool = false)
    {
      o.I(bool);
      this.NL = true;
      I(paramParcel, paramInt);
      return;
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\drive\i.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */