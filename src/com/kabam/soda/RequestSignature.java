package com.kabam.soda;

import java.util.Date;
import java.util.UUID;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import org.apache.http.client.methods.HttpRequestBase;

class RequestSignature
{
  private static final String HMAC_SHA256_ALGORITHM = "HmacSHA256";
  
  private String getNonce()
  {
    return UUID.randomUUID().toString().replace("-", "");
  }
  
  private String getSignature(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(paramString2);
    localStringBuilder.append(paramString3);
    localStringBuilder.append(paramString4);
    localStringBuilder.append(paramString5);
    localStringBuilder.append(paramString6);
    return sign(localStringBuilder.toString(), paramString1);
  }
  
  public static String sign(String paramString1, String paramString2)
  {
    Object localObject4 = null;
    Object localObject3 = null;
    localObject1 = localObject3;
    localObject2 = localObject4;
    try
    {
      SecretKeySpec localSecretKeySpec = new javax/crypto/spec/SecretKeySpec;
      localObject1 = localObject3;
      localObject2 = localObject4;
      localSecretKeySpec.<init>(paramString2.getBytes(), "HmacSHA256");
      localObject1 = localObject3;
      localObject2 = localObject4;
      paramString2 = Mac.getInstance("HmacSHA256");
      localObject1 = paramString2;
      localObject2 = paramString2;
      paramString2.init(localSecretKeySpec);
      localObject1 = paramString2;
      localObject2 = paramString2;
      paramString1 = Base16Encoder.encode(paramString2.doFinal(paramString1.getBytes()));
      localObject1 = paramString1;
      paramString1 = (String)localObject1;
      if (paramString2 != null)
      {
        paramString2.reset();
        paramString1 = (String)localObject1;
      }
    }
    catch (Exception paramString1)
    {
      for (;;)
      {
        paramString2 = null;
        paramString1 = paramString2;
        if (localObject1 != null)
        {
          ((Mac)localObject1).reset();
          paramString1 = paramString2;
        }
      }
    }
    finally
    {
      if (localObject2 == null) {
        break label115;
      }
      ((Mac)localObject2).reset();
    }
    return paramString1;
  }
  
  public void sign(HttpRequestBase paramHttpRequestBase, String paramString1, String paramString2, String paramString3, String paramString4)
  {
    paramHttpRequestBase.addHeader("X-KBM-Client-Id", paramString1);
    paramString1 = Utility.toRFC2616Date(new Date());
    paramHttpRequestBase.addHeader("X-KBM-Timestamp", paramString1);
    String str = getNonce();
    paramHttpRequestBase.addHeader("X-KBM-Nonce", str);
    paramHttpRequestBase.addHeader("X-KBM-Signature", getSignature(paramString2, paramHttpRequestBase.getMethod(), paramString1, str, paramString3, paramString4));
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\kabam\soda\RequestSignature.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */