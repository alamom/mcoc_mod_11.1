package com.google.android.gms.games.multiplayer.realtime;

import android.database.CharArrayBuffer;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.common.internal.n.a;
import com.google.android.gms.games.Player;
import com.google.android.gms.games.internal.GamesDowngradeableSafeParcel;
import com.google.android.gms.games.multiplayer.Participant;
import com.google.android.gms.games.multiplayer.ParticipantEntity;
import com.google.android.gms.internal.jv;
import java.util.ArrayList;

public final class RoomEntity
  extends GamesDowngradeableSafeParcel
  implements Room
{
  public static final Parcelable.Creator<RoomEntity> CREATOR = new RoomEntityCreatorCompat();
  private final int BR;
  private final String Tr;
  private final String WQ;
  private final long abZ;
  private final ArrayList<ParticipantEntity> acc;
  private final int acd;
  private final Bundle acs;
  private final String acw;
  private final int acx;
  private final int acy;
  
  RoomEntity(int paramInt1, String paramString1, String paramString2, long paramLong, int paramInt2, String paramString3, int paramInt3, Bundle paramBundle, ArrayList<ParticipantEntity> paramArrayList, int paramInt4)
  {
    this.BR = paramInt1;
    this.WQ = paramString1;
    this.acw = paramString2;
    this.abZ = paramLong;
    this.acx = paramInt2;
    this.Tr = paramString3;
    this.acd = paramInt3;
    this.acs = paramBundle;
    this.acc = paramArrayList;
    this.acy = paramInt4;
  }
  
  public RoomEntity(Room paramRoom)
  {
    this.BR = 2;
    this.WQ = paramRoom.getRoomId();
    this.acw = paramRoom.getCreatorId();
    this.abZ = paramRoom.getCreationTimestamp();
    this.acx = paramRoom.getStatus();
    this.Tr = paramRoom.getDescription();
    this.acd = paramRoom.getVariant();
    this.acs = paramRoom.getAutoMatchCriteria();
    ArrayList localArrayList = paramRoom.getParticipants();
    int j = localArrayList.size();
    this.acc = new ArrayList(j);
    for (int i = 0; i < j; i++) {
      this.acc.add((ParticipantEntity)((Participant)localArrayList.get(i)).freeze());
    }
    this.acy = paramRoom.getAutoMatchWaitEstimateSeconds();
  }
  
  static int a(Room paramRoom)
  {
    return n.hashCode(new Object[] { paramRoom.getRoomId(), paramRoom.getCreatorId(), Long.valueOf(paramRoom.getCreationTimestamp()), Integer.valueOf(paramRoom.getStatus()), paramRoom.getDescription(), Integer.valueOf(paramRoom.getVariant()), paramRoom.getAutoMatchCriteria(), paramRoom.getParticipants(), Integer.valueOf(paramRoom.getAutoMatchWaitEstimateSeconds()) });
  }
  
  static int a(Room paramRoom, String paramString)
  {
    ArrayList localArrayList = paramRoom.getParticipants();
    int j = localArrayList.size();
    for (int i = 0; i < j; i++)
    {
      Participant localParticipant = (Participant)localArrayList.get(i);
      if (localParticipant.getParticipantId().equals(paramString)) {
        return localParticipant.getStatus();
      }
    }
    throw new IllegalStateException("Participant " + paramString + " is not in room " + paramRoom.getRoomId());
  }
  
  static boolean a(Room paramRoom, Object paramObject)
  {
    boolean bool2 = true;
    boolean bool1;
    if (!(paramObject instanceof Room)) {
      bool1 = false;
    }
    for (;;)
    {
      return bool1;
      bool1 = bool2;
      if (paramRoom != paramObject)
      {
        paramObject = (Room)paramObject;
        if ((n.equal(((Room)paramObject).getRoomId(), paramRoom.getRoomId())) && (n.equal(((Room)paramObject).getCreatorId(), paramRoom.getCreatorId())) && (n.equal(Long.valueOf(((Room)paramObject).getCreationTimestamp()), Long.valueOf(paramRoom.getCreationTimestamp()))) && (n.equal(Integer.valueOf(((Room)paramObject).getStatus()), Integer.valueOf(paramRoom.getStatus()))) && (n.equal(((Room)paramObject).getDescription(), paramRoom.getDescription())) && (n.equal(Integer.valueOf(((Room)paramObject).getVariant()), Integer.valueOf(paramRoom.getVariant()))) && (n.equal(((Room)paramObject).getAutoMatchCriteria(), paramRoom.getAutoMatchCriteria())) && (n.equal(((Room)paramObject).getParticipants(), paramRoom.getParticipants())))
        {
          bool1 = bool2;
          if (n.equal(Integer.valueOf(((Room)paramObject).getAutoMatchWaitEstimateSeconds()), Integer.valueOf(paramRoom.getAutoMatchWaitEstimateSeconds()))) {}
        }
        else
        {
          bool1 = false;
        }
      }
    }
  }
  
  static String b(Room paramRoom)
  {
    return n.h(paramRoom).a("RoomId", paramRoom.getRoomId()).a("CreatorId", paramRoom.getCreatorId()).a("CreationTimestamp", Long.valueOf(paramRoom.getCreationTimestamp())).a("RoomStatus", Integer.valueOf(paramRoom.getStatus())).a("Description", paramRoom.getDescription()).a("Variant", Integer.valueOf(paramRoom.getVariant())).a("AutoMatchCriteria", paramRoom.getAutoMatchCriteria()).a("Participants", paramRoom.getParticipants()).a("AutoMatchWaitEstimateSeconds", Integer.valueOf(paramRoom.getAutoMatchWaitEstimateSeconds())).toString();
  }
  
  static String b(Room paramRoom, String paramString)
  {
    ArrayList localArrayList = paramRoom.getParticipants();
    int j = localArrayList.size();
    int i = 0;
    Participant localParticipant;
    if (i < j)
    {
      localParticipant = (Participant)localArrayList.get(i);
      paramRoom = localParticipant.getPlayer();
      if ((paramRoom == null) || (!paramRoom.getPlayerId().equals(paramString))) {}
    }
    for (paramRoom = localParticipant.getParticipantId();; paramRoom = null)
    {
      return paramRoom;
      i++;
      break;
    }
  }
  
  static Participant c(Room paramRoom, String paramString)
  {
    ArrayList localArrayList = paramRoom.getParticipants();
    int j = localArrayList.size();
    for (int i = 0; i < j; i++)
    {
      Participant localParticipant = (Participant)localArrayList.get(i);
      if (localParticipant.getParticipantId().equals(paramString)) {
        return localParticipant;
      }
    }
    throw new IllegalStateException("Participant " + paramString + " is not in match " + paramRoom.getRoomId());
  }
  
  static ArrayList<String> c(Room paramRoom)
  {
    ArrayList localArrayList = paramRoom.getParticipants();
    int j = localArrayList.size();
    paramRoom = new ArrayList(j);
    for (int i = 0; i < j; i++) {
      paramRoom.add(((Participant)localArrayList.get(i)).getParticipantId());
    }
    return paramRoom;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    return a(this, paramObject);
  }
  
  public Room freeze()
  {
    return this;
  }
  
  public Bundle getAutoMatchCriteria()
  {
    return this.acs;
  }
  
  public int getAutoMatchWaitEstimateSeconds()
  {
    return this.acy;
  }
  
  public long getCreationTimestamp()
  {
    return this.abZ;
  }
  
  public String getCreatorId()
  {
    return this.acw;
  }
  
  public String getDescription()
  {
    return this.Tr;
  }
  
  public void getDescription(CharArrayBuffer paramCharArrayBuffer)
  {
    jv.b(this.Tr, paramCharArrayBuffer);
  }
  
  public Participant getParticipant(String paramString)
  {
    return c(this, paramString);
  }
  
  public String getParticipantId(String paramString)
  {
    return b(this, paramString);
  }
  
  public ArrayList<String> getParticipantIds()
  {
    return c(this);
  }
  
  public int getParticipantStatus(String paramString)
  {
    return a(this, paramString);
  }
  
  public ArrayList<Participant> getParticipants()
  {
    return new ArrayList(this.acc);
  }
  
  public String getRoomId()
  {
    return this.WQ;
  }
  
  public int getStatus()
  {
    return this.acx;
  }
  
  public int getVariant()
  {
    return this.acd;
  }
  
  public int getVersionCode()
  {
    return this.BR;
  }
  
  public int hashCode()
  {
    return a(this);
  }
  
  public boolean isDataValid()
  {
    return true;
  }
  
  public String toString()
  {
    return b(this);
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    if (!gQ()) {
      RoomEntityCreator.a(this, paramParcel, paramInt);
    }
    for (;;)
    {
      return;
      paramParcel.writeString(this.WQ);
      paramParcel.writeString(this.acw);
      paramParcel.writeLong(this.abZ);
      paramParcel.writeInt(this.acx);
      paramParcel.writeString(this.Tr);
      paramParcel.writeInt(this.acd);
      paramParcel.writeBundle(this.acs);
      int j = this.acc.size();
      paramParcel.writeInt(j);
      for (int i = 0; i < j; i++) {
        ((ParticipantEntity)this.acc.get(i)).writeToParcel(paramParcel, paramInt);
      }
    }
  }
  
  static final class RoomEntityCreatorCompat
    extends RoomEntityCreator
  {
    public RoomEntity co(Parcel paramParcel)
    {
      if ((RoomEntity.b(RoomEntity.jT())) || (RoomEntity.bw(RoomEntity.class.getCanonicalName()))) {}
      String str1;
      String str3;
      long l;
      int j;
      String str2;
      int m;
      Bundle localBundle;
      ArrayList localArrayList;
      for (paramParcel = super.co(paramParcel);; paramParcel = new RoomEntity(2, str1, str3, l, j, str2, m, localBundle, localArrayList, -1))
      {
        return paramParcel;
        str1 = paramParcel.readString();
        str3 = paramParcel.readString();
        l = paramParcel.readLong();
        j = paramParcel.readInt();
        str2 = paramParcel.readString();
        m = paramParcel.readInt();
        localBundle = paramParcel.readBundle();
        int k = paramParcel.readInt();
        localArrayList = new ArrayList(k);
        for (int i = 0; i < k; i++) {
          localArrayList.add(ParticipantEntity.CREATOR.createFromParcel(paramParcel));
        }
      }
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\games\multiplayer\realtime\RoomEntity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */