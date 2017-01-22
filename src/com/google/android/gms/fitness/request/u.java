package com.google.android.gms.fitness.request;

import android.app.PendingIntent;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.common.internal.n.a;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class u
  implements SafeParcelable
{
  public static final Parcelable.Creator<u> CREATOR = new v();
  private final int BR;
  private final PendingIntent mPendingIntent;
  
  u(int paramInt, PendingIntent paramPendingIntent)
  {
    this.BR = paramInt;
    this.mPendingIntent = paramPendingIntent;
  }
  
  public u(PendingIntent paramPendingIntent)
  {
    this.BR = 3;
    this.mPendingIntent = paramPendingIntent;
  }
  
  private boolean a(u paramu)
  {
    return n.equal(this.mPendingIntent, paramu.mPendingIntent);
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    if ((this == paramObject) || (((paramObject instanceof u)) && (a((u)paramObject)))) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  int getVersionCode()
  {
    return this.BR;
  }
  
  public int hashCode()
  {
    return n.hashCode(new Object[] { this.mPendingIntent });
  }
  
  public PendingIntent jr()
  {
    return this.mPendingIntent;
  }
  
  public String toString()
  {
    return n.h(this).a("pendingIntent", this.mPendingIntent).toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    v.a(this, paramParcel, paramInt);
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\fitness\request\u.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */