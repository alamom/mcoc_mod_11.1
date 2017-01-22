package com.google.android.gms.common.api;

import android.os.Looper;
import java.util.ArrayList;
import java.util.List;

public final class Batch
  extends BaseImplementation.AbstractPendingResult<BatchResult>
{
  private int IA;
  private boolean IB;
  private boolean IC;
  private final PendingResult<?>[] IE;
  private final Object mw = new Object();
  
  private Batch(List<PendingResult<?>> paramList, Looper paramLooper)
  {
    super(new BaseImplementation.CallbackHandler(paramLooper));
    this.IA = paramList.size();
    this.IE = new PendingResult[this.IA];
    for (int i = 0; i < paramList.size(); i++)
    {
      paramLooper = (PendingResult)paramList.get(i);
      this.IE[i] = paramLooper;
      paramLooper.a(new PendingResult.a()
      {
        public void n(Status paramAnonymousStatus)
        {
          for (;;)
          {
            synchronized (Batch.a(Batch.this))
            {
              if (Batch.this.isCanceled()) {
                return;
              }
              if (paramAnonymousStatus.isCanceled())
              {
                Batch.a(Batch.this, true);
                Batch.b(Batch.this);
                if (Batch.c(Batch.this) == 0)
                {
                  if (!Batch.d(Batch.this)) {
                    break;
                  }
                  Batch.e(Batch.this);
                }
              }
            }
            if (!paramAnonymousStatus.isSuccess()) {
              Batch.b(Batch.this, true);
            }
          }
          if (Batch.f(Batch.this))
          {
            paramAnonymousStatus = new com/google/android/gms/common/api/Status;
            paramAnonymousStatus.<init>(13);
          }
          for (;;)
          {
            Batch localBatch = Batch.this;
            BatchResult localBatchResult = new com/google/android/gms/common/api/BatchResult;
            localBatchResult.<init>(paramAnonymousStatus, Batch.g(Batch.this));
            localBatch.b(localBatchResult);
            break;
            paramAnonymousStatus = Status.Jv;
          }
        }
      });
    }
  }
  
  public void cancel()
  {
    super.cancel();
    PendingResult[] arrayOfPendingResult = this.IE;
    int j = arrayOfPendingResult.length;
    for (int i = 0; i < j; i++) {
      arrayOfPendingResult[i].cancel();
    }
  }
  
  public BatchResult createFailedResult(Status paramStatus)
  {
    return new BatchResult(paramStatus, this.IE);
  }
  
  public static final class Builder
  {
    private List<PendingResult<?>> IG = new ArrayList();
    private Looper IH;
    
    public Builder(GoogleApiClient paramGoogleApiClient)
    {
      this.IH = paramGoogleApiClient.getLooper();
    }
    
    public <R extends Result> BatchResultToken<R> add(PendingResult<R> paramPendingResult)
    {
      BatchResultToken localBatchResultToken = new BatchResultToken(this.IG.size());
      this.IG.add(paramPendingResult);
      return localBatchResultToken;
    }
    
    public Batch build()
    {
      return new Batch(this.IG, this.IH, null);
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\common\api\Batch.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */