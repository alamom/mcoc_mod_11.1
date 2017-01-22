package com.google.android.gms.common.internal;

import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.lang.reflect.Field;

public abstract class d
  implements SafeParcelable
{
  private static final Object Lv = new Object();
  private static ClassLoader Lw = null;
  private static Integer Lx = null;
  private boolean Ly = false;
  
  private static boolean a(Class<?> paramClass)
  {
    boolean bool2 = false;
    try
    {
      bool1 = "SAFE_PARCELABLE_NULL_STRING".equals(paramClass.getField("NULL").get(null));
      return bool1;
    }
    catch (IllegalAccessException paramClass)
    {
      for (;;)
      {
        bool1 = bool2;
      }
    }
    catch (NoSuchFieldException paramClass)
    {
      for (;;)
      {
        boolean bool1 = bool2;
      }
    }
  }
  
  protected static boolean aV(String paramString)
  {
    ClassLoader localClassLoader = gO();
    boolean bool;
    if (localClassLoader == null) {
      bool = true;
    }
    for (;;)
    {
      return bool;
      try
      {
        bool = a(localClassLoader.loadClass(paramString));
      }
      catch (Exception paramString)
      {
        bool = false;
      }
    }
  }
  
  protected static ClassLoader gO()
  {
    synchronized (Lv)
    {
      ClassLoader localClassLoader = Lw;
      return localClassLoader;
    }
  }
  
  protected static Integer gP()
  {
    synchronized (Lv)
    {
      Integer localInteger = Lx;
      return localInteger;
    }
  }
  
  protected boolean gQ()
  {
    return this.Ly;
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\common\internal\d.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */