package com.google.android.gms.fitness.result;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.common.internal.n.a;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.fitness.data.DataType;

public class DataTypeResult
  implements Result, SafeParcelable
{
  public static final Parcelable.Creator<DataTypeResult> CREATOR = new d();
  private final int BR;
  private final Status CM;
  private final DataType Sp;
  
  DataTypeResult(int paramInt, Status paramStatus, DataType paramDataType)
  {
    this.BR = paramInt;
    this.CM = paramStatus;
    this.Sp = paramDataType;
  }
  
  public DataTypeResult(Status paramStatus, DataType paramDataType)
  {
    this.BR = 2;
    this.CM = paramStatus;
    this.Sp = paramDataType;
  }
  
  public static DataTypeResult F(Status paramStatus)
  {
    return new DataTypeResult(paramStatus, null);
  }
  
  private boolean b(DataTypeResult paramDataTypeResult)
  {
    if ((this.CM.equals(paramDataTypeResult.CM)) && (n.equal(this.Sp, paramDataTypeResult.Sp))) {}
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
    if ((this == paramObject) || (((paramObject instanceof DataTypeResult)) && (b((DataTypeResult)paramObject)))) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public DataType getDataType()
  {
    return this.Sp;
  }
  
  public Status getStatus()
  {
    return this.CM;
  }
  
  int getVersionCode()
  {
    return this.BR;
  }
  
  public int hashCode()
  {
    return n.hashCode(new Object[] { this.CM, this.Sp });
  }
  
  public String toString()
  {
    return n.h(this).a("status", this.CM).a("dataType", this.Sp).toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    d.a(this, paramParcel, paramInt);
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\fitness\result\DataTypeResult.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */