package com.google.android.gms.internal;

import java.security.MessageDigest;

public class ar
  extends ao
{
  private MessageDigest nP;
  
  byte[] a(String[] paramArrayOfString)
  {
    byte[] arrayOfByte = new byte[paramArrayOfString.length];
    for (int i = 0; i < paramArrayOfString.length; i++) {
      arrayOfByte[i] = ((byte)(aq.o(paramArrayOfString[i]) & 0xFF));
    }
    return arrayOfByte;
  }
  
  public byte[] l(String paramString)
  {
    paramString = a(paramString.split(" "));
    this.nP = ba();
    for (;;)
    {
      byte[] arrayOfByte;
      synchronized (this.mw)
      {
        if (this.nP == null)
        {
          paramString = new byte[0];
          return paramString;
        }
        this.nP.reset();
        this.nP.update(paramString);
        arrayOfByte = this.nP.digest();
        i = 4;
        if (arrayOfByte.length > 4)
        {
          paramString = new byte[i];
          System.arraycopy(arrayOfByte, 0, paramString, 0, paramString.length);
        }
      }
      int i = arrayOfByte.length;
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\internal\ar.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */