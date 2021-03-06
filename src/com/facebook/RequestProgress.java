package com.facebook;

import android.os.Handler;

class RequestProgress
{
  private final Handler callbackHandler;
  private long lastReportedProgress;
  private long maxProgress;
  private long progress;
  private final Request request;
  private final long threshold;
  
  RequestProgress(Handler paramHandler, Request paramRequest)
  {
    this.request = paramRequest;
    this.callbackHandler = paramHandler;
    this.threshold = Settings.getOnProgressThreshold();
  }
  
  void addProgress(long paramLong)
  {
    this.progress += paramLong;
    if ((this.progress >= this.lastReportedProgress + this.threshold) || (this.progress >= this.maxProgress)) {
      reportProgress();
    }
  }
  
  void addToMax(long paramLong)
  {
    this.maxProgress += paramLong;
  }
  
  long getMaxProgress()
  {
    return this.maxProgress;
  }
  
  long getProgress()
  {
    return this.progress;
  }
  
  void reportProgress()
  {
    final Object localObject;
    final long l1;
    long l2;
    if (this.progress > this.lastReportedProgress)
    {
      localObject = this.request.getCallback();
      if ((this.maxProgress > 0L) && ((localObject instanceof Request.OnProgressCallback)))
      {
        l1 = this.progress;
        l2 = this.maxProgress;
        localObject = (Request.OnProgressCallback)localObject;
        if (this.callbackHandler != null) {
          break label80;
        }
        ((Request.OnProgressCallback)localObject).onProgress(l1, l2);
      }
    }
    for (;;)
    {
      this.lastReportedProgress = this.progress;
      return;
      label80:
      this.callbackHandler.post(new Runnable()
      {
        public void run()
        {
          localObject.onProgress(l1, this.val$maxProgressCopy);
        }
      });
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\facebook\RequestProgress.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */