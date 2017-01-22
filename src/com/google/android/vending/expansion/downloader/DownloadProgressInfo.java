package com.google.android.vending.expansion.downloader;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class DownloadProgressInfo
  implements Parcelable
{
  public static final Parcelable.Creator<DownloadProgressInfo> CREATOR = new Parcelable.Creator()
  {
    public DownloadProgressInfo createFromParcel(Parcel paramAnonymousParcel)
    {
      return new DownloadProgressInfo(paramAnonymousParcel);
    }
    
    public DownloadProgressInfo[] newArray(int paramAnonymousInt)
    {
      return new DownloadProgressInfo[paramAnonymousInt];
    }
  };
  public float mCurrentSpeed;
  public long mOverallProgress;
  public long mOverallTotal;
  public long mTimeRemaining;
  
  public DownloadProgressInfo(long paramLong1, long paramLong2, long paramLong3, float paramFloat)
  {
    this.mOverallTotal = paramLong1;
    this.mOverallProgress = paramLong2;
    this.mTimeRemaining = paramLong3;
    this.mCurrentSpeed = paramFloat;
  }
  
  public DownloadProgressInfo(Parcel paramParcel)
  {
    this.mOverallTotal = paramParcel.readLong();
    this.mOverallProgress = paramParcel.readLong();
    this.mTimeRemaining = paramParcel.readLong();
    this.mCurrentSpeed = paramParcel.readFloat();
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeLong(this.mOverallTotal);
    paramParcel.writeLong(this.mOverallProgress);
    paramParcel.writeLong(this.mTimeRemaining);
    paramParcel.writeFloat(this.mCurrentSpeed);
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\vending\expansion\downloader\DownloadProgressInfo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */