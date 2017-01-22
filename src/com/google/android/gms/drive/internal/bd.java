package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;

public class bd
  implements Parcelable.Creator<UpdateMetadataRequest>
{
  static void a(UpdateMetadataRequest paramUpdateMetadataRequest, Parcel paramParcel, int paramInt)
  {
    int i = b.D(paramParcel);
    b.c(paramParcel, 1, paramUpdateMetadataRequest.BR);
    b.a(paramParcel, 2, paramUpdateMetadataRequest.Od, paramInt, false);
    b.a(paramParcel, 3, paramUpdateMetadataRequest.Oe, paramInt, false);
    b.H(paramParcel, i);
  }
  
  public UpdateMetadataRequest aD(Parcel paramParcel)
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
        localObject3 = (DriveId)a.a(paramParcel, k, DriveId.CREATOR);
        localObject1 = localObject2;
        localObject2 = localObject3;
        continue;
        localObject3 = (MetadataBundle)a.a(paramParcel, k, MetadataBundle.CREATOR);
        localObject2 = localObject1;
        localObject1 = localObject3;
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new a.a("Overread allowed size end=" + j, paramParcel);
    }
    return new UpdateMetadataRequest(i, (DriveId)localObject1, (MetadataBundle)localObject2);
  }
  
  public UpdateMetadataRequest[] bP(int paramInt)
  {
    return new UpdateMetadataRequest[paramInt];
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\drive\internal\bd.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */