package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.a;
import com.google.android.gms.internal.b;
import com.google.android.gms.internal.d.a;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

class s
  extends aj
{
  private static final String ID = a.L.toString();
  private static final String anV = b.bl.toString();
  private static final String aoE = b.cV.toString();
  private final a aoF;
  
  public s(a parama)
  {
    super(ID, new String[] { aoE });
    this.aoF = parama;
  }
  
  public d.a C(Map<String, d.a> paramMap)
  {
    String str = di.j((d.a)paramMap.get(aoE));
    HashMap localHashMap = new HashMap();
    paramMap = (d.a)paramMap.get(anV);
    if (paramMap != null)
    {
      paramMap = di.o(paramMap);
      if (!(paramMap instanceof Map))
      {
        bh.W("FunctionCallMacro: expected ADDITIONAL_PARAMS to be a map.");
        paramMap = di.pK();
      }
    }
    for (;;)
    {
      return paramMap;
      Iterator localIterator = ((Map)paramMap).entrySet().iterator();
      while (localIterator.hasNext())
      {
        paramMap = (Map.Entry)localIterator.next();
        localHashMap.put(paramMap.getKey().toString(), paramMap.getValue());
      }
      try
      {
        paramMap = di.u(this.aoF.b(str, localHashMap));
      }
      catch (Exception paramMap)
      {
        bh.W("Custom macro/tag " + str + " threw exception " + paramMap.getMessage());
        paramMap = di.pK();
      }
    }
  }
  
  public boolean nN()
  {
    return false;
  }
  
  public static abstract interface a
  {
    public abstract Object b(String paramString, Map<String, Object> paramMap);
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\tagmanager\s.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */