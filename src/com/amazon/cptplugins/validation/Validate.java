package com.amazon.cptplugins.validation;

public final class Validate
{
  public static <T> T notNull(T paramT, String paramString)
  {
    if (paramT == null) {
      throw new IllegalArgumentException("Unexpected null value. " + paramString);
    }
    return paramT;
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\amazon\cptplugins\validation\Validate.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */