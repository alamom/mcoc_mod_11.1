package com.google.gson;

import com.google.gson.internal.Streams;
import com.google.gson.stream.JsonReader;
import java.io.EOFException;
import java.io.Reader;
import java.io.StringReader;
import java.util.Iterator;
import java.util.NoSuchElementException;

public final class JsonStreamParser
  implements Iterator<JsonElement>
{
  private final Object lock;
  private final JsonReader parser;
  
  public JsonStreamParser(Reader paramReader)
  {
    this.parser = new JsonReader(paramReader);
    this.parser.setLenient(true);
    this.lock = new Object();
  }
  
  public JsonStreamParser(String paramString)
  {
    this(new StringReader(paramString));
  }
  
  /* Error */
  public boolean hasNext()
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 28	com/google/gson/JsonStreamParser:lock	Ljava/lang/Object;
    //   4: astore_2
    //   5: aload_2
    //   6: monitorenter
    //   7: aload_0
    //   8: getfield 22	com/google/gson/JsonStreamParser:parser	Lcom/google/gson/stream/JsonReader;
    //   11: invokevirtual 45	com/google/gson/stream/JsonReader:peek	()Lcom/google/gson/stream/JsonToken;
    //   14: astore_3
    //   15: getstatic 51	com/google/gson/stream/JsonToken:END_DOCUMENT	Lcom/google/gson/stream/JsonToken;
    //   18: astore 4
    //   20: aload_3
    //   21: aload 4
    //   23: if_acmpeq +9 -> 32
    //   26: iconst_1
    //   27: istore_1
    //   28: aload_2
    //   29: monitorexit
    //   30: iload_1
    //   31: ireturn
    //   32: iconst_0
    //   33: istore_1
    //   34: goto -6 -> 28
    //   37: astore_3
    //   38: new 53	com/google/gson/JsonSyntaxException
    //   41: astore 4
    //   43: aload 4
    //   45: aload_3
    //   46: invokespecial 56	com/google/gson/JsonSyntaxException:<init>	(Ljava/lang/Throwable;)V
    //   49: aload 4
    //   51: athrow
    //   52: astore_3
    //   53: aload_2
    //   54: monitorexit
    //   55: aload_3
    //   56: athrow
    //   57: astore_3
    //   58: new 58	com/google/gson/JsonIOException
    //   61: astore 4
    //   63: aload 4
    //   65: aload_3
    //   66: invokespecial 59	com/google/gson/JsonIOException:<init>	(Ljava/lang/Throwable;)V
    //   69: aload 4
    //   71: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	72	0	this	JsonStreamParser
    //   27	7	1	bool	boolean
    //   14	7	3	localJsonToken	com.google.gson.stream.JsonToken
    //   37	9	3	localMalformedJsonException	com.google.gson.stream.MalformedJsonException
    //   52	4	3	localObject2	Object
    //   57	9	3	localIOException	java.io.IOException
    //   18	52	4	localObject3	Object
    // Exception table:
    //   from	to	target	type
    //   7	20	37	com/google/gson/stream/MalformedJsonException
    //   7	20	52	finally
    //   28	30	52	finally
    //   38	52	52	finally
    //   53	55	52	finally
    //   58	72	52	finally
    //   7	20	57	java/io/IOException
  }
  
  public JsonElement next()
    throws JsonParseException
  {
    if (!hasNext()) {
      throw new NoSuchElementException();
    }
    try
    {
      JsonElement localJsonElement = Streams.parse(this.parser);
      return localJsonElement;
    }
    catch (StackOverflowError localStackOverflowError)
    {
      throw new JsonParseException("Failed parsing JSON source to Json", localStackOverflowError);
    }
    catch (OutOfMemoryError localOutOfMemoryError)
    {
      throw new JsonParseException("Failed parsing JSON source to Json", localOutOfMemoryError);
    }
    catch (JsonParseException localJsonParseException)
    {
      Object localObject = localJsonParseException;
      if ((localJsonParseException.getCause() instanceof EOFException)) {
        localObject = new NoSuchElementException();
      }
      throw ((Throwable)localObject);
    }
  }
  
  public void remove()
  {
    throw new UnsupportedOperationException();
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\gson\JsonStreamParser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */