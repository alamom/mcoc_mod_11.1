package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.drive.DriveId;

public class au
  implements Parcelable.Creator<OpenContentsRequest>
{
  static void a(OpenContentsRequest paramOpenContentsRequest, Parcel paramParcel, int paramInt)
  {
    int i = b.D(paramParcel);
    b.c(paramParcel, 1, paramOpenContentsRequest.BR);
    b.a(paramParcel, 2, paramOpenContentsRequest.Od, paramInt, false);
    b.c(paramParcel, 3, paramOpenContentsRequest.MV);
    b.c(paramParcel, 4, paramOpenContentsRequest.Px);
    b.H(paramParcel, i);
  }
  
  public OpenContentsRequest aw(Parcel paramParcel)
  {
    int i = 0;
    int n = a.C(paramParcel);
    DriveId localDriveId = null;
    int k = 0;
    int j = 0;
    if (paramParcel.dataPosition() < n)
    {
      int m = a.B(paramParcel);
      switch (a.aD(m))
      {
      default: 
        a.b(paramParcel, m);
        m = k;
        k = j;
        j = m;
      }
      for (;;)
      {
        m = k;
        k = j;
        j = m;
        break;
        m = a.g(paramParcel, m);
        j = k;
        k = m;
        continue;
        localDriveId = (DriveId)a.a(paramParcel, m, DriveId.CREATOR);
        m = j;
        j = k;
        k = m;
        continue;
        m = a.g(paramParcel, m);
        k = j;
        j = m;
        continue;
        i = a.g(paramParcel, m);
        m = j;
        j = k;
        k = m;
      }
    }
    if (paramParcel.dataPosition() != n) {
      throw new a.a("Overread allowed size end=" + n, paramParcel);
    }
    return new OpenContentsRequest(j, localDriveId, k, i);
  }
  
  public OpenContentsRequest[] bI(int paramInt)
  {
    return new OpenContentsRequest[paramInt];
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\drive\internal\au.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */