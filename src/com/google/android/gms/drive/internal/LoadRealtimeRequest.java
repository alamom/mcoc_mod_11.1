package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.drive.DriveId;

public class LoadRealtimeRequest
  implements SafeParcelable
{
  public static final Parcelable.Creator<LoadRealtimeRequest> CREATOR = new ag();
  final int BR;
  final DriveId MW;
  final boolean Pk;
  
  LoadRealtimeRequest(int paramInt, DriveId paramDriveId, boolean paramBoolean)
  {
    this.BR = paramInt;
    this.MW = paramDriveId;
    this.Pk = paramBoolean;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    ag.a(this, paramParcel, paramInt);
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\drive\internal\LoadRealtimeRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */