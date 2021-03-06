package com.google.android.gms.drive.query.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.drive.metadata.MetadataField;
import com.google.android.gms.drive.metadata.SearchableMetadataField;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;

public class FieldOnlyFilter
  extends AbstractFilter
{
  public static final Parcelable.Creator<FieldOnlyFilter> CREATOR = new b();
  final int BR;
  final MetadataBundle QL;
  private final MetadataField<?> QM;
  
  FieldOnlyFilter(int paramInt, MetadataBundle paramMetadataBundle)
  {
    this.BR = paramInt;
    this.QL = paramMetadataBundle;
    this.QM = e.b(paramMetadataBundle);
  }
  
  public FieldOnlyFilter(SearchableMetadataField<?> paramSearchableMetadataField)
  {
    this(1, MetadataBundle.a(paramSearchableMetadataField, null));
  }
  
  public <T> T a(f<T> paramf)
  {
    return (T)paramf.d(this.QM);
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    b.a(this, paramParcel, paramInt);
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\drive\query\internal\FieldOnlyFilter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */