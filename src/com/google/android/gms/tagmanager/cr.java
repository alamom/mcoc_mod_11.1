package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.b;
import com.google.android.gms.internal.c.b;
import com.google.android.gms.internal.c.e;
import com.google.android.gms.internal.c.f;
import com.google.android.gms.internal.c.g;
import com.google.android.gms.internal.c.h;
import com.google.android.gms.internal.d.a;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

class cr
{
  private static d.a a(int paramInt, c.f paramf, d.a[] paramArrayOfa, Set<Integer> paramSet)
    throws cr.g
  {
    int k = 0;
    int m = 0;
    int j = 0;
    if (paramSet.contains(Integer.valueOf(paramInt))) {
      cL("Value cycle detected.  Current value reference: " + paramInt + "." + "  Previous value references: " + paramSet + ".");
    }
    d.a locala1 = (d.a)a(paramf.fG, paramInt, "values");
    if (paramArrayOfa[paramInt] != null)
    {
      localObject1 = paramArrayOfa[paramInt];
      return (d.a)localObject1;
    }
    Object localObject1 = null;
    paramSet.add(Integer.valueOf(paramInt));
    switch (locala1.type)
    {
    }
    for (;;)
    {
      if (localObject1 == null) {
        cL("Invalid value: " + locala1);
      }
      paramArrayOfa[paramInt] = localObject1;
      paramSet.remove(Integer.valueOf(paramInt));
      break;
      localObject1 = h(locala1);
      d.a locala2 = g(locala1);
      locala2.gw = new d.a[((c.h)localObject1).gh.length];
      Object localObject2 = ((c.h)localObject1).gh;
      k = localObject2.length;
      for (int i = 0;; i++)
      {
        localObject1 = locala2;
        if (j >= k) {
          break;
        }
        m = localObject2[j];
        locala2.gw[i] = a(m, paramf, paramArrayOfa, paramSet);
        j++;
      }
      locala2 = g(locala1);
      localObject2 = h(locala1);
      if (((c.h)localObject2).gi.length != ((c.h)localObject2).gj.length) {
        cL("Uneven map keys (" + ((c.h)localObject2).gi.length + ") and map values (" + ((c.h)localObject2).gj.length + ")");
      }
      locala2.gx = new d.a[((c.h)localObject2).gi.length];
      locala2.gy = new d.a[((c.h)localObject2).gi.length];
      localObject1 = ((c.h)localObject2).gi;
      m = localObject1.length;
      j = 0;
      for (i = 0; j < m; i++)
      {
        int n = localObject1[j];
        locala2.gx[i] = a(n, paramf, paramArrayOfa, paramSet);
        j++;
      }
      localObject2 = ((c.h)localObject2).gj;
      m = localObject2.length;
      i = 0;
      j = k;
      for (;;)
      {
        localObject1 = locala2;
        if (j >= m) {
          break;
        }
        k = localObject2[j];
        locala2.gy[i] = a(k, paramf, paramArrayOfa, paramSet);
        j++;
        i++;
      }
      localObject1 = g(locala1);
      ((d.a)localObject1).gz = di.j(a(h(locala1).gm, paramf, paramArrayOfa, paramSet));
      continue;
      locala2 = g(locala1);
      localObject1 = h(locala1);
      locala2.gD = new d.a[((c.h)localObject1).gl.length];
      localObject2 = ((c.h)localObject1).gl;
      k = localObject2.length;
      i = 0;
      j = m;
      for (;;)
      {
        localObject1 = locala2;
        if (j >= k) {
          break;
        }
        m = localObject2[j];
        locala2.gD[i] = a(m, paramf, paramArrayOfa, paramSet);
        j++;
        i++;
      }
      localObject1 = locala1;
    }
  }
  
  private static a a(c.b paramb, c.f paramf, d.a[] paramArrayOfa, int paramInt)
    throws cr.g
  {
    b localb = a.oT();
    paramb = paramb.fq;
    int i = paramb.length;
    paramInt = 0;
    if (paramInt < i)
    {
      int j = paramb[paramInt];
      Object localObject = (c.e)a(paramf.fH, Integer.valueOf(j).intValue(), "properties");
      String str = (String)a(paramf.fF, ((c.e)localObject).key, "keys");
      localObject = (d.a)a(paramArrayOfa, ((c.e)localObject).value, "values");
      if (b.ec.toString().equals(str)) {
        localb.i((d.a)localObject);
      }
      for (;;)
      {
        paramInt++;
        break;
        localb.b(str, (d.a)localObject);
      }
    }
    return localb.oW();
  }
  
  private static e a(c.g paramg, List<a> paramList1, List<a> paramList2, List<a> paramList3, c.f paramf)
  {
    f localf = e.pb();
    int[] arrayOfInt = paramg.fV;
    int j = arrayOfInt.length;
    for (int i = 0; i < j; i++) {
      localf.b((a)paramList3.get(Integer.valueOf(arrayOfInt[i]).intValue()));
    }
    arrayOfInt = paramg.fW;
    j = arrayOfInt.length;
    for (i = 0; i < j; i++) {
      localf.c((a)paramList3.get(Integer.valueOf(arrayOfInt[i]).intValue()));
    }
    paramList3 = paramg.fX;
    j = paramList3.length;
    for (i = 0; i < j; i++) {
      localf.d((a)paramList1.get(Integer.valueOf(paramList3[i]).intValue()));
    }
    int k;
    for (k : paramg.fZ) {
      localf.cN(paramf.fG[Integer.valueOf(k).intValue()].gv);
    }
    paramList3 = paramg.fY;
    j = paramList3.length;
    for (i = 0; i < j; i++) {
      localf.e((a)paramList1.get(Integer.valueOf(paramList3[i]).intValue()));
    }
    for (k : paramg.ga) {
      localf.cO(paramf.fG[Integer.valueOf(k).intValue()].gv);
    }
    paramList1 = paramg.gb;
    j = paramList1.length;
    for (i = 0; i < j; i++) {
      localf.f((a)paramList2.get(Integer.valueOf(paramList1[i]).intValue()));
    }
    for (k : paramg.gd) {
      localf.cP(paramf.fG[Integer.valueOf(k).intValue()].gv);
    }
    paramList1 = paramg.gc;
    j = paramList1.length;
    for (i = 0; i < j; i++) {
      localf.g((a)paramList2.get(Integer.valueOf(paramList1[i]).intValue()));
    }
    for (k : paramg.ge) {
      localf.cQ(paramf.fG[Integer.valueOf(k).intValue()].gv);
    }
    return localf.pm();
  }
  
  private static <T> T a(T[] paramArrayOfT, int paramInt, String paramString)
    throws cr.g
  {
    if ((paramInt < 0) || (paramInt >= paramArrayOfT.length)) {
      cL("Index out of bounds detected: " + paramInt + " in " + paramString);
    }
    return paramArrayOfT[paramInt];
  }
  
  public static c b(c.f paramf)
    throws cr.g
  {
    int j = 0;
    d.a[] arrayOfa = new d.a[paramf.fG.length];
    for (int i = 0; i < paramf.fG.length; i++) {
      a(i, paramf, arrayOfa, new HashSet(0));
    }
    d locald = c.oX();
    ArrayList localArrayList1 = new ArrayList();
    for (i = 0; i < paramf.fJ.length; i++) {
      localArrayList1.add(a(paramf.fJ[i], paramf, arrayOfa, i));
    }
    ArrayList localArrayList2 = new ArrayList();
    for (i = 0; i < paramf.fK.length; i++) {
      localArrayList2.add(a(paramf.fK[i], paramf, arrayOfa, i));
    }
    ArrayList localArrayList3 = new ArrayList();
    for (i = 0; i < paramf.fI.length; i++)
    {
      localObject = a(paramf.fI[i], paramf, arrayOfa, i);
      locald.a((a)localObject);
      localArrayList3.add(localObject);
    }
    Object localObject = paramf.fL;
    int k = localObject.length;
    for (i = j; i < k; i++) {
      locald.a(a(localObject[i], localArrayList1, localArrayList3, localArrayList2, paramf));
    }
    locald.cM(paramf.version);
    locald.fm(paramf.fT);
    return locald.pa();
  }
  
  public static void b(InputStream paramInputStream, OutputStream paramOutputStream)
    throws IOException
  {
    byte[] arrayOfByte = new byte['Ѐ'];
    for (;;)
    {
      int i = paramInputStream.read(arrayOfByte);
      if (i == -1) {
        return;
      }
      paramOutputStream.write(arrayOfByte, 0, i);
    }
  }
  
  private static void cL(String paramString)
    throws cr.g
  {
    bh.T(paramString);
    throw new g(paramString);
  }
  
  public static d.a g(d.a parama)
  {
    d.a locala = new d.a();
    locala.type = parama.type;
    locala.gE = ((int[])parama.gE.clone());
    if (parama.gF) {
      locala.gF = parama.gF;
    }
    return locala;
  }
  
  private static c.h h(d.a parama)
    throws cr.g
  {
    if ((c.h)parama.a(c.h.gf) == null) {
      cL("Expected a ServingValue and didn't get one. Value is: " + parama);
    }
    return (c.h)parama.a(c.h.gf);
  }
  
  public static class a
  {
    private final Map<String, d.a> aqD;
    private final d.a aqE;
    
    private a(Map<String, d.a> paramMap, d.a parama)
    {
      this.aqD = paramMap;
      this.aqE = parama;
    }
    
    public static cr.b oT()
    {
      return new cr.b(null);
    }
    
    public void a(String paramString, d.a parama)
    {
      this.aqD.put(paramString, parama);
    }
    
    public Map<String, d.a> oU()
    {
      return Collections.unmodifiableMap(this.aqD);
    }
    
    public d.a oV()
    {
      return this.aqE;
    }
    
    public String toString()
    {
      return "Properties: " + oU() + " pushAfterEvaluate: " + this.aqE;
    }
  }
  
  public static class b
  {
    private final Map<String, d.a> aqD = new HashMap();
    private d.a aqE;
    
    public b b(String paramString, d.a parama)
    {
      this.aqD.put(paramString, parama);
      return this;
    }
    
    public b i(d.a parama)
    {
      this.aqE = parama;
      return this;
    }
    
    public cr.a oW()
    {
      return new cr.a(this.aqD, this.aqE, null);
    }
  }
  
  public static class c
  {
    private final String Sx;
    private final List<cr.e> aqF;
    private final Map<String, List<cr.a>> aqG;
    private final int aqH;
    
    private c(List<cr.e> paramList, Map<String, List<cr.a>> paramMap, String paramString, int paramInt)
    {
      this.aqF = Collections.unmodifiableList(paramList);
      this.aqG = Collections.unmodifiableMap(paramMap);
      this.Sx = paramString;
      this.aqH = paramInt;
    }
    
    public static cr.d oX()
    {
      return new cr.d(null);
    }
    
    public String getVersion()
    {
      return this.Sx;
    }
    
    public List<cr.e> oY()
    {
      return this.aqF;
    }
    
    public Map<String, List<cr.a>> oZ()
    {
      return this.aqG;
    }
    
    public String toString()
    {
      return "Rules: " + oY() + "  Macros: " + this.aqG;
    }
  }
  
  public static class d
  {
    private String Sx = "";
    private final List<cr.e> aqF = new ArrayList();
    private final Map<String, List<cr.a>> aqG = new HashMap();
    private int aqH = 0;
    
    public d a(cr.a parama)
    {
      String str = di.j((d.a)parama.oU().get(b.df.toString()));
      List localList = (List)this.aqG.get(str);
      Object localObject = localList;
      if (localList == null)
      {
        localObject = new ArrayList();
        this.aqG.put(str, localObject);
      }
      ((List)localObject).add(parama);
      return this;
    }
    
    public d a(cr.e parame)
    {
      this.aqF.add(parame);
      return this;
    }
    
    public d cM(String paramString)
    {
      this.Sx = paramString;
      return this;
    }
    
    public d fm(int paramInt)
    {
      this.aqH = paramInt;
      return this;
    }
    
    public cr.c pa()
    {
      return new cr.c(this.aqF, this.aqG, this.Sx, this.aqH, null);
    }
  }
  
  public static class e
  {
    private final List<cr.a> aqI;
    private final List<cr.a> aqJ;
    private final List<cr.a> aqK;
    private final List<cr.a> aqL;
    private final List<cr.a> aqM;
    private final List<cr.a> aqN;
    private final List<String> aqO;
    private final List<String> aqP;
    private final List<String> aqQ;
    private final List<String> aqR;
    
    private e(List<cr.a> paramList1, List<cr.a> paramList2, List<cr.a> paramList3, List<cr.a> paramList4, List<cr.a> paramList5, List<cr.a> paramList6, List<String> paramList7, List<String> paramList8, List<String> paramList9, List<String> paramList10)
    {
      this.aqI = Collections.unmodifiableList(paramList1);
      this.aqJ = Collections.unmodifiableList(paramList2);
      this.aqK = Collections.unmodifiableList(paramList3);
      this.aqL = Collections.unmodifiableList(paramList4);
      this.aqM = Collections.unmodifiableList(paramList5);
      this.aqN = Collections.unmodifiableList(paramList6);
      this.aqO = Collections.unmodifiableList(paramList7);
      this.aqP = Collections.unmodifiableList(paramList8);
      this.aqQ = Collections.unmodifiableList(paramList9);
      this.aqR = Collections.unmodifiableList(paramList10);
    }
    
    public static cr.f pb()
    {
      return new cr.f(null);
    }
    
    public List<cr.a> pc()
    {
      return this.aqI;
    }
    
    public List<cr.a> pd()
    {
      return this.aqJ;
    }
    
    public List<cr.a> pe()
    {
      return this.aqK;
    }
    
    public List<cr.a> pf()
    {
      return this.aqL;
    }
    
    public List<cr.a> pg()
    {
      return this.aqM;
    }
    
    public List<String> ph()
    {
      return this.aqO;
    }
    
    public List<String> pi()
    {
      return this.aqP;
    }
    
    public List<String> pj()
    {
      return this.aqQ;
    }
    
    public List<String> pk()
    {
      return this.aqR;
    }
    
    public List<cr.a> pl()
    {
      return this.aqN;
    }
    
    public String toString()
    {
      return "Positive predicates: " + pc() + "  Negative predicates: " + pd() + "  Add tags: " + pe() + "  Remove tags: " + pf() + "  Add macros: " + pg() + "  Remove macros: " + pl();
    }
  }
  
  public static class f
  {
    private final List<cr.a> aqI = new ArrayList();
    private final List<cr.a> aqJ = new ArrayList();
    private final List<cr.a> aqK = new ArrayList();
    private final List<cr.a> aqL = new ArrayList();
    private final List<cr.a> aqM = new ArrayList();
    private final List<cr.a> aqN = new ArrayList();
    private final List<String> aqO = new ArrayList();
    private final List<String> aqP = new ArrayList();
    private final List<String> aqQ = new ArrayList();
    private final List<String> aqR = new ArrayList();
    
    public f b(cr.a parama)
    {
      this.aqI.add(parama);
      return this;
    }
    
    public f c(cr.a parama)
    {
      this.aqJ.add(parama);
      return this;
    }
    
    public f cN(String paramString)
    {
      this.aqQ.add(paramString);
      return this;
    }
    
    public f cO(String paramString)
    {
      this.aqR.add(paramString);
      return this;
    }
    
    public f cP(String paramString)
    {
      this.aqO.add(paramString);
      return this;
    }
    
    public f cQ(String paramString)
    {
      this.aqP.add(paramString);
      return this;
    }
    
    public f d(cr.a parama)
    {
      this.aqK.add(parama);
      return this;
    }
    
    public f e(cr.a parama)
    {
      this.aqL.add(parama);
      return this;
    }
    
    public f f(cr.a parama)
    {
      this.aqM.add(parama);
      return this;
    }
    
    public f g(cr.a parama)
    {
      this.aqN.add(parama);
      return this;
    }
    
    public cr.e pm()
    {
      return new cr.e(this.aqI, this.aqJ, this.aqK, this.aqL, this.aqM, this.aqN, this.aqO, this.aqP, this.aqQ, this.aqR, null);
    }
  }
  
  public static class g
    extends Exception
  {
    public g(String paramString)
    {
      super();
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\tagmanager\cr.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */