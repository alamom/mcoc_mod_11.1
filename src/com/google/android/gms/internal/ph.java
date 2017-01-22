package com.google.android.gms.internal;

import java.io.IOException;

public abstract class ph<M extends ph<M>>
  extends pn
{
  protected pj awJ;
  
  public final <T> T a(pi<M, T> parampi)
  {
    Object localObject = null;
    if (this.awJ == null) {}
    for (;;)
    {
      return (T)localObject;
      pk localpk = this.awJ.gE(pq.gI(parampi.tag));
      if (localpk != null) {
        localObject = localpk.b(parampi);
      }
    }
  }
  
  public void a(pg parampg)
    throws IOException
  {
    if (this.awJ == null) {}
    for (;;)
    {
      return;
      for (int i = 0; i < this.awJ.size(); i++) {
        this.awJ.gF(i).a(parampg);
      }
    }
  }
  
  protected final boolean a(pf parampf, int paramInt)
    throws IOException
  {
    int j = parampf.getPosition();
    boolean bool;
    if (!parampf.gn(paramInt))
    {
      bool = false;
      return bool;
    }
    int i = pq.gI(paramInt);
    pp localpp = new pp(paramInt, parampf.r(j, parampf.getPosition() - j));
    parampf = null;
    if (this.awJ == null) {
      this.awJ = new pj();
    }
    for (;;)
    {
      Object localObject = parampf;
      if (parampf == null)
      {
        localObject = new pk();
        this.awJ.a(i, (pk)localObject);
      }
      ((pk)localObject).a(localpp);
      bool = true;
      break;
      parampf = this.awJ.gE(i);
    }
  }
  
  protected final boolean a(M paramM)
  {
    boolean bool;
    if ((this.awJ == null) || (this.awJ.isEmpty())) {
      if ((paramM.awJ == null) || (paramM.awJ.isEmpty())) {
        bool = true;
      }
    }
    for (;;)
    {
      return bool;
      bool = false;
      continue;
      bool = this.awJ.equals(paramM.awJ);
    }
  }
  
  protected int c()
  {
    int j = 0;
    if (this.awJ != null)
    {
      int i = 0;
      for (;;)
      {
        k = i;
        if (j >= this.awJ.size()) {
          break;
        }
        i += this.awJ.gF(j).c();
        j++;
      }
    }
    int k = 0;
    return k;
  }
  
  protected final int qz()
  {
    if ((this.awJ == null) || (this.awJ.isEmpty())) {}
    for (int i = 0;; i = this.awJ.hashCode()) {
      return i;
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\internal\ph.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */