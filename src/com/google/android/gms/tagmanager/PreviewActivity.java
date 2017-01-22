package com.google.android.gms.tagmanager;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

public class PreviewActivity
  extends Activity
{
  private void d(String paramString1, String paramString2, String paramString3)
  {
    AlertDialog localAlertDialog = new AlertDialog.Builder(this).create();
    localAlertDialog.setTitle(paramString1);
    localAlertDialog.setMessage(paramString2);
    localAlertDialog.setButton(-1, paramString3, new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt) {}
    });
    localAlertDialog.show();
  }
  
  public void onCreate(Bundle paramBundle)
  {
    for (;;)
    {
      try
      {
        super.onCreate(paramBundle);
        bh.U("Preview activity");
        Object localObject = getIntent().getData();
        if (!TagManager.getInstance(this).i((Uri)localObject))
        {
          paramBundle = new java/lang/StringBuilder;
          paramBundle.<init>();
          paramBundle = "Cannot preview the app with the uri: " + localObject + ". Launching current version instead.";
          bh.W(paramBundle);
          d("Preview failure", paramBundle, "Continue");
        }
        paramBundle = getPackageManager().getLaunchIntentForPackage(getPackageName());
        if (paramBundle != null)
        {
          localObject = new java/lang/StringBuilder;
          ((StringBuilder)localObject).<init>();
          bh.U("Invoke the launch activity for package name: " + getPackageName());
          startActivity(paramBundle);
          return;
        }
      }
      catch (Exception paramBundle)
      {
        bh.T("Calling preview threw an exception: " + paramBundle.getMessage());
        continue;
      }
      paramBundle = new java/lang/StringBuilder;
      paramBundle.<init>();
      bh.U("No launch activity found for package name: " + getPackageName());
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\tagmanager\PreviewActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */