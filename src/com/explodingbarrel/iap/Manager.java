package com.explodingbarrel.iap;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.util.Log;
import com.explodingbarrel.iap.util.IabBroadcastReceiver;
import com.explodingbarrel.iap.util.IabBroadcastReceiver.IabBroadcastListener;
import com.explodingbarrel.iap.util.IabHelper.OnConsumeFinishedListener;
import com.explodingbarrel.iap.util.IabHelper.OnIabPurchaseFinishedListener;
import com.explodingbarrel.iap.util.IabHelper.OnIabSetupFinishedListener;
import com.explodingbarrel.iap.util.IabHelper.QueryInventoryFinishedListener;
import com.explodingbarrel.iap.util.IabResult;
import com.explodingbarrel.iap.util.Inventory;
import com.explodingbarrel.iap.util.Purchase;
import com.explodingbarrel.iap.util.SkuDetails;
import com.unity3d.player.UnityPlayer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Manager
  implements IabBroadcastReceiver.IabBroadcastListener
{
  private static final int RC_REQUEST = 1;
  private static final String TAG = "IapManager";
  private static final String UNITY_PLUGIN_NAME = "iap_plugin_android";
  public static Manager _this;
  boolean _supported = false;
  IabHelper.OnConsumeFinishedListener mConsumeFinishedListener = new IabHelper.OnConsumeFinishedListener()
  {
    public void onConsumeFinished(Purchase paramAnonymousPurchase, IabResult paramAnonymousIabResult)
    {
      Log.d("IapManager", "onConsumeFinished(" + paramAnonymousIabResult + ")");
      Manager.SendMessage("OnItemPurchaseComplete", "");
    }
  };
  IabHelper.QueryInventoryFinishedListener mGotInventoryListener = new IabHelper.QueryInventoryFinishedListener()
  {
    public void onQueryInventoryFinished(IabResult paramAnonymousIabResult, Inventory paramAnonymousInventory)
    {
      if (paramAnonymousIabResult.isFailure())
      {
        Manager.SendMessage("OnIAPUnsupported", "");
        Log.e("IapManager", "Query inventory failed. " + paramAnonymousIabResult);
        return;
      }
      Log.d("IapManager", "Query inventory finished: " + paramAnonymousIabResult.toString());
      Object localObject = paramAnonymousInventory.getAllPurchases().iterator();
      while (((Iterator)localObject).hasNext())
      {
        paramAnonymousIabResult = (Purchase)((Iterator)localObject).next();
        Manager.SendMessage("OnItemPurchased", paramAnonymousIabResult.getOriginalJson() + "|" + paramAnonymousIabResult.getSignature());
      }
      paramAnonymousIabResult = paramAnonymousInventory.getAllSkus();
      JSONArray localJSONArray;
      if (paramAnonymousIabResult.size() > 0) {
        try
        {
          localJSONArray = new org/json/JSONArray;
          localJSONArray.<init>();
          paramAnonymousInventory = paramAnonymousIabResult.iterator();
          while (paramAnonymousInventory.hasNext())
          {
            localObject = (SkuDetails)paramAnonymousInventory.next();
            JSONObject localJSONObject = new org/json/JSONObject;
            localJSONObject.<init>(((SkuDetails)localObject).getJson());
            localJSONArray.put(localJSONObject);
            continue;
            Log.d("IapManager", "Query inventory was successful. " + paramAnonymousIabResult.size());
          }
        }
        catch (JSONException paramAnonymousInventory)
        {
          Manager.SendMessage("OnIAPSuppored", "");
        }
      }
      for (;;)
      {
        break;
        Manager.SendMessage("OnIAPSuppored", localJSONArray.toString());
        continue;
        Manager.SendMessage("OnIAPSuppored", "");
      }
    }
  };
  FuseIABHelper mHelper = null;
  IabBroadcastReceiver mIabBroadcastReceiver = null;
  IabHelper.OnIabSetupFinishedListener mIabSetupFinishedListener = new IabHelper.OnIabSetupFinishedListener()
  {
    public void onIabSetupFinished(IabResult paramAnonymousIabResult)
    {
      Log.d("IapManager", "onIabSetupFinished(" + paramAnonymousIabResult + ")");
      if (!paramAnonymousIabResult.isSuccess())
      {
        Manager._this._supported = false;
        Log.d("IapManager", "not supported");
        Manager.SendMessage("OnInitComplete", "false");
      }
      for (;;)
      {
        return;
        Manager._this._supported = true;
        Manager.SendMessage("OnInitComplete", "true");
      }
    }
  };
  Activity mPurchaseActivity = null;
  IabHelper.OnIabPurchaseFinishedListener mPurchaseFinishedListener = new IabHelper.OnIabPurchaseFinishedListener()
  {
    public void onIabPurchaseFinished(IabResult paramAnonymousIabResult, Purchase paramAnonymousPurchase)
    {
      Log.d("IapManager", "Purchase finished: " + paramAnonymousIabResult + ", purchase: " + paramAnonymousPurchase);
      if (paramAnonymousIabResult.isFailure()) {
        if ((paramAnonymousIabResult.getResponse() == 1) || (paramAnonymousIabResult.getResponse() == 64531)) {
          Manager.SendMessage("OnItemPurchaseCanceled", paramAnonymousIabResult.getMessage());
        }
      }
      for (;;)
      {
        if (Manager._this.mPurchaseActivity != null)
        {
          Manager._this.mPurchaseActivity.finish();
          Manager._this.mPurchaseActivity = null;
        }
        return;
        if (paramAnonymousIabResult.getResponse() == 7)
        {
          Manager._this.mHelper.queryInventoryAsync(Manager._this.mGotInventoryListener);
        }
        else
        {
          Manager.SendMessage("OnItemPurchaseFailed", paramAnonymousIabResult.getMessage());
          continue;
          Manager.SendMessage("OnItemPurchased", paramAnonymousPurchase.getOriginalJson() + "|" + paramAnonymousPurchase.getSignature());
        }
      }
    }
  };
  
  public static void CheckPromoEligible()
  {
    if (!_this._supported) {
      Log.d("IapManager", "IABHelper is not currently supported");
    }
    for (;;)
    {
      return;
      String str = UnityPlayer.currentActivity.getPackageName();
      UnityPlayer.currentActivity.runOnUiThread(new Runnable()
      {
        public void run()
        {
          Manager._this._CheckPromoEligible(this.val$packageName);
        }
      });
    }
  }
  
  public static void CompletePurchase(String paramString1, String paramString2)
  {
    Log.d("IapManager", "CompletePurchase " + paramString1);
    if (_this != null) {
      _this._CompletePurchase(paramString1, paramString2);
    }
  }
  
  public static void Enumerate(String paramString)
  {
    if (_this != null) {
      _this._Enumerate(paramString);
    }
  }
  
  public static boolean HandleActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    Log.d("IapManager", "onActivityResult(" + paramInt1 + "," + paramInt2 + "," + paramIntent);
    if (_this != null) {}
    for (boolean bool = _this.mHelper.handleActivityResult(paramInt1, paramInt2, paramIntent);; bool = false) {
      return bool;
    }
  }
  
  public static void Init(String paramString, boolean paramBoolean)
  {
    if (_this == null) {
      _this = new Manager();
    }
    _this._Init(paramString, paramBoolean);
  }
  
  public static void LaunchPurchaseFlow(Activity paramActivity, String paramString1, String paramString2, String paramString3)
  {
    if (_this != null) {}
    try
    {
      _this.mPurchaseActivity = paramActivity;
      _this.mHelper.launchPurchaseFlow(paramActivity, paramString1, 1, _this.mPurchaseFinishedListener, paramString3);
      return;
    }
    catch (IllegalStateException paramActivity)
    {
      for (;;)
      {
        SendMessage("OnItemPurchaseCanceled", "");
      }
    }
  }
  
  public static void PurchaseItem(String paramString1, String paramString2, String paramString3)
  {
    if (_this != null)
    {
      Intent localIntent = new Intent(UnityPlayer.currentActivity, PurchaseActivity.class);
      localIntent.putExtra("product", paramString1);
      localIntent.putExtra("type", paramString2);
      localIntent.putExtra("type", paramString3);
      UnityPlayer.currentActivity.startActivity(localIntent);
    }
  }
  
  private static void SendMessage(String paramString1, String paramString2)
  {
    UnityPlayer.UnitySendMessage("iap_plugin_android", paramString1, paramString2);
  }
  
  private void _CheckPromoEligible(String paramString)
  {
    Log.d("IapManager", "Checking for promo eligibility: " + paramString);
    new PromoCheck(paramString, this.mHelper).execute(new Void[] { null, null, null });
  }
  
  private void _CompletePurchase(String paramString1, String paramString2)
  {
    try
    {
      Purchase localPurchase = new com/explodingbarrel/iap/util/Purchase;
      localPurchase.<init>("inapp", paramString1, paramString2);
      paramString2 = UnityPlayer.currentActivity;
      paramString1 = new com/explodingbarrel/iap/Manager$2;
      paramString1.<init>(this, localPurchase);
      paramString2.runOnUiThread(paramString1);
      return;
    }
    catch (JSONException paramString1)
    {
      for (;;) {}
    }
  }
  
  private void _Enumerate(final String paramString)
  {
    Log.d("IapManager", "Enumerating " + paramString);
    if (!this._supported)
    {
      SendMessage("OnIAPUnsupported", "");
      Log.d("IapManager", "Enumerating failed. " + this._supported);
    }
    for (;;)
    {
      return;
      if (this.mIabBroadcastReceiver == null)
      {
        this.mIabBroadcastReceiver = new IabBroadcastReceiver(this);
        IntentFilter localIntentFilter = new IntentFilter("com.android.vending.billing.PURCHASES_UPDATED");
        UnityPlayer.currentActivity.registerReceiver(this.mIabBroadcastReceiver, localIntentFilter);
      }
      UnityPlayer.currentActivity.runOnUiThread(new Runnable()
      {
        public void run()
        {
          Log.d("IapManager", "Enumerated " + paramString);
          String[] arrayOfString = paramString.split(",");
          ArrayList localArrayList = new ArrayList();
          for (int i = 0; i < arrayOfString.length; i++) {
            localArrayList.add(arrayOfString[i]);
          }
          Manager.this.mHelper.queryInventoryAsync(true, localArrayList, Manager.this.mGotInventoryListener);
        }
      });
    }
  }
  
  private void _Init(String paramString, boolean paramBoolean)
  {
    Log.d("IapManager", "intializing");
    try
    {
      FuseIABHelper localFuseIABHelper = new com/explodingbarrel/iap/FuseIABHelper;
      localFuseIABHelper.<init>(UnityPlayer.currentActivity, paramString);
      this.mHelper = localFuseIABHelper;
      this.mHelper.enableDebugLogging(paramBoolean);
      this.mHelper.startSetup(this.mIabSetupFinishedListener);
      return;
    }
    catch (Exception paramString)
    {
      for (;;)
      {
        _this._supported = false;
        Log.d("IapManager", "failed in init: " + paramString);
        SendMessage("OnInitComplete", "");
      }
    }
  }
  
  public void receivedIABBroadcast()
  {
    Log.d("IapManager", "receivedIABBroadcast");
    SendMessage("OnPurchasesUpdated", "");
  }
  
  private static class PromoCheck
    extends AsyncTask<Void, Void, Integer>
  {
    FuseIABHelper mHelper;
    String mPackageName;
    
    public PromoCheck(String paramString, FuseIABHelper paramFuseIABHelper)
    {
      this.mPackageName = paramString;
      this.mHelper = paramFuseIABHelper;
    }
    
    protected Integer doInBackground(Void... paramVarArgs)
    {
      return Integer.valueOf(this.mHelper.isPromoEligible(this.mPackageName));
    }
    
    protected void onPostExecute(Integer paramInteger)
    {
      Log.d("IapManager", "promo check result " + paramInteger.toString());
      switch (Integer.valueOf(paramInteger.intValue()).intValue())
      {
      default: 
        Log.d("IapManager", "Error checking promo eligibility " + paramInteger.toString());
        Manager.SendMessage("OnCheckPromoComplete", "error");
      }
      for (;;)
      {
        return;
        Manager.SendMessage("OnCheckPromoComplete", "true");
        continue;
        Manager.SendMessage("OnCheckPromoComplete", "false");
      }
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\explodingbarrel\iap\Manager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */