package com.google.android.gms.common.data;

import android.os.Bundle;
import java.util.ArrayList;
import java.util.Iterator;

public final class DataBufferUtils
{
  public static <T, E extends Freezable<T>> ArrayList<T> freezeAndClose(DataBuffer<E> paramDataBuffer)
  {
    ArrayList localArrayList = new ArrayList(paramDataBuffer.getCount());
    try
    {
      Iterator localIterator = paramDataBuffer.iterator();
      while (localIterator.hasNext()) {
        localArrayList.add(((Freezable)localIterator.next()).freeze());
      }
    }
    finally
    {
      paramDataBuffer.close();
    }
    return localArrayList1;
  }
  
  public static boolean hasData(DataBuffer<?> paramDataBuffer)
  {
    if ((paramDataBuffer != null) && (paramDataBuffer.getCount() > 0)) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public static boolean hasNextPage(DataBuffer<?> paramDataBuffer)
  {
    paramDataBuffer = paramDataBuffer.gy();
    if ((paramDataBuffer != null) && (paramDataBuffer.getString("next_page_token") != null)) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public static boolean hasPrevPage(DataBuffer<?> paramDataBuffer)
  {
    paramDataBuffer = paramDataBuffer.gy();
    if ((paramDataBuffer != null) && (paramDataBuffer.getString("prev_page_token") != null)) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\common\data\DataBufferUtils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */