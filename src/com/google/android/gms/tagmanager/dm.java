package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.d.a;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

class dm
{
  private static bz<d.a> a(bz<d.a> parambz)
  {
    try
    {
      String str = de(di.j((d.a)parambz.getObject()));
      bz localbz = new com/google/android/gms/tagmanager/bz;
      localbz.<init>(di.u(str), parambz.oG());
      parambz = localbz;
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
      for (;;)
      {
        bh.b("Escape URI: unsupported encoding", localUnsupportedEncodingException);
      }
    }
    return parambz;
  }
  
  private static bz<d.a> a(bz<d.a> parambz, int paramInt)
  {
    if (!q((d.a)parambz.getObject())) {
      bh.T("Escaping can only be applied to strings.");
    }
    for (;;)
    {
      return parambz;
      switch (paramInt)
      {
      default: 
        bh.T("Unsupported Value Escaping: " + paramInt);
        break;
      case 12: 
        parambz = a(parambz);
      }
    }
  }
  
  static bz<d.a> a(bz<d.a> parambz, int... paramVarArgs)
  {
    int j = paramVarArgs.length;
    for (int i = 0; i < j; i++) {
      parambz = a(parambz, paramVarArgs[i]);
    }
    return parambz;
  }
  
  static String de(String paramString)
    throws UnsupportedEncodingException
  {
    return URLEncoder.encode(paramString, "UTF-8").replaceAll("\\+", "%20");
  }
  
  private static boolean q(d.a parama)
  {
    return di.o(parama) instanceof String;
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\tagmanager\dm.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */