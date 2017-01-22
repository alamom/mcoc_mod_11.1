package com.google.android.gms.common.data;

import android.os.Bundle;
import com.google.android.gms.common.api.Releasable;
import java.util.Iterator;

public abstract class DataBuffer<T>
  implements Releasable, Iterable<T>
{
  protected final DataHolder II;
  
  protected DataBuffer(DataHolder paramDataHolder)
  {
    this.II = paramDataHolder;
    if (this.II != null) {
      this.II.e(this);
    }
  }
  
  @Deprecated
  public final void close()
  {
    release();
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public abstract T get(int paramInt);
  
  public int getCount()
  {
    if (this.II == null) {}
    for (int i = 0;; i = this.II.getCount()) {
      return i;
    }
  }
  
  public Bundle gy()
  {
    return this.II.gy();
  }
  
  @Deprecated
  public boolean isClosed()
  {
    if (this.II == null) {}
    for (boolean bool = true;; bool = this.II.isClosed()) {
      return bool;
    }
  }
  
  public Iterator<T> iterator()
  {
    return new c(this);
  }
  
  public void release()
  {
    if (this.II != null) {
      this.II.close();
    }
  }
  
  public Iterator<T> singleRefIterator()
  {
    return new h(this);
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\common\data\DataBuffer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */