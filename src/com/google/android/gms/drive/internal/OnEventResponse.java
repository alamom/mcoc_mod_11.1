package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.drive.events.ChangeEvent;
import com.google.android.gms.drive.events.CompletionEvent;
import com.google.android.gms.drive.events.DriveEvent;

public class OnEventResponse
  implements SafeParcelable
{
  public static final Parcelable.Creator<OnEventResponse> CREATOR = new am();
  final int BR;
  final int Oa;
  final ChangeEvent Ps;
  final CompletionEvent Pt;
  
  OnEventResponse(int paramInt1, int paramInt2, ChangeEvent paramChangeEvent, CompletionEvent paramCompletionEvent)
  {
    this.BR = paramInt1;
    this.Oa = paramInt2;
    this.Ps = paramChangeEvent;
    this.Pt = paramCompletionEvent;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public DriveEvent ih()
  {
    switch (this.Oa)
    {
    default: 
      throw new IllegalStateException("Unexpected event type " + this.Oa);
    }
    for (Object localObject = this.Ps;; localObject = this.Pt) {
      return (DriveEvent)localObject;
    }
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    am.a(this, paramParcel, paramInt);
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\drive\internal\OnEventResponse.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */