package com.google.android.gms.maps;

import android.graphics.Bitmap;
import android.location.Location;
import android.os.RemoteException;
import android.view.View;
import com.google.android.gms.common.internal.o;
import com.google.android.gms.dynamic.e;
import com.google.android.gms.maps.internal.IGoogleMapDelegate;
import com.google.android.gms.maps.internal.ILocationSourceDelegate.a;
import com.google.android.gms.maps.internal.b.a;
import com.google.android.gms.maps.internal.d.a;
import com.google.android.gms.maps.internal.e.a;
import com.google.android.gms.maps.internal.f.a;
import com.google.android.gms.maps.internal.g.a;
import com.google.android.gms.maps.internal.h;
import com.google.android.gms.maps.internal.i.a;
import com.google.android.gms.maps.internal.j.a;
import com.google.android.gms.maps.internal.k.a;
import com.google.android.gms.maps.internal.l.a;
import com.google.android.gms.maps.internal.m.a;
import com.google.android.gms.maps.internal.n.a;
import com.google.android.gms.maps.internal.o.a;
import com.google.android.gms.maps.internal.s.a;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.IndoorBuilding;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.maps.model.RuntimeRemoteException;
import com.google.android.gms.maps.model.internal.f;

public final class GoogleMap
{
  public static final int MAP_TYPE_HYBRID = 4;
  public static final int MAP_TYPE_NONE = 0;
  public static final int MAP_TYPE_NORMAL = 1;
  public static final int MAP_TYPE_SATELLITE = 2;
  public static final int MAP_TYPE_TERRAIN = 3;
  private final IGoogleMapDelegate ain;
  private UiSettings aio;
  
  protected GoogleMap(IGoogleMapDelegate paramIGoogleMapDelegate)
  {
    this.ain = ((IGoogleMapDelegate)o.i(paramIGoogleMapDelegate));
  }
  
  public final Circle addCircle(CircleOptions paramCircleOptions)
  {
    try
    {
      paramCircleOptions = new Circle(this.ain.addCircle(paramCircleOptions));
      return paramCircleOptions;
    }
    catch (RemoteException paramCircleOptions)
    {
      throw new RuntimeRemoteException(paramCircleOptions);
    }
  }
  
  /* Error */
  public final com.google.android.gms.maps.model.GroundOverlay addGroundOverlay(com.google.android.gms.maps.model.GroundOverlayOptions paramGroundOverlayOptions)
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 104	com/google/android/gms/maps/GoogleMap:ain	Lcom/google/android/gms/maps/internal/IGoogleMapDelegate;
    //   4: aload_1
    //   5: invokeinterface 127 2 0
    //   10: astore_1
    //   11: aload_1
    //   12: ifnull +14 -> 26
    //   15: new 129	com/google/android/gms/maps/model/GroundOverlay
    //   18: dup
    //   19: aload_1
    //   20: invokespecial 132	com/google/android/gms/maps/model/GroundOverlay:<init>	(Lcom/google/android/gms/maps/model/internal/c;)V
    //   23: astore_1
    //   24: aload_1
    //   25: areturn
    //   26: aconst_null
    //   27: astore_1
    //   28: goto -4 -> 24
    //   31: astore_1
    //   32: new 119	com/google/android/gms/maps/model/RuntimeRemoteException
    //   35: dup
    //   36: aload_1
    //   37: invokespecial 122	com/google/android/gms/maps/model/RuntimeRemoteException:<init>	(Landroid/os/RemoteException;)V
    //   40: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	41	0	this	GoogleMap
    //   0	41	1	paramGroundOverlayOptions	com.google.android.gms.maps.model.GroundOverlayOptions
    // Exception table:
    //   from	to	target	type
    //   0	11	31	android/os/RemoteException
    //   15	24	31	android/os/RemoteException
  }
  
  /* Error */
  public final Marker addMarker(com.google.android.gms.maps.model.MarkerOptions paramMarkerOptions)
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 104	com/google/android/gms/maps/GoogleMap:ain	Lcom/google/android/gms/maps/internal/IGoogleMapDelegate;
    //   4: aload_1
    //   5: invokeinterface 137 2 0
    //   10: astore_1
    //   11: aload_1
    //   12: ifnull +14 -> 26
    //   15: new 139	com/google/android/gms/maps/model/Marker
    //   18: dup
    //   19: aload_1
    //   20: invokespecial 142	com/google/android/gms/maps/model/Marker:<init>	(Lcom/google/android/gms/maps/model/internal/f;)V
    //   23: astore_1
    //   24: aload_1
    //   25: areturn
    //   26: aconst_null
    //   27: astore_1
    //   28: goto -4 -> 24
    //   31: astore_1
    //   32: new 119	com/google/android/gms/maps/model/RuntimeRemoteException
    //   35: dup
    //   36: aload_1
    //   37: invokespecial 122	com/google/android/gms/maps/model/RuntimeRemoteException:<init>	(Landroid/os/RemoteException;)V
    //   40: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	41	0	this	GoogleMap
    //   0	41	1	paramMarkerOptions	com.google.android.gms.maps.model.MarkerOptions
    // Exception table:
    //   from	to	target	type
    //   0	11	31	android/os/RemoteException
    //   15	24	31	android/os/RemoteException
  }
  
  public final Polygon addPolygon(PolygonOptions paramPolygonOptions)
  {
    try
    {
      paramPolygonOptions = new Polygon(this.ain.addPolygon(paramPolygonOptions));
      return paramPolygonOptions;
    }
    catch (RemoteException paramPolygonOptions)
    {
      throw new RuntimeRemoteException(paramPolygonOptions);
    }
  }
  
  public final Polyline addPolyline(PolylineOptions paramPolylineOptions)
  {
    try
    {
      paramPolylineOptions = new Polyline(this.ain.addPolyline(paramPolylineOptions));
      return paramPolylineOptions;
    }
    catch (RemoteException paramPolylineOptions)
    {
      throw new RuntimeRemoteException(paramPolylineOptions);
    }
  }
  
  /* Error */
  public final com.google.android.gms.maps.model.TileOverlay addTileOverlay(com.google.android.gms.maps.model.TileOverlayOptions paramTileOverlayOptions)
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 104	com/google/android/gms/maps/GoogleMap:ain	Lcom/google/android/gms/maps/internal/IGoogleMapDelegate;
    //   4: aload_1
    //   5: invokeinterface 167 2 0
    //   10: astore_1
    //   11: aload_1
    //   12: ifnull +14 -> 26
    //   15: new 169	com/google/android/gms/maps/model/TileOverlay
    //   18: dup
    //   19: aload_1
    //   20: invokespecial 172	com/google/android/gms/maps/model/TileOverlay:<init>	(Lcom/google/android/gms/maps/model/internal/h;)V
    //   23: astore_1
    //   24: aload_1
    //   25: areturn
    //   26: aconst_null
    //   27: astore_1
    //   28: goto -4 -> 24
    //   31: astore_1
    //   32: new 119	com/google/android/gms/maps/model/RuntimeRemoteException
    //   35: dup
    //   36: aload_1
    //   37: invokespecial 122	com/google/android/gms/maps/model/RuntimeRemoteException:<init>	(Landroid/os/RemoteException;)V
    //   40: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	41	0	this	GoogleMap
    //   0	41	1	paramTileOverlayOptions	com.google.android.gms.maps.model.TileOverlayOptions
    // Exception table:
    //   from	to	target	type
    //   0	11	31	android/os/RemoteException
    //   15	24	31	android/os/RemoteException
  }
  
  public final void animateCamera(CameraUpdate paramCameraUpdate)
  {
    try
    {
      this.ain.animateCamera(paramCameraUpdate.mo());
      return;
    }
    catch (RemoteException paramCameraUpdate)
    {
      throw new RuntimeRemoteException(paramCameraUpdate);
    }
  }
  
  /* Error */
  public final void animateCamera(CameraUpdate paramCameraUpdate, int paramInt, CancelableCallback paramCancelableCallback)
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 104	com/google/android/gms/maps/GoogleMap:ain	Lcom/google/android/gms/maps/internal/IGoogleMapDelegate;
    //   4: astore 4
    //   6: aload_1
    //   7: invokevirtual 180	com/google/android/gms/maps/CameraUpdate:mo	()Lcom/google/android/gms/dynamic/d;
    //   10: astore 5
    //   12: aload_3
    //   13: ifnonnull +17 -> 30
    //   16: aconst_null
    //   17: astore_1
    //   18: aload 4
    //   20: aload 5
    //   22: iload_2
    //   23: aload_1
    //   24: invokeinterface 188 4 0
    //   29: return
    //   30: new 73	com/google/android/gms/maps/GoogleMap$a
    //   33: dup
    //   34: aload_3
    //   35: invokespecial 191	com/google/android/gms/maps/GoogleMap$a:<init>	(Lcom/google/android/gms/maps/GoogleMap$CancelableCallback;)V
    //   38: astore_1
    //   39: goto -21 -> 18
    //   42: astore_1
    //   43: new 119	com/google/android/gms/maps/model/RuntimeRemoteException
    //   46: dup
    //   47: aload_1
    //   48: invokespecial 122	com/google/android/gms/maps/model/RuntimeRemoteException:<init>	(Landroid/os/RemoteException;)V
    //   51: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	52	0	this	GoogleMap
    //   0	52	1	paramCameraUpdate	CameraUpdate
    //   0	52	2	paramInt	int
    //   0	52	3	paramCancelableCallback	CancelableCallback
    //   4	15	4	localIGoogleMapDelegate	IGoogleMapDelegate
    //   10	11	5	locald	com.google.android.gms.dynamic.d
    // Exception table:
    //   from	to	target	type
    //   0	12	42	android/os/RemoteException
    //   18	29	42	android/os/RemoteException
    //   30	39	42	android/os/RemoteException
  }
  
  /* Error */
  public final void animateCamera(CameraUpdate paramCameraUpdate, CancelableCallback paramCancelableCallback)
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 104	com/google/android/gms/maps/GoogleMap:ain	Lcom/google/android/gms/maps/internal/IGoogleMapDelegate;
    //   4: astore_3
    //   5: aload_1
    //   6: invokevirtual 180	com/google/android/gms/maps/CameraUpdate:mo	()Lcom/google/android/gms/dynamic/d;
    //   9: astore 4
    //   11: aload_2
    //   12: ifnonnull +15 -> 27
    //   15: aconst_null
    //   16: astore_1
    //   17: aload_3
    //   18: aload 4
    //   20: aload_1
    //   21: invokeinterface 196 3 0
    //   26: return
    //   27: new 73	com/google/android/gms/maps/GoogleMap$a
    //   30: dup
    //   31: aload_2
    //   32: invokespecial 191	com/google/android/gms/maps/GoogleMap$a:<init>	(Lcom/google/android/gms/maps/GoogleMap$CancelableCallback;)V
    //   35: astore_1
    //   36: goto -19 -> 17
    //   39: astore_1
    //   40: new 119	com/google/android/gms/maps/model/RuntimeRemoteException
    //   43: dup
    //   44: aload_1
    //   45: invokespecial 122	com/google/android/gms/maps/model/RuntimeRemoteException:<init>	(Landroid/os/RemoteException;)V
    //   48: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	49	0	this	GoogleMap
    //   0	49	1	paramCameraUpdate	CameraUpdate
    //   0	49	2	paramCancelableCallback	CancelableCallback
    //   4	14	3	localIGoogleMapDelegate	IGoogleMapDelegate
    //   9	10	4	locald	com.google.android.gms.dynamic.d
    // Exception table:
    //   from	to	target	type
    //   0	11	39	android/os/RemoteException
    //   17	26	39	android/os/RemoteException
    //   27	36	39	android/os/RemoteException
  }
  
  public final void clear()
  {
    try
    {
      this.ain.clear();
      return;
    }
    catch (RemoteException localRemoteException)
    {
      throw new RuntimeRemoteException(localRemoteException);
    }
  }
  
  public final CameraPosition getCameraPosition()
  {
    try
    {
      CameraPosition localCameraPosition = this.ain.getCameraPosition();
      return localCameraPosition;
    }
    catch (RemoteException localRemoteException)
    {
      throw new RuntimeRemoteException(localRemoteException);
    }
  }
  
  /* Error */
  public IndoorBuilding getFocusedBuilding()
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 104	com/google/android/gms/maps/GoogleMap:ain	Lcom/google/android/gms/maps/internal/IGoogleMapDelegate;
    //   4: invokeinterface 208 1 0
    //   9: astore_1
    //   10: aload_1
    //   11: ifnull +14 -> 25
    //   14: new 210	com/google/android/gms/maps/model/IndoorBuilding
    //   17: dup
    //   18: aload_1
    //   19: invokespecial 213	com/google/android/gms/maps/model/IndoorBuilding:<init>	(Lcom/google/android/gms/maps/model/internal/d;)V
    //   22: astore_1
    //   23: aload_1
    //   24: areturn
    //   25: aconst_null
    //   26: astore_1
    //   27: goto -4 -> 23
    //   30: astore_1
    //   31: new 119	com/google/android/gms/maps/model/RuntimeRemoteException
    //   34: dup
    //   35: aload_1
    //   36: invokespecial 122	com/google/android/gms/maps/model/RuntimeRemoteException:<init>	(Landroid/os/RemoteException;)V
    //   39: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	40	0	this	GoogleMap
    //   9	18	1	localObject	Object
    //   30	6	1	localRemoteException	RemoteException
    // Exception table:
    //   from	to	target	type
    //   0	10	30	android/os/RemoteException
    //   14	23	30	android/os/RemoteException
  }
  
  public final int getMapType()
  {
    try
    {
      int i = this.ain.getMapType();
      return i;
    }
    catch (RemoteException localRemoteException)
    {
      throw new RuntimeRemoteException(localRemoteException);
    }
  }
  
  public final float getMaxZoomLevel()
  {
    try
    {
      float f = this.ain.getMaxZoomLevel();
      return f;
    }
    catch (RemoteException localRemoteException)
    {
      throw new RuntimeRemoteException(localRemoteException);
    }
  }
  
  public final float getMinZoomLevel()
  {
    try
    {
      float f = this.ain.getMinZoomLevel();
      return f;
    }
    catch (RemoteException localRemoteException)
    {
      throw new RuntimeRemoteException(localRemoteException);
    }
  }
  
  @Deprecated
  public final Location getMyLocation()
  {
    try
    {
      Location localLocation = this.ain.getMyLocation();
      return localLocation;
    }
    catch (RemoteException localRemoteException)
    {
      throw new RuntimeRemoteException(localRemoteException);
    }
  }
  
  public final Projection getProjection()
  {
    try
    {
      Projection localProjection = new Projection(this.ain.getProjection());
      return localProjection;
    }
    catch (RemoteException localRemoteException)
    {
      throw new RuntimeRemoteException(localRemoteException);
    }
  }
  
  public final UiSettings getUiSettings()
  {
    try
    {
      if (this.aio == null)
      {
        localUiSettings = new com/google/android/gms/maps/UiSettings;
        localUiSettings.<init>(this.ain.getUiSettings());
        this.aio = localUiSettings;
      }
      UiSettings localUiSettings = this.aio;
      return localUiSettings;
    }
    catch (RemoteException localRemoteException)
    {
      throw new RuntimeRemoteException(localRemoteException);
    }
  }
  
  public final boolean isBuildingsEnabled()
  {
    try
    {
      boolean bool = this.ain.isBuildingsEnabled();
      return bool;
    }
    catch (RemoteException localRemoteException)
    {
      throw new RuntimeRemoteException(localRemoteException);
    }
  }
  
  public final boolean isIndoorEnabled()
  {
    try
    {
      boolean bool = this.ain.isIndoorEnabled();
      return bool;
    }
    catch (RemoteException localRemoteException)
    {
      throw new RuntimeRemoteException(localRemoteException);
    }
  }
  
  public final boolean isMyLocationEnabled()
  {
    try
    {
      boolean bool = this.ain.isMyLocationEnabled();
      return bool;
    }
    catch (RemoteException localRemoteException)
    {
      throw new RuntimeRemoteException(localRemoteException);
    }
  }
  
  public final boolean isTrafficEnabled()
  {
    try
    {
      boolean bool = this.ain.isTrafficEnabled();
      return bool;
    }
    catch (RemoteException localRemoteException)
    {
      throw new RuntimeRemoteException(localRemoteException);
    }
  }
  
  public final void moveCamera(CameraUpdate paramCameraUpdate)
  {
    try
    {
      this.ain.moveCamera(paramCameraUpdate.mo());
      return;
    }
    catch (RemoteException paramCameraUpdate)
    {
      throw new RuntimeRemoteException(paramCameraUpdate);
    }
  }
  
  IGoogleMapDelegate mq()
  {
    return this.ain;
  }
  
  public final void setBuildingsEnabled(boolean paramBoolean)
  {
    try
    {
      this.ain.setBuildingsEnabled(paramBoolean);
      return;
    }
    catch (RemoteException localRemoteException)
    {
      throw new RuntimeRemoteException(localRemoteException);
    }
  }
  
  public final boolean setIndoorEnabled(boolean paramBoolean)
  {
    try
    {
      paramBoolean = this.ain.setIndoorEnabled(paramBoolean);
      return paramBoolean;
    }
    catch (RemoteException localRemoteException)
    {
      throw new RuntimeRemoteException(localRemoteException);
    }
  }
  
  public final void setInfoWindowAdapter(InfoWindowAdapter paramInfoWindowAdapter)
  {
    if (paramInfoWindowAdapter == null) {}
    for (;;)
    {
      try
      {
        this.ain.setInfoWindowAdapter(null);
        return;
      }
      catch (RemoteException paramInfoWindowAdapter)
      {
        IGoogleMapDelegate localIGoogleMapDelegate;
        d.a local13;
        throw new RuntimeRemoteException(paramInfoWindowAdapter);
      }
      localIGoogleMapDelegate = this.ain;
      local13 = new com/google/android/gms/maps/GoogleMap$13;
      local13.<init>(this, paramInfoWindowAdapter);
      localIGoogleMapDelegate.setInfoWindowAdapter(local13);
    }
  }
  
  public final void setLocationSource(LocationSource paramLocationSource)
  {
    if (paramLocationSource == null) {}
    for (;;)
    {
      try
      {
        this.ain.setLocationSource(null);
        return;
      }
      catch (RemoteException paramLocationSource)
      {
        IGoogleMapDelegate localIGoogleMapDelegate;
        ILocationSourceDelegate.a local6;
        throw new RuntimeRemoteException(paramLocationSource);
      }
      localIGoogleMapDelegate = this.ain;
      local6 = new com/google/android/gms/maps/GoogleMap$6;
      local6.<init>(this, paramLocationSource);
      localIGoogleMapDelegate.setLocationSource(local6);
    }
  }
  
  public final void setMapType(int paramInt)
  {
    try
    {
      this.ain.setMapType(paramInt);
      return;
    }
    catch (RemoteException localRemoteException)
    {
      throw new RuntimeRemoteException(localRemoteException);
    }
  }
  
  public final void setMyLocationEnabled(boolean paramBoolean)
  {
    try
    {
      this.ain.setMyLocationEnabled(paramBoolean);
      return;
    }
    catch (RemoteException localRemoteException)
    {
      throw new RuntimeRemoteException(localRemoteException);
    }
  }
  
  public final void setOnCameraChangeListener(OnCameraChangeListener paramOnCameraChangeListener)
  {
    if (paramOnCameraChangeListener == null) {}
    for (;;)
    {
      try
      {
        this.ain.setOnCameraChangeListener(null);
        return;
      }
      catch (RemoteException paramOnCameraChangeListener)
      {
        IGoogleMapDelegate localIGoogleMapDelegate;
        e.a local7;
        throw new RuntimeRemoteException(paramOnCameraChangeListener);
      }
      localIGoogleMapDelegate = this.ain;
      local7 = new com/google/android/gms/maps/GoogleMap$7;
      local7.<init>(this, paramOnCameraChangeListener);
      localIGoogleMapDelegate.setOnCameraChangeListener(local7);
    }
  }
  
  public final void setOnIndoorStateChangeListener(OnIndoorStateChangeListener paramOnIndoorStateChangeListener)
  {
    if (paramOnIndoorStateChangeListener == null) {}
    for (;;)
    {
      try
      {
        this.ain.setOnIndoorStateChangeListener(null);
        return;
      }
      catch (RemoteException paramOnIndoorStateChangeListener)
      {
        IGoogleMapDelegate localIGoogleMapDelegate;
        f.a local1;
        throw new RuntimeRemoteException(paramOnIndoorStateChangeListener);
      }
      localIGoogleMapDelegate = this.ain;
      local1 = new com/google/android/gms/maps/GoogleMap$1;
      local1.<init>(this, paramOnIndoorStateChangeListener);
      localIGoogleMapDelegate.setOnIndoorStateChangeListener(local1);
    }
  }
  
  public final void setOnInfoWindowClickListener(OnInfoWindowClickListener paramOnInfoWindowClickListener)
  {
    if (paramOnInfoWindowClickListener == null) {}
    for (;;)
    {
      try
      {
        this.ain.setOnInfoWindowClickListener(null);
        return;
      }
      catch (RemoteException paramOnInfoWindowClickListener)
      {
        IGoogleMapDelegate localIGoogleMapDelegate;
        g.a local12;
        throw new RuntimeRemoteException(paramOnInfoWindowClickListener);
      }
      localIGoogleMapDelegate = this.ain;
      local12 = new com/google/android/gms/maps/GoogleMap$12;
      local12.<init>(this, paramOnInfoWindowClickListener);
      localIGoogleMapDelegate.setOnInfoWindowClickListener(local12);
    }
  }
  
  public final void setOnMapClickListener(OnMapClickListener paramOnMapClickListener)
  {
    if (paramOnMapClickListener == null) {}
    for (;;)
    {
      try
      {
        this.ain.setOnMapClickListener(null);
        return;
      }
      catch (RemoteException paramOnMapClickListener)
      {
        IGoogleMapDelegate localIGoogleMapDelegate;
        i.a local8;
        throw new RuntimeRemoteException(paramOnMapClickListener);
      }
      localIGoogleMapDelegate = this.ain;
      local8 = new com/google/android/gms/maps/GoogleMap$8;
      local8.<init>(this, paramOnMapClickListener);
      localIGoogleMapDelegate.setOnMapClickListener(local8);
    }
  }
  
  public void setOnMapLoadedCallback(OnMapLoadedCallback paramOnMapLoadedCallback)
  {
    if (paramOnMapLoadedCallback == null) {}
    for (;;)
    {
      try
      {
        this.ain.setOnMapLoadedCallback(null);
        return;
      }
      catch (RemoteException paramOnMapLoadedCallback)
      {
        IGoogleMapDelegate localIGoogleMapDelegate;
        j.a local4;
        throw new RuntimeRemoteException(paramOnMapLoadedCallback);
      }
      localIGoogleMapDelegate = this.ain;
      local4 = new com/google/android/gms/maps/GoogleMap$4;
      local4.<init>(this, paramOnMapLoadedCallback);
      localIGoogleMapDelegate.setOnMapLoadedCallback(local4);
    }
  }
  
  public final void setOnMapLongClickListener(OnMapLongClickListener paramOnMapLongClickListener)
  {
    if (paramOnMapLongClickListener == null) {}
    for (;;)
    {
      try
      {
        this.ain.setOnMapLongClickListener(null);
        return;
      }
      catch (RemoteException paramOnMapLongClickListener)
      {
        IGoogleMapDelegate localIGoogleMapDelegate;
        k.a local9;
        throw new RuntimeRemoteException(paramOnMapLongClickListener);
      }
      localIGoogleMapDelegate = this.ain;
      local9 = new com/google/android/gms/maps/GoogleMap$9;
      local9.<init>(this, paramOnMapLongClickListener);
      localIGoogleMapDelegate.setOnMapLongClickListener(local9);
    }
  }
  
  public final void setOnMarkerClickListener(OnMarkerClickListener paramOnMarkerClickListener)
  {
    if (paramOnMarkerClickListener == null) {}
    for (;;)
    {
      try
      {
        this.ain.setOnMarkerClickListener(null);
        return;
      }
      catch (RemoteException paramOnMarkerClickListener)
      {
        IGoogleMapDelegate localIGoogleMapDelegate;
        l.a local10;
        throw new RuntimeRemoteException(paramOnMarkerClickListener);
      }
      localIGoogleMapDelegate = this.ain;
      local10 = new com/google/android/gms/maps/GoogleMap$10;
      local10.<init>(this, paramOnMarkerClickListener);
      localIGoogleMapDelegate.setOnMarkerClickListener(local10);
    }
  }
  
  public final void setOnMarkerDragListener(OnMarkerDragListener paramOnMarkerDragListener)
  {
    if (paramOnMarkerDragListener == null) {}
    for (;;)
    {
      try
      {
        this.ain.setOnMarkerDragListener(null);
        return;
      }
      catch (RemoteException paramOnMarkerDragListener)
      {
        IGoogleMapDelegate localIGoogleMapDelegate;
        m.a local11;
        throw new RuntimeRemoteException(paramOnMarkerDragListener);
      }
      localIGoogleMapDelegate = this.ain;
      local11 = new com/google/android/gms/maps/GoogleMap$11;
      local11.<init>(this, paramOnMarkerDragListener);
      localIGoogleMapDelegate.setOnMarkerDragListener(local11);
    }
  }
  
  public final void setOnMyLocationButtonClickListener(OnMyLocationButtonClickListener paramOnMyLocationButtonClickListener)
  {
    if (paramOnMyLocationButtonClickListener == null) {}
    for (;;)
    {
      try
      {
        this.ain.setOnMyLocationButtonClickListener(null);
        return;
      }
      catch (RemoteException paramOnMyLocationButtonClickListener)
      {
        IGoogleMapDelegate localIGoogleMapDelegate;
        n.a local3;
        throw new RuntimeRemoteException(paramOnMyLocationButtonClickListener);
      }
      localIGoogleMapDelegate = this.ain;
      local3 = new com/google/android/gms/maps/GoogleMap$3;
      local3.<init>(this, paramOnMyLocationButtonClickListener);
      localIGoogleMapDelegate.setOnMyLocationButtonClickListener(local3);
    }
  }
  
  @Deprecated
  public final void setOnMyLocationChangeListener(OnMyLocationChangeListener paramOnMyLocationChangeListener)
  {
    if (paramOnMyLocationChangeListener == null) {}
    for (;;)
    {
      try
      {
        this.ain.setOnMyLocationChangeListener(null);
        return;
      }
      catch (RemoteException paramOnMyLocationChangeListener)
      {
        IGoogleMapDelegate localIGoogleMapDelegate;
        o.a local2;
        throw new RuntimeRemoteException(paramOnMyLocationChangeListener);
      }
      localIGoogleMapDelegate = this.ain;
      local2 = new com/google/android/gms/maps/GoogleMap$2;
      local2.<init>(this, paramOnMyLocationChangeListener);
      localIGoogleMapDelegate.setOnMyLocationChangeListener(local2);
    }
  }
  
  public final void setPadding(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    try
    {
      this.ain.setPadding(paramInt1, paramInt2, paramInt3, paramInt4);
      return;
    }
    catch (RemoteException localRemoteException)
    {
      throw new RuntimeRemoteException(localRemoteException);
    }
  }
  
  public final void setTrafficEnabled(boolean paramBoolean)
  {
    try
    {
      this.ain.setTrafficEnabled(paramBoolean);
      return;
    }
    catch (RemoteException localRemoteException)
    {
      throw new RuntimeRemoteException(localRemoteException);
    }
  }
  
  public final void snapshot(SnapshotReadyCallback paramSnapshotReadyCallback)
  {
    snapshot(paramSnapshotReadyCallback, null);
  }
  
  public final void snapshot(SnapshotReadyCallback paramSnapshotReadyCallback, Bitmap paramBitmap)
  {
    if (paramBitmap != null) {}
    for (paramBitmap = e.k(paramBitmap);; paramBitmap = null)
    {
      e locale = (e)paramBitmap;
      try
      {
        IGoogleMapDelegate localIGoogleMapDelegate = this.ain;
        paramBitmap = new com/google/android/gms/maps/GoogleMap$5;
        paramBitmap.<init>(this, paramSnapshotReadyCallback);
        localIGoogleMapDelegate.snapshot(paramBitmap, locale);
        return;
      }
      catch (RemoteException paramSnapshotReadyCallback)
      {
        throw new RuntimeRemoteException(paramSnapshotReadyCallback);
      }
    }
  }
  
  public final void stopAnimation()
  {
    try
    {
      this.ain.stopAnimation();
      return;
    }
    catch (RemoteException localRemoteException)
    {
      throw new RuntimeRemoteException(localRemoteException);
    }
  }
  
  public static abstract interface CancelableCallback
  {
    public abstract void onCancel();
    
    public abstract void onFinish();
  }
  
  public static abstract interface InfoWindowAdapter
  {
    public abstract View getInfoContents(Marker paramMarker);
    
    public abstract View getInfoWindow(Marker paramMarker);
  }
  
  public static abstract interface OnCameraChangeListener
  {
    public abstract void onCameraChange(CameraPosition paramCameraPosition);
  }
  
  public static abstract interface OnIndoorStateChangeListener
  {
    public abstract void onIndoorBuildingFocused();
    
    public abstract void onIndoorLevelActivated(IndoorBuilding paramIndoorBuilding);
  }
  
  public static abstract interface OnInfoWindowClickListener
  {
    public abstract void onInfoWindowClick(Marker paramMarker);
  }
  
  public static abstract interface OnMapClickListener
  {
    public abstract void onMapClick(LatLng paramLatLng);
  }
  
  public static abstract interface OnMapLoadedCallback
  {
    public abstract void onMapLoaded();
  }
  
  public static abstract interface OnMapLongClickListener
  {
    public abstract void onMapLongClick(LatLng paramLatLng);
  }
  
  public static abstract interface OnMarkerClickListener
  {
    public abstract boolean onMarkerClick(Marker paramMarker);
  }
  
  public static abstract interface OnMarkerDragListener
  {
    public abstract void onMarkerDrag(Marker paramMarker);
    
    public abstract void onMarkerDragEnd(Marker paramMarker);
    
    public abstract void onMarkerDragStart(Marker paramMarker);
  }
  
  public static abstract interface OnMyLocationButtonClickListener
  {
    public abstract boolean onMyLocationButtonClick();
  }
  
  @Deprecated
  public static abstract interface OnMyLocationChangeListener
  {
    public abstract void onMyLocationChange(Location paramLocation);
  }
  
  public static abstract interface SnapshotReadyCallback
  {
    public abstract void onSnapshotReady(Bitmap paramBitmap);
  }
  
  private static final class a
    extends b.a
  {
    private final GoogleMap.CancelableCallback aiF;
    
    a(GoogleMap.CancelableCallback paramCancelableCallback)
    {
      this.aiF = paramCancelableCallback;
    }
    
    public void onCancel()
    {
      this.aiF.onCancel();
    }
    
    public void onFinish()
    {
      this.aiF.onFinish();
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\maps\GoogleMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */