package com.google.android.gms.drive.query.internal;

import com.google.android.gms.drive.metadata.MetadataField;
import com.google.android.gms.drive.metadata.b;
import java.util.List;

public abstract interface f<F>
{
  public abstract <T> F b(b<T> paramb, T paramT);
  
  public abstract <T> F b(Operator paramOperator, MetadataField<T> paramMetadataField, T paramT);
  
  public abstract F b(Operator paramOperator, List<F> paramList);
  
  public abstract F d(MetadataField<?> paramMetadataField);
  
  public abstract <T> F d(MetadataField<T> paramMetadataField, T paramT);
  
  public abstract F is();
  
  public abstract F j(F paramF);
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\drive\query\internal\f.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */