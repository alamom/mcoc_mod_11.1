package com.facebook.internal;

import java.util.Collection;
import java.util.Iterator;

public final class Validate
{
  public static void containsNoNullOrEmpty(Collection<String> paramCollection, String paramString)
  {
    notNull(paramCollection, paramString);
    paramCollection = paramCollection.iterator();
    while (paramCollection.hasNext())
    {
      String str = (String)paramCollection.next();
      if (str == null) {
        throw new NullPointerException("Container '" + paramString + "' cannot contain null values");
      }
      if (str.length() == 0) {
        throw new IllegalArgumentException("Container '" + paramString + "' cannot contain empty values");
      }
    }
  }
  
  public static <T> void containsNoNulls(Collection<T> paramCollection, String paramString)
  {
    notNull(paramCollection, paramString);
    paramCollection = paramCollection.iterator();
    while (paramCollection.hasNext()) {
      if (paramCollection.next() == null) {
        throw new NullPointerException("Container '" + paramString + "' cannot contain null values");
      }
    }
  }
  
  public static <T> void notEmpty(Collection<T> paramCollection, String paramString)
  {
    if (paramCollection.isEmpty()) {
      throw new IllegalArgumentException("Container '" + paramString + "' cannot be empty");
    }
  }
  
  public static <T> void notEmptyAndContainsNoNulls(Collection<T> paramCollection, String paramString)
  {
    containsNoNulls(paramCollection, paramString);
    notEmpty(paramCollection, paramString);
  }
  
  public static void notNull(Object paramObject, String paramString)
  {
    if (paramObject == null) {
      throw new NullPointerException("Argument '" + paramString + "' cannot be null");
    }
  }
  
  public static void notNullOrEmpty(String paramString1, String paramString2)
  {
    if (Utility.isNullOrEmpty(paramString1)) {
      throw new IllegalArgumentException("Argument '" + paramString2 + "' cannot be null or empty");
    }
  }
  
  public static void oneOf(Object paramObject, String paramString, Object... paramVarArgs)
  {
    int j = paramVarArgs.length;
    for (int i = 0; i < j; i++)
    {
      Object localObject = paramVarArgs[i];
      if (localObject != null)
      {
        if (!localObject.equals(paramObject)) {}
      }
      else {
        while (paramObject == null) {
          return;
        }
      }
    }
    throw new IllegalArgumentException("Argument '" + paramString + "' was not one of the allowed values");
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\facebook\internal\Validate.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */