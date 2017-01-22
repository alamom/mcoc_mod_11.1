package com.google.android.gms.internal;

import android.content.Context;
import android.content.res.Resources;
import android.os.Handler;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import org.json.JSONException;
import org.json.JSONObject;

@ez
public class fb
  extends gg
  implements ff.a
{
  private final Context mContext;
  private final Object mw = new Object();
  private cm pR;
  private final fa.a sU;
  private final Object sV = new Object();
  private final fi.a sW;
  private final k sX;
  private gg sY;
  private fk sZ;
  
  public fb(Context paramContext, fi.a parama, k paramk, fa.a parama1)
  {
    this.sU = parama1;
    this.mContext = paramContext;
    this.sW = parama;
    this.sX = paramk;
  }
  
  private ay a(fi paramfi)
    throws fb.a
  {
    if (this.sZ.tL == null) {
      throw new a("The ad response must specify one of the supported ad sizes.", 0);
    }
    Object localObject = this.sZ.tL.split("x");
    if (localObject.length != 2) {
      throw new a("Could not parse the ad size from the ad response: " + this.sZ.tL, 0);
    }
    for (;;)
    {
      int i;
      ay localay;
      try
      {
        int m = Integer.parseInt(localObject[0]);
        int i1 = Integer.parseInt(localObject[1]);
        localObject = paramfi.lH.oh;
        int n = localObject.length;
        i = 0;
        if (i >= n) {
          break;
        }
        localay = localObject[i];
        float f = this.mContext.getResources().getDisplayMetrics().density;
        if (localay.width == -1)
        {
          j = (int)(localay.widthPixels / f);
          if (localay.height != -2) {
            break label253;
          }
          k = (int)(localay.heightPixels / f);
          if ((m != j) || (i1 != k)) {
            break label263;
          }
          return new ay(localay, paramfi.lH.oh);
        }
      }
      catch (NumberFormatException paramfi)
      {
        throw new a("Could not parse the ad size from the ad response: " + this.sZ.tL, 0);
      }
      int j = localay.width;
      continue;
      label253:
      int k = localay.height;
      continue;
      label263:
      i++;
    }
    throw new a("The ad size from the ad response was not one of the requested sizes: " + this.sZ.tL, 0);
  }
  
  private boolean c(long paramLong)
    throws fb.a
  {
    paramLong = 60000L - (SystemClock.elapsedRealtime() - paramLong);
    boolean bool;
    if (paramLong <= 0L) {
      bool = false;
    }
    for (;;)
    {
      return bool;
      try
      {
        this.mw.wait(paramLong);
        bool = true;
      }
      catch (InterruptedException localInterruptedException)
      {
        throw new a("Ad request cancelled.", -1);
      }
    }
  }
  
  private void cx()
    throws fb.a
  {
    if (this.sZ.errorCode == -3) {}
    for (;;)
    {
      return;
      if (TextUtils.isEmpty(this.sZ.tG)) {
        throw new a("No fill from ad server.", 3);
      }
      gb.a(this.mContext, this.sZ.tF);
      if (!this.sZ.tI) {
        continue;
      }
      try
      {
        cm localcm = new com/google/android/gms/internal/cm;
        localcm.<init>(this.sZ.tG);
        this.pR = localcm;
      }
      catch (JSONException localJSONException)
      {
        throw new a("Could not parse mediation config: " + this.sZ.tG, 0);
      }
    }
  }
  
  private void e(long paramLong)
    throws fb.a
  {
    do
    {
      if (!c(paramLong)) {
        throw new a("Timed out waiting for ad response.", 2);
      }
    } while (this.sZ == null);
    synchronized (this.sV)
    {
      this.sY = null;
      if ((this.sZ.errorCode != -2) && (this.sZ.errorCode != -3)) {
        throw new a("There was a problem getting an ad response. ErrorCode: " + this.sZ.errorCode, this.sZ.errorCode);
      }
    }
  }
  
  private void r(boolean paramBoolean)
  {
    gb.cU().v(paramBoolean);
    an localan = gb.cU().l(this.mContext);
    if ((localan != null) && (!localan.isAlive()))
    {
      gs.S("start fetching content...");
      localan.aV();
    }
  }
  
  public void a(fk paramfk)
  {
    synchronized (this.mw)
    {
      gs.S("Received ad response.");
      this.sZ = paramfk;
      this.mw.notify();
      return;
    }
  }
  
  public void co()
  {
    for (;;)
    {
      int i;
      long l2;
      long l1;
      synchronized (this.mw)
      {
        gs.S("AdLoaderBackgroundTask started.");
        Object localObject1 = this.sX.z().a(this.mContext);
        fi localfi = new com/google/android/gms/internal/fi;
        localfi.<init>(this.sW, (String)localObject1);
        i = -2;
        l2 = -1L;
        l1 = l2;
        long l3;
        try
        {
          l3 = SystemClock.elapsedRealtime();
          l1 = l2;
          Object localObject4 = ff.a(this.mContext, localfi, this);
          l1 = l2;
          localObject1 = this.sV;
          l1 = l2;
          try
          {
            this.sY = ((gg)localObject4);
            if (this.sY != null) {
              continue;
            }
            localObject4 = new com/google/android/gms/internal/fb$a;
            ((a)localObject4).<init>("Could not start the ad request service.", 0);
            throw ((Throwable)localObject4);
          }
          finally
          {
            l1 = l2;
          }
          i = locala1.getErrorCode();
        }
        catch (a locala1)
        {
          localObject1 = null;
        }
        Object localObject9;
        if ((i == 3) || (i == -1))
        {
          gs.U(locala1.getMessage());
          if (this.sZ != null) {
            break label397;
          }
          localObject6 = new com/google/android/gms/internal/fk;
          ((fk)localObject6).<init>(i);
          this.sZ = ((fk)localObject6);
          localObject6 = gr.wC;
          localObject9 = new com/google/android/gms/internal/fb$1;
          ((1)localObject9).<init>(this);
          ((Handler)localObject6).post((Runnable)localObject9);
          boolean bool = TextUtils.isEmpty(this.sZ.tQ);
          if (bool) {
            break label434;
          }
        }
        try
        {
          localObject6 = new org/json/JSONObject;
          ((JSONObject)localObject6).<init>(this.sZ.tQ);
          localObject9 = new com/google/android/gms/internal/fz$a;
          ((fz.a)localObject9).<init>(localfi, this.sZ, this.pR, (ay)localObject1, i, l1, this.sZ.tM, (JSONObject)localObject6);
          localObject1 = gr.wC;
          localObject6 = new com/google/android/gms/internal/fb$2;
          ((2)localObject6).<init>(this, (fz.a)localObject9);
          ((Handler)localObject1).post((Runnable)localObject6);
          return;
        }
        catch (Exception localException)
        {
          gs.b("Error parsing the JSON for Active View.", localException);
        }
        l1 = l2;
        e(l3);
        l1 = l2;
        l2 = SystemClock.elapsedRealtime();
        l1 = l2;
        cx();
        l1 = l2;
        if (localfi.lH.oh == null) {
          break label448;
        }
        l1 = l2;
        localObject1 = a(localfi);
        try
        {
          r(this.sZ.tT);
          l1 = l2;
        }
        catch (a locala2)
        {
          Object localObject7;
          l1 = l2;
        }
        gs.W(((a)localObject6).getMessage());
      }
      label397:
      Object localObject6 = new com/google/android/gms/internal/fk;
      ((fk)localObject6).<init>(i, this.sZ.qj);
      this.sZ = ((fk)localObject6);
      continue;
      label434:
      localObject7 = null;
      continue;
      continue;
      label448:
      Object localObject3 = null;
    }
  }
  
  public void onStop()
  {
    synchronized (this.sV)
    {
      if (this.sY != null) {
        this.sY.cancel();
      }
      return;
    }
  }
  
  @ez
  private static final class a
    extends Exception
  {
    private final int tc;
    
    public a(String paramString, int paramInt)
    {
      super();
      this.tc = paramInt;
    }
    
    public int getErrorCode()
    {
      return this.tc;
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\internal\fb.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */