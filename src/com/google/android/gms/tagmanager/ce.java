package com.google.android.gms.tagmanager;

import android.net.Uri;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

class ce
{
  private static ce aqd;
  private volatile String aoc;
  private volatile a aqe;
  private volatile String aqf;
  private volatile String aqg;
  
  ce()
  {
    clear();
  }
  
  private String cI(String paramString)
  {
    return paramString.split("&")[0].split("=")[1];
  }
  
  private String j(Uri paramUri)
  {
    return paramUri.getQuery().replace("&gtm_debug=x", "");
  }
  
  static ce oJ()
  {
    try
    {
      if (aqd == null)
      {
        localce = new com/google/android/gms/tagmanager/ce;
        localce.<init>();
        aqd = localce;
      }
      ce localce = aqd;
      return localce;
    }
    finally {}
  }
  
  void clear()
  {
    this.aqe = a.aqh;
    this.aqf = null;
    this.aoc = null;
    this.aqg = null;
  }
  
  String getContainerId()
  {
    return this.aoc;
  }
  
  boolean i(Uri paramUri)
  {
    boolean bool = true;
    String str;
    try
    {
      str = URLDecoder.decode(paramUri.toString(), "UTF-8");
      if (!str.matches("^tagmanager.c.\\S+:\\/\\/preview\\/p\\?id=\\S+&gtm_auth=\\S+&gtm_preview=\\d+(&gtm_debug=x)?$")) {
        break label160;
      }
      StringBuilder localStringBuilder = new java/lang/StringBuilder;
      localStringBuilder.<init>();
      bh.V("Container preview url: " + str);
      if (!str.matches(".*?&gtm_debug=x$")) {
        break label145;
      }
      this.aqe = a.aqj;
    }
    catch (UnsupportedEncodingException paramUri)
    {
      for (;;)
      {
        bool = false;
        continue;
        this.aqe = a.aqi;
      }
    }
    finally {}
    this.aqg = j(paramUri);
    if ((this.aqe == a.aqi) || (this.aqe == a.aqj))
    {
      paramUri = new java/lang/StringBuilder;
      paramUri.<init>();
      this.aqf = ("/r?" + this.aqg);
    }
    this.aoc = cI(this.aqg);
    for (;;)
    {
      return bool;
      label145:
      label160:
      if (str.matches("^tagmanager.c.\\S+:\\/\\/preview\\/p\\?id=\\S+&gtm_preview=$"))
      {
        if (cI(paramUri.getQuery()).equals(this.aoc))
        {
          paramUri = new java/lang/StringBuilder;
          paramUri.<init>();
          bh.V("Exit preview mode for container: " + this.aoc);
          this.aqe = a.aqh;
          this.aqf = null;
        }
        else
        {
          bool = false;
        }
      }
      else
      {
        paramUri = new java/lang/StringBuilder;
        paramUri.<init>();
        bh.W("Invalid preview uri: " + str);
        bool = false;
      }
    }
  }
  
  a oK()
  {
    return this.aqe;
  }
  
  String oL()
  {
    return this.aqf;
  }
  
  static enum a
  {
    private a() {}
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\tagmanager\ce.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */