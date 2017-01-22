package com.google.android.gms.games.request;

import android.os.Bundle;
import android.os.Parcel;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.common.internal.n.a;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.games.Game;
import com.google.android.gms.games.GameEntity;
import com.google.android.gms.games.Player;
import com.google.android.gms.games.PlayerEntity;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class GameRequestEntity
  implements SafeParcelable, GameRequest
{
  public static final GameRequestEntityCreator CREATOR = new GameRequestEntityCreator();
  private final int BR;
  private final int FD;
  private final int Fa;
  private final String XC;
  private final GameEntity aay;
  private final long abZ;
  private final byte[] acH;
  private final PlayerEntity adc;
  private final ArrayList<PlayerEntity> add;
  private final long ade;
  private final Bundle adf;
  
  GameRequestEntity(int paramInt1, GameEntity paramGameEntity, PlayerEntity paramPlayerEntity, byte[] paramArrayOfByte, String paramString, ArrayList<PlayerEntity> paramArrayList, int paramInt2, long paramLong1, long paramLong2, Bundle paramBundle, int paramInt3)
  {
    this.BR = paramInt1;
    this.aay = paramGameEntity;
    this.adc = paramPlayerEntity;
    this.acH = paramArrayOfByte;
    this.XC = paramString;
    this.add = paramArrayList;
    this.FD = paramInt2;
    this.abZ = paramLong1;
    this.ade = paramLong2;
    this.adf = paramBundle;
    this.Fa = paramInt3;
  }
  
  public GameRequestEntity(GameRequest paramGameRequest)
  {
    this.BR = 2;
    this.aay = new GameEntity(paramGameRequest.getGame());
    this.adc = new PlayerEntity(paramGameRequest.getSender());
    this.XC = paramGameRequest.getRequestId();
    this.FD = paramGameRequest.getType();
    this.abZ = paramGameRequest.getCreationTimestamp();
    this.ade = paramGameRequest.getExpirationTimestamp();
    this.Fa = paramGameRequest.getStatus();
    Object localObject = paramGameRequest.getData();
    if (localObject == null) {
      this.acH = null;
    }
    for (;;)
    {
      List localList = paramGameRequest.getRecipients();
      int j = localList.size();
      this.add = new ArrayList(j);
      this.adf = new Bundle();
      for (int i = 0; i < j; i++)
      {
        localObject = (Player)((Player)localList.get(i)).freeze();
        String str = ((Player)localObject).getPlayerId();
        this.add.add((PlayerEntity)localObject);
        this.adf.putInt(str, paramGameRequest.getRecipientStatus(str));
      }
      this.acH = new byte[localObject.length];
      System.arraycopy(localObject, 0, this.acH, 0, localObject.length);
    }
  }
  
  static int a(GameRequest paramGameRequest)
  {
    return n.hashCode(new Object[] { paramGameRequest.getGame(), paramGameRequest.getRecipients(), paramGameRequest.getRequestId(), paramGameRequest.getSender(), b(paramGameRequest), Integer.valueOf(paramGameRequest.getType()), Long.valueOf(paramGameRequest.getCreationTimestamp()), Long.valueOf(paramGameRequest.getExpirationTimestamp()) });
  }
  
  static boolean a(GameRequest paramGameRequest, Object paramObject)
  {
    boolean bool2 = true;
    boolean bool1;
    if (!(paramObject instanceof GameRequest)) {
      bool1 = false;
    }
    for (;;)
    {
      return bool1;
      bool1 = bool2;
      if (paramGameRequest != paramObject)
      {
        paramObject = (GameRequest)paramObject;
        if ((n.equal(((GameRequest)paramObject).getGame(), paramGameRequest.getGame())) && (n.equal(((GameRequest)paramObject).getRecipients(), paramGameRequest.getRecipients())) && (n.equal(((GameRequest)paramObject).getRequestId(), paramGameRequest.getRequestId())) && (n.equal(((GameRequest)paramObject).getSender(), paramGameRequest.getSender())) && (Arrays.equals(b((GameRequest)paramObject), b(paramGameRequest))) && (n.equal(Integer.valueOf(((GameRequest)paramObject).getType()), Integer.valueOf(paramGameRequest.getType()))) && (n.equal(Long.valueOf(((GameRequest)paramObject).getCreationTimestamp()), Long.valueOf(paramGameRequest.getCreationTimestamp()))))
        {
          bool1 = bool2;
          if (n.equal(Long.valueOf(((GameRequest)paramObject).getExpirationTimestamp()), Long.valueOf(paramGameRequest.getExpirationTimestamp()))) {}
        }
        else
        {
          bool1 = false;
        }
      }
    }
  }
  
  private static int[] b(GameRequest paramGameRequest)
  {
    List localList = paramGameRequest.getRecipients();
    int j = localList.size();
    int[] arrayOfInt = new int[j];
    for (int i = 0; i < j; i++) {
      arrayOfInt[i] = paramGameRequest.getRecipientStatus(((Player)localList.get(i)).getPlayerId());
    }
    return arrayOfInt;
  }
  
  static String c(GameRequest paramGameRequest)
  {
    return n.h(paramGameRequest).a("Game", paramGameRequest.getGame()).a("Sender", paramGameRequest.getSender()).a("Recipients", paramGameRequest.getRecipients()).a("Data", paramGameRequest.getData()).a("RequestId", paramGameRequest.getRequestId()).a("Type", Integer.valueOf(paramGameRequest.getType())).a("CreationTimestamp", Long.valueOf(paramGameRequest.getCreationTimestamp())).a("ExpirationTimestamp", Long.valueOf(paramGameRequest.getExpirationTimestamp())).toString();
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    return a(this, paramObject);
  }
  
  public GameRequest freeze()
  {
    return this;
  }
  
  public long getCreationTimestamp()
  {
    return this.abZ;
  }
  
  public byte[] getData()
  {
    return this.acH;
  }
  
  public long getExpirationTimestamp()
  {
    return this.ade;
  }
  
  public Game getGame()
  {
    return this.aay;
  }
  
  public int getRecipientStatus(String paramString)
  {
    return this.adf.getInt(paramString, 0);
  }
  
  public List<Player> getRecipients()
  {
    return new ArrayList(this.add);
  }
  
  public String getRequestId()
  {
    return this.XC;
  }
  
  public Player getSender()
  {
    return this.adc;
  }
  
  public int getStatus()
  {
    return this.Fa;
  }
  
  public int getType()
  {
    return this.FD;
  }
  
  public int getVersionCode()
  {
    return this.BR;
  }
  
  public int hashCode()
  {
    return a(this);
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
  
  public boolean isDataValid()
  {
    return true;
  }
  
  public Bundle lL()
  {
    return this.adf;
  }
  
  public String toString()
  {
    return c(this);
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    GameRequestEntityCreator.a(this, paramParcel, paramInt);
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\games\request\GameRequestEntity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */