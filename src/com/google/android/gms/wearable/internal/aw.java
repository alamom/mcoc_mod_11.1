package com.google.android.gms.wearable.internal;

import android.content.Context;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Looper;
import android.os.ParcelFileDescriptor;
import android.os.ParcelFileDescriptor.AutoCloseOutputStream;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.api.BaseImplementation.b;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.e;
import com.google.android.gms.common.internal.e.e;
import com.google.android.gms.common.internal.l;
import com.google.android.gms.wearable.Asset;
import com.google.android.gms.wearable.DataApi.DataItemResult;
import com.google.android.gms.wearable.DataApi.DataListener;
import com.google.android.gms.wearable.DataApi.DeleteDataItemsResult;
import com.google.android.gms.wearable.DataApi.GetFdForAssetResult;
import com.google.android.gms.wearable.DataItemAsset;
import com.google.android.gms.wearable.DataItemBuffer;
import com.google.android.gms.wearable.MessageApi.MessageListener;
import com.google.android.gms.wearable.MessageApi.SendMessageResult;
import com.google.android.gms.wearable.NodeApi.GetConnectedNodesResult;
import com.google.android.gms.wearable.NodeApi.GetLocalNodeResult;
import com.google.android.gms.wearable.NodeApi.NodeListener;
import com.google.android.gms.wearable.PutDataRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class aw
  extends e<af>
{
  private final ExecutorService aqA = Executors.newCachedThreadPool();
  private final HashMap<DataApi.DataListener, ax> avQ = new HashMap();
  private final HashMap<MessageApi.MessageListener, ax> avR = new HashMap();
  private final HashMap<NodeApi.NodeListener, ax> avS = new HashMap();
  
  public aw(Context paramContext, Looper paramLooper, GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener)
  {
    super(paramContext, paramLooper, paramConnectionCallbacks, paramOnConnectionFailedListener, new String[0]);
  }
  
  private FutureTask<Boolean> a(final ParcelFileDescriptor paramParcelFileDescriptor, final byte[] paramArrayOfByte)
  {
    new FutureTask(new Callable()
    {
      public Boolean qa()
      {
        if (Log.isLoggable("WearableClient", 3)) {
          Log.d("WearableClient", "processAssets: writing data to FD : " + paramParcelFileDescriptor);
        }
        localAutoCloseOutputStream = new ParcelFileDescriptor.AutoCloseOutputStream(paramParcelFileDescriptor);
        try
        {
          localAutoCloseOutputStream.write(paramArrayOfByte);
          localAutoCloseOutputStream.flush();
          if (Log.isLoggable("WearableClient", 3))
          {
            localObject1 = new java/lang/StringBuilder;
            ((StringBuilder)localObject1).<init>();
            Log.d("WearableClient", "processAssets: wrote data: " + paramParcelFileDescriptor);
          }
          localObject1 = Boolean.valueOf(true);
        }
        catch (IOException localIOException1)
        {
          for (;;)
          {
            Object localObject1;
            label148:
            Object localObject2 = new java/lang/StringBuilder;
            ((StringBuilder)localObject2).<init>();
            Log.w("WearableClient", "processAssets: writing data failed: " + paramParcelFileDescriptor);
            try
            {
              if (Log.isLoggable("WearableClient", 3))
              {
                localObject2 = new java/lang/StringBuilder;
                ((StringBuilder)localObject2).<init>();
                Log.d("WearableClient", "processAssets: closing: " + paramParcelFileDescriptor);
              }
              localAutoCloseOutputStream.close();
            }
            catch (IOException localIOException2)
            {
              for (;;) {}
            }
            localObject2 = Boolean.valueOf(false);
          }
        }
        finally
        {
          try
          {
            StringBuilder localStringBuilder;
            if (Log.isLoggable("WearableClient", 3))
            {
              localStringBuilder = new java/lang/StringBuilder;
              localStringBuilder.<init>();
              Log.d("WearableClient", "processAssets: closing: " + paramParcelFileDescriptor);
            }
            localAutoCloseOutputStream.close();
          }
          catch (IOException localIOException3)
          {
            for (;;) {}
          }
        }
        try
        {
          if (Log.isLoggable("WearableClient", 3))
          {
            localStringBuilder = new java/lang/StringBuilder;
            localStringBuilder.<init>();
            Log.d("WearableClient", "processAssets: closing: " + paramParcelFileDescriptor);
          }
          localAutoCloseOutputStream.close();
        }
        catch (IOException localIOException4)
        {
          break label148;
        }
        return (Boolean)localObject1;
      }
    });
  }
  
  protected void a(int paramInt, IBinder paramIBinder, Bundle paramBundle)
  {
    if (Log.isLoggable("WearableClient", 2)) {
      Log.d("WearableClient", "onPostInitHandler: statusCode " + paramInt);
    }
    Object localObject1;
    Object localObject3;
    Object localObject2;
    Object localObject4;
    if (paramInt == 0) {
      try
      {
        a local1 = new com/google/android/gms/wearable/internal/aw$1;
        local1.<init>(this);
        if (Log.isLoggable("WearableClient", 2))
        {
          localObject1 = new java/lang/StringBuilder;
          ((StringBuilder)localObject1).<init>();
          Log.d("WearableClient", "onPostInitHandler: service " + paramIBinder);
        }
        localObject1 = af.a.bT(paramIBinder);
        localObject3 = this.avQ.entrySet().iterator();
        while (((Iterator)localObject3).hasNext())
        {
          localObject2 = (Map.Entry)((Iterator)localObject3).next();
          if (Log.isLoggable("WearableClient", 2))
          {
            localObject4 = new java/lang/StringBuilder;
            ((StringBuilder)localObject4).<init>();
            Log.d("WearableClient", "onPostInitHandler: adding Data listener " + ((Map.Entry)localObject2).getValue());
          }
          localObject4 = new com/google/android/gms/wearable/internal/b;
          ((b)localObject4).<init>((ax)((Map.Entry)localObject2).getValue());
          ((af)localObject1).a(local1, (b)localObject4);
          continue;
          Log.d("WearableClient", "WearableClientImpl.onPostInitHandler: done");
        }
      }
      catch (RemoteException localRemoteException)
      {
        Log.d("WearableClient", "WearableClientImpl.onPostInitHandler: error while adding listener", localRemoteException);
      }
    }
    for (;;)
    {
      super.a(paramInt, paramIBinder, paramBundle);
      return;
      localObject2 = this.avR.entrySet().iterator();
      while (((Iterator)localObject2).hasNext())
      {
        localObject3 = (Map.Entry)((Iterator)localObject2).next();
        if (Log.isLoggable("WearableClient", 2))
        {
          localObject4 = new java/lang/StringBuilder;
          ((StringBuilder)localObject4).<init>();
          Log.d("WearableClient", "onPostInitHandler: adding Message listener " + ((Map.Entry)localObject3).getValue());
        }
        localObject4 = new com/google/android/gms/wearable/internal/b;
        ((b)localObject4).<init>((ax)((Map.Entry)localObject3).getValue());
        ((af)localObject1).a(localRemoteException, (b)localObject4);
      }
      localObject2 = this.avS.entrySet().iterator();
      while (((Iterator)localObject2).hasNext())
      {
        localObject3 = (Map.Entry)((Iterator)localObject2).next();
        if (Log.isLoggable("WearableClient", 2))
        {
          localObject4 = new java/lang/StringBuilder;
          ((StringBuilder)localObject4).<init>();
          Log.d("WearableClient", "onPostInitHandler: adding Node listener " + ((Map.Entry)localObject3).getValue());
        }
        localObject4 = new com/google/android/gms/wearable/internal/b;
        ((b)localObject4).<init>((ax)((Map.Entry)localObject3).getValue());
        ((af)localObject1).a(localRemoteException, (b)localObject4);
      }
    }
  }
  
  public void a(final BaseImplementation.b<DataApi.DataItemResult> paramb, Uri paramUri)
    throws RemoteException
  {
    ((af)gS()).a(new a()
    {
      public void a(x paramAnonymousx)
      {
        paramb.b(new f.a(new Status(paramAnonymousx.statusCode), paramAnonymousx.avA));
      }
    }, paramUri);
  }
  
  public void a(final BaseImplementation.b<DataApi.GetFdForAssetResult> paramb, Asset paramAsset)
    throws RemoteException
  {
    ((af)gS()).a(new a()
    {
      public void a(z paramAnonymousz)
      {
        paramb.b(new f.c(new Status(paramAnonymousz.statusCode), paramAnonymousz.avB));
      }
    }, paramAsset);
  }
  
  public void a(BaseImplementation.b<Status> paramb, DataApi.DataListener paramDataListener)
    throws RemoteException
  {
    for (;;)
    {
      synchronized (this.avQ)
      {
        paramDataListener = (ae)this.avQ.remove(paramDataListener);
        if (paramDataListener == null)
        {
          paramb.b(new Status(4002));
          return;
        }
      }
      a(paramb, paramDataListener);
    }
  }
  
  public void a(final BaseImplementation.b<Status> paramb, final DataApi.DataListener paramDataListener, IntentFilter[] arg3)
    throws RemoteException
  {
    ax localax = ax.a(paramDataListener, ???);
    synchronized (this.avQ)
    {
      if (this.avQ.get(paramDataListener) != null)
      {
        paramDataListener = new com/google/android/gms/common/api/Status;
        paramDataListener.<init>(4001);
        paramb.b(paramDataListener);
        return;
      }
      this.avQ.put(paramDataListener, localax);
      ((af)gS()).a(new a()new b
      {
        public void a(Status paramAnonymousStatus)
        {
          if (!paramAnonymousStatus.isSuccess()) {}
          synchronized (aw.b(aw.this))
          {
            aw.b(aw.this).remove(paramDataListener);
            paramb.b(paramAnonymousStatus);
            return;
          }
        }
      }, new b(localax));
    }
  }
  
  public void a(BaseImplementation.b<DataApi.GetFdForAssetResult> paramb, DataItemAsset paramDataItemAsset)
    throws RemoteException
  {
    a(paramb, Asset.createFromRef(paramDataItemAsset.getId()));
  }
  
  public void a(BaseImplementation.b<Status> paramb, MessageApi.MessageListener paramMessageListener)
    throws RemoteException
  {
    synchronized (this.avR)
    {
      paramMessageListener = (ae)this.avR.remove(paramMessageListener);
      if (paramMessageListener == null)
      {
        paramMessageListener = new com/google/android/gms/common/api/Status;
        paramMessageListener.<init>(4002);
        paramb.b(paramMessageListener);
        return;
      }
      a(paramb, paramMessageListener);
    }
  }
  
  public void a(final BaseImplementation.b<Status> paramb, final MessageApi.MessageListener paramMessageListener, IntentFilter[] arg3)
    throws RemoteException
  {
    ax localax = ax.a(paramMessageListener, ???);
    synchronized (this.avR)
    {
      if (this.avR.get(paramMessageListener) != null)
      {
        paramMessageListener = new com/google/android/gms/common/api/Status;
        paramMessageListener.<init>(4001);
        paramb.b(paramMessageListener);
        return;
      }
      this.avR.put(paramMessageListener, localax);
      ((af)gS()).a(new a()new b
      {
        public void a(Status paramAnonymousStatus)
        {
          if (!paramAnonymousStatus.isSuccess()) {}
          synchronized (aw.c(aw.this))
          {
            aw.c(aw.this).remove(paramMessageListener);
            paramb.b(paramAnonymousStatus);
            return;
          }
        }
      }, new b(localax));
    }
  }
  
  public void a(final BaseImplementation.b<Status> paramb, final NodeApi.NodeListener paramNodeListener)
    throws RemoteException, RemoteException
  {
    ax localax = ax.a(paramNodeListener);
    synchronized (this.avS)
    {
      if (this.avS.get(paramNodeListener) != null)
      {
        paramNodeListener = new com/google/android/gms/common/api/Status;
        paramNodeListener.<init>(4001);
        paramb.b(paramNodeListener);
        return;
      }
      this.avS.put(paramNodeListener, localax);
      ((af)gS()).a(new a()new b
      {
        public void a(Status paramAnonymousStatus)
        {
          if (!paramAnonymousStatus.isSuccess()) {}
          synchronized (aw.d(aw.this))
          {
            aw.d(aw.this).remove(paramNodeListener);
            paramb.b(paramAnonymousStatus);
            return;
          }
        }
      }, new b(localax));
    }
  }
  
  public void a(BaseImplementation.b<DataApi.DataItemResult> paramb, PutDataRequest paramPutDataRequest)
    throws RemoteException
  {
    Object localObject2 = paramPutDataRequest.getAssets().entrySet().iterator();
    while (((Iterator)localObject2).hasNext())
    {
      localObject1 = (Asset)((Map.Entry)((Iterator)localObject2).next()).getValue();
      if ((((Asset)localObject1).getData() == null) && (((Asset)localObject1).getDigest() == null) && (((Asset)localObject1).getFd() == null) && (((Asset)localObject1).getUri() == null)) {
        throw new IllegalArgumentException("Put for " + paramPutDataRequest.getUri() + " contains invalid asset: " + localObject1);
      }
    }
    localObject2 = PutDataRequest.k(paramPutDataRequest.getUri());
    ((PutDataRequest)localObject2).setData(paramPutDataRequest.getData());
    Object localObject1 = new ArrayList();
    Object localObject3 = paramPutDataRequest.getAssets().entrySet().iterator();
    Object localObject4;
    while (((Iterator)localObject3).hasNext())
    {
      localObject4 = (Map.Entry)((Iterator)localObject3).next();
      Asset localAsset = (Asset)((Map.Entry)localObject4).getValue();
      if (localAsset.getData() == null) {
        ((PutDataRequest)localObject2).putAsset((String)((Map.Entry)localObject4).getKey(), (Asset)((Map.Entry)localObject4).getValue());
      } else {
        try
        {
          ParcelFileDescriptor[] arrayOfParcelFileDescriptor = ParcelFileDescriptor.createPipe();
          if (Log.isLoggable("WearableClient", 3)) {
            Log.d("WearableClient", "processAssets: replacing data with FD in asset: " + localAsset + " read:" + arrayOfParcelFileDescriptor[0] + " write:" + arrayOfParcelFileDescriptor[1]);
          }
          ((PutDataRequest)localObject2).putAsset((String)((Map.Entry)localObject4).getKey(), Asset.createFromFd(arrayOfParcelFileDescriptor[0]));
          localObject4 = a(arrayOfParcelFileDescriptor[1], localAsset.getData());
          ((List)localObject1).add(localObject4);
          this.aqA.submit((Runnable)localObject4);
        }
        catch (IOException paramb)
        {
          throw new IllegalStateException("Unable to create ParcelFileDescriptor for asset in request: " + paramPutDataRequest, paramb);
        }
      }
    }
    try
    {
      localObject3 = (af)gS();
      localObject4 = new com/google/android/gms/wearable/internal/aw$a;
      ((a)localObject4).<init>(paramb, (List)localObject1);
      ((af)localObject3).a((ad)localObject4, (PutDataRequest)localObject2);
      return;
    }
    catch (NullPointerException paramb)
    {
      throw new IllegalStateException("Unable to putDataItem: " + paramPutDataRequest, paramb);
    }
  }
  
  public void a(final BaseImplementation.b<Status> paramb, ae paramae)
    throws RemoteException
  {
    ((af)gS()).a(new a()new aq
    {
      public void a(Status paramAnonymousStatus)
      {
        paramb.b(paramAnonymousStatus);
      }
    }, new aq(paramae));
  }
  
  public void a(final BaseImplementation.b<MessageApi.SendMessageResult> paramb, String paramString1, String paramString2, byte[] paramArrayOfByte)
    throws RemoteException
  {
    ((af)gS()).a(new a()
    {
      public void a(as paramAnonymousas)
      {
        paramb.b(new ag.a(new Status(paramAnonymousas.statusCode), paramAnonymousas.avO));
      }
    }, paramString1, paramString2, paramArrayOfByte);
  }
  
  protected void a(l paraml, e.e parame)
    throws RemoteException
  {
    paraml.e(parame, 6171000, getContext().getPackageName());
  }
  
  public void b(final BaseImplementation.b<DataItemBuffer> paramb, Uri paramUri)
    throws RemoteException
  {
    ((af)gS()).b(new a()
    {
      public void aa(DataHolder paramAnonymousDataHolder)
      {
        paramb.b(new DataItemBuffer(paramAnonymousDataHolder));
      }
    }, paramUri);
  }
  
  public void b(BaseImplementation.b<Status> paramb, NodeApi.NodeListener paramNodeListener)
    throws RemoteException
  {
    synchronized (this.avS)
    {
      paramNodeListener = (ae)this.avS.remove(paramNodeListener);
      if (paramNodeListener == null)
      {
        paramNodeListener = new com/google/android/gms/common/api/Status;
        paramNodeListener.<init>(4002);
        paramb.b(paramNodeListener);
        return;
      }
      a(paramb, paramNodeListener);
    }
  }
  
  protected af bU(IBinder paramIBinder)
  {
    return af.a.bT(paramIBinder);
  }
  
  public void c(final BaseImplementation.b<DataApi.DeleteDataItemsResult> paramb, Uri paramUri)
    throws RemoteException
  {
    ((af)gS()).c(new a()
    {
      public void a(p paramAnonymousp)
      {
        paramb.b(new f.b(new Status(paramAnonymousp.statusCode), paramAnonymousp.avw));
      }
    }, paramUri);
  }
  
  public void disconnect()
  {
    super.disconnect();
    this.avQ.clear();
    this.avR.clear();
    this.avS.clear();
  }
  
  protected String getServiceDescriptor()
  {
    return "com.google.android.gms.wearable.internal.IWearableService";
  }
  
  protected String getStartServiceAction()
  {
    return "com.google.android.gms.wearable.BIND";
  }
  
  public void o(final BaseImplementation.b<DataItemBuffer> paramb)
    throws RemoteException
  {
    ((af)gS()).b(new a()
    {
      public void aa(DataHolder paramAnonymousDataHolder)
      {
        paramb.b(new DataItemBuffer(paramAnonymousDataHolder));
      }
    });
  }
  
  public void p(final BaseImplementation.b<NodeApi.GetLocalNodeResult> paramb)
    throws RemoteException
  {
    ((af)gS()).c(new a()
    {
      public void a(ab paramAnonymousab)
      {
        paramb.b(new aj.b(new Status(paramAnonymousab.statusCode), paramAnonymousab.avC));
      }
    });
  }
  
  public void q(final BaseImplementation.b<NodeApi.GetConnectedNodesResult> paramb)
    throws RemoteException
  {
    ((af)gS()).d(new a()
    {
      public void a(v paramAnonymousv)
      {
        ArrayList localArrayList = new ArrayList();
        localArrayList.addAll(paramAnonymousv.avz);
        paramb.b(new aj.a(new Status(paramAnonymousv.statusCode), localArrayList));
      }
    });
  }
  
  private static class a
    extends a
  {
    private final BaseImplementation.b<DataApi.DataItemResult> De;
    private final List<FutureTask<Boolean>> avW;
    
    a(BaseImplementation.b<DataApi.DataItemResult> paramb, List<FutureTask<Boolean>> paramList)
    {
      this.De = paramb;
      this.avW = paramList;
    }
    
    public void a(ao paramao)
    {
      this.De.b(new f.a(new Status(paramao.statusCode), paramao.avA));
      if (paramao.statusCode != 0)
      {
        paramao = this.avW.iterator();
        while (paramao.hasNext()) {
          ((FutureTask)paramao.next()).cancel(true);
        }
      }
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\wearable\internal\aw.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */