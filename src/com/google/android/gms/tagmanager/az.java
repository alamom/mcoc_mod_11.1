package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.a;
import com.google.android.gms.internal.b;
import com.google.android.gms.internal.d.a;
import java.io.UnsupportedEncodingException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

class az
  extends aj
{
  private static final String ID = a.ae.toString();
  private static final String apA = b.di.toString();
  private static final String apB = b.dm.toString();
  private static final String apC = b.cH.toString();
  private static final String apf = b.bw.toString();
  
  public az()
  {
    super(ID, new String[] { apf });
  }
  
  private String a(String paramString, a parama, Set<Character> paramSet)
  {
    switch (1.apD[parama.ordinal()])
    {
    }
    for (;;)
    {
      return paramString;
      try
      {
        parama = dm.de(paramString);
        paramString = parama;
      }
      catch (UnsupportedEncodingException parama)
      {
        bh.b("Joiner: unsupported encoding", parama);
      }
      continue;
      paramString = paramString.replace("\\", "\\\\");
      parama = paramSet.iterator();
      while (parama.hasNext())
      {
        paramSet = ((Character)parama.next()).toString();
        paramString = paramString.replace(paramSet, "\\" + paramSet);
      }
    }
  }
  
  private void a(StringBuilder paramStringBuilder, String paramString, a parama, Set<Character> paramSet)
  {
    paramStringBuilder.append(a(paramString, parama, paramSet));
  }
  
  private void a(Set<Character> paramSet, String paramString)
  {
    for (int i = 0; i < paramString.length(); i++) {
      paramSet.add(Character.valueOf(paramString.charAt(i)));
    }
  }
  
  public d.a C(Map<String, d.a> paramMap)
  {
    Object localObject2 = (d.a)paramMap.get(apf);
    if (localObject2 == null)
    {
      paramMap = di.pK();
      return paramMap;
    }
    Object localObject1 = (d.a)paramMap.get(apA);
    String str1;
    label51:
    String str2;
    if (localObject1 != null)
    {
      str1 = di.j((d.a)localObject1);
      localObject1 = (d.a)paramMap.get(apB);
      if (localObject1 == null) {
        break label189;
      }
      str2 = di.j((d.a)localObject1);
      label77:
      localObject1 = a.apE;
      paramMap = (d.a)paramMap.get(apC);
      if (paramMap == null) {
        break label422;
      }
      paramMap = di.j(paramMap);
      if (!"url".equals(paramMap)) {
        break label196;
      }
      localObject1 = a.apF;
      paramMap = null;
    }
    for (;;)
    {
      label120:
      StringBuilder localStringBuilder = new StringBuilder();
      switch (((d.a)localObject2).type)
      {
      default: 
        a(localStringBuilder, di.j((d.a)localObject2), (a)localObject1, paramMap);
      }
      for (;;)
      {
        paramMap = di.u(localStringBuilder.toString());
        break;
        str1 = "";
        break label51;
        label189:
        str2 = "=";
        break label77;
        label196:
        if ("backslash".equals(paramMap))
        {
          localObject1 = a.apG;
          paramMap = new HashSet();
          a(paramMap, str1);
          a(paramMap, str2);
          paramMap.remove(Character.valueOf('\\'));
          break label120;
        }
        bh.T("Joiner: unsupported escape type: " + paramMap);
        paramMap = di.pK();
        break;
        int i = 1;
        localObject2 = ((d.a)localObject2).gw;
        int k = localObject2.length;
        int j = 0;
        while (j < k)
        {
          str2 = localObject2[j];
          if (i == 0) {
            localStringBuilder.append(str1);
          }
          a(localStringBuilder, di.j(str2), (a)localObject1, paramMap);
          j++;
          i = 0;
        }
        for (i = 0; i < ((d.a)localObject2).gx.length; i++)
        {
          if (i > 0) {
            localStringBuilder.append(str1);
          }
          String str3 = di.j(localObject2.gx[i]);
          String str4 = di.j(localObject2.gy[i]);
          a(localStringBuilder, str3, (a)localObject1, paramMap);
          localStringBuilder.append(str2);
          a(localStringBuilder, str4, (a)localObject1, paramMap);
        }
      }
      label422:
      paramMap = null;
    }
  }
  
  public boolean nN()
  {
    return true;
  }
  
  private static enum a
  {
    private a() {}
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\tagmanager\az.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */