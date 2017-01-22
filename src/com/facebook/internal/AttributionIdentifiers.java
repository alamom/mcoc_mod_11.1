package com.facebook.internal;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Looper;
import android.util.Log;
import com.facebook.FacebookException;
import java.lang.reflect.Method;

public class AttributionIdentifiers
{
  private static final String ANDROID_ID_COLUMN_NAME = "androidid";
  private static final String ATTRIBUTION_ID_COLUMN_NAME = "aid";
  private static final Uri ATTRIBUTION_ID_CONTENT_URI = Uri.parse("content://com.facebook.katana.provider.AttributionIdProvider");
  private static final int CONNECTION_RESULT_SUCCESS = 0;
  private static final long IDENTIFIER_REFRESH_INTERVAL_MILLIS = 3600000L;
  private static final String LIMIT_TRACKING_COLUMN_NAME = "limit_tracking";
  private static final String TAG = AttributionIdentifiers.class.getCanonicalName();
  private static AttributionIdentifiers recentlyFetchedIdentifiers;
  private String androidAdvertiserId;
  private String attributionId;
  private long fetchTime;
  private boolean limitTracking;
  
  private static AttributionIdentifiers getAndroidId(Context paramContext)
  {
    AttributionIdentifiers localAttributionIdentifiers = new AttributionIdentifiers();
    try
    {
      if (Looper.myLooper() == Looper.getMainLooper())
      {
        paramContext = new com/facebook/FacebookException;
        paramContext.<init>("getAndroidId cannot be called on the main thread.");
        throw paramContext;
      }
    }
    catch (Exception paramContext)
    {
      Utility.logd("android_id", paramContext);
    }
    for (;;)
    {
      return localAttributionIdentifiers;
      Object localObject1 = Utility.getMethodQuietly("com.google.android.gms.common.GooglePlayServicesUtil", "isGooglePlayServicesAvailable", new Class[] { Context.class });
      if (localObject1 != null)
      {
        localObject1 = Utility.invokeMethodQuietly(null, (Method)localObject1, new Object[] { paramContext });
        if (((localObject1 instanceof Integer)) && (((Integer)localObject1).intValue() == 0))
        {
          localObject1 = Utility.getMethodQuietly("com.google.android.gms.ads.identifier.AdvertisingIdClient", "getAdvertisingIdInfo", new Class[] { Context.class });
          if (localObject1 != null)
          {
            Object localObject2 = Utility.invokeMethodQuietly(null, (Method)localObject1, new Object[] { paramContext });
            if (localObject2 != null)
            {
              paramContext = Utility.getMethodQuietly(localObject2.getClass(), "getId", new Class[0]);
              localObject1 = Utility.getMethodQuietly(localObject2.getClass(), "isLimitAdTrackingEnabled", new Class[0]);
              if ((paramContext != null) && (localObject1 != null))
              {
                localAttributionIdentifiers.androidAdvertiserId = ((String)Utility.invokeMethodQuietly(localObject2, paramContext, new Object[0]));
                localAttributionIdentifiers.limitTracking = ((Boolean)Utility.invokeMethodQuietly(localObject2, (Method)localObject1, new Object[0])).booleanValue();
              }
            }
          }
        }
      }
    }
  }
  
  public static AttributionIdentifiers getAttributionIdentifiers(Context paramContext)
  {
    if ((recentlyFetchedIdentifiers != null) && (System.currentTimeMillis() - recentlyFetchedIdentifiers.fetchTime < 3600000L)) {
      paramContext = recentlyFetchedIdentifiers;
    }
    for (;;)
    {
      return paramContext;
      AttributionIdentifiers localAttributionIdentifiers = getAndroidId(paramContext);
      try
      {
        Cursor localCursor = paramContext.getContentResolver().query(ATTRIBUTION_ID_CONTENT_URI, new String[] { "aid", "androidid", "limit_tracking" }, null, null, null);
        paramContext = localAttributionIdentifiers;
        if (localCursor != null)
        {
          paramContext = localAttributionIdentifiers;
          if (localCursor.moveToFirst())
          {
            int i = localCursor.getColumnIndex("aid");
            int j = localCursor.getColumnIndex("androidid");
            int k = localCursor.getColumnIndex("limit_tracking");
            localAttributionIdentifiers.attributionId = localCursor.getString(i);
            if ((j > 0) && (k > 0) && (localAttributionIdentifiers.getAndroidAdvertiserId() == null))
            {
              localAttributionIdentifiers.androidAdvertiserId = localCursor.getString(j);
              localAttributionIdentifiers.limitTracking = Boolean.parseBoolean(localCursor.getString(k));
            }
            localCursor.close();
            localAttributionIdentifiers.fetchTime = System.currentTimeMillis();
            recentlyFetchedIdentifiers = localAttributionIdentifiers;
            paramContext = localAttributionIdentifiers;
          }
        }
      }
      catch (Exception paramContext)
      {
        Log.d(TAG, "Caught unexpected exception in getAttributionId(): " + paramContext.toString());
        paramContext = null;
      }
    }
  }
  
  public String getAndroidAdvertiserId()
  {
    return this.androidAdvertiserId;
  }
  
  public String getAttributionId()
  {
    return this.attributionId;
  }
  
  public boolean isTrackingLimited()
  {
    return this.limitTracking;
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\facebook\internal\AttributionIdentifiers.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */