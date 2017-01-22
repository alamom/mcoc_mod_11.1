package com.google.android.gms.common.api;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.IntentSender.SendIntentException;
import android.os.Parcel;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.common.internal.n.a;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class Status
  implements Result, SafeParcelable
{
  public static final StatusCreator CREATOR = new StatusCreator();
  public static final Status Jv = new Status(0);
  public static final Status Jw = new Status(14);
  public static final Status Jx = new Status(8);
  public static final Status Jy = new Status(15);
  public static final Status Jz = new Status(16);
  private final int BR;
  private final int HF;
  private final String JA;
  private final PendingIntent mPendingIntent;
  
  public Status(int paramInt)
  {
    this(paramInt, null);
  }
  
  Status(int paramInt1, int paramInt2, String paramString, PendingIntent paramPendingIntent)
  {
    this.BR = paramInt1;
    this.HF = paramInt2;
    this.JA = paramString;
    this.mPendingIntent = paramPendingIntent;
  }
  
  public Status(int paramInt, String paramString)
  {
    this(1, paramInt, paramString, null);
  }
  
  public Status(int paramInt, String paramString, PendingIntent paramPendingIntent)
  {
    this(1, paramInt, paramString, paramPendingIntent);
  }
  
  private String fX()
  {
    if (this.JA != null) {}
    for (String str = this.JA;; str = CommonStatusCodes.getStatusCodeString(this.HF)) {
      return str;
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
    if (!(paramObject instanceof Status)) {
      bool1 = bool2;
    }
    for (;;)
    {
      return bool1;
      paramObject = (Status)paramObject;
      bool1 = bool2;
      if (this.BR == ((Status)paramObject).BR)
      {
        bool1 = bool2;
        if (this.HF == ((Status)paramObject).HF)
        {
          bool1 = bool2;
          if (n.equal(this.JA, ((Status)paramObject).JA))
          {
            bool1 = bool2;
            if (n.equal(this.mPendingIntent, ((Status)paramObject).mPendingIntent)) {
              bool1 = true;
            }
          }
        }
      }
    }
  }
  
  PendingIntent getPendingIntent()
  {
    return this.mPendingIntent;
  }
  
  public PendingIntent getResolution()
  {
    return this.mPendingIntent;
  }
  
  public Status getStatus()
  {
    return this;
  }
  
  public int getStatusCode()
  {
    return this.HF;
  }
  
  public String getStatusMessage()
  {
    return this.JA;
  }
  
  int getVersionCode()
  {
    return this.BR;
  }
  
  @Deprecated
  public ConnectionResult gt()
  {
    return new ConnectionResult(this.HF, this.mPendingIntent);
  }
  
  public boolean hasResolution()
  {
    if (this.mPendingIntent != null) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public int hashCode()
  {
    return n.hashCode(new Object[] { Integer.valueOf(this.BR), Integer.valueOf(this.HF), this.JA, this.mPendingIntent });
  }
  
  public boolean isCanceled()
  {
    if (this.HF == 16) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public boolean isInterrupted()
  {
    if (this.HF == 14) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public boolean isSuccess()
  {
    if (this.HF <= 0) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public void startResolutionForResult(Activity paramActivity, int paramInt)
    throws IntentSender.SendIntentException
  {
    if (!hasResolution()) {}
    for (;;)
    {
      return;
      paramActivity.startIntentSenderForResult(this.mPendingIntent.getIntentSender(), paramInt, null, 0, 0, 0);
    }
  }
  
  public String toString()
  {
    return n.h(this).a("statusCode", fX()).a("resolution", this.mPendingIntent).toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    StatusCreator.a(this, paramParcel, paramInt);
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\common\api\Status.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */