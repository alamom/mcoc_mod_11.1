package com.mobileapptracker;

import android.content.Context;
import android.webkit.WebSettings;
import android.webkit.WebView;
import java.lang.ref.WeakReference;

final class e
  implements Runnable
{
  private final WeakReference<Context> b;
  
  public e(MATParameters paramMATParameters, Context paramContext)
  {
    this.b = new WeakReference(paramContext);
  }
  
  public final void run()
  {
    try
    {
      WebView localWebView = new android/webkit/WebView;
      localWebView.<init>((Context)this.b.get());
      String str = localWebView.getSettings().getUserAgentString();
      localWebView.destroy();
      MATParameters.a(this.a, str);
      return;
    }
    catch (VerifyError localVerifyError)
    {
      for (;;) {}
    }
    catch (Exception localException)
    {
      for (;;) {}
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\mobileapptracker\e.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */