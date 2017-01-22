package com.google.android.gms.internal;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import com.google.android.gms.ads.AdSize;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;

@ez
public class dd
{
  static final Set<String> qT = new HashSet(Arrays.asList(new String[] { "top-left", "top-right", "top-center", "center", "bottom-left", "bottom-right", "bottom-center" }));
  private int lf = -1;
  private int lg = -1;
  private final Context mContext;
  private final gv md;
  private final Map<String, String> qM;
  private int qU = 0;
  private int qV = 0;
  private boolean qW = true;
  private String qX = "top-right";
  
  public dd(gv paramgv, Map<String, String> paramMap)
  {
    this.md = paramgv;
    this.qM = paramMap;
    this.mContext = paramgv.dz();
  }
  
  private void bG()
  {
    Object localObject = gj.t(this.mContext);
    int i;
    if (!TextUtils.isEmpty((CharSequence)this.qM.get("width")))
    {
      i = gj.M((String)this.qM.get("width"));
      if (b(i, localObject[0])) {
        this.lf = i;
      }
    }
    if (!TextUtils.isEmpty((CharSequence)this.qM.get("height")))
    {
      i = gj.M((String)this.qM.get("height"));
      if (c(i, localObject[1])) {
        this.lg = i;
      }
    }
    if (!TextUtils.isEmpty((CharSequence)this.qM.get("offsetX"))) {
      this.qU = gj.M((String)this.qM.get("offsetX"));
    }
    if (!TextUtils.isEmpty((CharSequence)this.qM.get("offsetY"))) {
      this.qV = gj.M((String)this.qM.get("offsetY"));
    }
    if (!TextUtils.isEmpty((CharSequence)this.qM.get("allowOffscreen"))) {
      this.qW = Boolean.parseBoolean((String)this.qM.get("allowOffscreen"));
    }
    localObject = (String)this.qM.get("customClosePosition");
    if ((!TextUtils.isEmpty((CharSequence)localObject)) && (qT.contains(localObject))) {
      this.qX = ((String)localObject);
    }
  }
  
  boolean b(int paramInt1, int paramInt2)
  {
    if ((paramInt1 >= 50) && (paramInt1 < paramInt2)) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  boolean bH()
  {
    if ((this.lf > -1) && (this.lg > -1)) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  void bI()
  {
    try
    {
      JSONObject localJSONObject = new org/json/JSONObject;
      localJSONObject.<init>();
      localJSONObject = localJSONObject.put("x", this.qU).put("y", this.qV).put("width", this.lf).put("height", this.lg);
      this.md.b("onSizeChanged", localJSONObject);
      return;
    }
    catch (JSONException localJSONException)
    {
      for (;;)
      {
        gs.b("Error occured while dispatching size change.", localJSONException);
      }
    }
  }
  
  void bJ()
  {
    try
    {
      JSONObject localJSONObject = new org/json/JSONObject;
      localJSONObject.<init>();
      localJSONObject = localJSONObject.put("state", "resized");
      this.md.b("onStateChanged", localJSONObject);
      return;
    }
    catch (JSONException localJSONException)
    {
      for (;;)
      {
        gs.b("Error occured while dispatching state change.", localJSONException);
      }
    }
  }
  
  boolean c(int paramInt1, int paramInt2)
  {
    if ((paramInt1 >= 50) && (paramInt1 < paramInt2)) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public void execute()
  {
    gs.U("PLEASE IMPLEMENT mraid.resize()");
    if (this.mContext == null) {
      gs.W("Not an activity context. Cannot resize.");
    }
    for (;;)
    {
      return;
      if (this.md.Y().og)
      {
        gs.W("Is interstitial. Cannot resize an interstitial.");
      }
      else if (this.md.dy())
      {
        gs.W("Is expanded. Cannot resize an expanded banner.");
      }
      else
      {
        bG();
        if (bH()) {
          break;
        }
        gs.W("Invalid width and height options. Cannot resize.");
      }
    }
    Object localObject1 = (WindowManager)this.mContext.getSystemService("window");
    Object localObject2 = new DisplayMetrics();
    ((WindowManager)localObject1).getDefaultDisplay().getMetrics((DisplayMetrics)localObject2);
    int i = gr.a((DisplayMetrics)localObject2, this.lf);
    int j = gr.a((DisplayMetrics)localObject2, this.lg);
    localObject1 = this.md.getParent();
    if ((localObject1 != null) && ((localObject1 instanceof ViewGroup))) {
      ((ViewGroup)localObject1).removeView(this.md);
    }
    localObject2 = new LinearLayout(this.mContext);
    ((LinearLayout)localObject2).setBackgroundColor(0);
    localObject1 = new PopupWindow(this.mContext);
    ((PopupWindow)localObject1).setHeight(j + 16);
    ((PopupWindow)localObject1).setWidth(i + 16);
    if (!this.qW) {}
    for (boolean bool = true;; bool = false)
    {
      ((PopupWindow)localObject1).setClippingEnabled(bool);
      ((PopupWindow)localObject1).setContentView((View)localObject2);
      ((LinearLayout)localObject2).addView(this.md, -1, -1);
      ((PopupWindow)localObject1).showAtLocation(((Activity)this.mContext).getWindow().getDecorView(), 0, this.qU, this.qV);
      this.md.a(new ay(this.mContext, new AdSize(this.lf, this.lg)));
      bI();
      bJ();
      break;
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\internal\dd.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */