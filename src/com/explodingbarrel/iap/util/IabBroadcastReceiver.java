package com.explodingbarrel.iap.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class IabBroadcastReceiver
  extends BroadcastReceiver
{
  private final IabBroadcastListener mListener;
  
  public IabBroadcastReceiver(IabBroadcastListener paramIabBroadcastListener)
  {
    this.mListener = paramIabBroadcastListener;
  }
  
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    if (this.mListener != null) {
      this.mListener.receivedIABBroadcast();
    }
  }
  
  public static abstract interface IabBroadcastListener
  {
    public abstract void receivedIABBroadcast();
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\explodingbarrel\iap\util\IabBroadcastReceiver.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */