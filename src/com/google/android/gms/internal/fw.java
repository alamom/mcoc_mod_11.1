package com.google.android.gms.internal;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.media.AudioManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.DetailedState;
import android.net.Uri;
import android.os.Build.VERSION;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import java.util.Locale;

@ez
public final class fw
{
  public final int uS;
  public final boolean uT;
  public final boolean uU;
  public final String uV;
  public final String uW;
  public final boolean uX;
  public final boolean uY;
  public final boolean uZ;
  public final String va;
  public final String vb;
  public final int vc;
  public final int vd;
  public final int ve;
  public final int vf;
  public final int vg;
  public final int vh;
  public final float vi;
  public final int vj;
  public final int vk;
  public final double vl;
  public final boolean vm;
  public final boolean vn;
  public final int vo;
  
  public fw(Context paramContext)
  {
    AudioManager localAudioManager = (AudioManager)paramContext.getSystemService("audio");
    ConnectivityManager localConnectivityManager = (ConnectivityManager)paramContext.getSystemService("connectivity");
    DisplayMetrics localDisplayMetrics = paramContext.getResources().getDisplayMetrics();
    Locale localLocale = Locale.getDefault();
    PackageManager localPackageManager = paramContext.getPackageManager();
    TelephonyManager localTelephonyManager = (TelephonyManager)paramContext.getSystemService("phone");
    Intent localIntent = paramContext.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
    this.uS = localAudioManager.getMode();
    boolean bool1;
    if (a(localPackageManager, "geo:0,0?q=donuts") != null)
    {
      bool1 = true;
      this.uT = bool1;
      if (a(localPackageManager, "http://www.google.com") == null) {
        break label386;
      }
      bool1 = true;
      label117:
      this.uU = bool1;
      this.uV = localTelephonyManager.getNetworkOperator();
      this.uW = localLocale.getCountry();
      this.uX = gr.dr();
      this.uY = localAudioManager.isMusicActive();
      this.uZ = localAudioManager.isSpeakerphoneOn();
      this.va = localLocale.getLanguage();
      this.vb = a(localPackageManager);
      this.vc = localAudioManager.getStreamVolume(3);
      this.vd = a(paramContext, localConnectivityManager, localPackageManager);
      this.ve = localTelephonyManager.getNetworkType();
      this.vf = localTelephonyManager.getPhoneType();
      this.vg = localAudioManager.getRingerMode();
      this.vh = localAudioManager.getStreamVolume(2);
      this.vi = localDisplayMetrics.density;
      this.vj = localDisplayMetrics.widthPixels;
      this.vk = localDisplayMetrics.heightPixels;
      if (localIntent == null) {
        break label398;
      }
      int k = localIntent.getIntExtra("status", -1);
      int i = localIntent.getIntExtra("level", -1);
      int j = localIntent.getIntExtra("scale", -1);
      this.vl = (i / j);
      bool1 = bool2;
      if (k != 2)
      {
        if (k != 5) {
          break label392;
        }
        bool1 = bool2;
      }
      label333:
      this.vm = bool1;
      label339:
      if (Build.VERSION.SDK_INT < 16) {
        break label421;
      }
      this.vn = localConnectivityManager.isActiveNetworkMetered();
      if (localConnectivityManager.getActiveNetworkInfo() == null) {
        break label413;
      }
      this.vo = localConnectivityManager.getActiveNetworkInfo().getDetailedState().ordinal();
    }
    for (;;)
    {
      return;
      bool1 = false;
      break;
      label386:
      bool1 = false;
      break label117;
      label392:
      bool1 = false;
      break label333;
      label398:
      this.vl = -1.0D;
      this.vm = false;
      break label339;
      label413:
      this.vo = -1;
      continue;
      label421:
      this.vn = false;
      this.vo = -1;
    }
  }
  
  private static int a(Context paramContext, ConnectivityManager paramConnectivityManager, PackageManager paramPackageManager)
  {
    int i = -2;
    if (gj.a(paramPackageManager, paramContext.getPackageName(), "android.permission.ACCESS_NETWORK_STATE"))
    {
      paramContext = paramConnectivityManager.getActiveNetworkInfo();
      if (paramContext == null) {
        break label32;
      }
    }
    label32:
    for (i = paramContext.getType();; i = -1) {
      return i;
    }
  }
  
  private static ResolveInfo a(PackageManager paramPackageManager, String paramString)
  {
    return paramPackageManager.resolveActivity(new Intent("android.intent.action.VIEW", Uri.parse(paramString)), 65536);
  }
  
  private static String a(PackageManager paramPackageManager)
  {
    Object localObject2 = null;
    Object localObject1 = a(paramPackageManager, "market://details?id=com.google.android.gms.ads");
    if (localObject1 == null) {
      localObject1 = localObject2;
    }
    for (;;)
    {
      return (String)localObject1;
      ActivityInfo localActivityInfo = ((ResolveInfo)localObject1).activityInfo;
      localObject1 = localObject2;
      if (localActivityInfo != null) {
        try
        {
          paramPackageManager = paramPackageManager.getPackageInfo(localActivityInfo.packageName, 0);
          localObject1 = localObject2;
          if (paramPackageManager != null)
          {
            localObject1 = new java/lang/StringBuilder;
            ((StringBuilder)localObject1).<init>();
            localObject1 = paramPackageManager.versionCode + "." + localActivityInfo.packageName;
          }
        }
        catch (PackageManager.NameNotFoundException paramPackageManager)
        {
          localObject1 = localObject2;
        }
      }
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\internal\fw.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */