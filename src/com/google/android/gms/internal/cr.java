package com.google.android.gms.internal;

import android.content.Context;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@ez
public final class cr
{
  public static List<String> a(JSONObject paramJSONObject, String paramString)
    throws JSONException
  {
    paramString = paramJSONObject.optJSONArray(paramString);
    if (paramString != null)
    {
      paramJSONObject = new ArrayList(paramString.length());
      for (int i = 0; i < paramString.length(); i++) {
        paramJSONObject.add(paramString.getString(i));
      }
    }
    for (paramJSONObject = Collections.unmodifiableList(paramJSONObject);; paramJSONObject = null) {
      return paramJSONObject;
    }
  }
  
  public static void a(Context paramContext, String paramString1, fz paramfz, String paramString2, boolean paramBoolean, List<String> paramList)
  {
    if (paramBoolean) {}
    for (String str1 = "1";; str1 = "0")
    {
      Iterator localIterator = paramList.iterator();
      while (localIterator.hasNext())
      {
        String str2 = ((String)localIterator.next()).replaceAll("@gw_adlocid@", paramString2).replaceAll("@gw_adnetrefresh@", str1).replaceAll("@gw_qdata@", paramfz.vq.qi).replaceAll("@gw_sdkver@", paramString1).replaceAll("@gw_sessid@", gb.vK).replaceAll("@gw_seqnum@", paramfz.tA);
        paramList = str2;
        if (paramfz.qy != null) {
          paramList = str2.replaceAll("@gw_adnetid@", paramfz.qy.pX).replaceAll("@gw_allocid@", paramfz.qy.pZ);
        }
        new gq(paramContext, paramString1, paramList).start();
      }
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\internal\cr.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */