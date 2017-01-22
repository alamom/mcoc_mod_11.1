package com.google.android.gms.fitness.result;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.fitness.data.DataType;

public class d
  implements Parcelable.Creator<DataTypeResult>
{
  static void a(DataTypeResult paramDataTypeResult, Parcel paramParcel, int paramInt)
  {
    int i = b.D(paramParcel);
    b.a(paramParcel, 1, paramDataTypeResult.getStatus(), paramInt, false);
    b.c(paramParcel, 1000, paramDataTypeResult.getVersionCode());
    b.a(paramParcel, 3, paramDataTypeResult.getDataType(), paramInt, false);
    b.H(paramParcel, i);
  }
  
  public DataTypeResult bY(Parcel paramParcel)
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
        localObject3 = (Status)a.a(paramParcel, k, Status.CREATOR);
        localObject1 = localObject2;
        localObject2 = localObject3;
        continue;
        i = a.g(paramParcel, k);
        localObject3 = localObject1;
        localObject1 = localObject2;
        localObject2 = localObject3;
        continue;
        localObject3 = (DataType)a.a(paramParcel, k, DataType.CREATOR);
        localObject2 = localObject1;
        localObject1 = localObject3;
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new a.a("Overread allowed size end=" + j, paramParcel);
    }
    return new DataTypeResult(i, (Status)localObject1, (DataType)localObject2);
  }
  
  public DataTypeResult[] dq(int paramInt)
  {
    return new DataTypeResult[paramInt];
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\fitness\result\d.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */