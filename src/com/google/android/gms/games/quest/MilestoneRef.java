package com.google.android.gms.games.quest;

import android.os.Parcel;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.d;

public final class MilestoneRef
  extends d
  implements Milestone
{
  MilestoneRef(DataHolder paramDataHolder, int paramInt)
  {
    super(paramDataHolder, paramInt);
  }
  
  private long lI()
  {
    return getLong("initial_value");
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    return MilestoneEntity.a(this, paramObject);
  }
  
  public Milestone freeze()
  {
    return new MilestoneEntity(this);
  }
  
  public byte[] getCompletionRewardData()
  {
    return getByteArray("completion_reward_data");
  }
  
  public long getCurrentProgress()
  {
    long l2 = 0L;
    long l1 = l2;
    switch (getState())
    {
    default: 
      l1 = l2;
    }
    for (;;)
    {
      return l1;
      l1 = getTargetProgress();
      continue;
      l1 = getLong("current_value") - lI();
    }
  }
  
  public String getEventId()
  {
    return getString("external_event_id");
  }
  
  public String getMilestoneId()
  {
    return getString("external_milestone_id");
  }
  
  public int getState()
  {
    return getInteger("milestone_state");
  }
  
  public long getTargetProgress()
  {
    return getLong("target_value");
  }
  
  public int hashCode()
  {
    return MilestoneEntity.a(this);
  }
  
  public String toString()
  {
    return MilestoneEntity.b(this);
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    ((MilestoneEntity)freeze()).writeToParcel(paramParcel, paramInt);
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\games\quest\MilestoneRef.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */