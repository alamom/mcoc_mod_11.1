package com.google.android.gms.internal;

import com.google.android.gms.wearable.Asset;
import com.google.android.gms.wearable.DataMap;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public final class pc
{
  private static int a(String paramString, pd.a.a[] paramArrayOfa)
  {
    int m = paramArrayOfa.length;
    int i = 0;
    int k = 14;
    if (i < m)
    {
      pd.a.a locala = paramArrayOfa[i];
      int j;
      if (k == 14) {
        if ((locala.type == 9) || (locala.type == 2) || (locala.type == 6)) {
          j = locala.type;
        }
      }
      do
      {
        do
        {
          i++;
          k = j;
          break;
          j = k;
        } while (locala.type == 14);
        throw new IllegalArgumentException("Unexpected TypedValue type: " + locala.type + " for key " + paramString);
        j = k;
      } while (locala.type == k);
      throw new IllegalArgumentException("The ArrayList elements should all be the same type, but ArrayList with key " + paramString + " contains items of type " + k + " and " + locala.type);
    }
    return k;
  }
  
  static int a(List<Asset> paramList, Asset paramAsset)
  {
    paramList.add(paramAsset);
    return paramList.size() - 1;
  }
  
  public static a a(DataMap paramDataMap)
  {
    pd localpd = new pd();
    ArrayList localArrayList = new ArrayList();
    localpd.awd = a(paramDataMap, localArrayList);
    return new a(localpd, localArrayList);
  }
  
  private static pd.a.a a(List<Asset> paramList, Object paramObject)
  {
    pd.a.a locala1 = new pd.a.a();
    if (paramObject == null)
    {
      locala1.type = 14;
      return locala1;
    }
    locala1.awh = new pd.a.a.a();
    if ((paramObject instanceof String))
    {
      locala1.type = 2;
      locala1.awh.awj = ((String)paramObject);
    }
    Object localObject2;
    Object localObject1;
    Object localObject3;
    int i;
    for (;;)
    {
      break;
      if ((paramObject instanceof Integer))
      {
        locala1.type = 6;
        locala1.awh.awn = ((Integer)paramObject).intValue();
      }
      else if ((paramObject instanceof Long))
      {
        locala1.type = 5;
        locala1.awh.awm = ((Long)paramObject).longValue();
      }
      else if ((paramObject instanceof Double))
      {
        locala1.type = 3;
        locala1.awh.awk = ((Double)paramObject).doubleValue();
      }
      else if ((paramObject instanceof Float))
      {
        locala1.type = 4;
        locala1.awh.awl = ((Float)paramObject).floatValue();
      }
      else if ((paramObject instanceof Boolean))
      {
        locala1.type = 8;
        locala1.awh.awp = ((Boolean)paramObject).booleanValue();
      }
      else if ((paramObject instanceof Byte))
      {
        locala1.type = 7;
        locala1.awh.awo = ((Byte)paramObject).byteValue();
      }
      else if ((paramObject instanceof byte[]))
      {
        locala1.type = 1;
        locala1.awh.awi = ((byte[])paramObject);
      }
      else if ((paramObject instanceof String[]))
      {
        locala1.type = 11;
        locala1.awh.aws = ((String[])paramObject);
      }
      else if ((paramObject instanceof long[]))
      {
        locala1.type = 12;
        locala1.awh.awt = ((long[])paramObject);
      }
      else if ((paramObject instanceof float[]))
      {
        locala1.type = 15;
        locala1.awh.awu = ((float[])paramObject);
      }
      else if ((paramObject instanceof Asset))
      {
        locala1.type = 13;
        locala1.awh.awv = a(paramList, (Asset)paramObject);
      }
      else
      {
        if (!(paramObject instanceof DataMap)) {
          break label539;
        }
        locala1.type = 9;
        paramObject = (DataMap)paramObject;
        localObject2 = ((DataMap)paramObject).keySet();
        localObject1 = new pd.a[((Set)localObject2).size()];
        localObject3 = ((Set)localObject2).iterator();
        for (i = 0; ((Iterator)localObject3).hasNext(); i++)
        {
          localObject2 = (String)((Iterator)localObject3).next();
          localObject1[i] = new pd.a();
          localObject1[i].name = ((String)localObject2);
          localObject1[i].awf = a(paramList, ((DataMap)paramObject).get((String)localObject2));
        }
        locala1.awh.awq = ((pd.a[])localObject1);
      }
    }
    label539:
    int j;
    label583:
    pd.a.a locala2;
    if ((paramObject instanceof ArrayList))
    {
      locala1.type = 10;
      localObject3 = (ArrayList)paramObject;
      localObject2 = new pd.a.a[((ArrayList)localObject3).size()];
      paramObject = null;
      int k = ((ArrayList)localObject3).size();
      j = 0;
      i = 14;
      if (j < k)
      {
        localObject1 = ((ArrayList)localObject3).get(j);
        locala2 = a(paramList, localObject1);
        if ((locala2.type != 14) && (locala2.type != 2) && (locala2.type != 6) && (locala2.type != 9)) {
          throw new IllegalArgumentException("The only ArrayList element types supported by DataBundleUtil are String, Integer, Bundle, and null, but this ArrayList contains a " + localObject1.getClass());
        }
        if ((i == 14) && (locala2.type != 14))
        {
          i = locala2.type;
          paramObject = localObject1;
        }
      }
    }
    for (;;)
    {
      localObject2[j] = locala2;
      j++;
      break label583;
      if (locala2.type != i)
      {
        throw new IllegalArgumentException("ArrayList elements must all be of the sameclass, but this one contains a " + paramObject.getClass() + " and a " + localObject1.getClass());
        locala1.awh.awr = ((pd.a.a[])localObject2);
        break;
        throw new RuntimeException("newFieldValueFromValue: unexpected value " + paramObject.getClass().getSimpleName());
      }
    }
  }
  
  public static DataMap a(a parama)
  {
    DataMap localDataMap = new DataMap();
    for (pd.a locala : parama.awb.awd) {
      a(parama.awc, localDataMap, locala.name, locala.awf);
    }
    return localDataMap;
  }
  
  private static ArrayList a(List<Asset> paramList, pd.a.a.a parama, int paramInt)
  {
    ArrayList localArrayList = new ArrayList(parama.awr.length);
    pd.a.a[] arrayOfa = parama.awr;
    int k = arrayOfa.length;
    int i = 0;
    if (i < k)
    {
      Object localObject1 = arrayOfa[i];
      if (((pd.a.a)localObject1).type == 14) {
        localArrayList.add(null);
      }
      for (;;)
      {
        i++;
        break;
        if (paramInt == 9)
        {
          parama = new DataMap();
          for (Object localObject2 : ((pd.a.a)localObject1).awh.awq) {
            a(paramList, parama, ((pd.a)localObject2).name, ((pd.a)localObject2).awf);
          }
          localArrayList.add(parama);
        }
        else if (paramInt == 2)
        {
          localArrayList.add(((pd.a.a)localObject1).awh.awj);
        }
        else
        {
          if (paramInt != 6) {
            break label187;
          }
          localArrayList.add(Integer.valueOf(((pd.a.a)localObject1).awh.awn));
        }
      }
      label187:
      throw new IllegalArgumentException("Unexpected typeOfArrayList: " + paramInt);
    }
    return localArrayList;
  }
  
  private static void a(List<Asset> paramList, DataMap paramDataMap, String paramString, pd.a.a parama)
  {
    int i = parama.type;
    if (i == 14) {
      paramDataMap.putString(paramString, null);
    }
    for (;;)
    {
      return;
      pd.a.a.a locala = parama.awh;
      if (i == 1)
      {
        paramDataMap.putByteArray(paramString, locala.awi);
      }
      else if (i == 11)
      {
        paramDataMap.putStringArray(paramString, locala.aws);
      }
      else if (i == 12)
      {
        paramDataMap.putLongArray(paramString, locala.awt);
      }
      else if (i == 15)
      {
        paramDataMap.putFloatArray(paramString, locala.awu);
      }
      else if (i == 2)
      {
        paramDataMap.putString(paramString, locala.awj);
      }
      else if (i == 3)
      {
        paramDataMap.putDouble(paramString, locala.awk);
      }
      else if (i == 4)
      {
        paramDataMap.putFloat(paramString, locala.awl);
      }
      else if (i == 5)
      {
        paramDataMap.putLong(paramString, locala.awm);
      }
      else if (i == 6)
      {
        paramDataMap.putInt(paramString, locala.awn);
      }
      else if (i == 7)
      {
        paramDataMap.putByte(paramString, (byte)locala.awo);
      }
      else if (i == 8)
      {
        paramDataMap.putBoolean(paramString, locala.awp);
      }
      else if (i == 13)
      {
        if (paramList == null) {
          throw new RuntimeException("populateBundle: unexpected type for: " + paramString);
        }
        paramDataMap.putAsset(paramString, (Asset)paramList.get((int)locala.awv));
      }
      else if (i == 9)
      {
        parama = new DataMap();
        for (locala : locala.awq) {
          a(paramList, parama, locala.name, locala.awf);
        }
        paramDataMap.putDataMap(paramString, parama);
      }
      else
      {
        if (i != 10) {
          break label497;
        }
        i = a(paramString, locala.awr);
        paramList = a(paramList, locala, i);
        if (i == 14)
        {
          paramDataMap.putStringArrayList(paramString, paramList);
        }
        else if (i == 9)
        {
          paramDataMap.putDataMapArrayList(paramString, paramList);
        }
        else if (i == 2)
        {
          paramDataMap.putStringArrayList(paramString, paramList);
        }
        else
        {
          if (i != 6) {
            break;
          }
          paramDataMap.putIntegerArrayList(paramString, paramList);
        }
      }
    }
    throw new IllegalStateException("Unexpected typeOfArrayList: " + i);
    label497:
    throw new RuntimeException("populateBundle: unexpected type " + i);
  }
  
  private static pd.a[] a(DataMap paramDataMap, List<Asset> paramList)
  {
    Object localObject1 = paramDataMap.keySet();
    pd.a[] arrayOfa = new pd.a[((Set)localObject1).size()];
    Iterator localIterator = ((Set)localObject1).iterator();
    for (int i = 0; localIterator.hasNext(); i++)
    {
      localObject1 = (String)localIterator.next();
      Object localObject2 = paramDataMap.get((String)localObject1);
      arrayOfa[i] = new pd.a();
      arrayOfa[i].name = ((String)localObject1);
      arrayOfa[i].awf = a(paramList, localObject2);
    }
    return arrayOfa;
  }
  
  public static class a
  {
    public final pd awb;
    public final List<Asset> awc;
    
    public a(pd parampd, List<Asset> paramList)
    {
      this.awb = parampd;
      this.awc = paramList;
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\internal\pc.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */