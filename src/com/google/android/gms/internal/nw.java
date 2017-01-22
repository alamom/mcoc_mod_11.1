package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.plus.model.moments.ItemScope;
import com.google.android.gms.plus.model.moments.Moment;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public final class nw
  extends jj
  implements Moment
{
  public static final nx CREATOR = new nx();
  private static final HashMap<String, ji.a<?, ?>> amb = new HashMap();
  String BL;
  final int BR;
  String amP;
  nu amX;
  nu amY;
  final Set<Integer> amc;
  String uO;
  
  static
  {
    amb.put("id", ji.a.l("id", 2));
    amb.put("result", ji.a.a("result", 4, nu.class));
    amb.put("startDate", ji.a.l("startDate", 5));
    amb.put("target", ji.a.a("target", 6, nu.class));
    amb.put("type", ji.a.l("type", 7));
  }
  
  public nw()
  {
    this.BR = 1;
    this.amc = new HashSet();
  }
  
  nw(Set<Integer> paramSet, int paramInt, String paramString1, nu paramnu1, String paramString2, nu paramnu2, String paramString3)
  {
    this.amc = paramSet;
    this.BR = paramInt;
    this.BL = paramString1;
    this.amX = paramnu1;
    this.amP = paramString2;
    this.amY = paramnu2;
    this.uO = paramString3;
  }
  
  public nw(Set<Integer> paramSet, String paramString1, nu paramnu1, String paramString2, nu paramnu2, String paramString3)
  {
    this.amc = paramSet;
    this.BR = 1;
    this.BL = paramString1;
    this.amX = paramnu1;
    this.amP = paramString2;
    this.amY = paramnu2;
    this.uO = paramString3;
  }
  
  protected boolean a(ji.a parama)
  {
    return this.amc.contains(Integer.valueOf(parama.hm()));
  }
  
  protected Object b(ji.a parama)
  {
    switch (parama.hm())
    {
    case 3: 
    default: 
      throw new IllegalStateException("Unknown safe parcelable id=" + parama.hm());
    case 2: 
      parama = this.BL;
    }
    for (;;)
    {
      return parama;
      parama = this.amX;
      continue;
      parama = this.amP;
      continue;
      parama = this.amY;
      continue;
      parama = this.uO;
    }
  }
  
  public int describeContents()
  {
    nx localnx = CREATOR;
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    boolean bool;
    if (!(paramObject instanceof nw)) {
      bool = false;
    }
    for (;;)
    {
      return bool;
      if (this == paramObject)
      {
        bool = true;
      }
      else
      {
        paramObject = (nw)paramObject;
        Iterator localIterator = amb.values().iterator();
        for (;;)
        {
          if (localIterator.hasNext())
          {
            ji.a locala = (ji.a)localIterator.next();
            if (a(locala))
            {
              if (((nw)paramObject).a(locala))
              {
                if (b(locala).equals(((nw)paramObject).b(locala))) {
                  continue;
                }
                bool = false;
                break;
              }
              bool = false;
              break;
            }
            if (((nw)paramObject).a(locala))
            {
              bool = false;
              break;
            }
          }
        }
        bool = true;
      }
    }
  }
  
  public String getId()
  {
    return this.BL;
  }
  
  public ItemScope getResult()
  {
    return this.amX;
  }
  
  public String getStartDate()
  {
    return this.amP;
  }
  
  public ItemScope getTarget()
  {
    return this.amY;
  }
  
  public String getType()
  {
    return this.uO;
  }
  
  public boolean hasId()
  {
    return this.amc.contains(Integer.valueOf(2));
  }
  
  public boolean hasResult()
  {
    return this.amc.contains(Integer.valueOf(4));
  }
  
  public boolean hasStartDate()
  {
    return this.amc.contains(Integer.valueOf(5));
  }
  
  public boolean hasTarget()
  {
    return this.amc.contains(Integer.valueOf(6));
  }
  
  public boolean hasType()
  {
    return this.amc.contains(Integer.valueOf(7));
  }
  
  public int hashCode()
  {
    Iterator localIterator = amb.values().iterator();
    int i = 0;
    if (localIterator.hasNext())
    {
      ji.a locala = (ji.a)localIterator.next();
      if (!a(locala)) {
        break label67;
      }
      int j = locala.hm();
      i = b(locala).hashCode() + (i + j);
    }
    label67:
    for (;;)
    {
      break;
      return i;
    }
  }
  
  public HashMap<String, ji.a<?, ?>> hf()
  {
    return amb;
  }
  
  public boolean isDataValid()
  {
    return true;
  }
  
  public nw ns()
  {
    return this;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    nx localnx = CREATOR;
    nx.a(this, paramParcel, paramInt);
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\internal\nw.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */