package com.amazon.cptplugins.validation;

public final class Assert
{
  public static <T> void notNull(T paramT, String paramString)
  {
    if (paramT == null) {
      throw new IllegalStateException("Unexpected null object: " + paramString);
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\amazon\cptplugins\validation\Assert.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */