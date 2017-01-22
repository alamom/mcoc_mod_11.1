package com.google.android.gms.drive.events;

import android.os.IBinder;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable.Creator;
import android.os.RemoteException;
import android.text.TextUtils;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.MetadataChangeSet;
import com.google.android.gms.drive.internal.ae;
import com.google.android.gms.drive.internal.ae.a;
import com.google.android.gms.drive.internal.v;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;
import com.google.android.gms.internal.jy;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public final class CompletionEvent
  implements SafeParcelable, ResourceEvent
{
  public static final Parcelable.Creator<CompletionEvent> CREATOR = new b();
  public static final int STATUS_CONFLICT = 2;
  public static final int STATUS_FAILURE = 1;
  public static final int STATUS_SUCCESS = 0;
  final int BR;
  final String Dd;
  final int Fa;
  final DriveId MW;
  final ParcelFileDescriptor NN;
  final ParcelFileDescriptor NO;
  final MetadataBundle NP;
  final ArrayList<String> NQ;
  final IBinder NR;
  private boolean NS = false;
  private boolean NT = false;
  private boolean NU = false;
  
  CompletionEvent(int paramInt1, DriveId paramDriveId, String paramString, ParcelFileDescriptor paramParcelFileDescriptor1, ParcelFileDescriptor paramParcelFileDescriptor2, MetadataBundle paramMetadataBundle, ArrayList<String> paramArrayList, int paramInt2, IBinder paramIBinder)
  {
    this.BR = paramInt1;
    this.MW = paramDriveId;
    this.Dd = paramString;
    this.NN = paramParcelFileDescriptor1;
    this.NO = paramParcelFileDescriptor2;
    this.NP = paramMetadataBundle;
    this.NQ = paramArrayList;
    this.Fa = paramInt2;
    this.NR = paramIBinder;
  }
  
  private void L(boolean paramBoolean)
  {
    hU();
    this.NU = true;
    jy.a(this.NN);
    jy.a(this.NO);
    StringBuilder localStringBuilder;
    if (this.NR == null)
    {
      localStringBuilder = new StringBuilder().append("No callback on ");
      if (paramBoolean)
      {
        str = "snooze";
        v.q("CompletionEvent", str);
      }
    }
    for (;;)
    {
      return;
      str = "dismiss";
      break;
      try
      {
        ae.a.X(this.NR).L(paramBoolean);
      }
      catch (RemoteException localRemoteException)
      {
        localStringBuilder = new StringBuilder().append("RemoteException on ");
        if (!paramBoolean) {}
      }
    }
    for (String str = "snooze";; str = "dismiss")
    {
      v.q("CompletionEvent", str + ": " + localRemoteException);
      break;
    }
  }
  
  private void hU()
  {
    if (this.NU) {
      throw new IllegalStateException("Event has already been dismissed or snoozed.");
    }
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public void dismiss()
  {
    L(false);
  }
  
  public String getAccountName()
  {
    hU();
    return this.Dd;
  }
  
  public InputStream getBaseContentsInputStream()
  {
    hU();
    if (this.NN == null) {}
    for (Object localObject = null;; localObject = new FileInputStream(this.NN.getFileDescriptor()))
    {
      return (InputStream)localObject;
      if (this.NS) {
        throw new IllegalStateException("getBaseInputStream() can only be called once per CompletionEvent instance.");
      }
      this.NS = true;
    }
  }
  
  public DriveId getDriveId()
  {
    hU();
    return this.MW;
  }
  
  public InputStream getModifiedContentsInputStream()
  {
    hU();
    if (this.NO == null) {}
    for (Object localObject = null;; localObject = new FileInputStream(this.NO.getFileDescriptor()))
    {
      return (InputStream)localObject;
      if (this.NT) {
        throw new IllegalStateException("getModifiedInputStream() can only be called once per CompletionEvent instance.");
      }
      this.NT = true;
    }
  }
  
  public MetadataChangeSet getModifiedMetadataChangeSet()
  {
    hU();
    if (this.NP != null) {}
    for (MetadataChangeSet localMetadataChangeSet = new MetadataChangeSet(this.NP);; localMetadataChangeSet = null) {
      return localMetadataChangeSet;
    }
  }
  
  public int getStatus()
  {
    hU();
    return this.Fa;
  }
  
  public List<String> getTrackingTags()
  {
    hU();
    return new ArrayList(this.NQ);
  }
  
  public int getType()
  {
    return 2;
  }
  
  public void snooze()
  {
    L(true);
  }
  
  public String toString()
  {
    if (this.NQ == null) {}
    for (String str = "<null>";; str = "'" + TextUtils.join("','", this.NQ) + "'") {
      return String.format(Locale.US, "CompletionEvent [id=%s, status=%s, trackingTag=%s]", new Object[] { this.MW, Integer.valueOf(this.Fa), str });
    }
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    b.a(this, paramParcel, paramInt);
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\drive\events\CompletionEvent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */