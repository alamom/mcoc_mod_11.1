package com.google.android.gms.drive.query.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class Operator
  implements SafeParcelable
{
  public static final Parcelable.Creator<Operator> CREATOR = new l();
  public static final Operator QZ = new Operator("=");
  public static final Operator Ra = new Operator("<");
  public static final Operator Rb = new Operator("<=");
  public static final Operator Rc = new Operator(">");
  public static final Operator Rd = new Operator(">=");
  public static final Operator Re = new Operator("and");
  public static final Operator Rf = new Operator("or");
  public static final Operator Rg = new Operator("not");
  public static final Operator Rh = new Operator("contains");
  final int BR;
  final String mTag;
  
  Operator(int paramInt, String paramString)
  {
    this.BR = paramInt;
    this.mTag = paramString;
  }
  
  private Operator(String paramString)
  {
    this(1, paramString);
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
      if (paramObject == null)
      {
        bool = false;
      }
      else if (getClass() != paramObject.getClass())
      {
        bool = false;
      }
      else
      {
        paramObject = (Operator)paramObject;
        if (this.mTag == null)
        {
          if (((Operator)paramObject).mTag != null) {
            bool = false;
          }
        }
        else if (!this.mTag.equals(((Operator)paramObject).mTag)) {
          bool = false;
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
    if (this.mTag == null) {}
    for (int i = 0;; i = this.mTag.hashCode()) {
      return i + 31;
    }
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    l.a(this, paramParcel, paramInt);
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\drive\query\internal\Operator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */