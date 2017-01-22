package com.google.android.gms.drive.internal;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import com.google.android.gms.common.api.BaseImplementation.a;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.e;
import com.google.android.gms.common.internal.e.e;
import com.google.android.gms.common.internal.l;
import com.google.android.gms.common.internal.o;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.events.c;
import com.google.android.gms.drive.events.d;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class q
  extends e<ab>
{
  private final String Dd;
  private final String IM;
  private final Bundle OA;
  private final boolean OB;
  private DriveId OC;
  private DriveId OD;
  final GoogleApiClient.ConnectionCallbacks OE;
  final Map<DriveId, Map<c, y>> OF = new HashMap();
  
  public q(Context paramContext, Looper paramLooper, ClientSettings paramClientSettings, GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener, String[] paramArrayOfString, Bundle paramBundle)
  {
    super(paramContext, paramLooper, paramConnectionCallbacks, paramOnConnectionFailedListener, paramArrayOfString);
    this.Dd = ((String)o.b(paramClientSettings.getAccountNameOrDefault(), "Must call Api.ClientBuilder.setAccountName()"));
    this.IM = paramClientSettings.getRealClientPackageName();
    this.OE = paramConnectionCallbacks;
    this.OA = paramBundle;
    paramLooper = new Intent("com.google.android.gms.drive.events.HANDLE_EVENT");
    paramLooper.setPackage(paramContext.getPackageName());
    paramContext = paramContext.getPackageManager().queryIntentServices(paramLooper, 0);
    switch (paramContext.size())
    {
    default: 
      throw new IllegalStateException("AndroidManifest.xml can only define one service that handles the " + paramLooper.getAction() + " action");
    }
    for (this.OB = false;; this.OB = true)
    {
      return;
      paramContext = ((ResolveInfo)paramContext.get(0)).serviceInfo;
      if (!paramContext.exported) {
        throw new IllegalStateException("Drive event service " + paramContext.name + " must be exported in AndroidManifest.xml");
      }
    }
  }
  
  protected ab T(IBinder paramIBinder)
  {
    return ab.a.U(paramIBinder);
  }
  
  PendingResult<Status> a(GoogleApiClient paramGoogleApiClient, final DriveId paramDriveId, final int paramInt)
  {
    o.b(d.a(paramInt, paramDriveId), "id");
    o.i("eventService");
    o.a(isConnected(), "Client must be connected");
    if (!this.OB) {
      throw new IllegalStateException("Application must define an exported DriveEventService subclass in AndroidManifest.xml to add event subscriptions");
    }
    paramGoogleApiClient.b(new p.a()
    {
      protected void a(q paramAnonymousq)
        throws RemoteException
      {
        paramAnonymousq.hY().a(new AddEventListenerRequest(paramDriveId, paramInt), null, null, new bb(this));
      }
    });
  }
  
  PendingResult<Status> a(GoogleApiClient paramGoogleApiClient, DriveId paramDriveId, int paramInt, c paramc)
  {
    o.b(d.a(paramInt, paramDriveId), "id");
    o.b(paramc, "listener");
    o.a(isConnected(), "Client must be connected");
    for (;;)
    {
      synchronized (this.OF)
      {
        Object localObject = (Map)this.OF.get(paramDriveId);
        if (localObject == null)
        {
          localObject = new java/util/HashMap;
          ((HashMap)localObject).<init>();
          this.OF.put(paramDriveId, localObject);
          y localy = (y)((Map)localObject).get(paramc);
          if (localy == null)
          {
            localy = new com/google/android/gms/drive/internal/y;
            localy.<init>(getLooper(), getContext(), paramInt, paramc);
            ((Map)localObject).put(paramc, localy);
            paramc = localy;
            paramc.bq(paramInt);
            localObject = new com/google/android/gms/drive/internal/q$1;
            ((1)localObject).<init>(this, paramDriveId, paramInt, paramc);
            paramGoogleApiClient = paramGoogleApiClient.b((BaseImplementation.a)localObject);
            return paramGoogleApiClient;
          }
          paramc = localy;
          if (localy.br(paramInt))
          {
            paramDriveId = new com/google/android/gms/drive/internal/o$m;
            paramDriveId.<init>(paramGoogleApiClient, Status.Jv);
            paramGoogleApiClient = paramDriveId;
          }
        }
      }
    }
  }
  
  protected void a(int paramInt, IBinder paramIBinder, Bundle paramBundle)
  {
    if (paramBundle != null)
    {
      paramBundle.setClassLoader(getClass().getClassLoader());
      this.OC = ((DriveId)paramBundle.getParcelable("com.google.android.gms.drive.root_id"));
      this.OD = ((DriveId)paramBundle.getParcelable("com.google.android.gms.drive.appdata_id"));
    }
    super.a(paramInt, paramIBinder, paramBundle);
  }
  
  protected void a(l paraml, e.e parame)
    throws RemoteException
  {
    String str = getContext().getPackageName();
    o.i(parame);
    o.i(str);
    o.i(gR());
    Bundle localBundle = new Bundle();
    if (!str.equals(this.IM)) {
      localBundle.putString("proxy_package_name", this.IM);
    }
    localBundle.putAll(this.OA);
    paraml.a(parame, 6171000, str, gR(), this.Dd, localBundle);
  }
  
  PendingResult<Status> b(GoogleApiClient paramGoogleApiClient, final DriveId paramDriveId, final int paramInt)
  {
    o.b(d.a(paramInt, paramDriveId), "id");
    o.i("eventService");
    o.a(isConnected(), "Client must be connected");
    paramGoogleApiClient.b(new p.a()
    {
      protected void a(q paramAnonymousq)
        throws RemoteException
      {
        paramAnonymousq.hY().a(new RemoveEventListenerRequest(paramDriveId, paramInt), null, null, new bb(this));
      }
    });
  }
  
  PendingResult<Status> b(GoogleApiClient paramGoogleApiClient, DriveId paramDriveId, int paramInt, c paramc)
  {
    o.b(d.a(paramInt, paramDriveId), "id");
    o.a(isConnected(), "Client must be connected");
    o.b(paramc, "listener");
    for (;;)
    {
      synchronized (this.OF)
      {
        localObject = (Map)this.OF.get(paramDriveId);
        if (localObject == null)
        {
          paramDriveId = new com/google/android/gms/drive/internal/o$m;
          paramDriveId.<init>(paramGoogleApiClient, Status.Jv);
          paramGoogleApiClient = paramDriveId;
          return paramGoogleApiClient;
        }
        paramc = (y)((Map)localObject).remove(paramc);
        if (paramc == null)
        {
          paramDriveId = new com/google/android/gms/drive/internal/o$m;
          paramDriveId.<init>(paramGoogleApiClient, Status.Jv);
          paramGoogleApiClient = paramDriveId;
        }
      }
      if (((Map)localObject).isEmpty()) {
        this.OF.remove(paramDriveId);
      }
      Object localObject = new com/google/android/gms/drive/internal/q$2;
      ((2)localObject).<init>(this, paramDriveId, paramInt, paramc);
      paramGoogleApiClient = paramGoogleApiClient.b((BaseImplementation.a)localObject);
    }
  }
  
  public void disconnect()
  {
    ab localab = (ab)gS();
    if (localab != null) {}
    try
    {
      DisconnectRequest localDisconnectRequest = new com/google/android/gms/drive/internal/DisconnectRequest;
      localDisconnectRequest.<init>();
      localab.a(localDisconnectRequest);
      super.disconnect();
      this.OF.clear();
      return;
    }
    catch (RemoteException localRemoteException)
    {
      for (;;) {}
    }
  }
  
  protected String getServiceDescriptor()
  {
    return "com.google.android.gms.drive.internal.IDriveService";
  }
  
  protected String getStartServiceAction()
  {
    return "com.google.android.gms.drive.ApiService.START";
  }
  
  public ab hY()
  {
    return (ab)gS();
  }
  
  public DriveId hZ()
  {
    return this.OC;
  }
  
  public DriveId ia()
  {
    return this.OD;
  }
  
  public boolean ib()
  {
    return this.OB;
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\drive\internal\q.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */