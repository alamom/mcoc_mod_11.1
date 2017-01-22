package com.google.android.gms.tagmanager;

import android.content.Context;
import com.google.android.gms.internal.c.f;
import com.google.android.gms.internal.c.i;
import com.google.android.gms.internal.c.j;
import com.google.android.gms.internal.d.a;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Container
{
  private final String aoc;
  private final DataLayer aod;
  private ct aoe;
  private Map<String, FunctionCallMacroCallback> aof = new HashMap();
  private Map<String, FunctionCallTagCallback> aog = new HashMap();
  private volatile long aoh;
  private volatile String aoi = "";
  private final Context mContext;
  
  Container(Context paramContext, DataLayer paramDataLayer, String paramString, long paramLong, c.j paramj)
  {
    this.mContext = paramContext;
    this.aod = paramDataLayer;
    this.aoc = paramString;
    this.aoh = paramLong;
    a(paramj.gs);
    if (paramj.gr != null) {
      a(paramj.gr);
    }
  }
  
  Container(Context paramContext, DataLayer paramDataLayer, String paramString, long paramLong, cr.c paramc)
  {
    this.mContext = paramContext;
    this.aod = paramDataLayer;
    this.aoc = paramString;
    this.aoh = paramLong;
    a(paramc);
  }
  
  private void a(c.f paramf)
  {
    if (paramf == null) {
      throw new NullPointerException();
    }
    try
    {
      cr.c localc = cr.b(paramf);
      a(localc);
      return;
    }
    catch (cr.g localg)
    {
      for (;;)
      {
        bh.T("Not loading resource: " + paramf + " because it is invalid: " + localg.toString());
      }
    }
  }
  
  private void a(cr.c paramc)
  {
    this.aoi = paramc.getVersion();
    ag localag = cq(this.aoi);
    a(new ct(this.mContext, paramc, this.aod, new a(null), new b(null), localag));
  }
  
  private void a(ct paramct)
  {
    try
    {
      this.aoe = paramct;
      return;
    }
    finally
    {
      paramct = finally;
      throw paramct;
    }
  }
  
  private void a(c.i[] paramArrayOfi)
  {
    ArrayList localArrayList = new ArrayList();
    int j = paramArrayOfi.length;
    for (int i = 0; i < j; i++) {
      localArrayList.add(paramArrayOfi[i]);
    }
    nT().k(localArrayList);
  }
  
  private ct nT()
  {
    try
    {
      ct localct = this.aoe;
      return localct;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  FunctionCallMacroCallback cn(String paramString)
  {
    synchronized (this.aof)
    {
      paramString = (FunctionCallMacroCallback)this.aof.get(paramString);
      return paramString;
    }
  }
  
  FunctionCallTagCallback co(String paramString)
  {
    synchronized (this.aog)
    {
      paramString = (FunctionCallTagCallback)this.aog.get(paramString);
      return paramString;
    }
  }
  
  void cp(String paramString)
  {
    nT().cp(paramString);
  }
  
  ag cq(String paramString)
  {
    if (ce.oJ().oK().equals(ce.a.aqj)) {}
    return new br();
  }
  
  public boolean getBoolean(String paramString)
  {
    ct localct = nT();
    boolean bool;
    if (localct == null)
    {
      bh.T("getBoolean called for closed container.");
      bool = di.pH().booleanValue();
    }
    for (;;)
    {
      return bool;
      try
      {
        bool = di.n((d.a)localct.cR(paramString).getObject()).booleanValue();
      }
      catch (Exception paramString)
      {
        bh.T("Calling getBoolean() threw an exception: " + paramString.getMessage() + " Returning default value.");
        bool = di.pH().booleanValue();
      }
    }
  }
  
  public String getContainerId()
  {
    return this.aoc;
  }
  
  public double getDouble(String paramString)
  {
    ct localct = nT();
    double d;
    if (localct == null)
    {
      bh.T("getDouble called for closed container.");
      d = di.pG().doubleValue();
    }
    for (;;)
    {
      return d;
      try
      {
        d = di.m((d.a)localct.cR(paramString).getObject()).doubleValue();
      }
      catch (Exception paramString)
      {
        bh.T("Calling getDouble() threw an exception: " + paramString.getMessage() + " Returning default value.");
        d = di.pG().doubleValue();
      }
    }
  }
  
  public long getLastRefreshTime()
  {
    return this.aoh;
  }
  
  public long getLong(String paramString)
  {
    ct localct = nT();
    long l;
    if (localct == null)
    {
      bh.T("getLong called for closed container.");
      l = di.pF().longValue();
    }
    for (;;)
    {
      return l;
      try
      {
        l = di.l((d.a)localct.cR(paramString).getObject()).longValue();
      }
      catch (Exception paramString)
      {
        bh.T("Calling getLong() threw an exception: " + paramString.getMessage() + " Returning default value.");
        l = di.pF().longValue();
      }
    }
  }
  
  public String getString(String paramString)
  {
    ct localct = nT();
    if (localct == null)
    {
      bh.T("getString called for closed container.");
      paramString = di.pJ();
    }
    for (;;)
    {
      return paramString;
      try
      {
        paramString = di.j((d.a)localct.cR(paramString).getObject());
      }
      catch (Exception paramString)
      {
        bh.T("Calling getString() threw an exception: " + paramString.getMessage() + " Returning default value.");
        paramString = di.pJ();
      }
    }
  }
  
  public boolean isDefault()
  {
    if (getLastRefreshTime() == 0L) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  String nS()
  {
    return this.aoi;
  }
  
  public void registerFunctionCallMacroCallback(String paramString, FunctionCallMacroCallback paramFunctionCallMacroCallback)
  {
    if (paramFunctionCallMacroCallback == null) {
      throw new NullPointerException("Macro handler must be non-null");
    }
    synchronized (this.aof)
    {
      this.aof.put(paramString, paramFunctionCallMacroCallback);
      return;
    }
  }
  
  public void registerFunctionCallTagCallback(String paramString, FunctionCallTagCallback paramFunctionCallTagCallback)
  {
    if (paramFunctionCallTagCallback == null) {
      throw new NullPointerException("Tag callback must be non-null");
    }
    synchronized (this.aog)
    {
      this.aog.put(paramString, paramFunctionCallTagCallback);
      return;
    }
  }
  
  void release()
  {
    this.aoe = null;
  }
  
  public void unregisterFunctionCallMacroCallback(String paramString)
  {
    synchronized (this.aof)
    {
      this.aof.remove(paramString);
      return;
    }
  }
  
  public void unregisterFunctionCallTagCallback(String paramString)
  {
    synchronized (this.aog)
    {
      this.aog.remove(paramString);
      return;
    }
  }
  
  public static abstract interface FunctionCallMacroCallback
  {
    public abstract Object getValue(String paramString, Map<String, Object> paramMap);
  }
  
  public static abstract interface FunctionCallTagCallback
  {
    public abstract void execute(String paramString, Map<String, Object> paramMap);
  }
  
  private class a
    implements s.a
  {
    private a() {}
    
    public Object b(String paramString, Map<String, Object> paramMap)
    {
      Container.FunctionCallMacroCallback localFunctionCallMacroCallback = Container.this.cn(paramString);
      if (localFunctionCallMacroCallback == null) {}
      for (paramString = null;; paramString = localFunctionCallMacroCallback.getValue(paramString, paramMap)) {
        return paramString;
      }
    }
  }
  
  private class b
    implements s.a
  {
    private b() {}
    
    public Object b(String paramString, Map<String, Object> paramMap)
    {
      Container.FunctionCallTagCallback localFunctionCallTagCallback = Container.this.co(paramString);
      if (localFunctionCallTagCallback != null) {
        localFunctionCallTagCallback.execute(paramString, paramMap);
      }
      return di.pJ();
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\tagmanager\Container.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */