package com.kabam.android.iap.samsung;

import android.app.Activity;
import android.os.Handler;
import android.util.Base64;
import android.util.Log;
import com.samsung.android.sdk.iap.lib.helper.SamsungIapHelper;
import com.samsung.android.sdk.iap.lib.listener.OnGetItemListener;
import com.samsung.android.sdk.iap.lib.listener.OnPaymentListener;
import com.samsung.android.sdk.iap.lib.vo.ErrorVo;
import com.samsung.android.sdk.iap.lib.vo.ItemVo;
import com.samsung.android.sdk.iap.lib.vo.PurchaseVo;
import com.unity3d.player.UnityPlayer;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

public class SamsungIAPManager
{
  private static final String TAG = "SamsungIAPManager";
  private static final String UNITY_PLUGIN_NAME = "iap_plugin_samsung";
  public static SamsungIAPManager _this;
  private int _EnumerateStart = 1;
  private int _EnumerateWindow = 20;
  private JSONArray _EnumeratedItems = new JSONArray();
  private SamsungIapHelper _IAPHelper = null;
  private int _IAPMode = -1;
  OnGetItemListener _OnGetItemListListener = new OnGetItemListener()
  {
    public void onGetItem(ErrorVo paramAnonymousErrorVo, ArrayList<ItemVo> paramAnonymousArrayList)
    {
      if (paramAnonymousErrorVo != null) {}
      for (;;)
      {
        try
        {
          switch (paramAnonymousErrorVo.getErrorCode())
          {
          default: 
            paramAnonymousArrayList = new java/lang/StringBuilder;
            paramAnonymousArrayList.<init>();
            Log.d("SamsungIAPManager", "Enumerating failed. Error: " + paramAnonymousErrorVo);
            SamsungIAPManager.SendMessage("OnIAPUnsupported", "");
            return;
          }
        }
        catch (Exception paramAnonymousErrorVo)
        {
          int j;
          int k;
          int i;
          ItemVo localItemVo;
          JSONObject localJSONObject;
          Log.d("SamsungIAPManager", "_OnGetItemListListener Exception: " + paramAnonymousErrorVo.toString());
          SamsungIAPManager.SendMessage("OnIAPUnsupported", "");
          continue;
          SamsungIAPManager.access$302(SamsungIAPManager.this, SamsungIAPManager.this._EnumerateStart + SamsungIAPManager.this._EnumerateWindow);
          paramAnonymousArrayList = UnityPlayer.currentActivity;
          paramAnonymousErrorVo = new com/kabam/android/iap/samsung/SamsungIAPManager$1$1;
          paramAnonymousErrorVo.<init>(this);
          paramAnonymousArrayList.runOnUiThread(paramAnonymousErrorVo);
          continue;
        }
        j = 0;
        if (paramAnonymousArrayList != null)
        {
          k = paramAnonymousArrayList.size();
          i = 0;
          j = k;
          if (i < k)
          {
            localItemVo = (ItemVo)paramAnonymousArrayList.get(i);
            paramAnonymousErrorVo = SamsungIAPManager.this._EnumeratedItems;
            localJSONObject = new org/json/JSONObject;
            localJSONObject.<init>(localItemVo.getJsonString());
            paramAnonymousErrorVo.put(localJSONObject);
            i++;
            continue;
          }
        }
        paramAnonymousErrorVo = new java/lang/StringBuilder;
        paramAnonymousErrorVo.<init>();
        Log.d("SamsungIAPManager", "Enumerating onGetItem Count " + j);
        if (j >= SamsungIAPManager.this._EnumerateWindow) {
          continue;
        }
        paramAnonymousErrorVo = new java/lang/StringBuilder;
        paramAnonymousErrorVo.<init>();
        Log.d("SamsungIAPManager", "Enumerating Complete " + SamsungIAPManager.this._EnumeratedItems.length());
        SamsungIAPManager.SendMessage("OnIAPSuppored", SamsungIAPManager.this._EnumeratedItems.toString());
      }
    }
  };
  OnPaymentListener _OnPaymentListener = new OnPaymentListener()
  {
    public void onPayment(ErrorVo paramAnonymousErrorVo, PurchaseVo paramAnonymousPurchaseVo)
    {
      if (paramAnonymousErrorVo != null) {
        switch (paramAnonymousErrorVo.getErrorCode())
        {
        default: 
          Log.d("SamsungIAPManager", "PurchaseItem failed. Error: " + paramAnonymousErrorVo.getErrorString());
          SamsungIAPManager.SendMessage("OnItemPurchaseFailed", paramAnonymousErrorVo.getErrorString());
        }
      }
      for (;;)
      {
        return;
        Log.d("SamsungIAPManager", "PurchaseItem Success. Data: " + paramAnonymousPurchaseVo);
        try
        {
          paramAnonymousErrorVo = new org/json/JSONObject;
          paramAnonymousErrorVo.<init>(paramAnonymousPurchaseVo.getJsonString());
          paramAnonymousErrorVo.put("mIapMode", SamsungIAPManager.this._IAPMode);
          String str = Base64.encodeToString(paramAnonymousErrorVo.toString().getBytes("UTF-8"), 0);
          paramAnonymousPurchaseVo = new org/json/JSONObject;
          paramAnonymousPurchaseVo.<init>();
          paramAnonymousPurchaseVo.put("transactionId", paramAnonymousErrorVo.optString("mPurchaseId", ""));
          paramAnonymousPurchaseVo.put("productId", paramAnonymousErrorVo.optString("mItemId", ""));
          paramAnonymousPurchaseVo.put("payload", str);
          SamsungIAPManager.SendMessage("OnItemPurchased", paramAnonymousPurchaseVo.toString());
        }
        catch (Exception paramAnonymousErrorVo)
        {
          Log.d("SamsungIAPManager", "PurchaseItem Exception. Error: " + paramAnonymousErrorVo.toString());
          SamsungIAPManager.SendMessage("OnItemPurchaseFailed", paramAnonymousErrorVo.toString());
        }
      }
    }
  };
  private boolean _Supported = false;
  
  public static void Enumerate(String paramString, int paramInt)
  {
    if (_this != null) {
      _this._Enumerate(paramString, paramInt);
    }
  }
  
  public static void Init(String paramString)
  {
    if (_this == null) {
      _this = new SamsungIAPManager();
    }
    _this._Init(paramString);
  }
  
  public static void PurchaseItem(String paramString1, String paramString2, String paramString3, boolean paramBoolean)
  {
    if (_this != null) {
      _this._PurchaseItem(paramString1, paramString2, paramString3, paramBoolean);
    }
  }
  
  private static void SendMessage(String paramString1, String paramString2)
  {
    UnityPlayer.UnitySendMessage("iap_plugin_samsung", paramString1, paramString2);
  }
  
  private void _Enumerate(String paramString, int paramInt)
  {
    Log.d("SamsungIAPManager", "Enumerating Window " + paramInt + " SKUs " + paramString);
    if ((!this._Supported) || (this._IAPHelper == null)) {
      SendMessage("OnIAPUnsupported", "");
    }
    for (;;)
    {
      return;
      this._EnumerateWindow = paramInt;
      this._EnumerateStart = 1;
      this._EnumeratedItems = new JSONArray();
      paramInt = this._EnumerateStart + this._EnumerateWindow - 1;
      Log.d("SamsungIAPManager", "Enumerating GetMore getItemList " + this._EnumerateStart + " -> " + paramInt);
      this._IAPHelper.getItemList(this._EnumerateStart, paramInt, "10", this._IAPMode, this._OnGetItemListListener);
    }
  }
  
  private void _Init(String paramString)
  {
    Log.d("SamsungIAPManager", "Init: " + paramString);
    try
    {
      if (paramString.equals("production")) {}
      for (this._IAPMode = 0;; this._IAPMode = 1)
      {
        if (this._IAPMode < 0) {
          break label151;
        }
        this._IAPHelper = SamsungIapHelper.getInstance(UnityPlayer.currentActivity, this._IAPMode);
        this._Supported = true;
        return;
        if (!paramString.equals("sandbox-success")) {
          break;
        }
      }
    }
    catch (Exception paramString)
    {
      for (;;)
      {
        _this._Supported = false;
        Log.d("SamsungIAPManager", "Init Failed: " + paramString);
        SendMessage("OnInitComplete", "");
        continue;
        if (paramString.equals("sandbox-fail"))
        {
          this._IAPMode = -1;
        }
        else
        {
          this._IAPMode = -1;
          continue;
          label151:
          StringBuilder localStringBuilder = new java/lang/StringBuilder;
          localStringBuilder.<init>();
          Log.d("SamsungIAPManager", "Init Unknown mode: " + paramString);
          this._Supported = false;
        }
      }
    }
  }
  
  private void _PurchaseItem(String paramString1, String paramString2, String paramString3, boolean paramBoolean)
  {
    if (this._IAPHelper == null) {
      SendMessage("OnItemPurchaseFailed", "");
    }
    for (;;)
    {
      return;
      this._IAPHelper.startPayment(paramString1, paramBoolean, this._OnPaymentListener);
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\kabam\android\iap\samsung\SamsungIAPManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */