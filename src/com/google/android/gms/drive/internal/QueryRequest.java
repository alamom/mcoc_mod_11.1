package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.drive.query.Query;

public class QueryRequest
  implements SafeParcelable
{
  public static final Parcelable.Creator<QueryRequest> CREATOR = new ax();
  final int BR;
  final Query Py;
  
  QueryRequest(int paramInt, Query paramQuery)
  {
    this.BR = paramInt;
    this.Py = paramQuery;
  }
  
  public QueryRequest(Query paramQuery)
  {
    this(1, paramQuery);
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    ax.a(this, paramParcel, paramInt);
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\drive\internal\QueryRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */