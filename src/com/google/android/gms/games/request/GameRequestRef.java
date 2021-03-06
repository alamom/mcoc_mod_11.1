package com.google.android.gms.games.request;

import android.os.Parcel;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.d;
import com.google.android.gms.games.Game;
import com.google.android.gms.games.GameRef;
import com.google.android.gms.games.Player;
import com.google.android.gms.games.PlayerRef;
import java.util.ArrayList;
import java.util.List;

public final class GameRequestRef
  extends d
  implements GameRequest
{
  private final int aaK;
  
  public GameRequestRef(DataHolder paramDataHolder, int paramInt1, int paramInt2)
  {
    super(paramDataHolder, paramInt1);
    this.aaK = paramInt2;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    return GameRequestEntity.a(this, paramObject);
  }
  
  public GameRequest freeze()
  {
    return new GameRequestEntity(this);
  }
  
  public long getCreationTimestamp()
  {
    return getLong("creation_timestamp");
  }
  
  public byte[] getData()
  {
    return getByteArray("data");
  }
  
  public long getExpirationTimestamp()
  {
    return getLong("expiration_timestamp");
  }
  
  public Game getGame()
  {
    return new GameRef(this.II, this.JX);
  }
  
  public int getRecipientStatus(String paramString)
  {
    int i = this.JX;
    int j;
    if (i < this.JX + this.aaK)
    {
      j = this.II.ar(i);
      if (!this.II.c("recipient_external_player_id", i, j).equals(paramString)) {}
    }
    for (i = this.II.b("recipient_status", i, j);; i = -1)
    {
      return i;
      i++;
      break;
    }
  }
  
  public List<Player> getRecipients()
  {
    ArrayList localArrayList = new ArrayList(this.aaK);
    for (int i = 0; i < this.aaK; i++) {
      localArrayList.add(new PlayerRef(this.II, this.JX + i, "recipient_"));
    }
    return localArrayList;
  }
  
  public String getRequestId()
  {
    return getString("external_request_id");
  }
  
  public Player getSender()
  {
    return new PlayerRef(this.II, gz(), "sender_");
  }
  
  public int getStatus()
  {
    return getInteger("status");
  }
  
  public int getType()
  {
    return getInteger("type");
  }
  
  public int hashCode()
  {
    return GameRequestEntity.a(this);
  }
  
  public boolean isConsumed(String paramString)
  {
    boolean bool = true;
    if (getRecipientStatus(paramString) == 1) {}
    for (;;)
    {
      return bool;
      bool = false;
    }
  }
  
  public String toString()
  {
    return GameRequestEntity.c(this);
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    ((GameRequestEntity)freeze()).writeToParcel(paramParcel, paramInt);
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\games\request\GameRequestRef.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */