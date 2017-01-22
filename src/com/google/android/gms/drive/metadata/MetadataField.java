package com.google.android.gms.drive.metadata;

import android.os.Bundle;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;

public abstract interface MetadataField<T>
{
  public abstract T a(DataHolder paramDataHolder, int paramInt1, int paramInt2);
  
  public abstract void a(DataHolder paramDataHolder, MetadataBundle paramMetadataBundle, int paramInt1, int paramInt2);
  
  public abstract void a(T paramT, Bundle paramBundle);
  
  public abstract T f(Bundle paramBundle);
  
  public abstract String getName();
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\drive\metadata\MetadataField.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */