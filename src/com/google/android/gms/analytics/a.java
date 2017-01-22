package com.google.android.gms.analytics;

import android.content.Context;
import android.text.TextUtils;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Locale;

class a
  implements l
{
  private static a xA;
  private static Object xz = new Object();
  private Context mContext;
  private AdvertisingIdClient.Info xB;
  private long xC;
  private String xD;
  private boolean xE = false;
  private Object xF = new Object();
  
  a(Context paramContext)
  {
    this.mContext = paramContext.getApplicationContext();
  }
  
  private boolean a(AdvertisingIdClient.Info paramInfo1, AdvertisingIdClient.Info paramInfo2)
  {
    StringBuilder localStringBuilder = null;
    boolean bool;
    if (paramInfo2 == null)
    {
      paramInfo2 = null;
      if (!TextUtils.isEmpty(paramInfo2)) {
        break label28;
      }
      bool = true;
    }
    label28:
    h localh;
    Object localObject1;
    for (;;)
    {
      return bool;
      paramInfo2 = paramInfo2.getId();
      break;
      h.y(this.mContext);
      localh = h.dQ();
      localObject1 = localh.getValue("&cid");
      for (;;)
      {
        synchronized (this.xF)
        {
          if (!this.xE)
          {
            this.xD = x(this.mContext);
            this.xE = true;
            paramInfo1 = new java/lang/StringBuilder;
            paramInfo1.<init>();
            paramInfo1 = aa(paramInfo2 + (String)localObject1);
            if (!TextUtils.isEmpty(paramInfo1)) {
              break label222;
            }
            bool = false;
          }
        }
        if (TextUtils.isEmpty(this.xD))
        {
          if (paramInfo1 == null) {}
          for (paramInfo1 = localStringBuilder;; paramInfo1 = paramInfo1.getId())
          {
            if (paramInfo1 != null) {
              break label188;
            }
            paramInfo1 = new java/lang/StringBuilder;
            paramInfo1.<init>();
            bool = ab(paramInfo2 + (String)localObject1);
            break;
          }
          label188:
          localStringBuilder = new java/lang/StringBuilder;
          localStringBuilder.<init>();
          this.xD = aa(paramInfo1 + (String)localObject1);
        }
      }
      label222:
      if (!paramInfo1.equals(this.xD)) {
        break label241;
      }
      bool = true;
    }
    label241:
    if (!TextUtils.isEmpty(this.xD))
    {
      z.V("Resetting the client id because Advertising Id changed.");
      paramInfo1 = localh.dR();
      localObject1 = new java/lang/StringBuilder;
      ((StringBuilder)localObject1).<init>();
      z.V("New client Id: " + paramInfo1);
    }
    for (;;)
    {
      localObject1 = new java/lang/StringBuilder;
      ((StringBuilder)localObject1).<init>();
      bool = ab(paramInfo2 + paramInfo1);
      break;
      paramInfo1 = (AdvertisingIdClient.Info)localObject1;
    }
  }
  
  static String aa(String paramString)
  {
    MessageDigest localMessageDigest = aj.ap("MD5");
    if (localMessageDigest == null) {}
    for (paramString = null;; paramString = String.format(Locale.US, "%032X", new Object[] { new BigInteger(1, localMessageDigest.digest(paramString.getBytes())) })) {
      return paramString;
    }
  }
  
  private boolean ab(String paramString)
  {
    boolean bool = false;
    try
    {
      String str = aa(paramString);
      z.V("Storing hashed adid.");
      paramString = this.mContext.openFileOutput("gaClientIdData", 0);
      paramString.write(str.getBytes());
      paramString.close();
      this.xD = str;
      bool = true;
    }
    catch (FileNotFoundException paramString)
    {
      for (;;)
      {
        z.T("Error creating hash file.");
      }
    }
    catch (IOException paramString)
    {
      for (;;)
      {
        z.T("Error writing to hash file.");
      }
    }
    return bool;
  }
  
  public static l w(Context paramContext)
  {
    if (xA == null) {}
    synchronized (xz)
    {
      if (xA == null)
      {
        a locala = new com/google/android/gms/analytics/a;
        locala.<init>(paramContext);
        xA = locala;
      }
      return xA;
    }
  }
  
  static String x(Context paramContext)
  {
    localObject4 = null;
    localObject3 = null;
    for (;;)
    {
      try
      {
        localFileInputStream = paramContext.openFileInput("gaClientIdData");
        localObject1 = new byte[''];
        i = localFileInputStream.read((byte[])localObject1, 0, 128);
        if (localFileInputStream.available() <= 0) {
          continue;
        }
        z.W("Hash file seems corrupted, deleting it.");
        localFileInputStream.close();
        paramContext.deleteFile("gaClientIdData");
        paramContext = (Context)localObject3;
      }
      catch (FileNotFoundException paramContext)
      {
        int i;
        paramContext = (Context)localObject3;
        continue;
        localObject1 = new String((byte[])localObject1, 0, i);
      }
      catch (IOException localIOException1)
      {
        try
        {
          FileInputStream localFileInputStream;
          Object localObject1;
          localFileInputStream.close();
          paramContext = (Context)localObject1;
        }
        catch (IOException localIOException2)
        {
          continue;
        }
        catch (FileNotFoundException paramContext)
        {
          Object localObject2;
          paramContext = (Context)localObject2;
        }
        localIOException1 = localIOException1;
        localObject2 = localObject4;
        z.W("Error reading Hash file, deleting it.");
        paramContext.deleteFile("gaClientIdData");
        paramContext = (Context)localObject2;
        continue;
        continue;
      }
      return paramContext;
      if (i > 0) {
        continue;
      }
      z.U("Hash file is empty.");
      localFileInputStream.close();
      paramContext = (Context)localObject3;
    }
  }
  
  AdvertisingIdClient.Info dG()
  {
    Object localObject = null;
    try
    {
      AdvertisingIdClient.Info localInfo = AdvertisingIdClient.getAdvertisingIdInfo(this.mContext);
      localObject = localInfo;
    }
    catch (IllegalStateException localIllegalStateException)
    {
      for (;;)
      {
        z.W("IllegalStateException getting Ad Id Info. If you would like to see Audience reports, please ensure that you have added '<meta-data android:name=\"com.google.android.gms.version\" android:value=\"@integer/google_play_services_version\" />' to your application manifest file. See http://goo.gl/naFqQk for details.");
      }
    }
    catch (GooglePlayServicesRepairableException localGooglePlayServicesRepairableException)
    {
      for (;;)
      {
        z.W("GooglePlayServicesRepairableException getting Ad Id Info");
      }
    }
    catch (IOException localIOException)
    {
      for (;;)
      {
        z.W("IOException getting Ad Id Info");
      }
    }
    catch (GooglePlayServicesNotAvailableException localGooglePlayServicesNotAvailableException)
    {
      for (;;)
      {
        z.W("GooglePlayServicesNotAvailableException getting Ad Id Info");
      }
    }
    catch (Throwable localThrowable)
    {
      for (;;)
      {
        z.W("Unknown exception. Could not get the ad Id.");
      }
    }
    return (AdvertisingIdClient.Info)localObject;
  }
  
  public String getValue(String paramString)
  {
    long l = System.currentTimeMillis();
    if (l - this.xC > 1000L)
    {
      AdvertisingIdClient.Info localInfo = dG();
      if (a(this.xB, localInfo))
      {
        this.xB = localInfo;
        this.xC = l;
      }
    }
    else
    {
      if (this.xB == null) {
        break label126;
      }
      if (!"&adid".equals(paramString)) {
        break label92;
      }
      paramString = this.xB.getId();
    }
    for (;;)
    {
      return paramString;
      this.xB = new AdvertisingIdClient.Info("", false);
      break;
      label92:
      if ("&ate".equals(paramString))
      {
        if (this.xB.isLimitAdTrackingEnabled()) {
          paramString = "0";
        } else {
          paramString = "1";
        }
      }
      else {
        label126:
        paramString = null;
      }
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\analytics\a.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */