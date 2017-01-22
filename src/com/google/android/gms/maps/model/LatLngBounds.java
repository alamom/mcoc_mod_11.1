package com.google.android.gms.maps.model;

import android.os.Parcel;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.common.internal.n.a;
import com.google.android.gms.common.internal.o;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.maps.internal.v;

public final class LatLngBounds
  implements SafeParcelable
{
  public static final g CREATOR = new g();
  private final int BR;
  public final LatLng northeast;
  public final LatLng southwest;
  
  LatLngBounds(int paramInt, LatLng paramLatLng1, LatLng paramLatLng2)
  {
    o.b(paramLatLng1, "null southwest");
    o.b(paramLatLng2, "null northeast");
    if (paramLatLng2.latitude >= paramLatLng1.latitude) {}
    for (boolean bool = true;; bool = false)
    {
      o.b(bool, "southern latitude exceeds northern latitude (%s > %s)", new Object[] { Double.valueOf(paramLatLng1.latitude), Double.valueOf(paramLatLng2.latitude) });
      this.BR = paramInt;
      this.southwest = paramLatLng1;
      this.northeast = paramLatLng2;
      return;
    }
  }
  
  public LatLngBounds(LatLng paramLatLng1, LatLng paramLatLng2)
  {
    this(1, paramLatLng1, paramLatLng2);
  }
  
  private static double b(double paramDouble1, double paramDouble2)
  {
    return (paramDouble1 - paramDouble2 + 360.0D) % 360.0D;
  }
  
  public static Builder builder()
  {
    return new Builder();
  }
  
  private static double c(double paramDouble1, double paramDouble2)
  {
    return (paramDouble2 - paramDouble1 + 360.0D) % 360.0D;
  }
  
  private boolean c(double paramDouble)
  {
    if ((this.southwest.latitude <= paramDouble) && (paramDouble <= this.northeast.latitude)) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  private boolean d(double paramDouble)
  {
    boolean bool2 = true;
    boolean bool1 = false;
    if (this.southwest.longitude <= this.northeast.longitude) {
      if ((this.southwest.longitude <= paramDouble) && (paramDouble <= this.northeast.longitude)) {
        bool1 = bool2;
      }
    }
    for (;;)
    {
      return bool1;
      bool1 = false;
      continue;
      if ((this.southwest.longitude <= paramDouble) || (paramDouble <= this.northeast.longitude)) {
        bool1 = true;
      }
    }
  }
  
  public boolean contains(LatLng paramLatLng)
  {
    if ((c(paramLatLng.latitude)) && (d(paramLatLng.longitude))) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
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
      if (!(paramObject instanceof LatLngBounds))
      {
        bool = false;
      }
      else
      {
        paramObject = (LatLngBounds)paramObject;
        if ((!this.southwest.equals(((LatLngBounds)paramObject).southwest)) || (!this.northeast.equals(((LatLngBounds)paramObject).northeast))) {
          bool = false;
        }
      }
    }
  }
  
  public LatLng getCenter()
  {
    double d2 = (this.southwest.latitude + this.northeast.latitude) / 2.0D;
    double d1 = this.northeast.longitude;
    double d3 = this.southwest.longitude;
    if (d3 <= d1) {}
    for (d1 = (d1 + d3) / 2.0D;; d1 = (d1 + 360.0D + d3) / 2.0D) {
      return new LatLng(d2, d1);
    }
  }
  
  int getVersionCode()
  {
    return this.BR;
  }
  
  public int hashCode()
  {
    return n.hashCode(new Object[] { this.southwest, this.northeast });
  }
  
  public LatLngBounds including(LatLng paramLatLng)
  {
    double d4 = Math.min(this.southwest.latitude, paramLatLng.latitude);
    double d5 = Math.max(this.northeast.latitude, paramLatLng.latitude);
    double d3 = this.northeast.longitude;
    double d2 = this.southwest.longitude;
    double d1 = paramLatLng.longitude;
    if (!d(d1)) {
      if (b(d2, d1) < c(d3, d1)) {
        d2 = d3;
      }
    }
    for (;;)
    {
      return new LatLngBounds(new LatLng(d4, d1), new LatLng(d5, d2));
      d3 = d1;
      d1 = d2;
      d2 = d3;
      continue;
      d1 = d2;
      d2 = d3;
    }
  }
  
  public String toString()
  {
    return n.h(this).a("southwest", this.southwest).a("northeast", this.northeast).toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    if (v.mM()) {
      h.a(this, paramParcel, paramInt);
    }
    for (;;)
    {
      return;
      g.a(this, paramParcel, paramInt);
    }
  }
  
  public static final class Builder
  {
    private double ajY = Double.POSITIVE_INFINITY;
    private double ajZ = Double.NEGATIVE_INFINITY;
    private double aka = NaN.0D;
    private double akb = NaN.0D;
    
    private boolean d(double paramDouble)
    {
      boolean bool2 = true;
      boolean bool1 = false;
      if (this.aka <= this.akb) {
        if ((this.aka <= paramDouble) && (paramDouble <= this.akb)) {
          bool1 = bool2;
        }
      }
      for (;;)
      {
        return bool1;
        bool1 = false;
        continue;
        if ((this.aka <= paramDouble) || (paramDouble <= this.akb)) {
          bool1 = true;
        }
      }
    }
    
    public LatLngBounds build()
    {
      if (!Double.isNaN(this.aka)) {}
      for (boolean bool = true;; bool = false)
      {
        o.a(bool, "no included points");
        return new LatLngBounds(new LatLng(this.ajY, this.aka), new LatLng(this.ajZ, this.akb));
      }
    }
    
    public Builder include(LatLng paramLatLng)
    {
      this.ajY = Math.min(this.ajY, paramLatLng.latitude);
      this.ajZ = Math.max(this.ajZ, paramLatLng.latitude);
      double d = paramLatLng.longitude;
      if (Double.isNaN(this.aka))
      {
        this.aka = d;
        this.akb = d;
      }
      for (;;)
      {
        return this;
        if (!d(d)) {
          if (LatLngBounds.d(this.aka, d) < LatLngBounds.e(this.akb, d)) {
            this.aka = d;
          } else {
            this.akb = d;
          }
        }
      }
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\maps\model\LatLngBounds.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */