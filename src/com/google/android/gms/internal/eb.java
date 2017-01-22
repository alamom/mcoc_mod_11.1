package com.google.android.gms.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;

@ez
public final class eb
  extends ek.a
  implements ServiceConnection
{
  private Context mContext;
  private boolean sD = false;
  private int sE;
  private Intent sF;
  private dw sn;
  private String su;
  private ea sy;
  
  public eb(Context paramContext, String paramString, boolean paramBoolean, int paramInt, Intent paramIntent, ea paramea)
  {
    this.su = paramString;
    this.sE = paramInt;
    this.sF = paramIntent;
    this.sD = paramBoolean;
    this.mContext = paramContext;
    this.sy = paramea;
  }
  
  public void finishPurchase()
  {
    int i = ed.d(this.sF);
    if ((this.sE != -1) || (i != 0)) {}
    for (;;)
    {
      return;
      this.sn = new dw(this.mContext);
      Context localContext2 = this.mContext;
      Intent localIntent = new Intent("com.android.vending.billing.InAppBillingService.BIND");
      Context localContext1 = this.mContext;
      localContext2.bindService(localIntent, this, 1);
    }
  }
  
  public String getProductId()
  {
    return this.su;
  }
  
  public Intent getPurchaseData()
  {
    return this.sF;
  }
  
  public int getResultCode()
  {
    return this.sE;
  }
  
  public boolean isVerified()
  {
    return this.sD;
  }
  
  public void onServiceConnected(ComponentName paramComponentName, IBinder paramIBinder)
  {
    gs.U("In-app billing service connected.");
    this.sn.r(paramIBinder);
    paramComponentName = ed.E(ed.e(this.sF));
    if (paramComponentName == null) {}
    for (;;)
    {
      return;
      if (this.sn.c(this.mContext.getPackageName(), paramComponentName) == 0) {
        ec.j(this.mContext).a(this.sy);
      }
      this.mContext.unbindService(this);
      this.sn.destroy();
    }
  }
  
  public void onServiceDisconnected(ComponentName paramComponentName)
  {
    gs.U("In-app billing service disconnected.");
    this.sn.destroy();
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\internal\eb.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */