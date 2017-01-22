package com.amazon.device.iap.cpt;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.amazon.cptplugins.AndroidResources;
import com.amazon.cptplugins.CrossPlatformTool;
import com.amazon.cptplugins.concurrent.SdkCallbackListener;
import com.amazon.cptplugins.concurrent.SdkEventListener;
import com.amazon.cptplugins.exceptions.AmazonException;
import com.amazon.cptplugins.exceptions.jsonutils.AmazonError;
import com.google.gson.Gson;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.concurrent.SynchronousQueue;

public class AmazonIapV2JavaControllerNativeProxy
  implements AndroidResources, SdkEventListener, SdkCallbackListener
{
  private static final String TAG = "AmazonIapV2JavaControllerNativeProxy";
  private static AmazonIapV2JavaController amazonIapV2Controller;
  private ActivityLifecycleListener activityLifecycleListener = null;
  private volatile Class<?> controllerImplClass = null;
  
  private static Context getApplicationContext()
  {
    try
    {
      Context localContext = ((Application)Class.forName("android.app.ActivityThread").getMethod("currentApplication", new Class[0]).invoke(null, (Object[])null)).getApplicationContext();
      return localContext;
    }
    catch (Exception localException)
    {
      throw new AmazonException("UnexpectedException during getApplication() in AmazonIapV2JavaControllerImpl: " + localException);
    }
  }
  
  private static Context getContext()
    throws InterruptedException
  {
    if (runningOnUiThread()) {}
    for (Object localObject = getApplicationContext();; localObject = (Context)((SynchronousQueue)localObject).take())
    {
      return (Context)localObject;
      localObject = new SynchronousQueue();
      new Handler(Looper.getMainLooper()).post(new Runnable()
      {
        public void run()
        {
          try
          {
            Context localContext = AmazonIapV2JavaControllerNativeProxy.access$000();
            this.val$syncContextQueue.put(localContext);
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
            throw new AmazonException("UnexpectedException during setContext() in AmazonIapV2JavaControllerNativeProxy: " + localException);
          }
        }
      });
    }
  }
  
  private void initController()
  {
    registerSdkEventListener();
    registerCallbackListener();
    registerAndroidResources();
    if (!runningOnUnity()) {
      this.activityLifecycleListener = new ActivityLifecycleListener();
    }
    try
    {
      ActivityLifecycleListener.registerActivityLifecycleCallbacks((Application)getContext(), this.activityLifecycleListener);
      return;
    }
    catch (InterruptedException localInterruptedException)
    {
      throw new AmazonException("UnexpectedException during initController in the class AmazonIapV2JavaControllerNativeProxy: " + localInterruptedException);
    }
  }
  
  private void initProxy()
  {
    try
    {
      initController();
      return;
    }
    catch (Exception localException)
    {
      throw new AmazonException("UnexpectedException when instantiating AmazonIapV2JavaControllerImpl: " + localException);
    }
  }
  
  private static native void nativeCallback(String paramString);
  
  private static native void nativeFireEvent(String paramString);
  
  public static AmazonIapV2JavaControllerNativeProxy newInstance()
    throws InterruptedException
  {
    AmazonIapV2JavaControllerNativeProxy localAmazonIapV2JavaControllerNativeProxy = new AmazonIapV2JavaControllerNativeProxy();
    amazonIapV2Controller = AmazonIapV2JavaControllerImpl.newInstance(getContext());
    localAmazonIapV2JavaControllerNativeProxy.initProxy();
    return localAmazonIapV2JavaControllerNativeProxy;
  }
  
  private void registerAndroidResources()
  {
    amazonIapV2Controller.setAndroidResources(this);
  }
  
  private void registerCallbackListener()
  {
    amazonIapV2Controller.setSdkCallbackListener(this);
  }
  
  private void registerSdkEventListener()
  {
    amazonIapV2Controller.setSdkEventListener(this);
  }
  
  private static boolean runningOnUiThread()
  {
    if (Looper.myLooper() == Looper.getMainLooper()) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public static boolean runningOnUnity()
  {
    try
    {
      Class.forName("com.unity3d.player.UnityPlayer");
      bool = true;
    }
    catch (ClassNotFoundException localClassNotFoundException)
    {
      for (;;)
      {
        boolean bool = false;
      }
    }
    catch (Exception localException)
    {
      throw new AmazonException("UnexpectedException during runningOnUnity() in AmazonIapV2JavaControllerNativeProxy: " + localException);
    }
    return bool;
  }
  
  public void fireSdkEvent(String paramString)
  {
    nativeFireEvent(paramString);
  }
  
  public CrossPlatformTool getCrossPlatformTool()
  {
    if (runningOnUnity()) {}
    for (CrossPlatformTool localCrossPlatformTool = CrossPlatformTool.UNITY;; localCrossPlatformTool = CrossPlatformTool.XAMARIN) {
      return localCrossPlatformTool;
    }
  }
  
  public Activity getCurrentAndroidActivity()
  {
    if (runningOnUnity()) {}
    for (;;)
    {
      try
      {
        Activity localActivity1 = (Activity)Class.forName("com.unity3d.player.UnityPlayer").getField("currentActivity").get(null);
        return localActivity1;
      }
      catch (Exception localException)
      {
        throw new AmazonException("UnexpectedException during getCurrentAndroidActivity() in AmazonIapV2JavaControllerNativeProxy: " + localException);
      }
      Activity localActivity2 = this.activityLifecycleListener.getCurrentAndroidActivity();
    }
  }
  
  public String getProductData(String paramString)
  {
    try
    {
      paramString = amazonIapV2Controller.getProductData(paramString);
      return paramString;
    }
    catch (Exception paramString)
    {
      for (;;)
      {
        paramString = new Gson().toJson(new AmazonError(paramString));
      }
    }
  }
  
  public String getPurchaseUpdates(String paramString)
  {
    try
    {
      paramString = amazonIapV2Controller.getPurchaseUpdates(paramString);
      return paramString;
    }
    catch (Exception paramString)
    {
      for (;;)
      {
        paramString = new Gson().toJson(new AmazonError(paramString));
      }
    }
  }
  
  public String getUserData(String paramString)
  {
    try
    {
      paramString = amazonIapV2Controller.getUserData(paramString);
      return paramString;
    }
    catch (Exception paramString)
    {
      for (;;)
      {
        paramString = new Gson().toJson(new AmazonError(paramString));
      }
    }
  }
  
  public void handleSdkCallback(String paramString)
  {
    nativeCallback(paramString);
  }
  
  public String notifyFulfillment(String paramString)
  {
    try
    {
      paramString = amazonIapV2Controller.notifyFulfillment(paramString);
      return paramString;
    }
    catch (Exception paramString)
    {
      for (;;)
      {
        paramString = new Gson().toJson(new AmazonError(paramString));
      }
    }
  }
  
  public String purchase(String paramString)
  {
    try
    {
      paramString = amazonIapV2Controller.purchase(paramString);
      return paramString;
    }
    catch (Exception paramString)
    {
      for (;;)
      {
        paramString = new Gson().toJson(new AmazonError(paramString));
      }
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\amazon\device\iap\cpt\AmazonIapV2JavaControllerNativeProxy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */