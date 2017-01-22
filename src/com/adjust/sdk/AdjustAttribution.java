package com.adjust.sdk;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamField;
import java.io.Serializable;
import java.util.Locale;
import org.json.JSONObject;

public class AdjustAttribution
  implements Serializable
{
  private static final ObjectStreamField[] serialPersistentFields = { new ObjectStreamField("trackerToken", String.class), new ObjectStreamField("trackerName", String.class), new ObjectStreamField("network", String.class), new ObjectStreamField("campaign", String.class), new ObjectStreamField("adgroup", String.class), new ObjectStreamField("creative", String.class) };
  private static final long serialVersionUID = 1L;
  public String adgroup;
  public String campaign;
  public String creative;
  public String network;
  public String trackerName;
  public String trackerToken;
  
  public static AdjustAttribution fromJson(JSONObject paramJSONObject)
  {
    if (paramJSONObject == null) {}
    AdjustAttribution localAdjustAttribution;
    for (paramJSONObject = null;; paramJSONObject = localAdjustAttribution)
    {
      return paramJSONObject;
      localAdjustAttribution = new AdjustAttribution();
      localAdjustAttribution.trackerToken = paramJSONObject.optString("tracker_token", null);
      localAdjustAttribution.trackerName = paramJSONObject.optString("tracker_name", null);
      localAdjustAttribution.network = paramJSONObject.optString("network", null);
      localAdjustAttribution.campaign = paramJSONObject.optString("campaign", null);
      localAdjustAttribution.adgroup = paramJSONObject.optString("adgroup", null);
      localAdjustAttribution.creative = paramJSONObject.optString("creative", null);
    }
  }
  
  private void readObject(ObjectInputStream paramObjectInputStream)
    throws ClassNotFoundException, IOException
  {
    paramObjectInputStream.defaultReadObject();
  }
  
  private void writeObject(ObjectOutputStream paramObjectOutputStream)
    throws IOException
  {
    paramObjectOutputStream.defaultWriteObject();
  }
  
  public boolean equals(Object paramObject)
  {
    boolean bool = true;
    if (paramObject == this) {}
    for (;;)
    {
      return bool;
      if (paramObject == null)
      {
        bool = false;
      }
      else if (getClass() != paramObject.getClass())
      {
        bool = false;
      }
      else
      {
        paramObject = (AdjustAttribution)paramObject;
        if (!Util.equalString(this.trackerToken, ((AdjustAttribution)paramObject).trackerToken)) {
          bool = false;
        } else if (!Util.equalString(this.trackerName, ((AdjustAttribution)paramObject).trackerName)) {
          bool = false;
        } else if (!Util.equalString(this.network, ((AdjustAttribution)paramObject).network)) {
          bool = false;
        } else if (!Util.equalString(this.campaign, ((AdjustAttribution)paramObject).campaign)) {
          bool = false;
        } else if (!Util.equalString(this.adgroup, ((AdjustAttribution)paramObject).adgroup)) {
          bool = false;
        } else if (!Util.equalString(this.creative, ((AdjustAttribution)paramObject).creative)) {
          bool = false;
        }
      }
    }
  }
  
  public int hashCode()
  {
    return (((((Util.hashString(this.trackerToken) + 629) * 37 + Util.hashString(this.trackerName)) * 37 + Util.hashString(this.network)) * 37 + Util.hashString(this.campaign)) * 37 + Util.hashString(this.adgroup)) * 37 + Util.hashString(this.creative);
  }
  
  public String toString()
  {
    return String.format(Locale.US, "tt:%s tn:%s net:%s cam:%s adg:%s cre:%s", new Object[] { this.trackerToken, this.trackerName, this.network, this.campaign, this.adgroup, this.creative });
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\adjust\sdk\AdjustAttribution.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */