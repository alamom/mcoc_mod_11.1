package com.google.android.vending.expansion.downloader;

import android.os.Messenger;

public abstract interface IDownloaderService
{
  public static final int FLAGS_DOWNLOAD_OVER_CELLULAR = 1;
  
  public abstract void onClientUpdated(Messenger paramMessenger);
  
  public abstract void requestAbortDownload();
  
  public abstract void requestContinueDownload();
  
  public abstract void requestDownloadStatus();
  
  public abstract void requestPauseDownload();
  
  public abstract void setDownloadFlags(int paramInt);
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\vending\expansion\downloader\IDownloaderService.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */