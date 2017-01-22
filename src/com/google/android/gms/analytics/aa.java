package com.google.android.gms.analytics;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

class aa
{
  private final Map<String, Integer> AU = new HashMap();
  private final Map<String, String> AV = new HashMap();
  private final boolean AW;
  private final String AX;
  
  aa(String paramString, boolean paramBoolean)
  {
    this.AW = paramBoolean;
    this.AX = paramString;
  }
  
  void e(String paramString, int paramInt)
  {
    if (!this.AW) {}
    for (;;)
    {
      return;
      Integer localInteger2 = (Integer)this.AU.get(paramString);
      Integer localInteger1 = localInteger2;
      if (localInteger2 == null) {
        localInteger1 = Integer.valueOf(0);
      }
      this.AU.put(paramString, Integer.valueOf(localInteger1.intValue() + paramInt));
    }
  }
  
  String eL()
  {
    if (!this.AW) {}
    for (Object localObject1 = "";; localObject1 = ((StringBuilder)localObject1).toString())
    {
      return (String)localObject1;
      localObject1 = new StringBuilder();
      ((StringBuilder)localObject1).append(this.AX);
      Object localObject2 = this.AU.keySet().iterator();
      while (((Iterator)localObject2).hasNext())
      {
        localObject3 = (String)((Iterator)localObject2).next();
        ((StringBuilder)localObject1).append("&").append((String)localObject3).append("=").append(this.AU.get(localObject3));
      }
      Object localObject3 = this.AV.keySet().iterator();
      while (((Iterator)localObject3).hasNext())
      {
        localObject2 = (String)((Iterator)localObject3).next();
        ((StringBuilder)localObject1).append("&").append((String)localObject2).append("=").append((String)this.AV.get(localObject2));
      }
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\analytics\aa.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */