package com.google.android.gms.common.internal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class n
{
  public static boolean equal(Object paramObject1, Object paramObject2)
  {
    if ((paramObject1 == paramObject2) || ((paramObject1 != null) && (paramObject1.equals(paramObject2)))) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public static a h(Object paramObject)
  {
    return new a(paramObject, null);
  }
  
  public static int hashCode(Object... paramVarArgs)
  {
    return Arrays.hashCode(paramVarArgs);
  }
  
  public static final class a
  {
    private final List<String> Mg;
    private final Object Mh;
    
    private a(Object paramObject)
    {
      this.Mh = o.i(paramObject);
      this.Mg = new ArrayList();
    }
    
    public a a(String paramString, Object paramObject)
    {
      this.Mg.add((String)o.i(paramString) + "=" + String.valueOf(paramObject));
      return this;
    }
    
    public String toString()
    {
      StringBuilder localStringBuilder = new StringBuilder(100).append(this.Mh.getClass().getSimpleName()).append('{');
      int j = this.Mg.size();
      for (int i = 0; i < j; i++)
      {
        localStringBuilder.append((String)this.Mg.get(i));
        if (i < j - 1) {
          localStringBuilder.append(", ");
        }
      }
      return '}';
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\common\internal\n.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */