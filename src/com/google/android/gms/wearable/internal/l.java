package com.google.android.gms.wearable.internal;

import android.net.Uri;
import android.util.Log;
import com.google.android.gms.wearable.DataItem;
import com.google.android.gms.wearable.DataItemAsset;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class l
  implements DataItem
{
  private byte[] acH;
  private Map<String, DataItemAsset> avv;
  private Uri mUri;
  
  public l(DataItem paramDataItem)
  {
    this.mUri = paramDataItem.getUri();
    this.acH = paramDataItem.getData();
    HashMap localHashMap = new HashMap();
    paramDataItem = paramDataItem.getAssets().entrySet().iterator();
    while (paramDataItem.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)paramDataItem.next();
      if (localEntry.getKey() != null) {
        localHashMap.put(localEntry.getKey(), ((DataItemAsset)localEntry.getValue()).freeze());
      }
    }
    this.avv = Collections.unmodifiableMap(localHashMap);
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
  
  public DataItem pY()
  {
    return this;
  }
  
  public DataItem setData(byte[] paramArrayOfByte)
  {
    throw new UnsupportedOperationException();
  }
  
  public String toString()
  {
    return toString(Log.isLoggable("DataItem", 3));
  }
  
  public String toString(boolean paramBoolean)
  {
    StringBuilder localStringBuilder = new StringBuilder("DataItemEntity[");
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
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\wearable\internal\l.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */