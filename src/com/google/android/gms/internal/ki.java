package com.google.android.gms.internal;

import java.util.Iterator;
import java.util.List;

public class ki
{
  public static <T> boolean a(List<T> paramList1, List<T> paramList2)
  {
    boolean bool = false;
    if (paramList1.size() != paramList2.size()) {}
    for (;;)
    {
      return bool;
      paramList1 = paramList1.iterator();
      for (;;)
      {
        if (paramList1.hasNext()) {
          if (!paramList2.contains(paramList1.next())) {
            break;
          }
        }
      }
      bool = true;
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\internal\ki.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */