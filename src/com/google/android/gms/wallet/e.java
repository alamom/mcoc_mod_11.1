package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;

public class e
  implements Parcelable.Creator<d>
{
  static void a(d paramd, Parcel paramParcel, int paramInt)
  {
    int i = b.D(paramParcel);
    b.c(paramParcel, 1, paramd.getVersionCode());
    b.a(paramParcel, 2, paramd.asz, paramInt, false);
    b.a(paramParcel, 3, paramd.asA, paramInt, false);
    b.H(paramParcel, i);
  }
  
  public d dq(Parcel paramParcel)
  {
    Object localObject2 = null;
    int j = a.C(paramParcel);
    int i = 0;
    Object localObject1 = null;
    if (paramParcel.dataPosition() < j)
    {
      int k = a.B(paramParcel);
      Object localObject3;
      switch (a.aD(k))
      {
      default: 
        a.b(paramParcel, k);
        localObject3 = localObject2;
        localObject2 = localObject1;
        localObject1 = localObject3;
      }
      for (;;)
      {
        localObject3 = localObject2;
        localObject2 = localObject1;
        localObject1 = localObject3;
        break;
        i = a.g(paramParcel, k);
        localObject3 = localObject1;
        localObject1 = localObject2;
        localObject2 = localObject3;
        continue;
        localObject3 = (LoyaltyWalletObject)a.a(paramParcel, k, LoyaltyWalletObject.CREATOR);
        localObject1 = localObject2;
        localObject2 = localObject3;
        continue;
        localObject3 = (OfferWalletObject)a.a(paramParcel, k, OfferWalletObject.CREATOR);
        localObject2 = localObject1;
        localObject1 = localObject3;
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new a.a("Overread allowed size end=" + j, paramParcel);
    }
    return new d(i, (LoyaltyWalletObject)localObject1, (OfferWalletObject)localObject2);
  }
  
  public d[] fr(int paramInt)
  {
    return new d[paramInt];
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\wallet\e.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */