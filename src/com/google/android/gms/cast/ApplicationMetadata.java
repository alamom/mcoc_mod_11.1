package com.google.android.gms.cast;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.images.WebImage;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.ik;
import java.util.ArrayList;
import java.util.List;

public final class ApplicationMetadata
  implements SafeParcelable
{
  public static final Parcelable.Creator<ApplicationMetadata> CREATOR = new a();
  private final int BR;
  List<WebImage> EA;
  List<String> EB;
  String EC;
  Uri ED;
  String Ez;
  String mName;
  
  private ApplicationMetadata()
  {
    this.BR = 1;
    this.EA = new ArrayList();
    this.EB = new ArrayList();
  }
  
  ApplicationMetadata(int paramInt, String paramString1, String paramString2, List<WebImage> paramList, List<String> paramList1, String paramString3, Uri paramUri)
  {
    this.BR = paramInt;
    this.Ez = paramString1;
    this.mName = paramString2;
    this.EA = paramList;
    this.EB = paramList1;
    this.EC = paramString3;
    this.ED = paramUri;
  }
  
  public boolean areNamespacesSupported(List<String> paramList)
  {
    if ((this.EB != null) && (this.EB.containsAll(paramList))) {}
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
    boolean bool = true;
    if (paramObject == this) {}
    for (;;)
    {
      return bool;
      if (!(paramObject instanceof ApplicationMetadata))
      {
        bool = false;
      }
      else
      {
        paramObject = (ApplicationMetadata)paramObject;
        if ((!ik.a(this.Ez, ((ApplicationMetadata)paramObject).Ez)) || (!ik.a(this.EA, ((ApplicationMetadata)paramObject).EA)) || (!ik.a(this.mName, ((ApplicationMetadata)paramObject).mName)) || (!ik.a(this.EB, ((ApplicationMetadata)paramObject).EB)) || (!ik.a(this.EC, ((ApplicationMetadata)paramObject).EC)) || (!ik.a(this.ED, ((ApplicationMetadata)paramObject).ED))) {
          bool = false;
        }
      }
    }
  }
  
  public Uri fu()
  {
    return this.ED;
  }
  
  public String getApplicationId()
  {
    return this.Ez;
  }
  
  public List<WebImage> getImages()
  {
    return this.EA;
  }
  
  public String getName()
  {
    return this.mName;
  }
  
  public String getSenderAppIdentifier()
  {
    return this.EC;
  }
  
  int getVersionCode()
  {
    return this.BR;
  }
  
  public int hashCode()
  {
    return n.hashCode(new Object[] { Integer.valueOf(this.BR), this.Ez, this.mName, this.EA, this.EB, this.EC, this.ED });
  }
  
  public boolean isNamespaceSupported(String paramString)
  {
    if ((this.EB != null) && (this.EB.contains(paramString))) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public String toString()
  {
    return this.mName;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    a.a(this, paramParcel, paramInt);
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\cast\ApplicationMetadata.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */