package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.drive.query.Query;

public class ax
  implements Parcelable.Creator<QueryRequest>
{
  static void a(QueryRequest paramQueryRequest, Parcel paramParcel, int paramInt)
  {
    int i = b.D(paramParcel);
    b.c(paramParcel, 1, paramQueryRequest.BR);
    b.a(paramParcel, 2, paramQueryRequest.Py, paramInt, false);
    b.H(paramParcel, i);
  }
  
  public QueryRequest ay(Parcel paramParcel)
  {
    int j = a.C(paramParcel);
    int i = 0;
    Query localQuery = null;
    while (paramParcel.dataPosition() < j)
    {
      int k = a.B(paramParcel);
      switch (a.aD(k))
      {
      default: 
        a.b(paramParcel, k);
        break;
      case 1: 
        i = a.g(paramParcel, k);
        break;
      case 2: 
        localQuery = (Query)a.a(paramParcel, k, Query.CREATOR);
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new a.a("Overread allowed size end=" + j, paramParcel);
    }
    return new QueryRequest(i, localQuery);
  }
  
  public QueryRequest[] bK(int paramInt)
  {
    return new QueryRequest[paramInt];
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\drive\internal\ax.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */