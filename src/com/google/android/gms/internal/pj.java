package com.google.android.gms.internal;

class pj
{
  private static final pk awM = new pk();
  private boolean awN = false;
  private int[] awO;
  private pk[] awP;
  private int mSize;
  
  public pj()
  {
    this(10);
  }
  
  public pj(int paramInt)
  {
    paramInt = idealIntArraySize(paramInt);
    this.awO = new int[paramInt];
    this.awP = new pk[paramInt];
    this.mSize = 0;
  }
  
  private boolean a(int[] paramArrayOfInt1, int[] paramArrayOfInt2, int paramInt)
  {
    boolean bool = false;
    int i = 0;
    if (i < paramInt) {
      if (paramArrayOfInt1[i] == paramArrayOfInt2[i]) {}
    }
    for (;;)
    {
      return bool;
      i++;
      break;
      bool = true;
    }
  }
  
  private boolean a(pk[] paramArrayOfpk1, pk[] paramArrayOfpk2, int paramInt)
  {
    boolean bool = false;
    int i = 0;
    if (i < paramInt) {
      if (paramArrayOfpk1[i].equals(paramArrayOfpk2[i])) {}
    }
    for (;;)
    {
      return bool;
      i++;
      break;
      bool = true;
    }
  }
  
  private int gG(int paramInt)
  {
    int i = 0;
    int j = this.mSize - 1;
    int k;
    for (;;)
    {
      if (i > j) {
        break label63;
      }
      k = i + j >>> 1;
      int m = this.awO[k];
      if (m < paramInt)
      {
        i = k + 1;
      }
      else
      {
        if (m <= paramInt) {
          break;
        }
        j = k - 1;
      }
    }
    label63:
    for (paramInt = k;; paramInt = i ^ 0xFFFFFFFF) {
      return paramInt;
    }
  }
  
  private void gc()
  {
    int m = this.mSize;
    int[] arrayOfInt = this.awO;
    pk[] arrayOfpk = this.awP;
    int i = 0;
    int k;
    for (int j = 0; i < m; j = k)
    {
      pk localpk = arrayOfpk[i];
      k = j;
      if (localpk != awM)
      {
        if (i != j)
        {
          arrayOfInt[j] = arrayOfInt[i];
          arrayOfpk[j] = localpk;
          arrayOfpk[i] = null;
        }
        k = j + 1;
      }
      i++;
    }
    this.awN = false;
    this.mSize = j;
  }
  
  private int idealByteArraySize(int paramInt)
  {
    for (int i = 4;; i++)
    {
      int j = paramInt;
      if (i < 32)
      {
        if (paramInt <= (1 << i) - 12) {
          j = (1 << i) - 12;
        }
      }
      else {
        return j;
      }
    }
  }
  
  private int idealIntArraySize(int paramInt)
  {
    return idealByteArraySize(paramInt * 4) / 4;
  }
  
  public void a(int paramInt, pk parampk)
  {
    int i = gG(paramInt);
    if (i >= 0) {
      this.awP[i] = parampk;
    }
    for (;;)
    {
      return;
      int j = i ^ 0xFFFFFFFF;
      if ((j < this.mSize) && (this.awP[j] == awM))
      {
        this.awO[j] = paramInt;
        this.awP[j] = parampk;
      }
      else
      {
        i = j;
        if (this.awN)
        {
          i = j;
          if (this.mSize >= this.awO.length)
          {
            gc();
            i = gG(paramInt) ^ 0xFFFFFFFF;
          }
        }
        if (this.mSize >= this.awO.length)
        {
          j = idealIntArraySize(this.mSize + 1);
          int[] arrayOfInt = new int[j];
          pk[] arrayOfpk = new pk[j];
          System.arraycopy(this.awO, 0, arrayOfInt, 0, this.awO.length);
          System.arraycopy(this.awP, 0, arrayOfpk, 0, this.awP.length);
          this.awO = arrayOfInt;
          this.awP = arrayOfpk;
        }
        if (this.mSize - i != 0)
        {
          System.arraycopy(this.awO, i, this.awO, i + 1, this.mSize - i);
          System.arraycopy(this.awP, i, this.awP, i + 1, this.mSize - i);
        }
        this.awO[i] = paramInt;
        this.awP[i] = parampk;
        this.mSize += 1;
      }
    }
  }
  
  public boolean equals(Object paramObject)
  {
    boolean bool = true;
    if (paramObject == this) {}
    for (;;)
    {
      return bool;
      if (!(paramObject instanceof pj))
      {
        bool = false;
      }
      else
      {
        paramObject = (pj)paramObject;
        if (size() != ((pj)paramObject).size()) {
          bool = false;
        } else if ((!a(this.awO, ((pj)paramObject).awO, this.mSize)) || (!a(this.awP, ((pj)paramObject).awP, this.mSize))) {
          bool = false;
        }
      }
    }
  }
  
  public pk gE(int paramInt)
  {
    paramInt = gG(paramInt);
    if ((paramInt < 0) || (this.awP[paramInt] == awM)) {}
    for (pk localpk = null;; localpk = this.awP[paramInt]) {
      return localpk;
    }
  }
  
  public pk gF(int paramInt)
  {
    if (this.awN) {
      gc();
    }
    return this.awP[paramInt];
  }
  
  public int hashCode()
  {
    if (this.awN) {
      gc();
    }
    int j = 17;
    for (int i = 0; i < this.mSize; i++) {
      j = (j * 31 + this.awO[i]) * 31 + this.awP[i].hashCode();
    }
    return j;
  }
  
  public boolean isEmpty()
  {
    if (size() == 0) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public int size()
  {
    if (this.awN) {
      gc();
    }
    return this.mSize;
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\internal\pj.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */