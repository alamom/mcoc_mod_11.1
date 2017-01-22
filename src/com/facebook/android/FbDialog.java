package com.facebook.android;

import android.content.Context;
import android.os.Bundle;
import com.facebook.FacebookDialogException;
import com.facebook.FacebookException;
import com.facebook.FacebookOperationCanceledException;
import com.facebook.widget.WebDialog;
import com.facebook.widget.WebDialog.OnCompleteListener;

@Deprecated
public class FbDialog
  extends WebDialog
{
  private Facebook.DialogListener mListener;
  
  public FbDialog(Context paramContext, String paramString, Bundle paramBundle, Facebook.DialogListener paramDialogListener)
  {
    super(paramContext, paramString, paramBundle, 16973840, null);
    setDialogListener(paramDialogListener);
  }
  
  public FbDialog(Context paramContext, String paramString, Bundle paramBundle, Facebook.DialogListener paramDialogListener, int paramInt)
  {
    super(paramContext, paramString, paramBundle, paramInt, null);
    setDialogListener(paramDialogListener);
  }
  
  public FbDialog(Context paramContext, String paramString, Facebook.DialogListener paramDialogListener)
  {
    this(paramContext, paramString, paramDialogListener, 16973840);
  }
  
  public FbDialog(Context paramContext, String paramString, Facebook.DialogListener paramDialogListener, int paramInt)
  {
    super(paramContext, paramString, paramInt);
    setDialogListener(paramDialogListener);
  }
  
  private void callDialogListener(Bundle paramBundle, FacebookException paramFacebookException)
  {
    if (this.mListener == null) {}
    for (;;)
    {
      return;
      if (paramBundle != null)
      {
        this.mListener.onComplete(paramBundle);
      }
      else if ((paramFacebookException instanceof FacebookDialogException))
      {
        paramBundle = (FacebookDialogException)paramFacebookException;
        paramBundle = new DialogError(paramBundle.getMessage(), paramBundle.getErrorCode(), paramBundle.getFailingUrl());
        this.mListener.onError(paramBundle);
      }
      else if ((paramFacebookException instanceof FacebookOperationCanceledException))
      {
        this.mListener.onCancel();
      }
      else
      {
        paramBundle = new FacebookError(paramFacebookException.getMessage());
        this.mListener.onFacebookError(paramBundle);
      }
    }
  }
  
  private void setDialogListener(Facebook.DialogListener paramDialogListener)
  {
    this.mListener = paramDialogListener;
    setOnCompleteListener(new WebDialog.OnCompleteListener()
    {
      public void onComplete(Bundle paramAnonymousBundle, FacebookException paramAnonymousFacebookException)
      {
        FbDialog.this.callDialogListener(paramAnonymousBundle, paramAnonymousFacebookException);
      }
    });
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\facebook\android\FbDialog.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */