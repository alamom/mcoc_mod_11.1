package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.b;
import com.google.android.gms.internal.d.a;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class ba
{
  public static cr.c cG(String paramString)
    throws JSONException
  {
    paramString = n(new JSONObject(paramString));
    cr.d locald = cr.c.oX();
    for (int i = 0; i < paramString.gx.length; i++) {
      locald.a(cr.a.oT().b(b.df.toString(), paramString.gx[i]).b(b.cU.toString(), di.cX(m.nQ())).b(m.nR(), paramString.gy[i]).oW());
    }
    return locald.pa();
  }
  
  private static d.a n(Object paramObject)
    throws JSONException
  {
    return di.u(o(paramObject));
  }
  
  static Object o(Object paramObject)
    throws JSONException
  {
    if ((paramObject instanceof JSONArray)) {
      throw new RuntimeException("JSONArrays are not supported");
    }
    if (JSONObject.NULL.equals(paramObject)) {
      throw new RuntimeException("JSON nulls are not supported");
    }
    Object localObject = paramObject;
    if ((paramObject instanceof JSONObject))
    {
      paramObject = (JSONObject)paramObject;
      localObject = new HashMap();
      Iterator localIterator = ((JSONObject)paramObject).keys();
      while (localIterator.hasNext())
      {
        String str = (String)localIterator.next();
        ((Map)localObject).put(str, o(((JSONObject)paramObject).get(str)));
      }
    }
    return localObject;
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\tagmanager\ba.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */