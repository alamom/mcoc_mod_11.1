package com.google.android.gms.internal;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.text.TextUtils;
import java.util.HashMap;
import java.util.Map;

@ez
public final class bx
{
  public static final by pA = new by()
  {
    public void a(gv paramAnonymousgv, Map<String, String> paramAnonymousMap) {}
  };
  public static final by pB = new by()
  {
    public void a(gv paramAnonymousgv, Map<String, String> paramAnonymousMap)
    {
      paramAnonymousMap = (String)paramAnonymousMap.get("urls");
      if (TextUtils.isEmpty(paramAnonymousMap)) {
        gs.W("URLs missing in canOpenURLs GMSG.");
      }
      for (;;)
      {
        return;
        String[] arrayOfString = paramAnonymousMap.split(",");
        HashMap localHashMap = new HashMap();
        PackageManager localPackageManager = paramAnonymousgv.getContext().getPackageManager();
        int j = arrayOfString.length;
        int i = 0;
        if (i < j)
        {
          String str1 = arrayOfString[i];
          paramAnonymousMap = str1.split(";", 2);
          String str2 = paramAnonymousMap[0].trim();
          if (paramAnonymousMap.length > 1)
          {
            paramAnonymousMap = paramAnonymousMap[1].trim();
            label100:
            if (localPackageManager.resolveActivity(new Intent(paramAnonymousMap, Uri.parse(str2)), 65536) == null) {
              break label151;
            }
          }
          label151:
          for (boolean bool = true;; bool = false)
          {
            localHashMap.put(str1, Boolean.valueOf(bool));
            i++;
            break;
            paramAnonymousMap = "android.intent.action.VIEW";
            break label100;
          }
        }
        paramAnonymousgv.a("openableURLs", localHashMap);
      }
    }
  };
  public static final by pC = new by()
  {
    public void a(gv paramAnonymousgv, Map<String, String> paramAnonymousMap)
    {
      str = (String)paramAnonymousMap.get("u");
      if (str == null) {
        gs.W("URL missing from click GMSG.");
      }
      for (;;)
      {
        return;
        paramAnonymousMap = Uri.parse(str);
        try
        {
          Object localObject = paramAnonymousgv.dw();
          if ((localObject == null) || (!((k)localObject).b(paramAnonymousMap))) {
            break;
          }
          localObject = ((k)localObject).a(paramAnonymousMap, paramAnonymousgv.getContext());
          paramAnonymousMap = (Map<String, String>)localObject;
        }
        catch (l locall)
        {
          for (;;)
          {
            gs.W("Unable to append parameter to URL: " + str);
          }
        }
        paramAnonymousMap = paramAnonymousMap.toString();
        new gq(paramAnonymousgv.getContext(), paramAnonymousgv.dx().wD, paramAnonymousMap).start();
      }
    }
  };
  public static final by pD = new by()
  {
    public void a(gv paramAnonymousgv, Map<String, String> paramAnonymousMap)
    {
      paramAnonymousgv = paramAnonymousgv.dt();
      if (paramAnonymousgv == null) {
        gs.W("A GMSG tried to close something that wasn't an overlay.");
      }
      for (;;)
      {
        return;
        paramAnonymousgv.close();
      }
    }
  };
  public static final by pE = new by()
  {
    public void a(gv paramAnonymousgv, Map<String, String> paramAnonymousMap)
    {
      paramAnonymousgv.o("1".equals(paramAnonymousMap.get("custom_close")));
    }
  };
  public static final by pF = new by()
  {
    public void a(gv paramAnonymousgv, Map<String, String> paramAnonymousMap)
    {
      paramAnonymousMap = (String)paramAnonymousMap.get("u");
      if (paramAnonymousMap == null) {
        gs.W("URL missing from httpTrack GMSG.");
      }
      for (;;)
      {
        return;
        new gq(paramAnonymousgv.getContext(), paramAnonymousgv.dx().wD, paramAnonymousMap).start();
      }
    }
  };
  public static final by pG = new by()
  {
    public void a(gv paramAnonymousgv, Map<String, String> paramAnonymousMap)
    {
      gs.U("Received log message: " + (String)paramAnonymousMap.get("string"));
    }
  };
  public static final by pH = new by()
  {
    public void a(gv paramAnonymousgv, Map<String, String> paramAnonymousMap)
    {
      String str2 = (String)paramAnonymousMap.get("tx");
      String str1 = (String)paramAnonymousMap.get("ty");
      paramAnonymousMap = (String)paramAnonymousMap.get("td");
      try
      {
        int k = Integer.parseInt(str2);
        int j = Integer.parseInt(str1);
        int i = Integer.parseInt(paramAnonymousMap);
        paramAnonymousgv = paramAnonymousgv.dw();
        if (paramAnonymousgv != null) {
          paramAnonymousgv.z().a(k, j, i);
        }
        return;
      }
      catch (NumberFormatException paramAnonymousgv)
      {
        for (;;)
        {
          gs.W("Could not parse touch parameters from gmsg.");
        }
      }
    }
  };
  public static final by pI = new ce();
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\internal\bx.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */