package com.google.android.vending.licensing;

import android.text.TextUtils;
import java.util.regex.Pattern;

public class ResponseData
{
  public String extra;
  public int nonce;
  public String packageName;
  public int responseCode;
  public long timestamp;
  public String userId;
  public String versionCode;
  
  public static ResponseData parse(String paramString)
  {
    int i = paramString.indexOf(':');
    Object localObject2;
    if (-1 == i)
    {
      localObject2 = "";
      localObject1 = paramString;
      paramString = (String)localObject2;
      localObject2 = TextUtils.split((String)localObject1, Pattern.quote("|"));
      if (localObject2.length < 6) {
        throw new IllegalArgumentException("Wrong number of fields.");
      }
    }
    else
    {
      localObject1 = paramString.substring(0, i);
      if (i >= paramString.length()) {}
      for (paramString = "";; paramString = paramString.substring(i + 1)) {
        break;
      }
    }
    Object localObject1 = new ResponseData();
    ((ResponseData)localObject1).extra = paramString;
    ((ResponseData)localObject1).responseCode = Integer.parseInt(localObject2[0]);
    ((ResponseData)localObject1).nonce = Integer.parseInt(localObject2[1]);
    ((ResponseData)localObject1).packageName = localObject2[2];
    ((ResponseData)localObject1).versionCode = localObject2[3];
    ((ResponseData)localObject1).userId = localObject2[4];
    ((ResponseData)localObject1).timestamp = Long.parseLong(localObject2[5]);
    return (ResponseData)localObject1;
  }
  
  public String toString()
  {
    return TextUtils.join("|", new Object[] { Integer.valueOf(this.responseCode), Integer.valueOf(this.nonce), this.packageName, this.versionCode, this.userId, Long.valueOf(this.timestamp) });
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\vending\licensing\ResponseData.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */