package com.google.android.gms.analytics;

class y
  implements ac
{
  private final long AN;
  private final int AO;
  private double AP;
  private long AQ;
  private final Object AR = new Object();
  private final String AS;
  
  public y(int paramInt, long paramLong, String paramString)
  {
    this.AO = paramInt;
    this.AP = this.AO;
    this.AN = paramLong;
    this.AS = paramString;
  }
  
  public y(String paramString)
  {
    this(60, 2000L, paramString);
  }
  
  public boolean eJ()
  {
    synchronized (this.AR)
    {
      long l = System.currentTimeMillis();
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
        return bool;
      }
      StringBuilder localStringBuilder = new java/lang/StringBuilder;
      localStringBuilder.<init>();
      z.W("Excessive " + this.AS + " detected; call ignored.");
      boolean bool = false;
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\analytics\y.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */