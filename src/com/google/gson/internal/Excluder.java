package com.google.gson.internal;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.Since;
import com.google.gson.annotations.Until;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public final class Excluder
  implements TypeAdapterFactory, Cloneable
{
  public static final Excluder DEFAULT = new Excluder();
  private static final double IGNORE_VERSIONS = -1.0D;
  private List<ExclusionStrategy> deserializationStrategies = Collections.emptyList();
  private int modifiers = 136;
  private boolean requireExpose;
  private List<ExclusionStrategy> serializationStrategies = Collections.emptyList();
  private boolean serializeInnerClasses = true;
  private double version = -1.0D;
  
  private boolean isAnonymousOrLocal(Class<?> paramClass)
  {
    if ((!Enum.class.isAssignableFrom(paramClass)) && ((paramClass.isAnonymousClass()) || (paramClass.isLocalClass()))) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  private boolean isInnerClass(Class<?> paramClass)
  {
    if ((paramClass.isMemberClass()) && (!isStatic(paramClass))) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  private boolean isStatic(Class<?> paramClass)
  {
    if ((paramClass.getModifiers() & 0x8) != 0) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  private boolean isValidSince(Since paramSince)
  {
    if ((paramSince != null) && (paramSince.value() > this.version)) {}
    for (boolean bool = false;; bool = true) {
      return bool;
    }
  }
  
  private boolean isValidUntil(Until paramUntil)
  {
    if ((paramUntil != null) && (paramUntil.value() <= this.version)) {}
    for (boolean bool = false;; bool = true) {
      return bool;
    }
  }
  
  private boolean isValidVersion(Since paramSince, Until paramUntil)
  {
    if ((isValidSince(paramSince)) && (isValidUntil(paramUntil))) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  protected Excluder clone()
  {
    try
    {
      Excluder localExcluder = (Excluder)super.clone();
      return localExcluder;
    }
    catch (CloneNotSupportedException localCloneNotSupportedException)
    {
      throw new AssertionError();
    }
  }
  
  public <T> TypeAdapter<T> create(final Gson paramGson, final TypeToken<T> paramTypeToken)
  {
    Class localClass = paramTypeToken.getRawType();
    final boolean bool1 = excludeClass(localClass, true);
    final boolean bool2 = excludeClass(localClass, false);
    if ((!bool1) && (!bool2)) {}
    for (paramGson = null;; paramGson = new TypeAdapter()
        {
          private TypeAdapter<T> delegate;
          
          private TypeAdapter<T> delegate()
          {
            TypeAdapter localTypeAdapter = this.delegate;
            if (localTypeAdapter != null) {}
            for (;;)
            {
              return localTypeAdapter;
              localTypeAdapter = paramGson.getDelegateAdapter(Excluder.this, paramTypeToken);
              this.delegate = localTypeAdapter;
            }
          }
          
          public T read(JsonReader paramAnonymousJsonReader)
            throws IOException
          {
            if (bool2) {
              paramAnonymousJsonReader.skipValue();
            }
            for (paramAnonymousJsonReader = null;; paramAnonymousJsonReader = delegate().read(paramAnonymousJsonReader)) {
              return paramAnonymousJsonReader;
            }
          }
          
          public void write(JsonWriter paramAnonymousJsonWriter, T paramAnonymousT)
            throws IOException
          {
            if (bool1) {
              paramAnonymousJsonWriter.nullValue();
            }
            for (;;)
            {
              return;
              delegate().write(paramAnonymousJsonWriter, paramAnonymousT);
            }
          }
        }) {
      return paramGson;
    }
  }
  
  public Excluder disableInnerClassSerialization()
  {
    Excluder localExcluder = clone();
    localExcluder.serializeInnerClasses = false;
    return localExcluder;
  }
  
  public boolean excludeClass(Class<?> paramClass, boolean paramBoolean)
  {
    if ((this.version != -1.0D) && (!isValidVersion((Since)paramClass.getAnnotation(Since.class), (Until)paramClass.getAnnotation(Until.class)))) {
      paramBoolean = true;
    }
    for (;;)
    {
      return paramBoolean;
      if ((!this.serializeInnerClasses) && (isInnerClass(paramClass)))
      {
        paramBoolean = true;
      }
      else if (isAnonymousOrLocal(paramClass))
      {
        paramBoolean = true;
      }
      else
      {
        if (paramBoolean) {}
        for (Object localObject = this.serializationStrategies;; localObject = this.deserializationStrategies)
        {
          localObject = ((List)localObject).iterator();
          do
          {
            if (!((Iterator)localObject).hasNext()) {
              break;
            }
          } while (!((ExclusionStrategy)((Iterator)localObject).next()).shouldSkipClass(paramClass));
          paramBoolean = true;
          break;
        }
        paramBoolean = false;
      }
    }
  }
  
  public boolean excludeField(Field paramField, boolean paramBoolean)
  {
    if ((this.modifiers & paramField.getModifiers()) != 0) {
      paramBoolean = true;
    }
    for (;;)
    {
      return paramBoolean;
      if ((this.version != -1.0D) && (!isValidVersion((Since)paramField.getAnnotation(Since.class), (Until)paramField.getAnnotation(Until.class))))
      {
        paramBoolean = true;
      }
      else if (paramField.isSynthetic())
      {
        paramBoolean = true;
      }
      else
      {
        Object localObject;
        if (this.requireExpose)
        {
          localObject = (Expose)paramField.getAnnotation(Expose.class);
          if (localObject != null)
          {
            if (!paramBoolean) {
              break label108;
            }
            if (((Expose)localObject).serialize()) {
              break label117;
            }
          }
          label108:
          while (!((Expose)localObject).deserialize())
          {
            paramBoolean = true;
            break;
          }
        }
        label117:
        if ((!this.serializeInnerClasses) && (isInnerClass(paramField.getType())))
        {
          paramBoolean = true;
        }
        else if (isAnonymousOrLocal(paramField.getType()))
        {
          paramBoolean = true;
        }
        else
        {
          if (paramBoolean) {}
          for (localObject = this.serializationStrategies;; localObject = this.deserializationStrategies)
          {
            if (((List)localObject).isEmpty()) {
              break label230;
            }
            paramField = new FieldAttributes(paramField);
            localObject = ((List)localObject).iterator();
            do
            {
              if (!((Iterator)localObject).hasNext()) {
                break;
              }
            } while (!((ExclusionStrategy)((Iterator)localObject).next()).shouldSkipField(paramField));
            paramBoolean = true;
            break;
          }
          label230:
          paramBoolean = false;
        }
      }
    }
  }
  
  public Excluder excludeFieldsWithoutExposeAnnotation()
  {
    Excluder localExcluder = clone();
    localExcluder.requireExpose = true;
    return localExcluder;
  }
  
  public Excluder withExclusionStrategy(ExclusionStrategy paramExclusionStrategy, boolean paramBoolean1, boolean paramBoolean2)
  {
    Excluder localExcluder = clone();
    if (paramBoolean1)
    {
      localExcluder.serializationStrategies = new ArrayList(this.serializationStrategies);
      localExcluder.serializationStrategies.add(paramExclusionStrategy);
    }
    if (paramBoolean2)
    {
      localExcluder.deserializationStrategies = new ArrayList(this.deserializationStrategies);
      localExcluder.deserializationStrategies.add(paramExclusionStrategy);
    }
    return localExcluder;
  }
  
  public Excluder withModifiers(int... paramVarArgs)
  {
    Excluder localExcluder = clone();
    localExcluder.modifiers = 0;
    int j = paramVarArgs.length;
    for (int i = 0; i < j; i++)
    {
      int k = paramVarArgs[i];
      localExcluder.modifiers |= k;
    }
    return localExcluder;
  }
  
  public Excluder withVersion(double paramDouble)
  {
    Excluder localExcluder = clone();
    localExcluder.version = paramDouble;
    return localExcluder;
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\gson\internal\Excluder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */