package com.google.android.gms.wearable.internal;

import android.content.IntentFilter;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class b
  implements SafeParcelable
{
  public static final Parcelable.Creator<b> CREATOR = new c();
  final int BR;
  public final ae avk;
  public final IntentFilter[] avl;
  
  b(int paramInt, IBinder paramIBinder, IntentFilter[] paramArrayOfIntentFilter)
  {
    this.BR = paramInt;
    if (paramIBinder != null) {}
    for (this.avk = ae.a.bS(paramIBinder);; this.avk = null)
    {
      this.avl = paramArrayOfIntentFilter;
      return;
    }
  }
  
  public b(ax paramax)
  {
    this.BR = 1;
    this.avk = paramax;
    this.avl = paramax.qb();
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  IBinder pV()
  {
    if (this.avk == null) {}
    for (IBinder localIBinder = null;; localIBinder = this.avk.asBinder()) {
      return localIBinder;
    }
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    c.a(this, paramParcel, paramInt);
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\wearable\internal\b.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */