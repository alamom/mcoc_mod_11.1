package com.google.android.gms.location;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import com.google.android.gms.common.internal.o;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class ActivityRecognitionResult
  implements SafeParcelable
{
  public static final ActivityRecognitionResultCreator CREATOR = new ActivityRecognitionResultCreator();
  public static final String EXTRA_ACTIVITY_RESULT = "com.google.android.location.internal.EXTRA_ACTIVITY_RESULT";
  private final int BR;
  List<DetectedActivity> aeb;
  long aec;
  long aed;
  
  public ActivityRecognitionResult(int paramInt, List<DetectedActivity> paramList, long paramLong1, long paramLong2)
  {
    this.BR = 1;
    this.aeb = paramList;
    this.aec = paramLong1;
    this.aed = paramLong2;
  }
  
  public ActivityRecognitionResult(DetectedActivity paramDetectedActivity, long paramLong1, long paramLong2)
  {
    this(Collections.singletonList(paramDetectedActivity), paramLong1, paramLong2);
  }
  
  public ActivityRecognitionResult(List<DetectedActivity> paramList, long paramLong1, long paramLong2)
  {
    if ((paramList != null) && (paramList.size() > 0)) {}
    for (boolean bool1 = true;; bool1 = false)
    {
      o.b(bool1, "Must have at least 1 detected activity");
      bool1 = bool2;
      if (paramLong1 > 0L)
      {
        bool1 = bool2;
        if (paramLong2 > 0L) {
          bool1 = true;
        }
      }
      o.b(bool1, "Must set times");
      this.BR = 1;
      this.aeb = paramList;
      this.aec = paramLong1;
      this.aed = paramLong2;
      return;
    }
  }
  
  public static ActivityRecognitionResult extractResult(Intent paramIntent)
  {
    if (!hasResult(paramIntent)) {
      paramIntent = null;
    }
    for (;;)
    {
      return paramIntent;
      paramIntent = paramIntent.getExtras().get("com.google.android.location.internal.EXTRA_ACTIVITY_RESULT");
      if ((paramIntent instanceof byte[]))
      {
        Parcel localParcel = Parcel.obtain();
        localParcel.unmarshall((byte[])paramIntent, 0, ((byte[])paramIntent).length);
        localParcel.setDataPosition(0);
        paramIntent = CREATOR.createFromParcel(localParcel);
      }
      else if ((paramIntent instanceof ActivityRecognitionResult))
      {
        paramIntent = (ActivityRecognitionResult)paramIntent;
      }
      else
      {
        paramIntent = null;
      }
    }
  }
  
  public static boolean hasResult(Intent paramIntent)
  {
    if (paramIntent == null) {}
    for (boolean bool = false;; bool = paramIntent.hasExtra("com.google.android.location.internal.EXTRA_ACTIVITY_RESULT")) {
      return bool;
    }
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public int getActivityConfidence(int paramInt)
  {
    Iterator localIterator = this.aeb.iterator();
    DetectedActivity localDetectedActivity;
    do
    {
      if (!localIterator.hasNext()) {
        break;
      }
      localDetectedActivity = (DetectedActivity)localIterator.next();
    } while (localDetectedActivity.getType() != paramInt);
    for (paramInt = localDetectedActivity.getConfidence();; paramInt = 0) {
      return paramInt;
    }
  }
  
  public long getElapsedRealtimeMillis()
  {
    return this.aed;
  }
  
  public DetectedActivity getMostProbableActivity()
  {
    return (DetectedActivity)this.aeb.get(0);
  }
  
  public List<DetectedActivity> getProbableActivities()
  {
    return this.aeb;
  }
  
  public long getTime()
  {
    return this.aec;
  }
  
  public int getVersionCode()
  {
    return this.BR;
  }
  
  public String toString()
  {
    return "ActivityRecognitionResult [probableActivities=" + this.aeb + ", timeMillis=" + this.aec + ", elapsedRealtimeMillis=" + this.aed + "]";
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    ActivityRecognitionResultCreator.a(this, paramParcel, paramInt);
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\location\ActivityRecognitionResult.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */