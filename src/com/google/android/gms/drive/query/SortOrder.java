package com.google.android.gms.drive.query;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.drive.metadata.SortableMetadataField;
import com.google.android.gms.drive.query.internal.FieldWithSortOrder;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class SortOrder
  implements SafeParcelable
{
  public static final Parcelable.Creator<SortOrder> CREATOR = new b();
  final int BR;
  final List<FieldWithSortOrder> QI;
  final boolean QJ;
  
  SortOrder(int paramInt, List<FieldWithSortOrder> paramList, boolean paramBoolean)
  {
    this.BR = paramInt;
    this.QI = paramList;
    this.QJ = paramBoolean;
  }
  
  private SortOrder(List<FieldWithSortOrder> paramList, boolean paramBoolean)
  {
    this(1, paramList, paramBoolean);
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public String toString()
  {
    return String.format(Locale.US, "SortOrder[%s, %s]", new Object[] { TextUtils.join(",", this.QI), Boolean.valueOf(this.QJ) });
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    b.a(this, paramParcel, paramInt);
  }
  
  public static class Builder
  {
    private final List<FieldWithSortOrder> QI = new ArrayList();
    private boolean QJ = false;
    
    public Builder addSortAscending(SortableMetadataField paramSortableMetadataField)
    {
      this.QI.add(new FieldWithSortOrder(paramSortableMetadataField.getName(), true));
      return this;
    }
    
    public Builder addSortDescending(SortableMetadataField paramSortableMetadataField)
    {
      this.QI.add(new FieldWithSortOrder(paramSortableMetadataField.getName(), false));
      return this;
    }
    
    public SortOrder build()
    {
      return new SortOrder(this.QI, this.QJ, null);
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\drive\query\SortOrder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */