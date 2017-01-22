package com.google.gson.internal.bind;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.internal.ConstructorConstructor;
import com.google.gson.internal.ObjectConstructor;
import com.google.gson.reflect.TypeToken;

public final class JsonAdapterAnnotationTypeAdapterFactory
  implements TypeAdapterFactory
{
  private final ConstructorConstructor constructorConstructor;
  
  public JsonAdapterAnnotationTypeAdapterFactory(ConstructorConstructor paramConstructorConstructor)
  {
    this.constructorConstructor = paramConstructorConstructor;
  }
  
  static TypeAdapter<?> getTypeAdapter(ConstructorConstructor paramConstructorConstructor, Gson paramGson, TypeToken<?> paramTypeToken, JsonAdapter paramJsonAdapter)
  {
    paramJsonAdapter = paramJsonAdapter.value();
    if (TypeAdapter.class.isAssignableFrom(paramJsonAdapter)) {}
    for (paramConstructorConstructor = (TypeAdapter)paramConstructorConstructor.get(TypeToken.get(paramJsonAdapter)).construct();; paramConstructorConstructor = ((TypeAdapterFactory)paramConstructorConstructor.get(TypeToken.get(paramJsonAdapter)).construct()).create(paramGson, paramTypeToken))
    {
      return paramConstructorConstructor;
      if (!TypeAdapterFactory.class.isAssignableFrom(paramJsonAdapter)) {
        break;
      }
    }
    throw new IllegalArgumentException("@JsonAdapter value must be TypeAdapter or TypeAdapterFactory reference.");
  }
  
  public <T> TypeAdapter<T> create(Gson paramGson, TypeToken<T> paramTypeToken)
  {
    JsonAdapter localJsonAdapter = (JsonAdapter)paramTypeToken.getRawType().getAnnotation(JsonAdapter.class);
    if (localJsonAdapter == null) {}
    for (paramGson = null;; paramGson = getTypeAdapter(this.constructorConstructor, paramGson, paramTypeToken, localJsonAdapter)) {
      return paramGson;
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\gson\internal\bind\JsonAdapterAnnotationTypeAdapterFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */