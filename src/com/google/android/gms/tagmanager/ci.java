package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.a;
import com.google.android.gms.internal.b;
import com.google.android.gms.internal.d.a;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

class ci
  extends dd
{
  private static final String ID = a.ap.toString();
  private static final String aqp = b.dc.toString();
  
  public ci()
  {
    super(ID);
  }
  
  protected boolean a(String paramString1, String paramString2, Map<String, d.a> paramMap)
  {
    if (di.n((d.a)paramMap.get(aqp)).booleanValue()) {}
    for (int i = 66;; i = 64) {
      try
      {
        bool = Pattern.compile(paramString2, i).matcher(paramString1).find();
        return bool;
      }
      catch (PatternSyntaxException paramString1)
      {
        for (;;)
        {
          boolean bool = false;
        }
      }
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\tagmanager\ci.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */