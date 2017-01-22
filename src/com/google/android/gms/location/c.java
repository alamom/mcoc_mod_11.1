package com.google.android.gms.location;

import android.os.Parcel;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class c
  implements SafeParcelable
{
  public static final d CREATOR = new d();
  private final int BR;
  int aex;
  int aey;
  long aez;
  
  c(int paramInt1, int paramInt2, int paramInt3, long paramLong)
  {
    this.BR = paramInt1;
    this.aex = paramInt2;
    this.aey = paramInt3;
    this.aez = paramLong;
  }
  
  private String ee(int paramInt)
  {
    String str;
    switch (paramInt)
    {
    case 1: 
    default: 
      str = "STATUS_UNKNOWN";
    }
    for (;;)
    {
      return str;
      str = "STATUS_SUCCESSFUL";
      continue;
      str = "STATUS_TIMED_OUT_ON_SCAN";
      continue;
      str = "STATUS_NO_INFO_IN_DATABASE";
      continue;
      str = "STATUS_INVALID_SCAN";
      continue;
      str = "STATUS_UNABLE_TO_QUERY_DATABASE";
      continue;
      str = "STATUS_SCANS_DISABLED_IN_SETTINGS";
      continue;
      str = "STATUS_LOCATION_DISABLED_IN_SETTINGS";
      continue;
      str = "STATUS_IN_PROGRESS";
    }
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    boolean bool2 = false;
    boolean bool1;
    if (!(paramObject instanceof c)) {
      bool1 = bool2;
    }
    for (;;)
    {
      return bool1;
      paramObject = (c)paramObject;
      bool1 = bool2;
      if (this.aex == ((c)paramObject).aex)
      {
        bool1 = bool2;
        if (this.aey == ((c)paramObject).aey)
        {
          bool1 = bool2;
          if (this.aez == ((c)paramObject).aez) {
            bool1 = true;
          }
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
    return n.hashCode(new Object[] { Integer.valueOf(this.aex), Integer.valueOf(this.aey), Long.valueOf(this.aez) });
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("LocationStatus[cell status: ").append(ee(this.aex));
    localStringBuilder.append(", wifi status: ").append(ee(this.aey));
    localStringBuilder.append(", elapsed realtime ns: ").append(this.aez);
    localStringBuilder.append(']');
    return localStringBuilder.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    d.a(this, paramParcel, paramInt);
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\location\c.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */