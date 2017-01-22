package com.google.android.gms.internal;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.KeyguardManager;
import android.content.Context;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.Process;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.webkit.ValueCallback;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.TextView;
import java.util.Iterator;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

@ez
public class an
  extends Thread
{
  private boolean mStarted = false;
  private final Object mw;
  private final int nf;
  private final int nh;
  private boolean ns = false;
  private boolean nt = false;
  private final am nu;
  private final al nv;
  private final ey nw;
  private final int nx;
  private final int ny;
  private final int nz;
  
  public an(am paramam, al paramal, Bundle paramBundle, ey paramey)
  {
    this.nu = paramam;
    this.nv = paramal;
    this.nw = paramey;
    this.mw = new Object();
    this.nf = paramBundle.getInt(bn.pe.getKey());
    this.ny = paramBundle.getInt(bn.pf.getKey());
    this.nh = paramBundle.getInt(bn.pg.getKey());
    this.nz = paramBundle.getInt(bn.ph.getKey());
    this.nx = paramBundle.getInt(bn.pi.getKey(), 10);
    setName("ContentFetchTask");
  }
  
  private void a(Activity paramActivity)
  {
    if (paramActivity == null) {}
    for (;;)
    {
      return;
      Object localObject2 = null;
      Object localObject1 = localObject2;
      if (paramActivity.getWindow() != null)
      {
        localObject1 = localObject2;
        if (paramActivity.getWindow().getDecorView() != null) {
          localObject1 = paramActivity.getWindow().getDecorView().findViewById(16908290);
        }
      }
      if (localObject1 != null) {
        g((View)localObject1);
      }
    }
  }
  
  private boolean a(final WebView paramWebView, final ak paramak)
  {
    if (!kc.hI()) {}
    for (boolean bool = false;; bool = true)
    {
      return bool;
      paramak.aR();
      paramWebView.post(new Runnable()
      {
        ValueCallback<String> nC = new ValueCallback()
        {
          public void k(String paramAnonymous2String)
          {
            an.this.a(an.2.this.nD, an.2.this.nE, paramAnonymous2String);
          }
        };
        
        public void run()
        {
          if (paramWebView.getSettings().getJavaScriptEnabled()) {
            paramWebView.evaluateJavascript("(function() { return  {text:document.body.innerText}})();", this.nC);
          }
        }
      });
    }
  }
  
  private boolean aW()
  {
    for (;;)
    {
      try
      {
        localObject1 = this.nu.getContext();
        if (localObject1 != null) {
          continue;
        }
        bool = false;
      }
      catch (Throwable localThrowable)
      {
        Object localObject1;
        Object localObject2;
        KeyguardManager localKeyguardManager;
        ActivityManager.RunningAppProcessInfo localRunningAppProcessInfo;
        boolean bool = false;
        continue;
      }
      return bool;
      localObject2 = (ActivityManager)((Context)localObject1).getSystemService("activity");
      localKeyguardManager = (KeyguardManager)((Context)localObject1).getSystemService("keyguard");
      localObject1 = (PowerManager)((Context)localObject1).getSystemService("power");
      if ((localObject2 == null) || (localKeyguardManager == null) || (localObject1 == null))
      {
        bool = false;
      }
      else
      {
        localObject2 = ((ActivityManager)localObject2).getRunningAppProcesses();
        if (localObject2 == null)
        {
          bool = false;
        }
        else
        {
          localObject2 = ((List)localObject2).iterator();
          if (((Iterator)localObject2).hasNext())
          {
            localRunningAppProcessInfo = (ActivityManager.RunningAppProcessInfo)((Iterator)localObject2).next();
            if (Process.myPid() != localRunningAppProcessInfo.pid) {
              continue;
            }
            if ((localRunningAppProcessInfo.importance == 100) && (!localKeyguardManager.inKeyguardRestrictedInputMode()))
            {
              bool = ((PowerManager)localObject1).isScreenOn();
              if (bool)
              {
                bool = true;
                continue;
              }
            }
          }
          bool = false;
        }
      }
    }
  }
  
  a a(View paramView, ak paramak)
  {
    int j = 0;
    if (paramView == null) {
      paramView = new a(0, 0);
    }
    for (;;)
    {
      return paramView;
      if (((paramView instanceof TextView)) && (!(paramView instanceof EditText)))
      {
        paramak.i(((TextView)paramView).getText().toString());
        paramView = new a(1, 0);
      }
      else if (((paramView instanceof WebView)) && (!(paramView instanceof gv)))
      {
        paramak.aR();
        if (a((WebView)paramView, paramak)) {
          paramView = new a(0, 1);
        } else {
          paramView = new a(0, 0);
        }
      }
      else if ((paramView instanceof ViewGroup))
      {
        paramView = (ViewGroup)paramView;
        int i = 0;
        int k = 0;
        while (j < paramView.getChildCount())
        {
          a locala = a(paramView.getChildAt(j), paramak);
          k += locala.nG;
          i += locala.nH;
          j++;
        }
        paramView = new a(k, i);
      }
      else
      {
        paramView = new a(0, 0);
      }
    }
  }
  
  void a(ak paramak, WebView paramWebView, String paramString)
  {
    paramak.aQ();
    for (;;)
    {
      try
      {
        if (!TextUtils.isEmpty(paramString))
        {
          Object localObject = new org/json/JSONObject;
          ((JSONObject)localObject).<init>(paramString);
          paramString = ((JSONObject)localObject).optString("text");
          if (!TextUtils.isEmpty(paramWebView.getTitle()))
          {
            localObject = new java/lang/StringBuilder;
            ((StringBuilder)localObject).<init>();
            paramak.h(paramWebView.getTitle() + "\n" + paramString);
          }
        }
        else
        {
          if (paramak.aN()) {
            this.nv.b(paramak);
          }
          return;
        }
      }
      catch (JSONException paramak)
      {
        gs.S("Json string may be malformed.");
        continue;
      }
      catch (Throwable paramak)
      {
        gs.a("Failed to get webview content.", paramak);
        this.nw.b(paramak);
        continue;
      }
      paramak.h(paramString);
    }
  }
  
  public void aV()
  {
    synchronized (this.mw)
    {
      if (this.mStarted)
      {
        gs.S("Content hash thread already started, quiting...");
        return;
      }
      this.mStarted = true;
      start();
    }
  }
  
  public ak aX()
  {
    return this.nv.aU();
  }
  
  public void aY()
  {
    synchronized (this.mw)
    {
      this.ns = true;
      StringBuilder localStringBuilder = new java/lang/StringBuilder;
      localStringBuilder.<init>();
      gs.S("ContentFetchThread: paused, mPause = " + this.ns);
      return;
    }
  }
  
  public boolean aZ()
  {
    return this.ns;
  }
  
  boolean g(final View paramView)
  {
    if (paramView == null) {}
    for (boolean bool = false;; bool = true)
    {
      return bool;
      paramView.post(new Runnable()
      {
        public void run()
        {
          an.this.h(paramView);
        }
      });
    }
  }
  
  void h(View paramView)
  {
    try
    {
      ak localak = new com/google/android/gms/internal/ak;
      localak.<init>(this.nf, this.ny, this.nh, this.nz);
      paramView = a(paramView, localak);
      localak.aS();
      if ((paramView.nG == 0) && (paramView.nH == 0)) {}
      for (;;)
      {
        return;
        if (((paramView.nH != 0) || (localak.aT() != 0)) && ((paramView.nH != 0) || (!this.nv.a(localak)))) {
          this.nv.c(localak);
        }
      }
    }
    catch (Exception paramView)
    {
      for (;;)
      {
        gs.b("Exception in fetchContentOnUIThread", paramView);
        this.nw.b(paramView);
      }
    }
  }
  
  public void run()
  {
    while (!this.nt) {
      try
      {
        if (aW())
        {
          Activity localActivity = this.nu.getActivity();
          if (localActivity == null) {
            gs.S("ContentFetchThread: no activity");
          }
        }
      }
      catch (Throwable localThrowable)
      {
        gs.b("Error in ContentFetchTask", localThrowable);
        this.nw.b(localThrowable);
        synchronized (this.mw)
        {
          for (;;)
          {
            boolean bool = this.ns;
            if (!bool) {
              break;
            }
            try
            {
              gs.S("ContentFetchTask: waiting");
              this.mw.wait();
            }
            catch (InterruptedException localInterruptedException) {}
          }
          a((Activity)???);
          for (;;)
          {
            Thread.sleep(this.nx * 1000);
            break;
            gs.S("ContentFetchTask: sleeping");
            aY();
          }
        }
      }
    }
  }
  
  public void wakeup()
  {
    synchronized (this.mw)
    {
      this.ns = false;
      this.mw.notifyAll();
      gs.S("ContentFetchThread: wakeup");
      return;
    }
  }
  
  @ez
  class a
  {
    final int nG;
    final int nH;
    
    a(int paramInt1, int paramInt2)
    {
      this.nG = paramInt1;
      this.nH = paramInt2;
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\internal\an.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */