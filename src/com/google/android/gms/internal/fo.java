package com.google.android.gms.internal;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import com.google.android.gms.common.internal.o;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeoutException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@ez
public class fo
  implements Callable<fz>
{
  private final Context mContext;
  private final Object mw = new Object();
  private final u pw;
  private final go tX;
  private final ai tY;
  private boolean tZ;
  private int tc;
  private final fz.a tn;
  private List<String> ua;
  
  public fo(Context paramContext, u paramu, ai paramai, go paramgo, fz.a parama)
  {
    this.mContext = paramContext;
    this.pw = paramu;
    this.tX = paramgo;
    this.tY = paramai;
    this.tn = parama;
    this.tZ = false;
    this.tc = -2;
    this.ua = null;
  }
  
  private bq.a a(ah paramah, a parama, JSONObject paramJSONObject)
    throws ExecutionException, InterruptedException, JSONException
  {
    Object localObject2 = null;
    if (cH()) {
      paramah = (ah)localObject2;
    }
    for (;;)
    {
      return paramah;
      Object localObject1 = b(paramJSONObject.getJSONObject("tracking_urls_and_actions"), "impression_tracking_urls");
      if (localObject1 == null) {}
      for (localObject1 = null;; localObject1 = Arrays.asList((Object[])localObject1))
      {
        this.ua = ((List)localObject1);
        parama = parama.a(this, paramJSONObject);
        if (parama != null) {
          break label77;
        }
        gs.T("Failed to retrieve ad assets.");
        paramah = (ah)localObject2;
        break;
      }
      label77:
      parama.a(new bq(this.pw, paramah, paramJSONObject));
      paramah = parama;
    }
  }
  
  private fz a(bq.a parama)
  {
    for (;;)
    {
      synchronized (this.mw)
      {
        int j = this.tc;
        int i = j;
        if (parama == null)
        {
          i = j;
          if (this.tc == -2) {
            i = 0;
          }
        }
        if (i != -2)
        {
          parama = null;
          return new fz(this.tn.vv.tx, null, this.tn.vw.qf, i, this.tn.vw.qg, this.ua, this.tn.vw.orientation, this.tn.vw.qj, this.tn.vv.tA, false, null, null, null, null, null, 0L, this.tn.lH, this.tn.vw.tH, this.tn.vs, this.tn.vt, this.tn.vw.tN, this.tn.vp, parama);
        }
      }
    }
  }
  
  private String[] b(JSONObject paramJSONObject, String paramString)
    throws JSONException
  {
    paramString = paramJSONObject.optJSONArray(paramString);
    if (paramString == null) {
      paramJSONObject = null;
    }
    for (;;)
    {
      return paramJSONObject;
      paramJSONObject = new String[paramString.length()];
      for (int i = 0; i < paramString.length(); i++) {
        paramJSONObject[i] = paramString.getString(i);
      }
    }
  }
  
  private JSONObject c(final ah paramah)
    throws TimeoutException, JSONException
  {
    if (cH()) {}
    final gk localgk;
    for (paramah = null;; paramah = (JSONObject)localgk.get())
    {
      return paramah;
      localgk = new gk();
      paramah.a("/nativeAdPreProcess", new by()
      {
        public void a(gv paramAnonymousgv, Map<String, String> paramAnonymousMap)
        {
          paramah.g("/nativeAdPreProcess");
          try
          {
            paramAnonymousMap = (String)paramAnonymousMap.get("success");
            if (!TextUtils.isEmpty(paramAnonymousMap))
            {
              paramAnonymousgv = localgk;
              JSONObject localJSONObject = new org/json/JSONObject;
              localJSONObject.<init>(paramAnonymousMap);
              paramAnonymousgv.a(localJSONObject.getJSONArray("ads").getJSONObject(0));
              return;
            }
          }
          catch (JSONException paramAnonymousgv)
          {
            for (;;)
            {
              gs.b("Malformed native JSON response.", paramAnonymousgv);
              fo.this.s(0);
              o.a(fo.this.cH(), "Unable to set the ad state error!");
              localgk.a(null);
            }
          }
        }
      });
      paramah.a("google.afma.nativeAds.preProcessJsonGmsg", new JSONObject(this.tn.vw.tG));
    }
  }
  
  private ah cG()
    throws CancellationException, ExecutionException, InterruptedException, TimeoutException
  {
    ah localah;
    if (cH()) {
      localah = null;
    }
    for (;;)
    {
      return localah;
      localah = (ah)this.tY.a(this.mContext, this.tn.vv.lD, "https://googleads.g.doubleclick.net/mads/static/mad/sdk/native/native_ads.html").get();
      localah.a(this.pw, this.pw, this.pw, this.pw, false, this.pw);
    }
  }
  
  public Future<Drawable> a(JSONObject paramJSONObject, String paramString, final boolean paramBoolean)
    throws JSONException
  {
    if (paramBoolean)
    {
      paramJSONObject = paramJSONObject.getJSONObject(paramString);
      paramString = paramJSONObject;
      if (paramJSONObject == null) {
        paramString = new JSONObject();
      }
      if (!paramBoolean) {
        break label68;
      }
      paramJSONObject = paramString.getString("url");
      label35:
      if (!TextUtils.isEmpty(paramJSONObject)) {
        break label78;
      }
      a(0, paramBoolean);
    }
    label68:
    label78:
    for (paramJSONObject = new gl(null);; paramJSONObject = this.tX.a(paramJSONObject, new go.a()
        {
          public Drawable a(InputStream paramAnonymousInputStream)
          {
            Object localObject = null;
            try
            {
              paramAnonymousInputStream = jy.d(paramAnonymousInputStream);
              if (paramAnonymousInputStream == null)
              {
                fo.this.a(2, paramBoolean);
                paramAnonymousInputStream = (InputStream)localObject;
                return paramAnonymousInputStream;
              }
            }
            catch (IOException paramAnonymousInputStream)
            {
              for (;;)
              {
                paramAnonymousInputStream = null;
                continue;
                paramAnonymousInputStream = BitmapFactory.decodeByteArray(paramAnonymousInputStream, 0, paramAnonymousInputStream.length);
                if (paramAnonymousInputStream == null)
                {
                  fo.this.a(2, paramBoolean);
                  paramAnonymousInputStream = (InputStream)localObject;
                }
                else
                {
                  paramAnonymousInputStream = new BitmapDrawable(Resources.getSystem(), paramAnonymousInputStream);
                }
              }
            }
          }
          
          public Drawable cI()
          {
            fo.this.a(2, paramBoolean);
            return null;
          }
        }))
    {
      return paramJSONObject;
      paramJSONObject = paramJSONObject.optJSONObject(paramString);
      break;
      paramJSONObject = paramString.optString("url");
      break label35;
    }
  }
  
  public void a(int paramInt, boolean paramBoolean)
  {
    if (paramBoolean) {
      s(paramInt);
    }
  }
  
  protected a b(JSONObject paramJSONObject)
    throws JSONException
  {
    Object localObject = null;
    if (cH()) {
      paramJSONObject = (JSONObject)localObject;
    }
    for (;;)
    {
      return paramJSONObject;
      paramJSONObject = paramJSONObject.getString("template_id");
      if ("2".equals(paramJSONObject))
      {
        paramJSONObject = new fp();
      }
      else if ("1".equals(paramJSONObject))
      {
        paramJSONObject = new fq();
      }
      else
      {
        s(0);
        paramJSONObject = (JSONObject)localObject;
      }
    }
  }
  
  public fz cF()
  {
    try
    {
      Object localObject = cG();
      JSONObject localJSONObject = c((ah)localObject);
      localObject = a(a((ah)localObject, b(localJSONObject), localJSONObject));
      return (fz)localObject;
    }
    catch (JSONException localJSONException)
    {
      for (;;)
      {
        gs.d("Malformed native JSON response.", localJSONException);
        if (!this.tZ) {
          s(0);
        }
        fz localfz = a(null);
      }
    }
    catch (TimeoutException localTimeoutException)
    {
      for (;;)
      {
        gs.d("Timeout when loading native ad.", localTimeoutException);
      }
    }
    catch (InterruptedException localInterruptedException)
    {
      for (;;) {}
    }
    catch (ExecutionException localExecutionException)
    {
      for (;;) {}
    }
    catch (CancellationException localCancellationException)
    {
      for (;;) {}
    }
  }
  
  public boolean cH()
  {
    synchronized (this.mw)
    {
      boolean bool = this.tZ;
      return bool;
    }
  }
  
  public void s(int paramInt)
  {
    synchronized (this.mw)
    {
      this.tZ = true;
      this.tc = paramInt;
      return;
    }
  }
  
  public static abstract interface a<T extends bq.a>
  {
    public abstract T a(fo paramfo, JSONObject paramJSONObject)
      throws JSONException, InterruptedException, ExecutionException;
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\internal\fo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */