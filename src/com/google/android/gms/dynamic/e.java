package com.google.android.gms.dynamic;

import java.lang.reflect.Field;

public final class e<T>
  extends d.a
{
  private final T Sk;
  
  private e(T paramT)
  {
    this.Sk = paramT;
  }
  
  public static <T> T f(d paramd)
  {
    if ((paramd instanceof e)) {
      paramd = ((e)paramd).Sk;
    }
    for (;;)
    {
      return paramd;
      paramd = paramd.asBinder();
      Object localObject = paramd.getClass().getDeclaredFields();
      if (localObject.length != 1) {
        break label109;
      }
      localObject = localObject[0];
      if (!((Field)localObject).isAccessible())
      {
        ((Field)localObject).setAccessible(true);
        try
        {
          paramd = ((Field)localObject).get(paramd);
        }
        catch (NullPointerException paramd)
        {
          throw new IllegalArgumentException("Binder object is null.", paramd);
        }
        catch (IllegalArgumentException paramd)
        {
          throw new IllegalArgumentException("remoteBinder is the wrong class.", paramd);
        }
        catch (IllegalAccessException paramd)
        {
          throw new IllegalArgumentException("Could not access the field in remoteBinder.", paramd);
        }
      }
    }
    throw new IllegalArgumentException("The concrete class implementing IObjectWrapper must have exactly one declared *private* field for the wrapped object. Preferably, this is an instance of the ObjectWrapper<T> class.");
    label109:
    throw new IllegalArgumentException("The concrete class implementing IObjectWrapper must have exactly *one* declared private field for the wrapped object.  Preferably, this is an instance of the ObjectWrapper<T> class.");
  }
  
  public static <T> d k(T paramT)
  {
    return new e(paramT);
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\dynamic\e.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */