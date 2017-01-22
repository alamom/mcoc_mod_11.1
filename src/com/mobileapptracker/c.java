package com.mobileapptracker;

import android.content.Context;
import android.os.Bundle;
import java.lang.reflect.Method;
import java.util.Locale;

final class c
{
  private static Object a;
  private static boolean b = false;
  
  public static void a(Context paramContext, boolean paramBoolean)
  {
    try
    {
      Class[] arrayOfClass = new Class[1];
      arrayOfClass[0] = Context.class;
      Class localClass = Boolean.TYPE;
      Method localMethod = Class.forName("com.facebook.AppEventsLogger").getMethod("activateApp", arrayOfClass);
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = paramContext;
      localMethod.invoke(null, arrayOfObject);
      b = true;
      Class.forName("com.facebook.Settings").getMethod("setLimitEventAndDataUsage", new Class[] { Context.class, localClass }).invoke(null, new Object[] { paramContext, Boolean.valueOf(paramBoolean) });
      a = Class.forName("com.facebook.AppEventsLogger").getMethod("newLogger", arrayOfClass).invoke(null, arrayOfObject);
      return;
    }
    catch (Exception paramContext)
    {
      for (;;)
      {
        paramContext.printStackTrace();
      }
    }
  }
  
  private static void a(Bundle paramBundle, String paramString1, String paramString2)
  {
    if (paramString2 != null) {
      paramBundle.putString(paramString1, paramString2);
    }
  }
  
  public static void a(MATEvent paramMATEvent)
  {
    if (a != null) {}
    try
    {
      localObject1 = Double.TYPE;
      Method localMethod = a.getClass().getMethod("logEvent", new Class[] { String.class, localObject1, Bundle.class });
      localObject1 = paramMATEvent.getEventName();
      d2 = paramMATEvent.getRevenue();
      MATParameters localMATParameters = MATParameters.getInstance();
      localObject2 = paramMATEvent.getEventName().toLowerCase(Locale.US);
      if (((String)localObject2).contains("session"))
      {
        if (b) {}
        for (;;)
        {
          return;
          localObject1 = "fb_mobile_activate_app";
          d1 = d2;
          localObject2 = new android/os/Bundle;
          ((Bundle)localObject2).<init>();
          a((Bundle)localObject2, "fb_currency", paramMATEvent.getCurrencyCode());
          a((Bundle)localObject2, "fb_content_id", paramMATEvent.getContentId());
          a((Bundle)localObject2, "fb_content_type", paramMATEvent.getContentType());
          a((Bundle)localObject2, "fb_search_string", paramMATEvent.getSearchString());
          a((Bundle)localObject2, "fb_num_items", Integer.toString(paramMATEvent.getQuantity()));
          a((Bundle)localObject2, "fb_level", Integer.toString(paramMATEvent.getLevel()));
          a((Bundle)localObject2, "tune_referral_source", localMATParameters.getReferralSource());
          a((Bundle)localObject2, "tune_source_sdk", "TUNE-MAT");
          localMethod.invoke(a, new Object[] { localObject1, Double.valueOf(d1), localObject2 });
          b = false;
        }
      }
    }
    catch (Exception paramMATEvent)
    {
      double d2;
      double d1;
      for (;;)
      {
        Object localObject1;
        Object localObject2;
        paramMATEvent.printStackTrace();
        continue;
        if (((String)localObject2).contains("registration"))
        {
          localObject1 = "fb_mobile_complete_registration";
          d1 = d2;
        }
        else if (((String)localObject2).contains("content_view"))
        {
          localObject1 = "fb_mobile_content_view";
          d1 = d2;
        }
        else if (((String)localObject2).contains("search"))
        {
          localObject1 = "fb_mobile_search";
          d1 = d2;
        }
        else
        {
          if (((String)localObject2).contains("rated")) {
            localObject1 = "fb_mobile_rate";
          }
          try
          {
            d1 = paramMATEvent.getRating();
            continue;
            if (((String)localObject2).contains("tutorial_complete"))
            {
              localObject1 = "fb_mobile_tutorial_completion";
              d1 = d2;
              continue;
            }
            if (((String)localObject2).contains("add_to_cart"))
            {
              localObject1 = "fb_mobile_add_to_cart";
              d1 = d2;
              continue;
            }
            if (((String)localObject2).contains("add_to_wishlist"))
            {
              localObject1 = "fb_mobile_add_to_wishlist";
              d1 = d2;
              continue;
            }
            if (((String)localObject2).contains("checkout_initiated"))
            {
              localObject1 = "fb_mobile_initiated_checkout";
              d1 = d2;
              continue;
            }
            if (((String)localObject2).contains("added_payment_info"))
            {
              localObject1 = "fb_mobile_add_payment_info";
              d1 = d2;
              continue;
            }
            if (((String)localObject2).contains("purchase"))
            {
              localObject1 = "fb_mobile_purchase";
              d1 = d2;
              continue;
            }
            if (((String)localObject2).contains("level_achieved"))
            {
              localObject1 = "fb_mobile_level_achieved";
              d1 = d2;
              continue;
            }
            if (((String)localObject2).contains("achievement_unlocked"))
            {
              localObject1 = "fb_mobile_achievement_unlocked";
              d1 = d2;
              continue;
            }
            d1 = d2;
            if (!((String)localObject2).contains("spent_credits")) {
              continue;
            }
            localObject1 = "fb_mobile_spent_credits";
          }
          catch (Exception localException1)
          {
            try
            {
              int i = paramMATEvent.getQuantity();
              d1 = i;
            }
            catch (Exception localException2)
            {
              d1 = d2;
            }
            localException1 = localException1;
            d1 = d2;
          }
        }
      }
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\mobileapptracker\c.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */