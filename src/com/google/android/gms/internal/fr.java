package com.google.android.gms.internal;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.text.TextUtils;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

@ez
public final class fr
  extends fm.a
{
  private static final Object uf = new Object();
  private static fr ug;
  private final Context mContext;
  private final fx uh;
  private final ci ui;
  private final bm uj;
  
  fr(Context paramContext, bm parambm, ci paramci, fx paramfx)
  {
    this.mContext = paramContext;
    this.uh = paramfx;
    this.ui = paramci;
    this.uj = parambm;
  }
  
  private static gw.a I(String paramString)
  {
    new gw.a()
    {
      public void a(gv paramAnonymousgv)
      {
        String str = String.format("javascript:%s(%s);", new Object[] { "AFMA_buildAdURL", this.uo });
        gs.V("About to execute: " + str);
        paramAnonymousgv.loadUrl(str);
      }
    };
  }
  
  private static fk a(Context paramContext, final bm parambm, final ci paramci, fx paramfx, final fi paramfi)
  {
    gs.S("Starting ad request from service.");
    paramci.init();
    fw localfw = new fw(paramContext);
    if (localfw.vd == -1)
    {
      gs.S("Device is offline.");
      paramContext = new fk(2);
    }
    for (;;)
    {
      return paramContext;
      final ft localft = new ft(paramfi.applicationInfo.packageName);
      if (paramfi.tx.extras != null)
      {
        localObject = paramfi.tx.extras.getString("_ad");
        if (localObject != null)
        {
          paramContext = fs.a(paramContext, paramfi, (String)localObject);
          continue;
        }
      }
      Object localObject = paramci.a(250L);
      paramci = parambm.bp();
      parambm = fs.a(paramfi, localfw, (Location)localObject, parambm.bq(), parambm.br());
      if (parambm == null)
      {
        paramContext = new fk(0);
      }
      else
      {
        parambm = I(parambm);
        gr.wC.post(new Runnable()
        {
          public void run()
          {
            gv localgv = gv.a(this.mV, new ay(), false, false, null, paramfi.lD);
            localgv.setWillNotDraw(true);
            localft.b(localgv);
            gw localgw = localgv.du();
            localgw.a("/invalidRequest", localft.us);
            localgw.a("/loadAdURL", localft.ut);
            localgw.a("/log", bx.pG);
            localgw.a(parambm);
            gs.S("Loading the JS library.");
            localgv.loadUrl(paramci);
          }
        });
        try
        {
          paramci = (fv)localft.cK().get(10L, TimeUnit.SECONDS);
          if (paramci != null) {
            break label231;
          }
          paramContext = new fk(0);
        }
        catch (Exception paramContext)
        {
          paramContext = new fk(0);
        }
        continue;
        label231:
        if (paramci.getErrorCode() != -2)
        {
          paramContext = new fk(paramci.getErrorCode());
        }
        else
        {
          parambm = null;
          if (paramci.cN()) {
            parambm = paramfx.K(paramfi.ty.packageName);
          }
          paramContext = a(paramContext, paramfi.lD.wD, paramci.getUrl(), parambm, paramci);
        }
      }
    }
  }
  
  public static fk a(Context paramContext, String paramString1, String paramString2, String paramString3, fv paramfv)
  {
    try
    {
      localfu = new com/google/android/gms/internal/fu;
      localfu.<init>();
      localObject1 = new java/lang/StringBuilder;
      ((StringBuilder)localObject1).<init>();
      gs.S("AdRequestServiceImpl: Sending request: " + paramString2);
      localObject1 = new java/net/URL;
      ((URL)localObject1).<init>(paramString2);
      l = SystemClock.elapsedRealtime();
      paramString2 = (String)localObject1;
      i = 0;
    }
    catch (IOException paramContext)
    {
      for (;;)
      {
        try
        {
          fu localfu;
          long l;
          int i;
          gj.a(paramContext, paramString1, false, (HttpURLConnection)localObject1);
          if (!TextUtils.isEmpty(paramString3)) {
            ((HttpURLConnection)localObject1).addRequestProperty("x-afma-drt-cookie", paramString3);
          }
          Object localObject3;
          if ((paramfv != null) && (!TextUtils.isEmpty(paramfv.cM())))
          {
            ((HttpURLConnection)localObject1).setDoOutput(true);
            localObject3 = paramfv.cM().getBytes();
            ((HttpURLConnection)localObject1).setFixedLengthStreamingMode(localObject3.length);
            localObject2 = new java/io/BufferedOutputStream;
            ((BufferedOutputStream)localObject2).<init>(((HttpURLConnection)localObject1).getOutputStream());
            ((BufferedOutputStream)localObject2).write((byte[])localObject3);
            ((BufferedOutputStream)localObject2).close();
          }
          int j = ((HttpURLConnection)localObject1).getResponseCode();
          Object localObject2 = ((HttpURLConnection)localObject1).getHeaderFields();
          if ((j >= 200) && (j < 300))
          {
            paramContext = paramString2.toString();
            paramString1 = new java/io/InputStreamReader;
            paramString1.<init>(((HttpURLConnection)localObject1).getInputStream());
            paramString1 = gj.a(paramString1);
            a(paramContext, (Map)localObject2, paramString1, j);
            localfu.a(paramContext, (Map)localObject2, paramString1);
            paramContext = localfu.i(l);
            return paramContext;
          }
          a(paramString2.toString(), (Map)localObject2, null, j);
          if ((j >= 300) && (j < 400))
          {
            localObject3 = ((HttpURLConnection)localObject1).getHeaderField("Location");
            if (TextUtils.isEmpty((CharSequence)localObject3))
            {
              gs.W("No location header to follow redirect.");
              paramContext = new fk(0);
              ((HttpURLConnection)localObject1).disconnect();
              continue;
            }
            paramString2 = new java/net/URL;
            paramString2.<init>((String)localObject3);
            i++;
            if (i > 5)
            {
              gs.W("Too many redirects.");
              paramContext = new fk(0);
              ((HttpURLConnection)localObject1).disconnect();
            }
          }
          else
          {
            paramContext = new java/lang/StringBuilder;
            paramContext.<init>();
            gs.W("Received error HTTP response code: " + j);
            paramContext = new fk(0);
            ((HttpURLConnection)localObject1).disconnect();
            continue;
          }
          localfu.e((Map)localObject2);
          ((HttpURLConnection)localObject1).disconnect();
          continue;
        }
        finally
        {
          Object localObject1;
          ((HttpURLConnection)localObject1).disconnect();
        }
        paramContext = paramContext;
        gs.W("Error while connecting to ad server: " + paramContext.getMessage());
        paramContext = new fk(2);
      }
    }
    localObject1 = (HttpURLConnection)paramString2.openConnection();
  }
  
  public static fr a(Context paramContext, bm parambm, ci paramci, fx paramfx)
  {
    synchronized (uf)
    {
      if (ug == null)
      {
        fr localfr = new com/google/android/gms/internal/fr;
        localfr.<init>(paramContext.getApplicationContext(), parambm, paramci, paramfx);
        ug = localfr;
      }
      paramContext = ug;
      return paramContext;
    }
  }
  
  private static void a(String paramString1, Map<String, List<String>> paramMap, String paramString2, int paramInt)
  {
    if (gs.u(2))
    {
      gs.V("Http Response: {\n  URL:\n    " + paramString1 + "\n  Headers:");
      if (paramMap != null)
      {
        paramString1 = paramMap.keySet().iterator();
        while (paramString1.hasNext())
        {
          String str = (String)paramString1.next();
          gs.V("    " + str + ":");
          Iterator localIterator = ((List)paramMap.get(str)).iterator();
          while (localIterator.hasNext())
          {
            str = (String)localIterator.next();
            gs.V("      " + str);
          }
        }
      }
      gs.V("  Body:");
      if (paramString2 != null) {
        for (int i = 0; i < Math.min(paramString2.length(), 100000); i += 1000) {
          gs.V(paramString2.substring(i, Math.min(paramString2.length(), i + 1000)));
        }
      }
      gs.V("    null");
      gs.V("  Response Code:\n    " + paramInt + "\n}");
    }
  }
  
  public fk b(fi paramfi)
  {
    return a(this.mContext, this.uj, this.ui, this.uh, paramfi);
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\internal\fr.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */