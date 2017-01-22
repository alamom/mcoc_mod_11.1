package com.google.android.gms.internal;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class pi<M extends ph<M>, T>
{
  protected final Class<T> awK;
  protected final boolean awL;
  protected final int tag;
  protected final int type;
  
  private pi(int paramInt1, Class<T> paramClass, int paramInt2, boolean paramBoolean)
  {
    this.type = paramInt1;
    this.awK = paramClass;
    this.tag = paramInt2;
    this.awL = paramBoolean;
  }
  
  public static <M extends ph<M>, T extends pn> pi<M, T> a(int paramInt1, Class<T> paramClass, int paramInt2)
  {
    return new pi(paramInt1, paramClass, paramInt2, false);
  }
  
  private T m(List<pp> paramList)
  {
    int j = 0;
    ArrayList localArrayList = new ArrayList();
    for (int i = 0; i < paramList.size(); i++)
    {
      localObject = (pp)paramList.get(i);
      if (((pp)localObject).awV.length != 0) {
        a((pp)localObject, localArrayList);
      }
    }
    int k = localArrayList.size();
    if (k == 0)
    {
      paramList = null;
      return paramList;
    }
    Object localObject = this.awK.cast(Array.newInstance(this.awK.getComponentType(), k));
    for (i = j;; i++)
    {
      paramList = (List<pp>)localObject;
      if (i >= k) {
        break;
      }
      Array.set(localObject, i, localArrayList.get(i));
    }
  }
  
  private T n(List<pp> paramList)
  {
    if (paramList.isEmpty()) {}
    for (paramList = null;; paramList = this.awK.cast(u(pf.p(paramList.awV))))
    {
      return paramList;
      paramList = (pp)paramList.get(paramList.size() - 1);
    }
  }
  
  int A(Object paramObject)
  {
    if (this.awL) {}
    for (int i = B(paramObject);; i = C(paramObject)) {
      return i;
    }
  }
  
  protected int B(Object paramObject)
  {
    int k = 0;
    int m = Array.getLength(paramObject);
    int i = 0;
    while (i < m)
    {
      int j = k;
      if (Array.get(paramObject, i) != null) {
        j = k + C(Array.get(paramObject, i));
      }
      i++;
      k = j;
    }
    return k;
  }
  
  protected int C(Object paramObject)
  {
    int i = pq.gI(this.tag);
    switch (this.type)
    {
    default: 
      throw new IllegalArgumentException("Unknown type " + this.type);
    }
    for (i = pg.b(i, (pn)paramObject);; i = pg.c(i, (pn)paramObject)) {
      return i;
    }
  }
  
  protected void a(pp parampp, List<Object> paramList)
  {
    paramList.add(u(pf.p(parampp.awV)));
  }
  
  void a(Object paramObject, pg parampg)
    throws IOException
  {
    if (this.awL) {
      c(paramObject, parampg);
    }
    for (;;)
    {
      return;
      b(paramObject, parampg);
    }
  }
  
  protected void b(Object paramObject, pg parampg)
  {
    try
    {
      parampg.gA(this.tag);
      switch (this.type)
      {
      default: 
        parampg = new java/lang/IllegalArgumentException;
        paramObject = new java/lang/StringBuilder;
        ((StringBuilder)paramObject).<init>();
        parampg.<init>("Unknown type " + this.type);
        throw parampg;
      }
    }
    catch (IOException paramObject)
    {
      throw new IllegalStateException((Throwable)paramObject);
    }
    paramObject = (pn)paramObject;
    int i = pq.gI(this.tag);
    parampg.b((pn)paramObject);
    parampg.w(i, 4);
    for (;;)
    {
      return;
      parampg.c((pn)paramObject);
    }
  }
  
  protected void c(Object paramObject, pg parampg)
  {
    int j = Array.getLength(paramObject);
    for (int i = 0; i < j; i++)
    {
      Object localObject = Array.get(paramObject, i);
      if (localObject != null) {
        b(localObject, parampg);
      }
    }
  }
  
  final T l(List<pp> paramList)
  {
    if (paramList == null) {
      paramList = null;
    }
    for (;;)
    {
      return paramList;
      if (this.awL) {
        paramList = m(paramList);
      } else {
        paramList = n(paramList);
      }
    }
  }
  
  protected Object u(pf parampf)
  {
    Class localClass;
    if (this.awL) {
      localClass = this.awK.getComponentType();
    }
    try
    {
      switch (this.type)
      {
      default: 
        localObject = new java/lang/IllegalArgumentException;
        parampf = new java/lang/StringBuilder;
        parampf.<init>();
        ((IllegalArgumentException)localObject).<init>("Unknown type " + this.type);
        throw ((Throwable)localObject);
      }
    }
    catch (InstantiationException parampf)
    {
      for (;;)
      {
        throw new IllegalArgumentException("Error creating instance of class " + localClass, parampf);
        localClass = this.awK;
      }
      Object localObject = (pn)localClass.newInstance();
      parampf.a((pn)localObject, pq.gI(this.tag));
      for (parampf = (pf)localObject;; parampf = (pf)localObject)
      {
        return parampf;
        localObject = (pn)localClass.newInstance();
        parampf.a((pn)localObject);
      }
    }
    catch (IllegalAccessException parampf)
    {
      throw new IllegalArgumentException("Error creating instance of class " + localClass, parampf);
    }
    catch (IOException parampf)
    {
      throw new IllegalArgumentException("Error reading extension field", parampf);
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\internal\pi.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */