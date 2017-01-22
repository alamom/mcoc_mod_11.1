package com.explodingbarrel.notifications;

import android.os.Bundle;
import org.json.JSONObject;

public class NotificationNote
{
  public String category;
  public Bundle data;
  public String group;
  public String id;
  public String summary;
  public String text;
  public String ticker;
  public String title;
  
  public NotificationNote(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, String paramString7, Bundle paramBundle)
  {
    this.id = paramString1;
    this.category = paramString2;
    this.title = paramString3;
    this.ticker = paramString4;
    this.text = paramString5;
    this.group = paramString6;
    this.summary = paramString7;
    this.data = paramBundle;
  }
  
  public NotificationNote(JSONObject paramJSONObject)
  {
    this.id = paramJSONObject.optString("id");
    this.category = paramJSONObject.optString("category");
    this.title = paramJSONObject.optString("title");
    this.ticker = paramJSONObject.optString("ticker");
    this.text = paramJSONObject.optString("text");
    this.group = paramJSONObject.optString("group");
    this.summary = paramJSONObject.optString("summary");
    this.data = Util.JSONtoBundle(paramJSONObject.optJSONObject("data"));
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\explodingbarrel\notifications\NotificationNote.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */