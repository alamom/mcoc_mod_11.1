package com.google.gson.stream;

import java.io.IOException;

public final class MalformedJsonException
  extends IOException
{
  private static final long serialVersionUID = 1L;
  
  public MalformedJsonException(String paramString)
  {
    super(paramString);
  }
  
  public MalformedJsonException(String paramString, Throwable paramThrowable)
  {
    super(paramString);
    initCause(paramThrowable);
  }
  
  public MalformedJsonException(Throwable paramThrowable)
  {
    initCause(paramThrowable);
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\gson\stream\MalformedJsonException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */