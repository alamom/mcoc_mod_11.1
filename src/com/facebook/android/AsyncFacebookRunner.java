package com.facebook.android;

import android.content.Context;
import android.os.Bundle;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;

@Deprecated
public class AsyncFacebookRunner
{
  Facebook fb;
  
  public AsyncFacebookRunner(Facebook paramFacebook)
  {
    this.fb = paramFacebook;
  }
  
  @Deprecated
  public void logout(Context paramContext, RequestListener paramRequestListener)
  {
    logout(paramContext, paramRequestListener, null);
  }
  
  @Deprecated
  public void logout(final Context paramContext, final RequestListener paramRequestListener, final Object paramObject)
  {
    new Thread()
    {
      public void run()
      {
        for (;;)
        {
          try
          {
            localObject = AsyncFacebookRunner.this.fb.logoutImpl(paramContext);
            if ((((String)localObject).length() == 0) || (((String)localObject).equals("false")))
            {
              AsyncFacebookRunner.RequestListener localRequestListener = paramRequestListener;
              localObject = new com/facebook/android/FacebookError;
              ((FacebookError)localObject).<init>("auth.expireSession failed");
              localRequestListener.onFacebookError((FacebookError)localObject, paramObject);
              return;
            }
          }
          catch (FileNotFoundException localFileNotFoundException)
          {
            Object localObject;
            paramRequestListener.onFileNotFoundException(localFileNotFoundException, paramObject);
            continue;
          }
          catch (MalformedURLException localMalformedURLException)
          {
            paramRequestListener.onMalformedURLException(localMalformedURLException, paramObject);
            continue;
          }
          catch (IOException localIOException)
          {
            paramRequestListener.onIOException(localIOException, paramObject);
            continue;
          }
          paramRequestListener.onComplete((String)localObject, paramObject);
        }
      }
    }.start();
  }
  
  @Deprecated
  public void request(Bundle paramBundle, RequestListener paramRequestListener)
  {
    request(null, paramBundle, "GET", paramRequestListener, null);
  }
  
  @Deprecated
  public void request(Bundle paramBundle, RequestListener paramRequestListener, Object paramObject)
  {
    request(null, paramBundle, "GET", paramRequestListener, paramObject);
  }
  
  @Deprecated
  public void request(String paramString, Bundle paramBundle, RequestListener paramRequestListener)
  {
    request(paramString, paramBundle, "GET", paramRequestListener, null);
  }
  
  @Deprecated
  public void request(String paramString, Bundle paramBundle, RequestListener paramRequestListener, Object paramObject)
  {
    request(paramString, paramBundle, "GET", paramRequestListener, paramObject);
  }
  
  @Deprecated
  public void request(final String paramString1, final Bundle paramBundle, final String paramString2, final RequestListener paramRequestListener, final Object paramObject)
  {
    new Thread()
    {
      public void run()
      {
        try
        {
          String str = AsyncFacebookRunner.this.fb.requestImpl(paramString1, paramBundle, paramString2);
          paramRequestListener.onComplete(str, paramObject);
          return;
        }
        catch (FileNotFoundException localFileNotFoundException)
        {
          for (;;)
          {
            paramRequestListener.onFileNotFoundException(localFileNotFoundException, paramObject);
          }
        }
        catch (MalformedURLException localMalformedURLException)
        {
          for (;;)
          {
            paramRequestListener.onMalformedURLException(localMalformedURLException, paramObject);
          }
        }
        catch (IOException localIOException)
        {
          for (;;)
          {
            paramRequestListener.onIOException(localIOException, paramObject);
          }
        }
      }
    }.start();
  }
  
  @Deprecated
  public void request(String paramString, RequestListener paramRequestListener)
  {
    request(paramString, new Bundle(), "GET", paramRequestListener, null);
  }
  
  @Deprecated
  public void request(String paramString, RequestListener paramRequestListener, Object paramObject)
  {
    request(paramString, new Bundle(), "GET", paramRequestListener, paramObject);
  }
  
  @Deprecated
  public static abstract interface RequestListener
  {
    public abstract void onComplete(String paramString, Object paramObject);
    
    public abstract void onFacebookError(FacebookError paramFacebookError, Object paramObject);
    
    public abstract void onFileNotFoundException(FileNotFoundException paramFileNotFoundException, Object paramObject);
    
    public abstract void onIOException(IOException paramIOException, Object paramObject);
    
    public abstract void onMalformedURLException(MalformedURLException paramMalformedURLException, Object paramObject);
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\facebook\android\AsyncFacebookRunner.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */