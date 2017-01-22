package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.List;

public class OnResourceIdSetResponse
  implements SafeParcelable
{
  public static final Parcelable.Creator<OnResourceIdSetResponse> CREATOR = new ar();
  private final int BR;
  private final List<String> Oc;
  
  OnResourceIdSetResponse(int paramInt, List<String> paramList)
  {
    this.BR = paramInt;
    this.Oc = paramList;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public int getVersionCode()
  {
    return this.BR;
  }
  
  public List<String> hX()
  {
    return this.Oc;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    ar.a(this, paramParcel, paramInt);
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\drive\internal\OnResourceIdSetResponse.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */