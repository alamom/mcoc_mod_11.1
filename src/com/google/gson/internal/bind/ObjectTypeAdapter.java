package com.google.gson.internal.bind;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class ObjectTypeAdapter
  extends TypeAdapter<Object>
{
  public static final TypeAdapterFactory FACTORY = new TypeAdapterFactory()
  {
    public <T> TypeAdapter<T> create(Gson paramAnonymousGson, TypeToken<T> paramAnonymousTypeToken)
    {
      if (paramAnonymousTypeToken.getRawType() == Object.class) {}
      for (paramAnonymousGson = new ObjectTypeAdapter(paramAnonymousGson, null);; paramAnonymousGson = null) {
        return paramAnonymousGson;
      }
    }
  };
  private final Gson gson;
  
  private ObjectTypeAdapter(Gson paramGson)
  {
    this.gson = paramGson;
  }
  
  public Object read(JsonReader paramJsonReader)
    throws IOException
  {
    Object localObject = paramJsonReader.peek();
    switch (localObject)
    {
    default: 
      throw new IllegalStateException();
    case ???: 
      localObject = new ArrayList();
      paramJsonReader.beginArray();
      while (paramJsonReader.hasNext()) {
        ((List)localObject).add(read(paramJsonReader));
      }
      paramJsonReader.endArray();
      paramJsonReader = (JsonReader)localObject;
    }
    for (;;)
    {
      return paramJsonReader;
      localObject = new LinkedTreeMap();
      paramJsonReader.beginObject();
      while (paramJsonReader.hasNext()) {
        ((Map)localObject).put(paramJsonReader.nextName(), read(paramJsonReader));
      }
      paramJsonReader.endObject();
      paramJsonReader = (JsonReader)localObject;
      continue;
      paramJsonReader = paramJsonReader.nextString();
      continue;
      paramJsonReader = Double.valueOf(paramJsonReader.nextDouble());
      continue;
      paramJsonReader = Boolean.valueOf(paramJsonReader.nextBoolean());
      continue;
      paramJsonReader.nextNull();
      paramJsonReader = null;
    }
  }
  
  public void write(JsonWriter paramJsonWriter, Object paramObject)
    throws IOException
  {
    if (paramObject == null) {
      paramJsonWriter.nullValue();
    }
    for (;;)
    {
      return;
      TypeAdapter localTypeAdapter = this.gson.getAdapter(paramObject.getClass());
      if ((localTypeAdapter instanceof ObjectTypeAdapter))
      {
        paramJsonWriter.beginObject();
        paramJsonWriter.endObject();
      }
      else
      {
        localTypeAdapter.write(paramJsonWriter, paramObject);
      }
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\gson\internal\bind\ObjectTypeAdapter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */