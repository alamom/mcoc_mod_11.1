package com.google.android.gms.fitness.request;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class ah
  implements SafeParcelable
{
  public static final Parcelable.Creator<ah> CREATOR = new ai();
  private final int BR;
  private final String Ui;
  
  ah(int paramInt, String paramString)
  {
    this.BR = paramInt;
    this.Ui = paramString;
  }
  
  public ah(String paramString)
  {
    this(3, paramString);
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public String getDeviceAddress()
  {
    return this.Ui;
  }
  
  int getVersionCode()
  {
    return this.BR;
  }
  
  public String toString()
  {
    return String.format("UnclaimBleDeviceRequest{%s}", new Object[] { this.Ui });
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    ai.a(this, paramParcel, paramInt);
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\fitness\request\ah.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */