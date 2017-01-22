package com.google.android.gms.internal;

import java.io.IOException;

public abstract interface ol
{
  public static final class a
    extends ph<a>
  {
    public long asr;
    public c.j ass;
    public c.f gs;
    
    public a()
    {
      pL();
    }
    
    public static a l(byte[] paramArrayOfByte)
      throws pm
    {
      return (a)pn.a(new a(), paramArrayOfByte);
    }
    
    public void a(pg parampg)
      throws IOException
    {
      parampg.b(1, this.asr);
      if (this.gs != null) {
        parampg.a(2, this.gs);
      }
      if (this.ass != null) {
        parampg.a(3, this.ass);
      }
      super.a(parampg);
    }
    
    protected int c()
    {
      int j = super.c() + pg.d(1, this.asr);
      int i = j;
      if (this.gs != null) {
        i = j + pg.c(2, this.gs);
      }
      j = i;
      if (this.ass != null) {
        j = i + pg.c(3, this.ass);
      }
      return j;
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
        if ((paramObject instanceof a))
        {
          paramObject = (a)paramObject;
          bool1 = bool2;
          if (this.asr == ((a)paramObject).asr)
          {
            if (this.gs == null)
            {
              bool1 = bool2;
              if (((a)paramObject).gs != null) {
                continue;
              }
              label55:
              if (this.ass != null) {
                break label99;
              }
              bool1 = bool2;
              if (((a)paramObject).ass != null) {
                continue;
              }
            }
            label99:
            while (this.ass.equals(((a)paramObject).ass))
            {
              bool1 = a((ph)paramObject);
              break;
              if (this.gs.equals(((a)paramObject).gs)) {
                break label55;
              }
              bool1 = bool2;
              break;
            }
            bool1 = bool2;
          }
        }
      }
    }
    
    public int hashCode()
    {
      int j = 0;
      int k = (int)(this.asr ^ this.asr >>> 32);
      int i;
      if (this.gs == null)
      {
        i = 0;
        if (this.ass != null) {
          break label67;
        }
      }
      for (;;)
      {
        return ((i + (k + 527) * 31) * 31 + j) * 31 + qz();
        i = this.gs.hashCode();
        break;
        label67:
        j = this.ass.hashCode();
      }
    }
    
    public a p(pf parampf)
      throws IOException
    {
      for (;;)
      {
        int i = parampf.qi();
        switch (i)
        {
        default: 
          if (a(parampf, i)) {}
          break;
        case 0: 
          return this;
        case 8: 
          this.asr = parampf.qk();
          break;
        case 18: 
          if (this.gs == null) {
            this.gs = new c.f();
          }
          parampf.a(this.gs);
          break;
        case 26: 
          if (this.ass == null) {
            this.ass = new c.j();
          }
          parampf.a(this.ass);
        }
      }
    }
    
    public a pL()
    {
      this.asr = 0L;
      this.gs = null;
      this.ass = null;
      this.awJ = null;
      this.awU = -1;
      return this;
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\internal\ol.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */