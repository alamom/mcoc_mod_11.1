package com.samsung.android.sdk.iap.lib.listener;

import com.samsung.android.sdk.iap.lib.vo.ErrorVo;
import com.samsung.android.sdk.iap.lib.vo.PurchaseVo;

public abstract interface OnPaymentListener
{
  public abstract void onPayment(ErrorVo paramErrorVo, PurchaseVo paramPurchaseVo);
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\samsung\android\sdk\iap\lib\listener\OnPaymentListener.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */