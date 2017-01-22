package com.google.android.gms.tagmanager;

import android.util.Base64;
import com.google.android.gms.internal.a;
import com.google.android.gms.internal.b;
import com.google.android.gms.internal.d.a;
import java.util.Map;

class ac
  extends aj
{
  private static final String ID = a.aa.toString();
  private static final String apf = b.bw.toString();
  private static final String apg = b.dH.toString();
  private static final String aph = b.de.toString();
  private static final String api = b.dP.toString();
  
  public ac()
  {
    super(ID, new String[] { apf });
  }
  
  public d.a C(Map<String, d.a> paramMap)
  {
    Object localObject = (d.a)paramMap.get(apf);
    if ((localObject == null) || (localObject == di.pK()))
    {
      paramMap = di.pK();
      return paramMap;
    }
    String str2 = di.j((d.a)localObject);
    localObject = (d.a)paramMap.get(aph);
    String str1;
    if (localObject == null)
    {
      str1 = "text";
      label57:
      localObject = (d.a)paramMap.get(api);
      if (localObject != null) {
        break label153;
      }
      localObject = "base16";
      label77:
      paramMap = (d.a)paramMap.get(apg);
      if ((paramMap == null) || (!di.n(paramMap).booleanValue())) {
        break label336;
      }
    }
    label153:
    label268:
    label307:
    label336:
    for (int i = 3;; i = 2)
    {
      for (;;)
      {
        try
        {
          if ("text".equals(str1))
          {
            paramMap = str2.getBytes();
            if (!"base16".equals(localObject)) {
              break label268;
            }
            paramMap = j.d(paramMap);
            paramMap = di.u(paramMap);
            break;
            str1 = di.j((d.a)localObject);
            break label57;
            localObject = di.j((d.a)localObject);
            break label77;
          }
          if ("base16".equals(str1))
          {
            paramMap = j.cm(str2);
            continue;
          }
          if ("base64".equals(str1))
          {
            paramMap = Base64.decode(str2, i);
            continue;
          }
          if ("base64url".equals(str1))
          {
            paramMap = Base64.decode(str2, i | 0x8);
            continue;
          }
          paramMap = new java/lang/StringBuilder;
          paramMap.<init>();
          bh.T("Encode: unknown input format: " + str1);
          paramMap = di.pK();
        }
        catch (IllegalArgumentException paramMap)
        {
          bh.T("Encode: invalid input:");
          paramMap = di.pK();
        }
        break;
        if ("base64".equals(localObject))
        {
          paramMap = Base64.encodeToString(paramMap, i);
        }
        else
        {
          if (!"base64url".equals(localObject)) {
            break label307;
          }
          paramMap = Base64.encodeToString(paramMap, i | 0x8);
        }
      }
      bh.T("Encode: unknown output format: " + (String)localObject);
      paramMap = di.pK();
      break;
    }
  }
  
  public boolean nN()
  {
    return true;
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\tagmanager\ac.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */