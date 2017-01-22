package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class ls
  implements SafeParcelable
{
  public static final lt CREATOR = new lt();
  private final int BR;
  public final String packageName;
  public final int uid;
  
  ls(int paramInt1, int paramInt2, String paramString)
  {
    this.BR = paramInt1;
    this.uid = paramInt2;
    this.packageName = paramString;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    boolean bool2 = false;
    boolean bool1;
    if (!(paramObject instanceof ls)) {
      bool1 = bool2;
    }
    for (;;)
    {
      return bool1;
      paramObject = (ls)paramObject;
      bool1 = bool2;
      if (((ls)paramObject).uid == this.uid)
      {
        bool1 = bool2;
        if (n.equal(((ls)paramObject).packageName, this.packageName)) {
          bool1 = true;
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
    return this.uid;
  }
  
  public String toString()
  {
    return String.format("%d:%s", new Object[] { Integer.valueOf(this.uid), this.packageName });
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    lt.a(this, paramParcel, paramInt);
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\internal\ls.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */