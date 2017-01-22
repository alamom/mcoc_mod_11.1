package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.a;
import com.google.android.gms.internal.b;
import com.google.android.gms.internal.d.a;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

class ch
  extends aj
{
  private static final String ID = a.ag.toString();
  private static final String aqn = b.bw.toString();
  private static final String aqo = b.bx.toString();
  private static final String aqp = b.dc.toString();
  private static final String aqq = b.cW.toString();
  
  public ch()
  {
    super(ID, new String[] { aqn, aqo });
  }
  
  public d.a C(Map<String, d.a> paramMap)
  {
    Object localObject = (d.a)paramMap.get(aqn);
    d.a locala = (d.a)paramMap.get(aqo);
    if ((localObject == null) || (localObject == di.pK()) || (locala == null) || (locala == di.pK())) {
      paramMap = di.pK();
    }
    for (;;)
    {
      return paramMap;
      int i = 64;
      if (di.n((d.a)paramMap.get(aqp)).booleanValue()) {
        i = 66;
      }
      paramMap = (d.a)paramMap.get(aqq);
      int j;
      if (paramMap != null)
      {
        paramMap = di.l(paramMap);
        if (paramMap == di.pF())
        {
          paramMap = di.pK();
          continue;
        }
        int k = paramMap.intValue();
        j = k;
        if (k < 0) {
          paramMap = di.pK();
        }
      }
      else
      {
        j = 1;
      }
      try
      {
        paramMap = di.j((d.a)localObject);
        localObject = di.j(locala);
        locala = null;
        localObject = Pattern.compile((String)localObject, i).matcher(paramMap);
        paramMap = locala;
        if (((Matcher)localObject).find())
        {
          paramMap = locala;
          if (((Matcher)localObject).groupCount() >= j) {
            paramMap = ((Matcher)localObject).group(j);
          }
        }
        if (paramMap == null) {
          paramMap = di.pK();
        } else {
          paramMap = di.u(paramMap);
        }
      }
      catch (PatternSyntaxException paramMap)
      {
        paramMap = di.pK();
      }
    }
  }
  
  public boolean nN()
  {
    return true;
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\tagmanager\ch.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */