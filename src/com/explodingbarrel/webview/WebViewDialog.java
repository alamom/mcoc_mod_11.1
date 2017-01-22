package com.explodingbarrel.webview;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import com.unity3d.player.UnityPlayer;

public class WebViewDialog
  extends Dialog
{
  private static final String TAG = "WebView";
  private int Height = 0;
  private Activity Parent = null;
  private int TargetWidth = 1024;
  private String Url = null;
  private int Width = 0;
  private int X = 0;
  private int Y = 0;
  
  public WebViewDialog(Activity paramActivity, String paramString, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
  {
    super(paramActivity);
    this.Parent = paramActivity;
    this.Url = paramString;
    this.TargetWidth = paramInt1;
    this.X = paramInt2;
    this.Y = paramInt3;
    this.Width = paramInt4;
    this.Height = paramInt5;
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    requestWindowFeature(1);
    RelativeLayout localRelativeLayout = new RelativeLayout(this.Parent);
    localRelativeLayout.setBackgroundColor(-16777216);
    paramBundle = new WebView(this.Parent);
    paramBundle.setWebViewClient(new DialogWebViewClient(null));
    WebSettings localWebSettings = paramBundle.getSettings();
    localWebSettings.setJavaScriptEnabled(true);
    localWebSettings.setLoadWithOverviewMode(true);
    localWebSettings.setUseWideViewPort(true);
    paramBundle.setBackgroundColor(0);
    paramBundle.setInitialScale((int)(100.0F * (this.TargetWidth / this.Width)));
    paramBundle.loadUrl(this.Url);
    localRelativeLayout.addView(paramBundle, new RelativeLayout.LayoutParams(-1, -1));
    setContentView(localRelativeLayout, new RelativeLayout.LayoutParams(this.Width, this.Height));
  }
  
  private class DialogWebViewClient
    extends WebViewClient
  {
    private DialogWebViewClient() {}
    
    public void onPageFinished(WebView paramWebView, String paramString)
    {
      paramWebView.setBackgroundColor(0);
    }
    
    public void onScaleChanged(WebView paramWebView, float paramFloat1, float paramFloat2) {}
    
    public boolean shouldOverrideUrlLoading(WebView paramWebView, String paramString)
    {
      boolean bool = true;
      Log.d("WebView", "DialogWebViewClient shouldOverrideUrlLoading : url = " + paramString);
      if (paramString.startsWith("client://") == true) {
        UnityPlayer.UnitySendMessage("webview_callbacks", "OnUrl", paramString);
      }
      for (;;)
      {
        return bool;
        bool = false;
      }
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\explodingbarrel\webview\WebViewDialog.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */