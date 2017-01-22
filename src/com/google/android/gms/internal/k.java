package com.google.android.gms.internal;

import android.content.Context;
import android.net.Uri;
import android.net.Uri.Builder;
import android.view.MotionEvent;

public class k
{
  private String kR = "googleads.g.doubleclick.net";
  private String kS = "/pagead/ads";
  private String kT = "ad.doubleclick.net";
  private String[] kU = { ".doubleclick.net", ".googleadservices.com", ".googlesyndication.com" };
  private g kV;
  
  public k(g paramg)
  {
    this.kV = paramg;
  }
  
  private Uri a(Uri paramUri, Context paramContext, String paramString, boolean paramBoolean)
    throws l
  {
    boolean bool;
    try
    {
      bool = a(paramUri);
      if (bool)
      {
        if (!paramUri.toString().contains("dc_ms=")) {
          break label68;
        }
        paramUri = new com/google/android/gms/internal/l;
        paramUri.<init>("Parameter already exists: dc_ms");
        throw paramUri;
      }
    }
    catch (UnsupportedOperationException paramUri)
    {
      throw new l("Provided Uri is not in a valid state");
    }
    if (paramUri.getQueryParameter("ms") != null)
    {
      paramUri = new com/google/android/gms/internal/l;
      paramUri.<init>("Query parameter already exists: ms");
      throw paramUri;
    }
    label68:
    if (paramBoolean)
    {
      paramContext = this.kV.a(paramContext, paramString);
      if (!bool) {
        break label115;
      }
    }
    label115:
    for (paramUri = b(paramUri, "dc_ms", paramContext);; paramUri = a(paramUri, "ms", paramContext))
    {
      return paramUri;
      paramContext = this.kV.a(paramContext);
      break;
    }
  }
  
  private Uri a(Uri paramUri, String paramString1, String paramString2)
    throws UnsupportedOperationException
  {
    String str = paramUri.toString();
    int j = str.indexOf("&adurl");
    int i = j;
    if (j == -1) {
      i = str.indexOf("?adurl");
    }
    if (i != -1) {}
    for (paramUri = Uri.parse(str.substring(0, i + 1) + paramString1 + "=" + paramString2 + "&" + str.substring(i + 1));; paramUri = paramUri.buildUpon().appendQueryParameter(paramString1, paramString2).build()) {
      return paramUri;
    }
  }
  
  private Uri b(Uri paramUri, String paramString1, String paramString2)
  {
    String str = paramUri.toString();
    int i = str.indexOf(";adurl");
    if (i != -1) {}
    for (paramUri = Uri.parse(str.substring(0, i + 1) + paramString1 + "=" + paramString2 + ";" + str.substring(i + 1));; paramUri = Uri.parse(str.substring(0, paramUri.length() + i) + ";" + paramString1 + "=" + paramString2 + ";" + str.substring(paramUri.length() + i)))
    {
      return paramUri;
      paramUri = paramUri.getEncodedPath();
      i = str.indexOf(paramUri);
    }
  }
  
  public Uri a(Uri paramUri, Context paramContext)
    throws l
  {
    try
    {
      paramUri = a(paramUri, paramContext, paramUri.getQueryParameter("ai"), true);
      return paramUri;
    }
    catch (UnsupportedOperationException paramUri)
    {
      throw new l("Provided Uri is not in a valid state");
    }
  }
  
  public void a(MotionEvent paramMotionEvent)
  {
    this.kV.a(paramMotionEvent);
  }
  
  public boolean a(Uri paramUri)
  {
    if (paramUri == null) {
      throw new NullPointerException();
    }
    try
    {
      bool = paramUri.getHost().equals(this.kT);
      return bool;
    }
    catch (NullPointerException paramUri)
    {
      for (;;)
      {
        boolean bool = false;
      }
    }
  }
  
  public boolean b(Uri paramUri)
  {
    bool2 = false;
    if (paramUri == null) {
      throw new NullPointerException();
    }
    for (;;)
    {
      try
      {
        paramUri = paramUri.getHost();
        String[] arrayOfString = this.kU;
        int j = arrayOfString.length;
        i = 0;
        bool1 = bool2;
        if (i < j)
        {
          bool1 = paramUri.endsWith(arrayOfString[i]);
          if (!bool1) {
            continue;
          }
          bool1 = true;
        }
      }
      catch (NullPointerException paramUri)
      {
        int i;
        boolean bool1 = bool2;
        continue;
      }
      return bool1;
      i++;
    }
  }
  
  public g z()
  {
    return this.kV;
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\internal\k.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */