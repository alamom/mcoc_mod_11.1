package com.google.android.gms.common.api;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.content.IntentSender.SendIntentException;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.LoaderManager;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.Loader;
import android.util.Log;
import android.util.SparseArray;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.internal.o;
import java.util.List;

public class d
  extends Fragment
  implements DialogInterface.OnCancelListener, LoaderManager.LoaderCallbacks<ConnectionResult>
{
  private boolean JB;
  private int JC = -1;
  private ConnectionResult JD;
  private final Handler JE = new Handler(Looper.getMainLooper());
  private final SparseArray<b> JF = new SparseArray();
  
  public static d a(FragmentActivity paramFragmentActivity)
  {
    o.aT("Must be called from main thread of process");
    FragmentManager localFragmentManager = paramFragmentActivity.getSupportFragmentManager();
    try
    {
      d locald = (d)localFragmentManager.findFragmentByTag("GmsSupportLifecycleFragment");
      if (locald != null)
      {
        paramFragmentActivity = locald;
        if (!locald.isRemoving()) {}
      }
      else
      {
        paramFragmentActivity = new d();
        localFragmentManager.beginTransaction().add(paramFragmentActivity, "GmsSupportLifecycleFragment").commit();
        localFragmentManager.executePendingTransactions();
      }
      return paramFragmentActivity;
    }
    catch (ClassCastException paramFragmentActivity)
    {
      throw new IllegalStateException("Fragment with tag GmsSupportLifecycleFragment is not a SupportLifecycleFragment", paramFragmentActivity);
    }
  }
  
  private void a(int paramInt, ConnectionResult paramConnectionResult)
  {
    if (!this.JB)
    {
      this.JB = true;
      this.JC = paramInt;
      this.JD = paramConnectionResult;
      this.JE.post(new c(paramInt, paramConnectionResult));
    }
  }
  
  private void an(int paramInt)
  {
    if (paramInt == this.JC) {
      gu();
    }
  }
  
  private void b(int paramInt, ConnectionResult paramConnectionResult)
  {
    Log.w("GmsSupportLifecycleFragment", "Unresolved error while connecting client. Stopping auto-manage.");
    Object localObject = (b)this.JF.get(paramInt);
    if (localObject != null)
    {
      al(paramInt);
      localObject = ((b)localObject).JJ;
      if (localObject != null) {
        ((GoogleApiClient.OnConnectionFailedListener)localObject).onConnectionFailed(paramConnectionResult);
      }
    }
    gu();
  }
  
  private void gu()
  {
    int i = 0;
    this.JB = false;
    this.JC = -1;
    this.JD = null;
    LoaderManager localLoaderManager = getLoaderManager();
    while (i < this.JF.size())
    {
      int j = this.JF.keyAt(i);
      a locala = am(j);
      if (locala != null) {
        locala.gv();
      }
      localLoaderManager.initLoader(j, null, this);
      i++;
    }
  }
  
  public void a(int paramInt, GoogleApiClient paramGoogleApiClient, GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener)
  {
    o.b(paramGoogleApiClient, "GoogleApiClient instance cannot be null");
    if (this.JF.indexOfKey(paramInt) < 0) {}
    for (boolean bool = true;; bool = false)
    {
      o.a(bool, "Already managing a GoogleApiClient with id " + paramInt);
      paramGoogleApiClient = new b(paramGoogleApiClient, paramOnConnectionFailedListener, null);
      this.JF.put(paramInt, paramGoogleApiClient);
      if (getActivity() != null) {
        getLoaderManager().initLoader(paramInt, null, this);
      }
      return;
    }
  }
  
  public void a(Loader<ConnectionResult> paramLoader, ConnectionResult paramConnectionResult)
  {
    if (paramConnectionResult.isSuccess()) {
      an(paramLoader.getId());
    }
    for (;;)
    {
      return;
      a(paramLoader.getId(), paramConnectionResult);
    }
  }
  
  public GoogleApiClient ak(int paramInt)
  {
    if (getActivity() != null)
    {
      localObject = am(paramInt);
      if (localObject == null) {}
    }
    for (Object localObject = ((a)localObject).JG;; localObject = null) {
      return (GoogleApiClient)localObject;
    }
  }
  
  public void al(int paramInt)
  {
    getLoaderManager().destroyLoader(paramInt);
    this.JF.remove(paramInt);
  }
  
  a am(int paramInt)
  {
    try
    {
      a locala = (a)getLoaderManager().getLoader(paramInt);
      return locala;
    }
    catch (ClassCastException localClassCastException)
    {
      throw new IllegalStateException("Unknown loader in SupportLifecycleFragment", localClassCastException);
    }
  }
  
  public void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    int i = 1;
    switch (paramInt1)
    {
    default: 
      paramInt1 = 0;
      label30:
      if (paramInt1 != 0) {
        gu();
      }
      break;
    }
    for (;;)
    {
      return;
      if (GooglePlayServicesUtil.isGooglePlayServicesAvailable(getActivity()) != 0) {
        break;
      }
      paramInt1 = i;
      break label30;
      if (paramInt2 != -1) {
        break;
      }
      paramInt1 = i;
      break label30;
      b(this.JC, this.JD);
    }
  }
  
  public void onAttach(Activity paramActivity)
  {
    super.onAttach(paramActivity);
    int i = 0;
    if (i < this.JF.size())
    {
      int j = this.JF.keyAt(i);
      paramActivity = am(j);
      if ((paramActivity != null) && (((b)this.JF.valueAt(i)).JG != paramActivity.JG)) {
        getLoaderManager().restartLoader(j, null, this);
      }
      for (;;)
      {
        i++;
        break;
        getLoaderManager().initLoader(j, null, this);
      }
    }
  }
  
  public void onCancel(DialogInterface paramDialogInterface)
  {
    b(this.JC, this.JD);
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    if (paramBundle != null)
    {
      this.JB = paramBundle.getBoolean("resolving_error", false);
      this.JC = paramBundle.getInt("failed_client_id", -1);
      if (this.JC >= 0) {
        this.JD = new ConnectionResult(paramBundle.getInt("failed_status"), (PendingIntent)paramBundle.getParcelable("failed_resolution"));
      }
    }
  }
  
  public Loader<ConnectionResult> onCreateLoader(int paramInt, Bundle paramBundle)
  {
    return new a(getActivity(), ((b)this.JF.get(paramInt)).JG);
  }
  
  public void onLoaderReset(Loader<ConnectionResult> paramLoader)
  {
    if (paramLoader.getId() == this.JC) {
      gu();
    }
  }
  
  public void onSaveInstanceState(Bundle paramBundle)
  {
    super.onSaveInstanceState(paramBundle);
    paramBundle.putBoolean("resolving_error", this.JB);
    if (this.JC >= 0)
    {
      paramBundle.putInt("failed_client_id", this.JC);
      paramBundle.putInt("failed_status", this.JD.getErrorCode());
      paramBundle.putParcelable("failed_resolution", this.JD.getResolution());
    }
  }
  
  public void onStart()
  {
    super.onStart();
    if (!this.JB) {
      for (int i = 0; i < this.JF.size(); i++) {
        getLoaderManager().initLoader(this.JF.keyAt(i), null, this);
      }
    }
  }
  
  static class a
    extends Loader<ConnectionResult>
    implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener
  {
    public final GoogleApiClient JG;
    private boolean JH;
    private ConnectionResult JI;
    
    public a(Context paramContext, GoogleApiClient paramGoogleApiClient)
    {
      super();
      this.JG = paramGoogleApiClient;
    }
    
    private void a(ConnectionResult paramConnectionResult)
    {
      this.JI = paramConnectionResult;
      if ((isStarted()) && (!isAbandoned())) {
        deliverResult(paramConnectionResult);
      }
    }
    
    public void gv()
    {
      if (this.JH)
      {
        this.JH = false;
        if ((isStarted()) && (!isAbandoned())) {
          this.JG.connect();
        }
      }
    }
    
    public void onConnected(Bundle paramBundle)
    {
      this.JH = false;
      a(ConnectionResult.HE);
    }
    
    public void onConnectionFailed(ConnectionResult paramConnectionResult)
    {
      this.JH = true;
      a(paramConnectionResult);
    }
    
    public void onConnectionSuspended(int paramInt) {}
    
    protected void onReset()
    {
      this.JI = null;
      this.JH = false;
      this.JG.unregisterConnectionCallbacks(this);
      this.JG.unregisterConnectionFailedListener(this);
      this.JG.disconnect();
    }
    
    protected void onStartLoading()
    {
      super.onStartLoading();
      this.JG.registerConnectionCallbacks(this);
      this.JG.registerConnectionFailedListener(this);
      if (this.JI != null) {
        deliverResult(this.JI);
      }
      if ((!this.JG.isConnected()) && (!this.JG.isConnecting()) && (!this.JH)) {
        this.JG.connect();
      }
    }
    
    protected void onStopLoading()
    {
      this.JG.disconnect();
    }
  }
  
  private static class b
  {
    public final GoogleApiClient JG;
    public final GoogleApiClient.OnConnectionFailedListener JJ;
    
    private b(GoogleApiClient paramGoogleApiClient, GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener)
    {
      this.JG = paramGoogleApiClient;
      this.JJ = paramOnConnectionFailedListener;
    }
  }
  
  private class c
    implements Runnable
  {
    private final int JK;
    private final ConnectionResult JL;
    
    public c(int paramInt, ConnectionResult paramConnectionResult)
    {
      this.JK = paramInt;
      this.JL = paramConnectionResult;
    }
    
    public void run()
    {
      if (this.JL.hasResolution()) {}
      for (;;)
      {
        try
        {
          int i = d.this.getActivity().getSupportFragmentManager().getFragments().indexOf(d.this);
          this.JL.startResolutionForResult(d.this.getActivity(), (i + 1 << 16) + 1);
          return;
        }
        catch (IntentSender.SendIntentException localSendIntentException)
        {
          d.a(d.this);
          continue;
        }
        if (GooglePlayServicesUtil.isUserRecoverableError(this.JL.getErrorCode())) {
          GooglePlayServicesUtil.showErrorDialogFragment(this.JL.getErrorCode(), d.this.getActivity(), d.this, 2, d.this);
        } else {
          d.a(d.this, this.JK, this.JL);
        }
      }
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\common\api\d.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */