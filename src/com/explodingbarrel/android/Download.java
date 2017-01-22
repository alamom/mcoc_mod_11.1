package com.explodingbarrel.android;

import android.app.DownloadManager;
import android.app.DownloadManager.Query;
import android.app.DownloadManager.Request;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;
import com.unity3d.player.UnityPlayer;
import org.json.JSONException;
import org.json.JSONObject;

class Download
{
  private static final String TAG = "Download";
  String _callback = null;
  DownloadManager _manager = null;
  
  public Download(String paramString)
  {
    this._callback = paramString;
    this._manager = ((DownloadManager)getContext().getSystemService("download"));
    paramString = new BroadcastReceiver()
    {
      public void onReceive(Context paramAnonymousContext, Intent paramAnonymousIntent)
      {
        try
        {
          jdField_this.downloadComplete(paramAnonymousContext, paramAnonymousIntent);
          return;
        }
        catch (JSONException paramAnonymousContext)
        {
          for (;;) {}
        }
      }
    };
    getContext().registerReceiver(paramString, new IntentFilter("android.intent.action.DOWNLOAD_COMPLETE"));
  }
  
  private void SendResult(String paramString)
  {
    Log.w("Download", "OnDownloadResult: " + paramString);
    UnityPlayer.UnitySendMessage(this._callback, "OnDownloadResult", paramString);
  }
  
  public void ToFile(String paramString1, String paramString2)
  {
    DownloadManager.Request localRequest = new DownloadManager.Request(Uri.parse(paramString1));
    localRequest.setDescription(paramString1);
    localRequest.setVisibleInDownloadsUi(false);
    localRequest.setNotificationVisibility(2);
    localRequest.setDestinationUri(Uri.parse("file://" + paramString2));
    long l = this._manager.enqueue(localRequest);
    Log.v("Download", "Started download " + paramString1 + "->" + paramString2 + "=" + l);
  }
  
  void downloadComplete(Context paramContext, Intent paramIntent)
    throws JSONException
  {
    long l = paramIntent.getLongExtra("extra_download_id", 0L);
    Log.v("Download", "Got download result " + l);
    paramContext = new DownloadManager.Query();
    paramContext.setFilterById(new long[] { l });
    paramContext = this._manager.query(paramContext);
    if (!paramContext.moveToFirst()) {
      Log.e("Download", "Empty row");
    }
    for (;;)
    {
      return;
      String str = paramContext.getString(paramContext.getColumnIndex("description"));
      paramIntent = new JSONObject();
      paramIntent.put("url", str);
      int i = paramContext.getColumnIndex("status");
      if (8 != paramContext.getInt(i))
      {
        Log.w("Download", "Download Failed");
        int j = paramContext.getColumnIndex("reason");
        paramIntent.put("err", "Download failed: " + paramContext.getString(j) + " code: " + paramContext.getInt(i));
        SendResult(paramIntent.toString());
      }
      else
      {
        paramIntent.put("file", paramContext.getString(paramContext.getColumnIndex("local_uri")));
        SendResult(paramIntent.toString());
      }
    }
  }
  
  Context getContext()
  {
    return UnityPlayer.currentActivity;
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\explodingbarrel\android\Download.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */