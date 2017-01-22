package com.mobileapptracker;

import android.util.Log;
import java.util.concurrent.Semaphore;
import org.json.JSONException;
import org.json.JSONObject;

public class MATEventQueue$Add
  implements Runnable
{
  private String b = null;
  private String c = null;
  private JSONObject d = null;
  private boolean e = false;
  
  protected MATEventQueue$Add(MATEventQueue paramMATEventQueue, String paramString1, String paramString2, JSONObject paramJSONObject, boolean paramBoolean)
  {
    this.b = paramString1;
    this.c = paramString2;
    this.d = paramJSONObject;
    this.e = paramBoolean;
  }
  
  public void run()
  {
    for (;;)
    {
      try
      {
        MATEventQueue.a(this.a).acquire();
        localJSONObject = new org/json/JSONObject;
        localJSONObject.<init>();
      }
      catch (InterruptedException localInterruptedException)
      {
        JSONObject localJSONObject;
        int i;
        String str;
        Log.w("MobileAppTracker", "Interrupted adding event to queue");
        localInterruptedException.printStackTrace();
        MATEventQueue.a(this.a).release();
        continue;
      }
      finally
      {
        MATEventQueue.a(this.a).release();
      }
      try
      {
        localJSONObject.put("link", this.b);
        localJSONObject.put("data", this.c);
        localJSONObject.put("post_body", this.d);
        localJSONObject.put("first_session", this.e);
        i = this.a.getQueueSize() + 1;
        this.a.setQueueSize(i);
        str = Integer.toString(i);
        this.a.setQueueItemForKey(localJSONObject, str);
        MATEventQueue.a(this.a).release();
        return;
      }
      catch (JSONException localJSONException)
      {
        Log.w("MobileAppTracker", "Failed creating event for queueing");
        localJSONException.printStackTrace();
        MATEventQueue.a(this.a).release();
      }
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\mobileapptracker\MATEventQueue$Add.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */