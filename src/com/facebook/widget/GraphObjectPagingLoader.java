package com.facebook.widget;

import android.content.Context;
import android.os.Handler;
import android.support.v4.content.Loader;
import com.facebook.FacebookException;
import com.facebook.FacebookRequestError;
import com.facebook.Request;
import com.facebook.Request.Callback;
import com.facebook.RequestBatch;
import com.facebook.Response;
import com.facebook.Response.PagingDirection;
import com.facebook.internal.CacheableRequestBatch;
import com.facebook.model.GraphObject;
import com.facebook.model.GraphObjectList;
import java.util.Collection;

class GraphObjectPagingLoader<T extends GraphObject>
  extends Loader<SimpleGraphObjectCursor<T>>
{
  private boolean appendResults = false;
  private Request currentRequest;
  private SimpleGraphObjectCursor<T> cursor;
  private final Class<T> graphObjectClass;
  private boolean loading = false;
  private Request nextRequest;
  private OnErrorListener onErrorListener;
  private Request originalRequest;
  private boolean skipRoundtripIfCached;
  
  public GraphObjectPagingLoader(Context paramContext, Class<T> paramClass)
  {
    super(paramContext);
    this.graphObjectClass = paramClass;
  }
  
  private void addResults(Response paramResponse)
  {
    SimpleGraphObjectCursor localSimpleGraphObjectCursor;
    boolean bool;
    int i;
    if ((this.cursor == null) || (!this.appendResults))
    {
      localSimpleGraphObjectCursor = new SimpleGraphObjectCursor();
      Object localObject = (PagedResults)paramResponse.getGraphObjectAs(PagedResults.class);
      bool = paramResponse.getIsFromCache();
      localObject = ((PagedResults)localObject).getData().castToListOf(this.graphObjectClass);
      if (((GraphObjectList)localObject).size() <= 0) {
        break label158;
      }
      i = 1;
      label69:
      if (i != 0)
      {
        this.nextRequest = paramResponse.getRequestForPagedResults(Response.PagingDirection.NEXT);
        localSimpleGraphObjectCursor.addGraphObjects((Collection)localObject, bool);
        if (this.nextRequest == null) {
          break label163;
        }
        localSimpleGraphObjectCursor.setMoreObjectsAvailable(true);
      }
    }
    for (;;)
    {
      if (i == 0)
      {
        localSimpleGraphObjectCursor.setMoreObjectsAvailable(false);
        localSimpleGraphObjectCursor.setFromCache(bool);
        this.nextRequest = null;
      }
      if (!bool) {
        this.skipRoundtripIfCached = false;
      }
      deliverResult(localSimpleGraphObjectCursor);
      return;
      localSimpleGraphObjectCursor = new SimpleGraphObjectCursor(this.cursor);
      break;
      label158:
      i = 0;
      break label69;
      label163:
      localSimpleGraphObjectCursor.setMoreObjectsAvailable(false);
    }
  }
  
  private CacheableRequestBatch putRequestIntoBatch(Request paramRequest, boolean paramBoolean)
  {
    boolean bool = true;
    paramRequest = new CacheableRequestBatch(new Request[] { paramRequest });
    if (!paramBoolean) {}
    for (paramBoolean = bool;; paramBoolean = false)
    {
      paramRequest.setForceRoundTrip(paramBoolean);
      return paramRequest;
    }
  }
  
  private void requestCompleted(Response paramResponse)
  {
    if (paramResponse.getRequest() != this.currentRequest) {}
    for (;;)
    {
      return;
      this.loading = false;
      this.currentRequest = null;
      Object localObject1 = paramResponse.getError();
      if (localObject1 == null) {}
      for (localObject1 = null;; localObject1 = ((FacebookRequestError)localObject1).getException())
      {
        Object localObject2 = localObject1;
        if (paramResponse.getGraphObject() == null)
        {
          localObject2 = localObject1;
          if (localObject1 == null) {
            localObject2 = new FacebookException("GraphObjectPagingLoader received neither a result nor an error.");
          }
        }
        if (localObject2 == null) {
          break label96;
        }
        this.nextRequest = null;
        if (this.onErrorListener == null) {
          break;
        }
        this.onErrorListener.onError((FacebookException)localObject2, this);
        break;
      }
      label96:
      addResults(paramResponse);
    }
  }
  
  private void startLoading(Request paramRequest, boolean paramBoolean, long paramLong)
  {
    this.skipRoundtripIfCached = paramBoolean;
    this.appendResults = false;
    this.nextRequest = null;
    this.currentRequest = paramRequest;
    this.currentRequest.setCallback(new Request.Callback()
    {
      public void onCompleted(Response paramAnonymousResponse)
      {
        GraphObjectPagingLoader.this.requestCompleted(paramAnonymousResponse);
      }
    });
    this.loading = true;
    paramRequest = new Runnable()
    {
      public void run()
      {
        Request.executeBatchAsync(this.val$batch);
      }
    };
    if (paramLong == 0L) {
      paramRequest.run();
    }
    for (;;)
    {
      return;
      new Handler().postDelayed(paramRequest, paramLong);
    }
  }
  
  public void clearResults()
  {
    this.nextRequest = null;
    this.originalRequest = null;
    this.currentRequest = null;
    deliverResult(null);
  }
  
  public void deliverResult(SimpleGraphObjectCursor<T> paramSimpleGraphObjectCursor)
  {
    SimpleGraphObjectCursor localSimpleGraphObjectCursor = this.cursor;
    this.cursor = paramSimpleGraphObjectCursor;
    if (isStarted())
    {
      super.deliverResult(paramSimpleGraphObjectCursor);
      if ((localSimpleGraphObjectCursor != null) && (localSimpleGraphObjectCursor != paramSimpleGraphObjectCursor) && (!localSimpleGraphObjectCursor.isClosed())) {
        localSimpleGraphObjectCursor.close();
      }
    }
  }
  
  public void followNextLink()
  {
    if (this.nextRequest != null)
    {
      this.appendResults = true;
      this.currentRequest = this.nextRequest;
      this.currentRequest.setCallback(new Request.Callback()
      {
        public void onCompleted(Response paramAnonymousResponse)
        {
          GraphObjectPagingLoader.this.requestCompleted(paramAnonymousResponse);
        }
      });
      this.loading = true;
      Request.executeBatchAsync(putRequestIntoBatch(this.currentRequest, this.skipRoundtripIfCached));
    }
  }
  
  public SimpleGraphObjectCursor<T> getCursor()
  {
    return this.cursor;
  }
  
  public OnErrorListener getOnErrorListener()
  {
    return this.onErrorListener;
  }
  
  public boolean isLoading()
  {
    return this.loading;
  }
  
  protected void onStartLoading()
  {
    super.onStartLoading();
    if (this.cursor != null) {
      deliverResult(this.cursor);
    }
  }
  
  public void refreshOriginalRequest(long paramLong)
  {
    if (this.originalRequest == null) {
      throw new FacebookException("refreshOriginalRequest may not be called until after startLoading has been called.");
    }
    startLoading(this.originalRequest, false, paramLong);
  }
  
  public void setOnErrorListener(OnErrorListener paramOnErrorListener)
  {
    this.onErrorListener = paramOnErrorListener;
  }
  
  public void startLoading(Request paramRequest, boolean paramBoolean)
  {
    this.originalRequest = paramRequest;
    startLoading(paramRequest, paramBoolean, 0L);
  }
  
  public static abstract interface OnErrorListener
  {
    public abstract void onError(FacebookException paramFacebookException, GraphObjectPagingLoader<?> paramGraphObjectPagingLoader);
  }
  
  static abstract interface PagedResults
    extends GraphObject
  {
    public abstract GraphObjectList<GraphObject> getData();
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\facebook\widget\GraphObjectPagingLoader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */