package com.google.android.gms.internal;

import java.io.IOException;
import java.util.Arrays;

final class pp
{
  final byte[] awV;
  final int tag;
  
  pp(int paramInt, byte[] paramArrayOfByte)
  {
    this.tag = paramInt;
    this.awV = paramArrayOfByte;
  }
  
  void a(pg parampg)
    throws IOException
  {
    parampg.gA(this.tag);
    parampg.t(this.awV);
  }
  
  int c()
  {
    return 0 + pg.gB(this.tag) + this.awV.length;
  }
  
  public boolean equals(Object paramObject)
  {
    boolean bool = true;
    if (paramObject == this) {}
    for (;;)
    {
      return bool;
      if (!(paramObject instanceof pp))
      {
        bool = false;
      }
      else
      {
        paramObject = (pp)paramObject;
        if ((this.tag != ((pp)paramObject).tag) || (!Arrays.equals(this.awV, ((pp)paramObject).awV))) {
          bool = false;
        }
      }
    }
  }
  
  public int hashCode()
  {
    return (this.tag + 527) * 31 + Arrays.hashCode(this.awV);
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\internal\pp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */