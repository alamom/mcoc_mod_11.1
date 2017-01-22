package com.google.android.gms.maps.internal;

import android.content.Context;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.internal.o;
import com.google.android.gms.dynamic.e;
import com.google.android.gms.maps.model.RuntimeRemoteException;

public class u
{
  private static Context ajx;
  private static c ajy;
  
  public static c S(Context paramContext)
    throws GooglePlayServicesNotAvailableException
  {
    o.i(paramContext);
    if (ajy != null) {
      paramContext = ajy;
    }
    for (;;)
    {
      return paramContext;
      T(paramContext);
      ajy = U(paramContext);
      try
      {
        ajy.a(e.k(getRemoteContext(paramContext).getResources()), 6171000);
        paramContext = ajy;
      }
      catch (RemoteException paramContext)
      {
        throw new RuntimeRemoteException(paramContext);
      }
    }
  }
  
  private static void T(Context paramContext)
    throws GooglePlayServicesNotAvailableException
  {
    int i = GooglePlayServicesUtil.isGooglePlayServicesAvailable(paramContext);
    switch (i)
    {
    default: 
      throw new GooglePlayServicesNotAvailableException(i);
    }
  }
  
  private static c U(Context paramContext)
  {
    if (mK()) {
      Log.i(u.class.getSimpleName(), "Making Creator statically");
    }
    for (paramContext = (c)c(mL());; paramContext = c.a.aP((IBinder)a(getRemoteContext(paramContext).getClassLoader(), "com.google.android.gms.maps.internal.CreatorImpl")))
    {
      return paramContext;
      Log.i(u.class.getSimpleName(), "Making Creator dynamically");
    }
  }
  
  private static <T> T a(ClassLoader paramClassLoader, String paramString)
  {
    try
    {
      paramClassLoader = c(((ClassLoader)o.i(paramClassLoader)).loadClass(paramString));
      return paramClassLoader;
    }
    catch (ClassNotFoundException paramClassLoader)
    {
      throw new IllegalStateException("Unable to find dynamic class " + paramString);
    }
  }
  
  private static <T> T c(Class<?> paramClass)
  {
    try
    {
      Object localObject = paramClass.newInstance();
      return (T)localObject;
    }
    catch (InstantiationException localInstantiationException)
    {
      throw new IllegalStateException("Unable to instantiate the dynamic class " + paramClass.getName());
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      throw new IllegalStateException("Unable to call the default constructor of " + paramClass.getName());
    }
  }
  
  private static Context getRemoteContext(Context paramContext)
  {
    if (ajx == null) {
      if (!mK()) {
        break label23;
      }
    }
    label23:
    for (ajx = paramContext.getApplicationContext();; ajx = GooglePlayServicesUtil.getRemoteContext(paramContext)) {
      return ajx;
    }
  }
  
  private static boolean mK()
  {
    return false;
  }
  
  /* Error */
  private static Class<?> mL()
  {
    // Byte code:
    //   0: getstatic 172	android/os/Build$VERSION:SDK_INT	I
    //   3: bipush 15
    //   5: if_icmpge +11 -> 16
    //   8: ldc -82
    //   10: invokestatic 177	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
    //   13: astore_0
    //   14: aload_0
    //   15: areturn
    //   16: ldc 105
    //   18: invokestatic 177	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
    //   21: astore_0
    //   22: goto -8 -> 14
    //   25: astore_0
    //   26: new 179	java/lang/RuntimeException
    //   29: dup
    //   30: aload_0
    //   31: invokespecial 182	java/lang/RuntimeException:<init>	(Ljava/lang/Throwable;)V
    //   34: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   13	9	0	localClass	Class
    //   25	6	0	localClassNotFoundException	ClassNotFoundException
    // Exception table:
    //   from	to	target	type
    //   0	14	25	java/lang/ClassNotFoundException
    //   16	22	25	java/lang/ClassNotFoundException
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\maps\internal\u.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */