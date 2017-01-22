package com.google.android.gms.common.data;

import java.util.ArrayList;
import java.util.Iterator;

public final class FreezableUtils
{
  public static <T, E extends Freezable<T>> ArrayList<T> freeze(ArrayList<E> paramArrayList)
  {
    ArrayList localArrayList = new ArrayList(paramArrayList.size());
    int j = paramArrayList.size();
    for (int i = 0; i < j; i++) {
      localArrayList.add(((Freezable)paramArrayList.get(i)).freeze());
    }
    return localArrayList;
  }
  
  public static <T, E extends Freezable<T>> ArrayList<T> freeze(E[] paramArrayOfE)
  {
    ArrayList localArrayList = new ArrayList(paramArrayOfE.length);
    for (int i = 0; i < paramArrayOfE.length; i++) {
      localArrayList.add(paramArrayOfE[i].freeze());
    }
    return localArrayList;
  }
  
  public static <T, E extends Freezable<T>> ArrayList<T> freezeIterable(Iterable<E> paramIterable)
  {
    ArrayList localArrayList = new ArrayList();
    paramIterable = paramIterable.iterator();
    while (paramIterable.hasNext()) {
      localArrayList.add(((Freezable)paramIterable.next()).freeze());
    }
    return localArrayList;
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\common\data\FreezableUtils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */