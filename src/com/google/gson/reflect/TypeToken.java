package com.google.gson.reflect;

import com.google.gson.internal..Gson.Preconditions;
import com.google.gson.internal..Gson.Types;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.HashMap;
import java.util.Map;

public class TypeToken<T>
{
  final int hashCode;
  final Class<? super T> rawType;
  final Type type;
  
  protected TypeToken()
  {
    this.type = getSuperclassTypeParameter(getClass());
    this.rawType = .Gson.Types.getRawType(this.type);
    this.hashCode = this.type.hashCode();
  }
  
  TypeToken(Type paramType)
  {
    this.type = .Gson.Types.canonicalize((Type).Gson.Preconditions.checkNotNull(paramType));
    this.rawType = .Gson.Types.getRawType(this.type);
    this.hashCode = this.type.hashCode();
  }
  
  private static AssertionError buildUnexpectedTypeError(Type paramType, Class<?>... paramVarArgs)
  {
    StringBuilder localStringBuilder = new StringBuilder("Unexpected type. Expected one of: ");
    int j = paramVarArgs.length;
    for (int i = 0; i < j; i++) {
      localStringBuilder.append(paramVarArgs[i].getName()).append(", ");
    }
    localStringBuilder.append("but got: ").append(paramType.getClass().getName()).append(", for type token: ").append(paramType.toString()).append('.');
    return new AssertionError(localStringBuilder.toString());
  }
  
  public static <T> TypeToken<T> get(Class<T> paramClass)
  {
    return new TypeToken(paramClass);
  }
  
  public static TypeToken<?> get(Type paramType)
  {
    return new TypeToken(paramType);
  }
  
  static Type getSuperclassTypeParameter(Class<?> paramClass)
  {
    paramClass = paramClass.getGenericSuperclass();
    if ((paramClass instanceof Class)) {
      throw new RuntimeException("Missing type parameter.");
    }
    return .Gson.Types.canonicalize(((ParameterizedType)paramClass).getActualTypeArguments()[0]);
  }
  
  private static boolean isAssignableFrom(Type paramType, GenericArrayType paramGenericArrayType)
  {
    Type localType = paramGenericArrayType.getGenericComponentType();
    if ((localType instanceof ParameterizedType))
    {
      paramGenericArrayType = paramType;
      if ((paramType instanceof GenericArrayType)) {
        paramGenericArrayType = ((GenericArrayType)paramType).getGenericComponentType();
      }
    }
    for (boolean bool = isAssignableFrom(paramGenericArrayType, (ParameterizedType)localType, new HashMap());; bool = true)
    {
      return bool;
      if (!(paramType instanceof Class)) {
        break;
      }
      for (paramType = (Class)paramType; paramType.isArray(); paramType = paramType.getComponentType()) {}
      paramGenericArrayType = paramType;
      break;
    }
  }
  
  private static boolean isAssignableFrom(Type paramType, ParameterizedType paramParameterizedType, Map<String, Type> paramMap)
  {
    boolean bool;
    if (paramType == null) {
      bool = false;
    }
    for (;;)
    {
      return bool;
      if (paramParameterizedType.equals(paramType))
      {
        bool = true;
      }
      else
      {
        Class localClass = .Gson.Types.getRawType(paramType);
        ParameterizedType localParameterizedType = null;
        if ((paramType instanceof ParameterizedType)) {
          localParameterizedType = (ParameterizedType)paramType;
        }
        if (localParameterizedType != null)
        {
          Type[] arrayOfType = localParameterizedType.getActualTypeArguments();
          TypeVariable[] arrayOfTypeVariable = localClass.getTypeParameters();
          for (i = 0; i < arrayOfType.length; i++)
          {
            paramType = arrayOfType[i];
            TypeVariable localTypeVariable = arrayOfTypeVariable[i];
            while ((paramType instanceof TypeVariable)) {
              paramType = (Type)paramMap.get(((TypeVariable)paramType).getName());
            }
            paramMap.put(localTypeVariable.getName(), paramType);
          }
          if (typeEquals(localParameterizedType, paramParameterizedType, paramMap))
          {
            bool = true;
            continue;
          }
        }
        paramType = localClass.getGenericInterfaces();
        int j = paramType.length;
        for (int i = 0;; i++)
        {
          if (i >= j) {
            break label201;
          }
          if (isAssignableFrom(paramType[i], paramParameterizedType, new HashMap(paramMap)))
          {
            bool = true;
            break;
          }
        }
        label201:
        bool = isAssignableFrom(localClass.getGenericSuperclass(), paramParameterizedType, new HashMap(paramMap));
      }
    }
  }
  
  private static boolean matches(Type paramType1, Type paramType2, Map<String, Type> paramMap)
  {
    if ((paramType2.equals(paramType1)) || (((paramType1 instanceof TypeVariable)) && (paramType2.equals(paramMap.get(((TypeVariable)paramType1).getName()))))) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  private static boolean typeEquals(ParameterizedType paramParameterizedType1, ParameterizedType paramParameterizedType2, Map<String, Type> paramMap)
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    int i;
    if (paramParameterizedType1.getRawType().equals(paramParameterizedType2.getRawType()))
    {
      paramParameterizedType1 = paramParameterizedType1.getActualTypeArguments();
      paramParameterizedType2 = paramParameterizedType2.getActualTypeArguments();
      i = 0;
      if (i >= paramParameterizedType1.length) {
        break label73;
      }
      if (matches(paramParameterizedType1[i], paramParameterizedType2[i], paramMap)) {
        break label67;
      }
    }
    label67:
    label73:
    for (bool1 = bool2;; bool1 = true)
    {
      return bool1;
      i++;
      break;
    }
  }
  
  public final boolean equals(Object paramObject)
  {
    if (((paramObject instanceof TypeToken)) && (.Gson.Types.equals(this.type, ((TypeToken)paramObject).type))) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public final Class<? super T> getRawType()
  {
    return this.rawType;
  }
  
  public final Type getType()
  {
    return this.type;
  }
  
  public final int hashCode()
  {
    return this.hashCode;
  }
  
  @Deprecated
  public boolean isAssignableFrom(TypeToken<?> paramTypeToken)
  {
    return isAssignableFrom(paramTypeToken.getType());
  }
  
  @Deprecated
  public boolean isAssignableFrom(Class<?> paramClass)
  {
    return isAssignableFrom(paramClass);
  }
  
  @Deprecated
  public boolean isAssignableFrom(Type paramType)
  {
    boolean bool = false;
    if (paramType == null) {}
    for (;;)
    {
      return bool;
      if (this.type.equals(paramType))
      {
        bool = true;
      }
      else if ((this.type instanceof Class))
      {
        bool = this.rawType.isAssignableFrom(.Gson.Types.getRawType(paramType));
      }
      else
      {
        if (!(this.type instanceof ParameterizedType)) {
          break;
        }
        bool = isAssignableFrom(paramType, (ParameterizedType)this.type, new HashMap());
      }
    }
    if ((this.type instanceof GenericArrayType))
    {
      if ((this.rawType.isAssignableFrom(.Gson.Types.getRawType(paramType))) && (isAssignableFrom(paramType, (GenericArrayType)this.type))) {}
      for (bool = true;; bool = false) {
        break;
      }
    }
    throw buildUnexpectedTypeError(this.type, new Class[] { Class.class, ParameterizedType.class, GenericArrayType.class });
  }
  
  public final String toString()
  {
    return .Gson.Types.typeToString(this.type);
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\gson\reflect\TypeToken.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */