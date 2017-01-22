package com.kabam.soda;

public class KabamException
  extends Exception
{
  private static final long serialVersionUID = -2182944620869774042L;
  
  public KabamException() {}
  
  public KabamException(String paramString)
  {
    super(paramString);
  }
  
  public KabamException(String paramString, Throwable paramThrowable)
  {
    super(paramString, paramThrowable);
  }
  
  public KabamException(Throwable paramThrowable)
  {
    super(paramThrowable);
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\kabam\soda\KabamException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */