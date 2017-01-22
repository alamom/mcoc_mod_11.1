package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.List;

public final class RawDataSet
  implements SafeParcelable
{
  public static final Parcelable.Creator<RawDataSet> CREATOR = new o();
  final int BR;
  final boolean SF;
  final int Tm;
  final int To;
  final List<RawDataPoint> Tp;
  
  RawDataSet(int paramInt1, int paramInt2, int paramInt3, List<RawDataPoint> paramList, boolean paramBoolean)
  {
    this.BR = paramInt1;
    this.Tm = paramInt2;
    this.To = paramInt3;
    this.Tp = paramList;
    this.SF = paramBoolean;
  }
  
  public RawDataSet(DataSet paramDataSet, List<DataSource> paramList, List<DataType> paramList1)
  {
    this.BR = 2;
    this.Tp = paramDataSet.e(paramList);
    this.SF = paramDataSet.iC();
    this.Tm = t.a(paramDataSet.getDataSource(), paramList);
    this.To = t.a(paramDataSet.getDataType(), paramList1);
  }
  
  private boolean a(RawDataSet paramRawDataSet)
  {
    if ((this.Tm == paramRawDataSet.Tm) && (this.To == paramRawDataSet.To) && (this.SF == paramRawDataSet.SF) && (n.equal(this.Tp, paramRawDataSet.Tp))) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    if ((this == paramObject) || (((paramObject instanceof RawDataSet)) && (a((RawDataSet)paramObject)))) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public int hashCode()
  {
    return n.hashCode(new Object[] { Integer.valueOf(this.Tm), Integer.valueOf(this.To) });
  }
  
  public String toString()
  {
    return String.format("RawDataSet{%s@[%s, %s]}", new Object[] { Integer.valueOf(this.Tm), Integer.valueOf(this.To), this.Tp });
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    o.a(this, paramParcel, paramInt);
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\fitness\data\RawDataSet.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */