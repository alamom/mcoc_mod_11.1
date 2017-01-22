package com.google.android.gms.games;

import android.database.CharArrayBuffer;
import android.net.Uri;
import android.os.Parcel;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.d;
import com.google.android.gms.games.internal.player.MostRecentGameInfo;
import com.google.android.gms.games.internal.player.MostRecentGameInfoRef;
import com.google.android.gms.games.internal.player.PlayerColumnNames;

public final class PlayerRef
  extends d
  implements Player
{
  private final PlayerLevelInfo VP;
  private final PlayerColumnNames VY;
  private final MostRecentGameInfoRef VZ;
  
  public PlayerRef(DataHolder paramDataHolder, int paramInt)
  {
    this(paramDataHolder, paramInt, null);
  }
  
  public PlayerRef(DataHolder paramDataHolder, int paramInt, String paramString)
  {
    super(paramDataHolder, paramInt);
    this.VY = new PlayerColumnNames(paramString);
    this.VZ = new MostRecentGameInfoRef(paramDataHolder, paramInt, this.VY);
    if (jW())
    {
      int i = getInteger(this.VY.abc);
      paramInt = getInteger(this.VY.abf);
      paramString = new PlayerLevel(i, getLong(this.VY.abd), getLong(this.VY.abe));
      if (i == paramInt) {
        break label180;
      }
    }
    label180:
    for (paramDataHolder = new PlayerLevel(paramInt, getLong(this.VY.abe), getLong(this.VY.abg));; paramDataHolder = paramString) {
      for (this.VP = new PlayerLevelInfo(getLong(this.VY.abb), getLong(this.VY.abh), paramString, paramDataHolder);; this.VP = null) {
        return;
      }
    }
  }
  
  private boolean jW()
  {
    boolean bool = false;
    if (aS(this.VY.abb)) {}
    for (;;)
    {
      return bool;
      if (getLong(this.VY.abb) != -1L) {
        bool = true;
      }
    }
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    return PlayerEntity.a(this, paramObject);
  }
  
  public Player freeze()
  {
    return new PlayerEntity(this);
  }
  
  public String getDisplayName()
  {
    return getString(this.VY.aaT);
  }
  
  public void getDisplayName(CharArrayBuffer paramCharArrayBuffer)
  {
    a(this.VY.aaT, paramCharArrayBuffer);
  }
  
  public Uri getHiResImageUri()
  {
    return aR(this.VY.aaW);
  }
  
  public String getHiResImageUrl()
  {
    return getString(this.VY.aaX);
  }
  
  public Uri getIconImageUri()
  {
    return aR(this.VY.aaU);
  }
  
  public String getIconImageUrl()
  {
    return getString(this.VY.aaV);
  }
  
  public long getLastPlayedWithTimestamp()
  {
    if ((!aQ(this.VY.aba)) || (aS(this.VY.aba))) {}
    for (long l = -1L;; l = getLong(this.VY.aba)) {
      return l;
    }
  }
  
  public PlayerLevelInfo getLevelInfo()
  {
    return this.VP;
  }
  
  public String getPlayerId()
  {
    return getString(this.VY.aaS);
  }
  
  public long getRetrievedTimestamp()
  {
    return getLong(this.VY.aaY);
  }
  
  public String getTitle()
  {
    return getString(this.VY.abi);
  }
  
  public void getTitle(CharArrayBuffer paramCharArrayBuffer)
  {
    a(this.VY.abi, paramCharArrayBuffer);
  }
  
  public boolean hasHiResImage()
  {
    if (getHiResImageUri() != null) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public boolean hasIconImage()
  {
    if (getIconImageUri() != null) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public int hashCode()
  {
    return PlayerEntity.b(this);
  }
  
  public boolean isProfileVisible()
  {
    return getBoolean(this.VY.abk);
  }
  
  public int jU()
  {
    return getInteger(this.VY.aaZ);
  }
  
  public MostRecentGameInfo jV()
  {
    if (aS(this.VY.abl)) {}
    for (Object localObject = null;; localObject = this.VZ) {
      return (MostRecentGameInfo)localObject;
    }
  }
  
  public String toString()
  {
    return PlayerEntity.c(this);
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    ((PlayerEntity)freeze()).writeToParcel(paramParcel, paramInt);
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\games\PlayerRef.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */