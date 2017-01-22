package com.google.android.gms.common;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.Notification;
import android.app.Notification.BigTextStyle;
import android.app.Notification.Builder;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.Resources.Theme;
import android.net.Uri;
import android.net.Uri.Builder;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.util.TypedValue;
import com.google.android.gms.R.drawable;
import com.google.android.gms.R.string;
import com.google.android.gms.common.internal.c;
import com.google.android.gms.common.internal.h;
import com.google.android.gms.common.internal.o;
import com.google.android.gms.internal.jt;
import com.google.android.gms.internal.kc;
import java.io.InputStream;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Set;

public final class GooglePlayServicesUtil
{
  public static final String GMS_ERROR_DIALOG = "GooglePlayServicesErrorDialog";
  public static final String GOOGLE_PLAY_SERVICES_PACKAGE = "com.google.android.gms";
  public static final int GOOGLE_PLAY_SERVICES_VERSION_CODE = 6171000;
  public static final String GOOGLE_PLAY_STORE_PACKAGE = "com.android.vending";
  public static boolean Ii = false;
  public static boolean Ij = false;
  private static int Ik = -1;
  private static final Object Il = new Object();
  
  public static void D(Context paramContext)
    throws GooglePlayServicesRepairableException, GooglePlayServicesNotAvailableException
  {
    int i = isGooglePlayServicesAvailable(paramContext);
    if (i != 0)
    {
      paramContext = c(paramContext, i);
      Log.e("GooglePlayServicesUtil", "GooglePlayServices not available due to error " + i);
      if (paramContext == null) {
        throw new GooglePlayServicesNotAvailableException(i);
      }
      throw new GooglePlayServicesRepairableException(i, "Google Play Services not available", paramContext);
    }
  }
  
  private static void E(Context paramContext)
  {
    Object localObject = null;
    int i;
    try
    {
      paramContext = paramContext.getPackageManager().getApplicationInfo(paramContext.getPackageName(), 128);
      paramContext = paramContext.metaData;
      if (paramContext == null) {
        break label107;
      }
      i = paramContext.getInt("com.google.android.gms.version");
      if (i == 6171000) {
        return;
      }
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      for (;;)
      {
        Log.wtf("GooglePlayServicesUtil", "This should never happen.", paramContext);
        paramContext = (Context)localObject;
      }
    }
    throw new IllegalStateException("The meta-data tag in your app's AndroidManifest.xml does not have the right value.  Expected 6171000 but found " + i + ".  You must have the" + " following declaration within the <application> element: " + "    <meta-data android:name=\"" + "com.google.android.gms.version" + "\" android:value=\"@integer/google_play_services_version\" />");
    label107:
    throw new IllegalStateException("A required meta-data tag in your app's AndroidManifest.xml does not exist.  You must have the following declaration within the <application> element:     <meta-data android:name=\"com.google.android.gms.version\" android:value=\"@integer/google_play_services_version\" />");
  }
  
  private static String F(Context paramContext)
  {
    Object localObject2 = paramContext.getApplicationInfo().name;
    Object localObject1 = localObject2;
    if (TextUtils.isEmpty((CharSequence)localObject2))
    {
      localObject1 = paramContext.getPackageName();
      localObject2 = paramContext.getApplicationContext().getPackageManager();
    }
    try
    {
      paramContext = ((PackageManager)localObject2).getApplicationInfo(paramContext.getPackageName(), 0);
      if (paramContext != null) {
        localObject1 = ((PackageManager)localObject2).getApplicationLabel(paramContext).toString();
      }
      return (String)localObject1;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      for (;;)
      {
        paramContext = null;
      }
    }
  }
  
  private static Dialog a(int paramInt1, Activity paramActivity, Fragment paramFragment, int paramInt2, DialogInterface.OnCancelListener paramOnCancelListener)
  {
    int i = paramInt1;
    if (jt.K(paramActivity))
    {
      i = paramInt1;
      if (paramInt1 == 2) {
        i = 42;
      }
    }
    if (kc.hE())
    {
      localObject1 = new TypedValue();
      paramActivity.getTheme().resolveAttribute(16843529, (TypedValue)localObject1, true);
      if (!"Theme.Dialog.Alert".equals(paramActivity.getResources().getResourceEntryName(((TypedValue)localObject1).resourceId))) {}
    }
    for (Object localObject1 = new AlertDialog.Builder(paramActivity, 5);; localObject1 = null)
    {
      Object localObject2 = localObject1;
      if (localObject1 == null) {
        localObject2 = new AlertDialog.Builder(paramActivity);
      }
      ((AlertDialog.Builder)localObject2).setMessage(d(paramActivity, i));
      if (paramOnCancelListener != null) {
        ((AlertDialog.Builder)localObject2).setOnCancelListener(paramOnCancelListener);
      }
      paramOnCancelListener = c(paramActivity, i);
      if (paramFragment == null)
      {
        paramFragment = new c(paramActivity, paramOnCancelListener, paramInt2);
        paramActivity = e(paramActivity, i);
        if (paramActivity != null) {
          ((AlertDialog.Builder)localObject2).setPositiveButton(paramActivity, paramFragment);
        }
        switch (i)
        {
        default: 
          Log.e("GooglePlayServicesUtil", "Unexpected error code " + i);
          paramActivity = ((AlertDialog.Builder)localObject2).create();
        }
      }
      for (;;)
      {
        return paramActivity;
        paramFragment = new c(paramFragment, paramOnCancelListener, paramInt2);
        break;
        paramActivity = null;
        continue;
        paramActivity = ((AlertDialog.Builder)localObject2).create();
        continue;
        paramActivity = ((AlertDialog.Builder)localObject2).setTitle(R.string.common_google_play_services_install_title).create();
        continue;
        paramActivity = ((AlertDialog.Builder)localObject2).setTitle(R.string.common_google_play_services_enable_title).create();
        continue;
        paramActivity = ((AlertDialog.Builder)localObject2).setTitle(R.string.common_google_play_services_update_title).create();
        continue;
        paramActivity = ((AlertDialog.Builder)localObject2).setTitle(R.string.common_android_wear_update_title).create();
        continue;
        Log.e("GooglePlayServicesUtil", "Google Play services is invalid. Cannot recover.");
        paramActivity = ((AlertDialog.Builder)localObject2).setTitle(R.string.common_google_play_services_unsupported_title).create();
        continue;
        Log.e("GooglePlayServicesUtil", "Network error occurred. Please retry request later.");
        paramActivity = ((AlertDialog.Builder)localObject2).setTitle(R.string.common_google_play_services_network_error_title).create();
        continue;
        Log.e("GooglePlayServicesUtil", "Internal error occurred. Please see logs for detailed information");
        paramActivity = ((AlertDialog.Builder)localObject2).create();
        continue;
        Log.e("GooglePlayServicesUtil", "Developer error occurred. Please see logs for detailed information");
        paramActivity = ((AlertDialog.Builder)localObject2).create();
        continue;
        Log.e("GooglePlayServicesUtil", "An invalid account was specified when connecting. Please provide a valid account.");
        paramActivity = ((AlertDialog.Builder)localObject2).setTitle(R.string.common_google_play_services_invalid_account_title).create();
        continue;
        Log.e("GooglePlayServicesUtil", "The application is not licensed to the user.");
        paramActivity = ((AlertDialog.Builder)localObject2).create();
      }
    }
  }
  
  public static boolean a(PackageManager paramPackageManager, PackageInfo paramPackageInfo)
  {
    boolean bool1 = true;
    boolean bool3 = false;
    boolean bool2 = false;
    if (paramPackageInfo == null) {}
    for (;;)
    {
      return bool2;
      if (c(paramPackageManager))
      {
        if (a(paramPackageInfo, true) != null) {}
        for (;;)
        {
          bool2 = bool1;
          break;
          bool1 = false;
        }
      }
      bool1 = bool3;
      if (a(paramPackageInfo, false) != null) {
        bool1 = true;
      }
      bool2 = bool1;
      if (!bool1)
      {
        bool2 = bool1;
        if (a(paramPackageInfo, true) != null)
        {
          Log.w("GooglePlayServicesUtil", "Test-keys aren't accepted on this build.");
          bool2 = bool1;
        }
      }
    }
  }
  
  public static boolean a(Resources paramResources)
  {
    boolean bool = false;
    if (paramResources == null) {}
    label49:
    for (;;)
    {
      return bool;
      if ((paramResources.getConfiguration().screenLayout & 0xF) > 3) {}
      for (int i = 1;; i = 0)
      {
        if (((!kc.hB()) || (i == 0)) && (!b(paramResources))) {
          break label49;
        }
        bool = true;
        break;
      }
    }
  }
  
  private static byte[] a(PackageInfo paramPackageInfo, boolean paramBoolean)
  {
    if (paramPackageInfo.signatures.length != 1)
    {
      Log.w("GooglePlayServicesUtil", "Package has more than one signature.");
      paramPackageInfo = null;
    }
    for (;;)
    {
      return paramPackageInfo;
      byte[] arrayOfByte = paramPackageInfo.signatures[0].toByteArray();
      if (paramBoolean) {}
      for (paramPackageInfo = b.fY();; paramPackageInfo = b.fZ())
      {
        if (!paramPackageInfo.contains(arrayOfByte)) {
          break label62;
        }
        paramPackageInfo = arrayOfByte;
        break;
      }
      label62:
      if (Log.isLoggable("GooglePlayServicesUtil", 2)) {
        Log.v("GooglePlayServicesUtil", "Signature not valid.  Found: \n" + Base64.encodeToString(arrayOfByte, 0));
      }
      paramPackageInfo = null;
    }
  }
  
  private static byte[] a(PackageInfo paramPackageInfo, byte[]... paramVarArgs)
  {
    if (paramPackageInfo.signatures.length != 1)
    {
      Log.w("GooglePlayServicesUtil", "Package has more than one signature.");
      paramPackageInfo = null;
    }
    for (;;)
    {
      return paramPackageInfo;
      byte[] arrayOfByte = paramPackageInfo.signatures[0].toByteArray();
      for (int i = 0;; i++)
      {
        if (i >= paramVarArgs.length) {
          break label61;
        }
        paramPackageInfo = paramVarArgs[i];
        if (Arrays.equals(paramPackageInfo, arrayOfByte)) {
          break;
        }
      }
      label61:
      if (Log.isLoggable("GooglePlayServicesUtil", 2)) {
        Log.v("GooglePlayServicesUtil", "Signature not valid.  Found: \n" + Base64.encodeToString(arrayOfByte, 0));
      }
      paramPackageInfo = null;
    }
  }
  
  public static Intent ai(int paramInt)
  {
    Intent localIntent;
    switch (paramInt)
    {
    default: 
      localIntent = null;
    }
    for (;;)
    {
      return localIntent;
      localIntent = h.aY("com.google.android.gms");
      continue;
      localIntent = h.gZ();
      continue;
      localIntent = h.aW("com.google.android.gms");
    }
  }
  
  public static boolean b(PackageManager paramPackageManager)
  {
    for (boolean bool = true;; bool = false) {
      synchronized (Il)
      {
        int i = Ik;
        if (i == -1) {}
        try
        {
          if (a(paramPackageManager.getPackageInfo("com.google.android.gms", 64), new byte[][] { b.Ie[1] }) != null) {}
          for (Ik = 1; Ik != 0; Ik = 0) {
            return bool;
          }
        }
        catch (PackageManager.NameNotFoundException paramPackageManager)
        {
          for (;;)
          {
            Ik = 0;
          }
        }
      }
    }
  }
  
  public static boolean b(PackageManager paramPackageManager, String paramString)
  {
    try
    {
      PackageInfo localPackageInfo = paramPackageManager.getPackageInfo(paramString, 64);
      bool = a(paramPackageManager, localPackageInfo);
    }
    catch (PackageManager.NameNotFoundException paramPackageManager)
    {
      for (;;)
      {
        if (Log.isLoggable("GooglePlayServicesUtil", 3)) {
          Log.d("GooglePlayServicesUtil", "Package manager can't find package " + paramString + ", defaulting to false");
        }
        boolean bool = false;
      }
    }
    return bool;
  }
  
  private static boolean b(Resources paramResources)
  {
    boolean bool2 = false;
    paramResources = paramResources.getConfiguration();
    boolean bool1 = bool2;
    if (kc.hD())
    {
      bool1 = bool2;
      if ((paramResources.screenLayout & 0xF) <= 3)
      {
        bool1 = bool2;
        if (paramResources.smallestScreenWidthDp >= 600) {
          bool1 = true;
        }
      }
    }
    return bool1;
  }
  
  @Deprecated
  public static Intent c(Context paramContext, int paramInt)
  {
    return ai(paramInt);
  }
  
  public static boolean c(PackageManager paramPackageManager)
  {
    if ((b(paramPackageManager)) || (!ga())) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public static String d(Context paramContext, int paramInt)
  {
    Resources localResources = paramContext.getResources();
    switch (paramInt)
    {
    default: 
      paramContext = localResources.getString(R.string.common_google_play_services_unknown_issue);
    }
    for (;;)
    {
      return paramContext;
      if (a(paramContext.getResources()))
      {
        paramContext = localResources.getString(R.string.common_google_play_services_install_text_tablet);
      }
      else
      {
        paramContext = localResources.getString(R.string.common_google_play_services_install_text_phone);
        continue;
        paramContext = localResources.getString(R.string.common_google_play_services_enable_text);
        continue;
        paramContext = localResources.getString(R.string.common_google_play_services_update_text);
        continue;
        paramContext = localResources.getString(R.string.common_android_wear_update_text);
        continue;
        paramContext = localResources.getString(R.string.common_google_play_services_unsupported_text);
        continue;
        paramContext = localResources.getString(R.string.common_google_play_services_network_error_text);
        continue;
        paramContext = localResources.getString(R.string.common_google_play_services_invalid_account_text);
      }
    }
  }
  
  public static String e(Context paramContext, int paramInt)
  {
    paramContext = paramContext.getResources();
    switch (paramInt)
    {
    default: 
      paramContext = paramContext.getString(17039370);
    }
    for (;;)
    {
      return paramContext;
      paramContext = paramContext.getString(R.string.common_google_play_services_install_button);
      continue;
      paramContext = paramContext.getString(R.string.common_google_play_services_enable_button);
      continue;
      paramContext = paramContext.getString(R.string.common_google_play_services_update_button);
    }
  }
  
  public static String f(Context paramContext, int paramInt)
  {
    paramContext = paramContext.getResources();
    switch (paramInt)
    {
    default: 
      paramContext = paramContext.getString(R.string.common_google_play_services_unknown_issue);
    }
    for (;;)
    {
      return paramContext;
      paramContext = paramContext.getString(R.string.common_google_play_services_notification_needs_installation_title);
      continue;
      paramContext = paramContext.getString(R.string.common_google_play_services_notification_needs_update_title);
      continue;
      paramContext = paramContext.getString(R.string.common_android_wear_notification_needs_update_text);
      continue;
      paramContext = paramContext.getString(R.string.common_google_play_services_needs_enabling_title);
      continue;
      paramContext = paramContext.getString(R.string.common_google_play_services_unsupported_text);
      continue;
      paramContext = paramContext.getString(R.string.common_google_play_services_network_error_text);
      continue;
      paramContext = paramContext.getString(R.string.common_google_play_services_invalid_account_text);
    }
  }
  
  public static boolean ga()
  {
    if (Ii) {}
    for (boolean bool = Ij;; bool = "user".equals(Build.TYPE)) {
      return bool;
    }
  }
  
  public static Dialog getErrorDialog(int paramInt1, Activity paramActivity, int paramInt2)
  {
    return getErrorDialog(paramInt1, paramActivity, paramInt2, null);
  }
  
  public static Dialog getErrorDialog(int paramInt1, Activity paramActivity, int paramInt2, DialogInterface.OnCancelListener paramOnCancelListener)
  {
    return a(paramInt1, paramActivity, null, paramInt2, paramOnCancelListener);
  }
  
  public static PendingIntent getErrorPendingIntent(int paramInt1, Context paramContext, int paramInt2)
  {
    Intent localIntent = c(paramContext, paramInt1);
    if (localIntent == null) {}
    for (paramContext = null;; paramContext = PendingIntent.getActivity(paramContext, paramInt2, localIntent, 268435456)) {
      return paramContext;
    }
  }
  
  public static String getErrorString(int paramInt)
  {
    String str;
    switch (paramInt)
    {
    default: 
      str = "UNKNOWN_ERROR_CODE";
    }
    for (;;)
    {
      return str;
      str = "SUCCESS";
      continue;
      str = "SERVICE_MISSING";
      continue;
      str = "SERVICE_VERSION_UPDATE_REQUIRED";
      continue;
      str = "SERVICE_DISABLED";
      continue;
      str = "SIGN_IN_REQUIRED";
      continue;
      str = "INVALID_ACCOUNT";
      continue;
      str = "RESOLUTION_REQUIRED";
      continue;
      str = "NETWORK_ERROR";
      continue;
      str = "INTERNAL_ERROR";
      continue;
      str = "SERVICE_INVALID";
      continue;
      str = "DEVELOPER_ERROR";
      continue;
      str = "LICENSE_CHECK_FAILED";
    }
  }
  
  public static String getOpenSourceSoftwareLicenseInfo(Context paramContext)
  {
    Object localObject = new Uri.Builder().scheme("android.resource").authority("com.google.android.gms").appendPath("raw").appendPath("oss_notice").build();
    for (;;)
    {
      try
      {
        localInputStream = paramContext.getContentResolver().openInputStream((Uri)localObject);
      }
      catch (Exception paramContext)
      {
        InputStream localInputStream;
        paramContext = null;
        continue;
      }
      try
      {
        paramContext = new java/util/Scanner;
        paramContext.<init>(localInputStream);
        localObject = paramContext.useDelimiter("\\A").next();
        paramContext = (Context)localObject;
        if (localInputStream != null)
        {
          localInputStream.close();
          paramContext = (Context)localObject;
        }
      }
      catch (NoSuchElementException paramContext)
      {
        paramContext = paramContext;
        if (localInputStream == null) {
          continue;
        }
        localInputStream.close();
        paramContext = null;
      }
      finally
      {
        paramContext = finally;
        if (localInputStream == null) {
          continue;
        }
        localInputStream.close();
        throw paramContext;
      }
    }
    return paramContext;
  }
  
  public static Context getRemoteContext(Context paramContext)
  {
    try
    {
      paramContext = paramContext.createPackageContext("com.google.android.gms", 3);
      return paramContext;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      for (;;)
      {
        paramContext = null;
      }
    }
  }
  
  public static Resources getRemoteResource(Context paramContext)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getResourcesForApplication("com.google.android.gms");
      return paramContext;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      for (;;)
      {
        paramContext = null;
      }
    }
  }
  
  public static int isGooglePlayServicesAvailable(Context paramContext)
  {
    j = 1;
    localObject = paramContext.getPackageManager();
    if (!com.google.android.gms.common.internal.b.Lr) {}
    try
    {
      paramContext.getResources().getString(R.string.common_google_play_services_unknown_issue);
      if (!com.google.android.gms.common.internal.b.Lr) {
        E(paramContext);
      }
      try
      {
        localPackageInfo1 = ((PackageManager)localObject).getPackageInfo("com.google.android.gms", 64);
        if (!jt.aP(localPackageInfo1.versionCode)) {
          break label247;
        }
        if (!ga()) {
          break label142;
        }
        i = 0;
      }
      catch (PackageManager.NameNotFoundException paramContext)
      {
        for (;;)
        {
          PackageInfo localPackageInfo1;
          Log.w("GooglePlayServicesUtil", "Google Play services is missing.");
          int i = j;
          continue;
          i = 1;
          continue;
          paramContext = paramContext.getPackageName();
          try
          {
            PackageInfo localPackageInfo2 = ((PackageManager)localObject).getPackageInfo(paramContext, 64);
            if (a((PackageManager)localObject, localPackageInfo2)) {
              break label363;
            }
            localObject = new java/lang/StringBuilder;
            ((StringBuilder)localObject).<init>();
            Log.w("GooglePlayServicesUtil", "Calling package " + localPackageInfo2.packageName + " signature invalid on Glass.");
            i = 9;
          }
          catch (PackageManager.NameNotFoundException localNameNotFoundException)
          {
            Log.w("GooglePlayServicesUtil", "Could not get info for calling package: " + paramContext);
            i = 9;
          }
          continue;
          if (jt.K(paramContext))
          {
            if (a(localThrowable, b.HH) == null)
            {
              Log.w("GooglePlayServicesUtil", "Google Play services signature invalid.");
              i = 9;
            }
          }
          else
          {
            try
            {
              paramContext = localNameNotFoundException.getPackageInfo("com.android.vending", 64);
              paramContext = a(paramContext, b.HH);
              if (paramContext != null) {
                break label332;
              }
              Log.w("GooglePlayServicesUtil", "Google Play Store signature invalid.");
              i = 9;
            }
            catch (PackageManager.NameNotFoundException paramContext)
            {
              Log.w("GooglePlayServicesUtil", "Google Play Store is missing.");
              i = 9;
            }
            continue;
            if (a(localThrowable, new byte[][] { paramContext }) == null)
            {
              Log.w("GooglePlayServicesUtil", "Google Play services signature invalid.");
              i = 9;
              continue;
            }
          }
          i = jt.aN(6171000);
          if (jt.aN(localThrowable.versionCode) < i)
          {
            Log.w("GooglePlayServicesUtil", "Google Play services out of date.  Requires 6171000 but found " + localThrowable.versionCode);
            i = 2;
          }
          else
          {
            try
            {
              paramContext = localNameNotFoundException.getApplicationInfo("com.google.android.gms", 0);
              if (paramContext.enabled) {
                break label455;
              }
              i = 3;
            }
            catch (PackageManager.NameNotFoundException paramContext)
            {
              Log.wtf("GooglePlayServicesUtil", "Google Play services missing when getting application info.");
              paramContext.printStackTrace();
              i = j;
            }
            continue;
            i = 0;
          }
        }
      }
      if (a(localPackageInfo1, new byte[][] { b.HH[i], b.HN[i], b.HM[i] }) == null)
      {
        Log.w("GooglePlayServicesUtil", "Google Play Services signature invalid on Glass.");
        i = 9;
        return i;
      }
    }
    catch (Throwable localThrowable)
    {
      for (;;)
      {
        Log.e("GooglePlayServicesUtil", "The Google Play services resources were not found. Check your project configuration to ensure that the resources are included.");
      }
    }
  }
  
  public static boolean isGoogleSignedUid(PackageManager paramPackageManager, int paramInt)
  {
    if (paramPackageManager == null) {
      throw new SecurityException("Unknown error: invalid Package Manager");
    }
    String[] arrayOfString = paramPackageManager.getPackagesForUid(paramInt);
    if ((arrayOfString.length == 0) || (!b(paramPackageManager, arrayOfString[0]))) {
      throw new SecurityException("Uid is not Google Signed");
    }
    return true;
  }
  
  public static boolean isUserRecoverableError(int paramInt)
  {
    switch (paramInt)
    {
    }
    for (boolean bool = false;; bool = true) {
      return bool;
    }
  }
  
  public static boolean showErrorDialogFragment(int paramInt1, Activity paramActivity, int paramInt2)
  {
    return showErrorDialogFragment(paramInt1, paramActivity, paramInt2, null);
  }
  
  public static boolean showErrorDialogFragment(int paramInt1, Activity paramActivity, int paramInt2, DialogInterface.OnCancelListener paramOnCancelListener)
  {
    return showErrorDialogFragment(paramInt1, paramActivity, null, paramInt2, paramOnCancelListener);
  }
  
  public static boolean showErrorDialogFragment(int paramInt1, Activity paramActivity, Fragment paramFragment, int paramInt2, DialogInterface.OnCancelListener paramOnCancelListener)
  {
    boolean bool1 = false;
    boolean bool2 = false;
    paramFragment = a(paramInt1, paramActivity, paramFragment, paramInt2, paramOnCancelListener);
    if (paramFragment == null)
    {
      bool1 = bool2;
      return bool1;
    }
    try
    {
      bool2 = paramActivity instanceof FragmentActivity;
      bool1 = bool2;
    }
    catch (NoClassDefFoundError localNoClassDefFoundError)
    {
      for (;;) {}
    }
    if (bool1)
    {
      paramActivity = ((FragmentActivity)paramActivity).getSupportFragmentManager();
      SupportErrorDialogFragment.newInstance(paramFragment, paramOnCancelListener).show(paramActivity, "GooglePlayServicesErrorDialog");
    }
    for (;;)
    {
      bool1 = true;
      break;
      if (!kc.hB()) {
        break label94;
      }
      paramActivity = paramActivity.getFragmentManager();
      ErrorDialogFragment.newInstance(paramFragment, paramOnCancelListener).show(paramActivity, "GooglePlayServicesErrorDialog");
    }
    label94:
    throw new RuntimeException("This Activity does not support Fragments.");
  }
  
  public static void showErrorNotification(int paramInt, Context paramContext)
  {
    boolean bool = jt.K(paramContext);
    int i = paramInt;
    if (bool)
    {
      i = paramInt;
      if (paramInt == 2) {
        i = 42;
      }
    }
    Object localObject = paramContext.getResources();
    String str1 = f(paramContext, i);
    String str2 = ((Resources)localObject).getString(R.string.common_google_play_services_error_notification_requested_by_msg, new Object[] { F(paramContext) });
    PendingIntent localPendingIntent = getErrorPendingIntent(i, paramContext, 0);
    if (bool)
    {
      o.I(kc.hF());
      localObject = new Notification.Builder(paramContext).setSmallIcon(R.drawable.common_ic_googleplayservices).setPriority(2).setAutoCancel(true).setStyle(new Notification.BigTextStyle().bigText(str1 + " " + str2)).addAction(R.drawable.common_full_open_on_phone, ((Resources)localObject).getString(R.string.common_open_on_phone), localPendingIntent).build();
    }
    for (;;)
    {
      ((NotificationManager)paramContext.getSystemService("notification")).notify(39789, (Notification)localObject);
      return;
      localObject = new Notification(17301642, ((Resources)localObject).getString(R.string.common_google_play_services_notification_ticker), System.currentTimeMillis());
      ((Notification)localObject).flags |= 0x10;
      ((Notification)localObject).setLatestEventInfo(paramContext, str1, str2, localPendingIntent);
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\common\GooglePlayServicesUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */