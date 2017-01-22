package com.google.gson.internal.bind;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public final class TimeTypeAdapter
  extends TypeAdapter<Time>
{
  public static final TypeAdapterFactory FACTORY = new TypeAdapterFactory()
  {
    public <T> TypeAdapter<T> create(Gson paramAnonymousGson, TypeToken<T> paramAnonymousTypeToken)
    {
      if (paramAnonymousTypeToken.getRawType() == Time.class) {}
      for (paramAnonymousGson = new TimeTypeAdapter();; paramAnonymousGson = null) {
        return paramAnonymousGson;
      }
    }
  };
  private final DateFormat format = new SimpleDateFormat("hh:mm:ss a");
  
  /* Error */
  public Time read(com.google.gson.stream.JsonReader paramJsonReader)
    throws IOException
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_1
    //   3: invokevirtual 45	com/google/gson/stream/JsonReader:peek	()Lcom/google/gson/stream/JsonToken;
    //   6: getstatic 51	com/google/gson/stream/JsonToken:NULL	Lcom/google/gson/stream/JsonToken;
    //   9: if_acmpne +13 -> 22
    //   12: aload_1
    //   13: invokevirtual 54	com/google/gson/stream/JsonReader:nextNull	()V
    //   16: aconst_null
    //   17: astore_1
    //   18: aload_0
    //   19: monitorexit
    //   20: aload_1
    //   21: areturn
    //   22: new 56	java/sql/Time
    //   25: dup
    //   26: aload_0
    //   27: getfield 29	com/google/gson/internal/bind/TimeTypeAdapter:format	Ljava/text/DateFormat;
    //   30: aload_1
    //   31: invokevirtual 60	com/google/gson/stream/JsonReader:nextString	()Ljava/lang/String;
    //   34: invokevirtual 66	java/text/DateFormat:parse	(Ljava/lang/String;)Ljava/util/Date;
    //   37: invokevirtual 72	java/util/Date:getTime	()J
    //   40: invokespecial 75	java/sql/Time:<init>	(J)V
    //   43: astore_1
    //   44: goto -26 -> 18
    //   47: astore_2
    //   48: new 77	com/google/gson/JsonSyntaxException
    //   51: astore_1
    //   52: aload_1
    //   53: aload_2
    //   54: invokespecial 80	com/google/gson/JsonSyntaxException:<init>	(Ljava/lang/Throwable;)V
    //   57: aload_1
    //   58: athrow
    //   59: astore_1
    //   60: aload_0
    //   61: monitorexit
    //   62: aload_1
    //   63: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	64	0	this	TimeTypeAdapter
    //   0	64	1	paramJsonReader	com.google.gson.stream.JsonReader
    //   47	7	2	localParseException	java.text.ParseException
    // Exception table:
    //   from	to	target	type
    //   22	44	47	java/text/ParseException
    //   2	16	59	finally
    //   22	44	59	finally
    //   48	59	59	finally
  }
  
  public void write(JsonWriter paramJsonWriter, Time paramTime)
    throws IOException
  {
    if (paramTime == null) {}
    for (paramTime = null;; paramTime = this.format.format(paramTime)) {
      try
      {
        paramJsonWriter.value(paramTime);
        return;
      }
      finally {}
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\gson\internal\bind\TimeTypeAdapter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */