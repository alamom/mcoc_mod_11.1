package com.google.android.gms.maps.model;

import android.os.Parcel;

public class b
{
  static void a(CameraPosition paramCameraPosition, Parcel paramParcel, int paramInt)
  {
    int i = com.google.android.gms.common.internal.safeparcel.b.D(paramParcel);
    com.google.android.gms.common.internal.safeparcel.b.c(paramParcel, 1, paramCameraPosition.getVersionCode());
    com.google.android.gms.common.internal.safeparcel.b.a(paramParcel, 2, paramCameraPosition.target, paramInt, false);
    com.google.android.gms.common.internal.safeparcel.b.a(paramParcel, 3, paramCameraPosition.zoom);
    com.google.android.gms.common.internal.safeparcel.b.a(paramParcel, 4, paramCameraPosition.tilt);
    com.google.android.gms.common.internal.safeparcel.b.a(paramParcel, 5, paramCameraPosition.bearing);
    com.google.android.gms.common.internal.safeparcel.b.H(paramParcel, i);
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\maps\model\b.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */