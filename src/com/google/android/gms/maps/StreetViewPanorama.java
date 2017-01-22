package com.google.android.gms.maps;

import android.graphics.Point;
import android.os.RemoteException;
import com.google.android.gms.common.internal.o;
import com.google.android.gms.dynamic.e;
import com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate;
import com.google.android.gms.maps.internal.p.a;
import com.google.android.gms.maps.internal.q.a;
import com.google.android.gms.maps.internal.r.a;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.RuntimeRemoteException;
import com.google.android.gms.maps.model.StreetViewPanoramaCamera;
import com.google.android.gms.maps.model.StreetViewPanoramaLocation;
import com.google.android.gms.maps.model.StreetViewPanoramaOrientation;

public class StreetViewPanorama
{
  private final IStreetViewPanoramaDelegate ajb;
  
  protected StreetViewPanorama(IStreetViewPanoramaDelegate paramIStreetViewPanoramaDelegate)
  {
    this.ajb = ((IStreetViewPanoramaDelegate)o.i(paramIStreetViewPanoramaDelegate));
  }
  
  public void animateTo(StreetViewPanoramaCamera paramStreetViewPanoramaCamera, long paramLong)
  {
    try
    {
      this.ajb.animateTo(paramStreetViewPanoramaCamera, paramLong);
      return;
    }
    catch (RemoteException paramStreetViewPanoramaCamera)
    {
      throw new RuntimeRemoteException(paramStreetViewPanoramaCamera);
    }
  }
  
  public StreetViewPanoramaLocation getLocation()
  {
    try
    {
      StreetViewPanoramaLocation localStreetViewPanoramaLocation = this.ajb.getStreetViewPanoramaLocation();
      return localStreetViewPanoramaLocation;
    }
    catch (RemoteException localRemoteException)
    {
      throw new RuntimeRemoteException(localRemoteException);
    }
  }
  
  public StreetViewPanoramaCamera getPanoramaCamera()
  {
    try
    {
      StreetViewPanoramaCamera localStreetViewPanoramaCamera = this.ajb.getPanoramaCamera();
      return localStreetViewPanoramaCamera;
    }
    catch (RemoteException localRemoteException)
    {
      throw new RuntimeRemoteException(localRemoteException);
    }
  }
  
  public boolean isPanningGesturesEnabled()
  {
    try
    {
      boolean bool = this.ajb.isPanningGesturesEnabled();
      return bool;
    }
    catch (RemoteException localRemoteException)
    {
      throw new RuntimeRemoteException(localRemoteException);
    }
  }
  
  public boolean isStreetNamesEnabled()
  {
    try
    {
      boolean bool = this.ajb.isStreetNamesEnabled();
      return bool;
    }
    catch (RemoteException localRemoteException)
    {
      throw new RuntimeRemoteException(localRemoteException);
    }
  }
  
  public boolean isUserNavigationEnabled()
  {
    try
    {
      boolean bool = this.ajb.isUserNavigationEnabled();
      return bool;
    }
    catch (RemoteException localRemoteException)
    {
      throw new RuntimeRemoteException(localRemoteException);
    }
  }
  
  public boolean isZoomGesturesEnabled()
  {
    try
    {
      boolean bool = this.ajb.isZoomGesturesEnabled();
      return bool;
    }
    catch (RemoteException localRemoteException)
    {
      throw new RuntimeRemoteException(localRemoteException);
    }
  }
  
  IStreetViewPanoramaDelegate mC()
  {
    return this.ajb;
  }
  
  /* Error */
  public Point orientationToPoint(StreetViewPanoramaOrientation paramStreetViewPanoramaOrientation)
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 36	com/google/android/gms/maps/StreetViewPanorama:ajb	Lcom/google/android/gms/maps/internal/IStreetViewPanoramaDelegate;
    //   4: aload_1
    //   5: invokeinterface 77 2 0
    //   10: astore_1
    //   11: aload_1
    //   12: ifnonnull +7 -> 19
    //   15: aconst_null
    //   16: astore_1
    //   17: aload_1
    //   18: areturn
    //   19: aload_1
    //   20: invokestatic 83	com/google/android/gms/dynamic/e:f	(Lcom/google/android/gms/dynamic/d;)Ljava/lang/Object;
    //   23: checkcast 85	android/graphics/Point
    //   26: astore_1
    //   27: goto -10 -> 17
    //   30: astore_1
    //   31: new 45	com/google/android/gms/maps/model/RuntimeRemoteException
    //   34: dup
    //   35: aload_1
    //   36: invokespecial 48	com/google/android/gms/maps/model/RuntimeRemoteException:<init>	(Landroid/os/RemoteException;)V
    //   39: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	40	0	this	StreetViewPanorama
    //   0	40	1	paramStreetViewPanoramaOrientation	StreetViewPanoramaOrientation
    // Exception table:
    //   from	to	target	type
    //   0	11	30	android/os/RemoteException
    //   19	27	30	android/os/RemoteException
  }
  
  public StreetViewPanoramaOrientation pointToOrientation(Point paramPoint)
  {
    try
    {
      paramPoint = this.ajb.pointToOrientation(e.k(paramPoint));
      return paramPoint;
    }
    catch (RemoteException paramPoint)
    {
      throw new RuntimeRemoteException(paramPoint);
    }
  }
  
  public final void setOnStreetViewPanoramaCameraChangeListener(OnStreetViewPanoramaCameraChangeListener paramOnStreetViewPanoramaCameraChangeListener)
  {
    if (paramOnStreetViewPanoramaCameraChangeListener == null) {}
    for (;;)
    {
      try
      {
        this.ajb.setOnStreetViewPanoramaCameraChangeListener(null);
        return;
      }
      catch (RemoteException paramOnStreetViewPanoramaCameraChangeListener)
      {
        IStreetViewPanoramaDelegate localIStreetViewPanoramaDelegate;
        p.a local2;
        throw new RuntimeRemoteException(paramOnStreetViewPanoramaCameraChangeListener);
      }
      localIStreetViewPanoramaDelegate = this.ajb;
      local2 = new com/google/android/gms/maps/StreetViewPanorama$2;
      local2.<init>(this, paramOnStreetViewPanoramaCameraChangeListener);
      localIStreetViewPanoramaDelegate.setOnStreetViewPanoramaCameraChangeListener(local2);
    }
  }
  
  public final void setOnStreetViewPanoramaChangeListener(OnStreetViewPanoramaChangeListener paramOnStreetViewPanoramaChangeListener)
  {
    if (paramOnStreetViewPanoramaChangeListener == null) {}
    for (;;)
    {
      try
      {
        this.ajb.setOnStreetViewPanoramaChangeListener(null);
        return;
      }
      catch (RemoteException paramOnStreetViewPanoramaChangeListener)
      {
        IStreetViewPanoramaDelegate localIStreetViewPanoramaDelegate;
        q.a local1;
        throw new RuntimeRemoteException(paramOnStreetViewPanoramaChangeListener);
      }
      localIStreetViewPanoramaDelegate = this.ajb;
      local1 = new com/google/android/gms/maps/StreetViewPanorama$1;
      local1.<init>(this, paramOnStreetViewPanoramaChangeListener);
      localIStreetViewPanoramaDelegate.setOnStreetViewPanoramaChangeListener(local1);
    }
  }
  
  public final void setOnStreetViewPanoramaClickListener(OnStreetViewPanoramaClickListener paramOnStreetViewPanoramaClickListener)
  {
    if (paramOnStreetViewPanoramaClickListener == null) {}
    for (;;)
    {
      try
      {
        this.ajb.setOnStreetViewPanoramaClickListener(null);
        return;
      }
      catch (RemoteException paramOnStreetViewPanoramaClickListener)
      {
        IStreetViewPanoramaDelegate localIStreetViewPanoramaDelegate;
        r.a local3;
        throw new RuntimeRemoteException(paramOnStreetViewPanoramaClickListener);
      }
      localIStreetViewPanoramaDelegate = this.ajb;
      local3 = new com/google/android/gms/maps/StreetViewPanorama$3;
      local3.<init>(this, paramOnStreetViewPanoramaClickListener);
      localIStreetViewPanoramaDelegate.setOnStreetViewPanoramaClickListener(local3);
    }
  }
  
  public void setPanningGesturesEnabled(boolean paramBoolean)
  {
    try
    {
      this.ajb.enablePanning(paramBoolean);
      return;
    }
    catch (RemoteException localRemoteException)
    {
      throw new RuntimeRemoteException(localRemoteException);
    }
  }
  
  public void setPosition(LatLng paramLatLng)
  {
    try
    {
      this.ajb.setPosition(paramLatLng);
      return;
    }
    catch (RemoteException paramLatLng)
    {
      throw new RuntimeRemoteException(paramLatLng);
    }
  }
  
  public void setPosition(LatLng paramLatLng, int paramInt)
  {
    try
    {
      this.ajb.setPositionWithRadius(paramLatLng, paramInt);
      return;
    }
    catch (RemoteException paramLatLng)
    {
      throw new RuntimeRemoteException(paramLatLng);
    }
  }
  
  public void setPosition(String paramString)
  {
    try
    {
      this.ajb.setPositionWithID(paramString);
      return;
    }
    catch (RemoteException paramString)
    {
      throw new RuntimeRemoteException(paramString);
    }
  }
  
  public void setStreetNamesEnabled(boolean paramBoolean)
  {
    try
    {
      this.ajb.enableStreetNames(paramBoolean);
      return;
    }
    catch (RemoteException localRemoteException)
    {
      throw new RuntimeRemoteException(localRemoteException);
    }
  }
  
  public void setUserNavigationEnabled(boolean paramBoolean)
  {
    try
    {
      this.ajb.enableUserNavigation(paramBoolean);
      return;
    }
    catch (RemoteException localRemoteException)
    {
      throw new RuntimeRemoteException(localRemoteException);
    }
  }
  
  public void setZoomGesturesEnabled(boolean paramBoolean)
  {
    try
    {
      this.ajb.enableZoom(paramBoolean);
      return;
    }
    catch (RemoteException localRemoteException)
    {
      throw new RuntimeRemoteException(localRemoteException);
    }
  }
  
  public static abstract interface OnStreetViewPanoramaCameraChangeListener
  {
    public abstract void onStreetViewPanoramaCameraChange(StreetViewPanoramaCamera paramStreetViewPanoramaCamera);
  }
  
  public static abstract interface OnStreetViewPanoramaChangeListener
  {
    public abstract void onStreetViewPanoramaChange(StreetViewPanoramaLocation paramStreetViewPanoramaLocation);
  }
  
  public static abstract interface OnStreetViewPanoramaClickListener
  {
    public abstract void onStreetViewPanoramaClick(StreetViewPanoramaOrientation paramStreetViewPanoramaOrientation);
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\maps\StreetViewPanorama.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */