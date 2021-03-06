package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.fitness.data.DataSource;

public class li
  implements SafeParcelable
{
  public static final Parcelable.Creator<li> CREATOR = new lj();
  private final int BR;
  private final DataSource Sq;
  
  li(int paramInt, DataSource paramDataSource)
  {
    this.BR = paramInt;
    this.Sq = paramDataSource;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public DataSource getDataSource()
  {
    return this.Sq;
  }
  
  int getVersionCode()
  {
    return this.BR;
  }
  
  public String toString()
  {
    return String.format("ApplicationUnregistrationRequest{%s}", new Object[] { this.Sq });
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    lj.a(this, paramParcel, paramInt);
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\internal\li.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */