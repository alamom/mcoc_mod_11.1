package com.google.android.gms.drive.metadata.internal;

import android.os.Bundle;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.drive.metadata.a;

public class g
  extends a<Long>
{
  public g(String paramString, int paramInt)
  {
    super(paramString, paramInt);
  }
  
  protected void a(Bundle paramBundle, Long paramLong)
  {
    paramBundle.putLong(getName(), paramLong.longValue());
  }
  
  protected Long h(DataHolder paramDataHolder, int paramInt1, int paramInt2)
  {
    return Long.valueOf(paramDataHolder.a(getName(), paramInt1, paramInt2));
  }
  
  protected Long k(Bundle paramBundle)
  {
    return Long.valueOf(paramBundle.getLong(getName()));
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\drive\metadata\internal\g.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */