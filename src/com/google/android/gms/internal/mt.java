package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.o;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public final class mt
  implements SafeParcelable
{
  public static final mu CREATOR = new mu();
  public static final mt aic = y("test_type", 1);
  public static final mt aid = y("trellis_store", 2);
  public static final mt aie = y("labeled_place", 6);
  public static final Set<mt> aif = Collections.unmodifiableSet(new HashSet(Arrays.asList(new mt[] { aic, aid, aie })));
  final int BR;
  final int aig;
  final String uO;
  
  mt(int paramInt1, String paramString, int paramInt2)
  {
    o.aZ(paramString);
    this.BR = paramInt1;
    this.uO = paramString;
    this.aig = paramInt2;
  }
  
  private static mt y(String paramString, int paramInt)
  {
    return new mt(0, paramString, paramInt);
  }
  
  public int describeContents()
  {
    mu localmu = CREATOR;
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    boolean bool = true;
    if (this == paramObject) {}
    for (;;)
    {
      return bool;
      if (!(paramObject instanceof mt))
      {
        bool = false;
      }
      else
      {
        paramObject = (mt)paramObject;
        if ((!this.uO.equals(((mt)paramObject).uO)) || (this.aig != ((mt)paramObject).aig)) {
          bool = false;
        }
      }
    }
  }
  
  public int hashCode()
  {
    return this.uO.hashCode();
  }
  
  public String toString()
  {
    return this.uO;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    mu localmu = CREATOR;
    mu.a(this, paramParcel, paramInt);
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\internal\mt.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */