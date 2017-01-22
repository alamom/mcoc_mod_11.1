package com.google.gson.internal.bind;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSyntaxException;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.internal..Gson.Types;
import com.google.gson.internal.ConstructorConstructor;
import com.google.gson.internal.JsonReaderInternalAccess;
import com.google.gson.internal.ObjectConstructor;
import com.google.gson.internal.Streams;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public final class MapTypeAdapterFactory
  implements TypeAdapterFactory
{
  private final boolean complexMapKeySerialization;
  private final ConstructorConstructor constructorConstructor;
  
  public MapTypeAdapterFactory(ConstructorConstructor paramConstructorConstructor, boolean paramBoolean)
  {
    this.constructorConstructor = paramConstructorConstructor;
    this.complexMapKeySerialization = paramBoolean;
  }
  
  private TypeAdapter<?> getKeyAdapter(Gson paramGson, Type paramType)
  {
    if ((paramType == Boolean.TYPE) || (paramType == Boolean.class)) {}
    for (paramGson = TypeAdapters.BOOLEAN_AS_STRING;; paramGson = paramGson.getAdapter(TypeToken.get(paramType))) {
      return paramGson;
    }
  }
  
  public <T> TypeAdapter<T> create(Gson paramGson, TypeToken<T> paramTypeToken)
  {
    Object localObject = paramTypeToken.getType();
    if (!Map.class.isAssignableFrom(paramTypeToken.getRawType())) {}
    TypeAdapter localTypeAdapter2;
    TypeAdapter localTypeAdapter1;
    for (paramGson = null;; paramGson = new Adapter(paramGson, localObject[0], localTypeAdapter2, localObject[1], localTypeAdapter1, paramTypeToken))
    {
      return paramGson;
      localObject = .Gson.Types.getMapKeyAndValueTypes((Type)localObject, .Gson.Types.getRawType((Type)localObject));
      localTypeAdapter2 = getKeyAdapter(paramGson, localObject[0]);
      localTypeAdapter1 = paramGson.getAdapter(TypeToken.get(localObject[1]));
      paramTypeToken = this.constructorConstructor.get(paramTypeToken);
    }
  }
  
  private final class Adapter<K, V>
    extends TypeAdapter<Map<K, V>>
  {
    private final ObjectConstructor<? extends Map<K, V>> constructor;
    private final TypeAdapter<K> keyTypeAdapter;
    private final TypeAdapter<V> valueTypeAdapter;
    
    public Adapter(Type paramType1, TypeAdapter<K> paramTypeAdapter, Type paramType2, TypeAdapter<V> paramTypeAdapter1, ObjectConstructor<? extends Map<K, V>> paramObjectConstructor)
    {
      this.keyTypeAdapter = new TypeAdapterRuntimeTypeWrapper(paramType1, paramType2, paramTypeAdapter);
      this.valueTypeAdapter = new TypeAdapterRuntimeTypeWrapper(paramType1, paramObjectConstructor, paramTypeAdapter1);
      ObjectConstructor localObjectConstructor;
      this.constructor = localObjectConstructor;
    }
    
    private String keyToString(JsonElement paramJsonElement)
    {
      if (paramJsonElement.isJsonPrimitive())
      {
        paramJsonElement = paramJsonElement.getAsJsonPrimitive();
        if (paramJsonElement.isNumber()) {
          paramJsonElement = String.valueOf(paramJsonElement.getAsNumber());
        }
      }
      for (;;)
      {
        return paramJsonElement;
        if (paramJsonElement.isBoolean())
        {
          paramJsonElement = Boolean.toString(paramJsonElement.getAsBoolean());
        }
        else if (paramJsonElement.isString())
        {
          paramJsonElement = paramJsonElement.getAsString();
        }
        else
        {
          throw new AssertionError();
          if (!paramJsonElement.isJsonNull()) {
            break;
          }
          paramJsonElement = "null";
        }
      }
      throw new AssertionError();
    }
    
    public Map<K, V> read(JsonReader paramJsonReader)
      throws IOException
    {
      Object localObject = paramJsonReader.peek();
      if (localObject == JsonToken.NULL)
      {
        paramJsonReader.nextNull();
        paramJsonReader = null;
      }
      for (;;)
      {
        return paramJsonReader;
        Map localMap = (Map)this.constructor.construct();
        if (localObject == JsonToken.BEGIN_ARRAY)
        {
          paramJsonReader.beginArray();
          while (paramJsonReader.hasNext())
          {
            paramJsonReader.beginArray();
            localObject = this.keyTypeAdapter.read(paramJsonReader);
            if (localMap.put(localObject, this.valueTypeAdapter.read(paramJsonReader)) != null) {
              throw new JsonSyntaxException("duplicate key: " + localObject);
            }
            paramJsonReader.endArray();
          }
          paramJsonReader.endArray();
          paramJsonReader = localMap;
        }
        else
        {
          paramJsonReader.beginObject();
          while (paramJsonReader.hasNext())
          {
            JsonReaderInternalAccess.INSTANCE.promoteNameToValue(paramJsonReader);
            localObject = this.keyTypeAdapter.read(paramJsonReader);
            if (localMap.put(localObject, this.valueTypeAdapter.read(paramJsonReader)) != null) {
              throw new JsonSyntaxException("duplicate key: " + localObject);
            }
          }
          paramJsonReader.endObject();
          paramJsonReader = localMap;
        }
      }
    }
    
    public void write(JsonWriter paramJsonWriter, Map<K, V> paramMap)
      throws IOException
    {
      if (paramMap == null) {
        paramJsonWriter.nullValue();
      }
      for (;;)
      {
        return;
        Object localObject;
        if (!MapTypeAdapterFactory.this.complexMapKeySerialization)
        {
          paramJsonWriter.beginObject();
          paramMap = paramMap.entrySet().iterator();
          while (paramMap.hasNext())
          {
            localObject = (Map.Entry)paramMap.next();
            paramJsonWriter.name(String.valueOf(((Map.Entry)localObject).getKey()));
            this.valueTypeAdapter.write(paramJsonWriter, ((Map.Entry)localObject).getValue());
          }
          paramJsonWriter.endObject();
        }
        else
        {
          int i = 0;
          ArrayList localArrayList = new ArrayList(paramMap.size());
          localObject = new ArrayList(paramMap.size());
          paramMap = paramMap.entrySet().iterator();
          if (paramMap.hasNext())
          {
            Map.Entry localEntry = (Map.Entry)paramMap.next();
            JsonElement localJsonElement = this.keyTypeAdapter.toJsonTree(localEntry.getKey());
            localArrayList.add(localJsonElement);
            ((List)localObject).add(localEntry.getValue());
            if ((localJsonElement.isJsonArray()) || (localJsonElement.isJsonObject())) {}
            for (int j = 1;; j = 0)
            {
              i |= j;
              break;
            }
          }
          if (i != 0)
          {
            paramJsonWriter.beginArray();
            for (i = 0; i < localArrayList.size(); i++)
            {
              paramJsonWriter.beginArray();
              Streams.write((JsonElement)localArrayList.get(i), paramJsonWriter);
              this.valueTypeAdapter.write(paramJsonWriter, ((List)localObject).get(i));
              paramJsonWriter.endArray();
            }
            paramJsonWriter.endArray();
          }
          else
          {
            paramJsonWriter.beginObject();
            for (i = 0; i < localArrayList.size(); i++)
            {
              paramJsonWriter.name(keyToString((JsonElement)localArrayList.get(i)));
              this.valueTypeAdapter.write(paramJsonWriter, ((List)localObject).get(i));
            }
            paramJsonWriter.endObject();
          }
        }
      }
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\gson\internal\bind\MapTypeAdapterFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */