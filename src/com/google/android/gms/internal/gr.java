package com.google.android.gms.internal;

import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings.Secure;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.TextView;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;

@ez
public final class gr
{
  public static final Handler wC = new Handler(Looper.getMainLooper());
  
  public static String R(String paramString)
  {
    int i = 0;
    if (i < 2) {}
    for (;;)
    {
      try
      {
        Object localObject = MessageDigest.getInstance("MD5");
        ((MessageDigest)localObject).update(paramString.getBytes());
        Locale localLocale = Locale.US;
        BigInteger localBigInteger = new java/math/BigInteger;
        localBigInteger.<init>(1, ((MessageDigest)localObject).digest());
        localObject = String.format(localLocale, "%032X", new Object[] { localBigInteger });
        paramString = (String)localObject;
        return paramString;
      }
      catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
      {
        i++;
      }
      break;
      paramString = null;
    }
  }
  
  public static int a(Context paramContext, int paramInt)
  {
    return a(paramContext.getResources().getDisplayMetrics(), paramInt);
  }
  
  public static int a(DisplayMetrics paramDisplayMetrics, int paramInt)
  {
    return (int)TypedValue.applyDimension(1, paramInt, paramDisplayMetrics);
  }
  
  public static void a(ViewGroup paramViewGroup, ay paramay, String paramString)
  {
    a(paramViewGroup, paramay, paramString, -16777216, -1);
  }
  
  private static void a(ViewGroup paramViewGroup, ay paramay, String paramString, int paramInt1, int paramInt2)
  {
    if (paramViewGroup.getChildCount() != 0) {}
    for (;;)
    {
      return;
      Context localContext = paramViewGroup.getContext();
      TextView localTextView = new TextView(localContext);
      localTextView.setGravity(17);
      localTextView.setText(paramString);
      localTextView.setTextColor(paramInt1);
      localTextView.setBackgroundColor(paramInt2);
      paramString = new FrameLayout(localContext);
      paramString.setBackgroundColor(paramInt1);
      paramInt1 = a(localContext, 3);
      paramString.addView(localTextView, new FrameLayout.LayoutParams(paramay.widthPixels - paramInt1, paramay.heightPixels - paramInt1, 17));
      paramViewGroup.addView(paramString, paramay.widthPixels, paramay.heightPixels);
    }
  }
  
  public static void a(ViewGroup paramViewGroup, ay paramay, String paramString1, String paramString2)
  {
    gs.W(paramString2);
    a(paramViewGroup, paramay, paramString1, -65536, -16777216);
  }
  
  public static boolean dr()
  {
    return Build.DEVICE.startsWith("generic");
  }
  
  public static boolean ds()
  {
    if (Looper.myLooper() == Looper.getMainLooper()) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public static String v(Context paramContext)
  {
    paramContext = Settings.Secure.getString(paramContext.getContentResolver(), "android_id");
    if ((paramContext == null) || (dr())) {
      paramContext = "emulator";
    }
    return R(paramContext);
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\internal\gr.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */