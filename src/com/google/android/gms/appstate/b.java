package com.google.android.gms.appstate;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.d;

public final class b
  extends d
  implements AppState
{
  b(DataHolder paramDataHolder, int paramInt)
  {
    super(paramDataHolder, paramInt);
  }
  
  public boolean equals(Object paramObject)
  {
    return a.a(this, paramObject);
  }
  
  public AppState fo()
  {
    return new a(this);
  }
  
  public byte[] getConflictData()
  {
    return getByteArray("conflict_data");
  }
  
  public String getConflictVersion()
  {
    return getString("conflict_version");
  }
  
  public int getKey()
  {
    return getInteger("key");
  }
  
  public byte[] getLocalData()
  {
    return getByteArray("local_data");
  }
  
  public String getLocalVersion()
  {
    return getString("local_version");
  }
  
  public boolean hasConflict()
  {
    if (!aS("conflict_version")) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public int hashCode()
  {
    return a.a(this);
  }
  
  public String toString()
  {
    return a.b(this);
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\appstate\b.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */