package com.google.android.gms.internal;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;

@ez
public final class dh
{
  public static boolean a(Context paramContext, dj paramdj, dq paramdq)
  {
    boolean bool = false;
    if (paramdj == null) {
      gs.W("No intent data for launcher overlay.");
    }
    for (;;)
    {
      return bool;
      Intent localIntent = new Intent();
      if (TextUtils.isEmpty(paramdj.rq))
      {
        gs.W("Open GMSG did not contain a URL.");
      }
      else
      {
        if (!TextUtils.isEmpty(paramdj.mimeType)) {
          localIntent.setDataAndType(Uri.parse(paramdj.rq), paramdj.mimeType);
        }
        String[] arrayOfString;
        for (;;)
        {
          localIntent.setAction("android.intent.action.VIEW");
          if (!TextUtils.isEmpty(paramdj.packageName)) {
            localIntent.setPackage(paramdj.packageName);
          }
          if (TextUtils.isEmpty(paramdj.rr)) {
            break label182;
          }
          arrayOfString = paramdj.rr.split("/", 2);
          if (arrayOfString.length >= 2) {
            break label168;
          }
          gs.W("Could not parse component name from open GMSG: " + paramdj.rr);
          break;
          localIntent.setData(Uri.parse(paramdj.rq));
        }
        label168:
        localIntent.setClassName(arrayOfString[0], arrayOfString[1]);
        try
        {
          label182:
          paramdj = new java/lang/StringBuilder;
          paramdj.<init>();
          gs.V("Launching an intent: " + localIntent);
          paramContext.startActivity(localIntent);
          paramdq.ab();
          bool = true;
        }
        catch (ActivityNotFoundException paramContext)
        {
          gs.W(paramContext.getMessage());
        }
      }
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\internal\dh.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */