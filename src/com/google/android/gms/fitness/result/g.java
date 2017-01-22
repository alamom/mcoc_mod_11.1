package com.google.android.gms.fitness.result;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.fitness.data.Session;
import java.util.List;

public class g
  implements Parcelable.Creator<SessionStopResult>
{
  static void a(SessionStopResult paramSessionStopResult, Parcel paramParcel, int paramInt)
  {
    int i = b.D(paramParcel);
    b.c(paramParcel, 1000, paramSessionStopResult.getVersionCode());
    b.a(paramParcel, 2, paramSessionStopResult.getStatus(), paramInt, false);
    b.c(paramParcel, 3, paramSessionStopResult.getSessions(), false);
    b.H(paramParcel, i);
  }
  
  public SessionStopResult cb(Parcel paramParcel)
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
        localObject3 = (Status)a.a(paramParcel, k, Status.CREATOR);
        localObject1 = localObject2;
        localObject2 = localObject3;
        continue;
        localObject3 = a.c(paramParcel, k, Session.CREATOR);
        localObject2 = localObject1;
        localObject1 = localObject3;
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new a.a("Overread allowed size end=" + j, paramParcel);
    }
    return new SessionStopResult(i, (Status)localObject1, (List)localObject2);
  }
  
  public SessionStopResult[] dt(int paramInt)
  {
    return new SessionStopResult[paramInt];
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\fitness\result\g.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */