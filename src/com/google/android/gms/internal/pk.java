package com.google.android.gms.internal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

class pk
{
  private pi<?, ?> awQ;
  private Object awR;
  private List<pp> awS = new ArrayList();
  
  private byte[] toByteArray()
    throws IOException
  {
    byte[] arrayOfByte = new byte[c()];
    a(pg.q(arrayOfByte));
    return arrayOfByte;
  }
  
  void a(pg parampg)
    throws IOException
  {
    if (this.awR != null) {
      this.awQ.a(this.awR, parampg);
    }
    for (;;)
    {
      return;
      Iterator localIterator = this.awS.iterator();
      while (localIterator.hasNext()) {
        ((pp)localIterator.next()).a(parampg);
      }
    }
  }
  
  void a(pp parampp)
  {
    this.awS.add(parampp);
  }
  
  <T> T b(pi<?, T> parampi)
  {
    if (this.awR != null)
    {
      if (this.awQ != parampi) {
        throw new IllegalStateException("Tried to getExtension with a differernt Extension.");
      }
    }
    else
    {
      this.awQ = parampi;
      this.awR = parampi.l(this.awS);
      this.awS = null;
    }
    return (T)this.awR;
  }
  
  int c()
  {
    int j;
    if (this.awR != null)
    {
      j = this.awQ.A(this.awR);
      return j;
    }
    Iterator localIterator = this.awS.iterator();
    for (int i = 0;; i = ((pp)localIterator.next()).c() + i)
    {
      j = i;
      if (!localIterator.hasNext()) {
        break;
      }
    }
  }
  
  public boolean equals(Object paramObject)
  {
    boolean bool2 = false;
    boolean bool1;
    if (paramObject == this) {
      bool1 = true;
    }
    for (;;)
    {
      return bool1;
      bool1 = bool2;
      if (!(paramObject instanceof pk)) {
        continue;
      }
      paramObject = (pk)paramObject;
      if ((this.awR != null) && (((pk)paramObject).awR != null))
      {
        bool1 = bool2;
        if (this.awQ != ((pk)paramObject).awQ) {
          continue;
        }
        if (!this.awQ.awK.isArray())
        {
          bool1 = this.awR.equals(((pk)paramObject).awR);
          continue;
        }
        if ((this.awR instanceof byte[]))
        {
          bool1 = Arrays.equals((byte[])this.awR, (byte[])((pk)paramObject).awR);
          continue;
        }
        if ((this.awR instanceof int[]))
        {
          bool1 = Arrays.equals((int[])this.awR, (int[])((pk)paramObject).awR);
          continue;
        }
        if ((this.awR instanceof long[]))
        {
          bool1 = Arrays.equals((long[])this.awR, (long[])((pk)paramObject).awR);
          continue;
        }
        if ((this.awR instanceof float[]))
        {
          bool1 = Arrays.equals((float[])this.awR, (float[])((pk)paramObject).awR);
          continue;
        }
        if ((this.awR instanceof double[]))
        {
          bool1 = Arrays.equals((double[])this.awR, (double[])((pk)paramObject).awR);
          continue;
        }
        if ((this.awR instanceof boolean[]))
        {
          bool1 = Arrays.equals((boolean[])this.awR, (boolean[])((pk)paramObject).awR);
          continue;
        }
        bool1 = Arrays.deepEquals((Object[])this.awR, (Object[])((pk)paramObject).awR);
        continue;
      }
      if ((this.awS != null) && (((pk)paramObject).awS != null))
      {
        bool1 = this.awS.equals(((pk)paramObject).awS);
        continue;
      }
      try
      {
        bool1 = Arrays.equals(toByteArray(), ((pk)paramObject).toByteArray());
      }
      catch (IOException paramObject)
      {
        throw new IllegalStateException((Throwable)paramObject);
      }
    }
  }
  
  public int hashCode()
  {
    try
    {
      int i = Arrays.hashCode(toByteArray());
      return i + 527;
    }
    catch (IOException localIOException)
    {
      throw new IllegalStateException(localIOException);
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\internal\pk.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */