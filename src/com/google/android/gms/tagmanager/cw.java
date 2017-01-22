package com.google.android.gms.tagmanager;

class cw
  implements cg
{
  private final long AN;
  private final int AO;
  private double AP;
  private final Object AR = new Object();
  private long arp;
  
  public cw()
  {
    this(60, 2000L);
  }
  
  public cw(int paramInt, long paramLong)
  {
    this.AO = paramInt;
    this.AP = this.AO;
    this.AN = paramLong;
  }
  
  public boolean eJ()
  {
    synchronized (this.AR)
    {
      long l = System.currentTimeMillis();
      if (this.AP < this.AO)
      {
        double d = (l - this.arp) / this.AN;
        if (d > 0.0D) {
          this.AP = Math.min(this.AO, d + this.AP);
        }
      }
      this.arp = l;
      if (this.AP >= 1.0D)
      {
        this.AP -= 1.0D;
        bool = true;
        return bool;
      }
      bh.W("No more tokens available.");
      boolean bool = false;
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\tagmanager\cw.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */