package com.google.android.gms.wearable.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.wearable.MessageEvent;

public class ah
  implements SafeParcelable, MessageEvent
{
  public static final Parcelable.Creator<ah> CREATOR = new ai();
  final int BR;
  private final byte[] acH;
  private final String avH;
  private final String avI;
  private final int uQ;
  
  ah(int paramInt1, int paramInt2, String paramString1, byte[] paramArrayOfByte, String paramString2)
  {
    this.BR = paramInt1;
    this.uQ = paramInt2;
    this.avH = paramString1;
    this.acH = paramArrayOfByte;
    this.avI = paramString2;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public byte[] getData()
  {
    return this.acH;
  }
  
  public String getPath()
  {
    return this.avH;
  }
  
  public int getRequestId()
  {
    return this.uQ;
  }
  
  public String getSourceNodeId()
  {
    return this.avI;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder().append("MessageEventParcelable[").append(this.uQ).append(",").append(this.avH).append(", size=");
    if (this.acH == null) {}
    for (Object localObject = "null";; localObject = Integer.valueOf(this.acH.length)) {
      return localObject + "]";
    }
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    ai.a(this, paramParcel, paramInt);
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\wearable\internal\ah.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */