package com.google.android.gms.games.internal.request;

import android.os.Parcel;
import com.google.android.gms.common.internal.a;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.games.Game;
import com.google.android.gms.games.Player;
import com.google.android.gms.games.request.GameRequest;
import com.google.android.gms.games.request.GameRequestEntity;
import java.util.ArrayList;

public final class GameRequestCluster
  implements SafeParcelable, GameRequest
{
  public static final GameRequestClusterCreator CREATOR = new GameRequestClusterCreator();
  private final int BR;
  private final ArrayList<GameRequestEntity> abr;
  
  GameRequestCluster(int paramInt, ArrayList<GameRequestEntity> paramArrayList)
  {
    this.BR = paramInt;
    this.abr = paramArrayList;
    li();
  }
  
  private void li()
  {
    GameRequest localGameRequest2;
    int i;
    label39:
    GameRequest localGameRequest1;
    if (!this.abr.isEmpty())
    {
      bool = true;
      a.I(bool);
      localGameRequest2 = (GameRequest)this.abr.get(0);
      int j = this.abr.size();
      i = 1;
      if (i >= j) {
        return;
      }
      localGameRequest1 = (GameRequest)this.abr.get(i);
      if (localGameRequest2.getType() != localGameRequest1.getType()) {
        break label115;
      }
    }
    label115:
    for (boolean bool = true;; bool = false)
    {
      a.a(bool, "All the requests must be of the same type");
      a.a(localGameRequest2.getSender().equals(localGameRequest1.getSender()), "All the requests must be from the same sender");
      i++;
      break label39;
      bool = false;
      break;
    }
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    boolean bool;
    if (!(paramObject instanceof GameRequestCluster)) {
      bool = false;
    }
    for (;;)
    {
      return bool;
      if (this == paramObject)
      {
        bool = true;
      }
      else
      {
        paramObject = (GameRequestCluster)paramObject;
        if (((GameRequestCluster)paramObject).abr.size() != this.abr.size())
        {
          bool = false;
        }
        else
        {
          int j = this.abr.size();
          for (int i = 0;; i++)
          {
            if (i >= j) {
              break label107;
            }
            if (!((GameRequest)this.abr.get(i)).equals((GameRequest)((GameRequestCluster)paramObject).abr.get(i)))
            {
              bool = false;
              break;
            }
          }
          label107:
          bool = true;
        }
      }
    }
  }
  
  public GameRequest freeze()
  {
    return this;
  }
  
  public long getCreationTimestamp()
  {
    throw new UnsupportedOperationException("Method not supported on a cluster");
  }
  
  public byte[] getData()
  {
    throw new UnsupportedOperationException("Method not supported on a cluster");
  }
  
  public long getExpirationTimestamp()
  {
    throw new UnsupportedOperationException("Method not supported on a cluster");
  }
  
  public Game getGame()
  {
    throw new UnsupportedOperationException("Method not supported on a cluster");
  }
  
  public int getRecipientStatus(String paramString)
  {
    throw new UnsupportedOperationException("Method not supported on a cluster");
  }
  
  public String getRequestId()
  {
    return ((GameRequestEntity)this.abr.get(0)).getRequestId();
  }
  
  public Player getSender()
  {
    return ((GameRequestEntity)this.abr.get(0)).getSender();
  }
  
  public int getStatus()
  {
    throw new UnsupportedOperationException("Method not supported on a cluster");
  }
  
  public int getType()
  {
    return ((GameRequestEntity)this.abr.get(0)).getType();
  }
  
  public int getVersionCode()
  {
    return this.BR;
  }
  
  public int hashCode()
  {
    return n.hashCode(this.abr.toArray());
  }
  
  public boolean isConsumed(String paramString)
  {
    throw new UnsupportedOperationException("Method not supported on a cluster");
  }
  
  public boolean isDataValid()
  {
    return true;
  }
  
  public ArrayList<GameRequest> lw()
  {
    return new ArrayList(this.abr);
  }
  
  public ArrayList<Player> lx()
  {
    throw new UnsupportedOperationException("Method not supported on a cluster");
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    GameRequestClusterCreator.a(this, paramParcel, paramInt);
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\games\internal\request\GameRequestCluster.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */