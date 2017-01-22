package com.google.android.vending.expansion.downloader.impl;

import android.util.Log;
import com.google.android.vending.expansion.downloader.Helpers;
import java.util.Random;

public class DownloadInfo
{
  public int mControl;
  public long mCurrentBytes;
  public String mETag;
  public final String mFileName;
  public int mFuzz = Helpers.sRandom.nextInt(1001);
  public final int mIndex;
  boolean mInitialized;
  public long mLastMod;
  public int mNumFailed;
  public int mRedirectCount;
  public int mRetryAfter;
  public int mStatus;
  public long mTotalBytes;
  public String mUri;
  
  public DownloadInfo(int paramInt, String paramString1, String paramString2)
  {
    this.mFileName = paramString1;
    this.mIndex = paramInt;
  }
  
  public void logVerboseInfo()
  {
    Log.v("LVLDL", "Service adding new entry");
    Log.v("LVLDL", "FILENAME: " + this.mFileName);
    Log.v("LVLDL", "URI     : " + this.mUri);
    Log.v("LVLDL", "FILENAME: " + this.mFileName);
    Log.v("LVLDL", "CONTROL : " + this.mControl);
    Log.v("LVLDL", "STATUS  : " + this.mStatus);
    Log.v("LVLDL", "FAILED_C: " + this.mNumFailed);
    Log.v("LVLDL", "RETRY_AF: " + this.mRetryAfter);
    Log.v("LVLDL", "REDIRECT: " + this.mRedirectCount);
    Log.v("LVLDL", "LAST_MOD: " + this.mLastMod);
    Log.v("LVLDL", "TOTAL   : " + this.mTotalBytes);
    Log.v("LVLDL", "CURRENT : " + this.mCurrentBytes);
    Log.v("LVLDL", "ETAG    : " + this.mETag);
  }
  
  public void resetDownload()
  {
    this.mCurrentBytes = 0L;
    this.mETag = "";
    this.mLastMod = 0L;
    this.mStatus = 0;
    this.mControl = 0;
    this.mNumFailed = 0;
    this.mRetryAfter = 0;
    this.mRedirectCount = 0;
  }
  
  public long restartTime(long paramLong)
  {
    if (this.mNumFailed == 0) {}
    for (;;)
    {
      return paramLong;
      if (this.mRetryAfter > 0) {
        paramLong = this.mLastMod + this.mRetryAfter;
      } else {
        paramLong = this.mLastMod + (this.mFuzz + 1000) * 30 * (1 << this.mNumFailed - 1);
      }
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\vending\expansion\downloader\impl\DownloadInfo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */