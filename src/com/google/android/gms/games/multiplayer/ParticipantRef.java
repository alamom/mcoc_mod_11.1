package com.google.android.gms.games.multiplayer;

import android.database.CharArrayBuffer;
import android.net.Uri;
import android.os.Parcel;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.d;
import com.google.android.gms.games.Player;
import com.google.android.gms.games.PlayerRef;

public final class ParticipantRef
  extends d
  implements Participant
{
  private final PlayerRef aci;
  
  public ParticipantRef(DataHolder paramDataHolder, int paramInt)
  {
    super(paramDataHolder, paramInt);
    this.aci = new PlayerRef(paramDataHolder, paramInt);
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    return ParticipantEntity.a(this, paramObject);
  }
  
  public Participant freeze()
  {
    return new ParticipantEntity(this);
  }
  
  public int getCapabilities()
  {
    return getInteger("capabilities");
  }
  
  public String getDisplayName()
  {
    if (aS("external_player_id")) {}
    for (String str = getString("default_display_name");; str = this.aci.getDisplayName()) {
      return str;
    }
  }
  
  public void getDisplayName(CharArrayBuffer paramCharArrayBuffer)
  {
    if (aS("external_player_id")) {
      a("default_display_name", paramCharArrayBuffer);
    }
    for (;;)
    {
      return;
      this.aci.getDisplayName(paramCharArrayBuffer);
    }
  }
  
  public Uri getHiResImageUri()
  {
    if (aS("external_player_id")) {}
    for (Uri localUri = aR("default_display_hi_res_image_uri");; localUri = this.aci.getHiResImageUri()) {
      return localUri;
    }
  }
  
  public String getHiResImageUrl()
  {
    if (aS("external_player_id")) {}
    for (String str = getString("default_display_hi_res_image_url");; str = this.aci.getHiResImageUrl()) {
      return str;
    }
  }
  
  public Uri getIconImageUri()
  {
    if (aS("external_player_id")) {}
    for (Uri localUri = aR("default_display_image_uri");; localUri = this.aci.getIconImageUri()) {
      return localUri;
    }
  }
  
  public String getIconImageUrl()
  {
    if (aS("external_player_id")) {}
    for (String str = getString("default_display_image_url");; str = this.aci.getIconImageUrl()) {
      return str;
    }
  }
  
  public String getParticipantId()
  {
    return getString("external_participant_id");
  }
  
  public Player getPlayer()
  {
    if (aS("external_player_id")) {}
    for (Object localObject = null;; localObject = this.aci) {
      return (Player)localObject;
    }
  }
  
  public ParticipantResult getResult()
  {
    if (aS("result_type")) {}
    int j;
    int i;
    for (ParticipantResult localParticipantResult = null;; localParticipantResult = new ParticipantResult(getParticipantId(), j, i))
    {
      return localParticipantResult;
      j = getInteger("result_type");
      i = getInteger("placing");
    }
  }
  
  public int getStatus()
  {
    return getInteger("player_status");
  }
  
  public int hashCode()
  {
    return ParticipantEntity.a(this);
  }
  
  public boolean isConnectedToRoom()
  {
    if (getInteger("connected") > 0) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public String jX()
  {
    return getString("client_address");
  }
  
  public String toString()
  {
    return ParticipantEntity.b(this);
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    ((ParticipantEntity)freeze()).writeToParcel(paramParcel, paramInt);
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\games\multiplayer\ParticipantRef.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */