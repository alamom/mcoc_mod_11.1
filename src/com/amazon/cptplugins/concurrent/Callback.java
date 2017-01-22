package com.amazon.cptplugins.concurrent;

import com.amazon.cptplugins.exceptions.jsonutils.AmazonError;
import com.google.gson.Gson;
import java.util.concurrent.Future;

public class Callback<T>
  implements Runnable
{
  private static final Gson GSON = new Gson();
  private final CallbackHandler delegator;
  private final Future<T> future;
  private final String uuid;
  
  public Callback(Future<T> paramFuture, String paramString, CallbackHandler paramCallbackHandler)
  {
    this.future = paramFuture;
    this.uuid = paramString;
    this.delegator = paramCallbackHandler;
  }
  
  public void run()
  {
    try
    {
      Object localObject = this.future.get();
      CallbackHandler localCallbackHandler = this.delegator;
      Gson localGson = GSON;
      CallbackResultSuccess localCallbackResultSuccess = new com/amazon/cptplugins/concurrent/Callback$CallbackResultSuccess;
      localCallbackResultSuccess.<init>(this.uuid, localObject);
      localCallbackHandler.handleSdkCallback(localGson.toJson(localCallbackResultSuccess));
      return;
    }
    catch (InterruptedException localInterruptedException)
    {
      for (;;)
      {
        Thread.currentThread().interrupt();
      }
    }
    catch (Exception localException)
    {
      for (;;)
      {
        this.delegator.handleSdkCallback(GSON.toJson(new CallbackResultError(this.uuid, new AmazonError(localException))));
      }
    }
  }
  
  private static abstract interface CallbackResult<T> {}
  
  private static class CallbackResultError
    implements Callback.CallbackResult<AmazonError>
  {
    private final String callerId;
    private final AmazonError response;
    
    public CallbackResultError(String paramString, AmazonError paramAmazonError)
    {
      this.callerId = paramString;
      this.response = paramAmazonError;
    }
  }
  
  private static class CallbackResultSuccess<T>
    implements Callback.CallbackResult<T>
  {
    private final String callerId;
    private final T response;
    
    public CallbackResultSuccess(String paramString, T paramT)
    {
      this.callerId = paramString;
      this.response = paramT;
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\amazon\cptplugins\concurrent\Callback.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */