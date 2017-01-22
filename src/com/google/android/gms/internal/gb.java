package com.google.android.gms.internal;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import com.google.android.gms.common.GooglePlayServicesUtil;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

@ez
public class gb
  implements cf.b
{
  private static final gb vJ = new gb();
  public static final String vK = vJ.vL;
  private Context mContext;
  private final Object mw = new Object();
  private am nu = null;
  private al nv = null;
  private ey nw = null;
  private gt qs;
  private boolean uH = true;
  private boolean uI = true;
  public final String vL = gj.jdMethod_do();
  private final gc vM = new gc(this.vL);
  private BigInteger vN = BigInteger.ONE;
  private final HashSet<ga> vO = new HashSet();
  private final HashMap<String, ge> vP = new HashMap();
  private boolean vQ = false;
  private boolean vR = false;
  private an vS = null;
  private LinkedList<Thread> vT = new LinkedList();
  private boolean vU = false;
  private Bundle vV = bn.bs();
  private String vW;
  
  public static Bundle a(Context paramContext, gd paramgd, String paramString)
  {
    return vJ.b(paramContext, paramgd, paramString);
  }
  
  public static void a(Context paramContext, gt paramgt)
  {
    vJ.b(paramContext, paramgt);
  }
  
  public static void a(Context paramContext, boolean paramBoolean)
  {
    vJ.b(paramContext, paramBoolean);
  }
  
  public static void b(HashSet<ga> paramHashSet)
  {
    vJ.c(paramHashSet);
  }
  
  public static Bundle bD()
  {
    return vJ.dg();
  }
  
  public static String c(int paramInt, String paramString)
  {
    return vJ.d(paramInt, paramString);
  }
  
  public static gb cU()
  {
    return vJ;
  }
  
  public static String cW()
  {
    return vJ.cX();
  }
  
  public static gc cY()
  {
    return vJ.cZ();
  }
  
  public static boolean da()
  {
    return vJ.db();
  }
  
  public static boolean dc()
  {
    return vJ.dd();
  }
  
  public static String de()
  {
    return vJ.df();
  }
  
  public static void e(Throwable paramThrowable)
  {
    vJ.f(paramThrowable);
  }
  
  public void a(Bundle paramBundle)
  {
    synchronized (this.mw)
    {
      this.vU = true;
      this.vV = paramBundle;
      if (!this.vT.isEmpty()) {
        ey.a(this.mContext, (Thread)this.vT.remove(0), this.qs);
      }
    }
  }
  
  public void a(ga paramga)
  {
    synchronized (this.mw)
    {
      this.vO.add(paramga);
      return;
    }
  }
  
  public void a(String paramString, ge paramge)
  {
    synchronized (this.mw)
    {
      this.vP.put(paramString, paramge);
      return;
    }
  }
  
  public void a(Thread paramThread)
  {
    synchronized (this.mw)
    {
      if (this.vU)
      {
        ey.a(this.mContext, paramThread, this.qs);
        return;
      }
      this.vT.add(paramThread);
    }
  }
  
  public Bundle b(Context paramContext, gd paramgd, String paramString)
  {
    Bundle localBundle1;
    Bundle localBundle2;
    synchronized (this.mw)
    {
      localBundle1 = new android/os/Bundle;
      localBundle1.<init>();
      localBundle1.putBundle("app", this.vM.b(paramContext, paramString));
      localBundle2 = new android/os/Bundle;
      localBundle2.<init>();
      paramString = this.vP.keySet().iterator();
      if (paramString.hasNext())
      {
        paramContext = (String)paramString.next();
        localBundle2.putBundle(paramContext, ((ge)this.vP.get(paramContext)).toBundle());
      }
    }
    localBundle1.putBundle("slots", localBundle2);
    paramString = new java/util/ArrayList;
    paramString.<init>();
    paramContext = this.vO.iterator();
    while (paramContext.hasNext()) {
      paramString.add(((ga)paramContext.next()).toBundle());
    }
    localBundle1.putParcelableArrayList("ads", paramString);
    paramgd.a(this.vO);
    this.vO.clear();
    return localBundle1;
  }
  
  public void b(Context paramContext, gt paramgt)
  {
    synchronized (this.mw)
    {
      if (!this.vR)
      {
        this.mContext = paramContext.getApplicationContext();
        this.qs = paramgt;
        this.uH = gh.o(paramContext);
        iv.H(paramContext);
        cf.a(paramContext, this);
        a(Thread.currentThread());
        this.vW = gj.c(paramContext, paramgt.wD);
        this.vR = true;
      }
      return;
    }
  }
  
  public void b(Context paramContext, boolean paramBoolean)
  {
    synchronized (this.mw)
    {
      if (paramBoolean != this.uH)
      {
        this.uH = paramBoolean;
        gh.a(paramContext, paramBoolean);
      }
      return;
    }
  }
  
  public void c(HashSet<ga> paramHashSet)
  {
    synchronized (this.mw)
    {
      this.vO.addAll(paramHashSet);
      return;
    }
  }
  
  public boolean cV()
  {
    synchronized (this.mw)
    {
      boolean bool = this.uI;
      return bool;
    }
  }
  
  public String cX()
  {
    synchronized (this.mw)
    {
      String str = this.vN.toString();
      this.vN = this.vN.add(BigInteger.ONE);
      return str;
    }
  }
  
  public gc cZ()
  {
    synchronized (this.mw)
    {
      gc localgc = this.vM;
      return localgc;
    }
  }
  
  public String d(int paramInt, String paramString)
  {
    Resources localResources;
    if (this.qs.wG)
    {
      localResources = this.mContext.getResources();
      if (localResources != null) {
        break label35;
      }
    }
    for (;;)
    {
      return paramString;
      localResources = GooglePlayServicesUtil.getRemoteResource(this.mContext);
      break;
      label35:
      paramString = localResources.getString(paramInt);
    }
  }
  
  public boolean db()
  {
    synchronized (this.mw)
    {
      boolean bool = this.vQ;
      this.vQ = true;
      return bool;
    }
  }
  
  public boolean dd()
  {
    synchronized (this.mw)
    {
      boolean bool = this.uH;
      return bool;
    }
  }
  
  public String df()
  {
    synchronized (this.mw)
    {
      String str = this.vW;
      return str;
    }
  }
  
  public Bundle dg()
  {
    synchronized (this.mw)
    {
      Bundle localBundle = this.vV;
      return localBundle;
    }
  }
  
  public void f(Throwable paramThrowable)
  {
    if (this.vR) {
      new ey(this.mContext, this.qs, null, null).b(paramThrowable);
    }
  }
  
  public an l(Context paramContext)
  {
    am localam = null;
    Object localObject1 = localam;
    if (bD().getBoolean(bn.pd.getKey(), false))
    {
      localObject1 = localam;
      if (kc.hE())
      {
        if (!cV()) {
          break label39;
        }
        localObject1 = localam;
      }
    }
    for (;;)
    {
      return (an)localObject1;
      label39:
      synchronized (this.mw)
      {
        if (this.nu != null) {
          break label100;
        }
        if (!(paramContext instanceof Activity)) {
          localObject1 = localam;
        }
      }
      localObject1 = new com/google/android/gms/internal/am;
      ((am)localObject1).<init>((Application)paramContext.getApplicationContext(), (Activity)paramContext);
      this.nu = ((am)localObject1);
      label100:
      if (this.nv == null)
      {
        paramContext = new com/google/android/gms/internal/al;
        paramContext.<init>();
        this.nv = paramContext;
      }
      if (this.vS == null)
      {
        localObject1 = new com/google/android/gms/internal/an;
        localam = this.nu;
        paramContext = this.nv;
        Bundle localBundle = this.vV;
        ey localey = new com/google/android/gms/internal/ey;
        localey.<init>(this.mContext, this.qs, null, null);
        ((an)localObject1).<init>(localam, paramContext, localBundle, localey);
        this.vS = ((an)localObject1);
      }
      this.vS.aV();
      localObject1 = this.vS;
    }
  }
  
  public void v(boolean paramBoolean)
  {
    synchronized (this.mw)
    {
      this.uI = paramBoolean;
      return;
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\internal\gb.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */