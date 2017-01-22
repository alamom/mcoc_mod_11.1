package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.d.a;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

class di
{
  private static final Object arU = null;
  private static Long arV = new Long(0L);
  private static Double arW = new Double(0.0D);
  private static dh arX = dh.z(0L);
  private static String arY = new String("");
  private static Boolean arZ = new Boolean(false);
  private static List<Object> asa = new ArrayList(0);
  private static Map<Object, Object> asb = new HashMap();
  private static d.a asc = u(arY);
  
  public static d.a cX(String paramString)
  {
    d.a locala = new d.a();
    locala.type = 5;
    locala.gA = paramString;
    return locala;
  }
  
  private static dh cY(String paramString)
  {
    try
    {
      dh localdh = dh.cW(paramString);
      paramString = localdh;
    }
    catch (NumberFormatException localNumberFormatException)
    {
      for (;;)
      {
        bh.T("Failed to convert '" + paramString + "' to a number.");
        paramString = arX;
      }
    }
    return paramString;
  }
  
  private static Long cZ(String paramString)
  {
    paramString = cY(paramString);
    if (paramString == arX) {}
    for (paramString = arV;; paramString = Long.valueOf(paramString.longValue())) {
      return paramString;
    }
  }
  
  private static Double da(String paramString)
  {
    paramString = cY(paramString);
    if (paramString == arX) {}
    for (paramString = arW;; paramString = Double.valueOf(paramString.doubleValue())) {
      return paramString;
    }
  }
  
  private static Boolean db(String paramString)
  {
    if ("true".equalsIgnoreCase(paramString)) {
      paramString = Boolean.TRUE;
    }
    for (;;)
    {
      return paramString;
      if ("false".equalsIgnoreCase(paramString)) {
        paramString = Boolean.FALSE;
      } else {
        paramString = arZ;
      }
    }
  }
  
  private static double getDouble(Object paramObject)
  {
    if ((paramObject instanceof Number)) {}
    for (double d = ((Number)paramObject).doubleValue();; d = 0.0D)
    {
      return d;
      bh.T("getDouble received non-Number");
    }
  }
  
  public static String j(d.a parama)
  {
    return p(o(parama));
  }
  
  public static dh k(d.a parama)
  {
    return q(o(parama));
  }
  
  public static Long l(d.a parama)
  {
    return r(o(parama));
  }
  
  public static Double m(d.a parama)
  {
    return s(o(parama));
  }
  
  public static Boolean n(d.a parama)
  {
    return t(o(parama));
  }
  
  public static Object o(d.a parama)
  {
    int k = 0;
    int j = 0;
    int i = 0;
    if (parama == null) {
      parama = arU;
    }
    for (;;)
    {
      return parama;
      Object localObject1;
      Object localObject2;
      switch (parama.type)
      {
      default: 
        bh.T("Failed to convert a value of type: " + parama.type);
        parama = arU;
        break;
      case 1: 
        parama = parama.gv;
        break;
      case 2: 
        localObject1 = new ArrayList(parama.gw.length);
        localObject2 = parama.gw;
        j = localObject2.length;
        for (;;)
        {
          if (i >= j) {
            break label172;
          }
          parama = o(localObject2[i]);
          if (parama == arU)
          {
            parama = arU;
            break;
          }
          ((ArrayList)localObject1).add(parama);
          i++;
        }
        parama = (d.a)localObject1;
        break;
      case 3: 
        if (parama.gx.length != parama.gy.length)
        {
          bh.T("Converting an invalid value to object: " + parama.toString());
          parama = arU;
        }
        else
        {
          localObject1 = new HashMap(parama.gy.length);
          for (i = k;; i++)
          {
            if (i >= parama.gx.length) {
              break label311;
            }
            localObject2 = o(parama.gx[i]);
            Object localObject3 = o(parama.gy[i]);
            if ((localObject2 == arU) || (localObject3 == arU))
            {
              parama = arU;
              break;
            }
            ((Map)localObject1).put(localObject2, localObject3);
          }
          parama = (d.a)localObject1;
        }
        break;
      case 4: 
        bh.T("Trying to convert a macro reference to object");
        parama = arU;
        break;
      case 5: 
        bh.T("Trying to convert a function id to object");
        parama = arU;
        break;
      case 6: 
        parama = Long.valueOf(parama.gB);
        break;
      case 7: 
        localObject1 = new StringBuffer();
        localObject2 = parama.gD;
        k = localObject2.length;
        for (i = j;; i++)
        {
          if (i >= k) {
            break label413;
          }
          parama = j(localObject2[i]);
          if (parama == arY)
          {
            parama = arU;
            break;
          }
          ((StringBuffer)localObject1).append(parama);
        }
        parama = ((StringBuffer)localObject1).toString();
        break;
      case 8: 
        label172:
        label311:
        label413:
        parama = Boolean.valueOf(parama.gC);
      }
    }
  }
  
  public static String p(Object paramObject)
  {
    if (paramObject == null) {}
    for (paramObject = arY;; paramObject = paramObject.toString()) {
      return (String)paramObject;
    }
  }
  
  public static Object pE()
  {
    return arU;
  }
  
  public static Long pF()
  {
    return arV;
  }
  
  public static Double pG()
  {
    return arW;
  }
  
  public static Boolean pH()
  {
    return arZ;
  }
  
  public static dh pI()
  {
    return arX;
  }
  
  public static String pJ()
  {
    return arY;
  }
  
  public static d.a pK()
  {
    return asc;
  }
  
  public static dh q(Object paramObject)
  {
    if ((paramObject instanceof dh)) {
      paramObject = (dh)paramObject;
    }
    for (;;)
    {
      return (dh)paramObject;
      if (w(paramObject)) {
        paramObject = dh.z(x(paramObject));
      } else if (v(paramObject)) {
        paramObject = dh.a(Double.valueOf(getDouble(paramObject)));
      } else {
        paramObject = cY(p(paramObject));
      }
    }
  }
  
  public static Long r(Object paramObject)
  {
    if (w(paramObject)) {}
    for (paramObject = Long.valueOf(x(paramObject));; paramObject = cZ(p(paramObject))) {
      return (Long)paramObject;
    }
  }
  
  public static Double s(Object paramObject)
  {
    if (v(paramObject)) {}
    for (paramObject = Double.valueOf(getDouble(paramObject));; paramObject = da(p(paramObject))) {
      return (Double)paramObject;
    }
  }
  
  public static Boolean t(Object paramObject)
  {
    if ((paramObject instanceof Boolean)) {}
    for (paramObject = (Boolean)paramObject;; paramObject = db(p(paramObject))) {
      return (Boolean)paramObject;
    }
  }
  
  public static d.a u(Object paramObject)
  {
    boolean bool = false;
    Object localObject1 = new d.a();
    if ((paramObject instanceof d.a))
    {
      paramObject = (d.a)paramObject;
      return (d.a)paramObject;
    }
    if ((paramObject instanceof String))
    {
      ((d.a)localObject1).type = 1;
      ((d.a)localObject1).gv = ((String)paramObject);
    }
    for (;;)
    {
      ((d.a)localObject1).gF = bool;
      paramObject = localObject1;
      break;
      Object localObject2;
      Object localObject3;
      if ((paramObject instanceof List))
      {
        ((d.a)localObject1).type = 2;
        localObject2 = (List)paramObject;
        paramObject = new ArrayList(((List)localObject2).size());
        localObject3 = ((List)localObject2).iterator();
        bool = false;
        if (((Iterator)localObject3).hasNext())
        {
          localObject2 = u(((Iterator)localObject3).next());
          if (localObject2 == asc)
          {
            paramObject = asc;
            break;
          }
          if ((bool) || (((d.a)localObject2).gF)) {}
          for (bool = true;; bool = false)
          {
            ((List)paramObject).add(localObject2);
            break;
          }
        }
        ((d.a)localObject1).gw = ((d.a[])((List)paramObject).toArray(new d.a[0]));
        continue;
      }
      if ((paramObject instanceof Map))
      {
        ((d.a)localObject1).type = 3;
        localObject3 = ((Map)paramObject).entrySet();
        paramObject = new ArrayList(((Set)localObject3).size());
        localObject2 = new ArrayList(((Set)localObject3).size());
        Iterator localIterator = ((Set)localObject3).iterator();
        bool = false;
        if (localIterator.hasNext())
        {
          Object localObject4 = (Map.Entry)localIterator.next();
          localObject3 = u(((Map.Entry)localObject4).getKey());
          localObject4 = u(((Map.Entry)localObject4).getValue());
          if ((localObject3 == asc) || (localObject4 == asc))
          {
            paramObject = asc;
            break;
          }
          if ((bool) || (((d.a)localObject3).gF) || (((d.a)localObject4).gF)) {}
          for (bool = true;; bool = false)
          {
            ((List)paramObject).add(localObject3);
            ((List)localObject2).add(localObject4);
            break;
          }
        }
        ((d.a)localObject1).gx = ((d.a[])((List)paramObject).toArray(new d.a[0]));
        ((d.a)localObject1).gy = ((d.a[])((List)localObject2).toArray(new d.a[0]));
        continue;
      }
      if (v(paramObject))
      {
        ((d.a)localObject1).type = 1;
        ((d.a)localObject1).gv = paramObject.toString();
      }
      else if (w(paramObject))
      {
        ((d.a)localObject1).type = 6;
        ((d.a)localObject1).gB = x(paramObject);
      }
      else
      {
        if (!(paramObject instanceof Boolean)) {
          break label471;
        }
        ((d.a)localObject1).type = 8;
        ((d.a)localObject1).gC = ((Boolean)paramObject).booleanValue();
      }
    }
    label471:
    localObject1 = new StringBuilder().append("Converting to Value from unknown object type: ");
    if (paramObject == null) {}
    for (paramObject = "null";; paramObject = paramObject.getClass().toString())
    {
      bh.T((String)paramObject);
      paramObject = asc;
      break;
    }
  }
  
  private static boolean v(Object paramObject)
  {
    if (((paramObject instanceof Double)) || ((paramObject instanceof Float)) || (((paramObject instanceof dh)) && (((dh)paramObject).pz()))) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  private static boolean w(Object paramObject)
  {
    if (((paramObject instanceof Byte)) || ((paramObject instanceof Short)) || ((paramObject instanceof Integer)) || ((paramObject instanceof Long)) || (((paramObject instanceof dh)) && (((dh)paramObject).pA()))) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  private static long x(Object paramObject)
  {
    if ((paramObject instanceof Number)) {}
    for (long l = ((Number)paramObject).longValue();; l = 0L)
    {
      return l;
      bh.T("getInt64 received non-Number");
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\tagmanager\di.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */