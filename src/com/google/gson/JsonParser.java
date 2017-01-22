package com.google.gson;

import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.MalformedJsonException;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

public final class JsonParser
{
  /* Error */
  public JsonElement parse(JsonReader paramJsonReader)
    throws JsonIOException, JsonSyntaxException
  {
    // Byte code:
    //   0: aload_1
    //   1: invokevirtual 25	com/google/gson/stream/JsonReader:isLenient	()Z
    //   4: istore_2
    //   5: aload_1
    //   6: iconst_1
    //   7: invokevirtual 29	com/google/gson/stream/JsonReader:setLenient	(Z)V
    //   10: aload_1
    //   11: invokestatic 33	com/google/gson/internal/Streams:parse	(Lcom/google/gson/stream/JsonReader;)Lcom/google/gson/JsonElement;
    //   14: astore_3
    //   15: aload_1
    //   16: iload_2
    //   17: invokevirtual 29	com/google/gson/stream/JsonReader:setLenient	(Z)V
    //   20: aload_3
    //   21: areturn
    //   22: astore_3
    //   23: new 35	com/google/gson/JsonParseException
    //   26: astore 4
    //   28: new 37	java/lang/StringBuilder
    //   31: astore 5
    //   33: aload 5
    //   35: invokespecial 38	java/lang/StringBuilder:<init>	()V
    //   38: aload 4
    //   40: aload 5
    //   42: ldc 40
    //   44: invokevirtual 44	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   47: aload_1
    //   48: invokevirtual 47	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   51: ldc 49
    //   53: invokevirtual 44	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   56: invokevirtual 53	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   59: aload_3
    //   60: invokespecial 56	com/google/gson/JsonParseException:<init>	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   63: aload 4
    //   65: athrow
    //   66: astore_3
    //   67: aload_1
    //   68: iload_2
    //   69: invokevirtual 29	com/google/gson/stream/JsonReader:setLenient	(Z)V
    //   72: aload_3
    //   73: athrow
    //   74: astore 5
    //   76: new 35	com/google/gson/JsonParseException
    //   79: astore 4
    //   81: new 37	java/lang/StringBuilder
    //   84: astore_3
    //   85: aload_3
    //   86: invokespecial 38	java/lang/StringBuilder:<init>	()V
    //   89: aload 4
    //   91: aload_3
    //   92: ldc 40
    //   94: invokevirtual 44	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   97: aload_1
    //   98: invokevirtual 47	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   101: ldc 49
    //   103: invokevirtual 44	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   106: invokevirtual 53	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   109: aload 5
    //   111: invokespecial 56	com/google/gson/JsonParseException:<init>	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   114: aload 4
    //   116: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	117	0	this	JsonParser
    //   0	117	1	paramJsonReader	JsonReader
    //   4	65	2	bool	boolean
    //   14	7	3	localJsonElement	JsonElement
    //   22	38	3	localStackOverflowError	StackOverflowError
    //   66	7	3	localObject	Object
    //   84	8	3	localStringBuilder1	StringBuilder
    //   26	89	4	localJsonParseException	JsonParseException
    //   31	10	5	localStringBuilder2	StringBuilder
    //   74	36	5	localOutOfMemoryError	OutOfMemoryError
    // Exception table:
    //   from	to	target	type
    //   10	15	22	java/lang/StackOverflowError
    //   10	15	66	finally
    //   23	66	66	finally
    //   76	117	66	finally
    //   10	15	74	java/lang/OutOfMemoryError
  }
  
  public JsonElement parse(Reader paramReader)
    throws JsonIOException, JsonSyntaxException
  {
    try
    {
      JsonReader localJsonReader = new com/google/gson/stream/JsonReader;
      localJsonReader.<init>(paramReader);
      paramReader = parse(localJsonReader);
      if ((!paramReader.isJsonNull()) && (localJsonReader.peek() != JsonToken.END_DOCUMENT))
      {
        paramReader = new com/google/gson/JsonSyntaxException;
        paramReader.<init>("Did not consume the entire document.");
        throw paramReader;
      }
    }
    catch (MalformedJsonException paramReader)
    {
      throw new JsonSyntaxException(paramReader);
    }
    catch (IOException paramReader)
    {
      throw new JsonIOException(paramReader);
    }
    catch (NumberFormatException paramReader)
    {
      throw new JsonSyntaxException(paramReader);
    }
    return paramReader;
  }
  
  public JsonElement parse(String paramString)
    throws JsonSyntaxException
  {
    return parse(new StringReader(paramString));
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\gson\JsonParser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */