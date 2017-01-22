package com.google.android.gms.maps.model;

import android.os.Parcel;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.common.internal.n.a;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class StreetViewPanoramaLink
  implements SafeParcelable
{
  public static final r CREATOR = new r();
  private final int BR;
  public final float bearing;
  public final String panoId;
  
  StreetViewPanoramaLink(int paramInt, String paramString, float paramFloat)
  {
    this.BR = paramInt;
    this.panoId = paramString;
    float f = paramFloat;
    if (paramFloat <= 0.0D) {
      f = paramFloat % 360.0F + 360.0F;
    }
    this.bearing = (f % 360.0F);
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    boolean bool = true;
    if (this == paramObject) {}
    for (;;)
    {
      return bool;
      if (!(paramObject instanceof StreetViewPanoramaLink))
      {
        bool = false;
      }
      else
      {
        paramObject = (StreetViewPanoramaLink)paramObject;
        if ((!this.panoId.equals(((StreetViewPanoramaLink)paramObject).panoId)) || (Float.floatToIntBits(this.bearing) != Float.floatToIntBits(((StreetViewPanoramaLink)paramObject).bearing))) {
          bool = false;
        }
      }
    }
  }
  
  int getVersionCode()
  {
    return this.BR;
  }
  
  public int hashCode()
  {
    return n.hashCode(new Object[] { this.panoId, Float.valueOf(this.bearing) });
  }
  
  public String toString()
  {
    return n.h(this).a("panoId", this.panoId).a("bearing", Float.valueOf(this.bearing)).toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    r.a(this, paramParcel, paramInt);
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\maps\model\StreetViewPanoramaLink.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */