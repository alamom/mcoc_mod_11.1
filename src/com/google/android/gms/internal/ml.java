package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.common.internal.n.a;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class ml
  implements SafeParcelable
{
  public static final mm CREATOR = new mm();
  final int BR;
  private final String afz;
  private final String mTag;
  
  ml(int paramInt, String paramString1, String paramString2)
  {
    this.BR = paramInt;
    this.afz = paramString1;
    this.mTag = paramString2;
  }
  
  public int describeContents()
  {
    mm localmm = CREATOR;
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    boolean bool2 = false;
    boolean bool1;
    if (!(paramObject instanceof ml)) {
      bool1 = bool2;
    }
    for (;;)
    {
      return bool1;
      paramObject = (ml)paramObject;
      bool1 = bool2;
      if (n.equal(this.afz, ((ml)paramObject).afz))
      {
        bool1 = bool2;
        if (n.equal(this.mTag, ((ml)paramObject).mTag)) {
          bool1 = true;
        }
      }
    }
  }
  
  public String getTag()
  {
    return this.mTag;
  }
  
  public int hashCode()
  {
    return n.hashCode(new Object[] { this.afz, this.mTag });
  }
  
  public String mk()
  {
    return this.afz;
  }
  
  public String toString()
  {
    return n.h(this).a("mPlaceId", this.afz).a("mTag", this.mTag).toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    mm localmm = CREATOR;
    mm.a(this, paramParcel, paramInt);
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\internal\ml.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */