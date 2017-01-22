package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.ju;

class bf
  implements cg
{
  private final long AN;
  private final int AO;
  private double AP;
  private long AQ;
  private final Object AR = new Object();
  private final String AS;
  private final long apL;
  private final ju yD;
  
  public bf(int paramInt, long paramLong1, long paramLong2, String paramString, ju paramju)
  {
    this.AO = paramInt;
    this.AP = this.AO;
    this.AN = paramLong1;
    this.apL = paramLong2;
    this.AS = paramString;
    this.yD = paramju;
  }
  
  public boolean eJ()
  {
    boolean bool = false;
    for (;;)
    {
      synchronized (this.AR)
      {
        long l = this.yD.currentTimeMillis();
        if (l - this.AQ < this.apL)
        {
          StringBuilder localStringBuilder1 = new java/lang/StringBuilder;
          localStringBuilder1.<init>();
          bh.W("Excessive " + this.AS + " detected; call ignored.");
          return bool;
        }
        if (this.AP < this.AO)
        {
          double d = (l - this.AQ) / this.AN;
          if (d > 0.0D) {
            this.AP = Math.min(this.AO, d + this.AP);
          }
        }
        this.AQ = l;
        if (this.AP >= 1.0D)
        {
          this.AP -= 1.0D;
          bool = true;
        }
      }
      StringBuilder localStringBuilder2 = new java/lang/StringBuilder;
      localStringBuilder2.<init>();
      bh.W("Excessive " + this.AS + " detected; call ignored.");
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\tagmanager\bf.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */