package com.google.android.gms.plus.model.people;

import android.os.Bundle;
import com.google.android.gms.common.data.DataBuffer;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.e;
import com.google.android.gms.internal.nz;
import com.google.android.gms.internal.ok;

public final class PersonBuffer
  extends DataBuffer<Person>
{
  private final e<nz> anJ;
  
  public PersonBuffer(DataHolder paramDataHolder)
  {
    super(paramDataHolder);
    if ((paramDataHolder.gy() != null) && (paramDataHolder.gy().getBoolean("com.google.android.gms.plus.IsSafeParcelable", false))) {}
    for (this.anJ = new e(paramDataHolder, nz.CREATOR);; this.anJ = null) {
      return;
    }
  }
  
  public Person get(int paramInt)
  {
    if (this.anJ != null) {}
    for (Object localObject = (Person)this.anJ.aq(paramInt);; localObject = new ok(this.II, paramInt)) {
      return (Person)localObject;
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\plus\model\people\PersonBuffer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */