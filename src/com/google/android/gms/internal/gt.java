package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@ez
public final class gt
  implements SafeParcelable
{
  public static final gu CREATOR = new gu();
  public final int versionCode;
  public String wD;
  public int wE;
  public int wF;
  public boolean wG;
  
  public gt(int paramInt1, int paramInt2, boolean paramBoolean) {}
  
  gt(int paramInt1, String paramString, int paramInt2, int paramInt3, boolean paramBoolean)
  {
    this.versionCode = paramInt1;
    this.wD = paramString;
    this.wE = paramInt2;
    this.wF = paramInt3;
    this.wG = paramBoolean;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    gu.a(this, paramParcel, paramInt);
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\internal\gt.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */