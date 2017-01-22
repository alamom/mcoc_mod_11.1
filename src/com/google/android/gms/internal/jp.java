package com.google.android.gms.internal;

import android.os.Bundle;
import android.os.Parcel;
import com.google.android.gms.common.internal.o;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

public class jp
  extends ji
  implements SafeParcelable
{
  public static final jq CREATOR = new jq();
  private final int BR;
  private final jm MG;
  private final Parcel MN;
  private final int MO;
  private int MP;
  private int MQ;
  private final String mClassName;
  
  jp(int paramInt, Parcel paramParcel, jm paramjm)
  {
    this.BR = paramInt;
    this.MN = ((Parcel)o.i(paramParcel));
    this.MO = 2;
    this.MG = paramjm;
    if (this.MG == null) {}
    for (this.mClassName = null;; this.mClassName = this.MG.hv())
    {
      this.MP = 2;
      return;
    }
  }
  
  private jp(SafeParcelable paramSafeParcelable, jm paramjm, String paramString)
  {
    this.BR = 1;
    this.MN = Parcel.obtain();
    paramSafeParcelable.writeToParcel(this.MN, 0);
    this.MO = 1;
    this.MG = ((jm)o.i(paramjm));
    this.mClassName = ((String)o.i(paramString));
    this.MP = 2;
  }
  
  public static <T extends ji,  extends SafeParcelable> jp a(T paramT)
  {
    String str = paramT.getClass().getCanonicalName();
    jm localjm = b(paramT);
    return new jp((SafeParcelable)paramT, localjm, str);
  }
  
  private static void a(jm paramjm, ji paramji)
  {
    Class localClass = paramji.getClass();
    if (!paramjm.b(localClass))
    {
      HashMap localHashMap = paramji.hf();
      paramjm.a(localClass, paramji.hf());
      Iterator localIterator = localHashMap.keySet().iterator();
      while (localIterator.hasNext())
      {
        paramji = (ji.a)localHashMap.get((String)localIterator.next());
        localClass = paramji.hn();
        if (localClass != null) {
          try
          {
            a(paramjm, (ji)localClass.newInstance());
          }
          catch (InstantiationException paramjm)
          {
            throw new IllegalStateException("Could not instantiate an object of type " + paramji.hn().getCanonicalName(), paramjm);
          }
          catch (IllegalAccessException paramjm)
          {
            throw new IllegalStateException("Could not access object of type " + paramji.hn().getCanonicalName(), paramjm);
          }
        }
      }
    }
  }
  
  private void a(StringBuilder paramStringBuilder, int paramInt, Object paramObject)
  {
    switch (paramInt)
    {
    default: 
      throw new IllegalArgumentException("Unknown type = " + paramInt);
    case 0: 
    case 1: 
    case 2: 
    case 3: 
    case 4: 
    case 5: 
    case 6: 
      paramStringBuilder.append(paramObject);
    case 7: 
    case 8: 
    case 9: 
    case 10: 
      for (;;)
      {
        return;
        paramStringBuilder.append("\"").append(jz.bf(paramObject.toString())).append("\"");
        continue;
        paramStringBuilder.append("\"").append(js.d((byte[])paramObject)).append("\"");
        continue;
        paramStringBuilder.append("\"").append(js.e((byte[])paramObject));
        paramStringBuilder.append("\"");
        continue;
        ka.a(paramStringBuilder, (HashMap)paramObject);
      }
    }
    throw new IllegalArgumentException("Method does not accept concrete type.");
  }
  
  private void a(StringBuilder paramStringBuilder, ji.a<?, ?> parama, Parcel paramParcel, int paramInt)
  {
    switch (parama.he())
    {
    default: 
      throw new IllegalArgumentException("Unknown field out type = " + parama.he());
    case 0: 
      b(paramStringBuilder, parama, a(parama, Integer.valueOf(a.g(paramParcel, paramInt))));
    case 1: 
    case 2: 
    case 3: 
    case 4: 
    case 5: 
    case 6: 
    case 7: 
    case 8: 
    case 9: 
    case 10: 
      for (;;)
      {
        return;
        b(paramStringBuilder, parama, a(parama, a.k(paramParcel, paramInt)));
        continue;
        b(paramStringBuilder, parama, a(parama, Long.valueOf(a.i(paramParcel, paramInt))));
        continue;
        b(paramStringBuilder, parama, a(parama, Float.valueOf(a.l(paramParcel, paramInt))));
        continue;
        b(paramStringBuilder, parama, a(parama, Double.valueOf(a.m(paramParcel, paramInt))));
        continue;
        b(paramStringBuilder, parama, a(parama, a.n(paramParcel, paramInt)));
        continue;
        b(paramStringBuilder, parama, a(parama, Boolean.valueOf(a.c(paramParcel, paramInt))));
        continue;
        b(paramStringBuilder, parama, a(parama, a.o(paramParcel, paramInt)));
        continue;
        b(paramStringBuilder, parama, a(parama, a.r(paramParcel, paramInt)));
        continue;
        b(paramStringBuilder, parama, a(parama, e(a.q(paramParcel, paramInt))));
      }
    }
    throw new IllegalArgumentException("Method does not accept concrete type.");
  }
  
  private void a(StringBuilder paramStringBuilder, String paramString, ji.a<?, ?> parama, Parcel paramParcel, int paramInt)
  {
    paramStringBuilder.append("\"").append(paramString).append("\":");
    if (parama.hp()) {
      a(paramStringBuilder, parama, paramParcel, paramInt);
    }
    for (;;)
    {
      return;
      b(paramStringBuilder, parama, paramParcel, paramInt);
    }
  }
  
  private void a(StringBuilder paramStringBuilder, HashMap<String, ji.a<?, ?>> paramHashMap, Parcel paramParcel)
  {
    paramHashMap = b(paramHashMap);
    paramStringBuilder.append('{');
    int j = a.C(paramParcel);
    int i = 0;
    while (paramParcel.dataPosition() < j)
    {
      int k = a.B(paramParcel);
      Map.Entry localEntry = (Map.Entry)paramHashMap.get(Integer.valueOf(a.aD(k)));
      if (localEntry != null)
      {
        if (i != 0) {
          paramStringBuilder.append(",");
        }
        a(paramStringBuilder, (String)localEntry.getKey(), (ji.a)localEntry.getValue(), paramParcel, k);
        i = 1;
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new a.a("Overread allowed size end=" + j, paramParcel);
    }
    paramStringBuilder.append('}');
  }
  
  private static jm b(ji paramji)
  {
    jm localjm = new jm(paramji.getClass());
    a(localjm, paramji);
    localjm.ht();
    localjm.hs();
    return localjm;
  }
  
  private static HashMap<Integer, Map.Entry<String, ji.a<?, ?>>> b(HashMap<String, ji.a<?, ?>> paramHashMap)
  {
    HashMap localHashMap = new HashMap();
    paramHashMap = paramHashMap.entrySet().iterator();
    while (paramHashMap.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)paramHashMap.next();
      localHashMap.put(Integer.valueOf(((ji.a)localEntry.getValue()).hm()), localEntry);
    }
    return localHashMap;
  }
  
  private void b(StringBuilder paramStringBuilder, ji.a<?, ?> parama, Parcel paramParcel, int paramInt)
  {
    if (parama.hk())
    {
      paramStringBuilder.append("[");
      switch (parama.he())
      {
      default: 
        throw new IllegalStateException("Unknown field type out.");
      case 0: 
        jr.a(paramStringBuilder, a.u(paramParcel, paramInt));
        paramStringBuilder.append("]");
      }
    }
    for (;;)
    {
      return;
      jr.a(paramStringBuilder, a.w(paramParcel, paramInt));
      break;
      jr.a(paramStringBuilder, a.v(paramParcel, paramInt));
      break;
      jr.a(paramStringBuilder, a.x(paramParcel, paramInt));
      break;
      jr.a(paramStringBuilder, a.y(paramParcel, paramInt));
      break;
      jr.a(paramStringBuilder, a.z(paramParcel, paramInt));
      break;
      jr.a(paramStringBuilder, a.t(paramParcel, paramInt));
      break;
      jr.a(paramStringBuilder, a.A(paramParcel, paramInt));
      break;
      throw new UnsupportedOperationException("List of type BASE64, BASE64_URL_SAFE, or STRING_MAP is not supported");
      paramParcel = a.E(paramParcel, paramInt);
      int i = paramParcel.length;
      for (paramInt = 0; paramInt < i; paramInt++)
      {
        if (paramInt > 0) {
          paramStringBuilder.append(",");
        }
        paramParcel[paramInt].setDataPosition(0);
        a(paramStringBuilder, parama.hr(), paramParcel[paramInt]);
      }
      break;
      switch (parama.he())
      {
      default: 
        throw new IllegalStateException("Unknown field type out");
      case 0: 
        paramStringBuilder.append(a.g(paramParcel, paramInt));
        break;
      case 1: 
        paramStringBuilder.append(a.k(paramParcel, paramInt));
        break;
      case 2: 
        paramStringBuilder.append(a.i(paramParcel, paramInt));
        break;
      case 3: 
        paramStringBuilder.append(a.l(paramParcel, paramInt));
        break;
      case 4: 
        paramStringBuilder.append(a.m(paramParcel, paramInt));
        break;
      case 5: 
        paramStringBuilder.append(a.n(paramParcel, paramInt));
        break;
      case 6: 
        paramStringBuilder.append(a.c(paramParcel, paramInt));
        break;
      case 7: 
        parama = a.o(paramParcel, paramInt);
        paramStringBuilder.append("\"").append(jz.bf(parama)).append("\"");
        break;
      case 8: 
        parama = a.r(paramParcel, paramInt);
        paramStringBuilder.append("\"").append(js.d(parama)).append("\"");
        break;
      case 9: 
        parama = a.r(paramParcel, paramInt);
        paramStringBuilder.append("\"").append(js.e(parama));
        paramStringBuilder.append("\"");
        break;
      case 10: 
        parama = a.q(paramParcel, paramInt);
        paramParcel = parama.keySet();
        paramParcel.size();
        paramStringBuilder.append("{");
        paramParcel = paramParcel.iterator();
        for (paramInt = 1; paramParcel.hasNext(); paramInt = 0)
        {
          String str = (String)paramParcel.next();
          if (paramInt == 0) {
            paramStringBuilder.append(",");
          }
          paramStringBuilder.append("\"").append(str).append("\"");
          paramStringBuilder.append(":");
          paramStringBuilder.append("\"").append(jz.bf(parama.getString(str))).append("\"");
        }
        paramStringBuilder.append("}");
        break;
      case 11: 
        paramParcel = a.D(paramParcel, paramInt);
        paramParcel.setDataPosition(0);
        a(paramStringBuilder, parama.hr(), paramParcel);
      }
    }
  }
  
  private void b(StringBuilder paramStringBuilder, ji.a<?, ?> parama, Object paramObject)
  {
    if (parama.hj()) {
      b(paramStringBuilder, parama, (ArrayList)paramObject);
    }
    for (;;)
    {
      return;
      a(paramStringBuilder, parama.hd(), paramObject);
    }
  }
  
  private void b(StringBuilder paramStringBuilder, ji.a<?, ?> parama, ArrayList<?> paramArrayList)
  {
    paramStringBuilder.append("[");
    int j = paramArrayList.size();
    for (int i = 0; i < j; i++)
    {
      if (i != 0) {
        paramStringBuilder.append(",");
      }
      a(paramStringBuilder, parama.hd(), paramArrayList.get(i));
    }
    paramStringBuilder.append("]");
  }
  
  public static HashMap<String, String> e(Bundle paramBundle)
  {
    HashMap localHashMap = new HashMap();
    Iterator localIterator = paramBundle.keySet().iterator();
    while (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      localHashMap.put(str, paramBundle.getString(str));
    }
    return localHashMap;
  }
  
  protected Object ba(String paramString)
  {
    throw new UnsupportedOperationException("Converting to JSON does not require this method.");
  }
  
  protected boolean bb(String paramString)
  {
    throw new UnsupportedOperationException("Converting to JSON does not require this method.");
  }
  
  public int describeContents()
  {
    jq localjq = CREATOR;
    return 0;
  }
  
  public int getVersionCode()
  {
    return this.BR;
  }
  
  public HashMap<String, ji.a<?, ?>> hf()
  {
    if (this.MG == null) {}
    for (Object localObject = null;; localObject = this.MG.be(this.mClassName)) {
      return (HashMap<String, ji.a<?, ?>>)localObject;
    }
  }
  
  public Parcel hx()
  {
    switch (this.MP)
    {
    }
    for (;;)
    {
      return this.MN;
      this.MQ = b.D(this.MN);
      b.H(this.MN, this.MQ);
      this.MP = 2;
      continue;
      b.H(this.MN, this.MQ);
      this.MP = 2;
    }
  }
  
  jm hy()
  {
    jm localjm;
    switch (this.MO)
    {
    default: 
      throw new IllegalStateException("Invalid creation type: " + this.MO);
    case 0: 
      localjm = null;
    }
    for (;;)
    {
      return localjm;
      localjm = this.MG;
      continue;
      localjm = this.MG;
    }
  }
  
  public String toString()
  {
    o.b(this.MG, "Cannot convert to JSON on client side.");
    Parcel localParcel = hx();
    localParcel.setDataPosition(0);
    StringBuilder localStringBuilder = new StringBuilder(100);
    a(localStringBuilder, this.MG.be(this.mClassName), localParcel);
    return localStringBuilder.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    jq localjq = CREATOR;
    jq.a(this, paramParcel, paramInt);
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\internal\jp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */