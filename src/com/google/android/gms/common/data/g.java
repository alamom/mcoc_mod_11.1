package com.google.android.gms.common.data;

import java.util.ArrayList;

public abstract class g<T>
  extends DataBuffer<T>
{
  private boolean Kp = false;
  private ArrayList<Integer> Kq;
  
  protected g(DataHolder paramDataHolder)
  {
    super(paramDataHolder);
  }
  
  private void gE()
  {
    label153:
    for (;;)
    {
      try
      {
        if (!this.Kp)
        {
          int j = this.II.getCount();
          Object localObject1 = new java/util/ArrayList;
          ((ArrayList)localObject1).<init>();
          this.Kq = ((ArrayList)localObject1);
          if (j > 0)
          {
            this.Kq.add(Integer.valueOf(0));
            String str2 = gD();
            int i = this.II.ar(0);
            localObject1 = this.II.c(str2, 0, i);
            i = 1;
            if (i < j)
            {
              int k = this.II.ar(i);
              String str1 = this.II.c(str2, i, k);
              if (str1.equals(localObject1)) {
                break label153;
              }
              this.Kq.add(Integer.valueOf(i));
              localObject1 = str1;
              i++;
              continue;
            }
          }
          this.Kp = true;
        }
        else
        {
          return;
        }
      }
      finally {}
    }
  }
  
  int au(int paramInt)
  {
    if ((paramInt < 0) || (paramInt >= this.Kq.size())) {
      throw new IllegalArgumentException("Position " + paramInt + " is out of bounds for this buffer");
    }
    return ((Integer)this.Kq.get(paramInt)).intValue();
  }
  
  protected int av(int paramInt)
  {
    int j;
    if ((paramInt < 0) || (paramInt == this.Kq.size()))
    {
      j = 0;
      return j;
    }
    if (paramInt == this.Kq.size() - 1) {}
    for (int i = this.II.getCount() - ((Integer)this.Kq.get(paramInt)).intValue();; i = ((Integer)this.Kq.get(paramInt + 1)).intValue() - ((Integer)this.Kq.get(paramInt)).intValue())
    {
      j = i;
      if (i != 1) {
        break;
      }
      paramInt = au(paramInt);
      int k = this.II.ar(paramInt);
      String str = gF();
      j = i;
      if (str == null) {
        break;
      }
      j = i;
      if (this.II.c(str, paramInt, k) != null) {
        break;
      }
      j = 0;
      break;
    }
  }
  
  protected abstract T f(int paramInt1, int paramInt2);
  
  protected abstract String gD();
  
  protected String gF()
  {
    return null;
  }
  
  public final T get(int paramInt)
  {
    gE();
    return (T)f(au(paramInt), av(paramInt));
  }
  
  public int getCount()
  {
    gE();
    return this.Kq.size();
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\common\data\g.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */