package com.google.gson.internal;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class Primitives
{
  private static final Map<Class<?>, Class<?>> PRIMITIVE_TO_WRAPPER_TYPE;
  private static final Map<Class<?>, Class<?>> WRAPPER_TO_PRIMITIVE_TYPE;
  
  static
  {
    HashMap localHashMap2 = new HashMap(16);
    HashMap localHashMap1 = new HashMap(16);
    add(localHashMap2, localHashMap1, Boolean.TYPE, Boolean.class);
    add(localHashMap2, localHashMap1, Byte.TYPE, Byte.class);
    add(localHashMap2, localHashMap1, Character.TYPE, Character.class);
    add(localHashMap2, localHashMap1, Double.TYPE, Double.class);
    add(localHashMap2, localHashMap1, Float.TYPE, Float.class);
    add(localHashMap2, localHashMap1, Integer.TYPE, Integer.class);
    add(localHashMap2, localHashMap1, Long.TYPE, Long.class);
    add(localHashMap2, localHashMap1, Short.TYPE, Short.class);
    add(localHashMap2, localHashMap1, Void.TYPE, Void.class);
    PRIMITIVE_TO_WRAPPER_TYPE = Collections.unmodifiableMap(localHashMap2);
    WRAPPER_TO_PRIMITIVE_TYPE = Collections.unmodifiableMap(localHashMap1);
  }
  
  private static void add(Map<Class<?>, Class<?>> paramMap1, Map<Class<?>, Class<?>> paramMap2, Class<?> paramClass1, Class<?> paramClass2)
  {
    paramMap1.put(paramClass1, paramClass2);
    paramMap2.put(paramClass2, paramClass1);
  }
  
  public static boolean isPrimitive(Type paramType)
  {
    return PRIMITIVE_TO_WRAPPER_TYPE.containsKey(paramType);
  }
  
  public static boolean isWrapperType(Type paramType)
  {
    return WRAPPER_TO_PRIMITIVE_TYPE.containsKey(.Gson.Preconditions.checkNotNull(paramType));
  }
  
  public static <T> Class<T> unwrap(Class<T> paramClass)
  {
    Class localClass = (Class)WRAPPER_TO_PRIMITIVE_TYPE.get(.Gson.Preconditions.checkNotNull(paramClass));
    if (localClass == null) {}
    for (;;)
    {
      return paramClass;
      paramClass = localClass;
    }
  }
  
  public static <T> Class<T> wrap(Class<T> paramClass)
  {
    Class localClass = (Class)PRIMITIVE_TO_WRAPPER_TYPE.get(.Gson.Preconditions.checkNotNull(paramClass));
    if (localClass == null) {}
    for (;;)
    {
      return paramClass;
      paramClass = localClass;
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\gson\internal\Primitives.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */