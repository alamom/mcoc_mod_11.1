package bolts;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class Task<TResult>
{
  public static final ExecutorService BACKGROUND_EXECUTOR = ;
  private static final Executor IMMEDIATE_EXECUTOR = BoltsExecutors.immediate();
  public static final Executor UI_THREAD_EXECUTOR = AndroidExecutors.uiThread();
  private boolean cancelled;
  private boolean complete;
  private List<Continuation<TResult, Void>> continuations = new ArrayList();
  private Exception error;
  private final Object lock = new Object();
  private TResult result;
  
  public static <TResult> Task<TResult> call(Callable<TResult> paramCallable)
  {
    return call(paramCallable, IMMEDIATE_EXECUTOR);
  }
  
  public static <TResult> Task<TResult> call(final Callable<TResult> paramCallable, Executor paramExecutor)
  {
    TaskCompletionSource localTaskCompletionSource = create();
    paramExecutor.execute(new Runnable()
    {
      public void run()
      {
        try
        {
          this.val$tcs.setResult(paramCallable.call());
          return;
        }
        catch (Exception localException)
        {
          for (;;)
          {
            this.val$tcs.setError(localException);
          }
        }
      }
    });
    return localTaskCompletionSource.getTask();
  }
  
  public static <TResult> Task<TResult> callInBackground(Callable<TResult> paramCallable)
  {
    return call(paramCallable, BACKGROUND_EXECUTOR);
  }
  
  public static <TResult> Task<TResult> cancelled()
  {
    TaskCompletionSource localTaskCompletionSource = create();
    localTaskCompletionSource.setCancelled();
    return localTaskCompletionSource.getTask();
  }
  
  private static <TContinuationResult, TResult> void completeAfterTask(final Task<TContinuationResult>.TaskCompletionSource paramTask, Continuation<TResult, Task<TContinuationResult>> paramContinuation, final Task<TResult> paramTask1, Executor paramExecutor)
  {
    paramExecutor.execute(new Runnable()
    {
      public void run()
      {
        for (;;)
        {
          try
          {
            localTask = (Task)this.val$continuation.then(paramTask1);
            if (localTask == null)
            {
              paramTask.setResult(null);
              return;
            }
          }
          catch (Exception localException)
          {
            Task localTask;
            Continuation local1;
            paramTask.setError(localException);
            continue;
          }
          local1 = new bolts/Task$10$1;
          local1.<init>(this);
          localTask.continueWith(local1);
        }
      }
    });
  }
  
  private static <TContinuationResult, TResult> void completeImmediately(final Task<TContinuationResult>.TaskCompletionSource paramTask, Continuation<TResult, TContinuationResult> paramContinuation, final Task<TResult> paramTask1, Executor paramExecutor)
  {
    paramExecutor.execute(new Runnable()
    {
      public void run()
      {
        try
        {
          Object localObject = this.val$continuation.then(paramTask1);
          paramTask.setResult(localObject);
          return;
        }
        catch (Exception localException)
        {
          for (;;)
          {
            paramTask.setError(localException);
          }
        }
      }
    });
  }
  
  public static <TResult> Task<TResult>.TaskCompletionSource create()
  {
    Task localTask = new Task();
    localTask.getClass();
    return new TaskCompletionSource(null);
  }
  
  public static <TResult> Task<TResult> forError(Exception paramException)
  {
    TaskCompletionSource localTaskCompletionSource = create();
    localTaskCompletionSource.setError(paramException);
    return localTaskCompletionSource.getTask();
  }
  
  public static <TResult> Task<TResult> forResult(TResult paramTResult)
  {
    TaskCompletionSource localTaskCompletionSource = create();
    localTaskCompletionSource.setResult(paramTResult);
    return localTaskCompletionSource.getTask();
  }
  
  private void runContinuations()
  {
    for (;;)
    {
      Continuation localContinuation;
      synchronized (this.lock)
      {
        Iterator localIterator = this.continuations.iterator();
        if (!localIterator.hasNext()) {
          break;
        }
        localContinuation = (Continuation)localIterator.next();
      }
      try
      {
        localContinuation.then(this);
      }
      catch (RuntimeException localRuntimeException1)
      {
        throw localRuntimeException1;
        localObject2 = finally;
        throw ((Throwable)localObject2);
      }
      catch (Exception localException)
      {
        RuntimeException localRuntimeException2 = new java/lang/RuntimeException;
        localRuntimeException2.<init>(localException);
        throw localRuntimeException2;
      }
    }
    this.continuations = null;
  }
  
  public static Task<Void> whenAll(Collection<? extends Task<?>> paramCollection)
  {
    if (paramCollection.size() == 0) {}
    final TaskCompletionSource localTaskCompletionSource;
    for (paramCollection = forResult(null);; paramCollection = localTaskCompletionSource.getTask())
    {
      return paramCollection;
      localTaskCompletionSource = create();
      final ArrayList localArrayList = new ArrayList();
      Object localObject = new Object();
      final AtomicInteger localAtomicInteger = new AtomicInteger(paramCollection.size());
      final AtomicBoolean localAtomicBoolean = new AtomicBoolean(false);
      paramCollection = paramCollection.iterator();
      while (paramCollection.hasNext()) {
        ((Task)paramCollection.next()).continueWith(new Continuation()
        {
          public Void then(Task<Object> paramAnonymousTask)
          {
            if (paramAnonymousTask.isFaulted()) {}
            for (;;)
            {
              synchronized (this.val$errorLock)
              {
                localArrayList.add(paramAnonymousTask.getError());
                if (paramAnonymousTask.isCancelled()) {
                  localAtomicBoolean.set(true);
                }
                if (localAtomicInteger.decrementAndGet() == 0)
                {
                  if (localArrayList.size() == 0) {
                    break label120;
                  }
                  if (localArrayList.size() == 1) {
                    localTaskCompletionSource.setError((Exception)localArrayList.get(0));
                  }
                }
                else
                {
                  return null;
                }
              }
              localTaskCompletionSource.setError(new AggregateException(localArrayList));
              continue;
              label120:
              if (localAtomicBoolean.get()) {
                localTaskCompletionSource.setCancelled();
              } else {
                localTaskCompletionSource.setResult(null);
              }
            }
          }
        });
      }
    }
  }
  
  public <TOut> Task<TOut> cast()
  {
    return this;
  }
  
  public Task<Void> continueWhile(Callable<Boolean> paramCallable, Continuation<Void, Task<Void>> paramContinuation)
  {
    return continueWhile(paramCallable, paramContinuation, IMMEDIATE_EXECUTOR);
  }
  
  public Task<Void> continueWhile(final Callable<Boolean> paramCallable, final Continuation<Void, Task<Void>> paramContinuation, final Executor paramExecutor)
  {
    final Capture localCapture = new Capture();
    localCapture.set(new Continuation()
    {
      public Task<Void> then(Task<Void> paramAnonymousTask)
        throws Exception
      {
        if (((Boolean)paramCallable.call()).booleanValue()) {}
        for (paramAnonymousTask = Task.forResult(null).onSuccessTask(paramContinuation, paramExecutor).onSuccessTask((Continuation)localCapture.get(), paramExecutor);; paramAnonymousTask = Task.forResult(null)) {
          return paramAnonymousTask;
        }
      }
    });
    return makeVoid().continueWithTask((Continuation)localCapture.get(), paramExecutor);
  }
  
  public <TContinuationResult> Task<TContinuationResult> continueWith(Continuation<TResult, TContinuationResult> paramContinuation)
  {
    return continueWith(paramContinuation, IMMEDIATE_EXECUTOR);
  }
  
  public <TContinuationResult> Task<TContinuationResult> continueWith(Continuation<TResult, TContinuationResult> paramContinuation, Executor paramExecutor)
  {
    TaskCompletionSource localTaskCompletionSource = create();
    synchronized (this.lock)
    {
      boolean bool = isCompleted();
      if (!bool)
      {
        List localList = this.continuations;
        Continuation local5 = new bolts/Task$5;
        local5.<init>(this, localTaskCompletionSource, paramContinuation, paramExecutor);
        localList.add(local5);
      }
      if (bool) {
        completeImmediately(localTaskCompletionSource, paramContinuation, this, paramExecutor);
      }
      return localTaskCompletionSource.getTask();
    }
  }
  
  public <TContinuationResult> Task<TContinuationResult> continueWithTask(Continuation<TResult, Task<TContinuationResult>> paramContinuation)
  {
    return continueWithTask(paramContinuation, IMMEDIATE_EXECUTOR);
  }
  
  public <TContinuationResult> Task<TContinuationResult> continueWithTask(Continuation<TResult, Task<TContinuationResult>> paramContinuation, Executor paramExecutor)
  {
    TaskCompletionSource localTaskCompletionSource = create();
    synchronized (this.lock)
    {
      boolean bool = isCompleted();
      if (!bool)
      {
        List localList = this.continuations;
        Continuation local6 = new bolts/Task$6;
        local6.<init>(this, localTaskCompletionSource, paramContinuation, paramExecutor);
        localList.add(local6);
      }
      if (bool) {
        completeAfterTask(localTaskCompletionSource, paramContinuation, this, paramExecutor);
      }
      return localTaskCompletionSource.getTask();
    }
  }
  
  public Exception getError()
  {
    synchronized (this.lock)
    {
      Exception localException = this.error;
      return localException;
    }
  }
  
  public TResult getResult()
  {
    synchronized (this.lock)
    {
      Object localObject2 = this.result;
      return (TResult)localObject2;
    }
  }
  
  public boolean isCancelled()
  {
    synchronized (this.lock)
    {
      boolean bool = this.cancelled;
      return bool;
    }
  }
  
  public boolean isCompleted()
  {
    synchronized (this.lock)
    {
      boolean bool = this.complete;
      return bool;
    }
  }
  
  public boolean isFaulted()
  {
    synchronized (this.lock)
    {
      if (this.error != null)
      {
        bool = true;
        return bool;
      }
      boolean bool = false;
    }
  }
  
  public Task<Void> makeVoid()
  {
    continueWithTask(new Continuation()
    {
      public Task<Void> then(Task<TResult> paramAnonymousTask)
        throws Exception
      {
        if (paramAnonymousTask.isCancelled()) {
          paramAnonymousTask = Task.cancelled();
        }
        for (;;)
        {
          return paramAnonymousTask;
          if (paramAnonymousTask.isFaulted()) {
            paramAnonymousTask = Task.forError(paramAnonymousTask.getError());
          } else {
            paramAnonymousTask = Task.forResult(null);
          }
        }
      }
    });
  }
  
  public <TContinuationResult> Task<TContinuationResult> onSuccess(Continuation<TResult, TContinuationResult> paramContinuation)
  {
    return onSuccess(paramContinuation, IMMEDIATE_EXECUTOR);
  }
  
  public <TContinuationResult> Task<TContinuationResult> onSuccess(final Continuation<TResult, TContinuationResult> paramContinuation, Executor paramExecutor)
  {
    continueWithTask(new Continuation()
    {
      public Task<TContinuationResult> then(Task<TResult> paramAnonymousTask)
      {
        if (paramAnonymousTask.isFaulted()) {
          paramAnonymousTask = Task.forError(paramAnonymousTask.getError());
        }
        for (;;)
        {
          return paramAnonymousTask;
          if (paramAnonymousTask.isCancelled()) {
            paramAnonymousTask = Task.cancelled();
          } else {
            paramAnonymousTask = paramAnonymousTask.continueWith(paramContinuation);
          }
        }
      }
    }, paramExecutor);
  }
  
  public <TContinuationResult> Task<TContinuationResult> onSuccessTask(Continuation<TResult, Task<TContinuationResult>> paramContinuation)
  {
    return onSuccessTask(paramContinuation, IMMEDIATE_EXECUTOR);
  }
  
  public <TContinuationResult> Task<TContinuationResult> onSuccessTask(final Continuation<TResult, Task<TContinuationResult>> paramContinuation, Executor paramExecutor)
  {
    continueWithTask(new Continuation()
    {
      public Task<TContinuationResult> then(Task<TResult> paramAnonymousTask)
      {
        if (paramAnonymousTask.isFaulted()) {
          paramAnonymousTask = Task.forError(paramAnonymousTask.getError());
        }
        for (;;)
        {
          return paramAnonymousTask;
          if (paramAnonymousTask.isCancelled()) {
            paramAnonymousTask = Task.cancelled();
          } else {
            paramAnonymousTask = paramAnonymousTask.continueWithTask(paramContinuation);
          }
        }
      }
    }, paramExecutor);
  }
  
  public void waitForCompletion()
    throws InterruptedException
  {
    synchronized (this.lock)
    {
      if (!isCompleted()) {
        this.lock.wait();
      }
      return;
    }
  }
  
  public class TaskCompletionSource
  {
    private TaskCompletionSource() {}
    
    public Task<TResult> getTask()
    {
      return Task.this;
    }
    
    public void setCancelled()
    {
      if (!trySetCancelled()) {
        throw new IllegalStateException("Cannot cancel a completed task.");
      }
    }
    
    public void setError(Exception paramException)
    {
      if (!trySetError(paramException)) {
        throw new IllegalStateException("Cannot set the error on a completed task.");
      }
    }
    
    public void setResult(TResult paramTResult)
    {
      if (!trySetResult(paramTResult)) {
        throw new IllegalStateException("Cannot set the result of a completed task.");
      }
    }
    
    public boolean trySetCancelled()
    {
      boolean bool = true;
      synchronized (Task.this.lock)
      {
        if (Task.this.complete)
        {
          bool = false;
          return bool;
        }
        Task.access$402(Task.this, true);
        Task.access$502(Task.this, true);
        Task.this.lock.notifyAll();
        Task.this.runContinuations();
      }
    }
    
    public boolean trySetError(Exception paramException)
    {
      boolean bool = true;
      synchronized (Task.this.lock)
      {
        if (Task.this.complete)
        {
          bool = false;
          return bool;
        }
        Task.access$402(Task.this, true);
        Task.access$802(Task.this, paramException);
        Task.this.lock.notifyAll();
        Task.this.runContinuations();
      }
    }
    
    public boolean trySetResult(TResult paramTResult)
    {
      boolean bool = true;
      synchronized (Task.this.lock)
      {
        if (Task.this.complete)
        {
          bool = false;
          return bool;
        }
        Task.access$402(Task.this, true);
        Task.access$702(Task.this, paramTResult);
        Task.this.lock.notifyAll();
        Task.this.runContinuations();
      }
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\bolts\Task.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */