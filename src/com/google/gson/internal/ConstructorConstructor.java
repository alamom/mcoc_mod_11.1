package com.google.gson.internal;

import com.google.gson.InstanceCreator;
import com.google.gson.JsonIOException;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

public final class ConstructorConstructor
{
  private final Map<Type, InstanceCreator<?>> instanceCreators;
  
  public ConstructorConstructor(Map<Type, InstanceCreator<?>> paramMap)
  {
    this.instanceCreators = paramMap;
  }
  
  private <T> ObjectConstructor<T> newDefaultConstructor(Class<? super T> paramClass)
  {
    try
    {
      Constructor localConstructor = paramClass.getDeclaredConstructor(new Class[0]);
      if (!localConstructor.isAccessible()) {
        localConstructor.setAccessible(true);
      }
      paramClass = new com/google/gson/internal/ConstructorConstructor$3;
      paramClass.<init>(this, localConstructor);
    }
    catch (NoSuchMethodException paramClass)
    {
      for (;;)
      {
        paramClass = null;
      }
    }
    return paramClass;
  }
  
  private <T> ObjectConstructor<T> newDefaultImplementationConstructor(final Type paramType, Class<? super T> paramClass)
  {
    if (Collection.class.isAssignableFrom(paramClass)) {
      if (SortedSet.class.isAssignableFrom(paramClass)) {
        paramType = new ObjectConstructor()
        {
          public T construct()
          {
            return new TreeSet();
          }
        };
      }
    }
    for (;;)
    {
      return paramType;
      if (EnumSet.class.isAssignableFrom(paramClass))
      {
        paramType = new ObjectConstructor()
        {
          public T construct()
          {
            if ((paramType instanceof ParameterizedType))
            {
              Type localType = ((ParameterizedType)paramType).getActualTypeArguments()[0];
              if ((localType instanceof Class)) {
                return EnumSet.noneOf((Class)localType);
              }
              throw new JsonIOException("Invalid EnumSet type: " + paramType.toString());
            }
            throw new JsonIOException("Invalid EnumSet type: " + paramType.toString());
          }
        };
      }
      else if (Set.class.isAssignableFrom(paramClass))
      {
        paramType = new ObjectConstructor()
        {
          public T construct()
          {
            return new LinkedHashSet();
          }
        };
      }
      else if (Queue.class.isAssignableFrom(paramClass))
      {
        paramType = new ObjectConstructor()
        {
          public T construct()
          {
            return new LinkedList();
          }
        };
      }
      else
      {
        paramType = new ObjectConstructor()
        {
          public T construct()
          {
            return new ArrayList();
          }
        };
        continue;
        if (Map.class.isAssignableFrom(paramClass))
        {
          if (SortedMap.class.isAssignableFrom(paramClass)) {
            paramType = new ObjectConstructor()
            {
              public T construct()
              {
                return new TreeMap();
              }
            };
          } else if (((paramType instanceof ParameterizedType)) && (!String.class.isAssignableFrom(TypeToken.get(((ParameterizedType)paramType).getActualTypeArguments()[0]).getRawType()))) {
            paramType = new ObjectConstructor()
            {
              public T construct()
              {
                return new LinkedHashMap();
              }
            };
          } else {
            paramType = new ObjectConstructor()
            {
              public T construct()
              {
                return new LinkedTreeMap();
              }
            };
          }
        }
        else {
          paramType = null;
        }
      }
    }
  }
  
  private <T> ObjectConstructor<T> newUnsafeAllocator(final Type paramType, final Class<? super T> paramClass)
  {
    new ObjectConstructor()
    {
      private final UnsafeAllocator unsafeAllocator = UnsafeAllocator.create();
      
      public T construct()
      {
        try
        {
          Object localObject = this.unsafeAllocator.newInstance(paramClass);
          return (T)localObject;
        }
        catch (Exception localException)
        {
          throw new RuntimeException("Unable to invoke no-args constructor for " + paramType + ". " + "Register an InstanceCreator with Gson for this type may fix this problem.", localException);
        }
      }
    };
  }
  
  public <T> ObjectConstructor<T> get(final TypeToken<T> paramTypeToken)
  {
    final Type localType = paramTypeToken.getType();
    Class localClass = paramTypeToken.getRawType();
    paramTypeToken = (InstanceCreator)this.instanceCreators.get(localType);
    if (paramTypeToken != null) {
      paramTypeToken = new ObjectConstructor()
      {
        public T construct()
        {
          return (T)paramTypeToken.createInstance(localType);
        }
      };
    }
    for (;;)
    {
      return paramTypeToken;
      paramTypeToken = (InstanceCreator)this.instanceCreators.get(localClass);
      if (paramTypeToken != null)
      {
        paramTypeToken = new ObjectConstructor()
        {
          public T construct()
          {
            return (T)paramTypeToken.createInstance(localType);
          }
        };
      }
      else
      {
        ObjectConstructor localObjectConstructor = newDefaultConstructor(localClass);
        paramTypeToken = localObjectConstructor;
        if (localObjectConstructor == null)
        {
          paramTypeToken = newDefaultImplementationConstructor(localType, localClass);
          if (paramTypeToken == null) {
            paramTypeToken = newUnsafeAllocator(localType, localClass);
          }
        }
      }
    }
  }
  
  public String toString()
  {
    return this.instanceCreators.toString();
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\gson\internal\ConstructorConstructor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */