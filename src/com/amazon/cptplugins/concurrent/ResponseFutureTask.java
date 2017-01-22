package com.amazon.cptplugins.concurrent;

import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class ResponseFutureTask<T>
  implements Future<T>
{
  private final String requestId;
  private final ConcurrentMap<String, SynchronousQueue<T>> responseQueue;
  
  public ResponseFutureTask(String paramString, ConcurrentMap<String, SynchronousQueue<T>> paramConcurrentMap)
  {
    this.requestId = paramString;
    this.responseQueue = paramConcurrentMap;
  }
  
  public boolean cancel(boolean paramBoolean)
  {
    return false;
  }
  
  public T get()
    throws InterruptedException, ExecutionException
  {
    Object localObject = ((SynchronousQueue)this.responseQueue.get(this.requestId)).take();
    this.responseQueue.remove(this.requestId);
    return (T)localObject;
  }
  
  public T get(long paramLong, TimeUnit paramTimeUnit)
    throws InterruptedException, ExecutionException, TimeoutException
  {
    throw new UnsupportedOperationException();
  }
  
  public boolean isCancelled()
  {
    return false;
  }
  
  public boolean isDone()
  {
    if (!this.responseQueue.containsKey(this.requestId)) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\amazon\cptplugins\concurrent\ResponseFutureTask.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */