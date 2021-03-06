package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.common.internal.o;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class Value
  implements SafeParcelable
{
  public static final Parcelable.Creator<Value> CREATOR = new u();
  private final int BR;
  private final int Th;
  private boolean Tv;
  private float Tw;
  
  Value(int paramInt)
  {
    this(1, paramInt, false, 0.0F);
  }
  
  Value(int paramInt1, int paramInt2, boolean paramBoolean, float paramFloat)
  {
    this.BR = paramInt1;
    this.Th = paramInt2;
    this.Tv = paramBoolean;
    this.Tw = paramFloat;
  }
  
  private boolean a(Value paramValue)
  {
    boolean bool = true;
    if ((this.Th == paramValue.Th) && (this.Tv == paramValue.Tv)) {
      switch (this.Th)
      {
      default: 
        if (this.Tw != paramValue.Tw) {
          break;
        }
      }
    }
    for (;;)
    {
      return bool;
      if (asInt() != paramValue.asInt())
      {
        bool = false;
        continue;
        if (asFloat() != paramValue.asFloat())
        {
          bool = false;
          continue;
          bool = false;
          continue;
          bool = false;
        }
      }
    }
  }
  
  public float asFloat()
  {
    if (this.Th == 2) {}
    for (boolean bool = true;; bool = false)
    {
      o.a(bool, "Value is not in float format");
      return this.Tw;
    }
  }
  
  public int asInt()
  {
    boolean bool = true;
    if (this.Th == 1) {}
    for (;;)
    {
      o.a(bool, "Value is not in int format");
      return Float.floatToRawIntBits(this.Tw);
      bool = false;
    }
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    if ((this == paramObject) || (((paramObject instanceof Value)) && (a((Value)paramObject)))) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public int getFormat()
  {
    return this.Th;
  }
  
  int getVersionCode()
  {
    return this.BR;
  }
  
  public int hashCode()
  {
    return n.hashCode(new Object[] { Float.valueOf(this.Tw), Integer.valueOf(this.Th), Boolean.valueOf(this.Tv) });
  }
  
  public boolean isSet()
  {
    return this.Tv;
  }
  
  float ja()
  {
    return this.Tw;
  }
  
  public void setFloat(float paramFloat)
  {
    if (this.Th == 2) {}
    for (boolean bool = true;; bool = false)
    {
      o.a(bool, "Attempting to set an float value to a field that is not in FLOAT format.  Please check the data type definition and use the right format.");
      this.Tv = true;
      this.Tw = paramFloat;
      return;
    }
  }
  
  public void setInt(int paramInt)
  {
    if (this.Th == 1) {}
    for (boolean bool = true;; bool = false)
    {
      o.a(bool, "Attempting to set an int value to a field that is not in INT32 format.  Please check the data type definition and use the right format.");
      this.Tv = true;
      this.Tw = Float.intBitsToFloat(paramInt);
      return;
    }
  }
  
  public String toString()
  {
    String str;
    if (!this.Tv) {
      str = "unset";
    }
    for (;;)
    {
      return str;
      switch (this.Th)
      {
      default: 
        str = "unknown";
        break;
      case 1: 
        str = Integer.toString(asInt());
        break;
      case 2: 
        str = Float.toString(asFloat());
      }
    }
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    u.a(this, paramParcel, paramInt);
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\fitness\data\Value.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */