package com.google.android.gms.wearable.internal;

import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.util.Log;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.wearable.DataItem;
import com.google.android.gms.wearable.DataItemAsset;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class m
  implements SafeParcelable, DataItem
{
  public static final Parcelable.Creator<m> CREATOR = new n();
  final int BR;
  private byte[] acH;
  private final Map<String, DataItemAsset> avv;
  private final Uri mUri;
  
  m(int paramInt, Uri paramUri, Bundle paramBundle, byte[] paramArrayOfByte)
  {
    this.BR = paramInt;
    this.mUri = paramUri;
    HashMap localHashMap = new HashMap();
    paramBundle.setClassLoader(DataItemAssetParcelable.class.getClassLoader());
    paramUri = paramBundle.keySet().iterator();
    while (paramUri.hasNext())
    {
      String str = (String)paramUri.next();
      localHashMap.put(str, (DataItemAssetParcelable)paramBundle.getParcelable(str));
    }
    this.avv = localHashMap;
    this.acH = paramArrayOfByte;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public Map<String, DataItemAsset> getAssets()
  {
    return this.avv;
  }
  
  public byte[] getData()
  {
    return this.acH;
  }
  
  public Uri getUri()
  {
    return this.mUri;
  }
  
  public boolean isDataValid()
  {
    return true;
  }
  
  public m m(byte[] paramArrayOfByte)
  {
    this.acH = paramArrayOfByte;
    return this;
  }
  
  public Bundle pT()
  {
    Bundle localBundle = new Bundle();
    localBundle.setClassLoader(DataItemAssetParcelable.class.getClassLoader());
    Iterator localIterator = this.avv.entrySet().iterator();
    while (localIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      localBundle.putParcelable((String)localEntry.getKey(), new DataItemAssetParcelable((DataItemAsset)localEntry.getValue()));
    }
    return localBundle;
  }
  
  public m pZ()
  {
    return this;
  }
  
  public String toString()
  {
    return toString(Log.isLoggable("DataItem", 3));
  }
  
  public String toString(boolean paramBoolean)
  {
    StringBuilder localStringBuilder = new StringBuilder("DataItemParcelable[");
    localStringBuilder.append("@");
    localStringBuilder.append(Integer.toHexString(hashCode()));
    Object localObject2 = new StringBuilder().append(",dataSz=");
    if (this.acH == null)
    {
      localObject1 = "null";
      localStringBuilder.append(localObject1);
      localStringBuilder.append(", numAssets=" + this.avv.size());
      localStringBuilder.append(", uri=" + this.mUri);
      if (paramBoolean) {
        break label156;
      }
      localStringBuilder.append("]");
    }
    for (Object localObject1 = localStringBuilder.toString();; localObject1 = localStringBuilder.toString())
    {
      return (String)localObject1;
      localObject1 = Integer.valueOf(this.acH.length);
      break;
      label156:
      localStringBuilder.append("]\n  assets: ");
      localObject2 = this.avv.keySet().iterator();
      while (((Iterator)localObject2).hasNext())
      {
        localObject1 = (String)((Iterator)localObject2).next();
        localStringBuilder.append("\n    " + (String)localObject1 + ": " + this.avv.get(localObject1));
      }
      localStringBuilder.append("\n  ]");
    }
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    n.a(this, paramParcel, paramInt);
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\wearable\internal\m.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */