package com.google.android.gms.internal;

import android.text.TextUtils;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.google.android.gms.common.internal.n;
import java.net.URI;
import java.net.URISyntaxException;

@ez
public class ha
  extends WebViewClient
{
  private final gv md;
  private final String xc = Z(paramString);
  private boolean xd = false;
  private final fc xe;
  
  public ha(fc paramfc, gv paramgv, String paramString)
  {
    this.md = paramgv;
    this.xe = paramfc;
  }
  
  private String Z(String paramString)
  {
    String str1;
    if (TextUtils.isEmpty(paramString)) {
      str1 = paramString;
    }
    for (;;)
    {
      return str1;
      str1 = paramString;
      try
      {
        if (paramString.endsWith("/")) {
          str1 = paramString.substring(0, paramString.length() - 1);
        }
      }
      catch (IndexOutOfBoundsException localIndexOutOfBoundsException)
      {
        gs.T(localIndexOutOfBoundsException.getMessage());
        String str2 = paramString;
      }
    }
  }
  
  protected boolean Y(String paramString)
  {
    boolean bool2 = false;
    paramString = Z(paramString);
    boolean bool1;
    if (TextUtils.isEmpty(paramString)) {
      bool1 = bool2;
    }
    for (;;)
    {
      return bool1;
      try
      {
        Object localObject1 = new java/net/URI;
        ((URI)localObject1).<init>(paramString);
        if ("passback".equals(((URI)localObject1).getScheme()))
        {
          gs.S("Passback received");
          this.xe.cz();
          bool1 = true;
        }
        else
        {
          bool1 = bool2;
          if (!TextUtils.isEmpty(this.xc))
          {
            Object localObject2 = new java/net/URI;
            ((URI)localObject2).<init>(this.xc);
            String str = ((URI)localObject2).getHost();
            paramString = ((URI)localObject1).getHost();
            localObject2 = ((URI)localObject2).getPath();
            localObject1 = ((URI)localObject1).getPath();
            bool1 = bool2;
            if (n.equal(str, paramString))
            {
              bool1 = bool2;
              if (n.equal(localObject2, localObject1))
              {
                gs.S("Passback received");
                this.xe.cz();
                bool1 = true;
              }
            }
          }
        }
      }
      catch (URISyntaxException paramString)
      {
        gs.T(paramString.getMessage());
        bool1 = bool2;
      }
    }
  }
  
  public void onLoadResource(WebView paramWebView, String paramString)
  {
    gs.S("JavascriptAdWebViewClient::onLoadResource: " + paramString);
    if (!Y(paramString)) {
      this.md.du().onLoadResource(this.md, paramString);
    }
  }
  
  public void onPageFinished(WebView paramWebView, String paramString)
  {
    gs.S("JavascriptAdWebViewClient::onPageFinished: " + paramString);
    if (!this.xd)
    {
      this.xe.cy();
      this.xd = true;
    }
  }
  
  public boolean shouldOverrideUrlLoading(WebView paramWebView, String paramString)
  {
    gs.S("JavascriptAdWebViewClient::shouldOverrideUrlLoading: " + paramString);
    if (Y(paramString)) {
      gs.S("shouldOverrideUrlLoading: received passback url");
    }
    for (boolean bool = true;; bool = this.md.du().shouldOverrideUrlLoading(this.md, paramString)) {
      return bool;
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\internal\ha.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */