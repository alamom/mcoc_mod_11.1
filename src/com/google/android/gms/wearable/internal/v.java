package com.google.android.gms.wearable.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.List;

public class v
  implements SafeParcelable
{
  public static final Parcelable.Creator<v> CREATOR = new w();
  public final List<ak> avz;
  public final int statusCode;
  public final int versionCode;
  
  v(int paramInt1, int paramInt2, List<ak> paramList)
  {
    this.versionCode = paramInt1;
    this.statusCode = paramInt2;
    this.avz = paramList;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    w.a(this, paramParcel, paramInt);
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\wearable\internal\v.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */