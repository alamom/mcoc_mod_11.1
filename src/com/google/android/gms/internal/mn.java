package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.common.internal.n.a;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.concurrent.TimeUnit;

public final class mn
  implements SafeParcelable
{
  public static final mo CREATOR = new mo();
  static final long afA = TimeUnit.HOURS.toMillis(1L);
  final int BR;
  private final long aes;
  private final mj afB;
  private final int mPriority;
  
  public mn(int paramInt1, mj parammj, long paramLong, int paramInt2)
  {
    this.BR = paramInt1;
    this.afB = parammj;
    this.aes = paramLong;
    this.mPriority = paramInt2;
  }
  
  public int describeContents()
  {
    mo localmo = CREATOR;
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    boolean bool = true;
    if (this == paramObject) {}
    for (;;)
    {
      return bool;
      if (!(paramObject instanceof mn))
      {
        bool = false;
      }
      else
      {
        paramObject = (mn)paramObject;
        if ((!n.equal(this.afB, ((mn)paramObject).afB)) || (this.aes != ((mn)paramObject).aes) || (this.mPriority != ((mn)paramObject).mPriority)) {
          bool = false;
        }
      }
    }
  }
  
  public long getInterval()
  {
    return this.aes;
  }
  
  public int getPriority()
  {
    return this.mPriority;
  }
  
  public int hashCode()
  {
    return n.hashCode(new Object[] { this.afB, Long.valueOf(this.aes), Integer.valueOf(this.mPriority) });
  }
  
  public mj mh()
  {
    return this.afB;
  }
  
  public String toString()
  {
    return n.h(this).a("filter", this.afB).a("interval", Long.valueOf(this.aes)).a("priority", Integer.valueOf(this.mPriority)).toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    mo localmo = CREATOR;
    mo.a(this, paramParcel, paramInt);
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\internal\mn.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */