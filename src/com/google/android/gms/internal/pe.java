package com.google.android.gms.internal;

public class pe
{
  private final byte[] aww = new byte['Ā'];
  private int awx;
  private int awy;
  
  public pe(byte[] paramArrayOfByte)
  {
    for (int j = 0; j < 256; j++) {
      this.aww[j] = ((byte)j);
    }
    int k = 0;
    for (j = 0; j < 256; j++)
    {
      k = k + this.aww[j] + paramArrayOfByte[(j % paramArrayOfByte.length)] & 0xFF;
      int i = this.aww[j];
      this.aww[j] = this.aww[k];
      this.aww[k] = i;
    }
    this.awx = 0;
    this.awy = 0;
  }
  
  public void o(byte[] paramArrayOfByte)
  {
    int m = this.awx;
    int k = this.awy;
    for (int j = 0; j < paramArrayOfByte.length; j++)
    {
      m = m + 1 & 0xFF;
      k = k + this.aww[m] & 0xFF;
      int i = this.aww[m];
      this.aww[m] = this.aww[k];
      this.aww[k] = i;
      paramArrayOfByte[j] = ((byte)(paramArrayOfByte[j] ^ this.aww[(this.aww[m] + this.aww[k] & 0xFF)]));
    }
    this.awx = m;
    this.awy = k;
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\internal\pe.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */