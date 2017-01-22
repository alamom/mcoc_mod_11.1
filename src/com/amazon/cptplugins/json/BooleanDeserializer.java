package com.amazon.cptplugins.json;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import java.lang.reflect.Type;

class BooleanDeserializer
  implements JsonDeserializer<Boolean>
{
  private String getValue(JsonElement paramJsonElement)
  {
    return paramJsonElement.getAsJsonPrimitive().getAsString();
  }
  
  public Boolean deserialize(JsonElement paramJsonElement, Type paramType, JsonDeserializationContext paramJsonDeserializationContext)
  {
    return Boolean.valueOf(getValue(paramJsonElement).toLowerCase());
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\amazon\cptplugins\json\BooleanDeserializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */