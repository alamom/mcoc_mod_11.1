package com.google.gson.internal.bind;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSyntaxException;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.annotations.SerializedName;
import com.google.gson.internal.LazilyParsedNumber;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.BitSet;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.UUID;

public final class TypeAdapters
{
  public static final TypeAdapter<BigDecimal> BIG_DECIMAL;
  public static final TypeAdapter<BigInteger> BIG_INTEGER;
  public static final TypeAdapter<BitSet> BIT_SET;
  public static final TypeAdapterFactory BIT_SET_FACTORY;
  public static final TypeAdapter<Boolean> BOOLEAN;
  public static final TypeAdapter<Boolean> BOOLEAN_AS_STRING;
  public static final TypeAdapterFactory BOOLEAN_FACTORY;
  public static final TypeAdapter<Number> BYTE;
  public static final TypeAdapterFactory BYTE_FACTORY;
  public static final TypeAdapter<Calendar> CALENDAR;
  public static final TypeAdapterFactory CALENDAR_FACTORY;
  public static final TypeAdapter<Character> CHARACTER;
  public static final TypeAdapterFactory CHARACTER_FACTORY;
  public static final TypeAdapter<Class> CLASS = new TypeAdapter()
  {
    public Class read(JsonReader paramAnonymousJsonReader)
      throws IOException
    {
      if (paramAnonymousJsonReader.peek() == JsonToken.NULL)
      {
        paramAnonymousJsonReader.nextNull();
        return null;
      }
      throw new UnsupportedOperationException("Attempted to deserialize a java.lang.Class. Forgot to register a type adapter?");
    }
    
    public void write(JsonWriter paramAnonymousJsonWriter, Class paramAnonymousClass)
      throws IOException
    {
      if (paramAnonymousClass == null)
      {
        paramAnonymousJsonWriter.nullValue();
        return;
      }
      throw new UnsupportedOperationException("Attempted to serialize java.lang.Class: " + paramAnonymousClass.getName() + ". Forgot to register a type adapter?");
    }
  };
  public static final TypeAdapterFactory CLASS_FACTORY = newFactory(Class.class, CLASS);
  public static final TypeAdapter<Number> DOUBLE;
  public static final TypeAdapterFactory ENUM_FACTORY = new TypeAdapterFactory()
  {
    public <T> TypeAdapter<T> create(Gson paramAnonymousGson, TypeToken<T> paramAnonymousTypeToken)
    {
      paramAnonymousTypeToken = paramAnonymousTypeToken.getRawType();
      if ((!Enum.class.isAssignableFrom(paramAnonymousTypeToken)) || (paramAnonymousTypeToken == Enum.class)) {}
      for (paramAnonymousGson = null;; paramAnonymousGson = new TypeAdapters.EnumTypeAdapter(paramAnonymousGson))
      {
        return paramAnonymousGson;
        paramAnonymousGson = paramAnonymousTypeToken;
        if (!paramAnonymousTypeToken.isEnum()) {
          paramAnonymousGson = paramAnonymousTypeToken.getSuperclass();
        }
      }
    }
  };
  public static final TypeAdapter<Number> FLOAT;
  public static final TypeAdapter<InetAddress> INET_ADDRESS;
  public static final TypeAdapterFactory INET_ADDRESS_FACTORY;
  public static final TypeAdapter<Number> INTEGER;
  public static final TypeAdapterFactory INTEGER_FACTORY;
  public static final TypeAdapter<JsonElement> JSON_ELEMENT;
  public static final TypeAdapterFactory JSON_ELEMENT_FACTORY;
  public static final TypeAdapter<Locale> LOCALE;
  public static final TypeAdapterFactory LOCALE_FACTORY;
  public static final TypeAdapter<Number> LONG;
  public static final TypeAdapter<Number> NUMBER;
  public static final TypeAdapterFactory NUMBER_FACTORY;
  public static final TypeAdapter<Number> SHORT;
  public static final TypeAdapterFactory SHORT_FACTORY;
  public static final TypeAdapter<String> STRING;
  public static final TypeAdapter<StringBuffer> STRING_BUFFER;
  public static final TypeAdapterFactory STRING_BUFFER_FACTORY;
  public static final TypeAdapter<StringBuilder> STRING_BUILDER;
  public static final TypeAdapterFactory STRING_BUILDER_FACTORY;
  public static final TypeAdapterFactory STRING_FACTORY;
  public static final TypeAdapterFactory TIMESTAMP_FACTORY;
  public static final TypeAdapter<URI> URI;
  public static final TypeAdapterFactory URI_FACTORY;
  public static final TypeAdapter<URL> URL;
  public static final TypeAdapterFactory URL_FACTORY;
  public static final TypeAdapter<UUID> UUID;
  public static final TypeAdapterFactory UUID_FACTORY;
  
  static
  {
    BIT_SET = new TypeAdapter()
    {
      public BitSet read(JsonReader paramAnonymousJsonReader)
        throws IOException
      {
        if (paramAnonymousJsonReader.peek() == JsonToken.NULL) {
          paramAnonymousJsonReader.nextNull();
        }
        BitSet localBitSet;
        for (paramAnonymousJsonReader = null;; paramAnonymousJsonReader = localBitSet)
        {
          return paramAnonymousJsonReader;
          localBitSet = new BitSet();
          paramAnonymousJsonReader.beginArray();
          int i = 0;
          Object localObject = paramAnonymousJsonReader.peek();
          if (localObject != JsonToken.END_ARRAY)
          {
            boolean bool;
            switch (TypeAdapters.32.$SwitchMap$com$google$gson$stream$JsonToken[localObject.ordinal()])
            {
            default: 
              throw new JsonSyntaxException("Invalid bitset value type: " + localObject);
            case 1: 
              if (paramAnonymousJsonReader.nextInt() != 0) {
                bool = true;
              }
              break;
            }
            for (;;)
            {
              if (bool) {
                localBitSet.set(i);
              }
              i++;
              localObject = paramAnonymousJsonReader.peek();
              break;
              bool = false;
              continue;
              bool = paramAnonymousJsonReader.nextBoolean();
              continue;
              localObject = paramAnonymousJsonReader.nextString();
              try
              {
                int j = Integer.parseInt((String)localObject);
                if (j != 0) {}
                for (bool = true;; bool = false) {
                  break;
                }
                paramAnonymousJsonReader.endArray();
              }
              catch (NumberFormatException paramAnonymousJsonReader)
              {
                throw new JsonSyntaxException("Error: Expecting: bitset number value (1, 0), Found: " + (String)localObject);
              }
            }
          }
        }
      }
      
      public void write(JsonWriter paramAnonymousJsonWriter, BitSet paramAnonymousBitSet)
        throws IOException
      {
        if (paramAnonymousBitSet == null) {
          paramAnonymousJsonWriter.nullValue();
        }
        for (;;)
        {
          return;
          paramAnonymousJsonWriter.beginArray();
          int i = 0;
          if (i < paramAnonymousBitSet.length())
          {
            if (paramAnonymousBitSet.get(i)) {}
            for (int j = 1;; j = 0)
            {
              paramAnonymousJsonWriter.value(j);
              i++;
              break;
            }
          }
          paramAnonymousJsonWriter.endArray();
        }
      }
    };
    BIT_SET_FACTORY = newFactory(BitSet.class, BIT_SET);
    BOOLEAN = new TypeAdapter()
    {
      public Boolean read(JsonReader paramAnonymousJsonReader)
        throws IOException
      {
        if (paramAnonymousJsonReader.peek() == JsonToken.NULL)
        {
          paramAnonymousJsonReader.nextNull();
          paramAnonymousJsonReader = null;
        }
        for (;;)
        {
          return paramAnonymousJsonReader;
          if (paramAnonymousJsonReader.peek() == JsonToken.STRING) {
            paramAnonymousJsonReader = Boolean.valueOf(Boolean.parseBoolean(paramAnonymousJsonReader.nextString()));
          } else {
            paramAnonymousJsonReader = Boolean.valueOf(paramAnonymousJsonReader.nextBoolean());
          }
        }
      }
      
      public void write(JsonWriter paramAnonymousJsonWriter, Boolean paramAnonymousBoolean)
        throws IOException
      {
        if (paramAnonymousBoolean == null) {
          paramAnonymousJsonWriter.nullValue();
        }
        for (;;)
        {
          return;
          paramAnonymousJsonWriter.value(paramAnonymousBoolean.booleanValue());
        }
      }
    };
    BOOLEAN_AS_STRING = new TypeAdapter()
    {
      public Boolean read(JsonReader paramAnonymousJsonReader)
        throws IOException
      {
        if (paramAnonymousJsonReader.peek() == JsonToken.NULL) {
          paramAnonymousJsonReader.nextNull();
        }
        for (paramAnonymousJsonReader = null;; paramAnonymousJsonReader = Boolean.valueOf(paramAnonymousJsonReader.nextString())) {
          return paramAnonymousJsonReader;
        }
      }
      
      public void write(JsonWriter paramAnonymousJsonWriter, Boolean paramAnonymousBoolean)
        throws IOException
      {
        if (paramAnonymousBoolean == null) {}
        for (paramAnonymousBoolean = "null";; paramAnonymousBoolean = paramAnonymousBoolean.toString())
        {
          paramAnonymousJsonWriter.value(paramAnonymousBoolean);
          return;
        }
      }
    };
    BOOLEAN_FACTORY = newFactory(Boolean.TYPE, Boolean.class, BOOLEAN);
    BYTE = new TypeAdapter()
    {
      public Number read(JsonReader paramAnonymousJsonReader)
        throws IOException
      {
        if (paramAnonymousJsonReader.peek() == JsonToken.NULL)
        {
          paramAnonymousJsonReader.nextNull();
          paramAnonymousJsonReader = null;
        }
        for (;;)
        {
          return paramAnonymousJsonReader;
          try
          {
            byte b = (byte)paramAnonymousJsonReader.nextInt();
            paramAnonymousJsonReader = Byte.valueOf(b);
          }
          catch (NumberFormatException paramAnonymousJsonReader)
          {
            throw new JsonSyntaxException(paramAnonymousJsonReader);
          }
        }
      }
      
      public void write(JsonWriter paramAnonymousJsonWriter, Number paramAnonymousNumber)
        throws IOException
      {
        paramAnonymousJsonWriter.value(paramAnonymousNumber);
      }
    };
    BYTE_FACTORY = newFactory(Byte.TYPE, Byte.class, BYTE);
    SHORT = new TypeAdapter()
    {
      public Number read(JsonReader paramAnonymousJsonReader)
        throws IOException
      {
        if (paramAnonymousJsonReader.peek() == JsonToken.NULL)
        {
          paramAnonymousJsonReader.nextNull();
          paramAnonymousJsonReader = null;
        }
        for (;;)
        {
          return paramAnonymousJsonReader;
          try
          {
            short s = (short)paramAnonymousJsonReader.nextInt();
            paramAnonymousJsonReader = Short.valueOf(s);
          }
          catch (NumberFormatException paramAnonymousJsonReader)
          {
            throw new JsonSyntaxException(paramAnonymousJsonReader);
          }
        }
      }
      
      public void write(JsonWriter paramAnonymousJsonWriter, Number paramAnonymousNumber)
        throws IOException
      {
        paramAnonymousJsonWriter.value(paramAnonymousNumber);
      }
    };
    SHORT_FACTORY = newFactory(Short.TYPE, Short.class, SHORT);
    INTEGER = new TypeAdapter()
    {
      public Number read(JsonReader paramAnonymousJsonReader)
        throws IOException
      {
        if (paramAnonymousJsonReader.peek() == JsonToken.NULL)
        {
          paramAnonymousJsonReader.nextNull();
          paramAnonymousJsonReader = null;
        }
        for (;;)
        {
          return paramAnonymousJsonReader;
          try
          {
            int i = paramAnonymousJsonReader.nextInt();
            paramAnonymousJsonReader = Integer.valueOf(i);
          }
          catch (NumberFormatException paramAnonymousJsonReader)
          {
            throw new JsonSyntaxException(paramAnonymousJsonReader);
          }
        }
      }
      
      public void write(JsonWriter paramAnonymousJsonWriter, Number paramAnonymousNumber)
        throws IOException
      {
        paramAnonymousJsonWriter.value(paramAnonymousNumber);
      }
    };
    INTEGER_FACTORY = newFactory(Integer.TYPE, Integer.class, INTEGER);
    LONG = new TypeAdapter()
    {
      public Number read(JsonReader paramAnonymousJsonReader)
        throws IOException
      {
        if (paramAnonymousJsonReader.peek() == JsonToken.NULL)
        {
          paramAnonymousJsonReader.nextNull();
          paramAnonymousJsonReader = null;
        }
        for (;;)
        {
          return paramAnonymousJsonReader;
          try
          {
            long l = paramAnonymousJsonReader.nextLong();
            paramAnonymousJsonReader = Long.valueOf(l);
          }
          catch (NumberFormatException paramAnonymousJsonReader)
          {
            throw new JsonSyntaxException(paramAnonymousJsonReader);
          }
        }
      }
      
      public void write(JsonWriter paramAnonymousJsonWriter, Number paramAnonymousNumber)
        throws IOException
      {
        paramAnonymousJsonWriter.value(paramAnonymousNumber);
      }
    };
    FLOAT = new TypeAdapter()
    {
      public Number read(JsonReader paramAnonymousJsonReader)
        throws IOException
      {
        if (paramAnonymousJsonReader.peek() == JsonToken.NULL) {
          paramAnonymousJsonReader.nextNull();
        }
        for (paramAnonymousJsonReader = null;; paramAnonymousJsonReader = Float.valueOf((float)paramAnonymousJsonReader.nextDouble())) {
          return paramAnonymousJsonReader;
        }
      }
      
      public void write(JsonWriter paramAnonymousJsonWriter, Number paramAnonymousNumber)
        throws IOException
      {
        paramAnonymousJsonWriter.value(paramAnonymousNumber);
      }
    };
    DOUBLE = new TypeAdapter()
    {
      public Number read(JsonReader paramAnonymousJsonReader)
        throws IOException
      {
        if (paramAnonymousJsonReader.peek() == JsonToken.NULL) {
          paramAnonymousJsonReader.nextNull();
        }
        for (paramAnonymousJsonReader = null;; paramAnonymousJsonReader = Double.valueOf(paramAnonymousJsonReader.nextDouble())) {
          return paramAnonymousJsonReader;
        }
      }
      
      public void write(JsonWriter paramAnonymousJsonWriter, Number paramAnonymousNumber)
        throws IOException
      {
        paramAnonymousJsonWriter.value(paramAnonymousNumber);
      }
    };
    NUMBER = new TypeAdapter()
    {
      public Number read(JsonReader paramAnonymousJsonReader)
        throws IOException
      {
        JsonToken localJsonToken = paramAnonymousJsonReader.peek();
        switch (TypeAdapters.32.$SwitchMap$com$google$gson$stream$JsonToken[localJsonToken.ordinal()])
        {
        case 2: 
        case 3: 
        default: 
          throw new JsonSyntaxException("Expecting number, got: " + localJsonToken);
        case 4: 
          paramAnonymousJsonReader.nextNull();
        }
        for (paramAnonymousJsonReader = null;; paramAnonymousJsonReader = new LazilyParsedNumber(paramAnonymousJsonReader.nextString())) {
          return paramAnonymousJsonReader;
        }
      }
      
      public void write(JsonWriter paramAnonymousJsonWriter, Number paramAnonymousNumber)
        throws IOException
      {
        paramAnonymousJsonWriter.value(paramAnonymousNumber);
      }
    };
    NUMBER_FACTORY = newFactory(Number.class, NUMBER);
    CHARACTER = new TypeAdapter()
    {
      public Character read(JsonReader paramAnonymousJsonReader)
        throws IOException
      {
        if (paramAnonymousJsonReader.peek() == JsonToken.NULL) {
          paramAnonymousJsonReader.nextNull();
        }
        for (paramAnonymousJsonReader = null;; paramAnonymousJsonReader = Character.valueOf(paramAnonymousJsonReader.charAt(0)))
        {
          return paramAnonymousJsonReader;
          paramAnonymousJsonReader = paramAnonymousJsonReader.nextString();
          if (paramAnonymousJsonReader.length() != 1) {
            throw new JsonSyntaxException("Expecting character, got: " + paramAnonymousJsonReader);
          }
        }
      }
      
      public void write(JsonWriter paramAnonymousJsonWriter, Character paramAnonymousCharacter)
        throws IOException
      {
        if (paramAnonymousCharacter == null) {}
        for (paramAnonymousCharacter = null;; paramAnonymousCharacter = String.valueOf(paramAnonymousCharacter))
        {
          paramAnonymousJsonWriter.value(paramAnonymousCharacter);
          return;
        }
      }
    };
    CHARACTER_FACTORY = newFactory(Character.TYPE, Character.class, CHARACTER);
    STRING = new TypeAdapter()
    {
      public String read(JsonReader paramAnonymousJsonReader)
        throws IOException
      {
        JsonToken localJsonToken = paramAnonymousJsonReader.peek();
        if (localJsonToken == JsonToken.NULL)
        {
          paramAnonymousJsonReader.nextNull();
          paramAnonymousJsonReader = null;
        }
        for (;;)
        {
          return paramAnonymousJsonReader;
          if (localJsonToken == JsonToken.BOOLEAN) {
            paramAnonymousJsonReader = Boolean.toString(paramAnonymousJsonReader.nextBoolean());
          } else {
            paramAnonymousJsonReader = paramAnonymousJsonReader.nextString();
          }
        }
      }
      
      public void write(JsonWriter paramAnonymousJsonWriter, String paramAnonymousString)
        throws IOException
      {
        paramAnonymousJsonWriter.value(paramAnonymousString);
      }
    };
    BIG_DECIMAL = new TypeAdapter()
    {
      public BigDecimal read(JsonReader paramAnonymousJsonReader)
        throws IOException
      {
        if (paramAnonymousJsonReader.peek() == JsonToken.NULL)
        {
          paramAnonymousJsonReader.nextNull();
          paramAnonymousJsonReader = null;
        }
        for (;;)
        {
          return paramAnonymousJsonReader;
          try
          {
            paramAnonymousJsonReader = new BigDecimal(paramAnonymousJsonReader.nextString());
          }
          catch (NumberFormatException paramAnonymousJsonReader)
          {
            throw new JsonSyntaxException(paramAnonymousJsonReader);
          }
        }
      }
      
      public void write(JsonWriter paramAnonymousJsonWriter, BigDecimal paramAnonymousBigDecimal)
        throws IOException
      {
        paramAnonymousJsonWriter.value(paramAnonymousBigDecimal);
      }
    };
    BIG_INTEGER = new TypeAdapter()
    {
      public BigInteger read(JsonReader paramAnonymousJsonReader)
        throws IOException
      {
        if (paramAnonymousJsonReader.peek() == JsonToken.NULL)
        {
          paramAnonymousJsonReader.nextNull();
          paramAnonymousJsonReader = null;
        }
        for (;;)
        {
          return paramAnonymousJsonReader;
          try
          {
            paramAnonymousJsonReader = new BigInteger(paramAnonymousJsonReader.nextString());
          }
          catch (NumberFormatException paramAnonymousJsonReader)
          {
            throw new JsonSyntaxException(paramAnonymousJsonReader);
          }
        }
      }
      
      public void write(JsonWriter paramAnonymousJsonWriter, BigInteger paramAnonymousBigInteger)
        throws IOException
      {
        paramAnonymousJsonWriter.value(paramAnonymousBigInteger);
      }
    };
    STRING_FACTORY = newFactory(String.class, STRING);
    STRING_BUILDER = new TypeAdapter()
    {
      public StringBuilder read(JsonReader paramAnonymousJsonReader)
        throws IOException
      {
        if (paramAnonymousJsonReader.peek() == JsonToken.NULL) {
          paramAnonymousJsonReader.nextNull();
        }
        for (paramAnonymousJsonReader = null;; paramAnonymousJsonReader = new StringBuilder(paramAnonymousJsonReader.nextString())) {
          return paramAnonymousJsonReader;
        }
      }
      
      public void write(JsonWriter paramAnonymousJsonWriter, StringBuilder paramAnonymousStringBuilder)
        throws IOException
      {
        if (paramAnonymousStringBuilder == null) {}
        for (paramAnonymousStringBuilder = null;; paramAnonymousStringBuilder = paramAnonymousStringBuilder.toString())
        {
          paramAnonymousJsonWriter.value(paramAnonymousStringBuilder);
          return;
        }
      }
    };
    STRING_BUILDER_FACTORY = newFactory(StringBuilder.class, STRING_BUILDER);
    STRING_BUFFER = new TypeAdapter()
    {
      public StringBuffer read(JsonReader paramAnonymousJsonReader)
        throws IOException
      {
        if (paramAnonymousJsonReader.peek() == JsonToken.NULL) {
          paramAnonymousJsonReader.nextNull();
        }
        for (paramAnonymousJsonReader = null;; paramAnonymousJsonReader = new StringBuffer(paramAnonymousJsonReader.nextString())) {
          return paramAnonymousJsonReader;
        }
      }
      
      public void write(JsonWriter paramAnonymousJsonWriter, StringBuffer paramAnonymousStringBuffer)
        throws IOException
      {
        if (paramAnonymousStringBuffer == null) {}
        for (paramAnonymousStringBuffer = null;; paramAnonymousStringBuffer = paramAnonymousStringBuffer.toString())
        {
          paramAnonymousJsonWriter.value(paramAnonymousStringBuffer);
          return;
        }
      }
    };
    STRING_BUFFER_FACTORY = newFactory(StringBuffer.class, STRING_BUFFER);
    URL = new TypeAdapter()
    {
      public URL read(JsonReader paramAnonymousJsonReader)
        throws IOException
      {
        Object localObject = null;
        if (paramAnonymousJsonReader.peek() == JsonToken.NULL)
        {
          paramAnonymousJsonReader.nextNull();
          paramAnonymousJsonReader = (JsonReader)localObject;
        }
        for (;;)
        {
          return paramAnonymousJsonReader;
          String str = paramAnonymousJsonReader.nextString();
          paramAnonymousJsonReader = (JsonReader)localObject;
          if (!"null".equals(str)) {
            paramAnonymousJsonReader = new URL(str);
          }
        }
      }
      
      public void write(JsonWriter paramAnonymousJsonWriter, URL paramAnonymousURL)
        throws IOException
      {
        if (paramAnonymousURL == null) {}
        for (paramAnonymousURL = null;; paramAnonymousURL = paramAnonymousURL.toExternalForm())
        {
          paramAnonymousJsonWriter.value(paramAnonymousURL);
          return;
        }
      }
    };
    URL_FACTORY = newFactory(URL.class, URL);
    URI = new TypeAdapter()
    {
      public URI read(JsonReader paramAnonymousJsonReader)
        throws IOException
      {
        Object localObject = null;
        if (paramAnonymousJsonReader.peek() == JsonToken.NULL)
        {
          paramAnonymousJsonReader.nextNull();
          paramAnonymousJsonReader = (JsonReader)localObject;
        }
        for (;;)
        {
          return paramAnonymousJsonReader;
          try
          {
            String str = paramAnonymousJsonReader.nextString();
            paramAnonymousJsonReader = (JsonReader)localObject;
            if ("null".equals(str)) {
              continue;
            }
            paramAnonymousJsonReader = new URI(str);
          }
          catch (URISyntaxException paramAnonymousJsonReader)
          {
            throw new JsonIOException(paramAnonymousJsonReader);
          }
        }
      }
      
      public void write(JsonWriter paramAnonymousJsonWriter, URI paramAnonymousURI)
        throws IOException
      {
        if (paramAnonymousURI == null) {}
        for (paramAnonymousURI = null;; paramAnonymousURI = paramAnonymousURI.toASCIIString())
        {
          paramAnonymousJsonWriter.value(paramAnonymousURI);
          return;
        }
      }
    };
    URI_FACTORY = newFactory(URI.class, URI);
    INET_ADDRESS = new TypeAdapter()
    {
      public InetAddress read(JsonReader paramAnonymousJsonReader)
        throws IOException
      {
        if (paramAnonymousJsonReader.peek() == JsonToken.NULL) {
          paramAnonymousJsonReader.nextNull();
        }
        for (paramAnonymousJsonReader = null;; paramAnonymousJsonReader = InetAddress.getByName(paramAnonymousJsonReader.nextString())) {
          return paramAnonymousJsonReader;
        }
      }
      
      public void write(JsonWriter paramAnonymousJsonWriter, InetAddress paramAnonymousInetAddress)
        throws IOException
      {
        if (paramAnonymousInetAddress == null) {}
        for (paramAnonymousInetAddress = null;; paramAnonymousInetAddress = paramAnonymousInetAddress.getHostAddress())
        {
          paramAnonymousJsonWriter.value(paramAnonymousInetAddress);
          return;
        }
      }
    };
    INET_ADDRESS_FACTORY = newTypeHierarchyFactory(InetAddress.class, INET_ADDRESS);
    UUID = new TypeAdapter()
    {
      public UUID read(JsonReader paramAnonymousJsonReader)
        throws IOException
      {
        if (paramAnonymousJsonReader.peek() == JsonToken.NULL) {
          paramAnonymousJsonReader.nextNull();
        }
        for (paramAnonymousJsonReader = null;; paramAnonymousJsonReader = UUID.fromString(paramAnonymousJsonReader.nextString())) {
          return paramAnonymousJsonReader;
        }
      }
      
      public void write(JsonWriter paramAnonymousJsonWriter, UUID paramAnonymousUUID)
        throws IOException
      {
        if (paramAnonymousUUID == null) {}
        for (paramAnonymousUUID = null;; paramAnonymousUUID = paramAnonymousUUID.toString())
        {
          paramAnonymousJsonWriter.value(paramAnonymousUUID);
          return;
        }
      }
    };
    UUID_FACTORY = newFactory(UUID.class, UUID);
    TIMESTAMP_FACTORY = new TypeAdapterFactory()
    {
      public <T> TypeAdapter<T> create(Gson paramAnonymousGson, TypeToken<T> paramAnonymousTypeToken)
      {
        if (paramAnonymousTypeToken.getRawType() != Timestamp.class) {}
        for (paramAnonymousGson = null;; paramAnonymousGson = new TypeAdapter()
            {
              public Timestamp read(JsonReader paramAnonymous2JsonReader)
                throws IOException
              {
                paramAnonymous2JsonReader = (Date)this.val$dateTypeAdapter.read(paramAnonymous2JsonReader);
                if (paramAnonymous2JsonReader != null) {}
                for (paramAnonymous2JsonReader = new Timestamp(paramAnonymous2JsonReader.getTime());; paramAnonymous2JsonReader = null) {
                  return paramAnonymous2JsonReader;
                }
              }
              
              public void write(JsonWriter paramAnonymous2JsonWriter, Timestamp paramAnonymous2Timestamp)
                throws IOException
              {
                this.val$dateTypeAdapter.write(paramAnonymous2JsonWriter, paramAnonymous2Timestamp);
              }
            }) {
          return paramAnonymousGson;
        }
      }
    };
    CALENDAR = new TypeAdapter()
    {
      private static final String DAY_OF_MONTH = "dayOfMonth";
      private static final String HOUR_OF_DAY = "hourOfDay";
      private static final String MINUTE = "minute";
      private static final String MONTH = "month";
      private static final String SECOND = "second";
      private static final String YEAR = "year";
      
      public Calendar read(JsonReader paramAnonymousJsonReader)
        throws IOException
      {
        if (paramAnonymousJsonReader.peek() == JsonToken.NULL) {
          paramAnonymousJsonReader.nextNull();
        }
        int i2;
        int i1;
        int n;
        int m;
        int k;
        int j;
        for (paramAnonymousJsonReader = null;; paramAnonymousJsonReader = new GregorianCalendar(i2, i1, n, m, k, j))
        {
          return paramAnonymousJsonReader;
          paramAnonymousJsonReader.beginObject();
          i2 = 0;
          i1 = 0;
          n = 0;
          m = 0;
          k = 0;
          j = 0;
          while (paramAnonymousJsonReader.peek() != JsonToken.END_OBJECT)
          {
            String str = paramAnonymousJsonReader.nextName();
            int i = paramAnonymousJsonReader.nextInt();
            if ("year".equals(str)) {
              i2 = i;
            } else if ("month".equals(str)) {
              i1 = i;
            } else if ("dayOfMonth".equals(str)) {
              n = i;
            } else if ("hourOfDay".equals(str)) {
              m = i;
            } else if ("minute".equals(str)) {
              k = i;
            } else if ("second".equals(str)) {
              j = i;
            }
          }
          paramAnonymousJsonReader.endObject();
        }
      }
      
      public void write(JsonWriter paramAnonymousJsonWriter, Calendar paramAnonymousCalendar)
        throws IOException
      {
        if (paramAnonymousCalendar == null) {
          paramAnonymousJsonWriter.nullValue();
        }
        for (;;)
        {
          return;
          paramAnonymousJsonWriter.beginObject();
          paramAnonymousJsonWriter.name("year");
          paramAnonymousJsonWriter.value(paramAnonymousCalendar.get(1));
          paramAnonymousJsonWriter.name("month");
          paramAnonymousJsonWriter.value(paramAnonymousCalendar.get(2));
          paramAnonymousJsonWriter.name("dayOfMonth");
          paramAnonymousJsonWriter.value(paramAnonymousCalendar.get(5));
          paramAnonymousJsonWriter.name("hourOfDay");
          paramAnonymousJsonWriter.value(paramAnonymousCalendar.get(11));
          paramAnonymousJsonWriter.name("minute");
          paramAnonymousJsonWriter.value(paramAnonymousCalendar.get(12));
          paramAnonymousJsonWriter.name("second");
          paramAnonymousJsonWriter.value(paramAnonymousCalendar.get(13));
          paramAnonymousJsonWriter.endObject();
        }
      }
    };
    CALENDAR_FACTORY = newFactoryForMultipleTypes(Calendar.class, GregorianCalendar.class, CALENDAR);
    LOCALE = new TypeAdapter()
    {
      public Locale read(JsonReader paramAnonymousJsonReader)
        throws IOException
      {
        if (paramAnonymousJsonReader.peek() == JsonToken.NULL)
        {
          paramAnonymousJsonReader.nextNull();
          paramAnonymousJsonReader = null;
        }
        for (;;)
        {
          return paramAnonymousJsonReader;
          StringTokenizer localStringTokenizer = new StringTokenizer(paramAnonymousJsonReader.nextString(), "_");
          paramAnonymousJsonReader = null;
          String str1 = null;
          String str2 = null;
          if (localStringTokenizer.hasMoreElements()) {
            paramAnonymousJsonReader = localStringTokenizer.nextToken();
          }
          if (localStringTokenizer.hasMoreElements()) {
            str1 = localStringTokenizer.nextToken();
          }
          if (localStringTokenizer.hasMoreElements()) {
            str2 = localStringTokenizer.nextToken();
          }
          if ((str1 == null) && (str2 == null)) {
            paramAnonymousJsonReader = new Locale(paramAnonymousJsonReader);
          } else if (str2 == null) {
            paramAnonymousJsonReader = new Locale(paramAnonymousJsonReader, str1);
          } else {
            paramAnonymousJsonReader = new Locale(paramAnonymousJsonReader, str1, str2);
          }
        }
      }
      
      public void write(JsonWriter paramAnonymousJsonWriter, Locale paramAnonymousLocale)
        throws IOException
      {
        if (paramAnonymousLocale == null) {}
        for (paramAnonymousLocale = null;; paramAnonymousLocale = paramAnonymousLocale.toString())
        {
          paramAnonymousJsonWriter.value(paramAnonymousLocale);
          return;
        }
      }
    };
    LOCALE_FACTORY = newFactory(Locale.class, LOCALE);
    JSON_ELEMENT = new TypeAdapter()
    {
      public JsonElement read(JsonReader paramAnonymousJsonReader)
        throws IOException
      {
        switch (TypeAdapters.32.$SwitchMap$com$google$gson$stream$JsonToken[paramAnonymousJsonReader.peek().ordinal()])
        {
        default: 
          throw new IllegalArgumentException();
        case 3: 
          paramAnonymousJsonReader = new JsonPrimitive(paramAnonymousJsonReader.nextString());
        }
        for (;;)
        {
          return paramAnonymousJsonReader;
          paramAnonymousJsonReader = new JsonPrimitive(new LazilyParsedNumber(paramAnonymousJsonReader.nextString()));
          continue;
          paramAnonymousJsonReader = new JsonPrimitive(Boolean.valueOf(paramAnonymousJsonReader.nextBoolean()));
          continue;
          paramAnonymousJsonReader.nextNull();
          paramAnonymousJsonReader = JsonNull.INSTANCE;
          continue;
          Object localObject = new JsonArray();
          paramAnonymousJsonReader.beginArray();
          while (paramAnonymousJsonReader.hasNext()) {
            ((JsonArray)localObject).add(read(paramAnonymousJsonReader));
          }
          paramAnonymousJsonReader.endArray();
          paramAnonymousJsonReader = (JsonReader)localObject;
          continue;
          localObject = new JsonObject();
          paramAnonymousJsonReader.beginObject();
          while (paramAnonymousJsonReader.hasNext()) {
            ((JsonObject)localObject).add(paramAnonymousJsonReader.nextName(), read(paramAnonymousJsonReader));
          }
          paramAnonymousJsonReader.endObject();
          paramAnonymousJsonReader = (JsonReader)localObject;
        }
      }
      
      public void write(JsonWriter paramAnonymousJsonWriter, JsonElement paramAnonymousJsonElement)
        throws IOException
      {
        if ((paramAnonymousJsonElement == null) || (paramAnonymousJsonElement.isJsonNull())) {
          paramAnonymousJsonWriter.nullValue();
        }
        for (;;)
        {
          return;
          if (paramAnonymousJsonElement.isJsonPrimitive())
          {
            paramAnonymousJsonElement = paramAnonymousJsonElement.getAsJsonPrimitive();
            if (paramAnonymousJsonElement.isNumber()) {
              paramAnonymousJsonWriter.value(paramAnonymousJsonElement.getAsNumber());
            } else if (paramAnonymousJsonElement.isBoolean()) {
              paramAnonymousJsonWriter.value(paramAnonymousJsonElement.getAsBoolean());
            } else {
              paramAnonymousJsonWriter.value(paramAnonymousJsonElement.getAsString());
            }
          }
          else if (paramAnonymousJsonElement.isJsonArray())
          {
            paramAnonymousJsonWriter.beginArray();
            paramAnonymousJsonElement = paramAnonymousJsonElement.getAsJsonArray().iterator();
            while (paramAnonymousJsonElement.hasNext()) {
              write(paramAnonymousJsonWriter, (JsonElement)paramAnonymousJsonElement.next());
            }
            paramAnonymousJsonWriter.endArray();
          }
          else
          {
            if (!paramAnonymousJsonElement.isJsonObject()) {
              break;
            }
            paramAnonymousJsonWriter.beginObject();
            Iterator localIterator = paramAnonymousJsonElement.getAsJsonObject().entrySet().iterator();
            while (localIterator.hasNext())
            {
              paramAnonymousJsonElement = (Map.Entry)localIterator.next();
              paramAnonymousJsonWriter.name((String)paramAnonymousJsonElement.getKey());
              write(paramAnonymousJsonWriter, (JsonElement)paramAnonymousJsonElement.getValue());
            }
            paramAnonymousJsonWriter.endObject();
          }
        }
        throw new IllegalArgumentException("Couldn't write " + paramAnonymousJsonElement.getClass());
      }
    };
    JSON_ELEMENT_FACTORY = newTypeHierarchyFactory(JsonElement.class, JSON_ELEMENT);
  }
  
  public static <TT> TypeAdapterFactory newFactory(TypeToken<TT> paramTypeToken, final TypeAdapter<TT> paramTypeAdapter)
  {
    new TypeAdapterFactory()
    {
      public <T> TypeAdapter<T> create(Gson paramAnonymousGson, TypeToken<T> paramAnonymousTypeToken)
      {
        if (paramAnonymousTypeToken.equals(this.val$type)) {}
        for (paramAnonymousGson = paramTypeAdapter;; paramAnonymousGson = null) {
          return paramAnonymousGson;
        }
      }
    };
  }
  
  public static <TT> TypeAdapterFactory newFactory(Class<TT> paramClass, final TypeAdapter<TT> paramTypeAdapter)
  {
    new TypeAdapterFactory()
    {
      public <T> TypeAdapter<T> create(Gson paramAnonymousGson, TypeToken<T> paramAnonymousTypeToken)
      {
        if (paramAnonymousTypeToken.getRawType() == this.val$type) {}
        for (paramAnonymousGson = paramTypeAdapter;; paramAnonymousGson = null) {
          return paramAnonymousGson;
        }
      }
      
      public String toString()
      {
        return "Factory[type=" + this.val$type.getName() + ",adapter=" + paramTypeAdapter + "]";
      }
    };
  }
  
  public static <TT> TypeAdapterFactory newFactory(Class<TT> paramClass1, final Class<TT> paramClass2, final TypeAdapter<? super TT> paramTypeAdapter)
  {
    new TypeAdapterFactory()
    {
      public <T> TypeAdapter<T> create(Gson paramAnonymousGson, TypeToken<T> paramAnonymousTypeToken)
      {
        paramAnonymousGson = paramAnonymousTypeToken.getRawType();
        if ((paramAnonymousGson == this.val$unboxed) || (paramAnonymousGson == paramClass2)) {}
        for (paramAnonymousGson = paramTypeAdapter;; paramAnonymousGson = null) {
          return paramAnonymousGson;
        }
      }
      
      public String toString()
      {
        return "Factory[type=" + paramClass2.getName() + "+" + this.val$unboxed.getName() + ",adapter=" + paramTypeAdapter + "]";
      }
    };
  }
  
  public static <TT> TypeAdapterFactory newFactoryForMultipleTypes(Class<TT> paramClass, final Class<? extends TT> paramClass1, final TypeAdapter<? super TT> paramTypeAdapter)
  {
    new TypeAdapterFactory()
    {
      public <T> TypeAdapter<T> create(Gson paramAnonymousGson, TypeToken<T> paramAnonymousTypeToken)
      {
        paramAnonymousGson = paramAnonymousTypeToken.getRawType();
        if ((paramAnonymousGson == this.val$base) || (paramAnonymousGson == paramClass1)) {}
        for (paramAnonymousGson = paramTypeAdapter;; paramAnonymousGson = null) {
          return paramAnonymousGson;
        }
      }
      
      public String toString()
      {
        return "Factory[type=" + this.val$base.getName() + "+" + paramClass1.getName() + ",adapter=" + paramTypeAdapter + "]";
      }
    };
  }
  
  public static <TT> TypeAdapterFactory newTypeHierarchyFactory(Class<TT> paramClass, final TypeAdapter<TT> paramTypeAdapter)
  {
    new TypeAdapterFactory()
    {
      public <T> TypeAdapter<T> create(Gson paramAnonymousGson, TypeToken<T> paramAnonymousTypeToken)
      {
        if (this.val$clazz.isAssignableFrom(paramAnonymousTypeToken.getRawType())) {}
        for (paramAnonymousGson = paramTypeAdapter;; paramAnonymousGson = null) {
          return paramAnonymousGson;
        }
      }
      
      public String toString()
      {
        return "Factory[typeHierarchy=" + this.val$clazz.getName() + ",adapter=" + paramTypeAdapter + "]";
      }
    };
  }
  
  private static final class EnumTypeAdapter<T extends Enum<T>>
    extends TypeAdapter<T>
  {
    private final Map<T, String> constantToName = new HashMap();
    private final Map<String, T> nameToConstant = new HashMap();
    
    public EnumTypeAdapter(Class<T> paramClass)
    {
      try
      {
        for (Enum localEnum : (Enum[])paramClass.getEnumConstants())
        {
          String str = localEnum.name();
          SerializedName localSerializedName = (SerializedName)paramClass.getField(str).getAnnotation(SerializedName.class);
          if (localSerializedName != null) {
            str = localSerializedName.value();
          }
          this.nameToConstant.put(str, localEnum);
          this.constantToName.put(localEnum, str);
        }
        return;
      }
      catch (NoSuchFieldException paramClass)
      {
        throw new AssertionError();
      }
    }
    
    public T read(JsonReader paramJsonReader)
      throws IOException
    {
      if (paramJsonReader.peek() == JsonToken.NULL) {
        paramJsonReader.nextNull();
      }
      for (paramJsonReader = null;; paramJsonReader = (Enum)this.nameToConstant.get(paramJsonReader.nextString())) {
        return paramJsonReader;
      }
    }
    
    public void write(JsonWriter paramJsonWriter, T paramT)
      throws IOException
    {
      if (paramT == null) {}
      for (paramT = null;; paramT = (String)this.constantToName.get(paramT))
      {
        paramJsonWriter.value(paramT);
        return;
      }
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\gson\internal\bind\TypeAdapters.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */