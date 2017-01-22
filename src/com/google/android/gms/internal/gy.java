package com.google.android.gms.internal;

import android.content.Context;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@ez
public class gy
  extends gw
{
  public gy(gv paramgv, boolean paramBoolean)
  {
    super(paramgv, paramBoolean);
  }
  
  protected WebResourceResponse d(Context paramContext, String paramString1, String paramString2)
    throws IOException
  {
    paramString2 = (HttpURLConnection)new URL(paramString2).openConnection();
    try
    {
      gj.a(paramContext, paramString1, true, paramString2, true);
      paramString2.addRequestProperty("Cache-Control", "max-stale=3600");
      paramString2.connect();
      paramContext = new java/io/InputStreamReader;
      paramContext.<init>(paramString2.getInputStream());
      paramString1 = gj.a(paramContext);
      paramContext = new java/io/ByteArrayInputStream;
      paramContext.<init>(paramString1.getBytes("UTF-8"));
      paramContext = new WebResourceResponse("application/javascript", "UTF-8", paramContext);
      return paramContext;
    }
    finally
    {
      paramString2.disconnect();
    }
  }
  
  public WebResourceResponse shouldInterceptRequest(WebView paramWebView, String paramString)
  {
    for (;;)
    {
      try
      {
        localObject = new java/io/File;
        ((File)localObject).<init>(paramString);
        if ("mraid.js".equalsIgnoreCase(((File)localObject).getName())) {
          continue;
        }
        localObject = super.shouldInterceptRequest(paramWebView, paramString);
        paramWebView = (WebView)localObject;
      }
      catch (IOException localIOException)
      {
        Object localObject;
        gs.W("Could not fetch MRAID JS. " + localIOException.getMessage());
        paramWebView = super.shouldInterceptRequest(paramWebView, paramString);
        continue;
      }
      return paramWebView;
      if (!(paramWebView instanceof gv))
      {
        gs.W("Tried to intercept request from a WebView that wasn't an AdWebView.");
        localObject = super.shouldInterceptRequest(paramWebView, paramString);
        paramWebView = (WebView)localObject;
      }
      else
      {
        localObject = (gv)paramWebView;
        ((gv)localObject).du().bX();
        if (((gv)localObject).Y().og)
        {
          gs.V("shouldInterceptRequest(https://googleads.g.doubleclick.net/mads/static/mad/sdk/native/mraid/v2/mraid_app_interstitial.js)");
          localObject = d(((gv)localObject).getContext(), this.md.dx().wD, "https://googleads.g.doubleclick.net/mads/static/mad/sdk/native/mraid/v2/mraid_app_interstitial.js");
          paramWebView = (WebView)localObject;
        }
        else if (((gv)localObject).dy())
        {
          gs.V("shouldInterceptRequest(https://googleads.g.doubleclick.net/mads/static/mad/sdk/native/mraid/v2/mraid_app_expanded_banner.js)");
          localObject = d(((gv)localObject).getContext(), this.md.dx().wD, "https://googleads.g.doubleclick.net/mads/static/mad/sdk/native/mraid/v2/mraid_app_expanded_banner.js");
          paramWebView = (WebView)localObject;
        }
        else
        {
          gs.V("shouldInterceptRequest(https://googleads.g.doubleclick.net/mads/static/mad/sdk/native/mraid/v2/mraid_app_banner.js)");
          localObject = d(((gv)localObject).getContext(), this.md.dx().wD, "https://googleads.g.doubleclick.net/mads/static/mad/sdk/native/mraid/v2/mraid_app_banner.js");
          paramWebView = (WebView)localObject;
        }
      }
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\internal\gy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */