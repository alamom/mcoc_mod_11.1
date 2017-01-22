package com.google.android.gms.wearable;

import android.net.Uri;
import com.google.android.gms.internal.pc;
import com.google.android.gms.internal.pc.a;
import com.google.android.gms.internal.pd;
import com.google.android.gms.internal.pm;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DataMapItem
{
  private final DataMap auX;
  private final Uri mUri;
  
  private DataMapItem(DataItem paramDataItem)
  {
    this.mUri = paramDataItem.getUri();
    this.auX = a((DataItem)paramDataItem.freeze());
  }
  
  private DataMap a(DataItem paramDataItem)
  {
    if ((paramDataItem.getData() == null) && (paramDataItem.getAssets().size() > 0)) {
      throw new IllegalArgumentException("Cannot create DataMapItem from a DataItem  that wasn't made with DataMapItem.");
    }
    if (paramDataItem.getData() == null) {}
    Object localObject2;
    for (paramDataItem = new DataMap();; paramDataItem = pc.a((pc.a)localObject2))
    {
      return paramDataItem;
      Object localObject1;
      for (;;)
      {
        int i;
        try
        {
          localObject1 = new java/util/ArrayList;
          ((ArrayList)localObject1).<init>();
          int j = paramDataItem.getAssets().size();
          i = 0;
          if (i >= j) {
            break;
          }
          localObject2 = (DataItemAsset)paramDataItem.getAssets().get(Integer.toString(i));
          if (localObject2 == null)
          {
            localObject2 = new java/lang/IllegalStateException;
            localObject1 = new java/lang/StringBuilder;
            ((StringBuilder)localObject1).<init>();
            ((IllegalStateException)localObject2).<init>("Cannot find DataItemAsset referenced in data at " + i + " for " + paramDataItem);
            throw ((Throwable)localObject2);
          }
        }
        catch (pm paramDataItem)
        {
          throw new IllegalStateException("Unable to parse. Not a DataItem.");
        }
        ((List)localObject1).add(Asset.createFromRef(((DataItemAsset)localObject2).getId()));
        i++;
      }
      localObject2 = new com/google/android/gms/internal/pc$a;
      ((pc.a)localObject2).<init>(pd.n(paramDataItem.getData()), (List)localObject1);
    }
  }
  
  public static DataMapItem fromDataItem(DataItem paramDataItem)
  {
    if (paramDataItem == null) {
      throw new IllegalStateException("provided dataItem is null");
    }
    return new DataMapItem(paramDataItem);
  }
  
  public DataMap getDataMap()
  {
    return this.auX;
  }
  
  public Uri getUri()
  {
    return this.mUri;
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\wearable\DataMapItem.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */