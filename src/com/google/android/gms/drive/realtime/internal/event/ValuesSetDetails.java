package com.google.android.gms.drive.realtime.internal.event;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class ValuesSetDetails
  implements SafeParcelable
{
  public static final Parcelable.Creator<ValuesSetDetails> CREATOR = new j();
  final int BR;
  final int Rr;
  final int Rs;
  final int mIndex;
  
  ValuesSetDetails(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    this.BR = paramInt1;
    this.mIndex = paramInt2;
    this.Rr = paramInt3;
    this.Rs = paramInt4;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    j.a(this, paramParcel, paramInt);
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\drive\realtime\internal\event\ValuesSetDetails.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */