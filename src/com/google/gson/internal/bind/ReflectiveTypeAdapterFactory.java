package com.google.gson.internal.bind;

import com.google.gson.FieldNamingStrategy;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.internal..Gson.Types;
import com.google.gson.internal.ConstructorConstructor;
import com.google.gson.internal.Excluder;
import com.google.gson.internal.ObjectConstructor;
import com.google.gson.internal.Primitives;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public final class ReflectiveTypeAdapterFactory
  implements TypeAdapterFactory
{
  private final ConstructorConstructor constructorConstructor;
  private final Excluder excluder;
  private final FieldNamingStrategy fieldNamingPolicy;
  
  public ReflectiveTypeAdapterFactory(ConstructorConstructor paramConstructorConstructor, FieldNamingStrategy paramFieldNamingStrategy, Excluder paramExcluder)
  {
    this.constructorConstructor = paramConstructorConstructor;
    this.fieldNamingPolicy = paramFieldNamingStrategy;
    this.excluder = paramExcluder;
  }
  
  private BoundField createBoundField(final Gson paramGson, final Field paramField, String paramString, final TypeToken<?> paramTypeToken, boolean paramBoolean1, boolean paramBoolean2)
  {
    new BoundField(paramString, paramBoolean1, paramBoolean2)
    {
      final TypeAdapter<?> typeAdapter = ReflectiveTypeAdapterFactory.this.getFieldAdapter(paramGson, paramField, paramTypeToken);
      
      void read(JsonReader paramAnonymousJsonReader, Object paramAnonymousObject)
        throws IOException, IllegalAccessException
      {
        paramAnonymousJsonReader = this.typeAdapter.read(paramAnonymousJsonReader);
        if ((paramAnonymousJsonReader != null) || (!this.val$isPrimitive)) {
          paramField.set(paramAnonymousObject, paramAnonymousJsonReader);
        }
      }
      
      void write(JsonWriter paramAnonymousJsonWriter, Object paramAnonymousObject)
        throws IOException, IllegalAccessException
      {
        paramAnonymousObject = paramField.get(paramAnonymousObject);
        new TypeAdapterRuntimeTypeWrapper(paramGson, this.typeAdapter, paramTypeToken.getType()).write(paramAnonymousJsonWriter, paramAnonymousObject);
      }
      
      public boolean writeField(Object paramAnonymousObject)
        throws IOException, IllegalAccessException
      {
        boolean bool = false;
        if (!this.serialized) {}
        for (;;)
        {
          return bool;
          if (paramField.get(paramAnonymousObject) != paramAnonymousObject) {
            bool = true;
          }
        }
      }
    };
  }
  
  static boolean excludeField(Field paramField, boolean paramBoolean, Excluder paramExcluder)
  {
    if ((!paramExcluder.excludeClass(paramField.getType(), paramBoolean)) && (!paramExcluder.excludeField(paramField, paramBoolean))) {}
    for (paramBoolean = true;; paramBoolean = false) {
      return paramBoolean;
    }
  }
  
  private Map<String, BoundField> getBoundFields(Gson paramGson, TypeToken<?> paramTypeToken, Class<?> paramClass)
  {
    LinkedHashMap localLinkedHashMap = new LinkedHashMap();
    if (paramClass.isInterface()) {}
    for (;;)
    {
      return localLinkedHashMap;
      Type localType1 = paramTypeToken.getType();
      while (paramClass != Object.class)
      {
        Field[] arrayOfField = paramClass.getDeclaredFields();
        int j = arrayOfField.length;
        int i = 0;
        if (i < j)
        {
          Object localObject = arrayOfField[i];
          boolean bool2 = excludeField((Field)localObject, true);
          boolean bool1 = excludeField((Field)localObject, false);
          if ((!bool2) && (!bool1)) {}
          do
          {
            i++;
            break;
            ((Field)localObject).setAccessible(true);
            Type localType2 = .Gson.Types.resolve(paramTypeToken.getType(), paramClass, ((Field)localObject).getGenericType());
            localObject = createBoundField(paramGson, (Field)localObject, getFieldName((Field)localObject), TypeToken.get(localType2), bool2, bool1);
            localObject = (BoundField)localLinkedHashMap.put(((BoundField)localObject).name, localObject);
          } while (localObject == null);
          throw new IllegalArgumentException(localType1 + " declares multiple JSON fields named " + ((BoundField)localObject).name);
        }
        paramTypeToken = TypeToken.get(.Gson.Types.resolve(paramTypeToken.getType(), paramClass, paramClass.getGenericSuperclass()));
        paramClass = paramTypeToken.getRawType();
      }
    }
  }
  
  private TypeAdapter<?> getFieldAdapter(Gson paramGson, Field paramField, TypeToken<?> paramTypeToken)
  {
    paramField = (JsonAdapter)paramField.getAnnotation(JsonAdapter.class);
    if (paramField != null)
    {
      paramField = JsonAdapterAnnotationTypeAdapterFactory.getTypeAdapter(this.constructorConstructor, paramGson, paramTypeToken, paramField);
      if (paramField == null) {}
    }
    for (paramGson = paramField;; paramGson = paramGson.getAdapter(paramTypeToken)) {
      return paramGson;
    }
  }
  
  static String getFieldName(FieldNamingStrategy paramFieldNamingStrategy, Field paramField)
  {
    SerializedName localSerializedName = (SerializedName)paramField.getAnnotation(SerializedName.class);
    if (localSerializedName == null) {}
    for (paramFieldNamingStrategy = paramFieldNamingStrategy.translateName(paramField);; paramFieldNamingStrategy = localSerializedName.value()) {
      return paramFieldNamingStrategy;
    }
  }
  
  private String getFieldName(Field paramField)
  {
    return getFieldName(this.fieldNamingPolicy, paramField);
  }
  
  public <T> TypeAdapter<T> create(Gson paramGson, TypeToken<T> paramTypeToken)
  {
    Object localObject = null;
    Class localClass = paramTypeToken.getRawType();
    if (!Object.class.isAssignableFrom(localClass)) {}
    for (paramGson = (Gson)localObject;; paramGson = new Adapter(this.constructorConstructor.get(paramTypeToken), getBoundFields(paramGson, paramTypeToken, localClass), null)) {
      return paramGson;
    }
  }
  
  public boolean excludeField(Field paramField, boolean paramBoolean)
  {
    return excludeField(paramField, paramBoolean, this.excluder);
  }
  
  public static final class Adapter<T>
    extends TypeAdapter<T>
  {
    private final Map<String, ReflectiveTypeAdapterFactory.BoundField> boundFields;
    private final ObjectConstructor<T> constructor;
    
    private Adapter(ObjectConstructor<T> paramObjectConstructor, Map<String, ReflectiveTypeAdapterFactory.BoundField> paramMap)
    {
      this.constructor = paramObjectConstructor;
      this.boundFields = paramMap;
    }
    
    public T read(JsonReader paramJsonReader)
      throws IOException
    {
      if (paramJsonReader.peek() == JsonToken.NULL) {
        paramJsonReader.nextNull();
      }
      Object localObject1;
      for (paramJsonReader = null;; paramJsonReader = (JsonReader)localObject1)
      {
        return paramJsonReader;
        localObject1 = this.constructor.construct();
        try
        {
          paramJsonReader.beginObject();
          for (;;)
          {
            if (!paramJsonReader.hasNext()) {
              break label105;
            }
            localObject2 = paramJsonReader.nextName();
            localObject2 = (ReflectiveTypeAdapterFactory.BoundField)this.boundFields.get(localObject2);
            if ((localObject2 != null) && (((ReflectiveTypeAdapterFactory.BoundField)localObject2).deserialized)) {
              break;
            }
            paramJsonReader.skipValue();
          }
        }
        catch (IllegalStateException paramJsonReader)
        {
          for (;;)
          {
            Object localObject2;
            throw new JsonSyntaxException(paramJsonReader);
            ((ReflectiveTypeAdapterFactory.BoundField)localObject2).read(paramJsonReader, localObject1);
          }
        }
        catch (IllegalAccessException paramJsonReader)
        {
          throw new AssertionError(paramJsonReader);
        }
        label105:
        paramJsonReader.endObject();
      }
    }
    
    public void write(JsonWriter paramJsonWriter, T paramT)
      throws IOException
    {
      if (paramT == null) {
        paramJsonWriter.nullValue();
      }
      for (;;)
      {
        return;
        paramJsonWriter.beginObject();
        try
        {
          Iterator localIterator = this.boundFields.values().iterator();
          while (localIterator.hasNext())
          {
            ReflectiveTypeAdapterFactory.BoundField localBoundField = (ReflectiveTypeAdapterFactory.BoundField)localIterator.next();
            if (localBoundField.writeField(paramT))
            {
              paramJsonWriter.name(localBoundField.name);
              localBoundField.write(paramJsonWriter, paramT);
            }
          }
          paramJsonWriter.endObject();
        }
        catch (IllegalAccessException paramJsonWriter)
        {
          throw new AssertionError();
        }
      }
    }
  }
  
  static abstract class BoundField
  {
    final boolean deserialized;
    final String name;
    final boolean serialized;
    
    protected BoundField(String paramString, boolean paramBoolean1, boolean paramBoolean2)
    {
      this.name = paramString;
      this.serialized = paramBoolean1;
      this.deserialized = paramBoolean2;
    }
    
    abstract void read(JsonReader paramJsonReader, Object paramObject)
      throws IOException, IllegalAccessException;
    
    abstract void write(JsonWriter paramJsonWriter, Object paramObject)
      throws IOException, IllegalAccessException;
    
    abstract boolean writeField(Object paramObject)
      throws IOException, IllegalAccessException;
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\gson\internal\bind\ReflectiveTypeAdapterFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */