package com.mobileapptracker;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import java.util.concurrent.Semaphore;
import org.json.JSONObject;

public class MATEventQueue
{
  private static long d = 0L;
  private SharedPreferences a;
  private Semaphore b;
  private MobileAppTracker c;
  
  public MATEventQueue(Context paramContext, MobileAppTracker paramMobileAppTracker)
  {
    this.a = paramContext.getSharedPreferences("mat_queue", 0);
    this.b = new Semaphore(1, true);
    this.c = paramMobileAppTracker;
  }
  
  protected String getKeyFromQueue(String paramString)
  {
    try
    {
      paramString = this.a.getString(paramString, null);
      return paramString;
    }
    finally
    {
      paramString = finally;
      throw paramString;
    }
  }
  
  protected int getQueueSize()
  {
    try
    {
      int i = this.a.getInt("queuesize", 0);
      return i;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  protected void removeKeyFromQueue(String paramString)
  {
    try
    {
      setQueueSize(getQueueSize() - 1);
      SharedPreferences.Editor localEditor = this.a.edit();
      localEditor.remove(paramString);
      localEditor.commit();
      return;
    }
    finally
    {
      paramString = finally;
      throw paramString;
    }
  }
  
  protected void setQueueItemForKey(JSONObject paramJSONObject, String paramString)
  {
    try
    {
      SharedPreferences.Editor localEditor = this.a.edit();
      localEditor.putString(paramString, paramJSONObject.toString());
      localEditor.commit();
      return;
    }
    finally
    {
      paramJSONObject = finally;
      throw paramJSONObject;
    }
  }
  
  protected void setQueueSize(int paramInt)
  {
    try
    {
      SharedPreferences.Editor localEditor = this.a.edit();
      int i = paramInt;
      if (paramInt < 0) {
        i = 0;
      }
      localEditor.putInt("queuesize", i);
      localEditor.commit();
      return;
    }
    finally {}
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\mobileapptracker\MATEventQueue.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */