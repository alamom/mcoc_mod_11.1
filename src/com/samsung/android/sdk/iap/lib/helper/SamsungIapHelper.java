package com.samsung.android.sdk.iap.lib.helper;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.AsyncTask.Status;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import com.samsung.android.sdk.iap.lib.R.string;
import com.samsung.android.sdk.iap.lib.activity.BaseActivity;
import com.samsung.android.sdk.iap.lib.activity.InboxActivity;
import com.samsung.android.sdk.iap.lib.activity.ItemActivity;
import com.samsung.android.sdk.iap.lib.activity.PaymentActivity;
import com.samsung.android.sdk.iap.lib.listener.OnGetInboxListener;
import com.samsung.android.sdk.iap.lib.listener.OnGetItemListener;
import com.samsung.android.sdk.iap.lib.listener.OnIapBindListener;
import com.samsung.android.sdk.iap.lib.listener.OnInitIapListener;
import com.samsung.android.sdk.iap.lib.listener.OnPaymentListener;
import com.samsung.android.sdk.iap.lib.vo.ErrorVo;
import com.samsung.android.sdk.iap.lib.vo.InboxVo;
import com.samsung.android.sdk.iap.lib.vo.ItemVo;
import com.samsung.android.sdk.iap.lib.vo.PurchaseVo;
import com.samsung.android.sdk.iap.lib.vo.VerificationVo;
import com.sec.android.iap.IAPConnector;
import com.sec.android.iap.IAPConnector.Stub;
import java.util.ArrayList;
import java.util.Iterator;

public class SamsungIapHelper
{
  private static final int FLAG_INCLUDE_STOPPED_PACKAGES = 32;
  private static final int HONEYCOMB_MR1 = 12;
  public static final int IAP_ERROR_ALREADY_PURCHASED = -1003;
  public static final int IAP_ERROR_COMMON = -1002;
  public static final int IAP_ERROR_CONFIRM_INBOX = -1006;
  public static final int IAP_ERROR_CONNECT_TIMEOUT = -1011;
  public static final int IAP_ERROR_INITIALIZATION = -1000;
  public static final int IAP_ERROR_IOEXCEPTION_ERROR = -1009;
  public static final int IAP_ERROR_ITEM_GROUP_DOES_NOT_EXIST = -1007;
  public static final int IAP_ERROR_NEED_APP_UPGRADE = -1001;
  public static final int IAP_ERROR_NETWORK_NOT_AVAILABLE = -1008;
  public static final int IAP_ERROR_NONE = 0;
  public static final int IAP_ERROR_NOT_AVAILABLE_SHOP = -1013;
  public static final int IAP_ERROR_NOT_EXIST_LOCAL_PRICE = -1012;
  public static final int IAP_ERROR_PRODUCT_DOES_NOT_EXIST = -1005;
  public static final int IAP_ERROR_SOCKET_TIMEOUT = -1010;
  public static final int IAP_ERROR_WHILE_RUNNING = -1004;
  public static final int IAP_MODE_COMMERCIAL = 0;
  public static final int IAP_MODE_TEST_FAIL = -1;
  public static final int IAP_MODE_TEST_SUCCESS = 1;
  public static final String IAP_PACKAGE_NAME = "com.sec.android.iap";
  public static final int IAP_PAYMENT_IS_CANCELED = 1;
  public static final int IAP_RESPONSE_RESULT_OK = 0;
  public static final int IAP_RESPONSE_RESULT_UNAVAILABLE = 2;
  public static final String IAP_SERVICE_NAME = "com.sec.android.iap.service.iapService";
  public static final int IAP_SIGNATURE_HASHCODE = 2055122763;
  public static final int INBOX_TYPE_ALL_ITEMS = 0;
  public static final int INBOX_TYPE_NONE = -1;
  public static final int INBOX_TYPE_SELECTED_ITEMS = 1;
  public static final String ITEM_TYPE_ALL = "10";
  public static final String ITEM_TYPE_AUTO_RECURRING_SUBSCRIPTIONS = "03";
  public static final String ITEM_TYPE_CONSUMABLE = "00";
  public static final String ITEM_TYPE_NON_CONSUMABLE = "01";
  public static final String ITEM_TYPE_SUBSCRIPTION = "02";
  public static final String KEY_NAME_ERROR_STRING = "ERROR_STRING";
  public static final String KEY_NAME_IAP_UPGRADE_URL = "IAP_UPGRADE_URL";
  public static final String KEY_NAME_ITEM_GROUP_ID = "ITEM_GROUP_ID";
  public static final String KEY_NAME_ITEM_ID = "ITEM_ID";
  public static final String KEY_NAME_RESULT_LIST = "RESULT_LIST";
  public static final String KEY_NAME_RESULT_OBJECT = "RESULT_OBJECT";
  public static final String KEY_NAME_STATUS_CODE = "STATUS_CODE";
  public static final String KEY_NAME_THIRD_PARTY_NAME = "THIRD_PARTY_NAME";
  public static final int REQUEST_CODE_IS_ACCOUNT_CERTIFICATION = 2;
  public static final int REQUEST_CODE_IS_IAP_PAYMENT = 1;
  private static final int STATE_BINDING = 1;
  private static final int STATE_READY = 2;
  private static final int STATE_TERM = 0;
  private static final String TAG = SamsungIapHelper.class.getSimpleName();
  private static final String VERIFY_URL = "https://iap.samsungapps.com/iap/appsItemVerifyIAPReceipt.as?protocolVersion=2.0";
  private static SamsungIapHelper mInstance = null;
  private Context mContext = null;
  private GetInboxListTask mGetInboxListTask = null;
  private GetItemListTask mGetItemListTask = null;
  private IAPConnector mIapConnector = null;
  private InitIapTask mInitIapTask = null;
  private int mMode = 1;
  private OnGetInboxListener mOnGetInboxListener = null;
  private OnGetItemListener mOnGetItemListener = null;
  private OnInitIapListener mOnInitIapListener = null;
  private OnPaymentListener mOnPaymentListener = null;
  private ServiceConnection mServiceConn = null;
  private int mState = 0;
  private VerifyClientToServer mVerifyClientToServer = null;
  
  private SamsungIapHelper(Context paramContext, int paramInt)
  {
    _setContextAndMode(paramContext, paramInt);
  }
  
  private void _setContextAndMode(Context paramContext, int paramInt)
  {
    this.mContext = paramContext.getApplicationContext();
    this.mMode = paramInt;
  }
  
  public static SamsungIapHelper getInstance(Context paramContext, int paramInt)
  {
    if (mInstance == null) {
      mInstance = new SamsungIapHelper(paramContext, paramInt);
    }
    for (;;)
    {
      return mInstance;
      mInstance._setContextAndMode(paramContext, paramInt);
    }
  }
  
  private void stopTasksIfNotFinished()
  {
    if ((this.mInitIapTask != null) && (this.mInitIapTask.getStatus() != AsyncTask.Status.FINISHED)) {
      this.mInitIapTask.cancel(true);
    }
    if ((this.mGetItemListTask != null) && (this.mGetItemListTask.getStatus() != AsyncTask.Status.FINISHED)) {
      this.mGetItemListTask.cancel(true);
    }
    if ((this.mGetInboxListTask != null) && (this.mGetInboxListTask.getStatus() != AsyncTask.Status.FINISHED)) {
      this.mGetInboxListTask.cancel(true);
    }
    if ((this.mVerifyClientToServer != null) && (this.mVerifyClientToServer.getStatus() != AsyncTask.Status.FINISHED)) {
      this.mVerifyClientToServer.cancel(true);
    }
  }
  
  public void bindIapService(final OnIapBindListener paramOnIapBindListener)
  {
    if (this.mState >= 1) {
      if (paramOnIapBindListener != null) {
        paramOnIapBindListener.onBindIapFinished(0);
      }
    }
    for (;;)
    {
      return;
      this.mServiceConn = new ServiceConnection()
      {
        public void onServiceConnected(ComponentName paramAnonymousComponentName, IBinder paramAnonymousIBinder)
        {
          SamsungIapHelper.access$202(SamsungIapHelper.this, IAPConnector.Stub.asInterface(paramAnonymousIBinder));
          if (paramOnIapBindListener != null)
          {
            if (SamsungIapHelper.this.mIapConnector == null) {
              break label49;
            }
            SamsungIapHelper.access$102(SamsungIapHelper.this, 1);
            paramOnIapBindListener.onBindIapFinished(0);
          }
          for (;;)
          {
            return;
            label49:
            SamsungIapHelper.access$102(SamsungIapHelper.this, 0);
            paramOnIapBindListener.onBindIapFinished(2);
          }
        }
        
        public void onServiceDisconnected(ComponentName paramAnonymousComponentName)
        {
          Log.d(SamsungIapHelper.TAG, "IAP Service Disconnected...");
          SamsungIapHelper.access$102(SamsungIapHelper.this, 0);
          SamsungIapHelper.access$202(SamsungIapHelper.this, null);
          SamsungIapHelper.access$302(SamsungIapHelper.this, null);
        }
      };
      paramOnIapBindListener = new Intent();
      paramOnIapBindListener.setComponent(new ComponentName("com.sec.android.iap", "com.sec.android.iap.service.IAPService"));
      this.mContext.bindService(paramOnIapBindListener, this.mServiceConn, 1);
    }
  }
  
  public void dispose()
  {
    stopTasksIfNotFinished();
    if ((this.mContext != null) && (this.mServiceConn != null)) {
      this.mContext.unbindService(this.mServiceConn);
    }
    this.mState = 0;
    this.mServiceConn = null;
    this.mIapConnector = null;
  }
  
  public void getItemInboxList(int paramInt1, int paramInt2, String paramString1, String paramString2, OnGetInboxListener paramOnGetInboxListener)
  {
    getItemInboxList(paramInt1, paramInt2, paramString1, paramString2, paramOnGetInboxListener, true);
  }
  
  public void getItemInboxList(int paramInt1, int paramInt2, String paramString1, String paramString2, OnGetInboxListener paramOnGetInboxListener, boolean paramBoolean)
  {
    if (paramOnGetInboxListener == null) {
      try
      {
        paramString1 = new java/lang/Exception;
        paramString1.<init>("OnGetInboxListener is null");
        throw paramString1;
      }
      catch (Exception paramString1)
      {
        paramString1.printStackTrace();
      }
    }
    for (;;)
    {
      return;
      setOnGetInboxListener(paramOnGetInboxListener);
      paramOnGetInboxListener = new android/content/Intent;
      paramOnGetInboxListener.<init>(this.mContext, InboxActivity.class);
      paramOnGetInboxListener.putExtra("OpenApiType", 0);
      paramOnGetInboxListener.putExtra("IapMode", this.mMode);
      paramOnGetInboxListener.putExtra("StartNum", paramInt1);
      paramOnGetInboxListener.putExtra("EndNum", paramInt2);
      paramOnGetInboxListener.putExtra("StartDate", paramString1);
      paramOnGetInboxListener.putExtra("EndDate", paramString2);
      paramOnGetInboxListener.putExtra("ShowErrorDialog", paramBoolean);
      paramOnGetInboxListener.setFlags(268435456);
      this.mContext.startActivity(paramOnGetInboxListener);
    }
  }
  
  public void getItemInboxList(String paramString1, int paramInt1, int paramInt2, String paramString2, String paramString3, OnGetInboxListener paramOnGetInboxListener)
  {
    getItemInboxList(paramInt1, paramInt2, paramString2, paramString3, paramOnGetInboxListener, true);
  }
  
  public void getItemInboxList(String paramString, OnGetInboxListener paramOnGetInboxListener)
  {
    getItemInboxList(paramString, paramOnGetInboxListener, true);
  }
  
  public void getItemInboxList(String paramString, OnGetInboxListener paramOnGetInboxListener, boolean paramBoolean)
  {
    if (paramOnGetInboxListener == null) {
      try
      {
        paramString = new java/lang/Exception;
        paramString.<init>("OnGetInboxListener is null");
        throw paramString;
      }
      catch (Exception paramString)
      {
        paramString.printStackTrace();
      }
    }
    for (;;)
    {
      return;
      setOnGetInboxListener(paramOnGetInboxListener);
      paramOnGetInboxListener = new android/content/Intent;
      paramOnGetInboxListener.<init>(this.mContext, InboxActivity.class);
      paramOnGetInboxListener.putExtra("OpenApiType", 1);
      paramOnGetInboxListener.putExtra("ItemIds", paramString);
      paramOnGetInboxListener.putExtra("IapMode", this.mMode);
      paramOnGetInboxListener.putExtra("ShowErrorDialog", paramBoolean);
      paramOnGetInboxListener.setFlags(268435456);
      this.mContext.startActivity(paramOnGetInboxListener);
    }
  }
  
  public void getItemInboxList(String paramString1, String paramString2, OnGetInboxListener paramOnGetInboxListener)
  {
    getItemInboxList(paramString2, paramOnGetInboxListener, true);
  }
  
  public void getItemList(int paramInt1, int paramInt2, String paramString, int paramInt3, OnGetItemListener paramOnGetItemListener)
  {
    getItemList(paramInt1, paramInt2, paramString, paramInt3, paramOnGetItemListener, true);
  }
  
  public void getItemList(int paramInt1, int paramInt2, String paramString, int paramInt3, OnGetItemListener paramOnGetItemListener, boolean paramBoolean)
  {
    if (paramOnGetItemListener == null) {
      try
      {
        paramString = new java/lang/Exception;
        paramString.<init>("OnGetItemListener is null");
        throw paramString;
      }
      catch (Exception paramString)
      {
        paramString.printStackTrace();
      }
    }
    for (;;)
    {
      return;
      setOnGetItemListener(paramOnGetItemListener);
      paramOnGetItemListener = new android/content/Intent;
      paramOnGetItemListener.<init>(this.mContext, ItemActivity.class);
      paramOnGetItemListener.putExtra("StartNum", paramInt1);
      paramOnGetItemListener.putExtra("EndNum", paramInt2);
      paramOnGetItemListener.putExtra("ItemType", paramString);
      paramOnGetItemListener.putExtra("IapMode", paramInt3);
      paramOnGetItemListener.putExtra("ShowErrorDialog", paramBoolean);
      paramOnGetItemListener.setFlags(268435456);
      this.mContext.startActivity(paramOnGetItemListener);
    }
  }
  
  public void getItemList(String paramString1, int paramInt1, int paramInt2, String paramString2, int paramInt3, OnGetItemListener paramOnGetItemListener)
  {
    getItemList(paramInt1, paramInt2, paramString2, paramInt3, paramOnGetItemListener, true);
  }
  
  public OnGetInboxListener getOnGetInboxListener()
  {
    return this.mOnGetInboxListener;
  }
  
  public OnGetItemListener getOnGetItemListener()
  {
    return this.mOnGetItemListener;
  }
  
  public OnPaymentListener getOnPaymentListener()
  {
    return this.mOnPaymentListener;
  }
  
  public void init(ErrorVo paramErrorVo)
  {
    try
    {
      Bundle localBundle = this.mIapConnector.init(this.mMode);
      if (localBundle != null)
      {
        paramErrorVo.setError(localBundle.getInt("STATUS_CODE"), localBundle.getString("ERROR_STRING"));
        paramErrorVo.setExtraString(localBundle.getString("IAP_UPGRADE_URL"));
      }
      return;
    }
    catch (RemoteException paramErrorVo)
    {
      for (;;)
      {
        paramErrorVo.printStackTrace();
      }
    }
  }
  
  public void installIapPackage(BaseActivity paramBaseActivity)
  {
    Runnable local2 = new Runnable()
    {
      public void run()
      {
        Uri localUri = Uri.parse("samsungapps://ProductDetail/com.sec.android.iap");
        Intent localIntent = new Intent();
        localIntent.setData(localUri);
        if (Build.VERSION.SDK_INT >= 12) {
          localIntent.addFlags(335544352);
        }
        for (;;)
        {
          if (localIntent.resolveActivity(SamsungIapHelper.this.mContext.getPackageManager()) != null) {
            SamsungIapHelper.this.mContext.startActivity(localIntent);
          }
          return;
          localIntent.addFlags(335544320);
        }
      }
    };
    ErrorVo localErrorVo = new ErrorVo();
    paramBaseActivity.setErrorVo(localErrorVo);
    localErrorVo.setError(1, paramBaseActivity.getString(R.string.IDS_SAPPS_POP_PAYMENT_CANCELLED));
    showIapDialogIfNeeded(paramBaseActivity, paramBaseActivity.getString(R.string.IDS_SAPPS_POP_SAMSUNG_IN_APP_PURCHASE), paramBaseActivity.getString(R.string.IDS_SAPPS_POP_TO_PURCHASE_ITEMS_YOU_NEED_TO_INSTALL_SAMSUNG_IN_APP_PURCHASE_INSTALL_Q), true, local2, true);
  }
  
  public boolean isInstalledIapPackage(Context paramContext)
  {
    paramContext = paramContext.getPackageManager();
    try
    {
      paramContext.getApplicationInfo("com.sec.android.iap", 128);
      bool = true;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      for (;;)
      {
        paramContext.printStackTrace();
        boolean bool = false;
      }
    }
    return bool;
  }
  
  public boolean isValidIapPackage(Context paramContext)
  {
    bool = true;
    try
    {
      int i = paramContext.getPackageManager().getPackageInfo("com.sec.android.iap", 64).signatures[0].hashCode();
      if (i != 2055122763) {
        bool = false;
      }
    }
    catch (Exception paramContext)
    {
      for (;;)
      {
        paramContext.printStackTrace();
        bool = false;
      }
    }
    return bool;
  }
  
  public void removeAllListener()
  {
    this.mOnGetInboxListener = null;
    this.mOnGetItemListener = null;
    this.mOnPaymentListener = null;
  }
  
  public void safeGetItemInboxTask(BaseActivity paramBaseActivity, int paramInt1, int paramInt2, String paramString1, String paramString2, boolean paramBoolean)
  {
    try
    {
      if ((this.mGetInboxListTask != null) && (this.mGetInboxListTask.getStatus() != AsyncTask.Status.FINISHED)) {
        this.mGetInboxListTask.cancel(true);
      }
      GetInboxListTask localGetInboxListTask = new com/samsung/android/sdk/iap/lib/helper/SamsungIapHelper$GetInboxListTask;
      localGetInboxListTask.<init>(this, paramBaseActivity, paramInt1, paramInt2, paramString1, paramString2, paramBoolean);
      this.mGetInboxListTask = localGetInboxListTask;
      this.mGetInboxListTask.execute(new String[0]);
      return;
    }
    catch (Exception paramString1)
    {
      for (;;)
      {
        if (paramBaseActivity != null) {
          paramBaseActivity.finish();
        }
        paramString1.printStackTrace();
      }
    }
  }
  
  public void safeGetItemInboxTask(BaseActivity paramBaseActivity, String paramString, boolean paramBoolean)
  {
    try
    {
      if ((this.mGetInboxListTask != null) && (this.mGetInboxListTask.getStatus() != AsyncTask.Status.FINISHED)) {
        this.mGetInboxListTask.cancel(true);
      }
      GetInboxListTask localGetInboxListTask = new com/samsung/android/sdk/iap/lib/helper/SamsungIapHelper$GetInboxListTask;
      localGetInboxListTask.<init>(this, paramBaseActivity, paramString, paramBoolean);
      this.mGetInboxListTask = localGetInboxListTask;
      this.mGetInboxListTask.execute(new String[0]);
      return;
    }
    catch (Exception paramString)
    {
      for (;;)
      {
        if (paramBaseActivity != null) {
          paramBaseActivity.finish();
        }
        paramString.printStackTrace();
      }
    }
  }
  
  public void safeGetItemList(BaseActivity paramBaseActivity, int paramInt1, int paramInt2, String paramString, boolean paramBoolean)
  {
    try
    {
      if ((this.mGetItemListTask != null) && (this.mGetItemListTask.getStatus() != AsyncTask.Status.FINISHED)) {
        this.mGetItemListTask.cancel(true);
      }
      GetItemListTask localGetItemListTask = new com/samsung/android/sdk/iap/lib/helper/SamsungIapHelper$GetItemListTask;
      localGetItemListTask.<init>(this, paramBaseActivity, paramInt1, paramInt2, paramString, paramBoolean);
      this.mGetItemListTask = localGetItemListTask;
      this.mGetItemListTask.execute(new String[0]);
      return;
    }
    catch (Exception paramString)
    {
      for (;;)
      {
        if (paramBaseActivity != null) {
          paramBaseActivity.finish();
        }
        paramString.printStackTrace();
      }
    }
  }
  
  public void safeInitIap(BaseActivity paramBaseActivity, boolean paramBoolean)
  {
    try
    {
      if ((this.mInitIapTask != null) && (this.mInitIapTask.getStatus() != AsyncTask.Status.FINISHED)) {
        this.mInitIapTask.cancel(true);
      }
      InitIapTask localInitIapTask = new com/samsung/android/sdk/iap/lib/helper/SamsungIapHelper$InitIapTask;
      localInitIapTask.<init>(this, paramBaseActivity, paramBoolean);
      this.mInitIapTask = localInitIapTask;
      this.mInitIapTask.execute(new String[0]);
      return;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        if (paramBaseActivity != null) {
          paramBaseActivity.finish();
        }
        localException.printStackTrace();
      }
    }
  }
  
  public void setOnGetInboxListener(OnGetInboxListener paramOnGetInboxListener)
  {
    this.mOnGetInboxListener = paramOnGetInboxListener;
  }
  
  public void setOnGetItemListener(OnGetItemListener paramOnGetItemListener)
  {
    this.mOnGetItemListener = paramOnGetItemListener;
  }
  
  public void setOnInitIapListener(OnInitIapListener paramOnInitIapListener)
  {
    this.mOnInitIapListener = paramOnInitIapListener;
  }
  
  public void setOnPaymentListener(OnPaymentListener paramOnPaymentListener)
  {
    this.mOnPaymentListener = paramOnPaymentListener;
  }
  
  public void showIapDialogIfNeeded(final Activity paramActivity, String paramString1, String paramString2, final boolean paramBoolean1, final Runnable paramRunnable, boolean paramBoolean2)
  {
    if (!paramBoolean2) {
      if (paramBoolean1 != true) {}
    }
    for (;;)
    {
      try
      {
        paramActivity.finish();
        return;
      }
      catch (Exception paramActivity)
      {
        paramActivity.printStackTrace();
        continue;
      }
      AlertDialog.Builder localBuilder = new AlertDialog.Builder(paramActivity);
      localBuilder.setTitle(paramString1);
      localBuilder.setMessage(paramString2);
      localBuilder.setPositiveButton(17039370, new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
        {
          if (paramRunnable != null) {
            paramRunnable.run();
          }
          paramAnonymousDialogInterface.dismiss();
          if (true == paramBoolean1) {
            paramActivity.finish();
          }
        }
      });
      if (true == paramBoolean1) {
        localBuilder.setOnCancelListener(new DialogInterface.OnCancelListener()
        {
          public void onCancel(DialogInterface paramAnonymousDialogInterface)
          {
            paramActivity.finish();
          }
        });
      }
      try
      {
        localBuilder.show();
      }
      catch (Exception paramActivity)
      {
        paramActivity.printStackTrace();
      }
    }
  }
  
  public void startAccountActivity(Activity paramActivity)
  {
    ComponentName localComponentName = new ComponentName("com.sec.android.iap", "com.sec.android.iap.activity.AccountActivity");
    Intent localIntent = new Intent();
    localIntent.setComponent(localComponentName);
    if (localIntent.resolveActivity(this.mContext.getPackageManager()) != null) {
      paramActivity.startActivityForResult(localIntent, 2);
    }
  }
  
  public void startPayment(String paramString1, String paramString2, boolean paramBoolean, OnPaymentListener paramOnPaymentListener)
  {
    startPayment(paramString2, paramBoolean, true, paramOnPaymentListener);
  }
  
  public void startPayment(String paramString, boolean paramBoolean, OnPaymentListener paramOnPaymentListener)
  {
    startPayment(paramString, paramBoolean, true, paramOnPaymentListener);
  }
  
  public void startPayment(String paramString, boolean paramBoolean1, boolean paramBoolean2, OnPaymentListener paramOnPaymentListener)
  {
    if (paramOnPaymentListener == null) {
      try
      {
        paramString = new java/lang/Exception;
        paramString.<init>("OnPaymentListener is null");
        throw paramString;
      }
      catch (Exception paramString)
      {
        paramString.printStackTrace();
      }
    }
    for (;;)
    {
      return;
      setOnPaymentListener(paramOnPaymentListener);
      paramOnPaymentListener = new android/content/Intent;
      paramOnPaymentListener.<init>(this.mContext, PaymentActivity.class);
      paramOnPaymentListener.putExtra("ItemId", paramString);
      paramOnPaymentListener.putExtra("ShowSuccessDialog", paramBoolean1);
      paramOnPaymentListener.putExtra("ShowErrorDialog", paramBoolean2);
      paramOnPaymentListener.putExtra("IapMode", this.mMode);
      paramOnPaymentListener.setFlags(268435456);
      this.mContext.startActivity(paramOnPaymentListener);
    }
  }
  
  public void startPaymentActivity(Activity paramActivity, int paramInt, String paramString)
  {
    try
    {
      Bundle localBundle = new android/os/Bundle;
      localBundle.<init>();
      localBundle.putString("THIRD_PARTY_NAME", this.mContext.getPackageName());
      localBundle.putString("ITEM_ID", paramString);
      ComponentName localComponentName = new android/content/ComponentName;
      localComponentName.<init>("com.sec.android.iap", "com.sec.android.iap.activity.PaymentMethodListActivity");
      paramString = new android/content/Intent;
      paramString.<init>("android.intent.action.MAIN");
      paramString.addCategory("android.intent.category.LAUNCHER");
      paramString.setComponent(localComponentName);
      paramString.putExtras(localBundle);
      if (paramString.resolveActivity(this.mContext.getPackageManager()) != null) {
        paramActivity.startActivityForResult(paramString, paramInt);
      }
      return;
    }
    catch (Exception paramActivity)
    {
      for (;;)
      {
        paramActivity.printStackTrace();
      }
    }
  }
  
  public void verifyPurchaseResult(BaseActivity paramBaseActivity, PurchaseVo paramPurchaseVo, boolean paramBoolean1, boolean paramBoolean2)
  {
    try
    {
      if ((this.mVerifyClientToServer != null) && (this.mVerifyClientToServer.getStatus() != AsyncTask.Status.FINISHED)) {
        this.mVerifyClientToServer.cancel(true);
      }
      VerifyClientToServer localVerifyClientToServer = new com/samsung/android/sdk/iap/lib/helper/SamsungIapHelper$VerifyClientToServer;
      localVerifyClientToServer.<init>(this, paramBaseActivity, paramPurchaseVo, paramBoolean1, paramBoolean2);
      this.mVerifyClientToServer = localVerifyClientToServer;
      this.mVerifyClientToServer.execute(new Void[0]);
      return;
    }
    catch (Exception paramPurchaseVo)
    {
      for (;;)
      {
        if (paramBaseActivity != null) {
          paramBaseActivity.finish();
        }
        paramPurchaseVo.printStackTrace();
      }
    }
  }
  
  private class GetInboxListTask
    extends AsyncTask<String, Object, Boolean>
  {
    private BaseActivity mActivity = null;
    private String mEndDate = "";
    private int mEndNum = 0;
    private ErrorVo mErrorVo = new ErrorVo();
    private ArrayList<InboxVo> mInbox = new ArrayList();
    private int mInboxApiType = 0;
    private String mItemIds = "";
    private boolean mShowErrorDialog = true;
    private String mStartDate = "";
    private int mStartNum = 0;
    
    public GetInboxListTask(BaseActivity paramBaseActivity, int paramInt1, int paramInt2, String paramString1, String paramString2, boolean paramBoolean)
    {
      this.mInboxApiType = 0;
      this.mActivity = paramBaseActivity;
      this.mStartNum = paramInt1;
      this.mEndNum = paramInt2;
      this.mStartDate = paramString1;
      this.mEndDate = paramString2;
      this.mShowErrorDialog = paramBoolean;
      this.mActivity.setErrorVo(this.mErrorVo);
      this.mActivity.setInbox(this.mInbox);
    }
    
    public GetInboxListTask(BaseActivity paramBaseActivity, String paramString, boolean paramBoolean)
    {
      this.mInboxApiType = 1;
      this.mActivity = paramBaseActivity;
      this.mItemIds = paramString;
      this.mShowErrorDialog = paramBoolean;
      this.mActivity.setErrorVo(this.mErrorVo);
      this.mActivity.setInbox(this.mInbox);
    }
    
    protected Boolean doInBackground(String... paramVarArgs)
    {
      for (;;)
      {
        try
        {
          if (1 == this.mInboxApiType)
          {
            paramVarArgs = SamsungIapHelper.this.mIapConnector.getItemsInbox2(this.mActivity.getPackageName(), this.mItemIds);
            if (paramVarArgs == null) {
              break label193;
            }
            this.mErrorVo.setError(paramVarArgs.getInt("STATUS_CODE"), paramVarArgs.getString("ERROR_STRING"));
            if (this.mErrorVo.getErrorCode() != 0) {
              break label233;
            }
            paramVarArgs = paramVarArgs.getStringArrayList("RESULT_LIST");
            if (paramVarArgs == null) {
              break;
            }
            Iterator localIterator = paramVarArgs.iterator();
            if (!localIterator.hasNext()) {
              break label225;
            }
            String str = (String)localIterator.next();
            paramVarArgs = new com/samsung/android/sdk/iap/lib/vo/InboxVo;
            paramVarArgs.<init>(str);
            this.mInbox.add(paramVarArgs);
            continue;
            return paramVarArgs;
          }
        }
        catch (Exception paramVarArgs)
        {
          this.mErrorVo.setError(64534, this.mActivity.getString(R.string.IDS_SAPPS_POP_UNKNOWN_ERROR_OCCURRED));
          paramVarArgs.printStackTrace();
          paramVarArgs = Boolean.valueOf(false);
        }
        paramVarArgs = SamsungIapHelper.this.mIapConnector.getItemsInbox(this.mActivity.getPackageName(), null, this.mStartNum, this.mEndNum, this.mStartDate, this.mEndDate);
        continue;
        label193:
        this.mErrorVo.setError(64534, this.mActivity.getString(R.string.IDS_SAPPS_POP_UNKNOWN_ERROR_OCCURRED));
      }
      Log.d(SamsungIapHelper.TAG, "Bundle Value 'RESULT_LIST' is null.");
      for (;;)
      {
        label225:
        paramVarArgs = Boolean.valueOf(true);
        break;
        label233:
        Log.d(SamsungIapHelper.TAG, this.mErrorVo.getErrorString());
      }
    }
    
    protected void onPostExecute(Boolean paramBoolean)
    {
      if (true == paramBoolean.booleanValue()) {
        if (this.mErrorVo.getErrorCode() == 0) {
          if (this.mActivity != null) {
            this.mActivity.finish();
          }
        }
      }
      for (;;)
      {
        return;
        SamsungIapHelper.this.showIapDialogIfNeeded(this.mActivity, this.mActivity.getString(R.string.IDS_SAPPS_POP_SAMSUNG_IN_APP_PURCHASE), this.mErrorVo.getErrorString(), true, null, this.mShowErrorDialog);
        continue;
        SamsungIapHelper.this.showIapDialogIfNeeded(this.mActivity, this.mActivity.getString(R.string.IDS_SAPPS_POP_SAMSUNG_IN_APP_PURCHASE), this.mActivity.getString(R.string.IDS_SAPPS_POP_UNKNOWN_ERROR_OCCURRED) + "[Lib_InboxList]", true, null, this.mShowErrorDialog);
      }
    }
  }
  
  private class GetItemListTask
    extends AsyncTask<String, Object, Boolean>
  {
    private BaseActivity mActivity = null;
    private int mEndNum = 15;
    ErrorVo mErrorVo = new ErrorVo();
    ArrayList<ItemVo> mItemList = new ArrayList();
    private String mItemType = "";
    private boolean mShowErrorDialog = true;
    private int mStartNum = 1;
    
    public GetItemListTask(BaseActivity paramBaseActivity, int paramInt1, int paramInt2, String paramString, boolean paramBoolean)
    {
      this.mActivity = paramBaseActivity;
      this.mStartNum = paramInt1;
      this.mEndNum = paramInt2;
      this.mItemType = paramString;
      this.mShowErrorDialog = paramBoolean;
      this.mActivity.setErrorVo(this.mErrorVo);
      this.mActivity.setItemList(this.mItemList);
    }
    
    protected Boolean doInBackground(String... paramVarArgs)
    {
      for (;;)
      {
        try
        {
          paramVarArgs = SamsungIapHelper.this.mIapConnector.getItemList(SamsungIapHelper.this.mMode, this.mActivity.getPackageName(), null, this.mStartNum, this.mEndNum, this.mItemType);
          if (paramVarArgs != null)
          {
            this.mErrorVo.setError(paramVarArgs.getInt("STATUS_CODE"), paramVarArgs.getString("ERROR_STRING"));
            this.mErrorVo.setExtraString(paramVarArgs.getString("IAP_UPGRADE_URL"));
            if (this.mErrorVo.getErrorCode() != 0) {
              break label214;
            }
            paramVarArgs = paramVarArgs.getStringArrayList("RESULT_LIST");
            if (paramVarArgs == null) {
              break;
            }
            Iterator localIterator = paramVarArgs.iterator();
            if (!localIterator.hasNext()) {
              break label206;
            }
            String str = (String)localIterator.next();
            paramVarArgs = new com/samsung/android/sdk/iap/lib/vo/ItemVo;
            paramVarArgs.<init>(str);
            this.mItemList.add(paramVarArgs);
            continue;
            return paramVarArgs;
          }
        }
        catch (Exception paramVarArgs)
        {
          this.mErrorVo.setError(64534, this.mActivity.getString(R.string.IDS_SAPPS_POP_UNKNOWN_ERROR_OCCURRED));
          paramVarArgs.printStackTrace();
          paramVarArgs = Boolean.valueOf(false);
        }
        this.mErrorVo.setError(64534, this.mActivity.getString(R.string.IDS_SAPPS_POP_UNKNOWN_ERROR_OCCURRED));
      }
      Log.d(SamsungIapHelper.TAG, "Bundle Value 'RESULT_LIST' is null.");
      for (;;)
      {
        label206:
        paramVarArgs = Boolean.valueOf(true);
        break;
        label214:
        Log.d(SamsungIapHelper.TAG, this.mErrorVo.getErrorString());
      }
    }
    
    protected void onPostExecute(Boolean paramBoolean)
    {
      if (true == paramBoolean.booleanValue()) {
        if (this.mErrorVo.getErrorCode() == 0) {
          if (this.mActivity != null) {
            this.mActivity.finish();
          }
        }
      }
      for (;;)
      {
        return;
        if (this.mErrorVo.getErrorCode() == 64535)
        {
          paramBoolean = new Runnable()
          {
            public void run()
            {
              if (true == TextUtils.isEmpty(SamsungIapHelper.GetItemListTask.this.mErrorVo.getExtraString())) {}
              for (;;)
              {
                return;
                Intent localIntent = new Intent("android.intent.action.VIEW");
                localIntent.setData(Uri.parse(SamsungIapHelper.GetItemListTask.this.mErrorVo.getExtraString()));
                localIntent.addFlags(268435456);
                try
                {
                  SamsungIapHelper.GetItemListTask.this.mActivity.startActivity(localIntent);
                }
                catch (ActivityNotFoundException localActivityNotFoundException)
                {
                  localActivityNotFoundException.printStackTrace();
                }
              }
            }
          };
          SamsungIapHelper.this.showIapDialogIfNeeded(this.mActivity, this.mActivity.getString(R.string.IDS_SAPPS_POP_SAMSUNG_IN_APP_PURCHASE), this.mErrorVo.getErrorString(), true, paramBoolean, true);
          Log.e(SamsungIapHelper.TAG, this.mErrorVo.getErrorString());
        }
        else
        {
          SamsungIapHelper.this.showIapDialogIfNeeded(this.mActivity, this.mActivity.getString(R.string.IDS_SAPPS_POP_SAMSUNG_IN_APP_PURCHASE), this.mErrorVo.getErrorString(), true, null, this.mShowErrorDialog);
          Log.e(SamsungIapHelper.TAG, this.mErrorVo.getErrorString());
          continue;
          SamsungIapHelper.this.showIapDialogIfNeeded(this.mActivity, this.mActivity.getString(R.string.IDS_SAPPS_POP_SAMSUNG_IN_APP_PURCHASE), this.mActivity.getString(R.string.IDS_SAPPS_POP_UNKNOWN_ERROR_OCCURRED) + "[Lib_ItemList]", true, null, this.mShowErrorDialog);
        }
      }
    }
  }
  
  private class InitIapTask
    extends AsyncTask<String, Object, Boolean>
  {
    private BaseActivity mActivity = null;
    private ErrorVo mErrorVo = new ErrorVo();
    private boolean mShowErrorDialog = true;
    
    public InitIapTask(BaseActivity paramBaseActivity, boolean paramBoolean)
    {
      this.mActivity = paramBaseActivity;
      this.mShowErrorDialog = paramBoolean;
      this.mActivity.setErrorVo(this.mErrorVo);
    }
    
    protected Boolean doInBackground(String... paramVarArgs)
    {
      for (;;)
      {
        try
        {
          if (SamsungIapHelper.this.mState != 2) {
            continue;
          }
          this.mErrorVo.setError(0, "");
          paramVarArgs = Boolean.valueOf(true);
        }
        catch (Exception paramVarArgs)
        {
          this.mErrorVo.setError(64536, this.mActivity.getString(R.string.IDS_SAPPS_POP_UNKNOWN_ERROR_OCCURRED));
          paramVarArgs.printStackTrace();
          paramVarArgs = Boolean.valueOf(false);
          continue;
        }
        return paramVarArgs;
        Log.i(SamsungIapHelper.TAG, "start Init... ");
        SamsungIapHelper.this.init(this.mErrorVo);
        Log.i(SamsungIapHelper.TAG, "end Init... ");
      }
    }
    
    protected void onPostExecute(Boolean paramBoolean)
    {
      if (true == paramBoolean.booleanValue()) {
        if (this.mErrorVo.getErrorCode() == 0) {
          if (SamsungIapHelper.this.mOnInitIapListener != null)
          {
            SamsungIapHelper.access$102(SamsungIapHelper.this, 2);
            SamsungIapHelper.this.mOnInitIapListener.onSucceedInitIap();
          }
        }
      }
      for (;;)
      {
        return;
        if (this.mErrorVo.getErrorCode() == 64535)
        {
          paramBoolean = new Runnable()
          {
            public void run()
            {
              if (true == TextUtils.isEmpty(SamsungIapHelper.InitIapTask.this.mErrorVo.getExtraString())) {}
              for (;;)
              {
                return;
                Intent localIntent = new Intent("android.intent.action.VIEW");
                localIntent.setData(Uri.parse(SamsungIapHelper.InitIapTask.this.mErrorVo.getExtraString()));
                localIntent.addFlags(268435456);
                try
                {
                  SamsungIapHelper.InitIapTask.this.mActivity.startActivity(localIntent);
                }
                catch (ActivityNotFoundException localActivityNotFoundException)
                {
                  localActivityNotFoundException.printStackTrace();
                }
              }
            }
          };
          SamsungIapHelper.this.showIapDialogIfNeeded(this.mActivity, this.mActivity.getString(R.string.IDS_SAPPS_POP_SAMSUNG_IN_APP_PURCHASE), this.mErrorVo.getErrorString(), true, paramBoolean, true);
        }
        else
        {
          SamsungIapHelper.this.showIapDialogIfNeeded(this.mActivity, this.mActivity.getString(R.string.IDS_SAPPS_POP_SAMSUNG_IN_APP_PURCHASE), this.mErrorVo.getErrorString(), true, null, this.mShowErrorDialog);
          continue;
          SamsungIapHelper.this.showIapDialogIfNeeded(this.mActivity, this.mActivity.getString(R.string.IDS_SAPPS_POP_SAMSUNG_IN_APP_PURCHASE), this.mActivity.getString(R.string.IDS_SAPPS_POP_UNKNOWN_ERROR_OCCURRED) + "[Lib_Init]", true, null, this.mShowErrorDialog);
        }
      }
    }
  }
  
  private class VerifyClientToServer
    extends AsyncTask<Void, Void, Boolean>
  {
    private BaseActivity mActivity = null;
    private ErrorVo mErrorVo = null;
    private PurchaseVo mPurchaseVO = null;
    private boolean mShowErrorDialog = true;
    private boolean mShowSuccessDialog = true;
    private VerificationVo mVerificationVO = null;
    
    public VerifyClientToServer(BaseActivity paramBaseActivity, PurchaseVo paramPurchaseVo, boolean paramBoolean1, boolean paramBoolean2)
    {
      this.mActivity = paramBaseActivity;
      this.mPurchaseVO = paramPurchaseVo;
      this.mShowSuccessDialog = paramBoolean1;
      this.mShowErrorDialog = paramBoolean2;
      this.mErrorVo = new ErrorVo();
      this.mActivity.setErrorVo(this.mErrorVo);
      this.mActivity.setPurchaseVo(this.mPurchaseVO);
    }
    
    /* Error */
    private String getHttpGetData(String paramString, int paramInt1, int paramInt2)
    {
      // Byte code:
      //   0: aconst_null
      //   1: astore 14
      //   3: aconst_null
      //   4: astore 8
      //   6: aconst_null
      //   7: astore 7
      //   9: aconst_null
      //   10: astore 12
      //   12: aconst_null
      //   13: astore 11
      //   15: aconst_null
      //   16: astore 6
      //   18: aconst_null
      //   19: astore 13
      //   21: aconst_null
      //   22: astore 10
      //   24: aconst_null
      //   25: astore 9
      //   27: aload 12
      //   29: astore 4
      //   31: aload 13
      //   33: astore 5
      //   35: new 62	java/net/URL
      //   38: astore 15
      //   40: aload 12
      //   42: astore 4
      //   44: aload 13
      //   46: astore 5
      //   48: aload 15
      //   50: aload_1
      //   51: invokespecial 65	java/net/URL:<init>	(Ljava/lang/String;)V
      //   54: aload 12
      //   56: astore 4
      //   58: aload 13
      //   60: astore 5
      //   62: aload 15
      //   64: invokevirtual 69	java/net/URL:openConnection	()Ljava/net/URLConnection;
      //   67: astore_1
      //   68: aload 12
      //   70: astore 4
      //   72: aload 13
      //   74: astore 5
      //   76: aload_1
      //   77: sipush 10000
      //   80: invokevirtual 75	java/net/URLConnection:setConnectTimeout	(I)V
      //   83: aload 12
      //   85: astore 4
      //   87: aload 13
      //   89: astore 5
      //   91: aload_1
      //   92: sipush 10000
      //   95: invokevirtual 78	java/net/URLConnection:setReadTimeout	(I)V
      //   98: aload 12
      //   100: astore 4
      //   102: aload 13
      //   104: astore 5
      //   106: aload_1
      //   107: checkcast 80	java/net/HttpURLConnection
      //   110: astore 15
      //   112: aload 12
      //   114: astore 4
      //   116: aload 13
      //   118: astore 5
      //   120: aload 15
      //   122: ldc 82
      //   124: invokevirtual 85	java/net/HttpURLConnection:setRequestMethod	(Ljava/lang/String;)V
      //   127: aload 12
      //   129: astore 4
      //   131: aload 13
      //   133: astore 5
      //   135: aload 15
      //   137: invokevirtual 88	java/net/HttpURLConnection:connect	()V
      //   140: aload 14
      //   142: astore_1
      //   143: aload 12
      //   145: astore 4
      //   147: aload 13
      //   149: astore 5
      //   151: aload 15
      //   153: invokevirtual 92	java/net/HttpURLConnection:getResponseCode	()I
      //   156: sipush 200
      //   159: if_icmpne +146 -> 305
      //   162: aload 12
      //   164: astore 4
      //   166: aload 13
      //   168: astore 5
      //   170: new 94	java/io/BufferedInputStream
      //   173: astore_1
      //   174: aload 12
      //   176: astore 4
      //   178: aload 13
      //   180: astore 5
      //   182: aload_1
      //   183: aload 15
      //   185: invokevirtual 98	java/net/HttpURLConnection:getInputStream	()Ljava/io/InputStream;
      //   188: sipush 4096
      //   191: invokespecial 101	java/io/BufferedInputStream:<init>	(Ljava/io/InputStream;I)V
      //   194: new 103	java/io/ByteArrayOutputStream
      //   197: astore 4
      //   199: aload 4
      //   201: sipush 4096
      //   204: invokespecial 105	java/io/ByteArrayOutputStream:<init>	(I)V
      //   207: sipush 4096
      //   210: newarray <illegal type>
      //   212: astore 5
      //   214: aload_1
      //   215: aload 5
      //   217: iconst_0
      //   218: sipush 4096
      //   221: invokevirtual 109	java/io/BufferedInputStream:read	([BII)I
      //   224: istore_2
      //   225: iload_2
      //   226: iconst_m1
      //   227: if_icmpeq +56 -> 283
      //   230: aload 4
      //   232: aload 5
      //   234: iconst_0
      //   235: iload_2
      //   236: invokevirtual 113	java/io/ByteArrayOutputStream:write	([BII)V
      //   239: goto -25 -> 214
      //   242: astore 7
      //   244: aload 4
      //   246: astore 6
      //   248: aload_1
      //   249: astore 4
      //   251: aload 6
      //   253: astore 5
      //   255: aload 7
      //   257: invokevirtual 116	java/lang/Exception:printStackTrace	()V
      //   260: aload_1
      //   261: ifnull +7 -> 268
      //   264: aload_1
      //   265: invokevirtual 119	java/io/BufferedInputStream:close	()V
      //   268: aload 6
      //   270: ifnull +8 -> 278
      //   273: aload 6
      //   275: invokevirtual 120	java/io/ByteArrayOutputStream:close	()V
      //   278: aload 8
      //   280: astore_1
      //   281: aload_1
      //   282: areturn
      //   283: aload 4
      //   285: invokevirtual 123	java/io/ByteArrayOutputStream:flush	()V
      //   288: aload 4
      //   290: invokevirtual 127	java/io/ByteArrayOutputStream:toString	()Ljava/lang/String;
      //   293: astore 5
      //   295: aload_1
      //   296: astore 7
      //   298: aload 5
      //   300: astore_1
      //   301: aload 4
      //   303: astore 6
      //   305: aload 7
      //   307: ifnull +8 -> 315
      //   310: aload 7
      //   312: invokevirtual 119	java/io/BufferedInputStream:close	()V
      //   315: aload 6
      //   317: ifnull +8 -> 325
      //   320: aload 6
      //   322: invokevirtual 120	java/io/ByteArrayOutputStream:close	()V
      //   325: goto -44 -> 281
      //   328: astore_1
      //   329: aload 4
      //   331: ifnull +8 -> 339
      //   334: aload 4
      //   336: invokevirtual 119	java/io/BufferedInputStream:close	()V
      //   339: aload 5
      //   341: ifnull +8 -> 349
      //   344: aload 5
      //   346: invokevirtual 120	java/io/ByteArrayOutputStream:close	()V
      //   349: aload_1
      //   350: athrow
      //   351: astore 4
      //   353: goto -38 -> 315
      //   356: astore 4
      //   358: goto -33 -> 325
      //   361: astore_1
      //   362: goto -94 -> 268
      //   365: astore_1
      //   366: goto -88 -> 278
      //   369: astore 4
      //   371: goto -32 -> 339
      //   374: astore 4
      //   376: goto -27 -> 349
      //   379: astore 6
      //   381: aload_1
      //   382: astore 4
      //   384: aload 10
      //   386: astore 5
      //   388: aload 6
      //   390: astore_1
      //   391: goto -62 -> 329
      //   394: astore 6
      //   396: aload 4
      //   398: astore 5
      //   400: aload_1
      //   401: astore 4
      //   403: aload 6
      //   405: astore_1
      //   406: goto -77 -> 329
      //   409: astore 7
      //   411: aload 11
      //   413: astore_1
      //   414: aload 9
      //   416: astore 6
      //   418: goto -170 -> 248
      //   421: astore 7
      //   423: aload 9
      //   425: astore 6
      //   427: goto -179 -> 248
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	430	0	this	VerifyClientToServer
      //   0	430	1	paramString	String
      //   0	430	2	paramInt1	int
      //   0	430	3	paramInt2	int
      //   29	306	4	localObject1	Object
      //   351	1	4	localException1	Exception
      //   356	1	4	localIOException1	java.io.IOException
      //   369	1	4	localException2	Exception
      //   374	1	4	localIOException2	java.io.IOException
      //   382	20	4	str1	String
      //   33	366	5	localObject2	Object
      //   16	305	6	localObject3	Object
      //   379	10	6	localObject4	Object
      //   394	10	6	localObject5	Object
      //   416	10	6	localObject6	Object
      //   7	1	7	localObject7	Object
      //   242	14	7	localException3	Exception
      //   296	15	7	str2	String
      //   409	1	7	localException4	Exception
      //   421	1	7	localException5	Exception
      //   4	275	8	localObject8	Object
      //   25	399	9	localObject9	Object
      //   22	363	10	localObject10	Object
      //   13	399	11	localObject11	Object
      //   10	165	12	localObject12	Object
      //   19	160	13	localObject13	Object
      //   1	140	14	localObject14	Object
      //   38	146	15	localObject15	Object
      // Exception table:
      //   from	to	target	type
      //   207	214	242	java/lang/Exception
      //   214	225	242	java/lang/Exception
      //   230	239	242	java/lang/Exception
      //   283	295	242	java/lang/Exception
      //   35	40	328	finally
      //   48	54	328	finally
      //   62	68	328	finally
      //   76	83	328	finally
      //   91	98	328	finally
      //   106	112	328	finally
      //   120	127	328	finally
      //   135	140	328	finally
      //   151	162	328	finally
      //   170	174	328	finally
      //   182	194	328	finally
      //   255	260	328	finally
      //   310	315	351	java/lang/Exception
      //   320	325	356	java/io/IOException
      //   264	268	361	java/lang/Exception
      //   273	278	365	java/io/IOException
      //   334	339	369	java/lang/Exception
      //   344	349	374	java/io/IOException
      //   194	207	379	finally
      //   207	214	394	finally
      //   214	225	394	finally
      //   230	239	394	finally
      //   283	295	394	finally
      //   35	40	409	java/lang/Exception
      //   48	54	409	java/lang/Exception
      //   62	68	409	java/lang/Exception
      //   76	83	409	java/lang/Exception
      //   91	98	409	java/lang/Exception
      //   106	112	409	java/lang/Exception
      //   120	127	409	java/lang/Exception
      //   135	140	409	java/lang/Exception
      //   151	162	409	java/lang/Exception
      //   170	174	409	java/lang/Exception
      //   182	194	409	java/lang/Exception
      //   194	207	421	java/lang/Exception
    }
    
    protected Boolean doInBackground(Void... paramVarArgs)
    {
      if ((this.mPurchaseVO == null) || (this.mActivity == null)) {
        paramVarArgs = Boolean.valueOf(false);
      }
      for (;;)
      {
        return paramVarArgs;
        try
        {
          Object localObject = new java/lang/StringBuffer;
          ((StringBuffer)localObject).<init>();
          ((StringBuffer)localObject).append("https://iap.samsungapps.com/iap/appsItemVerifyIAPReceipt.as?protocolVersion=2.0");
          paramVarArgs = new java/lang/StringBuilder;
          paramVarArgs.<init>();
          ((StringBuffer)localObject).append("&purchaseID=" + this.mPurchaseVO.getPurchaseId());
          int i = 0;
          do
          {
            int j;
            do
            {
              paramVarArgs = getHttpGetData(((StringBuffer)localObject).toString(), 10000, 10000);
              j = i + 1;
              if (j >= 3) {
                break;
              }
              i = j;
            } while (paramVarArgs == null);
            i = j;
          } while (true == TextUtils.isEmpty(paramVarArgs));
          if ((paramVarArgs != null) && (true != TextUtils.isEmpty(paramVarArgs)))
          {
            localObject = new com/samsung/android/sdk/iap/lib/vo/VerificationVo;
            ((VerificationVo)localObject).<init>(paramVarArgs);
            this.mVerificationVO = ((VerificationVo)localObject);
            if (!"true".equals(this.mVerificationVO.getStatus()))
            {
              paramVarArgs = Boolean.valueOf(false);
              continue;
            }
          }
          paramVarArgs = Boolean.valueOf(true);
        }
        catch (Exception paramVarArgs)
        {
          paramVarArgs.printStackTrace();
          paramVarArgs = Boolean.valueOf(true);
        }
      }
    }
    
    protected void onPostExecute(Boolean paramBoolean)
    {
      if (paramBoolean.booleanValue() == true)
      {
        this.mErrorVo.setError(0, this.mActivity.getString(R.string.dlg_msg_payment_success));
        SamsungIapHelper.this.showIapDialogIfNeeded(this.mActivity, this.mActivity.getString(R.string.IDS_SAPPS_POP_SAMSUNG_IN_APP_PURCHASE), this.mErrorVo.getErrorString(), true, null, this.mShowSuccessDialog);
      }
      for (;;)
      {
        return;
        this.mErrorVo.setError(64534, this.mActivity.getString(R.string.IDS_SAPPS_POP_YOUR_PURCHASE_VIA_SAMSUNG_IN_APP_PURCHASE_IS_INVALID_A_FAKE_APPLICATION_HAS_BEEN_DETECTED_CHECK_THE_APP_MSG));
        SamsungIapHelper.this.showIapDialogIfNeeded(this.mActivity, this.mActivity.getString(R.string.IDS_SAPPS_POP_SAMSUNG_IN_APP_PURCHASE), this.mErrorVo.getErrorString(), true, null, this.mShowErrorDialog);
      }
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\samsung\android\sdk\iap\lib\helper\SamsungIapHelper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */