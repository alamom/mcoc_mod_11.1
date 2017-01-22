package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.a;
import com.google.android.gms.internal.b;
import com.google.android.gms.internal.d.a;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

class ao
  extends aj
{
  private static final String ID = a.ac.toString();
  private static final String apf = b.bw.toString();
  private static final String aph = b.de.toString();
  private static final String apl = b.bn.toString();
  
  public ao()
  {
    super(ID, new String[] { apf });
  }
  
  private byte[] d(String paramString, byte[] paramArrayOfByte)
    throws NoSuchAlgorithmException
  {
    paramString = MessageDigest.getInstance(paramString);
    paramString.update(paramArrayOfByte);
    return paramString.digest();
  }
  
  public d.a C(Map<String, d.a> paramMap)
  {
    Object localObject = (d.a)paramMap.get(apf);
    if ((localObject == null) || (localObject == di.pK())) {
      paramMap = di.pK();
    }
    for (;;)
    {
      return paramMap;
      String str = di.j((d.a)localObject);
      localObject = (d.a)paramMap.get(apl);
      if (localObject == null)
      {
        localObject = "MD5";
        label55:
        paramMap = (d.a)paramMap.get(aph);
        if (paramMap != null) {
          break label113;
        }
        paramMap = "text";
        label75:
        if (!"text".equals(paramMap)) {
          break label121;
        }
      }
      for (paramMap = str.getBytes();; paramMap = j.cm(str))
      {
        try
        {
          paramMap = di.u(j.d(d((String)localObject, paramMap)));
        }
        catch (NoSuchAlgorithmException paramMap)
        {
          label113:
          label121:
          bh.T("Hash: unknown algorithm: " + (String)localObject);
          paramMap = di.pK();
        }
        localObject = di.j((d.a)localObject);
        break label55;
        paramMap = di.j(paramMap);
        break label75;
        if (!"base16".equals(paramMap)) {
          break label138;
        }
      }
      label138:
      bh.T("Hash: unknown input format: " + paramMap);
      paramMap = di.pK();
    }
  }
  
  public boolean nN()
  {
    return true;
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\tagmanager\ao.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */