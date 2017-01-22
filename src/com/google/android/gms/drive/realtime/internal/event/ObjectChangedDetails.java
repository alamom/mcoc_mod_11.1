package com.google.android.gms.drive.realtime.internal.event;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class ObjectChangedDetails
  implements SafeParcelable
{
  public static final Parcelable.Creator<ObjectChangedDetails> CREATOR = new a();
  final int BR;
  final int Rr;
  final int Rs;
  
  ObjectChangedDetails(int paramInt1, int paramInt2, int paramInt3)
  {
    this.BR = paramInt1;
    this.Rr = paramInt2;
    this.Rs = paramInt3;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    a.a(this, paramParcel, paramInt);
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\drive\realtime\internal\event\ObjectChangedDetails.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */