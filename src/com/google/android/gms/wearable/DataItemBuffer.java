package com.google.android.gms.wearable;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.g;
import com.google.android.gms.wearable.internal.o;

public class DataItemBuffer
  extends g<DataItem>
  implements Result
{
  private final Status CM;
  
  public DataItemBuffer(DataHolder paramDataHolder)
  {
    super(paramDataHolder);
    this.CM = new Status(paramDataHolder.getStatusCode());
  }
  
  protected String gD()
  {
    return "path";
  }
  
  public Status getStatus()
  {
    return this.CM;
  }
  
  protected DataItem q(int paramInt1, int paramInt2)
  {
    return new o(this.II, paramInt1, paramInt2);
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\wearable\DataItemBuffer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */