package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.drive.Contents;

public class CloseContentsRequest
  implements SafeParcelable
{
  public static final Parcelable.Creator<CloseContentsRequest> CREATOR = new f();
  final int BR;
  final Contents Of;
  final Boolean Oh;
  
  CloseContentsRequest(int paramInt, Contents paramContents, Boolean paramBoolean)
  {
    this.BR = paramInt;
    this.Of = paramContents;
    this.Oh = paramBoolean;
  }
  
  public CloseContentsRequest(Contents paramContents, boolean paramBoolean)
  {
    this(1, paramContents, Boolean.valueOf(paramBoolean));
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    f.a(this, paramParcel, paramInt);
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\drive\internal\CloseContentsRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */