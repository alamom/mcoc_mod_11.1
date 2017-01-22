package com.google.android.gms.internal;

import android.content.Context;
import android.content.res.Resources;
import android.os.SystemClock;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import java.util.Map;

@ez
public final class ce
  implements by
{
  private static int a(DisplayMetrics paramDisplayMetrics, Map<String, String> paramMap, String paramString, int paramInt)
  {
    paramMap = (String)paramMap.get(paramString);
    int i = paramInt;
    if (paramMap != null) {}
    try
    {
      i = gr.a(paramDisplayMetrics, Integer.parseInt(paramMap));
      return i;
    }
    catch (NumberFormatException paramDisplayMetrics)
    {
      for (;;)
      {
        gs.W("Could not parse " + paramString + " in a video GMSG: " + paramMap);
        i = paramInt;
      }
    }
  }
  
  public void a(gv paramgv, Map<String, String> paramMap)
  {
    String str = (String)paramMap.get("action");
    if (str == null) {
      gs.W("Action missing from video GMSG.");
    }
    for (;;)
    {
      return;
      Object localObject = paramgv.dt();
      if (localObject == null)
      {
        gs.W("Could not get ad overlay for a video GMSG.");
      }
      else
      {
        boolean bool2 = "new".equalsIgnoreCase(str);
        boolean bool1 = "position".equalsIgnoreCase(str);
        int i;
        int j;
        if ((bool2) || (bool1))
        {
          paramgv = paramgv.getContext().getResources().getDisplayMetrics();
          int m = a(paramgv, paramMap, "x", 0);
          i = a(paramgv, paramMap, "y", 0);
          int k = a(paramgv, paramMap, "w", -1);
          j = a(paramgv, paramMap, "h", -1);
          if ((bool2) && (((dk)localObject).bV() == null)) {
            ((dk)localObject).c(m, i, k, j);
          } else {
            ((dk)localObject).b(m, i, k, j);
          }
        }
        else
        {
          localObject = ((dk)localObject).bV();
          if (localObject == null)
          {
            do.a(paramgv, "no_video_view", null);
          }
          else if ("click".equalsIgnoreCase(str))
          {
            paramgv = paramgv.getContext().getResources().getDisplayMetrics();
            i = a(paramgv, paramMap, "x", 0);
            j = a(paramgv, paramMap, "y", 0);
            long l = SystemClock.uptimeMillis();
            paramgv = MotionEvent.obtain(l, l, 0, i, j, 0);
            ((do)localObject).b(paramgv);
            paramgv.recycle();
          }
          else if ("controls".equalsIgnoreCase(str))
          {
            paramgv = (String)paramMap.get("enabled");
            if (paramgv == null) {
              gs.W("Enabled parameter missing from controls video GMSG.");
            } else {
              ((do)localObject).q(Boolean.parseBoolean(paramgv));
            }
          }
          else if ("currentTime".equalsIgnoreCase(str))
          {
            paramgv = (String)paramMap.get("time");
            if (paramgv == null) {
              gs.W("Time parameter missing from currentTime video GMSG.");
            } else {
              try
              {
                ((do)localObject).seekTo((int)(Float.parseFloat(paramgv) * 1000.0F));
              }
              catch (NumberFormatException paramMap)
              {
                gs.W("Could not parse time parameter from currentTime video GMSG: " + paramgv);
              }
            }
          }
          else if ("hide".equalsIgnoreCase(str))
          {
            ((do)localObject).setVisibility(4);
          }
          else if ("load".equalsIgnoreCase(str))
          {
            ((do)localObject).ch();
          }
          else if ("pause".equalsIgnoreCase(str))
          {
            ((do)localObject).pause();
          }
          else if ("play".equalsIgnoreCase(str))
          {
            ((do)localObject).play();
          }
          else if ("show".equalsIgnoreCase(str))
          {
            ((do)localObject).setVisibility(0);
          }
          else if ("src".equalsIgnoreCase(str))
          {
            ((do)localObject).C((String)paramMap.get("src"));
          }
          else
          {
            gs.W("Unknown video action: " + str);
          }
        }
      }
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\internal\ce.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */