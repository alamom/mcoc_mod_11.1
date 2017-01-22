package com.samsung.android.sdk.iap.lib.listener;

import com.samsung.android.sdk.iap.lib.vo.ErrorVo;
import com.samsung.android.sdk.iap.lib.vo.InboxVo;
import java.util.ArrayList;

public abstract interface OnGetInboxListener
{
  public abstract void onGetItemInbox(ErrorVo paramErrorVo, ArrayList<InboxVo> paramArrayList);
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\samsung\android\sdk\iap\lib\listener\OnGetInboxListener.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */