package com.google.android.gms.internal;

import android.content.Context;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.appstate.AppState;
import com.google.android.gms.appstate.AppStateBuffer;
import com.google.android.gms.appstate.AppStateManager.StateConflictResult;
import com.google.android.gms.appstate.AppStateManager.StateDeletedResult;
import com.google.android.gms.appstate.AppStateManager.StateListResult;
import com.google.android.gms.appstate.AppStateManager.StateLoadedResult;
import com.google.android.gms.appstate.AppStateManager.StateResult;
import com.google.android.gms.common.api.BaseImplementation.b;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.a;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.e;
import com.google.android.gms.common.internal.e.e;
import com.google.android.gms.common.internal.l;
import com.google.android.gms.common.internal.o;

public final class ib
  extends e<id>
{
  private final String Dd;
  
  public ib(Context paramContext, Looper paramLooper, GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener, String paramString, String[] paramArrayOfString)
  {
    super(paramContext, paramLooper, paramConnectionCallbacks, paramOnConnectionFailedListener, paramArrayOfString);
    this.Dd = ((String)o.i(paramString));
  }
  
  protected id I(IBinder paramIBinder)
  {
    return id.a.K(paramIBinder);
  }
  
  public void a(BaseImplementation.b<AppStateManager.StateListResult> paramb)
  {
    try
    {
      id localid = (id)gS();
      c localc = new com/google/android/gms/internal/ib$c;
      localc.<init>(paramb);
      localid.a(localc);
      return;
    }
    catch (RemoteException paramb)
    {
      for (;;)
      {
        Log.w("AppStateClient", "service died");
      }
    }
  }
  
  public void a(BaseImplementation.b<AppStateManager.StateDeletedResult> paramb, int paramInt)
  {
    try
    {
      id localid = (id)gS();
      a locala = new com/google/android/gms/internal/ib$a;
      locala.<init>(paramb);
      localid.b(locala, paramInt);
      return;
    }
    catch (RemoteException paramb)
    {
      for (;;)
      {
        Log.w("AppStateClient", "service died");
      }
    }
  }
  
  public void a(BaseImplementation.b<AppStateManager.StateResult> paramb, int paramInt, String paramString, byte[] paramArrayOfByte)
  {
    try
    {
      id localid = (id)gS();
      e locale = new com/google/android/gms/internal/ib$e;
      locale.<init>(paramb);
      localid.a(locale, paramInt, paramString, paramArrayOfByte);
      return;
    }
    catch (RemoteException paramb)
    {
      for (;;)
      {
        Log.w("AppStateClient", "service died");
      }
    }
  }
  
  public void a(BaseImplementation.b<AppStateManager.StateResult> paramb, int paramInt, byte[] paramArrayOfByte)
  {
    if (paramb == null) {
      paramb = null;
    }
    try
    {
      for (;;)
      {
        ((id)gS()).a(paramb, paramInt, paramArrayOfByte);
        return;
        paramb = new e(paramb);
      }
    }
    catch (RemoteException paramb)
    {
      for (;;)
      {
        Log.w("AppStateClient", "service died");
      }
    }
  }
  
  protected void a(l paraml, e.e parame)
    throws RemoteException
  {
    paraml.a(parame, 6171000, getContext().getPackageName(), this.Dd, gR());
  }
  
  public void b(BaseImplementation.b<Status> paramb)
  {
    try
    {
      id localid = (id)gS();
      g localg = new com/google/android/gms/internal/ib$g;
      localg.<init>(paramb);
      localid.b(localg);
      return;
    }
    catch (RemoteException paramb)
    {
      for (;;)
      {
        Log.w("AppStateClient", "service died");
      }
    }
  }
  
  public void b(BaseImplementation.b<AppStateManager.StateResult> paramb, int paramInt)
  {
    try
    {
      id localid = (id)gS();
      e locale = new com/google/android/gms/internal/ib$e;
      locale.<init>(paramb);
      localid.a(locale, paramInt);
      return;
    }
    catch (RemoteException paramb)
    {
      for (;;)
      {
        Log.w("AppStateClient", "service died");
      }
    }
  }
  
  protected void c(String... paramVarArgs)
  {
    int i = 0;
    boolean bool = false;
    while (i < paramVarArgs.length)
    {
      if (paramVarArgs[i].equals("https://www.googleapis.com/auth/appstate")) {
        bool = true;
      }
      i++;
    }
    o.a(bool, String.format("App State APIs requires %s to function.", new Object[] { "https://www.googleapis.com/auth/appstate" }));
  }
  
  public int fq()
  {
    try
    {
      i = ((id)gS()).fq();
      return i;
    }
    catch (RemoteException localRemoteException)
    {
      for (;;)
      {
        Log.w("AppStateClient", "service died");
        int i = 2;
      }
    }
  }
  
  public int fr()
  {
    try
    {
      i = ((id)gS()).fr();
      return i;
    }
    catch (RemoteException localRemoteException)
    {
      for (;;)
      {
        Log.w("AppStateClient", "service died");
        int i = 2;
      }
    }
  }
  
  protected String getServiceDescriptor()
  {
    return "com.google.android.gms.appstate.internal.IAppStateService";
  }
  
  protected String getStartServiceAction()
  {
    return "com.google.android.gms.appstate.service.START";
  }
  
  private static final class a
    extends ia
  {
    private final BaseImplementation.b<AppStateManager.StateDeletedResult> De;
    
    public a(BaseImplementation.b<AppStateManager.StateDeletedResult> paramb)
    {
      this.De = ((BaseImplementation.b)o.b(paramb, "Result holder must not be null"));
    }
    
    public void e(int paramInt1, int paramInt2)
    {
      Status localStatus = new Status(paramInt1);
      this.De.b(new ib.b(localStatus, paramInt2));
    }
  }
  
  private static final class b
    implements AppStateManager.StateDeletedResult
  {
    private final Status CM;
    private final int Df;
    
    public b(Status paramStatus, int paramInt)
    {
      this.CM = paramStatus;
      this.Df = paramInt;
    }
    
    public int getStateKey()
    {
      return this.Df;
    }
    
    public Status getStatus()
    {
      return this.CM;
    }
  }
  
  private static final class c
    extends ia
  {
    private final BaseImplementation.b<AppStateManager.StateListResult> De;
    
    public c(BaseImplementation.b<AppStateManager.StateListResult> paramb)
    {
      this.De = ((BaseImplementation.b)o.b(paramb, "Result holder must not be null"));
    }
    
    public void a(DataHolder paramDataHolder)
    {
      this.De.b(new ib.d(paramDataHolder));
    }
  }
  
  private static final class d
    extends a
    implements AppStateManager.StateListResult
  {
    private final AppStateBuffer Dg;
    
    public d(DataHolder paramDataHolder)
    {
      super();
      this.Dg = new AppStateBuffer(paramDataHolder);
    }
    
    public AppStateBuffer getStateBuffer()
    {
      return this.Dg;
    }
  }
  
  private static final class e
    extends ia
  {
    private final BaseImplementation.b<AppStateManager.StateResult> De;
    
    public e(BaseImplementation.b<AppStateManager.StateResult> paramb)
    {
      this.De = ((BaseImplementation.b)o.b(paramb, "Result holder must not be null"));
    }
    
    public void a(int paramInt, DataHolder paramDataHolder)
    {
      this.De.b(new ib.f(paramInt, paramDataHolder));
    }
  }
  
  private static final class f
    extends a
    implements AppStateManager.StateConflictResult, AppStateManager.StateLoadedResult, AppStateManager.StateResult
  {
    private final int Df;
    private final AppStateBuffer Dg;
    
    public f(int paramInt, DataHolder paramDataHolder)
    {
      super();
      this.Df = paramInt;
      this.Dg = new AppStateBuffer(paramDataHolder);
    }
    
    private boolean fs()
    {
      if (this.CM.getStatusCode() == 2000) {}
      for (boolean bool = true;; bool = false) {
        return bool;
      }
    }
    
    public AppStateManager.StateConflictResult getConflictResult()
    {
      if (fs()) {}
      for (f localf = this;; localf = null) {
        return localf;
      }
    }
    
    public AppStateManager.StateLoadedResult getLoadedResult()
    {
      f localf = this;
      if (fs()) {
        localf = null;
      }
      return localf;
    }
    
    public byte[] getLocalData()
    {
      if (this.Dg.getCount() == 0) {}
      for (byte[] arrayOfByte = null;; arrayOfByte = this.Dg.get(0).getLocalData()) {
        return arrayOfByte;
      }
    }
    
    public String getResolvedVersion()
    {
      if (this.Dg.getCount() == 0) {}
      for (String str = null;; str = this.Dg.get(0).getConflictVersion()) {
        return str;
      }
    }
    
    public byte[] getServerData()
    {
      if (this.Dg.getCount() == 0) {}
      for (byte[] arrayOfByte = null;; arrayOfByte = this.Dg.get(0).getConflictData()) {
        return arrayOfByte;
      }
    }
    
    public int getStateKey()
    {
      return this.Df;
    }
    
    public void release()
    {
      this.Dg.close();
    }
  }
  
  private static final class g
    extends ia
  {
    private final BaseImplementation.b<Status> De;
    
    public g(BaseImplementation.b<Status> paramb)
    {
      this.De = ((BaseImplementation.b)o.b(paramb, "Holder must not be null"));
    }
    
    public void fp()
    {
      Status localStatus = new Status(0);
      this.De.b(localStatus);
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\internal\ib.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */