package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.drive.DriveId;
import java.util.List;

public class SetResourceParentsRequest
  implements SafeParcelable
{
  public static final Parcelable.Creator<SetResourceParentsRequest> CREATOR = new ba();
  final int BR;
  final List<DriveId> PA;
  final DriveId Pz;
  
  SetResourceParentsRequest(int paramInt, DriveId paramDriveId, List<DriveId> paramList)
  {
    this.BR = paramInt;
    this.Pz = paramDriveId;
    this.PA = paramList;
  }
  
  public SetResourceParentsRequest(DriveId paramDriveId, List<DriveId> paramList)
  {
    this(1, paramDriveId, paramList);
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    ba.a(this, paramParcel, paramInt);
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\drive\internal\SetResourceParentsRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */