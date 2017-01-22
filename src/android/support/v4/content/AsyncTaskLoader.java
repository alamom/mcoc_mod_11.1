package android.support.v4.content;

import android.content.Context;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v4.os.OperationCanceledException;
import android.support.v4.util.TimeUtils;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;

public abstract class AsyncTaskLoader<D>
  extends Loader<D>
{
  static final boolean DEBUG = false;
  static final String TAG = "AsyncTaskLoader";
  volatile AsyncTaskLoader<D>.LoadTask mCancellingTask;
  private final Executor mExecutor;
  Handler mHandler;
  long mLastLoadCompleteTime = -10000L;
  volatile AsyncTaskLoader<D>.LoadTask mTask;
  long mUpdateThrottle;
  
  public AsyncTaskLoader(Context paramContext)
  {
    this(paramContext, ModernAsyncTask.THREAD_POOL_EXECUTOR);
  }
  
  private AsyncTaskLoader(Context paramContext, Executor paramExecutor)
  {
    super(paramContext);
    this.mExecutor = paramExecutor;
  }
  
  public void cancelLoadInBackground() {}
  
  void dispatchOnCancelled(AsyncTaskLoader<D>.LoadTask paramAsyncTaskLoader, D paramD)
  {
    onCanceled(paramD);
    if (this.mCancellingTask == paramAsyncTaskLoader)
    {
      rollbackContentChanged();
      this.mLastLoadCompleteTime = SystemClock.uptimeMillis();
      this.mCancellingTask = null;
      deliverCancellation();
      executePendingTask();
    }
  }
  
  void dispatchOnLoadComplete(AsyncTaskLoader<D>.LoadTask paramAsyncTaskLoader, D paramD)
  {
    if (this.mTask != paramAsyncTaskLoader) {
      dispatchOnCancelled(paramAsyncTaskLoader, paramD);
    }
    for (;;)
    {
      return;
      if (isAbandoned())
      {
        onCanceled(paramD);
      }
      else
      {
        commitContentChanged();
        this.mLastLoadCompleteTime = SystemClock.uptimeMillis();
        this.mTask = null;
        deliverResult(paramD);
      }
    }
  }
  
  public void dump(String paramString, FileDescriptor paramFileDescriptor, PrintWriter paramPrintWriter, String[] paramArrayOfString)
  {
    super.dump(paramString, paramFileDescriptor, paramPrintWriter, paramArrayOfString);
    if (this.mTask != null)
    {
      paramPrintWriter.print(paramString);
      paramPrintWriter.print("mTask=");
      paramPrintWriter.print(this.mTask);
      paramPrintWriter.print(" waiting=");
      paramPrintWriter.println(this.mTask.waiting);
    }
    if (this.mCancellingTask != null)
    {
      paramPrintWriter.print(paramString);
      paramPrintWriter.print("mCancellingTask=");
      paramPrintWriter.print(this.mCancellingTask);
      paramPrintWriter.print(" waiting=");
      paramPrintWriter.println(this.mCancellingTask.waiting);
    }
    if (this.mUpdateThrottle != 0L)
    {
      paramPrintWriter.print(paramString);
      paramPrintWriter.print("mUpdateThrottle=");
      TimeUtils.formatDuration(this.mUpdateThrottle, paramPrintWriter);
      paramPrintWriter.print(" mLastLoadCompleteTime=");
      TimeUtils.formatDuration(this.mLastLoadCompleteTime, SystemClock.uptimeMillis(), paramPrintWriter);
      paramPrintWriter.println();
    }
  }
  
  void executePendingTask()
  {
    if ((this.mCancellingTask == null) && (this.mTask != null))
    {
      if (this.mTask.waiting)
      {
        this.mTask.waiting = false;
        this.mHandler.removeCallbacks(this.mTask);
      }
      if ((this.mUpdateThrottle <= 0L) || (SystemClock.uptimeMillis() >= this.mLastLoadCompleteTime + this.mUpdateThrottle)) {
        break label98;
      }
      this.mTask.waiting = true;
      this.mHandler.postAtTime(this.mTask, this.mLastLoadCompleteTime + this.mUpdateThrottle);
    }
    for (;;)
    {
      return;
      label98:
      this.mTask.executeOnExecutor(this.mExecutor, (Void[])null);
    }
  }
  
  public boolean isLoadInBackgroundCanceled()
  {
    if (this.mCancellingTask != null) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public abstract D loadInBackground();
  
  protected boolean onCancelLoad()
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    if (this.mTask != null)
    {
      if (this.mCancellingTask == null) {
        break label56;
      }
      if (this.mTask.waiting)
      {
        this.mTask.waiting = false;
        this.mHandler.removeCallbacks(this.mTask);
      }
      this.mTask = null;
      bool1 = bool2;
    }
    for (;;)
    {
      return bool1;
      label56:
      if (this.mTask.waiting)
      {
        this.mTask.waiting = false;
        this.mHandler.removeCallbacks(this.mTask);
        this.mTask = null;
        bool1 = bool2;
      }
      else
      {
        bool1 = this.mTask.cancel(false);
        if (bool1)
        {
          this.mCancellingTask = this.mTask;
          cancelLoadInBackground();
        }
        this.mTask = null;
      }
    }
  }
  
  public void onCanceled(D paramD) {}
  
  protected void onForceLoad()
  {
    super.onForceLoad();
    cancelLoad();
    this.mTask = new LoadTask();
    executePendingTask();
  }
  
  protected D onLoadInBackground()
  {
    return (D)loadInBackground();
  }
  
  public void setUpdateThrottle(long paramLong)
  {
    this.mUpdateThrottle = paramLong;
    if (paramLong != 0L) {
      this.mHandler = new Handler();
    }
  }
  
  public void waitForLoader()
  {
    LoadTask localLoadTask = this.mTask;
    if (localLoadTask != null) {
      localLoadTask.waitForLoader();
    }
  }
  
  final class LoadTask
    extends ModernAsyncTask<Void, Void, D>
    implements Runnable
  {
    private final CountDownLatch mDone = new CountDownLatch(1);
    boolean waiting;
    
    LoadTask() {}
    
    protected D doInBackground(Void... paramVarArgs)
    {
      try
      {
        paramVarArgs = AsyncTaskLoader.this.onLoadInBackground();
        return paramVarArgs;
      }
      catch (OperationCanceledException paramVarArgs)
      {
        for (;;)
        {
          if (!isCancelled()) {
            throw paramVarArgs;
          }
          paramVarArgs = null;
        }
      }
    }
    
    protected void onCancelled(D paramD)
    {
      try
      {
        AsyncTaskLoader.this.dispatchOnCancelled(this, paramD);
        return;
      }
      finally
      {
        this.mDone.countDown();
      }
    }
    
    protected void onPostExecute(D paramD)
    {
      try
      {
        AsyncTaskLoader.this.dispatchOnLoadComplete(this, paramD);
        return;
      }
      finally
      {
        this.mDone.countDown();
      }
    }
    
    public void run()
    {
      this.waiting = false;
      AsyncTaskLoader.this.executePendingTask();
    }
    
    public void waitForLoader()
    {
      try
      {
        this.mDone.await();
        return;
      }
      catch (InterruptedException localInterruptedException)
      {
        for (;;) {}
      }
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\android\support\v4\content\AsyncTaskLoader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */