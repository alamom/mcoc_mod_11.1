package com.google.gson.internal;

import java.io.ObjectStreamException;
import java.math.BigDecimal;

public final class LazilyParsedNumber
  extends Number
{
  private final String value;
  
  public LazilyParsedNumber(String paramString)
  {
    this.value = paramString;
  }
  
  private Object writeReplace()
    throws ObjectStreamException
  {
    return new BigDecimal(this.value);
  }
  
  public double doubleValue()
  {
    return Double.parseDouble(this.value);
  }
  
  public float floatValue()
  {
    return Float.parseFloat(this.value);
  }
  
  public int intValue()
  {
    try
    {
      i = Integer.parseInt(this.value);
      return i;
    }
    catch (NumberFormatException localNumberFormatException1)
    {
      for (;;)
      {
        try
        {
          long l = Long.parseLong(this.value);
          i = (int)l;
        }
        catch (NumberFormatException localNumberFormatException2)
        {
          int i = new BigDecimal(this.value).intValue();
        }
      }
    }
  }
  
  public long longValue()
  {
    try
    {
      l = Long.parseLong(this.value);
      return l;
    }
    catch (NumberFormatException localNumberFormatException)
    {
      for (;;)
      {
        long l = new BigDecimal(this.value).longValue();
      }
    }
  }
  
  public String toString()
  {
    return this.value;
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\gson\internal\LazilyParsedNumber.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */