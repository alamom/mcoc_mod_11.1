package com.google.android.gms.internal;

import android.content.Context;
import android.os.RemoteException;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.doubleclick.AppEventListener;
import com.google.android.gms.ads.doubleclick.PublisherAdView;
import com.google.android.gms.ads.doubleclick.b;
import com.google.android.gms.ads.purchase.InAppPurchaseListener;
import com.google.android.gms.ads.purchase.PlayStorePurchaseListener;
import com.google.android.gms.dynamic.d;
import com.google.android.gms.dynamic.e;

public final class bh
{
  private AdListener nR;
  private String oA;
  private ViewGroup oB;
  private InAppPurchaseListener oC;
  private PlayStorePurchaseListener oD;
  private b oE;
  private AppEventListener oi;
  private AdSize[] oj;
  private String ok;
  private final cs ox = new cs();
  private final ax oy;
  private bd oz;
  
  public bh(ViewGroup paramViewGroup)
  {
    this(paramViewGroup, null, false, ax.bb());
  }
  
  public bh(ViewGroup paramViewGroup, AttributeSet paramAttributeSet, boolean paramBoolean)
  {
    this(paramViewGroup, paramAttributeSet, paramBoolean, ax.bb());
  }
  
  bh(ViewGroup paramViewGroup, AttributeSet paramAttributeSet, boolean paramBoolean, ax paramax)
  {
    this(paramViewGroup, paramAttributeSet, paramBoolean, paramax, null);
  }
  
  bh(ViewGroup paramViewGroup, AttributeSet paramAttributeSet, boolean paramBoolean, ax paramax, bd parambd)
  {
    this.oB = paramViewGroup;
    this.oy = paramax;
    if (paramAttributeSet != null) {
      paramax = paramViewGroup.getContext();
    }
    for (;;)
    {
      try
      {
        bb localbb = new com/google/android/gms/internal/bb;
        localbb.<init>(paramax, paramAttributeSet);
        this.oj = localbb.f(paramBoolean);
        this.ok = localbb.getAdUnitId();
        if (paramViewGroup.isInEditMode())
        {
          gr.a(paramViewGroup, new ay(paramax, this.oj[0]), "Ads by Google");
          return;
        }
      }
      catch (IllegalArgumentException paramAttributeSet)
      {
        gr.a(paramViewGroup, new ay(paramax, AdSize.BANNER), paramAttributeSet.getMessage(), paramAttributeSet.getMessage());
        continue;
      }
      this.oz = parambd;
    }
  }
  
  private void bh()
  {
    for (;;)
    {
      try
      {
        locald = this.oz.X();
        if (locald == null) {
          return;
        }
      }
      catch (RemoteException localRemoteException)
      {
        d locald;
        gs.d("Failed to get an ad frame.", localRemoteException);
        continue;
      }
      this.oB.addView((View)e.f(locald));
    }
  }
  
  private void bi()
    throws RemoteException
  {
    if (((this.oj == null) || (this.ok == null)) && (this.oz == null)) {
      throw new IllegalStateException("The ad size and ad unit ID must be set before loadAd is called.");
    }
    Context localContext = this.oB.getContext();
    this.oz = au.a(localContext, new ay(localContext, this.oj), this.ok, this.ox);
    if (this.nR != null) {
      this.oz.a(new at(this.nR));
    }
    if (this.oi != null) {
      this.oz.a(new ba(this.oi));
    }
    if (this.oC != null) {
      this.oz.a(new em(this.oC));
    }
    if (this.oD != null) {
      this.oz.a(new eq(this.oD), this.oA);
    }
    if (this.oE != null) {
      this.oz.a(new ew(this.oE, (PublisherAdView)this.oB));
    }
    bh();
  }
  
  public void a(bg parambg)
  {
    try
    {
      if (this.oz == null) {
        bi();
      }
      if (this.oz.a(this.oy.a(this.oB.getContext(), parambg))) {
        this.ox.d(parambg.be());
      }
      return;
    }
    catch (RemoteException parambg)
    {
      for (;;)
      {
        gs.d("Failed to load ad.", parambg);
      }
    }
  }
  
  public void a(AdSize... paramVarArgs)
  {
    this.oj = paramVarArgs;
    try
    {
      if (this.oz != null)
      {
        paramVarArgs = this.oz;
        ay localay = new com/google/android/gms/internal/ay;
        localay.<init>(this.oB.getContext(), this.oj);
        paramVarArgs.a(localay);
      }
      this.oB.requestLayout();
      return;
    }
    catch (RemoteException paramVarArgs)
    {
      for (;;)
      {
        gs.d("Failed to set the ad size.", paramVarArgs);
      }
    }
  }
  
  public void destroy()
  {
    try
    {
      if (this.oz != null) {
        this.oz.destroy();
      }
      return;
    }
    catch (RemoteException localRemoteException)
    {
      for (;;)
      {
        gs.d("Failed to destroy AdView.", localRemoteException);
      }
    }
  }
  
  public AdListener getAdListener()
  {
    return this.nR;
  }
  
  public AdSize getAdSize()
  {
    try
    {
      if (this.oz != null)
      {
        AdSize localAdSize1 = this.oz.Y().bc();
        return localAdSize1;
      }
    }
    catch (RemoteException localRemoteException)
    {
      for (;;)
      {
        gs.d("Failed to get the current AdSize.", localRemoteException);
        AdSize localAdSize2;
        if (this.oj != null) {
          localAdSize2 = this.oj[0];
        } else {
          localAdSize2 = null;
        }
      }
    }
  }
  
  public AdSize[] getAdSizes()
  {
    return this.oj;
  }
  
  public String getAdUnitId()
  {
    return this.ok;
  }
  
  public AppEventListener getAppEventListener()
  {
    return this.oi;
  }
  
  public InAppPurchaseListener getInAppPurchaseListener()
  {
    return this.oC;
  }
  
  public String getMediationAdapterClassName()
  {
    try
    {
      if (this.oz != null)
      {
        String str = this.oz.getMediationAdapterClassName();
        return str;
      }
    }
    catch (RemoteException localRemoteException)
    {
      for (;;)
      {
        gs.d("Failed to get the mediation adapter class name.", localRemoteException);
        Object localObject = null;
      }
    }
  }
  
  public void pause()
  {
    try
    {
      if (this.oz != null) {
        this.oz.pause();
      }
      return;
    }
    catch (RemoteException localRemoteException)
    {
      for (;;)
      {
        gs.d("Failed to call pause.", localRemoteException);
      }
    }
  }
  
  public void recordManualImpression()
  {
    try
    {
      if (this.oz != null) {
        this.oz.aj();
      }
      return;
    }
    catch (RemoteException localRemoteException)
    {
      for (;;)
      {
        gs.d("Failed to record impression.", localRemoteException);
      }
    }
  }
  
  public void resume()
  {
    try
    {
      if (this.oz != null) {
        this.oz.resume();
      }
      return;
    }
    catch (RemoteException localRemoteException)
    {
      for (;;)
      {
        gs.d("Failed to call resume.", localRemoteException);
      }
    }
  }
  
  public void setAdListener(AdListener paramAdListener)
  {
    for (;;)
    {
      try
      {
        this.nR = paramAdListener;
        if (this.oz != null)
        {
          bd localbd = this.oz;
          if (paramAdListener != null)
          {
            at localat = new com/google/android/gms/internal/at;
            localat.<init>(paramAdListener);
            paramAdListener = localat;
            localbd.a(paramAdListener);
          }
        }
        else
        {
          return;
        }
      }
      catch (RemoteException paramAdListener)
      {
        gs.d("Failed to set the AdListener.", paramAdListener);
        continue;
      }
      paramAdListener = null;
    }
  }
  
  public void setAdSizes(AdSize... paramVarArgs)
  {
    if (this.oj != null) {
      throw new IllegalStateException("The ad size can only be set once on AdView.");
    }
    a(paramVarArgs);
  }
  
  public void setAdUnitId(String paramString)
  {
    if (this.ok != null) {
      throw new IllegalStateException("The ad unit ID can only be set once on AdView.");
    }
    this.ok = paramString;
  }
  
  public void setAppEventListener(AppEventListener paramAppEventListener)
  {
    for (;;)
    {
      try
      {
        this.oi = paramAppEventListener;
        if (this.oz != null)
        {
          bd localbd = this.oz;
          if (paramAppEventListener != null)
          {
            ba localba = new com/google/android/gms/internal/ba;
            localba.<init>(paramAppEventListener);
            paramAppEventListener = localba;
            localbd.a(paramAppEventListener);
          }
        }
        else
        {
          return;
        }
      }
      catch (RemoteException paramAppEventListener)
      {
        gs.d("Failed to set the AppEventListener.", paramAppEventListener);
        continue;
      }
      paramAppEventListener = null;
    }
  }
  
  public void setInAppPurchaseListener(InAppPurchaseListener paramInAppPurchaseListener)
  {
    if (this.oD != null) {
      throw new IllegalStateException("Play store purchase parameter has already been set.");
    }
    for (;;)
    {
      try
      {
        this.oC = paramInAppPurchaseListener;
        if (this.oz != null)
        {
          bd localbd = this.oz;
          if (paramInAppPurchaseListener != null)
          {
            em localem = new com/google/android/gms/internal/em;
            localem.<init>(paramInAppPurchaseListener);
            paramInAppPurchaseListener = localem;
            localbd.a(paramInAppPurchaseListener);
          }
        }
        else
        {
          return;
        }
      }
      catch (RemoteException paramInAppPurchaseListener)
      {
        gs.d("Failed to set the InAppPurchaseListener.", paramInAppPurchaseListener);
        continue;
      }
      paramInAppPurchaseListener = null;
    }
  }
  
  public void setPlayStorePurchaseParams(PlayStorePurchaseListener paramPlayStorePurchaseListener, String paramString)
  {
    if (this.oC != null) {
      throw new IllegalStateException("InAppPurchaseListener has already been set.");
    }
    for (;;)
    {
      try
      {
        this.oD = paramPlayStorePurchaseListener;
        this.oA = paramString;
        if (this.oz != null)
        {
          bd localbd = this.oz;
          if (paramPlayStorePurchaseListener != null)
          {
            eq localeq = new com/google/android/gms/internal/eq;
            localeq.<init>(paramPlayStorePurchaseListener);
            paramPlayStorePurchaseListener = localeq;
            localbd.a(paramPlayStorePurchaseListener, paramString);
          }
        }
        else
        {
          return;
        }
      }
      catch (RemoteException paramPlayStorePurchaseListener)
      {
        gs.d("Failed to set the play store purchase parameter.", paramPlayStorePurchaseListener);
        continue;
      }
      paramPlayStorePurchaseListener = null;
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\internal\bh.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */