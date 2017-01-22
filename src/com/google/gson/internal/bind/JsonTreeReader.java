package com.google.gson.internal.bind;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

public final class JsonTreeReader
  extends JsonReader
{
  private static final Object SENTINEL_CLOSED = new Object();
  private static final Reader UNREADABLE_READER = new Reader()
  {
    public void close()
      throws IOException
    {
      throw new AssertionError();
    }
    
    public int read(char[] paramAnonymousArrayOfChar, int paramAnonymousInt1, int paramAnonymousInt2)
      throws IOException
    {
      throw new AssertionError();
    }
  };
  private final List<Object> stack = new ArrayList();
  
  public JsonTreeReader(JsonElement paramJsonElement)
  {
    super(UNREADABLE_READER);
    this.stack.add(paramJsonElement);
  }
  
  private void expect(JsonToken paramJsonToken)
    throws IOException
  {
    if (peek() != paramJsonToken) {
      throw new IllegalStateException("Expected " + paramJsonToken + " but was " + peek());
    }
  }
  
  private Object peekStack()
  {
    return this.stack.get(this.stack.size() - 1);
  }
  
  private Object popStack()
  {
    return this.stack.remove(this.stack.size() - 1);
  }
  
  public void beginArray()
    throws IOException
  {
    expect(JsonToken.BEGIN_ARRAY);
    JsonArray localJsonArray = (JsonArray)peekStack();
    this.stack.add(localJsonArray.iterator());
  }
  
  public void beginObject()
    throws IOException
  {
    expect(JsonToken.BEGIN_OBJECT);
    JsonObject localJsonObject = (JsonObject)peekStack();
    this.stack.add(localJsonObject.entrySet().iterator());
  }
  
  public void close()
    throws IOException
  {
    this.stack.clear();
    this.stack.add(SENTINEL_CLOSED);
  }
  
  public void endArray()
    throws IOException
  {
    expect(JsonToken.END_ARRAY);
    popStack();
    popStack();
  }
  
  public void endObject()
    throws IOException
  {
    expect(JsonToken.END_OBJECT);
    popStack();
    popStack();
  }
  
  public boolean hasNext()
    throws IOException
  {
    JsonToken localJsonToken = peek();
    if ((localJsonToken != JsonToken.END_OBJECT) && (localJsonToken != JsonToken.END_ARRAY)) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public boolean nextBoolean()
    throws IOException
  {
    expect(JsonToken.BOOLEAN);
    return ((JsonPrimitive)popStack()).getAsBoolean();
  }
  
  public double nextDouble()
    throws IOException
  {
    JsonToken localJsonToken = peek();
    if ((localJsonToken != JsonToken.NUMBER) && (localJsonToken != JsonToken.STRING)) {
      throw new IllegalStateException("Expected " + JsonToken.NUMBER + " but was " + localJsonToken);
    }
    double d = ((JsonPrimitive)peekStack()).getAsDouble();
    if ((!isLenient()) && ((Double.isNaN(d)) || (Double.isInfinite(d)))) {
      throw new NumberFormatException("JSON forbids NaN and infinities: " + d);
    }
    popStack();
    return d;
  }
  
  public int nextInt()
    throws IOException
  {
    JsonToken localJsonToken = peek();
    if ((localJsonToken != JsonToken.NUMBER) && (localJsonToken != JsonToken.STRING)) {
      throw new IllegalStateException("Expected " + JsonToken.NUMBER + " but was " + localJsonToken);
    }
    int i = ((JsonPrimitive)peekStack()).getAsInt();
    popStack();
    return i;
  }
  
  public long nextLong()
    throws IOException
  {
    JsonToken localJsonToken = peek();
    if ((localJsonToken != JsonToken.NUMBER) && (localJsonToken != JsonToken.STRING)) {
      throw new IllegalStateException("Expected " + JsonToken.NUMBER + " but was " + localJsonToken);
    }
    long l = ((JsonPrimitive)peekStack()).getAsLong();
    popStack();
    return l;
  }
  
  public String nextName()
    throws IOException
  {
    expect(JsonToken.NAME);
    Map.Entry localEntry = (Map.Entry)((Iterator)peekStack()).next();
    this.stack.add(localEntry.getValue());
    return (String)localEntry.getKey();
  }
  
  public void nextNull()
    throws IOException
  {
    expect(JsonToken.NULL);
    popStack();
  }
  
  public String nextString()
    throws IOException
  {
    JsonToken localJsonToken = peek();
    if ((localJsonToken != JsonToken.STRING) && (localJsonToken != JsonToken.NUMBER)) {
      throw new IllegalStateException("Expected " + JsonToken.STRING + " but was " + localJsonToken);
    }
    return ((JsonPrimitive)popStack()).getAsString();
  }
  
  public JsonToken peek()
    throws IOException
  {
    Object localObject;
    if (this.stack.isEmpty()) {
      localObject = JsonToken.END_DOCUMENT;
    }
    for (;;)
    {
      return (JsonToken)localObject;
      localObject = peekStack();
      if ((localObject instanceof Iterator))
      {
        boolean bool = this.stack.get(this.stack.size() - 2) instanceof JsonObject;
        localObject = (Iterator)localObject;
        if (((Iterator)localObject).hasNext())
        {
          if (bool)
          {
            localObject = JsonToken.NAME;
          }
          else
          {
            this.stack.add(((Iterator)localObject).next());
            localObject = peek();
          }
        }
        else if (bool) {
          localObject = JsonToken.END_OBJECT;
        } else {
          localObject = JsonToken.END_ARRAY;
        }
      }
      else if ((localObject instanceof JsonObject))
      {
        localObject = JsonToken.BEGIN_OBJECT;
      }
      else if ((localObject instanceof JsonArray))
      {
        localObject = JsonToken.BEGIN_ARRAY;
      }
      else if ((localObject instanceof JsonPrimitive))
      {
        localObject = (JsonPrimitive)localObject;
        if (((JsonPrimitive)localObject).isString()) {
          localObject = JsonToken.STRING;
        } else if (((JsonPrimitive)localObject).isBoolean()) {
          localObject = JsonToken.BOOLEAN;
        } else if (((JsonPrimitive)localObject).isNumber()) {
          localObject = JsonToken.NUMBER;
        } else {
          throw new AssertionError();
        }
      }
      else
      {
        if (!(localObject instanceof JsonNull)) {
          break;
        }
        localObject = JsonToken.NULL;
      }
    }
    if (localObject == SENTINEL_CLOSED) {
      throw new IllegalStateException("JsonReader is closed");
    }
    throw new AssertionError();
  }
  
  public void promoteNameToValue()
    throws IOException
  {
    expect(JsonToken.NAME);
    Map.Entry localEntry = (Map.Entry)((Iterator)peekStack()).next();
    this.stack.add(localEntry.getValue());
    this.stack.add(new JsonPrimitive((String)localEntry.getKey()));
  }
  
  public void skipValue()
    throws IOException
  {
    if (peek() == JsonToken.NAME) {
      nextName();
    }
    for (;;)
    {
      return;
      popStack();
    }
  }
  
  public String toString()
  {
    return getClass().getSimpleName();
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\gson\internal\bind\JsonTreeReader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */