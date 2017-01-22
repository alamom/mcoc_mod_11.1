package com.google.android.gms.internal;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.net.Uri.Builder;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

@ez
public final class gm
{
  private final Context mContext;
  private int mState = 0;
  private final float ri;
  private String ws;
  private float wt;
  private float wu;
  private float wv;
  
  public gm(Context paramContext)
  {
    this.mContext = paramContext;
    this.ri = paramContext.getResources().getDisplayMetrics().density;
  }
  
  public gm(Context paramContext, String paramString)
  {
    this(paramContext);
    this.ws = paramString;
  }
  
  private void a(int paramInt, float paramFloat1, float paramFloat2)
  {
    if (paramInt == 0)
    {
      this.mState = 0;
      this.wt = paramFloat1;
      this.wu = paramFloat2;
      this.wv = paramFloat2;
    }
    for (;;)
    {
      return;
      if (this.mState != -1) {
        if (paramInt == 2)
        {
          if (paramFloat2 > this.wu) {
            this.wu = paramFloat2;
          }
          for (;;)
          {
            if (this.wu - this.wv <= 30.0F * this.ri) {
              break label97;
            }
            this.mState = -1;
            break;
            if (paramFloat2 < this.wv) {
              this.wv = paramFloat2;
            }
          }
          label97:
          if ((this.mState == 0) || (this.mState == 2)) {
            if (paramFloat1 - this.wt >= 50.0F * this.ri) {
              this.wt = paramFloat1;
            }
          }
          for (this.mState += 1;; this.mState += 1)
          {
            do
            {
              if ((this.mState != 1) && (this.mState != 3)) {
                break label228;
              }
              if (paramFloat1 <= this.wt) {
                break;
              }
              this.wt = paramFloat1;
              break;
            } while (((this.mState != 1) && (this.mState != 3)) || (paramFloat1 - this.wt > -50.0F * this.ri));
            this.wt = paramFloat1;
          }
          label228:
          if ((this.mState == 2) && (paramFloat1 < this.wt)) {
            this.wt = paramFloat1;
          }
        }
        else if ((paramInt == 1) && (this.mState == 4))
        {
          showDialog();
        }
      }
    }
  }
  
  private void showDialog()
  {
    Object localObject2;
    final Object localObject1;
    if (!TextUtils.isEmpty(this.ws))
    {
      localObject2 = new Uri.Builder().encodedQuery(this.ws).build();
      localObject1 = new StringBuilder();
      Map localMap = gj.c((Uri)localObject2);
      localObject2 = localMap.keySet().iterator();
      while (((Iterator)localObject2).hasNext())
      {
        String str = (String)((Iterator)localObject2).next();
        ((StringBuilder)localObject1).append(str).append(" = ").append((String)localMap.get(str)).append("\n\n");
      }
      localObject1 = ((StringBuilder)localObject1).toString().trim();
      if (TextUtils.isEmpty((CharSequence)localObject1)) {}
    }
    for (;;)
    {
      localObject2 = new AlertDialog.Builder(this.mContext);
      ((AlertDialog.Builder)localObject2).setMessage((CharSequence)localObject1);
      ((AlertDialog.Builder)localObject2).setTitle("Ad Information");
      ((AlertDialog.Builder)localObject2).setPositiveButton("Share", new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
        {
          gm.a(gm.this).startActivity(Intent.createChooser(new Intent("android.intent.action.SEND").setType("text/plain").putExtra("android.intent.extra.TEXT", localObject1), "Share via"));
        }
      });
      ((AlertDialog.Builder)localObject2).setNegativeButton("Close", new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt) {}
      });
      ((AlertDialog.Builder)localObject2).create().show();
      return;
      localObject1 = "No debug information";
      continue;
      localObject1 = "No debug information";
    }
  }
  
  public void Q(String paramString)
  {
    this.ws = paramString;
  }
  
  public void c(MotionEvent paramMotionEvent)
  {
    int j = paramMotionEvent.getHistorySize();
    for (int i = 0; i < j; i++) {
      a(paramMotionEvent.getActionMasked(), paramMotionEvent.getHistoricalX(0, i), paramMotionEvent.getHistoricalY(0, i));
    }
    a(paramMotionEvent.getActionMasked(), paramMotionEvent.getX(), paramMotionEvent.getY());
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\internal\gm.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */