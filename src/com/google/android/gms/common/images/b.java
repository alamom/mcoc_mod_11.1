package com.google.android.gms.common.images;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;

public class b
  implements Parcelable.Creator<WebImage>
{
  static void a(WebImage paramWebImage, Parcel paramParcel, int paramInt)
  {
    int i = com.google.android.gms.common.internal.safeparcel.b.D(paramParcel);
    com.google.android.gms.common.internal.safeparcel.b.c(paramParcel, 1, paramWebImage.getVersionCode());
    com.google.android.gms.common.internal.safeparcel.b.a(paramParcel, 2, paramWebImage.getUrl(), paramInt, false);
    com.google.android.gms.common.internal.safeparcel.b.c(paramParcel, 3, paramWebImage.getWidth());
    com.google.android.gms.common.internal.safeparcel.b.c(paramParcel, 4, paramWebImage.getHeight());
    com.google.android.gms.common.internal.safeparcel.b.H(paramParcel, i);
  }
  
  public WebImage A(Parcel paramParcel)
  {
    int i = 0;
    int n = a.C(paramParcel);
    Uri localUri = null;
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
        localUri = (Uri)a.a(paramParcel, m, Uri.CREATOR);
        m = j;
        j = k;
        k = m;
        continue;
        m = a.g(paramParcel, m);
        k = j;
        j = m;
        continue;
        i = a.g(paramParcel, m);
        m = k;
        k = j;
        j = m;
      }
    }
    if (paramParcel.dataPosition() != n) {
      throw new a.a("Overread allowed size end=" + n, paramParcel);
    }
    return new WebImage(j, localUri, k, i);
  }
  
  public WebImage[] ax(int paramInt)
  {
    return new WebImage[paramInt];
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\common\images\b.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */