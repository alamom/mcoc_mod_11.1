package com.google.android.gms.games.snapshot;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;

public class SnapshotEntityCreator
  implements Parcelable.Creator<SnapshotEntity>
{
  public static final int CONTENT_DESCRIPTION = 0;
  
  static void a(SnapshotEntity paramSnapshotEntity, Parcel paramParcel, int paramInt)
  {
    int i = b.D(paramParcel);
    b.a(paramParcel, 1, paramSnapshotEntity.getMetadata(), paramInt, false);
    b.c(paramParcel, 1000, paramSnapshotEntity.getVersionCode());
    b.a(paramParcel, 3, paramSnapshotEntity.getSnapshotContents(), paramInt, false);
    b.H(paramParcel, i);
  }
  
  public SnapshotEntity createFromParcel(Parcel paramParcel)
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
        localObject3 = (SnapshotMetadataEntity)a.a(paramParcel, k, SnapshotMetadataEntity.CREATOR);
        localObject1 = localObject2;
        localObject2 = localObject3;
        continue;
        i = a.g(paramParcel, k);
        localObject3 = localObject1;
        localObject1 = localObject2;
        localObject2 = localObject3;
        continue;
        localObject3 = (SnapshotContents)a.a(paramParcel, k, SnapshotContents.CREATOR);
        localObject2 = localObject1;
        localObject1 = localObject3;
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new a.a("Overread allowed size end=" + j, paramParcel);
    }
    return new SnapshotEntity(i, (SnapshotMetadata)localObject1, (SnapshotContents)localObject2);
  }
  
  public SnapshotEntity[] newArray(int paramInt)
  {
    return new SnapshotEntity[paramInt];
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\games\snapshot\SnapshotEntityCreator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */