package com.google.android.gms.tagmanager;

class dh
  extends Number
  implements Comparable<dh>
{
  private double arR;
  private long arS;
  private boolean arT;
  
  private dh(double paramDouble)
  {
    this.arR = paramDouble;
    this.arT = false;
  }
  
  private dh(long paramLong)
  {
    this.arS = paramLong;
    this.arT = true;
  }
  
  public static dh a(Double paramDouble)
  {
    return new dh(paramDouble.doubleValue());
  }
  
  public static dh cW(String paramString)
    throws NumberFormatException
  {
    try
    {
      dh localdh1 = new com/google/android/gms/tagmanager/dh;
      localdh1.<init>(Long.parseLong(paramString));
      paramString = localdh1;
    }
    catch (NumberFormatException localNumberFormatException1)
    {
      try
      {
        dh localdh2 = new dh(Double.parseDouble(paramString));
        paramString = localdh2;
      }
      catch (NumberFormatException localNumberFormatException2)
      {
        throw new NumberFormatException(paramString + " is not a valid TypedNumber");
      }
    }
    return paramString;
  }
  
  public static dh z(long paramLong)
  {
    return new dh(paramLong);
  }
  
  public int a(dh paramdh)
  {
    if ((pA()) && (paramdh.pA())) {}
    for (int i = new Long(this.arS).compareTo(Long.valueOf(paramdh.arS));; i = Double.compare(doubleValue(), paramdh.doubleValue())) {
      return i;
    }
  }
  
  public byte byteValue()
  {
    return (byte)(int)longValue();
  }
  
  public double doubleValue()
  {
    if (pA()) {}
    for (double d = this.arS;; d = this.arR) {
      return d;
    }
  }
  
  public boolean equals(Object paramObject)
  {
    if (((paramObject instanceof dh)) && (a((dh)paramObject) == 0)) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public float floatValue()
  {
    return (float)doubleValue();
  }
  
  public int hashCode()
  {
    return new Long(longValue()).hashCode();
  }
  
  public int intValue()
  {
    return pC();
  }
  
  public long longValue()
  {
    return pB();
  }
  
  public boolean pA()
  {
    return this.arT;
  }
  
  public long pB()
  {
    if (pA()) {}
    for (long l = this.arS;; l = this.arR) {
      return l;
    }
  }
  
  public int pC()
  {
    return (int)longValue();
  }
  
  public short pD()
  {
    return (short)(int)longValue();
  }
  
  public boolean pz()
  {
    if (!pA()) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public short shortValue()
  {
    return pD();
  }
  
  public String toString()
  {
    if (pA()) {}
    for (String str = Long.toString(this.arS);; str = Double.toString(this.arR)) {
      return str;
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\tagmanager\dh.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */