package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.common.internal.n.a;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class mh
  implements SafeParcelable
{
  public static final mi CREATOR = new mi();
  private final int BR;
  private final int aeh;
  private final int afp;
  private final mj afq;
  
  mh(int paramInt1, int paramInt2, int paramInt3, mj parammj)
  {
    this.BR = paramInt1;
    this.aeh = paramInt2;
    this.afp = paramInt3;
    this.afq = parammj;
  }
  
  public int describeContents()
  {
    mi localmi = CREATOR;
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    boolean bool = true;
    if (this == paramObject) {}
    for (;;)
    {
      return bool;
      if (!(paramObject instanceof mh))
      {
        bool = false;
      }
      else
      {
        paramObject = (mh)paramObject;
        if ((this.aeh != ((mh)paramObject).aeh) || (this.afp != ((mh)paramObject).afp) || (!this.afq.equals(((mh)paramObject).afq))) {
          bool = false;
        }
      }
    }
  }
  
  public int getVersionCode()
  {
    return this.BR;
  }
  
  public int hashCode()
  {
    return n.hashCode(new Object[] { Integer.valueOf(this.aeh), Integer.valueOf(this.afp) });
  }
  
  public int mc()
  {
    return this.aeh;
  }
  
  public int mg()
  {
    return this.afp;
  }
  
  public mj mh()
  {
    return this.afq;
  }
  
  public String toString()
  {
    return n.h(this).a("transitionTypes", Integer.valueOf(this.aeh)).a("loiteringTimeMillis", Integer.valueOf(this.afp)).a("placeFilter", this.afq).toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    mi localmi = CREATOR;
    mi.a(this, paramParcel, paramInt);
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\internal\mh.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */