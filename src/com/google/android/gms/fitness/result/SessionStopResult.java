package com.google.android.gms.fitness.result;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.common.internal.n.a;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.fitness.data.Session;
import java.util.Collections;
import java.util.List;

public class SessionStopResult
  implements Result, SafeParcelable
{
  public static final Parcelable.Creator<SessionStopResult> CREATOR = new g();
  private final int BR;
  private final Status CM;
  private final List<Session> Ul;
  
  SessionStopResult(int paramInt, Status paramStatus, List<Session> paramList)
  {
    this.BR = paramInt;
    this.CM = paramStatus;
    this.Ul = Collections.unmodifiableList(paramList);
  }
  
  public SessionStopResult(Status paramStatus, List<Session> paramList)
  {
    this.BR = 3;
    this.CM = paramStatus;
    this.Ul = Collections.unmodifiableList(paramList);
  }
  
  public static SessionStopResult I(Status paramStatus)
  {
    return new SessionStopResult(paramStatus, Collections.emptyList());
  }
  
  private boolean b(SessionStopResult paramSessionStopResult)
  {
    if ((this.CM.equals(paramSessionStopResult.CM)) && (n.equal(this.Ul, paramSessionStopResult.Ul))) {}
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
    if ((this == paramObject) || (((paramObject instanceof SessionStopResult)) && (b((SessionStopResult)paramObject)))) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public List<Session> getSessions()
  {
    return this.Ul;
  }
  
  public Status getStatus()
  {
    return this.CM;
  }
  
  int getVersionCode()
  {
    return this.BR;
  }
  
  public int hashCode()
  {
    return n.hashCode(new Object[] { this.CM, this.Ul });
  }
  
  public String toString()
  {
    return n.h(this).a("status", this.CM).a("sessions", this.Ul).toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    g.a(this, paramParcel, paramInt);
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\fitness\result\SessionStopResult.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */